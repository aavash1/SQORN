package algorithm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import framework.Graph;
import framework.Node;

public class GraphTraversal {

	public void BFS2(Graph gr, int nodeId) {
		boolean visited[] = new boolean[gr.getNumberOfNodes()];

		LinkedList<Integer> queue = new LinkedList<Integer>();

		visited[nodeId] = true;
		queue.add(nodeId);

		while (queue.size() != 0) {
			nodeId = queue.poll();
			System.out.print(nodeId + " ");

			Iterator<Integer> i = gr.getAdjNodeIds(nodeId).listIterator();

			while (i.hasNext()) {
				int n = i.next();
				if (!visited[n]) {
					visited[n] = true;
					queue.add(n);
				}
			}
		}

	}

	public void DFS(Graph gr, int nodeId) {

	}

}
