package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import framework.Graph;

public class ClusteringRoadObjects {

	private Graph m_graph;
	private Map<Integer, LinkedList<Integer>> m_nodeClusters = new HashMap<Integer, LinkedList<Integer>>();

	// m_objectClusters: HashMap <index, LinkedList<Obj ID> >
	private Map<Integer, LinkedList<Integer>> m_objectIdClusters = new HashMap<Integer, LinkedList<Integer>>();
	// private Map<Integer, LinkedList<Integer>> m_objectClusters = new
	// HashMap<Integer, LinkedList<Integer>>();
	private Set<Integer> m_clusteredObjects = new HashSet<Integer>();
	boolean m_typeOfClusteredObjects;

	private void initialize() {
		// m_nodeClusters.clear();
		m_objectIdClusters.clear();
		m_clusteredObjects.clear();
	}

	public boolean nodeObjectValidator(Graph gr, Map<Integer, LinkedList<Integer>> nodeClusters,
			Map<Integer, LinkedList<Integer>> objectClusters) {

		for (Integer nodeClusterIndexId : nodeClusters.keySet()) {

			ArrayList<Integer> edgeIdList = new ArrayList<Integer>();

			for (int i = 0; i < nodeClusters.get(nodeClusterIndexId).size() - 1; i++) {
				edgeIdList.add(gr.getEdgeId(nodeClusters.get(nodeClusterIndexId).get(i),
						nodeClusters.get(nodeClusterIndexId).get(i + 1)));
			}
			for (int j = 0; j < objectClusters.get(nodeClusterIndexId).size() - 1; j++) {

				if (!edgeIdList.contains(gr.getEdgeIdOfRoadObject(objectClusters.get(nodeClusterIndexId).get(j)))) {
					System.out.println("index: " + nodeClusterIndexId);
					System.out.println(" edgeId of TO 1: "
							+ gr.getEdgeIdOfRoadObject(objectClusters.get(nodeClusterIndexId).get(j))
							+ " edgeId of TO 2: "
							+ gr.getEdgeIdOfRoadObject(objectClusters.get(nodeClusterIndexId).get(j + 1))
							+ " edgeId of TO 3: "
							+ gr.getEdgeIdOfRoadObject(objectClusters.get(nodeClusterIndexId).get(j + 2))
							+ " edgeId of TO 4: "
							+ gr.getEdgeIdOfRoadObject(objectClusters.get(nodeClusterIndexId).get(j + 3)));
					System.out.println("EdgeList: " + edgeIdList);
					System.out.println("NodeCluster: " + nodeClusters.get(nodeClusterIndexId));
					System.out.println("object Cluster: " + objectClusters.get(nodeClusterIndexId));
					return false;
				}

			}

		}

		return true;
	}

	public Map<Integer, LinkedList<Integer>> cluster(Graph gr, Map<Integer, LinkedList<Integer>> nodeClusters,
			boolean typeOfClusteredObjects) {

		initialize();

		m_graph = gr;
		m_nodeClusters = nodeClusters;
		m_typeOfClusteredObjects = typeOfClusteredObjects;
		int m_clusterCounter = 0;
		boolean clusteredObjectExists = false;

		for (Integer nodeClusterIndex : m_nodeClusters.keySet()) {
			LinkedList<Integer> objectCluster = new LinkedList<Integer>();
			ArrayList<Integer> objectsOnEdge = new ArrayList<Integer>();
			clusteredObjectExists = false;
			for (int i = 0; i < m_nodeClusters.get(nodeClusterIndex).size() - 1; i++) {
				int edgeId = m_graph.getEdgeId(m_nodeClusters.get(nodeClusterIndex).get(i),
						m_nodeClusters.get(nodeClusterIndex).get(i + 1));

				if (typeOfClusteredObjects) {
					objectsOnEdge = m_graph.getTrueObjectsIdOnGivenEdge(edgeId);
				} else {
					objectCluster.addAll(m_graph.getFalseObjectsIdOnGivenEdge(edgeId));
				}
				if (!objectsOnEdge.isEmpty()) {
					objectCluster.addAll(objectsOnEdge);
					m_clusteredObjects.addAll(objectCluster);
					clusteredObjectExists = true;
				}
			}
			if (clusteredObjectExists) {
				m_clusterCounter++;
				m_objectIdClusters.put(m_clusterCounter, objectCluster);
			}

		}
		return m_objectIdClusters;
	}

	public Map<Integer, LinkedList<Integer>> clusterWithIndex1(Graph gr, Map<Integer, LinkedList<Integer>> nodeClusters,
			boolean typeOfClusteredObjects) {

		initialize();

		m_graph = gr;
		m_nodeClusters = nodeClusters;
		m_typeOfClusteredObjects = typeOfClusteredObjects;
		int m_clusterCounter = 0;
		// boolean clusteredObjectExists = false;

		for (Integer nodeClusterIndex : m_nodeClusters.keySet()) {
			LinkedList<Integer> objectCluster = new LinkedList<Integer>();
			ArrayList<Integer> objectsOnEdge = new ArrayList<Integer>();
			// boolean clusteredObjectExists = false;
			for (int i = 0; i < m_nodeClusters.get(nodeClusterIndex).size() - 1; i++) {
				int edgeId = m_graph.getEdgeId(m_nodeClusters.get(nodeClusterIndex).get(i),
						m_nodeClusters.get(nodeClusterIndex).get(i + 1));

				if (typeOfClusteredObjects) {
					objectsOnEdge = m_graph.getTrueObjectsIdOnGivenEdge(edgeId);
				} else {
					objectCluster.addAll(m_graph.getFalseObjectsIdOnGivenEdge(edgeId));
				}
				if (!objectsOnEdge.isEmpty()) {
					objectCluster.addAll(objectsOnEdge);
					m_clusteredObjects.addAll(objectCluster);
					// clusteredObjectExists = true;
				}
			}
			// if (clusteredObjectExists) {
			m_clusterCounter++;
			m_objectIdClusters.put(m_clusterCounter, objectCluster);
			// }

		}
		System.out.println("Objects clustering finished. Total number of Objects-Clustered: " + m_clusterCounter);
		return m_objectIdClusters;
	}

