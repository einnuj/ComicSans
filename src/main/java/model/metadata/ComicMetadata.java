package model.metadata;

import model.metadata.fields.Comment;
import model.metadata.fields.Rating;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A class that represents the Metadata of a Comic.
 * Created by einnuj on 4/7/2016.
 */
public class ComicMetadata extends AbstractMetadata {
    private String author;              // This is a stand-in type
    private GenreEnum genre;

    private int favorites;
    private int likes;
    private float rating;

    private List<Comment> commentList;
    private HashMap<String, Rating> ratingMap;

    ComicMetadata(){}

    public ComicMetadata(String name, String author) {
        super(name);

        this.author = author;
        genre = GenreEnum.UNLISTED;
        commentList = new ArrayList<Comment>();
        ratingMap = new HashMap<String, Rating>();
    }

    public ComicMetadata(String name, String author, String genre){
        super(name);

        this.author = author;
        setEnum(genre);
        commentList = new ArrayList<>();
        ratingMap = new HashMap<>();
    }

    private void setEnum(String genre){
        for(GenreEnum g : GenreEnum.values()){
            if (genre.equals(g)){
                this.genre = g;
            }
        }
    }
    /* Getters */

    public String getAuthor() {
        return author;
    }

    public GenreEnum getGenre() {
        return genre;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public int getFavorites() {
        return favorites;
    }

    public int getLikes() {
        return likes;
    }

    public HashMap<String, Rating> getRatingMap() {
        return ratingMap;
    }


    /* Setters */

    public void setGenre(GenreEnum genre) {
        this.genre = genre;
    }

    public void addRating(Rating rating){
        ratingMap.put(rating.getUserOrigin(), rating);
    }

    /* Methods */

    public void incrementLike(){
        likes++;
    }

    public void decrementLike(){
        likes--;
    }

    public void incrementFaves(){
        favorites++;
    }

    public void decrementFaves(){
        favorites--;
    }

    public int getRatingAsInt() {
        if(ratingMap != null) {
            if(ratingMap.size() > 0) {
                double num = ratingMap.size();
                double total = 0;
                for (Rating r : ratingMap.values()) {
                    total += r.getRating();
                }
                return (int) Math.round(total / num);
            }
        }
        return 0;
    }

    public void addComment(Comment c){
        commentList.add(c);
    }

    public void deleteComment(int n){
        commentList.remove(n);
    }

    /**
     * Reinitializes any null collection in the object as returned by Objectify
     */
    public void reload() {
        if (commentList == null) {
            commentList = new ArrayList<Comment>();
        }
        if (ratingMap == null) {
            ratingMap = new HashMap<>();
        }
    }
}
