package testing;

import algorithm.RandomObjectGenerator;
import framework.Graph;
import framework.UtilsManagment;

public class RandomObjectOnOldenburgDatasetTest {
	public static void main(String[] args) {
		UtilsManagment um = new UtilsManagment();
		Graph oldenburgGraph = new Graph("Oldenburg");

		String nodeDatasetFile = "Datasets/OLDN-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/OLDN-Edge_Eid-ESrc-EDest-EDist.csv";

		um.readEdgeFile(oldenburgGraph, edgeDatasetFile);
		um.readNodeFile(oldenburgGraph, nodeDatasetFile);

		RandomObjectGenerator.generateRandomObjectsOnMap(oldenburgGraph, 100000, 10000);
		RandomObjectGenerator.printStatistics();
		System.out.println("Finished Generating");
		um.writeRoadObjsOnEdgeFile(oldenburgGraph.getObjectsOnEdges(), oldenburgGraph.getDatasetName());

	}

}
