package controller.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import controller.mock.MockComicController;
import model.comics.ComicPage;
import model.comics.WebComic;
import utilities.JsonHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by cherrinkim on 4/22/16.
 */
public class CreateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("json") != null){
            String json = req.getParameter("json");
            System.out.println(json);

        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebComic webComic = new MockComicController().genWebComic("Comic " +
                "Sam", "Junnie");
        String comicInJson = JsonHelper.objectToJson(webComic);

        resp.getWriter().write(comicInJson);
    }
}

