package hu.tassiviktor.multiserver.exceptions;

/**
 *
 * @author
 */
public class ListenerExistsException extends Exception {

    public ListenerExistsException() {
    }

    public ListenerExistsException(String msg) {
        super(msg);
    }
}
