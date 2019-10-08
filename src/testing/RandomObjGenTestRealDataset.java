package testing;

import java.util.ArrayList;

import algorithm.RandomObjectGenerator;
import framework.Edge;
import framework.Graph;
import framework.Node;
import framework.UtilsManagment;

public class RandomObjGenTestRealDataset {

	public static void main(String[] args) {
		UtilsManagment um = new UtilsManagment();
		Graph calGraph = new Graph();

		String calNodeDataset = "Datasets/CAL-Node_NId-NLong-NLat.csv";
		String calEdgeDataset = "Datasets/CAL-Edge_Eid-ESrc-EDest-EDist.csv";

		long startTimeCal;
		long graphLoadingTimeCal;
		double graphLoadingTimeDCal;
		startTimeCal = System.nanoTime();
		
		calGraph = um.readEdgeFileReturnGraph(calEdgeDataset);
		ArrayList<Node> calNodesInfo = um.readNodeFile(calNodeDataset);
		calGraph.setNodesWithInfo(calNodesInfo);
		ArrayList<Edge> calEdgeInfo = um.readEdgeFile(calEdgeDataset);
		calGraph.setEdgeWithInfo(calEdgeInfo);
		graphLoadingTimeCal = System.nanoTime() - startTimeCal;
		graphLoadingTimeDCal = (double) graphLoadingTimeCal / 1000000000.0;
		calGraph.printGraph();
		System.out.println("Elapsed time of California dataset loading: " + graphLoadingTimeDCal + " seconds");

		startTimeCal = System.nanoTime();
		Graph calGraph2 = new Graph();
		um.readEdgeFile(calGraph2, calEdgeDataset);

		graphLoadingTimeCal = System.nanoTime() - startTimeCal;
		graphLoadingTimeDCal = (double) graphLoadingTimeCal / 1000000000.0;
		// calGraph2.printGraph();
		System.out.println("Elapsed time of California dataset loading: " + graphLoadingTimeDCal + " seconds");

		System.out.println("---This is after generator------");
		RandomObjectGenerator.generateRandomObjectsOnMap(calGraph2);
		calGraph2.printGraph();
		calGraph2.printObjectsOnEdges();

	}
}
