
package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

//import com.google.common.collect.MinMaxPriorityQueue;

import framework.Edge;
import framework.Graph;
import framework.RoadObject;

public class RandomObjectGenerator {

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
	private static double m_totalLengthOfEdges;

	public static void generateRandomObjectsOnMap(Graph graph, double probOFTrueObjs) {

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
//				System.out.println("Chosen number of edges to generate on: " + randomNumberOfEdges);
//				System.out.println("Current edge: " + keyEdgeId + ", length: " + edgeLength);
//				// System.out.println("Max num of obj on edge: " + maxNumberOfObjectsPerEdge /
//				// 2);
//				// System.out.println("Chosen number of object per edge: " +
//				// randomNumberOfObjsOnEdge);
//				System.out.println("Num of Objects generated: " + totalNumOfGenObjCounter);

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
	
	public static void generateRandomObjectsOnMap(Graph graph) {

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

			// System.out.println("edgeLength: " + edgeLength);
			maxNumberOfObjectsPerEdge = (int) (edgeLength / minDistBetweenObjects - 1);
			// System.out.println("maxNumberOfObjsPerEdge: " + maxNumberOfObjsPerEdge);
			// randomNumberOfObjsOnEdge = getRandIntBetRange(minNumOfObjectsPerEdge,
			// maxNumberOfObjsPerEdge);
			// randomNumberOfObjsOnEdge = getRandIntBetRange(maxNumberOfObjsPerEdge / 5,
			// maxNumberOfObjsPerEdge);
			randomNumberOfObjsOnEdge = getRandIntBetRange(maxNumberOfObjectsPerEdge / 5, maxNumberOfObjectsPerEdge / 2);
			// randomNumberOfObjsOnEdge = getRandIntBetRange(1, 2);

			// System.out.println("randomNumberOfObjsOnEdge # " + edgeId + ": " +
			// randomNumberOfObjsOnEdge);
			ArrayList<Double> checkedRandomDistances = new ArrayList<Double>();
			randomDistances.clear();
			checkedRandomDistances.clear();
			// System.out.println("Number of edges completed: " + genEdgeCounter);
			// j - obj
			int genObjCounter = 0;

			for (int j = 0; j < randomNumberOfObjsOnEdge; j++) {
				RoadObject randObj = new RoadObject();

				// System.out.println("First For Loop");
				if (randomDistances.isEmpty()) {
					// System.out.println("randomDistances is empty");
					distanceFromStartNode = getRandDoubleBetRange(minEdgeLength, edgeLength);
					randObj.setDistanceFromStartNode(distanceFromStartNode);
					randomDistances.add(distanceFromStartNode);

					checkedRandomDistances.add(distanceFromStartNode);

					isAcceptableDistance = false;
					isThereDistanceConflict = false;
					genObjCounter++;
				} else {
					while (!isAcceptableDistance) {
						// System.out.println("First While Loop");

						distanceFromStartNode = getRandDoubleBetRange(minEdgeLength, edgeLength);
						if (checkedRandomDistances.contains(distanceFromStartNode)) {
							isAcceptableDistance = false;
							continue;
						}
						// isThereDistanceConflict = false;
						checkedRandomDistances.add(distanceFromStartNode);
						for (int k = 0; k < randomDistances.size(); k++) {
							// System.out.println("SEcond For Loop");
							isThereDistanceConflict = false;
							if (!((randomDistances.get(k) + minDistBetweenObjects <= distanceFromStartNode)
									|| (randomDistances.get(k) - minDistBetweenObjects >= distanceFromStartNode))) {
								isThereDistanceConflict = true;
								// System.err.println("There is a distance conflict, Current dist: "+
								// distanceFromStartNode);
								continue;
							}
//							System.out.println("Current Edge Length: " + edgeLength);
//							System.out.println("Min Edge Length: " + minEdgeLength);
//							System.out.println("min dist betwn objects: " + minDistBetweenObjects);
//							System.out.println("Max num of obj on edge: " + maxNumberOfObjectsPerEdge2);
//							System.out.println("chosen number of object per edge: " + randomNumberOfObjsOnEdge);
//							System.out.println("Num of Objects generated: " + genObjCounter);
//							System.out.println("Current dist: "+distanceFromStartNode);
//							System.out.println("Size of randomDistances: " + randomDistances.size());
//							System.out.println("RandomDistances: " + randomDistances);
						}
						if (!isThereDistanceConflict) {
							isAcceptableDistance = true;
							genObjCounter++;
						}

					}
					randObj.setDistanceFromStartNode(distanceFromStartNode);
					randomDistances.add(distanceFromStartNode);
				}
				// randObj.setObjId(edgeId * m_objToEdgeId + j); // This is good if it is
				// certain that all edges will have max 9 objects
				objCounter++;
				randObj.setObjId(objCounter);
				randObj.setType(rand.nextBoolean());
				graph.addObjectOnEdge(edgeId, randObj);
				// System.out.println("edgeId: " + edgeId + "; " + "randObj: " + randObj);
				isAcceptableDistance = false;
				isThereDistanceConflict = false;
				System.out.println("Chosen number of edges to generate on: " + randomNumberOfEdges);
				System.out.println("Current edge: " + i + ", length: " + edgeLength);
				System.out.println("Max num of obj on edge: " + maxNumberOfObjectsPerEdge / 2);
				System.out.println("Chosen number of object per edge: " + randomNumberOfObjsOnEdge);
				System.out.println("Num of Objects generated: " + genObjCounter);
				// System.out.println("Num of tries: " + genObjCounterConfl);
			}

		}

		// For Statistics
		m_totalNumberOfObjects = graph.getTotalNumberOfObjects();
		m_totalNumberOfTrueObjects = graph.getTotalNumberOfTrueObjects();
		m_totalNumberOfFalseObjects = graph.getTotalNumberOfFalseObjects();
		m_totalNumberOfEdges = totalNumberOfEdges;
		m_totalLengthOfEdges = graph.getTotalLengthOfAllEdges();

	}

	public static int getRandIntBetRange(double min, double max) {
		int x = (int) ((Math.random() * ((max - min) + 1)) + min);
		return x;
	}

	public static double getRandDoubleBetRange(double min, double max) {
		double x;
		x = ThreadLocalRandom.current().nextDouble(min, max);
		x = Math.round(x * 100000.0) / 100000.0;
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
