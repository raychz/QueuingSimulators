package hw6.problem3;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class PartC extends Thread {
	private int id;
	private static volatile boolean[] flag = new boolean[2];
	private static volatile int turn = 0;
	private static volatile int[] counter = new int[2];

	public static void main(String[] args) {
		PartC[] p = new PartC[2];
		for (int i = 0; i < 2; i++) {
			p[i] = new PartC(i);
			p[i].start();
		}
	}

	public PartC(int id) {
		this.id = id;
	}

	public void run() {
		Random r = new Random();
		int i = this.id;
		int j = i > 0 ? 0 : 1;

		for (int k = 0; k < 5; k++) {
			// Entry Section
			System.out.printf("Thread %d is executing entry section in iteration %d\n", this.id, k + 1);
			turn = j;
			flag[i] = true;
			while (flag[j] && turn == j) {
				counter[i]++;
			}
			
			// Critical Section
			System.out.printf("Thread %d is starting iteration %d\n", this.id, k + 1);
			try {
				Thread.sleep(r.nextInt(20));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Artificial delay
			if (this.id == 0) {
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			System.out.println("We hold these truths to be self-evident, that all men are created equal,");
			try {
				Thread.sleep(r.nextInt(1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("that they are endowed by their Creator with certain unalienable Rights,");
			try {
				Thread.sleep(r.nextInt(20));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("that among these are Life, Liberty and the pursuit of Happiness.");
			try {
				Thread.sleep(r.nextInt(20));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.printf("Thread %d is done with iteration %d\n", this.id, k + 1);
			try {
				Thread.sleep(r.nextInt(20));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Exit section
			flag[i] = false;
		}
		System.out.println(counter[i]);
	}
}
