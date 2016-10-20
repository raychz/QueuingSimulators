package cs350.hw3.problem1;

/**
 * <p>
 * Boston University CS350
 * <p>
 * HW3.1: M/M/1 Simulator
 * 
 * @author Raymond Chavez {@literal <rchavez9@bu.edu>}
 */
public class Monitor extends Event {
	private double startTime;

	public Monitor(Controller c, MM1System m) {
		super(c, m);
		/*
		 * We must start performing monitors after the warm up period has
		 * completed
		 */
		startTime = c.getCurrentTime() == 0 ? m.getSimulationTime() : c.getCurrentTime() + m.genExpRV(2 * m.lambda);
	}

	@Override
	public void exec() {
		m.numMonitors++;

		// Schedule new monitor event
		c.addEvent(new Monitor(c, m));

		int curQ = m.requestQueue.size();
		int curW = (curQ > 0) ? (m.requestQueue.size() - 1) : 0;
		m.wTotal += curW;
		m.qTotal += curQ;
	}

	@Override
	public double getTime() {
		return this.startTime;
	}
}