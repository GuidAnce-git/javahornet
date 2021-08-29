package p2p.githubtest.message;

public class Type
{
  private static final byte messageTypeMilestoneRequest = 1;
  private static final byte messageTypeMessage        = 2;
  private static final byte messageTypeMessageRequest = 3;
  private static final byte messageTypeHeartbeat      = 4;

  private byte type;

  public Type(byte type)
  {
    this.type = type;
  }

  public byte getType()
  {
    return type;
  }

  public void setType(byte type)
  {
    this.type = type;
  }
}
