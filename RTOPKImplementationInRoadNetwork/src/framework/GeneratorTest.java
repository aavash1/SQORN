package framework;

import java.util.ArrayList;

import algorithm.RandomObjectGenerator;

public class GeneratorTest {

	public static void main(String[] args) {
		UtilsManagment um = new UtilsManagment();
		Graph2 calGraph = new Graph2();
		RandomObjectGenerator rg = new RandomObjectGenerator();

		String calNodeDataset = "Datasets/CAL-Node_NId-NLong-NLat.csv";
		String calEdgeDataset = "Datasets/CAL-Edge_Eid-ESrc-EDest-EDist.csv";

		long startTimeCal = System.nanoTime();
		calGraph = um.readEdgeFileReturnGraph(calEdgeDataset);
//		ArrayList<Node> calNodesInfo = um.readNodeFile(calNodeDataset);
//		calGraph.setNodesWithInfo(calNodesInfo);

		ArrayList<Edge> calEdgeInfo = um.readEdgeFile(calEdgeDataset);
		calGraph.setEdgeWithInfo(calEdgeInfo);
		long graphLoadingTimeCal = System.nanoTime() - startTimeCal;
		double graphLoadingTimeDCal = (double) graphLoadingTimeCal / 1000000000.0;
		// calGraph.printGraph();
		System.out.println("Elapsed time of California dataset loading: " + graphLoadingTimeDCal + " seconds");

		startTimeCal = System.nanoTime();
		Graph2 calGraph2 = new Graph2();
		um.readEdgeFile(calGraph2, calEdgeDataset);

		graphLoadingTimeCal = System.nanoTime() - startTimeCal;
		graphLoadingTimeDCal = (double) graphLoadingTimeCal / 1000000000.0;
		// calGraph2.printGraph();
		System.out.println("Elapsed time of California dataset loading: " + graphLoadingTimeDCal + " seconds");

		
		//ranG.generateObjectOnEdge(gr);
		System.out.println("---This is after generator------");
		rg.generateRandomObjectsOnMap(calGraph2);
		//calGraph2.printGraph();
		calGraph2.printObjectOnEdge3();
	}
}
