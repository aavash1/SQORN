package testing;

import framework.Graph;
import framework.UtilsManagment;

public class EdgeLenghtTest {

	public static void main(String[] args) {
		
		Graph sanFGraph = new Graph("SanFrancisco");
		String nodeDatasetFile = "Datasets/SANF-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/SANF-Edge_Eid-ESrc-EDest-EDist.csv";

		UtilsManagment.readNodeFile(sanFGraph, nodeDatasetFile);
		UtilsManagment.readEdgeFile(sanFGraph, edgeDatasetFile);
		
		System.out.println(sanFGraph.getTotalLengthOfAllEdges());

	}

}
