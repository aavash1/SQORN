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
import road_network.NorthWestUSARN;

public class SQORN3_ANNEvaluationNW {

	public static void main(String[] args) {
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
		Graph northWestGraph = NorthWestUSARN.getGraph();
		int valueOfSD = 2;
		int datasetScaleFactor = 3000;

		LinkedList<Integer> queryParams = new LinkedList<Integer>();
		LinkedList<Integer> dataParams = new LinkedList<Integer>();

		queryParams.add(1024);
		queryParams.add(65600);


		dataParams.add(65600);
		dataParams.add(2048);

		Map<Integer, LinkedList<Integer>> nodeClusterFromFile = UtilsManagment
				.readNodeClustersFile("ClusterDatasets/NorthWestUSA_node-clusters_2020-12-12 07-56-51.csv");

		String graphName = northWestGraph.getDatasetName();

		String evaluationResultFile = "ResultFiles/" + graphName + "_" + "_ANNs-Naive-Clustereds_VIVET"
				+ UtilsManagment.getNormalDateTime() + ".csv";
		System.err.println("Test is running now...");
		while (!queryParams.isEmpty()) {
			int queryObjNum = queryParams.poll();
			int dataObjNum = dataParams.poll();

			for (int i = 0; i < 3; i++) {
				if (i < 1) {
					String distribution = "<U,U>";
					// randomObjectwillgenerate <Uniform, Uniform> distribution of <true,false>
					// object
					RandomObjectGenerator.generateUniformRandomObjectsOnMap(northWestGraph, queryObjNum, dataObjNum);
					String roadObjsOnEdgeCSVFile = "GeneratedFiles/" + graphName + "_Q_" + queryObjNum + "_D_"
							+ dataObjNum + UtilsManagment.getNormalDateTime() + ".csv";

					UtilsManagment.writeRoadObjsOnEdgeFile(northWestGraph.getObjectsOnEdges(), northWestGraph.getDatasetName(),
							roadObjsOnEdgeCSVFile);
					SQORN3_ANNEvaluationNW.executeAlgorithms(northWestGraph, nodeClusterFromFile, evaluationResultFile,
							distribution);

					System.err.println("---------------------------<U,U>-------------------Finished");
				} else if ((i >= 1) && (i < 2)) {
					String distribution = "<U,C>";
					// randomObjectwillgenerate <Uniform, Centroid> distribution of <true,false>
					// object
					RandomObjectGenerator.zgenerateCUUCDistribution(northWestGraph, valueOfSD, datasetScaleFactor,
							queryObjNum, dataObjNum, false);
					String roadObjsOnEdgeCSVFile = "GeneratedFiles/" + graphName + "_Q_" + queryObjNum + "_D_"
							+ dataObjNum + UtilsManagment.getNormalDateTime() + ".csv";

					UtilsManagment.writeRoadObjsOnEdgeFile(northWestGraph.getObjectsOnEdges(), northWestGraph.getDatasetName(),
							roadObjsOnEdgeCSVFile);
					SQORN3_ANNEvaluationNW.executeAlgorithms(northWestGraph, nodeClusterFromFile, evaluationResultFile,
							distribution);

					System.err.println("---------------------------<U,C>-------------------Finished");
				}
					else if ((i >= 2) && (i < 3)) {
						String distribution = "<C,U>";
						// randomObjectwillgenerate <Uniform, Centroid> distribution of <true,false>
						// object
						RandomObjectGenerator.zgenerateCUUCDistribution(northWestGraph, valueOfSD, datasetScaleFactor,
								queryObjNum, dataObjNum, true);
						String roadObjsOnEdgeCSVFile = "GeneratedFiles/" + graphName + "_Q_" + queryObjNum + "_D_"
								+ dataObjNum + UtilsManagment.getNormalDateTime() + ".csv";

						UtilsManagment.writeRoadObjsOnEdgeFile(northWestGraph.getObjectsOnEdges(), northWestGraph.getDatasetName(),
								roadObjsOnEdgeCSVFile);
						SQORN3_ANNEvaluationNW.executeAlgorithms(northWestGraph, nodeClusterFromFile, evaluationResultFile,
								distribution);

						System.err.println("---------------------------<C,U>-------------------Finished");
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
