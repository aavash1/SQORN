package Framework;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		UtilsManagment um = new UtilsManagment();

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
		roadNetwork1.addEdge(0, 1, 2);
		roadNetwork1.addEdge(0, 4, 3);
		roadNetwork1.addEdge(1, 2, 2);
		roadNetwork1.addEdge(1, 3, 3);
		roadNetwork1.addEdge(1, 4, 4);
		roadNetwork1.addEdge(2, 3, 2);
		roadNetwork1.addEdge(3, 4, 5);
		
		System.out.println("Distance between node 1 and 4: " + roadNetwork1.getEdgeDistance(1, 4));
		System.out.println("Number of edges: " + roadNetwork1.getNumberOfEdges());
		System.out.println("Number of nodes: " + roadNetwork1.getNumberOfNodes());
		System.out.println();
		roadNetwork1.printGraph();
		
	}

}
