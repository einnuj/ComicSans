package model.comics;

import com.googlecode.objectify.annotation.Subclass;

import java.util.ArrayList;
import java.util.List;

/**
 * A ComicMediaParent class that represents a ComicChapter.
 * Created by einnuj on 4/7/2016.
 */
@Subclass
public class ComicChapter extends ComicMediaParent {
    private List<ComicPage> childMediaList;
    private int chapterNumber;

    ComicChapter(){}

    public ComicChapter(String name) {
        super(name);

        childMediaList = new ArrayList<ComicPage>();
    }

    public void setChapterNumber(int n){
        chapterNumber = n;
    }

    public int getChapterNumber(){
        return chapterNumber;
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
