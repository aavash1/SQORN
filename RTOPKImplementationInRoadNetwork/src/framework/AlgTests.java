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

		String snfNodeDataset = "Datasets/SANF-Node_NId-NLong-NLat.csv";
		String snfEdgeDataset = "Datasets/SANF-Edge_Eid-ESrc-EDest-EDist.csv";
		
		String oldnNodeDataset = "Datasets/OLDN-Node_NId-NLong-NLat.csv";
		String oldnEdgeDataset = "Datasets/OLDN-Edge_EId-ESrc-EDest-EDist.csv";
		
		/*
		// Graph loading:
		Graph calGraph = um.readMergedPOI(calMergedDataset);
		//calGraph.printGraph();
		ArrayList<Node> calNodesInfo = um.readNodeFile(calNodeDataset);
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
		LinkedList<Node> pathAStarCal = dijkAlgCal.getPath(destNodeForDijkAlgCal);
		long AStarCalTime = System.nanoTime() - startTimeAStarCal;
		double AStarCalTimeD = (double) AStarCalTime / 1000000000.0;
		
		System.out.println("Source node: " + sourceNodeAStarAlgCal + " Dest node: " + destNodeAStarAlgCal );
		for (Node node : pathAStarCal) {
			System.out.println(node);
		}		
		
		System.out.println("Elapsed time of A* algorithm on California dataset: " + AStarCalTimeD + " seconds");	
		*/
		///*
		// ----------------------------------------------------------
		//San Francisco Dataset		
		// Graph loading:
		Graph snfGraph = um.readEdgeFileReturnGraph(snfEdgeDataset);
		snfGraph.printGraph();
		ArrayList<Node> snfNodesInfo = um.readNodeFile(snfNodeDataset);
		snfGraph.setNodesWithInfo(snfNodesInfo);
		
		System.out.println("Dijkstra algorithm on San Francisco dataset");
		DijkstraAlgorithm dijkAlgSnf = new DijkstraAlgorithm(snfGraph);
		
		Node sourceNodeForDijkAlgSnf, destNodeForDijkAlgSnf;
		sourceNodeForDijkAlgSnf = snfNodesInfo.get(0);		
		destNodeForDijkAlgSnf = snfNodesInfo.get(15000);
		
		long startTimeDijkstraSnf = System.nanoTime();
		dijkAlgSnf.execute(sourceNodeForDijkAlgSnf);
		LinkedList<Node> pathDijkSnf = dijkAlgSnf.getPath(destNodeForDijkAlgSnf);
		long DijkstraSnfTime = System.nanoTime() - startTimeDijkstraSnf;
		double DijkstraSnfTimeD = (double) DijkstraSnfTime / 1000000000.0;

		System.out.println("Source node: " + sourceNodeForDijkAlgSnf + " Dest node: " + destNodeForDijkAlgSnf );
		for (Node node : pathDijkSnf) {
			System.out.println(node);
		}
		System.out.println("Elapsed time of Dijkstra algorithm on San Francisco dataset: " + DijkstraSnfTimeD + " seconds");
		
		//-----------------------------------------------------------------------------------------------------------------------
		System.out.println("A* algorithm on San Francisco dataset");
		AStarAlgorithm aStarAlgSnf = new AStarAlgorithm(snfGraph);
		
		Node sourceNodeAStarAlgSnf, destNodeAStarAlgSnf;
		sourceNodeAStarAlgSnf = snfNodesInfo.get(0);		
		destNodeAStarAlgSnf = snfNodesInfo.get(1400);

		long startTimeAStarSnf = System.nanoTime();
		aStarAlgSnf.execute(sourceNodeAStarAlgSnf, destNodeAStarAlgSnf);
		LinkedList<Node> pathAStarSnf = aStarAlgSnf.getPath(destNodeAStarAlgSnf);
		long AStarSnfTime = System.nanoTime() - startTimeAStarSnf;
		double AStarSnfTimeD = (double) AStarSnfTime / 1000000000.0;
		
		System.out.println("Source node: " + sourceNodeAStarAlgSnf + " Dest node: " + destNodeAStarAlgSnf );
		for (Node node : pathAStarSnf) {
			System.out.println(node);
		}		
		
		System.out.println("Elapsed time of A* algorithm on San Francisco dataset: " + AStarSnfTimeD + " seconds");// Test of Dijkstra and A* algorithms on San Francisco dataset
		//*/
		/*
		// ----------------------------------------------------------
		//Oldenburg Dataset		
		// Graph loading:
		Graph oldnGraph = um.readEdgeFileReturnGraph(oldnEdgeDataset);
		oldnGraph.printGraph();
		ArrayList<Node> oldnNodesInfo = um.readNodeFile(oldnNodeDataset);
		oldnGraph.setNodesWithInfo(oldnNodesInfo);
		
		// Test of Dijkstra and A* algorithms on Oldenburg dataset
		System.out.println("Dijkstra algorithm on Oldenburg dataset");
		DijkstraAlgorithm dijkAlgOldn = new DijkstraAlgorithm(oldnGraph);
		
		Node sourceNodeForDijkAlgOldn, destNodeForDijkAlgOldn;
		sourceNodeForDijkAlgOldn = oldnNodesInfo.get(0);		
		destNodeForDijkAlgOldn = oldnNodesInfo.get(15000);
		
		long startTimeDijkstraOldn = System.nanoTime();
		dijkAlgOldn.execute(sourceNodeForDijkAlgOldn);
		LinkedList<Node> pathDijkOldn = dijkAlgOldn.getPath(destNodeForDijkAlgOldn);
		long DijkstraOldnTime = System.nanoTime() - startTimeDijkstraOldn;
		double DijkstraOldnTimeD = (double) DijkstraOldnTime / 1000000000.0;

		System.out.println("Source node: " + sourceNodeForDijkAlgOldn + " Dest node: " + destNodeForDijkAlgOldn );
		for (Node node : pathDijkOldn) {
			System.out.println(node);
		}
		System.out.println("Elapsed time of Dijkstra algorithm on Oldenburg dataset: " + DijkstraOldnTimeD + " seconds");
		
		//-----------------------------------------------------------------------------------------------------------------------
		System.out.println("A* algorithm on Oldenburg dataset");
		AStarAlgorithm aStarAlgOldn = new AStarAlgorithm(oldnGraph);
		
		Node sourceNodeAStarAlgOldn, destNodeAStarAlgOldn;
		sourceNodeAStarAlgOldn = oldnNodesInfo.get(0);		
		destNodeAStarAlgOldn = oldnNodesInfo.get(1400);

		long startTimeAStarOldn = System.nanoTime();
		aStarAlgOldn.execute(sourceNodeAStarAlgOldn, destNodeAStarAlgOldn);
		LinkedList<Node> pathAStarOldn = aStarAlgOldn.getPath(destNodeAStarAlgOldn);
		long AStarOldnTime = System.nanoTime() - startTimeAStarOldn;
		double AStarOldnTimeD = (double) AStarOldnTime / 1000000000.0;
		
		System.out.println("Source node: " + sourceNodeAStarAlgOldn + " Dest node: " + destNodeAStarAlgOldn );
		for (Node node : pathAStarOldn) {
			System.out.println(node);
		}		
		
		System.out.println("Elapsed time of A* algorithm on Oldenburg dataset: " + AStarOldnTimeD + " seconds");
		*/
		
	}

}
