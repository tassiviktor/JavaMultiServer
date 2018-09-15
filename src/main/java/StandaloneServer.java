
import hu.tassiviktor.multiserver.Exceptions.ListenerExistsException;
import hu.tassiviktor.multiserver.Exceptions.ListenerInitializationException;
import hu.tassiviktor.multiserver.Exceptions.ListenerNotExistsException;
import hu.tassiviktor.multiserver.Listener;
import hu.tassiviktor.multiserver.MultiServer;
import hu.tassiviktor.multiserver.ProtocolHandlers.HttpHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.*;

/**
 *
 * @author Viktor Tassi
 */
public class StandaloneServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        MultiServer s = new MultiServer();
        Listener h = new Listener();
        h.setPort(8888);
        h.addServerSocketFactory(SSLServerSocketFactory.getDefault());
        h.setProtocolHandler(HttpHandler.class);
        try {
            s.addListener("DefaultWebServer", h);
            s.initializeListener("DefaultWebServer");
            s.startListeners();
            System.out.println("All listeners started");
        } catch (Exception ex) {
            Logger.getLogger(StandaloneServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Main thread down.");
    }
    
}
