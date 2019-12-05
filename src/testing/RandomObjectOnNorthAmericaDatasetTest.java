package testing;

import algorithm.RandomObjectGenerator;
import framework.Graph;
import framework.UtilsManagment;

public class RandomObjectOnNorthAmericaDatasetTest {
	public static void main(String[] args) {

		Graph northAmericaGraph = new Graph("NorthAmerica");

		String nodeDatasetFile = "Datasets/NA-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/NA-Edge_Eid-ESrc-EDest-EDist.csv";
		int trueObjSize = 100000;
		int falseObjSize = 10000;

		UtilsManagment.readEdgeFile(northAmericaGraph, edgeDatasetFile);
		UtilsManagment.readNodeFile(northAmericaGraph, nodeDatasetFile);

		RandomObjectGenerator.generateRandomObjectsOnMap6(northAmericaGraph, trueObjSize, falseObjSize);
		RandomObjectGenerator.printStatistics();
		System.out.println("Finished Generating");
		UtilsManagment.writeRoadObjsOnEdgeFile1(northAmericaGraph.getObjectsOnEdges(),
				northAmericaGraph.getDatasetName(),trueObjSize,falseObjSize);

	}

}
