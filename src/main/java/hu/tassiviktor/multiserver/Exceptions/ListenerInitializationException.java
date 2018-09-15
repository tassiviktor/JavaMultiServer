package hu.tassiviktor.multiserver.Exceptions;

/**
 *
 * @author Viktor Tassi
 */
public class ListenerInitializationException extends Exception {

    public ListenerInitializationException() {
    }

    public ListenerInitializationException(String msg) {
        super(msg);
    }
     public ListenerInitializationException(String msg, Exception ex) {
        super(msg, ex);
    }
}
