package testing;

import java.util.ArrayList;

import algorithm.ANNClustered;
import algorithm.ANNNaive;
import algorithm.RandomObjectGenerator2;
import framework.Edge;
import framework.Graph;
import framework.Node;
import framework.RoadObject;
import framework.UtilsManagment;

public class ANNEvaluationNARealDatasetTest {

	public static void main(String[] args) {
		UtilsManagment um = new UtilsManagment();
		Graph naGraph = new Graph();

		String nodeDatasetFile = "Datasets//NA-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/NA-Edge_Eid-ESrc-EDest-EDist.csv";

		naGraph = um.readEdgeFileReturnGraph(edgeDatasetFile);
		ArrayList<Node> calNodesInfo = um.readNodeFile(nodeDatasetFile);
		naGraph.setNodesWithInfo(calNodesInfo);
		ArrayList<Edge> calEdgeInfo = um.readEdgeFile(edgeDatasetFile);
		naGraph.setEdgeWithInfo(calEdgeInfo);

		RandomObjectGenerator2.generateRandomObjectsOnMap(naGraph, 0.2);

		um.writeRoadObjsOnEdgeFile(naGraph.getObjectsOnEdges(), "NorthAmerica");
		um.writeObjStats(naGraph, "NorthAmerica");

		System.out.println();
//		ANNNaive annNaive = new ANNNaive();
//		ANNClustered annClustered = new ANNClustered();
//
//		long startTimeNaive = System.nanoTime();
//		annNaive.compute(naGraph, true);
//		long graphLoadingTimeNaive = System.nanoTime() - startTimeNaive;
//		double graphLoadingTimeDNaive = (double) graphLoadingTimeNaive / 1000000000.0;
//		// annNaive.printNearestNeighborSets();
//		System.out.println("Time to compute Naive ANN: " + graphLoadingTimeDNaive);
//
//		ANNClustered ann3 = new ANNClustered();
//		long startTimeClustered = System.nanoTime();
//		ann3.compute(naGraph, true);
//		long graphLoadingTimeClustered = System.nanoTime() - startTimeClustered;
//		double graphLoadingTimeDClustered = (double) graphLoadingTimeClustered / 1000000000.0;
//		// ann3.printNearestSets();
//		System.out.println("Time to compute Clustered ANN: " + graphLoadingTimeDClustered);
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
