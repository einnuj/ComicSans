package model.metadata;

import model.metadata.fields.Comment;
import model.metadata.fields.Rating;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents the Metadata of a Comic.
 * Created by einnuj on 4/7/2016.
 */
public class ComicMetadata extends AbstractMetadata {
    private String author;              // This is a stand-in type

    private GenreEnum genre;

    private List<Comment> comicsCommentedList;

    private List<Rating> ratingList;

    private int rating;

    ComicMetadata(){};

    public ComicMetadata(String name, String author) {
        super(name);

        this.author = author;
        genre = GenreEnum.UNLISTED;
        ratingList = new ArrayList<Rating>();
        comicsCommentedList = new ArrayList<Comment>();

    }

    /* Getters */

    public List<Rating> getRatingList() { return ratingList; }

    public GenreEnum getGenre() {
        return genre;
    }

    public void setGenre(GenreEnum genre) {
        this.genre = genre;
    }

    public List<Comment> getComicsCommentedList() {
        return comicsCommentedList;
    }



    public double getRating(){
        int total = 0;
        int numRatings = ratingList.size();
        for(Rating rate : ratingList){
            total += rate.getRating();
        }
        double realRating = total/numRatings;
        return (Math.round(realRating * 2) / 2.0);
    }

    /* Setters */

    public String getAuthor() {
        return author;
    }

    /* Methods */

    public void addToRatingList(Rating rating) { ratingList.add(rating); }

    public void addToCommentedList(Comment comment) {
        comicsCommentedList.add(comment);
    }

    public int getNumberOfComicsCommented() {
        return comicsCommentedList.size();
    }


    @Override
    public void reload() {
        super.reload();

        if (ratingList == null) {
            ratingList = new ArrayList<Rating>();
        }
        if (comicsCommentedList == null) {
            comicsCommentedList = new ArrayList<Comment>();
        }
    }
}
