package controller.mock;

import model.metadata.UserMetadata;
import model.metadata.fields.FieldFactory;
import model.users.User;

import java.util.ArrayList;
import java.util.List;

/**
 * A Mock Class that generates a hard-coded User
 * Created by einnuj on 4/12/2016.
 */
public class MockUserController {

    /* Methods */
    public User genMockUser(String userName) {
        User user = new User(userName);
        UserMetadata metadata = user.getMetadata();

        // Filling in random data
        metadata.setBio("This is my bio!");

        // List of Comics to work with
        List<String> comicsList = new ArrayList<String>();

        comicsList.add("CoCo the Nut");
        comicsList.add("The Misadventures of Goat the GOAT-goat");
        comicsList.add("Androgynous Android");
        comicsList.add("Two Ton Munich");
        comicsList.add("The Pearls of Prussia");
        comicsList.add("I Can't Believe You've Done This");
        comicsList.add("Steve Jobs In Purgatory");
        comicsList.add("Finding Nemo A Job");
        comicsList.add("Avatar: the Last Fender-Bender");
        comicsList.add("Link In Park");

        // Generating random Fields and tie to the User
        for (String comicName : comicsList) {

            FieldFactory fieldFactory = new FieldFactory(comicName, userName);

            MockFieldGenerator mockFieldGenerator = new MockFieldGenerator();
            mockFieldGenerator.genMockUserFields(metadata, fieldFactory);
        }

        return user;
    }
}
