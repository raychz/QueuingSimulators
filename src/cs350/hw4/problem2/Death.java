package cs350.hw4.problem2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * Boston University CS350
 * <p>
 * HW4.2: Multi-Server Network Simulator
 * 
 * @author Raymond Chavez {@literal <rchavez9@bu.edu>}
 */
public class Death extends Event {
	public Death(Controller c, MM1System m, double eventTime) {
		super(c, m);
		this.eventTime = eventTime;
		// this.r = r;
	}

	@Override
	public void exec() {
		// Remove the record of the request from the queue in the system, m.
		Request r = m.requestQueue.remove();

		// Log stats about request
		log(r);

		// Upon completion of service, check if other requests
		// are pending in the queue.
		if (m.requestQueue.size() > 0) {
			Request nextReq = m.requestQueue.peek();
			double deathTime = c.getCurrentTime() + nextReq.getTs();
			c.addEvent(new Death(c, m, deathTime));
		}
	}

	private void log(Request r) {
		if (c.getCurrentTime() >= m.getSimulationTime()) {
			r.setEnd(c.getCurrentTime());
			r.setStart(r.getEnd() - r.getTs());
			r.setTq(r.getEnd() - r.getArrival());
			r.setTw(r.getTq() - r.getTs());

			c.log(r.toString());

			// Update state vars in MM1System for gathering stats with every
			// monitor event.
			m.numDepartures++;
			m.TqTotal += r.getTq();
			m.TwTotal += r.getTw();
			m.TsTotal += r.getTs();
			m.rhoTotal += m.lambda * r.getTs();
		}
	}

	@Override
	public double getTime() {
		return this.eventTime;
	}
}