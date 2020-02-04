package code;

import java.util.HashMap;

import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.coap.Response;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;


public class ProxyServer extends CoapServer{
	
	
	protected static HashMap<String, String> cache = new HashMap<String, String>();
	
	private static String parseRequest(String request){
		
		JSONObject obj = new JSONObject(request);
		return  obj.getString("Uri-Path");

	}
	
	private static String createGETResponse(String resource){
		ResourceInfo info = MainClass.cache.get(resource);
		
		JSONObject json = new JSONObject();
		
		json.put("n", resource);
		json.put("u", info.getUnit());
		json.put("t", info.getTime());
		json.put("v", info.getValue());
		
		return json.toString();
	}
		
	
	protected static void handleGET (CoapExchange exchange) {
		Response response = new Response(ResponseCode.CONTENT);

		//l'output è questo {"Uri-Host":"localhost", "Uri-Path":"prova"}
		System.out.println("path risorsa " + parseRequest(exchange.getRequestOptions().toString()));
		String resource = parseRequest(exchange.getRequestOptions().toString());
		response.getOptions().setContentFormat(MediaTypeRegistry.APPLICATION_JSON);
				
		String msg = createGETResponse(resource);
		
		response.setPayload(msg);

		exchange.respond(response);
	}
		

}
