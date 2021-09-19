package p2p.githubtest.message;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;

@QuarkusTest
class MessageBATest {

    @Test
    void deserialize() {
        String hexString = "0a8602020301b77f44715e0b30140412deb426629b1fc6f1ae8dc6895a7ab069dc2292b78cee90385c4db76c7fbe522609019bcef0f8fdd4820c53ad4872bc6febecf2e1d6e46eee2dab23993f598070b53105b5b11b29fe1fd256c02c9449b1d3d432835051419bf253e6fa574e5d8d2b0d02443f136dc298f1762a223bd11c6b323079cdd0bef410352828bbddf26e000000020000000f00546573746e6574205370616d6d6572550000005370616d6d696e6720646174612e0a436f756e743a203438303935310a54696d657374616d703a20323032312d30392d31375430303a30373a33352b30323a30300a54697073656c656374696f6e3a203532c2b573f86703000000000038ff01e03f3364c1f1ccdc38b82031a2";
        IotaMessageDTO iotaMessageDTO = new IotaMessageDTO();

        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(hexString.getBytes(StandardCharsets.UTF_8)); ObjectInput objectInput = new ObjectInputStream(byteArrayInputStream)) {
            System.out.println("test");
            iotaMessageDTO = (IotaMessageDTO) objectInput.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void MessageFromBytes(byte[] data) {

    }

}