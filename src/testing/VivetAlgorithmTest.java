package testing;

import java.util.ArrayList;

import algorithm.ANNNaive;
import algorithm.RandomObjectGenerator;
import algorithm.VivetAlgorithm;
import framework.Edge;
import framework.Graph;
import framework.Node;
import framework.UtilsManagment;

public class VivetAlgorithmTest {

	public static void main(String[] args) {
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
		Graph calGraph = new Graph();
		calGraph.setDatasetName("California");

		String nodeDatasetFile = "Datasets/CAL-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/CAL-Edge_Eid-ESrc-EDest-EDist.csv";

		calGraph = UtilsManagment.readEdgeFileReturnGraph(edgeDatasetFile);
		ArrayList<Node> calNodesInfo = UtilsManagment.readNodeFile(nodeDatasetFile);
		calGraph.setNodesWithInfo(calNodesInfo);
		ArrayList<Edge> calEdgeInfo = UtilsManagment.readEdgeFile(edgeDatasetFile);
		calGraph.setEdgeWithInfo(calEdgeInfo);

		// calGraph.printGraph();
		
		//TODO: check version of generator and input parameters
		RandomObjectGenerator.generateRandomObjectsOnEdgeWithCentroidForSameDistribution(calGraph, 100, 100, 1.6);
		System.out.println("Objects generated");
		VivetAlgorithm vivetAlg = new VivetAlgorithm();
		long startTimeVivet = System.nanoTime();
		vivetAlg.compute(calGraph);
		long vivetAlgTime = System.nanoTime() - startTimeVivet;
		double vivetAlgTimeD = (double) vivetAlgTime / 1000000000.0;
		System.out.println("Time to compute Vivet ANN: " + vivetAlgTimeD);
		//RandomObjectGenerator.generateRandomObjectsOnMap5(calGraph,0.2,20000);
		
//		calGraph.printObjectsOnEdges();
		

		
	}

}
