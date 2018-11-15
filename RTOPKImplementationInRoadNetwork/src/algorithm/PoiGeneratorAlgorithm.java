package algorithm;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import framework.Edge;
import framework.Graph;

public class PoiGeneratorAlgorithm {
	private ArrayList<Integer> listUsedStartNodeId = new ArrayList<Integer>();
	private ArrayList<Integer> listUsedEndNodeId = new ArrayList<Integer>();

	private ArrayList<Edge> listUsedEdges = new ArrayList<Edge>();

	private char separator = ',';

	// Final Generated File Format:
	// PoiId, StartNodeId, EndNodeId, DistFromStartNode, CategoryId, Type, Rating

	public double[][] generateRandomPois2(Graph g, int maxNumofPoiPerGraph, int maxNumofPoiPerEdge,
			double minDistanceBetPoiAndNode, double minDistanceBetPois) {

		// [1, totalNumberOfPois]
		int genPoiId = 0;

		int genStartNodeId, genEndNodeId;

		// [min, max]: min=0 (exclusive), max=length of Edge on which the poi is located
		double genDistFromStartNode;

		// {1, 2, 3}: Hotel-1, Restaurant-2, Cafe-3
		int genCategoryId;

		// {0, 1}
		int genType;

		// [0, 10]
		double genRating;

		int numOfEdge = g.getNumberOfEdges();
		int numOfNodes = g.getNumberOfNodes();

		int totalPossibleNumberOfPois;
		totalPossibleNumberOfPois = g.getNumberOfEdges() * maxNumofPoiPerEdge;

		if (maxNumofPoiPerGraph > totalPossibleNumberOfPois) {
			maxNumofPoiPerGraph = totalPossibleNumberOfPois;
		}

		int numberOfColumns = 7;
		double[][] genPois = new double[maxNumofPoiPerGraph][numberOfColumns];

		
		// Finding correct/possible random values for each column
		Random rand = new Random();

		genStartNodeId = rand.nextInt(numOfNodes);
		listUsedStartNodeId.add(genStartNodeId);

		int randNumberOfPoiOnCurrentEdge;
		randNumberOfPoiOnCurrentEdge = rand.nextInt(maxNumofPoiPerEdge + 1);

		ArrayList<Integer> listUsedRandomUsedEndNodeId = new ArrayList<Integer>();

		Set<Integer> setEndNodes = g.getAdjancencyMap().get(genStartNodeId).keySet();
		List<Integer> listEndNodes = new ArrayList<Integer>(setEndNodes);

		int randEndNodeId = 0;// = rand.nextInt(listEndNodes.size());
		int k = 1;
		while (listUsedRandomUsedEndNodeId.isEmpty() || !listUsedRandomUsedEndNodeId.contains(randEndNodeId)) {
			randEndNodeId = rand.nextInt(listEndNodes.size());
			listUsedRandomUsedEndNodeId.add(randEndNodeId);
			k++;
			if (randNumberOfPoiOnCurrentEdge == k) {
				break;
			}

			genEndNodeId = listEndNodes.get(randEndNodeId);
			listUsedEndNodeId.add(genEndNodeId);

		}

		FileWriter fw;
		try {
			fw = new FileWriter("generatedPoiDataset3.txt");

			for (int i = 0; i < genPois.length; i++) {
				for (int j = 0; j < genPois[i].length; j++) {
					genPoiId++;
					genPois[i][0] = genPoiId;
					fw.write(String.valueOf(genPois[i][0]));
					fw.write(separator);
					System.out.print(genPois[i][0] + ", ");

					genPois[i][1] = genStartNodeId;
					fw.write(String.valueOf(genPois[i][1]));
					fw.write(separator);
					System.out.print(genPois[i][1] + ", ");

					genEndNodeId = 5; //just for not to have an error (need to figure out the correct value earlier)
					genPois[i][2] = genEndNodeId;
					fw.write(String.valueOf(genPois[i][2]));
					fw.write(separator);
					System.out.print(genPois[i][2] + ", ");

					genDistFromStartNode = 4;//just for not to have an error (need to figure out the correct value earlier)
					genPois[i][3] = genDistFromStartNode;
					fw.write(String.valueOf(genPois[i][3]));
					fw.write(separator);
					System.out.print(genPois[i][3] + ", ");

					genCategoryId = 2;//just for not to have an error (need to figure out the correct value earlier)
					genPois[i][4] = genCategoryId;
					fw.write(String.valueOf(genPois[i][4]));
					fw.write(separator);
					System.out.print(genPois[i][4] + ", ");

					genType = 1;//just for not to have an error (need to figure out the correct value earlier)
					genPois[i][5] = genType;
					fw.write(String.valueOf(genPois[i][5]));
					fw.write(separator);
					System.out.print(genPois[i][5] + ", ");

					genRating = 5;//just for not to have an error (need to figure out the correct value earlier)
					genPois[i][6] = genRating;
					fw.write(String.valueOf(genPois[i][6]));
					fw.write("\n");
					System.out.print(genPois[i][6] + ", ");
					System.out.println();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// returning generated POIs
		return genPois;

	}

	public void generateRandomPois(Graph g, int maxNumofPoiPerGraph, int maxNumofPoiPerEdge,
			double minDistanceBetPoiAndNode, double minDistanceBetPois) {
		try {
			int PoiId = 0;
			int startNode;
			Set<Integer> endNode;
			int totalNumberofPOIinGraph = 0;
			ArrayList<Integer> listGenEdges = new ArrayList<Integer>();

			int numOfEdge = g.getNumberOfEdges();
			int[][] genPoi = new int[numOfEdge][7];

			Random rand1 = new Random();
			startNode = rand1.nextInt(numOfEdge);
			endNode = g.getAdjancencyMap().get(startNode).keySet();
			System.out.println("Start: " + startNode);
			System.out.println("End: " + endNode);

			FileWriter fw = new FileWriter("generatedPoi.txt");

			for (int i = 0; i < genPoi.length; i++) {
				for (int j = 0; j < genPoi[i].length; j++) {
					Random rand = new Random();
					genPoi[i][j] = PoiId;
					startNode = rand.nextInt(numOfEdge);
					endNode = g.getAdjancencyMap().get(startNode).keySet();
					genPoi[i][1] = startNode;
					// genPoi[i][2]=endNode;
					// double distance=g.getEdgeDistance(startNode, endNode);
					// double distFromSrc=
					PoiId++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showGraphInfor(Graph g) {

		Random rand1 = new Random();
		// int startNode = rand1.nextInt(g.getNumberOfEdges());
		// int startNode = rand1.nextInt(g.getNumberOfEdges());
		// Set<Integer> endNode = g.getAdjancencyMap().get(startNode).keySet();
		// System.out.println("Start: " + startNode);
		// System.out.println("End: " + endNode);
		int poiId = 0;

		String[][] genPoi = new String[g.getNumberOfEdges()][6];
		try {
			FileWriter filew = new FileWriter("generatedFile.txt");
			for (int i = 0; i < genPoi.length; i++) {
				int startNode = rand1.nextInt(g.getNumberOfEdges());
				for (int k = 0; k < startNode; k++) {
					for (int j = 0; j < genPoi[i].length; j++) {
						genPoi[i][j] = String.valueOf(poiId);
						poiId++;
						filew.write(genPoi[i][j]);
						filew.write(separator);

						genPoi[i][1] = String.valueOf(startNode);
						filew.write(genPoi[i][1]);
						filew.write(separator);

						Set<Integer> endNode = g.getAdjancencyMap().get(startNode).keySet();
						int endnode = endNode.iterator().next();
						if (listUsedEndNodeId.isEmpty() || !listUsedEndNodeId.contains(endnode)) {
							genPoi[i][2] = String.valueOf(endnode);
							filew.write(genPoi[i][2]);
							filew.write(separator);
							listUsedEndNodeId.add(endnode);
						}

						double distance = g.getEdgeDistance(startNode, endnode);

						double distFromSrc = rand1.nextDouble() * distance + 1;
						if (distFromSrc < distance) {
							genPoi[i][3] = String.valueOf(distFromSrc);
							filew.write(genPoi[i][3]);
							filew.write(separator);
						}
						// Category_Id: Hotel-1, Restaurant-2, Cafe-3
						int cat_Id = rand1.nextInt() * 3 + 1;
						genPoi[i][4] = String.valueOf(cat_Id);
						filew.write(genPoi[i][4]);
						filew.write(separator);

						double rating = rand1.nextDouble() * 10 + 1;
						genPoi[i][5] = String.valueOf(rating);
						filew.write(genPoi[i][5]);
						filew.write(separator);

					}

					listUsedStartNodeId.add(startNode);
				}

			}
			filew.write("\n");
			filew.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
