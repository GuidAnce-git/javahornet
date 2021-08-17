package configuration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Prometheus {
    public String bindAddress;
    public FileServiceDiscovery fileServiceDiscovery;
    public boolean databaseMetrics;
    public boolean nodeMetrics;
    public boolean gossipMetrics;
    public boolean cachesMetrics;
    public boolean restAPIMetrics;
    public boolean migrationMetrics;
    public boolean coordinatorMetrics;
    public boolean debugMetrics;
    public boolean goMetrics;
    public boolean processMetrics;
    public boolean promhttpMetrics;

    public String getBindAddress() {
        return bindAddress;
    }

    public void setBindAddress(String bindAddress) {
        this.bindAddress = bindAddress;
    }

    public FileServiceDiscovery getFileServiceDiscovery() {
        return fileServiceDiscovery;
    }

    public void setFileServiceDiscovery(FileServiceDiscovery fileServiceDiscovery) {
        this.fileServiceDiscovery = fileServiceDiscovery;
    }

    public boolean isDatabaseMetrics() {
        return databaseMetrics;
    }

    public void setDatabaseMetrics(boolean databaseMetrics) {
        this.databaseMetrics = databaseMetrics;
    }

    public boolean isNodeMetrics() {
        return nodeMetrics;
    }

    public void setNodeMetrics(boolean nodeMetrics) {
        this.nodeMetrics = nodeMetrics;
    }

    public boolean isGossipMetrics() {
        return gossipMetrics;
    }

    public void setGossipMetrics(boolean gossipMetrics) {
        this.gossipMetrics = gossipMetrics;
    }

    public boolean isCachesMetrics() {
        return cachesMetrics;
    }

    public void setCachesMetrics(boolean cachesMetrics) {
        this.cachesMetrics = cachesMetrics;
    }

    public boolean isRestAPIMetrics() {
        return restAPIMetrics;
    }

    public void setRestAPIMetrics(boolean restAPIMetrics) {
        this.restAPIMetrics = restAPIMetrics;
    }

    public boolean isMigrationMetrics() {
        return migrationMetrics;
    }

    public void setMigrationMetrics(boolean migrationMetrics) {
        this.migrationMetrics = migrationMetrics;
    }

    public boolean isCoordinatorMetrics() {
        return coordinatorMetrics;
    }

    public void setCoordinatorMetrics(boolean coordinatorMetrics) {
        this.coordinatorMetrics = coordinatorMetrics;
    }

    public boolean isDebugMetrics() {
        return debugMetrics;
    }

    public void setDebugMetrics(boolean debugMetrics) {
        this.debugMetrics = debugMetrics;
    }

    public boolean isGoMetrics() {
        return goMetrics;
    }

    public void setGoMetrics(boolean goMetrics) {
        this.goMetrics = goMetrics;
    }

    public boolean isProcessMetrics() {
        return processMetrics;
    }

    public void setProcessMetrics(boolean processMetrics) {
        this.processMetrics = processMetrics;
    }

    public boolean isPromhttpMetrics() {
        return promhttpMetrics;
    }

    public void setPromhttpMetrics(boolean promhttpMetrics) {
        this.promhttpMetrics = promhttpMetrics;
    }
}
