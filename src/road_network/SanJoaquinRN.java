package road_network;

import framework.Graph;
import framework.UtilsManagment;

public class SanJoaquinRN {

	private static Graph gr = new Graph("SanJoaquin");
	public static Graph getGraph () { 
		initialize();
		return gr;
	}
	
	public static void initialize() { 
		
		String nodeDatasetFile = "Datasets/SJ-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/SJ-Edge_Eid-ESrc-EDest-EDist.csv";
		UtilsManagment.readEdgeFile(gr, edgeDatasetFile);
		UtilsManagment.readNodeFile(gr, nodeDatasetFile);
	}
	
}
