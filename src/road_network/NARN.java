package road_network;

import framework.Graph;
import framework.UtilsManagment;

public class NARN {

	private static Graph gr = new Graph("NorthAmerica");
	public static Graph getGraph () { 
		initialize();
		return gr;
	}
	
	public static void initialize() { 
		
		String nodeDatasetFile = "Datasets/NA-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/NA-Edge_Eid-ESrc-EDest-EDist.csv";
		UtilsManagment.readEdgeFile(gr, edgeDatasetFile);
		UtilsManagment.readNodeFile(gr, nodeDatasetFile);
	}
	
}
