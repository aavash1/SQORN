package testing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

import algorithm.ClusteringNodes;
import algorithm.ClusteringRoadObjects;
import framework.Graph;
import framework.RoadObject;
import framework.UtilsManagment;

public class clusteringValidationTest {
	private static Map<Integer, LinkedList<Integer>> m_objectIdClusters;
	private static Map<Integer, LinkedList<Integer>> m_nodeIdClusters;

	public static void main(String[] args) {

		Graph caliGraph = new Graph("California");

		String nodeDatasetFile = "Datasets/CAL-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/CAL-Edge_Eid-ESrc-EDest-EDist.csv";

		// T=110000 F=10000
//		String objectDatasetFile = "GeneratedFiles/SanFrancisco_2019-11-29 17-44-02.csv";
		// T=10000 F=110000
		String objectDatasetFile = "GeneratedFiles/roadObjectsOnEdge_California size_110000_2019-11-25 15-42-53.csv";

		UtilsManagment.readNodeFile(caliGraph, nodeDatasetFile);
		UtilsManagment.readEdgeFile(caliGraph, edgeDatasetFile);

		Map<Integer, ArrayList<RoadObject>> objectsOnEdge = UtilsManagment.readRoadObjectFile(objectDatasetFile);
		caliGraph.setObjectsOnEdges(objectsOnEdge);
//		caliGraph.printObjectsOnEdges();

		ClusteringNodes clusterNodes = new ClusteringNodes();
		ClusteringRoadObjects clusterRObjects = new ClusteringRoadObjects();

		m_nodeIdClusters = clusterNodes.cluster(caliGraph);
		System.out.println("Validation for NC: " + clusterNodes.nodeClusterValidator(caliGraph, m_nodeIdClusters));
		m_objectIdClusters = clusterRObjects.cluster(caliGraph, m_nodeIdClusters, true);
		// clusterNodes.printNodeClusters();
		// clusterRObjects.printRoadObjectClusters();

		System.out.println("Validation for OC: "
				+ clusterRObjects.nodeObjectValidator(caliGraph, m_nodeIdClusters, m_objectIdClusters));

	}

}
