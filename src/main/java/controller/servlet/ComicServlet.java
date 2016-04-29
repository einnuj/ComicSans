package controller.servlet;

import com.google.appengine.api.users.UserServiceFactory;
import controller.data.ComicAccess;
import controller.data.UserAccess;
import controller.exceptions.ComicNotFoundException;
import controller.exceptions.NonUniqueGoogleIdException;
import controller.exceptions.NonUniqueLongIdException;
import controller.exceptions.UserNotFoundException;
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

/**
 * A Servlet that will respond with a WebComic class.
 * Created by einnuj on 4/12/2016.
 */

public class ComicServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action) {
            case "CREATE CHAPTER":
                Long id = Long.valueOf(req.getParameter("id"));

                try {
                    WebComic comic = ComicAccess.queryForComic(id);

                    if (comic == null) {
                        throw new ComicNotFoundException(id);
                    }

                    String name = req.getParameter("name");
                    String file = req.getParameter("files");

                    ComicChapter newChapter = new ComicChapter(name);
                    newChapter.addToChildMediaList(new ComicPage("fileName", file));

                    comic.addToChildMediaList(newChapter);

                    ObjectifyHelper.save(comic);

                    comic.reload();
                    comic.getMetadata().reload();
                }
                catch (NonUniqueLongIdException | ComicNotFoundException ex) {
                    resp.getWriter().write("{\"error\":" + ex.getMessage() + "}");

                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
                break;

            case "CREATE COMIC":
                com.google.appengine.api.users.User googleUser = UserServiceFactory.getUserService().getCurrentUser();

                resp.setContentType("application/json");

                if (googleUser != null) {
                    try {
                        User author = UserAccess.queryForUser(googleUser.getUserId());

                        if (author == null) {
                            throw new UserNotFoundException(googleUser.getUserId());
                        }

                        String name = req.getParameter("name");
                        String description = req.getParameter("description");

                        WebComic newComic = new WebComic(name, author.getMetadata().getName());
                        newComic.getMetadata().setBio(description);

                        ObjectifyHelper.save(newComic);         // Gets us the Long id
                        newComic.reload();

                        // Update User!
                        author.getMetadata().addToComicsCreated(newComic);

                        ObjectifyHelper.save(newComic);
                        ObjectifyHelper.save(author);

                        newComic.reload();

                        resp.getWriter().write(JsonHelper.objectToJson(newComic));
                        resp.setStatus(HttpServletResponse.SC_OK);
                    }
                    catch (NonUniqueGoogleIdException | UserNotFoundException ex) {
                        resp.getWriter().write("{\"error\":" + ex.getMessage() + "}");

                        resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    }
                }
                else {
                    resp.getWriter().write("{\"error\":\"No User Logged In\"}");

                    resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
                break;

        }
    }

    /**
     * Returns to the caller a JSON representation of the WebComic queried for via Long id.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));

        if (id != null) {
            //resp.setContentType("application/json");

            try {
                WebComic webComic = ComicAccess.queryForComic(id);

                if (webComic == null) {
                    throw new ComicNotFoundException(id);
                }

                resp.getWriter().write(JsonHelper.objectToJson(webComic));
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.setContentType("application/json");
            } catch (NonUniqueLongIdException | ComicNotFoundException ex) {
                resp.getWriter().write("{\"error\":" + ex.getMessage() + "}");

                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
        else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
