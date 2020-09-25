package testing;

import java.util.ArrayList;

import algorithm.ANNClustered;
import algorithm.ANNNaive;
import algorithm.ClusteringNodes;
import algorithm.ClusteringRoadObjects;
import algorithm.RandomObjectGenerator;
import framework.Graph;
import framework.Node;
import framework.RoadObject;
import framework.UtilsManagment;
import road_network.ManualRN4;

public class ANNEvaluationManualDatasetTest {

	public static void main(String[] args) {
		
		Graph gr = ManualRN4.getGraph();		

		ANNNaive annNaive = new ANNNaive();
		ANNClustered annClustered = new ANNClustered();

		System.out.println();

//		long startTimeNaive = System.nanoTime();
//		annNaive.compute(gr, true);
//		long graphLoadingTimeNaive = System.nanoTime() - startTimeNaive;
//		double graphLoadingTimeDNaive = (double) graphLoadingTimeNaive / 1000000000.0;
//		// annNaive.printNearestNeighborSets();
//		System.out.println("Time to compute Naive ANN: " + graphLoadingTimeDNaive);

		long startTimeClustered = System.nanoTime();
		annClustered.computeWithoutTime(gr, true);
		long graphLoadingTimeClustered = System.nanoTime() - startTimeClustered;
		double graphLoadingTimeDClustered = (double) graphLoadingTimeClustered / 1000000000.0;
		// ann3.printNearestSets();
		System.out.println("Time to compute Clustered ANN: " + graphLoadingTimeDClustered);

//
//		UtilsManagment um = new UtilsManagment();
//		um.writeNaiveAndClusteredANNTestResult(gr, totalNumberOfNodeClusters, totalNumberOfObjectClusters, graphLoadingTimeDNaive,
//				graphLoadingTimeDClustered);

	}

}