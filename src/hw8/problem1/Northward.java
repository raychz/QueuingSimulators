package hw8.problem1;

import java.util.concurrent.Semaphore;

public class Northward extends Thread {
	private int id;
	static volatile int northwardDriversCount = 0;
	static volatile Semaphore mutex = new Semaphore(1, false);
	static volatile Semaphore buffer = new Semaphore(10, false);

	public Northward(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		try {
			// Wait on green light for northwardDirection
			Tunnel.admitNorthward.acquire();

			// Wait on an available spot in the tunnel
			Tunnel.tunnelCapacity.acquire();

			// Access northwardDriversCount in a mutually exclusive fashion
			Northward.mutex.acquire();
			northwardDriversCount++;
			if (northwardDriversCount % Tunnel.switchDirectionsAt == 1) {
				Tunnel.lock.acquire();
			}
			Northward.mutex.release();

			// Enter tunnel for a random amount of time
			System.out.printf("Driver %d is driving northward in the tunnel\n", id);
			Thread.sleep(10);
			System.out.printf("Driver %d has exited the tunnel\n\n", id);

			// Access northwardDriversCount in a mutually exclusive fashion
			Northward.mutex.acquire();
			System.out.printf("Opening a spot in the tunnel with %d permits now available.\n",
					Tunnel.tunnelCapacity.availablePermits());

			if (northwardDriversCount % Tunnel.switchDirectionsAt == 0) {
				System.out.println("Switching tunnel direction to southbound.");
				for (int _ = 0; _ < Tunnel.switchDirectionsAt; _++) {
					Tunnel.admitSouthward.release();
				}
				Tunnel.lock.release();
			}
			Northward.mutex.release();
			
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
			mutex.acquire();
			mutex.acquire();
			mutex.acquire();

			System.out.println(mutex.availablePermits());

			mutex.acquire();
			System.out.println(mutex.availablePermits());
			mutex.release();
			mutex.release();
			mutex.release();
			mutex.release();
			System.out.println(mutex.availablePermits());
			System.out.println(mutex.availablePermits());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
