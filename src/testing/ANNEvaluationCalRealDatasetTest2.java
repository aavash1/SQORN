package testing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

import org.apache.commons.math3.random.RandomDataGenerator;

import algorithm.ANNClustered;
import algorithm.ANNNaive;
import algorithm.ClusteringNodes;
import algorithm.ClusteringRoadObjects;
import algorithm.RandomObjectGenerator;
import framework.Graph;
import framework.RoadObject;
import framework.UtilsManagment;

public class ANNEvaluationCalRealDatasetTest2 {

	public static void main(String[] args) {
		Graph calGraph = new Graph("California");

		String nodeDatasetFile = "Datasets/CAL-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/CAL-Edge_EId-ESrc-EDest-EDist.csv";

		//String objectDatasetFile = "GeneratedFiles/California_30000_T_F_10000_20000_2019-12-05 00-04-32.csv";
		 String objectDatasetFile ="GeneratedFiles/California_30000_T_F_20000_10000_2019-12-05 00-07-01.csv";

		UtilsManagment.readNodeFile(calGraph, nodeDatasetFile);
		UtilsManagment.readEdgeFile(calGraph, edgeDatasetFile);

		Map<Integer, ArrayList<RoadObject>> objectsOnEdge = UtilsManagment.readRoadObjectFile(objectDatasetFile);
		calGraph.setObjectsOnEdges(objectsOnEdge);
		// for (int i = 1; i < 10; i++) {
		
		ClusteringNodes clusteringNodes = new ClusteringNodes();

		ClusteringRoadObjects clusteringObjects = new ClusteringRoadObjects();
		Map<Integer, LinkedList<Integer>> nodeIdClusters = clusteringNodes.cluster(calGraph);
		Map<Integer, LinkedList<Integer>> objectIdClusters = clusteringObjects.clusterWithIndex(calGraph,
				nodeIdClusters, true);

		ANNClustered annClustered = new ANNClustered();
		long startTimeClustered = System.nanoTime();
		annClustered.computeWithoutClustering(calGraph, true, nodeIdClusters, objectIdClusters);
		long graphLoadingTimeClustered = System.nanoTime() - startTimeClustered;
		double graphLoadingTimeDClustered = (double) graphLoadingTimeClustered / 1000000000.0;
		// ann3.printNearestSets();
		System.out.println("Time to compute Clustered ANN: " + graphLoadingTimeDClustered);
		
		System.out.println();
		ANNNaive annNaive = new ANNNaive();
		
		long startTimeNaive = System.nanoTime();
		annNaive.compute(calGraph, true);
		long graphLoadingTimeNaive = System.nanoTime() - startTimeNaive;
		double graphLoadingTimeDNaive = (double) graphLoadingTimeNaive / 1000000000.0;
		// annNaive.printNearestNeighborSets();
		System.out.println("Time to compute Naive ANN: " + graphLoadingTimeDNaive);

		
		// System.out.println("Number of iteration: "+i+1);
		// System.out.println("---------------------------");
		//
		// int totalNumberOfNodes = oldenGraph.getNodesWithInfo().size();
		// int totalNumberOfEdges = oldenGraph.getEdgesWithInfo().size();
		// int totalNumberOfRandomEdgesSelected =
		// RandomObjectGenerator2.getTotalNumberOfRandomEdges();
		// int totalNumberOfObjectsGenerated = oldenGraph.getObjectsWithInfo().size();
		// int totalNumberOfTrueObjects = oldenGraph.getTotalNumberOfTrueObjects();
		// int totalNumberOfFalseObjects = oldenGraph.getTotalNumberOfFalseObjects();
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