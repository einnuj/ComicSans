package model.metadata.fields;

import com.google.appengine.repackaged.com.google.api.client.util.DateTime;

/**
 * A Field class that represents a User's integer rating of a Comic
 * Created by einnuj on 4/7/2016.
 */
public class Rating extends AbstractField {
    private int rating;

    public Rating(String userOrigin, String comicTarget, DateTime dateTime, int
                  rating) {
        super(userOrigin, comicTarget, dateTime);
        this.rating = (rating <= 5) ? rating : -1;
    }

    public int getRating() {
        return rating;
    }
}
