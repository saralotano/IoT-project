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
		
		//info != null means that it exists a CoapObserveRelation between the Proxy and the requested resource
		//info.getValue() == -1 means that the proxy has never received the first data for that resource 
		//in this case the proxy will send another observe request to the server exposing the requested resource
		if(info != null && info.getValue() == -1){
			
			info.getRelation().proactiveCancel();			
			MainClass.client.restartObservation(key);
			res = "Sending a new observe request. Please try after a while.";
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
		
		String resource = (new JSONObject(exchange.getRequestOptions().toString())).getString("Uri-Path"); //resource format is "temperature_xx"		
		String requestedResource = retrieveResource(resource.split("_")[1]); //retrieveResource only needs the last 2 characters
	
		Response response = new Response(ResponseCode.CONTENT);
		response.getOptions().setContentFormat(MediaTypeRegistry.APPLICATION_JSON);
		response.setPayload(requestedResource);
		exchange.respond(response);
	}
	
}
