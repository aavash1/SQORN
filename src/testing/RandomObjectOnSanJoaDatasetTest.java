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
		int trueObjSize = 100000;
		int falseObjSize = 10000;

		RandomObjectGenerator.generateRandomObjectsOnMap6(sanJoaGraph, trueObjSize, falseObjSize);
		RandomObjectGenerator.printStatistics();
		System.out.println("Finished Generating");
		UtilsManagment.writeRoadObjsOnEdgeFile1(sanJoaGraph.getObjectsOnEdges(), sanJoaGraph.getDatasetName(),trueObjSize,falseObjSize);

	}

}
