package algorithm;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import framework.Graph;

public class PoiGeneratorAlgorithm {
	private ArrayList<Integer> listSrcnode = new ArrayList<Integer>();
	private ArrayList<Integer> listEndnode = new ArrayList<Integer>();
	private char separator = ',';

//	public void generateRandomPois(Graph g, int maxNumofPoiPerGraph, int maxNumofPoiPerEdge,
//			double minDistanceBetPoiAndNode, double minDistanceBetPois) {
//		try {
//			int PoiId = 0;
//			int startNode;
//			Set<Integer> endNode;
//			int totalNumberofPOIinGraph = 0;
//			ArrayList<Integer> listGenEdges = new ArrayList<Integer>();
//
//			int numOfEdge = g.getNumberOfEdges();
//			int[][] genPoi = new int[numOfEdge][7];
//
//			Random rand1 = new Random();
//			startNode = rand1.nextInt(numOfEdge);
//			endNode = g.getAdjancencyMap().get(startNode).keySet();
//			System.out.println("Start: " + startNode);
//			System.out.println("End: " + endNode);
//
//			FileWriter fw = new FileWriter("generatedPoi.txt");
//
//			for (int i = 0; i < genPoi.length; i++) {
//				for (int j = 0; j < genPoi[i].length; j++) {
//					Random rand = new Random();
//					genPoi[i][j] = PoiId;
//					startNode = rand.nextInt(numOfEdge);
//					endNode = g.getAdjancencyMap().get(startNode).keySet();
//					genPoi[i][1] = startNode;
//					// genPoi[i][2]=endNode;
//					// double distance=g.getEdgeDistance(startNode, endNode);
//					// double distFromSrc=
//					PoiId++;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		// TODO: handle exception
//	}

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
						if (listEndnode.isEmpty() || !listEndnode.contains(endnode)) {
							genPoi[i][2] = String.valueOf(endnode);
							filew.write(genPoi[i][2]);
							filew.write(separator);
							listEndnode.add(endnode);
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
					
					listSrcnode.add(startNode);
				}

			}filew.write("\n");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
