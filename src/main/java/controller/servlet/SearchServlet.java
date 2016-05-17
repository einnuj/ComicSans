package controller.servlet;

import controller.data.ComicAccess;
import controller.data.SearchEngine;
import controller.data.UserAccess;
import model.comics.WebComic;
import model.users.User;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * A HTTPServlet class that will handle all Search requests.
 */
public class SearchServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String searchParameter = req.getParameter("search");

        List<WebComic> allWebComicsList = ComicAccess.queryAllComics();
        List<User> allUsersList = UserAccess.queryAllUsers();

        if (allWebComicsList == null || allUsersList == null) {
            // Something terrible has happened!
            return;
        }

        SearchEngine.search(allWebComicsList, allUsersList, "Junnie");

        allWebComicsList.get(0).getName();                                                          // Comic Names
        allWebComicsList.get(0).getChildMediaList().get(0).getName();                               // Chapter Names
        allWebComicsList.get(0).getChildMediaList().get(0).getChildMediaList().get(0).getName();    // Page Names

        allWebComicsList.get(0).getMetadata().getAuthor();                                          // Author (AS LONG)
        allWebComicsList.get(0).getMetadata().getBio();                                             // Comic Bio

        allWebComicsList.get(0).getMetadata().getCommentList();                                     // Comments

        allUsersList.get(0).getMetadata().getName();                                                // User Name
        allUsersList.get(0).getMetadata().getBio();                                                 // User Bio

        /*
        Thought Process

        Grab all of those, append ALL of them into a gigantic String; then, add them appropriately to three Map<id, String>, Comic, Comment, User.

        Search through them all, cut the ones that don't have any relevance.

        JSONify the three maps back.
         */
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
    }
}
