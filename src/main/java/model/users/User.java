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

    /* Methods */

    // TODO: implement relevant change in AbstractMetadata class
    public boolean hasIcon() {
        return false;
    }

    // TODO: implement relevant change in AbstractMetadata class
    public String getIconURL() {
        return null;
    }
}
