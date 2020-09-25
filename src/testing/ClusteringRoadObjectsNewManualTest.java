package testing;

import algorithm.ClusteringNodes;
import algorithm.ClusteringRoadObjects;
import algorithm.ANNClusteredOptimizedWithHeuristic;
import algorithm.ANNNaive;
import framework.Graph;
import framework.RoadObject;
import road_network.ManualRN1;

public class ClusteringRoadObjectsNewManualTest {

	public static void main(String[] args) {	

		Graph gr = ManualRN1.getGraph();
		
		ANNClusteredOptimizedWithHeuristic annClustered = new ANNClusteredOptimizedWithHeuristic();
		System.out.println();

		long startTimeClustered = System.nanoTime();
		annClustered.computeWithTime(gr, true);
		//annClustered.computeWithTimeAndHeuristic(gr, true);
		long graphLoadingTimeClustered = System.nanoTime() - startTimeClustered;
		double graphLoadingTimeDClustered = (double) graphLoadingTimeClustered / 1000000000.0;

		System.out.println("Time to compute Clustered ANN: " +graphLoadingTimeDClustered);
		//System.out.println("Time to compute Clustered ANN with Heuristic: " + graphLoadingTimeDClustered);

		//
		//
		// ClusteringNodes clusteringNodes = new ClusteringNodes();
		//
		// ClusteringRoadObjects clusteringObjects = new ClusteringRoadObjects();
		// clusteringObjects.clusterWithIndex4(gr, clusteringNodes.cluster(gr), true);
		// System.out.println("num of terminal nodes:
		// "+clusteringNodes.getNumOfTerminalNodes());
		// // clusteringObjects.clusterWithIndex(gr, clusteringNodes.cluster(gr), true);
		//
		// // clusteringObjects.printRoadObjectClusters();
		// //clusteringNodes.printNodeClusters();
		// //System.out.println(clusteringObjects.getNodeClusterAndObjectInfo());

	}

}
