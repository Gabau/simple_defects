package simplebug1.simplebug;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import simplebug.airline.Airline;
import simplebug.badthreads.BadThreads;
import simplebug.badthreads.BadThreads2;
import simplebug.badthreads.BadThreads3;
import simplebug.concurrency.ProducerConsumer;
import simplebug.datadeps.DataDependencyBug;
import simplebug.datadeps.DataDependencyBug2;
import simplebug.datadeps.DataDependencyBug3;
import simplebug.deadlock.SimpleDeadLock;
import simplebug.faultypetersons.FaultyPetersons;
import simplebug.goodthreads.GoodThreads;
import simplebug.goodthreads.GoodThreads2;
import simplebug.goodthreads.GoodThreads3;
import simplebug.massiverace.LargeRace;
import simplebug.util.SimpleBug;
import simplebug.wrongLock.WrongLockBug;

public class TestObjectTest {
	@Test
	public void simpleTest() {
		TestObject testObject = new TestObject(100);
		testObject.start();
		try {
			testObject.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(testObject.getValue(), 102);
	}
	
	
	@Test
	public void testRace() {
		LargeRace race = new LargeRace();
		race.run();
		assertEquals(LargeRace.getGlobal(), 2000);
	}
	
	
	private void testSimpleBug(SimpleBug<Integer> intBug, int expected) {
		intBug.startBug();
		int result = intBug.getResult();
		assertEquals(expected, result);
	}
	
	@Test
	public void testSequentialBug() {
		SequentialBugInThread sequentialBugInThread = new SequentialBugInThread();
		sequentialBugInThread.setInput(10);
		testSimpleBug(sequentialBugInThread, 55);
	}
	@Test
	public void testFloydWarshall() {
		final int INFINITY = 10000;
	
		int[][] input = {
				{INFINITY, 2, 2},
				{-1, 0, -1},
				{INFINITY, INFINITY, 0},
		};
		
		FloydWarshall fWarshall = new FloydWarshall(input);
		fWarshall.startBug();
		fWarshall.setUV(0, 2);
		assertEquals(1, (int) fWarshall.getResult());
	}

	@Test
	public void testFloydWarshall2() {
		int[][] input = {
				{0, 1, 4},
				{0, 2, 3},
				{1, 3, 7},
				{2, 3, -2}
		};
		
		FloydWarshall fWarshall = FloydWarshall.fromEdgeList(input);
		fWarshall.startBug();
		assertEquals(1, fWarshall.getResult(0, 3));
	}
	
	
	@Test(timeout=1000)
	public void testProdCons() {
		SimpleBug<Integer> prodConSimpleBug = new ProducerConsumer();
		prodConSimpleBug.startBug();
		testSimpleBug(prodConSimpleBug, 99);
	}
	
	@Test(timeout=1000)
	public void testPetersons() {
		FaultyPetersons fPetersons = new FaultyPetersons();
		fPetersons.setMaxIter(1000);
		testSimpleBug(fPetersons, 2000);
	}
	
	@Test
	public void testControlDeps() {
		ControlDepsBug bug = new ControlDepsBug();
		testSimpleBug(bug, 1);
	}
	
	
	@Test
	public void testAirLine() {
		Airline airline = new Airline();
		airline.startBug();
	}
	
	@Test
	public void testWrongLock() {
		WrongLockBug wrongLockBug = new WrongLockBug();
		wrongLockBug.startBug();
	}
	
	@Test
	public void testDataDeps1() {
		DataDependencyBug dataDependencyBug = new DataDependencyBug();
		dataDependencyBug.startBug();
		int result = dataDependencyBug.getResult();
		assertEquals(2020, result);
	}
	

	@Test
	public void testDeadLock() {
		SimpleDeadLock simpleDeadLock = new SimpleDeadLock();
		simpleDeadLock.run();
	}
	
	
	@Test
	public void testDataDeps2() {
		DataDependencyBug2 dataDependencyBug = new DataDependencyBug2();
		dataDependencyBug.startBug();
		int result = dataDependencyBug.getResult();
		assertEquals(2020, result);
	}
	
	@Test
	public void testDataDeps3() {
		DataDependencyBug3 dataDependencyBug = new DataDependencyBug3();
		dataDependencyBug.startBug();
		int result = dataDependencyBug.getResult();
		assertEquals(2020, result);
	}
	
	@Test
	public void testBadThreads() {
		BadThreads badThreads = new BadThreads();
		testSimpleBug(badThreads, 0);
	}

	@Test
	public void testBadThreads2() {
		BadThreads2 badThreads = new BadThreads2();
		testSimpleBug(badThreads, 0);
	}
	
	@Test
	public void testBadThreads3() {
		BadThreads3 badThreads = new BadThreads3();
		testSimpleBug(badThreads, 0);
	}
	
	
	@Test
	public void testGoodThreads1() {
		GoodThreads goodThreads = new GoodThreads();
		testSimpleBug(goodThreads, 0);
	}
	@Test
	public void testGoodThreads2() {
		GoodThreads2 goodThreads = new GoodThreads2();
		testSimpleBug(goodThreads, 0);
	}
	
	@Test
	public void testGoodThreads3() {
		GoodThreads3 goodThreads = new GoodThreads3();
		testSimpleBug(goodThreads, 0);
	}
	
}
