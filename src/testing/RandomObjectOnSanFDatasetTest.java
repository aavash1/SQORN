package testing;

import java.util.ArrayList;
import algorithm.RandomObjectGenerator;
import algorithm.RandomObjectGenerator2;
import framework.Edge;
import framework.Graph;
import framework.Node;
import framework.UtilsManagment;

public class RandomObjectOnSanFDatasetTest {
	public static void main(String[] args) {
		UtilsManagment um = new UtilsManagment();
		Graph sanFGraph = new Graph();

		String nodeDatasetFile = "Datasets/SANF-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/SANF-Edge_Eid-ESrc-EDest-EDist.csv";

		sanFGraph = um.readEdgeFileReturnGraph(edgeDatasetFile);
		ArrayList<Node> sanFNodesInfo = um.readNodeFile(nodeDatasetFile);
		sanFGraph.setNodesWithInfo(sanFNodesInfo);
		ArrayList<Edge> sanFEdgeInfo = um.readEdgeFile(edgeDatasetFile);
		sanFGraph.setEdgeWithInfo(sanFEdgeInfo);

		// calGraph.printGraph();

		// RandomObjectGenerator.generateRandomObjectsOnMap2(calGraph, 0.1);
		// RandomObjectGenerator.generateRandomObjectsOnMap5(calGraph, 0.259, 27000);
		RandomObjectGenerator2.generateRandomObjectsOnMap6(sanFGraph, 7000, 20000);
		System.out.println("Finished Generating");
		um.writeRoadObjsOnEdgeFile(sanFGraph.getObjectsOnEdges(), "SanFrancisco");
		um.writeDatasetStatistics(sanFGraph);
		// calGraph.printObjectsOnEdges();
		RandomObjectGenerator.printStatistics();

	}

}
