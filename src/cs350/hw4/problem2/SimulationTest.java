package cs350.hw4.problem2;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * Boston University CS350
 * <p>
 * HW4.2: Multi-Server Network Simulator
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

		// HW4.2
		MM2System CPU1 = new MM2System("CPU1", 40, 0.02);
		MM1System disk1 = new MM1System("Disk1", 0.1);
		MM1System network1 = new MM1System("Network1", 0.025);
		State s1 = new State("HW4.1", CPU1, disk1, network1);
		Controller c1 = new Controller(1000);
		c1.start(s1);

		systemName = "HW3_1b";
		System.out.printf("-- Now Simulating System %s -- \n\n", systemName);
		System.out.printf("-- Writing log data to: %s.csv -- \n\n", systemName);
		Controller c2 = new Controller(1000);
		MM1System m2 = new MM1System(systemName, c2, 6, 0.15);
		c2.start(m2);

		systemName = "HW3_1c";
		System.out.printf("-- Now Simulating System %s -- \n\n", systemName);
		System.out.printf("-- Writing log data to: %s.csv -- \n\n", systemName);
		Controller c3 = new Controller(1000);
		MM1System m3 = new MM1System(systemName, c3, 6, 0.20);
		c3.start(m3);
	}

	public static void test() {
	}
}
