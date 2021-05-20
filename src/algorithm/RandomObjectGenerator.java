package algorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

//These libraries are for getting last element from the hashmap.
import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.Map;
import java.util.Map.Entry;

//import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
////
//import org.apache.commons.math3.random.RandomDataGenerator;
//import org.decimal4j.util.DoubleRounder;

//import com.google.common.collect.Multiset.Entry;

//import com.google.common.collect.MinMaxPriorityQueue;

import framework.Edge;
import framework.Graph;
import framework.RoadObject;
import framework.UtilsManagment;
//import testing.RandomGaussian;

public class RandomObjectGenerator {

	// not complete yet
	// Object Type: false = data object ; true = query object;
//	private static boolean uniformObjects = false;
//	private static boolean uniformDataObjects = false;
//	private static boolean uniformQueryObjects = false;
//	private static int maxNumberOfObjsPerEdge;
	// For Statistics
	private static int m_totalNumberOfObjects;
	private static int m_totalNumberOfTrueObjects;
	private static int m_totalNumberOfFalseObjects;
	private static int m_totalNumberOfEdges;
//	private static double minDistBetNodeAndObject;
	private static int m_totalNumberOfEdgesContainingObjects;
	private static double m_totalLengthOfEdges;
	private static double m_minEdgeLength;
	private static double m_maxEdgeLength;
	private static double m_minDistBetweenObjs;
	private static double m_minDistBetweenObjsPrecision = 1000000.0;
	private static double m_distFromStartNodePrecision = 1000000.0;

	private static int m_numberOfCentroids = 10;

	private static Random random = new Random();

	public static ArrayList<Integer> countList = new ArrayList<Integer>();

	public static void generateRandomObjectsOnEdgeWithCentroidForSameDistribution(Graph graph,
			int totalNumberOfTrueObjects, int totalNumberOfFalseObjects, double certainPerimeter) {
		graph.removeObjectsOnEdges();
		int objCounter = 1;
		int totalNumberOfEdges = graph.getNumberOfEdges();
		int totalNumberOfObjects = totalNumberOfFalseObjects + totalNumberOfTrueObjects;
		Map<Integer, ArrayList<Double>> acceptedDistancesOnEdge = new HashMap<Integer, ArrayList<Double>>();
		int randomEdgeId;
		//int centroidObjectSize = totalNumberOfObjects;
		//double standardDeviation = 0.0;

		ArrayList<Integer> centroidEdgeIds = new ArrayList<Integer>();
		// centroidEdgeBrances will hold all the edges that traverses through first 10
		// selected centroid edge.
		ArrayList<Integer> centroidEdgeBranches = new ArrayList<Integer>();

		for (int i = 0; i < m_numberOfCentroids; i++) {
			randomEdgeId = (int) getThreadRandomNumberInBetween(1, totalNumberOfEdges - 1);
			RoadObject object = new RoadObject();
			object.setObjId(objCounter);
			object.setType(true);

			if (!acceptedDistancesOnEdge.containsKey(randomEdgeId)) {
				ArrayList<Double> acceptedDistances = new ArrayList<Double>();
				acceptedDistancesOnEdge.put(randomEdgeId, acceptedDistances);
			}
			double edgeLength = graph.getEdgeDistance(randomEdgeId);
			double distFromStartNode = getRandDoubleInBetween(0, edgeLength);
			if (!acceptedDistancesOnEdge.get(randomEdgeId).contains(distFromStartNode)) {
				object.setDistanceFromStartNode(distFromStartNode);
			} else {
				continue;

			}

			if (graph.addObjectOnEdge(randomEdgeId, object)) {
				objCounter++;

				acceptedDistancesOnEdge.get(randomEdgeId).add(distFromStartNode);
				centroidEdgeIds.add(randomEdgeId);
				if (!centroidEdgeBranches.contains(randomEdgeId)) {
					centroidEdgeBranches.add(randomEdgeId);
				}

			}
		}
		for (Integer edgeWithObjects : centroidEdgeIds) {
			int centroidObjectCounter = 0;
			int remainingObjectsTobeGenerated = (totalNumberOfTrueObjects - m_numberOfCentroids) / m_numberOfCentroids;

			ArrayList<Integer> edgesSelected = getEdgesWithinRadius(graph, certainPerimeter, edgeWithObjects);

			while (centroidObjectCounter < remainingObjectsTobeGenerated) {
				randomEdgeId = edgesSelected.get(ThreadLocalRandom.current().nextInt(edgesSelected.size()));
				RoadObject object = new RoadObject();
				object.setObjId(objCounter);
				object.setType(true);

				if (!acceptedDistancesOnEdge.containsKey(randomEdgeId)) {
					ArrayList<Double> acceptedDistances = new ArrayList<Double>();
					acceptedDistancesOnEdge.put(randomEdgeId, acceptedDistances);
				}
				double edgeLength = graph.getEdgeDistance(randomEdgeId);
				double distFromStartNode = getRandDoubleInBetween(0, edgeLength);
				if (!acceptedDistancesOnEdge.get(randomEdgeId).contains(distFromStartNode)) {
					object.setDistanceFromStartNode(distFromStartNode);
				} else {
					continue;

				}

				if (graph.addObjectOnEdge(randomEdgeId, object)) {
					objCounter++;

					centroidObjectCounter++;
					acceptedDistancesOnEdge.get(randomEdgeId).add(distFromStartNode);
					if (!centroidEdgeBranches.contains(randomEdgeId)) {
						centroidEdgeBranches.add(randomEdgeId);
					}
				}

			}

		}

		while (objCounter < totalNumberOfObjects) {
			randomEdgeId = centroidEdgeBranches.get(ThreadLocalRandom.current().nextInt(centroidEdgeBranches.size()));
			RoadObject object = new RoadObject();
			object.setObjId(objCounter);
			object.setType(false);

			if (!acceptedDistancesOnEdge.containsKey(randomEdgeId)) {
				ArrayList<Double> acceptedDistances = new ArrayList<Double>();
				acceptedDistancesOnEdge.put(randomEdgeId, acceptedDistances);
			}
			double edgeLength = graph.getEdgeDistance(randomEdgeId);
			double distFromStartNode = getRandDoubleInBetween(0, edgeLength);
			if (!acceptedDistancesOnEdge.get(randomEdgeId).contains(distFromStartNode)) {
				object.setDistanceFromStartNode(distFromStartNode);
			} else {
				continue;

			}

			if (graph.addObjectOnEdge(randomEdgeId, object)) {
				objCounter++;

				acceptedDistancesOnEdge.get(randomEdgeId).add(distFromStartNode);
			}

		}

		m_totalNumberOfObjects = graph.getTotalNumberOfObjects();
		m_totalNumberOfTrueObjects = graph.getTotalNumberOfTrueObjects();
		m_totalNumberOfFalseObjects = graph.getTotalNumberOfFalseObjects();
		m_totalNumberOfEdges = totalNumberOfEdges;
		m_totalNumberOfEdgesContainingObjects = graph.getObjectsOnEdges().size();

		System.out.println("Finished Generating Road Objects");
		System.out.println("objCounter: " + objCounter + " TrueObjects: " + graph.getTotalNumberOfTrueObjects()
				+ " FalseObjects: " + graph.getTotalNumberOfFalseObjects());

	}

	// This method is for Generating Objects on the basis of the Gaussian
	// Distribution Distance.
	// Starts from here
	// 1. To Generate randomObject
	public static void generateRandomObjectsOnEdgeWithCentroidGaussian(Graph graph, int totalNumberOfTrueObjects,
			int totalNumberOfFalseObjects, boolean centroidObjType) {
		graph.removeObjectsOnEdges();
		int objCounter = 1;
		int totalNumberOfEdges = graph.getNumberOfEdges();
		int totalNumberOfNodes = graph.getNumberOfNodes();
		Map<Integer, ArrayList<Double>> acceptedDistancesOnEdge = new HashMap<Integer, ArrayList<Double>>();
		ArrayList<Integer> centroidEdgeIds = new ArrayList<Integer>();

		// int totalNumberOfObjects = totalNumberOfTrueObjects +
		// totalNumberOfFalseObjects;

		// For generating 10 centroid, we select 10 random nodes and start generating
		// objects in them until
		// the arraylist of gaussian distance is empty, then generate rest of the
		// objects "query,data & vice-versa"
		// ArrayList<Double> randomGaussianDistance = new ArrayList<Double>();
		// randomGaussianDistance.add(6.178);
		// randomGaussianDistance.add(5.278);
		// randomGaussianDistance.add(7.18);
		// randomGaussianDistance.add(7.68);
		// randomGaussianDistance.add(5.68);
		// randomGaussianDistance.add(8.1);
		// randomGaussianDistance.add(4.75);
		// randomGaussianDistance.add(8.81);
		// randomGaussianDistance.add(4.25);
		// randomGaussianDistance.add(4.98);

		// TODO: work on these two variables left
		int centroidObjectSize = 0;
		int uniformObjectSize = 0;
		if (centroidObjType == true) {
			centroidObjectSize = totalNumberOfTrueObjects;
			uniformObjectSize = totalNumberOfFalseObjects;
		} else {
			centroidObjectSize = totalNumberOfFalseObjects;
			uniformObjectSize = totalNumberOfTrueObjects;
		}
		double standardDeviation = 0.0;

		ArrayList<Double> randomGaussianDistance = getGaussianDistributionDistance(centroidObjectSize,
				standardDeviation, 1);

		Collections.shuffle(randomGaussianDistance);

		// for (int i = 0; i < 10; i++) {
		int selectedRandomNode = (int) getThreadRandomNumberInBetween(1, totalNumberOfNodes - 1);
		while (randomGaussianDistance.size() != 0) {
			int RandomIndex = random.nextInt(randomGaussianDistance.size());
			double selectedRandomGaussianDistance = randomGaussianDistance.get(RandomIndex);
			randomGaussianDistance.remove(RandomIndex);

			// Map <edgeId, finalDistance>
			Map<Integer, Double> edgeAndDistance = traverseFromGivenNodeUptoDistance(graph, selectedRandomNode,
					selectedRandomGaussianDistance);
			int edgeId = getFirst(edgeAndDistance).getKey();
			double finalDistance = getFirst(edgeAndDistance).getValue();

			RoadObject roadObj = new RoadObject();
			roadObj.setObjId(objCounter);
			roadObj.setType(centroidObjType);

			if (!acceptedDistancesOnEdge.containsKey(edgeId)) {
				ArrayList<Double> acceptedDistances = new ArrayList<Double>();
				acceptedDistancesOnEdge.put(edgeId, acceptedDistances);
			}

			if (!acceptedDistancesOnEdge.get(edgeId).contains(finalDistance)) {
				roadObj.setDistanceFromStartNode(finalDistance);
			}

			if (graph.addObjectOnEdge(edgeId, roadObj)) {
				objCounter++;
				// System.out.println(objCounter+" objects added");
				acceptedDistancesOnEdge.get(edgeId).add(finalDistance);
				centroidEdgeIds.add(edgeId);
			}
		}

		for (int j = 0; j < uniformObjectSize; j++) {

			int selectedRandomEdge = (int) getThreadRandomNumberInBetween(1, totalNumberOfEdges - 1);
			RoadObject roadObject = new RoadObject();
			roadObject.setObjId(objCounter);
			roadObject.setType(!centroidObjType);

			if (!acceptedDistancesOnEdge.containsKey(selectedRandomEdge)) {
				ArrayList<Double> acceptedDistances = new ArrayList<Double>();
				acceptedDistancesOnEdge.put(selectedRandomEdge, acceptedDistances);
			}
			double edgeLength = graph.getEdgeDistance(selectedRandomEdge);
			double distFromStartNode = getRandDoubleInBetween(0, edgeLength);
			if (!acceptedDistancesOnEdge.get(selectedRandomEdge).contains(distFromStartNode)) {
				roadObject.setDistanceFromStartNode(distFromStartNode);
			} else {
				continue;

			}

			if (graph.addObjectOnEdge(selectedRandomEdge, roadObject)) {
				objCounter++;
				// System.out.println(objCounter+" objects added");
				acceptedDistancesOnEdge.get(selectedRandomEdge).add(distFromStartNode);
			}
			// System.out.println("end of for loop for false objects, objCounter:
			// "+objCounter + " TrueObjects: " + graph.getTotalNumberOfTrueObjects() + "
			// FalseObjects: " + graph.getTotalNumberOfFalseObjects());
		}

		m_totalNumberOfObjects = graph.getTotalNumberOfObjects();
		m_totalNumberOfTrueObjects = graph.getTotalNumberOfTrueObjects();
		m_totalNumberOfFalseObjects = graph.getTotalNumberOfFalseObjects();
		m_totalNumberOfEdges = totalNumberOfEdges;
		m_totalNumberOfEdgesContainingObjects = graph.getObjectsOnEdges().size();

		System.out.println("Finished Generating Road Objects");
		System.out.println("objCounter: " + (objCounter - 1) + " TrueObjects: " + graph.getTotalNumberOfTrueObjects()
				+ " FalseObjects: " + graph.getTotalNumberOfFalseObjects());

	}

