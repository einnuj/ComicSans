package model.metadata.fields;

/**
 * A Field class that represents a User's integer rating of a Comic
 * Created by einnuj on 4/7/2016.
 */
public class Rating extends AbstractField {
    private int rating;

    public Rating(String comicTarget, String userOrigin, int rating) {
        super(comicTarget, userOrigin);

        this.rating = (validateRating(rating)) ? rating : 0;
    }


    /* Getters */

    public int getRating() {
        return rating;
    }

    /* Setters */

    public void setRating(int rating) {
        if (validateRating(rating)) {
            this.rating = rating;
        }
    }

    /* Private Methods */

    private boolean validateRating(int rating) {
        return 0 <= rating && 5 >= rating;
    }
}
