package model.metadata.fields;

/**
 * A Field class that represents a User's favoriting of a comic
 * Created by einnuj on 4/7/2016.
 */
public class Favorite extends AbstractField {

    // This class really doesn't need anything in it. The only relevant bits
    // needed are in the AbstractField class.

    public Favorite(String comicTarget, String userOrigin) {
        super(comicTarget, userOrigin);
    }

}
