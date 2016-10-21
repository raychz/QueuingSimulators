package cs350.hw4.problem2.utilities;

import cs350.hw4.problem2.Controller;

public class Logger {
	
	// Variables to monitor the state of the system.
	public int numArrivals, numDepartures, numMonitors;
	public double TqTotal, TwTotal, TsTotal, rhoTotal, wTotal, qTotal;
	private Controller c;
	
	public Logger(Controller c) {
		this.c = c;
	}


	public void printStatistics() {
		System.out.printf("-- Simulation %s Complete --\n\n", c.getSystemName());
		System.out.println("System Parameters:");
		//System.out.printf("Lambda: %.3f \t Ts: %.3f \t Sim. Time: %d \n \n", lambda, Ts, c.getSimulationTime());

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
