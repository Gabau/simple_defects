package simplebug1.simplebug;

/**
 * Simple single threaded bug
 */
public class TestObject extends Thread {
	private Integer k = 0;
	private Object lock = new Object();
	public TestObject(int value) {
		k = value;
	}
	
	@Override
	public void run() {
		synchronized (lock) {
			this.k += 4;
		}
	}
	
	public int getValue() {
		return k;
	}
}
