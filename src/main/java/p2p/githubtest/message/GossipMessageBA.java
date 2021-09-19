package p2p.githubtest.message;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

public class GossipMessageBA {
    static final int MESSAGE_BIN_SERIALIZED_MAX_SIZE = 32768;


    IotaMessageDTO deserialize(byte[] data) {
        IotaMessageDTO iotaMessageDTO = new IotaMessageDTO();

        if (data.length <= MESSAGE_BIN_SERIALIZED_MAX_SIZE) {
            try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data); ObjectInput objectInput = new ObjectInputStream(byteArrayInputStream)) {
                iotaMessageDTO = (IotaMessageDTO) objectInput.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        return iotaMessageDTO;
    }

    IotaMessageDTO deserialize2(byte[] data) {
        IotaMessageDTO iotaMessageDTO = new IotaMessageDTO();

        if (data.length <= MESSAGE_BIN_SERIALIZED_MAX_SIZE) {
            
        }
        return iotaMessageDTO;
    }
}
