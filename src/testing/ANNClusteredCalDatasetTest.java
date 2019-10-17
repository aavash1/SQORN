package testing;

import java.util.ArrayList;

import algorithm.ANNClustered;
import algorithm.ANNNaive;
import algorithm.RandomObjectGenerator;
import framework.Edge;
import framework.Graph;
import framework.Node;
import framework.RoadObject;
import framework.UtilsManagment;

public class ANNClusteredCalDatasetTest {

	public static void main(String[] args) {
		UtilsManagment um = new UtilsManagment();
		Graph calGraph = new Graph();

		String nodeDatasetFile = "Datasets/CAL-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/CAL-Edge_Eid-ESrc-EDest-EDist.csv";

		calGraph = um.readEdgeFileReturnGraph(edgeDatasetFile);
		ArrayList<Node> calNodesInfo = um.readNodeFile(nodeDatasetFile);
		calGraph.setNodesWithInfo(calNodesInfo);
		ArrayList<Edge> calEdgeInfo = um.readEdgeFile(edgeDatasetFile);
		calGraph.setEdgeWithInfo(calEdgeInfo);

		//RandomObjectGenerator.generateRandomObjectsOnMap(calGraph, 0.2);
		

		ANNClustered ann3 = new ANNClustered();
		long startTimeClustered = System.nanoTime();
		ann3.compute(calGraph, true);
		long graphLoadingTimeClustered = System.nanoTime() - startTimeClustered;
		double graphLoadingTimeDClustered = (double) graphLoadingTimeClustered / 1000000000.0;
		// ann3.printNearestSets();
		System.out.println("Time to compute Clustered ANN: " + graphLoadingTimeDClustered);
	}

}
