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

    /* Methods */
    public void genMockComicFields(ComicMetadata metadata, FieldFactory
            fieldFactory) {

        for (int i = 0; i < 3;) {
            List<String> alreadyGeneratedFieldList = new ArrayList<String>();
            String fieldToBeAdded;

            switch ((int) Math.floor(Math.random() * 5)) {
                case 0:
                    fieldToBeAdded = "Bookmark";
                    if (!alreadyGeneratedFieldList.contains(fieldToBeAdded)) {
                        alreadyGeneratedFieldList.add(fieldToBeAdded);
                        metadata.addToBookmarkList(fieldFactory.getBookmark());
                        i++;
                    }
                    break;
                case 1:
                    fieldToBeAdded = "Comment";
                    if (!alreadyGeneratedFieldList.contains(fieldToBeAdded)) {
                        alreadyGeneratedFieldList.add(fieldToBeAdded);
                        metadata.addToCommentList(fieldFactory.getComment("Great " +
                                "Comic!"));
                        i++;
                    }
                    break;
                case 2:
                    fieldToBeAdded = "Favorite";
                    if (!alreadyGeneratedFieldList.contains(fieldToBeAdded)) {
                        alreadyGeneratedFieldList.add(fieldToBeAdded);
                        metadata.addToFavoriteList(fieldFactory.getFavorite());
                        i++;
                    }
                    break;
                case 3:
                    fieldToBeAdded = "Like";
                    if (!alreadyGeneratedFieldList.contains(fieldToBeAdded)) {
                        alreadyGeneratedFieldList.add(fieldToBeAdded);
                        metadata.addToLikeList(fieldFactory.getLike());
                        i++;
                    }
                    break;
                case 4:
                    fieldToBeAdded = "Rating";
                    if (!alreadyGeneratedFieldList.contains(fieldToBeAdded)) {
                        alreadyGeneratedFieldList.add(fieldToBeAdded);
                        metadata.addToRatingList(fieldFactory.getRating(4));
                        i++;
                    }
                    break;
                default:
                    System.out.println("Something that shouldn't have happened happened in our MockUserController.");
                    i = 100;
                    break;
            }
        }
    }

    public void genMockUserFields(UserMetadata metadata,FieldFactory
            fieldFactory) {

        for (int i = 0; i < 3;) {
            List<String> alreadyGeneratedFieldList = new ArrayList<String>();
            String fieldToBeAdded;

            switch ((int) Math.floor(Math.random() * 5)) {
                case 0:
                    fieldToBeAdded = "Bookmark";
                    if (!alreadyGeneratedFieldList.contains(fieldToBeAdded)) {
                        alreadyGeneratedFieldList.add(fieldToBeAdded);
                        metadata.addToBookmarkedList(fieldFactory.getBookmark());
                        i++;
                    }
                    break;
                case 1:
                    fieldToBeAdded = "Comment";
                    if (!alreadyGeneratedFieldList.contains(fieldToBeAdded)) {
                        alreadyGeneratedFieldList.add(fieldToBeAdded);
                        metadata.addToCommentedList(fieldFactory.getComment("Great Comic!"));
                        i++;
                    }
                    break;
                case 2:
                    fieldToBeAdded = "Favorite";
                    if (!alreadyGeneratedFieldList.contains(fieldToBeAdded)) {
                        alreadyGeneratedFieldList.add(fieldToBeAdded);
                        metadata.addToFavoritedList(fieldFactory.getFavorite());
                        i++;
                    }
                    break;
                case 3:
                    fieldToBeAdded = "Like";
                    if (!alreadyGeneratedFieldList.contains(fieldToBeAdded)) {
                        alreadyGeneratedFieldList.add(fieldToBeAdded);
                        metadata.addToLikedList(fieldFactory.getLike());
                        i++;
                    }
                    break;
                case 4:
                    fieldToBeAdded = "Rating";
                    if (!alreadyGeneratedFieldList.contains(fieldToBeAdded)) {
                        alreadyGeneratedFieldList.add(fieldToBeAdded);
                        metadata.addToRatedList(fieldFactory.getRating(4));
                        i++;
                    }
                    break;
                default:
                    System.out.println("Something that shouldn't have happened happened in our MockUserController.");
                    i = 100;
                    break;
            }
        }
    }
}
