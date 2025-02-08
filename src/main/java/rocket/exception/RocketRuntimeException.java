package rocket.exception;

/**
 * Represents an exception thrown when an error occurs during the execution of Rocket.
 */
public class RocketRuntimeException extends RuntimeException{
    public RocketRuntimeException(String message) {
        super(message);
    }
}
