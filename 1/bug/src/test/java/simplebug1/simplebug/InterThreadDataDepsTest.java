package simplebug1.simplebug;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class InterThreadDataDepsTest {
	@Test
	public void simpleTest() {
		InterThreadDataDeps deps = new InterThreadDataDeps();
		deps.startBug();
		int result = deps.getResult();
		assertEquals(result, 100);
	}
}
