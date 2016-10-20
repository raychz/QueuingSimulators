package cs350.hw3.problem2;

/**
 * <p>
 * Boston University CS350
 * <p>
 * HW3.2: M/M/1/K Simulator
 * 
 * @author Raymond Chavez {@literal <rchavez9@bu.edu>}
 */
public abstract class QueuingSystem {
	protected int simulationTime;
	protected String systemName;

	public QueuingSystem() {
		this.simulationTime = 0;
	}

	public int getSimulationTime() {
		return simulationTime;
	}

	public abstract void printStatistics();
}
