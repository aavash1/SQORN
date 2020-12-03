package testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import algorithm.RandomObjectGenerator;
import framework.Graph;
import framework.UtilsManagment;
import road_network.CaliforniaRN;
import road_network.OldenburgRN;

public class RandomObjectOnCaliforniaDatasetTest {
	public static void main(String[] args) {

		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
		Graph calGraph = CaliforniaRN.getGraph();

		int trueObjSize = 100000;
		int falseObjSize = 10000;
		int valueOfSD = 2;
		int datasetScaleFactor = 1;

		// //RandomObjectGenerator.zgenerateCCDistribution(calGraph, trueObjSize,
		// falseObjSize);
		// Map<Integer, ArrayList<Double>> acceptedDistancesOnEdge=new
		// HashMap<Integer,ArrayList<Double>>();
		// ArrayList<Integer> centroidNodeIds=new ArrayList<Integer>();
		// // RandomObjectGenerator.zgenerateCUUCDistribution(calGraph, trueObjSize,
		// falseObjSize, true);
		// RandomObjectGenerator.zcreateCentroidDistribution(calGraph, 1,
		// acceptedDistancesOnEdge, centroidNodeIds, trueObjSize, true);
		// calGraph.printObjectsOnEdges();

		// RandomObjectGenerator.generateUniformRandomObjectsOnMap(calGraph,
		// trueObjSize, falseObjSize);
		// //RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(gr, 30, 40,
		// true);
		// RandomObjectGenerator.printStatistics();
		// System.out.println("Finished Generating");
		// UtilsManagment.writeRoadObjsOnEdgeFile(calGraph.getObjectsOnEdges(),
		// calGraph.getDatasetName(), "NAMEOFROADOBJDATASET");
		// //UtilsManagment.writeDatasetStatistics(calGraph);
		// // calGraph.printObjectsOnEdges();

		Map<Integer, ArrayList<Double>> acceptedDistancesOnEdge = new HashMap<Integer, ArrayList<Double>>();
		ArrayList<Integer> centroidNodeIds = new ArrayList<Integer>();
		System.out.println(calGraph.getDatasetName() + " with " + trueObjSize + " , SD: " + valueOfSD);
		RandomObjectGenerator.zgenerateCUUCDistribution(calGraph, valueOfSD,datasetScaleFactor, trueObjSize, falseObjSize, true);
		// RandomObjectGenerator.zcreateCentroidDistribution(calGraph, 1,valueOfSD,
		// acceptedDistancesOnEdge, centroidNodeIds, falseObjSize, false);
		// RandomObjectGenerator.zcreateCentroidDistribution2(calGraph, 1, 1,
		// acceptedDistancesOnEdge, centroidNodeIds, trueObjSize, true);
		//RandomObjectGenerator.zgenerateCCDistribution(calGraph, valueOfSD, trueObjSize, falseObjSize);
		calGraph.printObjectsOnEdges();
		System.out.println("---------------------------------------------------------");

	}

}
