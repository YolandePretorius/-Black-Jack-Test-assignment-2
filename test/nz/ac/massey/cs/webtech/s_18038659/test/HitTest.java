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
public class HitTest {
    private static final String path = "assignment2_server_18038659/"; //TODO: Change to your setup
    private static int port = 8080;
    public CloseableHttpClient client = HttpClients.createDefault();
    
    public HitTest() {
    }
    
    @Before
    public  void setUpClass() {
    }
    
    @After
    public  void tearDownClass() {
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
     public void startHit() throws Exception{
         assertEquals(statusRequest("jack/start"), HttpStatus.SC_OK);
         Object hit = statusRequest("jack/move/hit");
         hit();
     }
     public void hit() throws Exception {
         //CloseableHttpClient client = HttpClients.createDefault();
         //assertEquals(statusRequest("jack/hit",client), HttpStatus.SC_NOT_FOUND);
         //assertEquals(statusRequest("jack/hit",client), HttpStatus.SC_NOT_FOUND); // if there is not active game
         //assertEquals(statusRequest("jack/start"), HttpStatus.SC_OK);
        // assertEquals(statusRequest("jack/hit"),statusRequest("jack/start"));
         for (int i = 0; i < 3; i++) {
             Object hit = statusRequest("jack/move/hit");
             System.out.println("hit");
             //assertEquals(HttpStatus.SC_OK,statusRequest("jack/move/hit"));
         }
         //assertEquals(HttpStatus.SC_OK,statusRequest("jack/move/hit"));
     }
     
     
     
     private Object statusRequest(String expression) throws IOException, Exception {
        //CloseableHttpClient client = HttpClients.createDefault();
        
        //HttpGet get = new HttpGet("http://localhost:8080/assignment2_server_18038659/jack/start");
       // HttpGet get = new HttpGet("http://localhost:8080/assignment2_server_18038659/jack/hit");
        
        URI url = constructUri(expression);
        HttpPost get = new HttpPost(url);
         System.out.println(get);
         CloseableHttpResponse response = null;
        try{
             response = client.execute(get);
        }catch(Exception e){
        int a =7;
        };
        int r = response.getStatusLine().getStatusCode();
        return r;
    }
     
     
     
     private static URI constructUri(String expression) throws Exception {

        URI url = null;
        try {
            url = new URIBuilder()
                    .setScheme("http")
                    .setHost("localhost")
                    .setPort(port)
                    .setPath(path+expression)
                    //.setParameter("expression", expression)
                    .build();
        } catch (URISyntaxException ex) {
            throw new Exception("Error creating service URI", ex);
        }
        return url;
    }
}
