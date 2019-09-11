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

import framework.Graph;
import framework.RoadObject;

public class NearestNeighbor {

	private Graph m_graph;

	///// get Nearest Object to a given Object on whole Map
	public RoadObject getNearestObjectToGivenObjOnMap(Graph gr, int sourceObjId) {

		m_graph = gr;
		RoadObject nearestObj;// return value

		// Source Info
		int sourceEdgeId = m_graph.getEdgeIdOfRoadObject(sourceObjId);
		RoadObject sourceObj = m_graph.getDatasetRoadObject(sourceObjId);
		int sourceStartNodeId = m_graph.getStartNodeIdOfEdge(sourceEdgeId);
		int sourceEndNodeId = m_graph.getEndNodeIdOfEdge(sourceEdgeId);

		// foundObjectsWithSortedDistance: Map<Total distance, Object Id>, Total
		// distance from query object to the found object
		SortedMap<Double, Integer> foundObjectsWithSortedDistance = new TreeMap<Double, Integer>();

		// foundNodesWithSortedDistance: Map<Total distance, Node Id>, Total distance -
		// distance from query object to the found Node
		SortedMap<Double, Integer> foundNodesWithSortedDistance = new TreeMap<Double, Integer>();

		// Create a queue for Traversing
		LinkedList<Integer> nonClearedNodeQueue = new LinkedList<Integer>();

		Set<Integer> visitedEdges = new HashSet<Integer>();

		RoadObject nearestObjOnSameEdge = m_graph.getNearestObjectToGivenObjOnEdge(sourceEdgeId, sourceObjId);
		if (nearestObjOnSameEdge != null) {
			foundObjectsWithSortedDistance.put(
					m_graph.getDistanceToNearestObjectFromGivenObjOnEdge(sourceEdgeId, sourceObjId),
					nearestObjOnSameEdge.getObjectId());
		}
		visitedEdges.add(sourceEdgeId);

		double distanceFromQueryToStartNode = sourceObj.getDistanceFromStartNode();
		double distanceFromQueryToEndNode = m_graph.getDistanceFromNodeToGivenObjOnSameEdge(sourceEndNodeId, sourceObjId);

		foundNodesWithSortedDistance.put(distanceFromQueryToStartNode, sourceStartNodeId);
		foundNodesWithSortedDistance.put(distanceFromQueryToEndNode, sourceEndNodeId);

		nonClearedNodeQueue.add(foundNodesWithSortedDistance.get(foundNodesWithSortedDistance.firstKey()));
		nonClearedNodeQueue.add(foundNodesWithSortedDistance.get(foundNodesWithSortedDistance.lastKey()));

		int currentNode;
		while (nonClearedNodeQueue.size() != 0) {

			currentNode = nonClearedNodeQueue.poll();
			double distanceFromQueryToCurrentNode = getMapKey(foundNodesWithSortedDistance, currentNode);

			Iterator<Integer> iteratorAdjNodes = m_graph.getAdjNodeIds(currentNode).listIterator();
			while (iteratorAdjNodes.hasNext()) {
				int adjNode = iteratorAdjNodes.next();

				int edgeId = m_graph.getEdgeId(currentNode, adjNode);
				if (visitedEdges.contains(edgeId))
					continue;

				RoadObject nearestObjOnAdjEdge = m_graph.getNearestObjectToGivenNodeOnEdge(edgeId, currentNode);

				if (nearestObjOnAdjEdge != null) {

					double distanceFromQueryObj = distanceFromQueryToCurrentNode
							+ m_graph.getDistanceToNearestObjectFromGivenNodeOnEdge(edgeId, currentNode);
					foundObjectsWithSortedDistance.put(distanceFromQueryObj, nearestObjOnAdjEdge.getObjectId());
					visitedEdges.add(edgeId);
				} else {
					double adjEdgeLength;
					if (m_graph.isStartNode(currentNode, edgeId)) {
						adjEdgeLength = m_graph.getEdgeDistance(currentNode, adjNode);
					} else {
						adjEdgeLength = m_graph.getEdgeDistance(adjNode, currentNode);
					}
					double distanceFromQueryToAdjNode = distanceFromQueryToCurrentNode + adjEdgeLength;
					foundNodesWithSortedDistance.put(distanceFromQueryToAdjNode, adjNode);
					if (distanceFromQueryToAdjNode < foundObjectsWithSortedDistance.firstKey()) {
						nonClearedNodeQueue.add(adjNode);
					}
					visitedEdges.add(edgeId);
				}
			}
		}
		double nearestObjDist = foundObjectsWithSortedDistance.firstKey();
		// System.out.println("Distance to the nearest Object: " + nearestObjDist);

		int nearestObjId = foundObjectsWithSortedDistance.get(nearestObjDist);
		int edgeOfNearestObj = m_graph.getEdgeIdOfRoadObject(nearestObjId);
		nearestObj = m_graph.getRoadObjectOnEdge(edgeOfNearestObj, nearestObjId);

		return nearestObj;
	}

