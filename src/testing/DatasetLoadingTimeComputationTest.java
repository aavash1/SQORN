package testing;

import framework.Graph;
import framework.UtilsManagment;

public class DatasetLoadingTimeComputationTest {
	public static void main(String[] args) {
		UtilsManagment um = new UtilsManagment();
		String nodeDatasetFile = "Datasets/CAL-Node_NId-NLong-NLat.csv";
		String poiDatasetFile = "Datasets/CAL-Poi_PoiLong-PoiLat-PoiCatId.csv";

		String mergedPoiDatasetFileShort = "Datasets/MergedPoi-short.txt";
		String generatedDataset1 = "Datasets/GeneratedDataset2.txt";

		Graph roadNetwork2 = new Graph();

		System.out.println("Loading graph ...");
		long startTimeGraphLoading = System.nanoTime();
		roadNetwork2 = um.readMergedObjectFile(mergedPoiDatasetFileShort);
		roadNetwork2.printGraph();
		System.out.println();
		roadNetwork2.printObjectsOnMap();

		long graphLoadingTime = System.nanoTime() - startTimeGraphLoading;

		System.out.println("Loading nodes' information... ");
		long startTimeLoadNodes = System.nanoTime();
		um.loadNodesInfo(roadNetwork2, nodeDatasetFile);
		long nodesLoadingTime = System.nanoTime() - startTimeLoadNodes;
		roadNetwork2.printNodesInfo();

		System.out.println("Loading pois' information... ");
		long poisStartTime = System.nanoTime();
		um.loadPoiInfo(roadNetwork2, poiDatasetFile);
		long poisLoadingTime = System.nanoTime() - poisStartTime;
		roadNetwork2.printObjectsOnMap();

		// long graphLoadingTimeSec = TimeUnit.SECONDS.convert(graphLoadingTime,
		// TimeUnit.NANOSECONDS);
		// long nodesLoadingTimeSec = TimeUnit.SECONDS.convert(nodesLoadingTime,
		// TimeUnit.NANOSECONDS);
		// long poisLoadingTimeSec = TimeUnit.SECONDS.convert(poisLoadingTime,
		// TimeUnit.NANOSECONDS);

		double graphLoadingTimeD = (double) graphLoadingTime / 1000000000.0;
		double nodesLoadingTimeD = (double) nodesLoadingTime / 1000000000.0;
		double poisLoadingTimeD = (double) poisLoadingTime / 1000000000.0;

		System.out.println("Elapsed time of Graph loading: " + graphLoadingTimeD + "seconds");
		System.out.println("Elapsed time of Nodes info loading: " + nodesLoadingTimeD + " seconds");
		System.out.println("Elapsed time of POIs info loading: " + poisLoadingTimeD + " seconds");

		roadNetwork2.printObjectsOnMap();
		roadNetwork2.printEdgesInfo();
		Graph roadNetwork3 = um.readMergedObjectFile(generatedDataset1);
		roadNetwork3.printGraph();

	}

}
