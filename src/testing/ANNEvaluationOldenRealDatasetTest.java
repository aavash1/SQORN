package testing;

import java.util.ArrayList;
import java.util.Map;

import algorithm.ANNClustered;
import algorithm.ANNNaive;

import framework.Graph;

import framework.RoadObject;
import framework.UtilsManagment;

public class ANNEvaluationOldenRealDatasetTest {

	public static void main(String[] args) {
		Graph oldenGraph = new Graph("Oldenburg");

		String nodeDatasetFile = "Datasets/OLDN-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/OLDN-Edge_EId-ESrc-EDest-EDist.csv";

		String objectDatasetFile = "GeneratedFiles/Oldenburg_2019-11-28 15-45-01.csv";

		UtilsManagment.readNodeFile(oldenGraph, nodeDatasetFile);
		UtilsManagment.readEdgeFile(oldenGraph, edgeDatasetFile);

		Map<Integer, ArrayList<RoadObject>> objectsOnEdge = UtilsManagment.readRoadObjectFile(objectDatasetFile);
		oldenGraph.setObjectsOnEdges(objectsOnEdge);

		System.out.println();
		ANNNaive annNaive = new ANNNaive();
		ANNClustered annClustered = new ANNClustered();
//		long startTimeNaive = System.nanoTime();
//		annNaive.compute(oldenGraph, true);
//		long graphLoadingTimeNaive = System.nanoTime() - startTimeNaive;
//		double graphLoadingTimeDNaive = (double) graphLoadingTimeNaive / 1000000000.0;
//		// annNaive.printNearestNeighborSets();
//		System.out.println("Time to compute Naive ANN: " + graphLoadingTimeDNaive);

		long startTimeClustered = System.nanoTime();
		annClustered.compute(oldenGraph, true);
		long graphLoadingTimeClustered = System.nanoTime() - startTimeClustered;
		double graphLoadingTimeDClustered = (double) graphLoadingTimeClustered / 1000000000.0;
		// ann3.printNearestSets();
		System.out.println("Time to compute Clustered ANN: " + graphLoadingTimeDClustered);
//
//		int totalNumberOfNodes = oldenGraph.getNodesWithInfo().size();
//		int totalNumberOfEdges = oldenGraph.getEdgesWithInfo().size();
//		int totalNumberOfRandomEdgesSelected = RandomObjectGenerator2.getTotalNumberOfRandomEdges();
//		int totalNumberOfObjectsGenerated = oldenGraph.getObjectsWithInfo().size();
//		int totalNumberOfTrueObjects = oldenGraph.getTotalNumberOfTrueObjects();
//		int totalNumberOfFalseObjects = oldenGraph.getTotalNumberOfFalseObjects();
//		int totalNumberOfNodeClusters = annClustered.getSizeOfNodeClusters();
//		int totalNumberOfObjectClusters = annClustered.getSizeOfObjectClusters();
//
//		um.writeNaiveAndClusteredANNTestResult(totalNumberOfNodes, totalNumberOfEdges, totalNumberOfRandomEdgesSelected,
//				totalNumberOfObjectsGenerated, totalNumberOfTrueObjects, totalNumberOfFalseObjects,
//				totalNumberOfNodeClusters, totalNumberOfObjectClusters, graphLoadingTimeDNaive,
//				graphLoadingTimeDClustered);

	}

}
