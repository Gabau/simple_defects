package simplebug.datadeps;

import java.util.ArrayList;

import simplebug.util.SimpleBug;

/**
 * A more complicated data dependency bug with the master slave paradigm
 */
public class DataDependencyBug implements SimpleBug<Integer> {

	int aResult = 0;
	int bResult = 0;
	int cResult = 0;
	
	/**
	 * 3 Threads
	 * 
	 * A starts computation
	 * B starts computation
	 * C relies on computation in A and B
	 */
	class A extends Thread {
		@Override
		public void run() {
			// the bug
			aResult = 10;
			for (int i = 0; i < 1000; ++i) {
				aResult++;
			}
		}
	}
	
	class B extends Thread {
		@Override
		public void run() {
			bResult = 10;
			for (int i = 0; i < 1000; ++i) {
				bResult++;
			}
		}
	}
	
	class C extends Thread {
		@Override
		public void run() {
			cResult = aResult + bResult;
		}
	}
	
	@Override
	public void startBug() {
		B b = new B();
		A a = new A();
		b.start();
		a.start();
		try {
			b.join();
			a.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		C c= new C();
		c.start();
		try {
			c.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Integer getResult() {
		return cResult;
	}

}
