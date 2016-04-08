package model.comics;

import java.util.List;

/**
 * A ComicMedia Abstract that represents a piece of Media that "has" another.
 * Created by einnuj on 4/7/2016.
 */
public abstract class ComicMediaParent extends ComicMedia {
    private List<ComicMedia> childMediaList;

    public ComicMediaParent(String author) {
        super(author);
    }

    public List<ComicMedia> getChildMediaList() {
        return childMediaList;
    }

    public abstract void addToChildMediaList(ComicMedia mediaToAdd);

    public boolean removeFromChildMediaList(ComicMedia mediaToRemove) {
        return childMediaList.remove(mediaToRemove);
    }
}
