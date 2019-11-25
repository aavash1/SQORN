package testing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

import org.apache.commons.math3.random.RandomDataGenerator;

import algorithm.ANNClustered;
import algorithm.ANNNaive;
import algorithm.ClusteringNodes;
import algorithm.ClusteringRoadObjects;
import algorithm.RandomObjectGenerator;
import framework.Graph;
import framework.RoadObject;
import framework.UtilsManagment;

public class ANNEvaluationCalRealDatasetTest {

	public static void main(String[] args) {
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

		dataParams.add(20000);
		dataParams.add(30000);
		dataParams.add(50000);
		dataParams.add(70000);
		dataParams.add(100000);
		String filename = "QueryResults/" + calGraph.getDatasetName();
		ANNNaive annNaive = new ANNNaive(); //
		ANNClustered annClustered = new ANNClustered();
		for (int i = 0; i < 1; i++) {
			String roadObjFileName = RandomObjectGenerator.generateRandomObjectsOnMap6(calGraph, queryParams.poll(),
					dataParams.poll());
			//UtilsManagment.writeRoadObjsOnEdgeFile(calGraph.getObjectsOnEdges(), calGraph.getDatasetName(),
			//		roadObjFileName);

			//Map<Integer, ArrayList<RoadObject>> objectsOnEdge = UtilsManagment.readRoadObjectFile(roadObjFileName);
			//calGraph.setObjectsOnEdges(objectsOnEdge);

			double timeForANNNaive = annNaive.computeReturnTime(calGraph, true);

			double timeForClusteredANN = annClustered.compute(calGraph, true);

			UtilsManagment.writeFinalEvaluationResult(calGraph, filename, timeForANNNaive, timeForClusteredANN);

		}

//		ClusteringNodes clusteringNodes = new ClusteringNodes();
//
//		ClusteringRoadObjects clusteringObjects = new ClusteringRoadObjects();
//		Map<Integer, LinkedList<Integer>> nodeIdClusters = clusteringNodes.cluster(calGraph);
//		Map<Integer, LinkedList<Integer>> objectIdClusters = clusteringObjects.clusterWithIndex(calGraph,
//				nodeIdClusters, true);
//
//		long startTimeClustered = System.nanoTime();
//		annClustered.computeWithoutClustering(calGraph, true, nodeIdClusters, objectIdClusters);
//		long graphLoadingTimeClustered = System.nanoTime() - startTimeClustered;
//		double graphLoadingTimeDClustered = (double) graphLoadingTimeClustered / 1000000000.0;
//		System.out.println("Time to compute Clustered ANN: " + graphLoadingTimeDClustered);

	}

}
