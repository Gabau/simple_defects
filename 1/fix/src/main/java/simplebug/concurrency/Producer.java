package simplebug.concurrency;

import java.util.Queue;

public class Producer extends Thread {
	private int limit = 10000;
	private int queue_limit = 5;
	private final Queue<Integer> sharedQueue;
	private int message = 0;
	
	public Producer(Queue<Integer> dataQueue, int limit) {
		this.sharedQueue = dataQueue;
		this.limit = limit;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < limit; ++i) {
			synchronized (sharedQueue) {
				while (sharedQueue.size() >= queue_limit) {
					try {
						sharedQueue.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				sharedQueue.add(message);
				sharedQueue.notifyAll();
			}
			message++;
		}
	}

}
