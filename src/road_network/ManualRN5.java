package road_network;

import framework.Graph;

public class ManualRN5 {

	// Manual graph originally was designed to test Gaussian Centroid Distribution of Road Objects 
	
	private static Graph gr = new Graph();
	public static Graph getGraph () { 
		initialize();
		return gr;
	}
	
	private static void initialize() { 
		
		gr.addEdge(1, 2, 5);
		gr.addEdge(1, 6, 3);
		gr.addEdge(2, 3, 6);
		gr.addEdge(2, 7, 6);
		gr.addEdge(3, 4, 4);
		gr.addEdge(3, 8, 6);
		gr.addEdge(4, 5, 9);
		gr.addEdge(4, 8, 7);
		gr.addEdge(5, 9, 7);
		gr.addEdge(5, 10, 8);
		gr.addEdge(6, 11, 6);
		gr.addEdge(7, 8, 9);
		gr.addEdge(7, 12, 5);
		gr.addEdge(7, 13, 7);
		gr.addEdge(8, 13, 10);
		gr.addEdge(8, 9, 6);
		gr.addEdge(9, 10, 4);
		gr.addEdge(10, 15, 4);
		gr.addEdge(11, 18, 11);
		gr.addEdge(11, 12, 6);
		gr.addEdge(12, 16, 10);
		gr.addEdge(13, 14, 8);
		gr.addEdge(13, 16, 6);
		gr.addEdge(14, 15, 8);
		gr.addEdge(15, 17, 14);
		gr.addEdge(15, 20, 6);
		gr.addEdge(16, 17, 6);
		gr.addEdge(16, 18, 5);
		gr.addEdge(17, 19, 3);
		gr.addEdge(18, 19, 12);
		gr.addEdge(19, 20, 10);
		
	}
	
}
