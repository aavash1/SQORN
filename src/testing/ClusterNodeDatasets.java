package testing;

import java.util.LinkedList;
import java.util.Map;

import algorithm.ClusteringNodes;
import framework.Graph;
import framework.UtilsManagment;

public class ClusterNodeDatasets {
	private static Map<Integer, LinkedList<Integer>> m_nodeIdClusters;

	public static void main(String[] args) {
		Graph northWestUSAGraph = new Graph("NorthWestUSA");


		String nodeDatasetFile = "Datasets/TextFiles/NW-Node.txt";

		String edgeDatasetFile = "Datasets/TextFiles/NW-Edge.txt";

		UtilsManagment.readTxtNodeFile(northWestUSAGraph, nodeDatasetFile);
		UtilsManagment.readTxtEdgeFile(northWestUSAGraph, edgeDatasetFile);
		
		//northWestUSAGraph.printNodesInfo();
		//northWestUSAGraph.printEdgesInfo();

		ClusteringNodes clusterNodes = new ClusteringNodes();
		m_nodeIdClusters = clusterNodes.cluster(northWestUSAGraph);
	//	System.out.println(clusterNodes.getNumOfTerminalNodes());

		String graphName = northWestUSAGraph.getDatasetName();

		String nodeClusterFileName = "ClusterDatasets/" + graphName + "_node-clusters_"
				+ UtilsManagment.getNormalDateTime() + ".csv";

		UtilsManagment.writeNodeClustersFile(m_nodeIdClusters, nodeClusterFileName);
//		Map<Integer, LinkedList<Integer>> nodeClusterFromFile = UtilsManagment
//				.readNodeClustersFile("ClusterDatasets/California_node_2019-12-06 16-15-20.csv");

	}

}
