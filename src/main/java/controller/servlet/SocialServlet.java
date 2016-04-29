package controller.servlet;

import controller.data.ComicAccess;
import controller.data.UserAccess;
import controller.exceptions.NonUniqueGoogleIdException;
import controller.exceptions.NonUniqueLongIdException;
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
                    // TODO: User somehow gained access to this page without logging in to our system. Address immediately.
                    return;
                }

                String googleId = genUser.getGoogleId();
                String action = req.getParameter("action");
                String comicId = req.getParameter("comicId");

                if (comicId == null) {
                    // TODO: comicId was not passed back to BackEnd.
                    return;
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
                        userMetadata.addToLikedList(myLike);
                        userMetadata.addToLikesMap(myLike);

                        comicMetadata.addToLikeList(myLike);
                        break;
                    case "UNLIKE":
                        Like like = fieldFactory.getLike();
                        userMetadata.removeFromLikedList(like);
                        userMetadata.removeFromLikesMap(like);

                        comicMetadata.removeFromLikeList(like);
                        break;
                    case "FAVORITE":
                        Favorite myFave = fieldFactory.getFavorite();
                        userMetadata.addToFavoritedList(myFave);
                        userMetadata.addToFavoritesMap(myFave);

                        comicMetadata.addToFavoriteList(myFave);
                        break;
                    case "UNFAVORITE":
                        Favorite favorite = fieldFactory.getFavorite();
                        userMetadata.removeFromFavoritedList(favorite);
                        userMetadata.removeFromFavoritesMap(favorite);

                        comicMetadata.removeFromFavoriteList(favorite);
                        break;
                    case "COMMENT":
                        String comment = req.getParameter("comment");

                        if (comment == null) {
                            // TODO: comment was not passed back to BackEnd.,
                            return;
                        }

                        Comment myComment = fieldFactory.getComment(comment);
                        userMetadata.addToCommentedList(myComment);
                        userMetadata.incrementComment();

                        comicMetadata.addToCommentList(myComment);
                        //testComic.getMetadata().addToCommentedList(myComment);
                        break;
                    case "RATE":
                        String rating = req.getParameter("rating");

                        if (rating == null) {
                            // TODO: rating was not passed back to BackEnd
                            return;
                        }

                        Rating myRating = fieldFactory.getRating(Integer.parseInt(rating));
                        userMetadata.addToRatedList(myRating);

                        comicMetadata.addToRatingList(myRating);
                        // testComic.getMetadata().addToRatingList(myRating);
                        break;
                    case "BOOKMARK":
                        Bookmark myBM = fieldFactory.getBookmark(0, 0);
                        userMetadata.addToBookmarkedList(myBM);

                        comicMetadata.addToBookmarkList(myBM);
                        break;
                    case "UNBOOKMARK":
                        Bookmark myBm = fieldFactory.getBookmark(0, 0);
                        userMetadata.removeFromBookmarkedList(myBm);

                        comicMetadata.removeFromBookmarkList(myBm);
                        break;
                    default:
                        // TODO: an unspecified action was given
                        return;
                }

                ObjectifyHelper.save(genUser);
                ObjectifyHelper.save(targetComic);

                resp.getWriter().write(JsonHelper.objectToJson(genUser));
                resp.setStatus(HttpServletResponse.SC_OK);

            }
            catch (NonUniqueGoogleIdException | NonUniqueLongIdException ex) {
                resp.getWriter().write("{\"error\":" + ex.getMessage() + "}");

                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
