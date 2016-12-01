package hw8.problem2;
import java.util.Random;
import java.util.concurrent.*;

public class Passenger extends Thread {
	private int id;
	private Shuttle shuttle = ShuttleSynchSimulation.shuttle;
	
	public Passenger(int id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		while(true) {
			int startingTerminal = chooseRandTerminal();
			int destinationTerminal = chooseRandTerminal();
			while(startingTerminal == destinationTerminal) destinationTerminal = chooseRandTerminal();
			
			try {
				/*
				 * Increment the waiting count array for the passenger's starting terminal
				 */
				shuttle.waitingCountsMutex.acquire();
				shuttle.waitingCounts[startingTerminal]++;
				shuttle.waitingCountsMutex.release();
				
				/*
				 * Block until shuttle arrives to this starting terminal
				 */
				shuttle.waiting[startingTerminal].acquire();
				
				/*
				 * Once shuttle picks up passenger, decrement the waiting count array
				 */
				shuttle.waitingCountsMutex.acquire();
				shuttle.waitingCounts[startingTerminal]--;
				
				/*
				 * Indicate to the shuttle the desired destination terminal
				 */
				shuttle.ridingCountsMutex.acquire();
				shuttle.ridingCounts[destinationTerminal]++;
				shuttle.ridingCountsMutex.release();
				shuttle.waitingCountsMutex.release();
				
				/*
				 * Block until shuttle arrives to destination terminal
				 */
				shuttle.riding[destinationTerminal].acquire();
				
				/*
				 * Once shuttle arrives, release passenger
				 */
				shuttle.ridingCountsMutex.acquire();
				shuttle.ridingCounts[destinationTerminal]--;
				shuttle.ridingCountsMutex.release();
				
				Thread.sleep((int) (Math.random() * 1000));
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
	
	private int chooseRandTerminal() {
		return (int) (Math.random() * ShuttleSynchSimulation.K);
	}
	
	public static void main(String[] args) {
	}
}
