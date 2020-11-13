package testing;

import java.util.LinkedList;
import java.util.Map;

import algorithm.ANNClustered;
import algorithm.ANNNaive;
import algorithm.ClusteringRoadObjects;
import algorithm.RandomObjectGenerator;
import algorithm.VivetAlgorithm;
import framework.Graph;
import framework.RoadObject;
import framework.UtilsManagment;

public class VivetAlgorithmCalDataset {

	public static void main(String[] args) {
		
		Graph calGraph = new Graph("California");

		String nodeDatasetFile = "Datasets/CAL-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/CAL-Edge_Eid-ESrc-EDest-EDist.csv";
		UtilsManagment.readEdgeFile(calGraph, edgeDatasetFile);
		UtilsManagment.readNodeFile(calGraph, nodeDatasetFile);
		
		RandomObjectGenerator.generateRandomObjectsOnMap6(calGraph, 30000, 20000);
		
		String roadObjsOnEdgeCSVFile = "GeneratedFiles/" + calGraph.getDatasetName() + "_Q_" + 30000 + "_D_" + 20000
				+ "_" + UtilsManagment.getNormalDateTime() + ".csv";

		UtilsManagment.writeRoadObjsOnEdgeFile(calGraph.getObjectsOnEdges(), calGraph.getDatasetName(), roadObjsOnEdgeCSVFile);
		
//		roadNetwork.printGraph();
//		System.out.println();
//		roadNetwork.printEdgesInfo();
//		System.out.println();
//		roadNetwork.printNodesInfo();
//		System.out.println();
				

		//calGraph.printObjectsOnEdges();
		
		VivetAlgorithm vivetAlg = new VivetAlgorithm();
		long startTimeVivet = System.nanoTime();
		vivetAlg.compute(calGraph);
		long vivetAlgTime = System.nanoTime() - startTimeVivet;
		double vivetAlgTimeD = (double) vivetAlgTime / 1000000000.0;
		System.out.println("Time to compute VIVET ANN: " + vivetAlgTimeD);
		
		
		ANNNaive annNaive = new ANNNaive();
		long startTimeNaive = System.nanoTime();
		annNaive.compute(calGraph, true);
		long computationTimeNaive = System.nanoTime() - startTimeNaive;
		double computationTimeDNaive = (double) computationTimeNaive / 1000000000.0;
		// annNaive.printNearestNeighborSets();
		System.out.println("Time to compute Naive ANN: " + computationTimeDNaive);

		
		Map<Integer, LinkedList<Integer>> nodeClusterFromFile = UtilsManagment
				.readNodeClustersFile("ClusterDatasets/California_node-clusters_2019-12-06 17-35-41.csv");
		ClusteringRoadObjects clusteringObjects = new ClusteringRoadObjects();
		Map<Integer, LinkedList<Integer>> objectIdClusters = clusteringObjects.clusterWithIndex3(calGraph,
				nodeClusterFromFile, true);

		ANNClustered annClustered = new ANNClustered();
		long startTimeClustered = System.nanoTime();
		annClustered.computeWithoutClustering(calGraph, true, nodeClusterFromFile, objectIdClusters);
		long computationTimeClustered = System.nanoTime() - startTimeClustered;
		double computationTimeDClustered = (double) computationTimeClustered / 1000000000.0;
		// ann3.printNearestSets();
		System.out.println("Time to compute Clustered ANN: " + computationTimeDClustered);
		System.out.println();
		
	}

}
