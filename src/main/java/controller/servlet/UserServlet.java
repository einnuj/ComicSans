package controller.servlet;

import com.google.appengine.api.users.UserServiceFactory;
import controller.exceptions.NonUniqueGoogleIdException;
import controller.exceptions.UserNotFoundException;
import model.metadata.UserMetadata;
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
 * A Servlet that handles User-related requests.
 * Created by einnuj on 4/12/2016.
 */
public class UserServlet extends HttpServlet {

    /**
     * Writes a JSON representation of the User if they're logged in to Response.
     * @param req the HttpServletRequest Object
     * @param resp the HttpServletResponse Object
     * @throws ServletException - not explicitly thrown
     * @throws IOException - not explicitly thrown
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        com.google.appengine.api.users.User googleUser = getGoogleUser();

        if (googleUser != null) {
            resp.setContentType("application/json");
            try {
                User genUser = queryForUser(googleUser.getUserId());

                // There was no User in the DB; must be a new User.
                if (genUser == null) {
                    genUser = new User(googleUser.getNickname(), googleUser.getUserId());

                    ObjectifyHelper.save(genUser);
                }

                resp.getWriter().write(JsonHelper.objectToJson(genUser));
                resp.setStatus(HttpServletResponse.SC_OK);
            }
            catch (NonUniqueGoogleIdException ex) {
                resp.getWriter().write("{\"error\":" + ex.getMessage() + "}");

                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
    }

    /**
     * Augments a User's Metadata according to parameter fields and returns a JSON representation of the User.
     * @param req the HttpServletRequest Object
     * @param resp the HttpServletResponse Object
     * @throws ServletException - not explicitly thrown
     * @throws IOException - not explicitly thrown
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        com.google.appengine.api.users.User googleUser = getGoogleUser();
        resp.setContentType("application/json");

        if (googleUser != null) {
            try {
                User currentUser = queryForUser(googleUser.getUserId());

                // Must have existed for our logic to continue
                if (currentUser == null) {
                    throw new UserNotFoundException(googleUser.getUserId());
                }

                String name = req.getParameter("name");
                String description = req.getParameter("description");

                currentUser.getMetadata().setName(name);
                currentUser.getMetadata().setBio(description);

                ObjectifyHelper.save(currentUser);

                resp.getWriter().write(JsonHelper.objectToJson(currentUser));
                resp.setStatus(HttpServletResponse.SC_OK);
            }
            catch (NonUniqueGoogleIdException | UserNotFoundException ex) {
                resp.setContentType("application/json");
                resp.getWriter().write("{\"error\":" + ex.getMessage() + "}");

                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
        else {
            resp.setContentType("application/json");
            resp.getWriter().write("{\"error\":\"No User Logged In\"}");

            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private com.google.appengine.api.users.User getGoogleUser() {
        return UserServiceFactory.getUserService().getCurrentUser();
    }

    /**
     * Attempts to return the first User with the relevant googleId.
     * @param googleId the unique String representation of a User
     * @return User Object with the same googleId parameter, or null if not found in DataStore
     * @throws NonUniqueGoogleIdException - thrown when more than one Users are returned from the query
     */
    private User queryForUser(String googleId) throws NonUniqueGoogleIdException {
        List<User> userList = ObjectifyHelper.loadWithEqualsFilter(User.class, "googleId", googleId);

        if (userList.isEmpty()) {
            return null;
        }
        else if (userList.size() == 1) {
            User user = userList.get(0);
            user.getMetadata().reload();    // Reinstantiates any null Collection resulting from DS load
            return user;
        }
        else {
            throw new NonUniqueGoogleIdException(googleId);
        }
    }
}
