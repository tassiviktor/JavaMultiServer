package hu.tassiviktor.multiserver.exceptions;

/**
 *
 * @author
 */
public class ListenerNotExistsException extends Exception {

    /**
     * Creates a new instance of <code>ListenerNotExistsException</code> without
     * detail message.
     */
    public ListenerNotExistsException() {
    }

    /**
     * Constructs an instance of <code>ListenerNotExistsException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public ListenerNotExistsException(String msg) {
        super(msg);
    }
}
