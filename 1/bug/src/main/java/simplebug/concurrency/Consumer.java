package simplebug.concurrency;

import java.util.Queue;

public class Consumer extends Thread {

	private Queue<Integer> dataQueue;
	private int limit = 10000;
	private int lastMessage = -1;
	public Consumer(Queue<Integer> data, int limit) {
		this.dataQueue = data;
		this.limit = limit;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < limit-1; ++i) {
			synchronized (dataQueue) {
				while (dataQueue.isEmpty()) {
					try {
						dataQueue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				lastMessage = dataQueue.poll();
				dataQueue.notify();
			}
		}
	}
	public int getLastMessage() {
		return lastMessage;
	}
	
}
