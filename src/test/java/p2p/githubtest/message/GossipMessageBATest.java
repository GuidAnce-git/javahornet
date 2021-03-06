package p2p.githubtest.message;

import com.google.common.io.BaseEncoding;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import p2p.githubtest.message.gossip.GossipHelperBA;
import p2p.githubtest.message.gossip.GossipPayloadBA;
import p2p.githubtest.message.gossip.GossipPayloadDTO;
import p2p.githubtest.message.iota.IotaMessageMilestoneBA;
import p2p.githubtest.message.iota.IotaMessageMilestoneDTO;

import javax.inject.Inject;

@QuarkusTest
class GossipMessageBATest {

    @Inject
    GossipPayloadBA gossipPayloadBA;

    @Inject
    GossipPayloadDTO gossipPayloadDTO;

    @Inject
    IotaMessageMilestoneDTO iotaMessageMilestoneDTO;

    @Inject
    IotaMessageMilestoneBA iotaMessageMilestoneBA;

    private static final int TRANSACTION_PAYLOAD_TYPE_ID = 0;
    private static final int MILESTONE_PAYLOAD_TYPE_ID = 1;
    private static final int INDEXATION_PAYLOAD_TYPE_ID = 2;
    private static final int RECEIPT_PAYLOAD_TYPE_ID = 3;
    private static final int TREASURY_PAYLOAD_TYPE_ID = 4;

