
package algorithm;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import framework.Edge;
import framework.Graph2;
import framework.Poi;

public class RandomObjectGenerator {

	private int m_totalNumberofEdge;
	private int m_totalNumerofNode;
	private int m_totalNumberofObject, m_totalNumberofDataObject, m_intTotalNumberofQueryObject,
			m_maxNumberofObjectPerEdge;
	private double m_minDistanceBetweenNodeandObject, m_minDistanceBetweenObject;

	// for generateRandomObjectsOnMap2()
	private static int minNumberOfPoisPerEdge = 3;
	private static double minDistanceBetPois = 3;

	// not complete yet
	// Object Type: false = data object ; true = query object;
	private static boolean uniformObjects = false;
	private static boolean uniformDataObjects = false;
	private static boolean uniformQueryObjects = false;
	private static int maxNumberOfPoisPerEdge;
	private static double maxDistBetNodeAndObject;

	public static void generateRandomObjectsOnMap2(Graph2 graph2) {
		
		
		int edgeId = 0;
		double distanceFromStartNode = 0.0;
		double edgeLength;
		int totalNumberOfEdges = graph2.getNumberOfEdges();

		// int totalNumberOfPois = (int) (graph2.getTotalLengthOfAllEdges() /
		// (minDistanceBetPois) - 1);

		Random rand = new Random();

		// int randomNumberOfEdges = rand.nextInt(totalNumberOfEdges);
		int randomNumberOfEdges = getRandIntBetRange(2, totalNumberOfEdges);
		System.out.println("randomNumberOfEdges: " + randomNumberOfEdges);
		int randomNumberOfPoisOnEdge;
		// Poi randPoi;
		Boolean isAcceptableDistance = false;
		Boolean isThereDistanceConflict = false;
		Boolean isAcceptableEdgeId = false;

		ArrayList<Integer> randomlyChosenEdgeIds = new ArrayList<Integer>();
		ArrayList<Double> randomDistances = new ArrayList<Double>();
		printGeneratorParameters();

		// i - edge
		for (int i = 0; i < randomNumberOfEdges; i++) {

			while (!isAcceptableEdgeId) {
				edgeId = (rand.nextInt(totalNumberOfEdges - 1) + 1) + 1;
				if (!randomlyChosenEdgeIds.contains(edgeId))
					isAcceptableEdgeId = true;
				randomlyChosenEdgeIds.add(edgeId);
			}
			isAcceptableEdgeId = false;
			// System.out.println("edgeId: " + edgeId);
			edgeLength = graph2.getEdgeDistance(edgeId);
			// System.out.println("edgeLength: " + edgeLength);
			// randomNumberOfPoisOnEdge = rand.nextInt(((int) (edgeLength /
			// minDistanceBetPois)) - 1);
			maxNumberOfPoisPerEdge = (int) (edgeLength / minDistanceBetPois - 1);
			// System.out.println("maxNumberOfPoisPerEdge: " + maxNumberOfPoisPerEdge);
			randomNumberOfPoisOnEdge = getRandIntBetRange(minNumberOfPoisPerEdge, maxNumberOfPoisPerEdge);
			System.out.println("randomNumberOfPoisOnEdge # " + edgeId + ": " + randomNumberOfPoisOnEdge);

			randomDistances.clear();
			// j - poi
			for (int j = 0; j < randomNumberOfPoisOnEdge; j++) {
				Poi randPoi = new Poi();

				if (randomDistances.isEmpty()) {
					distanceFromStartNode = Math.round(getRandDoubleBetRange(1.0, edgeLength));
					// System.out.println("distanceFromStartNode: " + distanceFromStartNode);
					randPoi.setPoiId(edgeId * 10 + j);
					randPoi.setDistanceFromStartNode(distanceFromStartNode);
					randomDistances.add(distanceFromStartNode);
					isAcceptableDistance = false;
					isThereDistanceConflict = false;
					// System.out.println("randomDistances2 (0): " + randomDistances2.get(0));
				} else {
					while (!isAcceptableDistance) {
						distanceFromStartNode = Math.round(getRandDoubleBetRange(1, edgeLength));
						isThereDistanceConflict = false;
						for (int k = 0; k < randomDistances.size(); k++) {
							// System.out.println("randomDistances2 (" + k + "): " +
							// randomDistances2.get(k));
							if (!((randomDistances.get(k) + minDistanceBetPois <= distanceFromStartNode)
									|| (randomDistances.get(k) - minDistanceBetPois >= distanceFromStartNode))) {
								isThereDistanceConflict = true;
							}
						}
						if (!isThereDistanceConflict) {
							isAcceptableDistance = true;
						}

					}
					randPoi.setPoiId(edgeId * 10 + j);
					randPoi.setDistanceFromStartNode(distanceFromStartNode);
					// System.out.println("distanceFromStartNode: " + distanceFromStartNode);
					randomDistances.add(distanceFromStartNode);
				}

				randPoi.setType(rand.nextBoolean());
				graph2.addObjectOnEdge3(edgeId, randPoi);
				// System.out.println("edgeId: " + edgeId + "; " + "randPoi" + randPoi);
				isAcceptableDistance = false;
				isThereDistanceConflict = false;
			}

		}

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
		System.out.println("minNumberOfPoisPerEdge: " + minNumberOfPoisPerEdge);
		System.out.println("minDistanceBetPois: " + minDistanceBetPois);
		// System.out.println("maxNumberOfPoisPerEdge: " + maxNumberOfPoisPerEdge );
		// System.out.println("uniformObjects: " + uniformObjects);
		// System.out.println("uniformDataObjects: " + uniformDataObjects);
		// System.out.println("uniformQueryObjects: " + uniformQueryObjects); s
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
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////// Archive//////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
