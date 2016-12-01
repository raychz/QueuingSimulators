package cs350.hw4.problem2;

import cs350.hw4.problem2.utilities.Logger;

public class State {
	private MM2System CPU;
	private MM1System disk;
	private MM1System network;
	private String stateName;
	private double simulationTime;
	public final Logger logger;
	
	public State(String stateName, double simulationTime, MM2System CPU, MM1System disk, MM1System network) {
		this.stateName = stateName;
		this.simulationTime = simulationTime;
		this.logger = new Logger(this);
		this.CPU = CPU;
		this.disk = disk;
		this.network = network;
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
		return this.stateName;
	}

	public void log(String string) {
		logger.log(string);
	}

	public double getSimulationTime() {
		return this.simulationTime;
	}
}
