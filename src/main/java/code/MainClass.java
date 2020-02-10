package code;

import java.util.HashMap;

import org.eclipse.californium.core.CoapServer;

public class MainClass {
	
	protected static HashMap<String, ServerResource> cache = new HashMap<String, ServerResource>();
	//protected static ProxyServer server = new ProxyServer(); //è inutile creare una nuova classe proxy server
	protected static CoapServer server = new CoapServer();
	protected static ProxyClient client = new ProxyClient();
	
	public static void main(String args[]){
		
		//-----------------------PROXY-CLIENT--------------------------------------
		
		client.start();

		//-----------------------PROXY-SERVER--------------------------------------
		
		server.start();		
				
	}
}
