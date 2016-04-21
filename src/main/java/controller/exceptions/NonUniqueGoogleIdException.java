package controller.exceptions;

/**
 * An Exception class that represents a situation in which a Google ID is not unique in the Datastore.
 * Created by einnuj on 4/21/2016.
 */
public class NonUniqueGoogleIdException extends Exception {

    public NonUniqueGoogleIdException(String googleId) {
        super("More than one User in DataStore with googleId: " + googleId);
    }
}
