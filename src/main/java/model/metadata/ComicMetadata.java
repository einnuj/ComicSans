package model.metadata;

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

    private List<Rating> ratingList;

    ComicMetadata(){}

    public ComicMetadata(String name, String author) {
        super(name);

        this.author = author;
        genre = GenreEnum.UNLISTED;
        ratingList = new ArrayList<Rating>();
    }

    /* Getters */

    public String getAuthor() {
        return author;
    }

    public GenreEnum getGenre() {
        return genre;
    }

    public List<Rating> getRatingList() { return ratingList; }

    /* Setters */

    public void setGenre(GenreEnum genre) {
        this.genre = genre;
    }

    /* Methods */

    public void addToRatingList(Rating rating) { ratingList.add(rating); }

    public double calculateRating(){
        double total = 0;
        for(Rating rate : ratingList){
            total += rate.getRating();
        }
        double realRating = total/ratingList.size();
        return (Math.round(realRating * 2) / 2.0);
    }

    @Override
    public void reload() {
        super.reload();

        if (ratingList == null) {
            ratingList = new ArrayList<Rating>();
        }
    }
}
