package testing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import framework.Graph;
import framework.Node;
import algorithm.DijkstraAlgorithmDirected;
import algorithm.DijkstraAlgorithmUndirected;
import framework.UtilsManagment;

public class DijkstraAlgorithmUndirectedTest {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("-----Test of Dijkstra Algorithm-----");
		String generatedDatasetForDijkstra1 = "Datasets/TextFiles/DatasetForDijkstra1.txt";
		Graph roadNetwork4 = UtilsManagment.readEdgeFileReturnGraph(generatedDatasetForDijkstra1);
		roadNetwork4.printGraph();

		System.out.println();

		ArrayList<Integer> nodeList = new ArrayList<Integer>();
		for (int i = 0; i < roadNetwork4.getNodesWithInfo().size(); i++) {
			if (roadNetwork4.getNodesWithInfo().get(i).getNodeId() != 0) {
				nodeList.add(roadNetwork4.getNodesWithInfo().get(i).getNodeId());
			}
		}
		System.out.println(nodeList.size());
		System.out.println(nodeList);
		System.err.println("");
		DijkstraAlgorithmUndirected dijkstra = new DijkstraAlgorithmUndirected(roadNetwork4);

		ArrayList<LinkedList<Node>> listofPath = new ArrayList<LinkedList<Node>>();
		LinkedList<Node> listofNodes = new LinkedList<Node>();

		for (int j = 0; j < nodeList.size(); j++) {
			int sourceId = 0;
			int destinationId = nodeList.get(j);
			// System.out.println("dest id when j at "+j+" is : "+destinationId);

			dijkstra.execute(roadNetwork4.getNode(sourceId));
			if(destinationId==3) {
				System.out.println();
			}
			listofNodes = dijkstra.getPath(roadNetwork4.getNode(destinationId));
			listofPath.add(listofNodes);

		}
		for (int i = 0; i < listofPath.size(); i++) {

			if (listofPath.get(i) != null) {
				for (Node vertex : listofPath.get(i)) {
					System.out.print(vertex.getNodeId()+ " ");
					
				}
			}System.out.println();

		}

	}

}
