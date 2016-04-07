package model.metadata.fields;

import com.google.appengine.repackaged.com.google.api.client.util.DateTime;

/**
 * A Field Class that represents a User's bookmarking
 * Created by einnuj on 4/7/2016.
 */
public class Bookmark extends AbstractField {
    
    // Like Favorite and such, there are no fields/methods necessary.

    public Bookmark(String userOrigin, String comicTarget, DateTime dateTime) {
        super(userOrigin, comicTarget, dateTime);
    }
}
