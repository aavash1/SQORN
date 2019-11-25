package testing;

import java.util.ArrayList;
import java.util.Map;

import framework.Graph;
import framework.RoadObject;
import framework.UtilsManagment;

public class fileWriterTEEST {
	public static void main(String[] args) {
		Graph calGraph = new Graph("California");

		String nodeDatasetFile = "Datasets/CAL-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/CAL-Edge_Eid-ESrc-EDest-EDist.csv";
		
		String objectDatasetFile = "GeneratedFiles/roadObjectsOnEdge_California size_110000_2019-11-25 15-42-53.csv";

		
		UtilsManagment.readEdgeFile(calGraph, edgeDatasetFile);
		UtilsManagment.readNodeFile(calGraph, nodeDatasetFile);
		Map<Integer, ArrayList<RoadObject>> objectsOnEdge = UtilsManagment.readRoadObjectFile(objectDatasetFile);
		calGraph.setObjectsOnEdges(objectsOnEdge);
		
		
		String filename = "QueryResults/" + calGraph.getDatasetName();
		for (int i = 0; i < 2; i++) {
			UtilsManagment.writeFinalEvaluationResult(calGraph, filename, (i * 5.3), ( i * 10.3));

		}
		System.out.println("done");

	}
}
