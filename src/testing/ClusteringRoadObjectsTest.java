package testing;

import algorithm.ClusteringNodes;
import algorithm.ClusteringRoadObjects;
import algorithm.ANNNaive;
import framework.Graph;
import framework.RoadObject;
import road_network.ManualRN4;

public class ClusteringRoadObjectsTest {

	public static void main(String[] args) {

		Graph gr = ManualRN4.getGraph();

//		ANNNaive nAnn = new ANNNaive();
//		nAnn.compute(gr);
//		nAnn.printNearestNeighborSets();

		ClusteringNodes clusteringNodes = new ClusteringNodes();

		ClusteringRoadObjects clusteringObjects = new ClusteringRoadObjects();
		clusteringObjects.clusterWithIndex2(gr, clusteringNodes.cluster(gr), true);
		// clusteringObjects.clusterWithIndex(gr, clusteringNodes.cluster(gr), false);
		//clusteringNodes.printNodeClusters();
		clusteringObjects.printRoadObjectClusters();

	}

}
