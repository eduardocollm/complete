package example.micronaut.controller;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class InfoEndpointTest {

    private static EmbeddedServer server;
    private static HttpClient client;

    @BeforeClass
    public static void setUp() {
        server = ApplicationContext.run(EmbeddedServer.class);
        client = server.getApplicationContext().createBean(HttpClient.class, server.getURL());
    }

    @AfterClass
    public static void tearDown() {
        if (server != null) { server.stop(); }

        if (client != null) { client.stop(); }
    }

    @Test
    public void testInfo() {
        HttpRequest request = HttpRequest.GET("/info");

        HttpResponse<Map> rsp = client.toBlocking().exchange(request, Map.class);

        assertEquals(rsp.status().getCode(), 200);

        Map json = rsp.body();

        then:
        assertNotNull(json.get("git"));
        assertNotNull(((Map) json.get("git")).get("commit"));
        assertNotNull(((Map) ((Map) json.get("git")).get("commit")).get("message"));
        assertNotNull(((Map) ((Map) json.get("git")).get("commit")).get("time"));
        assertNotNull(((Map) ((Map) json.get("git")).get("commit")).get("id"));
        assertNotNull(((Map) ((Map) json.get("git")).get("commit")).get("user"));
        assertNotNull(((Map) json.get("git")).get("branch"));
    }
}
