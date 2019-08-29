package algorithm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import framework.Graph2;
import framework.Node;

public class GraphTraversal {
	private Queue<Integer> que = new LinkedList<Integer>();
	private ArrayList<Integer> isVisitedNode = new ArrayList<Integer>();
	private boolean setVistited = false;

	public void BFS(Graph2 gr, int nodeId) {
		que.add(nodeId);
		while (!que.isEmpty()) {
			for (int nId : gr.getEdges(nodeId)) {
				if (!que.contains(nId)) {
					que.add(nId);
				}
				isVisitedNode.add(que.remove());
				nodeId = que.peek();
			}

		}

	}

	public void BFS2(Graph2 gr, int nodeId) {
		boolean visited[] = new boolean[gr.getNumberOfNodes()];

		LinkedList<Integer> queue = new LinkedList<Integer>();

		visited[nodeId] = true;
		queue.add(nodeId);

		while (queue.size() == 0) {
			nodeId = queue.poll();
			System.out.println(nodeId + " ");

			Iterator<Integer> i = gr.getEdges(nodeId).listIterator();

			while (i.hasNext()) {
				int n = i.next();
				if (!visited[n]) {
					visited[n] = true;
					queue.add(n);
				}
			}
		}

	}

	public void printBFS() {
		for (int i = 0; i < isVisitedNode.size(); i++) {
			System.out.println("The traversal looks like: " + i);
		}
	}

	public void DFS(Graph2 gr, int nodeId) {

	}

}
