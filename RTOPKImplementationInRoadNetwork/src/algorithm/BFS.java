package algorithm;

import java.util.Iterator;
import java.util.LinkedList;

import framework.Graph2;
import framework.Node;

public class BFS {

	private Graph2 graph;

	public void traverse(Graph2 gr, int nodeId) {

		this.graph = gr;
		// Mark all the vertices as not visited(By default
		// set as false)
		boolean visited[] = new boolean[graph.getNumberOfNodes()];

		// Create a queue for BFS
		LinkedList<Integer> queue = new LinkedList<Integer>();

		// Mark the current node as visited and enqueue it		
		int indexOfNode = graph.getIndexOfNodeByNodeId(nodeId);
		visited[indexOfNode] = true;
		queue.add(nodeId);

		System.out.println("BFS from " + nodeId + ": ");
		while (queue.size() != 0) {
			// Dequeue a vertex from queue and print it
			nodeId = queue.poll();
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
}
