package model.metadata;

/**
 * An Abstract class that is the ancestor of all Metadata classes.
 * Created by einnuj.
 */
abstract class AbstractMetadata {
    private String bio;
    private String name;
    private String displayPicture;          // This is a stand-in type.

    AbstractMetadata(String name) {
        bio = "";
        this.name = name;
        displayPicture = "";
    }

    AbstractMetadata(){}

    /* Getters */

    public String getBio() {
        return bio;
    }

    public String getName() {
        return name;
    }

    public String getDisplayPicture() {
        return displayPicture;
    }

    /* Setters */

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setName(String name) { this.name = name; }

    public void setDisplayPicture(String displayPicture) {
        this.displayPicture = displayPicture;
    }

    /* Methods */
}
