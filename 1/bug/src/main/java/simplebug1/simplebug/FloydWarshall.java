package simplebug1.simplebug;

import java.util.Arrays;
import java.util.LinkedList;

import simplebug.util.SimpleBug;

/**
 * Multi threaded implementation of floyd warshall
 */
public class FloydWarshall implements SimpleBug<Integer> {
	
	private int u;
	private int v;
	private int[][] distGraph;
	private int[][] graph;
	
	private class RowThread extends Thread {
		int i;
		int k;
		
		public RowThread(int row, int k) {
			this.i = row;
			this.k = k;
		}
		
		public void run() {
			for (int j = 0; j < distGraph[i].length; ++j) {
				if (distGraph[i][k] + distGraph[j][k] < distGraph[i][j]) {
					distGraph[i][j] = distGraph[i][k] + distGraph[k][j];
				}
			}
		}
	}
	
	public static FloydWarshall fromEdgeList(int[][] edgeList) { 
		int max_index = 0;
		for (int i = 0; i < edgeList.length; ++i) {
			max_index = Math.max(edgeList[i][0], max_index);
			max_index = Math.max(edgeList[i][1], max_index);
		}
		int[][] graph = new int[max_index+1][max_index+1];
		int INFINITY = 10000;
		for (int i = 0; i < graph.length; ++i)
			Arrays.fill(graph[i], INFINITY);
		for (int i = 0; i < edgeList.length; ++i) {
			graph[edgeList[i][0]][edgeList[i][1]] = edgeList[i][2];
		}
		return new FloydWarshall(graph);
	}
	
	
	public FloydWarshall(int[][] graph) {
		distGraph = new int[graph.length][graph[0].length];
		for (int i = 0; i < distGraph.length; ++i) {
			for (int j = 0; j < distGraph[i].length; ++j) {
				distGraph[i][j]  = graph[i][j];
			}
		}
		this.graph = graph;
	}
	
	public void setUV(int u, int v) {
		this.u = u;
		this.v = v;
	}
	
	public int getResult(int u, int v) {
		setUV(u, v);
		return getResult();
	}
	
	@Override
	public Integer getResult() {
		return distGraph[u][v];
	}
	

	@Override
	public void startBug() {
		int m = distGraph.length;
		int n = distGraph[0].length;
		LinkedList<RowThread> rowThreads = new LinkedList<FloydWarshall.RowThread>();
		// assign a thread to each row
		for (int k = 0; k < distGraph.length; ++k) {
			// initial the row threads
			for (int i = 0; i < distGraph.length; ++i) {
				RowThread rThread = new RowThread(i, k);
				rowThreads.add(rThread);
				rThread.start();
			}
			for (RowThread rowThread : rowThreads) {
				try {
					rowThread.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			rowThreads.clear();
		}
	}
}
