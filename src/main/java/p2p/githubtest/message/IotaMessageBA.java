package p2p.githubtest.message;

public class IotaMessageBA {
    IotaMessageDTO MessageFromBytes(byte[] data) {

        IotaMessageDTO iotaMsg = new IotaMessageDTO();
        GossipMessageBA gossipMessageBA = new GossipMessageBA();
        iotaMsg = gossipMessageBA.deserialize(data);


        return iotaMsg;
    }
}
