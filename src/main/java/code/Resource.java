package code;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class Resource extends CoapResource{
	
	public Resource (String name){
		super(name);
		//setObservable(true);
	}
	
	
	public void handleGET (CoapExchange exchange) {
		ProxyServer.handleGET(exchange);
	}
	
}
