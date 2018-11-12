package algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import framework.*;

public class DijkstraAlgorithm {

	private int  current_Node;
	private double current_Node_Value = 0.00;
	private double nextNodeDistance = Double.MAX_VALUE;
	private boolean setVisited = false;

	private PriorityQueue<String> pq = new PriorityQueue<String>(); // It will store the nodes that are enqueued for
																	// visiting.

	
	
	private final List<Node> nodes;
	private final List<Edge> edges;
	Graph graph;
	Edge edge;
	Node node;
	private ArrayList<Node> visitedNodes;
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
sssss
	//
	public ArrayList<Node> getNeighborNode(Node nodeId) {
		ArrayList<Node> neighborNode = new ArrayList<Node>();
		for (int i = 0; i < graph.getEdgesWithInfo().size(); i++) {
			neighborNode.add((Node) graph.getAdjancencyMap().get(nodeId).keySet());
		}
		visitedNodes.add(nodeId);

		return neighborNode;
	}

	public double getDistanceBetweenCurrentNodeandOtherNode(int startnode, int endnode) {
		double distance = 0;
		edge.getLength();
		return distance;

	}

	public boolean setVistitedNode(Node nodeId) {
		if (!visitedNodes.contains(nodeId)) {
			return false;
		}
		return true;
	}

	public void dijkstra_getMinDistance(int sourceVertex) {
		boolean[] spt = new boolean[graph.getNumberOfNodes()];

		double[] distance = new double[graph.getNumberOfNodes()];

		// Initialize all the distance to infinity
		for (int i = 0; i < graph.getNumberOfNodes(); i++) {
			distance[i] = Double.MAX_VALUE;
		}

	}

}