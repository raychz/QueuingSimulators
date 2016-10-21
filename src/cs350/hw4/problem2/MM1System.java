package cs350.hw4.problem2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import cs350.hw4.problem2.events.Birth;
import cs350.hw4.problem2.events.Monitor;

/**
 * <p>
 * Boston University CS350
 * <p>
 * HW4.2: Multi-Server Network Simulator
 * 
 * @author Raymond Chavez {@literal <rchavez9@bu.edu>}
 */
public class MM1System extends QueuingSystem {
	public final double lambda;
	public final double Ts;
	protected final Queue<Request> requestQueue;
	private final Controller c;

	// Variables to monitor the state of the system.
	protected int numArrivals, numDepartures, numMonitors;
	protected double TqTotal, TwTotal, TsTotal, rhoTotal, wTotal, qTotal;

	public MM1System(String systemName, Controller c, double lambda, double Ts) {
		super(systemName);
		//this.simulationTime = simulationTime;
		this.lambda = lambda;
		this.Ts = Ts;
		this.requestQueue = new LinkedList<Request>();
		this.c = c;

		// Initialize new MM1System with a birth and monitor event.
		this.c.addEvent(new Birth(c, this));
		this.c.addEvent(new Monitor(c, this));
	}

	public void printStatistics() {
		System.out.printf("-- Simulation %s Complete --\n\n", systemName);
		System.out.println("System Parameters:");
		System.out.printf("Lambda: %.3f \t Ts: %.3f \t Sim. Time: %d \n \n", lambda, Ts, c.getSimulationTime());

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
}