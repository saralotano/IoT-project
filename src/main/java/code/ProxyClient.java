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
	        		if(!response.advanced().isError()) {
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
		        			// Add a new resource to the server. If the client ask for it now is available
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
	        		} else {
	        			System.out.println("ERROR CODE: "+response.getCode().toString()+"\n"+response.getResponseText());
	        		}
	        		
	        		
	        	}	
	        	
	        	public void onError(){
	        		System.err.println("-Failed--------");
	        	}
	        	
	
	        };
	        	        	        
	        startConnections(handler);
				
		}

	
	private static void startConnections(CoapHandler handler){
		int sec = 3000;
		Request request = new Request(Code.GET);
		for(int i = 0; i < Config.uri.size(); i++) {
			request.setURI(Config.uri.get(i));
			request.setObserve();
			request.getOptions().setContentFormat(MediaTypeRegistry.APPLICATION_JSON).setAccept(MediaTypeRegistry.APPLICATION_JSON);
			client.observe(request, handler);
			try {
				Thread.sleep(sec);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
		/*
		Request request1 = new Request(Code.GET);
		request1.setURI(Config.IPv6_SERVER_1);
		request1.setObserve();
		//provare la riga successiva
		request1.getOptions().setContentFormat(MediaTypeRegistry.APPLICATION_JSON).setAccept(MediaTypeRegistry.APPLICATION_JSON);
		try {
			client.observe(request1, handler);
			
			Thread.sleep(sec);
			Request request2 = new Request(Code.GET);
			request2.setURI(Config.IPv6_SERVER_2);
			request2.setObserve();
			client.observe(request2, handler);

			Thread.sleep(sec);
			Request request3 = new Request(Code.GET);
			request3.setURI(Config.IPv6_SERVER_3);
			request3.setObserve();
			client.observe(request3, handler);

			Thread.sleep(sec);
			Request request4 = new Request(Code.GET);
			request4.setURI(Config.IPv6_SERVER_4);
			request4.setObserve();
			client.observe(request4, handler);

			Thread.sleep(sec);	
			Request request5 = new Request(Code.GET);
			request5.setURI(Config.IPv6_SERVER_5);
			request5.setObserve();
			client.observe(request5, handler);

			Thread.sleep(sec);			
			Request request6 = new Request(Code.GET);
			request6.setURI(Config.IPv6_SERVER_6);
			request6.setObserve();
			client.observe(request6, handler);
			
			Thread.sleep(sec);			
			Request request7 = new Request(Code.GET);
			request7.setURI(Config.IPv6_SERVER_7);
			request7.setObserve();
			client.observe(request7, handler);

			Thread.sleep(sec);			
			Request request8 = new Request(Code.GET);
			request8.setURI(Config.IPv6_SERVER_8);
			request8.setObserve();
			client.observe(request8, handler);

			Thread.sleep(sec);			
			Request request9 = new Request(Code.GET);
			request9.setURI(Config.IPv6_SERVER_9);
			request9.setObserve();
			client.observe(request9, handler);

			Thread.sleep(sec);			
			Request request10 = new Request(Code.GET);
			request10.setURI(Config.IPv6_SERVER_10);
			request10.setObserve();
			client.observe(request10, handler);

			Thread.sleep(sec);			
			Request request11 = new Request(Code.GET);
			request11.setURI(Config.IPv6_SERVER_11);
			request11.setObserve();
			client.observe(request11, handler);

			Thread.sleep(sec);			
			Request request12 = new Request(Code.GET);
			request12.setURI(Config.IPv6_SERVER_12);
			request12.setObserve();
			client.observe(request12, handler);

			Thread.sleep(sec);			
			Request request13 = new Request(Code.GET);
			request13.setURI(Config.IPv6_SERVER_13);
			request13.setObserve();
			client.observe(request13, handler);

			Thread.sleep(sec);			
			Request request14 = new Request(Code.GET);
			request14.setURI(Config.IPv6_SERVER_14);
			request14.setObserve();
			client.observe(request14, handler);

			Thread.sleep(sec);	
			Request request15 = new Request(Code.GET);
			request15.setURI(Config.IPv6_SERVER_15);
			request15.setObserve();
			client.observe(request15, handler);

			Thread.sleep(sec);			
			Request request16 = new Request(Code.GET);
			request16.setURI(Config.IPv6_SERVER_16);
			request16.setObserve();
			client.observe(request16, handler);

			Thread.sleep(sec);			
			Request request17 = new Request(Code.GET);
			request17.setURI(Config.IPv6_SERVER_17);
			request17.setObserve();
			client.observe(request17, handler);

			Thread.sleep(sec);			
			Request request18 = new Request(Code.GET);
			request18.setURI(Config.IPv6_SERVER_18);
			request18.setObserve();
			client.observe(request18, handler);

			Thread.sleep(sec);			
			Request request19 = new Request(Code.GET);
			request19.setURI(Config.IPv6_SERVER_19);
			request19.setObserve();
			client.observe(request19, handler);

			Thread.sleep(sec);			
			Request request20 = new Request(Code.GET);
			request20.setURI(Config.IPv6_SERVER_20);
			request20.setObserve();
			client.observe(request20, handler);

			Thread.sleep(sec);			
			Request request21 = new Request(Code.GET);
			request21.setURI(Config.IPv6_SERVER_21);
			request21.setObserve();
			client.observe(request21, handler);
			
			Thread.sleep(sec);			
			Request request22 = new Request(Code.GET);
			request22.setURI(Config.IPv6_SERVER_22);
			request22.setObserve();
			client.observe(request22, handler);

			Thread.sleep(sec);			
			Request request23 = new Request(Code.GET);
			request23.setURI(Config.IPv6_SERVER_23);
			request23.setObserve();
			client.observe(request23, handler);

			Thread.sleep(sec);			
			Request request24 = new Request(Code.GET);
			request24.setURI(Config.IPv6_SERVER_24);
			request24.setObserve();
			client.observe(request24, handler);

			Thread.sleep(sec);	
			Request request25 = new Request(Code.GET);
			request25.setURI(Config.IPv6_SERVER_25);
			request25.setObserve();
			client.observe(request25, handler);
			/*
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
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	
	
	
}
