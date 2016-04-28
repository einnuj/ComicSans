package controller.servlet;

import com.google.appengine.api.users.UserServiceFactory;
import controller.exceptions.NonUniqueGoogleIdException;
import model.comics.ComicPage;
import model.comics.WebComic;
import model.users.User;
import utilities.data.ObjectifyHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by cherrinkim on 4/28/16.
 */
public class SocialServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

                String action = req.getParameter("action");

                WebComic testComic = new WebComic("TestName", "Bob Jones");
                ComicPage testPage = new ComicPage("test", "url");
                //need to change the comic into the actual comic object getting manipulated

                switch(action) {
                    case "LIKE":
                        genUser.addToLikes(testComic);
                        break;
                    case "UNLIKE":
                        genUser.removeFromLikes(testComic);
                        break;
                    case "FAVORITE":
                        genUser.addToFavorites(testComic);
                        break;
                    case "UNFAVORITE":
                        genUser.removeFromFavorites(testComic);
                        break;
                    case "COMMENT":
                        String comment = req.getParameter("comment");
                        genUser.incrementComment();
                        testComic.addComment(comment);
                        break;
                    case "RATE":
                        int rating = Integer.parseInt(req.getParameter("rating"));
                        testComic.addRating(rating);
                        break;
                    case "BOOKMARK":
                        genUser.addBookmark(testPage);
                        break;
                    case "UNBOOKMARK":
                        genUser.removeFromBookmarks(testPage);
                        break;
                }

                ObjectifyHelper.save(genUser);

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
                User genUser = queryForUser(googleUser.getUserId());

                // There was no User in the DB; must be a new User.
                if (genUser == null) {
                    //then we don't do anything
                    return;
                }
                String json = genUser.getDrawJson();
                resp.getWriter().write(json);
            }
            catch (NonUniqueGoogleIdException ex) {
                resp.getWriter().write("{\"error\":" + ex.getMessage() + "}");

                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
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
