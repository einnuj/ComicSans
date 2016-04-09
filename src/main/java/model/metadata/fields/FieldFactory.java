package model.metadata.fields;

/**
 * A Factory class that builds Fields.
 * Created by einnuj on 4/7/2016.
 */
public class FieldFactory {
    // Will become WebComic Object
    private final String comic;
    // Will become User Object
    private final String user;

    public FieldFactory(String comic, String user) {
        this.comic = comic;
        this.user = user;
    }

    /* Factory Methods */

    public Bookmark getBookmark() {
        return new Bookmark(comic, user);
    }

    public Comment getComment(String description) {
        return new Comment(comic, user, description);
    }

    public Favorite getFavorite() {
        return new Favorite(comic, user);
    }

    public Like getLike() {
        return new Like(comic, user);
    }

    public Rating getRating(int rating) {
        return new Rating(comic, user, rating);
    }
}