	// Method traverseFromGivenNodeUptoDistance and traverseUptoGivenDistance is
	// used to traverse randomly upto the given distance
	// using these methods to genrerate object on edges more than the gaussian
	// distance
	private static Map<Integer, Double> traverseFromGivenNodeUptoDistance(Graph gr, int nodeId, double distance) {

		ArrayList<Integer> visitedNodes = new ArrayList<Integer>();
		LinkedHashMap<Integer, Double> visitedEdges = new LinkedHashMap<Integer, Double>();
		traverseUptoGivenDistance(gr, nodeId, visitedNodes, visitedEdges, distance);

		int lastEdgeId = getLast(visitedEdges).getKey();
		double coveredDistance = getLast(visitedEdges).getValue();
		int lastNodeId = visitedNodes.get(visitedNodes.size() - 1);

		//
		//
		int traversedEdgeCount = visitedEdges.size();
		countList.add(traversedEdgeCount);
		// System.out.println("Number of edge traversed: "+traversedEdgeCount);

		//
		double finalDistance;

		if (gr.isStartNode(lastNodeId, lastEdgeId)) {
			finalDistance = gr.getEdgeDistance(lastEdgeId) - (coveredDistance - distance);
		} else {
			finalDistance = coveredDistance - distance;
		}

		Map<Integer, Double> result = new HashMap<Integer, Double>();
		result.put(lastEdgeId, finalDistance);

		return result;
	}

	private static void traverseUptoGivenDistance(Graph graph, int nodeId, ArrayList<Integer> visitedNode,
			LinkedHashMap<Integer, Double> visitedEdges, double distance) {

		double initialDistance = 0.0;
		double coveredDistance = 0.0;

		visitedNode.add(nodeId);

		ArrayList<Integer> adjacentNodes = graph.getAdjNodeIds(nodeId);
		Collections.shuffle(adjacentNodes);
		Iterator<Integer> i = adjacentNodes.listIterator();

		while (i.hasNext()) {
			int indexOfNode2 = i.next();
			int selectedEdgeId = graph.getEdgeId(nodeId, indexOfNode2);
			double selectedEdgeDistance = graph.getEdgeDistance(selectedEdgeId);
			if (!visitedNode.contains(indexOfNode2)) {
				if (visitedEdges.isEmpty()) {
					coveredDistance = initialDistance + selectedEdgeDistance;
					visitedEdges.put(selectedEdgeId, coveredDistance);
					// System.out.println("Visted Number:" +visitedEdges.size());
					if (distance >= coveredDistance) {
						traverseUptoGivenDistance(graph, indexOfNode2, visitedNode, visitedEdges, distance);

					}

					break;

				} else {
					coveredDistance = getLast(visitedEdges).getValue() + selectedEdgeDistance;
					visitedEdges.put(selectedEdgeId, coveredDistance);
					if (distance >= coveredDistance) {
						traverseUptoGivenDistance(graph, indexOfNode2, visitedNode, visitedEdges, distance);

					}

					break;
				}
			}
		}
	}

	public static void generateRandomObjectsOnEdgesWithCentroid(Graph graph, int totalNumberOfTrueObjects,
			int totalNumberOfFalseObjects, boolean centroidObjType, double certainPerimeter) {
		graph.removeObjectsOnEdges();
		int objCounter = 1;
		int totalNumberOfEdges = graph.getNumberOfEdges();

		Map<Integer, ArrayList<Double>> acceptedDistancesOnEdge = new HashMap<Integer, ArrayList<Double>>();

		int randomEdgeId;
		ArrayList<Integer> centroidEdgeIds = new ArrayList<Integer>();
		// System.out.println("totalNumberOfTrueObjects: "+totalNumberOfTrueObjects+",
		// totalNumberOfFalseObjects: "+totalNumberOfFalseObjects);
		// System.out.println("objCounter: "+objCounter + " TrueObjects: " +
		// graph.getTotalNumberOfTrueObjects() + " FalseObjects: " +
		// graph.getTotalNumberOfFalseObjects());

		// Generating centroids
		for (int i = 0; i < m_numberOfCentroids; i++) {
			randomEdgeId = (int) getThreadRandomNumberInBetween(1, totalNumberOfEdges - 1);
			RoadObject object = new RoadObject();
			object.setObjId(objCounter);
			object.setType(centroidObjType);

			if (!acceptedDistancesOnEdge.containsKey(randomEdgeId)) {
				ArrayList<Double> acceptedDistances = new ArrayList<Double>();
				acceptedDistancesOnEdge.put(randomEdgeId, acceptedDistances);
			}
			double edgeLength = graph.getEdgeDistance(randomEdgeId);
			double distFromStartNode = getRandDoubleInBetween(0, edgeLength);
			if (!acceptedDistancesOnEdge.get(randomEdgeId).contains(distFromStartNode)) {
				object.setDistanceFromStartNode(distFromStartNode);
			} else {
				continue;

			}

			if (graph.addObjectOnEdge(randomEdgeId, object)) {
				objCounter++;
				// System.out.println(objCounter+" objects added");
				acceptedDistancesOnEdge.get(randomEdgeId).add(distFromStartNode);
				centroidEdgeIds.add(randomEdgeId);
			}
		}
		// System.out.println("end of 1st for loop, objCounter: "+objCounter + "
		// TrueObjects: " + graph.getTotalNumberOfTrueObjects() + " FalseObjects: " +
		// graph.getTotalNumberOfFalseObjects());
		// System.out.println("centroidEdgeIds.size(): " + centroidEdgeIds.size());

		// Select all edges that has 10 true objects and make it as centroid
		// Generate ((n-10)/10) object for each centroid
		// Generate ((n-m_numberOfCentroids)/m_numberOfCentroids) object for each
		// centroid
		for (Integer edgeWithObjects : centroidEdgeIds) {
			int centroidObjectCounter = 0;
			int remainingObjectsTobeGenerated = (totalNumberOfTrueObjects - m_numberOfCentroids) / m_numberOfCentroids;
			// ArrayList<Integer> edgesSelected=getEdgesWithinPerimeter(graph, m_perimeter,
			// edgeWithObjects);
			ArrayList<Integer> edgesSelected = getEdgesWithinRadius(graph, certainPerimeter, edgeWithObjects);
			// System.out.println("remainingObjectsTobeGenerated: " +
			// remainingObjectsTobeGenerated + "; edgesSelected.size(): " +
			// edgesSelected.size());
			while (centroidObjectCounter < remainingObjectsTobeGenerated) {
				randomEdgeId = edgesSelected.get(ThreadLocalRandom.current().nextInt(edgesSelected.size()));
				RoadObject object = new RoadObject();
				object.setObjId(objCounter);
				object.setType(centroidObjType);

				if (!acceptedDistancesOnEdge.containsKey(randomEdgeId)) {
					ArrayList<Double> acceptedDistances = new ArrayList<Double>();
					acceptedDistancesOnEdge.put(randomEdgeId, acceptedDistances);
				}
				double edgeLength = graph.getEdgeDistance(randomEdgeId);
				double distFromStartNode = getRandDoubleInBetween(0, edgeLength);
				if (!acceptedDistancesOnEdge.get(randomEdgeId).contains(distFromStartNode)) {
					object.setDistanceFromStartNode(distFromStartNode);
				} else {
					continue;

				}

				if (graph.addObjectOnEdge(randomEdgeId, object)) {
					objCounter++;
					// System.out.println(objCounter+" objects added");
					centroidObjectCounter++;
					acceptedDistancesOnEdge.get(randomEdgeId).add(distFromStartNode);
				}
				// System.out.println("end of 'while' for one centroid, objCounter:
				// "+objCounter+ " TrueObjects: " + graph.getTotalNumberOfTrueObjects() + "
				// FalseObjects: " + graph.getTotalNumberOfFalseObjects());
			}
			// System.out.println("end of for loop for all centroids, objCounter:
			// "+objCounter + " TrueObjects: " + graph.getTotalNumberOfTrueObjects() + "
			// FalseObjects: " + graph.getTotalNumberOfFalseObjects());

		}
		// Generate Rest of other objects "FALSE" objects
		for (int i = 0; i < totalNumberOfFalseObjects; i++) {
			randomEdgeId = (int) getThreadRandomNumberInBetween(1, totalNumberOfEdges - 1);
			RoadObject object = new RoadObject();
			object.setObjId(objCounter);
			object.setType(!centroidObjType);

			if (!acceptedDistancesOnEdge.containsKey(randomEdgeId)) {
				ArrayList<Double> acceptedDistances = new ArrayList<Double>();
				acceptedDistancesOnEdge.put(randomEdgeId, acceptedDistances);
			}
			double edgeLength = graph.getEdgeDistance(randomEdgeId);
			double distFromStartNode = getRandDoubleInBetween(0, edgeLength);
			if (!acceptedDistancesOnEdge.get(randomEdgeId).contains(distFromStartNode)) {
				object.setDistanceFromStartNode(distFromStartNode);
			} else {
				continue;

			}

			if (graph.addObjectOnEdge(randomEdgeId, object)) {
				objCounter++;
				// System.out.println(objCounter+" objects added");
				acceptedDistancesOnEdge.get(randomEdgeId).add(distFromStartNode);
			}
			// System.out.println("end of for loop for false objects, objCounter:
			// "+objCounter + " TrueObjects: " + graph.getTotalNumberOfTrueObjects() + "
			// FalseObjects: " + graph.getTotalNumberOfFalseObjects());
		}

		m_totalNumberOfObjects = graph.getTotalNumberOfObjects();
		m_totalNumberOfTrueObjects = graph.getTotalNumberOfTrueObjects();
		m_totalNumberOfFalseObjects = graph.getTotalNumberOfFalseObjects();
		m_totalNumberOfEdges = totalNumberOfEdges;
		m_totalNumberOfEdgesContainingObjects = graph.getObjectsOnEdges().size();

		System.out.println("Finished Generating Road Objects");
		System.out.println("objCounter: " + objCounter + " TrueObjects: " + graph.getTotalNumberOfTrueObjects()
				+ " FalseObjects: " + graph.getTotalNumberOfFalseObjects());
	}

