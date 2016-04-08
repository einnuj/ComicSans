package model.comics;

import model.metadata.ComicMetadata;

import java.util.ArrayList;
import java.util.List;

/**
 * A ComicMediaParent class that represents an entire Web Comic.
 * Created by einnuj on 4/7/2016.
 */
public class WebComic extends ComicMediaParent {
    private List<ComicChapter> childMediaList;
    private ComicMetadata metadata;

    public WebComic(String name) {
        super(name);
        childMediaList = new ArrayList<ComicChapter>();
    }

    @Override
    public void addToChildMediaList(ComicMedia mediaToAdd) {
        if (mediaToAdd instanceof ComicChapter) {
            childMediaList.add((ComicChapter) mediaToAdd);
        }
    }
}
