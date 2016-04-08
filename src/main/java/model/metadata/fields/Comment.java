package model.metadata.fields;

import org.joda.time.DateTime;

/**
 * A Field class that represents a User's comments on a Comic
 * Created by einnuj on 4/7/2016.
 */
public class Comment extends AbstractField {
    private String description;
    private DateTime lastEditedTime;

    public Comment(String userOrigin, String comicTarget, String description) {
        super(userOrigin, comicTarget);
        setDescription(description);
    }

    public String getDescription() {
        return description;
    }

    public DateTime getLastEditedTime() {
        return lastEditedTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void updateLastEditedTime() {
        this.lastEditedTime = DateTime.now();
    }
}
