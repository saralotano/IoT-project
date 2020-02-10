package code;

//import java.time.LocalDateTime;

import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ResourceHandler implements CoapHandler{

	public void onLoad(CoapResponse response) {
		//String resourceName = response.advanced().getSource().toString().substring(1); //il primo carattere è un /, con substring(1) lo rimuovo
		
		if(!response.advanced().isError()) {	//SARA: secondo me se si verifica un errore, viene chiamato il metodo onError        			
    		
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
				//System.out.println("cache content: hashkey "+ hashkey + " value " + info.getValue());
				
				if(info.getValue() == -1){ //il server ha inviato per la prima volta la risorsa
					
					info.setValue(value);
					info.setName(uri);
					//info.setTime(LocalDateTime.now());
					//info = new ServerResource(value, LocalDateTime.now());			        			
        			//MainClass.cache.put(uri, info);			        			
        			Resource newResource = new Resource("temperature_" + hashkey);
        			// Add a new resource to the server. If the client ask for it now is available
        			MainClass.server.add(newResource);
        			
        			System.out.println("New resource inserted in temperature_" + hashkey + " of value " + info.getValue());
        			//System.out.println("Resource uri " + uri);
        			//System.out.println("Resource value " + info.getValue());
        		}
        		
        		else{ 
        			
        			if(info.getValue() != value){ //Update value
        				System.out.println("Update value from " + info.getValue() + " to " + value);
        				info.setValue(value);
	        			//info.setTime(LocalDateTime.now());			        			
        			}
        			/*
        			else {
        				System.out.println("Update timestamp");
	        			info.setTime(LocalDateTime.now());
	        		}
	        		*/
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
		//dobbiamo gestire questa cosa
		
	}

}
