package algorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

//import com.google.common.collect.MinMaxPriorityQueue;

import framework.Edge;
import framework.Graph;
import framework.RoadObject;

public class RandomObjectGenerator2 {

	// not complete yet
	// Object Type: false = data object ; true = query object;
	private static boolean uniformObjects = false;
	private static boolean uniformDataObjects = false;
	private static boolean uniformQueryObjects = false;
	private static int maxNumberOfObjsPerEdge;
	private static double maxDistBetNodeAndObject;

	// For Statistics
	private static int m_totalNumberOfObjects;
	private static int m_totalNumberOfTrueObjects;
	private static int m_totalNumberOfFalseObjects;
	private static int m_totalNumberOfEdges;
	private static int m_totalNumberOfEdgesContainingObjects;
	private static double m_totalLengthOfEdges;

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
		int edgeCounter = 0;
		for (Integer keyEdgeId : mapOfEdgeObjectNumber.keySet()) {
			edgeCounter++;
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

	public static void generateRandomObjectsOnMap6(Graph graph, int totalNumberOfTrueObjects,
			int totalNumberOfFalseObjects) {
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
		int totalNumOfObjects = totalNumberOfTrueObjects + totalNumberOfFalseObjects;
		Random rand = new Random();
		for (Edge edge : graph.getEdgesWithInfo()) {
			if (edge.getLength() < minEdgeLength) {
				minEdgeLength = edge.getLength();
			}
		}

		// minDistBetweenObjects = Math.round((graph.getTotalLengthOfAllEdges() /
		// totalNumOfObjects) * 1000000000000000.0)
		// / 1000000000000000.0;
		minDistBetweenObjects = Math
				.round((graph.getTotalLengthOfAllEdges() / getTotalObjParam(graph.getDatasetName(), totalNumOfObjects))
						* 1000000000000000.0)
				/ 1000000000000000.0;
		System.out.println("Min Dist between objects: " + minDistBetweenObjects);
		System.out.println("Min edgeLength: " + minEdgeLength);
		System.out.println("Total Length of all edges: " + graph.getTotalLengthOfAllEdges());
		int whileLoopCounter = 0;
		Map<Integer, ArrayList<Double>> acceptedDistancesOnEdge = new HashMap<Integer, ArrayList<Double>>();

		LinkedList<Boolean> boolValues = new LinkedList<Boolean>();
		for (int i = 0; i < totalNumberOfTrueObjects; i++) {
			boolValues.add(true);
		}
		for (int i = 0; i < totalNumberOfFalseObjects; i++) {
			boolValues.add(false);
		}
		Collections.shuffle(boolValues);

		totalNumOfObjects -= 1;
		while (objCounter < totalNumOfObjects) {

			if (objCounter > totalNumOfObjects) {
				continue;
			}
			whileLoopCounter++;
			System.err.println("Loop #: " + whileLoopCounter);
			// if (whileLoopCounter == 7) {
			// // System.out.println("Last loop");
			// }
			// System.out.println("While loop Count: " + whileLoopCounter);
			boolean isAcceptableDistance = false;
			boolean isThereDistanceConflict = false;
			// boolean isAcceptableEdgeId = false;

			// ArrayList<Integer> randomlyChosenEdgeIds = new ArrayList<Integer>();
			// ArrayList<Double> randomDistances = new ArrayList<Double>();

			// printGeneratorPara"meters();
			// i - edge
			int edgeCounter = 0;
			boolean testVar;
			for (Edge edge : graph.getEdgesWithInfo()) {
				if (objCounter > totalNumOfObjects) {
					continue;
				}
				// edgeCounter++;
				// System.out.println("Finished Generating on: " + edgeCounter);
				// System.out.println("Edge Id: " + edge.getEdgeId());
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

						distanceFromStartNode = getRandDoubleBetRange2(minEdgeLength, edgeLength);
						randObj.setDistanceFromStartNode(distanceFromStartNode);
						acceptedDistancesOnEdge.get(edge.getEdgeId()).add(distanceFromStartNode);
						checkedRandomDistances.add(distanceFromStartNode);

						isAcceptableDistance = false;
						isThereDistanceConflict = false;

					} else {
						while (!isAcceptableDistance) {

							distanceFromStartNode = getRandDoubleBetRange2(minEdgeLength, edgeLength);
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
								// checkedRandomDistances.add(distanceFromStartNode);
								isAcceptableDistance = true;
							}

						}

					}
					randObj.setDistanceFromStartNode(distanceFromStartNode);
					acceptedDistancesOnEdge.get(edge.getEdgeId()).add(distanceFromStartNode);
					randObj.setObjId(objCounter);
					// randObj.setType(rand.nextBoolean());
					// randObj.setType(Math.random() < probOFTrueObjs);
					if (totalNumOfObjects == boolValues.size()) {
						System.err.println("equal size");
					}

					if (boolValues == null) {
						System.err.println("bool values is  null");
					}

					if (!boolValues.isEmpty()) {
						testVar = boolValues.poll();
						randObj.setType(testVar);
					}

					if (graph.addObjectOnEdge(edge.getEdgeId(), randObj)) {
						objCounter++;

						System.out.println(objCounter + " Object Added");
					}
					// System.out.println("False Trial");
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
			// System.out.println("While loop Count: " + whileLoopCounter + ", size of
			// acceptedDistancesOnEdge: "
			// + acceptedDistancesOnEdge.size() + ", objects: " + objCounter);
			// if (graph.getTotalNumberOfObjects() == 20001) {
			// System.out.println("success");
			// }
		}

		m_totalNumberOfObjects = graph.getTotalNumberOfObjects();
		m_totalNumberOfTrueObjects = graph.getTotalNumberOfTrueObjects();
		m_totalNumberOfFalseObjects = graph.getTotalNumberOfFalseObjects();
		m_totalNumberOfEdges = totalNumberOfEdges;
		m_totalNumberOfEdgesContainingObjects = graph.getObjectsOnEdges().size();
		m_totalLengthOfEdges = graph.getTotalLengthOfAllEdges();

		System.out.println("size of acceptedDistancesOnEdge: " + acceptedDistancesOnEdge.size());

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

		return -1;
	}

	public static int getRandIntBetRange(double min, double max) {
		int x = (int) ((Math.random() * ((max - min) + 1)) + min);
		return x;
	}

	public static double getRandDoubleBetRange(double min, double max) {
		double x;
		x = ThreadLocalRandom.current().nextDouble(min, max);
		x = Math.round(x * 1000000000000000.0) / 1000000000000000.0;
		return x;
	}

	public static double getRandDoubleBetRange2(double min, double max) {
		double x;
		x = (Math.random() * ((max - min) + 1)) + min;
		x = Math.round(x * 1000000000000000.0) / 1000000000000000.0;
		return x;
	}

	public static void printGeneratorParameters() {
		System.out.println("--------------------------------------------------");
		System.out.println("Generator's Configurable Parameters: ");
		// System.out.println("minNumberOfObjsPerEdge: " + minNumberOfObjsPerEdge);
		// System.out.println("minDistanceBetObjs: " + minDistanceBetObjs);
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
		System.out.println("Total lenght of all edges: " + m_totalLengthOfEdges);

		System.out.println("--------------------------------------------------");

	}

	public static int getTotalNumberOfRandomEdges() {
		int randomNumberOfEdges = m_totalNumberOfEdges;
		return randomNumberOfEdges;
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