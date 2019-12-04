package testing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

import algorithm.ANNClustered;
import algorithm.ANNNaive;
import algorithm.ClusteringNodes;
import algorithm.ClusteringRoadObjects;
import framework.Edge;
import framework.Graph;
import framework.Node;
import framework.RoadObject;
import framework.UtilsManagment;

public class ANNEvaluationSanFRealDatasetTest {

	public static void main(String[] args) {
		Graph sanFGraph = new Graph("SanFrancisco");

		String nodeDatasetFile = "Datasets/SANF-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/SANF-Edge_Eid-ESrc-EDest-EDist.csv";

		// T=110000 F=10000
		String objectDatasetFile = "GeneratedFiles/SanFrancisco_2019-11-29 17-44-02.csv";
		// T=10000 F=110000
		//String objectDatasetFile = "GeneratedFiles/SanFrancisco_2019-11-29 17-24-44.csv";

		UtilsManagment.readNodeFile(sanFGraph, nodeDatasetFile);
		UtilsManagment.readEdgeFile(sanFGraph, edgeDatasetFile);

		Map<Integer, ArrayList<RoadObject>> objectsOnEdge = UtilsManagment.readRoadObjectFile(objectDatasetFile);
		sanFGraph.setObjectsOnEdges(objectsOnEdge);

		

		System.out.println();
		ANNNaive annNaive = new ANNNaive();
		long startTimeNaive = System.nanoTime();
		annNaive.compute(sanFGraph, true);
		long graphLoadingTimeNaive = System.nanoTime() - startTimeNaive;
		double graphLoadingTimeDNaive = (double) graphLoadingTimeNaive / 1000000000.0;
		// annNaive.printNearestNeighborSets();
		System.out.println("Time to compute Naive ANN: " + graphLoadingTimeDNaive);

		
		ClusteringNodes clusteringNodes = new ClusteringNodes();

		ClusteringRoadObjects clusteringObjects = new ClusteringRoadObjects();
		Map<Integer, LinkedList<Integer>> nodeIdClusters = clusteringNodes.cluster(sanFGraph);
		Map<Integer, LinkedList<Integer>> objectIdClusters = clusteringObjects.clusterWithIndex(sanFGraph,
				nodeIdClusters, true);

		ANNClustered ann3 = new ANNClustered();
//		long startTimeClustered = System.nanoTime();
		double graphLoadingTimeDClustered = ann3.computeWithoutClustering(sanFGraph, true, nodeIdClusters,
				objectIdClusters);
//		long graphLoadingTimeClustered = System.nanoTime() - startTimeClustered;
//		double graphLoadingTimeDClustered = (double) graphLoadingTimeClustered / 1000000000.0;
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
		// UtilsManagment.writeNaiveAndClusteredANNTestResult(graph,
		// totalNumOfNodeClusters, totalNumOfObjectClusters,
		// timeElapsedToComputeANNNAive, timeElapsedToComputeANNCLustered);

	}

}
