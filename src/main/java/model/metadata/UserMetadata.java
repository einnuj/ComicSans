package model.metadata;

import model.comics.WebComic;
import model.metadata.fields.*;

import java.util.*;

/**
 * A Class that represents the Metadata of a User.
 * Created by einnuj.
 */
public class UserMetadata extends AbstractMetadata {
    private HashMap<String, Rating> comicsRated;

    int numComments;

    private String drawJson;

    //key = comicTarget
    private HashMap<String, Like> comicsLikedMap;

    private HashMap<String, Favorite> comicsFavoritedMap;

    private HashMap<String, WebComic> comicsCreatedMap;

    private HashMap<String, Bookmark> comicsBookmarkedMap;

    private Queue<WebComic> userHistory;

    private HashMap<String, WebComic> subscriptions;

    public UserMetadata(String name) {
        super(name);

        comicsCreatedMap = new HashMap<String, WebComic>();

        comicsBookmarkedMap = new HashMap<String, Bookmark>();

        comicsRated = new HashMap<>();

        comicsLikedMap = new HashMap<String, Like>();

        comicsFavoritedMap = new HashMap<String, Favorite>();

        userHistory = new LinkedList<>();

        subscriptions = new HashMap<>();

        numComments = 0;
    }

    UserMetadata(){}

    /* Getters */

    public HashMap<String, WebComic> getSubscriptions(){
        return subscriptions;
    }

    public Queue<WebComic> getUserHistory(){
        return userHistory;
    }

    public HashMap<String, WebComic> getComicsCreatedMap() {
        return comicsCreatedMap;
    }

    public HashMap<String, Bookmark> getComicsBookmarkedMap() {
        return comicsBookmarkedMap;
    }

    public HashMap<String, Rating> getComicsRated() { return comicsRated; }

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

    public boolean hasLike(WebComic comic){
        if(comicsLikedMap.containsKey(comic.getId().toString())) return true;
        else return false;
    }

    public boolean hasSubscription(WebComic comic){
        if(subscriptions.containsKey(comic.getId().toString())) return true;
        else return false;
    }

    public boolean hasBookmark(WebComic comic){
        if(comicsBookmarkedMap.containsKey(comic.getId().toString())) return true;
        else return false;
    }

    public boolean hasRated(WebComic comic){
        if(comicsRated.containsKey(comic.getId().toString())) return true;
        else return false;
    }

    public boolean hasFavorited(WebComic comic){
        if(comicsFavoritedMap.containsKey(comic.getId().toString())) return true;
        else return false;
    }

    public void addSubscription(WebComic comic){
        subscriptions.put(comic.getId().toString(), comic);
    }

    public void removeSubscription(WebComic comic){
        subscriptions.remove(comic.getId().toString());
    }

    public void addToComicsCreated(WebComic comic) {
        comicsCreatedMap.put(comic.getId().toString(), comic);
    }

    public void addToBookmarks(Bookmark bookmark) {
        comicsBookmarkedMap.put(bookmark.getComicTarget(), bookmark);
    }

    public void addToRatedMap(Rating rating) { comicsRated.put(rating.getComicTarget(), rating); }

    public void removeFromComicsCreated(WebComic comic) {
        comicsCreatedMap.remove(comic.getId().toString());
    }

    public void removeFromBookmarks(String comicId) { comicsBookmarkedMap.remove(comicId); }

    public void removeFromRatedMap(Rating rating) { comicsRated.remove(rating.getComicTarget()); }

    public void addToHistory(WebComic comic){
        if(userHistory.size() >= 10){
            userHistory.remove();
        }
        userHistory.add(comic);
    }

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

    public int getNumberOfComicsRated() { return comicsRated.size(); }


    public void reload() {

        if (comicsCreatedMap == null) {
            comicsCreatedMap = new HashMap<String, WebComic>();
        }

        if (comicsBookmarkedMap == null) {
            comicsBookmarkedMap = new HashMap<String, Bookmark>();
        }

        if (comicsRated == null) {
            comicsRated = new HashMap<>();
        }

        if (comicsLikedMap == null) {
            comicsLikedMap = new HashMap<String, Like>();
        }

        if (comicsFavoritedMap == null) {
            comicsFavoritedMap = new HashMap<String, Favorite>();
        }

        if(userHistory == null) {
            userHistory = new LinkedList<WebComic>();
        }

        if(subscriptions == null) {
            subscriptions = new HashMap<>();
        }

    }
}
