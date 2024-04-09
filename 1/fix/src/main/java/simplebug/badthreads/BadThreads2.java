package simplebug.badthreads;

import simplebug.util.SimpleBug;

/**
 * Simple example to show what happens when threads
 * that shouldn't execute are ran.
 * Variant of BadThreads where bad computation is done that leads to bad condition
 */
public class BadThreads2 implements SimpleBug<Integer> {

	int result = 0;
	
	class BadThread extends Thread {
		@Override
		public void run() {
			result = 10;
		}
	}
	
	private int getTen() {
		return 10;
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
