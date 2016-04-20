package controller.servlet;

import controller.mock.MockUserController;
import model.users.User;
import utilities.JsonHelper;
import utilities.data.ObjectifyHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * A Servlet that handles User-related requests.
 * Created by einnuj on 4/12/2016.
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User newUser = new MockUserController().genMockUser("Junnie");
        req.setAttribute("user", newUser);

        ObjectifyHelper helper = new ObjectifyHelper();
        helper.save(newUser);

        System.out.println("USER SAVED");

        User query = helper.loadById(User.class, newUser.getId());

        String userJson = JsonHelper.objectToJson(query);

        resp.getWriter().write(userJson);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
