package simplebug.deadlock;

public class SimpleDeadLock implements Runnable {
    static Lock lock1;
    static Lock lock2;
    static int  state; 

    @Override
    public void run() {
        lock1 = new Lock();
        lock2 = new Lock();
        Process1 p1 = new Process1();
        Process2 p2 = new Process2();
        p1.start();
        p2.start();
    }
}

class Process1 extends Thread {
    public void run() {
    	SimpleDeadLock.state++;
        synchronized (SimpleDeadLock.lock1) {
            synchronized (SimpleDeadLock.lock2) {
            	SimpleDeadLock.state++;
            }
        }
    }
}

class Process2 extends Thread {
    public void run() {
    	SimpleDeadLock.state++;
        synchronized (SimpleDeadLock.lock2) {
            synchronized (SimpleDeadLock.lock1) {
            	SimpleDeadLock.state++;
            }
        }
    }
}

class Lock {
}