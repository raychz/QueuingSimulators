package cs350.hw4.problem2;

public class State {
	private MM2System CPU;
	private MM1System disk;
	private MM1System network;
	private Controller c;
	private String stateName;
	
	public State(String stateName, MM2System CPU, MM1System disk, MM1System network) {
		this.stateName = stateName;
		this.CPU = CPU;
		this.disk = disk;
		this.network = network;
	}
	
	// Initialize the state of the systems
	public void init() {
		// create birth into CPU
	}
	
	public MM2System getCPU() {
		return this.CPU;
	}
	
	public MM1System getDisk() {
		return this.disk;
	}
	
	public MM1System getNetwork() {
		return this.network;
	}
	
	public String getStateName() {
		return this.getStateName();
	}
}
