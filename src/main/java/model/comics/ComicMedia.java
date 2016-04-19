package model.comics;

/**
 * The ancestor class of all Comic Media Objects
 * Created by einnuj on 4/7/2016.
 */
abstract class ComicMedia {
    private String name;

    ComicMedia() {}

    ComicMedia(String name) {
        this.name = name;
    }

    /* Getters */

    public String getName() {
        return name;
    }
}
