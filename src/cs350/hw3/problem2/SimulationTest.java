package cs350.hw3.problem2;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * Boston University CS350
 * <p>
 * HW3.2: M/M/1/K Simulator
 * 
 * @author Raymond Chavez {@literal <rchavez9@bu.edu>}
 */
public class SimulationTest {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		start();
		// test();
	}

	public static void start() throws FileNotFoundException, UnsupportedEncodingException {
		String systemName = "HW3_2a";
		System.out.printf("-- Now simulating system: %s -- \n\n", systemName);
		System.out.printf("-- Writing log data to: %s.csv -- \n\n", systemName);
		Controller c1 = new Controller();
		MM1KSystem mk1 = new MM1KSystem(systemName, c1, 1000, 5, 0.15, 2);
		c1.start(mk1);

		systemName = "HW3_2b";
		System.out.printf("-- Now Simulating System %s -- \n\n", systemName);
		System.out.printf("-- Writing log data to: %s.csv -- \n\n", systemName);
		Controller c2 = new Controller();
		MM1KSystem mk2 = new MM1KSystem(systemName, c2, 1000, 6, 0.15, 2);
		c2.start(mk2);

		systemName = "HW3_2c";
		System.out.printf("-- Now Simulating System %s -- \n\n", systemName);
		System.out.printf("-- Writing log data to: %s.csv -- \n\n", systemName);
		Controller c3 = new Controller();
		MM1KSystem mk3 = new MM1KSystem(systemName, c3, 1000, 6, 0.20, 2);
		c3.start(mk3);

		systemName = "HW3_2d";
		System.out.printf("-- Now simulating system: %s -- \n\n", systemName);
		System.out.printf("-- Writing log data to: %s.csv -- \n\n", systemName);
		Controller c4 = new Controller();
		MM1KSystem mk4 = new MM1KSystem(systemName, c4, 1000, 5, 0.15, 4);
		c4.start(mk4);

		systemName = "HW3_2e";
		System.out.printf("-- Now Simulating System %s -- \n\n", systemName);
		System.out.printf("-- Writing log data to: %s.csv -- \n\n", systemName);
		Controller c5 = new Controller();
		MM1KSystem mk5 = new MM1KSystem(systemName, c5, 1000, 6, 0.15, 4);
		c5.start(mk5);

		systemName = "HW3_2f";
		System.out.printf("-- Now Simulating System %s -- \n\n", systemName);
		System.out.printf("-- Writing log data to: %s.csv -- \n\n", systemName);
		Controller c6 = new Controller();
		MM1KSystem mk6 = new MM1KSystem(systemName, c6, 1000, 6, 0.20, 4);
		c6.start(mk6);

		systemName = "HW3_2g";
		System.out.printf("-- Now simulating system: %s -- \n\n", systemName);
		System.out.printf("-- Writing log data to: %s.csv -- \n\n", systemName);
		Controller c7 = new Controller();
		MM1KSystem mk7 = new MM1KSystem(systemName, c7, 1000, 5, 0.15, 10);
		c7.start(mk7);

		systemName = "HW3_2h";
		System.out.printf("-- Now Simulating System %s -- \n\n", systemName);
		System.out.printf("-- Writing log data to: %s.csv -- \n\n", systemName);
		Controller c8 = new Controller();
		MM1KSystem mk8 = new MM1KSystem(systemName, c8, 1000, 6, 0.15, 10);
		c8.start(mk8);

		systemName = "HW3_2i";
		System.out.printf("-- Now Simulating System %s -- \n\n", systemName);
		System.out.printf("-- Writing log data to: %s.csv -- \n\n", systemName);
		Controller c9 = new Controller();
		MM1KSystem mk9 = new MM1KSystem(systemName, c9, 1000, 6, 0.20, 10);
		c9.start(mk9);
	}

	public static void test() {
	}
}
