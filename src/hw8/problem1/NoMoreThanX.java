
/*
 * CS350: Java Multithreaded Programming Example

 *
 * No more than X times in a row.
 *
 * Author: Qiaobin Fu (qiaobinf@bu.edu)
 * */
package hw8.problem1;

import java.util.concurrent.Semaphore;

class NoMoreThanX extends Thread {
	private int id;
	static volatile int Cp = 0;
	static Semaphore S1 = new Semaphore(1, false);
	static Semaphore S2 = new Semaphore(4, false);

	public NoMoreThanX(int i) {
		id = i;
	}

	public void run() {
		while (true) {
			try {
				if (id == 0) {
					S2.acquire();
					S1.acquire();
				} else {
					S1.acquire();
				}

				/* Critical Section. */
				System.out.println("Thread " + id);
				// System.out.println(id);

				if (id == 0) {
					Cp++;
					S1.release();
				} else {
					for (int i = 0; i < Cp; i++)
						S2.release();

					Cp = 0;
					S1.release();
				}

				if (id != 0) {
					Thread.sleep((int) Math.random() * 1);
				} else {
					Thread.sleep((int) Math.random() * 100000);
				}
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}

	public static void main(String[] args) {
		final int N = 2;
		NoMoreThanX[] p = new NoMoreThanX[N];

		for (int i = 0; i < N; i++) {
			p[i] = new NoMoreThanX(i);
			p[i].start();
		}
	}
}