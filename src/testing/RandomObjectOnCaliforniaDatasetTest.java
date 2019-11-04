package testing;

import java.util.ArrayList;
import algorithm.RandomObjectGenerator;
import algorithm.RandomObjectGenerator2;
import framework.Edge;
import framework.Graph;
import framework.Node;
import framework.UtilsManagment;

public class RandomObjectOnCaliforniaDatasetTest {
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

		// calGraph.printGraph();

		// RandomObjectGenerator.generateRandomObjectsOnMap2(calGraph, 0.1);
		// RandomObjectGenerator.generateRandomObjectsOnMap5(calGraph, 0.259, 27000);
		RandomObjectGenerator2.generateRandomObjectsOnMap6(calGraph, 20000, 10000);
		System.out.println("Finished Generating");
		um.writeRoadObjsOnEdgeFile(calGraph.getObjectsOnEdges(), "California");
		um.writeDatasetStatistics(calGraph);
		// calGraph.printObjectsOnEdges();
		RandomObjectGenerator.printStatistics();

	}

}
