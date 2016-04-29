package controller.exceptions;

/**
 * An Exception that denotes an expected and mandatory Request Parameter.
 * Created by einnuj on 4/29/2016.
 */
public class ParameterNotFoundException extends Exception {

    public ParameterNotFoundException(String parameter) {
        super("Expected parameter \"" + parameter + "\" not found.");
    }
}
