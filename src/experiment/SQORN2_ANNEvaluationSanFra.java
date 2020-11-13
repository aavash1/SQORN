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

import road_network.SanFRN;

public class SQORN2_ANNEvaluationSanFra {

	public static void main(String[] args) {
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
		double perimeter = 1600.00;
		// RandomObjectGeneration
		// <C, U> sanFraGraphCU1, sanFraGraphCU2, sanFraGraphCU3, sanFraGraphCU4
		// <U, C> sanFraGraphUC1, sanFraGraphUC2, sanFraGraphUC3, sanFraGraphUC4
		// <C, C> sanFraGraphCC1, sanFraGraphCC2, sanFraGraphCC3, sanFraGraphCC4
		// <U, U> sanFraGraphUU1, sanFraGraphUU2, sanFraGraphUU3, sanFraGraphUU4

		// NAIVE ///////////////////////
		// <C, U> sanFraGraphCU x 4
		// <U, C> sanFraGraphUC x 4
		// <C, C> sanFraGraphCC x4
		// <U, U> sanFraGraphUU x4

		// VIVET ///////////////////////
		// <C, U> sanFraGraphCU x4
		// <U, C> sanFraGraphUC x 4
		// <C, C> sanFraGraphCC x 4
		// <U, U> sanFraGraphUU x 4

		// CLUSTERED ///////////////////////
		// <C, U> sanFraGraphCU x 4
		// <U, C> sanFraGraphUC x 4
		// <C, C> sanFraGraphCC x 4
		// <U, U> sanFraGraphUU x 4

		Graph sanFraGraphCU1, sanFraGraphCU2, sanFraGraphCU3, sanFraGraphCU4, sanFraGraphUC1, sanFraGraphUC2,
				sanFraGraphUC3, sanFraGraphUC4, sanFraGraphCC1, sanFraGraphCC2, sanFraGraphCC3, sanFraGraphCC4,
				sanFraGraphUU1, sanFraGraphUU2, sanFraGraphUU3, sanFraGraphUU4;

		ArrayList<Graph> listOfGraphs = new ArrayList<Graph>();

		sanFraGraphCU1 = SanFRN.getGraph();// 0
		listOfGraphs.add(sanFraGraphCU1);
		sanFraGraphCU2 = SanFRN.getGraph(); // 1
		listOfGraphs.add(sanFraGraphCU2);
		sanFraGraphCU3 = SanFRN.getGraph(); // 2
		listOfGraphs.add(sanFraGraphCU3);
		sanFraGraphCU4 = SanFRN.getGraph(); // 3
		listOfGraphs.add(sanFraGraphCU4);
		sanFraGraphUC1 = SanFRN.getGraph(); // 4...
		listOfGraphs.add(sanFraGraphUC1);
		sanFraGraphUC2 = SanFRN.getGraph(); // 5
		listOfGraphs.add(sanFraGraphUC2);
		sanFraGraphUC3 = SanFRN.getGraph(); // 6
		listOfGraphs.add(sanFraGraphUC3);
		sanFraGraphUC4 = SanFRN.getGraph(); // 7
		listOfGraphs.add(sanFraGraphUC4);
		sanFraGraphCC1 = SanFRN.getGraph(); // 8...
		listOfGraphs.add(sanFraGraphCC1);
		sanFraGraphCC2 = SanFRN.getGraph(); // 9
		listOfGraphs.add(sanFraGraphCC2);
		sanFraGraphCC3 = SanFRN.getGraph(); // 10
		listOfGraphs.add(sanFraGraphCC3);
		sanFraGraphCC4 = SanFRN.getGraph(); // 11
		listOfGraphs.add(sanFraGraphCC4);
		sanFraGraphUU1 = SanFRN.getGraph(); // 12...
		listOfGraphs.add(sanFraGraphUU1);
		sanFraGraphUU2 = SanFRN.getGraph(); // 13
		listOfGraphs.add(sanFraGraphUU2);
		sanFraGraphUU3 = SanFRN.getGraph(); // 14
		listOfGraphs.add(sanFraGraphUU3);
		sanFraGraphUU4 = SanFRN.getGraph(); // 15
		listOfGraphs.add(sanFraGraphUU4);

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

		String evaluationResultFile = "ResultFiles/" + "SanFrancisco" + "_" + "_ANNs-Naive-Clustereds_VIVET"
				+ UtilsManagment.getNormalDateTime() + ".csv";

		while (!queryParams.isEmpty()) {
			int queryObjNum = queryParams.poll();
			int dataObjNum = dataParams.poll();

			// <C, U>
			RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(sanFraGraphCU1, queryObjNum, dataObjNum,
					true, perimeter);
			RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(sanFraGraphCU2, queryObjNum, dataObjNum,
					true, perimeter);
			RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(sanFraGraphCU3, queryObjNum, dataObjNum,
					true, perimeter);
			RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(sanFraGraphCU4, queryObjNum, dataObjNum,
					true, perimeter);

			// <U, C>
			RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(sanFraGraphUC1, queryObjNum, dataObjNum,
					false, perimeter);
			RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(sanFraGraphUC2, queryObjNum, dataObjNum,
					false, perimeter);
			RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(sanFraGraphUC3, queryObjNum, dataObjNum,
					false, perimeter);
			RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(sanFraGraphUC4, queryObjNum, dataObjNum,
					false, perimeter);

			// <C, C>
			RandomObjectGenerator.generateRandomObjectsOnEdgeWithCentroidForSameDistribution(sanFraGraphCC1,
					queryObjNum, dataObjNum, perimeter);
			RandomObjectGenerator.generateRandomObjectsOnEdgeWithCentroidForSameDistribution(sanFraGraphCC2,
					queryObjNum, dataObjNum, perimeter);
			RandomObjectGenerator.generateRandomObjectsOnEdgeWithCentroidForSameDistribution(sanFraGraphCC3,
					queryObjNum, dataObjNum, perimeter);
			RandomObjectGenerator.generateRandomObjectsOnEdgeWithCentroidForSameDistribution(sanFraGraphCC4,
					queryObjNum, dataObjNum, perimeter);

			// <U, U>
			RandomObjectGenerator.generateUniformRandomObjectsOnMap(sanFraGraphUU1, queryObjNum, dataObjNum);
			RandomObjectGenerator.generateUniformRandomObjectsOnMap(sanFraGraphUU2, queryObjNum, dataObjNum);
			RandomObjectGenerator.generateUniformRandomObjectsOnMap(sanFraGraphUU3, queryObjNum, dataObjNum);
			RandomObjectGenerator.generateUniformRandomObjectsOnMap(sanFraGraphUU4, queryObjNum, dataObjNum);

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

			// TODO: find for SANF
			Map<Integer, LinkedList<Integer>> nodeClusterFromFile = UtilsManagment
					.readNodeClustersFile("ClusterDatasets/SanFrancisco_node-clusters_2019-12-06 17-43-01.csv");

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

			//

		}

	}

}
