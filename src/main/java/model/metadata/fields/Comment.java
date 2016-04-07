package model.metadata.fields;

import com.google.appengine.repackaged.com.google.api.client.util.DateTime;

/**
 * A Field class that represents a User's comments on a Comic
 * Created by einnuj on 4/7/2016.
 */
public class Comment extends AbstractField {
    private String description;

    public Comment(String userOrigin, String comicTarget, DateTime
            dateTime, String description) {
        super(userOrigin, comicTarget, dateTime);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
