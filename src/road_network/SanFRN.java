package road_network;

import framework.Graph;
import framework.UtilsManagment;

public class SanFRN {

	private static Graph gr = new Graph("SanFrancisco");
	public static Graph getGraph () { 
		initialize();
		return gr;
	}
	
	public static void initialize() { 
		
		String nodeDatasetFile = "Datasets/SANF-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/SANF-Edge_Eid-ESrc-EDest-EDist.csv";
		UtilsManagment.readEdgeFile(gr, edgeDatasetFile);
		UtilsManagment.readNodeFile(gr, nodeDatasetFile);
	}
	
}
