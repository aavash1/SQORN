package testing;

import java.util.ArrayList;

import algorithm.RandomObjectGenerator;
import framework.Graph;
import framework.UtilsManagment;

public class RandomObjectOnCaliforniaDatasetTestWithCentroid {
	public static void main(String[] args) {

		Graph calGraph = new Graph("California");

		String nodeDatasetFile = "Datasets/CAL-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/CAL-Edge_Eid-ESrc-EDest-EDist.csv";
		int trueObjSize = 10000;
		int falseObjSize = 20000;

		UtilsManagment.readEdgeFile(calGraph, edgeDatasetFile);
		UtilsManagment.readNodeFile(calGraph, nodeDatasetFile);
		// calGraph.printGraph();

		//RandomObjectGenerator.generateRandomObjectsOnMap6(calGraph, trueObjSize, falseObjSize);
		//ArrayList<Integer> edgeList=RandomObjectGenerator.getEdgesWithinPerimeter(calGraph, 1.8, 19);
//		for(int i=0;i<edgeList.size();i++) {
//			System.out.print(edgeList.get(i)+", ");
//		}
		RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(calGraph, trueObjSize, falseObjSize, true,24.8);
		RandomObjectGenerator.printStatistics();
		System.out.println("Finished Generating datasets on "+calGraph.getDatasetName()+ " dataset");
		UtilsManagment.writeRoadObjsOnEdgeFile(calGraph.getObjectsOnEdges(), calGraph.getDatasetName(), "NAMEOFROADOBJDATASET");
		UtilsManagment.writeDatasetStatistics(calGraph);
		// calGraph.printObjectsOnEdges();

	}

}
