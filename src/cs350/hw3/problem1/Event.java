package cs350.hw3.problem1;

/**
 * <p>
 * Boston University CS350
 * <p>
 * HW3.1: M/M/1 Simulator
 * 
 * @author Raymond Chavez {@literal <rchavez9@bu.edu>}
 */
public abstract class Event {
	protected MM1System m;
	protected Controller c;
	protected double eventTime;
	// protected Request r;

	public Event(Controller c, MM1System m) {
		this.c = c;
		this.m = m;
	}

	public abstract void exec();

	public abstract double getTime();
}
