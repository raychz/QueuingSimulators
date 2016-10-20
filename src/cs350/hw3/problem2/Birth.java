package cs350.hw3.problem2;

/**
 * <p>
 * Boston University CS350
 * <p>
 * HW3.2: M/M/1/K Simulator
 * 
 * @author Raymond Chavez {@literal <rchavez9@bu.edu>}
 */
public class Birth extends Event {
	private Request r = new Request();

	public Birth(Controller c, MM1KSystem m) {
		super(c, m);
		r.setIAT(m.genExpRV(m.lambda));
		r.setArrival(c.getCurrentTime() + r.getIAT());
		r.setTs(m.genExpRV(1.0 / m.Ts));
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
		 * Add this new request to queue of arrivals if queue size < K.
		 */
		if (m.requestQueue.size() < m.K) {
			m.requestQueue.add(this.r);

			if (c.getCurrentTime() >= m.getSimulationTime()) {
				m.numArrivals++;
				m.numAttemptedArrivals++;
			}

			/*
			 * If this request happens to be the only one in the system (i.e.,
			 * the server was idle upon its birth) then we must "start service"
			 * for that request, which means that we can "predict" when service
			 * will be done (and hence schedule a death event), by generating a
			 * random "service time" according to the distribution of service
			 * times and adding that to the current time.
			 */
			if (m.requestQueue.size() == 1) {
				double deathTime = c.getCurrentTime() + r.getTs();
				c.addEvent(new Death(c, m, deathTime));
			}
		} else {
			if (c.getCurrentTime() >= m.getSimulationTime()) {
				m.numRejections++;
				m.numAttemptedArrivals++;
			}
		}

		/*
		 * Predict (and hence schedule) when the next birth will occur. We can
		 * predict when the next birth will occur by generating a random
		 * "IAT time" according to the specified distribution of IAT times, and
		 * adding that to the current time.
		 */
		c.addEvent(new Birth(c, m));
	}

	@Override
	public double getTime() {
		return r.getArrival();
	}
}