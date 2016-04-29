package controller.servlet;

import com.google.appengine.api.users.UserServiceFactory;
import controller.data.UserAccess;
import controller.exceptions.NonUniqueGoogleIdException;
import model.users.User;
import utilities.data.ObjectifyHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by cherrinkim on 4/22/16.
 */
public class CreateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        com.google.appengine.api.users.User googleUser = getGoogleUser();
        if (googleUser != null) {
            resp.setContentType("application/json");
            try {
                User genUser = UserAccess.queryForUser(googleUser.getUserId());

                // There was no User in the DB; must be a new User.
                if (genUser == null) {
                    genUser = new User(googleUser.getNickname(), googleUser.getUserId());

                    ObjectifyHelper.save(genUser);
                }
                if(req.getParameter("json") != null) {
                    String json = req.getParameter("json");
                    genUser.getMetadata().setDrawJson(json);
                    ObjectifyHelper.save(genUser);
                }
            }
            catch (NonUniqueGoogleIdException ex) {
                resp.getWriter().write("{\"error\":" + ex.getMessage() + "}");

                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }

    }

    private com.google.appengine.api.users.User getGoogleUser() {
        return UserServiceFactory.getUserService().getCurrentUser();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        com.google.appengine.api.users.User googleUser = getGoogleUser();
        if (googleUser != null) {
            resp.setContentType("application/json");
            try {
                User genUser = UserAccess.queryForUser(googleUser.getUserId());

                // There was no User in the DB; must be a new User.
                if (genUser == null) {
                    //then we don't do anything
                    return;
                }
                String json = genUser.getMetadata().getDrawJson();
                resp.getWriter().write(json);
            }
            catch (NonUniqueGoogleIdException ex) {
                resp.getWriter().write("{\"error\":" + ex.getMessage() + "}");

                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
    }
}

