package cs350.hw4.problem2.events;

import cs350.hw4.problem2.Controller;
import cs350.hw4.problem2.MM1System;
import cs350.hw4.problem2.MM2System;
import cs350.hw4.problem2.QueuingSystem;

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
	protected double eventTime;

	public Event(Controller c) {
		this.c = c;
	}

	public abstract void exec();

	public abstract double getTime();
}