	public Map<Integer, LinkedList<Integer>> clusterWithIndex(Graph gr, Map<Integer, LinkedList<Integer>> nodeClusters,
			boolean typeOfClusteredObjects) {

		initialize();

		m_graph = gr;
		m_nodeClusters = nodeClusters;
		m_typeOfClusteredObjects = typeOfClusteredObjects;
		int m_clusterCounter = 0;
		boolean clusteredObjectExists = false;

//		for (Integer nodeClusterIndex : m_nodeClusters.keySet()) {
//			LinkedList<Integer> objectCluster = new LinkedList<Integer>();
//			ArrayList<Integer> objectsOnEdge = new ArrayList<Integer>();
//			// boolean clusteredObjectExists = false;
////			for (int i = 0; i < m_nodeClusters.get(nodeClusterIndex).size() - 1; i++) {
////				int edgeId = m_graph.getEdgeId(m_nodeClusters.get(nodeClusterIndex).get(i),
////						m_nodeClusters.get(nodeClusterIndex).get(i + 1));
////
////				if (typeOfClusteredObjects) {
////
////					objectsOnEdge.addAll(m_graph.getTrueObjectsIdOnGivenEdge(edgeId));
////				} else {
////					objectsOnEdge.addAll(m_graph.getFalseObjectsIdOnGivenEdge(edgeId));
////				}
////				if (!objectsOnEdge.isEmpty()) {
////					if (objectsOnEdge.contains(20372) || objectsOnEdge.contains(28384) || objectsOnEdge.contains(4324)
////							|| objectsOnEdge.contains(3675)) {
////						System.err.println("obj clustering");
////					}
//////					objectCluster.addAll(objectsOnEdge);
//////					m_clusteredObjects.addAll(objectCluster);
////					// clusteredObjectExists = true;
////				}
////				objectCluster.addAll(objectsOnEdge);
////				m_clusteredObjects.addAll(objectCluster);
////			}
//			
//			// if (clusteredObjectExists) {
//			m_clusterCounter++;
//			// m_objectIdClusters.put(m_clusterCounter, objectCluster);
//			// }
//
//		}
		for (Integer nodeClusterIndex : m_nodeClusters.keySet()) {
			LinkedList<Integer> objectCluster = new LinkedList<Integer>();
			ArrayList<Integer> objectsOnEdge = new ArrayList<Integer>();
			clusteredObjectExists = false;
			for (int i = 0; i < m_nodeClusters.get(nodeClusterIndex).size() - 1; i++) {
				int edgeId = m_graph.getEdgeId(m_nodeClusters.get(nodeClusterIndex).get(i),
						m_nodeClusters.get(nodeClusterIndex).get(i + 1));

				if (typeOfClusteredObjects) {
					objectsOnEdge = m_graph.getTrueObjectsIdOnGivenEdge(edgeId);
				} else {
					objectCluster.addAll(m_graph.getFalseObjectsIdOnGivenEdge(edgeId));
				}
				if (!objectsOnEdge.isEmpty()) {
					objectCluster.addAll(objectsOnEdge);
					m_clusteredObjects.addAll(objectCluster);
					clusteredObjectExists = true;
				}
			}
			if (clusteredObjectExists) {
				m_clusterCounter++;
				m_objectIdClusters.put(m_clusterCounter, objectCluster);
			}

		}
		System.out.println("Objects clustering finished. Total number of Objects-Clustered: " + m_clusterCounter);
		return m_objectIdClusters;
	}

	public int getTotalNumberOfObjectClusters() {
		int totalNumberOfObjectClusters = 0;
		totalNumberOfObjectClusters += m_objectIdClusters.size();
		return totalNumberOfObjectClusters;
	}

	public void printRoadObjectClusters() {

		System.out.println();
		System.out.println("Total number of Road Objects: " + m_graph.getTotalNumberOfObjects());
		if (m_typeOfClusteredObjects) {
			System.out.println("Total number of True Objects: " + m_graph.getTotalNumberOfTrueObjects());
		} else {
			System.out.println("Total number of False Objects: " + m_graph.getTotalNumberOfFalseObjects());
		}
		System.out.println("Total number of Clustered Objects: " + m_clusteredObjects.size());
		System.out.println("Total number of Clusters: " + m_objectIdClusters.size());
		System.out.println("Road Object Clusters: ");// + m_objectIdClusters);
		for (Integer index : m_objectIdClusters.keySet()) {
			System.out.println("Object Cluster # " + index + ": " + m_objectIdClusters.get(index));
		}

		System.out.println();
	}

}
