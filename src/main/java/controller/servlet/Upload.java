package controller.servlet;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;
import com.google.appengine.api.users.UserServiceFactory;
import controller.data.ComicAccess;
import controller.data.UserAccess;
import controller.exceptions.*;
import model.comics.ComicChapter;
import model.comics.ComicPage;
import model.comics.WebComic;
import model.users.User;
import utilities.JsonHelper;
import utilities.data.ObjectifyHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by cherrinkim on 5/15/16.
 */
public class Upload extends HttpServlet {

    private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action) {
            case "CREATE COMIC":

                com.google.appengine.api.users.User googleUser = UserServiceFactory.getUserService().getCurrentUser();

                if (googleUser != null) {
                    try {
                        User author = UserAccess.queryForUser(googleUser.getUserId());
                        String name = req.getParameter("title");
                        String summary = req.getParameter("summary");
                        String genre = req.getParameter("genre");

                        Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);

                        WebComic newComic;

                        if (author == null) {
                            throw new UserNotFoundException(googleUser.getUserId());
                        }
                        if (name == null) {
                            throw new ParameterNotFoundException("name");
                        }
                        if (summary == null) {
                            throw new ParameterNotFoundException("summary");
                        }
                        if (genre == null) {
                            throw new ParameterNotFoundException("genre");
                        } else {
                            newComic = new WebComic(name, author.getGoogleId(), genre);

                        }

                        //setting parameters
                        String cover = blobs.get("file").get(0).getKeyString();
                        newComic.getMetadata().setCoverImage(cover);
                        newComic.getMetadata().setBio(summary);

                        ComicChapter newChapter = new ComicChapter("Comic");
                        newComic.addToChildMediaList(newChapter);

                        ObjectifyHelper.save(newComic);         // Gets us the Long id
                        newComic.reload();

                        // Update User!
                        author.getMetadata().addToComicsCreated(newComic);

                        ObjectifyHelper.save(newComic);
                        ObjectifyHelper.save(author);

                        newChapter.reload();
                        newComic.reload();

                        resp.getWriter().write(newComic.getId().toString());
                        break;

                    } catch (NonUniqueGoogleIdException | UserNotFoundException ex) {
                        resp.getWriter().write("{\"error\":" + ex.getMessage() + "}");

                        resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    } catch (ParameterNotFoundException ex) {
                        resp.getWriter().write("{\"error\":" + ex.getMessage() + "}");

                        resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                    }
                } else {
                    resp.getWriter().write("{\"error\":\"No User Logged In\"}");

                    resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
                break;

            case "ADD ISSUE":
                try {
                    Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);

                    String title = req.getParameter("issueTitle");
                    String comicId = req.getParameter("comicId");

                    if (title == null) {
                        throw new ParameterNotFoundException("issueTitle");
                    }
                    if (comicId == null) {
                        throw new ParameterNotFoundException("comicId");
                    }

                    Long id = Long.valueOf(comicId);
                    WebComic comic = ComicAccess.queryForComic(id);
                    ComicChapter myChapter = comic.getChildMediaList().get(0);

                    if (!isUniqueName(myChapter.getChildMediaList(), title)) {
                        throw new RepeatTitleException(title);
                    }

                    for (String n : blobs.keySet()) {
                        String file = blobs.get(n).get(0).getKeyString();
                        ComicPage newPage = new ComicPage(title, file);
                        myChapter.addToChildMediaList(newPage);
                    }

                    myChapter.reload();
                    ObjectifyHelper.save(comic);

                    comic.reload();
                    comic.getMetadata().reload();

                    resp.setStatus(HttpServletResponse.SC_OK);
                    resp.getWriter().write(comic.getId().toString());

                    //res.sendRedirect("/upload?blob_key=" + blobs.get("file0").get(0).getKeyString());
                } catch (ParameterNotFoundException | RepeatTitleException ex) {
                    resp.getWriter().write("{\"error\":" + ex.getMessage() + "}");

                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                }
                break;
            case "DELETE ISSUE":
                try {
                    String comicId = req.getParameter("comicId");
                    String issueTitle = req.getParameter("issueTitle");

                    if (comicId == null) {
                        throw new ParameterNotFoundException("comicId");
                    }
                    if (issueTitle == null) {
                        throw new ParameterNotFoundException("comicId");
                    }

                        Long id = Long.valueOf(comicId);
                        WebComic comic = ComicAccess.queryForComic(id);
                        ComicChapter chapter = comic.getChildMediaList().get(0);
                        boolean success = chapter.removeFromChildMediaList(issueTitle);
                        if(!success) throw new ParameterNotFoundException("Issue named" + issueTitle);

                        if(chapter.getChildMediaList().size() == 0){
                            comic.removeFromChildMediaList(chapter);
                            ComicChapter newChapter = new ComicChapter("Comic");
                            comic.addToChildMediaList(newChapter);
                            newChapter.reload();
                        }

                        ObjectifyHelper.save(comic);

                        comic.reload();
                        chapter.reload();
                        ObjectifyHelper.save(comic);
                        resp.getWriter().write("Success");

                        break;


                } catch (ParameterNotFoundException ex) {
                    resp.getWriter().write("{\"error\":" + ex.getMessage() + "}");

                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                }

        }

    }
/*
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        BlobKey blobKey = new BlobKey(req.getParameter("blob_key"));
        blobstoreService.serve(blobKey, res);
    }
*/

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String action = req.getParameter("action");
        switch(action) {
            case "GET ISSUE NAMES":
                try {
                    String comicId = req.getParameter("comicId");

                    if(comicId == null) {
                        throw new ParameterNotFoundException("comicId");
                    }

                    Long id = Long.valueOf(comicId);
                    WebComic comic = ComicAccess.queryForComic(id);
                    ComicChapter myChapter = comic.getChildMediaList().get(0);
                    ArrayList<String> issueList = new ArrayList<String>();
                    for(ComicPage page : myChapter.getChildMediaList()){
                        issueList.add(page.getName());
                    }

                    //String issueNames = new Gson().toJson(issueList);
                    //resp.setContentType("application/json");
                    resp.getWriter().write(JsonHelper.objectToJson(issueList));
                    break;

                } catch (ParameterNotFoundException ex) {
                    resp.getWriter().write("{\"error\":" + ex.getMessage() + "}");

                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                }
                break;
            case "GET IMAGE":
                try {
                    String blob_key_string = req.getParameter("blob_key");

                    if (blob_key_string == null) {
                        throw new ParameterNotFoundException("blob key");
                    }
                    else if (blob_key_string.isEmpty() || blob_key_string.equals("undefined")) {
                        return;
                    }
                    else
                    {
                        BlobKey blob_key = new BlobKey(blob_key_string);
                        ImagesService imagesService = ImagesServiceFactory.getImagesService();

                        ServingUrlOptions options = ServingUrlOptions.Builder.withBlobKey(blob_key).imageSize(1600);

                        String imageUrl = imagesService.getServingUrl(options);
                        resp.getWriter().write(imageUrl);
                    }
                } catch (ParameterNotFoundException ex) {
                    resp.getWriter().write("{\"error\":" + ex.getMessage() + "}");

                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                }
                break;
        }
    }

    private boolean isUniqueName(List<ComicPage> pageList, String name) {
        for (ComicPage page : pageList) {
            if (page.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }

}