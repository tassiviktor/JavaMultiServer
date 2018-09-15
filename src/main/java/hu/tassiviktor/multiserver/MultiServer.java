package hu.tassiviktor.multiserver;

import hu.tassiviktor.multiserver.Exceptions.ListenerExistsException;
import hu.tassiviktor.multiserver.Exceptions.ListenerInitializationException;
import hu.tassiviktor.multiserver.Exceptions.ListenerNotExistsException;
import hu.tassiviktor.multiserver._drop.Configuration;
import hu.tassiviktor.multiserver.Interfaces.ServerInterface;
import java.util.HashMap;
import java.util.Map;
import hu.tassiviktor.multiserver.Interfaces.ListenerInterface;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MultiServer implements ServerInterface {

    Map<String, ListenerInterface> listeners = new HashMap<>();

    public void addListener(String listenerName, ListenerInterface listener) throws ListenerExistsException {
        if (listeners.containsKey(listenerName) && listeners.get(listenerName) != null) {
            throw new ListenerExistsException("Listener: " + listenerName + " already exists.");
        }
        listeners.put(listenerName, listener);
    }

    public void removeListener(String listenerName) {
        if (!hasListener(listenerName)) {
            return;
        }

        releaseListener(listenerName);

        listeners.remove(listenerName);
    }

    public void replaceListener(String listenerName, ListenerInterface listener) throws ListenerExistsException {
        releaseListener(listenerName);
        listeners.replace(listenerName, listener);
    }

    public void initializeListener(String listenerName) throws ListenerNotExistsException, ListenerInitializationException {
        if (!hasListener(listenerName)) {
            throw new ListenerNotExistsException("The requested listener " + listenerName + " does not exists");
        }

        ListenerInterface listener = listeners.get(listenerName);
        listener.initializeListener();
    }

    public void startListener(String listenerName) throws ListenerNotExistsException {
        if (!hasListener(listenerName)) {
            throw new ListenerNotExistsException("The requested listener " + listenerName + " does not exists");
        }
        ListenerInterface listener = listeners.get(listenerName);
        listener.start();
    }

    public void stopListener(String listenerName) throws ListenerNotExistsException {
        if (!hasListener(listenerName)) {
            throw new ListenerNotExistsException("The requested listener " + listenerName + " does not exists");
        }
        ListenerInterface listener = listeners.get(listenerName);
        listener.stop();
    }

    public void startListeners() {
        for (Map.Entry pair : listeners.entrySet()) {
            ListenerInterface listener = (ListenerInterface) pair.getValue();
            if (!listener.isRunning()) {
                listener.start();
            }
        }
    }

    public void stopListeners() {
        for (Map.Entry pair : listeners.entrySet()) {
            ListenerInterface listener = (ListenerInterface) pair.getValue();
            if (listener.isRunning()) {
                listener.stop();
            }
        }
    }

    protected void releaseListener(String listenerName) {
        if (!hasListener(listenerName)) {
            return;
        }

        ListenerInterface listener = listeners.get(listenerName);

        if (listener.isRunning()) {
            listener.stop();
        }

        //Release Object?
        listeners.replace(listenerName, null);
    }

    protected boolean hasListener(String listenerName) {
        return listeners.containsKey(listenerName);
    }

}
