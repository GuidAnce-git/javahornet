package p2p.githubtest.message.gossip;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class GossipHelperBA {
    public static byte[] unsignedIntToSignedBytes(int[] values) {
        byte[] newData = new byte[values.length];
        for (int i = 0; i < values.length; i++) {
            newData[i] = (byte) values[i];
        }

        return newData;
    }

    public static int[] signedByteToUnsignedInt(byte[] values) {

        int[] newData = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            newData[i] = values[i] & 0xff;
        }

        return newData;
    }

    public static List<byte[]> extractBytesToArray(byte[] data, int bytesPosition, int bytesLength) {
        int count = data[bytesPosition];
        data = Arrays.copyOfRange(data, 1, data.length);

        List<byte[]> resultBytesList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            resultBytesList.add(Arrays.copyOfRange(data, (bytesLength * i), (bytesLength * i) + bytesLength));
        }
        return resultBytesList;
    }

    public static int extractBytesToInt(byte[] data, int bytesLength) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(Arrays.copyOfRange(data, 0, bytesLength));
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return byteBuffer.getInt();
    }

    public static long extractBytesToLong(byte[] data, int bytesLength) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(Arrays.copyOfRange(data, 0, bytesLength));
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return byteBuffer.getLong();
    }
}
