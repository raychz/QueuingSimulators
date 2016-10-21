package cs350.hw4.problem2.utilities;

import java.util.Random;

public class RandomGenerator {
	private static Random r = new Random();

	public static double genExpRV(double lambda) {
		double y = r.nextDouble();
		return -(Math.log(1.0 - y)) / lambda;
	}

	public static double genUniform(double start, double end) {
		double diff = end - start;
		double base = r.nextDouble();
		return base * diff + start;
	}

	public static double genNormal(double u, double s) {
		double y = (r.nextGaussian() * s) + u;
		while (y < 0) {
			y = (r.nextGaussian() * s) + u;
		}
		return y;
	}
	
	public static double genDouble() {
		return r.nextDouble();
	}
}
