package cs350.hw4.problem2.events;

import cs350.hw4.problem2.Controller;
import cs350.hw4.problem2.MM1System;
import cs350.hw4.problem2.MM2System;
import cs350.hw4.problem2.QueuingSystem;
import cs350.hw4.problem2.Request;
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
	private Request r = new Request();
	private MM2System m;

	public CPUBirth(Controller c, MM2System m) {
		super(c);
		this.m = m;
		r.setIAT(RandomGenerator.genExpRV(m.lambda));
		r.setArrival(c.getCurrentTime() + r.getIAT());
		r.setTs(RandomGenerator.genExpRV(1.0 / m.Ts));
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
		m.requestQueue.add(this.r);

		if (c.getCurrentTime() >= c.getSimulationTime())
			// TODO
			c.logger.numArrivals++;

		/*
		 * If this request happens to be the only one in the system (i.e., the
		 * server was idle upon its birth) then we must "start service" for that
		 * request, which means that we can "predict" when service will be done
		 * (and hence schedule a death event), by generating a random
		 * "service time" according to the distribution of service times and
		 * adding that to the current time.
		 */
		// if (m.requestQueue.size() == 1) {
		// double deathTime = c.getCurrentTime() + r.getTs();
		// c.addEvent(new Death(c, m, deathTime));
		// }
		//
		// if (m.requestQueue.size() > 0) {
		// double deathTime = c.getCurrentTime() + r.getTs();
		// c.addEvent(new Death(c, m, deathTime));
		// }

		if (!m.isCPU1Busy()) {
			double deathTime = c.getCurrentTime() + r.getTs();
			c.addEvent(new CPU1Death(c, m, deathTime));
			m.setCPU1Busy(true);
		}

		else if (!m.isCPU2Busy()) {
			double deathTime = c.getCurrentTime() + r.getTs();
			c.addEvent(new CPU2Death(c, m, deathTime));
			m.setCPU2Busy(true);
		}

		/*
		 * Predict (and hence schedule) when the next birth will occur. We can
		 * predict when the next birth will occur by generating a random
		 * "IAT time" according to the specified distribution of IAT times, and
		 * adding that to the current time.
		 */
		c.addEvent(new CPUBirth(c, m));
	}

	@Override
	public double getTime() {
		return r.getArrival();
	}
}