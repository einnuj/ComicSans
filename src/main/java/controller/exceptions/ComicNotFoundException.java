package controller.exceptions;

/**
 * An Exception Class that represents the situation where a WebComic is expected to be in the DataStore but is not.
 * Created by einnuj on 4/22/2016.
 */
public class ComicNotFoundException extends Exception {

    public ComicNotFoundException(Long id) {
        super("WebComic was not found, but was expected to be, in DataStore with Long id: " + id);
    }
}
