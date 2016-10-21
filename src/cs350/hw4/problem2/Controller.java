package cs350.hw4.problem2;

import java.io.PrintWriter;
import java.util.Comparator;
import java.util.PriorityQueue;

import cs350.hw4.problem2.events.Event;
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
	private final int simulationTime;
	private double currentTime;
	private PriorityQueue<Event> schedule;
	private PrintWriter writer;
	private String systemName;

	public Logger logger = new Logger(this);

	public Controller(int simulationTime) {
		this.simulationTime = simulationTime;
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

	public void start(QueuingSystem s) {
		this.systemName = s.systemName;
		log("IAT,Ts,Arrival,Start,End,Tq,Tw");

		// We run the simulator twice as long to allow for a warm-up period
		while (this.getCurrentTime() < 2 * this.getSimulationTime()) {
			Event e = this.nextEvent();
			this.setCurrentTime(e.getTime());
			e.exec();
		}
		// TODO Change method to print global network stats!
		// s.printStatistics();
		writer.close();
	}

	public double getCurrentTime() {
		return currentTime;
	}
	
	public int getSimulationTime() {
		return this.simulationTime;
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

	public void log(String s) {
		if (writer == null) {
			try {
				writer = new PrintWriter(systemName + ".csv", "UTF-8");
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		writer.println(s);
	}
}