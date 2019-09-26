package algorithm;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import framework.Edge;
import framework.Graph;

public class PoiGeneratorAlgorithm {
	private ArrayList<Integer> m_listUsedStartNodeId = new ArrayList<Integer>();
	private ArrayList<Integer> m_listUsedEndNodeId = new ArrayList<Integer>();

	private ArrayList<Edge> m_listUsedEdges = new ArrayList<Edge>();

	private Graph m_graph;
	private int m_numOfEdge;
	private int m_numOfNodes;
	private int m_totalPossibleNumberOfPois, m_maxNumofPoiPerGraph, m_maxNumofPoiPerEdge;
	private double m_minDistanceBetPoiAndNode, m_minDistanceBetPois;

	private char separator = ',';
	private int m_startNodeIdCounter = 0;
	private int m_endNodeIdCounter = 0;
	private int m_poiCounter = 0;
	private int m_randNumberOfPoiOnCurrentEdge = 0;
	private int m_randNumberOfEdgesWithSameStartNode = 0;
	private int m_randEndNode = 0;
	int m_randEndNodeIdIndex = 0;
	ArrayList<Integer> m_listUsedRandomUsedEndNodeIdIndex = new ArrayList<Integer>();

	List<Integer> m_listEndNodes = new ArrayList<Integer>();;
	int m_genStartNodeId, m_genEndNodeId;
	// Final Generated File Format:
	// PoiId, StartNodeId, EndNodeId, DistFromStartNode, CategoryId, Type, Rating

