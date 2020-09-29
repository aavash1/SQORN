package experiment;

import java.util.LinkedList;
import java.util.Map;

import algorithm.ANNClusteredOptimizedWithHeuristic;
import algorithm.ANNNaive;

import algorithm.RandomObjectGenerator;
import algorithm.VivetAlgorithm;
import framework.Graph;
import framework.UtilsManagment;
import road_network.CaliforniaRN;

public class SQORN1_ANNEvaluationCal {

	public static void main(String[] args) {
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");

		Graph calGraph = CaliforniaRN.getGraph();
		double perimeter = 1.88;

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

		Map<Integer, LinkedList<Integer>> nodeClusterFromFile = UtilsManagment
				.readNodeClustersFile("ClusterDatasets/California_node-clusters_2019-12-06 17-35-41.csv");

		String graphName = calGraph.getDatasetName();

		String evaluationResultFile = "ResultFiles/" + graphName + "_" + "_ANNs-Naive-Clustereds_VIVET"
				+ UtilsManagment.getNormalDateTime() + ".csv";
		System.err.println("Test is running now...");
		while (!queryParams.isEmpty()) {
			int queryObjNum = queryParams.poll();
			int dataObjNum = dataParams.poll();

			for (int i = 0; i < 16; i++) {
				if (i < 4) {
					String distribution = "<C,U>";
					// randomObjectwillgenerate <Centroid, Uniform> distribution of <true,false>
					// object
					RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(calGraph, queryObjNum, dataObjNum,
							true, perimeter);
					String roadObjsOnEdgeCSVFile = "GeneratedFiles/" + graphName + "_Q_" + queryObjNum + "_D_"
							+ dataObjNum + UtilsManagment.getNormalDateTime() + ".csv";

					UtilsManagment.writeRoadObjsOnEdgeFile(calGraph.getObjectsOnEdges(), calGraph.getDatasetName(),
							roadObjsOnEdgeCSVFile);
					SQORN1_ANNEvaluationCal.executeAlgorithms(calGraph, nodeClusterFromFile, evaluationResultFile,
							distribution);

					System.err.println("---------------------------<C,U>-------------------Finished");
				} else if ((i >= 4) && (i < 8)) {
					String distribution = "<U,C>";
					// randomObjectwillgenerate <Uniform, Centroid> distribution of <true,false>
					// object
					RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(calGraph, queryObjNum, dataObjNum,
							false, perimeter);
					String roadObjsOnEdgeCSVFile = "GeneratedFiles/" + graphName + "_Q_" + queryObjNum + "_D_"
							+ dataObjNum + UtilsManagment.getNormalDateTime() + ".csv";

					UtilsManagment.writeRoadObjsOnEdgeFile(calGraph.getObjectsOnEdges(), calGraph.getDatasetName(),
							roadObjsOnEdgeCSVFile);
					SQORN1_ANNEvaluationCal.executeAlgorithms(calGraph, nodeClusterFromFile, evaluationResultFile,
							distribution);

					System.err.println("---------------------------<U,C>-------------------Finished");
				} else if ((i >= 8) && (i < 12)) {
					String distribution = "<C,C>";
					// randomObjectwillgenerate <Centroid, Centroid> distribution of <true,false>
					// object
					RandomObjectGenerator.generateRandomObjectsOnEdgeWithCentroidForSameDistribution(calGraph,
							queryObjNum, dataObjNum, perimeter);
					String roadObjsOnEdgeCSVFile = "GeneratedFiles/" + graphName + "_Q_" + queryObjNum + "_D_"
							+ dataObjNum + UtilsManagment.getNormalDateTime() + ".csv";

					UtilsManagment.writeRoadObjsOnEdgeFile(calGraph.getObjectsOnEdges(), calGraph.getDatasetName(),
							roadObjsOnEdgeCSVFile);
					SQORN1_ANNEvaluationCal.executeAlgorithms(calGraph, nodeClusterFromFile, evaluationResultFile,
							distribution);

					System.err.println("---------------------------<C,C>-------------------Finished");
				} else if ((i >= 12) && (i < 16)) {
					String distribution = "<U,U>";
					// randomObjectwillgenerate <Uniform, Uniform> distribution of <true,false>
					// object
					RandomObjectGenerator.generateRandomObjectsOnMap6(calGraph, queryObjNum, dataObjNum);
					String roadObjsOnEdgeCSVFile = "GeneratedFiles/" + graphName + "_Q_" + queryObjNum + "_D_"
							+ dataObjNum + UtilsManagment.getNormalDateTime() + ".csv";

					UtilsManagment.writeRoadObjsOnEdgeFile(calGraph.getObjectsOnEdges(), calGraph.getDatasetName(),
							roadObjsOnEdgeCSVFile);
					SQORN1_ANNEvaluationCal.executeAlgorithms(calGraph, nodeClusterFromFile, evaluationResultFile,
							distribution);

					System.err.println("---------------------------<U,U>-------------------Finished");
				}

			}
		}
		System.err.println("Experiments has finished successfully.");

	}

	public static void executeAlgorithms(Graph graph, Map<Integer, LinkedList<Integer>> nodeClusterFromFile,
			String evaluationResultFile, String distributionCat) {

		ANNNaive annNaive = new ANNNaive();
		long startTimeNaive = System.nanoTime();
		annNaive.compute(graph, true);
		long computationTimeNaive = System.nanoTime() - startTimeNaive;
		double computationTimeDNaive = (double) computationTimeNaive / 1000000000.0;
		// annNaive.printNearestNeighborSets();
		System.out.println("Time to compute Naive ANN: " + computationTimeDNaive);
		System.out.println();

		ANNClusteredOptimizedWithHeuristic annClustered = new ANNClusteredOptimizedWithHeuristic();
		long startTimeClustered = System.nanoTime();
		annClustered.computeWithTimeAndHeuristicWithoutClustering(graph, true, nodeClusterFromFile);
		long computationTimeClustered = System.nanoTime() - startTimeClustered;
		double computationTimeDClustered = (double) computationTimeClustered / 1000000000.0;
		// ann3.printNearestSets();
		System.out.println("Time to compute Clustered ANN: " + computationTimeDClustered);
		System.out.println();

		VivetAlgorithm annVivet = new VivetAlgorithm();
		long startTimeVivet = System.nanoTime();
		annVivet.compute(graph);
		long computationTimeVivet = System.nanoTime() - startTimeVivet;
		double computationTimeDVivet = (double) computationTimeVivet / 1000000000.0;
		// annVivet.printNearestNeighborSets();
		System.out.println("Time to compute VIVET ANN: " + computationTimeDVivet);
		System.out.println();

		UtilsManagment.writeFinalEvaluationResultForThreeMethods(graph, evaluationResultFile, computationTimeDNaive,
				computationTimeDClustered, computationTimeDVivet, distributionCat);

	}

}
