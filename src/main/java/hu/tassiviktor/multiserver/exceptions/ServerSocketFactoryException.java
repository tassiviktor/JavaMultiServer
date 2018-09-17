package hu.tassiviktor.multiserver.exceptions;

/**
 *
 * @author Viktor Tassi
 */
public class ServerSocketFactoryException extends Exception {

    public ServerSocketFactoryException() {
    }

    public ServerSocketFactoryException(String msg) {
        super(msg);
    }

    public ServerSocketFactoryException(String msg, Exception ex) {
        super(msg, ex);
    }
}