	public String[][] generateRandomPois2(Graph g, int maxNumofPoiPerGraph, int maxNumofPoiPerEdge,
			double minDistanceBetPoiAndNode, double minDistanceBetPois) {

		m_graph = g;
		m_maxNumofPoiPerGraph = maxNumofPoiPerGraph;
		m_maxNumofPoiPerEdge = maxNumofPoiPerEdge;
		m_minDistanceBetPoiAndNode = minDistanceBetPoiAndNode;
		m_minDistanceBetPois = minDistanceBetPois;

		m_numOfEdge = g.getNumberOfEdges();
		m_numOfNodes = g.getNumberOfNodes();

		m_totalPossibleNumberOfPois = g.getNumberOfEdges() * maxNumofPoiPerEdge;

		if (maxNumofPoiPerGraph > m_totalPossibleNumberOfPois) {
			maxNumofPoiPerGraph = m_totalPossibleNumberOfPois;
		}

		// [1, totalNumberOfPois]
		Integer genPoiId = 0;

		int genStartNodeId = 0, genEndNodeId; // remove 0

		// [min, max]: min=0 (exclusive), max=length of Edge on which the poi is located
		double genDistFromStartNode;

		// {1, 2, 3}: Hotel-1, Restaurant-2, Cafe-3
		int genCategoryId;

		// {0, 1}
		int genType;

		// [0, 10]
		double genRating;

		int numberOfColumns = 7;
		String[][] genPois = new String[maxNumofPoiPerGraph][numberOfColumns];

		// Finding correct/possible random values for each column
		Random rand = new Random();
		Map<Integer, Integer> mapRandStartAndEndNode;
		FileWriter fw;
		try {
			fw = new FileWriter("generatedPoiDataset3.txt");

			for (int i = 0; i < genPois.length; i++) {
				for (int j = 0; j < genPois[i].length; j++) {
					genPoiId++;
					genPois[i][0] = String.valueOf(genPoiId);
					fw.write(String.valueOf(genPois[i][0]));
					fw.write(separator);
					
					System.out.print(genPois[i][0] + ", ");

					mapRandStartAndEndNode = getRandomStartAndEndNodeId();
					Set<Integer> setStartNode = mapRandStartAndEndNode.keySet();
					List<Integer> listStartNode = new ArrayList<Integer>();
					listStartNode.addAll(setStartNode);

					genStartNodeId = listStartNode.get(0);
					genPois[i][1] = String.valueOf(genStartNodeId);

					fw.write(String.valueOf(genPois[i][1]));
					fw.write(separator);
					System.out.print(genPois[i][1] + ", ");

					genEndNodeId = mapRandStartAndEndNode.get(genStartNodeId);
					genPois[i][2] = String.valueOf(genEndNodeId);
					fw.write(String.valueOf(genPois[i][2]));
					fw.write(separator);
					System.out.print(genPois[i][2] + ", ");

					genDistFromStartNode = 4;// just for not to have an error (need to figure out the correct value
												// earlier)
					genPois[i][3] = String.valueOf(genDistFromStartNode);
					fw.write(String.valueOf(genPois[i][3]));
					fw.write(separator);
					System.out.print(genPois[i][3] + ", ");

					genCategoryId = 2;// just for not to have an error (need to figure out the correct value earlier)
					genPois[i][4] = String.valueOf(genCategoryId);
					fw.write(String.valueOf(genPois[i][4]));
					fw.write(separator);
					System.out.print(genPois[i][4] + ", ");

					genType = 1;// just for not to have an error (need to figure out the correct value earlier)
					genPois[i][5] = String.valueOf(genType);
					fw.write(String.valueOf(genPois[i][5]));
					fw.write(separator);
					System.out.print(genPois[i][5] + ", ");

					genRating = 5;// just for not to have an error (need to figure out the correct value earlier)
					genPois[i][6] = String.valueOf(genRating);
					fw.write(String.valueOf(genPois[i][6]));
					fw.write("\n");
					System.out.print(genPois[i][6] + ", ");
					System.out.println();
				}
			}
			fw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// returning generated POIs
		return genPois;

	}

	private Map<Integer, Integer> getRandomStartAndEndNodeId() {

		Map<Integer, Integer> randStartAndEndNodeId = new HashMap<Integer, Integer>();

		Random rand = new Random();

		if (m_startNodeIdCounter == m_randNumberOfEdgesWithSameStartNode) {

			// int startNodeId = 0;
			// setting Start Node ID

			while (m_listUsedStartNodeId.isEmpty() || m_listUsedStartNodeId.contains(m_genStartNodeId)) {
				int randStartNodeId = rand.nextInt(m_numOfNodes);
				if (!m_listUsedStartNodeId.contains(randStartNodeId)) {
					m_genStartNodeId = randStartNodeId;
					m_listUsedStartNodeId.add(m_genStartNodeId);
					break;
				}
			}

			// setting the Set of End Node IDs
			Set<Integer> setEndNodes = m_graph.getAdjancencyMap().get(m_genStartNodeId).keySet();
			// if (setEndNodes instanceof List<?>) {
			// m_listEndNodes = (List<Integer>) setEndNodes;
			// }
			m_listEndNodes.clear();
			m_listEndNodes.addAll(setEndNodes);

			m_startNodeIdCounter = 0;
			m_listUsedRandomUsedEndNodeIdIndex.clear();
			m_randNumberOfEdgesWithSameStartNode = rand.nextInt(m_listEndNodes.size());
			if (m_randNumberOfEdgesWithSameStartNode == 0) m_randNumberOfEdgesWithSameStartNode++;

		}
		// int randEndNodeIdIndex = 0;// = rand.nextInt(listEndNodes.size());

		// Continue with same Start Node  
		if (m_endNodeIdCounter != m_randNumberOfEdgesWithSameStartNode) {
			
			if (m_poiCounter == m_randNumberOfPoiOnCurrentEdge) {
				// 
				while (m_listUsedRandomUsedEndNodeIdIndex.isEmpty()
						|| m_listUsedRandomUsedEndNodeIdIndex.contains(m_randEndNodeIdIndex)) {
					int randUsedEndNodeIndex = rand.nextInt(m_listEndNodes.size());
					if (!m_listUsedRandomUsedEndNodeIdIndex.contains(randUsedEndNodeIndex)) {
						m_randEndNodeIdIndex = randUsedEndNodeIndex;
						m_listUsedRandomUsedEndNodeIdIndex.add(m_randEndNodeIdIndex);
						break;
					}					
				}
				m_randNumberOfPoiOnCurrentEdge = rand.nextInt(m_maxNumofPoiPerEdge + 1);
				if (m_randNumberOfPoiOnCurrentEdge==0) m_randNumberOfPoiOnCurrentEdge++;
				m_poiCounter = 0;
				m_genEndNodeId = m_listEndNodes.get(m_randEndNodeIdIndex);
				m_listUsedEndNodeId.add(m_genEndNodeId);
				m_endNodeIdCounter++;

			}
			// m_randNumberOfEdgesWithSameStartNode

		} else

		{

			m_randNumberOfEdgesWithSameStartNode = 0;
			m_endNodeIdCounter = 0;
		}
		// while (m_listUsedRandomUsedEndNodeIdIndex.isEmpty()
		// || !m_listUsedRandomUsedEndNodeIdIndex.contains(m_randEndNodeIdIndex)) {
		// m_randEndNodeIdIndex = rand.nextInt(m_listEndNodes.size());
		// m_listUsedRandomUsedEndNodeIdIndex.add(m_randEndNodeIdIndex);
		// m_startNodeIdCounter++;
		// if (m_randNumberOfPoiOnCurrentEdge == m_startNodeIdCounter) {
		// break;
		// }
		//
		// m_genEndNodeId = m_listEndNodes.get(m_randEndNodeIdIndex);
		// m_listUsedEndNodeId.add(m_genEndNodeId);
		//
		// }

		m_startNodeIdCounter++;
		randStartAndEndNodeId.put(m_genStartNodeId, m_genEndNodeId);
		return randStartAndEndNodeId;
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
						if (m_listUsedEndNodeId.isEmpty() || !m_listUsedEndNodeId.contains(endnode)) {
							genPoi[i][2] = String.valueOf(endnode);
							filew.write(genPoi[i][2]);
							filew.write(separator);
							m_listUsedEndNodeId.add(endnode);
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

					m_listUsedStartNodeId.add(startNode);
				}

			}
			filew.write("\n");
			filew.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
