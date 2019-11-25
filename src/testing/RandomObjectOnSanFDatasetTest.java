package testing;

import algorithm.RandomObjectGenerator;
import framework.Graph;
import framework.UtilsManagment;

public class RandomObjectOnSanFDatasetTest {
	public static void main(String[] args) {
		Graph sanFGraph = new Graph("SanFrancisco");

		String nodeDatasetFile = "Datasets/SANF-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/SANF-Edge_Eid-ESrc-EDest-EDist.csv";

		UtilsManagment.readNodeFile(sanFGraph, nodeDatasetFile);
		UtilsManagment.readEdgeFile(sanFGraph, edgeDatasetFile);

		// calGraph.printGraph();
		RandomObjectGenerator.generateRandomObjectsOnMap6(sanFGraph, 10000, 110000);
		RandomObjectGenerator.printStatistics();
		System.out.println("Finished Generating");
		UtilsManagment.writeRoadObjsOnEdgeFile(sanFGraph.getObjectsOnEdges(), sanFGraph.getDatasetName());

	}

}
