package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import framework.Graph2;

public class ClusteringNodes {

	private Graph2 graph;

	// node clusters: HashMap < index, LinkedList<nodeId> >
	private Map<Integer, LinkedList<Integer>> m_nodeClusters = new HashMap<Integer, LinkedList<Integer>>();
	// another version: ArrayList<LinkedList<Integer>> nodeClusters2
	// private ArrayList<LinkedList<Integer>> nodeClusters2 = new
	// ArrayList<LinkedList<Integer>>() ;
	private int m_clusterCounter = 0;
	
	public void cluster(Graph2 gr, int nodeId) {
		this.graph = gr;
		LinkedList<Integer> nodeCluster = new LinkedList<Integer>();

		boolean nodeVisitStatus[] = new boolean[graph.getNumberOfNodes()];

		int indexOfNode = graph.getIndexOfNodeByNodeId(nodeId);
		nodeVisitStatus[indexOfNode] = true;

		nodeCluster.add(nodeId);

		while (!areAllNodesVisited(nodeVisitStatus)) {

			Iterator<Integer> i = graph.getEdges(nodeId).listIterator();

			while (i.hasNext()) {
				int n = i.next();
				int indexOfNode2 = graph.getIndexOfNodeByNodeId(n);

				if (!nodeVisitStatus[indexOfNode2]) {
					nodeVisitStatus[indexOfNode2] = true;

					if (graph.isIntermediateNode(n)) {
						nodeCluster.add(n);
					} else {
						m_clusterCounter++;
						m_nodeClusters.put(m_clusterCounter, nodeCluster);
					}
					// queue.add(n);
				}
			}

		}

		///// from BFS
		boolean visited[] = new boolean[graph.getNumberOfNodes()];
		// Create a queue for BFS
		LinkedList<Integer> queue = new LinkedList<Integer>();

		// Mark the current node as visited and enqueue it
		visited[indexOfNode] = true;
		queue.add(nodeId);

		System.out.println("BFS from " + nodeId + ": ");
		while (queue.size() != 0) {
			// Dequeue a vertex from queue and print it
			nodeId = queue.poll();

			// if (!nodeId == graph.isIntermediateNode(nodeId)) {
			//
			// }

			System.out.print(nodeId + " ");

			// Get all adjacent vertices of the dequeued vertex s
			// If a adjacent has not been visited, then mark it
			// visited and enqueue it
			Iterator<Integer> i = graph.getEdges(nodeId).listIterator();
			while (i.hasNext()) {
				int n = i.next();
				int indexOfNode2 = graph.getIndexOfNodeByNodeId(n);
				if (!visited[indexOfNode2]) {
					visited[indexOfNode2] = true;
					queue.add(n);
				}
			}
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
		Iterator<Integer> i = graph.getEdges(nodeId).listIterator();
		while (i.hasNext()) {
			int n = i.next();
			int indexOfNode2 = graph.getIndexOfNodeByNodeId(n);
			if (!visited[indexOfNode2])
				DFSUtil(n, visited);
		}
	}

	public void printNodeClusters() {
		// You should call cluster() method before printNodeClusters()

	}

}
