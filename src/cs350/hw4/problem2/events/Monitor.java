package cs350.hw4.problem2.events;

import cs350.hw4.problem2.Controller;
import cs350.hw4.problem2.MM1System;
import cs350.hw4.problem2.QueuingSystem;

/**
 * <p>
 * Boston University CS350
 * <p>
 * HW4.2: Multi-Server Network Simulator
 * 
 * @author Raymond Chavez {@literal <rchavez9@bu.edu>}
 */
public class Monitor extends Event {
	private double startTime;

	public Monitor(Controller c, QueuingSystem q) {
		super(c, q);
		/*
		 * We must start performing monitors after the warm up period has
		 * completed
		 */
		startTime = c.getCurrentTime() == 0 ? c.getSimulationTime() : c.getCurrentTime() + m.genExpRV(2 * m.lambda);
	}

	@Override
	public void exec() {
		q.numMonitors++;

		// Schedule new monitor event
		c.addEvent(new Monitor(c, q));

		int curQ = q.requestQueue.size();
		int curW = (curQ > 0) ? (q.requestQueue.size() - 1) : 0;
		m.wTotal += curW;
		m.qTotal += curQ;
	}

	@Override
	public double getTime() {
		return this.startTime;
	}
}