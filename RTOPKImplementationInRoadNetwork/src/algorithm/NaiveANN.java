package algorithm;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

import framework.Edge;
import framework.Graph2;
import framework.RoadObject;

public class NaiveANN {

	private Graph2 graph;

	// Map<QueryObjectId, DataObjectId>.
	private Map<Integer, Integer> nearestNeighborSets = new HashMap<Integer, Integer>();

	private ArrayList<Map<Integer, Integer>> nearestNeighborPair = new ArrayList<Map<Integer, Integer>>();

	// private private ArrayList<PriorityQueue<Edge>> visitedEdges = new
	// ArrayList<PriorityQueue<Edge>>();

	Comparator<Edge> edgeLengthComparator = new Comparator<Edge>() {
		@Override
		public int compare(Edge edge1, Edge edge2) {
			return (int) (edge1.getLength() - edge2.getLength());
		}

	};

	public void computeANN(Graph2 gr) {

		this.graph = gr;

		if (true) {
			System.out.println("The size of True object is: " + gr.getTotalNumberOfTrueObjects());
			for (int i = 0; i < gr.getObjectsOnEdges().size() - 1; i++) {
				for (RoadObject roadObject : gr.getObjectsOnEdges().get(i)) {
					System.out.println("The object is: " + roadObject.toString());
					if (roadObject.getType() == true) {
						Map<Integer, Integer> objectPairs = new HashMap<Integer, Integer>();
						int edgeId = gr.getEdgeIdOfRoadObject(roadObject.getObjectId());
						double distFromSrc = roadObject.getDistanceFromStartNode();
						int FalseObjId = roadObject.getObjectId();
						if (gr.getAllObjectsOnEdgeSortedByDist(edgeId).contains(roadObject.getType() == false)) {
							{
								ArrayList<Map<Integer, Double>> nearestObjects = new ArrayList<Map<Integer, Double>>();
								Map<Integer, Double> nearestObject = new HashMap<Integer, Double>();
								ArrayList<Double> distanceBetweenTAndF = new ArrayList<Double>();
								// ArrayList<Integer> nearestObject = new ArrayList<Integer>();
								for (int j = 0; j < gr.getFalseObjectsIdOnGivenEdge(edgeId).size() - 1; j++) {
									int objId = gr.getObjectsOnEdges().get(j).get(edgeId).getObjectId();
									double distBetween = gr.getObjectsOnEdges().get(j).get(edgeId)
											.getDistanceFromStartNode() - distFromSrc;
									nearestObject.put(objId, distBetween);
									nearestObjects.add(nearestObject);

								}
								for (int k = 0; k < nearestObjects.size() - 1; k++) {
									ArrayList<Double> byDist = new ArrayList<>(nearestObjects.get(i).values());
									Collections.sort(byDist);

								}
								for (int l = 0; l < nearestObject.size() - 1; l++) {
									int trueObjId = nearestObjects.indexOf(l);

									nearestNeighborSets.put(FalseObjId, trueObjId);
									break;
								}

							}

						}
					}
				}

			}
		}
//		} else {
//			for (int i = 0; i < gr.getObjectsOnEdges().size() - 1; i++) {
//				for (RoadObject roadObject : gr.getObjectsOnEdges().get(i)) {
//					if (roadObject.getType() == false) {
//						Map<Integer, Integer> objectPairs = new HashMap<Integer, Integer>();
//						int edgeId = gr.getEdgeIdOfRoadObject(roadObject.getObjectId());
//						double distFromSrc = roadObject.getDistanceFromStartNode();
//						int FalseObjId = roadObject.getObjectId();
//						if (gr.getAllObjectsOnEdgeSortedByDist(edgeId).contains(roadObject.getType() == true)) {
//							{
//								ArrayList<Map<Integer, Double>> nearestObjects = new ArrayList<Map<Integer, Double>>();
//								Map<Integer, Double> nearestObject = new HashMap<Integer, Double>();
//								ArrayList<Double> distanceBetweenTAndF = new ArrayList<Double>();
//								// ArrayList<Integer> nearestObject = new ArrayList<Integer>();
//								for (int j = 0; j < gr.getFalseObjectsIdOnGivenEdge(edgeId).size() - 1; j++) {
//									int objId = gr.getObjectsOnEdges().get(j).get(edgeId).getObjectId();
//									double distBetween = gr.getObjectsOnEdges().get(j).get(edgeId)
//											.getDistanceFromStartNode() - distFromSrc;
//									nearestObject.put(objId, distBetween);
//									nearestObjects.add(nearestObject);
//
//								}
//								for (int k = 0; k < nearestObjects.size() - 1; k++) {
//									ArrayList<Double> byDist = new ArrayList<>(nearestObjects.get(i).values());
//									Collections.sort(byDist);
//
//								}
//								for (int l = 0; l < nearestObject.size() - 1; l++) {
//									int trueObjId = nearestObjects.indexOf(l);
//
//									nearestNeighborSets.put(FalseObjId, trueObjId);
//									break;
//								}
//
//							}
//
//						}
//					}
//				}
//
//			}
//
//		}

//		if (true) {
//			// True Object = Query object; False Object = Data Object
//			for (int i = 0; i < gr.getObjectsOnEdges().size() - 1; i++) {
//				for (RoadObject obj : gr.getObjectsOnEdges().get(i)) {
//					if (obj.getType() == true) {
//						int existInEdge = gr.getEdgeIdOfRoadObject(obj.getObjectId());
//						for (int k = 0; k < gr.getAdjacencyNodeIds(existInEdge).size(); k++) {
//							PriorityQueue<Edge> edgePriorityQueue = new PriorityQueue<Edge>(edgeLengthComparator);
//							edgePriorityQueue.add(gr.getEdgesWithInfo().get(k));
//							visitedEdges.add(edgePriorityQueue);
//
//						}
//
//					}
//				}
//			}
//
//		} else {
//			// True Object = Data object; False Object = Query Object
////			for (int i = 0; i < gr.getObjectsOnEdges().size(); i++) {
////				for (RoadObject obj : gr.getObjectsOnEdges().get(i)) {
////					if (obj.getType() == false) {
////						System.out.println("False");
////
////					}
////				}
////			}
//
//		}
//		// return nearestNeighborSets;
	}

	public void showPriorityQueue() {
		for (Map.Entry<Integer, Integer> key : nearestNeighborSets.entrySet()) {
			System.out.println("[" + key.getKey() + "," + key.getValue() + "]");

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
