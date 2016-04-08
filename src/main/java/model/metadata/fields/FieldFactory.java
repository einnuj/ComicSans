package model.metadata.fields;

/**
 * A Factory class that builds Fields.
 * Created by einnuj on 4/7/2016.
 */
public class FieldFactory {
    // Will become User Object
    private final String user;

    // Will become WebComic Object
    private final String comic;

    public FieldFactory(String user, String comic) {
        this.user = user;
        this.comic = comic;
    }

    public Bookmark getBookmark() {
        return new Bookmark(user, comic);
    }

    public Comment getComment(String description) {
        return new Comment(user, comic, description);
    }

    public Favorite getFavorite() {
        return new Favorite(user, comic);
    }

    public Like getLike() {
        return new Like(user, comic);
    }

    public Rating getRating(int rating) {
        return new Rating(user, comic, rating);
    }
}
