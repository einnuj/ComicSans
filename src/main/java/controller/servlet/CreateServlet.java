package controller.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import controller.mock.MockComicController;
import model.comics.ComicPage;
import model.comics.WebComic;
import model.users.User;
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
            User user = new User("Mary", "googleid");
            user.setDrawJson(json);
            System.out.println(json);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = ("{\"objects\":[{\"type\":\"text\",\"originX\":\"center\",\"originY\":\"center\",\"left\":145.04,\"top\":48,\"width\":39.83,\"height\":23.4,\"fill\":\"rgb(0,0,0)\",\"overlayFill\":null,\"stroke\":null,\"strokeWidth\":1,\"strokeDashArray\":null,\"scaleX\":3.26,\"scaleY\":1,\"angle\":0,\"flipX\":false,\"flipY\":false,\"opacity\":1,\"selectable\":true,\"hasControls\":true,\"hasBorders\":true,\"hasRotatingPoint\":true,\"transparentCorners\":true,\"perPixelTargetFind\":false,\"shadow\":null,\"visible\":true,\"text\":\"Test\",\"fontSize\":18,\"fontWeight\":\"bold\",\"fontFamily\":\"Comic Sans MS\",\"fontStyle\":\"\",\"lineHeight\":1.3,\"textDecoration\":\"\",\"textShadow\":\"\",\"textAlign\":\"left\",\"path\":null,\"strokeStyle\":\"\",\"backgroundColor\":\"\",\"textBackgroundColor\":\"\",\"useNative\":true}],\"background\":\"\"}\n\n");
        resp.getWriter().write(json);
    }
}

