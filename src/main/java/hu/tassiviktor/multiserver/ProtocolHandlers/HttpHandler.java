package hu.tassiviktor.multiserver.ProtocolHandlers;

import hu.tassiviktor.multiserver.Interfaces.ProtocolHandlerInterface;
import java.net.Socket;

public class HttpHandler implements ProtocolHandlerInterface {
    
    protected Socket socket;
    protected Runnable onClose;
    
    @Override
    public void run() {
        // handle this
        
    }
   
    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }


    
}
