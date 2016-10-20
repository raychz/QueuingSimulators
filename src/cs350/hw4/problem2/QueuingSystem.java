package cs350.hw4.problem2;

/**
 * <p>
 * Boston University CS350
 * <p>
 * HW4.2: Multi-Server Network Simulator
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
