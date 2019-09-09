package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import framework.Graph2;
import framework.RoadObject;

public class NearestNeighbor2 {

	private Graph2 graph;

	///// get Nearest Object to a given Object on whole Map
	public RoadObject getNearestObjectToGivenObjOnMap(Graph2 gr, int sourceObjId) {

		graph = gr;
		RoadObject nearestObj;// = new RoadObject(); // return value

		// Source Info
		int sourceEdgeId = graph.getEdgeIdOfRoadObject(sourceObjId);
		RoadObject sourceObj = graph.getRoadObject(sourceObjId);
		int sourceStartNodeId = graph.getStartNodeIdOfEdge(sourceEdgeId);
		int sourceEndNodeId = graph.getEndNodeIdOfEdge(sourceEdgeId);

		// foundObjectsWithSortedDistance: Map<Total distance, Object Id>, Total
		// distance from query object to the found object
		SortedMap<Double, Integer> foundObjectsWithSortedDistance = new TreeMap<Double, Integer>();

		// foundNodesWithSortedDistance: Map<Total distance, Node Id>, Total distance -
		// distance from query object to the found Node
		SortedMap<Double, Integer> foundNodesWithSortedDistance = new TreeMap<Double, Integer>();

		// Create a queue for Traversing
		LinkedList<Integer> nonClearedNodeQueue = new LinkedList<Integer>();

		Set<Integer> visitedEdges = new HashSet<Integer>();
		RoadObject nearestObjOnSameEdge = graph.getNearestObjectToGivenObjOnEdge(sourceEdgeId, sourceObjId);
		if (nearestObjOnSameEdge != null) {
			foundObjectsWithSortedDistance.put(
					graph.getDistanceToNearestObjectFromGivenObjOnEdge(sourceEdgeId, sourceObjId),
					nearestObjOnSameEdge.getObjectId());
		}
		visitedEdges.add(sourceEdgeId);

		double distanceFromQueryToStartNode = sourceObj.getDistanceFromStartNode();
		double distanceFromQueryToEndNode = graph.getDistanceFromNodeToGivenObjOnSameEdge(sourceEndNodeId, sourceObjId);

		foundNodesWithSortedDistance.put(distanceFromQueryToStartNode, sourceStartNodeId);
		foundNodesWithSortedDistance.put(distanceFromQueryToEndNode, sourceEndNodeId);

		nonClearedNodeQueue.add(foundNodesWithSortedDistance.get(foundNodesWithSortedDistance.firstKey()));
		nonClearedNodeQueue.add(foundNodesWithSortedDistance.get(foundNodesWithSortedDistance.lastKey()));

		int currentNode;
		while (nonClearedNodeQueue.size() != 0) {

			currentNode = nonClearedNodeQueue.poll();
			double distanceFromQueryToCurrentNode = getMapKey(foundNodesWithSortedDistance, currentNode);

			Iterator<Integer> iteratorAdjNodes = graph.getAdjNodeIds(currentNode).listIterator();
			while (iteratorAdjNodes.hasNext()) {
				int adjNode = iteratorAdjNodes.next();

				int edgeId = graph.getEdgeId(currentNode, adjNode);
				if (visitedEdges.contains(edgeId))
					continue;

				RoadObject nearestObjOnAdjEdge = graph.getNearestObjectToGivenNodeOnEdge(edgeId, currentNode);

				if (nearestObjOnAdjEdge != null) {

					double distanceFromQueryObj = distanceFromQueryToCurrentNode
							+ graph.getDistanceToNearestObjectFromGivenNodeOnEdge(edgeId, currentNode);
					foundObjectsWithSortedDistance.put(distanceFromQueryObj, nearestObjOnAdjEdge.getObjectId());
					visitedEdges.add(edgeId);
				} else {
					double adjEdgeLength;
					if (graph.isStartNode(currentNode, edgeId)) {
						adjEdgeLength = graph.getEdgeDistance(currentNode, adjNode);
					} else {
						adjEdgeLength = graph.getEdgeDistance(adjNode, currentNode);
					}
					double distanceFromQueryToAdjNode = distanceFromQueryToCurrentNode + adjEdgeLength;
					foundNodesWithSortedDistance.put(distanceFromQueryToAdjNode, adjNode);
					nonClearedNodeQueue.add(adjNode);
					visitedEdges.add(edgeId);
				}
			}
		}
		double nearestObjDist = foundObjectsWithSortedDistance.firstKey();
		System.out.println("Distance to the nearest Object: " + nearestObjDist);

		int nearestObjId = foundObjectsWithSortedDistance.get(nearestObjDist);
		int edgeOfNearestObj = graph.getEdgeIdOfRoadObject(nearestObjId);
		nearestObj = graph.getRoadObjectOnEdge(edgeOfNearestObj, nearestObjId);

		return nearestObj;
	}

	public <K, V> K getMapKey(Map<K, V> map, V value) {
		for (Map.Entry<K, V> entry : map.entrySet()) {
			if (entry.getValue().equals(value)) {
				return entry.getKey();
			}
		}
		return null;
	}

}
