package configuration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Autopeering {
    public String bindAddress;
    public Db db;
    public List<String> entryNodes;
    public boolean entryNodesPreferIPv6;
    public boolean runAsEntryNode;

    public String getBindAddress() {
        return bindAddress;
    }

    public void setBindAddress(String bindAddress) {
        this.bindAddress = bindAddress;
    }

    public Db getDb() {
        return db;
    }

    public void setDb(Db db) {
        this.db = db;
    }

    public List<String> getEntryNodes() {
        return entryNodes;
    }

    public void setEntryNodes(List<String> entryNodes) {
        this.entryNodes = entryNodes;
    }

    public boolean isEntryNodesPreferIPv6() {
        return entryNodesPreferIPv6;
    }

    public void setEntryNodesPreferIPv6(boolean entryNodesPreferIPv6) {
        this.entryNodesPreferIPv6 = entryNodesPreferIPv6;
    }

    public boolean isRunAsEntryNode() {
        return runAsEntryNode;
    }

    public void setRunAsEntryNode(boolean runAsEntryNode) {
        this.runAsEntryNode = runAsEntryNode;
    }
}
