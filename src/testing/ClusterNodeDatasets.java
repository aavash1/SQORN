package testing;

import java.util.LinkedList;
import java.util.Map;

import algorithm.ClusteringNodes;
import framework.Graph;
import framework.UtilsManagment;

public class ClusterNodeDatasets {
	private static Map<Integer, LinkedList<Integer>> m_nodeIdClusters;

	public static void main(String[] args) {
		Graph northAmericaGraph = new Graph("SanJoaquin");

		String nodeDatasetFile = "Datasets/SANF-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/SANF-Edge_Eid-ESrc-EDest-EDist.csv";

		UtilsManagment.readNodeFile(northAmericaGraph, nodeDatasetFile);
		UtilsManagment.readEdgeFile(northAmericaGraph, edgeDatasetFile);

		ClusteringNodes clusterNodes = new ClusteringNodes();
		m_nodeIdClusters = clusterNodes.cluster(northAmericaGraph);
		System.out.println(clusterNodes.getNumOfTerminalNodes());

//		String graphName = northAmericaGraph.getDatasetName();
		
	//	String nodeClusterFileName = "ClusterDatasets/" + graphName + "_node-clusters_" + UtilsManagment.getNormalDateTime() + ".csv";
		
	//	UtilsManagment.writeNodeClustersFile(m_nodeIdClusters, nodeClusterFileName);
//		Map<Integer, LinkedList<Integer>> nodeClusterFromFile = UtilsManagment
//				.readNodeClustersFile("ClusterDatasets/California_node_2019-12-06 16-15-20.csv");

	}

}
