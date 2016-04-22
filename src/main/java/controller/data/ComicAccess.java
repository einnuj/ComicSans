package controller.data;

import controller.exceptions.NonUniqueLongIdException;
import model.comics.WebComic;
import utilities.data.ObjectifyHelper;

/**
 * A Class that handles CRUD Comics for Controllers against our DataBase.
 * Created by einnuj on 4/22/2016.
 */
public class ComicAccess {

    public static WebComic queryForComic(Long id) throws NonUniqueLongIdException {
        WebComic comic =  ObjectifyHelper.loadById(WebComic.class, id);
        if (comic != null) {
            comic.reload();
        }

        return comic;
    }
}
