package cs350.hw4.problem2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import cs350.hw4.problem2.events.Birth;
import cs350.hw4.problem2.events.CPUBirth;
import cs350.hw4.problem2.events.Monitor;

/**
 * <p>
 * Boston University CS350
 * <p>
 * HW4.2: Multi-Server Network Simulator
 * 
 * @author Raymond Chavez {@literal <rchavez9@bu.edu>}
 */
public class MM2System extends QueuingSystem {
	public final double lambda;
	public final double Ts;
	public final Queue<Request> requestQueue;
	private boolean isCPU1Busy = false;
	private boolean isCPU2Busy = false;
	//private final Controller c;
	
	public MM2System(String systemName, double lambda, double Ts) {
		super(systemName);
		//this.simulationTime = simulationTime;
		this.lambda = lambda;
		this.Ts = Ts;
		this.requestQueue = new LinkedList<Request>();
//		this.c = c;
//
//		// Initialize new MM2System with a birth and monitor event.
//		this.c.addEvent(new CPUBirth(c, this));
//		this.c.addEvent(new Monitor(c, this));
	}

	public boolean isCPU1Busy() {
		return isCPU1Busy;
	}

	public void setCPU1Busy(boolean isCPU1Busy) {
		this.isCPU1Busy = isCPU1Busy;
	}

	public boolean isCPU2Busy() {
		return isCPU2Busy;
	}

	public void setCPU2Busy(boolean isCPU2Busy) {
		this.isCPU2Busy = isCPU2Busy;
	}
}