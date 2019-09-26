package testing;

import algorithm.ANNNaive;
import algorithm.RandomObjectGenerator;
import framework.Graph;

public class NaiveANNGenDatasetTest {

	public static void main(String[] args) {
		Graph gr = new Graph();


		gr.addEdge(1, 2, 18.3698);
		gr.addEdge(1, 3, 20.5499);
		// gr.addEdge(2, 3, 19.0658);
		gr.addEdge(3, 4, 15.887);
		gr.addEdge(3, 5, 17.01466);
		gr.addEdge(5, 6, 23.963);
		gr.addEdge(4, 6, 22.17489);
		gr.addEdge(6, 7, 21.12365);
		gr.addEdge(7, 8, 24.4421);
		gr.addEdge(4, 10, 16.432);

		gr.printGraph();
		System.out.println();
		gr.printEdgesInfo();
		System.out.println();
		
		//RandomObjectGenerator.generateRandomObjectsOnMap(gr);
		gr.printObjectsOnEdges();
		RandomObjectGenerator.printStatistics();

//		System.out.println("----------All Objects of edge 3:");
//		System.out.println(gr.getAllObjectsOnGivenEdge(3));
//		System.out.println("----------All Objects of edge 3 (Sroted by distance ):");
//		System.out.println(gr.getAllObjectsOnEdgeSortedByDist(3));
//		System.out.println("----------True Objects of edge 3 (Sroted by distance ):");
//		System.out.println(gr.getTrueObjectsOnEdgeSortedByDist(3));
//		System.out.println("----------False Objects of edge 3 (Sroted by distance ):");
//		System.out.println(gr.getFalseObjectsOnEdgeSortedByDist(3));
//		
//		System.out.println("----------Nearest Object to Start Node on Edge 3:");
//		System.out.println(gr.getNearestObjectToStartNodeOnEdge(3));
//		System.out.println("----------Nearest Object ID to Start Node on Edge 3:");
//		System.out.println(gr.getNearestObjectIdToStartNodeOnEdge(3));
//		System.out.println("----------Farthest Object to Start Node on Edge 3:");
//		System.out.println(gr.getFarthestObjectFromStartNodeOnEdge(3));
//		System.out.println("----------Farthest Object ID to Start Node on Edge 3:");
//		System.out.println(gr.getFarthestObjectIdFromStartNodeOnEdge(3));
//		
//		System.out.println("----------Nearest Object to Obj with ID 32 on Edge 3:");
//		System.out.println(gr.getNearestObjectToGivenObjOnEdge(3, 32));
//		
//		System.out.println("----------Nearest True Object to Obj with ID 32 on Edge 3:");
//		System.out.println(gr.getNearestTrueObjectToGivenObjOnEdge(3, 32));
//		
//		System.out.println("----------Nearest False Object to Obj with ID 32 on Edge 3:");
//		System.out.println(gr.getNearestFalseObjectToGivenObjOnEdge(3, 32));
		
		//System.out.println("Adjacency edges to Edge 3:");
		//System.out.println(gr.getAdjacencyEdgeIds(3));
		//gr.getNearestObjectOnEdge(edgeId, sourceObj)	
		
		System.out.println();
		ANNNaive nAnn = new ANNNaive();
		nAnn.compute(gr);
		nAnn.printNearestNeighborSets();

	}

}
