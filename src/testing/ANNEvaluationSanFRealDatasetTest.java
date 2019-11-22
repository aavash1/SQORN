package testing;

import java.util.ArrayList;
import java.util.Map;

import algorithm.ANNClustered;
import algorithm.ANNNaive;
import framework.Edge;
import framework.Graph;
import framework.Node;
import framework.RoadObject;
import framework.UtilsManagment;

public class ANNEvaluationSanFRealDatasetTest {

	public static void main(String[] args) {
		Graph sanFGraph = new Graph();

		String nodeDatasetFile = "Datasets/SANF-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/SANF-Edge_Eid-ESrc-EDest-EDist.csv";
		String objectDatasetFile = "GeneratedFiles/roadObjectsOnEdge_SanFrancisco size_30000_2019-11-22 17-56-33.csv";

		UtilsManagment.readNodeFile(sanFGraph, nodeDatasetFile);
		UtilsManagment.readEdgeFile(sanFGraph, edgeDatasetFile);

		Map<Integer, ArrayList<RoadObject>> objectsOnEdge = UtilsManagment.readRoadObjectFile(objectDatasetFile);
		sanFGraph.setObjectsOnEdges(objectsOnEdge);

		// System.out.println();
		ANNNaive annNaive = new ANNNaive();
		long startTimeNaive = System.nanoTime();
		annNaive.compute(sanFGraph, true);
		long graphLoadingTimeNaive = System.nanoTime() - startTimeNaive;
		double graphLoadingTimeDNaive = (double) graphLoadingTimeNaive / 1000000000.0;
		// annNaive.printNearestNeighborSets();
		System.out.println("Time to compute Naive ANN: " + graphLoadingTimeDNaive);
		//
		ANNClustered ann3 = new ANNClustered();
		long startTimeClustered = System.nanoTime();
		ann3.compute(sanFGraph, true);
		long graphLoadingTimeClustered = System.nanoTime() - startTimeClustered;
		double graphLoadingTimeDClustered = (double) graphLoadingTimeClustered / 1000000000.0;
		// ann3.printNearestSets();
		System.out.println("Time to compute Clustered ANN: " + graphLoadingTimeDClustered);
		//
		// int totalNumberOfNodes = sanFGraph.getNodesWithInfo().size();
		// int totalNumberOfEdges = sanFGraph.getEdgesWithInfo().size();
		// int totalNumberOfRandomEdgesSelected =
		// RandomObjectGenerator2.getTotalNumberOfRandomEdges();
		// int totalNumberOfObjectsGenerated = sanFGraph.getObjectsWithInfo().size();
		// int totalNumberOfTrueObjects = sanFGraph.getTotalNumberOfTrueObjects();
		// int totalNumberOfFalseObjects = sanFGraph.getTotalNumberOfFalseObjects();
		// int totalNumberOfNodeClusters = annClustered.getSizeOfNodeClusters();
		// int totalNumberOfObjectClusters = annClustered.getSizeOfObjectClusters();
		//
		// um.writeNaiveAndClusteredANNTestResult(totalNumberOfNodes,
		// totalNumberOfEdges, totalNumberOfRandomEdgesSelected,
		// totalNumberOfObjectsGenerated, totalNumberOfTrueObjects,
		// totalNumberOfFalseObjects,
		// totalNumberOfNodeClusters, totalNumberOfObjectClusters,
		// graphLoadingTimeDNaive,
		// graphLoadingTimeDClustered);

	}

}
