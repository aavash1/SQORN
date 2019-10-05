package algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import framework.Graph;
import framework.RoadObject;
import framework.UtilsManagment;

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
		MultiValuedMap<Double, Integer> foundObjectsWithSortedDistance = new HashSetValuedHashMap<Double, Integer>();

		// foundNodesWithSortedDistance: Map<Total distance, Node Id>, Total distance -
		// distance from query object to the found Node
		MultiValuedMap<Double, Integer> foundNodesWithSortedDistance = new HashSetValuedHashMap<Double, Integer>();

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
		double distanceFromQueryToEndNode = m_graph.getDistanceFromNodeToGivenObjOnSameEdge(sourceEndNodeId,
				sourceObjId);

		foundNodesWithSortedDistance.put(distanceFromQueryToStartNode, sourceStartNodeId);
		foundNodesWithSortedDistance.put(distanceFromQueryToEndNode, sourceEndNodeId);

		nonClearedNodeQueue
				.addAll(foundNodesWithSortedDistance.get(UtilsManagment.getMinimumKey(foundNodesWithSortedDistance)));
		nonClearedNodeQueue
				.addAll(foundNodesWithSortedDistance.get(UtilsManagment.getSecondKey(foundNodesWithSortedDistance)));
		int currentNode;
		while (nonClearedNodeQueue.size() != 0) {

			currentNode = nonClearedNodeQueue.poll();
			double distanceFromQueryToCurrentNode = UtilsManagment.getMapKey(foundNodesWithSortedDistance, currentNode);

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
					if (distanceFromQueryToAdjNode < UtilsManagment.getMinimumKey(foundObjectsWithSortedDistance)) {
						nonClearedNodeQueue.add(adjNode);
					}
					visitedEdges.add(edgeId);
				}
			}
		}
		if (!foundObjectsWithSortedDistance.isEmpty()) {
			double nearestObjDist = UtilsManagment.getMinimumKey(foundObjectsWithSortedDistance);
			// System.out.println("Distance to the nearest Object: " + nearestObjDist);
			int nearestObjId = UtilsManagment
					.getFirstElementFromCollection(foundObjectsWithSortedDistance.get(nearestObjDist));
			int edgeOfNearestObj = m_graph.getEdgeIdOfRoadObject(nearestObjId);
			nearestObj = m_graph.getRoadObjectOnEdge(edgeOfNearestObj, nearestObjId);
		} else {
			nearestObj = null;
		}

		return nearestObj;
	}

	public int getNearestObjectIdToGivenObjOnMap(Graph gr, int sourceObjId) {
		return getNearestObjectToGivenObjOnMap(gr, sourceObjId).getObjectId();
	}

	public double getDistanceToNearestObjOnMap(Graph gr, int sourceObjId) {
		m_graph = gr;

		// Source Info
		int sourceEdgeId = m_graph.getEdgeIdOfRoadObject(sourceObjId);
		RoadObject sourceObj = m_graph.getDatasetRoadObject(sourceObjId);
		int sourceStartNodeId = m_graph.getStartNodeIdOfEdge(sourceEdgeId);
		int sourceEndNodeId = m_graph.getEndNodeIdOfEdge(sourceEdgeId);

		// foundObjectsWithSortedDistance: Map<Total distance, Object Id>, Total
		// distance from query object to the found object
		MultiValuedMap<Double, Integer> foundObjectsWithSortedDistance = new HashSetValuedHashMap<Double, Integer>();

		// foundNodesWithSortedDistance: Map<Total distance, Node Id>, Total distance -
		// distance from query object to the found Node
		MultiValuedMap<Double, Integer> foundNodesWithSortedDistance = new HashSetValuedHashMap<Double, Integer>();

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
		double distanceFromQueryToEndNode = m_graph.getDistanceFromNodeToGivenObjOnSameEdge(sourceEndNodeId,
				sourceObjId);

		foundNodesWithSortedDistance.put(distanceFromQueryToStartNode, sourceStartNodeId);
		foundNodesWithSortedDistance.put(distanceFromQueryToEndNode, sourceEndNodeId);

		nonClearedNodeQueue
				.addAll(foundNodesWithSortedDistance.get(UtilsManagment.getMinimumKey(foundNodesWithSortedDistance)));
		nonClearedNodeQueue
				.addAll(foundNodesWithSortedDistance.get(UtilsManagment.getSecondKey(foundNodesWithSortedDistance)));

		int currentNode;
		while (nonClearedNodeQueue.size() != 0) {

			currentNode = nonClearedNodeQueue.poll();
			double distanceFromQueryToCurrentNode = UtilsManagment.getMapKey(foundNodesWithSortedDistance, currentNode);

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
					if (distanceFromQueryToAdjNode < UtilsManagment.getMinimumKey(foundObjectsWithSortedDistance)) {
						nonClearedNodeQueue.add(adjNode);
					}
					visitedEdges.add(edgeId);
				}
			}
		}
		double nearestObjDist = 0.0;
		if (!foundObjectsWithSortedDistance.isEmpty()) {
			nearestObjDist = UtilsManagment.getMinimumKey(foundObjectsWithSortedDistance);

		}

		return nearestObjDist;
	}

	///// get Nearest True Object to a given Object (with Distance) on whole Map
	public Map<RoadObject, Double> getNearestTrueObjectToGivenObjOnMap(Graph gr, int sourceObjId) {

		m_graph = gr;
		RoadObject nearestObj;
		Map<RoadObject, Double> nearestObjectWithDist = new HashMap<RoadObject, Double>(); // return value
		// Source Info
		int sourceEdgeId = m_graph.getEdgeIdOfRoadObject(sourceObjId);
		RoadObject sourceObj = m_graph.getDatasetRoadObject(sourceObjId);
		int sourceStartNodeId = m_graph.getStartNodeIdOfEdge(sourceEdgeId);
		int sourceEndNodeId = m_graph.getEndNodeIdOfEdge(sourceEdgeId);

		// foundObjectsWithSortedDistance: Map<Total distance, Object Id>, Total
		// distance from query object to the found object
		MultiValuedMap<Double, Integer> foundObjectsWithSortedDistance = new HashSetValuedHashMap<Double, Integer>();

		// foundNodesWithSortedDistance: Map<Total distance, Node Id>, Total distance -
		// distance from query object to the found Node
		MultiValuedMap<Double, Integer> foundNodesWithSortedDistance = new HashSetValuedHashMap<Double, Integer>();

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
		double distanceFromQueryToEndNode = m_graph.getDistanceFromNodeToGivenObjOnSameEdge(sourceEndNodeId,
				sourceObjId);

		foundNodesWithSortedDistance.put(distanceFromQueryToStartNode, sourceStartNodeId);
		foundNodesWithSortedDistance.put(distanceFromQueryToEndNode, sourceEndNodeId);

		nonClearedNodeQueue
				.addAll(foundNodesWithSortedDistance.get(UtilsManagment.getMinimumKey(foundNodesWithSortedDistance)));
		nonClearedNodeQueue
				.addAll(foundNodesWithSortedDistance.get(UtilsManagment.getSecondKey(foundNodesWithSortedDistance)));

		int currentNode;
		while (nonClearedNodeQueue.size() != 0) {

			currentNode = nonClearedNodeQueue.poll();
			double distanceFromQueryToCurrentNode = UtilsManagment.getMapKey(foundNodesWithSortedDistance, currentNode);

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

					if (foundObjectsWithSortedDistance.isEmpty()) {

						nonClearedNodeQueue.add(adjNode);
					} else {
						if (distanceFromQueryToAdjNode < UtilsManagment.getMinimumKey(foundObjectsWithSortedDistance)) {
							nonClearedNodeQueue.add(adjNode);
						}
					}

					visitedEdges.add(edgeId);
				}
			}
		}
		if (!foundObjectsWithSortedDistance.isEmpty()) {

			double nearestObjDist = UtilsManagment.getMinimumKey(foundObjectsWithSortedDistance);
			// System.out.println("Distance to the nearest Object: " + nearestObjDist);
			int nearestObjId = UtilsManagment
					.getFirstElementFromCollection(foundObjectsWithSortedDistance.get(nearestObjDist));

			int edgeOfNearestObj = m_graph.getEdgeIdOfRoadObject(nearestObjId);
			nearestObj = m_graph.getRoadObjectOnEdge(edgeOfNearestObj, nearestObjId);

			nearestObjectWithDist.put(nearestObj, nearestObjDist);
		}

		return nearestObjectWithDist;
	}

	public int getNearestTrueObjectIdToGivenObjOnMap(Graph gr, int sourceObjId) {

		Map<RoadObject, Double> nearestObjectWithDist = getNearestTrueObjectToGivenObjOnMap(gr, sourceObjId);

		if (nearestObjectWithDist != null) {
			RoadObject[] objs = nearestObjectWithDist.keySet().toArray(new RoadObject[0]);
			return objs[0].getObjectId();
		}
		return -1;
	}

	//

	///// get Nearest False Object to a given Object (with Distance) on whole Map
	public Map<RoadObject, Double> getNearestFalseObjectToGivenObjOnMap(Graph gr, int sourceObjId) {

		m_graph = gr;
		RoadObject nearestObj;
		Map<RoadObject, Double> nearestObjectWithDist = new HashMap<RoadObject, Double>(); // return value
		// Source Info
		int sourceEdgeId = m_graph.getEdgeIdOfRoadObject(sourceObjId);
		RoadObject sourceObj = m_graph.getDatasetRoadObject(sourceObjId);
		int sourceStartNodeId = m_graph.getStartNodeIdOfEdge(sourceEdgeId);
		int sourceEndNodeId = m_graph.getEndNodeIdOfEdge(sourceEdgeId);

		// foundObjectsWithSortedDistance: Map<Total distance, Object Id>, Total
		// distance from query object to the found object
		MultiValuedMap<Double, Integer> foundObjectsWithSortedDistance = new HashSetValuedHashMap<Double, Integer>();

		// foundNodesWithSortedDistance: Map<Total distance, Node Id>, Total distance -
		// distance from query object to the found Node
		MultiValuedMap<Double, Integer> foundNodesWithSortedDistance = new HashSetValuedHashMap<Double, Integer>();

		// Create a queue for Traversing
		LinkedList<Integer> nonClearedNodeQueue = new LinkedList<Integer>();

		Set<Integer> visitedEdges = new HashSet<Integer>();

		RoadObject nearestObjOnSameEdge = m_graph.getNearestFalseObjectToGivenObjOnEdge(sourceEdgeId, sourceObjId);
		if (nearestObjOnSameEdge != null) {
			foundObjectsWithSortedDistance.put(m_graph.getDistanceBetweenTwoObjectsOnEdge(sourceEdgeId, sourceObjId,
					nearestObjOnSameEdge.getObjectId()), nearestObjOnSameEdge.getObjectId());
		}
		visitedEdges.add(sourceEdgeId);

		double distanceFromQueryToStartNode = sourceObj.getDistanceFromStartNode();
		double distanceFromQueryToEndNode = m_graph.getDistanceFromNodeToGivenObjOnSameEdge(sourceEndNodeId,
				sourceObjId);

		foundNodesWithSortedDistance.put(distanceFromQueryToStartNode, sourceStartNodeId);
		foundNodesWithSortedDistance.put(distanceFromQueryToEndNode, sourceEndNodeId);

		nonClearedNodeQueue
				.addAll(foundNodesWithSortedDistance.get(UtilsManagment.getMinimumKey(foundNodesWithSortedDistance)));
		nonClearedNodeQueue
				.addAll(foundNodesWithSortedDistance.get(UtilsManagment.getSecondKey(foundNodesWithSortedDistance)));
		int currentNode;
		while (nonClearedNodeQueue.size() != 0) {

			currentNode = nonClearedNodeQueue.poll();
			if (foundNodesWithSortedDistance.isEmpty()) {
				System.out.println("foundNodesWithSortedDistance is empty " + foundNodesWithSortedDistance
						+ " current node " + currentNode);
			}

			double distanceFromQueryToCurrentNode = UtilsManagment.getMapKey(foundNodesWithSortedDistance, currentNode);

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
						if (distanceFromQueryToAdjNode < UtilsManagment.getMinimumKey(foundObjectsWithSortedDistance)) {
							nonClearedNodeQueue.add(adjNode);
						}
					}
					visitedEdges.add(edgeId);
				}
			}
		}
		if (!foundObjectsWithSortedDistance.isEmpty()) {
			double nearestObjDist = UtilsManagment.getMinimumKey(foundObjectsWithSortedDistance);
			// System.out.println("Distance to the nearest Object: " + nearestObjDist);
			int nearestObjId = UtilsManagment
					.getFirstElementFromCollection(foundObjectsWithSortedDistance.get(nearestObjDist));
			int edgeOfNearestObj = m_graph.getEdgeIdOfRoadObject(nearestObjId);
			nearestObj = m_graph.getRoadObjectOnEdge(edgeOfNearestObj, nearestObjId);

			nearestObjectWithDist.put(nearestObj, nearestObjDist);

		}

		return nearestObjectWithDist;
	}

