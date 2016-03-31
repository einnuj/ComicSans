package controller.servlet;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet that handles Google Authentication using GAE.apk.
 *
 * Created by einnuj.
 */
public class AuthenticateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return;
    }

    /**
     * Generates request attributes, isLoggedIn and authURL, depending on
     * whether or not the User is logged in to Google.
     *
     * @param request: the HTTP request from the client
     * @param response: the HTTP response to send back
     * @throws ServletException: NOT IMPLEMENTED
     * @throws IOException: NOT IMPLEMENTED
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = UserServiceFactory.getUserService();
        String currentURL = request.getParameter("currentURL");
        String responseURL;

        response.setContentType("text/plain");

        if (request.getUserPrincipal() != null) {
            responseURL = userService.createLogoutURL(currentURL);
        }
        else {
            responseURL = userService.createLoginURL(currentURL);
        }

        response.getWriter().write(responseURL);
    }
}
