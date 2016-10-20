package cs350.hw3.problem1;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * Boston University CS350
 * <p>
 * HW3.1: M/M/1 Simulator
 * 
 * @author Raymond Chavez {@literal <rchavez9@bu.edu>}
 */
public class SimulationTest {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		start();
		// test();
	}

	public static void start() throws FileNotFoundException, UnsupportedEncodingException {
		String systemName = "HW3_1a";
		System.out.printf("-- Now simulating system: %s -- \n\n", systemName);
		System.out.printf("-- Writing log data to: %s.csv -- \n\n", systemName);

		Controller c1 = new Controller();
		MM1System m1 = new MM1System(systemName, c1, 1000, 5, 0.15);
		c1.start(m1);

		systemName = "HW3_1b";
		System.out.printf("-- Now Simulating System %s -- \n\n", systemName);
		System.out.printf("-- Writing log data to: %s.csv -- \n\n", systemName);
		Controller c2 = new Controller();
		MM1System m2 = new MM1System(systemName, c2, 1000, 6, 0.15);
		c2.start(m2);

		systemName = "HW3_1c";
		System.out.printf("-- Now Simulating System %s -- \n\n", systemName);
		System.out.printf("-- Writing log data to: %s.csv -- \n\n", systemName);
		Controller c3 = new Controller();
		MM1System m3 = new MM1System(systemName, c3, 1000, 6, 0.20);
		c3.start(m3);
	}

	public static void test() {
		Controller c = new Controller();
		MM1System m = new MM1System("test", c, 100, 5, 0.15);
		Birth b = new Birth(c, m);
		Birth b2 = new Birth(c, m);
		Birth b3 = new Birth(c, m);

		System.out.println(c.getCurrentTime());
		System.out.println(b.getTime());
		System.out.println(b2.getTime());
		System.out.println(b3.getTime());

		c.addEvent(b);
		c.addEvent(b2);
		c.addEvent(b3);

		System.out.println(c.nextEvent().getTime());
		System.out.println(c.nextEvent().getTime());
		System.out.println(c.nextEvent().getTime());
	}
}
