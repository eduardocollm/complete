package example.micronaut.controller;

import example.micronaut.domain.PostRequest;
import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class PostControllerTest {

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
    public void testPostIt() {
        HttpRequest request = HttpRequest.POST("/numbers", "{ \"dos\": \"hola\" }").basicAuth("sherlock", "password");
        try {
            String result = client.toBlocking().retrieve(request);
        } catch (HttpClientResponseException e) {
            Assert.assertEquals("request.uno: uno cannot be null", e.getMessage());
            return;
        }
        Assert.assertFalse(true);
    }
}
