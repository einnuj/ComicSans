package model.metadata;

import model.metadata.fields.Comment;
import model.metadata.fields.Favorite;
import model.metadata.fields.Like;
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

    public ComicMetadata(String author, String name) {
        super(name);

        this.author = author;
        genre = GenreEnum.UNLISTED;
        ratingList = new ArrayList<Rating>();
    }

    /* Getters */

    public List<Rating> getRatingList() { return ratingList; }

    public GenreEnum getGenre() {
        return genre;
    }

    public void setGenre(GenreEnum genre) {
        this.genre = genre;
    }

    /* Setters */

    public String getAuthor() {
        return author;
    }

    /* Methods */

    public void addToRatingList(Rating rating) { ratingList.add(rating); }
}
