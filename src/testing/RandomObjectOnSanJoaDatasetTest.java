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

		int trueObjSize = 10000;

		Map<Integer, ArrayList<Double>> acceptedDistancesOnEdge = new HashMap<Integer, ArrayList<Double>>();
		ArrayList<Integer> centroidNodeIds = new ArrayList<Integer>();
		RandomObjectGenerator.zcreateCentroidDistribution(sanJoagraph, 1, 1, acceptedDistancesOnEdge, centroidNodeIds,
				trueObjSize, true);
		sanJoagraph.printObjectsOnEdges();
	}

}
