package testing;

import algorithm.RandomObjectGenerator;
import framework.Graph;
import road_network.ManualRN5;

public class RandomObjectGeneratorManualDatasetTest {

	public static void main(String[] args) {

		Graph gr = ManualRN5.getGraph();

		

//		int randomNodeSelected = 6;
//		double randomGaussianDistance = 12.6;
//
//		HashMap<Integer, Double> finalEdge = RandomObjectGenerator.traverseFromGivenNodeUptoDistance(gr,
//				randomNodeSelected, randomGaussianDistance);
//		int key = RandomObjectGenerator.getLast(finalEdge).getKey();
//		double distance = RandomObjectGenerator.getLast(finalEdge).getValue();
//		int StartNode = gr.getStartNodeIdOfEdge(key);
//		int EndNode = gr.getEndNodeIdOfEdge(key);
//		System.out.println("Traversing from startNode: " + randomNodeSelected + " it would traverse upto node: "
//				+ EndNode + " covering total distance: " + distance + " where the Edge Id:" + key
//				+ " And the startNode= " + StartNode + " and EndNode: " + EndNode);

		// System.out.println(finalEdge);
		
		RandomObjectGenerator.zgenerateCCDistribution(gr, 10, 10);
		gr.printObjectsOnEdges();

	}

}
