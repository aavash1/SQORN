package testing;

import java.util.LinkedList;
import java.util.Scanner;

import framework.Graph;
import framework.Node;
import algorithm.DijkstraAlgorithm;
import framework.UtilsManagment;

public class DijkstraAlgorithmTest {

	public static void main(String[] args) {
		UtilsManagment um = new UtilsManagment();
		Scanner sc = new Scanner(System.in);
		System.out.println("-----Test of Dijkstra Algorithm-----");
		String generatedDatasetForDijkstra1 = "Datasets/DatasetForDijkstra1.txt";
		Graph roadNetwork4 = um.readMergedObjectFile(generatedDatasetForDijkstra1);
		roadNetwork4.printGraph();
		roadNetwork4.printGraph();

		System.out.println();
		System.out.println("Enter source node id (0 is recommended):");
		int sourceId = sc.nextInt();
		System.out.println("Enter destination node id (10 is recommended):");
		int destinationId = sc.nextInt();

		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(roadNetwork4);
		System.out.println();
		dijkstra.execute(roadNetwork4.getNodesWithInfo().get(sourceId));
		LinkedList<Node> path = dijkstra.getPath(roadNetwork4.getNodesWithInfo().get(destinationId));

		// assertNotNull(path); // assertTrue(path.size() > 0);

		for (Node vertex : path) {
			System.out.println(vertex);
		}

	}

}
