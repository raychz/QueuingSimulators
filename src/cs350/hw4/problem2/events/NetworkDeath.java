package cs350.hw4.problem2.events;

import cs350.hw4.problem2.Controller;
import cs350.hw4.problem2.MM1System;
import cs350.hw4.problem2.Request;
import cs350.hw4.problem2.State;

public class NetworkDeath extends Event {
	private final MM1System network;

	public NetworkDeath(Controller c, State s, double deathTime) {
		super(c, s);
		this.network = s.getNetwork();
		this.eventTime = deathTime;
	}

	@Override
	public void exec() {
		// Remove the record of the request from the queue in the system.
		Request r = network.requestQueue.remove();

		// Make network server available
		network.setMM1ServerBusy(false);

		// With probability 1, the request continues directly to the CPU queue
		// with zero IAT (hence, the false flag).
		c.addEvent(new CPUBirth(c, s, false));

		// Log stats about request
		log(r);

		// Upon completion of service, check if other requests
		// are pending in the queue.
		if (network.requestQueue.size() > 0) {
			Request nextReq = network.requestQueue.peek();
			double deathTime = c.getCurrentTime() + nextReq.getTs();
			c.addEvent(new NetworkDeath(c, s, deathTime));
			network.setMM1ServerBusy(true);
		}
	}

	private void log(Request r) {
		// We want to start monitoring stats after the simulation halfway point
		// to allow for a "warm-up period"
		if (c.getCurrentTime() >= s.getSimulationTime()) {
			r.setEnd(c.getCurrentTime());
			r.setStart(r.getEnd() - r.getTs());
			r.setTq(r.getEnd() - r.getArrival());
			r.setTw(r.getTq() - r.getTs());

			s.logger.log(r.toString());

			// Update state vars in Logger class for gathering stats with every
			// event.
			s.logger.numNetworkDepartures++;
			s.logger.NetworkTqTotal += r.getTq();
			s.logger.NetworkTwTotal += r.getTw();
			s.logger.NetworkTsTotal += r.getTs();
			//s.logger.NetworkRhoTotal += network.lambda * r.getTs();
		}
	}

	@Override
	public double getTime() {
		return this.eventTime;
	}
}