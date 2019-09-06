package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import framework.Graph2;
import framework.Node;

public class ClusteringNodes {

	private Graph2 graph;

	// node clusters: HashMap < index, LinkedList<nodeId> >
	private Map<Integer, LinkedList<Integer>> m_nodeClusters = new HashMap<Integer, LinkedList<Integer>>();
	// another version: ArrayList<LinkedList<Integer>> nodeClusters2
	// private ArrayList<LinkedList<Integer>> nodeClusters2 = new
	// ArrayList<LinkedList<Integer>>() ;
	private int m_clusterCounter = 0;

	public void cluster2(Graph2 gr) {
		this.graph = gr;
		LinkedList<Integer> nodeCluster = new LinkedList<Integer>();
		//ArrayList<Integer> adjList = new ArrayList<Integer>();
		boolean nodeClearedStatus[] = new boolean[graph.getNumberOfNodes()];
		int indexOfNode;
		
		// System.out.print("NodeId:");
		for (Node n : graph.getNodesWithInfo()) {
			System.out.println("Main node: " + n.getNodeId());
			//nodeCluster =  new LinkedList<Integer>(); 
			//nodeCluster.add(n.getNodeId());
			//adjList = graph.getEdges(n.getNodeId());
			
			indexOfNode = graph.getIndexOfNodeByNodeId(n.getNodeId());
			
			// Proceed on non-cleared node
			if (nodeClearedStatus[indexOfNode] != true) { 
				boolean isMainNodeIntermediate = graph.isIntermediateNode(n.getNodeId());
				Iterator<Integer> adjIterator = graph.getAdjNodeIds(n.getNodeId()).listIterator();

				while (adjIterator.hasNext()) {
					int adjNode = adjIterator.next();

					
					System.out.print("adjNode: " + adjNode);
					if (isMainNodeIntermediate) {
						if (graph.isIntermediateNode(adjNode)) {
							// If both nodes are intermediate nodes =>
							// Interm & Interm
							System.out.println(" Interm & Interm");
							if (!nodeCluster.contains(adjNode))	{ 
								nodeCluster.add(adjNode);
							}
							

						} else {
							// if 1st node is intermediate but adj node is intermediate node
							// Interm & Non-Interm
							System.out.println(" Interm & Non-Interm");
							if (!nodeCluster.contains(adjNode))	{ 
								nodeCluster.add(adjNode);
								//m_nodeClusters.put(m_clusterCounter, new LinkedList<Integer>(nodeCluster));
								//m_clusterCounter++;
								//nodeCluster.clear();
							}
							
						}

					} else {

						if (graph.isIntermediateNode(adjNode)) {
							// if 1st node is non-intermediate but adj node is intermediate node
							// Non-Interm & Interm
							System.out.println(" Non-Interm & Interm");

						} else {
							// If both nodes are non-intermediate nodes => make a cluster with 2 nodes
							// Non-Interm & Non-Interm
							System.out.println(" Non-Interm & Non-Interm");
							nodeCluster.add(n.getNodeId());
							nodeCluster.add(adjNode);
							m_nodeClusters.put(m_clusterCounter, new LinkedList<Integer>(nodeCluster));
							m_clusterCounter++;
							nodeCluster.clear();
						}

					}
					
					if (graph.isTerminalNode(adjNode)) { 
						indexOfNode = graph.getIndexOfNodeByNodeId(adjNode);
						nodeClearedStatus[indexOfNode] = true;
					}

				}
			}
			
			

			nodeCluster.clear();
			// for (int i = 0; i < adjList.size(); i++) {
			//
			// // if ( (!graph.isIntermediateNode(adjList.get(i))) &&
			// // (!graph.isIntermediateNode(n.getNodeId())) ) {
			// if (!graph.isIntermediateNode(adjList.get(i))) {
			// nodeCluster.add(adjList.get(i));
			// m_nodeClusters.put(m_clusterCounter, nodeCluster);
			// m_clusterCounter++;
			// }
			// }
		}
		 printNodeClusters();

	}

