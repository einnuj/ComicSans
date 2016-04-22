package model.users;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import model.metadata.UserMetadata;

/**
 * A Class that represents a User.
 * Created by einnuj.
 */
@Entity
public class User {
    @Id
    private Long id;

    @Index
    private String googleId;

    private UserMetadata metadata;

    private String drawJson;

    private User() {}

    public User(String name, String googleId) {
        this.googleId = googleId;
        metadata = new UserMetadata(name);
    }

    /* Getters */

    public Long getId() { return id; }

    public String getGoogleId() { return googleId; }

    public UserMetadata getMetadata() { return metadata; }

    public String getDrawJson() { return drawJson; }

    /* Setters */

    public void setDrawJson(String drawJson){
        this.drawJson = drawJson;
    }

    /* Methods */

    public boolean hasIcon() {
        return null != getIconURL();
    }

    public String getIconURL() {
        return metadata.getDisplayPicture();
    }
}
