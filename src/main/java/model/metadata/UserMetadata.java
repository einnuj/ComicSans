package model.metadata;

import model.comics.WebComic;
import model.metadata.fields.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A Class that represents the Metadata of a User.
 * Created by einnuj.
 */
public class UserMetadata extends AbstractMetadata {
    private List<WebComic> comicsCreatedList;

    private List<Bookmark> comicsBookmarkedList;
    private List<Comment> comicsCommentedList;
    private List<Favorite> comicsFavoritedList;
    private List<Like> comicsLikedList;
    private List<Rating> comicsRatedList;

    public UserMetadata(String name) {
        super(name);

        comicsCreatedList = new ArrayList<WebComic>();

        comicsBookmarkedList = new ArrayList<Bookmark>();
        comicsCommentedList = new ArrayList<Comment>();
        comicsFavoritedList = new ArrayList<Favorite>();
        comicsLikedList = new ArrayList<Like>();
        comicsRatedList = new ArrayList<Rating>();
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

    public List<Favorite> getComicsFavoritedList() {
        return comicsFavoritedList;
    }

    public List<Like> getComicsLikedList() {
        return comicsLikedList;
    }

    public List<Rating> getComicsRatedList() {
        return comicsRatedList;
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

    public void addToFavoritedList(Favorite favorite) {
        comicsFavoritedList.add(favorite);
    }

    public void addToLikedList(Like like) {
        comicsLikedList.add(like);
    }

    public void addToRatedList(Rating rating) {
        comicsRatedList.add(rating);
    }

    public int getNumberOfComicsBookmarked() {
        return comicsBookmarkedList.size();
    }

    public int getNumberOfComicsCommented() {
        return comicsCommentedList.size();
    }

    public int getNumberOfComicsFavorited() {
        return comicsFavoritedList.size();
    }

    public int getNumberOfComicsLiked() {
        return comicsLikedList.size();
    }

    public int getNumberOfComicsRated() {
        return comicsRatedList.size();
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
        if (comicsCommentedList == null) {
            comicsCommentedList = new ArrayList<Comment>();
        }
        if (comicsFavoritedList == null) {
            comicsFavoritedList = new ArrayList<Favorite>();
        }
        if (comicsLikedList == null) {
            comicsLikedList = new ArrayList<Like>();
        }
        if (comicsRatedList == null) {
            comicsRatedList = new ArrayList<Rating>();
        }
    }
}
