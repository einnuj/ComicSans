package controller.exceptions;

public class RepeatTitleException extends Exception {

    public RepeatTitleException(String title) {
        super("Title \"" + title + "\" is already a title for another issue.");
    }
}
