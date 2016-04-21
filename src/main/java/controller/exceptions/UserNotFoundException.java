package controller.exceptions;

/**
 * An Exception that represents the situation in which a User is expected to be found but is not.
 * Created by einnuj on 4/21/2016.
 */
public class UserNotFoundException extends Exception {

    public UserNotFoundException(String googleId) {
        super("User was not found, but was expected to be, in Datastore with googleId: " + googleId);
    }
}
