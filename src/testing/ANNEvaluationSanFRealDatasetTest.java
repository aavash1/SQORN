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
		UtilsManagment um = new UtilsManagment();
		Graph sanFGraph = new Graph();

		String nodeDatasetFile = "Datasets/SANF-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/SANF-Edge_Eid-ESrc-EDest-EDist.csv";
		String objectDatasetFile = "Datasets/SANF-Edge_Eid-ESrc-EDest-EDist.csv";
		

		ArrayList<Node> sanFNodesInfo = um.readNodeFile(nodeDatasetFile);
		sanFGraph.setNodesWithInfo(sanFNodesInfo);
		ArrayList<Edge> sanFEdgeInfo = um.readEdgeFile(edgeDatasetFile);
		sanFGraph.setEdgeWithInfo(sanFEdgeInfo);

		Map<Integer, ArrayList<RoadObject>> objectsOnEdge = um.readRoadObjectFile(objectDatasetFile);
		sanFGraph.setObjectsOnEdges(objectsOnEdge);

		// System.out.println();
		ANNNaive annNaive = new ANNNaive();
		ANNClustered annClustered = new ANNClustered();
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
