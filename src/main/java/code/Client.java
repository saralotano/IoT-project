package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.json.JSONObject;

public class Client {
		
	protected void doGETRequest() throws IOException{
		URI uri = null;

		try{
			uri = new URI("coap://localhost"); //Proxy Server
		} catch (Exception e) {
 			System.err.println("Caught Exception: " + e.getMessage());
		}	 
	
        CoapClient client = new CoapClient();

        while(true){
        	       	
        	BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in)); 
        	
        	System.out.println("Insert resource URI: (e.g. coap://localhost/temperature_02)");
        	String input = stdin.readLine();
        	
        	if(input.equals("exit")){
        		System.out.println("Client terminated");
        		System.exit(0);
        	}       	
        	else{
        		
        		if(input.split("/").length == 4) { //resource uri pattern has been respected
                		
            		String resourceName = input.split("/")[3]; //take only the last part of the URI. For example: "temperature_02" 
                    client.setURI(uri +"/"+resourceName);
                    CoapResponse response = client.get(); //send GET request to proxy server
                    
                    if(response.isSuccess()){
                    	
                    	JSONObject json = new JSONObject(response.getResponseText()); //Convert text to JSON object
            			System.out.println("Resource: \n" + json.toString(4));
                    }
                    
                    else if(response.getCode().name().toString().equals("NOT_FOUND")){
                    	                   	                    	
                    	System.out.println("ERROR: Resource " + response.getCode().name() );
                    	                   	
                	}
                	else{
                		System.out.println("ERROR " + response.getCode().name() + ". Try again.");
                	}
                	
            	}
        	}
        }
	}
	
}
