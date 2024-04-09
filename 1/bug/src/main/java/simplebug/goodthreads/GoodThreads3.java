package simplebug.goodthreads;

import simplebug.util.SimpleBug;

/**
 * Variant of good threads, where good thread 3 doesn't work
 * cause of a missing join.
 */
public class GoodThreads3 implements SimpleBug<Integer> {
	
	int result = 100;
	int data = 0;
	/**
	 * The thread that should have ran
	 */
	class GoodThread1 extends Thread {
		@Override
		public void run() {
			result = 0;
		}
	}
	
	/**
	 * An inconsequential thread
	 */
	class GoodThread2 extends Thread {
		@Override
		public void run() {
			for (int i = 0; i < 1000; ++i) {
				data++;
			}
		}
	}
	
	/**
	 * A thread with a bug
	 */
	class GoodThread3 extends Thread {
		@Override
		public void run() {
			for (int i = 0; i < 100; ++i) {
				result = result + 2;
			}
		}
	}

	@Override
	public void startBug() {
		GoodThread2 goodThread2 = new GoodThread2();
		GoodThread3 goodThread3 = new GoodThread3();
		goodThread2.start();
		goodThread3.start();
		try {
			goodThread2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (result == 300) {
			GoodThread1 goodThread1 = new GoodThread1();
			goodThread1.start();
			try {
				goodThread1.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Integer getResult() {
		// TODO Auto-generated method stub
		return result;
	}
	
	
	
}
