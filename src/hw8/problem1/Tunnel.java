package hw8.problem1;

import java.util.concurrent.Semaphore;

public class Tunnel {
	static final int experimentalNorthwardCount = 20;
	static final int experimentalSouthwardCount = 23;
	static final int capacity = 4;
	
	// Switch direction of traffic at most every switchDirectionsAt drivers
	static final int switchDirectionsAt = 10;
	
	// Wait on tunnelCapacity to admit drivers into tunnel
	static volatile Semaphore tunnelCapacity = new Semaphore(capacity, false);
	
	// or
	static volatile Semaphore admitNorthward = new Semaphore(switchDirectionsAt, false);
	static volatile Semaphore admitSouthward = new Semaphore(0, false);
	
	static volatile Semaphore lock = new Semaphore(1, false);
//	
//	// OR OR OR
//	//static volatile Semaphore emptyCount = new Semaphore(capacity, false);
//	static volatile Semaphore fullCount = new Semaphore(0, false);
//	
	static volatile Northward[] northwardDrivers = new Northward[experimentalNorthwardCount];
	static volatile Southward[] southwardDrivers = new Southward[experimentalSouthwardCount];
	
	/*
	 * Northward thread arrives to tunnel 
	 * Wait on TunnelCapacity (from Tunnel)
	 * Wait on NorthwardDirection (from Tunnel) 
	 * Wait(mutex) 
	 * northwardDrivers++;
	 * if(northwardDrivers == 1) {
	 * 		grab the direction lock (mutex) preventing southward drivers from
	 * 		entering tunnel
	 * }
	 * Signal(mutex) 
	 * enterTunnel(); 
	 * Wait(mutex)
	 * northwardDrivers--;
	 * Signal(mutex)
	 * Signal TunnelCapacity (from Tunnel)
	 */
	
	/*
	 * At each end, allow up to N drivers to enter the tunnel in a certain direction.  Every N drivers switch directions.
	 * Also switch directions when there are no more arrivals of traffic in a certain direction.
	 * 
	 * Upon the departure of the Nth or currentCarsWaiting-th driver, check if traffic is waiting to enter tunnel
	 * from opposite direction.  If true, switch tunnel directions and repeat the process above.
	 */

	public static void main(String[] args) {		
		for(int i=0; i<experimentalSouthwardCount; i++) {
			southwardDrivers[i] = new Southward(i);
			southwardDrivers[i].start();
		}
		for(int i=0; i<experimentalNorthwardCount; i++) {
			northwardDrivers[i] = new Northward(i);
			northwardDrivers[i].start();
		}
	}

}
