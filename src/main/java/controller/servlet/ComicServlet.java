package controller.servlet;

import controller.mock.MockComicController;
import model.comics.ComicPage;
import model.comics.WebComic;
import utilities.JsonHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A Servlet that will respond with a WebComic class.
 * Created by einnuj on 4/12/2016.
 */

@MultipartConfig
public class ComicServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = Integer.parseInt(req.getParameter("page"));
        int chapter = Integer.parseInt(req.getParameter("chapter"));
        String genre = req.getParameter("genre");
        String imgFile = req.getParameter("imgFile");
        String title = req.getParameter("title");

        ComicPage ourPage = new ComicPage(title, imgFile, page, chapter, genre);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebComic webComic = new MockComicController().genWebComic("Comic " +
                "Sam", "Junnie");
        String comicInJson = JsonHelper.objectToJson(webComic);

        resp.getWriter().write(comicInJson);


    }
}
