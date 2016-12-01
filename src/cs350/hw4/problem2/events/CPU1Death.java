package cs350.hw4.problem2.events;

import java.util.logging.Level;
import java.util.logging.Logger;

import cs350.hw4.problem2.Controller;
import cs350.hw4.problem2.MM1System;
import cs350.hw4.problem2.MM2System;
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
public class CPU1Death extends Event {
	private final MM2System CPU;

	public CPU1Death(Controller c, State s, double eventTime) {
		super(c, s);
		this.CPU = s.getCPU();
		this.eventTime = eventTime;
	}

	@Override
	public void exec() {
		// Remove the record of the request from the queue in the system.
		//Request r = CPU.requestQueue.remove();

		// Make CPU core 1 available
		CPU.setCPU1Busy(false);

		// With probability 0.1, the request continues to the disk.
		double rand = RandomGenerator.genDouble();
		if (rand <= 0.1) {
			c.addEvent(new DiskBirth(c,s));
		}
		
		// With probability 0.4, the request continues to the network.
		else if (rand <= 0.5) {
			c.addEvent(new NetworkBirth(c,s));
		}
		
		// With probability 0.5, the request exits the system.
		else {
			// Request has exited system
			if(c.getCurrentTime() >= s.getSimulationTime())
				s.logger.numSysDepartures++;
		}

		// Log stats about request
		//log(r);

		// Upon completion of service, check if other requests
		// are pending in the queue.
		if (CPU.requestQueue.size() > 0) {
			//Request nextReq = CPU.requestQueue.peek();
			Request nextReq = CPU.requestQueue.remove();
			double deathTime = c.getCurrentTime() + nextReq.getTs();
			c.addEvent(new CPU1Death(c, s, deathTime));
			log(nextReq);
			
			// TODO Might need to set cpu core 1 to busy here!
			CPU.setCPU1Busy(true);
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
			s.logger.numCPUDepartures++;
			s.logger.CPUTqTotal += r.getTq();
			s.logger.CPUTwTotal += r.getTw();
			s.logger.CPUTsTotal += r.getTs();
			//s.logger.CPURhoTotal += CPU.lambda * r.getTs();
		}
	}

	@Override
	public double getTime() {
		return this.eventTime;
	}
}