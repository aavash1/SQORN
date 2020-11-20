package road_network;

import framework.Graph;
import framework.UtilsManagment;

public class OldenburgRN {

	private static Graph gr = new Graph("Oldenburg");
	public static Graph getGraph () { 
		initialize();
		return gr;
	}
	
	public static void initialize() { 
		
		String nodeDatasetFile = "Datasets/OLDN-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/OLDN-Edge_EId-ESrc-EDest-EDist.csv";
		UtilsManagment.readEdgeFile(gr, edgeDatasetFile);
		UtilsManagment.readNodeFile(gr, nodeDatasetFile);
	}
	
}
