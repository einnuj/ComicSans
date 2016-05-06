package model.metadata.fields;

/**
 * A Field class that represents a User's comments on a Comic
 * Created by einnuj on 4/7/2016.
 */
public class Comment extends AbstractField {
    private String description;

    // If 0 -> unedited
    private long lastEditedTimeMillis;

    Comment(){}

    public Comment(String comicTarget, String userOrigin, String description) {
        super(comicTarget, userOrigin);

        setDescription(description);
    }

    /* Getters */

    public String getDescription() {
        return description;
    }

    public long getLastEditedTimeMillis() {
        return lastEditedTimeMillis;
    }

    /* Setters */

    public void setDescription(String description) {
        this.description = description;
    }

    /* Methods */


}
