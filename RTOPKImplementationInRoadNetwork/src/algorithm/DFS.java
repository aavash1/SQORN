package algorithm;

import java.util.ArrayList;
import java.util.Iterator;

import framework.Graph2;

public class DFS {

	private Graph2 graph;
	private ArrayList<Integer> listDFS = new ArrayList<Integer>();

	public ArrayList<Integer> getDFSList() {
		return listDFS;
	}

	// A function used by DFS
	private void DFSUtil(int nodeId, boolean visited[]) {
		// Mark the current node as visited and print it
		int indexOfNode = graph.getIndexOfNodeByNodeId(nodeId);
		visited[indexOfNode] = true;
		System.out.print(nodeId + " ");
		listDFS.add(nodeId);

		// for testing
		System.out.println(graph.getEdges(nodeId));

		// Recur for all the vertices adjacent to this vertex
		Iterator<Integer> i = graph.getEdges(nodeId).listIterator();
		while (i.hasNext()) {
			int n = i.next();
			int indexOfNode2 = graph.getIndexOfNodeByNodeId(n);
			if (!visited[indexOfNode2])
				DFSUtil(n, visited);
		}
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

}
