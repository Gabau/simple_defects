package simplebug.badthreads;

import simplebug.util.SimpleBug;

/**
 * Simple example to show what happens when threads
 * that shouldn't execute are ran.
 */
public class BadThreads implements SimpleBug<Integer> {

	int result = 0;
	
	class BadThread extends Thread {
		@Override
		public void run() {
			result = 10;
		}
	}
	
	@Override
	public void startBug() {
		this.result = 0;
		boolean condition = false;
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
