package controller.data;

import model.comics.WebComic;
import utilities.data.ObjectifyHelper;

/**
 * A Class that handles CRUD Comics for Controllers against our DataBase.
 * Created by einnuj on 4/22/2016.
 */
public class ComicAccess {

    /**
     * Attempts to return the first WebComic with the relevant id
     * @param id the unique Long representation of a WebComic
     * @return the WebComic with the same id, or null if not found in DataStore
     */
    public static WebComic queryForComic(Long id) {
        WebComic comic =  ObjectifyHelper.loadById(WebComic.class, id);

        if (comic != null) {

            // Objectify returns empty Collections to null; this reinstantializes them.
            comic.reload();
            comic.getMetadata().reload();
        }

        return comic;
    }
}
