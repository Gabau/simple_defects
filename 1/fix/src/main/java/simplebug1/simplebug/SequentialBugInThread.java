package simplebug1.simplebug;

import javax.swing.plaf.basic.BasicBorders.FieldBorder;

import simplebug.util.SimpleBug;

public class SequentialBugInThread implements SimpleBug<Integer> {

	private int result;
	private int input = 10;
	/*
	 * Wrong fibonacci code in a seperate thread
	 */
	private class SequentialThread extends Thread {
		
		private int fib(int n) {
			int a = 1;
			int b = 0;
			for (int i = 0; i < n; ++i) {
				int tmp = b;
				b = a;
				a = a + tmp;
			}
			return b;
		}
		
		
		
		@Override
		public void run() {
			result = fib(input);
		}
	}
	
	public void setInput(int input) {
		this.input = input;
	}
	
	@Override
	public void startBug() {
		SequentialThread sequentialThread = new SequentialThread();
		sequentialThread.start();
		try {
			sequentialThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Integer getResult() {
		return result;
	}
	
	
	
}
