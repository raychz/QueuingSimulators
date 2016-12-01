package hw6.problem3;
import java.util.concurrent.Semaphore;;

public class MyProc extends Thread {
	private int id;
	static volatile int Counter = 0;
	
	public static void main(String[] args) {
		int N = 100;
		MyProc[] p = new MyProc[100];
		
		System.out.println(Counter);
		for (int i=0; i < N; i++) {
			p[i] = new MyProc(i);
			p[i].start();
		}
		System.out.println(Counter);
	}
	
	public MyProc(int i) {
		this.id = i;
	}

	public void run() {
		Counter++;
	}
}
