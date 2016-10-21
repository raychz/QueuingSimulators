package cs350.hw4.problem2.events;

import java.util.logging.Level;
import java.util.logging.Logger;

import cs350.hw4.problem2.Controller;
import cs350.hw4.problem2.MM1System;
import cs350.hw4.problem2.MM2System;
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
public class CPU1Death extends Event {
	private MM2System m;

	public CPU1Death(Controller c, MM2System m, double eventTime) {
		super(c);
		this.m = m;
		this.eventTime = eventTime;
	}

	@Override
	public void exec() {
		// Remove the record of the request from the queue in the system, m.
		Request r = m.requestQueue.remove();

		// Make CPU core 1 available
		m.setCPU1Busy(false);

		double rand = RandomGenerator.genDouble();
		if (rand <= 0.1) {
			c.addEvent(new DiskBirth(c,m));
		}

		else if (rand <= 0.5) {
			c.addEvent(new NetworkBirth(c,m));
		}
		
		else {
			// Request has exited system
			// TODO DO SOMETHING HERE
		}

		// Log stats about request
		log(r);

		// Upon completion of service, check if other requests
		// are pending in the queue.
		if (m.requestQueue.size() > 0) {
			Request nextReq = m.requestQueue.peek();
			double deathTime = c.getCurrentTime() + nextReq.getTs();
			c.addEvent(new CPU1Death(c, m, deathTime));
		}
	}

	private void log(Request r) {
		if (c.getCurrentTime() >= c.getSimulationTime()) {
			r.setEnd(c.getCurrentTime());
			r.setStart(r.getEnd() - r.getTs());
			r.setTq(r.getEnd() - r.getArrival());
			r.setTw(r.getTq() - r.getTs());

			c.log(r.toString());

			// Update state vars in MM1System for gathering stats with every
			// monitor event.
			// m.numDepartures++;
			// m.TqTotal += r.getTq();
			// m.TwTotal += r.getTw();
			// m.TsTotal += r.getTs();
			// m.rhoTotal += m.lambda * r.getTs();
		}
	}

	@Override
	public double getTime() {
		return this.eventTime;
	}
}