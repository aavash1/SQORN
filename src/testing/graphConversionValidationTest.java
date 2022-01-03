package testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import algorithm.ClusteringNodes;
import algorithm.ClusteringRoadObjects;
import framework.Edge;
import framework.Graph;
import framework.Node;
import framework.RoadObject;
import framework.UtilsManagment;

public class graphConversionValidationTest {

	private static Map<Integer, Map<Integer, Double>> m_adjancencyMap;
	private static Map<Integer, Map<Integer, Double>> m_adjancencyMapStartingOne;
	private static ArrayList<Node> m_nodeList;
	private static ArrayList<Edge> m_edgeList;

	public static void main(String[] args) {

		Graph oldenGraph = new Graph("SJ");

		String nodeDatasetFile = "convertedDatasetsStartingWithOne/SJ-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "convertedDatasetsStartingWithOne/SJ-Edge_Eid-ESrc-EDest-EDist.csv";

		UtilsManagment.readNodeFile(oldenGraph, nodeDatasetFile);
		UtilsManagment.readEdgeFile(oldenGraph, edgeDatasetFile);

		// For conversion of nodes and edges that starts with 1.
		// m_nodeList = caliGraph.getNodesWithInfo();
		// m_edgeList = caliGraph.getEdgesWithInfo();
		//
		// String graphName = caliGraph.getDatasetName();
		// String nodeFileName = "convertedDatasetsStartingWithOne/" + graphName +
		// "-Node_NId-NLong-NLat" + ".csv";
		// String edgeFileName = "convertedDatasetsStartingWithOne/" + graphName +
		// "-Edge_Eid-ESrc-EDest-EDist" + ".csv";
		//
		// UtilsManagment.convertEdgeToCSVFile(caliGraph, m_edgeList, edgeFileName);
		// UtilsManagment.convertNodeToCSVFile(caliGraph, m_nodeList, nodeFileName);
		//

//		// For graph conversion to Metis Input Type.
//		String graphName = caliGraph.getDatasetName();
//		String graphFileName = "convertedGraphs/" + graphName + "_graph" + ".graph";
//		m_adjancencyMap = caliGraph.getAdjancencyMap();
//		UtilsManagment.convertGraphFile(caliGraph, m_adjancencyMap, graphFileName);

		// For graph conversion to Metis Input Type.
		String graphName = oldenGraph.getDatasetName();
		String graphFileName = "convertedGraphs/" + graphName + "_metisgraph1" + ".graph";
		m_adjancencyMap = oldenGraph.getAdjancencyMap();
		UtilsManagment.convertInputGraphFileToMETISFormat(oldenGraph, m_adjancencyMap, graphFileName);

		// for (Node node : m_nodeList) {
		// System.out.println("nodeID: " + node.getNodeId() + " longitude: " +
		// node.getLongitude() + " latitude: "
		// + node.getLatitude());
		//
		// }
		// UtilsManagment.convertNodeToCSVFile(caliGraph, m_nodeList, nodeFileName);
		// UtilsManagment.convertEdgeFile(caliGraph, m_adjancencyMap, graphFileName);
		//
		// Map<Integer, ArrayList<RoadObject>> objectsOnEdge =
		// UtilsManagment.readRoadObjectFile(objectDatasetFile);
		// caliGraph.setObjectsOnEdges(objectsOnEdge);
		// // caliGraph.printObjectsOnEdges();
		//
		// ClusteringNodes clusterNodes = new ClusteringNodes();
		// ClusteringRoadObjects clusterRObjects = new ClusteringRoadObjects();
		//
		// m_nodeIdClusters = clusterNodes.cluster(caliGraph);
		//
		// String graphName = caliGraph.getDatasetName();
		// String nodeClusterFileName = "ClusterDatasets/" + graphName +
		// "_node-clusters_"
		// + UtilsManagment.getNormalDateTime() + ".csv";
		//
		// UtilsManagment.writeNodeClustersFile(m_nodeIdClusters, nodeClusterFileName);

		// System.out.println("Validation for NC: " +
		// clusterNodes.nodeClusterValidator(caliGraph, m_nodeIdClusters));

		// m_objectIdClusters = clusterRObjects.clusterWithIndex(caliGraph,
		// m_nodeIdClusters, true);
		// clusterNodes.printNodeClusters();
		// clusterRObjects.printRoadObjectClusters();

		// System.out.println("Validation for OC: "
		// + clusterRObjects.nodeObjectValidator(caliGraph, m_nodeIdClusters,
		// m_objectIdClusters));

	}

}
