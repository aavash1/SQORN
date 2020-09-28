package testing;

import algorithm.RandomObjectGenerator;
import framework.Graph;
import framework.UtilsManagment;
import road_network.OldenburgRN;

public class RandomObjectOnOldenburgDatasetTest {
	public static void main(String[] args) {
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
		Graph oldenGraph = OldenburgRN.getGraph();
		String graphName = oldenGraph.getDatasetName();
		int trueObjSize = 100000;
		int falseObjSize = 10000;

		RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(oldenGraph, trueObjSize, falseObjSize, true,
				1200.586);
		String roadObjsOnEdgeCSVFile = "GeneratedFiles/" +"FORALLTEST_"+ graphName + "_Q_" + trueObjSize + "_D_" + falseObjSize
				+ UtilsManagment.getNormalDateTime() + ".csv";

		UtilsManagment.writeRoadObjsOnEdgeFile(oldenGraph.getObjectsOnEdges(), oldenGraph.getDatasetName(),
				roadObjsOnEdgeCSVFile);
		// RandomObjectGenerator.printStatistics();
		System.out.println("Finished Generating");
		// UtilsManagment.writeRoadObjsOnEdgeFile1(oldenburgGraph.getObjectsOnEdges(),
		// oldenburgGraph.getDatasetName(),trueObjSize,falseObjSize);

	}

}
