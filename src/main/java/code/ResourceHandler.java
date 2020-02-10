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
				hashkey = uri.substring(14); //it will be a string representing one number between "02" and "1a" 
				value = Integer.parseInt(json.get("v").toString());
				
				ServerResource info = MainClass.cache.get(hashkey);
				
				if(info.getValue() == -1){ //it can be equal to -1 only if the server has never sent a data					
					info.setValue(value);
					info.setName(uri);	
					
					//Add a new resource to the proxy server.
        			Resource newResource = new Resource("temperature_" + hashkey);       			
        			MainClass.server.add(newResource);        			
        			System.out.println("New resource inserted: temperature_" + hashkey);
        		}
        		
        		else if(info.getValue() != value){ //Update old value
    				//System.out.println("Update value from " + info.getValue() + " to " + value);
    				info.setValue(value);			        			
        			MainClass.cache.replace(hashkey, info);
        		}
								
			} catch (ParseException e) {				
				System.err.println("ERROR: Json format not correct");
			}
    		
		} else {
			System.out.println("ERROR CODE: " + response.getCode().toString() + "\n" + response.getResponseText());
		}
		
	}

	
	public void onError() {
		System.err.println("-----Failed observation------");
		//Come va gestita?		
	}

}