	public static void generateUniformRandomObjectsOnMap(Graph graph, int totalNumberOfTrueObjects,
			int totalNumberOfFalseObjects) {

		graph.removeObjectsOnEdges();

		// String fileName = "GeneratedFiles/" + graph.getDatasetName() + "_Q_" +
		// totalNumberOfTrueObjects + "_D_"
		// + totalNumberOfFalseObjects;
		int objCounter = 1;
		int totalNumberOfEdges = graph.getNumberOfEdges();
		int totalNumOfObjects = totalNumberOfTrueObjects + totalNumberOfFalseObjects;

		Map<Integer, ArrayList<Double>> acceptedDistancesOnEdge = new HashMap<Integer, ArrayList<Double>>();

		LinkedList<Boolean> boolValues = new LinkedList<Boolean>();
		for (int i = 0; i < totalNumberOfTrueObjects; i++) {
			boolValues.add(true);
		}
		for (int i = 0; i < totalNumberOfFalseObjects; i++) {
			boolValues.add(false);
		}
		Collections.shuffle(boolValues);

		// System.out.println("Total edge: " + totalNumberOfEdges);
		boolean testVar;
		int randomEdgeId;
		while (objCounter <= totalNumOfObjects) {
			randomEdgeId = getRandIntBetRange(0, totalNumberOfEdges - 1);
			RoadObject object = new RoadObject();
			object.setObjId(objCounter);

			if (!acceptedDistancesOnEdge.containsKey(randomEdgeId)) {
				ArrayList<Double> acceptedDistances = new ArrayList<Double>();
				acceptedDistancesOnEdge.put(randomEdgeId, acceptedDistances);
			}
			double edgeLength = graph.getEdgeDistance(randomEdgeId);
			double distFromStartNode = getRandDoubleBetRange2(0, edgeLength);
			if (!acceptedDistancesOnEdge.get(randomEdgeId).contains(distFromStartNode)) {
				object.setDistanceFromStartNode(distFromStartNode);
			} else {
				continue;

			}

			if (!boolValues.isEmpty()) {
				testVar = boolValues.poll();
				object.setType(testVar);
			}
			if (graph.addObjectOnEdge(randomEdgeId, object)) {
				objCounter++;
				acceptedDistancesOnEdge.get(randomEdgeId).add(distFromStartNode);
				// System.out.println(objCounter + " Object Added" + ", distFromSN: " +
				// distFromStartNode + " on edge: "
				// + randomEdgeId + " of length: " + edgeLength);
			}

		}
		// System.out.println("objCounter: " + objCounter);

		m_totalNumberOfObjects = graph.getTotalNumberOfObjects();
		m_totalNumberOfTrueObjects = graph.getTotalNumberOfTrueObjects();
		m_totalNumberOfFalseObjects = graph.getTotalNumberOfFalseObjects();
		m_totalNumberOfEdges = totalNumberOfEdges;
		m_totalNumberOfEdgesContainingObjects = graph.getObjectsOnEdges().size();

		// System.out.println("size of acceptedDistancesOnEdge: " +
		// acceptedDistancesOnEdge.size());
		// return fileName;
		System.out.println("Finished Generating Road Objects");
	}

	public static void generateRandomObjectsOnMap(Graph graph, int totalNumberOfTrueObjects,
			int totalNumberOfFalseObjects) {
		String debugCounterInfo = "Statistics/DebugCounterInfo.txt";
		int objCounter = 0;
		double distanceFromStartNode = 0.0;
		double edgeLength;
		int totalNumberOfEdges = graph.getNumberOfEdges();
		// double minDistBetweenObjects;
		int maxNumberOfObjectsPerEdge;
		m_minEdgeLength = Double.MAX_VALUE;
		m_maxEdgeLength = Double.MIN_VALUE;
		int totalNumOfObjects = totalNumberOfTrueObjects + totalNumberOfFalseObjects;
		for (Edge edge : graph.getEdgesWithInfo()) {
			if (edge.getLength() < m_minEdgeLength) {
				m_minEdgeLength = edge.getLength();
			}
			if (edge.getLength() > m_maxEdgeLength) {
				m_maxEdgeLength = edge.getLength();
			}
		}

		m_totalLengthOfEdges = graph.getTotalLengthOfAllEdges();
		// m_minEdgeLength =
		// graph.getEdgesWithInfo().get(graph.getEdgesWithInfo().size() -
		// 1).getLength();
		// m_maxEdgeLength = graph.getEdgesWithInfo().get(0).getLength();
		int objParam = getTotalObjParam(graph.getDatasetName(), totalNumOfObjects);
		m_minDistBetweenObjs = Math.round((m_totalLengthOfEdges / objParam) * m_minDistBetweenObjsPrecision)
				/ m_minDistBetweenObjsPrecision;

		System.out.println("Min Dist between objects: " + m_minDistBetweenObjs);
		System.out.println("Min edgeLength: " + m_minEdgeLength);
		System.out.println("Max edgeLength: " + m_maxEdgeLength);
		System.out.println("Total Length of all edges: " + m_totalLengthOfEdges);
		System.out.println("ObjectParameter: " + objParam);
		int whileLoopCounter = 0;
		Map<Integer, ArrayList<Double>> acceptedDistancesOnEdge = new HashMap<Integer, ArrayList<Double>>();
		Map<Integer, ArrayList<Double>> checkedRandomDistances = new HashMap<Integer, ArrayList<Double>>();
		LinkedList<Boolean> boolValues = new LinkedList<Boolean>();
		for (int i = 0; i < totalNumberOfTrueObjects; i++) {
			boolValues.add(true);
		}
		for (int i = 0; i < totalNumberOfFalseObjects; i++) {
			boolValues.add(false);
		}
		Collections.shuffle(boolValues);

		// while (objCounter < totalNumOfObjects) {

		// if (objCounter >= totalNumOfObjects) {
		// break;
		// }
		whileLoopCounter++;
		System.err.println("Loop #: " + whileLoopCounter);
		if (whileLoopCounter == 2) {
			System.out.println("2nd while loop");
		}

		boolean isAcceptableDistance = false;
		boolean isThereDistanceConflict = false;

		boolean testVar;
		for (Edge edge : graph.getEdgesWithInfo()) {
			// if (objCounter >= totalNumOfObjects) {
			// break;
			// }

			// checkedRandomDistances.clear();
			edgeLength = graph.getEdgeDistance(edge.getEdgeId());
			// maxNumberOfObjectsPerEdge = (int) (edgeLength / m_minDistBetweenObjs - 1);

			// double a = (edgeLength / m_minDistBetweenObjs - 1);
			// double a = (edgeLength / (m_minDistBetweenObjs/2) - 1);
			// BigDecimal bd = new BigDecimal(a).setScale(17, RoundingMode.HALF_UP);

			// maxNumberOfObjectsPerEdge = bd.intValue();

			maxNumberOfObjectsPerEdge = getMaxNumOfObjsPerEdge(edgeLength, m_minDistBetweenObjs);

			System.err.println("EdgeId: " + edge.getEdgeId() + "of length: " + edgeLength
					+ " maxNumberOfObjectsPerEdge: " + maxNumberOfObjectsPerEdge);

			if (maxNumberOfObjectsPerEdge < 1)
				continue;
			// ArrayList<Double> acceptedDistances;

			if (!acceptedDistancesOnEdge.containsKey(edge.getEdgeId())) {
				ArrayList<Double> acceptedDistances = new ArrayList<Double>();
				ArrayList<Double> checkedDistances = new ArrayList<Double>();
				acceptedDistancesOnEdge.put(edge.getEdgeId(), acceptedDistances);
				checkedRandomDistances.put(edge.getEdgeId(), checkedDistances);
			}
			int counterForObject = 0;

			for (int j = 0; j < maxNumberOfObjectsPerEdge; j++) {

				// if (objCounter >= totalNumOfObjects) {
				// break;
				// }

				RoadObject randObj = new RoadObject();

				if (acceptedDistancesOnEdge.get(edge.getEdgeId()).isEmpty()) {

					//distanceFromStartNode = getRandDoubleBetRange5(0, edgeLength);
					distanceFromStartNode =0.0;
					checkedRandomDistances.get(edge.getEdgeId()).add(distanceFromStartNode);
					// randObj.setDistanceFromStartNode(distanceFromStartNode);
					// acceptedDistancesOnEdge.get(edge.getEdgeId()).add(distanceFromStartNode);
					// checkedRandomDistances.add(distanceFromStartNode);
					// isAcceptableDistance = false;
					// isThereDistanceConflict = false;

				} else {
					//int conflictCounter = 0;
					while (!isAcceptableDistance) {

						//distanceFromStartNode = getRandDoubleBetRange5(0, edgeLength);
						distanceFromStartNode = getRandDoubleBetRange(0, edgeLength);
						if (checkedRandomDistances.get(edge.getEdgeId()).contains(distanceFromStartNode)) {
							continue;
						}
						checkedRandomDistances.get(edge.getEdgeId()).add(distanceFromStartNode);

						for (int k = 0; k < acceptedDistancesOnEdge.get(edge.getEdgeId()).size(); k++) {

							isThereDistanceConflict = false;
							// Double previousDistance =
							// acceptedDistancesOnEdge.get(edge.getEdgeId()).get(k);
							// Double newDistance = distanceFromStartNode;
							if (!((acceptedDistancesOnEdge.get(edge.getEdgeId()).get(k)
									+ m_minDistBetweenObjs <= distanceFromStartNode)
									|| (acceptedDistancesOnEdge.get(edge.getEdgeId()).get(k)
											- m_minDistBetweenObjs >= distanceFromStartNode))) {
								isThereDistanceConflict = true;
								break;
							}

						//	conflictCounter++;
							// System.out.println("conflictCounter: " + conflictCounter + ", distandFromSN:
							// " + distanceFromStartNode + ", edgeLength: " + edgeLength);

							// if (acceptedDistancesOnEdge.size() == 5) {
							// System.out.println("debug");
							// }
							// if (!((previousDistance + m_minDistBetweenObjs <= newDistance)
							// || (previousDistance - m_minDistBetweenObjs >= newDistance))) {
							// conflictCounter++;
							// if(conflictCounter > 20)
							// {
							// System.out.println("debugg");
							// }
							// isThereDistanceConflict = true;
							// break;
							// }

						}
						if (!isThereDistanceConflict) {
							// conflictCounter = 0;
							isAcceptableDistance = true;
						}
					}
				}

				acceptedDistancesOnEdge.get(edge.getEdgeId()).add(distanceFromStartNode);

				randObj.setObjId(objCounter);
				randObj.setDistanceFromStartNode(distanceFromStartNode);

				if (!boolValues.isEmpty()) {
					testVar = boolValues.poll();
					randObj.setType(testVar);
				}

				if (graph.addObjectOnEdge(edge.getEdgeId(), randObj)) {
					objCounter++;
					counterForObject++;
					System.out.println(objCounter + " Object Added" + ", distFromSN: " + distanceFromStartNode);
				}
				isAcceptableDistance = false;
				isThereDistanceConflict = false;

			}
			printCounterInfo(maxNumberOfObjectsPerEdge, counterForObject, debugCounterInfo);

		}
		System.out.println("objCounter: " + objCounter);

		// }

		m_totalNumberOfObjects = graph.getTotalNumberOfObjects();
		m_totalNumberOfTrueObjects = graph.getTotalNumberOfTrueObjects();
		m_totalNumberOfFalseObjects = graph.getTotalNumberOfFalseObjects();
		m_totalNumberOfEdges = totalNumberOfEdges;
		m_totalNumberOfEdgesContainingObjects = graph.getObjectsOnEdges().size();

		System.out.println("size of acceptedDistancesOnEdge: " + acceptedDistancesOnEdge.size());
		System.out.println("whileLoopCounter: " + whileLoopCounter);

	}

