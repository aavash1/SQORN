package testing;

import algorithm.RandomObjectGenerator;
import framework.Graph;
import framework.UtilsManagment;

public class RandomObjectOnOldenburgDatasetTest {
	public static void main(String[] args) {
		Graph oldenburgGraph = new Graph("Oldenburg");

		String nodeDatasetFile = "Datasets/OLDN-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/OLDN-Edge_Eid-ESrc-EDest-EDist.csv";

		UtilsManagment.readEdgeFile(oldenburgGraph, edgeDatasetFile);
		UtilsManagment.readNodeFile(oldenburgGraph, nodeDatasetFile);
		int trueObjSize = 100000;
		int falseObjSize = 10000;

		RandomObjectGenerator.generateRandomObjectsOnMap6(oldenburgGraph, trueObjSize, falseObjSize);
		RandomObjectGenerator.printStatistics();
		System.out.println("Finished Generating");
		UtilsManagment.writeRoadObjsOnEdgeFile1(oldenburgGraph.getObjectsOnEdges(), oldenburgGraph.getDatasetName(),trueObjSize,falseObjSize);

	}

}
