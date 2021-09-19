package p2p.githubtest.message;

public class GossipMessageDTO {
    private byte[] messageId;
    private byte[] data;
    private IotaMessageDTO iotaMessageDTO;

    public byte[] getMessageId() {
        return messageId;
    }

    public void setMessageId(byte[] messageId) {
        this.messageId = messageId;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public IotaMessageDTO getIotaMessageDTO() {
        return iotaMessageDTO;
    }

    public void setIotaMessageDTO(IotaMessageDTO iotaMessageDTO) {
        this.iotaMessageDTO = iotaMessageDTO;
    }
}
