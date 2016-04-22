package controller.servlet;

import com.google.appengine.api.users.UserServiceFactory;
import controller.data.UserAccess;
import controller.exceptions.NonUniqueGoogleIdException;
import controller.exceptions.UserNotFoundException;
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

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

                ObjectifyHelper.save(newComic);

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
    }
}
