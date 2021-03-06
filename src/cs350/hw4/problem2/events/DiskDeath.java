package cs350.hw4.problem2.events;

import cs350.hw4.problem2.Controller;
import cs350.hw4.problem2.MM1System;
import cs350.hw4.problem2.Request;
import cs350.hw4.problem2.State;
import cs350.hw4.problem2.utilities.RandomGenerator;

public class DiskDeath extends Event {
	private final MM1System disk;

	public DiskDeath(Controller c, State s, double deathTime) {
		super(c, s);
		this.disk = s.getDisk();
		this.eventTime = deathTime;
	}

	@Override
	public void exec() {
		// Remove the record of the request from the queue in the system.
		Request r = disk.requestQueue.remove();

		// Make disk server available
		disk.setMM1ServerBusy(false);

		
		// With probability 0.5, the request continues to the CPU.
		double rand = RandomGenerator.genDouble();
		if (rand <= 0.5) {
			c.addEvent(new CPUBirth(c, s, false));
		}

		// With probability 0.5, the request continues to the network.
		else {
			c.addEvent(new NetworkBirth(c, s));
		}

		// Log stats about request
		log(r);

		// Upon completion of service, check if other requests
		// are pending in the queue.
		if (disk.requestQueue.size() > 0) {
			//System.out.println("UHOHHHHHHHH THIS SHOULDNT HAPPEN");
			Request nextReq = disk.requestQueue.peek();
			double deathTime = c.getCurrentTime() + nextReq.getTs();
			c.addEvent(new DiskDeath(c, s, deathTime));
			disk.setMM1ServerBusy(true);
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
			s.logger.numDiskDepartures++;
			s.logger.DiskTqTotal += r.getTq();
			s.logger.DiskTwTotal += r.getTw();
			s.logger.DiskTsTotal += r.getTs();
			//s.logger.DiskRhoTotal += disk.lambda * r.getTs();
		}
	}

	@Override
	public double getTime() {
		return this.eventTime;
	}
}