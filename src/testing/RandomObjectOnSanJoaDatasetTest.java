package testing;

import algorithm.RandomObjectGenerator;
import framework.Graph;
import framework.UtilsManagment;

public class RandomObjectOnSanJoaDatasetTest {
	public static void main(String[] args) {
		Graph sanJoaGraph = new Graph("San Joaquin");

		String nodeDatasetFile = "Datasets/SJ-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/SJ-Edge_Eid-ESrc-EDest-EDist.csv";

		UtilsManagment.readEdgeFile(sanJoaGraph, edgeDatasetFile);
		UtilsManagment.readNodeFile(sanJoaGraph, nodeDatasetFile);

		RandomObjectGenerator.generateRandomObjectsOnMap6(sanJoaGraph, 20000, 10000);
		RandomObjectGenerator.printStatistics();
		System.out.println("Finished Generating");
		UtilsManagment.writeRoadObjsOnEdgeFile1(sanJoaGraph.getObjectsOnEdges(), sanJoaGraph.getDatasetName());

	}

}
