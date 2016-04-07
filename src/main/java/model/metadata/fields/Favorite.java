package model.metadata.fields;

import com.google.appengine.repackaged.com.google.api.client.util.DateTime;

/**
 * A Field class that represents a User's favoriting of a comic
 * Created by einnuj on 4/7/2016.
 */
public class Favorite extends AbstractField {

    // This class really doesn't need anything in it. The only relevant bits
    // needed are in the AbstractField class.

    public Favorite(String userOrigin, String comicTarget, DateTime dateTime) {
        super(userOrigin, comicTarget, dateTime);
    }

}
