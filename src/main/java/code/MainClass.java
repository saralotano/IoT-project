package code;

import java.io.IOException;
import java.util.HashMap;

import org.eclipse.californium.core.CoapServer;

public class MainClass {
	
	protected static HashMap<String, ServerResource> cache = new HashMap<String, ServerResource>();
	protected static CoapServer server = new CoapServer(); //used to handle GET requests from Client
	protected static Proxy client = new Proxy(); //used to observe CoapServer resources
	protected static Client coapClient = new Client(); //used to do GET requests
	
	public static void main(String args[]) throws IOException{
		
		System.out.println("STARTING PROXY - CLIENT SIDE");		
		client.start(); 
		
		System.out.println("STARTING PROXY - SERVER SIDE");
		server.start();	
		
		System.out.println("STARTING COAP CLIENT");
		coapClient.doGETRequest();
				
	}
}
