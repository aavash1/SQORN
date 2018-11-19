package framework;

import java.util.LinkedList;
import java.util.Scanner;

import org.w3c.dom.UserDataHandler;

import algorithm.DijkstraAlgorithm;
import algorithm.PoiGeneratorAlgorithm;
import algorithm.UserPreferenceGenerator;
import algorithm.kNNAlg;
import algorithm.kNNAlgorithm;

public class Main {
	public static void main(String[] args) {
		UtilsManagment um = new UtilsManagment();

		// String edgeDatasetFile = "CAL-Edge_Eid-ESrc-EDest-EDist.csv";
		String nodeDatasetFile = "Datasets/CAL-Node_NId-NLong-NLat.csv";
		String poiDatasetFile = "Datasets/CAL-Poi_PoiLong-PoiLat-PoiCatId.csv";
		String mergedPoiDatasetFile = "Datasets/CALMergerdPOI_Start-End-EdgeLen-NumofPOI.txt";
		String mergedPoiDatasetFileShort = "Datasets/MergedPoi-short.txt";
		String generatedDataset1 = "Datasets/GeneratedDataset2.txt";
		String generatedDatasetForDijkstra1 = "Datasets/DatasetForDijkstra1.txt";

		Graph roadNetwork2 = new Graph();

		// System.out.println("Loading graph ...");
		long startTimeGraphLoading = System.nanoTime();
		// roadNetwork2 = um.readMergedPOI(mergedPoiDatasetFileShort);
		// roadNetwork2.printGraph();
		// System.out.println();
		// roadNetwork2.printPOIs();
		long graphLoadingTime = System.nanoTime() - startTimeGraphLoading;

		// System.out.println("Loading nodes' information... ");
		long startTimeLoadNodes = System.nanoTime();
		// um.loadNodesInfo(roadNetwork2, nodeDatasetFile);
		long nodesLoadingTime = System.nanoTime() - startTimeLoadNodes;
		// roadNetwork2.printNodesInfo();

		// System.out.println("Loading pois' information... ");
		long poisStartTime = System.nanoTime();
		// um.loadPoiInfo(roadNetwork2, poiDatasetFile);
		long poisLoadingTime = System.nanoTime() - poisStartTime;
		// roadNetwork2.printPoisInfo();

		// long graphLoadingTimeSec = TimeUnit.SECONDS.convert(graphLoadingTime,
		// TimeUnit.NANOSECONDS);
		// long nodesLoadingTimeSec = TimeUnit.SECONDS.convert(nodesLoadingTime,
		// TimeUnit.NANOSECONDS);
		// long poisLoadingTimeSec = TimeUnit.SECONDS.convert(poisLoadingTime,
		// TimeUnit.NANOSECONDS);

		double graphLoadingTimeD = (double) graphLoadingTime / 1000000000.0;
		double nodesLoadingTimeD = (double) nodesLoadingTime / 1000000000.0;
		double poisLoadingTimeD = (double) poisLoadingTime / 1000000000.0;

		// System.out.println("Elapsed time of Graph loading: " + graphLoadingTimeD + "
		// seconds");
		// System.out.println("Elapsed time of Nodes info loading: " + nodesLoadingTimeD
		// + " seconds");
		// System.out.println("Elapsed time of POIs info loading: " + poisLoadingTimeD +
		// " seconds");

		// roadNetwork2.printPoisInfo();
		// roadNetwork2.printEdgesInfo();
		// Graph roadNetwork3 = um.readMergedPOI(generatedDataset1);
		// roadNetwork3.printGraph();

		// =============Dijkstra TEST=================================
		/*
		 * Scanner sc = new Scanner(System.in);
		 * System.out.println("-----Test of Dijkstra Algorith-----"); Graph roadNetwork4
		 * = um.readMergedPOI(generatedDatasetForDijkstra1); roadNetwork4.printGraph();
		 * roadNetwork4.printGraph();
		 * 
		 * System.out.println();
		 * System.out.println("Enter source node id (0 is recommended):"); int sourceId
		 * = sc.nextInt();
		 * System.out.println("Enter destination node id (10 is recommended):"); int
		 * destinationId = sc.nextInt();
		 * 
		 * DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(roadNetwork4);
		 * System.out.println();
		 * dijkstra.execute(roadNetwork4.getNodesWithInfo().get(sourceId));
		 * LinkedList<Node> path =
		 * dijkstra.getPath(roadNetwork4.getNodesWithInfo().get(destinationId));
		 * 
		 * // assertNotNull(path); // assertTrue(path.size() > 0);
		 * 
		 * for (Node vertex : path) { System.out.println(vertex); }
		 */

		// =============End of Dijkstra TEST=================================

		// =============OLD TESTS=================================
		// roadNetwork1.addEdge(0, 1, 2.1);
		// roadNetwork1.addEdge(0, 4, 3.2);
		// roadNetwork1.addEdge(1, 2, 2.3);
		// roadNetwork1.addEdge(1, 3, 3.4);
		// roadNetwork1.addEdge(1, 4, 4.8);
		// roadNetwork1.addEdge(2, 3, 2.9);
		// roadNetwork1.addEdge(3, 4, 5.6);
		//
		// System.out.println("Distance between node 1 and 4: " +
		// roadNetwork1.getEdgeDistance(1, 4));
		// System.out.println("Number of edges: " + roadNetwork1.getNumberOfEdges());
		// System.out.println("Number of nodes: " + roadNetwork1.getNumberOfNodes());
		// System.out.println();
		// roadNetwork1.printGraph();
		// roadNetwork1.removeEdge(2, 1);
		// roadNetwork1.removeEdge(3, 4);

		// //roadNetwork1.printGraph();
		// System.out.println(roadNetwork1.getEdges(1));
		// System.out.println(roadNetwork1.getEdgesWithDistances(1));
		// roadNetwork1.getEdges(1);

		// roadNetwork1.addPOI(31, 0, 1, 1.3);
		// roadNetwork1.addPOI(32, 0, 1, 1.9);
		// roadNetwork1.addPOI(33, 0, 4, 2.2);
		//
		// roadNetwork1.printPOIs();
		//
		// System.out.println(roadNetwork1.getPOIs(0, 1));
		// System.out.println(roadNetwork1.getPOIsWithDistance(0, 1));

		// ArrayList<Vertex> myVertices =
		// um.readVertexFiles("CAL-Vertex_Vid-VLong-VLat.csv");
		// System.out.println("Vertex File Imported Successfully!");
		//
		// ArrayList<Edge> myEdges =
		// um.readEdgeFile("Datasets/CAL-Edge_Eid-ESrc-EDest-EDist.csv");
		// System.out.println("Edge File Imported Successfully!");
		// System.out.println(myEdges.get(0).getEdgeId());
		//
		//// um.readPOIFile("CAL-POI_POIName-POILong-POILat.csv");
		//// System.out.println("POI File Imported Successfully!");
		//
		// um.readPOIFile2("CAL-POI_POILong-POILat-POIId.csv");
		// System.out.println("POI file with Id Imported Successfully");
		//
		// um.populatePOIMap("CAL-POI_POIId-POIName-POIType.csv");
		// um.displayPOIHmap();

		// --------Generator Algorithms TESTS-----------------------
		Graph roadNetwork5 = um.readMergedPOI(generatedDatasetForDijkstra1);
		// UserPreferenceGenerator upg = new UserPreferenceGenerator();
		// upg.generateRandomUserPreference(600, 2);

		// Graph newGraph2 = new Graph();
		// PoiGeneratorAlgorithm pga = new PoiGeneratorAlgorithm();
		// pga.generateRandomPois(roadNetwork5, 0, 0, 0, 0);
		// pga.showGraphInfor(roadNetwork5);
		// pga.generateRandomPois2(roadNetwork5, 300, 7, 0.000333, 0.000045);

		// --------KNN Test Algorithm--------
		Graph roadNetwork6 = um.readMergedPOI(generatedDataset1);
		kNNAlgorithm knn=new kNNAlgorithm(roadNetwork6);
		//kNNAlg knn = new kNNAlg(roadNetwork6);
		roadNetwork6.printGraph();
//		knn.getAdjacentNodes(5);

		// knn.getKNN(node, numberOfKnn)
	//	 knn.sortNode(roadNetwork6,40);
	 //knn.getKNN(40);
	knn.getKNearestN(50, 6);
	knn.getKNearestN(50, 2);
		 

	}

}
