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
import controller.data.ComicAccess;
import controller.exceptions.ParameterNotFoundException;
import controller.exceptions.UserNotFoundException;
import model.comics.ComicChapter;
import model.comics.ComicPage;
import model.comics.WebComic;
import utilities.data.ObjectifyHelper;

/**
 * Created by cherrinkim on 5/15/16.
 */
public class Upload extends HttpServlet{

     private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

        @Override
        public void doPost(HttpServletRequest req, HttpServletResponse res)
                throws ServletException, IOException {
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

                //res.sendRedirect("/upload?blob_key=" + blobs.get("file0").get(0).getKeyString());
            } catch (ParameterNotFoundException ex) {
                res.getWriter().write("{\"error\":" + ex.getMessage() + "}");

                res.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }

        }

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
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
    }

}
