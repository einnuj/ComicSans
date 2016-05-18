package controller.mock;

import model.metadata.ComicMetadata;
import model.metadata.UserMetadata;
import model.metadata.fields.FieldFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * A Class that randomly generates Fields for a Metadata that should not exist.
 * Created by einnuj on 4/14/2016.
 */
public class MockFieldGenerator {

    private ArrayList<String> listOfAuthors;
    private ArrayList<String> listOfNames;
    private ArrayList<String> listOfBios;

    public MockFieldGenerator() {
        listOfAuthors = new ArrayList<String>();
        listOfNames = new ArrayList<String>();
        listOfBios = new ArrayList<String>();
        init();
    }

    /* Methods */
    public void genMockComicFields(ComicMetadata metadata, FieldFactory
            fieldFactory) {
        List<String> alreadyGeneratedFieldList = new ArrayList<String>();

        for (int i = 0; i < 3;) {
            String fieldToBeAdded;

            switch ((int) Math.floor(Math.random() * 5)) {
                case 0:
                    fieldToBeAdded = "Bookmark";
                    if (!alreadyGeneratedFieldList.contains(fieldToBeAdded)) {
                        alreadyGeneratedFieldList.add(fieldToBeAdded);
                        //metadata.addToBookmarkList(fieldFactory.getBookmark(1, 5));
                        i++;
                    }
                    break;
                case 1:
                    fieldToBeAdded = "Comment";
                    if (!alreadyGeneratedFieldList.contains(fieldToBeAdded)) {
                        alreadyGeneratedFieldList.add(fieldToBeAdded);
                        //metadata.addToCommentList(fieldFactory.getComment("Great " +
                                //"Comic!"));
                        i++;
                    }
                    break;
                case 2:
                    fieldToBeAdded = "Favorite";
                    if (!alreadyGeneratedFieldList.contains(fieldToBeAdded)) {
                        alreadyGeneratedFieldList.add(fieldToBeAdded);
                        //metadata.addToFavoriteList(fieldFactory.getFavorite());
                        i++;
                    }
                    break;
                case 3:
                    fieldToBeAdded = "Like";
                    if (!alreadyGeneratedFieldList.contains(fieldToBeAdded)) {
                        alreadyGeneratedFieldList.add(fieldToBeAdded);
                        //metadata.addToLikeList(fieldFactory.getLike());
                        i++;
                    }
                    break;
                case 4:
                    fieldToBeAdded = "Rating";
                    if (!alreadyGeneratedFieldList.contains(fieldToBeAdded)) {
                        alreadyGeneratedFieldList.add(fieldToBeAdded);
                        //metadata.addToRatingList(fieldFactory.getRating(4));
                        i++;
                    }
                    break;
                default:
                    i = 100;
                    break;
            }
        }
    }

    public void genMockUserFields(UserMetadata metadata,FieldFactory
            fieldFactory) {
        List<String> alreadyGeneratedFieldList = new ArrayList<String>();

        for (int i = 0; i < 3;) {
            String fieldToBeAdded;

            switch ((int) Math.floor(Math.random() * 5)) {
                case 0:
                    fieldToBeAdded = "Bookmark";
                    if (!alreadyGeneratedFieldList.contains(fieldToBeAdded)) {
                        alreadyGeneratedFieldList.add(fieldToBeAdded);
                        //metadata.addToBookmarkedList(fieldFactory.getBookmark(1, 5));
                        i++;
                    }
                    break;
                case 1:
                    fieldToBeAdded = "Comment";
                    if (!alreadyGeneratedFieldList.contains(fieldToBeAdded)) {
                        alreadyGeneratedFieldList.add(fieldToBeAdded);
                        //metadata.addToCommentedList(fieldFactory.getComment("Great Comic!"));
                        i++;
                    }
                    break;
                case 2:
                    fieldToBeAdded = "Favorite";
                    if (!alreadyGeneratedFieldList.contains(fieldToBeAdded)) {
                        alreadyGeneratedFieldList.add(fieldToBeAdded);
                        //metadata.addToFavoritedList(fieldFactory.getFavorite());
                        i++;
                    }
                    break;
                case 3:
                    fieldToBeAdded = "Like";
                    if (!alreadyGeneratedFieldList.contains(fieldToBeAdded)) {
                        alreadyGeneratedFieldList.add(fieldToBeAdded);
                        //metadata.addToLikedList(fieldFactory.getLike());
                        i++;
                    }
                    break;
                case 4:
                    fieldToBeAdded = "Rating";
                    if (!alreadyGeneratedFieldList.contains(fieldToBeAdded)) {
                        alreadyGeneratedFieldList.add(fieldToBeAdded);
                        //metadata.addToRatedList(fieldFactory.getRating(4));
                        i++;
                    }
                    break;
                default:
                    i = 100;
                    break;
            }
        }
    }

    public String getRandomAuthor() {
        return listOfAuthors.get((int) Math.floor(Math.random() * listOfAuthors.size()));
    }

    public String getRandomName() {
        return listOfNames.get((int) Math.floor(Math.random() * listOfNames.size()));
    }

    public String getRandomBio() {
        return listOfBios.get((int) Math.floor(Math.random() * listOfBios.size()));
    }

    private void init() {
        listOfAuthors.add("Junnie");
        listOfAuthors.add("Chaerin");
        listOfAuthors.add("Maggie");
        listOfAuthors.add("John");
        listOfAuthors.add("Obama");
        listOfAuthors.add("McKenna");
        listOfAuthors.add("Wong");
        listOfAuthors.add("Fodor");

        listOfNames.add("Captain Hamerica");
        listOfNames.add("The Incredible Bulk");
        listOfNames.add("For: Son of Codin");
        listOfNames.add("Siren Man");
        listOfNames.add("man(steel)");
        listOfNames.add(".bat Man");
        listOfNames.add("Static Block");

        listOfBios.add("where procrastination and sleepiness go hand in hand");
        listOfBios.add("in a world where computers have had enough");
        listOfBios.add("The Professors are hell-bent on eradicating all students; only four remain.");
        listOfBios.add("A boring comic where all people are nice and thoughtful");
        listOfBios.add("people are really, really tall. What chaos ensues!");
        listOfBios.add("All the video-game instances of characters that have died are reborn all at once.");
    }
}
