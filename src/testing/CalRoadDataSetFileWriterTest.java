package testing;

import java.util.ArrayList;

import algorithm.ANNClustered;
import algorithm.ANNNaive;
import algorithm.RandomObjectGenerator;
import framework.Edge;
import framework.Graph;
import framework.Node;
import framework.UtilsManagment;

public class CalRoadDataSetFileWriterTest {

	public static void main(String[] args) {
		Graph calGraph = new Graph();
		calGraph.setDatasetName("California");

		String nodeDatasetFile = "Datasets/CAL-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/CAL-Edge_Eid-ESrc-EDest-EDist.csv";

		calGraph = UtilsManagment.readEdgeFileReturnGraph(edgeDatasetFile);
		ArrayList<Node> calNodesInfo = UtilsManagment.readNodeFile(nodeDatasetFile);
		calGraph.setNodesWithInfo(calNodesInfo);
		ArrayList<Edge> calEdgeInfo = UtilsManagment.readEdgeFile(edgeDatasetFile);
		calGraph.setEdgeWithInfo(calEdgeInfo);

		// calGraph.printGraph();

		long startGenerationTime = System.nanoTime();
		RandomObjectGenerator.generateRandomObjectsOnMap5(calGraph,0.2,20000);
		long elapsedTimeForGeneration = System.nanoTime() - startGenerationTime;
		double elapsedTimeAfterRound = (double) elapsedTimeForGeneration / 1000000000.0;

		calGraph.printObjectsOnEdges();
		long startTimeWriter = System.nanoTime();
	//	um.writeRoadObjsOnEdgeFile(calGraph.getObjectsOnEdges(), calGraph.getDatasetName());
		long graphLoadingTimeWriter = System.nanoTime() - startTimeWriter;
		double graphLoadingTimeDWriter = (double) graphLoadingTimeWriter / 1000000000.0;
		System.out.println("Time to Generate Objects: " + elapsedTimeAfterRound);
		System.out.println("Time to Write the file: " + graphLoadingTimeDWriter);

		ANNNaive annNaive = new ANNNaive();
		long startTimeNaive = System.nanoTime();
		annNaive.compute(calGraph, true);
		long graphLoadingTimeNaive = System.nanoTime() - startTimeNaive;
		double graphLoadingTimeDNaive = (double) graphLoadingTimeNaive / 1000000000.0;
		// annNaive.printNearestNeighborSets();
		System.out.println("Time to compute Naive ANN: " + graphLoadingTimeDNaive);

		ANNClustered ann3 = new ANNClustered();
		long startTimeClustered = System.nanoTime();
		ann3.computeWithTime(calGraph, true);
		long graphLoadingTimeClustered = System.nanoTime() - startTimeClustered;
		double graphLoadingTimeDClustered = (double) graphLoadingTimeClustered / 1000000000.0;
		// ann3.printNearestSets();
		System.out.println("Time to compute Clustered ANN: " + graphLoadingTimeDClustered);

	}

}
