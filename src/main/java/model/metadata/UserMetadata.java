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
    private List<Comment> comicsCommentedList;
    private List<Rating> comicsRatedList;

    int numComments;

    private String drawJson;


    //key = comicTarget
    private HashMap<String, Like> comicsLikedMap;

    private HashMap<String, Favorite> comicsFavoritedMap;

    public UserMetadata(String name) {
        super(name);

        comicsCreatedList = new ArrayList<WebComic>();

        comicsBookmarkedList = new ArrayList<Bookmark>();
        comicsCommentedList = new ArrayList<Comment>();
        comicsRatedList = new ArrayList<Rating>();

        comicsLikedMap = new HashMap<String, Like>();
        comicsFavoritedMap = new HashMap<String, Favorite>();

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

    public List<Comment> getComicsCommentedList() {
        return comicsCommentedList;
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

    public void addToComicsCreatedList(WebComic comic) {
        comicsCreatedList.add(comic);
    }

    public void addToBookmarkedList(Bookmark bookmark) {
        comicsBookmarkedList.add(bookmark);
    }

    public void addToCommentedList(Comment comment) {
        comicsCommentedList.add(comment);
    }

    public void addToRatedList(Rating rating) { comicsRatedList.add(rating); }

    public void removeFromComicsCreatedList(WebComic comic) {
        comicsCreatedList.remove(comic);
    }

    public void removeFromBookmarkedList(Bookmark bookmark) { comicsBookmarkedList.remove(bookmark); }

    public void removeFromCommentedList(Comment comment) { comicsCommentedList.remove(comment); }

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
        return comicsBookmarkedList.size();
    }

    public int getNumberOfComicsCommented() {
        return comicsCommentedList.size();
    }

    public int getNumberOfComicsRated() { return comicsRatedList.size(); }


    @Override
    public void reload() {
        super.reload();

        if (comicsCreatedList == null) {
            comicsCreatedList = new ArrayList<WebComic>();
        }

        if (comicsBookmarkedList == null) {
            comicsBookmarkedList = new ArrayList<Bookmark>();
        }

        if (comicsCommentedList == null) {
            comicsCommentedList = new ArrayList<Comment>();
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
