package model.metadata;

import model.metadata.fields.Bookmark;
import model.metadata.fields.Comment;
import model.metadata.fields.Favorite;
import model.metadata.fields.Like;

import java.util.ArrayList;
import java.util.List;

/**
 * An Abstract class that is the ancestor of all Metadata classes.
 * Created by einnuj.
 */
class AbstractMetadata {
    private String bio;
    private String name;
    private String displayPicture;          // This is a stand-in type.

    private List<Bookmark> bookmarkList;
    private List<Comment> commentList;
    private List<Favorite> favoriteList;
    private List<Like> likeList;

    AbstractMetadata(String name) {
        bio = "";
        this.name = name;
        displayPicture = "";

        bookmarkList = new ArrayList<Bookmark>();
        commentList = new ArrayList<Comment>();
        favoriteList = new ArrayList<Favorite>();
        likeList = new ArrayList<Like>();
    }

    /* Getters */

    public String getBio() {
        return bio;
    }

    public String getName() {
        return name;
    }

    public String getDisplayPicture() {
        return displayPicture;
    }

    public List<Bookmark> getBookmarkList() {
        return bookmarkList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public List<Favorite> getFavoriteList() {
        return favoriteList;
    }

    public List<Like> getLikeList() {
        return likeList;
    }

    /* Setters */

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setDisplayPicture(String displayPicture) {
        this.displayPicture = displayPicture;
    }

    /* Methods */

    public void addToBookmarkList(Bookmark bookmark) {
        bookmarkList.add(bookmark); }

    public void addToCommentList(Comment comment) {
        commentList.add(comment);
    }

    public void addToFavoriteList(Favorite favorite) {
        favoriteList.add(favorite);
    }

    public void addToLikeList(Like like) {
        likeList.add(like);
    }
}
