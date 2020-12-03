package testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import algorithm.ANNClustered;
import algorithm.ANNNaive;
import algorithm.ClusteringRoadObjects;
import algorithm.RandomObjectGenerator;
import framework.Graph;
import framework.UtilsManagment;
import road_network.CaliforniaRN;
import road_network.SanJoaquinRN;

public class RandomObjectOnSanJoaDatasetTest {
	public static void main(String[] args) {
		Graph sanJoagraph = SanJoaquinRN.getGraph();

		int trueObjSize = 100000;
		int falseObjSize=10000;
		int valueOfSD=2;
		int scaleFactor=1990;

		Map<Integer, ArrayList<Double>> acceptedDistancesOnEdge = new HashMap<Integer, ArrayList<Double>>();
		ArrayList<Integer> centroidNodeIds = new ArrayList<Integer>();
		System.out.println(sanJoagraph.getDatasetName()+" with "+trueObjSize+" , SD: "+valueOfSD);
//		RandomObjectGenerator.zcreateCentroidDistribution(sanJoagraph, 1, valueOfSD, acceptedDistancesOnEdge, centroidNodeIds,
//				trueObjSize, true);
		RandomObjectGenerator.zgenerateCUUCDistribution(sanJoagraph, valueOfSD,scaleFactor, trueObjSize, falseObjSize, true);
		sanJoagraph.printObjectsOnEdges();
		System.out.println("---------------------------------------------------------");
	}

}