	public static void generateRandomObjectsOnMap1(Graph graph) {

		int edgeId = 0;
		int objCounter = 0;
		double distanceFromStartNode = 0.0;
		double edgeLength;
		int totalNumberOfEdges = graph.getNumberOfEdges();
		double minDistBetweenObjects;
		int maxNumberOfObjectsPerEdge;
		double minEdgeLength = Double.MAX_VALUE;

		for (Edge edge : graph.getEdgesWithInfo()) {
			if (edge.getLength() < minEdgeLength) {
				minEdgeLength = edge.getLength();
			}
		}

		minDistBetweenObjects = Math.round((minEdgeLength / 3) * 100000.0) / 100000.0;
		System.out.println("Min Dist between objects for California only is: " + minDistBetweenObjects);
		System.out.println("Min edgeLength for California only is: " + minEdgeLength);

		Random rand = new Random();
		int randomNumberOfEdges = getRandIntBetRange(totalNumberOfEdges / 2, totalNumberOfEdges);

		System.out.println("randomNumberOfEdges: " + randomNumberOfEdges);
		int randomNumberOfObjsOnEdge;
		boolean isAcceptableDistance = false;
		boolean isThereDistanceConflict = false;
		boolean isAcceptableEdgeId = false;

		ArrayList<Integer> randomlyChosenEdgeIds = new ArrayList<Integer>();
		ArrayList<Double> randomDistances = new ArrayList<Double>();

		printGeneratorParameters();
		// i - edge
		for (int i = 0; i < randomNumberOfEdges; i++) {

			while (!isAcceptableEdgeId) {
				edgeId = getRandIntBetRange(1, totalNumberOfEdges);
				if (!randomlyChosenEdgeIds.contains(edgeId))
					isAcceptableEdgeId = true;
				randomlyChosenEdgeIds.add(edgeId);
			}
			isAcceptableEdgeId = false;
			edgeLength = graph.getEdgeDistance(edgeId);
			System.out.println("Current Edge is: " + edgeId + " Edge length: " + edgeLength);

			maxNumberOfObjectsPerEdge = (int) (edgeLength / minDistBetweenObjects - 1);

			randomNumberOfObjsOnEdge = getRandIntBetRange(maxNumberOfObjectsPerEdge / 5, maxNumberOfObjectsPerEdge / 2);

			ArrayList<Double> checkedRandomDistances = new ArrayList<Double>();
			randomDistances.clear();
			checkedRandomDistances.clear();
			// System.out.println("Number of edges completed: " + genEdgeCounter);
			// j - obj
			int genObjCounter = 0;

			for (int j = 0; j < randomNumberOfObjsOnEdge; j++) {
				RoadObject randObj = new RoadObject();

				if (randomDistances.isEmpty()) {

					distanceFromStartNode = getRandDoubleBetRange(minEdgeLength, edgeLength);
					randObj.setDistanceFromStartNode(distanceFromStartNode);
					randomDistances.add(distanceFromStartNode);

					checkedRandomDistances.add(distanceFromStartNode);

					isAcceptableDistance = false;
					isThereDistanceConflict = false;
					genObjCounter++;
				} else {
					while (!isAcceptableDistance) {

						distanceFromStartNode = getRandDoubleBetRange(minEdgeLength, edgeLength);
						if (checkedRandomDistances.contains(distanceFromStartNode)) {
							isAcceptableDistance = false;
							continue;
						}

						checkedRandomDistances.add(distanceFromStartNode);
						for (int k = 0; k < randomDistances.size(); k++) {

							isThereDistanceConflict = false;
							if (!((randomDistances.get(k) + minDistBetweenObjects <= distanceFromStartNode)
									|| (randomDistances.get(k) - minDistBetweenObjects >= distanceFromStartNode))) {
								isThereDistanceConflict = true;

								continue;
							}
							//
						}
						if (!isThereDistanceConflict) {
							isAcceptableDistance = true;
							genObjCounter++;
						}

					}
					randObj.setDistanceFromStartNode(distanceFromStartNode);
					randomDistances.add(distanceFromStartNode);
				}

				objCounter++;
				randObj.setObjId(objCounter);
				randObj.setType(rand.nextBoolean());
				graph.addObjectOnEdge(edgeId, randObj);

				isAcceptableDistance = false;
				isThereDistanceConflict = false;
				System.out.println("Chosen number of edges to generate on: " + randomNumberOfEdges);
				System.out.println("Current edge: " + i + ", length: " + edgeLength);
				System.out.println("Max num of obj on edge: " + maxNumberOfObjectsPerEdge / 2);
				System.out.println("Chosen number of object per edge: " + randomNumberOfObjsOnEdge);
				System.out.println("Num of Objects generated: " + genObjCounter);

			}

		}

		// For Statistics
		m_totalNumberOfObjects = graph.getTotalNumberOfObjects();
		m_totalNumberOfTrueObjects = graph.getTotalNumberOfTrueObjects();
		m_totalNumberOfFalseObjects = graph.getTotalNumberOfFalseObjects();
		m_totalNumberOfEdges = totalNumberOfEdges;
		m_totalLengthOfEdges = graph.getTotalLengthOfAllEdges();

	}

	public static void generateRandomObjectsOnMap2(Graph graph, double probOFTrueObjs) {

		int edgeId = 0;
		int objCounter = 0;
		double distanceFromStartNode = 0.0;
		double edgeLength;
		int totalNumberOfEdges = graph.getNumberOfEdges();
		double minDistBetweenObjects;
		int maxNumberOfObjectsPerEdge;
		double minEdgeLength = Double.MAX_VALUE;
		int totalNumOfGenObjCounter = 0;

		for (Edge edge : graph.getEdgesWithInfo()) {
			if (edge.getLength() < minEdgeLength) {
				minEdgeLength = edge.getLength();
			}
		}

		minDistBetweenObjects = Math.round((minEdgeLength / 3) * 100000.0) / 100000.0;
		System.out.println("Min Dist between objects: " + minDistBetweenObjects);
		System.out.println("Min edgeLength: " + minEdgeLength);

		int randomNumberOfEdges = getRandIntBetRange(totalNumberOfEdges / 2, totalNumberOfEdges);

		System.out.println("randomNumberOfEdges: " + randomNumberOfEdges);
		int randomNumberOfObjsOnEdge;
		boolean isAcceptableDistance = false;
		boolean isThereDistanceConflict = false;
		boolean isAcceptableEdgeId = false;

		ArrayList<Integer> randomlyChosenEdgeIds = new ArrayList<Integer>();
		ArrayList<Double> randomDistances = new ArrayList<Double>();

		Map<Integer, Integer> mapOfEdgeObjectNumber = new HashMap<Integer, Integer>();

		// Creating a map to store the edge Id and number of Objects on the edge.
		for (int i = 0; i < randomNumberOfEdges; i++) {
			while (!isAcceptableEdgeId) {
				edgeId = getRandIntBetRange(1, totalNumberOfEdges);
				if (!randomlyChosenEdgeIds.contains(edgeId))
					isAcceptableEdgeId = true;
				randomlyChosenEdgeIds.add(edgeId);
			}
			isAcceptableEdgeId = false;
			edgeLength = graph.getEdgeDistance(edgeId);
			// System.out.println("Current Edge is: " + edgeId + " Edge length: " +
			// edgeLength);

			maxNumberOfObjectsPerEdge = (int) (edgeLength / minDistBetweenObjects - 1);

			randomNumberOfObjsOnEdge = getRandIntBetRange(maxNumberOfObjectsPerEdge / 5, maxNumberOfObjectsPerEdge / 2);

			mapOfEdgeObjectNumber.put(edgeId, randomNumberOfObjsOnEdge);
			totalNumOfGenObjCounter += randomNumberOfObjsOnEdge;

		}

		// printGeneratorPara"meters();
		// i - edge
		int edgeCounter = 0;
		for (Integer keyEdgeId : mapOfEdgeObjectNumber.keySet()) {
			edgeCounter++;
			System.out.println("Finished Generating on: " + edgeCounter + " out of " + mapOfEdgeObjectNumber.size());
			ArrayList<Double> checkedRandomDistances = new ArrayList<Double>();
			checkedRandomDistances.clear();
			randomDistances.clear();
			edgeLength = graph.getEdgeDistance(keyEdgeId);
			for (int j = 0; j < mapOfEdgeObjectNumber.get(keyEdgeId); j++) {
				RoadObject randObj = new RoadObject();

				if (randomDistances.isEmpty()) {

					distanceFromStartNode = getRandDoubleBetRange(minEdgeLength, edgeLength);
					randObj.setDistanceFromStartNode(distanceFromStartNode);
					randomDistances.add(distanceFromStartNode);

					checkedRandomDistances.add(distanceFromStartNode);

					isAcceptableDistance = false;
					isThereDistanceConflict = false;

				} else {
					while (!isAcceptableDistance) {

						distanceFromStartNode = getRandDoubleBetRange(minEdgeLength, edgeLength);
						if (checkedRandomDistances.contains(distanceFromStartNode)) {
							continue;
						}

						checkedRandomDistances.add(distanceFromStartNode);
						for (int k = 0; k < randomDistances.size(); k++) {

							isThereDistanceConflict = false;
							if (!((randomDistances.get(k) + minDistBetweenObjects <= distanceFromStartNode)
									|| (randomDistances.get(k) - minDistBetweenObjects >= distanceFromStartNode))) {
								isThereDistanceConflict = true;
								continue;
							}

						}
						if (!isThereDistanceConflict) {
							isAcceptableDistance = true;
						}

					}

					randObj.setDistanceFromStartNode(distanceFromStartNode);
					randomDistances.add(distanceFromStartNode);

				}

				objCounter++;
				randObj.setObjId(objCounter);
				// randObj.setType(rand.nextBoolean());
				randObj.setType(Math.random() < probOFTrueObjs);
				graph.addObjectOnEdge(keyEdgeId, randObj);

				isAcceptableDistance = false;
				isThereDistanceConflict = false;
				// System.out.println("Chosen number of edges to generate on: " +
				// randomNumberOfEdges);
				// System.out.println("Current edge: " + keyEdgeId + ", length: " + edgeLength);
				// // System.out.println("Max num of obj on edge: " + maxNumberOfObjectsPerEdge
				// /
				// // 2);
				// // System.out.println("Chosen number of object per edge: " +
				// // randomNumberOfObjsOnEdge);
				// System.out.println("Num of Objects generated: " + totalNumOfGenObjCounter);

			}

		}

		m_totalNumberOfObjects = graph.getTotalNumberOfObjects();
		m_totalNumberOfTrueObjects = graph.getTotalNumberOfTrueObjects();
		m_totalNumberOfFalseObjects = graph.getTotalNumberOfFalseObjects();
		m_totalNumberOfEdges = totalNumberOfEdges;
		m_totalLengthOfEdges = graph.getTotalLengthOfAllEdges();
		int objectCounterOnMap = 0;
		for (Integer edgeIndex : mapOfEdgeObjectNumber.keySet()) {

			objectCounterOnMap += mapOfEdgeObjectNumber.get(edgeIndex);

		}
		int objectCounterOnEdge = 0;
		for (Integer indexEdgeId : graph.getObjectsOnEdges().keySet()) {

			objectCounterOnEdge += graph.getObjectsOnEdges().get(indexEdgeId).size();
		}

		System.out.println("number of objects in Map " + totalNumOfGenObjCounter + ", number of objs on edge: "
				+ graph.getTotalNumberOfObjects());
		System.out.println(
				"number of objects in Map " + objectCounterOnMap + ", number of objs on edge: " + objectCounterOnEdge);
		System.out.println("mapOfEdgeObjectNumber: " + mapOfEdgeObjectNumber.size() + ", graph.getObjectsOnEdges: "
				+ graph.getObjectsOnEdges().size());
	}

