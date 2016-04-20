package controller.servlet;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import model.users.User;
import utilities.data.ObjectifyHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A Servlet that handles User-related requests.
 * Created by einnuj on 4/12/2016.
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = UserServiceFactory.getUserService();
        com.google.appengine.api.users.User googleUser = userService.getCurrentUser();

        if (googleUser != null) {
            ObjectifyHelper helper = new ObjectifyHelper();
            List<User> loadedUsers = helper.loadWithEqualsFilter(User.class, "googleId", googleUser.getUserId());

            if (loadedUsers.size() == 0) {
                User newUser = new User(googleUser.getNickname(), googleUser.getUserId());
                helper.save(newUser);
            }

            // If it reaches here, that means that there already exists a relevant User
            // and we don't need to create a new one.
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
