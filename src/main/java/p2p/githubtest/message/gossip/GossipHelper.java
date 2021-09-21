package p2p.githubtest.message.gossip;

public final class GossipHelper {
    static byte[] unsignedIntToSignedBytes(int[] values) {
        byte[] newData = new byte[values.length];
        for (int i = 0; i < values.length; i++) {
            newData[i] = (byte) values[i];
        }

        return newData;
    }

    static int[] signedByteToUnsignedInt(byte[] values) {

        int[] newData = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            newData[i] = values[i] & 0xff;
        }

        return newData;
    }
}
