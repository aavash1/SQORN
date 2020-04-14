package testing;

import java.util.LinkedList;
import java.util.Map;

import algorithm.ANNClustered;
import algorithm.ANNNaive;

import algorithm.ClusteringRoadObjects;
import algorithm.RandomObjectGenerator;
import framework.Graph;

import framework.UtilsManagment;

public class ANNEvaluationSanJoRealDatasetWithCentroidTest {

	public static void main(String[] args) {
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
		Graph sanJoaGraph = new Graph("SanJoaquin");

		String nodeDatasetFile = "Datasets/SJ-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/SJ-Edge_Eid-ESrc-EDest-EDist.csv";
		UtilsManagment.readEdgeFile(sanJoaGraph, edgeDatasetFile);
		UtilsManagment.readNodeFile(sanJoaGraph, nodeDatasetFile);
		double parameter=800.85;

		LinkedList<Integer> queryParams = new LinkedList<Integer>();
		LinkedList<Integer> dataParams = new LinkedList<Integer>();
//		queryParams.add(10000);
//		queryParams.add(10000);
//		queryParams.add(10000);
//		queryParams.add(10000);
//		queryParams.add(10000);
		queryParams.add(20000);
		queryParams.add(30000);
		queryParams.add(50000);
//		queryParams.add(70000);
//		queryParams.add(100000);

//		dataParams.add(20000);
//		dataParams.add(30000);
//		dataParams.add(50000);
//		dataParams.add(70000);
//		dataParams.add(100000);
//		dataParams.add(10000);
//		dataParams.add(10000);
		dataParams.add(10000);
		dataParams.add(10000);
		dataParams.add(10000);

		Map<Integer, LinkedList<Integer>> nodeClusterFromFile = UtilsManagment
				.readNodeClustersFile("ClusterDatasets/SanJoaquin_node-clusters_2019-12-06 17-55-01.csv");
		

		String graphName = sanJoaGraph.getDatasetName();

		String evaluationResultFile = "ResultFiles/" + graphName + "_" + "_ANNs-Naive-Clustereds_"
				+ UtilsManagment.getNormalDateTime() + ".csv";
		System.err.println("Test is running now...");
		while (!queryParams.isEmpty()) {
			int queryObjNum = queryParams.poll();
			int dataObjNum = dataParams.poll();

			for (int i = 0; i < 15; i++) {
				if (i < 5) {
					// randomObjectwillgenerate <Centroid, Uniform> distribution of <true,false>
					// object
					RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(sanJoaGraph, queryObjNum, dataObjNum,
							true,parameter);
					String roadObjsOnEdgeCSVFile = "GeneratedFiles/" + graphName + "_Q_" + queryObjNum + "_D_"
							+ dataObjNum + UtilsManagment.getNormalDateTime() + ".csv";

					UtilsManagment.writeRoadObjsOnEdgeFile(sanJoaGraph.getObjectsOnEdges(), sanJoaGraph.getDatasetName(),
							roadObjsOnEdgeCSVFile);
					ANNEvaluationSanJoRealDatasetWithCentroidTest.executeAlgorithms(sanJoaGraph, nodeClusterFromFile,
							evaluationResultFile);
					System.err.println("---------------------------<C,U>-------------------Finished");
				} else if ((i >= 5) && (i < 10)) {
					// randomObjectwillgenerate <Uniform, Centroid> distribution of <true,false>
					// object
					RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(sanJoaGraph, queryObjNum, dataObjNum,
							false,parameter);
					String roadObjsOnEdgeCSVFile = "GeneratedFiles/" + graphName + "_Q_" + queryObjNum + "_D_"
							+ dataObjNum + UtilsManagment.getNormalDateTime() + ".csv";

					UtilsManagment.writeRoadObjsOnEdgeFile(sanJoaGraph.getObjectsOnEdges(), sanJoaGraph.getDatasetName(),
							roadObjsOnEdgeCSVFile);
					ANNEvaluationSanJoRealDatasetWithCentroidTest.executeAlgorithms(sanJoaGraph, nodeClusterFromFile,
							evaluationResultFile);
					System.err.println("---------------------------<U,C>-------------------Finished");
				} else if ((i >= 10) && (i < 15)) {
					// randomObjectwillgenerate <Centroid, Centroid> distribution of <true,false>
					// object
					RandomObjectGenerator.generateRandomObjectsOnEdgeWithCentroidForSameDistribution(sanJoaGraph,
							queryObjNum, dataObjNum,parameter);
					String roadObjsOnEdgeCSVFile = "GeneratedFiles/" + graphName + "_Q_" + queryObjNum + "_D_"
							+ dataObjNum + UtilsManagment.getNormalDateTime() + ".csv";

					UtilsManagment.writeRoadObjsOnEdgeFile(sanJoaGraph.getObjectsOnEdges(), sanJoaGraph.getDatasetName(),
							roadObjsOnEdgeCSVFile);
					ANNEvaluationSanJoRealDatasetWithCentroidTest.executeAlgorithms(sanJoaGraph, nodeClusterFromFile,
							evaluationResultFile);
					System.err.println("---------------------------<C,C>-------------------Finished");
				}

//				String roadObjsOnEdgeCSVFile = "GeneratedFiles/" + graphName + "_Q_" + queryObjNum + "_D_" + dataObjNum
//						+ UtilsManagment.getNormalDateTime() + ".csv";
//
//				UtilsManagment.writeRoadObjsOnEdgeFile(sanJoaGraph.getObjectsOnEdges(), sanJoaGraph.getDatasetName(),
//						roadObjsOnEdgeCSVFile);
//
//				// Map<Integer, ArrayList<RoadObject>> objectsOnEdge =
//				// UtilsManagment.readRoadObjectFile(roadObjsOnEdgeCSVFile);
//				// calGraph.setObjectsOnEdges(objectsOnEdge);
//
//				ANNNaive annNaive = new ANNNaive();
//				long startTimeNaive = System.nanoTime();
//				annNaive.compute(sanJoaGraph, true);
//				long computationTimeNaive = System.nanoTime() - startTimeNaive;
//				double computationTimeDNaive = (double) computationTimeNaive / 1000000000.0;
//				// annNaive.printNearestNeighborSets();
//				System.out.println("Time to compute Naive ANN: " + computationTimeDNaive);
//
//				ClusteringRoadObjects clusteringObjects = new ClusteringRoadObjects();
//				Map<Integer, LinkedList<Integer>> objectIdClusters = clusteringObjects.clusterWithIndex(sanJoaGraph,
//						nodeClusterFromFile, true);
//
//				ANNClustered annClustered = new ANNClustered();
//				long startTimeClustered = System.nanoTime();
//				annClustered.computeWithoutClustering(sanJoaGraph, true, nodeClusterFromFile, objectIdClusters);
//				long computationTimeClustered = System.nanoTime() - startTimeClustered;
//				double computationTimeDClustered = (double) computationTimeClustered / 1000000000.0;
//				// ann3.printNearestSets();
//				System.out.println("Time to compute Clustered ANN: " + computationTimeDClustered);
//				System.out.println();
//				if(i<4) {
//					System.err.println("---------------------------<C,U>-------------------Finished");
//				}
//				else if((i>=4)&&(i<7)){
//					System.err.println("---------------------------<U,C>-------------------Finished");
//				}
//				else if((i>=7)&&(i<11)){
//					System.err.println("---------------------------<C,C>-------------------Finished");
//				}
//
//				UtilsManagment.writeFinalEvaluationResult(sanJoaGraph, evaluationResultFile, computationTimeDNaive,
//						computationTimeDClustered);
			}
		}
		System.err.println("Experiment has completed successfully");

	}
	public static void executeAlgorithms(Graph graph, Map<Integer, LinkedList<Integer>> nodeClusterFromFile,
			String evaluationResultFile) {
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
				computationTimeDClustered);

	}

}
