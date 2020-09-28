package testing;

import java.util.ArrayList;

import algorithm.RandomObjectGenerator;
import framework.Graph;
import framework.UtilsManagment;

public class RadiusEstimateTestingForAllDataset {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		//For California
//		Graph calGraph = new Graph("California");
//
//		String nodeDatasetFile = "Datasets/CAL-Node_NId-NLong-NLat.csv";
//		String edgeDatasetFile = "Datasets/CAL-Edge_Eid-ESrc-EDest-EDist.csv";
//		UtilsManagment.readEdgeFile(calGraph, edgeDatasetFile);
//		UtilsManagment.readNodeFile(calGraph, nodeDatasetFile);
//		ArrayList<Integer> edgesSelected=RandomObjectGenerator.getEdgesWithinPerimeter(calGraph, 1.8, RandomObjectGenerator.getRandIntBetRange(0, calGraph.getEdgesWithInfo().size()));
//		System.out.println("Size of edges: "+edgesSelected.size());
//		System.out.print("[");
//		for(int i=0;i<edgesSelected.size();i++) {
//			System.out.print(edgesSelected.get(i)+", ");
//		}
//		System.out.print("]");
//		System.out.println();
//		//Ends
		

		//For SanJoaqion
//		Graph sanJoaGraph = new Graph("SanJoaquin");
//
//		String nodeDatasetFile = "Datasets/SJ-Node_NId-NLong-NLat.csv";
//		String edgeDatasetFile = "Datasets/SJ-Edge_Eid-ESrc-EDest-EDist.csv";
//		UtilsManagment.readEdgeFile(sanJoaGraph, edgeDatasetFile);
//		UtilsManagment.readNodeFile(sanJoaGraph, nodeDatasetFile);
////		
////		RandomObjectGenerator.generateRandomObjectsOnEdgeWithCentroidForSameDistribution(sanJoaGraph, 10000, 20000, 550.65);
////		RandomObjectGenerator.printStatistics();
//		ArrayList<Integer> edgesSelected=RandomObjectGenerator.getEdgesWithinPerimeter(sanJoaGraph, 800.8, RandomObjectGenerator.getRandIntBetRange(0, sanJoaGraph.getEdgesWithInfo().size()));
//		System.out.println("Size of edges: "+edgesSelected.size());
//		System.out.print("[");
//		for(int i=0;i<edgesSelected.size();i++) {
//			System.out.print(edgesSelected.get(i)+", ");
//		}
//		System.out.print("]");
//		System.out.println();
//		//Ends
//		
//		
		//For Oldenburg
		Graph oldenGraph = new Graph("SanFrancisco");

		String nodeDatasetFile = "Datasets/SANF-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/SANF-Edge_Eid-ESrc-EDest-EDist.csv";
		UtilsManagment.readEdgeFile(oldenGraph, edgeDatasetFile);
		UtilsManagment.readNodeFile(oldenGraph, nodeDatasetFile);
		ArrayList<Integer> edgesSelected=RandomObjectGenerator.getEdgesWithinPerimeter(oldenGraph, 1200.586, RandomObjectGenerator.getRandIntBetRange(0, oldenGraph.getEdgesWithInfo().size()));
		System.out.println("Size of edges: "+edgesSelected.size());
		System.out.print("[");
		for(int i=0;i<edgesSelected.size();i++) {
			System.out.print(edgesSelected.get(i)+", ");
		}
		System.out.print("]");
		System.out.println();
		//Ends

	}

}

