package code;

import java.time.LocalDateTime;

public class ResourceInfo {
	private Double value;
	private LocalDateTime time;
	private String unit;
	
	public ResourceInfo(Double v, LocalDateTime localDateTime, String u){
		value = v;
		time = localDateTime;
		unit = u;
	}
	
	protected Double getValue(){
		return this.value;
	}
	
	protected LocalDateTime getTime(){
		return this.time;
	}
	
	protected String getUnit(){
		return this.unit;
	}
	
	protected void setValue(Double d){
		this.value = d;
	}
	
	protected void setTime(LocalDateTime t){
		this.time = t;
	}
	
	protected void setUnit(String u){
		this.unit = u;
	}
}
