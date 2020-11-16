package testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import algorithm.RandomObjectGenerator;
import framework.Graph;
import framework.UtilsManagment;
import road_network.OldenburgRN;

public class RandomObjectOnOldenburgDatasetTest {
	public static void main(String[] args) {
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
		Graph oldenGraph = OldenburgRN.getGraph();
		String graphName = oldenGraph.getDatasetName();
//		int trueObjSize = 100000;
//		int falseObjSize = 10000;
		int trueObjSize = 10000;
	//	int falseObjSize = 20;

		Map<Integer, ArrayList<Double>> acceptedDistancesOnEdge=new HashMap<Integer,ArrayList<Double>>();
		ArrayList<Integer> centroidNodeIds=new ArrayList<Integer>();
		RandomObjectGenerator.zcreateCentroidDistribution(oldenGraph, 1, acceptedDistancesOnEdge, centroidNodeIds, trueObjSize, true);
		oldenGraph.printObjectsOnEdges();

	}

}
