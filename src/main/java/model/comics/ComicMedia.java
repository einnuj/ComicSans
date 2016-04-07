package model.comics;

import java.util.List;

/**
 * The ancestor class of all Comic Media Objects
 * Created by einnuj on 4/7/2016.
 */
public abstract class ComicMedia {
    private String name;
    private List<ComicMedia> childMediaList;

    public String getName() {
        return name;
    }

    public List<ComicMedia> getChildMediaList() {
        return childMediaList;
    }
}
