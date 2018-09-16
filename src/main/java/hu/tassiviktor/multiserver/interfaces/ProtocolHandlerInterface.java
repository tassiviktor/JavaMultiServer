package hu.tassiviktor.multiserver.interfaces;

import java.net.Socket;
import java.util.Observer;

/**
 *
 * @author Viktor Tassi
 */
public interface ProtocolHandlerInterface extends Runnable {
    
    public Socket getSocket();
    public void setSocket(Socket socket);
    public void addObserver(Observer observer);
    
}
