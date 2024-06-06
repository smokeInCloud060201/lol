package vn.com.lol.exceptions;

public class ResourceExistsException extends Exception {
    public ResourceExistsException(String message) {
        super(message);
    }

    public ResourceExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
