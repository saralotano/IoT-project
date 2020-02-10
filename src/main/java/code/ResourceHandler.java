package code;

import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ResourceHandler implements CoapHandler{

	public void onLoad(CoapResponse response) {
		
		if(!response.advanced().isError()) {	     			
    		
    		JSONParser parser = new JSONParser();
    		Integer value = 0;
    		String uri;
    		String hashkey;
    		try {
				JSONObject json = (JSONObject) parser.parse(response.getResponseText().toString());
				uri = json.get("n").toString();
				hashkey = uri.substring(14); //estraggo gli ultimi due caratteri relativi all'ID del sensore
				value = Integer.parseInt(json.get("v").toString());
				
				ServerResource info = MainClass.cache.get(hashkey);
				
				if(info.getValue() == -1){ //il server ha inviato per la prima volta la risorsa
					
					info.setValue(value);
					info.setName(uri);			        			
        			Resource newResource = new Resource("temperature_" + hashkey);
        			// Add a new resource to the server. If the client ask for it now is available
        			MainClass.server.add(newResource);
        			
        			System.out.println("New resource inserted in temperature_" + hashkey + " of value " + info.getValue());
        		}
        		
        		else if(info.getValue() != value){ //Update value
    				System.out.println("Update value from " + info.getValue() + " to " + value);
    				info.setValue(value);			        			
        			MainClass.cache.replace(hashkey, info);
        		}
								
			} catch (ParseException e) {
				
				System.err.println("ERROR: Json format not correct");
			}
    		
		} else {
			System.out.println("ERROR CODE: "+response.getCode().toString()+"\n"+response.getResponseText());
		}
		
	}

	
	public void onError() {
		System.err.println("-Failed--------");
		//Come va gestita?
		
	}

}
