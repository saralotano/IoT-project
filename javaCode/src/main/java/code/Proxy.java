package code;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.coap.CoAP.Code;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.coap.Request;


public class Proxy{
	
	private CoapClient client = new CoapClient();
	
	protected void start(){
		
		int sec = 1000; //only one observation request for second
		
		Request request;		
		ResourceHandler handler;
		ObservedResource resource;
		CoapObserveRelation relation;
		String key;
		
		
		for(int i = 0; i < Config.uri.size(); i++){
			
			request = new Request(Code.GET);
			request.setURI(Config.uri.get(i));
			request.setObserve();
			request.getOptions().setContentFormat(MediaTypeRegistry.APPLICATION_JSON).setAccept(MediaTypeRegistry.APPLICATION_JSON);
			
			
			//the key used in the cache will be a string representing the numbers between "02" and "1a" 
			if(i < 14) 
				key = "0"+Integer.toHexString(i+2);
			
			else 
				key = Integer.toHexString(i+2);
				
			handler = new ResourceHandler(key);
			relation = client.observe(request, handler);
			resource = new ObservedResource(relation, handler);
			MainClass.cache.put(key, resource);
			
			System.out.println("Sending observing request to "+ Config.uri.get(i));

			try {
				Thread.sleep(sec);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
			
	}
	
	protected void restartObservation(String resourceName){	
		//resourceName is a string representing one number between "02" and "1a" 
		String uri = "coap://[abcd::c30c:0:0:"+resourceName+"]:5683/temperature";
		System.out.println("Resending observe request to " + uri + ". Try again in a while.");
					
		Request request = new Request(Code.GET);
		request.setURI(uri);
		request.setObserve();
		request.getOptions().setContentFormat(MediaTypeRegistry.APPLICATION_JSON).setAccept(MediaTypeRegistry.APPLICATION_JSON);
		
		//update the resource with the new observe relation
		ObservedResource resource = MainClass.cache.get(resourceName);
		resource.setRelation(client.observe(request, resource.getHandler()));
		MainClass.cache.replace(resourceName, resource);	

	}
	
}
