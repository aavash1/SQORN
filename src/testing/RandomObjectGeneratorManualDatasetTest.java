package testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import algorithm.RandomObjectGenerator;
import framework.Graph;
import framework.UtilsManagment;
import road_network.ManualRN5;

public class RandomObjectGeneratorManualDatasetTest {

	public static void main(String[] args) {

		Graph gr = ManualRN5.getGraph();

		int trueObjSize = 1000;

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
		
		//RandomObjectGenerator.zgenerateCCDistribution(gr, 10, 10);
		//gr.printObjectsOnEdges();
		Map<Integer, ArrayList<Double>> acceptedDistancesOnEdge=new HashMap<Integer,ArrayList<Double>>();
		ArrayList<Integer> centroidNodeIds=new ArrayList<Integer>();
		//RandomObjectGenerator.zcreateCentroidDistribution(oldenGraph, 1, acceptedDistancesOnEdge, centroidNodeIds, trueObjSize, true);
		RandomObjectGenerator.zcreateCentroidDistribution(gr, 1, 1, acceptedDistancesOnEdge, centroidNodeIds, trueObjSize, true);
		gr.printObjectsOnEdges();
//		System.out.println(UtilsManagment.getEuclideanObjectPoints(1,500));

	}

}
