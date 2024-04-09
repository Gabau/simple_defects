package simplebug.faultypetersons;

import simplebug.util.SimpleBug;

public class FaultyPetersons implements SimpleBug<Integer> {
	int shared = 0;

	int MAX_ITER = 100;
	
	public void setMaxIter(int v) {
		this.MAX_ITER = v;
	}

	// https://stackoverflow.com/questions/2911915/peterson-algorithm-in-java
	volatile boolean[] flag = {false, false};
	volatile int turn = 0;
	int global = 0;
	void lock(int id) {
		int other = 1 - id;
		turn = other;
		flag[id] = true;
//		turn = other; // the correct position for turn = other

		while (flag[other] && turn == other) {

		}
	}

	void unlock(int id) {
		flag[id] = false;
	}

	class Process extends Thread {
		int id;
		public Process(int id) {
			assert(id == 1 || id == 0);
			this.id = id;
		}


		@Override
		public void run() {
			for (int i = 0; i < MAX_ITER; ++i) {
				lock(id);
				global++;
				unlock(id);
			}
		}
		
	}

	public void start() {
		Process process0 = new Process(0);
		Process process1 = new Process(1);
		process1.start(); process0.start();
		try {
			process0.join();
			process1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void startBug() {
		this.start();
	}

	@Override
	public Integer getResult() {
		return this.global;
	}
	
	
}
