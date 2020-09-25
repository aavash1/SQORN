package testing;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

import algorithm.BFS;
import algorithm.ClusteringNodes;
import algorithm.ClusteringRoadObjects;
import algorithm.DFS;
import algorithm.RandomObjectGenerator;

import framework.Graph;
import framework.RoadObject;
import framework.UtilsManagment;
import road_network.ManualRN1;

public class ManualDatasetForANNv2 {

	public static void main(String[] args) {

		Graph gr = ManualRN1.getGraph();

		//gr.printEdgesInfo();
		//gr.printObjectsOnEdges();
		
		ClusteringNodes clusteringNodes = new ClusteringNodes();

		ClusteringRoadObjects clusteringObjects = new ClusteringRoadObjects();
		clusteringObjects.clusterWithIndex2(gr, clusteringNodes.cluster(gr), true);
		// clusteringObjects.clusterWithIndex(gr, clusteringNodes.cluster(gr), false);
		clusteringNodes.printNodeClusters();
		clusteringObjects.printRoadObjectClusters();
	}
	

}

