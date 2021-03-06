package controller.mock;

import model.comics.WebComic;
import model.metadata.ComicMetadata;
import model.metadata.GenreEnum;
import model.metadata.fields.FieldFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * A Mock Controller that generates a hard-coded WebComic.
 * Created by einnuj on 4/12/2016.
 */
public class MockComicController {

    /* Methods */
    public WebComic genWebComic() {
        MockFieldGenerator mockFieldGenerator = new MockFieldGenerator();
        String name = mockFieldGenerator.getRandomName();
        WebComic webComic = new WebComic(name, mockFieldGenerator.getRandomAuthor());
        ComicMetadata metadata = webComic.getMetadata();

        // Filling in data
        metadata.setBio(mockFieldGenerator.getRandomBio());

        int rando = (int) Math.floor(Math.random() * 9);

        switch(rando) {
            case 0:
                metadata.setGenre(GenreEnum.ACTION);
                break;
            case 1:
                metadata.setGenre(GenreEnum.COMEDY);
                break;
            case 2:
                metadata.setGenre(GenreEnum.DRAMA);
                break;
            case 3:
                metadata.setGenre(GenreEnum.FANTASY);
                break;
            case 4:
                metadata.setGenre(GenreEnum.GAMING);
                break;
            case 5:
                metadata.setGenre(GenreEnum.HORROR);
                break;
            case 6:
                metadata.setGenre(GenreEnum.ROMANCE);
                break;
            case 7:
                metadata.setGenre(GenreEnum.THRILLER);
                break;
            case 8:
                metadata.setGenre(GenreEnum.SPORTS);
                break;
            default:
                metadata.setGenre(GenreEnum.UNLISTED);
        }

        // Dummy Data - List of "Users" to work with
        List<String> userList = new ArrayList<String>();

        userList.add("Barack Obama");
        userList.add("Alexander Hamilton");
        userList.add("John Cena");
        userList.add("Son Goku");

        // Randomly generate Fields per "User"
        for (String userName : userList) {
            FieldFactory fieldFactory = new FieldFactory(name, userName);

            mockFieldGenerator.genMockComicFields(metadata, fieldFactory);
        }

        return webComic;
    }
}
