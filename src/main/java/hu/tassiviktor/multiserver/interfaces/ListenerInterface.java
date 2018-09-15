package hu.tassiviktor.multiserver.interfaces;

import hu.tassiviktor.multiserver.exceptions.ListenerInitializationException;
import javax.net.ServerSocketFactory;

/**
 *
 * @author Viktor Tassi
 */
public interface ListenerInterface {
    public void addServerSocketFactory(ServerSocketFactory f);
    public void setProtocolHandler(Class<? extends ProtocolHandlerInterface> c);
    public void initializeListener() throws ListenerInitializationException;
    public void start();
    public boolean isRunning();
    public void stop();
    public void setPort(int port);
    public int getPort();
}
