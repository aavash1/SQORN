package algorithm;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import framework.Graph;
import framework.Graph2;
import framework.Node;

public class ClusertingAlgorithm {
	private LinkedList<ArrayList<Integer>> seqList = new LinkedList<ArrayList<Integer>>();

	public LinkedList<ArrayList<Integer>> getSequenceList() {
		return seqList;
	}

	public void clusterAllNodes(Graph2 graph) {
		ArrayList<Integer> visitedNodeSeq = new ArrayList<Integer>();

		for (Node node : graph.getNodesWithInfo()) {
			int counter = 0;
			int nodeId = node.getNodeId();
			System.out.println("Node Id: " + nodeId);
			int size = graph.getEdges(nodeId).size();
			System.out.println("Size is: " + size);

			if (!graph.isIntermediateNode(nodeId)) {
				ArrayList<Integer> nodeSeq = new ArrayList<Integer>();
				if (!visitedNodeSeq.contains(nodeId)) {
					visitedNodeSeq.add(nodeId);
					nodeSeq.add(nodeId);
				}
				// int flagNode = 0;
				for (int i : graph.getEdges(nodeId)) {
					counter = graph.getEdges(nodeId).size();
					while (counter != 0) {
						if ((graph.isTerminalNode(nodeId)) || (graph.isIntersectionNode(nodeId))) {
							if (!visitedNodeSeq.contains(i)) {
								nodeSeq.add(i);
								counter = 0;
							}

						} else if (!graph.isIntermediateNode(i)) {
							nodeSeq.add(i);
							graph.getEdges(i);
							nodeId = i;
							counter = 0;

						}

					}

				}

			} else {
				ArrayList<Integer> nodeSeq = new ArrayList<Integer>();
				for (int i : graph.getEdges(nodeId)) {
					counter = graph.getEdges(nodeId).size();
					while (counter != 0) {
						if ((graph.isTerminalNode(nodeId)) || (graph.isIntersectionNode(nodeId))) {
							if (!visitedNodeSeq.contains(i)) {
								nodeSeq.add(i);
								counter = 0;
							}

						} else if (!graph.isIntermediateNode(i)) {
							nodeSeq.add(i);
							graph.getEdges(i);
							nodeId = i;
							counter = 0;

						}

					}

				}

			}

		}

		// System.out.println(graph.getEdges(nodeId));
//			for (int i : graph.getEdges(nodeId)) {
//				System.out.println(i);
//
//			}
		// while (graph.getEdges(nodeId).size() != 0) {
		// System.out.println("Size of Edges: " + graph.getEdges(nodeId).size());
//				for (int i = 0; i < graph.getEdges(nodeId).size(); i++) {
//					if ((graph.isTerminalNode(node.getNodeId())) || (graph.isIntermediateNode(node.getNodeId()))) {
//						nodeSeq.add(node.getNodeId());
//					} else if (graph.isIntersectionNode(node.getNodeId())) {
//						ArrayList<Integer> edge = new ArrayList<Integer>();
//						edge.add(nodeId);
//						edge.add(node.getNodeId());
//						seqList.add(edge);
//					}
//
//				}
		// }
		// seqList.add(nodeSeq);

	}

	public void displayNodeSequence() {
		for (ArrayList<Integer> a : seqList) {
			System.out.println(a.iterator());

		}

	}

}
