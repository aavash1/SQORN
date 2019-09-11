package algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;

import framework.Graph;
import framework.RoadObject;

public class ClusteringRoadObjects2 {

	private Map<Integer, LinkedList<Integer>> m_objectClusters = new HashMap<Integer, LinkedList<Integer>>();
	private int m_objectClusterCounter = 0;

	public void clusterObject(Graph gr, Map<Integer, LinkedList<Integer>> nodeClusters) {

		for (int index : nodeClusters.keySet()) {

			for (int i = 0; i < nodeClusters.get(index).size() - 1; i++) {
				int startNode = nodeClusters.get(index).get(i);
				int endNode = nodeClusters.get(index).get(i + 1);
				int edgeId = gr.getEdgeId(startNode, endNode);
				LinkedList<Integer> clusteredObject = new LinkedList<Integer>();
				for (Integer objectId : gr.getTrueObjectsIdOnGivenEdge(edgeId)) {

					clusteredObject.add(objectId);
					m_objectClusterCounter++;
					m_objectClusters.put(m_objectClusterCounter, clusteredObject);
				}

			}
		}

		System.out.println("Number of Objects Clustered: " + m_objectClusterCounter);

	}

	public void printObjectInClusteredEdge() {
		for (int i = 0; i < m_objectClusters.size(); i++) {
			System.out.println(m_objectClusters.get(i));
		}
	}
}
