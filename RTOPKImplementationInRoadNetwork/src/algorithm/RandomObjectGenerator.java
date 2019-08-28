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

	public void generateRandomObjectsOnMap(Graph2 graph2) {

		boolean uniformDataObjects = false;
		boolean uniformQueryObjects = false;
		// Object Type: false = data object ; true = query object;
		/////////////////////////////////////

		boolean uniformObjects = false;

		int edgeId;
		int poiId;
		double distanceFromStartNode;
		boolean objectType;
		double edgeLength;
		int totalNumberOfEdges = graph2.getNumberOfEdges();

		int objectsPerEdge;
		double maxDistBetNodeAndObject;
		double minDistanceBetPois = 1;

		int totalNumberOfPois = (int) (graph2.getTotalLengthOfAllEdges() / (minDistanceBetPois) - 1);
		ArrayList<Poi> poiList = new ArrayList<Poi>();
		Poi listPoi[] = new Poi[totalNumberOfPois];
		ArrayList<Integer> usedPoiId = new ArrayList<Integer>();

		Random rand = new Random();
		for (int i = 0; i < listPoi.length; i++) {

			edgeId = rand.nextInt(totalNumberOfEdges);
			edgeLength = graph2.getEdgeDistance(edgeId);
			listPoi[i] = new Poi();
			listPoi[i].setPoiId(i * 10);
			// Prevent 0 distance
			distanceFromStartNode = Math.round(rand.nextDouble() * (edgeLength - 0.0001) * 100000.0) / 100000.0;

			listPoi[i].setDistanceFromStartNode(distanceFromStartNode);
			listPoi[i].setType(rand.nextBoolean());

			graph2.addObjectOnEdge3(edgeId, listPoi[i]);
		}

	}

	public void generateRandomObjectsOnMap2(Graph2 graph2) {

		boolean uniformDataObjects = false;
		boolean uniformQueryObjects = false;
		// Object Type: false = data object ; true = query object;
		/////////////////////////////////////
		boolean uniformObjects = false;

		int edgeId;
		double distanceFromStartNode = 0.0;
		double edgeLength;
		int totalNumberOfEdges = graph2.getNumberOfEdges();

		int minNumberOfPoisPerEdge = 3;
		int maxNumberOfPoisPerEdge;
		// double maxDistBetNodeAndObject;
		double minDistanceBetPois = 3;

		// int totalNumberOfPois = (int) (graph2.getTotalLengthOfAllEdges() /
		// (minDistanceBetPois) - 1);

		Random rand = new Random();

		// int randomNumberOfEdges = rand.nextInt(totalNumberOfEdges);
		int randomNumberOfEdges = getRandIntBetRange(2, totalNumberOfEdges);
		System.out.println("randomNumberOfEdges: " + randomNumberOfEdges);
		int randomNumberOfPoisOnEdge;
		Poi randPoi;
		Boolean isAcceptableDistance = false;
		Boolean isThereDistanceConflict = false;

		// i - edge
		for (int i = 0; i < randomNumberOfEdges; i++) {

			edgeId = (rand.nextInt(totalNumberOfEdges - 1) + 1) + 1;
			System.out.println("edgeId: " + edgeId);
			edgeLength = graph2.getEdgeDistance(edgeId);
			System.out.println("edgeLength: " + edgeLength);
//			randomNumberOfPoisOnEdge = rand.nextInt(((int) (edgeLength / minDistanceBetPois)) - 1);
			maxNumberOfPoisPerEdge = (int) (edgeLength / minDistanceBetPois - 1);
			System.out.println("maxNumberOfPoisPerEdge: " + maxNumberOfPoisPerEdge);
			randomNumberOfPoisOnEdge = getRandIntBetRange(minNumberOfPoisPerEdge, maxNumberOfPoisPerEdge);
			System.out.println("randomNumberOfPoisOnEdge: " + randomNumberOfPoisOnEdge);

			// double randomDistances[] = new double[randomNumberOfPoisOnEdge];
			ArrayList<Double> randomDistances2 = new ArrayList<Double>();

			// j - poi
			for (int j = 0; j < randomNumberOfPoisOnEdge; j++) {
				randPoi = new Poi();

				randPoi.setPoiId(j * 10);
				if (randomDistances2.isEmpty()) {
					distanceFromStartNode = Math.round(getRandDoubleBetRange(1.0, edgeLength));
					System.out.println("distanceFromStartNode: " + distanceFromStartNode);
					randPoi.setDistanceFromStartNode(distanceFromStartNode);
					randomDistances2.add(distanceFromStartNode);
					isAcceptableDistance = false;
					isThereDistanceConflict = false;
					//System.out.println("randomDistances2 (0): " + randomDistances2.get(0));
				} else {
					while (!isAcceptableDistance) {
						distanceFromStartNode = Math.round(getRandDoubleBetRange(1, edgeLength));
						for (int k = 0; k < randomDistances2.size(); k++) {
							//System.out.println("randomDistances2 (" + k + "): " + randomDistances2.get(k));
							if (!((randomDistances2.get(k) + minDistanceBetPois <= distanceFromStartNode)
									|| (randomDistances2.get(k) - minDistanceBetPois >= distanceFromStartNode))) {
								isThereDistanceConflict = true;
							}
						}
						if (!isThereDistanceConflict) {
							isAcceptableDistance = true;
						}

					}
					randPoi.setDistanceFromStartNode(distanceFromStartNode);
					System.out.println("distanceFromStartNode: " + distanceFromStartNode);
					randomDistances2.add(distanceFromStartNode);
				}

				randPoi.setType(rand.nextBoolean());
				graph2.addObjectOnEdge3(edgeId, randPoi);
				System.out.println("edgeId: " + edgeId + "; " + "randPoi" + randPoi);
				isAcceptableDistance = false;
				isThereDistanceConflict = false;
			}

		}

	}
	
	public void generateRandomObjectsOnMap3(Graph2 graph2) {

		boolean uniformDataObjects = false;
		boolean uniformQueryObjects = false;
		// Object Type: false = data object ; true = query object;
		/////////////////////////////////////
		boolean uniformObjects = false;

		int edgeId;
		double distanceFromStartNode = 0.0;
		double edgeLength;
		int totalNumberOfEdges = graph2.getNumberOfEdges();

		int minNumberOfPoisPerEdge = 3;
		int maxNumberOfPoisPerEdge;
		// double maxDistBetNodeAndObject;
		double minDistanceBetPois = 3;

		// int totalNumberOfPois = (int) (graph2.getTotalLengthOfAllEdges() /
		// (minDistanceBetPois) - 1);

		Random rand = new Random();

		// int randomNumberOfEdges = rand.nextInt(totalNumberOfEdges);
		int randomNumberOfEdges = getRandIntBetRange(2, totalNumberOfEdges);
		System.out.println("randomNumberOfEdges: " + randomNumberOfEdges);
		int randomNumberOfPoisOnEdge;
		Poi randPoi;
		Boolean isAcceptableDistance = false;
		Boolean isThereDistanceConflict = false;

		// i - edge
		for (int i = 0; i < randomNumberOfEdges; i++) {

			edgeId = (rand.nextInt(totalNumberOfEdges - 1) + 1) + 1;
			System.out.println("edgeId: " + edgeId);
			edgeLength = graph2.getEdgeDistance(edgeId);
			System.out.println("edgeLength: " + edgeLength);
//			randomNumberOfPoisOnEdge = rand.nextInt(((int) (edgeLength / minDistanceBetPois)) - 1);
			maxNumberOfPoisPerEdge = (int) (edgeLength / minDistanceBetPois - 1);
			System.out.println("maxNumberOfPoisPerEdge: " + maxNumberOfPoisPerEdge);
			randomNumberOfPoisOnEdge = getRandIntBetRange(minNumberOfPoisPerEdge, maxNumberOfPoisPerEdge);
			System.out.println("randomNumberOfPoisOnEdge: " + randomNumberOfPoisOnEdge);

			// double randomDistances[] = new double[randomNumberOfPoisOnEdge];
			ArrayList<Double> randomDistances2 = new ArrayList<Double>();

			// j - poi
			for (int j = 0; j < randomNumberOfPoisOnEdge; j++) {
				randPoi = new Poi();

				randPoi.setPoiId(j * 10);
				if (randomDistances2.isEmpty()) {
					distanceFromStartNode = Math.round(getRandDoubleBetRange(1.0, edgeLength));
					System.out.println("distanceFromStartNode: " + distanceFromStartNode);
					randPoi.setDistanceFromStartNode(distanceFromStartNode);
					randomDistances2.add(distanceFromStartNode);
					isAcceptableDistance = false;
					isThereDistanceConflict = false;
					//System.out.println("randomDistances2 (0): " + randomDistances2.get(0));
				} else {
					while (!isAcceptableDistance) {
						distanceFromStartNode = Math.round(getRandDoubleBetRange(1, edgeLength));
						for (int k = 0; k < randomDistances2.size(); k++) {
							//System.out.println("randomDistances2 (" + k + "): " + randomDistances2.get(k));
							if (!((randomDistances2.get(k) + minDistanceBetPois <= distanceFromStartNode)
									|| (randomDistances2.get(k) - minDistanceBetPois >= distanceFromStartNode))) {
								isThereDistanceConflict = true;
							}
						}
						if (!isThereDistanceConflict) {
							isAcceptableDistance = true;
						}

					}
					randPoi.setDistanceFromStartNode(distanceFromStartNode);
					System.out.println("distanceFromStartNode: " + distanceFromStartNode);
					randomDistances2.add(distanceFromStartNode);
				}

				randPoi.setType(rand.nextBoolean());
				graph2.addObjectOnEdge3(edgeId, randPoi);
				System.out.println("edgeId: " + edgeId + "; " + "randPoi" + randPoi);
				isAcceptableDistance = false;
				isThereDistanceConflict = false;
			}

		}

	}

	public int getRandIntBetRange(double min, double max) {
		int x = (int) ((Math.random() * ((max - min) + 1)) + min);
		return x;
	}

	public double getRandDoubleBetRange(double min, double max) {
		double x = Math.round((ThreadLocalRandom.current().nextDouble(min, max)) * 100.0) / 100.0;
		return x;
	}
}