	public void cluster(Graph2 gr, int nodeId) {
		this.graph = gr;
		LinkedList<Integer> nodeCluster = new LinkedList<Integer>();

		boolean nodeVisitStatus[] = new boolean[graph.getNumberOfNodes()];

		int indexOfNode = graph.getIndexOfNodeByNodeId(nodeId);
		nodeVisitStatus[indexOfNode] = true;

		nodeCluster.add(nodeId);
		System.out.println(nodeCluster);
		while (!areAllNodesVisited(nodeVisitStatus)) {

			Iterator<Integer> i = graph.getAdjNodeIds(nodeId).listIterator();

			while (i.hasNext()) {
				int n = i.next();
				int indexOfNode2 = graph.getIndexOfNodeByNodeId(n);

				if (!nodeVisitStatus[indexOfNode2]) {
					nodeVisitStatus[indexOfNode2] = true;

					if (graph.isIntermediateNode(n)) {
						nodeCluster.add(n);
						System.out.println(nodeCluster);
					} else {
						nodeCluster.add(n);
						m_nodeClusters.put(m_clusterCounter, nodeCluster);
						m_clusterCounter++;
					}
					nodeId = n;
					// queue.add(n);
				}
			}

		}
		printNodeClusters();

		// ///// from BFS
		// boolean visited[] = new boolean[graph.getNumberOfNodes()]; //
		// // Create a queue for BFS
		// LinkedList<Integer> queue = new LinkedList<Integer>();
		//
		// // Mark the current node as visited and enqueue it
		// visited[indexOfNode] = true;
		// queue.add(nodeId);
		//
		// System.out.println("BFS from " + nodeId + ": ");
		// while (queue.size() != 0) {
		// // Dequeue a vertex from queue and print it nodeId = queue.poll();
		//
		// // if (!nodeId == graph.isIntermediateNode(nodeId)) { // // }
		//
		// System.out.print(nodeId + " ");
		//
		// // Get all adjacent vertices of the dequeued vertex s // If a adjacent has
		// // not been visited, then mark it // visited and enqueue it
		// Iterator<Integer> i = graph.getEdges(nodeId).listIterator();
		// while (i.hasNext()) {
		// int n = i.next();
		// int indexOfNode2 = graph.getIndexOfNodeByNodeId(n);
		// if (!visited[indexOfNode2]) {
		// visited[indexOfNode2] = true;
		// queue.add(n);
		// }
		// }
		// }
		// //End from BFS

	}
	
	private boolean insertNodeIntoCluster(int nodeIdToInsert, int nodeIdOfNeighbor) { 
		LinkedList<Integer> nodeCluster = new LinkedList<Integer>();
		nodeCluster = getClusterOfGivenNodeId(nodeIdOfNeighbor);
		nodeCluster.add(nodeIdToInsert);		
		return true;
	}
	
	private boolean isNodeClusterComplete(LinkedList<Integer> nodeCluster) { 
		int numberOfBoundaryNodes = 0;
		for (Integer node: nodeCluster) { 
			if (!graph.isIntermediateNode(node)) { 
				numberOfBoundaryNodes++;
			}
		}
		if (numberOfBoundaryNodes == 2) 
			{
				return true;
			}
		return false;
	}

	private ArrayList<LinkedList<Integer>> getAllClustersOfGivenNodeId(int nodeId) {
		ArrayList<LinkedList<Integer>> nodeClusters = new ArrayList<LinkedList<Integer>>();

		for (Integer key : m_nodeClusters.keySet()) {
			if (m_nodeClusters.get(key).contains(nodeId)) {
				nodeClusters.add(m_nodeClusters.get(key));
			}
			// System.out.println("Node Cluster #" + key + " : " + m_nodeClusters.get(key));
		}

		return nodeClusters;
	}

	private LinkedList<Integer> getClusterOfGivenNodeId(int nodeId) {		

		for (Integer key : m_nodeClusters.keySet()) {
			if (m_nodeClusters.get(key).contains(nodeId)) {
				return m_nodeClusters.get(key);
			}
			// System.out.println("Node Cluster #" + key + " : " + m_nodeClusters.get(key));
		}

		return null;
	}
	
	public void printNodeClusters() {
		// You should call cluster() method before printNodeClusters()
		System.out.println();
		System.out.println("Node clusters: ");

		if (!m_nodeClusters.isEmpty()) {
			for (Integer key : m_nodeClusters.keySet()) {
				System.out.println("Node Cluster #" + key + " : " + m_nodeClusters.get(key));
			}
		} else {
			System.out.println("There is no any node cluster");
		}
	}

	public int getNumberOfClusters() {
		return m_clusterCounter;
	}

	private boolean areAllNodesVisited(boolean[] visitStatus) {
		for (int i = 0; i < visitStatus.length; i++) {
			if (visitStatus[i] == false) {
				return false;
			}
		}
		return true;
	}

	private ArrayList<Integer> getNodesTillClusterBoundary(int nodeId) {

		ArrayList<Integer> nodes = new ArrayList<Integer>();

		return nodes;

	}

	// The function to do DFS traversal. It uses recursive DFSUtil()
	public void traverse(Graph2 gr, int nodeId) {
		this.graph = gr;
		// Mark all the vertices as not visited(set as
		// false by default in java)
		boolean visited[] = new boolean[graph.getNumberOfNodes()];

		System.out.println("DFS from " + nodeId + ": ");
		// Call the recursive helper function to print DFS traversal
		DFSUtil(nodeId, visited);
	}

	// A function used by DFS
	private void DFSUtil(int nodeId, boolean visited[]) {
		// Mark the current node as visited and print it
		int indexOfNode = graph.getIndexOfNodeByNodeId(nodeId);
		visited[indexOfNode] = true;
		System.out.print(nodeId + " ");

		// Recur for all the vertices adjacent to this vertex
		Iterator<Integer> i = graph.getAdjNodeIds(nodeId).listIterator();
		while (i.hasNext()) {
			int n = i.next();
			int indexOfNode2 = graph.getIndexOfNodeByNodeId(n);
			if (!visited[indexOfNode2])
				DFSUtil(n, visited);
		}
	}

}
