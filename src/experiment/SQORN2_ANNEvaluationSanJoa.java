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

import road_network.SanJoaquinRN;

public class SQORN2_ANNEvaluationSanJoa {

	public static void main(String[] args) {
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
		double perimeter = 800.85;

		Graph sanJoaGraphCU1, sanJoaGraphCU2, sanJoaGraphCU3, sanJoaGraphCU4, sanJoaGraphUC1, sanJoaGraphUC2,
				sanJoaGraphUC3, sanJoaGraphUC4, sanJoaGraphCC1, sanJoaGraphCC2, sanJoaGraphCC3, sanJoaGraphCC4,
				sanJoaGraphUU1, sanJoaGraphUU2, sanJoaGraphUU3, sanJoaGraphUU4;

		ArrayList<Graph> listOfGraphs = new ArrayList<Graph>();

		sanJoaGraphCU1 = SanJoaquinRN.getGraph();// 0
		listOfGraphs.add(sanJoaGraphCU1);
		sanJoaGraphCU2 = SanJoaquinRN.getGraph(); // 1
		listOfGraphs.add(sanJoaGraphCU2);
		sanJoaGraphCU3 = SanJoaquinRN.getGraph(); // 2
		listOfGraphs.add(sanJoaGraphCU3);
		sanJoaGraphCU4 = SanJoaquinRN.getGraph(); // 3
		listOfGraphs.add(sanJoaGraphCU4);
		sanJoaGraphUC1 = SanJoaquinRN.getGraph(); // 4...
		listOfGraphs.add(sanJoaGraphUC1);
		sanJoaGraphUC2 = SanJoaquinRN.getGraph(); // 5
		listOfGraphs.add(sanJoaGraphUC2);
		sanJoaGraphUC3 = SanJoaquinRN.getGraph(); // 6
		listOfGraphs.add(sanJoaGraphUC3);
		sanJoaGraphUC4 = SanJoaquinRN.getGraph(); // 7
		listOfGraphs.add(sanJoaGraphUC4);
		sanJoaGraphCC1 = SanJoaquinRN.getGraph(); // 8...
		listOfGraphs.add(sanJoaGraphCC1);
		sanJoaGraphCC2 = SanJoaquinRN.getGraph(); // 9
		listOfGraphs.add(sanJoaGraphCC2);
		sanJoaGraphCC3 = SanJoaquinRN.getGraph(); // 10
		listOfGraphs.add(sanJoaGraphCC3);
		sanJoaGraphCC4 = SanJoaquinRN.getGraph(); // 11
		listOfGraphs.add(sanJoaGraphCC4);
		sanJoaGraphUU1 = SanJoaquinRN.getGraph(); // 12...
		listOfGraphs.add(sanJoaGraphUU1);
		sanJoaGraphUU2 = SanJoaquinRN.getGraph(); // 13
		listOfGraphs.add(sanJoaGraphUU2);
		sanJoaGraphUU3 = SanJoaquinRN.getGraph(); // 14
		listOfGraphs.add(sanJoaGraphUU3);
		sanJoaGraphUU4 = SanJoaquinRN.getGraph(); // 15
		listOfGraphs.add(sanJoaGraphUU4);

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

		String evaluationResultFile = "ResultFiles/" + "SanJoaquin" + "_" + "_ANNs-Naive-Clustereds_VIVET"
				+ UtilsManagment.getNormalDateTime() + ".csv";

		while (!queryParams.isEmpty()) {
			int queryObjNum = queryParams.poll();
			int dataObjNum = dataParams.poll();

			// <C, U>
			RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(sanJoaGraphCU1, queryObjNum, dataObjNum,
					true, perimeter);
			RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(sanJoaGraphCU2, queryObjNum, dataObjNum,
					true, perimeter);
			RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(sanJoaGraphCU3, queryObjNum, dataObjNum,
					true, perimeter);
			RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(sanJoaGraphCU4, queryObjNum, dataObjNum,
					true, perimeter);

			// <U, C>
			RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(sanJoaGraphUC1, queryObjNum, dataObjNum,
					false, perimeter);
			RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(sanJoaGraphUC2, queryObjNum, dataObjNum,
					false, perimeter);
			RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(sanJoaGraphUC3, queryObjNum, dataObjNum,
					false, perimeter);
			RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(sanJoaGraphUC4, queryObjNum, dataObjNum,
					false, perimeter);

			// <C, C>
			RandomObjectGenerator.generateRandomObjectsOnEdgeWithCentroidForSameDistribution(sanJoaGraphCC1,
					queryObjNum, dataObjNum, perimeter);
			RandomObjectGenerator.generateRandomObjectsOnEdgeWithCentroidForSameDistribution(sanJoaGraphCC2,
					queryObjNum, dataObjNum, perimeter);
			RandomObjectGenerator.generateRandomObjectsOnEdgeWithCentroidForSameDistribution(sanJoaGraphCC3,
					queryObjNum, dataObjNum, perimeter);
			RandomObjectGenerator.generateRandomObjectsOnEdgeWithCentroidForSameDistribution(sanJoaGraphCC4,
					queryObjNum, dataObjNum, perimeter);

			// <U, U>
			RandomObjectGenerator.generateUniformRandomObjectsOnMap(sanJoaGraphUU1, queryObjNum, dataObjNum);
			RandomObjectGenerator.generateUniformRandomObjectsOnMap(sanJoaGraphUU2, queryObjNum, dataObjNum);
			RandomObjectGenerator.generateUniformRandomObjectsOnMap(sanJoaGraphUU3, queryObjNum, dataObjNum);
			RandomObjectGenerator.generateUniformRandomObjectsOnMap(sanJoaGraphUU4, queryObjNum, dataObjNum);

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
					.readNodeClustersFile("ClusterDatasets/SanJoaquin_node-clusters_2019-12-06 17-55-01.csv");

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
				// make new writer, writer for separate algorithms
				// UtilsManagment.writeFinalEvaluationResult(listOfGraphs.get(i),
				// evaluationResultFile, computationTimeDNaive, computationTimeDClustered,
				// distributionCat);

			}
		}

	}
}
