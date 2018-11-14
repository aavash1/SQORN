package algorithm;

import java.io.FileWriter;
import java.io.IOException;

import framework.Graph;

public class PoiGeneratorAlgorithm {

	public void generateRandomPois(Graph g, int maxNumofPoiPerGraph, int maxNumofPoiPerEdge,
			double minDistanceBetPoiAndNode, double minDistanceBetPois) {
		Graph graph;
		try {
			FileWriter fw = new FileWriter("generatedPoi.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
