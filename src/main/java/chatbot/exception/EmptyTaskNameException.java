package chatbot.exception;

public class EmptyTaskNameException extends RuntimeException {
    public EmptyTaskNameException(String message) {
        super(message);
    }
}
