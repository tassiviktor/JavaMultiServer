package hu.tassiviktor.multiserver;

import hu.tassiviktor.multiserver.exceptions.ListenerInitializationException;
import hu.tassiviktor.multiserver.interfaces.ListenerInterface;
import hu.tassiviktor.multiserver.interfaces.ProtocolHandlerInterface;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ServerSocketFactory;

public class Listener implements ListenerInterface {

    protected ServerSocketFactory serverSocketFactory;
    protected Class<? extends ProtocolHandlerInterface> protocolHandlerClass;
    protected boolean isRunning;
    protected int port;
    
    protected ListenerWorker worker;
    
    private ServerSocket serverSocket;

    @Override
    public void addServerSocketFactory(ServerSocketFactory f) {
        serverSocketFactory = f;
    }

    @Override
    public void initializeListener() throws ListenerInitializationException {
        if (serverSocketFactory == null) {
            serverSocketFactory = ServerSocketFactory.getDefault();
        }
        try {
            serverSocket = serverSocketFactory.createServerSocket(port);
        } catch (IOException ex) {
            throw new ListenerInitializationException(ex.getMessage(), ex);
        }
        worker = new ListenerWorker(serverSocket,protocolHandlerClass);
    }

    @Override
    public void start() {
        isRunning = true;
        Thread t1 = new Thread(worker, this.toString());
        t1.start();
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public void stop() {
        isRunning = false;
    }

    @Override
    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public void setProtocolHandler(Class<? extends ProtocolHandlerInterface> c) {
        this.protocolHandlerClass = c;
    }

}
