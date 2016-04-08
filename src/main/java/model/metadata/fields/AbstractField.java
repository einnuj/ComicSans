package model.metadata.fields;

import org.joda.time.DateTime;

/**
 * An Abstract class that acts as parent to all Fields
 * Created by einnuj on 4/7/2016.
 */
public abstract class AbstractField {
    // This will become a User Object
    private String userOrigin;

    // This will become a Comic Object
    private String comicTarget;

    private DateTime dateTimeCreated;

    public AbstractField(String userOrigin, String comicTarget) {
        this.userOrigin = userOrigin;
        this.comicTarget = comicTarget;
        this.dateTimeCreated = DateTime.now();
    }

    public String getUserOrigin() {
        return userOrigin;
    }

    public String getComicTarget() {
        return comicTarget;
    }

    public DateTime getDateTime() {
        return dateTimeCreated;
    }
}
