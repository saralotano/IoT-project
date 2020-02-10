package code;

import org.eclipse.californium.core.CoapServer;



public class ProxyServer extends CoapServer{
	
	

		
	/*
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
	*/
		

}
