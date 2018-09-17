package hu.tassiviktor.multiserver.protocolhandlers.httphandler;

import hu.tassiviktor.multiserver.interfaces.ProtocolHandlerInterface;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLException;

public class HttpHandler extends Observable implements ProtocolHandlerInterface {

    protected Socket socket;

    @Override
    public void run() {
        try {

            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            is.read();
            os.write(("HTTP/1.1 200 OK\n"
                    + "Content-Type: text/plain\n"
                    + "Content-Length: 20\n"
                    + "Connection: close\n\n Done. " + String.valueOf(System.currentTimeMillis() + "\n\n")).getBytes());
            os.flush();
        } catch (SSLException ex){
            Logger.getLogger(HttpHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            //Chrome ALWAYS close the connection...
            Logger.getLogger(HttpHandler.class.getName()).log(Level.INFO, ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(HttpHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            shutDown();
        }
    }

    protected void shutDown() {
        if (!getSocket().isClosed()) {
            try {
                getSocket().close();
            } catch (IOException ex) {
                Logger.getLogger(HttpHandler.class.getName()).log(Level.INFO, ex.getMessage());
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
