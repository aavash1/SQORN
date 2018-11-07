package algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import framework.*;

public class DijkstraAlgorithm {

	private final List<Node> nodes;
	private final List<Edge> edges;
	Graph graph;
	Edge edge;
	// private Set<Vertex> settledNodes;
	// private Set<Vertex> unSettledNodes;
	// private Map<Vertex, Vertex> predecessors;
	// private Map<Vertex, Integer> distance;

	public DijkstraAlgorithm(Graph graph) {
		// create a copy of the array so that we can operate on this array
		this.graph = graph;
		this.edge = edge;
		this.nodes = graph.getNodesWithInfo();
		this.edges = graph.getEdgesWithInfo();
	}

	//
	public ArrayList<Node> getNeighborNode(Node nodeId) {
		ArrayList<Node> neighborNode = new ArrayList<Node>();
		for (int i = 0; i < graph.getEdgesWithInfo().size(); i++) {
			neighborNode.add((Node) graph.getAdjancencyMap().get(nodeId).keySet());
		}

		return neighborNode;
	}

	public void relaxNodes() {

	}

}