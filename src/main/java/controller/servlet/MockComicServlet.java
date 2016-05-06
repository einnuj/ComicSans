package controller.servlet;

import controller.mock.MockComicController;
import model.comics.ComicPage;
import model.comics.WebComic;
import utilities.JsonHelper;
import utilities.data.ObjectifyHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A Servlet that returns Mock Comic JSON.
 * Created by einnuj on 4/21/2016.
 */
public class MockComicServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = Integer.parseInt(req.getParameter("page"));
        int chapter = Integer.parseInt(req.getParameter("chapter"));
        String genre = req.getParameter("genre");
        String imgFile = req.getParameter("imgFile");
        String title = req.getParameter("title");


        System.out.println(title);
        System.out.println(genre);
        System.out.println(chapter);
        System.out.println(page);

        ComicPage ourPage = new ComicPage(title, imgFile, page, chapter, genre);


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebComic webComic = new MockComicController().genWebComic("Comic " +
                "Sam", "Junnie");
        String comicInJson = JsonHelper.objectToJson(webComic);

        ObjectifyHelper.save(webComic);

        resp.setContentType("application/json");
        resp.getWriter().write(comicInJson);
    }
}
