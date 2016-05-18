package controller.servlet;

import controller.data.ComicAccess;
import controller.data.SearchEngine;
import controller.data.UserAccess;
import controller.exceptions.ParameterNotFoundException;
import model.comics.WebComic;
import model.users.User;
import utilities.JsonHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

            // Once you're at this point, searchResultsList has all the WebComic/User ids loaded with their accompanying Objects that determine which fields had search hits.
            // For us at this point, we're loading entire WebComics/Users, so we ignore the other Objects.

            List<WebComic> hitComicsList = findComics(allWebComicsList, searchResultsList.get(0));
            List<WebComic> hitCommentComicsList = findComics(allWebComicsList, searchResultsList.get(1));
            List<User> hitUsersList = findUsers(allUsersList, searchResultsList.get(2));

            List<List> finalResultsList = new ArrayList<List>();

            finalResultsList.add(hitComicsList);
            finalResultsList.add(hitCommentComicsList);
            finalResultsList.add(hitUsersList);

            resp.setStatus(HttpServletResponse.SC_OK);

            if (req.getParameter("jsp") != null) {
                resp.setContentType("application/json");
                resp.getWriter().write(JsonHelper.objectToJson(finalResultsList));
            }
            else {
                req.getSession().setAttribute("result-object", JsonHelper.objectToJson(finalResultsList));
                resp.sendRedirect("/search.jsp");
            }
        }
        catch (ParameterNotFoundException ex) {
            resp.getWriter().write("{\"error\":" + ex.getMessage() + "}");

            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        doPost(req, resp);
    }

    /*
    The following two methods are identical but for the type (WebComic vs User); perhaps we should've had an Objectify interface implemented
     */

    private List<WebComic> findComics(List<WebComic> allWebComicsList, Map targetComics) {
        List<WebComic> allComicsListCopy = new ArrayList<WebComic>(allWebComicsList);

        for (Iterator<WebComic> iterator = allComicsListCopy.iterator(); iterator.hasNext();) {
            WebComic currentComic = iterator.next();
            if (!targetComics.containsKey(currentComic.getId())) {
                iterator.remove();
            }
        }
        return allComicsListCopy;
    }

    private List<User> findUsers(List<User> allUsersList, Map targetUsers) {
        List<User> allUsersListCopy = new ArrayList<User>(allUsersList);

        for (Iterator<User> iterator = allUsersListCopy.iterator(); iterator.hasNext()) {
            User currentUser = iterator.next();
            if(!targetUsers.containsKey(currentUser.getId())) {
                iterator.remove();
            }
        }
        return allUsersListCopy;
    }
}
