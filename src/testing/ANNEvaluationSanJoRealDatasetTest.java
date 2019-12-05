package testing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

import algorithm.ANNClustered;
import algorithm.ANNNaive;
import algorithm.ClusteringNodes;
import algorithm.ClusteringRoadObjects;

import framework.Graph;

import framework.RoadObject;
import framework.UtilsManagment;

public class ANNEvaluationSanJoRealDatasetTest {

	public static void main(String[] args) {
		Graph sanJoaGraph = new Graph("SanJoaquin");

		String nodeDatasetFile = "Datasets/SJ-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/SJ-Edge_Eid-ESrc-EDest-EDist.csv";

		//String objectDatasetFile = "GeneratedFiles/San Joaquin_40000_T_F_10000_30000_2019-12-05 00-33-12.csv";
		String objectDatasetFile = "GeneratedFiles/San Joaquin_40000_T_F_30000_10000_2019-12-05 00-34-22.csv";

		UtilsManagment.readNodeFile(sanJoaGraph, nodeDatasetFile);
		UtilsManagment.readEdgeFile(sanJoaGraph, edgeDatasetFile);

		Map<Integer, ArrayList<RoadObject>> objectsOnEdge = UtilsManagment.readRoadObjectFile(objectDatasetFile);
		sanJoaGraph.setObjectsOnEdges(objectsOnEdge);


		System.out.println();
		ANNNaive annNaive = new ANNNaive();
		long startTimeNaive = System.nanoTime();
		annNaive.compute(sanJoaGraph, true);
		long graphLoadingTimeNaive = System.nanoTime() - startTimeNaive;
		double graphLoadingTimeDNaive = (double) graphLoadingTimeNaive / 1000000000.0;
		// annNaive.printNearestNeighborSets();
		System.out.println("Time to compute Naive ANN: " + graphLoadingTimeDNaive);

		ClusteringNodes clusteringNodes = new ClusteringNodes();

		ClusteringRoadObjects clusteringObjects = new ClusteringRoadObjects();
		Map<Integer, LinkedList<Integer>> nodeIdClusters = clusteringNodes.cluster(sanJoaGraph);
		Map<Integer, LinkedList<Integer>> objectIdClusters = clusteringObjects.clusterWithIndex(sanJoaGraph,
				nodeIdClusters, true);
		ANNClustered ann3 = new ANNClustered();
		long startTimeClustered = System.nanoTime();
		ann3.computeWithoutClustering(sanJoaGraph, true, nodeIdClusters, objectIdClusters);
		long graphLoadingTimeClustered = System.nanoTime() - startTimeClustered;
		double graphLoadingTimeDClustered = (double) graphLoadingTimeClustered / 1000000000.0;
		// ann3.printNearestSets();
		System.out.println("Time to compute Clustered ANN: " + graphLoadingTimeDClustered);


	}

}
