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
public class test {

    private static final String path = "assignment2_server_18038659/jack/start"; //TODO: Change to your setup
    private static int port = 8080;

    public test() {
        // assertSame(2, 2);
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

//    @Test
//     public void hello() throws Exception{
//         
//        
//        //assertTrue(makeServiceRequest("").contains("hello"));
//         
//     
//     }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void StartTest() throws Exception {

        assertEquals(statusRequest(), HttpStatus.SC_OK);
        assertNotEquals(makeServiceRequest(), makeServiceRequest());
    }

    private static String makeServiceRequest() throws Exception {

        try (CloseableHttpClient client = HttpClients.createDefault()) {

            //URI url = constructUri(expression);
            //HttpGet get = new HttpGet(url);
            //create a request
            HttpGet get = new HttpGet("http://localhost:8080/assignment2_server_18038659/jack/start");

            try (CloseableHttpResponse response = client.execute(get)) {
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    //String header = response.headers().get("Set-Cookie");
                    Header[] header = response.getHeaders("Set-Cookie");
                    String arr[] = header[0].getValue().split(";");
                    String jsessionidArray[] = arr[0].split("=");
                    String jsessionid = jsessionidArray[1];
                    return jsessionid;
//                    String arr[] = header.split("=");
//                    String jsessionid = arr[1];
//                    jsessionid = jsessionid.substring(0, jsessionid.length() - 5);

                    //assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
                    //return response.getStatusLine().getStatusCode();
                    //getResponseBody(response.getEntity().getContent()); // 
                    //} else {
                    // throw new Exception("Error response from service.");
                    //}
                   
                }
            }
            
        }
        return null;

    }

    private static String getResponseBody(final InputStream stream) throws UnsupportedOperationException, IOException {

        StringBuilder builder = new StringBuilder();

        BufferedReader rd = new BufferedReader(new InputStreamReader(stream));
        String line = "";
        while ((line = rd.readLine()) != null) {
            builder.append(line);
        }
        return builder.toString();
    }

    private static URI constructUri(String expression) throws Exception {

        URI url = null;
        try {
            url = new URIBuilder()
                    .setScheme("http")
                    .setHost("localhost")
                    .setPort(port)
                    .setPath(path)
                    .setParameter("expression", expression)
                    .build();
        } catch (URISyntaxException ex) {
            throw new Exception("Error creating service URI", ex);
        }
        return url;
    }

    private Object statusRequest() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet("http://localhost:8080/assignment2_server_18038659/jack/start");
        CloseableHttpResponse response = client.execute(get);
        return response.getStatusLine().getStatusCode();
    }
}
