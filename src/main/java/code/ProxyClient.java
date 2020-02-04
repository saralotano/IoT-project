package code;

import java.time.LocalDateTime;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.CoAP.Code;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.coap.Request;


public class ProxyClient {
	
	private static CoapClient client = new CoapClient();
	
			
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
	        			MainClass.cache.put(resourceName, info);
	        			
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
	        		//System.err.println("-Failed--------");
	        	}
	        	
	
	        };
	        	        	        
	        startConnections(handler);
				
		}

	
	private static void startConnections(CoapHandler handler){
		Request request1 = new Request(Code.GET);
		request1.setURI(Config.IPv6_SERVER_1);
		request1.setObserve();
		//provare la riga successiva
		request1.getOptions().setContentFormat(MediaTypeRegistry.APPLICATION_JSON).setAccept(MediaTypeRegistry.APPLICATION_JSON);
		client.observe(request1, handler);
		
		Request request2 = new Request(Code.GET);
		request2.setURI(Config.IPv6_SERVER_2);
		request2.setObserve();
		client.observe(request2, handler);
		
		Request request3 = new Request(Code.GET);
		request3.setURI(Config.IPv6_SERVER_3);
		request3.setObserve();
		client.observe(request3, handler);
		
		Request request4 = new Request(Code.GET);
		request4.setURI(Config.IPv6_SERVER_4);
		request4.setObserve();
		client.observe(request4, handler);

		Request request5 = new Request(Code.GET);
		request5.setURI(Config.IPv6_SERVER_5);
		request5.setObserve();
		client.observe(request5, handler);
		
		Request request6 = new Request(Code.GET);
		request6.setURI(Config.IPv6_SERVER_6);
		request6.setObserve();
		client.observe(request6, handler);
		
		Request request7 = new Request(Code.GET);
		request7.setURI(Config.IPv6_SERVER_7);
		request7.setObserve();
		client.observe(request7, handler);
		
		Request request8 = new Request(Code.GET);
		request8.setURI(Config.IPv6_SERVER_8);
		request8.setObserve();
		client.observe(request8, handler);
		
		Request request9 = new Request(Code.GET);
		request9.setURI(Config.IPv6_SERVER_9);
		request9.setObserve();
		client.observe(request9, handler);
		
		Request request10 = new Request(Code.GET);
		request10.setURI(Config.IPv6_SERVER_10);
		request10.setObserve();
		client.observe(request10, handler);
		
		Request request11 = new Request(Code.GET);
		request11.setURI(Config.IPv6_SERVER_11);
		request11.setObserve();
		client.observe(request11, handler);
		
		Request request12 = new Request(Code.GET);
		request12.setURI(Config.IPv6_SERVER_12);
		request12.setObserve();
		client.observe(request12, handler);
		
		Request request13 = new Request(Code.GET);
		request13.setURI(Config.IPv6_SERVER_13);
		request13.setObserve();
		client.observe(request13, handler);
		
		Request request14 = new Request(Code.GET);
		request14.setURI(Config.IPv6_SERVER_14);
		request14.setObserve();
		client.observe(request14, handler);

		Request request15 = new Request(Code.GET);
		request15.setURI(Config.IPv6_SERVER_15);
		request15.setObserve();
		client.observe(request15, handler);
		
		Request request16 = new Request(Code.GET);
		request16.setURI(Config.IPv6_SERVER_16);
		request16.setObserve();
		client.observe(request16, handler);
		
		Request request17 = new Request(Code.GET);
		request17.setURI(Config.IPv6_SERVER_17);
		request17.setObserve();
		client.observe(request17, handler);
		
		Request request18 = new Request(Code.GET);
		request18.setURI(Config.IPv6_SERVER_18);
		request18.setObserve();
		client.observe(request18, handler);
		
		Request request19 = new Request(Code.GET);
		request19.setURI(Config.IPv6_SERVER_19);
		request19.setObserve();
		client.observe(request19, handler);
		
		Request request20 = new Request(Code.GET);
		request20.setURI(Config.IPv6_SERVER_20);
		request20.setObserve();
		client.observe(request20, handler);
		
		Request request21 = new Request(Code.GET);
		request21.setURI(Config.IPv6_SERVER_21);
		request21.setObserve();
		client.observe(request21, handler);
		
		Request request22 = new Request(Code.GET);
		request22.setURI(Config.IPv6_SERVER_22);
		request22.setObserve();
		client.observe(request22, handler);
		
		Request request23 = new Request(Code.GET);
		request23.setURI(Config.IPv6_SERVER_23);
		request23.setObserve();
		client.observe(request23, handler);
		
		Request request24 = new Request(Code.GET);
		request24.setURI(Config.IPv6_SERVER_24);
		request24.setObserve();
		client.observe(request24, handler);

		Request request25 = new Request(Code.GET);
		request25.setURI(Config.IPv6_SERVER_25);
		request25.setObserve();
		client.observe(request25, handler);
		
		Request request26 = new Request(Code.GET);
		request26.setURI(Config.IPv6_SERVER_26);
		request26.setObserve();
		client.observe(request26, handler);
		
		Request request27 = new Request(Code.GET);
		request27.setURI(Config.IPv6_SERVER_27);
		request27.setObserve();
		client.observe(request27, handler);
		
		Request request28 = new Request(Code.GET);
		request28.setURI(Config.IPv6_SERVER_28);
		request28.setObserve();
		client.observe(request28, handler);
		
		Request request29 = new Request(Code.GET);
		request29.setURI(Config.IPv6_SERVER_29);
		request29.setObserve();
		client.observe(request29, handler);
		
		Request request30 = new Request(Code.GET);
		request30.setURI(Config.IPv6_SERVER_10);
		request30.setObserve();
		client.observe(request30, handler);
		
	}
	
	
	
}
