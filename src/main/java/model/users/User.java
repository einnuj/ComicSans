package model.users;

import model.metadata.UserMetadata;

/**
 * A Class that represents a User.
 * Created by einnuj.
 */
public class User {
    private UserMetadata metadata;

    public User(String name) {
        metadata = new UserMetadata(name);
    }

    /* Getters */

    public UserMetadata getMetadata() { return metadata; }

    /* Methods */

    public boolean hasIcon() {
        return null != getIconURL();
    }

    public String getIconURL() {
        return metadata.getDisplayPicture();
    }
}
