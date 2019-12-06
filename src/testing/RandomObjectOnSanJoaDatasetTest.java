package testing;

import java.util.LinkedList;
import java.util.Map;

import algorithm.ANNClustered;
import algorithm.ANNNaive;
import algorithm.ClusteringRoadObjects;
import algorithm.RandomObjectGenerator;
import framework.Graph;
import framework.UtilsManagment;

public class RandomObjectOnSanJoaDatasetTest {
	public static void main(String[] args) {
		Graph sanJoaquinGraph = new Graph("SanJoaquin");

		String nodeDatasetFile = "Datasets/SJ-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/SJ-Edge_Eid-ESrc-EDest-EDist.csv";
		UtilsManagment.readEdgeFile(sanJoaquinGraph, edgeDatasetFile);
		UtilsManagment.readNodeFile(sanJoaquinGraph, nodeDatasetFile);

		LinkedList<Integer> queryParams = new LinkedList<Integer>();
		LinkedList<Integer> dataParams = new LinkedList<Integer>();
		queryParams.add(10000);
		queryParams.add(10000);
		queryParams.add(10000);
		queryParams.add(10000);
		queryParams.add(10000);

		dataParams.add(20000);
		dataParams.add(30000);
		dataParams.add(50000);
		dataParams.add(70000);
		dataParams.add(100000);


		Map<Integer, LinkedList<Integer>> nodeClusterFromFile = UtilsManagment
				.readNodeClustersFile("ClusterDatasets/SanJoaquin_node-clusters_2019-12-06 17-55-01.csv");

		
		String graphName = sanJoaquinGraph.getDatasetName();
		
		String evaluationResultFile = "ResultFiles/" + graphName + "_" + "_ANNs-Naive-Clustereds_"
				+ UtilsManagment.getNormalDateTime() + ".csv";
		while (!queryParams.isEmpty()) { 
			int queryObjNum = queryParams.poll();
			int dataObjNum = dataParams.poll();
			
			for (int i = 0; i < 10; i++) {
				RandomObjectGenerator.generateRandomObjectsOnMap6(sanJoaquinGraph, queryObjNum, dataObjNum);
				// RandomObjectGenerator.printStatistics();
				
				String roadObjsOnEdgeCSVFile = "GeneratedFiles/" + graphName + "_Q_" + queryObjNum + "_D_" + dataObjNum
						+ UtilsManagment.getNormalDateTime() + ".csv";

				UtilsManagment.writeRoadObjsOnEdgeFile(sanJoaquinGraph.getObjectsOnEdges(), sanJoaquinGraph.getDatasetName(), roadObjsOnEdgeCSVFile);

				// Map<Integer, ArrayList<RoadObject>> objectsOnEdge =
				// UtilsManagment.readRoadObjectFile(roadObjsOnEdgeCSVFile);
				// calGraph.setObjectsOnEdges(objectsOnEdge);

				ANNNaive annNaive = new ANNNaive();
				long startTimeNaive = System.nanoTime();
				annNaive.compute(sanJoaquinGraph, true);
				long computationTimeNaive = System.nanoTime() - startTimeNaive;
				double computationTimeDNaive = (double) computationTimeNaive / 1000000000.0;
				// annNaive.printNearestNeighborSets();
				System.out.println("Time to compute Naive ANN: " + computationTimeDNaive);

				ClusteringRoadObjects clusteringObjects = new ClusteringRoadObjects();
				Map<Integer, LinkedList<Integer>> objectIdClusters = clusteringObjects.clusterWithIndex(sanJoaquinGraph,
						nodeClusterFromFile, true);

				ANNClustered annClustered = new ANNClustered();
				long startTimeClustered = System.nanoTime();
				annClustered.computeWithoutClustering(sanJoaquinGraph, true, nodeClusterFromFile, objectIdClusters);
				long computationTimeClustered = System.nanoTime() - startTimeClustered;
				double computationTimeDClustered = (double) computationTimeClustered / 1000000000.0;
				// ann3.printNearestSets();
				System.out.println("Time to compute Clustered ANN: " + computationTimeDClustered);
				System.out.println();

				
				UtilsManagment.writeFinalEvaluationResult(sanJoaquinGraph, evaluationResultFile, computationTimeDNaive,
						computationTimeDClustered);
			}
		}
		
		


	}

}
