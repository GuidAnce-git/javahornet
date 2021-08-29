package iota;

public class IotaMessage
{
  // MessageIDLength defines the length of a message ID.
  // private static final int messageIDLength = blake2b.Size256;
  // MessageNetworkIDLength defines the length of the network ID in bytes.
  // private static final int messageNetworkIDLength = UInt64ByteSize;
  // MessageBinSerializedMinSize defines the minimum size of a message: network ID + parent count + 1 parent + uint16 payload length + nonce
  // private static final int messageBinSerializedMinSize = MessageNetworkIDLength + OneByte + MessageIDLength + UInt32ByteSize + UInt64ByteSize;
  // MessageBinSerializedMaxSize defines the maximum size of a message.
  public static final int messageBinSerializedMaxSize = 32768;
  // MinParentsInAMessage defines the minimum amount of parents in a message.
  static final int minParentsInAMessage = 1;
  // MaxParentsInAMessage defines the maximum amount of parents in a message.
  static final int MaxParentsInAMessage = 8;

}