package model.users;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import model.metadata.UserMetadata;

/**
 * A Class that represents a User.
 * Created by einnuj.
 */
@Entity
public class User {
    @Id
    private Long id;

    private UserMetadata metadata;

    private User() {}

    public User(String name) {
        metadata = new UserMetadata(name);
    }

    /* Getters */

    public Long getId() {return id; }

    public UserMetadata getMetadata() { return metadata; }

    /* Methods */

    public boolean hasIcon() {
        return null != getIconURL();
    }

    public String getIconURL() {
        return metadata.getDisplayPicture();
    }
}
