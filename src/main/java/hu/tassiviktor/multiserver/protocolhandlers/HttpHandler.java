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
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(HttpHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        safeClose();
    }

    protected void safeClose() {
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
        this.notifyObservers(this);
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

}
