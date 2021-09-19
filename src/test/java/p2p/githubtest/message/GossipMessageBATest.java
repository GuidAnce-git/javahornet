package p2p.githubtest.message;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

class GossipMessageBATest {

    int[] data = {183, 127, 68, 113, 94, 11, 48, 20, 6, 121, 9, 219, 250, 144, 202, 32, 235, 216, 186, 209, 66, 115, 84, 211, 81, 19, 88, 213, 179, 28, 80, 93, 39, 38, 37, 217, 35, 107, 88, 76, 225, 140, 83, 40, 115, 73, 105, 199, 157, 69, 112, 212, 96, 92, 222, 89, 208, 189, 125, 182, 120, 253, 196, 44, 47, 79, 95, 189, 143, 111, 46, 142, 116, 176, 11, 160, 78, 184, 94, 108, 44, 48, 13, 248, 124, 116, 93, 196, 36, 55, 231, 109, 244, 121, 151, 119, 206, 3, 25, 182, 227, 159, 36, 190, 225, 184, 226, 244, 89, 235, 153, 120, 142, 107, 16, 186, 80, 10, 250, 149, 3, 26, 31, 69, 143, 243, 195, 38, 4, 193, 117, 244, 75, 133, 185, 127, 56, 190, 84, 62, 23, 151, 10, 137, 19, 101, 198, 111, 254, 95, 9, 113, 196, 171, 86, 245, 230, 234, 7, 151, 136, 118, 169, 135, 206, 47, 206, 216, 174, 234, 154, 249, 245, 245, 78, 109, 200, 22, 225, 154, 134, 89, 33, 62, 48, 236, 190, 65, 137, 78, 32, 41, 131, 34, 10, 76, 195, 139, 239, 13, 165, 191, 1, 0, 0, 1, 0, 0, 0, 191, 228, 18, 0, 184, 2, 71, 97, 0, 0, 0, 0, 6, 121, 9, 219, 250, 144, 202, 32, 235, 216, 186, 209, 66, 115, 84, 211, 81, 19, 88, 213, 179, 28, 80, 93, 39, 38, 37, 217, 35, 107, 88, 76, 225, 140, 83, 40, 115, 73, 105, 199, 157, 69, 112, 212, 96, 92, 222, 89, 208, 189, 125, 182, 120, 253, 196, 44, 47, 79, 95, 189, 143, 111, 46, 142, 116, 176, 11, 160, 78, 184, 94, 108, 44, 48, 13, 248, 124, 116, 93, 196, 36, 55, 231, 109, 244, 121, 151, 119, 206, 3, 25, 182, 227, 159, 36, 190, 225, 184, 226, 244, 89, 235, 153, 120, 142, 107, 16, 186, 80, 10, 250, 149, 3, 26, 31, 69, 143, 243, 195, 38, 4, 193, 117, 244, 75, 133, 185, 127, 56, 190, 84, 62, 23, 151, 10, 137, 19, 101, 198, 111, 254, 95, 9, 113, 196, 171, 86, 245, 230, 234, 7, 151, 136, 118, 169, 135, 206, 47, 206, 216, 174, 234, 154, 249, 245, 245, 78, 109, 200, 22, 225, 154, 134, 89, 33, 62, 48, 236, 190, 65, 137, 78, 32, 41, 131, 34, 10, 76, 195, 139, 239, 13, 165, 66, 27, 213, 80, 48, 81, 240, 188, 0, 2, 100, 223, 255, 20, 28, 65, 108, 215, 23, 130, 8, 210, 140, 3, 10, 83, 89, 172, 16, 216, 91, 5, 0, 0, 0, 0, 0, 0, 0, 0, 2, 54, 95, 184, 94, 117, 104, 185, 179, 47, 115, 89, 214, 203, 175, 169, 129, 68, 114, 173, 14, 203, 173, 50, 215, 123, 234, 245, 221, 158, 132, 198, 186, 186, 109, 7, 209, 161, 174, 169, 105, 231, 228, 53, 249, 247, 209, 183, 54, 234, 158, 15, 203, 141, 228, 0, 191, 133, 93, 186, 127, 42, 87, 233, 71, 0, 0, 0, 0, 2, 165, 236, 31, 178, 171, 254, 23, 239, 214, 93, 175, 198, 82, 91, 247, 244, 233, 186, 11, 109, 120, 214, 225, 213, 180, 180, 181, 191, 60, 0, 249, 232, 31, 28, 221, 39, 200, 24, 34, 207, 173, 109, 247, 56, 148, 155, 37, 29, 190, 214, 113, 130, 163, 135, 162, 217, 110, 57, 179, 174, 133, 6, 103, 12, 111, 40, 96, 61, 126, 118, 146, 11, 130, 249, 171, 204, 247, 213, 200, 83, 76, 163, 17, 235, 62, 65, 207, 188, 25, 200, 124, 248, 55, 208, 174, 39, 198, 106, 42, 30, 221, 31, 72, 38, 36, 226, 248, 67, 52, 57, 114, 109, 120, 60, 43, 161, 88, 124, 66, 83, 47, 215, 129, 18, 45, 194, 58, 15, 190, 25, 100, 241, 21, 95, 241, 21};

    @Test
    void testingGoConversion() {

        IotaMessageDTO iotaMessageDTO = new IotaMessageDTO();
        iotaMessageDTO.setNetworkId(unsignedIntToLong(data, 0, 8));
        

    }

    byte[] unsignedIntToBytes(int[] values) {
        byte[] newData = new byte[values.length];
        for (int i = 0; i < 8; i++) {
            newData[i] = (byte) data[i];
        }

        return newData;
    }

    long unsignedIntToLong(int[] values, int from, int to) {

        ByteBuffer bb = ByteBuffer.wrap(Arrays.copyOfRange(unsignedIntToBytes(values), from, to));
        bb.order(ByteOrder.LITTLE_ENDIAN);

        return bb.getLong();
    }

}

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
