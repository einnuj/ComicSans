package controller.data;

import controller.exceptions.NonUniqueLongIdException;
import model.comics.WebComic;
import utilities.data.ObjectifyHelper;

import java.util.List;

/**
 * A Class that handles CRUD Comics for Controllers against our DataBase.
 * Created by einnuj on 4/22/2016.
 */
public class ComicAccess {

    public static WebComic queryForComic(Long id) throws NonUniqueLongIdException {
        List<WebComic> comicList = ObjectifyHelper.loadWithEqualsFilter(WebComic.class, "id", id);

        if (comicList.isEmpty()) {
            return null;
        }
        else if (comicList.size() == 1) {
            WebComic comic = comicList.get(0);
            comic.reload();
            comic.getMetadata().reload();
            return comic;
        }
        else {
            throw new NonUniqueLongIdException(id);
        }
    }
}
