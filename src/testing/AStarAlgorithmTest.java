package testing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import algorithm.AStarAlgorithm;
import framework.Graph;
import framework.Node;
import framework.UtilsManagment;

public class AStarAlgorithmTest {

	public static void main(String[] args) {
		UtilsManagment um = new UtilsManagment();
		String manualNodeDatasetA2 = "Datasets/DatasetForAStar1.txt";
		String manualDatasetForAStar = "Datasets/DatasetForAStarAlg-nodes.csv";
		Graph roadNetwork8 = um.readMergedObjectFile(manualNodeDatasetA2);
		ArrayList<Node> nodesInfo = um.readNodeFile(manualDatasetForAStar);// (manualnodeDatasetFile);
		roadNetwork8.setNodesWithInfo(nodesInfo);
		roadNetwork8.printGraph();

		AStarAlgorithm aStarAlg = new AStarAlgorithm(roadNetwork8);
		Scanner sc2 = new Scanner(System.in);
		System.out.println("-----Test of A Star Algorith-----");
		System.out.println();
		System.out.println("Enter source node id (0 is recommended):");
		int sourceId2 = sc2.nextInt();
		System.out.println("Enter destination node id (10 is recommended):");
		int destinationId2 = sc2.nextInt();

		System.out.println();
		aStarAlg.execute(roadNetwork8.getNodesWithInfo().get(sourceId2),
				roadNetwork8.getNodesWithInfo().get(destinationId2));
		LinkedList<Node> path2 = aStarAlg.getPath(roadNetwork8.getNodesWithInfo().get(destinationId2));
//		assertNotNull(path);
//		assertTrue(path.size() > 0);

		for (Node vertex : path2) {
			System.out.println("The path is: " + vertex);

		}

	}
}
