package simplebug.badthreads;

import simplebug.util.SimpleBug;

/**
 * 
 * Bad thread variant where a good thread should have ran, but bad thread is ran instead.
 * 
 */
public class BadThreads3 implements SimpleBug<Integer> {

	int result = 1000;
	
	class BadThread extends Thread {
		@Override
		public void run() {
			result = 10;
		}
	}
	
	class GoodThread extends Thread {
		@Override
		public void run() {
			result = 0;
		}
	}
	
	private int getTen() {
		return 100;
	}
	
	@Override
	public void startBug() {
		this.result = 0;
		boolean condition = getTen() != 10;
		if (condition) {
			BadThread badThread = new BadThread();
			badThread.start();
			try {
				badThread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
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
