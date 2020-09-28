package experiment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

import algorithm.ANNClusteredOptimizedWithHeuristic;
import algorithm.ANNNaive;

import algorithm.RandomObjectGenerator;
import algorithm.VivetAlgorithm;
import framework.Graph;

import framework.UtilsManagment;
import road_network.OldenburgRN;

public class SQORN2_ANNEvaluationOlden {

	public static void main(String[] args) {
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
		double perimeter = 1200.586;

		Graph oldenGraphCU1, oldenGraphCU2, oldenGraphCU3, oldenGraphCU4, oldenGraphUC1, oldenGraphUC2, oldenGraphUC3,
				oldenGraphUC4, oldenGraphCC1, oldenGraphCC2, oldenGraphCC3, oldenGraphCC4, oldenGraphUU1, oldenGraphUU2,
				oldenGraphUU3, oldenGraphUU4;

		ArrayList<Graph> listOfGraphs = new ArrayList<Graph>();

		oldenGraphCU1 = OldenburgRN.getGraph();// 0
		listOfGraphs.add(oldenGraphCU1);
		oldenGraphCU2 = OldenburgRN.getGraph(); // 1
		listOfGraphs.add(oldenGraphCU2);
		oldenGraphCU3 = OldenburgRN.getGraph(); // 2
		listOfGraphs.add(oldenGraphCU3);
		oldenGraphCU4 = OldenburgRN.getGraph(); // 3
		listOfGraphs.add(oldenGraphCU4);
		oldenGraphUC1 = OldenburgRN.getGraph(); // 4...
		listOfGraphs.add(oldenGraphUC1);
		oldenGraphUC2 = OldenburgRN.getGraph(); // 5
		listOfGraphs.add(oldenGraphUC2);
		oldenGraphUC3 = OldenburgRN.getGraph(); // 6
		listOfGraphs.add(oldenGraphUC3);
		oldenGraphUC4 = OldenburgRN.getGraph(); // 7
		listOfGraphs.add(oldenGraphUC4);
		oldenGraphCC1 = OldenburgRN.getGraph(); // 8...
		listOfGraphs.add(oldenGraphCC1);
		oldenGraphCC2 = OldenburgRN.getGraph(); // 9
		listOfGraphs.add(oldenGraphCC2);
		oldenGraphCC3 = OldenburgRN.getGraph(); // 10
		listOfGraphs.add(oldenGraphCC3);
		oldenGraphCC4 = OldenburgRN.getGraph(); // 11
		listOfGraphs.add(oldenGraphCC4);
		oldenGraphUU1 = OldenburgRN.getGraph(); // 12...
		listOfGraphs.add(oldenGraphUU1);
		oldenGraphUU2 = OldenburgRN.getGraph(); // 13
		listOfGraphs.add(oldenGraphUU2);
		oldenGraphUU3 = OldenburgRN.getGraph(); // 14
		listOfGraphs.add(oldenGraphUU3);
		oldenGraphUU4 = OldenburgRN.getGraph(); // 15
		listOfGraphs.add(oldenGraphUU4);

		LinkedList<Integer> queryParams = new LinkedList<Integer>();
		LinkedList<Integer> dataParams = new LinkedList<Integer>();
		queryParams.add(30000);
		queryParams.add(30000);
		queryParams.add(30000);
		queryParams.add(30000);
		queryParams.add(30000);
		queryParams.add(20000);
		queryParams.add(30000);
		queryParams.add(50000);
		queryParams.add(70000);
		queryParams.add(100000);

		dataParams.add(20000);
		dataParams.add(30000);
		dataParams.add(50000);
		dataParams.add(70000);
		dataParams.add(100000);
		dataParams.add(30000);
		dataParams.add(30000);
		dataParams.add(30000);
		dataParams.add(30000);
		dataParams.add(30000);

		String evaluationResultFile = "ResultFiles/" + "Oldenburg" + "_" + "_ANNs-Naive-Clustereds_VIVET"
				+ UtilsManagment.getNormalDateTime() + ".csv";

		while (!queryParams.isEmpty()) {
			int queryObjNum = queryParams.poll();
			int dataObjNum = dataParams.poll();

			// <C, U>
			RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(oldenGraphCU1, queryObjNum, dataObjNum, true,
					perimeter);
			RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(oldenGraphCU2, queryObjNum, dataObjNum, true,
					perimeter);
			RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(oldenGraphCU3, queryObjNum, dataObjNum, true,
					perimeter);
			RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(oldenGraphCU4, queryObjNum, dataObjNum, true,
					perimeter);

			// <U, C>
			RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(oldenGraphUC1, queryObjNum, dataObjNum,
					false, perimeter);
			RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(oldenGraphUC2, queryObjNum, dataObjNum,
					false, perimeter);
			RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(oldenGraphUC3, queryObjNum, dataObjNum,
					false, perimeter);
			RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(oldenGraphUC4, queryObjNum, dataObjNum,
					false, perimeter);

			// <C, C>
			RandomObjectGenerator.generateRandomObjectsOnEdgeWithCentroidForSameDistribution(oldenGraphCC1, queryObjNum,
					dataObjNum, perimeter);
			RandomObjectGenerator.generateRandomObjectsOnEdgeWithCentroidForSameDistribution(oldenGraphCC2, queryObjNum,
					dataObjNum, perimeter);
			RandomObjectGenerator.generateRandomObjectsOnEdgeWithCentroidForSameDistribution(oldenGraphCC3, queryObjNum,
					dataObjNum, perimeter);
			RandomObjectGenerator.generateRandomObjectsOnEdgeWithCentroidForSameDistribution(oldenGraphCC4, queryObjNum,
					dataObjNum, perimeter);

			// <U, U>
			RandomObjectGenerator.generateRandomObjectsOnMap6(oldenGraphUU1, queryObjNum, dataObjNum);
			RandomObjectGenerator.generateRandomObjectsOnMap6(oldenGraphUU2, queryObjNum, dataObjNum);
			RandomObjectGenerator.generateRandomObjectsOnMap6(oldenGraphUU3, queryObjNum, dataObjNum);
			RandomObjectGenerator.generateRandomObjectsOnMap6(oldenGraphUU4, queryObjNum, dataObjNum);

			// NAIVE
			for (int i = 0; i < listOfGraphs.size(); i++) {
				String algoType = "Naive-ANN";
				String distributionCat;

				if (i < 4) {
					distributionCat = "<C,U>";
				} else if (i < 8) {
					distributionCat = "<U,C>";

				} else if (i < 12) {
					distributionCat = "<C,C>";
				} else {
					distributionCat = "<U,U>";
				}
				ANNNaive annNaive = new ANNNaive();
				long startTimeNaive = System.nanoTime();
				annNaive.compute(listOfGraphs.get(i), true);
				long computationTimeNaive = System.nanoTime() - startTimeNaive;
				double computationTimeDNaive = (double) computationTimeNaive / 1000000000.0;
				// annNaive.printNearestNeighborSets();
				System.out.println("Time to compute Naive ANN: " + computationTimeDNaive);
				UtilsManagment.writeFinalEvaluationResult(listOfGraphs.get(i), evaluationResultFile, algoType,
						queryObjNum, dataObjNum, distributionCat, computationTimeDNaive);

			}

			// VIVET
			for (int i = 0; i < listOfGraphs.size(); i++) {
				String algoType = "VIVET";
				String distributionCat;

				if (i < 4) {
					distributionCat = "<C,U>";
				} else if (i < 8) {
					distributionCat = "<U,C>";

				} else if (i < 12) {
					distributionCat = "<C,C>";
				} else {
					distributionCat = "<U,U>";
				}

				VivetAlgorithm annVivet = new VivetAlgorithm();
				long startTimeVivet = System.nanoTime();
				annVivet.compute(listOfGraphs.get(i));
				long computationTimeVivet = System.nanoTime() - startTimeVivet;
				double computationTimeDVivet = (double) computationTimeVivet / 1000000000.0;
				// annVivet.printNearestNeighborSets();
				System.out.println("Time to compute VIVET ANN: " + computationTimeDVivet);

				// make new writer, writer for separate algorithms
				UtilsManagment.writeFinalEvaluationResult(listOfGraphs.get(i), evaluationResultFile, algoType,
						queryObjNum, dataObjNum, distributionCat, computationTimeDVivet);

			}

			// CLUSTERED

			// TODO: find for SanJoaquin
			Map<Integer, LinkedList<Integer>> nodeClusterFromFile = UtilsManagment
					.readNodeClustersFile("ClusterDatasets/Oldenburg_node-clusters_2019-12-06 17-54-10.csv");

			for (int i = 0; i < listOfGraphs.size(); i++) {
				String algoType = "CLUSTERED";
				String distributionCat;

				if (i < 4) {
					distributionCat = "<C,U>";
				} else if (i < 8) {
					distributionCat = "<U,C>";

				} else if (i < 12) {
					distributionCat = "<C,C>";
				} else {
					distributionCat = "<U,U>";
				}

				ANNClusteredOptimizedWithHeuristic annClustered = new ANNClusteredOptimizedWithHeuristic();
				long startTimeClustered = System.nanoTime();
				annClustered.computeWithTimeAndHeuristicWithoutClustering(listOfGraphs.get(i), true,
						nodeClusterFromFile);
				long computationTimeClustered = System.nanoTime() - startTimeClustered;
				double computationTimeDClustered = (double) computationTimeClustered / 1000000000.0;
				// annClustered.printNearestNeighborSets();
				System.out.println("Time to compute Clustered ANN: " + computationTimeDClustered);
				UtilsManagment.writeFinalEvaluationResult(listOfGraphs.get(i), evaluationResultFile, algoType,
						queryObjNum, dataObjNum, distributionCat, computationTimeDClustered);

			}

		}
	}
}