	public int getNearestObjectIdToGivenObjOnMap(Graph gr, int sourceObjId) {
		return getNearestObjectToGivenObjOnMap(gr, sourceObjId).getObjectId();
	}

	///// get Nearest True Object to a given Object on whole Map
	public RoadObject getNearestTrueObjectToGivenObjOnMap(Graph gr, int sourceObjId) {

		m_graph = gr;
		RoadObject nearestObj;

		// Source Info
		int sourceEdgeId = m_graph.getEdgeIdOfRoadObject(sourceObjId);
		RoadObject sourceObj = m_graph.getDatasetRoadObject(sourceObjId);
		int sourceStartNodeId = m_graph.getStartNodeIdOfEdge(sourceEdgeId);
		int sourceEndNodeId = m_graph.getEndNodeIdOfEdge(sourceEdgeId);

		// foundObjectsWithSortedDistance: Map<Total distance, Object Id>, Total
		// distance from query object to the found object
		SortedMap<Double, Integer> foundObjectsWithSortedDistance = new TreeMap<Double, Integer>();

		// foundNodesWithSortedDistance: Map<Total distance, Node Id>, Total distance -
		// distance from query object to the found Node
		SortedMap<Double, Integer> foundNodesWithSortedDistance = new TreeMap<Double, Integer>();

		// Create a queue for Traversing
		LinkedList<Integer> nonClearedNodeQueue = new LinkedList<Integer>();

		Set<Integer> visitedEdges = new HashSet<Integer>();

		RoadObject nearestObjOnSameEdge = m_graph.getNearestTrueObjectToGivenObjOnEdge(sourceEdgeId, sourceObjId);
		if (nearestObjOnSameEdge != null) {
			foundObjectsWithSortedDistance.put(m_graph.getDistanceToNearestTrueObjectOnEdge(sourceEdgeId, sourceObjId),
					nearestObjOnSameEdge.getObjectId());
		}
		visitedEdges.add(sourceEdgeId);

		double distanceFromQueryToStartNode = sourceObj.getDistanceFromStartNode();
		double distanceFromQueryToEndNode = m_graph.getDistanceFromNodeToGivenObjOnSameEdge(sourceEndNodeId, sourceObjId);

		foundNodesWithSortedDistance.put(distanceFromQueryToStartNode, sourceStartNodeId);
		foundNodesWithSortedDistance.put(distanceFromQueryToEndNode, sourceEndNodeId);

		nonClearedNodeQueue.add(foundNodesWithSortedDistance.get(foundNodesWithSortedDistance.firstKey()));
		nonClearedNodeQueue.add(foundNodesWithSortedDistance.get(foundNodesWithSortedDistance.lastKey()));

		int currentNode;
		while (nonClearedNodeQueue.size() != 0) {

			currentNode = nonClearedNodeQueue.poll();
			double distanceFromQueryToCurrentNode = getMapKey(foundNodesWithSortedDistance, currentNode);

			Iterator<Integer> iteratorAdjNodes = m_graph.getAdjNodeIds(currentNode).listIterator();
			while (iteratorAdjNodes.hasNext()) {
				int adjNode = iteratorAdjNodes.next();

				int edgeId = m_graph.getEdgeId(currentNode, adjNode);
				if (visitedEdges.contains(edgeId))
					continue;

				RoadObject nearestObjOnAdjEdge = m_graph.getNearestTrueObjectToGivenNodeOnEdge(edgeId, currentNode);

				if (nearestObjOnAdjEdge != null) {

					double distanceFromQueryObj = distanceFromQueryToCurrentNode
							+ m_graph.getDistanceToNearestTrueObjectFromGivenNodeOnEdge(edgeId, currentNode);
					foundObjectsWithSortedDistance.put(distanceFromQueryObj, nearestObjOnAdjEdge.getObjectId());
					visitedEdges.add(edgeId);
				} else {
					double adjEdgeLength;
					if (m_graph.isStartNode(currentNode, edgeId)) {
						adjEdgeLength = m_graph.getEdgeDistance(currentNode, adjNode);
					} else {
						adjEdgeLength = m_graph.getEdgeDistance(adjNode, currentNode);
					}
					double distanceFromQueryToAdjNode = distanceFromQueryToCurrentNode + adjEdgeLength;
					foundNodesWithSortedDistance.put(distanceFromQueryToAdjNode, adjNode);
					if (distanceFromQueryToAdjNode < foundObjectsWithSortedDistance.firstKey()) {
						nonClearedNodeQueue.add(adjNode);
					}
					visitedEdges.add(edgeId);
				}
			}
		}
		double nearestObjDist = foundObjectsWithSortedDistance.firstKey();
		// System.out.println("Distance to the nearest Object: " + nearestObjDist);

		int nearestObjId = foundObjectsWithSortedDistance.get(nearestObjDist);
		int edgeOfNearestObj = m_graph.getEdgeIdOfRoadObject(nearestObjId);
		nearestObj = m_graph.getRoadObjectOnEdge(edgeOfNearestObj, nearestObjId);

		return nearestObj;
	}