	public static void generateRandomObjectsOnMap3(Graph graph, double probOFTrueObjs, int objNumParam) {
		// objNumParam refers to the minimum number of objects on the minimum edge - 1.
		int edgeId = 0;
		int objCounter = 0;
		double distanceFromStartNode = 0.0;
		double edgeLength;
		int totalNumberOfEdges = graph.getNumberOfEdges();
		double minDistBetweenObjects;
		int maxNumberOfObjectsPerEdge;
		double minEdgeLength = Double.MAX_VALUE;
		int totalNumOfGenObjCounter = 0;

		for (Edge edge : graph.getEdgesWithInfo()) {
			if (edge.getLength() < minEdgeLength) {
				minEdgeLength = edge.getLength();
			}
		}

		minDistBetweenObjects = Math.round((minEdgeLength / objNumParam) * 100000.0) / 100000.0;
		System.out.println("Min Dist between objects: " + minDistBetweenObjects);
		System.out.println("Min edgeLength: " + minEdgeLength);

		// Random rand = new Random();
		int randomNumberOfEdges = getRandIntBetRange(totalNumberOfEdges / 2, totalNumberOfEdges);

		System.out.println("randomNumberOfEdges: " + randomNumberOfEdges);
		int randomNumberOfObjsOnEdge;
		boolean isAcceptableDistance = false;
		boolean isThereDistanceConflict = false;
		boolean isAcceptableEdgeId = false;

		ArrayList<Integer> randomlyChosenEdgeIds = new ArrayList<Integer>();
		ArrayList<Double> randomDistances = new ArrayList<Double>();

		Map<Integer, Integer> mapOfEdgeObjectNumber = new HashMap<Integer, Integer>();

		// Creating a map to store the edge Id and number of Objects on the edge.
		for (int i = 0; i < randomNumberOfEdges; i++) {
			while (!isAcceptableEdgeId) {
				edgeId = getRandIntBetRange(1, totalNumberOfEdges);
				if (!randomlyChosenEdgeIds.contains(edgeId))
					isAcceptableEdgeId = true;
				randomlyChosenEdgeIds.add(edgeId);
			}
			isAcceptableEdgeId = false;
			edgeLength = graph.getEdgeDistance(edgeId);
			// System.out.println("Current Edge is: " + edgeId + " Edge length: " +
			// edgeLength);

			maxNumberOfObjectsPerEdge = (int) (edgeLength / minDistBetweenObjects - 1);

			randomNumberOfObjsOnEdge = getRandIntBetRange(maxNumberOfObjectsPerEdge / 5, maxNumberOfObjectsPerEdge / 2);

			mapOfEdgeObjectNumber.put(edgeId, randomNumberOfObjsOnEdge);
			totalNumOfGenObjCounter += randomNumberOfObjsOnEdge;

		}

		// printGeneratorPara"meters();
		// i - edge
		//int edgeCounter = 0;
		for (Integer keyEdgeId : mapOfEdgeObjectNumber.keySet()) {
		//	edgeCounter++;
			// System.out.println("Finished Generating on: " + edgeCounter + " out of " +
			// mapOfEdgeObjectNumber.size());
			ArrayList<Double> checkedRandomDistances = new ArrayList<Double>();
			checkedRandomDistances.clear();
			randomDistances.clear();
			edgeLength = graph.getEdgeDistance(keyEdgeId);
			for (int j = 0; j < mapOfEdgeObjectNumber.get(keyEdgeId); j++) {
				RoadObject randObj = new RoadObject();

				if (randomDistances.isEmpty()) {

					distanceFromStartNode = getRandDoubleBetRange(minEdgeLength, edgeLength);
					randObj.setDistanceFromStartNode(distanceFromStartNode);
					randomDistances.add(distanceFromStartNode);

					checkedRandomDistances.add(distanceFromStartNode);

					isAcceptableDistance = false;
					isThereDistanceConflict = false;

				} else {
					while (!isAcceptableDistance) {

						distanceFromStartNode = getRandDoubleBetRange(minEdgeLength, edgeLength);
						if (checkedRandomDistances.contains(distanceFromStartNode)) {
							continue;
						}

						checkedRandomDistances.add(distanceFromStartNode);
						for (int k = 0; k < randomDistances.size(); k++) {

							isThereDistanceConflict = false;
							if (!((randomDistances.get(k) + minDistBetweenObjects <= distanceFromStartNode)
									|| (randomDistances.get(k) - minDistBetweenObjects >= distanceFromStartNode))) {
								isThereDistanceConflict = true;
								continue;
							}

						}
						if (!isThereDistanceConflict) {
							isAcceptableDistance = true;
						}

					}

					randObj.setDistanceFromStartNode(distanceFromStartNode);
					randomDistances.add(distanceFromStartNode);

				}

				objCounter++;
				randObj.setObjId(objCounter);
				// randObj.setType(rand.nextBoolean());
				randObj.setType(Math.random() < probOFTrueObjs);
				graph.addObjectOnEdge(keyEdgeId, randObj);

				isAcceptableDistance = false;
				isThereDistanceConflict = false;
				// System.out.println("Chosen number of edges to generate on: " +
				// randomNumberOfEdges);
				// System.out.println("Current edge: " + keyEdgeId + ", length: " + edgeLength);
				// // System.out.println("Max num of obj on edge: " + maxNumberOfObjectsPerEdge
				// /
				// // 2);
				// // System.out.println("Chosen number of object per edge: " +
				// // randomNumberOfObjsOnEdge);
				// System.out.println("Num of Objects generated: " + totalNumOfGenObjCounter);

			}

		}

		m_totalNumberOfObjects = graph.getTotalNumberOfObjects();
		m_totalNumberOfTrueObjects = graph.getTotalNumberOfTrueObjects();
		m_totalNumberOfFalseObjects = graph.getTotalNumberOfFalseObjects();
		m_totalNumberOfEdges = totalNumberOfEdges;
		m_totalLengthOfEdges = graph.getTotalLengthOfAllEdges();
		int objectCounterOnMap = 0;
		for (Integer edgeIndex : mapOfEdgeObjectNumber.keySet()) {

			objectCounterOnMap += mapOfEdgeObjectNumber.get(edgeIndex);

		}
		int objectCounterOnEdge = 0;
		for (Integer indexEdgeId : graph.getObjectsOnEdges().keySet()) {

			objectCounterOnEdge += graph.getObjectsOnEdges().get(indexEdgeId).size();
		}

		System.out.println("number of objects in Map " + totalNumOfGenObjCounter + ", number of objs on edge: "
				+ graph.getTotalNumberOfObjects());
		System.out.println(
				"number of objects in Map " + objectCounterOnMap + ", number of objs on edge: " + objectCounterOnEdge);
		System.out.println("mapOfEdgeObjectNumber: " + mapOfEdgeObjectNumber.size() + ", graph.getObjectsOnEdges: "
				+ graph.getObjectsOnEdges().size());
	}

