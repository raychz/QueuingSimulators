package hw8.problem1;

import java.util.concurrent.Semaphore;

public class Southward extends Thread {
	private int id;
	static volatile int southwardDriversCount = 0;
	static volatile Semaphore mutex = new Semaphore(1, false);
	static volatile Semaphore buffer = new Semaphore(10, false);

	public Southward(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		try {
			// Wait on green light for southwardDirection
			Tunnel.admitSouthward.acquire();
			
			// Wait on an available spot in the tunnel
			Tunnel.tunnelCapacity.acquire();

			// Access southwardDriversCount in a mutually exclusive fashion
			Southward.mutex.acquire();
			southwardDriversCount++;
			if(southwardDriversCount % Tunnel.switchDirectionsAt == 1) {
				Tunnel.lock.acquire();
			}
			Southward.mutex.release();

			// Enter tunnel for a random amount of time
			System.out.printf("Driver %d is driving southward in the tunnel\n", id);
			Thread.sleep(10);
			System.out.printf("Driver %d has exited the tunnel\n", id);
			
			// Access southwardDriversCount in a mutually exclusive fashion
			Southward.mutex.acquire();
			System.out.printf("Opening a spot in the tunnel with %d permits now available.\n", 
					Tunnel.tunnelCapacity.availablePermits());
			
			if (southwardDriversCount % Tunnel.switchDirectionsAt == 0) {
				System.out.println("Switching tunnel direction to northbound.");
				for(int _ = 0; _ < Tunnel.switchDirectionsAt; _++) {
					Tunnel.admitNorthward.release();
				}
				Tunnel.lock.release();
			}
			Southward.mutex.release();
			
			// Signal an available spot in the tunnel
			Tunnel.tunnelCapacity.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			System.out.println(mutex.availablePermits());
			mutex.release();
			mutex.release();
			mutex.release();
			mutex.release();
			mutex.release();
			mutex.release();
			System.out.println(mutex.availablePermits());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
