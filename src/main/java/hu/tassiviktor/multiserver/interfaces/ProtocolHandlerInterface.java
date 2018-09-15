package hu.tassiviktor.multiserver.interfaces;

import java.net.Socket;

/**
 *
 * @author Viktor Tassi
 */
public interface ProtocolHandlerInterface extends Runnable {
    
    public Socket getSocket();
    public void setSocket(Socket socket);
    
}
