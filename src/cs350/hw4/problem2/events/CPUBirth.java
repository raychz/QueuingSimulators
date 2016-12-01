package cs350.hw4.problem2.events;

import cs350.hw4.problem2.Controller;
import cs350.hw4.problem2.MM1System;
import cs350.hw4.problem2.MM2System;
import cs350.hw4.problem2.QueuingSystem;
import cs350.hw4.problem2.Request;
import cs350.hw4.problem2.State;
import cs350.hw4.problem2.utilities.RandomGenerator;

/**
 * <p>
 * Boston University CS350
 * <p>
 * HW4.2: Multi-Server Network Simulator
 * 
 * @author Raymond Chavez {@literal <rchavez9@bu.edu>}
 */
public class CPUBirth extends Event {
	private final Request r = new Request();
	private final MM2System CPU;
	private boolean isExternalArrival;

	public CPUBirth(Controller c, State s, boolean isExternalArrival) {
		super(c, s);
		this.CPU = s.getCPU();
		this.isExternalArrival = isExternalArrival;
		this.r.setIAT(isExternalArrival ? RandomGenerator.genExpRV(CPU.getLambda()) : 0);
		this.r.setArrival(c.getCurrentTime() + r.getIAT());
		this.r.setTs(RandomGenerator.genExpRV(1.0 / CPU.getTs()));
	}

	/**
	 * A function to handle a birth event (the arrival of a new customer to the
	 * queue).
	 * 
	 * @param e
	 */
	@Override
	public void exec() {
		/*
		 * Add this new request to queue of arrivals.
		 */
		CPU.requestQueue.add(this.r);

		if (c.getCurrentTime() >= s.getSimulationTime()) {
			s.logger.numCPUArrivals++;
			if (this.isExternalArrival) {
				s.logger.numSysArrivals++;
			}
		}

		/*
		 * If this request happens to be the only one in the system (i.e., the
		 * server was idle upon its birth) then we must "start service" for that
		 * request, which means that we can "predict" when service will be done
		 * (and hence schedule a death event), by generating a random
		 * "service time" according to the distribution of service times and
		 * adding that to the current time.
		 */
		if (!CPU.isCPU1Busy()) {
			double deathTime = c.getCurrentTime() + r.getTs();
			c.addEvent(new CPU1Death(c, s, deathTime));
			CPU.setCPU1Busy(true);
		}

		else if (!CPU.isCPU2Busy()) {
			double deathTime = c.getCurrentTime() + r.getTs();
			c.addEvent(new CPU2Death(c, s, deathTime));
			CPU.setCPU2Busy(true);
		}

		/*
		 * Predict (and hence schedule) when the next birth will occur. We can
		 * predict when the next birth will occur by generating a random
		 * "IAT time" according to the specified distribution of IAT times, and
		 * adding that to the current time. We do this, iff the birth currently
		 * being executed is an external arrival.
		 */
		if (this.isExternalArrival)
			c.addEvent(new CPUBirth(c, s, true));
	}

	@Override
	public double getTime() {
		return r.getArrival();
	}
}