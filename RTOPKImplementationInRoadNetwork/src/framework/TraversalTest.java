package framework;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import algorithm.GraphTraversal;
import algorithm.RandomObjectGenerator;

public class TraversalTest {

	public static void main(String[] args) {

		Graph2 gr = new Graph2();
		GraphTraversal gt = new GraphTraversal();

		gr.addEdge(1, 2, 18.3698);
		gr.addEdge(1, 3, 20.5499);
		gr.addEdge(2, 3, 19.0658);
		gr.addEdge(3, 4, 15.887);
//		gr.addEdge(3, 5, 17.01466);
//		gr.addEdge(5, 6, 23.963);
//		gr.addEdge(4, 6, 22.17489);
//		gr.addEdge(6, 7, 21.12365);
//		gr.addEdge(7, 8, 24.4421);

//		for (int i = 1; i <= 8; i++) {
//			int edgeIDforTest = i;
//			if (gr.isTerminalNode(edgeIDforTest) == true) {
//				System.out.println(i + " is Terminal node and has degree of " + gr.getEdges(i).size()
//						+ " adjacent nodes: " + gr.getEdges(i));
//			} else if (gr.isIntermediateNode(edgeIDforTest) == true) {
//				System.out.println(i + " is Intermediate Node and has degree of " + gr.getEdges(i).size()
//						+ " adjacent nodes: " + gr.getEdges(i));
//			} else if (gr.isIntersectionNode(edgeIDforTest) == true) {
//				System.out.println(i + " is Intersection Node and has degree of " + gr.getEdges(i).size()
//						+ " adjacent nodes: " + gr.getEdges(i));
//			} else
//				System.out.println("No idea");
//		}

		System.out.println("The traversal looks like: ");
		gt.BFS(gr, 1);
		gt.printBFS();

	}

	public static void randomDistanceGeneratorTest() {
		System.out.println("Random acceptable distance generator");
		Boolean isThereDistanceConflict = false;
		ArrayList<Double> randomDistances2 = new ArrayList<Double>();
		double distanceFromStartNode;
		double minDistanceBetPois = 3;

		randomDistances2.add(12.0);
		randomDistances2.add(4.0);
		distanceFromStartNode = 7.0;
		for (int k = 0; k < randomDistances2.size(); k++) {
			if (!((randomDistances2.get(k) + minDistanceBetPois <= distanceFromStartNode)
					|| (randomDistances2.get(k) - minDistanceBetPois >= distanceFromStartNode))) {
				isThereDistanceConflict = true;
			}
		}
		for (int k = 0; k < randomDistances2.size(); k++) {
			System.out.println("chosen distance " + k + ": " + randomDistances2.get(k));
		}

		System.out.println("minDistanceBetPois: " + minDistanceBetPois + ", " + "Random distance from SN: "
				+ distanceFromStartNode);
		System.out.println("isThereDistanceConflict: " + isThereDistanceConflict);
	}

}
