package configuration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Gossip {
    public int unknownPeersLimit;
    public String streamReadTimeout;
    public String streamWriteTimeout;

    public int getUnknownPeersLimit() {
        return unknownPeersLimit;
    }

    public void setUnknownPeersLimit(int unknownPeersLimit) {
        this.unknownPeersLimit = unknownPeersLimit;
    }

    public String getStreamReadTimeout() {
        return streamReadTimeout;
    }

    public void setStreamReadTimeout(String streamReadTimeout) {
        this.streamReadTimeout = streamReadTimeout;
    }

    public String getStreamWriteTimeout() {
        return streamWriteTimeout;
    }

    public void setStreamWriteTimeout(String streamWriteTimeout) {
        this.streamWriteTimeout = streamWriteTimeout;
    }
}
