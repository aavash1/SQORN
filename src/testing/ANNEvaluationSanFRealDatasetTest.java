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

public class ANNEvaluationSanFRealDatasetTest {

	public static void main(String[] args) {
		UtilsManagment um = new UtilsManagment();
		Graph sanFGraph = new Graph();

		String nodeDatasetFile = "Datasets/SANF-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/SANF-Edge_Eid-ESrc-EDest-EDist.csv";

		sanFGraph = um.readEdgeFileReturnGraph(edgeDatasetFile);
		ArrayList<Node> calNodesInfo = um.readNodeFile(nodeDatasetFile);
		sanFGraph.setNodesWithInfo(calNodesInfo);
		ArrayList<Edge> calEdgeInfo = um.readEdgeFile(edgeDatasetFile);
		sanFGraph.setEdgeWithInfo(calEdgeInfo);

		ANNNaive annNaive = new ANNNaive();
		ANNClustered annClustered = new ANNClustered();

		RandomObjectGenerator2.generateRandomObjectsOnMap(sanFGraph, 0.2);
		um.writeRoadObjsOnEdgeFile(sanFGraph.getObjectsOnEdges(), "SanFransisco");

		System.out.println();

		long startTimeNaive = System.nanoTime();
		annNaive.compute(sanFGraph, true);
		long graphLoadingTimeNaive = System.nanoTime() - startTimeNaive;
		double graphLoadingTimeDNaive = (double) graphLoadingTimeNaive / 1000000000.0;
		// annNaive.printNearestNeighborSets();
		System.out.println("Time to compute Naive ANN: " + graphLoadingTimeDNaive);

		ANNClustered ann3 = new ANNClustered();
		long startTimeClustered = System.nanoTime();
		ann3.compute(sanFGraph, true);
		long graphLoadingTimeClustered = System.nanoTime() - startTimeClustered;
		double graphLoadingTimeDClustered = (double) graphLoadingTimeClustered / 1000000000.0;
		// ann3.printNearestSets();
		System.out.println("Time to compute Clustered ANN: " + graphLoadingTimeDClustered);

		int totalNumberOfNodes = sanFGraph.getNodesWithInfo().size();
		int totalNumberOfEdges = sanFGraph.getEdgesWithInfo().size();
		int totalNumberOfRandomEdgesSelected = RandomObjectGenerator2.getTotalNumberOfRandomEdges();
		int totalNumberOfObjectsGenerated = sanFGraph.getObjectsWithInfo().size();
		int totalNumberOfTrueObjects = sanFGraph.getTotalNumberOfTrueObjects();
		int totalNumberOfFalseObjects = sanFGraph.getTotalNumberOfFalseObjects();
		int totalNumberOfNodeClusters = annClustered.getSizeOfNodeClusters();
		int totalNumberOfObjectClusters = annClustered.getSizeOfObjectClusters();

		um.writeNaiveAndClusteredANNTestResult(totalNumberOfNodes, totalNumberOfEdges, totalNumberOfRandomEdgesSelected,
				totalNumberOfObjectsGenerated, totalNumberOfTrueObjects, totalNumberOfFalseObjects,
				totalNumberOfNodeClusters, totalNumberOfObjectClusters, graphLoadingTimeDNaive,
				graphLoadingTimeDClustered);

	}

}
