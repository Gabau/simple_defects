package simplebug.concurrency;

import java.util.LinkedList;
import java.util.Queue;

import simplebug.util.SimpleBug;

public class ProducerConsumer implements SimpleBug<Integer>{
	int prodMessage;
	int consMessage;

	@Override
	public void startBug() {
		Queue<Integer> sharedDataIntegers = new LinkedList<Integer>();
		int limit = 100;
		Producer producer = new Producer(sharedDataIntegers, limit);
		Consumer consumer = new Consumer(sharedDataIntegers, limit);
		producer.start();
		consumer.start();
	
		try {
			consumer.join();
			producer.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		consMessage = consumer.getLastMessage();
	}
	
	@Override
	public Integer getResult() {
		return consMessage;
	}
	
}

