package cs350.hw4.problem2.events;

import cs350.hw4.problem2.Controller;
import cs350.hw4.problem2.MM1System;
import cs350.hw4.problem2.MM2System;
import cs350.hw4.problem2.QueuingSystem;
import cs350.hw4.problem2.State;

/**
 * <p>
 * Boston University CS350
 * <p>
 * HW4.2: Multi-Server Network Simulator
 * 
 * @author Raymond Chavez {@literal <rchavez9@bu.edu>}
 */
public abstract class Event {
	protected Controller c;
	protected State s;
	protected double eventTime;

	public Event(Controller c, State s) {
		this.c = c;
		this.s = s;
	}

	public abstract void exec();

	public abstract double getTime();
}
