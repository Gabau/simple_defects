package org.codehaus.groovy.runtime.metaclass;

import org.junit.Test;

public class TestMemoryAwareMap {

    private static final MemoryAwareConcurrentReadMap map = new MemoryAwareConcurrentReadMap();

    @Test
    public void testScriptCompilation() {

        final int threadCount = 1;

        Thread[] ts = new Thread[threadCount * 2];
        for (int i = 0; i < threadCount; i++) {
            GetThread runner = new GetThread(i);
            PutThread runner2 = new PutThread(i);
            ts[i * 2] = runner2;
            ts[i * 2 + 1] = runner;
        }

        for (int i = 0; i < ts.length; i++) {
            ts[i].start();
        }

        for (int i = 0; i < ts.length; i++) {
            try {
                ts[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class GetThread extends Thread {

        private int index = 0;

        public GetThread(int index) {
            this.index = index;
        }

        public void run() {
            map.get(new Integer(index));
        }
    }

    public static class PutThread extends Thread {

        private int index = 0;

        public PutThread(int index) {
            this.index = index;
        }

        public void run() {
            map.put(new Integer(index), new Integer(index));
        }
    }


}
