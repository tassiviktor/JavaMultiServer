package hu.tassiviktor.multiserver;

import hu.tassiviktor.multiserver.exceptions.ServerSocketFactoryException;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

/**
 *
 * @author tassiviktor
 */
public class ServerSocketFactory {
    
    public static ServerSocket createServerSocket(int port) throws ServerSocketFactoryException{
        try {
            return new ServerSocket(port);
        } catch (IOException ex) {
            throw new ServerSocketFactoryException("Cannot create serverSocket", ex);
        }
    }
    
    public static ServerSocket createSslServerSocket(int port, String pcks12KeyFile, String password) throws ServerSocketFactoryException {
        SSLServerSocket serverSocket = null;
        try {
            serverSocket = (SSLServerSocket) createSSLContextFromFile(pcks12KeyFile, password).getServerSocketFactory().createServerSocket(port);
            serverSocket.setEnabledCipherSuites(serverSocket.getSupportedCipherSuites());
            return serverSocket;
        } catch (IOException ex) {
            throw new ServerSocketFactoryException("Cannot create serverSocket", ex);
        }

    }
    
    // Create the and initialize the SSLContext
    private static SSLContext createSSLContextFromFile(String fileName, String password) throws ServerSocketFactoryException {
        try {
           KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(new FileInputStream(fileName), "".toCharArray());

            // Create key manager
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
            keyManagerFactory.init(keyStore, password.toCharArray());
            KeyManager[] km = keyManagerFactory.getKeyManagers();

            // Create trust manager
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
            trustManagerFactory.init(keyStore);
            TrustManager[] tm = trustManagerFactory.getTrustManagers();

            // Initialize SSLContext
            SSLContext sslContext = SSLContext.getInstance("TLSv1.1");
            sslContext.init(km, tm, null);

            return sslContext;
        } catch (IOException | KeyManagementException | KeyStoreException | NoSuchAlgorithmException | CertificateException | UnrecoverableKeyException ex) {
            throw new ServerSocketFactoryException("Cannot create SSL context", ex);
        }

    }
}
