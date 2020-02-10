package code;

import org.eclipse.californium.core.CoapObserveRelation;

public class ServerResource {
	
	private CoapObserveRelation relation;
	private Integer value;
	private String name;
	
	public ServerResource(CoapObserveRelation r){
		relation = r;
		value = -1;
		name = "";
	}
		
	protected CoapObserveRelation getRelation(){
		return this.relation;
	}
		
	protected Integer getValue(){
		return this.value;
	}
	
	protected String getName(){
		return this.name;
	}
	
	protected void setRelation(CoapObserveRelation r){
		this.relation = r;
	}
	
	protected void setName(String n){
		this.name = n;
	}
	
	protected void setValue(Integer d){
		this.value = d;
	}

}