    //test data
    int[] dataAsIntArray = {183, 127, 68, 113, 94, 11, 48, 20, 6, 121, 9, 219, 250, 144, 202, 32, 235, 216, 186, 209, 66, 115, 84, 211, 81, 19, 88, 213, 179, 28, 80, 93, 39, 38, 37, 217, 35, 107, 88, 76, 225, 140, 83, 40, 115, 73, 105, 199, 157, 69, 112, 212, 96, 92, 222, 89, 208, 189, 125, 182, 120, 253, 196, 44, 47, 79, 95, 189, 143, 111, 46, 142, 116, 176, 11, 160, 78, 184, 94, 108, 44, 48, 13, 248, 124, 116, 93, 196, 36, 55, 231, 109, 244, 121, 151, 119, 206, 3, 25, 182, 227, 159, 36, 190, 225, 184, 226, 244, 89, 235, 153, 120, 142, 107, 16, 186, 80, 10, 250, 149, 3, 26, 31, 69, 143, 243, 195, 38, 4, 193, 117, 244, 75, 133, 185, 127, 56, 190, 84, 62, 23, 151, 10, 137, 19, 101, 198, 111, 254, 95, 9, 113, 196, 171, 86, 245, 230, 234, 7, 151, 136, 118, 169, 135, 206, 47, 206, 216, 174, 234, 154, 249, 245, 245, 78, 109, 200, 22, 225, 154, 134, 89, 33, 62, 48, 236, 190, 65, 137, 78, 32, 41, 131, 34, 10, 76, 195, 139, 239, 13, 165, 191, 1, 0, 0, 1, 0, 0, 0, 191, 228, 18, 0, 184, 2, 71, 97, 0, 0, 0, 0, 6, 121, 9, 219, 250, 144, 202, 32, 235, 216, 186, 209, 66, 115, 84, 211, 81, 19, 88, 213, 179, 28, 80, 93, 39, 38, 37, 217, 35, 107, 88, 76, 225, 140, 83, 40, 115, 73, 105, 199, 157, 69, 112, 212, 96, 92, 222, 89, 208, 189, 125, 182, 120, 253, 196, 44, 47, 79, 95, 189, 143, 111, 46, 142, 116, 176, 11, 160, 78, 184, 94, 108, 44, 48, 13, 248, 124, 116, 93, 196, 36, 55, 231, 109, 244, 121, 151, 119, 206, 3, 25, 182, 227, 159, 36, 190, 225, 184, 226, 244, 89, 235, 153, 120, 142, 107, 16, 186, 80, 10, 250, 149, 3, 26, 31, 69, 143, 243, 195, 38, 4, 193, 117, 244, 75, 133, 185, 127, 56, 190, 84, 62, 23, 151, 10, 137, 19, 101, 198, 111, 254, 95, 9, 113, 196, 171, 86, 245, 230, 234, 7, 151, 136, 118, 169, 135, 206, 47, 206, 216, 174, 234, 154, 249, 245, 245, 78, 109, 200, 22, 225, 154, 134, 89, 33, 62, 48, 236, 190, 65, 137, 78, 32, 41, 131, 34, 10, 76, 195, 139, 239, 13, 165, 66, 27, 213, 80, 48, 81, 240, 188, 0, 2, 100, 223, 255, 20, 28, 65, 108, 215, 23, 130, 8, 210, 140, 3, 10, 83, 89, 172, 16, 216, 91, 5, 0, 0, 0, 0, 0, 0, 0, 0, 2, 54, 95, 184, 94, 117, 104, 185, 179, 47, 115, 89, 214, 203, 175, 169, 129, 68, 114, 173, 14, 203, 173, 50, 215, 123, 234, 245, 221, 158, 132, 198, 186, 186, 109, 7, 209, 161, 174, 169, 105, 231, 228, 53, 249, 247, 209, 183, 54, 234, 158, 15, 203, 141, 228, 0, 191, 133, 93, 186, 127, 42, 87, 233, 71, 0, 0, 0, 0, 2, 165, 236, 31, 178, 171, 254, 23, 239, 214, 93, 175, 198, 82, 91, 247, 244, 233, 186, 11, 109, 120, 214, 225, 213, 180, 180, 181, 191, 60, 0, 249, 232, 31, 28, 221, 39, 200, 24, 34, 207, 173, 109, 247, 56, 148, 155, 37, 29, 190, 214, 113, 130, 163, 135, 162, 217, 110, 57, 179, 174, 133, 6, 103, 12, 111, 40, 96, 61, 126, 118, 146, 11, 130, 249, 171, 204, 247, 213, 200, 83, 76, 163, 17, 235, 62, 65, 207, 188, 25, 200, 124, 248, 55, 208, 174, 39, 198, 106, 42, 30, 221, 31, 72, 38, 36, 226, 248, 67, 52, 57, 114, 109, 120, 60, 43, 161, 88, 124, 66, 83, 47, 215, 129, 18, 45, 194, 58, 15, 190, 25, 100, 241, 21, 95, 241, 21};
    String dataAsHex = "B77F44715E0B3014067909DBFA90CA20EBD8BAD1427354D3511358D5B31C505D272625D9236B584CE18C5328734969C79D4570D4605CDE59D0BD7DB678FDC42C2F4F5FBD8F6F2E8E74B00BA04EB85E6C2C300DF87C745DC42437E76DF4799777CE0319B6E39F24BEE1B8E2F459EB99788E6B10BA500AFA95031A1F458FF3C32604C175F44B85B97F38BE543E17970A891365C66FFE5F0971C4AB56F5E6EA07978876A987CE2FCED8AEEA9AF9F5F54E6DC816E19A8659213E30ECBE41894E202983220A4CC38BEF0DA5BF01000001000000BFE41200B802476100000000067909DBFA90CA20EBD8BAD1427354D3511358D5B31C505D272625D9236B584CE18C5328734969C79D4570D4605CDE59D0BD7DB678FDC42C2F4F5FBD8F6F2E8E74B00BA04EB85E6C2C300DF87C745DC42437E76DF4799777CE0319B6E39F24BEE1B8E2F459EB99788E6B10BA500AFA95031A1F458FF3C32604C175F44B85B97F38BE543E17970A891365C66FFE5F0971C4AB56F5E6EA07978876A987CE2FCED8AEEA9AF9F5F54E6DC816E19A8659213E30ECBE41894E202983220A4CC38BEF0DA5421BD5503051F0BC000264DFFF141C416CD7178208D28C030A5359AC10D85B05000000000000000002365FB85E7568B9B32F7359D6CBAFA9814472AD0ECBAD32D77BEAF5DD9E84C6BABA6D07D1A1AEA969E7E435F9F7D1B736EA9E0FCB8DE400BF855DBA7F2A57E9470000000002A5EC1FB2ABFE17EFD65DAFC6525BF7F4E9BA0B6D78D6E1D5B4B4B5BF3C00F9E81F1CDD27C81822CFAD6DF738949B251DBED67182A387A2D96E39B3AE8506670C6F28603D7E76920B82F9ABCCF7D5C8534CA311EB3E41CFBC19C87CF837D0AE27C66A2A1EDD1F482624E2F8433439726D783C2BA1587C42532FD781122DC23A0FBE1964F1155FF115";
    String messageHash = "4EE09335845F8C62A9B9958A77C10B4E3F336C23D949EC328916B33F4A8488E4";
    String messageId = "4EE09335845F8C62A9B9958A77C10B4E3F336C23D949EC328916B33F4A8488E4";
    String parent1 = "7909DBFA90CA20EBD8BAD1427354D3511358D5B31C505D272625D9236B584CE1";
    String parent2 = "8C5328734969C79D4570D4605CDE59D0BD7DB678FDC42C2F4F5FBD8F6F2E8E74";
    String parent3 = "B00BA04EB85E6C2C300DF87C745DC42437E76DF4799777CE0319B6E39F24BEE1";
    String parent4 = "B8E2F459EB99788E6B10BA500AFA95031A1F458FF3C32604C175F44B85B97F38";
    String parent5 = "BE543E17970A891365C66FFE5F0971C4AB56F5E6EA07978876A987CE2FCED8AE";
    String parent6 = "EA9AF9F5F54E6DC816E19A8659213E30ECBE41894E202983220A4CC38BEF0DA5";
    String inclusionMerkleProof = "421BD5503051F0BC000264DFFF141C416CD7178208D28C030A5359AC10D85B05";
    String publicKey1 = "365FB85E7568B9B32F7359D6CBAFA9814472AD0ECBAD32D77BEAF5DD9E84C6BA";
    String publicKey2 = "BA6D07D1A1AEA969E7E435F9F7D1B736EA9E0FCB8DE400BF855DBA7F2A57E947";
    String signature1 = "A5EC1FB2ABFE17EFD65DAFC6525BF7F4E9BA0B6D78D6E1D5B4B4B5BF3C00F9E81F1CDD27C81822CFAD6DF738949B251DBED67182A387A2D96E39B3AE8506670C";
    String signature2 = "6F28603D7E76920B82F9ABCCF7D5C8534CA311EB3E41CFBC19C87CF837D0AE27C66A2A1EDD1F482624E2F8433439726D783C2BA1587C42532FD781122DC23A0F";

