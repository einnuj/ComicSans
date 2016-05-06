package controller.servlet;

import com.google.appengine.api.users.UserServiceFactory;
import controller.data.ComicAccess;
import controller.data.UserAccess;
import controller.exceptions.ComicNotFoundException;
import controller.exceptions.NonUniqueGoogleIdException;
import controller.exceptions.ParameterNotFoundException;
import controller.exceptions.UserNotFoundException;
import model.comics.AllComics;
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
import java.util.List;

/**
 * A Servlet that will respond with a WebComic class.
 * Created by einnuj on 4/12/2016.
 */

public class ComicServlet extends HttpServlet {

    /**
     * This POST call reacts differently pending an "action" parameter:
     *      "CREATE CHAPTER" - attempts to create a ComicChapter with a name from a "name" parameter and ComicPage pages from files from a "files" parameter and save it to an existing WebComic with an id equal to the "id" parameter.
     *      "CREATE COMIC" - attempts to create a new WebComic with a name from a "name" parameter and a description from a "description" parameter. Returns the WebComic as a JSONObject.
     * @param req the HttpServletRequest Object
     * @param resp the HttpServletResponse Object
     * @throws ServletException - not explicitly thrown
     * @throws IOException - not explicitly thrown
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action) {
            case "CREATE CHAPTER":
                try {
                    String idString = req.getParameter("id");
                    String name = req.getParameter("name");
                    String file = req.getParameter("files");

                    if (idString == null) {
                        throw new ParameterNotFoundException("id");
                    }
                    if (name == null) {
                        throw new ParameterNotFoundException("name");
                    }
                    if (file == null) {
                        throw new ParameterNotFoundException("files");
                    }

                    Long id = Long.valueOf(idString);
                    WebComic comic = ComicAccess.queryForComic(id);

                    if (comic == null) {
                        throw new ComicNotFoundException(id);
                    }

                    ComicChapter newChapter = new ComicChapter(name);
                    newChapter.addToChildMediaList(new ComicPage("fileName", file));

                    comic.addToChildMediaList(newChapter);

                    ObjectifyHelper.save(comic);

                    comic.reload();
                    comic.getMetadata().reload();
                }
                catch (ComicNotFoundException ex) {
                    resp.getWriter().write("{\"error\":" + ex.getMessage() + "}");

                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
                catch (ParameterNotFoundException ex) {
                    resp.getWriter().write("{\"error\":" + ex.getMessage() + "}");

                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                }
                break;

            case "CREATE COMIC":
                com.google.appengine.api.users.User googleUser = UserServiceFactory.getUserService().getCurrentUser();

                resp.setContentType("application/json");

                if (googleUser != null) {
                    try {
                        User author = UserAccess.queryForUser(googleUser.getUserId());
                        String name = req.getParameter("name");
                        String description = req.getParameter("description");

                        if (author == null) {
                            throw new UserNotFoundException(googleUser.getUserId());
                        }
                        if (name == null) {
                            throw new ParameterNotFoundException("name");
                        }
                        if (description == null) {
                            throw new ParameterNotFoundException("description");
                        }

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

        }
    }

    /**
     * Returns to the caller a JSON representation of the WebComic queried for via Long id.
     * @param req the HttpServletRequest Object
     * @param resp the HttpServletResponse Object
     * @throws ServletException - now explicitly thrown
     * @throws IOException - not explicitly thrown
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idString = req.getParameter("id");

        if (idString != null) {
            Long id = Long.valueOf(req.getParameter("id"));

            try {
                WebComic webComic = ComicAccess.queryForComic(id);

                if (webComic == null) {
                    throw new ComicNotFoundException(id);
                }

                resp.getWriter().write(JsonHelper.objectToJson(webComic));
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.setContentType("application/json");
            } catch (ComicNotFoundException ex) {
                resp.getWriter().write("{\"error\":" + ex.getMessage() + "}");

                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
        else {
            try {
                List<WebComic> webComicList = ComicAccess.queryAllComics();

                if (webComicList == null) {
                    // Something horrible has happened.
                    return;
                }

                AllComics allComics = new AllComics();

                for (WebComic comic : webComicList) {
                    allComics.addComic(comic.getId(), comic);
                }

                req.setAttribute("allComics", allComics);
                resp.setStatus(HttpServletResponse.SC_OK);

            } catch (Exception ex) {
                resp.getWriter().write("{\"error\":" + ex.getMessage() + "}");

                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
    }
}
