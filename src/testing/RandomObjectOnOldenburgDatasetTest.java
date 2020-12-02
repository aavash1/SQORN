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
		int trueObjSize = 50000;
		int falseObjSize = 50000;
		// int trueObjSize = 100000;
		// int falseObjSize = 20;
		int valueOfSD = 2;

		Map<Integer, ArrayList<Double>> acceptedDistancesOnEdge = new HashMap<Integer, ArrayList<Double>>();
		ArrayList<Integer> centroidNodeIds = new ArrayList<Integer>();
		//System.out.println(oldenGraph.getDatasetName() + " with " + trueObjSize + " , SD: " + valueOfSD);
		RandomObjectGenerator.zgenerateCUUCDistribution(oldenGraph, valueOfSD, trueObjSize, falseObjSize, true);
		RandomObjectGenerator.zgenerateCUUCDistribution(oldenGraph, valueOfSD, trueObjSize, falseObjSize, false);
		RandomObjectGenerator.zgenerateCCDistribution(oldenGraph, valueOfSD, trueObjSize, falseObjSize);
		RandomObjectGenerator.generateUniformRandomObjectsOnMap(oldenGraph, trueObjSize, falseObjSize);
	
		oldenGraph.printObjectsOnEdges();
		System.out.println("---------------------------------------------------------");

	}

}
