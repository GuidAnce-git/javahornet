package p2p.githubtest.message.gossip;

import com.rfksystems.blake2b.Blake2b;
import com.rfksystems.blake2b.security.Blake2bProvider;

import javax.enterprise.context.ApplicationScoped;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Arrays;

@ApplicationScoped
public class GossipPayloadBA {

    private static final byte MESSAGE_ID_BYTES_LENGTH = 8;
    private static final byte PARRENTS_ID_BYTES_LENGTH = 32;
    private static final byte PARRENTS_BYTE_POSITION = 0;
    private static final byte PAYLOAD_LENGTH_BYTES_LENGTH = 4;
    private static final byte MESSAGE_TYPE_BYTE_POSITION = 0;
    private static final byte MESSAGE_TYPE_BYTES_LENGTH = 4;


    public void deserialize(GossipPayloadDTO gossipPayloadDTO) {
        generateMessageHash(gossipPayloadDTO);
        extractMessageId(gossipPayloadDTO);
        extractParents(gossipPayloadDTO);
        extractPayloadLength(gossipPayloadDTO);
        extractMessageType(gossipPayloadDTO);
    }


    void generateMessageHash(GossipPayloadDTO gossipPayloadDTO) {
        Security.addProvider(new Blake2bProvider());
        final MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(Blake2b.BLAKE2_B_256);
            digest.update(gossipPayloadDTO.getData());
            gossipPayloadDTO.setMessageHash(digest.digest());
            gossipPayloadDTO.setMessageId(gossipPayloadDTO.getMessageHash());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    private void extractMessageId(GossipPayloadDTO gossipPayloadDTO) {
        gossipPayloadDTO.setNetworkId(GossipHelperBA.extractBytesToLong(gossipPayloadDTO.getData(), MESSAGE_ID_BYTES_LENGTH));
        gossipPayloadDTO.setData(Arrays.copyOfRange(gossipPayloadDTO.getData(), MESSAGE_ID_BYTES_LENGTH, gossipPayloadDTO.getData().length));
    }


    private void extractParents(GossipPayloadDTO gossipPayloadDTO) {
        gossipPayloadDTO.setParents(GossipHelperBA.extractBytesToArray(gossipPayloadDTO.getData(), PARRENTS_BYTE_POSITION, PARRENTS_ID_BYTES_LENGTH));
        gossipPayloadDTO.setData(Arrays.copyOfRange(gossipPayloadDTO.getData(), PARRENTS_ID_BYTES_LENGTH * gossipPayloadDTO.getParents().size() + 1, gossipPayloadDTO.getData().length));
    }


    private void extractPayloadLength(GossipPayloadDTO gossipPayloadDTO) {
        int length = gossipPayloadDTO.getData()[0] & 0xff;
        length = length + ((gossipPayloadDTO.getData()[1] & 0xff) << 8);
        length = length + ((gossipPayloadDTO.getData()[2] & 0xff) << 16);
        length = length + ((gossipPayloadDTO.getData()[3] & 0xff) << 24);
        gossipPayloadDTO.setPayloadLength(length);
        gossipPayloadDTO.setData(Arrays.copyOfRange(gossipPayloadDTO.getData(), PAYLOAD_LENGTH_BYTES_LENGTH, gossipPayloadDTO.getData().length));
    }


    private void extractMessageType(GossipPayloadDTO gossipPayloadDTO) {
        gossipPayloadDTO.setMessageType(gossipPayloadDTO.getData()[MESSAGE_TYPE_BYTE_POSITION]);
        gossipPayloadDTO.setData(Arrays.copyOfRange(gossipPayloadDTO.getData(), MESSAGE_TYPE_BYTES_LENGTH, gossipPayloadDTO.getData().length));
    }

    
    public void extractNonce(GossipPayloadDTO gossipPayloadDTO) {
        gossipPayloadDTO.setNonce(GossipHelperBA.extractBytesToLong(gossipPayloadDTO.getData(), gossipPayloadDTO.getData().length));
    }

}
