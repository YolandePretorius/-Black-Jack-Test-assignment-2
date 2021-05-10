/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.massey.cs.webtech.s_18038659.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 18038659
 */
public class StateTestNoSession {

    private static final String path = "assignment2_server_18038659/"; //TODO: Change to your setup
    private static int port = 8080;
    public CloseableHttpClient client = HttpClients.createDefault();

    public StateTestNoSession() {
    }

    @Before
    public void setUpClass() {
    }

    @After
    public void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void startNoSessionState() throws Exception {
        // no game session created should receive 404 request
        assertEquals(HttpStatus.SC_NOT_FOUND, statusRequest("jack/state"));

    }

    private Object statusRequest(String expression) throws IOException, Exception {

        URI url = constructUri(expression);
        HttpGet get = new HttpGet(url);

        System.out.println(get);
        CloseableHttpResponse response = client.execute(get);
        return response.getStatusLine().getStatusCode();

    }

    private static URI constructUri(String expression) throws Exception {

        URI url = null;
        try {
            url = new URIBuilder()
                    .setScheme("http")
                    .setHost("localhost")
                    .setPort(port)
                    .setPath(path + expression)
                    //.setParameter("expression", expression)
                    .build();
        } catch (URISyntaxException ex) {
            throw new Exception("Error creating service URI", ex);
        }
        return url;
    }
}
