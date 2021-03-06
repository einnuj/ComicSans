package controller.servlet;

import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.repackaged.com.google.gson.Gson;
import controller.data.ComicAccess;
import controller.data.UserAccess;
import controller.exceptions.*;
import model.comics.WebComic;
import model.metadata.ComicMetadata;
import model.metadata.UserMetadata;
import model.metadata.fields.*;
import model.users.User;
import utilities.JsonHelper;
import utilities.data.ObjectifyHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A Servlet that handles all social-media related tasks for Users/Comics.
 *
 * Created by cherrinkim on 4/28/16.
 */
public class SocialServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        com.google.appengine.api.users.User googleUser = UserAccess.getGoogleUser();

        if (googleUser != null) {
            resp.setContentType("application/json");
            try {
                // Also serves as a way to ensure only a logged in person with an account with us can perform any social action
                User genUser = UserAccess.queryForUser(googleUser.getUserId());

                if (genUser == null) {
                    throw new UserNotFoundException(googleUser.getUserId());
                }

                String googleId = genUser.getGoogleId();
                String action = req.getParameter("action");
                String comicId = req.getParameter("comicId");

                if (comicId == null) {
                    throw new ParameterNotFoundException("comicId");
                }

                WebComic targetComic = ComicAccess.queryForComic(Long.valueOf(comicId));

                /* Cherrin's Additions
                * It may be smart to instantiate a cached version of all comics when the server instance starts just because DS queries
                * may take a while.

                //AllComics myComics = new AllComics();
                // do stuff to actually obtain all comics in myComics from datastore

                //WebComic testComic = myComics.getComic(comicId);

                //need to change the comic into the actual comic object getting manipulated by the passed comicId

                */

                FieldFactory fieldFactory = new FieldFactory(comicId, googleId);
                UserMetadata userMetadata = genUser.getMetadata();
                ComicMetadata comicMetadata = targetComic.getMetadata();

                targetComic.reload();
                userMetadata.reload();
                comicMetadata.reload();

                switch(action) {
                    case "LIKE":
                        Like myLike = fieldFactory.getLike();
                        if(!userMetadata.getLikes().containsKey(comicId)) {
                            userMetadata.addToLikesMap(myLike);
                            comicMetadata.incrementLike();
                        }
                        break;
                    case "UNLIKE":
                        Like like = fieldFactory.getLike();
                        if(userMetadata.getLikes().containsKey(comicId)){
                            userMetadata.removeFromLikesMap(like);
                            comicMetadata.decrementLike();
                        }
                        break;
                    case "FAVORITE":
                        Favorite myFave = fieldFactory.getFavorite();
                        if(!userMetadata.getFavorites().containsKey(comicId)) {
                            userMetadata.addToFavoritesMap(myFave);
                            comicMetadata.incrementFaves();
                        }
                        break;
                    case "UNFAVORITE":
                        Favorite favorite = fieldFactory.getFavorite();
                        if(userMetadata.getFavorites().containsKey(comicId)){
                            userMetadata.removeFromFavoritesMap(favorite);
                            comicMetadata.decrementFaves();
                        }
                        break;
                    case "COMMENT":
                        String comment = req.getParameter("comment");

                        if (comment == null) {
                            throw new ParameterNotFoundException("comment");
                        }

                        Comment myComment = fieldFactory.getComment(comment);
                        comicMetadata.addComment(myComment);
                        userMetadata.incrementComment();
                        break;
                    case "DELETECOMMENT":
                        int numComment = Integer.parseInt(req.getParameter("numComment"));
                        comicMetadata.deleteComment(numComment);
                        break;
                    case "RATE":
                        String rating = req.getParameter("rating");

                        if (rating == null) {
                            throw new ParameterNotFoundException("rating");
                        }

                        Rating myRating = fieldFactory.getRating(Integer.parseInt(rating));
                        userMetadata.addToRatedMap(myRating);
                        comicMetadata.addRating(myRating);
                        break;
                    case "BOOKMARK":
                        Bookmark myBM = fieldFactory.getBookmark(0, 0);
                        userMetadata.addToBookmarks(myBM);
                        break;
                    case "UNBOOKMARK":
                        Bookmark myBm = fieldFactory.getBookmark(0, 0);
                        userMetadata.removeFromBookmarks(myBm.getComicTarget());
                        break;
                    case "SUBSCRIBE":
                        userMetadata.addSubscription(targetComic);
                        break;
                    case "UNSUBSCRIBE":
                        userMetadata.removeSubscription(targetComic);
                        break;
                    default:
                        throw new UndefinedActionException(action);
                }

                ObjectifyHelper.save(genUser);
                ObjectifyHelper.save(targetComic);

                resp.getWriter().write(JsonHelper.objectToJson(genUser));
                resp.setStatus(HttpServletResponse.SC_OK);

            }
            catch (UserNotFoundException | NonUniqueGoogleIdException ex) {
                resp.getWriter().write("{\"error\":" + ex.getMessage() + "}");

                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
            catch (ParameterNotFoundException | UndefinedActionException ex) {
                resp.getWriter().write("{\"error\":" + ex.getMessage() + "}");

                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
        else {
            resp.getWriter().write("{\"error\":\"No User Logged In\"}");

            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
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
                String googleId = genUser.getGoogleId();
                String request = req.getParameter("request");
                String comicId = req.getParameter("comicId");

                if (comicId == null) {
                    throw new ParameterNotFoundException("comicId");
                }

                WebComic targetComic = ComicAccess.queryForComic(Long.valueOf(comicId));

                FieldFactory fieldFactory = new FieldFactory(comicId, googleId);
                UserMetadata userMetadata = genUser.getMetadata();
                ComicMetadata comicMetadata = targetComic.getMetadata();

                targetComic.reload();
                userMetadata.reload();
                comicMetadata.reload();

                switch (request) {
                    case "numFavorites":
                        int numFaves = comicMetadata.getFavorites();
                        resp.getWriter().write(Integer.toString(numFaves));
                        break;
                    case "numLikes":
                        int numLikes = comicMetadata.getLikes();

                        resp.getWriter().write(Integer.toString(numLikes));
                        break;
                    case "isSubscribed":
                         if(userMetadata.hasSubscription(targetComic)){
                             resp.getWriter().write("true");
                         } else
                             resp.getWriter().write("false");
                        break;
                    case "isLiked":
                        if(userMetadata.hasLike(targetComic)){
                            resp.getWriter().write("true");
                        } else
                            resp.getWriter().write("false");
                        break;
                    case "isFavorited":
                        if(userMetadata.hasFavorited(targetComic)){
                            resp.getWriter().write("true");
                        } else
                            resp.getWriter().write("false");
                        break;
                    case "getComments":
                        if(comicMetadata.getCommentList() != null && comicMetadata.getCommentList().size() > 0) {
                            String comments = new Gson().toJson(comicMetadata.getCommentList());
                            resp.getWriter().write(comments);
                        } else {
                            resp.getWriter().write("null");
                        }
                        break;
                    case "getRating":
                        if(comicMetadata.getRatingMap() != null && comicMetadata.getRatingMap().size()>0){
                            int rating = comicMetadata.getRatingAsInt();
                            resp.getWriter().write(Integer.toString(rating));
                        } else {
                            // to indicate there is no rating
                            resp.getWriter().write("0");
                        }
                        break;
                }

            }
            catch (NonUniqueGoogleIdException ex) {
                resp.getWriter().write("{\"error\":" + ex.getMessage() + "}");

                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (ParameterNotFoundException e) {
                e.printStackTrace();
            }
        } else { // user isn't logged in, but should still be able to view comments
            resp.setContentType("application/json");
            String request = req.getParameter("request");

            if (request.equals("getComments")) {

                String comicId = req.getParameter("comicId");

                WebComic targetComic = ComicAccess.queryForComic(Long.valueOf(comicId));

                ComicMetadata comicMetadata = targetComic.getMetadata();

                targetComic.reload();
                comicMetadata.reload();

                if (comicMetadata.getCommentList() != null && comicMetadata.getCommentList().size() > 0) {
                    String comments = new Gson().toJson(comicMetadata.getCommentList());
                    resp.getWriter().write(comments);
                } else {
                    resp.getWriter().write("null");
                }
            }
        }
    }
}
