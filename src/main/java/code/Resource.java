package code;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.coap.Response;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;

public class Resource extends CoapResource{
	
	public Resource (String name){
		super(name);
	}
		
	private static String retrieveResource(String key){
		
		ServerResource info = MainClass.cache.get(key);
		String res;
		
		if(info != null && info.getValue() == -1){
			info.getRelation().proactiveCancel();			
			MainClass.client.restartObservation(key);
			res = "Starting new observation on the requested resource. Please try after a while.";
		}
		else{
			JSONObject json = new JSONObject();			
			json.put("n", info.getName());
			json.put("v", info.getValue());
			res = json.toString();
		}
		return res;
	}
	
	
	public void handleGET(CoapExchange exchange) {
		
		String resource = (new JSONObject(exchange.getRequestOptions().toString())).getString("Uri-Path");
		String requestedResource = retrieveResource(resource.split("_")[1]); //in questo modo prendo il numero della risorsa. Ad esempio: "02"		
	
		Response response = new Response(ResponseCode.CONTENT);
		response.getOptions().setContentFormat(MediaTypeRegistry.APPLICATION_JSON);
		response.setPayload(requestedResource);
		exchange.respond(response);
	}
	
}