	public static void generateRandomObjectsOnMap5(Graph graph, double probOFTrueObjs, int totalNumOfObjects) {
		// objNumParam refers to the minimum number of objects on the minimum edge - 1.
		// int edgeId = 0;
		int objCounter = 0;
		double distanceFromStartNode = 0.0;
		double edgeLength;
		int totalNumberOfEdges = graph.getNumberOfEdges();
		double minDistBetweenObjects;
		int maxNumberOfObjectsPerEdge;
		double minEdgeLength = Double.MAX_VALUE;
		// int totalNumOfGenObjCounter = 0;

		for (Edge edge : graph.getEdgesWithInfo()) {
			if (edge.getLength() < minEdgeLength) {
				minEdgeLength = edge.getLength();
			}
		}

		// minDistBetweenObjects = Math.round((minEdgeLength / 3) * 100000.0) /
		// 100000.0;
		minDistBetweenObjects = Math.round((graph.getTotalLengthOfAllEdges() / totalNumOfObjects) * 100000.0)
				/ 100000.0;
		System.out.println("Min Dist between objects: " + minDistBetweenObjects);
		System.out.println("Min edgeLength: " + minEdgeLength);
		System.out.println("Total Length of all edges: " + graph.getTotalLengthOfAllEdges());
		int whileLoopCounter = 0;
		Map<Integer, ArrayList<Double>> acceptedDistancesOnEdge = new HashMap<Integer, ArrayList<Double>>();

		while (objCounter < totalNumOfObjects) {

			if (objCounter > totalNumOfObjects) {
				continue;
			}
			whileLoopCounter++;
			if (whileLoopCounter == 7) {
				// System.out.println("Last loop");
			}
			// System.out.println("While loop Count: " + whileLoopCounter);
			boolean isAcceptableDistance = false;
			boolean isThereDistanceConflict = false;
			// boolean isAcceptableEdgeId = false;

			// ArrayList<Integer> randomlyChosenEdgeIds = new ArrayList<Integer>();
			// ArrayList<Double> randomDistances = new ArrayList<Double>();

			// printGeneratorPara"meters();
			// i - edge
			// int edgeCounter = 0;
			for (Edge edge : graph.getEdgesWithInfo()) {
				if (objCounter > totalNumOfObjects) {
					continue;
				}
				// edgeCounter++;
				// System.out.println("Finished Generating on: " + edgeCounter + " out of "
				// +mapOfEdgeObjectNumber.size());
				ArrayList<Double> checkedRandomDistances = new ArrayList<Double>();

				checkedRandomDistances.clear();
				// randomDistances.clear();
				edgeLength = graph.getEdgeDistance(edge.getEdgeId());
				maxNumberOfObjectsPerEdge = (int) (edgeLength / minDistBetweenObjects - 1);
				if (maxNumberOfObjectsPerEdge < 1)
					continue;
				ArrayList<Double> acceptedDistances;

				if (!acceptedDistancesOnEdge.containsKey(edge.getEdgeId())) {
					acceptedDistances = new ArrayList<Double>();
					acceptedDistancesOnEdge.put(edge.getEdgeId(), acceptedDistances);
				}

				// System.out.println("max obj per edge: " + maxNumberOfObjectsPerEdge);
				for (int j = 0; j < maxNumberOfObjectsPerEdge - 1; j++) {

					if (objCounter > totalNumOfObjects) {
						continue;
					}
					RoadObject randObj = new RoadObject();

					if (acceptedDistancesOnEdge.get(edge.getEdgeId()).isEmpty()) {

						distanceFromStartNode = getRandDoubleBetRange(minEdgeLength, edgeLength);
						randObj.setDistanceFromStartNode(distanceFromStartNode);
						acceptedDistancesOnEdge.get(edge.getEdgeId()).add(distanceFromStartNode);
						checkedRandomDistances.add(distanceFromStartNode);

						isAcceptableDistance = false;
						isThereDistanceConflict = false;

					} else {
						while (!isAcceptableDistance) {

							distanceFromStartNode = getRandDoubleBetRange(minEdgeLength, edgeLength);
							if (checkedRandomDistances.contains(distanceFromStartNode)) {
								break;
							}

							checkedRandomDistances.add(distanceFromStartNode);
							for (int k = 0; k < acceptedDistancesOnEdge.get(edge.getEdgeId()).size(); k++) {

								isThereDistanceConflict = false;
								if (!((acceptedDistancesOnEdge.get(edge.getEdgeId()).get(k)
										+ minDistBetweenObjects <= distanceFromStartNode)
										|| (acceptedDistancesOnEdge.get(edge.getEdgeId()).get(k)
												- minDistBetweenObjects >= distanceFromStartNode))) {
									isThereDistanceConflict = true;
									break;
								}

							}
							if (!isThereDistanceConflict) {
								// System.out.println("The distance can be used");
								isAcceptableDistance = true;
							}

						}

					}
					randObj.setDistanceFromStartNode(distanceFromStartNode);
					acceptedDistancesOnEdge.get(edge.getEdgeId()).add(distanceFromStartNode);
					randObj.setObjId(objCounter);
					// randObj.setType(rand.nextBoolean());
					randObj.setType(Math.random() < probOFTrueObjs);

					if (graph.addObjectOnEdge(edge.getEdgeId(), randObj)) {
						objCounter++;

						System.out.println(objCounter + " Object Added");
					}

					isAcceptableDistance = false;
					isThereDistanceConflict = false;

				}
			}

			if (graph.getTotalNumberOfObjects() == 20001) {
				System.out.println("success");
			}
		}

		m_totalNumberOfObjects = graph.getTotalNumberOfObjects();
		m_totalNumberOfTrueObjects = graph.getTotalNumberOfTrueObjects();
		m_totalNumberOfFalseObjects = graph.getTotalNumberOfFalseObjects();
		m_totalNumberOfEdges = totalNumberOfEdges;
		m_totalNumberOfEdgesContainingObjects = graph.getObjectsOnEdges().size();
		m_totalLengthOfEdges = graph.getTotalLengthOfAllEdges();

		System.out.println("size of acceptedDistancesOnEdge: " + acceptedDistancesOnEdge.size());
	}

	public static void generateRandomObjectsOnMap4(Graph graph, double probOFTrueObjs, int totalNumOfObjsOnMap) {
		// objNumParam refers to the minimum number of objects on the minimum edge - 1.

		int objCounter = 0;
		double distanceFromStartNode = 0.0;
		double edgeLength;

		double minDistBetweenObjects;
		int maxNumberOfObjectsPerEdge;
		double minEdgeLength = Double.MAX_VALUE;

		for (Edge edge : graph.getEdgesWithInfo()) {
			if (edge.getLength() < minEdgeLength) {
				minEdgeLength = edge.getLength();
			}
		}
		minDistBetweenObjects = Math.round((graph.getTotalLengthOfAllEdges() / totalNumOfObjsOnMap) * 100000.0)
				/ 100000.0;
		// int edgeCounter = 0;
		int whileLoopCounter = 0;
		while (objCounter < totalNumOfObjsOnMap) {
			whileLoopCounter++;
			System.out.println("While loop: " + whileLoopCounter);
			boolean isAcceptableDistance = false;
			boolean isThereDistanceConflict = false;

			ArrayList<Double> randomDistances = new ArrayList<Double>();
			System.out.println("current obj counter: " + objCounter + " out of " + totalNumOfObjsOnMap);

			for (int i = 0; i < graph.getEdgesWithInfo().size(); i++) {
				// edgeCounter++;
				// System.out.println("Tried Generating on: " + edgeCounter + " out of " +
				// graph.getEdgesWithInfo().size());

				ArrayList<Double> checkedRandomDistances = new ArrayList<Double>();
				checkedRandomDistances.clear();
				randomDistances.clear();
				if (objCounter > totalNumOfObjsOnMap) {
					continue;
				}
				edgeLength = graph.getEdgeDistance(graph.getEdgesWithInfo().get(i).getEdgeId());
				maxNumberOfObjectsPerEdge = (int) (edgeLength / minDistBetweenObjects - 1);
				if (maxNumberOfObjectsPerEdge < 1)
					continue;
				// System.out.println("current obj counter: " + objCounter + " out of " +
				// totalNumOfObjsOnMap);
				if (objCounter > totalNumOfObjsOnMap) {
					continue;
				}
				for (int j = 0; j < maxNumberOfObjectsPerEdge; j++) {

					RoadObject randObj = new RoadObject();

					if (randomDistances.isEmpty()) {

						distanceFromStartNode = getRandDoubleBetRange(minEdgeLength, edgeLength);
						randObj.setDistanceFromStartNode(distanceFromStartNode);
						randomDistances.add(distanceFromStartNode);

						checkedRandomDistances.add(distanceFromStartNode);

						isAcceptableDistance = false;
						isThereDistanceConflict = false;

					} else {
						while (!isAcceptableDistance) {

							distanceFromStartNode = getRandDoubleBetRange(minEdgeLength, edgeLength);
							if (checkedRandomDistances.contains(distanceFromStartNode)) {
								continue;
							}

							checkedRandomDistances.add(distanceFromStartNode);
							for (int k = 0; k < randomDistances.size(); k++) {

								isThereDistanceConflict = false;
								if (!((randomDistances.get(k) + minDistBetweenObjects <= distanceFromStartNode)
										|| (randomDistances.get(k) - minDistBetweenObjects >= distanceFromStartNode))) {
									isThereDistanceConflict = true;
									continue;
								}

							}
							if (!isThereDistanceConflict) {
								isAcceptableDistance = true;
							}

						}

						randObj.setDistanceFromStartNode(distanceFromStartNode);
						randomDistances.add(distanceFromStartNode);

					}

					objCounter++;
					randObj.setObjId(objCounter);
					// randObj.setType(rand.nextBoolean());
					randObj.setType(Math.random() < probOFTrueObjs);
					graph.addObjectOnEdge(graph.getEdgesWithInfo().get(i).getEdgeId(), randObj);

					isAcceptableDistance = false;
					isThereDistanceConflict = false;
					if (objCounter > totalNumOfObjsOnMap) {
						continue;
					}
				}

			}
		}

		// For Statistics
		m_totalNumberOfObjects = graph.getTotalNumberOfObjects();
		m_totalNumberOfTrueObjects = graph.getTotalNumberOfTrueObjects();
		m_totalNumberOfFalseObjects = graph.getTotalNumberOfFalseObjects();
		m_totalNumberOfEdges = graph.getEdgesWithInfo().size();
		m_totalNumberOfEdgesContainingObjects = graph.getObjectsOnEdges().size();
		m_totalLengthOfEdges = graph.getTotalLengthOfAllEdges();
	}

	private static int getMaxNumOfObjsPerEdge(double edgeLength, double minDistBetweenObjs) {
		double m1 = 0.0;
		if ((edgeLength / minDistBetweenObjs - 6) < 0) {
			m1 = 0.0;
		} else {
			m1 = Math.round((edgeLength / minDistBetweenObjs - 6) * m_minDistBetweenObjsPrecision)
					/ m_minDistBetweenObjsPrecision;
		}

		double m2;
		if (m1 > minDistBetweenObjs) {
			m2 = m1;
		} else {
			m2 = 0;
		}

		if (m2 > 0) {
			if (m2 > 1) {
				return (int) m2;
			} else {
				return 1;
			}
		} else {
			return 0;
		}
	}

