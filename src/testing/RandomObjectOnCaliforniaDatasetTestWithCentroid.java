package testing;

import algorithm.RandomObjectGeneratorWithCentroid;
import algorithm.RandomObjectGeneratorWithGaussian;
import framework.Graph;
import framework.UtilsManagment;

public class RandomObjectOnCaliforniaDatasetTestWithCentroid {
	public static void main(String[] args) {

		Graph calGraph = new Graph("California");

		String nodeDatasetFile = "Datasets/CAL-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/CAL-Edge_Eid-ESrc-EDest-EDist.csv";
		int trueObjSize = 10000;
		int falseObjSize = 20000;

		UtilsManagment.readEdgeFile(calGraph, edgeDatasetFile);
		UtilsManagment.readNodeFile(calGraph, nodeDatasetFile);
		// calGraph.printGraph();

		System.out.println("Finished Generating");
		UtilsManagment.writeRoadObjsOnEdgeFile(calGraph.getObjectsOnEdges(), calGraph.getDatasetName(), "NAMEOFROADOBJDATASET");
		//UtilsManagment.writeDatasetStatistics(calGraph);
		// calGraph.printObjectsOnEdges();

	}

}
