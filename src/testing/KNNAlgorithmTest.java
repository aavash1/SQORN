package testing;

import algorithm.KnnAlgorithm;
import framework.Graph;
import framework.UtilsManagment;

public class KNNAlgorithmTest {

	public static void main(String[] args) {
		UtilsManagment um = new UtilsManagment();
		String generatedDataset1 = "Datasets/GeneratedDataset2.txt";
		Graph roadNetwork6 = um.readMergedObjectFile(generatedDataset1);
		roadNetwork6.printGraph();

		KnnAlgorithm knnAlg = new KnnAlgorithm(roadNetwork6);
		knnAlg.getKNNNodes(50, 2);
		knnAlg.getKNNNodesWithDistance(50, 2);

		String manualEdgeDataset2 = "Datasets/ManualEdgeDataset2.txt";
		Graph roadNetwork7 = um.readMergedObjectFile(manualEdgeDataset2);
		roadNetwork7.printGraph();

		KnnAlgorithm knnAlg2 = new KnnAlgorithm(roadNetwork7);
		knnAlg2.getKNNNodes(4, 3);
		knnAlg2.getKNNNodesWithDistance(4, 3);

		System.out.println("-------------------");
		knnAlg2.getKNNNodesWithDistance4(4, 3);
	}

}
