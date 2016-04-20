package controller.servlet;

import com.google.appengine.api.users.UserService;
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
     * Returns a JSON representation of the User if they're logged in.
     * @param req the HttpServletRequest Object
     * @param resp the HttpServletResponse Object
     * @throws ServletException - thrown when there are multiple Users with the same googleId as the Google.User.
     * @throws IOException - not explicitly thrown.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = UserServiceFactory.getUserService();
        com.google.appengine.api.users.User googleUser = userService.getCurrentUser();

        if (googleUser != null) {
            User genUser;

            ObjectifyHelper helper = new ObjectifyHelper();
            List<User> loadedUsers = helper.loadWithEqualsFilter(User.class, "googleId", googleUser.getUserId());

            if (loadedUsers.size() == 0) {
                genUser = new User(googleUser.getNickname(), googleUser.getUserId());
                helper.save(genUser);
            }
            else if (loadedUsers.size() == 1) {
                genUser = loadedUsers.get(0);
            }
            else {
                throw new ServletException("More than one User returned with googleId: " + googleUser.getUserId());
            }

            JsonHelper jsonHelper = new JsonHelper();
            resp.getWriter().write(jsonHelper.objectToJson(genUser));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
