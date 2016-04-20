package controller.servlet;

import com.google.appengine.api.users.UserServiceFactory;
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
     * @throws ServletException - thrown when there are multiple Users with the same googleId as the Google.User.
     * @throws IOException - not explicitly thrown.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        com.google.appengine.api.users.User googleUser = getGoogleUser();

        if (googleUser != null) {
            try {
                User genUser = queryForUser(googleUser.getUserId());

                // There was no User in the DB; must be a new User.
                if (genUser == null) {
                    genUser = new User(googleUser.getNickname(), googleUser.getUserId());

                    ObjectifyHelper.save(genUser);
                }
                resp.getWriter().write(JsonHelper.objectToJson(genUser));
            }
            catch (Exception ex) {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        com.google.appengine.api.users.User googleUser = getGoogleUser();

        if (googleUser != null) {
            List<User> loadedUsers = ObjectifyHelper.loadWithEqualsFilter(User.class, "googleId", googleUser.getUserId());
        }
    }

    private com.google.appengine.api.users.User getGoogleUser() {
        return UserServiceFactory.getUserService().getCurrentUser();
    }

    private User queryForUser(String googleId) throws Exception {
        List<User> userList = ObjectifyHelper.loadWithEqualsFilter(User.class, "googleId", googleId);

        if (userList.isEmpty()) {
            return null;
        }
        else if (userList.size() == 1) {
            return userList.get(0);
        }
        else {
            throw new Exception("More than one User returned with googleId: " + googleId);
        }
    }
}
