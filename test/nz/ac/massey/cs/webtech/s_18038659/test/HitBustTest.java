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
import static java.net.SocketOptions.SO_TIMEOUT;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 18038659
 */
public class HitBustTest {

    private static final String path = "assignment2_server_18038659/"; //TODO: Change to your setup
    private static int port = 8080;
    String jsessionid;
    CloseableHttpClient client;
    HashMap<String, String> allHeaders;
    CloseableHttpResponse response;
//    CloseableHttpClient client = HttpClients.createDefault();
//    private CookieStore cookieStore;
//    CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore);
    
            
    
//    BasicCookieStore cookieStore = new BasicCookieStore();
//    CloseableHttpClient client = HttpClients.custom()
//                .setDefaultCookieStore(cookieStore)
//                .build();
//    
//  
    public HitBustTest() {
    }

    @Before
    public void setUpClass() {
    }

    @After
    public void tearDownClass() {
    }

    @Before
    public void setUp() throws IOException, Exception {
        client = HttpClients.createDefault();
        
        URI url = constructUri("jack/start");
        HttpGet get = new HttpGet(url);

        //HttpGet get = new HttpGet("http://localhost:8080/assignment2_server_18038659/jack/start");
        response = client.execute(get);
       // response.setHeader(header);
      
       
      
        Header[] header = response.getHeaders("Set-Cookie");
//        Header[] headersArray = response.getAllHeaders();
//          Header[] headersArray = CloseableHttpResponse.getAllHeaders();
//         allHeaders = new HashMap<String, String>();
//        
//        for(Header header :headersArray){
//            allHeaders.put(header.getName(),header.getValue());
//        }
//        
//        String jsessionID = allHeaders[0];
        String arr[] = header[0].getValue().split(";");
        String jsessionidArray[] = arr[0].split("=");
        jsessionid = jsessionidArray[1];
         //response.addHeader("session-ID", jsessionid);
         //Header[] headerNew = response.getHeaders("session-ID");
         //return jsessionid;
    }

    @After
    public void tearDown() {
        
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void startHit() throws Exception {

       //assertEquals(statusRequest("jack/start"), HttpStatus.SC_OK); // create a game session
        //Object hit = statusRequest("jack/move/hit");
        hit();
    }

    public void hit() throws Exception {
        //CloseableHttpClient client = HttpClients.createDefault();
        //assertEquals(statusRequest("jack/hit",client), HttpStatus.SC_NOT_FOUND);
        //assertEquals(statusRequest("jack/hit",client), HttpStatus.SC_NOT_FOUND); // if there is not active game
        //assertEquals(statusRequest("jack/start"), HttpStatus.SC_OK);
        // assertEquals(statusRequest("jack/hit"),statusRequest("jack/start"));
        
        Object hit = statusRequest("jack/move/hit");
        
        Object hit2 = statusRequest("jack/start");
        Object hit3 = statusRequest("jack/move/hit");
        Object hit4 = statusRequest("jack/move/hit");
        Object hit5 = statusRequest("jack/move/hit");
//        for (int i = 0; i < 3; i++) {
//            Object hit = statusRequest("jack/move/hit");
//            System.out.println("hit");
//            //assertEquals(HttpStatus.SC_OK,statusRequest("jack/move/hit"));
//        }
        //assertEquals(HttpStatus.SC_OK,statusRequest("jack/move/hit"));
    }

    private Object statusRequest(String expression) throws IOException, Exception {
       // CloseableHttpClient client = HttpClients.createDefault();
        URI url = constructUri(expression);
        HttpPost post = new HttpPost(url);
        
       // post.addHeader(jsessionid);
        post.addHeader("session-ID", jsessionid);
        System.out.println(post);
        
//        CloseableHttpResponse response = client.execute(post);
        
        return response.getStatusLine().getStatusCode();

    }

    private static URI constructUri(String expression) throws Exception {

        URI url = null;
        try {
            url = new URIBuilder()
                    .setScheme("http")
                    .setHost("localhost")
                    .setPort(port)
                    .setPath(path + expression )
                   
                    
                    //.setParameter("expression", expression)
                    .build();
        } catch (URISyntaxException ex) {
            throw new Exception("Error creating service URI", ex);
        }
        return url;
    }
    
    
//    private CloseableHttpResponse execute(HttpPost post, CookieStore cookieStore) throws IOException {
//        HttpClientConnectionManager connectionManager = null;
//    try (CloseableHttpClient client = HttpClientBuilder
//            .create()
//            .useSystemProperties()
//            .setConnectionManager(connectionManager)
//            .setDefaultCookieStore(cookieStore)
//            .setConnectionManagerShared(true)
//            .setDefaultSocketConfig(
//                    SocketConfig.custom()
//                            .setSoTimeout(SO_TIMEOUT)
//                            .setTcpNoDelay(true)
//                            .build())
//            .build()) {
//        CloseableHttpResponse response = client.execute(post);
//        LOGGER.debug("Post response:\n"
//                + "code: {}\n"
//                + "headers: {}", response.getStatusLine().getStatusCode(), response.getAllHeaders());
//
//        return response;
//    }
//}
}
