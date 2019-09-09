package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import framework.PathManager;
import framework.Graph2;
import framework.RoadObject;

public class NaiveANN2 {

	private Graph2 graph;
	// Map<QueryObjectId, DataObjectId>.
	private Map<Integer, Integer> nearestNeighborSets = new HashMap<Integer, Integer>();
	private Map<Integer, Map<Integer, Double>> nearestNeighborSetsWithDist = new HashMap Map<Integer, Map<Integer, Double>>();
	//private PathManager annPaths = new PathManager();	
	// m_paths: Map<Length, List<NodeId>>; Length - length of current path
	//MultiValuedMap<Double, ArrayList<Integer>> m_paths = new HashSetValuedHashMap<Double, ArrayList<Integer>>();
	//private ArrayList<Integer> clearedNodes = new ArrayList<Integer>();

	public Map<Integer, Integer> computeANN(Graph2 gr) {
		this.graph = gr;
		//PriorityQueue<Integer> roadObjectIds = new PriorityQueue<Integer>();
		//PriorityQueue<RoadObject> roadObjects = new PriorityQueue<RoadObject>();
		
		NearestNeighbor3 nn = new NearestNeighbor3();


		if (graph.getTotalNumberOfFalseObjects() >= graph.getTotalNumberOfTrueObjects()) {
			// Query object = True Object; Data Object = False Object
			System.out.println("Query object = True Object; Data Object = False Object");

			// Iterate through all objects on every edge
			for (Integer edgeId : graph.getObjectsOnEdges().keySet()) {
				for (RoadObject trueObj : graph.getTrueObjectsOnEdgeSortedByDist(edgeId)) {
					int nearestFalseObjId = nn.getNearestFalseObjectIdToGivenObjOnMap(graph, trueObj.getObjectId());
					nearestNeighborSets.put(trueObj.getObjectId(), nearestFalseObjId);
				}
			}
		} else {
			// Query object = False Object; Data Object = True Object
			System.out.println("Query object = False Object; Data Object = True Object");

			// Iterate through all objects on every edge
			for (Integer edgeId : graph.getObjectsOnEdges().keySet()) {
				for (RoadObject falseObj : graph.getFalseObjectsOnEdgeSortedByDist(edgeId)) {
					int nearestTrueObjId = nn.getNearestTrueObjectIdToGivenObjOnMap(graph, falseObj.getObjectId());
					nearestNeighborSets.put(falseObj.getObjectId(), nearestTrueObjId);
				}
			}
		}
		//System.out.println(nearestNeighborSets);
		return nearestNeighborSets;
	}
	
	public void printNearestNeighborSets () { 
		
		for (Integer queryObj : nearestNeighborSets.keySet()) { 
			System.out.println("Query Object ID: " + queryObj + " [edge #" + graph.getEdgeIdOfRoadObject(queryObj) + "] - Data Object ID: " + nearestNeighborSets.get(queryObj) + " [edge #" + graph.getEdgeIdOfRoadObject(nearestNeighborSets.get(queryObj)) + "] Distance: " );
			//System.out.println("Query Object ID: " + queryObj + " - Data Object ID: " + nearestNeighborSets.get(queryObj) + " Distance: " );
			
		}
		
	}	

}
