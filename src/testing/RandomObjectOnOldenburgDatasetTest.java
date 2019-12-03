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

		RandomObjectGenerator.generateRandomObjectsOnMap6(oldenburgGraph, 10000, 20000);
		RandomObjectGenerator.printStatistics();
		System.out.println("Finished Generating");
		UtilsManagment.writeRoadObjsOnEdgeFile1(oldenburgGraph.getObjectsOnEdges(), oldenburgGraph.getDatasetName());

	}

}
