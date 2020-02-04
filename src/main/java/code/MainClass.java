package code;

import java.util.HashMap;

public class MainClass {
	
	protected static HashMap<String, ResourceInfo> cache = new HashMap<String, ResourceInfo>();
	protected static ProxyServer server = new ProxyServer();
	
	public static void main(String args[]){
		
		//------------------------SERVER--------------------------------------
		//Resource r = new Resource("prova");
		//ResourceInfo info = new ResourceInfo(23.4, LocalDateTime.now(), "Cel");
		
		//cache.put(r.getName(), info);
						
		//server.add(r);		
		server.start();
		
		//------------------------CLIENT--------------------------------------
		ProxyClient.startObservation();
		
	}
}
