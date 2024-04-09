package simplebug1.simplebug;

/**
 * Simple single threaded bug
 */
public class TestObject extends Thread {
	private Integer k = 0;
	
	public TestObject(int value) {
		k = value;
	}
	
	@Override
	public void run() {
		synchronized (k) {
			this.k += 2;
		}
	}
	
	public int getValue() {
		return k;
	}
}
