package Framework;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		UtilsManagment um = new UtilsManagment();

		um.readVertexFiles("CAL-Vertex_Vid-VLong-VLat.csv");
		System.out.println("Vertex File Imported Successfully!");

		ArrayList<Edge> myEdges = um.readEdgeFile("CAL-Edge_Eid-ESrc-EDest-EDist.csv");
		System.out.println("Edge File Imported Successfully!");
		System.out.println(myEdges.get(0).getEdgeId());

//		um.readPOIFile("CAL-POI_POIName-POILong-POILat.csv");
//		System.out.println("POI File Imported Successfully!");

		um.readPOIFile2("CAL-POI_POILong-POILat-POIId.csv");
		System.out.println("POI file with Id Imported Successfully");
		
		um.populatePOIMap("CAL-POI_POIId-POIName-POIType.csv");
		um.displayPOIHmap();

	}

}
