package testing;

import java.util.LinkedList;
import java.util.Map;

import algorithm.ANNClustered;
import algorithm.ANNNaive;
import algorithm.ClusteringRoadObjects;
import algorithm.RandomObjectGenerator;
import framework.Graph;
import framework.UtilsManagment;

public class ANNEvaluationCalRealDatasetWithCentroidTest {

	public static void main(String[] args) {
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
		Graph calGraph = new Graph("California");

		String nodeDatasetFile = "Datasets/CAL-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/CAL-Edge_Eid-ESrc-EDest-EDist.csv";
		UtilsManagment.readEdgeFile(calGraph, edgeDatasetFile);
		UtilsManagment.readNodeFile(calGraph, nodeDatasetFile);

		LinkedList<Integer> queryParams = new LinkedList<Integer>();
		LinkedList<Integer> dataParams = new LinkedList<Integer>();
		queryParams.add(10000);
		queryParams.add(10000);
		queryParams.add(10000);
		queryParams.add(10000);
		queryParams.add(10000);
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
		dataParams.add(10000);
		dataParams.add(10000);
		dataParams.add(10000);
		dataParams.add(10000);
		dataParams.add(10000);

		Map<Integer, LinkedList<Integer>> nodeClusterFromFile = UtilsManagment
				.readNodeClustersFile("ClusterDatasets/California_node-clusters_2019-12-06 17-35-41.csv");

		String graphName = calGraph.getDatasetName();

		String evaluationResultFile = "ResultFiles/" + graphName + "_" + "_ANNs-Naive-Clustereds_"
				+ UtilsManagment.getNormalDateTime() + ".csv";
		while (!queryParams.isEmpty()) {
			int queryObjNum = queryParams.poll();
			int dataObjNum = dataParams.poll();

			for (int i = 0; i < 12; i++) {
				if(i<4) {
					//randomObjectwillgenerate <Centroid, Uniform> distribution of <true,false> object
					 RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(calGraph,queryObjNum, dataObjNum, true);
				}
				else if((i>=4)&&(i<7)){
					//randomObjectwillgenerate <Uniform, Centroid> distribution of <true,false> object
					RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(calGraph,queryObjNum, dataObjNum, false);
				}
				else if((i>=7)&&(i<11)){
					//randomObjectwillgenerate <Centroid, Centroid> distribution of <true,false> object
					RandomObjectGenerator.generateRandomObjectsOnEdgeWithCentroidForSameDistribution(calGraph,queryObjNum,dataObjNum);
				}
				
				//RandomObjectGenerator.generateRandomObjectsOnMap6(calGraph, queryObjNum, dataObjNum);
				//RandomObjectGenerator.generateRandomObjectsOnEdgeWithCentroidForSameDistribution(calGraph,queryObjNum,dataObjNum);
				// RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(calGraph,queryObjNum, dataObjNum, true);
				// RandomObjectGenerator.printStatistics();

				String roadObjsOnEdgeCSVFile = "GeneratedFiles/" + graphName + "_Q_" + queryObjNum + "_D_" + dataObjNum
						+ UtilsManagment.getNormalDateTime() + ".csv";

				UtilsManagment.writeRoadObjsOnEdgeFile(calGraph.getObjectsOnEdges(), calGraph.getDatasetName(),
						roadObjsOnEdgeCSVFile);

				// Map<Integer, ArrayList<RoadObject>> objectsOnEdge =
				// UtilsManagment.readRoadObjectFile(roadObjsOnEdgeCSVFile);
				// calGraph.setObjectsOnEdges(objectsOnEdge);

				ANNNaive annNaive = new ANNNaive();
				long startTimeNaive = System.nanoTime();
				annNaive.compute(calGraph, true);
				long computationTimeNaive = System.nanoTime() - startTimeNaive;
				double computationTimeDNaive = (double) computationTimeNaive / 1000000000.0;
				// annNaive.printNearestNeighborSets();
				System.out.println("Time to compute Naive ANN: " + computationTimeDNaive);

				ClusteringRoadObjects clusteringObjects = new ClusteringRoadObjects();
				Map<Integer, LinkedList<Integer>> objectIdClusters = clusteringObjects.clusterWithIndex(calGraph,
						nodeClusterFromFile, true);

				ANNClustered annClustered = new ANNClustered();
				long startTimeClustered = System.nanoTime();
				annClustered.computeWithoutClustering(calGraph, true, nodeClusterFromFile, objectIdClusters);
				long computationTimeClustered = System.nanoTime() - startTimeClustered;
				double computationTimeDClustered = (double) computationTimeClustered / 1000000000.0;
				// ann3.printNearestSets();
				System.out.println("Time to compute Clustered ANN: " + computationTimeDClustered);
				System.out.println();
				if(i<4) {
					System.err.println("---------------------------<C,U>-------------------Finished");
				}
				else if((i>=4)&&(i<7)){
					System.err.println("---------------------------<U,C>-------------------Finished");
				}
				else if((i>=7)&&(i<11)){
					System.err.println("---------------------------<C,C>-------------------Finished");
				}

				UtilsManagment.writeFinalEvaluationResult(calGraph, evaluationResultFile, computationTimeDNaive,
						computationTimeDClustered);
			}
		}

	}

}
