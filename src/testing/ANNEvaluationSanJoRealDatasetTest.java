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

public class ANNEvaluationSanJoRealDatasetTest {

	public static void main(String[] args) {
		UtilsManagment um = new UtilsManagment();
		Graph sanJoaGraph = new Graph();

		String nodeDatasetFile = "Datasets/SJ-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/SJ-Edge_Eid-ESrc-EDest-EDist.csv";

		sanJoaGraph = um.readEdgeFileReturnGraph(edgeDatasetFile);
		ArrayList<Node> calNodesInfo = um.readNodeFile(nodeDatasetFile);
		sanJoaGraph.setNodesWithInfo(calNodesInfo);
		ArrayList<Edge> calEdgeInfo = um.readEdgeFile(edgeDatasetFile);
		sanJoaGraph.setEdgeWithInfo(calEdgeInfo);

		ANNNaive annNaive = new ANNNaive();
		ANNClustered annClustered = new ANNClustered();

		RandomObjectGenerator2.generateRandomObjectsOnMap(sanJoaGraph, 0.2);
		um.writeRoadObjsOnEdgeFile(sanJoaGraph.getObjectsOnEdges(), "SanJoaquin");

		System.out.println();

		long startTimeNaive = System.nanoTime();
		annNaive.compute(sanJoaGraph, true);
		long graphLoadingTimeNaive = System.nanoTime() - startTimeNaive;
		double graphLoadingTimeDNaive = (double) graphLoadingTimeNaive / 1000000000.0;
		// annNaive.printNearestNeighborSets();
		System.out.println("Time to compute Naive ANN: " + graphLoadingTimeDNaive);

		ANNClustered ann3 = new ANNClustered();
		long startTimeClustered = System.nanoTime();
		ann3.compute(sanJoaGraph, true);
		long graphLoadingTimeClustered = System.nanoTime() - startTimeClustered;
		double graphLoadingTimeDClustered = (double) graphLoadingTimeClustered / 1000000000.0;
		// ann3.printNearestSets();
		System.out.println("Time to compute Clustered ANN: " + graphLoadingTimeDClustered);

		int totalNumberOfNodes = sanJoaGraph.getNodesWithInfo().size();
		int totalNumberOfEdges = sanJoaGraph.getEdgesWithInfo().size();
		int totalNumberOfRandomEdgesSelected = RandomObjectGenerator2.getTotalNumberOfRandomEdges();
		int totalNumberOfObjectsGenerated = sanJoaGraph.getObjectsWithInfo().size();
		int totalNumberOfTrueObjects = sanJoaGraph.getTotalNumberOfTrueObjects();
		int totalNumberOfFalseObjects = sanJoaGraph.getTotalNumberOfFalseObjects();
		int totalNumberOfNodeClusters = annClustered.getSizeOfNodeClusters();
		int totalNumberOfObjectClusters = annClustered.getSizeOfObjectClusters();

		um.writeNaiveAndClusteredANNTestResult(totalNumberOfNodes, totalNumberOfEdges, totalNumberOfRandomEdgesSelected,
				totalNumberOfObjectsGenerated, totalNumberOfTrueObjects, totalNumberOfFalseObjects,
				totalNumberOfNodeClusters, totalNumberOfObjectClusters, graphLoadingTimeDNaive,
				graphLoadingTimeDClustered);

	}

}