    @Test
    void testingDeserialization() {
        gossipPayloadDTO.setData(BaseEncoding.base16().decode(dataAsHex));
        Assertions.assertEquals(BaseEncoding.base16().encode(GossipHelperBA.unsignedIntToSignedBytes(dataAsIntArray)), gossipPayloadDTO.getDataAsHex());
        Assertions.assertArrayEquals(GossipHelperBA.signedByteToUnsignedInt(gossipPayloadDTO.getData()), dataAsIntArray);
        gossipPayloadBA.deserialize(gossipPayloadDTO);
        Assertions.assertArrayEquals(BaseEncoding.base16().decode(messageHash), gossipPayloadDTO.getMessageHash());
        Assertions.assertArrayEquals(BaseEncoding.base16().decode(messageId), gossipPayloadDTO.getMessageId());
        Assertions.assertEquals(1454675179895816119L, gossipPayloadDTO.getNetworkId());
        Assertions.assertEquals(6, gossipPayloadDTO.getParents().size());
        Assertions.assertArrayEquals(BaseEncoding.base16().decode(parent1), gossipPayloadDTO.getParents().get(0));
        Assertions.assertArrayEquals(BaseEncoding.base16().decode(parent2), gossipPayloadDTO.getParents().get(1));
        Assertions.assertArrayEquals(BaseEncoding.base16().decode(parent3), gossipPayloadDTO.getParents().get(2));
        Assertions.assertArrayEquals(BaseEncoding.base16().decode(parent4), gossipPayloadDTO.getParents().get(3));
        Assertions.assertArrayEquals(BaseEncoding.base16().decode(parent5), gossipPayloadDTO.getParents().get(4));
        Assertions.assertArrayEquals(BaseEncoding.base16().decode(parent6), gossipPayloadDTO.getParents().get(5));
        Assertions.assertEquals(447, gossipPayloadDTO.getPayloadLength());
        Assertions.assertEquals(1, gossipPayloadDTO.getMessageType());

        // set Payload
        switch (gossipPayloadDTO.getMessageType()) {
            case TRANSACTION_PAYLOAD_TYPE_ID:
                break;
            case MILESTONE_PAYLOAD_TYPE_ID:
                iotaMessageMilestoneDTO.setData(gossipPayloadDTO.getData());

                iotaMessageMilestoneBA.deserialize(iotaMessageMilestoneDTO);
                Assertions.assertEquals(1238207, iotaMessageMilestoneDTO.getIndex());
                Assertions.assertEquals(1632043704L, iotaMessageMilestoneDTO.getTimestamp());
                Assertions.assertEquals(6, iotaMessageMilestoneDTO.getParents().size());
                Assertions.assertArrayEquals(BaseEncoding.base16().decode(parent1), iotaMessageMilestoneDTO.getParents().get(0));
                Assertions.assertArrayEquals(BaseEncoding.base16().decode(parent2), iotaMessageMilestoneDTO.getParents().get(1));
                Assertions.assertArrayEquals(BaseEncoding.base16().decode(parent3), iotaMessageMilestoneDTO.getParents().get(2));
                Assertions.assertArrayEquals(BaseEncoding.base16().decode(parent4), iotaMessageMilestoneDTO.getParents().get(3));
                Assertions.assertArrayEquals(BaseEncoding.base16().decode(parent5), iotaMessageMilestoneDTO.getParents().get(4));
                Assertions.assertArrayEquals(BaseEncoding.base16().decode(parent6), iotaMessageMilestoneDTO.getParents().get(5));
                Assertions.assertArrayEquals(BaseEncoding.base16().decode(inclusionMerkleProof), iotaMessageMilestoneDTO.getInclusionMerkleProof());
                Assertions.assertEquals(0, iotaMessageMilestoneDTO.getNextPoWScore());
                Assertions.assertEquals(0, iotaMessageMilestoneDTO.getNextPoWScoreMilestoneIndex());
                Assertions.assertEquals(2, iotaMessageMilestoneDTO.getPublicKeys().size());
                Assertions.assertArrayEquals(BaseEncoding.base16().decode(publicKey1), iotaMessageMilestoneDTO.getPublicKeys().get(0));
                Assertions.assertArrayEquals(BaseEncoding.base16().decode(publicKey2), iotaMessageMilestoneDTO.getPublicKeys().get(1));
                Assertions.assertNull(iotaMessageMilestoneDTO.getReceipt());
                Assertions.assertArrayEquals(BaseEncoding.base16().decode(signature1), iotaMessageMilestoneDTO.getSignatures().get(0));
                Assertions.assertArrayEquals(BaseEncoding.base16().decode(signature2), iotaMessageMilestoneDTO.getSignatures().get(1));
                break;
            case INDEXATION_PAYLOAD_TYPE_ID:
                break;
        }
        gossipPayloadDTO.setData(iotaMessageMilestoneDTO.getData());
        gossipPayloadBA.extractNonce(gossipPayloadDTO);
        Assertions.assertEquals(1581149492032575934L, gossipPayloadDTO.getNonce());
    }


}

