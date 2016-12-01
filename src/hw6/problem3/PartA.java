package hw6.problem3;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class PartA extends Thread {
	private int id;
	private static volatile boolean[] flag = new boolean[2];
	private static volatile int turn = 0;

	public static void main(String[] args) {
		PartA[] p = new PartA[2];
		for (int i = 0; i < 2; i++) {
			p[i] = new PartA(i);
			p[i].start();
		}
	}

	public PartA(int id) {
		this.id = id;
	}

	public void run() {
		Random r = new Random();
		int i = this.id;
		int j = i > 0 ? 0 : 1;

		for (int k = 0; k < 5; k++) {
			// Entry Section
			System.out.printf("Thread %d is executing entry section in iteration %d\n", this.id, k+1);
			flag[i] = true;
			while (flag[j]) {
				if (turn == j) {
					flag[i] = false;
					while (turn == j) {
						
					}
					flag[i] = true;
				}
			}
			// Critical Section
			System.out.printf("Thread %d is starting iteration %d\n", this.id, k+1);
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

			System.out.printf("Thread %d is done with iteration %d\n", this.id, k+1);
			try {
				Thread.sleep(r.nextInt(20));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Exit section
			turn = j;
			flag[i] = false;
		}
	}
}
