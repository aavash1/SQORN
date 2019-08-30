package algorithm;

import java.util.HashMap;
import java.util.Map;

import framework.Graph2;
import framework.RoadObject;

public class NaiveANN {

	// Map<QueryObjectId, DataObjectId>.
	private Map<Integer, Integer> nearestNeighborSets = new HashMap<Integer, Integer>();

	public void computeANN(Graph2 graph) {
		if (graph.getTotalNumberOfFalseObjects() > graph.getTotalNumberOfTrueObjects()) {

			for (int i = 0; i < graph.getObjectInfo().size(); i++) {
				for (RoadObject obj : graph.getObjectInfo().get(i)) {
					if (obj.getType() == true) {
						graph.getEdges(i);

					}
				}
			}

		}

	}

	public Map<Integer, Integer> nearestNeighborSets() {
		return nearestNeighborSets;
	}

}
