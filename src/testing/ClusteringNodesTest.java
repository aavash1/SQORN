package testing;

import algorithm.ClusteringNodes;
import algorithm.ANNNaive;
import framework.Graph;
import framework.RoadObject;
import road_network.ManualRN4;

public class ClusteringNodesTest {

	public static void main(String[] args) {
		Graph gr = ManualRN4.getGraph();

		System.out.println();
//		ANNNaive nAnn = new ANNNaive();
//		nAnn.compute(gr);
//		nAnn.printNearestNeighborSets();

		ClusteringNodes clusteringNode = new ClusteringNodes();
		clusteringNode.cluster(gr);
		clusteringNode.printNodeClusters();
		System.out.println();
		System.out.println(clusteringNode.getNumOfTerminalNodes());

	}

}
