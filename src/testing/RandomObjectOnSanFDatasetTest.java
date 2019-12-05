package testing;

import algorithm.RandomObjectGenerator;
import framework.Graph;
import framework.UtilsManagment;

public class RandomObjectOnSanFDatasetTest {
	public static void main(String[] args) {
		Graph sanFGraph = new Graph("SanFrancisco");

		String nodeDatasetFile = "Datasets/SANF-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/SANF-Edge_Eid-ESrc-EDest-EDist.csv";
		int trueObjSize = 100000;
		int falseObjSize = 10000;

		UtilsManagment.readNodeFile(sanFGraph, nodeDatasetFile);
		UtilsManagment.readEdgeFile(sanFGraph, edgeDatasetFile);

		// calGraph.printGraph();
		RandomObjectGenerator.generateRandomObjectsOnMap6(sanFGraph, trueObjSize, falseObjSize);
		RandomObjectGenerator.printStatistics();
		System.out.println("Finished Generating");
		UtilsManagment.writeRoadObjsOnEdgeFile1(sanFGraph.getObjectsOnEdges(), sanFGraph.getDatasetName(),trueObjSize,falseObjSize);

	}

}
