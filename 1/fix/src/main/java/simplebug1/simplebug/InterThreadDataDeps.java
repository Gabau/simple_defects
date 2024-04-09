package simplebug1.simplebug;

import java.util.ArrayList;
import simplebug.util.SimpleBug;

/**
 * To demonstrate how the program detects bugs due to interthread
 * data dependencies
 */
public class InterThreadDataDeps implements SimpleBug<Integer> {
	public static int k = 1000;
	private ArrayList<Thread> childrenThreads;
	
	public static class Thread1 extends Thread {
		@Override
		public void run() {
			for (int i = 0; i < 100; ++i) {
				k = 100;	
			}
		}
	}
	
	public static class Thread2 extends Thread {
		@Override
		public void run() {
			for (int i = 0; i < 100; ++i) {
				k = 100;	
			}
		}
	}
	
	@Override
	public Integer getResult() {
		return k;
	}
	
	@Override
	public void startBug() {
		Thread1 thread1 = new Thread1();
		thread1.start();
		Thread2 thread2 = new Thread2();
		thread2.start();
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
