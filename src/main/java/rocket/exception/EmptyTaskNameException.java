package rocket.exception;

/**
 * Represents an exception thrown when the task name is empty.
 */
public class EmptyTaskNameException extends RocketRuntimeException {
    public EmptyTaskNameException(String message) {
        super(message);
    }
}
