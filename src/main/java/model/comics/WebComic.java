package model.comics;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import model.metadata.ComicMetadata;

import java.util.ArrayList;
import java.util.List;

/**
 * A ComicMediaParent class that represents an entire Web Comic.
 * Created by einnuj on 4/7/2016.
 */
@Entity
public class WebComic extends ComicMediaParent {
    @Id
    private Long id;

    private List<ComicChapter> childMediaList;

    private ComicMetadata metadata;


    // Solely for Objectify
    private WebComic() {}

    public WebComic(String name, String author) {
        super(name);

        childMediaList = new ArrayList<ComicChapter>();
        metadata = new ComicMetadata(name, author);
    }

    public WebComic(String name, String author, String genre){
        super(name);

        childMediaList = new ArrayList<ComicChapter>();
        metadata = new ComicMetadata(name, author, genre);
    }

    /* Getters */
    public Long getId() {return id;}

    public ComicMetadata getMetadata() {
        return metadata;
    }

    @Override
    public List<ComicChapter> getChildMediaList() {
        return childMediaList;
    }



    /* Setters */


    /* Methods */

    /* Overridden Methods */

    @Override
    public void addToChildMediaList(ComicMedia mediaToAdd) {
        if (mediaToAdd instanceof ComicChapter) {
            childMediaList.add((ComicChapter) mediaToAdd);
        }
    }

    public boolean removeFromChildMediaList(ComicChapter chapter){
        childMediaList.remove(chapter);
        return true;

    }

    @Override
    public void reload() {
        if (childMediaList == null) {
            childMediaList = new ArrayList<ComicChapter>();
        } else {
            for(ComicChapter c : childMediaList){
                c.reload();
            }
        }

    }
}
