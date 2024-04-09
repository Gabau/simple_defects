package simplebug.wrongLock;

import java.util.LinkedList;

import javax.management.RuntimeErrorException;
import javax.security.auth.login.FailedLoginException;

import simplebug.util.SimpleBug;

/**
 * @author Xuan
 * Created on Apr 27, 2005
 * 
 * Test Case 1 
   number of threads that have a lock on data             :  1
   number of threads that have a wrong lock on the class :  1
 */
public class WrongLockBug implements SimpleBug<Void> {
    static int iNum1=10;
    static int iNum2=10;
    static boolean failed = false;
    
    public boolean isFailed() {
    	return failed;
    }
	
    @Override
    public void startBug() {
    	Data data=new Data();
    	WrongLock wl=new WrongLock(data);
    	LinkedList<Thread> threads = new LinkedList<Thread>();
    	for (int i=0;i<iNum1;i++) {
    		Thread a = new TClass1(wl);
    		threads.add(a);
    		a.start();	
    	}

    	for (int i=0;i<iNum2;i++) {
    		Thread a = new TClass2(wl);
    		threads.add(a);
    		a.start();
    	}
    	for (Thread thread : threads) {
    		try {
				thread.join();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
    	}
    }


	@Override
	public Void getResult() {
		// TODO Auto-generated method stub
		return null;
	}
}
