package algorithm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import framework.Graph;

import framework.Node;

public class ClusertingAlgorithm {
	private ArrayList<List<Integer>> nodeSequences = new ArrayList<List<Integer>>();
	private ArrayList<Integer> visitedEdges = new ArrayList<Integer>();
	private LinkedList<Integer> nonClearedNodes = new LinkedList<Integer>();

	// private ArrayList<Integer> nodePath2 = new ArrayList<Integer>();
	private int currentNodeId;// = 0;

	public void cluster(Graph graph, int initialNodeIdToTraverse) {
		nonClearedNodes.add(initialNodeIdToTraverse);
		while (nonClearedNodes.size() != 0) {
			currentNodeId = nonClearedNodes.poll();
			Iterator<Integer> i = graph.getAdjNodeIds(currentNodeId).listIterator();
			while (i.hasNext()) {
				int adjNode = i.next();
				ArrayList<Integer> nodeCluster = new ArrayList<Integer>();

				if (!visitedEdges.contains(graph.getEdgeId(currentNodeId, adjNode))) {

					visitedEdges.add(graph.getEdgeId(currentNodeId, adjNode));
					nodeCluster.add(currentNodeId);
					if (graph.isTerminalNode(adjNode)) {

						nodeCluster.add(adjNode);
						nodeSequences.add(nodeCluster);

					} else if (graph.isIntersectionNode(adjNode)) {
						nodeCluster.add(adjNode);
						nodeSequences.add(nodeCluster);
						nonClearedNodes.add(adjNode);

					} else {
						nodeCluster.add(adjNode);

						traverseIntermediateNode(graph, adjNode, nodeCluster);

					}
				}

			}
		}
		// System.out.println(nodeSequences);
	}

	private void traverseIntermediateNode(Graph graph, int nodeId, ArrayList<Integer> nodePath) {
		Iterator<Integer> j = graph.getAdjNodeIds(nodeId).listIterator();
		while (j.hasNext()) {
			int n = j.next();
			if (!visitedEdges.contains(graph.getEdgeId(nodeId, n))) {
				visitedEdges.add(graph.getEdgeId(nodeId, n));
				if (graph.isTerminalNode(n)) {
					nodePath.add(n);
					nodeSequences.add(nodePath);

				} else if (graph.isIntersectionNode(n)) {
					nodePath.add(n);
					nodeSequences.add(nodePath);
					nonClearedNodes.add(n);

				} else {
					nodePath.add(n);
					traverseIntermediateNode(graph, n, nodePath);

				}

			}

		}
	}

	public void printClusteredNodes() {
		for (int i = 0; i < nodeSequences.size(); i++) {
			System.out.println("[ " + nodeSequences.get(i) + " ]");
		}
	}

}
