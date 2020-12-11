package testing;

import framework.Graph;
import framework.UtilsManagment;

public class EdgeLenghtTest {

	public static void main(String[] args) {
		
		Graph northWestUSAGraph = new Graph("NorthWestUSA");

		String nodeDatasetFile = "Datasets/NW-Node_NId_NLong_NLat.csv";

		String edgeDatasetFile = "Datasets/NW-Edge_Eid-ESrc-EDest-EDist.csv";

		UtilsManagment.readNodeFile(northWestUSAGraph, nodeDatasetFile);
		UtilsManagment.readEdgeFile(northWestUSAGraph, edgeDatasetFile);
		
		System.out.println(northWestUSAGraph.getTotalLengthOfAllEdges());

	}

}
