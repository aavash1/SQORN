package testing;

import algorithm.RandomObjectGenerator;
import framework.Graph;
import framework.UtilsManagment;

public class RandomObjectOnSanJoaDatasetTest {
	public static void main(String[] args) {
		UtilsManagment um = new UtilsManagment();
		Graph sanJoaGraph = new Graph("San Joaquin");

		String nodeDatasetFile = "Datasets/SJ-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/SJ-Edge_Eid-ESrc-EDest-EDist.csv";

		um.readEdgeFile(sanJoaGraph, edgeDatasetFile);
		um.readNodeFile(sanJoaGraph, nodeDatasetFile);

		RandomObjectGenerator.generateRandomObjectsOnMap(sanJoaGraph, 20000, 10000);
		RandomObjectGenerator.printStatistics();
		System.out.println("Finished Generating");
		um.writeRoadObjsOnEdgeFile(sanJoaGraph.getObjectsOnEdges(), sanJoaGraph.getDatasetName());

	}

}
