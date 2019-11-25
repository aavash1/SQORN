package testing;

import java.util.ArrayList;
import java.util.Map;

import algorithm.ANNClustered;
import algorithm.ANNNaive;
import algorithm.RandomObjectGenerator;
import algorithm.RandomObjectGenerator;
import framework.Edge;
import framework.Graph;
import framework.Node;
import framework.RoadObject;
import framework.UtilsManagment;

public class ANNEvaluationCalRealDatasetTest {

	public static void main(String[] args) {
		Graph calGraph = new Graph("California");

		String nodeDatasetFile = "Datasets/CAL-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/CAL-Edge_Eid-ESrc-EDest-EDist.csv";
		String objectDatasetFile = "GeneratedFiles/roadObjectsOnEdge_California size_30000_2019-11-22 20-25-01.csv";
		// String objectDatasetFile =
		// "GeneratedFiles/roadObjectsOnEdge_California_2019-11-04 14-47-41.csv";

		// calGraph = um.readEdgeFileReturnGraph(edgeDatasetFile);
		UtilsManagment.readEdgeFile(calGraph, edgeDatasetFile);
		UtilsManagment.readNodeFile(calGraph, nodeDatasetFile);
		
		// calGraph.printEdgesInfo();
		// um.writeDatasetStatistics(calGraph);
		// System.out.println("Completed");
		//
		// calGraph.printGraph();
		// RandomObjectGenerator2.generateRandomObjectsOnMap6(calGraph, 50000, 10000);
		// RandomObjectGenerator.generateRandomObjectsOnMap(calGraph, 0.001);

		// um.writeRoadObjsOnEdgeFile(calGraph.getObjectsOnEdges(),
		// calGraph.getDatasetName());
		// um.writeObjStats(calGraph);

		// IF you wrote "ObjectsOnEdge file before (line33),Why are you trying to read
		// ObjectsWithInfo file???
		// ArrayList<RoadObject> listObjs = um.readRoadObjFile(objectDatasetFile);
		// calGraph.setObjectsWithInfo(listObjs);

		// You need to work with ObjectsOnEdge file, not ObjectsWithInfo!!!
		// Learn about main their difference
		Map<Integer, ArrayList<RoadObject>> objectsOnEdge = UtilsManagment.readRoadObjectFile(objectDatasetFile);
		calGraph.setObjectsOnEdges(objectsOnEdge);
		
		System.out.println(" Total Objects: "+calGraph.getTotalNumberOfObjects());
		System.out.println(" Total True Objects: "+calGraph.getTotalNumberOfTrueObjects());
		System.out.println(" Total False Objects: "+calGraph.getTotalNumberOfFalseObjects());
		
		//
		// System.out.println();
		//
		ANNNaive annNaive = new ANNNaive(); //

		long startTimeNaive = System.nanoTime(); //
		annNaive.compute(calGraph, true); //
		long graphLoadingTimeNaive = System.nanoTime() - startTimeNaive; //
		double graphLoadingTimeDNaive = (double) graphLoadingTimeNaive / 1000000000.0; // //
		annNaive.printNearestNeighborSets(); //
		System.out.println("Time to compute Naive ANN: " + graphLoadingTimeDNaive);

		ANNClustered annClustered = new ANNClustered();
		long startTimeClustered = System.nanoTime();
		annClustered.compute(calGraph, true);
		long graphLoadingTimeClustered = System.nanoTime() - startTimeClustered;
		double graphLoadingTimeDClustered = (double) graphLoadingTimeClustered / 1000000000.0;
		// ann3.printNearestSets();
		System.out.println("Time to compute Clustered ANN: " + graphLoadingTimeDClustered);

		// int totalNumberOfNodes = calGraph.getNodesWithInfo().size();
		// int totalNumberOfEdges = calGraph.getEdgesWithInfo().size();
		// int totalNumberOfRandomEdgesSelected =
		// RandomObjectGenerator.getTotalNumberOfRandomEdges();
		// int totalNumberOfObjectsGenerated = calGraph.getObjectsWithInfo().size();
		// int totalNumberOfTrueObjects = calGraph.getTotalNumberOfTrueObjects();
		// int totalNumberOfFalseObjects = calGraph.getTotalNumberOfFalseObjects();
		// int totalNumberOfNodeClusters = annClustered.getSizeOfNodeClusters();
		// int totalNumberOfObjectClusters = annClustered.getSizeOfObjectClusters();
		//
		// um.writeNaiveAndClusteredANNTestResult(calGraph, totalNumberOfNodeClusters,
		// totalNumberOfObjectClusters,
		// graphLoadingTimeDNaive, graphLoadingTimeDClustered);

	}

}
