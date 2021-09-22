package p2p.githubtest.message.iota;

import p2p.githubtest.message.gossip.GossipHelperBA;

import javax.enterprise.context.ApplicationScoped;
import java.util.Arrays;

@ApplicationScoped
public class IotaMessageMilestoneBA {
    private static final byte INDEX_BYTES_LENGTH = 4;
    private static final byte TIMESTAMP_BYTES_LENGTH = 8;
    private static final byte PARRENTS_ID_BYTES_LENGTH = 32;
    private static final byte PARRENTS_BYTE_POSITION = 0;
    private static final byte INCLUSION_MERKLE_PROOF_BYTE_LENGTH = 32;
    private static final byte NEXT_POW_SCORE_BYTES_LENGTH = 4;
    private static final byte NEXT_POW_SCORE_MILESTONE_INDEX_BYTES_LENGTH = 4;
    private static final byte PUBLIC_KEYS_BYTE_POSITION = 0;
    private static final byte PUBLIC_KEYS_BYTES_LENGTH = 32;
    private static final byte RECEIPT_BYTES_LENGTH = 4;
    private static final byte SIGNATURES_BYTE_POSITION = 0;
    private static final byte SIGNATURES_BYTES_LENGTH = 64;


    public void deserialize(IotaMessageMilestoneDTO iotaMessageMilestoneDTO) {
        extractIndex(iotaMessageMilestoneDTO);
        extractTimestamp(iotaMessageMilestoneDTO);
        extractParents(iotaMessageMilestoneDTO);
        extractInclusionMerkleProof(iotaMessageMilestoneDTO);
        extractNextPoWScore(iotaMessageMilestoneDTO);
        extractNextPoWScoreMilestoneIndex(iotaMessageMilestoneDTO);
        extractPublicKeys(iotaMessageMilestoneDTO);
        extractReceipt(iotaMessageMilestoneDTO);
        extractSignatures(iotaMessageMilestoneDTO);
    }


    private void extractIndex(IotaMessageMilestoneDTO iotaMessageMilestoneDTO) {
        iotaMessageMilestoneDTO.setIndex(GossipHelperBA.extractBytesToInt(iotaMessageMilestoneDTO.getData(), INDEX_BYTES_LENGTH));
        iotaMessageMilestoneDTO.setData(Arrays.copyOfRange(iotaMessageMilestoneDTO.getData(), INDEX_BYTES_LENGTH, iotaMessageMilestoneDTO.getData().length));
    }


    private void extractTimestamp(IotaMessageMilestoneDTO iotaMessageMilestoneDTO) {
        iotaMessageMilestoneDTO.setTimestamp(GossipHelperBA.extractBytesToLong(iotaMessageMilestoneDTO.getData(), TIMESTAMP_BYTES_LENGTH));
        iotaMessageMilestoneDTO.setData(Arrays.copyOfRange(iotaMessageMilestoneDTO.getData(), TIMESTAMP_BYTES_LENGTH, iotaMessageMilestoneDTO.getData().length));
    }


    private void extractParents(IotaMessageMilestoneDTO iotaMessageMilestoneDTO) {
        iotaMessageMilestoneDTO.setParents(GossipHelperBA.extractBytesToArray(iotaMessageMilestoneDTO.getData(), PARRENTS_BYTE_POSITION, PARRENTS_ID_BYTES_LENGTH));
        iotaMessageMilestoneDTO.setData(Arrays.copyOfRange(iotaMessageMilestoneDTO.getData(), PARRENTS_ID_BYTES_LENGTH * iotaMessageMilestoneDTO.getParents().size() + 1, iotaMessageMilestoneDTO.getData().length));
    }


    private void extractInclusionMerkleProof(IotaMessageMilestoneDTO iotaMessageMilestoneDTO) {
        iotaMessageMilestoneDTO.setInclusionMerkleProof(Arrays.copyOfRange(iotaMessageMilestoneDTO.getData(), 0, INCLUSION_MERKLE_PROOF_BYTE_LENGTH));
        iotaMessageMilestoneDTO.setData(Arrays.copyOfRange(iotaMessageMilestoneDTO.getData(), INCLUSION_MERKLE_PROOF_BYTE_LENGTH, iotaMessageMilestoneDTO.getData().length));
    }


    private void extractNextPoWScore(IotaMessageMilestoneDTO iotaMessageMilestoneDTO) {
        iotaMessageMilestoneDTO.setNextPoWScore(GossipHelperBA.extractBytesToInt(iotaMessageMilestoneDTO.getData(), NEXT_POW_SCORE_BYTES_LENGTH));
        iotaMessageMilestoneDTO.setData(Arrays.copyOfRange(iotaMessageMilestoneDTO.getData(), NEXT_POW_SCORE_BYTES_LENGTH, iotaMessageMilestoneDTO.getData().length));
    }


    private void extractNextPoWScoreMilestoneIndex(IotaMessageMilestoneDTO iotaMessageMilestoneDTO) {
        iotaMessageMilestoneDTO.setNextPoWScoreMilestoneIndex(GossipHelperBA.extractBytesToInt(iotaMessageMilestoneDTO.getData(), NEXT_POW_SCORE_MILESTONE_INDEX_BYTES_LENGTH));
        iotaMessageMilestoneDTO.setData(Arrays.copyOfRange(iotaMessageMilestoneDTO.getData(), NEXT_POW_SCORE_MILESTONE_INDEX_BYTES_LENGTH, iotaMessageMilestoneDTO.getData().length));
    }


    private void extractPublicKeys(IotaMessageMilestoneDTO iotaMessageMilestoneDTO) {
        iotaMessageMilestoneDTO.setPublicKeys(GossipHelperBA.extractBytesToArray(iotaMessageMilestoneDTO.getData(), PUBLIC_KEYS_BYTE_POSITION, PUBLIC_KEYS_BYTES_LENGTH));
        iotaMessageMilestoneDTO.setData(Arrays.copyOfRange(iotaMessageMilestoneDTO.getData(), PUBLIC_KEYS_BYTES_LENGTH * iotaMessageMilestoneDTO.getPublicKeys().size() + 1, iotaMessageMilestoneDTO.getData().length));
    }


    private void extractReceipt(IotaMessageMilestoneDTO iotaMessageMilestoneDTO) {
        if (GossipHelperBA.extractBytesToInt(iotaMessageMilestoneDTO.getData(), RECEIPT_BYTES_LENGTH) == 0) {
            iotaMessageMilestoneDTO.setData(Arrays.copyOfRange(iotaMessageMilestoneDTO.getData(), RECEIPT_BYTES_LENGTH, iotaMessageMilestoneDTO.getData().length));
        }
    }


    private void extractSignatures(IotaMessageMilestoneDTO iotaMessageMilestoneDTO) {
        iotaMessageMilestoneDTO.setSignatures(GossipHelperBA.extractBytesToArray(iotaMessageMilestoneDTO.getData(), SIGNATURES_BYTE_POSITION, SIGNATURES_BYTES_LENGTH));
        iotaMessageMilestoneDTO.setData(Arrays.copyOfRange(iotaMessageMilestoneDTO.getData(), SIGNATURES_BYTES_LENGTH * iotaMessageMilestoneDTO.getSignatures().size() + 1, iotaMessageMilestoneDTO.getData().length));
    }
}
