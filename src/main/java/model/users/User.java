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

    private List<ComicPage> bookmarks;

    private int numComments;

    private User() {}

    public User(String name, String googleId) {
        this.googleId = googleId;
        metadata = new UserMetadata(name);
        this.bookmarks = new ArrayList<ComicPage>();
    }

    /* Getters */

    public Long getId() { return id; }

    public String getGoogleId() { return googleId; }

    public UserMetadata getMetadata() { return metadata; }

    public String getDrawJson() { return drawJson; }

    public List<ComicPage> getBookmarks(){ return bookmarks; }

    /* Setters */

    public void setDrawJson(String drawJson){
        this.drawJson = drawJson;
    }


    /* Methods */

    public boolean hasIcon() {
        return null != getIconURL();
    }

    public String getIconURL() {
        return metadata.getDisplayPicture();
    }


    public void addBookmark(ComicPage comicPage){
        bookmarks.add(comicPage);
    }

    public void removeFromBookmarks(ComicPage comicPage){
        bookmarks.remove(comicPage);
    }
}
