package controller.data;

import controller.exceptions.NonUniqueGoogleIdException;
import model.users.User;
import utilities.data.ObjectifyHelper;

import java.util.List;

/**
 * A Class that handles CRUD Users for Controllers against our DataBase.
 * Created by einnuj on 4/21/2016.
 */
public class UserAccess {

    /**
     * Attempts to return the first User with the relevant googleId.
     * @param googleId the unique String representation of a User
     * @return User Object with the same googleId parameter, or null if not found in DataStore
     * @throws NonUniqueGoogleIdException - thrown when more than one Users are returned from the query
     */
    public static User queryForUser(String googleId) throws NonUniqueGoogleIdException {
        List<User> userList = ObjectifyHelper.loadWithEqualsFilter(User.class, "googleId", googleId);

        if (userList.isEmpty()) {
            return null;
        }
        else if (userList.size() == 1) {
            User user = userList.get(0);
            user.getMetadata().reload();    // Reinstantiates any null Collection resulting from DS load
            return user;
        }
        else {
            throw new NonUniqueGoogleIdException(googleId);
        }
    }
}
