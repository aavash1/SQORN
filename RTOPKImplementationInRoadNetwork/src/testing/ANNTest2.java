package testing;

import algorithm.ClusertingAlgorithm;
import algorithm.NaiveANN;
import algorithm.RandomObjectGenerator;
import framework.Graph2;

public class ANNTest2 {

	public static void main(String[] args) {
		Graph2 gr = new Graph2();
		ClusertingAlgorithm cla = new ClusertingAlgorithm();
		NaiveANN nann = new NaiveANN();
		RandomObjectGenerator rang = new RandomObjectGenerator();

		gr.addEdge(1, 2, 18.3698);
		gr.addEdge(1, 3, 20.5499);
		// gr.addEdge(2, 3, 19.0658);
		gr.addEdge(3, 4, 15.887);
		gr.addEdge(3, 5, 17.01466);
		gr.addEdge(5, 6, 23.963);
		gr.addEdge(4, 6, 22.17489);
		gr.addEdge(6, 7, 21.12365);
		gr.addEdge(7, 8, 24.4421);
		gr.addEdge(4, 10, 15.887);

		// gr.printGraph();
		System.out.println("--------------------THIS IS TEST FOR Naive ANN--------------------");
		System.out.println();
		System.out.println();
		// cla.clusterAllNodes(gr);

		//rang.generateRandomObjectsOnMap(gr);
		RandomObjectGenerator.generateRandomObjectsOnMap(gr);
		gr.printObjectsOnEdges();
		RandomObjectGenerator.printStatistics();

		System.out.println("----------All Objects of edge 3:");
		System.out.println(gr.getAllObjectsOnGivenEdge(3));
		System.out.println("----------All Objects of edge 3 (Sroted by distance ):");
		System.out.println(gr.getAllObjectsOnEdgeSortedByDist(3));
		System.out.println("----------True Objects of edge 3 (Sroted by distance ):");
		System.out.println(gr.getTrueObjectsOnEdgeSortedByDist(3));
		System.out.println("----------False Objects of edge 3 (Sroted by distance ):");
		System.out.println(gr.getFalseObjectsOnEdgeSortedByDist(3));
		
		System.out.println("----------Nearest Object to Start Node on Edge 3:");
		System.out.println(gr.getNearestObjectToStartNodeOnEdge(3));
		System.out.println("----------Nearest Object ID to Start Node on Edge 3:");
		System.out.println(gr.getNearestObjectIdToStartNodeOnEdge(3));
		System.out.println("----------Farthest Object to Start Node on Edge 3:");
		System.out.println(gr.getFarthestObjectFromStartNodeOnEdge(3));
		System.out.println("----------Farthest Object ID to Start Node on Edge 3:");
		System.out.println(gr.getFarthestObjectIdFromStartNodeOnEdge(3));
		
		System.out.println("----------Nearest Object to Obj with ID 32 on Edge 3:");
		System.out.println(gr.getNearestObjectToGivenObjOnEdge(3, 32));
		
		System.out.println("----------Nearest True Object to Obj with ID 32 on Edge 3:");
		System.out.println(gr.getNearestTrueObjectToGivenObjOnEdge(3, 32));
		
		System.out.println("----------Nearest False Object to Obj with ID 32 on Edge 3:");
		System.out.println(gr.getNearestFalseObjectToGivenObjOnEdge(3, 32));
		
		//gr.getNearestObjectOnEdge(edgeId, sourceObj)
		
		//nann.computeANN(gr);

	}

}
