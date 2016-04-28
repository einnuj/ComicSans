package model.metadata;

import model.comics.WebComic;
import model.metadata.fields.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A Class that represents the Metadata of a User.
 * Created by einnuj.
 */
public class UserMetadata extends AbstractMetadata {
    private List<WebComic> comicsCreatedList;

    private List<Bookmark> comicsBookmarkedList;
    private List<Favorite> comicsFavoritedList;
    private List<Like> comicsLikedList;

    int numComments;

    //key = comicTarget
    private HashMap<String, Like> comicsLiked;

    private HashMap<String, Favorite> comicsFavorited;

    public UserMetadata(String name) {
        super(name);

        comicsCreatedList = new ArrayList<WebComic>();

        comicsBookmarkedList = new ArrayList<Bookmark>();
        comicsFavoritedList = new ArrayList<Favorite>();
        comicsLikedList = new ArrayList<Like>();
        comicsLiked = new HashMap<String, Like>();
        numComments = 0;
    }

    UserMetadata(){}

    /* Getters */

    public List<WebComic> getComicsCreatedList() {
        return comicsCreatedList;
    }

    public List<Bookmark> getComicsBookmarkedList() {
        return comicsBookmarkedList;
    }


    public List<Favorite> getComicsFavoritedList() {
        return comicsFavoritedList;
    }

    public List<Like> getComicsLikedList() {
        return comicsLikedList;
    }


    public HashMap<String, Like> getLikes(){return comicsLiked;}

    public HashMap<String, Favorite> getFavorites(){ return comicsFavorited; }

    public int getNumComments(){
        return numComments;
    }

    /* Methods */

    public void addToComicsCreatedList(WebComic comic) {
        comicsCreatedList.add(comic);
    }

    public void addToBookmarkedList(Bookmark bookmark) {
        comicsBookmarkedList.add(bookmark);
    }


    public void addToFavoritedList(Favorite favorite) {
        comicsFavoritedList.add(favorite);
    }

    public void addToLikedList(Like like) {
        comicsLikedList.add(like);
    }


    public void addToLikes(String comicTarget, Like like) {
        comicsLiked.put(comicTarget, like);
    }

    public void removeFromLikes(String comicTarget) { comicsLiked.remove(comicTarget); }

    public void addToFavorites(String comicTarget, Favorite fave) { comicsFavorited.put(comicTarget, fave); }

    public void removeFromFavorites(String comicTarget) { comicsFavorited.remove(comicTarget); }

    public void incrementComment(){
        numComments++;
    }


    public void removeFromFavorites(Favorite favorite) { comicsFavoritedList.remove(favorite); }

    public void removeFromBookmarks(Bookmark bookmark) { comicsBookmarkedList.remove(bookmark); }

    public int getNumberOfComicsBookmarked() {
        return comicsBookmarkedList.size();
    }

    public int getNumberOfComicsFavorited() {
        return comicsFavoritedList.size();
    }

    public int getNumberOfComicsLiked() {
        return comicsLikedList.size();
    }


    @Override
    public void reload() {
        super.reload();

        if (comicsCreatedList == null) {
            comicsCreatedList = new ArrayList<WebComic>();
        }
        if (comicsBookmarkedList == null) {
            comicsBookmarkedList = new ArrayList<Bookmark>();
        }

        if (comicsFavoritedList == null) {
            comicsFavoritedList = new ArrayList<Favorite>();
        }
        if (comicsLikedList == null) {
            comicsLikedList = new ArrayList<Like>();
        }

    }
}
