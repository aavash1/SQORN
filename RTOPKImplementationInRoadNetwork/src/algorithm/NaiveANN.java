package algorithm;

import java.util.HashMap;
import java.util.Map;

import framework.Graph2;
import framework.RoadObject;

public class NaiveANN {

	private Graph2 graph;
	
	// Map<QueryObjectId, DataObjectId>.
	private Map<Integer, Integer> nearestNeighborSets = new HashMap<Integer, Integer>();

	public Map<Integer, Integer> computeANN(Graph2 gr) {
		
		this.graph=gr;
		
		if (gr.getTotalNumberOfFalseObjects() > gr.getTotalNumberOfTrueObjects()) {
			// True Object = Query object; False Object = Data Object 
			for (int i = 0; i < gr.getObjectsOnEdges().size(); i++) {
				for (RoadObject obj : gr.getObjectsOnEdges().get(i)) {
					if (obj.getType() == true) {
						gr.getEdges(i);

					}
				}
			}

		}
		else { 
			// True Object = Data object; False Object = Query Object 
			for (int i = 0; i < gr.getObjectsOnEdges().size(); i++) {
				for (RoadObject obj : gr.getObjectsOnEdges().get(i)) {
					if (obj.getType() == false) {
						gr.getEdges(i);

					}
				}
			}
			
		}
		return nearestNeighborSets;
	}

	private RoadObject getNearestDataObject(RoadObject queryObject) { 
		RoadObject obj = new RoadObject();
		
		
		
		
		
		return obj;
		
	}
	
	private int getNearestDataObjectId() { 
		
		return 0;
	}
	
	

}
