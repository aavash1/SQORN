package algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import framework.Graph;

public class ClusteringNodes {

	private Graph m_graph;
	// m_nodeClusters: HashMap < index, LinkedList<Node ID> >
	private Map<Integer, LinkedList<Integer>> m_nodeClusters = new HashMap<Integer, LinkedList<Integer>>();
	private int m_clusterCounter = 0;	

	// Create a queue for Traversing
	private LinkedList<Integer> m_nonClearedNodeQueue = new LinkedList<Integer>();
	private Set<Integer> m_visitedEdges = new HashSet<Integer>();
	private Set<Integer> m_clusteredNodes = new HashSet<Integer>();

	public Map<Integer, LinkedList<Integer>> cluster(Graph gr) {

		m_graph = gr;
		m_visitedEdges.clear();

		int startTraverseNode = m_graph.getFirstIntersectionNode();

		m_nonClearedNodeQueue.add(startTraverseNode);
		int currentNode;

		while (!m_nonClearedNodeQueue.isEmpty()) {

			currentNode = m_nonClearedNodeQueue.poll();

			Iterator<Integer> i = m_graph.getAdjNodeIds(currentNode).listIterator();

			while (i.hasNext()) {

				int adjNode = i.next();

				int edgeId = m_graph.getEdgeId(currentNode, adjNode);
				if (this.m_visitedEdges.contains(edgeId))
					continue;
				m_visitedEdges.add(edgeId);

				if (m_graph.isTerminalNode(adjNode)) {
					LinkedList<Integer> nodeCluster = new LinkedList<Integer>();
					nodeCluster.add(currentNode);
					m_clusteredNodes.add(currentNode);
					nodeCluster.add(adjNode);
					m_clusteredNodes.add(adjNode);
					m_clusterCounter++;
					m_nodeClusters.put(m_clusterCounter, nodeCluster);
					//System.out.println("New cluster:");
					//System.out.println(nodeCluster);
					//System.out.println("All current clusters (" + m_clusterCounter + ") :");
					//System.out.println(m_nodeClusters);
				}

				if (m_graph.isIntermediateNode(adjNode)) {
					LinkedList<Integer> nodeCluster = new LinkedList<Integer>();
					nodeCluster.add(currentNode);
					m_clusteredNodes.add(currentNode);
					nodeCluster.add(adjNode);
					m_clusteredNodes.add(adjNode);
					TraverseIntermediate(adjNode, nodeCluster);
				}

				if (m_graph.isIntersectionNode(adjNode)) {
					LinkedList<Integer> nodeCluster = new LinkedList<Integer>();
					nodeCluster.add(currentNode);
					m_clusteredNodes.add(currentNode);
					nodeCluster.add(adjNode);
					m_clusteredNodes.add(adjNode);
					m_clusterCounter++;
					m_nodeClusters.put(m_clusterCounter, nodeCluster);
					//System.out.println("New cluster:");
					//System.out.println(nodeCluster);
					//System.out.println("All current clusters (" + m_clusterCounter + ") :");
					//System.out.println(m_nodeClusters);
					m_nonClearedNodeQueue.add(adjNode);
				}

			}

		}
		
		return m_nodeClusters;
	}

	private void TraverseIntermediate(int traverseNodeId, LinkedList<Integer> nodeCluster) {// boolean visited[]) {

		Iterator<Integer> i = m_graph.getAdjNodeIds(traverseNodeId).listIterator();
		while (i.hasNext()) {
			int adjNode = i.next();

			int edgeId = m_graph.getEdgeId(traverseNodeId, adjNode);
			if (m_visitedEdges.contains(edgeId))
				continue;
			m_visitedEdges.add(edgeId);

			if (m_graph.isTerminalNode(adjNode)) {
				nodeCluster.add(adjNode);
				m_clusteredNodes.add(adjNode);
				m_clusterCounter++;
				m_nodeClusters.put(m_clusterCounter, nodeCluster);
//				System.out.println("New cluster:");
//				System.out.println(nodeCluster);
//				System.out.println("All current clusters (" + m_clusterCounter + ") :");
//				System.out.println(m_nodeClusters);
			}

			if (m_graph.isIntermediateNode(adjNode)) {
				nodeCluster.add(adjNode);
				m_clusteredNodes.add(adjNode);
				TraverseIntermediate(adjNode, nodeCluster);
			}

			if (m_graph.isIntersectionNode(adjNode)) {
				nodeCluster.add(adjNode);
				m_clusteredNodes.add(adjNode);
				m_clusterCounter++;
				m_nodeClusters.put(m_clusterCounter, nodeCluster);
//				System.out.println("New cluster:");
//				System.out.println(nodeCluster);
//				System.out.println("All current clusters (" + m_clusterCounter + ") :");
//				System.out.println(m_nodeClusters);
				m_nonClearedNodeQueue.add(adjNode);
			}
		}
	}
	
	public void printNodeClusters () { 
		System.out.println();
		System.out.println("Total number of Nodes: " + m_graph.getNumberOfNodes());
		System.out.println("Total number of Clustered Nodes: " + m_clusteredNodes.size());
		System.out.println("Number of Clusters: " +  m_clusterCounter);
		for ( Integer index : m_nodeClusters.keySet()) { 
			System.out.println("Node Cluster # " + index + ": " + m_nodeClusters.get(index));
		}
	}

}
