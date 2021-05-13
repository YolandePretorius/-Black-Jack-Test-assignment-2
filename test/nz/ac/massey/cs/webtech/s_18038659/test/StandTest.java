/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.massey.cs.webtech.s_18038659.test;

import java.io.IOException;
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
 *After jack/start paly jack/stand and then try jack/hit this 
 * should give a 200 back as the turn has changed to the dealer and player is not able to hit again
 * @author 18038659
 */
public class StandTest {
    
    private static final String path = "assignment2_server_18038659/"; //TODO: Change to your setup
    private static int port = 8080;
    String sessionID;
    CloseableHttpClient client;
  // HashMap<String, String> allHeaders;
    CloseableHttpResponse response;
    
    
    public StandTest() {
    }
          
    @Before // create session and send session Id through to next servlette
    public void setUp() throws Exception {
        client = HttpClients.createDefault();
        
        URI url = constructUri("jack/start");
        HttpGet get = new HttpGet(url);
        response = client.execute(get);
        Header[] header = response.getHeaders("Set-Cookie");
        String arr[] = header[0].getValue().split(";");
        String sessionID = arr[0];
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
     public void Stand() throws Exception {
           

        assertEquals(statusRequest("jack/move/stand"), HttpStatus.SC_MOVED_TEMPORARILY); // check if 302 returned indicating stand is working and turn is changed to dealer also servlteet is being redirected to state servlette 
     }
    
    
    private Object statusRequest(String expression) throws IOException, Exception {
      
        URI url = constructUri(expression);
        HttpPost post = new HttpPost(url);
       
        post.addHeader("session-ID", sessionID);
        System.out.println(post);
        
        response = client.execute(post);
        
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
}
