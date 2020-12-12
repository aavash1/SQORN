package road_network;

import framework.Graph;
import framework.UtilsManagment;

public class NorthWestUSARN {

	private static Graph gr = new Graph("NorthWestUSA");
	public static Graph getGraph () { 
		initialize();
		return gr;
	}
	
	private static void initialize() { 
	
		String nodeDatasetFile = "Datasets/TextFiles/NW-Node.txt";
		String edgeDatasetFile = "Datasets/TextFiles/NW-Edge.txt";
		UtilsManagment.readTxtEdgeFile(gr, edgeDatasetFile);
		UtilsManagment.readTxtNodeFile(gr, nodeDatasetFile);
		
	}
	
}
