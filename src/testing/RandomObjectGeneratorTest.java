package testing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import algorithm.RandomObjectGenerator;

//import com.google.common.collect.MinMaxPriorityQueue;

import framework.Edge;
import framework.Graph;
import framework.Node;
import framework.RoadObject;
import framework.UtilsManagment;

public class RandomObjectGeneratorTest {

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

	public static void generateRandomObjectsOnMap7(Graph graph, int totalNumberOfTrueObjects,
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
		int actualNumberOfTotalObjects = 0;
		//Random rand = new Random();
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
		//Map<Integer, ArrayList<Double>> acceptedDistancesOnEdge = new HashMap<Integer, ArrayList<Double>>();

		//LinkedList<Boolean> boolValues = new LinkedList<Boolean>();
//		for (int i = 0; i < totalNumberOfTrueObjects; i++) {
//			boolValues.add(true);
//		}
//		for (int i = 0; i < totalNumberOfFalseObjects; i++) {
//			boolValues.add(false);
//		}
//		Collections.shuffle(boolValues);

		while (objCounter < totalNumOfObjects) {

			if (objCounter >= totalNumOfObjects) {
				break;
			}
			whileLoopCounter++;
			System.err.println("While Loop # " + whileLoopCounter);

			//boolean isAcceptableDistance = false;
			//boolean isThereDistanceConflict = false;
			// boolean isAcceptableEdgeId = false;

			// ArrayList<Integer> randomlyChosenEdgeIds = new ArrayList<Integer>();
			// ArrayList<Double> randomDistances = new ArrayList<Double>();

			// printGeneratorPara"meters();
			// i - edge
			// int edgeCounter = 0;
			//boolean testVar;
			for (Edge edge : graph.getEdgesWithInfo()) {
				if (objCounter > totalNumOfObjects) {
					break;
				}
				// edgeCounter++;
				// System.out.println("Finished Generating on: " + edgeCounter + " out of "
				// +mapOfEdgeObjectNumber.size());
				//ArrayList<Double> checkedRandomDistances = new ArrayList<Double>();

				//checkedRandomDistances.clear();

				edgeLength = graph.getEdgeDistance(edge.getEdgeId());
				maxNumberOfObjectsPerEdge = (int) (edgeLength / minDistBetweenObjects - 1);
				if (maxNumberOfObjectsPerEdge < 1)
					continue;
				//ArrayList<Double> acceptedDistances;

//				if (!acceptedDistancesOnEdge.containsKey(edge.getEdgeId())) {
//					acceptedDistances = new ArrayList<Double>();
//					acceptedDistancesOnEdge.put(edge.getEdgeId(), acceptedDistances);
//				}

				// System.out.println("max obj per edge: " + maxNumberOfObjectsPerEdge);
				for (int j = 0; j < maxNumberOfObjectsPerEdge - 1; j++) {

					if (objCounter >= totalNumOfObjects) {
						break;
					}
					objCounter++;
					actualNumberOfTotalObjects ++;
					System.out.println(actualNumberOfTotalObjects);
					//RoadObject randObj = new RoadObject();

//					if (acceptedDistancesOnEdge.get(edge.getEdgeId()).isEmpty()) {
//
//						distanceFromStartNode = getRandDoubleBetRange2(minEdgeLength, edgeLength);
//						randObj.setDistanceFromStartNode(distanceFromStartNode);
//						acceptedDistancesOnEdge.get(edge.getEdgeId()).add(distanceFromStartNode);
//						checkedRandomDistances.add(distanceFromStartNode);
//
//						isAcceptableDistance = false;
//						isThereDistanceConflict = false;
//
//					} else {
//						while (!isAcceptableDistance) {
//
//							distanceFromStartNode = getRandDoubleBetRange2(minEdgeLength, edgeLength);
//							if (checkedRandomDistances.contains(distanceFromStartNode)) {
//								continue;
//							}
//
//							checkedRandomDistances.add(distanceFromStartNode);
//							for (int k = 0; k < acceptedDistancesOnEdge.get(edge.getEdgeId()).size(); k++) {
//
//								isThereDistanceConflict = false;
//								if (!((acceptedDistancesOnEdge.get(edge.getEdgeId()).get(k)
//										+ minDistBetweenObjects <= distanceFromStartNode)
//										|| (acceptedDistancesOnEdge.get(edge.getEdgeId()).get(k)
//												- minDistBetweenObjects >= distanceFromStartNode))) {
//									isThereDistanceConflict = true;
//									break;
//								}
//
//							}
//							if (!isThereDistanceConflict) {
//								// System.out.println("The distance can be used");
//								isAcceptableDistance = true;
//							}
//
//						}
//
//					}
//					randObj.setDistanceFromStartNode(distanceFromStartNode);
//					acceptedDistancesOnEdge.get(edge.getEdgeId()).add(distanceFromStartNode);
//					randObj.setObjId(objCounter);
//					// randObj.setType(rand.nextBoolean());
//					// randObj.setType(Math.random() < probOFTrueObjs);
//					if (totalNumOfObjects == boolValues.size()) {
//						System.err.println("equal size");
//					}
//
//					if (boolValues != null || !boolValues.isEmpty()) {
//						testVar = boolValues.poll();
//						randObj.setType(testVar);
//					}
//
//					if (graph.addObjectOnEdge(edge.getEdgeId(), randObj)) {
//						objCounter++;
//
//						System.out.println(objCounter + " Object Added, size of boolValues: " + boolValues.size()
//								+ ", totalNumOfObjects: " + totalNumOfObjects);
//						System.out.println("Size of acceptedDistancesOnEdge: " + acceptedDistancesOnEdge.size()
//								+ " , accepted objects on edge: "
//								+ acceptedDistancesOnEdge.get(edge.getEdgeId()).size());
//
//					}
//					// System.out.println("False Trial");
//					isAcceptableDistance = false;
//					isThereDistanceConflict = false;
//						System.out.println("Chosen number of edges to generate on: " + randomNumberOfEdges);
//						System.out.println("Current edge: " + keyEdgeId + ", length: " + edgeLength);
//						// System.out.println("Max num of obj on edge: " + maxNumberOfObjectsPerEdge /
//						// 2);
//						// System.out.println("Chosen number of object per edge: " +
//						// randomNumberOfObjsOnEdge);
//						System.out.println("Num of Objects generated: " + totalNumOfGenObjCounter);

				} // END OF OBJECTS ON EDGE FOR LOOP
			} // END OF EDGE FOR LOOP
//			System.out.println("While loop Count: " + whileLoopCounter + ", size of acceptedDistancesOnEdge: "
//					+ acceptedDistancesOnEdge.size() + ", objects: " + objCounter);
			
		} // END OF WHILE LOOP

		m_totalNumberOfObjects = graph.getTotalNumberOfObjects();
		m_totalNumberOfTrueObjects = graph.getTotalNumberOfTrueObjects();
		m_totalNumberOfFalseObjects = graph.getTotalNumberOfFalseObjects();
		m_totalNumberOfEdges = totalNumberOfEdges;
		m_totalNumberOfEdgesContainingObjects = graph.getObjectsOnEdges().size();
		m_totalLengthOfEdges = graph.getTotalLengthOfAllEdges();

		System.out.println("Actual number of objects: " + actualNumberOfTotalObjects);
	}

