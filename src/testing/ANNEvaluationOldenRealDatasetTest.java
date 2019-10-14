package testing;

import java.util.ArrayList;

import algorithm.ANNClustered;
import algorithm.ANNNaive;
import algorithm.RandomObjectGenerator;
import framework.Edge;
import framework.Graph;
import framework.Node;
import framework.RoadObject;
import framework.UtilsManagment;

public class ANNEvaluationOldenRealDatasetTest {

	public static void main(String[] args) {
		UtilsManagment um = new UtilsManagment();
		Graph oldenGraph = new Graph();

		String nodeDatasetFile = "Datasets/OLDN-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/OLDN-Edge_EId-ESrc-EDest-EDist.csv";

		oldenGraph.setDatasetName("Oldenburg");
		
		oldenGraph = um.readEdgeFileReturnGraph(edgeDatasetFile);
		ArrayList<Node> oldenBNodesInfo = um.readNodeFile(nodeDatasetFile);
		oldenGraph.setNodesWithInfo(oldenBNodesInfo);
		ArrayList<Edge> oldenBEdgeInfo = um.readEdgeFile(edgeDatasetFile);
		oldenGraph.setEdgeWithInfo(oldenBEdgeInfo);

		RandomObjectGenerator.generateRandomObjectsOnMap(oldenGraph, 0.2);
		um.writeRoadObjsOnEdgeFile(oldenGraph.getObjectsOnEdges(), "Oldenburg");
		um.writeObjStats(oldenGraph);

//		System.out.println();
//		ANNNaive annNaive = new ANNNaive();
//		ANNClustered annClustered = new ANNClustered();
//		long startTimeNaive = System.nanoTime();
//		annNaive.compute(oldenGraph, true);
//		long graphLoadingTimeNaive = System.nanoTime() - startTimeNaive;
//		double graphLoadingTimeDNaive = (double) graphLoadingTimeNaive / 1000000000.0;
//		// annNaive.printNearestNeighborSets();
//		System.out.println("Time to compute Naive ANN: " + graphLoadingTimeDNaive);
//
//		ANNClustered ann3 = new ANNClustered();
//		long startTimeClustered = System.nanoTime();
//		ann3.compute(oldenGraph, true);
//		long graphLoadingTimeClustered = System.nanoTime() - startTimeClustered;
//		double graphLoadingTimeDClustered = (double) graphLoadingTimeClustered / 1000000000.0;
//		// ann3.printNearestSets();
//		System.out.println("Time to compute Clustered ANN: " + graphLoadingTimeDClustered);
//
//		int totalNumberOfNodes = oldenGraph.getNodesWithInfo().size();
//		int totalNumberOfEdges = oldenGraph.getEdgesWithInfo().size();
//		int totalNumberOfRandomEdgesSelected = RandomObjectGenerator2.getTotalNumberOfRandomEdges();
//		int totalNumberOfObjectsGenerated = oldenGraph.getObjectsWithInfo().size();
//		int totalNumberOfTrueObjects = oldenGraph.getTotalNumberOfTrueObjects();
//		int totalNumberOfFalseObjects = oldenGraph.getTotalNumberOfFalseObjects();
//		int totalNumberOfNodeClusters = annClustered.getSizeOfNodeClusters();
//		int totalNumberOfObjectClusters = annClustered.getSizeOfObjectClusters();
//
//		um.writeNaiveAndClusteredANNTestResult(totalNumberOfNodes, totalNumberOfEdges, totalNumberOfRandomEdgesSelected,
//				totalNumberOfObjectsGenerated, totalNumberOfTrueObjects, totalNumberOfFalseObjects,
//				totalNumberOfNodeClusters, totalNumberOfObjectClusters, graphLoadingTimeDNaive,
//				graphLoadingTimeDClustered);

	}

}