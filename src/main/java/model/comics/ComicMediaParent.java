package model.comics;

import java.util.List;

/**
 * A ComicMedia Abstract that represents a piece of Media that "has" another.
 * Created by einnuj on 4/7/2016.
 */
public abstract class ComicMediaParent extends ComicMedia {
    private List<ComicMedia> childMediaList;

    public List<ComicMedia> getChildMediaList() {
        return childMediaList;
    }

    public void addToChildMediaList(ComicMedia mediaToAdd) {
        childMediaList.add(mediaToAdd);
    }
}
