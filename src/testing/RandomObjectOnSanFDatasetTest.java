package testing;

import algorithm.RandomObjectGenerator;
import framework.Graph;
import framework.UtilsManagment;

public class RandomObjectOnSanFDatasetTest {
	public static void main(String[] args) {
		UtilsManagment um = new UtilsManagment();
		Graph sanFGraph = new Graph("SanFrancisco");

		String nodeDatasetFile = "Datasets/SANF-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/SANF-Edge_Eid-ESrc-EDest-EDist.csv";

		um.readNodeFile(sanFGraph, nodeDatasetFile);
		um.readEdgeFile(sanFGraph, edgeDatasetFile);

		// calGraph.printGraph();
		RandomObjectGenerator.generateRandomObjectsOnMap(sanFGraph, 10000, 20000);
		RandomObjectGenerator.printStatistics();
		System.out.println("Finished Generating");
		um.writeRoadObjsOnEdgeFile(sanFGraph.getObjectsOnEdges(), sanFGraph.getDatasetName());

	}

}
