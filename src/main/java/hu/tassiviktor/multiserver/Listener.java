package hu.tassiviktor.multiserver;

import hu.tassiviktor.multiserver.exceptions.ListenerInitializationException;
import hu.tassiviktor.multiserver.interfaces.ListenerInterface;
import hu.tassiviktor.multiserver.interfaces.ProtocolHandlerInterface;
import java.net.ServerSocket;

public class Listener implements ListenerInterface {

    protected Class<? extends ProtocolHandlerInterface> protocolHandlerClass;
    protected boolean isRunning;
    protected int port;
    
    protected ListenerWorker worker;
    
    private ServerSocket serverSocket;

    @Override
    public void assignServerSocket(ServerSocket s){
        serverSocket = s;
    }

    @Override
    public void initializeListener() throws ListenerInitializationException {
        worker = new ListenerWorker(serverSocket,protocolHandlerClass);
        worker.initQueue();
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
    public void setProtocolHandler(Class<? extends ProtocolHandlerInterface> c) {
        this.protocolHandlerClass = c;
    }

    @Override
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

}
