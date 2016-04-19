package controller.mock;

import model.comics.WebComic;
import model.metadata.ComicMetadata;
import model.metadata.fields.FieldFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * A Mock Controller that generates a hard-coded WebComic.
 * Created by einnuj on 4/12/2016.
 */
public class MockComicController {

    /* Methods */
    public WebComic genWebComic(String name, String author) {
        WebComic webComic = new WebComic(name, author);
        ComicMetadata metadata = webComic.getMetadata();

        // Filling in data
        metadata.setBio("A Comic about procrastination and other deadly sins");

        // Dummy Data - List of "Users" to work with
        List<String> userList = new ArrayList<String>();

        userList.add("Junnie");
        userList.add("Maggie");
        userList.add("John");
        userList.add("Chaerin");
        userList.add("Barack Obama");
        userList.add("Alexander Hamilton");
        userList.add("John Cena");
        userList.add("Richard McKenna");
        userList.add("Jennifer Wong");
        userList.add("Son Goku");

        // Randomly generate Fields per "User"
        for (String userName : userList) {
            FieldFactory fieldFactory = new FieldFactory(name, userName);

            MockFieldGenerator mockFieldGenerator = new MockFieldGenerator();
            mockFieldGenerator.genMockComicFields(metadata, fieldFactory);
        }

        return webComic;
    }
}
