package controller.data;

import model.comics.WebComic;
import utilities.data.ObjectifyHelper;

import java.util.List;

/**
 * A Class that handles CRUD Comics for Controllers against our DataBase.
 * Created by einnuj on 4/22/2016.
 */
public class ComicAccess {

    /**
     * Attempts to return all WebComics from the DataStore
     * @return a List of all WebComics from the DataStore
     */
    public static List<WebComic> queryAllComics() {
        List<WebComic> comicList = ObjectifyHelper.loadAllOfType(WebComic.class);

        for (WebComic comic : comicList) {
            reinstantiate(comic);
        }

        return comicList;
    }

    /**
     * Attempts to return the first WebComic with the relevant id
     * @param id the unique Long representation of a WebComic
     * @return the WebComic with the same id, or null if not found in DataStore
     */
    public static WebComic queryForComic(Long id) {
        WebComic comic =  ObjectifyHelper.loadById(WebComic.class, id);

        reinstantiate(comic);

        return comic;
    }

    /**
     * Reinstantiates all Collections returned from Objectify.
     * @param comic - the WebComic to reinstantiate
     */
    private static void reinstantiate(WebComic comic) {

        if (comic != null) {
            comic.reload();
            comic.getMetadata().reload();
        }
    }
}