// original milestone test data from hornet node (unsigned)
// data = 183,127,68,113,94,11,48,20,6,121,9,219,250,144,202,32,235,216,186,209,66,115,84,211,81,19,88,213,179,28,80,93,39,38,37,217,35,107,88,76,225,140,83,40,115,73,105,199,157,69,112,212,96,92,222,89,208,189,125,182,120,253,196,44,47,79,95,189,143,111,46,142,116,176,11,160,78,184,94,108,44,48,13,248,124,116,93,196,36,55,231,109,244,121,151,119,206,3,25,182,227,159,36,190,225,184,226,244,89,235,153,120,142,107,16,186,80,10,250,149,3,26,31,69,143,243,195,38,4,193,117,244,75,133,185,127,56,190,84,62,23,151,10,137,19,101,198,111,254,95,9,113,196,171,86,245,230,234,7,151,136,118,169,135,206,47,206,216,174,234,154,249,245,245,78,109,200,22,225,154,134,89,33,62,48,236,190,65,137,78,32,41,131,34,10,76,195,139,239,13,165,191,1,0,0,1,0,0,0,191,228,18,0,184,2,71,97,0,0,0,0,6,121,9,219,250,144,202,32,235,216,186,209,66,115,84,211,81,19,88,213,179,28,80,93,39,38,37,217,35,107,88,76,225,140,83,40,115,73,105,199,157,69,112,212,96,92,222,89,208,189,125,182,120,253,196,44,47,79,95,189,143,111,46,142,116,176,11,160,78,184,94,108,44,48,13,248,124,116,93,196,36,55,231,109,244,121,151,119,206,3,25,182,227,159,36,190,225,184,226,244,89,235,153,120,142,107,16,186,80,10,250,149,3,26,31,69,143,243,195,38,4,193,117,244,75,133,185,127,56,190,84,62,23,151,10,137,19,101,198,111,254,95,9,113,196,171,86,245,230,234,7,151,136,118,169,135,206,47,206,216,174,234,154,249,245,245,78,109,200,22,225,154,134,89,33,62,48,236,190,65,137,78,32,41,131,34,10,76,195,139,239,13,165,66,27,213,80,48,81,240,188,0,2,100,223,255,20,28,65,108,215,23,130,8,210,140,3,10,83,89,172,16,216,91,5,0,0,0,0,0,0,0,0,2,54,95,184,94,117,104,185,179,47,115,89,214,203,175,169,129,68,114,173,14,203,173,50,215,123,234,245,221,158,132,198,186,186,109,7,209,161,174,169,105,231,228,53,249,247,209,183,54,234,158,15,203,141,228,0,191,133,93,186,127,42,87,233,71,0,0,0,0,2,165,236,31,178,171,254,23,239,214,93,175,198,82,91,247,244,233,186,11,109,120,214,225,213,180,180,181,191,60,0,249,232,31,28,221,39,200,24,34,207,173,109,247,56,148,155,37,29,190,214,113,130,163,135,162,217,110,57,179,174,133,6,103,12,111,40,96,61,126,118,146,11,130,249,171,204,247,213,200,83,76,163,17,235,62,65,207,188,25,200,124,248,55,208,174,39,198,106,42,30,221,31,72,38,36,226,248,67,52,57,114,109,120,60,43,161,88,124,66,83,47,215,129,18,45,194,58,15,190,25,100,241,21,95,241,21

