package framework;

import java.util.ArrayList;
import java.util.LinkedList;

import algorithm.AStarAlgorithm;
import algorithm.DijkstraAlgorithm;

public class AlgTests {

	public static void main(String[] args) {
		UtilsManagment um = new UtilsManagment();

		String calNodeDataset = "Datasets/CAL-Node_NId-NLong-NLat.csv";
		String calMergedDataset = "Datasets/CALMergerdPOI_Start-End-EdgeLen-NumofPOI.txt";

		// Graph loading:
		Graph calGraph = um.readMergedPOI(calMergedDataset);
		//calGraph.printGraph();
		ArrayList<Node> calNodesInfo = um.readVertexFiles(calNodeDataset);
		calGraph.setNodesWithInfo(calNodesInfo);

		// Test of Dijkstra and A* algorithms on California dataset
		// ----------------------------------------------------------
		System.out.println("Dijkstra algorithm on California dataset");
		DijkstraAlgorithm dijkAlgCal = new DijkstraAlgorithm(calGraph);
		
		Node sourceNodeForDijkAlgCal, destNodeForDijkAlgCal;
		sourceNodeForDijkAlgCal = calNodesInfo.get(0);		
		destNodeForDijkAlgCal = calNodesInfo.get(15000);
		
		long startTimeDijkstraCal = System.nanoTime();
		dijkAlgCal.execute(sourceNodeForDijkAlgCal);
		LinkedList<Node> pathDijkCal = dijkAlgCal.getPath(destNodeForDijkAlgCal);
		long DijkstraCalTime = System.nanoTime() - startTimeDijkstraCal;
		double DijkstraCalTimeD = (double) DijkstraCalTime / 1000000000.0;

		System.out.println("Source node: " + sourceNodeForDijkAlgCal + " Dest node: " + destNodeForDijkAlgCal );
		for (Node node : pathDijkCal) {
			System.out.println(node);
		}
		System.out.println("Elapsed time of Dijkstra algorithm on California dataset: " + DijkstraCalTimeD + " seconds");
		
		//-----------------------------------------------------------------------------------------------------------------------
		System.out.println("A* algorithm on California dataset");
		AStarAlgorithm aStarAlgCal = new AStarAlgorithm(calGraph);
		
		Node sourceNodeAStarAlgCal, destNodeAStarAlgCal;
		sourceNodeAStarAlgCal = calNodesInfo.get(0);		
		destNodeAStarAlgCal = calNodesInfo.get(1400);

		long startTimeAStarCal = System.nanoTime();
		aStarAlgCal.execute(sourceNodeAStarAlgCal, destNodeAStarAlgCal);
		LinkedList<Node> pathAStar = dijkAlgCal.getPath(destNodeForDijkAlgCal);
		long AStarCalTime = System.nanoTime() - startTimeAStarCal;
		double AStarCalTimeD = (double) AStarCalTime / 1000000000.0;
		
		System.out.println("Source node: " + sourceNodeAStarAlgCal + " Dest node: " + destNodeAStarAlgCal );
		for (Node node : pathAStar) {
			System.out.println(node);
		}		
		
		System.out.println("Elapsed time of A* algorithm on California dataset: " + AStarCalTimeD + " seconds");	
		
		
		// ----------------------------------------------------------
		// Test of Dijkstra and A* algorithms on __ dataset

	}

}
