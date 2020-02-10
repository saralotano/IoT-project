package code;

import java.util.HashMap;

import org.eclipse.californium.core.CoapServer;

public class MainClass {
	
	protected static HashMap<String, ServerResource> cache = new HashMap<String, ServerResource>();
	protected static CoapServer server = new CoapServer(); //used to handle GET requests from Client
	protected static Proxy client = new Proxy(); //used to observe CoapServer resources
	
	public static void main(String args[]){
				
		client.start(); 		
		server.start();		
				
	}
}
