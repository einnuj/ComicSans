package model.metadata.fields;

import com.google.appengine.repackaged.com.google.api.client.util.DateTime;

/**
 * An Abstract class that acts as parent to all Fields
 * Created by einnuj on 4/7/2016.
 */
public abstract class AbstractField {
    // This will become a User Object
    private String userOrigin;

    // This will become a Comic Object
    private String comicTarget;

    private DateTime dateTime;

    public AbstractField(String userOrigin, String comicTarget, DateTime
            dateTime) {
        this.userOrigin = userOrigin;
        this.comicTarget = comicTarget;
        this.dateTime = dateTime;
    }

    public String getUserOrigin() {
        return userOrigin;
    }

    public String getComicTarget() {
        return comicTarget;
    }

    public DateTime getDateTime() {
        return dateTime;
    }
}