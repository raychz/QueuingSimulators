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
	//protected int simulationTime;
	protected final String systemName;

	public QueuingSystem(String systemName) {
		this.systemName = systemName;
	}

	//public abstract void printStatistics();
}