///// get Nearest False Object to a given Object (with Distance) on whole Map
//	public Map<RoadObject, Double> getNearestFalseObjectToGivenObjOnMap2(Graph gr, int sourceObjId) {
//
//		m_graph = gr;
//		RoadObject nearestObj;
//		Map<RoadObject, Double> nearestObjectWithDist = new HashMap<RoadObject, Double>(); // return value
//		// Source Info
//		int sourceEdgeId = m_graph.getEdgeIdOfRoadObject(sourceObjId);
//		RoadObject sourceObj = m_graph.getDatasetRoadObject(sourceObjId);
//		int sourceStartNodeId = m_graph.getStartNodeIdOfEdge(sourceEdgeId);
//		int sourceEndNodeId = m_graph.getEndNodeIdOfEdge(sourceEdgeId);
//
//		// foundObjectsWithSortedDistance: Map<Total distance, Object Id>, Total
//		// distance from query object to the found object
//		MultiValuedMap<Double, Integer> foundObjectsWithSortedDistance = new HashSetValuedHashMap<Double, Integer>();
//
//		// foundNodesWithSortedDistance: Map<Total distance, Node Id>, Total distance -
//		// distance from query object to the found Node
//		MultiValuedMap<Double, Integer> foundNodesWithSortedDistance = new HashSetValuedHashMap<Double, Integer>();
//
//		// Create a queue for Traversing
//		LinkedList<Integer> nonClearedNodeQueue = new LinkedList<Integer>();
//
//		Set<Integer> visitedEdges = new HashSet<Integer>();
//
//		RoadObject nearestObjOnSameEdge = m_graph.getNearestFalseObjectToGivenObjOnEdge(sourceEdgeId, sourceObjId);
//		if (nearestObjOnSameEdge != null) {
//			foundObjectsWithSortedDistance.put(m_graph.getDistanceBetweenTwoObjectsOnEdge(sourceEdgeId, sourceObjId,
//					nearestObjOnSameEdge.getObjectId()), nearestObjOnSameEdge.getObjectId());
//		}
//		visitedEdges.add(sourceEdgeId);
//
//		double distanceFromQueryToStartNode = sourceObj.getDistanceFromStartNode();
//		double distanceFromQueryToEndNode = m_graph.getDistanceFromNodeToGivenObjOnSameEdge(sourceEndNodeId,
//				sourceObjId);
//
//		foundNodesWithSortedDistance.put(distanceFromQueryToStartNode, sourceStartNodeId);
//		foundNodesWithSortedDistance.put(distanceFromQueryToEndNode, sourceEndNodeId);
//
//		nonClearedNodeQueue
//				.addAll(foundNodesWithSortedDistance.get(UtilsManagment.getMinimumKey(foundNodesWithSortedDistance)));
//		nonClearedNodeQueue
//				.addAll(foundNodesWithSortedDistance.get(UtilsManagment.getSecondKey(foundNodesWithSortedDistance)));
//
//		int currentNode;
//		while (nonClearedNodeQueue.size() != 0) {
//
//			currentNode = nonClearedNodeQueue.poll();
//			if (foundNodesWithSortedDistance.isEmpty()) {
//				System.out.println("foundNodesWithSortedDistance is empty " + foundNodesWithSortedDistance
//						+ " current node " + currentNode);
//			}
//
//			double distanceFromQueryToCurrentNode = UtilsManagment.getMapKey(foundNodesWithSortedDistance, currentNode);
//
//			Iterator<Integer> iteratorAdjNodes = m_graph.getAdjNodeIds(currentNode).listIterator();
//			while (iteratorAdjNodes.hasNext()) {
//				int adjNode = iteratorAdjNodes.next();
//
//				int edgeId = m_graph.getEdgeId(currentNode, adjNode);
//				if (visitedEdges.contains(edgeId))
//					continue;
//
//				RoadObject nearestObjOnAdjEdge = m_graph.getNearestFalseObjectToGivenNodeOnEdge(edgeId, currentNode);
//
//				if (nearestObjOnAdjEdge != null) {
//
//					double distanceFromQueryObj = distanceFromQueryToCurrentNode
//							+ m_graph.getDistanceToNearestFalseObjectFromGivenNodeOnEdge(edgeId, currentNode);
//					foundObjectsWithSortedDistance.put(distanceFromQueryObj, nearestObjOnAdjEdge.getObjectId());
//					visitedEdges.add(edgeId);
//				} else {
//					double adjEdgeLength;
//					if (m_graph.isStartNode(currentNode, edgeId)) {
//						adjEdgeLength = m_graph.getEdgeDistance(currentNode, adjNode);
//					} else {
//						adjEdgeLength = m_graph.getEdgeDistance(adjNode, currentNode);
//					}
//					double distanceFromQueryToAdjNode = distanceFromQueryToCurrentNode + adjEdgeLength;
//					foundNodesWithSortedDistance.put(distanceFromQueryToAdjNode, adjNode);
//					if (foundObjectsWithSortedDistance.isEmpty()) {
//
//						nonClearedNodeQueue.add(adjNode);
//					} else {
//						if (distanceFromQueryToAdjNode < UtilsManagment.getMinimumKey(foundObjectsWithSortedDistance)) {
//							nonClearedNodeQueue.add(adjNode);
//						}
//					}
//					visitedEdges.add(edgeId);
//				}
//			}
//		}
//		if (!foundObjectsWithSortedDistance.isEmpty()) {
//
//			double nearestObjDist = UtilsManagment.getMinimumKey(foundObjectsWithSortedDistance);
//			// System.out.println("Distance to the nearest Object: " + nearestObjDist);
//			int nearestObjId = UtilsManagment
//					.getFirstElementFromCollection(foundObjectsWithSortedDistance.get(nearestObjDist));
//
//			int edgeOfNearestObj = m_graph.getEdgeIdOfRoadObject(nearestObjId);
//			nearestObj = m_graph.getRoadObjectOnEdge(edgeOfNearestObj, nearestObjId);
//
//			nearestObjectWithDist.put(nearestObj, nearestObjDist);
//
//		}
//
//		return nearestObjectWithDist;
//	}

	public int getNearestFalseObjectIdToGivenObjOnMap(Graph gr, int sourceObjId) {
		if (getNearestFalseObjectToGivenObjOnMap(gr, sourceObjId) != null) {
			RoadObject[] objs = getNearestFalseObjectToGivenObjOnMap(gr, sourceObjId).keySet()
					.toArray(new RoadObject[0]);
			return objs[0].getObjectId();

		}
		return -1;
	}

	// ///// get Nearest True Object to a given Object on whole Map
	// public RoadObject getNearestTrueObjectToGivenObjOnMap(Graph gr, int
	// sourceObjId) {
	//
	// m_graph = gr;
	// RoadObject nearestObj;
	//
	// // Source Info
	// int sourceEdgeId = m_graph.getEdgeIdOfRoadObject(sourceObjId);
	// RoadObject sourceObj = m_graph.getDatasetRoadObject(sourceObjId);
	// int sourceStartNodeId = m_graph.getStartNodeIdOfEdge(sourceEdgeId);
	// int sourceEndNodeId = m_graph.getEndNodeIdOfEdge(sourceEdgeId);
	//
	// // foundObjectsWithSortedDistance: Map<Total distance, Object Id>, Total
	// // distance from query object to the found object
	// SortedMap<Double, Integer> foundObjectsWithSortedDistance = new
	// TreeMap<Double, Integer>();
	//
	// // foundNodesWithSortedDistance: Map<Total distance, Node Id>, Total distance
	// -
	// // distance from query object to the found Node
	// SortedMap<Double, Integer> foundNodesWithSortedDistance = new TreeMap<Double,
	// Integer>();
	//
	// // Create a queue for Traversing
	// LinkedList<Integer> nonClearedNodeQueue = new LinkedList<Integer>();
	//
	// Set<Integer> visitedEdges = new HashSet<Integer>();
	//
	// RoadObject nearestObjOnSameEdge =
	// m_graph.getNearestTrueObjectToGivenObjOnEdge(sourceEdgeId, sourceObjId);
	// if (nearestObjOnSameEdge != null) {
	// foundObjectsWithSortedDistance.put(m_graph.getDistanceToNearestTrueObjectOnEdge(sourceEdgeId,
	// sourceObjId),
	// nearestObjOnSameEdge.getObjectId());
	// }
	// visitedEdges.add(sourceEdgeId);
	//
	// double distanceFromQueryToStartNode = sourceObj.getDistanceFromStartNode();
	// double distanceFromQueryToEndNode =
	// m_graph.getDistanceFromNodeToGivenObjOnSameEdge(sourceEndNodeId,
	// sourceObjId);
	//
	// foundNodesWithSortedDistance.put(distanceFromQueryToStartNode,
	// sourceStartNodeId);
	// foundNodesWithSortedDistance.put(distanceFromQueryToEndNode,
	// sourceEndNodeId);
	//
	// nonClearedNodeQueue.add(foundNodesWithSortedDistance.get(foundNodesWithSortedDistance.firstKey()));
	// nonClearedNodeQueue.add(foundNodesWithSortedDistance.get(foundNodesWithSortedDistance.lastKey()));
	//
	// int currentNode;
	// while (nonClearedNodeQueue.size() != 0) {
	//
	// currentNode = nonClearedNodeQueue.poll();
	// double distanceFromQueryToCurrentNode =
	// getMapKey(foundNodesWithSortedDistance, currentNode);
	//
	// Iterator<Integer> iteratorAdjNodes =
	// m_graph.getAdjNodeIds(currentNode).listIterator();
	// while (iteratorAdjNodes.hasNext()) {
	// int adjNode = iteratorAdjNodes.next();
	//
	// int edgeId = m_graph.getEdgeId(currentNode, adjNode);
	// if (visitedEdges.contains(edgeId))
	// continue;
	//
	// RoadObject nearestObjOnAdjEdge =
	// m_graph.getNearestTrueObjectToGivenNodeOnEdge(edgeId, currentNode);
	//
	// if (nearestObjOnAdjEdge != null) {
	//
	// double distanceFromQueryObj = distanceFromQueryToCurrentNode
	// + m_graph.getDistanceToNearestTrueObjectFromGivenNodeOnEdge(edgeId,
	// currentNode);
	// foundObjectsWithSortedDistance.put(distanceFromQueryObj,
	// nearestObjOnAdjEdge.getObjectId());
	// visitedEdges.add(edgeId);
	// } else {
	// double adjEdgeLength;
	// if (m_graph.isStartNode(currentNode, edgeId)) {
	// adjEdgeLength = m_graph.getEdgeDistance(currentNode, adjNode);
	// } else {
	// adjEdgeLength = m_graph.getEdgeDistance(adjNode, currentNode);
	// }
	// double distanceFromQueryToAdjNode = distanceFromQueryToCurrentNode +
	// adjEdgeLength;
	// foundNodesWithSortedDistance.put(distanceFromQueryToAdjNode, adjNode);
	// if (foundObjectsWithSortedDistance.isEmpty()) {
	// System.out.println("Empty");
	// }
	// if (distanceFromQueryToAdjNode < foundObjectsWithSortedDistance.firstKey()) {
	// nonClearedNodeQueue.add(adjNode);
	// }
	// visitedEdges.add(edgeId);
	// }
	// }
	// }
	// if (!foundObjectsWithSortedDistance.isEmpty()) {
	// double nearestObjDist = foundObjectsWithSortedDistance.firstKey();
	// // System.out.println("Distance to the nearest Object: " + nearestObjDist);
	//
	// int nearestObjId = foundObjectsWithSortedDistance.get(nearestObjDist);
	// int edgeOfNearestObj = m_graph.getEdgeIdOfRoadObject(nearestObjId);
	// nearestObj = m_graph.getRoadObjectOnEdge(edgeOfNearestObj, nearestObjId);
	// } else {
	// nearestObj = null;
	// }
	//
	// return nearestObj;
	// }

	// public double getDistanceToNearestTrueObject(Graph gr, int sourceObjId) {
	// m_graph = gr;
	//
	// // Source Info
	// int sourceEdgeId = m_graph.getEdgeIdOfRoadObject(sourceObjId);
	// RoadObject sourceObj = m_graph.getDatasetRoadObject(sourceObjId);
	// int sourceStartNodeId = m_graph.getStartNodeIdOfEdge(sourceEdgeId);
	// int sourceEndNodeId = m_graph.getEndNodeIdOfEdge(sourceEdgeId);
	//
	// // foundObjectsWithSortedDistance: Map<Total distance, Object Id>, Total
	// // distance from query object to the found object
	// SortedMap<Double, Integer> foundObjectsWithSortedDistance = new
	// TreeMap<Double, Integer>();
	//
	// // foundNodesWithSortedDistance: Map<Total distance, Node Id>, Total distance
	// -
	// // distance from query object to the found Node
	// SortedMap<Double, Integer> foundNodesWithSortedDistance = new TreeMap<Double,
	// Integer>();
	//
	// // Create a queue for Traversing
	// LinkedList<Integer> nonClearedNodeQueue = new LinkedList<Integer>();
	//
	// Set<Integer> visitedEdges = new HashSet<Integer>();
	//
	// RoadObject nearestObjOnSameEdge =
	// m_graph.getNearestTrueObjectToGivenObjOnEdge(sourceEdgeId, sourceObjId);
	// if (nearestObjOnSameEdge != null) {
	// foundObjectsWithSortedDistance.put(m_graph.getDistanceToNearestTrueObjectOnEdge(sourceEdgeId,
	// sourceObjId),
	// nearestObjOnSameEdge.getObjectId());
	// }
	// visitedEdges.add(sourceEdgeId);
	//
	// double distanceFromQueryToStartNode = sourceObj.getDistanceFromStartNode();
	// double distanceFromQueryToEndNode =
	// m_graph.getDistanceFromNodeToGivenObjOnSameEdge(sourceEndNodeId,
	// sourceObjId);
	//
	// foundNodesWithSortedDistance.put(distanceFromQueryToStartNode,
	// sourceStartNodeId);
	// foundNodesWithSortedDistance.put(distanceFromQueryToEndNode,
	// sourceEndNodeId);
	//
	// nonClearedNodeQueue.add(foundNodesWithSortedDistance.get(foundNodesWithSortedDistance.firstKey()));
	// nonClearedNodeQueue.add(foundNodesWithSortedDistance.get(foundNodesWithSortedDistance.lastKey()));
	//
	// int currentNode;
	// while (nonClearedNodeQueue.size() != 0) {
	//
	// currentNode = nonClearedNodeQueue.poll();
	// double distanceFromQueryToCurrentNode =
	// getMapKey(foundNodesWithSortedDistance, currentNode);
	//
	// Iterator<Integer> iteratorAdjNodes =
	// m_graph.getAdjNodeIds(currentNode).listIterator();
	// while (iteratorAdjNodes.hasNext()) {
	// int adjNode = iteratorAdjNodes.next();
	//
	// int edgeId = m_graph.getEdgeId(currentNode, adjNode);
	// if (visitedEdges.contains(edgeId))
	// continue;
	//
	// RoadObject nearestObjOnAdjEdge =
	// m_graph.getNearestTrueObjectToGivenNodeOnEdge(edgeId, currentNode);
	//
	// if (nearestObjOnAdjEdge != null) {
	//
	// double distanceFromQueryObj = distanceFromQueryToCurrentNode
	// + m_graph.getDistanceToNearestTrueObjectFromGivenNodeOnEdge(edgeId,
	// currentNode);
	// foundObjectsWithSortedDistance.put(distanceFromQueryObj,
	// nearestObjOnAdjEdge.getObjectId());
	// visitedEdges.add(edgeId);
	// } else {
	// double adjEdgeLength;
	// if (m_graph.isStartNode(currentNode, edgeId)) {
	// adjEdgeLength = m_graph.getEdgeDistance(currentNode, adjNode);
	// } else {
	// adjEdgeLength = m_graph.getEdgeDistance(adjNode, currentNode);
	// }
	// double distanceFromQueryToAdjNode = distanceFromQueryToCurrentNode +
	// adjEdgeLength;
	// foundNodesWithSortedDistance.put(distanceFromQueryToAdjNode, adjNode);
	// if (distanceFromQueryToAdjNode < foundObjectsWithSortedDistance.firstKey()) {
	// nonClearedNodeQueue.add(adjNode);
	// }
	// visitedEdges.add(edgeId);
	// }
	// }
	// }
	// return foundObjectsWithSortedDistance.firstKey();
	//

	// public RoadObject getNearestFalseObjectToGivenObjOnMap(Graph gr, int
	// sourceObjId) {
	//
	// m_graph = gr;
	// RoadObject nearestObj;// return value
	//
	// // Source Info
	// int sourceEdgeId = m_graph.getEdgeIdOfRoadObject(sourceObjId);
	// RoadObject sourceObj = m_graph.getDatasetRoadObject(sourceObjId);
	// int sourceStartNodeId = m_graph.getStartNodeIdOfEdge(sourceEdgeId);
	// int sourceEndNodeId = m_graph.getEndNodeIdOfEdge(sourceEdgeId);
	//
	// // foundObjectsWithSortedDistance: Map<Total distance, Object Id>, Total
	// // distance from query object to the found object
	// SortedMap<Double, Integer> foundObjectsWithSortedDistance = new
	// TreeMap<Double, Integer>();
	//
	// // foundNodesWithSortedDistance: Map<Total distance, Node Id>, Total distance
	// -
	// // distance from query object to the found Node
	// SortedMap<Double, Integer> foundNodesWithSortedDistance = new TreeMap<Double,
	// Integer>();
	//
	// // Create a queue for Traversing
	// LinkedList<Integer> nonClearedNodeQueue = new LinkedList<Integer>();
	//
	// Set<Integer> visitedEdges = new HashSet<Integer>();
	//
	// RoadObject nearestObjOnSameEdge =
	// m_graph.getNearestFalseObjectToGivenObjOnEdge(sourceEdgeId, sourceObjId);
	// if (nearestObjOnSameEdge != null) {
	// foundObjectsWithSortedDistance.put(m_graph.getDistanceToNearestFalseObjectOnEdge(sourceEdgeId,
	// sourceObjId),
	// nearestObjOnSameEdge.getObjectId());
	// }
	// visitedEdges.add(sourceEdgeId);
	//
	// double distanceFromQueryToStartNode = sourceObj.getDistanceFromStartNode();
	// double distanceFromQueryToEndNode =
	// m_graph.getDistanceFromNodeToGivenObjOnSameEdge(sourceEndNodeId,
	// sourceObjId);
	//
	// foundNodesWithSortedDistance.put(distanceFromQueryToStartNode,
	// sourceStartNodeId);
	// foundNodesWithSortedDistance.put(distanceFromQueryToEndNode,
	// sourceEndNodeId);
	//
	// nonClearedNodeQueue.add(foundNodesWithSortedDistance.get(foundNodesWithSortedDistance.firstKey()));
	// nonClearedNodeQueue.add(foundNodesWithSortedDistance.get(foundNodesWithSortedDistance.lastKey()));
	//
	// int currentNode;
	// while (nonClearedNodeQueue.size() != 0) {
	//
	// currentNode = nonClearedNodeQueue.poll();
	// double distanceFromQueryToCurrentNode =
	// getMapKey(foundNodesWithSortedDistance, currentNode);
	//
	// Iterator<Integer> iteratorAdjNodes =
	// m_graph.getAdjNodeIds(currentNode).listIterator();
	// while (iteratorAdjNodes.hasNext()) {
	// int adjNode = iteratorAdjNodes.next();
	//
	// int edgeId = m_graph.getEdgeId(currentNode, adjNode);
	// if (visitedEdges.contains(edgeId))
	// continue;
	//
	// RoadObject nearestObjOnAdjEdge =
	// m_graph.getNearestFalseObjectToGivenNodeOnEdge(edgeId, currentNode);
	//
	// if (nearestObjOnAdjEdge != null) {
	//
	// double distanceFromQueryObj = distanceFromQueryToCurrentNode
	// + m_graph.getDistanceToNearestFalseObjectFromGivenNodeOnEdge(edgeId,
	// currentNode);
	// foundObjectsWithSortedDistance.put(distanceFromQueryObj,
	// nearestObjOnAdjEdge.getObjectId());
	// visitedEdges.add(edgeId);
	// } else {
	// double adjEdgeLength;
	// if (m_graph.isStartNode(currentNode, edgeId)) {
	// adjEdgeLength = m_graph.getEdgeDistance(currentNode, adjNode);
	// } else {
	// adjEdgeLength = m_graph.getEdgeDistance(adjNode, currentNode);
	// }
	// double distanceFromQueryToAdjNode = distanceFromQueryToCurrentNode +
	// adjEdgeLength;
	// foundNodesWithSortedDistance.put(distanceFromQueryToAdjNode, adjNode);
	// if (foundObjectsWithSortedDistance.isEmpty()) {
	//
	// nonClearedNodeQueue.add(adjNode);
	// } else {
	// if (distanceFromQueryToAdjNode < foundObjectsWithSortedDistance.firstKey()) {
	// nonClearedNodeQueue.add(adjNode);
	// }
	// }
	// visitedEdges.add(edgeId);
	// }
	// }
	// }
	// if (!foundObjectsWithSortedDistance.isEmpty()) {
	// double nearestObjDist = foundObjectsWithSortedDistance.firstKey();
	// // System.out.println("Distance to the nearest Object: " + nearestObjDist);
	//
	// int nearestObjId = foundObjectsWithSortedDistance.get(nearestObjDist);
	// int edgeOfNearestObj = m_graph.getEdgeIdOfRoadObject(nearestObjId);
	// nearestObj = m_graph.getRoadObjectOnEdge(edgeOfNearestObj, nearestObjId);
	// } else {
	// nearestObj = null;
	// }
	//
	// return nearestObj;
	// }

	// public double getDistanceToNearestFalseObjectOnMap(Graph gr, int sourceObjId)
	// {
	// m_graph = gr;
	// // Source Info
	// int sourceEdgeId = m_graph.getEdgeIdOfRoadObject(sourceObjId);
	// RoadObject sourceObj = m_graph.getDatasetRoadObject(sourceObjId);
	// int sourceStartNodeId = m_graph.getStartNodeIdOfEdge(sourceEdgeId);
	// int sourceEndNodeId = m_graph.getEndNodeIdOfEdge(sourceEdgeId);
	//
	// // foundObjectsWithSortedDistance: Map<Total distance, Object Id>, Total
	// // distance from query object to the found object
	// SortedMap<Double, Integer> foundObjectsWithSortedDistance = new
	// TreeMap<Double, Integer>();
	//
	// // foundNodesWithSortedDistance: Map<Total distance, Node Id>, Total distance
	// -
	// // distance from query object to the found Node
	// SortedMap<Double, Integer> foundNodesWithSortedDistance = new TreeMap<Double,
	// Integer>();
	//
	// // Create a queue for Traversing
	// LinkedList<Integer> nonClearedNodeQueue = new LinkedList<Integer>();
	//
	// Set<Integer> visitedEdges = new HashSet<Integer>();
	//
	// RoadObject nearestObjOnSameEdge =
	// m_graph.getNearestFalseObjectToGivenObjOnEdge(sourceEdgeId, sourceObjId);
	// if (nearestObjOnSameEdge != null) {
	// foundObjectsWithSortedDistance.put(m_graph.getDistanceToNearestFalseObjectOnEdge(sourceEdgeId,
	// sourceObjId),
	// nearestObjOnSameEdge.getObjectId());
	// }
	// visitedEdges.add(sourceEdgeId);
	//
	// double distanceFromQueryToStartNode = sourceObj.getDistanceFromStartNode();
	// double distanceFromQueryToEndNode =
	// m_graph.getDistanceFromNodeToGivenObjOnSameEdge(sourceEndNodeId,
	// sourceObjId);
	//
	// foundNodesWithSortedDistance.put(distanceFromQueryToStartNode,
	// sourceStartNodeId);
	// foundNodesWithSortedDistance.put(distanceFromQueryToEndNode,
	// sourceEndNodeId);
	//
	// nonClearedNodeQueue.add(foundNodesWithSortedDistance.get(foundNodesWithSortedDistance.firstKey()));
	// nonClearedNodeQueue.add(foundNodesWithSortedDistance.get(foundNodesWithSortedDistance.lastKey()));
	//
	// int currentNode;
	// while (nonClearedNodeQueue.size() != 0) {
	//
	// currentNode = nonClearedNodeQueue.poll();
	// double distanceFromQueryToCurrentNode =
	// getMapKey(foundNodesWithSortedDistance, currentNode);
	//
	// Iterator<Integer> iteratorAdjNodes =
	// m_graph.getAdjNodeIds(currentNode).listIterator();
	// while (iteratorAdjNodes.hasNext()) {
	// int adjNode = iteratorAdjNodes.next();
	//
	// int edgeId = m_graph.getEdgeId(currentNode, adjNode);
	// if (visitedEdges.contains(edgeId))
	// continue;
	//
	// RoadObject nearestObjOnAdjEdge =
	// m_graph.getNearestFalseObjectToGivenNodeOnEdge(edgeId, currentNode);
	//
	// if (nearestObjOnAdjEdge != null) {
	//
	// double distanceFromQueryObj = distanceFromQueryToCurrentNode
	// + m_graph.getDistanceToNearestFalseObjectFromGivenNodeOnEdge(edgeId,
	// currentNode);
	// foundObjectsWithSortedDistance.put(distanceFromQueryObj,
	// nearestObjOnAdjEdge.getObjectId());
	// visitedEdges.add(edgeId);
	// } else {
	// double adjEdgeLength;
	// if (m_graph.isStartNode(currentNode, edgeId)) {
	// adjEdgeLength = m_graph.getEdgeDistance(currentNode, adjNode);
	// } else {
	// adjEdgeLength = m_graph.getEdgeDistance(adjNode, currentNode);
	// }
	// double distanceFromQueryToAdjNode = distanceFromQueryToCurrentNode +
	// adjEdgeLength;
	// foundNodesWithSortedDistance.put(distanceFromQueryToAdjNode, adjNode);
	// if (foundObjectsWithSortedDistance.isEmpty()) {
	//
	// nonClearedNodeQueue.add(adjNode);
	// } else {
	// if (distanceFromQueryToAdjNode < foundObjectsWithSortedDistance.firstKey()) {
	// nonClearedNodeQueue.add(adjNode);
	// }
	// }
	// visitedEdges.add(edgeId);
	// }
	// }
	// }
	// double nearestObjDist = 0.0;
	// if (!foundObjectsWithSortedDistance.isEmpty()) {
	// nearestObjDist = foundObjectsWithSortedDistance.firstKey();
	// // System.out.println("Distance to the nearest Object: " + nearestObjDist);
	//
	// }
	//
	// return nearestObjDist;
	// }

	public <K, V> K getMapKey(Map<K, V> map, V value) {
		if (map != null) {
			for (Map.Entry<K, V> entry : map.entrySet()) {
				if (entry.getValue().equals(value)) {
					return entry.getKey();
				}
			}
		}
		return null;
	}

}
