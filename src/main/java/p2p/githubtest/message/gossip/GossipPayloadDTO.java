package p2p.githubtest.message.gossip;

import com.google.common.io.BaseEncoding;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GossipPayloadDTO {
    private byte[] data;
    private long networkId;
    private List<byte[]> parents;
    private int payloadLength;
    private int messageType;
    private byte[] payload;
    private long nonce;
    private byte[] messageHash;
    private byte[] messageId;

    public String getDataAsHex() {
        return BaseEncoding.base16().encode(data);
    }

    public byte[] getData() {
        if (data == null) {
            return new byte[0];
        }
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public long getNetworkId() {
        return networkId;
    }

    public void setNetworkId(long networkId) {
        this.networkId = networkId;
    }

    public List<byte[]> getParents() {
        if (parents == null) {
            return new ArrayList<>();
        }
        return parents;
    }

    public void setParents(List<byte[]> parents) {
        this.parents = parents;
    }

    public int getPayloadLength() {
        return payloadLength;
    }

    public void setPayloadLength(int payloadLength) {
        this.payloadLength = payloadLength;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public byte[] getPayload() {
        return payload;
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
    }

    public long getNonce() {
        return nonce;
    }

    public void setNonce(long nonce) {
        this.nonce = nonce;
    }

    public byte[] getMessageHash() {
        if (messageHash == null) {
            return new byte[0];
        }
        return messageHash;
    }

    public void setMessageHash(byte[] messageHash) {
        this.messageHash = messageHash;
    }

    public byte[] getMessageId() {
        if (messageId == null) {
            return new byte[0];
        }
        return messageId;
    }

    public void setMessageId(byte[] messageId) {
        this.messageId = messageId;
    }
}
