package testing;

import java.util.ArrayList;
import java.util.Map;

import algorithm.ANNClustered;
import algorithm.ANNNaive;
import algorithm.ClusteringNodes;
import algorithm.RandomObjectGenerator;
import framework.Edge;
import framework.Graph;
import framework.Node;
import framework.RoadObject;
import framework.UtilsManagment;

public class ANNEvaluationNARealDatasetTest {

	public static void main(String[] args) {
		Graph naGraph = new Graph();

		String nodeDatasetFile = "Datasets/NA-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/NA-Edge_Eid-ESrc-EDest-EDist.csv";
		String objectDatasetFile = "GeneratedFiles/NorthAmerica_2019-11-27 16-48-57.csv";

		UtilsManagment.readNodeFile(naGraph, nodeDatasetFile);
		UtilsManagment.readEdgeFile(naGraph, edgeDatasetFile);

		Map<Integer, ArrayList<RoadObject>> objectsOnEdge = UtilsManagment.readRoadObjectFile(objectDatasetFile);
		naGraph.setObjectsOnEdges(objectsOnEdge);

//		RandomObjectGenerator.generateRandomObjectsOnMap(naGraph, 0.2);
//
//		um.writeRoadObjsOnEdgeFile(naGraph.getObjectsOnEdges(), naGraph.getDatasetName());
//		um.writeObjStats(naGraph);

		System.out.println();
		ANNNaive annNaive = new ANNNaive();
		ANNClustered annClustered = new ANNClustered();
//
//		long startTimeNaive = System.nanoTime();
//		annNaive.compute(naGraph, true);
//		long graphLoadingTimeNaive = System.nanoTime() - startTimeNaive;
//		double graphLoadingTimeDNaive = (double) graphLoadingTimeNaive / 1000000000.0;
//		// annNaive.printNearestNeighborSets();
//		System.out.println("Time to compute Naive ANN: " + graphLoadingTimeDNaive);

		ANNClustered ann3 = new ANNClustered();
		long startTimeClustered = System.nanoTime();
		ann3.computeWithTime(naGraph, true);
		long graphLoadingTimeClustered = System.nanoTime() - startTimeClustered;
		double graphLoadingTimeDClustered = (double) graphLoadingTimeClustered / 1000000000.0;
		// ann3.printNearestSets();
		System.out.println("Time to compute Clustered ANN: " + graphLoadingTimeDClustered);
//
//		int totalNumberOfNodes = naGraph.getNodesWithInfo().size();
//		int totalNumberOfEdges = naGraph.getEdgesWithInfo().size();
//		int totalNumberOfRandomEdgesSelected = RandomObjectGenerator2.getTotalNumberOfRandomEdges();
//		int totalNumberOfObjectsGenerated = naGraph.getObjectsWithInfo().size();
//		int totalNumberOfTrueObjects = naGraph.getTotalNumberOfTrueObjects();
//		int totalNumberOfFalseObjects = naGraph.getTotalNumberOfFalseObjects();
//		int totalNumberOfNodeClusters = annClustered.getSizeOfNodeClusters();
//		int totalNumberOfObjectClusters = annClustered.getSizeOfObjectClusters();
//
//		um.writeNaiveAndClusteredANNTestResult(totalNumberOfNodes, totalNumberOfEdges, totalNumberOfRandomEdgesSelected,
//				totalNumberOfObjectsGenerated, totalNumberOfTrueObjects, totalNumberOfFalseObjects,
//				totalNumberOfNodeClusters, totalNumberOfObjectClusters, graphLoadingTimeDNaive,
//				graphLoadingTimeDClustered);

	}

}
