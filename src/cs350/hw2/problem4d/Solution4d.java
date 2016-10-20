package cs350.hw2.problem4d;

import java.util.Random;

public class Solution4d {
	private static int[] percentages = { 50, 30, 14, 6 };
	private static int[] outputs = { 1, 3, 10, 30 };

	public static void main(String[] args) {
		for(int i = 0; i < 100; i++) {
			System.out.println(pmf());
		}
	}

	public static double pmf() {
		Random R = new Random();
	    int r = R.nextInt(100);
	    int sum = 0;
	    double out = 0;
	    for (int i = 0; i < percentages.length; i++) {
	        sum += percentages[i];
	        if (r < sum) {
	            out = outputs[i];
	            break;
	        }
	    }
	    return out;
	}
}