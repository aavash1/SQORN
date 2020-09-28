package testing;

import java.util.ArrayList;

import algorithm.ANNClustered;
import algorithm.ANNClusteredOptimizedWithHeuristic;
import algorithm.ANNNaive;
import algorithm.ClusteringNodes;
import algorithm.ClusteringRoadObjects;
import algorithm.RandomObjectGenerator;
import algorithm.VivetAlgorithm;
import framework.Graph;
import framework.Node;
import framework.RoadObject;
import framework.UtilsManagment;
import road_network.ManualRN2;
import road_network.ManualRN3;
import road_network.OldenburgRN;

public class ANNEvaluationHeuristicTest {

	public static void main(String[] args) {
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
		// Graph gr = ManualRN3.getGraph();
		// Graph gr = ManualRN2.getGraph();
		Graph gr = OldenburgRN.getGraph();
		String csvFilename = "GeneratedFiles/FORALLTEST_Oldenburg_Q_100000_D_100002020-09-26 00-07-30.csv";
		UtilsManagment.readRoadObjFile(csvFilename);

		ANNClusteredOptimizedWithHeuristic annClustered = new ANNClusteredOptimizedWithHeuristic();

		int trueObjSize = 100000;
		int falseObjSize = 100000;

		RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(gr, trueObjSize, falseObjSize, true, 1200.586);

		
		System.err.println("--------Starting---------");
		System.out.println(" ");
		long startTimeClusteredNonHeuristic = System.nanoTime();
		annClustered.computeWithTime(gr, true);
		long graphLoadingTimeForClusteredNonHeuristic = System.nanoTime() - startTimeClusteredNonHeuristic;
		double graphLoadingTimeDForClusteredNonHeuristic = (double) graphLoadingTimeForClusteredNonHeuristic
				/ 1000000000.0;
		// ann3.printNearestSets();
		System.out.println("Time to compute Non-Heuristic Clustered ANN: " + graphLoadingTimeDForClusteredNonHeuristic);
		System.out.println(" ");
		System.err.println("-----------------------------------------------------------");
		System.out.println(" ");
		
		long startTimeClusteredHeuristic = System.nanoTime();
		annClustered.computeWithTimeAndHeuristic(gr, true);
		long graphLoadingTimeClusteredHeuristic = System.nanoTime() - startTimeClusteredHeuristic;
		double graphLoadingTimeDClusteredHeuristic = (double) graphLoadingTimeClusteredHeuristic / 1000000000.0;
		// ann3.printNearestSets();
		System.out.println("Time to compute Clustered ANN with Heuristic: " + graphLoadingTimeDClusteredHeuristic);
		System.out.println(" ");
		System.err.println("-----------------------------------------------------------");
		System.out.println(" ");
		
		VivetAlgorithm vivet = new VivetAlgorithm();
		long startTimeClusteredVIVET = System.nanoTime();
		vivet.compute(gr);
		long graphLoadingTimeVIVET = System.nanoTime() - startTimeClusteredVIVET;
		double graphLoadingTimeDVIVET = (double) graphLoadingTimeVIVET / 1000000000.0;
		// ann3.printNearestSets();
		System.out.println("Time to compute VIVET ANN: " + graphLoadingTimeDVIVET);
		System.out.println(" ");
		System.err.println("-----------------------------------------------------------");
		System.out.println(" ");
		
		ANNNaive annNaive = new ANNNaive();
		long startTimeNaiveANN = System.nanoTime();
		annNaive.compute(gr, true);
		long graphLoadingTimeNaiveANN = System.nanoTime() - startTimeNaiveANN;
		double graphLoadingTimeDNaiveANN = (double) graphLoadingTimeNaiveANN / 1000000000.0;
		// ann3.printNearestSets();
		System.out.println("Time to compute Naive ANN: " + graphLoadingTimeDNaiveANN);
		System.out.println(" ");
		System.err.println("--------Finished---------");
		System.out.println(" ");
	}

}
