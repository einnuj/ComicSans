package model.metadata;

import model.metadata.fields.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents the Metadata of a Comic.
 * Created by einnuj on 4/7/2016.
 */
public class ComicMetadata extends AbstractMetadata {
    private String author;              // This is a stand-in type
    private GenreEnum genre;

    private int favorites;
    private int likes;
    private float rating;

    private List<Comment> commentList;

    ComicMetadata(){}

    public ComicMetadata(String name, String author) {
        super(name);

        this.author = author;
        genre = GenreEnum.UNLISTED;
        commentList = new ArrayList<Comment>();
    }

    /* Getters */

    public String getAuthor() {
        return author;
    }

    public GenreEnum getGenre() {
        return genre;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public int getFavorites() {
        return favorites;
    }

    public int getLikes() {
        return likes;
    }

    public float getRating() {
        return rating;
    }

    /* Setters */

    public void setGenre(GenreEnum genre) {
        this.genre = genre;
    }

    /* Methods */

    public void incrementLike(){
        likes++;
    }

    public void decrementLike(){
        likes--;
    }

    public int getRatingAsInt() {
        return Math.round(rating);
    }

    public void addComment(Comment c){
        commentList.add(c);
    }

    /**
     * Reinitializes any null collection in the object as returned by Objectify
     */
    public void reload() {
        if (commentList == null) {
            commentList = new ArrayList<Comment>();
        }
    }
}
