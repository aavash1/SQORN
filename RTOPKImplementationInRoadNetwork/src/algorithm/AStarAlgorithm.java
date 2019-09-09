package algorithm;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import framework.Edge;
import framework.Graph;
import framework.Graph;
import framework.Node;
import framework.UtilsManagment;

public class AStarAlgorithm extends RoutingAlgorithm {

	private final List<Node> nodes;
	private final List<Edge> edges;
	private Map<Node, Integer> distance;
	private Set<Node> settledNodes;
	private Set<Node> unSettledNodes;
	private Map<Node, Node> predecessors;

	public AStarAlgorithm(Graph graph) {
		// create a copy of the array so that we can operate on this array
		this.nodes = new ArrayList<Node>(graph.getNodesWithInfo());
		this.edges = new ArrayList<Edge>(graph.getEdgesWithInfo());
	}

	private double getHeuristicValue(Node currentNode, Node destinationNode) {
		return Point2D.distance(currentNode.getLatitude(), currentNode.getLongitude(), destinationNode.getLatitude(),
				destinationNode.getLongitude());
	}

	public void execute(Node sourceNode, Node destNode) {
		settledNodes = new HashSet<Node>();
		unSettledNodes = new HashSet<Node>();
		distance = new HashMap<Node, Integer>();
		predecessors = new HashMap<Node, Node>();
		distance.put(sourceNode, 0);
		unSettledNodes.add(sourceNode);
		while (unSettledNodes.size() > 0) {
			Node node = getMinimum(unSettledNodes, destNode);
			settledNodes.add(node);
			unSettledNodes.remove(node);
			findMinimalDistanceswithHeuristics(node);
		}
	}

	private void findMinimalDistances(Node node) {
		List<Node> adjacentNodes = getChildNodes(node);
		for (Node target : adjacentNodes) {
			if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)) {
				distance.put(target, getShortestDistance(node) + getDistance(node, target));
				predecessors.put(target, node);
				unSettledNodes.add(target);
			}
		}

	}

	private void findMinimalDistanceswithHeuristics(Node node) {
		List<Node> adjacentNodes = getChildNodes(node);
		for (Node target : adjacentNodes) {
			if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)) {
				distance.put(target, getShortestDistance(node) + getDistance(node, target));
				predecessors.put(target, node);
				unSettledNodes.add(target);
			}
		}

	}

	private int getDistance(Node currentNode, Node target) {
		for (Edge edge : edges) {
			if (edge.getStartNodeId() == currentNode.getNodeId() && edge.getEndNodeId() == target.getNodeId()) {
				return UtilsManagment.convertDoubleToInteger(edge.getLength());
			}
			// if (edge.getSource().equals(node)
			// && edge.getDestination().equals(target)) {
			// return edge.getWeight();
			// }
		}
		throw new RuntimeException("Should not happen");
	}

	private List<Node> getChildNodes(Node node) {
		List<Node> child = new ArrayList<Node>();
		for (Edge edge : edges) {
			if (edge.getStartNodeId() == node.getNodeId() && !isSettled(edge.getEndNodeId())) {
				child.add(getNode(edge.getEndNodeId()));
			}
			// if (edge.getSource().equals(node)
			// && !isSettled(edge.getDestination())) {
			// neighbors.add(edge.getDestination());
			// }
		}
		return child;
	}

	private Node getMinimum(Set<Node> nodes, Node destNode) {
		Node minimum = null;
		for (Node node : nodes) {
			if (minimum == null) {
				minimum = node;
			} else {
				if ( (getShortestDistance(node) + getHeuristicValue(node, destNode)) < (getShortestDistance(minimum) + getHeuristicValue(minimum, destNode)) ) {
					minimum = node;
				}
			}
		}
		return minimum;
	}

	private boolean isSettled(int nodeId) {
		return settledNodes.contains(nodeId);
	}
	// private boolean isSettled(Node vertex) {
	// return settledNodes.contains(vertex);
	// }

	private int getShortestDistance(Node destination) {
		Integer d = distance.get(destination);
		if (d == null) {
			return Integer.MAX_VALUE;
		} else {
			return d;
		}
	}

	/*
	 * This method returns the path from the source to the selected target and NULL
	 * if no path exists
	 */
	public LinkedList<Node> getPath(Node destinationNode) {
		LinkedList<Node> path = new LinkedList<Node>();
		Node step = destinationNode;
		// check if a path exists
		if (predecessors.get(step) == null) {
			return null;
		}
		path.add(step);
		while (predecessors.get(step) != null) {
			step = predecessors.get(step);
			path.add(step);
		}
		// Put it into the correct order
		Collections.reverse(path);
		return path;
	}

	////////////////////// own methods:
	private Node getNode(int nodeId) {
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getNodeId() == nodeId) {
				return nodes.get(i);
			}
		}
		return null;
	}

}