	public static int getRandIntBetRange(double min, double max) {
		int x = (int) ((Math.random() * ((max - min) + 1)) + min);
		return x;
	}

	public static double getRandDoubleBetRange(double min, double max) {
		double x;
		x = ThreadLocalRandom.current().nextDouble(min, max);
		x = Math.round(x * 100000000.0) / 100000000.0;
		return x;
	}

	public static double getRandDoubleBetRange2(double min, double max) {
		double x;
		x = (Math.random() * ((max - min) + 1)) + min;
		x = Math.round(x * 100000000.0) / 100000000.0;
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

	public static void main(String[] args) {
		UtilsManagment um = new UtilsManagment();
		Graph calGraph = new Graph();

		String nodeDatasetFile = "Datasets/CAL-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/CAL-Edge_Eid-ESrc-EDest-EDist.csv";

		calGraph = um.readEdgeFileReturnGraph(edgeDatasetFile);
		ArrayList<Node> calNodesInfo = um.readNodeFile(nodeDatasetFile);
		calGraph.setNodesWithInfo(calNodesInfo);
		ArrayList<Edge> calEdgeInfo = um.readEdgeFile(edgeDatasetFile);
		calGraph.setEdgeWithInfo(calEdgeInfo);

		// calGraph.printGraph();

		// RandomObjectGenerator.generateRandomObjectsOnMap2(calGraph, 0.1);
		// RandomObjectGenerator.generateRandomObjectsOnMap5(calGraph, 0.259, 27000);
		System.out.println("Start to generate objects");
		generateRandomObjectsOnMap7(calGraph, 7000, 20000);
		System.out.println("Finished Generating");
		

	}

}
