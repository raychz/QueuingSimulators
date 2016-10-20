package cs350.hw3.problem2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * <p>
 * Boston University CS350
 * <p>
 * HW3.2: M/M/1/K Simulator
 * 
 * @author Raymond Chavez {@literal <rchavez9@bu.edu>}
 */
public class MM1KSystem extends QueuingSystem {
	protected final double lambda;
	protected final double Ts;
	protected final int K;
	protected final Queue<Request> requestQueue;
	private final Controller c;

	// Variables to monitor the state of the system.
	protected int numArrivals, numDepartures, numMonitors, numAttemptedArrivals, numRejections;
	protected double TqTotal, TwTotal, TsTotal, rhoTotal, wTotal, qTotal;

	public MM1KSystem(String systemName, Controller c, int simulationTime, double lambda, double Ts, int K) {
		this.systemName = systemName;
		this.simulationTime = simulationTime;
		this.lambda = lambda;
		this.Ts = Ts;
		this.K = K;
		this.requestQueue = new LinkedList<Request>();
		this.c = c;

		// Initialize new MM1KSystem with a birth and monitor event.
		this.c.addEvent(new Birth(c, this));
		this.c.addEvent(new Monitor(c, this));
	}

	@Override
	public void printStatistics() {
		System.out.printf("-- Simulation %s Complete --\n\n", systemName);
		System.out.println("System Parameters:");
		System.out.printf("Lambda: %.3f \t Ts: %.3f \t Sim. Time: %d \t K: %d \n \n", lambda, Ts, simulationTime, K);

		System.out.println("Arrivals:\t " + numArrivals);
		System.out.println("Departures:\t " + numDepartures);
		System.out.println("Monitors:\t " + numMonitors);

		// System.out.println("\nw: \t\t" + wTotal/numMonitors);
		System.out.println("\nq: \t\t" + qTotal / numMonitors);
		// System.out.println("Tw: \t\t" + TwTotal/numDepartures);
		// System.out.println("Ts: \t\t" + TsTotal/numDepartures);
		System.out.println("Tq: \t\t" + TqTotal / numDepartures);
		// System.out.println("Tq: \t\t" +
		// (qTotal/numMonitors)/(lambda*(1.0-((double)numRejections/numAttemptedArrivals))));
		System.out.println("Rho: \t\t" + rhoTotal / numDepartures);
		double prSk = (double) numRejections / numAttemptedArrivals;
		double effLambda = lambda * (1.0 - prSk);
		System.out.println("Eff. Rho: \t" + effLambda * (TsTotal / numDepartures));
		System.out.println("Pr(Sk): \t" + prSk);
		System.out.println("*********************************");
		System.out.println();
	}

	public double genExpRV(double lambda) {
		Random r = new Random();
		double y = r.nextDouble();
		return -(Math.log(1.0 - y)) / lambda;
	}
}