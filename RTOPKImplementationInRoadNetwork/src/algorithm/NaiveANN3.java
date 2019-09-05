package algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import framework.Graph2;
import framework.RoadObject;

public class NaiveANN3 {

	private Graph2 graph;

	// Map<QueryObjectId, DataObjectId>.
	private Map<Integer, Integer> nearestNeighborSets = new HashMap<Integer, Integer>();
	
	
	
	public Map<Integer, Integer> computeANN(Graph2 gr) { 
		this.graph=gr;
		PriorityQueue<Integer> roadObjectIds = new PriorityQueue<>();
		PriorityQueue<RoadObject> roadObjects = new PriorityQueue<>();
		
		if (graph.getTotalNumberOfFalseObjects() >= graph.getTotalNumberOfTrueObjects()) {
			// Query object = True Object; Data Object = False Object
			System.out.println("Query object = True Object; Data Object = False Object");
			
			while (!roadObjectIds.isEmpty()) { 				
				
				for (Integer edgeId : graph.getObjectsOnEdges().keySet()) {
					
					for (RoadObject trueObj : graph.getTrueObjectsOnEdgeSortedByDist(edgeId)) { 						
						int nearestFalseObjId = graph.getNearestFalseObjectIdToGivenObjOnEdge(edgeId, trueObj.getObjectId());
						nearestNeighborSets.put(trueObj.getObjectId(), nearestFalseObjId);
					}
					
				}
				
				roadObjectIds.remove();
				
			}
			
			

		}
		else { 
			// Query object = False Object; Data Object = True Object
			System.out.println("Query object = False Object; Data Object = True Object");
			
		}
		
		return nearestNeighborSets;
	}

}
