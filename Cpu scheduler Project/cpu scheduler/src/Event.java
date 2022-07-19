
public class Event {

	private String processName;
	private int cpuBurst;
	private int priority;
	private int EventNum;
	
	public Event(int eventNum,String procssName, int cpuBurst, int priority){
		this.cpuBurst = cpuBurst;
		this.processName = procssName;
		this.priority = priority;
		this.EventNum = eventNum;
	}
	
	public String getProcessName() {
		return processName;
	}
	
	public int getCpuBurst() {
		return cpuBurst;
	}
	
	public int getpriority() {
		return priority;
	}
	public int getEventNum() {
		return EventNum;
	}
	
	public void setCpuBurst(Event event, int x) {
		event.cpuBurst = x;
	}
	
	
	
}
