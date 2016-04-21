package model.metadata.fields;

import org.joda.time.DateTime;

/**
 * An Abstract class that acts as parent to all Fields
 * Created by einnuj on 4/7/2016.
 */
abstract class AbstractField {
    // This will become a Comic Object
    private String comicTarget;
    // This will become a User Object
    private String userOrigin;

    private long timeCreatedMillis;

    AbstractField(String comicTarget, String userOrigin) {
        this.comicTarget = comicTarget;
        this.userOrigin = userOrigin;
        this.timeCreatedMillis = System.currentTimeMillis();
    }

    /* Getters */

    public String getComicTarget() {
        return comicTarget;
    }

    public String getUserOrigin() {
        return userOrigin;
    }

    public long getTimeCreatedMillis() {
        return timeCreatedMillis;
    }
}
