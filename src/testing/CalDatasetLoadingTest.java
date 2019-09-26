package testing;

import framework.Node;

import java.util.ArrayList;

import framework.Edge;
import framework.UtilsManagment;

public class CalDatasetLoadingTest {

	public static void main(String[] args) {
		UtilsManagment um = new UtilsManagment();

		// String edgeDatasetFile = "CAL-Edge_Eid-ESrc-EDest-EDist.csv";
		String nodeDatasetFile = "Datasets/CAL-Node_NId-NLong-NLat.csv";
		String poiDatasetFile = "Datasets/CAL-Poi_PoiLong-PoiLat-PoiCatId.csv";
		String mergedPoiDatasetFile = "Datasets/CALMergerdPOI_Start-End-EdgeLen-NumofPOI.txt";

		ArrayList<Node> myVertices = um.readNodeFile(nodeDatasetFile);
		System.out.println("Vertex File Imported Successfully!" + myVertices);

		ArrayList<Edge> myEdges = um.readEdgeFile("Datasets/CAL-Edge_Eid-ESrc-EDest-EDist.csv");
		System.out.println("Edge File Imported Successfully!");
		System.out.println(myEdges.get(0).getEdgeId());

		um.readRoadObjFile("CAL-POI_POIName-POILong-POILat.csv");
		System.out.println("POI File Imported Successfully!");

		um.readRoadObjFile("CAL-POI_POILong-POILat-POIId.csv");
		System.out.println("POI file with Id Imported Successfully");

		um.populateObjMap("CAL-POI_POIId-POIName-POIType.csv");
		um.displayObjHmap();
	}

}
