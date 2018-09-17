package hu.tassiviktor.multiserver.interfaces;

import hu.tassiviktor.multiserver.exceptions.ListenerInitializationException;
import java.net.ServerSocket;

/**
 *
 * @author Viktor Tassi
 */
public interface ListenerInterface {
    public void assignServerSocket(ServerSocket s);
    public void setProtocolHandler(Class<? extends ProtocolHandlerInterface> c);
    public void initializeListener() throws ListenerInitializationException;
    public void start();
    public boolean isRunning();
    public void stop();
    public ServerSocket getServerSocket();
}
