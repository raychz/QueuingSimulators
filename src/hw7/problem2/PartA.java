package hw7.problem2;
import java.util.concurrent.Semaphore;

public class PartA extends Thread{
	private int id; 
	private static volatile Semaphore[] semaphores = new Semaphore[3];

	public static void main(String[] args) {
		for (int i=0; i < 3; i++) {
			semaphores[i] = new Semaphore(0, false);
		}
		
		PartA[] p = new PartA[3];
		for (int i = 0; i < 3; i++) {
			p[i] = new PartA(i);
			p[i].start();
		}
	}
	
	public PartA(int id) {
		this.id = id;
	}
	
	public void run() {
		semaphores[id].release();
		for(int j = 0; j < 3; j++) {
			try {
				semaphores[j].acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			semaphores[j].release();
		}
		try {
			if(id == 0)
				Thread.sleep(1000);
			if(id == 1)
				Thread.sleep(5000);
			if(id == 2)
				Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.printf("Executing thread %d\n", id);
		System.out.printf("Sem value for id %d: %d\n", id, semaphores[id].availablePermits());
	}
}
