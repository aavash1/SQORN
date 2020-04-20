package testing;

import java.util.LinkedList;
import java.util.Map;

import algorithm.ANNClustered;
import algorithm.ANNNaive;
import algorithm.ClusteringRoadObjects;
import algorithm.RandomObjectGenerator;
import framework.Graph;
import framework.UtilsManagment;

public class ANNEvaluationCalRealDatasetWithCentroidTest2 {

	public static void main(String[] args) {
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
		Graph calGraph = new Graph("California");

		String nodeDatasetFile = "Datasets/CAL-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/CAL-Edge_Eid-ESrc-EDest-EDist.csv";
		UtilsManagment.readEdgeFile(calGraph, edgeDatasetFile);
		UtilsManagment.readNodeFile(calGraph, nodeDatasetFile);

		String graphName = calGraph.getDatasetName();
		
		RandomObjectGenerator.generateRandomObjectsOnEdgeWithCentroidForSameDistribution(calGraph, 10000, 20000, 1.8);
		RandomObjectGenerator.printStatistics();
		
	}

}
