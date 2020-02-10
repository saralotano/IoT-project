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
		//setObservable(true);
	}
	
	private static String parseRequest(String request){
		
		JSONObject obj = new JSONObject(request);
		System.out.println("uri - path " + obj.getString("Uri-Path"));
		return  obj.getString("Uri-Path");

	}
	
	private static String createGETResponse(String key){
		
		ServerResource info = MainClass.cache.get(key);
		
		//controllare errori json, controllare se info restituisce un valore null
		
		JSONObject json = new JSONObject();
		
		json.put("n", info.getName());
		//json.put("t", info.getTime());
		json.put("v", info.getValue());
		
		return json.toString();
	}
	
	public void handleGET (CoapExchange exchange) {
		//ProxyServer.handleGET(exchange);		
		
		//l'output è questo {"Uri-Host":"localhost", "Uri-Path":"prova"}
		String resource = parseRequest(exchange.getRequestOptions().toString());
		System.out.println("Requested resource " + resource);
		
		//String hashkey = resource.split("_")[1]; //in questo modo prendo il numero della risorsa. Ad esempio: "02"
		
		String msg = createGETResponse(resource.split("_")[1]); //in questo modo prendo il numero della risorsa. Ad esempio: "02"		
	
		//gestire il caso in cui la msg dia un errore. o lo faccio adesso o lo faccio nella createGET response
		
		//creo la risposta solo se ho qualcosa da inviare
		Response response = new Response(ResponseCode.CONTENT);
		response.getOptions().setContentFormat(MediaTypeRegistry.APPLICATION_JSON);
		response.setPayload(msg);
		exchange.respond(response);
	}
	
}
