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
import road_network.NARN;


public class SQORN2_ANNEvaluationNA {

	public static void main(String[] args) {
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");

		double perimeter = 512.0;

		Graph northAmGraphCU1, northAmGraphCU2, northAmGraphCU3, northAmGraphCU4, northAmGraphUC1, northAmGraphUC2, northAmGraphUC3,
				northAmGraphUC4, northAmGraphCC1, northAmGraphCC2, northAmGraphCC3, northAmGraphCC4, northAmGraphUU1, northAmGraphUU2,
				northAmGraphUU3, northAmGraphUU4;

		ArrayList<Graph> listOfGraphs = new ArrayList<Graph>();

		northAmGraphCU1 = NARN.getGraph();// 0
		listOfGraphs.add(northAmGraphCU1);
		northAmGraphCU2 = NARN.getGraph(); // 1
		listOfGraphs.add(northAmGraphCU2);
		northAmGraphCU3 = NARN.getGraph(); // 2
		listOfGraphs.add(northAmGraphCU3);
		northAmGraphCU4 = NARN.getGraph(); // 3
		listOfGraphs.add(northAmGraphCU4);
		northAmGraphUC1 = NARN.getGraph(); // 4...
		listOfGraphs.add(northAmGraphUC1);
		northAmGraphUC2 = NARN.getGraph(); // 5
		listOfGraphs.add(northAmGraphUC2);
		northAmGraphUC3 = NARN.getGraph(); // 6
		listOfGraphs.add(northAmGraphUC3);
		northAmGraphUC4 = NARN.getGraph(); // 7
		listOfGraphs.add(northAmGraphUC4);
		northAmGraphCC1 = NARN.getGraph(); // 8...
		listOfGraphs.add(northAmGraphCC1);
		northAmGraphCC2 = NARN.getGraph(); // 9
		listOfGraphs.add(northAmGraphCC2);
		northAmGraphCC3 = NARN.getGraph(); // 10
		listOfGraphs.add(northAmGraphCC3);
		northAmGraphCC4 = NARN.getGraph(); // 11
		listOfGraphs.add(northAmGraphCC4);
		northAmGraphUU1 = NARN.getGraph(); // 12...
		listOfGraphs.add(northAmGraphUU1);
		northAmGraphUU2 = NARN.getGraph(); // 13
		listOfGraphs.add(northAmGraphUU2);
		northAmGraphUU3 = NARN.getGraph(); // 14
		listOfGraphs.add(northAmGraphUU3);
		northAmGraphUU4 = NARN.getGraph(); // 15
		listOfGraphs.add(northAmGraphUU4);

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

		String evaluationResultFile = "ResultFiles/" + "NorthAmerica" + "_" + "_ANNs-Naive-Clustereds_VIVET"
				+ UtilsManagment.getNormalDateTime() + ".csv";

		while (!queryParams.isEmpty()) {
			int queryObjNum = queryParams.poll();
			int dataObjNum = dataParams.poll();

			// <C, U>
			RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(northAmGraphCU1, queryObjNum, dataObjNum, true,
					perimeter);
			RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(northAmGraphCU2, queryObjNum, dataObjNum, true,
					perimeter);
			RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(northAmGraphCU3, queryObjNum, dataObjNum, true,
					perimeter);
			RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(northAmGraphCU4, queryObjNum, dataObjNum, true,
					perimeter);

			// <U, C>
			RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(northAmGraphUC1, queryObjNum, dataObjNum,
					false, perimeter);
			RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(northAmGraphUC2, queryObjNum, dataObjNum,
					false, perimeter);
			RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(northAmGraphUC3, queryObjNum, dataObjNum,
					false, perimeter);
			RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(northAmGraphUC4, queryObjNum, dataObjNum,
					false, perimeter);

			// <C, C>
			RandomObjectGenerator.generateRandomObjectsOnEdgeWithCentroidForSameDistribution(northAmGraphCC1, queryObjNum,
					dataObjNum, perimeter);
			RandomObjectGenerator.generateRandomObjectsOnEdgeWithCentroidForSameDistribution(northAmGraphCC2, queryObjNum,
					dataObjNum, perimeter);
			RandomObjectGenerator.generateRandomObjectsOnEdgeWithCentroidForSameDistribution(northAmGraphCC3, queryObjNum,
					dataObjNum, perimeter);
			RandomObjectGenerator.generateRandomObjectsOnEdgeWithCentroidForSameDistribution(northAmGraphCC4, queryObjNum,
					dataObjNum, perimeter);

			// <U, U>
			RandomObjectGenerator.generateUniformRandomObjectsOnMap(northAmGraphUU1, queryObjNum, dataObjNum);
			RandomObjectGenerator.generateUniformRandomObjectsOnMap(northAmGraphUU2, queryObjNum, dataObjNum);
			RandomObjectGenerator.generateUniformRandomObjectsOnMap(northAmGraphUU3, queryObjNum, dataObjNum);
			RandomObjectGenerator.generateUniformRandomObjectsOnMap(northAmGraphUU4, queryObjNum, dataObjNum);

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
					.readNodeClustersFile("ClusterDatasets/NorthAmerica_node-clusters_2019-12-06 17-39-10.csv");

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
