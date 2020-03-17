package testing;

import algorithm.RandomObjectGenerator;
import algorithm.RandomObjectGeneratorWithGaussian;
import framework.Graph;
import framework.UtilsManagment;

public class RandomObjectGeneratorManualDatasetTest {

	public static void main(String[] args) {

		Graph gr = new Graph();
		

		gr.addEdge(1, 2, 40.6);
		gr.addEdge(1, 6, 40);
		gr.addEdge(6, 10, 43.33);
		gr.addEdge(1, 7, 65.23);
		gr.addEdge(3, 7, 65.22);
		gr.addEdge(3, 4, 33.20);
		gr.addEdge(4, 12, 62.55);
		gr.addEdge(4, 8, 47.47);
		gr.addEdge(5, 8, 49.08);
		gr.addEdge(8, 14, 19.54);
		gr.addEdge(14, 15, 64.03);
		gr.addEdge(13, 15, 55.22);
		gr.addEdge(11, 13, 48.00);
		gr.addEdge(10, 11, 33.2);
		gr.addEdge(9, 10, 33);
		gr.addEdge(9, 18, 53.3);
		gr.addEdge(11, 17, 25.21);
		gr.addEdge(18, 20, 43.33);
		gr.addEdge(17, 20, 60.12);
		gr.addEdge(17, 21, 40.22);
		gr.addEdge(16, 17, 33.11);
		gr.addEdge(13, 16, 31.13);
		gr.addEdge(16, 23, 37.62);
		gr.addEdge(22, 23, 26.00);
		gr.addEdge(22, 24, 43.11);
		gr.addEdge(20, 24, 22.43);
		gr.addEdge(20, 19, 30);
		gr.addEdge(15, 25, 90);

		RandomObjectGeneratorWithGaussian.generateRandomObjectsOnMap6(gr, 1000, 3000);
	//	UtilsManagment.writeRoadObjsOnEdgeFile(gr.getObjectsOnEdges(), "Manual",10,10);
		RandomObjectGenerator.printStatistics();
		gr.printObjectsOnMap();
		//gr.printObjectsOnEdges();
		gr.printGraph();

	}

}
