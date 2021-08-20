package Database;

import io.quarkus.test.junit.QuarkusTest;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@QuarkusTest
class DatabaseApiResourceTest {

    @BeforeAll
    static void createTestData() {
        RedisDB.save("TestKey", "TestValue");
    }

    @Test
    void getTest() throws IOException {
        final HttpUriRequest request = new HttpGet("http://locaLhost:8080/db/get/TestKey");
        final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        final String body = IOUtils.toString(httpResponse.getEntity().getContent().readAllBytes(), "UTF-8");
        Assertions.assertEquals("TestValue", body);
    }

    @Test
    void testPerformancewith100Tx() {
        final int counter = 1000;
        final long start = System.nanoTime();
        for (int i = 0; i < counter; i++) RedisDB.save(String.valueOf(i), RandomStringUtils.randomAlphabetic(10));
        final long duration = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
        Assertions.assertTrue(duration <= 4000);
    }

}