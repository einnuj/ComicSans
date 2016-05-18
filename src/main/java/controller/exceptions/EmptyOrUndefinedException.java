package controller.exceptions;

public class EmptyOrUndefinedException extends Exception {

    public EmptyOrUndefinedException (String parameter) {
        super("Expected parameter \"" + parameter + "\" was empty or undefined.");
    }
}
