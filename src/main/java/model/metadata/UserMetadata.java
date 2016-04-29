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
    private List<Rating> comicsRatedList;

    int numComments;

    private String drawJson;


    //key = comicTarget
    private HashMap<String, Like> comicsLikedMap;

    private HashMap<String, Favorite> comicsFavoritedMap;

    private HashMap<String, WebComic> comicsCreatedMap;

    private HashMap<String, Bookmark> comicsBookmarkedMap;

    public UserMetadata(String name) {
        super(name);

        comicsCreatedMap = new HashMap<String, WebComic>();

        comicsBookmarkedMap = new HashMap<String, Bookmark>();

        comicsRatedList = new ArrayList<Rating>();

        comicsLikedMap = new HashMap<String, Like>();

        comicsFavoritedMap = new HashMap<String, Favorite>();

        numComments = 0;
    }

    UserMetadata(){}

    /* Getters */

    public HashMap<String, WebComic> getComicsCreatedMap() {
        return comicsCreatedMap;
    }

    public HashMap<String, Bookmark> getComicsBookmarkedMap() {
        return comicsBookmarkedMap;
    }

    public List<Rating> getComicsRatedList() { return comicsRatedList; }

    public HashMap<String, Like> getLikes(){return comicsLikedMap;}

    public HashMap<String, Favorite> getFavorites(){ return comicsFavoritedMap; }

    public int getNumComments(){
        return numComments;
    }

    public String getDrawJson() { return drawJson; }

    /* Setters */

    public void setDrawJson(String drawJson){
        this.drawJson = drawJson;
    }

    /* Methods */

    public void addToComicsCreated(WebComic comic) {
        comicsCreatedMap.put(comic.getId().toString(), comic);
    }

    public void addToBookmarks(Bookmark bookmark) {
        comicsBookmarkedMap.put(bookmark.getComicTarget(), bookmark);
    }

    public void addToRatedList(Rating rating) { comicsRatedList.add(rating); }

    public void removeFromComicsCreated(WebComic comic) {
        comicsCreatedMap.remove(comic.getId().toString());
    }

    public void removeFromBookmarks(String comicId) { comicsBookmarkedMap.remove(comicId); }

    public void removeFromRatedList(Rating rating) { comicsRatedList.remove(rating); }



    public void addToLikesMap(Like like) {
        comicsLikedMap.put(like.getComicTarget(), like);
    }

    public void removeFromLikesMap(Like like) { comicsLikedMap.remove(like.getComicTarget()); }

    public void addToFavoritesMap(Favorite fave) { comicsFavoritedMap.put(fave.getComicTarget(), fave); }

    public void removeFromFavoritesMap(Favorite fave) { comicsFavoritedMap.remove(fave.getComicTarget()); }

    public void incrementComment(){
        numComments++;
    }

    public int getNumberOfComicsBookmarked() {
        return comicsBookmarkedMap.size();
    }

    public int getNumberOfComicsRated() { return comicsRatedList.size(); }


    public void reload() {

        if (comicsCreatedMap == null) {
            comicsCreatedMap = new HashMap<String, WebComic>();
        }

        if (comicsBookmarkedMap == null) {
            comicsBookmarkedMap = new HashMap<String, Bookmark>();
        }

        if (comicsRatedList == null) {
            comicsRatedList = new ArrayList<Rating>();
        }

        if (comicsLikedMap == null) {
            comicsLikedMap = new HashMap<String, Like>();
        }

        if (comicsFavoritedMap == null) {
            comicsFavoritedMap = new HashMap<String, Favorite>();
        }

    }
}
