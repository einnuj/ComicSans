package controller.servlet;

import com.googlecode.objectify.ObjectifyService;
import model.comics.WebComic;
import model.metadata.ComicMetadata;
import model.metadata.fields.Bookmark;
import model.metadata.fields.Comment;
import model.metadata.fields.Favorite;
import model.metadata.fields.Like;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * A Servlet class that will take care of initializing anything we need before
 * our application can run.
 * Created by einnuj on 4/19/2016.
 */
public class InitServlet extends HttpServlet {
    // Register Objectify Entities
    static {
        ObjectifyService.register(WebComic.class);
    }

    public void init() {
        System.out.println("INITIALIZING");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebComic comic = new WebComic("Super Comic", "Junnie");
        ComicMetadata metadata = comic.getMetadata();

        metadata.setBio("Totally awesome bio");
        metadata.addToBookmarkList(new Bookmark("Super Comic", "Stranger1"));
        metadata.addToCommentList(new Comment("Super Comic", "Stranger2",
                "Totes awesome bruh"));
        metadata.addToFavoriteList(new Favorite("Super Comic", "Stranger3"));
        metadata.addToLikeList(new Like("Super Comic", "Stranger4"));

        ofy().save().entity(comic).now();

        System.out.println("ENTITY SAVED");
    }
}
