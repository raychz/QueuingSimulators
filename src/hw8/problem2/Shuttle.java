package hw8.problem2;

import java.util.concurrent.*;

public class Shuttle extends Thread {
	private int id;

	int capacity;
	Semaphore waitingCountsMutex;
	Semaphore ridingCountsMutex;
	int[] waitingCounts;
	int[] ridingCounts;
	Semaphore[] waiting;
	Semaphore[] riding;
	
	public Shuttle(int id) {
		this.id = id;
		this.capacity = ShuttleSynchSimulation.N;
		this.waitingCountsMutex = new Semaphore(1, false);
		this.ridingCountsMutex = new Semaphore(1, false);
		this.waitingCounts = new int[ShuttleSynchSimulation.K];
		this.ridingCounts = new int[ShuttleSynchSimulation.K];
		this.waiting = new Semaphore[ShuttleSynchSimulation.K];
		this.riding = new Semaphore[ShuttleSynchSimulation.K];
		for (int i = 0; i < ShuttleSynchSimulation.K; i++) {
			waiting[i] = new Semaphore(0, false);
			riding[i] = new Semaphore(0, false);
		}
	}

	@Override
	public void run() {
		int currentTerminal = -1;
		int available = capacity;

		while(true) {
			try {
				waitingCountsMutex.acquire();
				ridingCountsMutex.acquire();
				
				Thread.sleep((int) (Math.random() * 1000));
				currentTerminal = (currentTerminal + 1) % ShuttleSynchSimulation.K;
				
				/* Open shuttle door and allow ridingCounts[currentTerminal] to exit
				 */
				for(int i = 0; i < ridingCounts[currentTerminal]; i++) {
					available++;
				}
				System.out.printf("Shuttle has arrived at terminal %s and is deboarding %d passengers. Available seats is now %d.", 
						ShuttleSynchSimulation.terminals[currentTerminal], ridingCounts[currentTerminal], available);
				/* Signal the min of waitingCounts[currentTerminal] and available
				 * to board the shuttle
				 */
				int n = Math.min(waitingCounts[currentTerminal], available);
				for(int i = 0; i < n; i++) {
					waiting[currentTerminal].release();
					available--;
				}
				 
				/*
				 * Close the door and signal the two mutices
				 */
				ridingCountsMutex.release();
				waitingCountsMutex.release();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
