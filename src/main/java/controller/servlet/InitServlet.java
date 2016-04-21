package controller.servlet;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.impl.translate.opt.joda.JodaTimeTranslators;
import model.comics.WebComic;
import model.metadata.ComicMetadata;
import model.metadata.fields.Bookmark;
import model.metadata.fields.Comment;
import model.metadata.fields.Favorite;
import model.metadata.fields.Like;
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
 * A Servlet class that will take care of initializing anything we need before
 * our application can run.
 * Created by einnuj on 4/19/2016.
 */
public class InitServlet extends HttpServlet {
    // Register Objectify Entities
    static {
        JodaTimeTranslators.add(ObjectifyService.factory());
        ObjectifyService.register(WebComic.class);
        ObjectifyService.register(User.class);
    }

    public void init() {
        System.out.println("INITIALIZING");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
