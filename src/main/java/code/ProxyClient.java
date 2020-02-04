package code;

import java.time.LocalDateTime;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapResponse;


public class ProxyClient {
		
	protected static void startObservation(){
		
		CoapHandler handler = new CoapHandler(){
			
	        	public void onLoad(CoapResponse response){
	        		String resourceName = response.advanced().getSource().toString().substring(1); //il primo carattere è un /, con substring(1) lo rimuovo
	        		ResourceInfo info = MainClass.cache.get(resourceName);
	        		        		
	        		JSONParser parser = new JSONParser();
	        		Double value = 0.0;
	        		try {
						JSONObject json = (JSONObject) parser.parse(response.getResponseText().toString());
						value = Double.parseDouble(json.get("value").toString());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	
	        		
	        		if(info == null){ //primo valore da memorizzare	        			
	        			info = new ResourceInfo(value, LocalDateTime.now(), "Cel"); //non so se Cel ha senso così hardcodato	        			
	        			MainClass.cache.put(response.advanced().getSource().toString(), info);
	        			
	        			Resource newResource = new Resource(resourceName);
	        			MainClass.server.add(newResource);
	        			
	        			System.out.println("Inserita nuova risorsa");
	        			System.out.println("Nome risorsa " + resourceName);
	        			System.out.println("Valore risorsa " + info.getValue());
	        		}
	        		
	        		else{ 
	        			if(info != null && !info.getValue().equals(response.getResponseText())){ //aggiorno il nuovo valore
	        				System.out.println("Aggiorno un nuovo valore");
	        				info.setValue(value);
		        			info.setTime(LocalDateTime.now());
		        			info.setUnit("Cel"); //non so se va bene così hardcodato
	        			
	        			}
	        			else {
	        				System.out.println("Aggiorno il timestamp del vecchio valore");
		        			info.setTime(LocalDateTime.now());
		        		}
	        			
	        			MainClass.cache.replace(response.advanced().getSource().toString(), info);
	        		}
	        		
	        	}	
	
	        	public void onError(){
	        		System.err.println("-Failed--------");
	        	}
	
	        };
		
	        startConnections(handler);
				
		}

	
	private static void startConnections(CoapHandler handler){
		new CoapClient(Config.PROTOCOL_NAME+"[" + Config.IPv6_SERVER_2 +"]:"+ Config.SERVER_PORT + Config.RESOURCE_PATH).observe(handler);
		new CoapClient(Config.PROTOCOL_NAME+"[" + Config.IPv6_SERVER_3 +"]:"+ Config.SERVER_PORT + Config.RESOURCE_PATH).observe(handler);
		new CoapClient(Config.PROTOCOL_NAME+"[" + Config.IPv6_SERVER_4 +"]:"+ Config.SERVER_PORT + Config.RESOURCE_PATH).observe(handler);
		new CoapClient(Config.PROTOCOL_NAME+"[" + Config.IPv6_SERVER_5 +"]:"+ Config.SERVER_PORT + Config.RESOURCE_PATH).observe(handler);
		new CoapClient(Config.PROTOCOL_NAME+"[" + Config.IPv6_SERVER_6 +"]:"+ Config.SERVER_PORT + Config.RESOURCE_PATH).observe(handler);
		new CoapClient(Config.PROTOCOL_NAME+"[" + Config.IPv6_SERVER_7 +"]:"+ Config.SERVER_PORT + Config.RESOURCE_PATH).observe(handler);
		new CoapClient(Config.PROTOCOL_NAME+"[" + Config.IPv6_SERVER_8 +"]:"+ Config.SERVER_PORT + Config.RESOURCE_PATH).observe(handler);
		new CoapClient(Config.PROTOCOL_NAME+"[" + Config.IPv6_SERVER_9 +"]:"+ Config.SERVER_PORT + Config.RESOURCE_PATH).observe(handler);
		new CoapClient(Config.PROTOCOL_NAME+"[" + Config.IPv6_SERVER_10 +"]:"+ Config.SERVER_PORT + Config.RESOURCE_PATH).observe(handler);
		new CoapClient(Config.PROTOCOL_NAME+"[" + Config.IPv6_SERVER_11 +"]:"+ Config.SERVER_PORT + Config.RESOURCE_PATH).observe(handler);
		new CoapClient(Config.PROTOCOL_NAME+"[" + Config.IPv6_SERVER_12 +"]:"+ Config.SERVER_PORT + Config.RESOURCE_PATH).observe(handler);
		new CoapClient(Config.PROTOCOL_NAME+"[" + Config.IPv6_SERVER_13 +"]:"+ Config.SERVER_PORT + Config.RESOURCE_PATH).observe(handler);
		new CoapClient(Config.PROTOCOL_NAME+"[" + Config.IPv6_SERVER_14 +"]:"+ Config.SERVER_PORT + Config.RESOURCE_PATH).observe(handler);
		new CoapClient(Config.PROTOCOL_NAME+"[" + Config.IPv6_SERVER_15 +"]:"+ Config.SERVER_PORT + Config.RESOURCE_PATH).observe(handler);
		new CoapClient(Config.PROTOCOL_NAME+"[" + Config.IPv6_SERVER_16 +"]:"+ Config.SERVER_PORT + Config.RESOURCE_PATH).observe(handler);
		new CoapClient(Config.PROTOCOL_NAME+"[" + Config.IPv6_SERVER_17 +"]:"+ Config.SERVER_PORT + Config.RESOURCE_PATH).observe(handler);
		new CoapClient(Config.PROTOCOL_NAME+"[" + Config.IPv6_SERVER_18 +"]:"+ Config.SERVER_PORT + Config.RESOURCE_PATH).observe(handler);
		new CoapClient(Config.PROTOCOL_NAME+"[" + Config.IPv6_SERVER_19 +"]:"+ Config.SERVER_PORT + Config.RESOURCE_PATH).observe(handler);
		new CoapClient(Config.PROTOCOL_NAME+"[" + Config.IPv6_SERVER_10 +"]:"+ Config.SERVER_PORT + Config.RESOURCE_PATH).observe(handler);
		new CoapClient(Config.PROTOCOL_NAME+"[" + Config.IPv6_SERVER_21 +"]:"+ Config.SERVER_PORT + Config.RESOURCE_PATH).observe(handler);
		new CoapClient(Config.PROTOCOL_NAME+"[" + Config.IPv6_SERVER_22 +"]:"+ Config.SERVER_PORT + Config.RESOURCE_PATH).observe(handler);
		new CoapClient(Config.PROTOCOL_NAME+"[" + Config.IPv6_SERVER_23 +"]:"+ Config.SERVER_PORT + Config.RESOURCE_PATH).observe(handler);
		new CoapClient(Config.PROTOCOL_NAME+"[" + Config.IPv6_SERVER_24 +"]:"+ Config.SERVER_PORT + Config.RESOURCE_PATH).observe(handler);
		new CoapClient(Config.PROTOCOL_NAME+"[" + Config.IPv6_SERVER_25 +"]:"+ Config.SERVER_PORT + Config.RESOURCE_PATH).observe(handler);
		new CoapClient(Config.PROTOCOL_NAME+"[" + Config.IPv6_SERVER_26 +"]:"+ Config.SERVER_PORT + Config.RESOURCE_PATH).observe(handler);
		new CoapClient(Config.PROTOCOL_NAME+"[" + Config.IPv6_SERVER_27 +"]:"+ Config.SERVER_PORT + Config.RESOURCE_PATH).observe(handler);
		new CoapClient(Config.PROTOCOL_NAME+"[" + Config.IPv6_SERVER_28 +"]:"+ Config.SERVER_PORT + Config.RESOURCE_PATH).observe(handler);
		new CoapClient(Config.PROTOCOL_NAME+"[" + Config.IPv6_SERVER_29 +"]:"+ Config.SERVER_PORT + Config.RESOURCE_PATH).observe(handler);
		new CoapClient(Config.PROTOCOL_NAME+"[" + Config.IPv6_SERVER_30 +"]:"+ Config.SERVER_PORT + Config.RESOURCE_PATH).observe(handler);
	
	}
	
	
	
}
