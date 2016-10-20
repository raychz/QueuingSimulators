package cs350.hw3.problem2;

import java.io.PrintWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 * <p>
 * Boston University CS350
 * <p>
 * HW3.2: M/M/1/K Simulator
 * 
 * @author Raymond Chavez {@literal <rchavez9@bu.edu>}
 */
public class Controller {
	private double currentTime;
	private PriorityQueue<Event> schedule;
	private PrintWriter writer;
	private String systemName;

	public Controller() {
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
		while (this.getCurrentTime() < 2 * s.getSimulationTime()) {
			Event e = this.nextEvent();
			this.setCurrentTime(e.getTime());
			e.exec();
		}
		s.printStatistics();
		writer.close();
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

	public void log(String s) {
		if (writer == null) {
			try {
				writer = new PrintWriter(systemName + ".csv", "UTF-8");
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		writer.println(s);
	}
}