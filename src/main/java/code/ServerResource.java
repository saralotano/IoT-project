package code;

import org.eclipse.californium.core.CoapObserveRelation;

public class ServerResource {
	
	private CoapObserveRelation relation;
	private String name;
	private Integer value;
	//private LocalDateTime time;
	
	public ServerResource(CoapObserveRelation r){
		relation = r;
		value = -1;
		name = "";
		//time = null;
	}
	
	/*
	public ServerResource(Integer v, LocalDateTime localDateTime){
		value = v;
		time = localDateTime;
	}
	*/
		
	protected CoapObserveRelation getRelation(){
		return this.relation;
	}
	
	protected Integer getValue(){
		return this.value;
	}
	
	protected String getName(){
		return this.name;
	}
	
	/*
	protected LocalDateTime getTime(){
		return this.time;
	}
	*/
	
	protected void setRelation(CoapObserveRelation r){
		this.relation = r;
	}
	
	protected void setName(String n){
		this.name = n;
	}
	
	protected void setValue(Integer d){
		this.value = d;
	}
	
	/*
	protected void setTime(LocalDateTime t){
		this.time = t;
	}
	*/
	
}
