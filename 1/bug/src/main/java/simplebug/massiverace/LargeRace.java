package simplebug.massiverace;

public class LargeRace implements Runnable  {
	
	private static Integer global = 0;
	
	private static class Thread1 extends Thread {
		@Override
		public void run() {
			for (int i = 0; i < 1000; ++i) {
				global++;
			}
		}
	}
	
	public static int getGlobal() {
		return global;
	}
	
	
	public void run() {
		Thread1 thread1 = new Thread1();
		Thread1 thread12 = new Thread1();
		thread1.start();
		thread12.start();
		try {
			thread12.join();
			thread1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
