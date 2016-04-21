package model.comics;

import java.util.ArrayList;
import java.util.List;

/**
 * A ComicMediaParent class that represents a ComicChapter.
 * Created by einnuj on 4/7/2016.
 */
public class ComicChapter extends ComicMediaParent {
    private List<ComicPage> childMediaList;

    public ComicChapter(String name) {
        super(name);

        childMediaList = new ArrayList<ComicPage>();
    }

    /* Overridden Methods */

    @Override
    public void addToChildMediaList(ComicMedia mediaToAdd) {
        if (mediaToAdd instanceof ComicPage) {
            childMediaList.add((ComicPage) mediaToAdd);
        }
    }

    @Override
    public void reload() {
        if (childMediaList == null) {
            childMediaList = new ArrayList<ComicPage>();
        }
    }
}
