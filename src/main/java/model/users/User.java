package model.users;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import model.comics.ComicPage;
import model.comics.WebComic;
import model.metadata.UserMetadata;

import java.util.ArrayList;
import java.util.List;

/**
 * A Class that represents a User.
 * Created by einnuj.
 */
@Entity
public class User {
    @Id
    private Long id;

    @Index
    private String googleId;

    private UserMetadata metadata;

    private String drawJson;

    private List<WebComic> likedComics;

    private List<WebComic> favorites;

    private List<ComicPage> bookmarks;

    private int numComments;

    private User() {}

    public User(String name, String googleId) {
        this.googleId = googleId;
        metadata = new UserMetadata(name);
        this.likedComics = new ArrayList<WebComic>();
        this.favorites = new ArrayList<WebComic>();
        this.bookmarks = new ArrayList<ComicPage>();
    }

    /* Getters */

    public Long getId() { return id; }

    public String getGoogleId() { return googleId; }

    public UserMetadata getMetadata() { return metadata; }

    public String getDrawJson() { return drawJson; }

    public List<WebComic> getLikedComics() { return likedComics; }

    public List<WebComic> getFavorites() { return favorites; }

    public int getNumComments() { return numComments; }

    public List<ComicPage> getBookmarks(){ return bookmarks; }

    /* Setters */

    public void setDrawJson(String drawJson){
        this.drawJson = drawJson;
    }

    public void setLikedComics(List<WebComic> comics) { this.likedComics = comics; }

    public void setFavorites(List<WebComic> comics) { this.favorites = comics; }

    public void setNumComments(int numComments){ this.numComments = numComments; }

    /* Methods */

    public boolean hasIcon() {
        return null != getIconURL();
    }

    public String getIconURL() {
        return metadata.getDisplayPicture();
    }

    public void addToLikes(WebComic comic){
        likedComics.add(comic);
    }

    public void removeFromLikes(WebComic comic){
        likedComics.remove(comic);
    }

    public void addToFavorites(WebComic comic){
        favorites.add(comic);
    }

    public void removeFromFavorites(WebComic comic){
        favorites.remove(comic);
    }

    public void incrementComment(){
        numComments++;
    }

    public void addBookmark(ComicPage comicPage){
        bookmarks.add(comicPage);
    }

    public void removeFromBookmarks(ComicPage comicPage){
        bookmarks.remove(comicPage);
    }
}