	public int getNearestTrueObjectIdToGivenObjOnMap(Graph gr, int sourceObjId) {
		if (getNearestTrueObjectToGivenObjOnMap(gr, sourceObjId) != null) {
			return getNearestTrueObjectToGivenObjOnMap(gr, sourceObjId).getObjectId();
		}
		return -1;
	}

	///// get Nearest False Object to a given Object on whole Map
	public RoadObject getNearestFalseObjectToGivenObjOnMap(Graph gr, int sourceObjId) {

		m_graph = gr;
		RoadObject nearestObj;// return value

		// Source Info
		int sourceEdgeId = m_graph.getEdgeIdOfRoadObject(sourceObjId);
		RoadObject sourceObj = m_graph.getDatasetRoadObject(sourceObjId);
		int sourceStartNodeId = m_graph.getStartNodeIdOfEdge(sourceEdgeId);
		int sourceEndNodeId = m_graph.getEndNodeIdOfEdge(sourceEdgeId);

		// foundObjectsWithSortedDistance: Map<Total distance, Object Id>, Total
		// distance from query object to the found object
		SortedMap<Double, Integer> foundObjectsWithSortedDistance = new TreeMap<Double, Integer>();

		// foundNodesWithSortedDistance: Map<Total distance, Node Id>, Total distance -
		// distance from query object to the found Node
		SortedMap<Double, Integer> foundNodesWithSortedDistance = new TreeMap<Double, Integer>();

		// Create a queue for Traversing
		LinkedList<Integer> nonClearedNodeQueue = new LinkedList<Integer>();

		Set<Integer> visitedEdges = new HashSet<Integer>();

		RoadObject nearestObjOnSameEdge = m_graph.getNearestFalseObjectToGivenObjOnEdge(sourceEdgeId, sourceObjId);
		if (nearestObjOnSameEdge != null) {
			foundObjectsWithSortedDistance.put(m_graph.getDistanceToNearestFalseObjectOnEdge(sourceEdgeId, sourceObjId),
					nearestObjOnSameEdge.getObjectId());
		}
		visitedEdges.add(sourceEdgeId);

		double distanceFromQueryToStartNode = sourceObj.getDistanceFromStartNode();
		double distanceFromQueryToEndNode = m_graph.getDistanceFromNodeToGivenObjOnSameEdge(sourceEndNodeId, sourceObjId);

		foundNodesWithSortedDistance.put(distanceFromQueryToStartNode, sourceStartNodeId);
		foundNodesWithSortedDistance.put(distanceFromQueryToEndNode, sourceEndNodeId);

		nonClearedNodeQueue.add(foundNodesWithSortedDistance.get(foundNodesWithSortedDistance.firstKey()));
		nonClearedNodeQueue.add(foundNodesWithSortedDistance.get(foundNodesWithSortedDistance.lastKey()));

		int currentNode;
		while (nonClearedNodeQueue.size() != 0) {

			currentNode = nonClearedNodeQueue.poll();
			double distanceFromQueryToCurrentNode = getMapKey(foundNodesWithSortedDistance, currentNode);

			Iterator<Integer> iteratorAdjNodes = m_graph.getAdjNodeIds(currentNode).listIterator();
			while (iteratorAdjNodes.hasNext()) {
				int adjNode = iteratorAdjNodes.next();

				int edgeId = m_graph.getEdgeId(currentNode, adjNode);
				if (visitedEdges.contains(edgeId))
					continue;

				RoadObject nearestObjOnAdjEdge = m_graph.getNearestFalseObjectToGivenNodeOnEdge(edgeId, currentNode);

				if (nearestObjOnAdjEdge != null) {

					double distanceFromQueryObj = distanceFromQueryToCurrentNode
							+ m_graph.getDistanceToNearestFalseObjectFromGivenNodeOnEdge(edgeId, currentNode);
					foundObjectsWithSortedDistance.put(distanceFromQueryObj, nearestObjOnAdjEdge.getObjectId());
					visitedEdges.add(edgeId);
				} else {
					double adjEdgeLength;
					if (m_graph.isStartNode(currentNode, edgeId)) {
						adjEdgeLength = m_graph.getEdgeDistance(currentNode, adjNode);
					} else {
						adjEdgeLength = m_graph.getEdgeDistance(adjNode, currentNode);
					}
					double distanceFromQueryToAdjNode = distanceFromQueryToCurrentNode + adjEdgeLength;
					foundNodesWithSortedDistance.put(distanceFromQueryToAdjNode, adjNode);
					if (foundObjectsWithSortedDistance.isEmpty()) {

						nonClearedNodeQueue.add(adjNode);
					} else {
						if (distanceFromQueryToAdjNode < foundObjectsWithSortedDistance.firstKey()) {
							nonClearedNodeQueue.add(adjNode);
						}
					}
					visitedEdges.add(edgeId);
				}
			}
		}
		if (!foundObjectsWithSortedDistance.isEmpty()) {
			double nearestObjDist = foundObjectsWithSortedDistance.firstKey();
			// System.out.println("Distance to the nearest Object: " + nearestObjDist);

			int nearestObjId = foundObjectsWithSortedDistance.get(nearestObjDist);
			int edgeOfNearestObj = m_graph.getEdgeIdOfRoadObject(nearestObjId);
			nearestObj = m_graph.getRoadObjectOnEdge(edgeOfNearestObj, nearestObjId);
		} else {
			nearestObj = null;
		}

		return nearestObj;
	}

	public int getNearestFalseObjectIdToGivenObjOnMap(Graph gr, int sourceObjId) {
		if (getNearestFalseObjectToGivenObjOnMap(gr, sourceObjId) != null) {
			return getNearestFalseObjectToGivenObjOnMap(gr, sourceObjId).getObjectId();
		}
		return -1;
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
