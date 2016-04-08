package model.comics;

/**
 * The ancestor class of all Comic Media Objects
 * Created by einnuj on 4/7/2016.
 */
public abstract class ComicMedia {
    private String name;

    public ComicMedia(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
