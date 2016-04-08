package model.metadata;

import model.metadata.fields.Comment;
import model.metadata.fields.Favorite;
import model.metadata.fields.Like;
import model.metadata.fields.Rating;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents the Metadata of a Comic.
 * Created by einnuj on 4/7/2016.
 */
public class ComicMetadata {
    private List<Like> likeList;
    private List<Favorite> favoriteList;
    private List<Comment> commentList;
    private List<Rating> ratingList;
    private GenreEnum genre;
    private String description;

    // This should be a User
    private String author;

    public ComicMetadata(String author) {
        likeList = new ArrayList<Like>();
        favoriteList = new ArrayList<Favorite>();
        commentList = new ArrayList<Comment>();
        ratingList = new ArrayList<Rating>();
        genre = GenreEnum.UNLISTED;
        description = "";
        this.author = author;
    }

    /* GETTERS */

    public List<Like> getLikeList() {
        return likeList;
    }

    public List<Favorite> getFavoriteList() {
        return favoriteList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public List<Rating> getRatingList() { return ratingList; }

    public GenreEnum getGenre() {
        return genre;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    /* Setters */

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGenre(GenreEnum genre) {
        this.genre = genre;
    }

    /* General Methods */

    public void addToLikeList(Like like) {
        likeList.add(like);
    }

    public void addToFavoriteList(Favorite favorite) {
        favoriteList.add(favorite);
    }

    public void addToCommentList(Comment comment) {
        commentList.add(comment);
    }
}
