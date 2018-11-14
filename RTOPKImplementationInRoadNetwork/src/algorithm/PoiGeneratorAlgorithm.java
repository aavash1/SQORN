package algorithm;

import java.io.FileWriter;
import java.io.IOException;

import framework.Graph;

public class PoiGeneratorAlgorithm {

	public void generateRandomPois(Graph g, int maxNumofPoiPerGraph, int maxNumofPoiPerEdge,
			double minDistanceBetPoiAndNode, double minDistanceBetPois) {
		try {
			FileWriter fw = new FileWriter("generatedPoi.txt");
			if ((maxNumofPoiPerEdge > 0) || (maxNumofPoiPerEdge < 8)) {

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
