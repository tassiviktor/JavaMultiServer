package hu.tassiviktor.multiserver;

import hu.tassiviktor.multiserver.Exceptions.ListenerInitializationException;
import hu.tassiviktor.multiserver.Interfaces.ProtocolHandlerInterface;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListenerWorker implements Runnable {

    protected ServerSocket serverSocket;
    protected Class<? extends ProtocolHandlerInterface> handlerClass;
    protected ProtocolHandlerInterface protocolHandler;

    protected volatile boolean isRunning = false;

    protected int timeoutWaitingQueueNotFullInSec = 90;
    protected int maxNumOfQueueItems = 10;

    ArrayBlockingQueue<ProtocolHandlerInterface> myQueue;

    public ListenerWorker(ServerSocket servSock, Class<? extends ProtocolHandlerInterface> hC) throws ListenerInitializationException {
        serverSocket = servSock;
        handlerClass = hC;
        if (serverSocket == null) {
            throw new ListenerInitializationException("Bad initializaton parameters (nullSocket)");
        }
    }

    public void initQueue() {
        myQueue = new ArrayBlockingQueue<>(maxNumOfQueueItems);
    }

    @Override
    public void run() {

        isRunning = true;
        while (isRunning) {
            Socket incomingConnection;
            try {
                incomingConnection = serverSocket.accept();

                try {
                    ProtocolHandlerInterface h;
                    h = handlerClass.newInstance();
                    h.setSocket(incomingConnection);
                    if (addHandlerToQueue(h)) {
                        h.run();
                    }
                } catch (InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(ListenerWorker.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void stop() {
        isRunning = false;
    }

    protected boolean addHandlerToQueue(ProtocolHandlerInterface h) {
        try {
            if (!myQueue.offer(h, timeoutWaitingQueueNotFullInSec, TimeUnit.SECONDS)) {
                Logger.getLogger(ListenerWorker.class.getName()).log(Level.SEVERE, "Queue full with timeout");
                destroyHandler(h);
                return false;
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ListenerWorker.class.getName()).log(Level.SEVERE, null, ex);
            destroyHandler(h);
            return false;
        }

        //if long queue wait, socket may interrupted meanwhile
        if (!h.getSocket().isClosed()) {
            destroyHandler(h);
            return false;
        }
        return true;
    }

    // For GC
    protected void destroyHandler(ProtocolHandlerInterface h) {
        if (!h.getSocket().isClosed()) {
            try {
                h.getSocket().close();
            } catch (IOException ex) {

            }
            h.setSocket(null);
            h = null;
        }
    }
}
