package Framework;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		UtilsManagment um = new UtilsManagment();

		String edgeDatasetFile = "CAL-Edge_Eid-ESrc-EDest-EDist.csv";
		String nodeDatasetFile = "CAL-Vertex_Vid-VLong-VLat.csv";
		String poiDatasetFile = "CAL-POI_POILong-POILat-POIId.csv";
		
//		ArrayList<Vertex> myVertices = um.readVertexFiles("CAL-Vertex_Vid-VLong-VLat.csv");
//		System.out.println("Vertex File Imported Successfully!");
//
//		ArrayList<Edge> myEdges = um.readEdgeFile("CAL-Edge_Eid-ESrc-EDest-EDist.csv");
//		System.out.println("Edge File Imported Successfully!");
//		System.out.println(myEdges.get(0).getEdgeId());
//
////		um.readPOIFile("CAL-POI_POIName-POILong-POILat.csv");
////		System.out.println("POI File Imported Successfully!");
//
//		um.readPOIFile2("CAL-POI_POILong-POILat-POIId.csv");
//		System.out.println("POI file with Id Imported Successfully");
//		
//		um.populatePOIMap("CAL-POI_POIId-POIName-POIType.csv");
//		um.displayPOIHmap();

		//Testing graph
//		0 1 2
//		0 4 3
//		1 2 2
//		1 3 3
//		1 4 4
//		2 3 2
//		3 4 5
		Graph roadNetwork1 = new Graph();
		
		roadNetwork1.loadDataset(edgeDatasetFile, nodeDatasetFile, poiDatasetFile);
		//roadNetwork1.loadDataset(edgeDatasetFile);
		roadNetwork1.printGraph();
		
		
		Graph roadNetwork2;
		
		roadNetwork2 = um.readMergedPOI("CAL-MergedPOI_Start_End_Length_NumofPOI2.csv");
		
		
//		roadNetwork1.addEdge(0, 1, 2.1);
//		roadNetwork1.addEdge(0, 4, 3.2);
//		roadNetwork1.addEdge(1, 2, 2.3);
//		roadNetwork1.addEdge(1, 3, 3.4);
//		roadNetwork1.addEdge(1, 4, 4.8);
//		roadNetwork1.addEdge(2, 3, 2.9);
//		roadNetwork1.addEdge(3, 4, 5.6);
		
//		
//		System.out.println("Distance between node 1 and 4: " + roadNetwork1.getEdgeDistance(1, 4));
//		System.out.println("Number of edges: " + roadNetwork1.getNumberOfEdges());
//		System.out.println("Number of nodes: " + roadNetwork1.getNumberOfNodes());
//		System.out.println();
//		roadNetwork1.printGraph();
		
		//roadNetwork1.removeEdge(2, 1);
		//roadNetwork1.removeEdge(3, 4);
		
//		//roadNetwork1.printGraph();
//		System.out.println(roadNetwork1.getEdges(1));
//		System.out.println(roadNetwork1.getEdgesWithDistances(1));
		//roadNetwork1.getEdges(1);
		
//		roadNetwork1.addPOI(31, 0, 1, 1.3);
//		roadNetwork1.addPOI(32, 0, 1, 1.9);
//		roadNetwork1.addPOI(33, 0, 4, 2.2);
//		
//		roadNetwork1.printPOIs();
//		
//		System.out.println(roadNetwork1.getPOIs(0, 1));
//		System.out.println(roadNetwork1.getPOIsWithDistance(0, 1));
		
	}

}
