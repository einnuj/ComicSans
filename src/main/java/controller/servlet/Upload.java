package controller.servlet;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;
import com.google.appengine.api.users.UserServiceFactory;
import controller.data.ComicAccess;
import controller.data.UserAccess;
import controller.exceptions.NonUniqueGoogleIdException;
import controller.exceptions.ParameterNotFoundException;
import controller.exceptions.UserNotFoundException;
import model.comics.ComicChapter;
import model.comics.ComicPage;
import model.comics.WebComic;
import model.users.User;
import utilities.data.ObjectifyHelper;

/**
 * Created by cherrinkim on 5/15/16.
 */
public class Upload extends HttpServlet{

     private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

        @Override
        public void doPost(HttpServletRequest req, HttpServletResponse resp)
                throws ServletException, IOException {
            String action = req.getParameter("action");

            switch(action) {
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
                            }
                            else {
                                newComic = new WebComic(name, author.getId().toString(), genre);

                            }

                            //setting parameters
                            String cover = blobs.get("file").get(0).getKeyString();
                            newComic.getMetadata().setCoverImage(cover);
                            newComic.getMetadata().setBio(summary);

                            ObjectifyHelper.save(newComic);         // Gets us the Long id
                            newComic.reload();

                            // Update User!
                            author.getMetadata().addToComicsCreated(newComic);

                            ObjectifyHelper.save(newComic);
                            ObjectifyHelper.save(author);

                            newComic.reload();

                            resp.getWriter().write(newComic.getId().toString());
                            break;

                        }
                        catch (NonUniqueGoogleIdException | UserNotFoundException ex) {
                            resp.getWriter().write("{\"error\":" + ex.getMessage() + "}");

                            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        }
                        catch (ParameterNotFoundException ex) {
                            resp.getWriter().write("{\"error\":" + ex.getMessage() + "}");

                            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                        }
                    }
                    else {
                        resp.getWriter().write("{\"error\":\"No User Logged In\"}");

                        resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    }
                    break;

                case "CREATE CHAPTER":
                    try {
                        Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);

                        String title = req.getParameter("chapterTitle");
                        String comicId = req.getParameter("comicId");

                        if (title == null) {
                            throw new ParameterNotFoundException("title");
                        }
                        if (comicId == null) {
                            throw new ParameterNotFoundException("comicId");
                        }

                        Long id = Long.valueOf(comicId);
                        WebComic comic = ComicAccess.queryForComic(id);

                        int numChapter = comic.getChildMediaList().size();

                        ComicChapter newChapter = new ComicChapter(title);
                        newChapter.setChapterNumber(numChapter + 1);

                        for (String n : blobs.keySet()) {
                            String file = blobs.get(n).get(0).getKeyString();
                            ComicPage newPage = new ComicPage(n, file);
                            newChapter.addToChildMediaList(newPage);
                        }


                        comic.addToChildMediaList(newChapter);

                        newChapter.reload();

                        ObjectifyHelper.save(comic);

                        comic.reload();
                        comic.getMetadata().reload();
                        break;

                        //res.sendRedirect("/upload?blob_key=" + blobs.get("file0").get(0).getKeyString());
                    } catch (ParameterNotFoundException ex) {
                        resp.getWriter().write("{\"error\":" + ex.getMessage() + "}");

                        resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                    }


            }

        }

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String action = req.getParameter("action");
        switch(action) {
            case "GET COMICID":

                break;
            case "GET IMAGE":
                try {
                    String blob_key_string = req.getParameter("blob_key");

                    if (blob_key_string == null) {
                        throw new ParameterNotFoundException("blob key");
                    }
                    System.out.println("BLOBKEY: " + blob_key_string);
                    if (blob_key_string == null) {
                        System.out.println("No blobkey given");
                    } else {
                        BlobKey blob_key = new BlobKey(blob_key_string);
                        ImagesService imagesService = ImagesServiceFactory.getImagesService();
                        ServingUrlOptions options = ServingUrlOptions.Builder.withBlobKey(blob_key);
                        String imageUrl = imagesService.getServingUrl(options);
                        System.out.println(imageUrl);
                    }
                } catch (ParameterNotFoundException ex) {
                    resp.getWriter().write("{\"error\":" + ex.getMessage() + "}");

                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                }
                break;
        }
    }

}
