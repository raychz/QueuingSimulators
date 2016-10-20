package cs350.hw3.problem1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * <p>
 * Boston University CS350
 * <p>
 * HW3.1: M/M/1 Simulator
 * 
 * @author Raymond Chavez {@literal <rchavez9@bu.edu>}
 */
public class MM1System extends QueuingSystem {
	protected final double lambda;
	protected final double Ts;
	protected final Queue<Request> requestQueue;
	private final Controller c;

	// Variables to monitor the state of the system.
	protected int numArrivals, numDepartures, numMonitors;
	protected double TqTotal, TwTotal, TsTotal, rhoTotal, wTotal, qTotal;

	public MM1System(String systemName, Controller c, int simulationTime, double lambda, double Ts) {
		this.systemName = systemName;
		this.simulationTime = simulationTime;
		this.lambda = lambda;
		this.Ts = Ts;
		this.requestQueue = new LinkedList<Request>();
		this.c = c;

		// Initialize new MM1System with a birth and monitor event.
		this.c.addEvent(new Birth(c, this));
		this.c.addEvent(new Monitor(c, this));
	}

	@Override
	public void printStatistics() {
		System.out.printf("-- Simulation %s Complete --\n\n", systemName);
		System.out.println("System Parameters:");
		System.out.printf("Lambda: %.3f \t Ts: %.3f \t Sim. Time: %d \n \n", lambda, Ts, simulationTime);

		System.out.println("Arrivals:\t " + numArrivals);
		System.out.println("Departures:\t " + numDepartures);
		System.out.println("Monitors:\t " + numMonitors);

		System.out.println("\nw: \t" + wTotal / numMonitors);
		System.out.println("q: \t" + qTotal / numMonitors);
		System.out.println("Tw: \t" + TwTotal / numDepartures);
		System.out.println("Ts: \t" + TsTotal / numDepartures);
		System.out.println("Tq: \t" + TqTotal / numDepartures);
		System.out.println("Rho: \t" + rhoTotal / numDepartures);
		System.out.println("*********************************");
		System.out.println();
	}

	public double genExpRV(double lambda) {
		Random r = new Random();
		double y = r.nextDouble();
		return -(Math.log(1.0 - y)) / lambda;
	}
}