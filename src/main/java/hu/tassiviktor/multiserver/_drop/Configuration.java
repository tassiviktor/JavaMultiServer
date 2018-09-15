
package hu.tassiviktor.multiserver._drop;


public class Configuration {
    protected Integer httpPort = 8888;
    protected String resourceRoot;
    protected String folderRoot;
    protected String bindAddress = "0.0.0.0";
    protected String hostName = "localhost.localdomain";
    protected Integer maxThreadsNumSoftLimit = 10;
    protected Integer maxThreadsNumHardLimit = 20;
    protected Integer assignSocketToThreadTimeoutInSec = 30;
    
    // Internals from here
    private final Integer socketFirsReadTimeOutInMs = 100;
    private final Integer socketFirsReadSleepInterval = 10;

    public Integer getHttpPort() {
        return httpPort;
    }

    public void setHttpPort(Integer httpPort) {
        this.httpPort = httpPort;
    }

    public String getResourceRoot() {
        return resourceRoot;
    }

    public void setResourceRoot(String resourceRoot) {
        this.resourceRoot = resourceRoot;
    }

    public String getFolderRoot() {
        return folderRoot;
    }

    public void setFolderRoot(String folderRoot) {
        this.folderRoot = folderRoot;
    }

    public Integer getMaxThreadsNumSoftLimit() {
        return maxThreadsNumSoftLimit;
    }

    public void setMaxThreadsNumSoftLimit(Integer maxThreadsNumSoftLimit) {
        this.maxThreadsNumSoftLimit = maxThreadsNumSoftLimit;
    }

    public Integer getMaxThreadsNumHardLimit() {
        return maxThreadsNumHardLimit;
    }

    public void setMaxThreadsNumHardLimit(Integer maxThreadsNumHardLimit) {
        this.maxThreadsNumHardLimit = maxThreadsNumHardLimit;
    }

    public Integer getAssignSocketToThreadTimeoutInSec() {
        return assignSocketToThreadTimeoutInSec;
    }

    public void setAssignSocketToThreadTimeoutInSec(Integer assignSocketToThreadTimeoutInSec) {
        this.assignSocketToThreadTimeoutInSec = assignSocketToThreadTimeoutInSec;
    }
    
}
