package experiment;

import java.util.LinkedList;
import java.util.Map;

import algorithm.ANNClustered;
import algorithm.ANNNaive;

import algorithm.ClusteringRoadObjects;
import algorithm.RandomObjectGenerator;
import framework.Graph;

import framework.UtilsManagment;

public class SQORN1_ANNEvaluationSanJo {

	public static void main(String[] args) {
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
		Graph sanJoaGraph = new Graph("SanJoaquin");

		String nodeDatasetFile = "Datasets/SJ-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/SJ-Edge_Eid-ESrc-EDest-EDist.csv";
		UtilsManagment.readEdgeFile(sanJoaGraph, edgeDatasetFile);
		UtilsManagment.readNodeFile(sanJoaGraph, nodeDatasetFile);
		double perimeter=800.85;

		LinkedList<Integer> queryParams = new LinkedList<Integer>();
		LinkedList<Integer> dataParams = new LinkedList<Integer>();
//		queryParams.add(30000);
//		queryParams.add(30000);
//		queryParams.add(30000);
//		queryParams.add(30000);
//		queryParams.add(30000);
		queryParams.add(20000);
		queryParams.add(30000);
		queryParams.add(50000);
		queryParams.add(70000);
		queryParams.add(100000);
//
//		dataParams.add(20000);
//		dataParams.add(30000);
//		dataParams.add(50000);
//		dataParams.add(70000);
//		dataParams.add(100000);
		dataParams.add(30000);
		dataParams.add(30000);
		dataParams.add(30000);
		dataParams.add(30000);
		dataParams.add(30000);

		Map<Integer, LinkedList<Integer>> nodeClusterFromFile = UtilsManagment
				.readNodeClustersFile("ClusterDatasets/SanJoaquin_node-clusters_2019-12-06 17-55-01.csv");
		

		String graphName = sanJoaGraph.getDatasetName();

		String evaluationResultFile = "ResultFiles/" + graphName + "_" + "_ANNs-Naive-Clustereds_"
				+ UtilsManagment.getNormalDateTime() + ".csv";
		System.err.println("Test is running now...");
		while (!queryParams.isEmpty()) {
			int queryObjNum = queryParams.poll();
			int dataObjNum = dataParams.poll();

			for (int i = 0; i < 16; i++) {
				if (i < 4) {
					String distribution="<C,U>";
					// randomObjectwillgenerate <Centroid, Uniform> distribution of <true,false>
					// object
					RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(sanJoaGraph, queryObjNum, dataObjNum,
							true, perimeter);
					String roadObjsOnEdgeCSVFile = "GeneratedFiles/" + graphName + "_Q_" + queryObjNum + "_D_"
							+ dataObjNum + UtilsManagment.getNormalDateTime() + ".csv";

					UtilsManagment.writeRoadObjsOnEdgeFile(sanJoaGraph.getObjectsOnEdges(), sanJoaGraph.getDatasetName(),
							roadObjsOnEdgeCSVFile);
					SQORN1_ANNEvaluationCal.executeAlgorithms(sanJoaGraph, nodeClusterFromFile,
							evaluationResultFile,distribution);

					System.err.println("---------------------------<C,U>-------------------Finished");
				} else if ((i >= 4) && (i < 8)) {
					String distribution="<U,C>";
					// randomObjectwillgenerate <Uniform, Centroid> distribution of <true,false>
					// object
					RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(sanJoaGraph, queryObjNum, dataObjNum,
							false, perimeter);
					String roadObjsOnEdgeCSVFile = "GeneratedFiles/" + graphName + "_Q_" + queryObjNum + "_D_"
							+ dataObjNum + UtilsManagment.getNormalDateTime() + ".csv";

					UtilsManagment.writeRoadObjsOnEdgeFile(sanJoaGraph.getObjectsOnEdges(), sanJoaGraph.getDatasetName(),
							roadObjsOnEdgeCSVFile);
					SQORN1_ANNEvaluationCal.executeAlgorithms(sanJoaGraph, nodeClusterFromFile,
							evaluationResultFile,distribution);

					System.err.println("---------------------------<U,C>-------------------Finished");
				} else if ((i >= 8) && (i < 12)) {
					String distribution="<C,C>";
					// randomObjectwillgenerate <Centroid, Centroid> distribution of <true,false>
					// object
					RandomObjectGenerator.generateRandomObjectsOnEdgeWithCentroidForSameDistribution(sanJoaGraph, queryObjNum, dataObjNum,
							perimeter);
					String roadObjsOnEdgeCSVFile = "GeneratedFiles/" + graphName + "_Q_" + queryObjNum + "_D_"
							+ dataObjNum + UtilsManagment.getNormalDateTime() + ".csv";

					UtilsManagment.writeRoadObjsOnEdgeFile(sanJoaGraph.getObjectsOnEdges(), sanJoaGraph.getDatasetName(),
							roadObjsOnEdgeCSVFile);
					SQORN1_ANNEvaluationCal.executeAlgorithms(sanJoaGraph, nodeClusterFromFile,
							evaluationResultFile,distribution);

					System.err.println("---------------------------<C,C>-------------------Finished");
				}
				else if ((i >= 12) && (i < 16)) {
					String distribution="<U,U>";
					// randomObjectwillgenerate <Uniform, Uniform> distribution of <true,false>
					// object
					RandomObjectGenerator.generateRandomObjectsOnMap6(sanJoaGraph, queryObjNum, dataObjNum);
					String roadObjsOnEdgeCSVFile = "GeneratedFiles/" + graphName + "_Q_" + queryObjNum + "_D_"
							+ dataObjNum + UtilsManagment.getNormalDateTime() + ".csv";

					UtilsManagment.writeRoadObjsOnEdgeFile(sanJoaGraph.getObjectsOnEdges(), sanJoaGraph.getDatasetName(),
							roadObjsOnEdgeCSVFile);
					SQORN1_ANNEvaluationCal.executeAlgorithms(sanJoaGraph, nodeClusterFromFile,
							evaluationResultFile,distribution);

					System.err.println("---------------------------<U,U>-------------------Finished");
				}

			}
		}
		System.err.println("Experiment has completed successfully");

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

		ClusteringRoadObjects clusteringObjects = new ClusteringRoadObjects();
		Map<Integer, LinkedList<Integer>> objectIdClusters = clusteringObjects.clusterWithIndex(graph,
				nodeClusterFromFile, true);

		ANNClustered annClustered = new ANNClustered();
		long startTimeClustered = System.nanoTime();
		annClustered.computeWithoutClustering(graph, true, nodeClusterFromFile, objectIdClusters);
		long computationTimeClustered = System.nanoTime() - startTimeClustered;
		double computationTimeDClustered = (double) computationTimeClustered / 1000000000.0;
		// ann3.printNearestSets();
		System.out.println("Time to compute Clustered ANN: " + computationTimeDClustered);
		System.out.println();

		UtilsManagment.writeFinalEvaluationResult(graph, evaluationResultFile, computationTimeDNaive,
				computationTimeDClustered, distributionCat);

	}

}
