package controller.servlet;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.impl.translate.opt.joda.JodaTimeTranslators;
import model.comics.WebComic;
import model.users.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
