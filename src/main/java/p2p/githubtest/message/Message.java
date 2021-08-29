package p2p.githubtest.message;

public class Message
{
  // The amount of bytes used for the requested message ID.
  public static final int requestedMessageIDMsgBytesLength = 32;

  // The amount of bytes used for the requested milestone index.
  public static final int requestedMilestoneIndexMsgBytesLength = 4;

  // The amount of bytes used for a milestone index within a heartbeat packet.
  public static final int heartbeatMilestoneIndexBytesLength = 4;

  // The index to use to request the latest milestone via a milestone request message.
  public static final int latestMilestoneRequestIndex = 0;

  Type id;
  int maxBytesLength;
  boolean variableLength;

  public Type getId()
  {
    return id;
  }

  public void setId(Type id)
  {
    this.id = id;
  }

  public int getMaxBytesLength()
  {
    return maxBytesLength;
  }

  public void setMaxBytesLength(int maxBytesLength)
  {
    this.maxBytesLength = maxBytesLength;
  }

  public boolean isVariableLength()
  {
    return variableLength;
  }

  public void setVariableLength(boolean variableLength)
  {
    this.variableLength = variableLength;
  }


}
