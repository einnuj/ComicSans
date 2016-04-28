package model.comics;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Subclass;
import model.metadata.ComicMetadata;

import java.util.ArrayList;
import java.util.List;

/**
 * A ComicMediaParent class that represents an entire Web Comic.
 * Created by einnuj on 4/7/2016.
 */
@Entity
@Subclass
public class WebComic extends ComicMediaParent {
    @Id
    private Long id;

    private List<ComicChapter> childMediaList;

    private ComicMetadata metadata;

    private List<String> comments;

    private double publicRating;

    private double realRating;

    private int numRatings;

    // Solely for Objectify
    private WebComic() {}

    public WebComic(String name, String author) {
        super(name);

        childMediaList = new ArrayList<ComicChapter>();
        metadata = new ComicMetadata(name, author);
        comments = new ArrayList<String>();
    }

    /* Getters */
    public Long getId() {return id;}

    public ComicMetadata getMetadata() {
        return metadata;
    }

    public List<String> getComments() { return comments; }

    public double getPublicRating() { return publicRating; }

    public double getRealRating() { return realRating; }

    public int getNumRatings() { return numRatings; }


    /* Setters */

    public void setComments(List<String> comments){
        this.comments = comments;
    }

    public void setPublicRating(double rating) {
        this.publicRating = rating;
    }

    public void setRealRating(double rating) {
        this.realRating = rating;
    }

    public void setNumRatings(int numRatings){
        this.numRatings = numRatings;
    }

    /* Methods */
    public void addComment(String comment){
        comments.add(comment);
    }

    public void deleteComment(String comment){
        comments.remove(comment);
    }

    public void addRating(int rating){
        incrementNumRatings();
        int currentNumRatings = getNumRatings();
        realRating = (realRating + rating)/currentNumRatings;
        // setsPublicRating to be rounded to the nearest .5
        setPublicRating((Math.round(realRating * 2) / 2.0));
    }

    public void incrementNumRatings(){
        numRatings++;
    }

    /* Overridden Methods */

    @Override
    public void addToChildMediaList(ComicMedia mediaToAdd) {
        if (mediaToAdd instanceof ComicChapter) {
            childMediaList.add((ComicChapter) mediaToAdd);
        }
    }

    @Override
    public void reload() {
        if (childMediaList == null) {
            childMediaList = new ArrayList<ComicChapter>();
        }
    }
}
