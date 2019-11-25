package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import framework.PathManager;
import framework.Graph;
import framework.RoadObject;

public class ANNNaive {

	private Graph m_graph;

	// m_nearestNeighborSets: Map<QueryObjectId, DataObjectId>.
	private Map<Integer, Integer> m_nearestNeighborSets = new HashMap<Integer, Integer>();

	public Map<Integer, Integer> compute(Graph gr, boolean queryObjectType) {
		System.out.println();
		System.out.println("Naive ANN is running ... ");
		this.m_graph = gr;
		int edgeCounter = 0;
		int objGlobalCounter = 0;
		NearestNeighbor nn = new NearestNeighbor();

		if (queryObjectType) {
			// Query object = True Object; Data Object = False Object
			System.out.println("Query object = True Object (" + m_graph.getTotalNumberOfTrueObjects()
					+ "); Data Object = False Object (" + m_graph.getTotalNumberOfFalseObjects() + ")");

			// Iterate through all objects on every edge
			for (Integer edgeId : m_graph.getObjectsOnEdges().keySet()) {
				edgeCounter++;
			//System.out.println(
						//"Completed Number of Edges: " + edgeCounter + " out of " + m_graph.getObjectsOnEdges().size());
				int objCounter = 0;
				for (RoadObject trueObj : m_graph.getTrueObjectsOnEdgeSortedByDist(edgeId)) {
					objCounter++;
					objGlobalCounter++;
					System.out.println(objCounter + " completed Objects on Edge: " + edgeId + " out of "
							+ m_graph.getTrueObjectsIdOnGivenEdge(edgeId).size() + "; Total completed Objs: "
							+ objGlobalCounter + " / " + m_graph.getTotalNumberOfTrueObjects());
					int nearestFalseObjId = nn.getNearestFalseObjectIdToGivenObjOnMap(m_graph, trueObj.getObjectId());// .getNearestFalseObjectIdToGivenObjOnMap(graph,
																														// trueObj.getObjectId());
					m_nearestNeighborSets.put(trueObj.getObjectId(), nearestFalseObjId);
				}
			}
		} else {
			// Query object = False Object; Data Object = True Object
			System.out.println("Query object = False Object (" + m_graph.getTotalNumberOfFalseObjects()
					+ "); Data Object = True Object (" + m_graph.getTotalNumberOfTrueObjects() + ")");

			// Iterate through all objects on every edge
			for (Integer edgeId : m_graph.getObjectsOnEdges().keySet()) {
				edgeCounter++;
				System.out.println(
						"Completed Number of Edges: " + edgeCounter + " out of " + m_graph.getObjectsOnEdges().size());
				int objCounter = 0;
				for (RoadObject falseObj : m_graph.getFalseObjectsOnEdgeSortedByDist(edgeId)) {
					objCounter++;
					objGlobalCounter++;
					System.out.println(objCounter + " completed Objects on Edge: " + edgeId + " out of "
							+ m_graph.getFalseObjectsIdOnGivenEdge(edgeId).size() + "; Total completed Objs: "
							+ objGlobalCounter + " / " + m_graph.getTotalNumberOfFalseObjects());
					int nearestTrueObjId = nn.getNearestTrueObjectIdToGivenObjOnMap(m_graph, falseObj.getObjectId());
					m_nearestNeighborSets.put(falseObj.getObjectId(), nearestTrueObjId);
				}
			}
		}
		// System.out.println(nearestNeighborSets);
		return m_nearestNeighborSets;
	}

	public Map<Integer, Integer> compute(Graph gr) {
		System.out.println();
		System.out.println("Naive ANN is running ... ");
		this.m_graph = gr;

		NearestNeighbor nn = new NearestNeighbor();
		int edgeCounter = 0;
		int objGlobalCounter = 0;
		if (m_graph.getTotalNumberOfFalseObjects() >= m_graph.getTotalNumberOfTrueObjects()) {
			// Query object = True Object; Data Object = False Object
			System.out.println("Query object = True Object (" + m_graph.getTotalNumberOfTrueObjects()
					+ "); Data Object = False Object (" + m_graph.getTotalNumberOfFalseObjects() + ")");

			// Iterate through all objects on every edge
			for (Integer edgeId : m_graph.getObjectsOnEdges().keySet()) {
				edgeCounter++;
				System.out.println(
						"Completed Number of Edges: " + edgeCounter + " out of " + m_graph.getObjectsOnEdges().size());
				int objCounter = 0;
				for (RoadObject trueObj : m_graph.getTrueObjectsOnEdgeSortedByDist(edgeId)) {
					objCounter++;
					objGlobalCounter++;
					System.out.println(objCounter + " completed Objects on Edge: " + edgeId + " out of "
							+ m_graph.getTrueObjectsIdOnGivenEdge(edgeId).size() + "; Total completed Objs: "
							+ objGlobalCounter + " / " + m_graph.getTotalNumberOfTrueObjects());
					int nearestFalseObjId = nn.getNearestFalseObjectIdToGivenObjOnMap(m_graph, trueObj.getObjectId());// .getNearestFalseObjectIdToGivenObjOnMap(graph,
																														// trueObj.getObjectId());
					m_nearestNeighborSets.put(trueObj.getObjectId(), nearestFalseObjId);
				}
			}
		} else {

			// Query object = False Object; Data Object = True Object
			System.out.println("Query object = False Object (" + m_graph.getTotalNumberOfFalseObjects()
					+ "); Data Object = True Object (" + m_graph.getTotalNumberOfTrueObjects() + ")");

			// Iterate through all objects on every edge
			for (Integer edgeId : m_graph.getObjectsOnEdges().keySet()) {
				edgeCounter++;
				System.out.println(
						"Completed Number of Edges: " + edgeCounter + " out of " + m_graph.getObjectsOnEdges().size());
				int objCounter = 0;
				for (RoadObject falseObj : m_graph.getFalseObjectsOnEdgeSortedByDist(edgeId)) {
					objCounter++;
					objGlobalCounter++;
					System.out.println(objCounter + " completed Objects on Edge: " + edgeId + " out of "
							+ m_graph.getFalseObjectsIdOnGivenEdge(edgeId).size()  + "; Total completed Objs: "
							+ objGlobalCounter + " / " + m_graph.getTotalNumberOfFalseObjects());
					int nearestTrueObjId = nn.getNearestTrueObjectIdToGivenObjOnMap(m_graph, falseObj.getObjectId());
					m_nearestNeighborSets.put(falseObj.getObjectId(), nearestTrueObjId);
				}
			}
		}
		// System.out.println(nearestNeighborSets);
		return m_nearestNeighborSets;
	}

	public void printNearestNeighborSets() {
		if (!m_nearestNeighborSets.isEmpty()) {
			System.out.println();
			System.out.println("Result of latest Naive ANN computation: ");
			for (Integer queryObj : m_nearestNeighborSets.keySet()) {
				System.out.println("Query Object ID: " + queryObj + " [edge #" + m_graph.getEdgeIdOfRoadObject(queryObj)
						+ "] - Data Object ID: " + m_nearestNeighborSets.get(queryObj) + " [edge #"
						+ m_graph.getEdgeIdOfRoadObject(m_nearestNeighborSets.get(queryObj)) + "]");
				// System.out.println("Query Object ID: " + queryObj + " - Data Object ID: " +
				// nearestNeighborSets.get(queryObj) + " Distance: " );

			}
		}
	}

}
