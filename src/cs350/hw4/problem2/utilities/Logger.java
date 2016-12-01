package cs350.hw4.problem2.utilities;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import cs350.hw4.problem2.Controller;
import cs350.hw4.problem2.Request;
import cs350.hw4.problem2.State;

public class Logger {
	private String stateName;
	private State state;
	private PrintWriter writer;

	// Variables to monitor the state of the entire system.
	public int numSysArrivals, numSysDepartures, numSysMonitors;
	public double SysTqTotal, SysTwTotal, SysTsTotal, SysWTotal, SysQTotal;

	// Variables to monitor the state of the CPU.
	public int numCPUArrivals, numCPUDepartures;
	public double CPUTqTotal, CPUTwTotal, CPUTsTotal, CPUWTotal, CPUQTotal, CPURhoTotal;

	// Variables to monitor the state of the Disk.
	public int numDiskArrivals, numDiskDepartures;
	public double DiskTqTotal, DiskTwTotal, DiskTsTotal, DiskWTotal, DiskQTotal, DiskRhoTotal;

	// Variables to monitor the state of the Network.
	public int numNetworkArrivals, numNetworkDepartures;
	public double NetworkTqTotal, NetworkTwTotal, NetworkTsTotal, NetworkWTotal, NetworkQTotal, NetworkRhoTotal;

	
	public Logger(State state) {
		this.state = state;
		this.stateName = state.getStateName();
		try {
			writer = new PrintWriter(this.stateName + ".csv", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public void printStatistics() {
		System.out.printf("-- Simulation %s Complete --\n\n", stateName);
		System.out.println("System Parameters:");
		System.out.printf("Lambda: %.3f \t Sim. Time: %f \n \n", state.getCPU().getLambda(), state.getSimulationTime());
		
		// System-wide stats
		System.out.println("System Arrivals:\t " + numSysArrivals);
		System.out.println("System Departures:\t " + numSysDepartures);
		System.out.println("System Monitors:\t " + numSysMonitors);
		
		SysWTotal = CPUWTotal + NetworkWTotal + DiskWTotal;
		SysQTotal = CPUQTotal + NetworkQTotal + DiskQTotal;
		SysTwTotal = CPUTwTotal + NetworkTwTotal + DiskTwTotal;
		SysTsTotal = CPUTsTotal + NetworkTsTotal + DiskTsTotal;
		SysTqTotal = CPUTqTotal + NetworkTqTotal + DiskTqTotal;
		System.out.println("\nSystem w: \t" + SysWTotal / numSysMonitors);
		System.out.println("System q: \t" + SysQTotal / numSysMonitors);
		System.out.println("System Tw: \t" + SysTwTotal / numSysDepartures);
		System.out.println("System Ts: \t" + SysTsTotal / numSysDepartures);
		System.out.println("System Tq: \t" + SysTqTotal / numSysDepartures);
		
		// CPU stats
		System.out.println("\nCPU Arrivals:\t " + numCPUArrivals);
		System.out.println("CPU Departures:\t " + numCPUDepartures);
		System.out.println("\nCPU w: \t" + CPUWTotal / numSysMonitors);
		System.out.println("CPU q: \t" + CPUQTotal / numSysMonitors);
		System.out.println("CPU Rho: \t" + CPURhoTotal / numSysMonitors);
		System.out.println("CPU Tw: \t" + CPUTwTotal / numCPUDepartures);
		System.out.println("CPU Ts: \t" + CPUTsTotal / numCPUDepartures);
		System.out.println("CPU Tq: \t" + CPUTqTotal / numCPUDepartures);
		
		// Disk stats
		System.out.println("\nDisk Arrivals:\t " + numDiskArrivals);
		System.out.println("Disk Departures:\t " + numDiskDepartures);
		System.out.println("\nDisk w: \t" + DiskWTotal / numSysMonitors);
		System.out.println("Disk q: \t" + DiskQTotal / numSysMonitors);
		System.out.println("Disk Rho: \t" + DiskRhoTotal / numSysMonitors);
		System.out.println("Disk Tw: \t" + DiskTwTotal / numDiskDepartures);
		System.out.println("Disk Ts: \t" + DiskTsTotal / numDiskDepartures);
		System.out.println("Disk Tq: \t" + DiskTqTotal / numDiskDepartures);
		
		// Network stats
		System.out.println("\nNetwork Arrivals:\t " + numNetworkArrivals);
		System.out.println("Network Departures:\t " + numNetworkDepartures);
		System.out.println("\nNetwork w: \t" + NetworkWTotal / numSysMonitors);
		System.out.println("Network q: \t" + NetworkQTotal / numSysMonitors);
		System.out.println("Network Rho: \t" + NetworkRhoTotal / numSysMonitors);
		System.out.println("Network Tw: \t" + NetworkTwTotal / numNetworkDepartures);
		System.out.println("Network Ts: \t" + NetworkTsTotal / numNetworkDepartures);
		System.out.println("Network Tq: \t" + NetworkTqTotal / numNetworkDepartures);
		
		//System.out.println("Rho: \t" + rhoTotal / numSysDepartures);
		System.out.println("*********************************");
		System.out.println();
		
		// Close csv file writer
		writer.close();
	}

	public void log(String s) {
		writer.println(s);
	}
}
