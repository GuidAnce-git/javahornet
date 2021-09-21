package p2p.githubtest.message.gossip;

import javax.enterprise.context.ApplicationScoped;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class GossipPayloadBA {

    private static final byte MESSAGE_ID_BYTES_LENGTH = 8;
    private static final byte PARRENTS_ID_BYTES_LENGTH = 32;
    private static final byte PARRENTS_BYTE_POSITION = 0;
    private static final byte PAYLOAD_LENGTH_BYTES_LENGTH = 4;
    private static final byte MESSAGE_TYPE_BYTE_POSITION = 0;
    private static final byte MESSAGE_TYPE_BYTES_LENGTH = 4;


    public void extractMessageId(GossipPayloadDTO gossipPayloadDTO) {
        if (!gossipPayloadDTO.isNetworkIdSet()
                && !gossipPayloadDTO.isParentsSet()
                && !gossipPayloadDTO.isPayloadLengthSet()
                && !gossipPayloadDTO.isMessageTypeSet()
                && !gossipPayloadDTO.isPayloadSet()
                && !gossipPayloadDTO.isNonceSet()) {
            ByteBuffer byteBuffer = ByteBuffer.wrap(Arrays.copyOfRange(gossipPayloadDTO.getData(), 0, MESSAGE_ID_BYTES_LENGTH));
            byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
            gossipPayloadDTO.setNetworkId(byteBuffer.getLong());
            gossipPayloadDTO.setData(Arrays.copyOfRange(gossipPayloadDTO.getData(), MESSAGE_ID_BYTES_LENGTH, gossipPayloadDTO.getData().length));
            gossipPayloadDTO.setNetworkIdSet(true);
        }
    }

    public void extractParents(GossipPayloadDTO gossipPayloadDTO) {
        if (gossipPayloadDTO.isNetworkIdSet()
                && !gossipPayloadDTO.isParentsSet()
                && !gossipPayloadDTO.isPayloadLengthSet()
                && !gossipPayloadDTO.isMessageTypeSet()
                && !gossipPayloadDTO.isPayloadSet()
                && !gossipPayloadDTO.isNonceSet()) {

            List<byte[]> parents = new ArrayList<>();
            int parentsCount = gossipPayloadDTO.getData()[PARRENTS_BYTE_POSITION];
            gossipPayloadDTO.setData(Arrays.copyOfRange(gossipPayloadDTO.getData(), 1, gossipPayloadDTO.getData().length));

            for (int i = 0; i < parentsCount; i++) {
                parents.add(Arrays.copyOfRange(gossipPayloadDTO.getData(), (PARRENTS_ID_BYTES_LENGTH * i), (PARRENTS_ID_BYTES_LENGTH * i) + PARRENTS_ID_BYTES_LENGTH));
            }

            gossipPayloadDTO.setParents(parents);
            gossipPayloadDTO.setData(Arrays.copyOfRange(gossipPayloadDTO.getData(), PARRENTS_ID_BYTES_LENGTH * parentsCount, gossipPayloadDTO.getData().length));
            gossipPayloadDTO.setParentsSet(true);
        }
    }

    public void extractPayloadLength(GossipPayloadDTO gossipPayloadDTO) {
        if (gossipPayloadDTO.isNetworkIdSet()
                && gossipPayloadDTO.isParentsSet()
                && !gossipPayloadDTO.isPayloadLengthSet()
                && !gossipPayloadDTO.isMessageTypeSet()
                && !gossipPayloadDTO.isPayloadSet()
                && !gossipPayloadDTO.isNonceSet()) {

            int length = gossipPayloadDTO.getData()[0] & 0xff;
            length = length + ((gossipPayloadDTO.getData()[1] & 0xff) << 8);
            length = length + ((gossipPayloadDTO.getData()[2] & 0xff) << 16);
            length = length + ((gossipPayloadDTO.getData()[3] & 0xff) << 24);
            gossipPayloadDTO.setPayloadLength(length);
            gossipPayloadDTO.setData(Arrays.copyOfRange(gossipPayloadDTO.getData(), PAYLOAD_LENGTH_BYTES_LENGTH, gossipPayloadDTO.getData().length));
            gossipPayloadDTO.setPayloadLengthSet(true);
        }
    }

    public void extractMessageType(GossipPayloadDTO gossipPayloadDTO) {
        if (gossipPayloadDTO.isNetworkIdSet()
                && gossipPayloadDTO.isParentsSet()
                && gossipPayloadDTO.isPayloadLengthSet()
                && !gossipPayloadDTO.isMessageTypeSet()
                && !gossipPayloadDTO.isPayloadSet()
                && !gossipPayloadDTO.isNonceSet()) {

            gossipPayloadDTO.setMessageType(gossipPayloadDTO.getData()[MESSAGE_TYPE_BYTE_POSITION]);
            gossipPayloadDTO.setData(Arrays.copyOfRange(gossipPayloadDTO.getData(), MESSAGE_TYPE_BYTES_LENGTH, gossipPayloadDTO.getData().length));
            gossipPayloadDTO.setMessageTypeSet(true);
        }
    }

    public void extractPayload(GossipPayloadDTO gossipPayloadDTO) {
        if (gossipPayloadDTO.isNetworkIdSet()
                && gossipPayloadDTO.isParentsSet()
                && gossipPayloadDTO.isPayloadLengthSet()
                && gossipPayloadDTO.isMessageTypeSet()
                && !gossipPayloadDTO.isPayloadSet()
                && !gossipPayloadDTO.isNonceSet()) {


            gossipPayloadDTO.setMessageType(gossipPayloadDTO.getData()[MESSAGE_TYPE_BYTE_POSITION]);
            gossipPayloadDTO.setData(Arrays.copyOfRange(gossipPayloadDTO.getData(), MESSAGE_TYPE_BYTES_LENGTH, gossipPayloadDTO.getData().length));
            gossipPayloadDTO.setPayloadSet(true);
        }
    }


}
