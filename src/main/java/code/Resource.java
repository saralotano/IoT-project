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
		
		JSONObject json = new JSONObject();			
		json.put("n", info.getName());
		json.put("v", info.getValue());
 
		return json.toString();

	}
	
	
	public void handleGET(CoapExchange exchange) {
		
		String resource = (new JSONObject(exchange.getRequestOptions().toString())).getString("Uri-Path"); //resource format is "temperature_xx"		
		String requestedResource = retrieveResource(resource.split("_")[1]); //retrieveResource only needs the last 2 characters
	
		Response response = new Response(ResponseCode.CONTENT);
		response.getOptions().setContentFormat(MediaTypeRegistry.APPLICATION_JSON);
		response.setPayload(requestedResource);
		exchange.respond(response);
	}
	
}
