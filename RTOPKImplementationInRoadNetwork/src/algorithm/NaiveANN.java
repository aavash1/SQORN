package algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import framework.Edge;
import framework.Graph2;
import framework.RoadObject;

public class NaiveANN {

	private Graph2 graph;

	// Map<QueryObjectId, DataObjectId>.
	private Map<Integer, Integer> nearestNeighborSets = new HashMap<Integer, Integer>();
	private ArrayList<PriorityQueue<Edge>> visitedEdges = new ArrayList<PriorityQueue<Edge>>();

	Comparator<Edge> edgeLengthComparator = new Comparator<Edge>() {
		@Override
		public int compare(Edge edge1, Edge edge2) {
			return (int) (edge1.getLength() - edge2.getLength());
		}

	};

	public void computeANN(Graph2 gr) {

		this.graph = gr;

		if (true) {
			// True Object = Query object; False Object = Data Object
			for (int i = 0; i < gr.getObjectsOnEdges().size()-1; i++) {
				for (RoadObject obj : gr.getObjectsOnEdges().get(i)) {
					if (obj.getType() == true) {
						int existInEdge = gr.getEdgeIdOfRoadObject(obj.getObjectId());
						for (int k = 0; k < gr.getAdjacencyNodeIds(existInEdge).size(); k++) {
							PriorityQueue<Edge> edgePriorityQueue = new PriorityQueue<Edge>(edgeLengthComparator);
							edgePriorityQueue.add(gr.getEdgesWithInfo().get(k));
							visitedEdges.add(edgePriorityQueue);

						}

					}
				}
			}

		} else {
			// True Object = Data object; False Object = Query Object
//			for (int i = 0; i < gr.getObjectsOnEdges().size(); i++) {
//				for (RoadObject obj : gr.getObjectsOnEdges().get(i)) {
//					if (obj.getType() == false) {
//						System.out.println("False");
//
//					}
//				}
//			}

		}
		// return nearestNeighborSets;
	}

	public void showPriorityQueue() {
		for (int a = 0; a < visitedEdges.size(); a++) {
			System.out.println(visitedEdges.get(a));
		}
	}

	private RoadObject getNearestDataObject(RoadObject queryObject) {
		RoadObject obj = new RoadObject();

		return obj;

	}

	private int getNearestDataObjectId() {

		return 0;
	}

	public double compareDistanceBetweenObjects(RoadObject dataObject1, RoadObject dataObject2) {
		return dataObject1.getDistanceFromStartNode() - dataObject2.getDistanceFromStartNode();

	}

	public double compareEdgeLength(Edge edge1, Edge edge2) {
		return edge1.getLength() - edge2.getLength();
	}

}
