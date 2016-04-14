package controller.servlet;

import model.comics.WebComic;
import utilities.JsonHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A Servlet that will respond with a WebComic class.
 * Created by einnuj on 4/12/2016.
 */
public class ComicServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebComic webComic = new WebComic("Comic Name", "Author");
        String comicInJson = JsonHelper.objectToJson(webComic);

        resp.getWriter().write(comicInJson);
    }
}
