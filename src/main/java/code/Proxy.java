package code;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.coap.CoAP.Code;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.coap.Request;


public class Proxy{
	
	private CoapClient client = new CoapClient();
	private ResourceHandler handler = new ResourceHandler();
	
	protected void start(){
		
		int sec = 1000; //inviamo una richiesta al secondo, in modo da non sovraccaricare la rete
		
		Request request;		
		ServerResource resource;
		
		for(int i = 0; i < Config.uri.size(); i++){
			
			request = new Request(Code.GET);
			request.setURI(Config.uri.get(i));
			request.setObserve();
			request.getOptions().setContentFormat(MediaTypeRegistry.APPLICATION_JSON).setAccept(MediaTypeRegistry.APPLICATION_JSON);
			
			resource = new ServerResource(client.observe(request, handler));
			
			if(i < 14){ 
				//aggiunge lo 0 davanti alle cifre tra 2 e f
				//in questo modo la chiave dell'hashmap sarà costituita sempre da due caratteri e sarà più facile fare il parse
				MainClass.cache.put("0"+Integer.toHexString(i+2), resource);
			}
			else
				MainClass.cache.put(Integer.toHexString(i+2), resource);
			
			
			try {
				Thread.sleep(sec);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
			
	}
	
	protected void restartObservation(String resourceName){
		String uri = "coap://[abcd::c30c:0:0:"+resourceName+"]:5683/temperature";
					
		Request request = new Request(Code.GET);
		request.setURI(uri);
		request.setObserve();
		request.getOptions().setContentFormat(MediaTypeRegistry.APPLICATION_JSON).setAccept(MediaTypeRegistry.APPLICATION_JSON);
		
		ServerResource resource = new ServerResource(client.observe(request, handler));
		MainClass.cache.put(resourceName, resource);
	}
	
}
