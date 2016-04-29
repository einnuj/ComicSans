package controller.exceptions;

/**
 * An Exception that represents the state where a Servlet is passed an Action for which we have not defined.
 * Created by einnuj on 4/29/2016.
 */
public class UndefinedActionException extends Exception {

    public UndefinedActionException(String action) {
        super("Received undefined action parameter: " + action);
    }
}
