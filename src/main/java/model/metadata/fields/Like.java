package model.metadata.fields;

/**
 * A Field Class that represents a User's liking a Comic.
 * Created by einnuj on 4/7/2016.
 */
public class Like extends AbstractField {

    // Like Favorite, this class can essentially be empty.

    Like(){}

    public Like(String comicTarget, String userOrigin) {
        super(comicTarget, userOrigin);
    }
}
