package testing;

import algorithm.RandomObjectGenerator;
import framework.Graph;
import framework.UtilsManagment;

public class RandomObjectOnNorthAmericaDatasetTest {
	public static void main(String[] args) {
		
		Graph northAmericaGraph = new Graph("NorthAmerica");

		String nodeDatasetFile = "Datasets/NA-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/NA-Edge_Eid-ESrc-EDest-EDist.csv";

		UtilsManagment.readEdgeFile(northAmericaGraph, edgeDatasetFile);
		UtilsManagment.readNodeFile(northAmericaGraph, nodeDatasetFile);

		RandomObjectGenerator.generateRandomObjectsOnMap6(northAmericaGraph, 10000,100000);
		RandomObjectGenerator.printStatistics();
		System.out.println("Finished Generating");
		UtilsManagment.writeRoadObjsOnEdgeFile(northAmericaGraph.getObjectsOnEdges(), northAmericaGraph.getDatasetName());

	}

}
