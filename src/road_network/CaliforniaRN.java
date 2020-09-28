package road_network;

import framework.Graph;
import framework.UtilsManagment;

public class CaliforniaRN {

	private static Graph gr = new Graph("California");
	public static Graph getGraph () { 
		initialize();
		return gr;
	}
	
	public static void initialize() { 
		
		String nodeDatasetFile = "Datasets/CAL-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/CAL-Edge_Eid-ESrc-EDest-EDist.csv";
		UtilsManagment.readEdgeFile(gr, edgeDatasetFile);
		UtilsManagment.readNodeFile(gr, nodeDatasetFile);
	}
	
}
