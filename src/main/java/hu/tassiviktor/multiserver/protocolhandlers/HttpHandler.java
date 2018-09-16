package hu.tassiviktor.multiserver.protocolhandlers;

import hu.tassiviktor.multiserver.interfaces.ProtocolHandlerInterface;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpHandler extends Observable implements ProtocolHandlerInterface {

    protected Socket socket;

    @Override
    public void run() {
        
        shutDown();
    }

    protected void shutDown() {
        if (!getSocket().isClosed()) {
            try {
                getSocket().close();
            } catch (IOException ex) {

            }
        }
        notifyObserver();
    }

    protected void notifyObserver() {
        setChanged();
        notifyObservers(this);
    }

    @Override
    public Socket getSocket() {
        return socket;
    }

    @Override
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

}
