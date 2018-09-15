package hu.tassiviktor.multiserver.Exceptions;

/**
 *
 * @author
 */
public class ListenerNotRemoved extends Exception {

    /**
     * Creates a new instance of <code>ListenerNotRemoved</code> without detail
     * message.
     */
    public ListenerNotRemoved() {
    }

    /**
     * Constructs an instance of <code>ListenerNotRemoved</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ListenerNotRemoved(String msg) {
        super(msg);
    }
}
