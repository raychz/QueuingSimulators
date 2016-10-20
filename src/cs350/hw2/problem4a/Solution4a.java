package cs350.hw2.problem4a;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Solution4a {

	public static void main(String[] args) {
		ArrayList<Double> list = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			list.add(expDist(4));
		}
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(String.format(i + ",%.4f", list.get(i)));

		}
	}

	public static double expDist(double lambda) {
		double y = new Random().nextDouble();
		double x = (-Math.log(1.0 - y)) / lambda;
		System.out.println(x);
		return x;
	}
}