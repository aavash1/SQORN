package testing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

import algorithm.ClusteringNodes;
import algorithm.ClusteringRoadObjects;
import framework.Graph;
import framework.RoadObject;
import framework.UtilsManagment;

public class graphConversionValidationTest {

	private static Map<Integer, Map<Integer, Double>> m_adjancencyMap;

	public static void main(String[] args) {

		Graph caliGraph = new Graph("SanJoaquin");

		String nodeDatasetFile = "Datasets/SJ-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/SJ-Edge_Eid-ESrc-EDest-EDist.csv";

		UtilsManagment.readNodeFile(caliGraph, nodeDatasetFile);
		UtilsManagment.readEdgeFile(caliGraph, edgeDatasetFile);

		m_adjancencyMap = caliGraph.getAdjancencyMap();
		String graphName = caliGraph.getDatasetName();
		String graphFileName = "convertedGraphs/" + graphName + "_dataset"+ ".graph";

//		for (Integer edgeId : m_adjancencyMap.keySet()) {
//			System.out.println("Edge Id:" + edgeId);
//			Map<Integer, Double> neighborEdgeId = m_adjancencyMap.get(edgeId);
//			for (Integer adjacentEdgeId : neighborEdgeId.keySet()) {
//				System.out.println("adjacent Id: " + adjacentEdgeId+" distance: "+neighborEdgeId.get(adjacentEdgeId));
//				
//
//			}
//		}

		 UtilsManagment.convertGraphFile(caliGraph, m_adjancencyMap, graphFileName);
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
