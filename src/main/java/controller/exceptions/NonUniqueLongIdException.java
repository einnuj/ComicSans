package controller.exceptions;

/**
 * An Exception Class that represents the situation where more than one entry shares the same Long id.
 * Created by einnuj on 4/22/2016.
 */
public class NonUniqueLongIdException extends Exception {

    public NonUniqueLongIdException(Long id) {
        super("More than one WebComic in DataStore with Long id: " + id);
    }
}
