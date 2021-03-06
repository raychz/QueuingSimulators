package cs350.hw4.problem2;

import java.io.PrintWriter;
import java.util.Comparator;
import java.util.PriorityQueue;

import cs350.hw4.problem2.events.CPUBirth;
import cs350.hw4.problem2.events.Event;
import cs350.hw4.problem2.events.Monitor;
import cs350.hw4.problem2.utilities.Logger;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;


/**
 * <p>
 * Boston University CS350
 * <p>
 * HW4.2: Multi-Server Network Simulator
 * 
 * @author Raymond Chavez {@literal <rchavez9@bu.edu>}
 */
public class Controller {
	private double currentTime;
	private PriorityQueue<Event> schedule;
	private String systemName;
	public State state;

	public Controller(State s) {
		this.state = s;
		this.currentTime = 0;
		this.schedule = new PriorityQueue<Event>(new Comparator<Event>() {
			public int compare(Event e1, Event e2) {
				if (e1.getTime() < e2.getTime()) {
					return -1;
				} else {
					return 1;
				}
			}
		});
	}

	public void start() {
		this.systemName = state.getStateName();
		state.logger.log("IAT,Ts,Arrival,Start,End,Tq,Tw");
		
		this.addEvent(new CPUBirth(this, state, true));
		this.addEvent(new Monitor(this, state));
		
		// We run the simulator twice as long to allow for a warm-up period
		while (this.getCurrentTime() < 2 * state.getSimulationTime()) {
			Event e = this.nextEvent();
			this.setCurrentTime(e.getTime());
			e.exec();
		}
		state.logger.printStatistics();
	}

	public double getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(double time) {
		this.currentTime = time;
	}

	public void addEvent(Event e) {
		schedule.add(e);
	}

	public Event nextEvent() {
		return schedule.remove();
	}

	public boolean hasNextEvent() {
		return !schedule.isEmpty();
	}
	
	public String getSystemName() {
		return systemName;
	}
}