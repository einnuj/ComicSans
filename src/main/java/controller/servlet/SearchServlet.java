package controller.servlet;

import controller.data.ComicAccess;
import controller.data.SearchEngine;
import controller.data.UserAccess;
import controller.exceptions.ParameterNotFoundException;
import model.comics.WebComic;
import model.users.User;
import utilities.JsonHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * A HTTPServlet class that will handle all Search requests.
 */
public class SearchServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchParameter = req.getParameter("search");

        try {

            List<WebComic> allWebComicsList = ComicAccess.queryAllComics();
            List<User> allUsersList = UserAccess.queryAllUsers();

            if (allWebComicsList == null || allUsersList == null) {
                // Something terrible has happened!
                return;
            }

            if (searchParameter == null) {
                throw new ParameterNotFoundException("search");
            }

            List<Map> searchResultsList = SearchEngine.search(allWebComicsList, allUsersList, searchParameter);

            req.getSession().setAttribute("result-object", JsonHelper.objectToJson(searchResultsList));
            resp.setStatus(HttpServletResponse.SC_OK);

            resp.sendRedirect("/search.jsp");
        }
        catch (ParameterNotFoundException ex) {
            resp.getWriter().write("{\"error\":" + ex.getMessage() + "}");

            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        doPost(req, resp);
    }
}
