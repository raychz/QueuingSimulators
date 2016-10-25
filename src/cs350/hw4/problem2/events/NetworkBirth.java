package cs350.hw4.problem2.events;

import cs350.hw4.problem2.Controller;
import cs350.hw4.problem2.MM1System;
import cs350.hw4.problem2.Request;
import cs350.hw4.problem2.State;
import cs350.hw4.problem2.utilities.RandomGenerator;

public class NetworkBirth extends Event {
	private Request r = new Request();
	private MM1System network;
	
	public NetworkBirth(Controller c, State s) {
		super(c, s);
		this.network = s.getNetwork();
		r.setIAT(RandomGenerator.genExpRV(network.lambda));
		r.setArrival(c.getCurrentTime() + r.getIAT());
		r.setTs(RandomGenerator.genExpRV(1.0 / network.Ts));
	}

	@Override
	public void exec() {
		/*
		 * Add this new request to queue of arrivals.
		 */
		network.requestQueue.add(this.r);

		if (c.getCurrentTime() >= c.getSimulationTime())
			network.numArrivals++;
		
		/*
		 * If this request happens to be the only one in the system (i.e., the
		 * server was idle upon its birth) then we must "start service" for that
		 * request, which means that we can "predict" when service will be done
		 * (and hence schedule a death event), by generating a random
		 * "service time" according to the distribution of service times and
		 * adding that to the current time.
		 */
		if (network.requestQueue.size() == 1) {
			double deathTime = c.getCurrentTime() + r.getTs();
			c.addEvent(new DiskDeath(c, s, deathTime));
		}
		
		/*
		 * Predict (and hence schedule) when the next birth will occur. We can
		 * predict when the next birth will occur by generating a random
		 * "IAT time" according to the specified distribution of IAT times, and
		 * adding that to the current time.
		 */
		c.addEvent(new DiskBirth(c, s));
	}

	@Override
	public double getTime() {
		return r.getArrival();
	}

}
