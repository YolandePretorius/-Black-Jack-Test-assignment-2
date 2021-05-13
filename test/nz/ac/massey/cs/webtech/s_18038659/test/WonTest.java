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
import java.util.HashMap;
import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 18038659
 */
public class WonTest {
    
    private static final String path = "assignment2_server_18038659/"; //TODO: Change to your setup
    private static int port = 8080;
    String sessionID;
    CloseableHttpClient client;
    HashMap<String, String> allHeaders;
    CloseableHttpResponse response;
    
    
    public WonTest() {
    }
          
    @Before // create session and send session Id through to next servlette
    public void setUp() throws Exception {
        client = HttpClients.createDefault();
        
        URI url = constructUri("jack/start");
        HttpGet get = new HttpGet(url);
        response = client.execute(get);
        Header[] header = response.getHeaders("Set-Cookie");
        String arr[] = header[0].getValue().split(";");
        sessionID = arr[0];
        //String jsessionidArray[] = arr[0].split("=");
        //jsessionid = jsessionidArray[1];
    }
    
    @After
    public void tearDown() {
    }
    
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void Won() throws Exception {
//         Object hit = statusRequest("jack/move/hit");
              
             
//
//         for (int i = 0; i < 20; i++) {
//             if(!hit.equals(HttpStatus.SC_BAD_REQUEST)){
//                  Object   hit2 = statusRequest("jack/move/hit");
//                    hit = hit2;
//             }
//         }
//         
         
         
        //assertEquals(hit, HttpStatus.SC_BAD_REQUEST); // check if 400 bad request is returned indicating its a bust
         //Object won = statusRequest("jack/won");
         boolean a = makeServiceRequest("jack/possiblemoves").contains("dealer");
       //  assertTrue(makeServiceRequest("jack/won").contains("dealer"));
     }
    
    
    private Object statusRequest(String expression) throws IOException, Exception {
      
        URI url = constructUri(expression);
        HttpPost post = new HttpPost(url);
       
        post.addHeader("session-ID", sessionID);
        System.out.println(post);
        
        response = client.execute(post);
      
        
        return response.getStatusLine().getStatusCode();

    }
    
     private Object statusRequest2(String expression) throws IOException, Exception {
      
        URI url = constructUri(expression);
        HttpGet get = new HttpGet(url);
        get.addHeader("session-ID", sessionID);
        //get.addHeader("session-ID", sessionID);
        System.out.println(get);
        
        response = client.execute(get);
        response.addHeader("session-ID", sessionID);
        
        return response.getStatusLine().getStatusCode();

    }
     
     private static String makeServiceRequest(String expression) throws Exception {
        
        try (CloseableHttpClient client = HttpClients.createDefault()) {
        
            URI url = constructUri(expression);
            HttpGet get = new HttpGet(url);
        
            try (CloseableHttpResponse response = client.execute(get)) {
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                   return getResponseBody(response.getEntity().getContent()); 
                }
                else{                
                    throw new Exception("Error response from service.");
		}
            }
        }
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

    
//    private String makeServiceRequest(String expression) throws Exception {
//        
//            client = HttpClients.createDefault();
//        
//            URI url = constructUri(expression);
//            HttpPost post = new HttpPost(url);
//            post.addHeader("session-ID", sessionID);
//            //get.addHeader("session-ID", sessionID);
//            response = client.execute(post);
////            try ( CloseableHttpResponse response = client.execute(post)) {
//                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//                   return getResponseBody(response.getEntity().getContent()); 
//                }
//                else{                
//                    throw new Exception("Error response from service.");
//		}
////            }
////        }
//    }
    
//    private static String getResponseBody(final InputStream stream) throws UnsupportedOperationException, IOException {
//
//        StringBuilder builder = new StringBuilder();
//
//        BufferedReader rd = new BufferedReader(new InputStreamReader(stream));
//        String line = "";
//        while ((line = rd.readLine()) != null) {
//            builder.append(line);
//        }
//        return builder.toString();
//    }
//    
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
}
