package model.comics;

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

    public List<ComicMedia> getChildMediaList() {
        return childMediaList;
    }

    /* Methods */

    public boolean removeFromChildMediaList(ComicMedia mediaToRemove) {
        return childMediaList.remove(mediaToRemove);
    }

    /* Abstract Methods */

    public abstract void addToChildMediaList(ComicMedia mediaToAdd);
}
