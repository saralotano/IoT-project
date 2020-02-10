package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.CoAP.Code;
import org.eclipse.californium.core.coap.Request;
import org.json.JSONObject;

public class Client {
	
	
	public static void main(String args[]) throws IOException {

		URI uri = null;

		try{
			uri = new URI("coap://localhost"); //proxy server
		} catch (Exception e) {
 			System.err.println("Caught Exception: " + e.getMessage());
		}	 
	
        CoapClient client = new CoapClient(uri);

        while(true){
        	       	
        	BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in)); 
        	
        	System.out.println("Insert resource URI: (e.g. coap://localhost/temperature_02)");
            //String requestedResource = stdin.readLine();
            String resourceName = stdin.readLine().split("/")[3]; //considero solamente "temperature_02"
            System.out.println("nome " + resourceName);
            
            Request req = new Request(Code.GET);
            req.getOptions().addUriPath(resourceName);                      
            CoapResponse response = client.advanced(req);	//send GET request to proxy server
                    	
        	if (response!=null) {
    			
    			if(response.getCode().toString().equals("2.05")){ //ok			
    				
    				JSONObject json = new JSONObject(response.getResponseText()); // Convert text to object
        			System.out.println("Resource: \n" + json.toString(4)); //4 è lo spazio di indentazione
        			
    			}
    			else{
    				System.out.println("ERROR. Something wrong happened. \n");
    			}
    					
    		} 
        	
        	else {
    			System.out.println("No response received. \n");
    		}
            	          
        }
	}
	
}
