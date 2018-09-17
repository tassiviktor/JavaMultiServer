
import hu.tassiviktor.multiserver.exceptions.ListenerExistsException;
import hu.tassiviktor.multiserver.exceptions.ListenerInitializationException;
import hu.tassiviktor.multiserver.exceptions.ListenerNotExistsException;
import hu.tassiviktor.multiserver.Listener;
import hu.tassiviktor.multiserver.MultiServer;
import hu.tassiviktor.multiserver.ServerSocketFactory;
import hu.tassiviktor.multiserver.exceptions.ServerSocketFactoryException;
import hu.tassiviktor.multiserver.protocolhandlers.httphandler.HttpHandler;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
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
     * @throws hu.tassiviktor.multiserver.exceptions.ServerSocketFactoryException
     */
    public static void main(String[] args) throws ServerSocketFactoryException  {

        MultiServer s = new MultiServer();
        ServerSocket serverSocket = ServerSocketFactory.createServerSocket(4444);
        ServerSocket sercureServerSocket = ServerSocketFactory.createSslServerSocket(8888,"cacert.p12","");
        
        Listener plainServerListener = new Listener();
        plainServerListener.assignServerSocket(serverSocket);
        plainServerListener.setProtocolHandler(HttpHandler.class);
        
        Listener secureServerListener = new Listener();
        secureServerListener.assignServerSocket(sercureServerSocket);
        secureServerListener.setProtocolHandler(HttpHandler.class);
        
        try {
            s.addListener("DefaultWebServer", plainServerListener);
            s.initializeListener("DefaultWebServer");
            s.addListener("DefaultSecureWebServer", secureServerListener);
            s.initializeListener("DefaultSecureWebServer");
            
            s.startListeners();
            System.out.println("All listeners started");
        } catch (Exception ex) {
            Logger.getLogger(StandaloneServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Main thread down.");
    }

   
    

}
