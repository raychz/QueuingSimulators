package cs350.hw3.problem2;

/**
 * <p>
 * Boston University CS350
 * <p>
 * HW3.2: M/M/1/K Simulator
 * 
 * @author Raymond Chavez {@literal <rchavez9@bu.edu>}
 */
public abstract class Event {
	protected MM1KSystem m;
	protected Controller c;
	protected double eventTime;
	// protected Request r;

	public Event(Controller c, MM1KSystem m) {
		this.c = c;
		this.m = m;
	}

	public abstract void exec();

	public abstract double getTime();
}
