package algorithm;

import java.util.ArrayList;
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
	private int m_clusterCounter;

	// Create a queue for Traversing
	private LinkedList<Integer> m_nonClearedNodeQueue = new LinkedList<Integer>();
	private Set<Integer> m_visitedEdges = new HashSet<Integer>();
	private Set<Integer> m_clusteredNodes = new HashSet<Integer>();
	private int m_numberOfTerminalNodes=0;

	private void initialize() {
		m_nodeClusters.clear();
		m_clusterCounter = 0;
		m_visitedEdges.clear();
		m_nonClearedNodeQueue.clear();
		m_clusteredNodes.clear();
	}

	public boolean nodeClusterValidator(Graph gr, Map<Integer, LinkedList<Integer>> nodeCluster) {

		for (Integer nodeClusterIndexId : nodeCluster.keySet()) {
			if (nodeCluster.get(nodeClusterIndexId).size() == 2) {
				if (!((gr.isTerminalNode(nodeCluster.get(nodeClusterIndexId).getFirst())
						|| gr.isIntersectionNode(nodeCluster.get(nodeClusterIndexId).getFirst()))
						&& (gr.isTerminalNode(nodeCluster.get(nodeClusterIndexId).getLast())
								|| gr.isIntersectionNode(nodeCluster.get(nodeClusterIndexId).getLast())))) {
					System.out.println("Node CLuster: " + nodeCluster.get(nodeClusterIndexId));
					return false;
				}
			} else if (nodeCluster.get(nodeClusterIndexId).size() >= 3) {
				if (((gr.isTerminalNode(nodeCluster.get(nodeClusterIndexId).getFirst())
						|| gr.isIntersectionNode(nodeCluster.get(nodeClusterIndexId).getFirst()))
						&& (gr.isTerminalNode(nodeCluster.get(nodeClusterIndexId).getLast())
								|| gr.isIntersectionNode(nodeCluster.get(nodeClusterIndexId).getLast())))) {
					for (int i = 1; i < nodeCluster.get(nodeClusterIndexId).size() - 1; i++) {
						if (!gr.isIntermediateNode(nodeCluster.get(nodeClusterIndexId).get(i))) {
							System.out.println("Node Id: " + nodeCluster.get(nodeClusterIndexId).get(i));
							System.out.println("Node CLuster: " + nodeCluster.get(nodeClusterIndexId));
							return false;
						}
					}

				}

			}
		}

		return true;

	}

	public Map<Integer, LinkedList<Integer>> cluster(Graph gr) {
System.err.println("Clustering nodes...");
		initialize();
		m_graph = gr;

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
//				if (adjNode == 13965 || adjNode == 8257 || adjNode == 13959 || adjNode == 13952 || adjNode == 13949
//						|| adjNode == 13945 || adjNode == 13940) {
//					System.out.println("nodeCLustering");
//				}
				if (m_graph.isTerminalNode(adjNode)) {
					LinkedList<Integer> nodeCluster = new LinkedList<Integer>();
					nodeCluster.add(currentNode);
					m_clusteredNodes.add(currentNode);
					nodeCluster.add(adjNode);
					m_clusteredNodes.add(adjNode);
					//m_clusterCounter++;  // for non-fixed version
					m_numberOfTerminalNodes++;

					m_nodeClusters.put(m_clusterCounter, nodeCluster);
					m_clusterCounter++; 
					// System.out.println("New cluster:");
					// System.out.println(nodeCluster);
					// System.out.println("All current clusters (" + m_clusterCounter + ") :");
					// System.out.println(m_nodeClusters);
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
					//m_clusterCounter++; // for non-fixed version
					m_nodeClusters.put(m_clusterCounter, nodeCluster);
					m_clusterCounter++; 
					// System.out.println("New cluster:");
					// System.out.println(nodeCluster);
					// System.out.println("All current clusters (" + m_clusterCounter + ") :");
					// System.out.println(m_nodeClusters);
					m_nonClearedNodeQueue.add(adjNode);
				}

			}

		}
		System.out.println("Node Clustering Completed. Total number of nodes-clusters: " + m_clusterCounter);
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
				//m_clusterCounter++; // for non-fixed version
				m_nodeClusters.put(m_clusterCounter, nodeCluster);
				m_clusterCounter++;
				m_numberOfTerminalNodes++;
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
				//m_clusterCounter++; // for non-fixed version
				m_nodeClusters.put(m_clusterCounter, nodeCluster);
				m_clusterCounter++; 
//				System.out.println("New cluster:");
//				System.out.println(nodeCluster);
//				System.out.println("All current clusters (" + m_clusterCounter + ") :");
//				System.out.println(m_nodeClusters);
				m_nonClearedNodeQueue.add(adjNode);
			}
		}
	}

	public Map<Integer, LinkedList<Integer>> getClusteredNodesSet() {
		return m_nodeClusters;
	}

	public int getTotalNumberOfNodeClusters() {
		int totalNumberOfNodeClusters = 0;
		totalNumberOfNodeClusters += m_clusterCounter;
		return totalNumberOfNodeClusters;
	}
	
	public int getNumOfTerminalNodes() {
		return m_numberOfTerminalNodes;
	}
	


	public void printNodeClusters() {
		System.out.println();
		System.out.println("Total number of Nodes: " + m_graph.getNumberOfNodes());
		System.out.println("Total number of Clustered Nodes: " + m_clusteredNodes.size());
		System.out.println("Number of Clusters: " + m_clusterCounter);
		for (Integer index : m_nodeClusters.keySet()) {
			System.out.println("Node Cluster # " + index + ": " + m_nodeClusters.get(index));
		}
	}

}
