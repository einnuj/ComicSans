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
abstract class AbstractMetadata {
    private String bioString;
    private String nameString;
    private String displayPicture;          // This is a stand-in type.

    private List<Bookmark> bookmarkList;
    private List<Comment> commentList;
    private List<Favorite> favoriteList;
    private List<Like> likeList;

    AbstractMetadata(String nameString) {
        bioString = "";
        this.nameString = nameString;
        displayPicture = "";

        bookmarkList = new ArrayList<Bookmark>();
        commentList = new ArrayList<Comment>();
        favoriteList = new ArrayList<Favorite>();
        likeList = new ArrayList<Like>();
    }

    /* Getters */

    public String getBioString() {
        return bioString;
    }

    public String getNameString() {
        return nameString;
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

    public void setBioString(String bioString) {
        this.bioString = bioString;
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