	private static int getTotalObjParam(String datasetName, int totalObject) {
		String line = "";
		String txtFilename = "ConfigFiles/totalObjectParam.txt";
		datasetName = datasetName.trim().toUpperCase();

		try (BufferedReader br = new BufferedReader(new FileReader(txtFilename))) {
			while ((line = br.readLine()) != null) {
				String[] record = line.split(" ");

				if (record.length == 3) {
					if ((datasetName.equals(record[0])) && (Integer.parseInt(record[1]) == totalObject)) {
						return Integer.parseInt(record[2]);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return totalObject;
	}

	public static int getRandIntBetRange(double min, double max) {
		int x = (int) ((Math.random() * ((max - min) + 1)) + min);
		return x;
	}

	public static double getRandDoubleBetRange(double min, double max) {
		double x = ThreadLocalRandom.current().nextDouble(min, max);
		// DoubleRounder.round(x, 17);
		// DoubleRounder.round(x,m_distFromStartNodePrecision);
		x = Math.round(x * m_distFromStartNodePrecision) / m_distFromStartNodePrecision;
		return x;
	}

	public static double getRandDoubleBetRange2(double min, double max) {
		double x = (Math.random() * ((max - min) + 1)) + min;
		x = Math.round(x * m_distFromStartNodePrecision) / m_distFromStartNodePrecision;
		return x;
	}

	public static double getRandDoubleBetRange1(double min, double max) {
		double x;
		x = ThreadLocalRandom.current().nextDouble(min, max);
		BigDecimal bd = new BigDecimal(Double.toString(x));
		bd = bd.setScale(17, RoundingMode.HALF_DOWN);
		double finalValue = bd.doubleValue();
		return finalValue;
	}

//	public static double getRandDoubleBetRange3(double min, double max) {
//		// 7.2 unboundeddoublerandom with commons math
//		double generateDouble = new RandomDataGenerator().getRandomGenerator().nextDouble() * ((max - min) + 1) + min;
//		// double x = (Math.random() * ((max - min) + 1)) + min;
//		generateDouble = Math.round(generateDouble * m_distFromStartNodePrecision) / m_distFromStartNodePrecision;
//		return generateDouble;
//	}

	public static double getRandDoubleBetRange4(double min, double max) {
		// 8.1 boundeddoublerandom with commons math
		double generateDouble = min + new Random().nextDouble() * (max - min);
		generateDouble = Math.round(generateDouble * m_distFromStartNodePrecision) / m_distFromStartNodePrecision;
		return generateDouble;
	}

//	public static double getRandDoubleBetRange5(double min, double max) {
//		// 8.2 boundeddoublerandom with commons math
//		double generateDouble = new RandomDataGenerator().nextUniform(min, max);
//		generateDouble = Math.round(generateDouble * m_distFromStartNodePrecision) / m_distFromStartNodePrecision;
//		return generateDouble;
//	}

	public static long getThreadRandomNumberInBetween(long lower, long upper) {
		ThreadLocalRandom generator = ThreadLocalRandom.current();
		long randomValue = generator.nextLong(lower, upper);
		return randomValue;
	}

	public static double getRandDoubleInBetween(double min, double max) {
		double x = (Math.random() * ((max - min) + 1)) + min;
		x = Math.round(x * m_distFromStartNodePrecision) / m_distFromStartNodePrecision;
		return x;
	}

	public static ArrayList<Integer> getEdgesWithinRadius(Graph graph, double perimeter, int edgeId) {
		ArrayList<Integer> selectedEdges = new ArrayList<Integer>();
		LinkedList<HashMap<Integer, Double>> visitedEdges = new LinkedList<HashMap<Integer, Double>>();
		LinkedList<Integer> edgeQueue = new LinkedList<Integer>();

		edgeQueue.add(edgeId);
		double initialDistance = 0.0;

		while (!edgeQueue.isEmpty()) {
			int currentEdge = edgeQueue.poll();
			if (visitedEdges.isEmpty()) {
				HashMap<Integer, Double> hashedEdge = new HashMap<Integer, Double>();
				hashedEdge.put(currentEdge, initialDistance);
				visitedEdges.add(hashedEdge);
				if (selectedEdges.isEmpty()) {
					if (!selectedEdges.contains(currentEdge)) {
						selectedEdges.add(currentEdge);
					}
				}
				// 1st you added edgeID (edgeQueue.add(edgeId);)
				// then you polled it from there and assigned to currentEdge (int currentEdge =
				// edgeQueue.poll();)
				// now you are adding same thing to the queue (next line)
				// what is point of making this clause of "if (visitedEdges.isEmpty())"
				// statement ?
				edgeQueue.add(currentEdge);
			} else {
				for (Integer edgeIndex : graph.getAdjacencyEdgeIds(currentEdge)) {
					if (checkIfEdgeExists(visitedEdges, edgeIndex) == true) {
						continue;
					} else {
						double edgeLength = graph.getEdgeDistance(edgeIndex);
						// System.out.println("Given Edge Len: " + edgeLength);

						for (int i = 0; i < visitedEdges.size(); i++) {
							if (visitedEdges.get(i).containsKey(currentEdge)) {
								double currentEdgeDistance = visitedEdges.get(i).get(currentEdge);
								// System.out.println("Current Edge Len: " + currentEdgeDistance);

								if (edgeLength + currentEdgeDistance <= perimeter) {
									HashMap<Integer, Double> hashedEdge = new HashMap<Integer, Double>();
									double lengthAfterAdding = currentEdgeDistance + edgeLength;
									// System.out.println("Edge Len after adding: " + lengthAfterAdding);

									hashedEdge.put(edgeIndex, lengthAfterAdding);
									// you are looping though visitedEdges (for (int i = 0; i < visitedEdges.size();
									// i++))
									// and in next line adding something to the same visitedEdges inside of same
									// loop
									// does it make sense?
									visitedEdges.add(hashedEdge);
									if (!selectedEdges.contains(edgeIndex)) {
										selectedEdges.add(edgeIndex);
									}
									edgeQueue.add(edgeIndex);
								}

							}
						}

					}

				}
			}

		}

		return selectedEdges;
	}

	public static boolean checkIfEdgeExists(LinkedList<HashMap<Integer, Double>> visitedEdges, int edgeId) {

		for (int i = 0; i < visitedEdges.size(); i++) {
			if (visitedEdges.get(i).containsKey(edgeId)) {
				return true;
			}
		}
		return false;
	}

	public static void printGeneratorParameters() {
		System.out.println("--------------------------------------------------");
		System.out.println("Generator's Configurable Parameters: ");
		// System.out.println("minNumberOfObjsPerEdge: " + minNumberOfObjsPerEdge);
		System.out.println("minDistanceBetObjs: " + m_minDistBetweenObjs);
		// System.out.println("maxNumberOfObjsPerEdge: " + maxNumberOfObjsPerEdge );
		// System.out.println("uniformObjects: " + uniformObjects);
		// System.out.println("uniformDataObjects: " + uniformDataObjects);
		// System.out.println("uniformQueryObjects: " + uniformQueryObjects);
		System.out.println("--------------------------------------------------");
	}

	public static void printStatistics() {
		double trueObjectsPer, falseObjectsPer;
		trueObjectsPer = Math.round((double) m_totalNumberOfTrueObjects / m_totalNumberOfObjects * 100 * 100.0) / 100.0;
		falseObjectsPer = Math.round((double) m_totalNumberOfFalseObjects / m_totalNumberOfObjects * 100 * 100.0)
				/ 100.0;
		System.out.println("--------------------------------------------------");
		System.out.println("Total number of Objects: " + m_totalNumberOfObjects);
		System.out
				.println("Total number of True Objects: " + m_totalNumberOfTrueObjects + ", " + trueObjectsPer + " %");
		System.out.println(
				"Total number of False Objects: " + m_totalNumberOfFalseObjects + ", " + falseObjectsPer + " %");
		System.out.println("Total number of Edges: " + m_totalNumberOfEdges);
		System.out.println("Total number of Edges containing objects: " + m_totalNumberOfEdgesContainingObjects);
		System.out.println("Minimum edge length: " + m_minEdgeLength);
		System.out.println("Maximum edge length: " + m_maxEdgeLength);
		System.out.println("Total lenght of all edges: " + m_totalLengthOfEdges);
		System.out.println("Minimum dist between objects: " + m_minDistBetweenObjs);

		System.out.println("--------------------------------------------------");

	}

	private static void printCounterInfo(int expected, int actual, String fileName) {
		// String debugCounterInfo = "Statistics/DebugCounterInfo-" +
		// UtilsManagment.getNormalDateTime() + ".txt";

		try {
			FileWriter outputFile = new FileWriter(fileName, true);
			PrintWriter pw = new PrintWriter(outputFile, true);
			pw.append(actual + "\n");
			// pw.append("maxNumberOfObjects: "+ expected + " counterForObject: " + actual +
			// "\n");
			// pw.write(System.lineSeparator()); // new line
			pw.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	// To get the first and last key of the map
	public static <K, V> Entry<K, V> getFirst(Map<K, V> map) {
		if (map.isEmpty())
			return null;
		return map.entrySet().iterator().next();
	}

	public static <K, V> Entry<K, V> getLast(Map<K, V> map) {
		try {
			if (map instanceof LinkedHashMap)
				return getLastViaReflection(map);
		} catch (Exception ignore) {
		}
		return getLastByIterating(map);
	}

	public static <K, V> Entry<K, V> getLastByIterating(Map<K, V> map) {
		Entry<K, V> last = null;
		for (Entry<K, V> e : map.entrySet())
			last = e;
		return last;
	}

	public static <K, V> Entry<K, V> getLastViaReflection(Map<K, V> map)
			throws NoSuchFieldException, IllegalAccessException {
		Field tail = map.getClass().getDeclaredField("tail");
		tail.setAccessible(true);
		return (Entry<K, V>) tail.get(map);
	}
	// --ends here//

	public static ArrayList<Double> getGaussianDistributionDistance(int size, double standardDeviation,
			int scaleFactor) {
		ArrayList<Double> generatedGaussianDistance = new ArrayList<Double>();
		Random gen = new Random();
		while (size != 0) {
			generatedGaussianDistance.add((Math.abs(gen.nextGaussian() * standardDeviation)) * scaleFactor);
			size--;
		}
		return generatedGaussianDistance;
	}

	public static int zcreateCentroidDistribution(Graph graph, int objectCounter, double standardDeviationValue,
			int scaleFactor, Map<Integer, ArrayList<Double>> acceptedDistancesOnEdge,
			ArrayList<Integer> centroidNodeIds, int numberOfObjects, boolean objectType) {

		int totalNumberOfNodes = graph.getNodesWithInfo().size();
		boolean foundCentroidNodeId = false;

		int selectedRandomNode = -1;
		while (!foundCentroidNodeId) {
			selectedRandomNode = (int) getThreadRandomNumberInBetween(1, totalNumberOfNodes - 1);
			System.out.println("selected Random node: " + selectedRandomNode);

			if (centroidNodeIds.isEmpty() || !centroidNodeIds.contains(selectedRandomNode)) {
				foundCentroidNodeId = true;
				break;
			}

		}
		ArrayList<Double> randomGaussianDistance = getGaussianDistributionDistance(numberOfObjects,
				standardDeviationValue, scaleFactor);
		Collections.shuffle(randomGaussianDistance);
		ArrayList<Integer> eachCentroidNodes = new ArrayList<Integer>();
		while (randomGaussianDistance.size() != 0) {

			int RandomIndex = random.nextInt(randomGaussianDistance.size());
			double selectedRandomGaussianDistance = randomGaussianDistance.get(RandomIndex);

			// Map <edgeId, finalDistance>
			Map<Integer, Double> edgeAndDistance = traverseFromGivenNodeUptoDistance(graph, selectedRandomNode,
					selectedRandomGaussianDistance);

			int edgeId = getFirst(edgeAndDistance).getKey();
			double finalDistance = getFirst(edgeAndDistance).getValue();
			if (!eachCentroidNodes.contains(edgeId)) {
				eachCentroidNodes.add(edgeId);
			}

			RoadObject roadObj = new RoadObject();
			roadObj.setObjId(objectCounter);
			roadObj.setType(objectType);

			if (!acceptedDistancesOnEdge.containsKey(edgeId)) {
				ArrayList<Double> acceptedDistances = new ArrayList<Double>();
				acceptedDistancesOnEdge.put(edgeId, acceptedDistances);
			}

			if (!acceptedDistancesOnEdge.get(edgeId).contains(finalDistance)) {
				if (!roadObj.setDistanceFromStartNode(finalDistance)) {
					continue;
				}
			}

			if (graph.addObjectOnEdge(edgeId, roadObj)) {
				objectCounter++;
				// System.out.println(objCounter+" objects added");
				acceptedDistancesOnEdge.get(edgeId).add(finalDistance);
				// centroidEdgeIds.add(edgeId);
				centroidNodeIds.add(selectedRandomNode);
				centroidNodeIds.addAll(graph.getStartAndEndNodes(edgeId));
				centroidNodeIds.add(selectedRandomNode);
				randomGaussianDistance.remove(RandomIndex);

			}

		}
		// System.out.println("For the centroid node: " + selectedRandomNode + "
		// generated " + numberOfObjects
		// + " objects covering total: " + eachCentroidNodes.size() + " edges.");
		// System.out.println(eachCentroidNodes.size());
		// int added = 0;
		// for (int j = 0; j < countList.size(); j++) {
		// added += countList.get(j);
		// }
		// System.out.println("min: " + Collections.min(countList));
		// System.out.println("max: " + Collections.max(countList));
		// System.out.println("Average: " + added / countList.size());
		// System.out.println(Collections.min(countList));
		// System.out.println(Collections.max(countList));
		// System.out.println(added / countList.size());
		// System.out.println(" ");
		//
		// countList.clear();
		// eachCentroidNodes.clear();

		// }

		// int added = 0;
		// for (int i = 0; i < countList.size(); i++) {
		// added += countList.get(i);
		// }
		// System.out.println("min: " + Collections.min(countList));
		// System.out.println("max: " + Collections.max(countList));
		// System.out.println("Average: " + added / countList.size());
		return objectCounter;
		// 5, 15, 6, 8
	}

//	public static void zcreateCentroidDistribution2(Graph graph, int datasetCode, int objectCounter,
//			Map<Integer, ArrayList<Double>> acceptedDistancesOnEdge, ArrayList<Integer> centroidNodeIds,
//			int numberOfObjects, boolean objectType) {
//		Map<Edge, ArrayList<Vector2D>> acceptedPoints = new HashMap<Edge, ArrayList<Vector2D>>();
//
//		while (objectCounter < numberOfObjects) {
//			ArrayList<Vector2D> retrievedPoints = UtilsManagment.getEuclideanObjectPoints(datasetCode, numberOfObjects);
//			Map<Edge, ArrayList<Vector2D>> initialAcceptedPoints = UtilsManagment.isRoadObjectOnEdge(graph,
//					retrievedPoints);
//			for (Edge edgeIndex : initialAcceptedPoints.keySet()) {
//				if (acceptedPoints.isEmpty()) {
//					acceptedPoints.put(edgeIndex, initialAcceptedPoints.get(edgeIndex));
//					objectCounter += initialAcceptedPoints.get(edgeIndex).size();
//				} else {
//					if (!acceptedPoints.containsKey(edgeIndex)) {
//						acceptedPoints.get(edgeIndex).addAll(initialAcceptedPoints.get(edgeIndex));
//						objectCounter += initialAcceptedPoints.get(edgeIndex).size();
//					}
//				}
//
//			}
//
//		}
//
//		if (objectCounter != numberOfObjects) {
//			while (objectCounter > numberOfObjects) {
//				int remainingObject = objectCounter - numberOfObjects;
//				for (int j = 0; j < remainingObject; j++) {
//					List<Integer> keyEdgeIndex = new ArrayList<Integer>(acceptedDistancesOnEdge.keySet());
//					Integer randomKeyEdgeId = keyEdgeIndex.get(random.nextInt(keyEdgeIndex.size()));
//					ArrayList<Double> randomKeyEdgeIdValue = acceptedDistancesOnEdge.get(randomKeyEdgeId);
//					randomKeyEdgeIdValue.remove(randomKeyEdgeIdValue.get(random.nextInt(randomKeyEdgeIdValue.size())));
//					Collections.shuffle(keyEdgeIndex);
//					objectCounter--;
//					j++;
//				}
//
//			}
//
//		}
//		Map<Integer, ArrayList<Double>> objectsOnRoad = UtilsManagment.convertRoadObjectPointsToDistance(graph,
//				acceptedPoints);
//		UtilsManagment.createRoadObjectsOnMap(graph, objectsOnRoad, objectType);
//
//	}

	public static void zcreateUniformDistribution(Graph graph, int objectCounter,
			Map<Integer, ArrayList<Double>> acceptedDistancesOnEdge, ArrayList<Integer> centroidEdgeIds,
			int numberOfObject, boolean ObjectType) {
		// int objCounter = objectCounter;
		int totalNumOfObjects = objectCounter + numberOfObject;
		int randomEdgeId;
		boolean testVar;
		int totalNumberOfEdges = graph.getEdgesWithInfo().size();
		LinkedList<Boolean> boolValues = new LinkedList<Boolean>();
		for (int i = 0; i < totalNumOfObjects; i++) {
			boolValues.add(ObjectType);
		}
		while (objectCounter <= totalNumOfObjects) {
			randomEdgeId = getRandIntBetRange(0, totalNumberOfEdges - 1);
			RoadObject object = new RoadObject();
			object.setObjId(objectCounter);

			if (!acceptedDistancesOnEdge.containsKey(randomEdgeId)) {
				ArrayList<Double> acceptedDistances = new ArrayList<Double>();
				acceptedDistancesOnEdge.put(randomEdgeId, acceptedDistances);
			}
			double edgeLength = graph.getEdgeDistance(randomEdgeId);
			double distFromStartNode = getRandDoubleBetRange2(0, edgeLength);
			if (!acceptedDistancesOnEdge.get(randomEdgeId).contains(distFromStartNode)) {
				object.setDistanceFromStartNode(distFromStartNode);
			} else {
				continue;

			}

			if (!boolValues.isEmpty()) {
				testVar = boolValues.poll();
				object.setType(testVar);
			}
			if (graph.addObjectOnEdge(randomEdgeId, object)) {
				objectCounter++;
				acceptedDistancesOnEdge.get(randomEdgeId).add(distFromStartNode);
				// System.out.println(objCounter + " Object Added" + ", distFromSN: " +
				// distFromStartNode + " on edge: "
				// + randomEdgeId + " of length: " + edgeLength);
			}

		}
	}

	public static void zgenerateCUUCDistribution(Graph graph, double standardDeviationValue, int scaleFactor,
			int numberOfTrueObject, int numberOfFalseObject, boolean objectType) {
		graph.removeObjectsOnEdges();
		int objCounter = 1;
		Map<Integer, ArrayList<Double>> acceptedDistancesOnEdge = new HashMap<Integer, ArrayList<Double>>();

		ArrayList<Integer> centroidEdgeIds = new ArrayList<Integer>();
		if (objectType == true) {
			// <C,U> : < Query/True - C, Data/False - U >
			for (int i = 0; i < m_numberOfCentroids; i++) {
				int numOfObjPerCentroid = numberOfTrueObject / m_numberOfCentroids;
				zcreateCentroidDistribution(graph, objCounter, standardDeviationValue, scaleFactor,
						acceptedDistancesOnEdge, centroidEdgeIds, numOfObjPerCentroid, objectType);
				objCounter += numOfObjPerCentroid;
			}
			zcreateUniformDistribution(graph, objCounter, acceptedDistancesOnEdge, centroidEdgeIds, numberOfFalseObject,
					!objectType);
			objCounter += numberOfFalseObject;

		} else {
			// <U,C> : < Query/True - U, Data/False - C >
			for (int i = 0; i < m_numberOfCentroids; i++) {
				int numOfObjPerCentroid = numberOfFalseObject / m_numberOfCentroids;
				zcreateCentroidDistribution(graph, objCounter, standardDeviationValue, scaleFactor,
						acceptedDistancesOnEdge, centroidEdgeIds, numOfObjPerCentroid, objectType);
				objCounter += numOfObjPerCentroid;
			}
			zcreateUniformDistribution(graph, objCounter, acceptedDistancesOnEdge, centroidEdgeIds, numberOfTrueObject,
					!objectType);
			objCounter += numberOfTrueObject;

		}

	}

	public static void zgenerateCCDistribution(Graph graph, double standardDeviationValue, int scaleFactor,
			int numberOfTrueObject, int numberOfFalseObject) {

		// <C,C> : < Query/True - C, Data/False - C >
		graph.removeObjectsOnEdges();
		int objCounter = 1;
		Map<Integer, ArrayList<Double>> acceptedDistancesOnEdge = new HashMap<Integer, ArrayList<Double>>();
		int numOfTrueObjPerCentroid = numberOfTrueObject / m_numberOfCentroids;
		int numOfFalseObjPerCentroid = numberOfFalseObject / m_numberOfCentroids;
		ArrayList<Integer> centroidEdgeIds = new ArrayList<Integer>();
		for (int i = 0; i < m_numberOfCentroids; i++) {

			// zcreateCentroidDistribution(graph, objCounter, acceptedDistancesOnEdge,
			// centroidEdgeIds,
			// numOfTrueObjPerCentroid, true);
			objCounter = zcreateCentroidDistribution(graph, objCounter, standardDeviationValue, scaleFactor,
					acceptedDistancesOnEdge, centroidEdgeIds, numOfTrueObjPerCentroid, true);
			// zcreateCentroidDistribution(graph, objCounter, acceptedDistancesOnEdge,
			// centroidEdgeIds,
			// numOfFalseObjPerCentroid, false);
			objCounter = zcreateCentroidDistribution(graph, objCounter, standardDeviationValue, scaleFactor,
					acceptedDistancesOnEdge, centroidEdgeIds, numOfFalseObjPerCentroid, false);
		}

	}

	public static void zgenerateUUDistribution(Graph graph, int numberOfTrueObject, int numberOfFalseObject) {

		// <C,U> : < Query/True - U, Data/False - U >
		graph.removeObjectsOnEdges();
		int objCounter = 1;
		Map<Integer, ArrayList<Double>> acceptedDistancesOnEdge = new HashMap<Integer, ArrayList<Double>>();

		ArrayList<Integer> centroidEdgeIds = new ArrayList<Integer>();

		zcreateUniformDistribution(graph, objCounter, acceptedDistancesOnEdge, centroidEdgeIds, numberOfTrueObject,
				true);
		zcreateUniformDistribution(graph, objCounter, acceptedDistancesOnEdge, centroidEdgeIds, numberOfFalseObject,
				false);
	}

	// public int getDistinctRandomEdgeId ()
	// {
	// while (!isAcceptableEdgeId) {
	// if (randomlyChosenEdgeIds.length == 0) {
	// edgeId = (rand.nextInt(totalNumberOfEdges - 1) + 1) + 1;
	// isAcceptableEdgeId = true;
	// } else {
	// edgeId = (rand.nextInt(totalNumberOfEdges - 1) + 1) + 1;
	// for (int a = 0; a < randomlyChosenEdgeIds.length; a++) {
	// if (randomlyChosenEdgeIds[a] == edgeId) {
	// randomlyChosenEdgeIds[a] = edgeId;
	// isAcceptableEdgeId = true;
	// }
	// }
	// }
	// }
	// }
	///////////////////////////////////////////////////// End of
	// Archive//////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}