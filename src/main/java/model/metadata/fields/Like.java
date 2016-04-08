package model.metadata.fields;

/**
 * A Field Class that represents a User's liking a Comic.
 * Created by einnuj on 4/7/2016.
 */
public class Like extends AbstractField {

    // Like Favorite, this class can essentially be empty.

    public Like(String userOrigin, String comicTarget) {
        super(userOrigin, comicTarget);
    }
}
