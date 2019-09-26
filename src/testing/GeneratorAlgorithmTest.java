package testing;

import algorithm.PoiGeneratorAlgorithm;
import algorithm.UserPreferenceGenerator;
import framework.Graph;
import framework.UtilsManagment;

public class GeneratorAlgorithmTest {

	public static void main(String[] args) {
		UtilsManagment um = new UtilsManagment();
		String generatedDatasetForDijkstra1 = "Datasets/DatasetForDijkstra1.txt";
		Graph roadNetwork5 = um.readMergedObjectFile(generatedDatasetForDijkstra1);
		UserPreferenceGenerator upg = new UserPreferenceGenerator();
		upg.generateRandomUserPreference(600, 2);

		// Graph newGraph2 = new Graph();
		PoiGeneratorAlgorithm pga = new PoiGeneratorAlgorithm();
		pga.generateRandomPois(roadNetwork5, 0, 0, 0, 0);
		pga.showGraphInfor(roadNetwork5);

		String manualEdgeDataset = "Datasets/ManualEdgeDataset1.txt";
		Graph roadNetwork6 = um.readMergedObjectFile(manualEdgeDataset);
		PoiGeneratorAlgorithm poiGen = new PoiGeneratorAlgorithm();
		String[][] genPois = poiGen.generateRandomPois2(roadNetwork6, 30, 4, 0.5, 1);
		System.out.println(genPois);

	}

}
