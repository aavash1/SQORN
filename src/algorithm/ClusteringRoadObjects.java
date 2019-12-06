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
		m_nodeClusters.clear();
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
				m_objectIdClusters.put(nodeClusterIndex, objectCluster);
			}

		}
		return m_objectIdClusters;
	}

	public Map<Integer, LinkedList<Integer>> clusterWithIndex(Graph gr, Map<Integer, LinkedList<Integer>> nodeClusters,
			boolean typeOfClusteredObjects) {

		initialize();

		m_graph = gr;
		m_nodeClusters = nodeClusters;
		m_typeOfClusteredObjects = typeOfClusteredObjects;

		int numberOfContributingClusters = 0;
		int queriedObjCounter = 0;

		for (Integer nodeClusterIndex : m_nodeClusters.keySet()) {
			LinkedList<Integer> objectCluster = new LinkedList<Integer>();
			ArrayList<Integer> objectsOnEdge = new ArrayList<Integer>();

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
				}
			}
			if (objectCluster.size() == 1) {
				queriedObjCounter++;
			} else if (objectCluster.size() == 2) {
				queriedObjCounter += 2;
			} else if (objectCluster.size() > 2) {
				queriedObjCounter += 2;
				numberOfContributingClusters++;
			}

			m_objectIdClusters.put(nodeClusterIndex, objectCluster);

		}
		System.out.println();
		System.out.println(
				"Objects clustering completed. Total number of Objects-Clusters: " + m_objectIdClusters.size());
		//double diffPerc = 100.0-queriedObjCounter/m_clusteredObjects.size()*100.0;
		//System.out.println("Total Query Objs: " + m_clusteredObjects.size() + ", Actual Quired Objects: " + queriedObjCounter + "; " + diffPerc + "%");
		System.out.println("Total Query Objs: " + m_clusteredObjects.size() + ", Actual Quired Objects: " + queriedObjCounter );
		System.out.println("Number of Contributing Object clusters: " + numberOfContributingClusters);
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
