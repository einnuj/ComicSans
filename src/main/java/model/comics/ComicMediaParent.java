package model.comics;

import java.util.ArrayList;
import java.util.List;

/**
 * A ComicMedia Abstract that represents a piece of Media that "has" another.
 * Created by einnuj on 4/7/2016.
 */
abstract class ComicMediaParent extends ComicMedia {
    private List<ComicMedia> childMediaList;

    ComicMediaParent() {}

    ComicMediaParent(String author) {
        super(author);
    }

    /* Getters */

    public abstract List getChildMediaList();

    /* Methods */


    /* Abstract Methods */

    public abstract void addToChildMediaList(ComicMedia mediaToAdd);

    /**
     * If this Object's lists are null, will initialize them to an Empty List.
     */
    public abstract void reload();
}
