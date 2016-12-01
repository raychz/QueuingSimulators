package cs350.hw4.problem2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

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
	private final double lambda;
	private final double Ts;
	public final Queue<Request> requestQueue;
	private boolean isMM1ServerBusy = false;

	public MM1System(String systemName, double lambda, double Ts) {
		super(systemName);
		this.lambda = lambda;
		this.Ts = Ts;
		this.requestQueue = new LinkedList<Request>();
	}

	// Used for a system with no external requests
	public MM1System(String systemName, double Ts) {
		super(systemName);
		this.lambda = Double.NEGATIVE_INFINITY;
		this.Ts = Ts;
		this.requestQueue = new LinkedList<Request>();
	}

	public double getLambda() throws Exception {
		if(lambda < 0.0) {
			String msg = "Attempting to use an exponentially distributed "
					+ "lambda in a system with no external arrivals.";
			throw new Exception(msg);
		}
		return lambda;
	}
	
	public double getTs() {
		return Ts;
	}

	public boolean isMM1ServerBusy() {
		return isMM1ServerBusy;
	}

	public void setMM1ServerBusy(boolean isMM1ServerBusy) {
		this.isMM1ServerBusy = isMM1ServerBusy;
	}
}