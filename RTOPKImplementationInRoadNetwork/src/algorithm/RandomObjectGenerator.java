
package algorithm;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import framework.Edge;
import framework.Graph;
import framework.RoadObject;

public class RandomObjectGenerator {

//	private int m_totalNumberofEdges;
//	private int m_totalNumerofNodes;
//	private int m_totalNumberofObject, m_totalNumberofDataObject, m_intTotalNumberofQueryObject,
//			m_maxNumberofObjectPerEdge;
//	private double m_minDistanceBetweenNodeandObject, m_minDistanceBetweenObject;

	private static int m_objToEdgeId = 10; // this number helps to identify edge on which obj is located, sync it with
											// same variable in Graph class
	
	// for generateRandomObjectsOnMap()
	private static int minNumberOfObjsPerEdge = 4;
	private static double minDistanceBetObjs = 3;

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

	public static void generateRandomObjectsOnMap(Graph graph) {
		
		
		int edgeId = 0;
		int objCounter = 0;
		double distanceFromStartNode = 0.0;
		double edgeLength;
		int totalNumberOfEdges = graph.getNumberOfEdges();
		
		Random rand = new Random();
		int randomNumberOfEdges = 7;//getRandIntBetRange(6, totalNumberOfEdges);
		System.out.println("randomNumberOfEdges: " + randomNumberOfEdges);
		int randomNumberOfObjsOnEdge;
		Boolean isAcceptableDistance = false;
		Boolean isThereDistanceConflict = false;
		Boolean isAcceptableEdgeId = false;

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
			// System.out.println("edgeId: " + edgeId);
			edgeLength = graph.getEdgeDistance(edgeId);
			// System.out.println("edgeLength: " + edgeLength);
			maxNumberOfObjsPerEdge = (int) (edgeLength / minDistanceBetObjs - 1);
			// System.out.println("maxNumberOfObjsPerEdge: " + maxNumberOfObjsPerEdge);
			randomNumberOfObjsOnEdge = getRandIntBetRange(minNumberOfObjsPerEdge, maxNumberOfObjsPerEdge);
			System.out.println("randomNumberOfObjsOnEdge # " + edgeId + ": " + randomNumberOfObjsOnEdge);

			randomDistances.clear();
			// j - obj
			for (int j = 0; j < randomNumberOfObjsOnEdge; j++) {
				RoadObject randObj = new RoadObject();

				if (randomDistances.isEmpty()) {
					distanceFromStartNode = getRandDoubleBetRange(1.0, edgeLength);
					randObj.setDistanceFromStartNode(distanceFromStartNode);
					randomDistances.add(distanceFromStartNode);
					isAcceptableDistance = false;
					isThereDistanceConflict = false;
				} else {
					while (!isAcceptableDistance) {
						distanceFromStartNode = getRandDoubleBetRange(1, edgeLength);
						isThereDistanceConflict = false;
						for (int k = 0; k < randomDistances.size(); k++) {
							if (!((randomDistances.get(k) + minDistanceBetObjs <= distanceFromStartNode)
									|| (randomDistances.get(k) - minDistanceBetObjs >= distanceFromStartNode))) {
								isThereDistanceConflict = true;
							}
						}
						if (!isThereDistanceConflict) {
							isAcceptableDistance = true;
						}

					}
					randObj.setDistanceFromStartNode(distanceFromStartNode);
					randomDistances.add(distanceFromStartNode);
				}
				//randObj.setObjId(edgeId * m_objToEdgeId + j); // This is good if it is certain that all edges will have max 9 objects
				objCounter++;
				randObj.setObjId(objCounter);
				randObj.setType(rand.nextBoolean());
				graph.addObjectOnEdge(edgeId, randObj);
				// System.out.println("edgeId: " + edgeId + "; " + "randObj: " + randObj);
				isAcceptableDistance = false;
				isThereDistanceConflict = false;
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
		double x = Math.round((ThreadLocalRandom.current().nextDouble(min, max)) * 100.0) / 100.0;
		return x;
	}

	public static void printGeneratorParameters() {
		System.out.println("--------------------------------------------------");
		System.out.println("Generator's Configurable Parameters: ");
		System.out.println("minNumberOfObjsPerEdge: " + minNumberOfObjsPerEdge);
		System.out.println("minDistanceBetObjs: " + minDistanceBetObjs);
		// System.out.println("maxNumberOfObjsPerEdge: " + maxNumberOfObjsPerEdge );
		// System.out.println("uniformObjects: " + uniformObjects);
		// System.out.println("uniformDataObjects: " + uniformDataObjects);
		// System.out.println("uniformQueryObjects: " + uniformQueryObjects); 
		System.out.println("--------------------------------------------------");
	}
	
	public static void printStatistics() {
		double trueObjectsPer, falseObjectsPer;
		trueObjectsPer = Math.round((double)m_totalNumberOfTrueObjects/m_totalNumberOfObjects*100*100.0)/100.0;
		falseObjectsPer = Math.round((double)m_totalNumberOfFalseObjects/m_totalNumberOfObjects*100*100.0)/100.0;
		System.out.println("--------------------------------------------------");
		System.out.println("Total number of Objects: " + m_totalNumberOfObjects);
		System.out.println("Total number of True Objects: " + m_totalNumberOfTrueObjects + ", " + trueObjectsPer + " %");
		System.out.println("Total number of False Objects: " + m_totalNumberOfFalseObjects + ", " + falseObjectsPer + " %");
		System.out.println("Total number of Edges: " + m_totalNumberOfEdges);
		System.out.println("Total lenght of all edges: " + m_totalLengthOfEdges);
		
		System.out.println("--------------------------------------------------");

		
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
	/////////////////////////////////////////////////////End of Archive//////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
