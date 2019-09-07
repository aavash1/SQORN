package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import framework.Graph2;
import framework.PathManager;
import framework.RoadObject;

public class NearestNeighbor {

	private Graph2 graph;

///// get Nearest Object to a given Object on whole Map
	public RoadObject getNearestObjectToGivenObjOnMap(Graph2 gr, int sourceObjId) {

		graph = gr;
		RoadObject nearestObj = new RoadObject(); // return value

		// Source Info
		int sourceEdgeId = graph.getEdgeIdOfRoadObject(sourceObjId);
		RoadObject sourceObj = graph.getRoadObject(sourceObjId);
		int sourceStartNodeId = graph.getStartNodeIdOfEdge(sourceEdgeId);
		int sourceEndNodeId = graph.getEndNodeIdOfEdge(sourceEdgeId);

		PathManager paths = new PathManager();
		ArrayList<Integer> clearedNodes = new ArrayList<Integer>();
		ArrayList<RoadObject> foundRoadObjects = new ArrayList<RoadObject>();

		// foundObjectsWithDistance: Map<Object Id, Total distance>, Total distance -
		// distance from query object to the found object
		Map<Integer, Double> foundObjectsWithDistance = new HashMap<Integer, Double>();
		double minDistance = Double.MAX_VALUE;
		ArrayList<Integer> adjEdges = graph.getAdjacencyEdgeIds(sourceEdgeId);
		boolean nearestObjFound = false;

		RoadObject nearestObjOnSameEdge = graph.getNearestObjectToGivenObjOnEdge(sourceEdgeId, sourceObjId);
		if (nearestObjOnSameEdge != null) {
			foundRoadObjects.add(nearestObjOnSameEdge);
			foundObjectsWithDistance.put(nearestObjOnSameEdge.getObjectId(),
					graph.getDistanceToNearestObjectFromGivenObjOnEdge(sourceEdgeId, sourceObjId));
		}
		double distanceFromQueryToStartNode = sourceObj.getDistanceFromStartNode();
		double distanceFromQueryToEndNode = graph.getDistanceFromNodeToGivenObjOnSameEdge(sourceEndNodeId, sourceObjId);

		while (!nearestObjFound) {

			Iterator<Integer> iteratorFromStartNode = graph.getAdjNodeIds(sourceStartNodeId).listIterator();
			Iterator<Integer> iteratorFromEndNode = graph.getAdjNodeIds(sourceEndNodeId).listIterator();

			// Traversing from Start Node of the Source Edge
			while (iteratorFromStartNode.hasNext()) {
				int currentNodeId = sourceStartNodeId;
				int adjNode = iteratorFromStartNode.next();
				if (adjNode == sourceEndNodeId)
					continue;
				int edgeId = graph.getEdgeId(sourceStartNodeId, adjNode);
				RoadObject nearestObjOnAdjEdge = graph.getNearestObjectToGivenNodeOnEdge(edgeId, currentNodeId);

				if (nearestObjOnAdjEdge.getObjectId() != 0) {
					double distanceFromQueryObj = distanceFromQueryToStartNode + graph
							.getDistanceFromNodeToGivenObjOnSameEdge(currentNodeId, nearestObjOnAdjEdge.getObjectId());
					foundRoadObjects.add(nearestObjOnAdjEdge);
					foundObjectsWithDistance.put(nearestObjOnAdjEdge.getObjectId(), distanceFromQueryObj);
				}

			}
			// Traversing from End Node of the Source Edge
			while (iteratorFromEndNode.hasNext()) {
				int currentNodeId = sourceEndNodeId;
				int adjNode = iteratorFromEndNode.next();
				if (adjNode == sourceStartNodeId)
					continue;
				int edgeId = graph.getEdgeId(sourceEndNodeId, adjNode);
				RoadObject nearestObjOnAdjEdge = graph.getNearestObjectToGivenNodeOnEdge(edgeId, currentNodeId);

				if (nearestObjOnAdjEdge.getObjectId() != 0) {
					double distanceFromQueryObj = distanceFromQueryToEndNode + graph
							.getDistanceFromNodeToGivenObjOnSameEdge(currentNodeId, nearestObjOnAdjEdge.getObjectId());
					foundRoadObjects.add(nearestObjOnAdjEdge);
					foundObjectsWithDistance.put(nearestObjOnAdjEdge.getObjectId(), distanceFromQueryObj);
				}

			}

			// Condition of found nearest object
			if (true) {
				nearestObjFound = true;
			}
		}

		// m_adjancencyMap: Map<startNodeId, Map <endNodeId, edgeLength> >
		// m_adjancencyMap

		// m_objectsOnEdges: Map<Edge Id, ArrayList<RoadObjects>>
		// m_objectsOnEdges

		for (Integer edgeId : graph.getObjectsOnEdges().keySet()) {
			// adjNodes = getAdjacencyNodeIds(int_nodeId)

		}

		return nearestObj;
	}

	public int getNearestObjectIdToGivenObjOnMap(Graph2 gr, int sourceObjId) {
		return getNearestObjectToGivenObjOnMap(gr, sourceObjId).getObjectId();
	}

	// True Object
	public RoadObject getNearestTrueObjectToGivenObjOnMap(Graph2 gr, int sourceObjId) {
		graph = gr;

		RoadObject nearestTrueObj = new RoadObject();

		return nearestTrueObj;
	}

	public int getNearestTrueObjectIdToGivenObjOnMap(Graph2 gr, int sourceObjId) {
		if (getNearestTrueObjectToGivenObjOnMap(gr, sourceObjId) != null) {
			return getNearestTrueObjectToGivenObjOnMap(gr, sourceObjId).getObjectId();
		}
		return -1;
	}

	public RoadObject getNearestFalseObjectToGivenObjOnMap(Graph2 gr, int sourceObjId) {
		graph = gr;
		RoadObject nearestFalseObj = new RoadObject();

		// Source Info
		int sourceEdgeId = graph.getEdgeIdOfRoadObject(sourceObjId);
		RoadObject sourceObj = graph.getRoadObject(sourceObjId);
		int sourceStartNodeId = graph.getStartNodeIdOfEdge(sourceEdgeId);
		int sourceEndNodeId = graph.getEndNodeIdOfEdge(sourceEdgeId);

		PathManager paths = new PathManager();
		ArrayList<Integer> clearedNodes = new ArrayList<Integer>();
		double minDistance = Double.MAX_VALUE;
		// ArrayList<Integer> adjEdges = graph.getAdjacencyEdgeIds(sourceEdgeId);

		// m_adjancencyMap: Map<startNodeId, Map <endNodeId, edgeLength> >
		// m_adjancencyMap

		// m_objectsOnEdges: Map<Edge Id, ArrayList<RoadObjects>>
		// m_objectsOnEdges

		for (Integer edgeId : graph.getObjectsOnEdges().keySet()) {
			// adjNodes = getAdjacencyNodeIds(int_nodeId)

		}

		return nearestFalseObj;
	}

	public int getNearestFalseObjectIdToGivenObjOnMap(Graph2 gr, int sourceObjId) {
		if (getNearestFalseObjectToGivenObjOnMap(gr, sourceObjId) != null) {
			return getNearestFalseObjectToGivenObjOnMap(gr, sourceObjId).getObjectId();
		}
		return -1;
	}
}
