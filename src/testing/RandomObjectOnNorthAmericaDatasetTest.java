package testing;

import algorithm.RandomObjectGenerator;
import framework.Graph;
import framework.UtilsManagment;

public class RandomObjectOnNorthAmericaDatasetTest {
	public static void main(String[] args) {
		UtilsManagment um = new UtilsManagment();
		Graph northAmericaGraph = new Graph("North America");

		String nodeDatasetFile = "Datasets/NA-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/NA-Edge_Eid-ESrc-EDest-EDist.csv";

		um.readEdgeFile(northAmericaGraph, edgeDatasetFile);
		um.readNodeFile(northAmericaGraph, nodeDatasetFile);

		RandomObjectGenerator.generateRandomObjectsOnMap(northAmericaGraph, 10000, 20000);
		RandomObjectGenerator.printStatistics();
		System.out.println("Finished Generating");
		um.writeRoadObjsOnEdgeFile(northAmericaGraph.getObjectsOnEdges(), northAmericaGraph.getDatasetName());

	}

}
