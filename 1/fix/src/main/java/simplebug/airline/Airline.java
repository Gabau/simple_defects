package simplebug.airline;

import simplebug.util.SimpleBug;

public class Airline implements SimpleBug<Void> {

    /*
     * Second parameter is the number of threads
     * Third parameter is the cushion
     */
	private static int numberThreads = 10;
	private static int cushion = 3;
	


	@Override
	public void startBug() {
	    Bug bug = new Bug("test",numberThreads,cushion);
	    bug.checkSales();
	}
	
	
	
	@Override
	public Void getResult() {
		// TODO Auto-generated method stub
		return null;
	}
}
