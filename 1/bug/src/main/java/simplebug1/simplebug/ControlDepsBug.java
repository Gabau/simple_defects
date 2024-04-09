package simplebug1.simplebug;

import simplebug.util.SimpleBug;

/**
 * A simple bug representing the idea
 * of what  happens when a thread doesn't run because of a bad control dependency.
 */
public class ControlDepsBug implements SimpleBug<Integer> {

	int result = 0;
	class ControlDepThread1 extends Thread {
		@Override
		public void run() {
			result = 1;
		}
	}

	
	@Override
	public void startBug() {
		boolean faultyControlDep = false;
		ControlDepThread1 thread1 = new ControlDepThread1();
		if (faultyControlDep) {
			thread1.start();
			try {
				thread1.join();
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
