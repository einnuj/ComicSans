package model.metadata.fields;

/**
 * A Field class that represents a User's integer rating of a Comic
 * Created by einnuj on 4/7/2016.
 */
public class Rating extends AbstractField {
    private int rating;

    public Rating(String userOrigin, String comicTarget, int rating) {
        super(userOrigin, comicTarget);
        this.rating = (validateRating(rating)) ? rating : 0;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (validateRating(rating)) {
            this.rating = rating;
        }
    }

    private boolean validateRating(int rating) {
        return 0 <= rating && 5 >= rating;
    }
}