// NetworkID = 1454675179895816119 (long)
//
// Parents 0 = 121,9,219,250,144,202,32,235,216,186,209,66,115,84,211,81,19,88,213,179,28,80,93,39,38,37,217,35,107,88,76,225
// Parents 1 = 140,83,40,115,73,105,199,157,69,112,212,96,92,222,89,208,189,125,182,120,253,196,44,47,79,95,189,143,111,46,142,116
// Parents 2 = 176,11,160,78,184,94,108,44,48,13,248,124,116,93,196,36,55,231,109,244,121,151,119,206,3,25,182,227,159,36,190,225
// Parents 3 = 184,226,244,89,235,153,120,142,107,16,186,80,10,250,149,3,26,31,69,143,243,195,38,4,193,117,244,75,133,185,127,56
// Parents 4 = 190,84,62,23,151,10,137,19,101,198,111,254,95,9,113,196,171,86,245,230,234,7,151,136,118,169,135,206,47,206,216,174
// Parents 5 = 234,154,249,245,245,78,109,200,22,225,154,134,89,33,62,48,236,190,65,137,78,32,41,131,34,10,76,195,139,239,13,165
//
// Payload
//      Index = 1238207 (int)
//      Timestamp = 1632043704 (long)
//      Parents 0 = 121,9,219,250,144,202,32,235,216,186,209,66,115,84,211,81,19,88,213,179,28,80,93,39,38,37,217,35,107,88,76,225
//      Parents 1 = 140,83,40,115,73,105,199,157,69,112,212,96,92,222,89,208,189,125,182,120,253,196,44,47,79,95,189,143,111,46,142,116
//      Parents 2 = 176,11,160,78,184,94,108,44,48,13,248,124,116,93,196,36,55,231,109,244,121,151,119,206,3,25,182,227,159,36,190,225
//      Parents 3 = 184,226,244,89,235,153,120,142,107,16,186,80,10,250,149,3,26,31,69,143,243,195,38,4,193,117,244,75,133,185,127,56
//      Parents 4 = 190,84,62,23,151,10,137,19,101,198,111,254,95,9,113,196,171,86,245,230,234,7,151,136,118,169,135,206,47,206,216,174
//      Parents 5 = 234,154,249,245,245,78,109,200,22,225,154,134,89,33,62,48,236,190,65,137,78,32,41,131,34,10,76,195,139,239,13,165
//      InclusionMerkleProof = 66,27,213,80,48,81,240,188,0,2,100,223,255,20,28,65,108,215,23,130,8,210,140,3,10,83,89,172,16,216,91,5
//      NextPoWScore = 0 (int)
//      NextPoWScoreMilestoneIndex = 0 (int)
//      PublicKeys 0 = 54,95,184,94,117,104,185,179,47,115,89,214,203,175,169,129,68,114,173,14,203,173,50,215,123,234,245,221,158,132,198,186
//      PublicKeys 1 = 186,109,7,209,161,174,169,105,231,228,53,249,247,209,183,54,234,158,15,203,141,228,0,191,133,93,186,127,42,87,233,71
//      Receipt = null
//      Signatures 0 = 165,236,31,178,171,254,23,239,214,93,175,198,82,91,247,244,233,186,11,109,120,214,225,213,180,180,181,191,60,0,249,232,31,28,221,39,200,24,34,207,173,109,247,56,148,155,37,29,190,214,113,130,163,135,162,217,110,57,179,174,133,6,103,12
//      Signatures 1 = 111,40,96,61,126,118,146,11,130,249,171,204,247,213,200,83,76,163,17,235,62,65,207,188,25,200,124,248,55,208,174,39,198,106,42,30,221,31,72,38,36,226,248,67,52,57,114,109,120,60,43,161,88,124,66,83,47,215,129,18,45,194,58,15
//
// Nonce = 1581149492032575934 (long)
//
//+++++++++++++
// msgHash = 78,224,147,53,132,95,140,98,169,185,149,138,119,193,11,78,63,51,108,35,217,73,236,50,137,22,179,63,74,132,136,228
// messageID = 78,224,147,53,132,95,140,98,169,185,149,138,119,193,11,78,63,51,108,35,217,73,236,50,137,22,179,63,74,132,136,228
