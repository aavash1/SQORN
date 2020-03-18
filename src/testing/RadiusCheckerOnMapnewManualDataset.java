package testing;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

import algorithm.BFS;
import algorithm.DFS;
import algorithm.RandomObjectGenerator;
import algorithm.RandomObjectGeneratorWithCentroid;
import framework.Graph;
import framework.UtilsManagment;

public class RadiusCheckerOnMapnewManualDataset {
	private static double m_minDistBetweenObjsPrecision = 1000000.0;
	private static double m_distFromStartNodePrecision = 1000000.0;

	public static void main(String[] args) {

		Graph gr = new Graph();

		gr.addEdge(0, 1, 5.4);
		gr.addEdge(1, 2, 6.2);
		gr.addEdge(1, 4, 10.0);
		gr.addEdge(1, 3, 4.1);
		gr.addEdge(3, 6, 11.20);
		gr.addEdge(3, 7, 11.21);
		gr.addEdge(3, 5, 8.2);
		gr.addEdge(4, 5, 7.6);
		gr.addEdge(2, 59, 32.6);
		gr.addEdge(4, 33, 15.7);
		gr.addEdge(5, 31, 10.01);

		gr.addEdge(5, 9, 8.1);
		gr.addEdge(9, 7, 3.4);
		gr.addEdge(6, 8, 14.1);
		gr.addEdge(8, 12, 18.3);
		gr.addEdge(10, 11, 16.7);
		gr.addEdge(11, 12, 6.1);
		gr.addEdge(10, 14, 10.5);
		gr.addEdge(14, 31, 14.2);
		gr.addEdge(14, 15, 6.0);
		gr.addEdge(13, 22, 17.2);

		gr.addEdge(22, 20, 17.4);
		gr.addEdge(20, 21, 9.3);
		gr.addEdge(21, 16, 16.7);
		gr.addEdge(16, 13, 10.6);
		gr.addEdge(16, 23, 16.4);
		gr.addEdge(15, 29, 5.8);
		gr.addEdge(14, 35, 16.2);
		gr.addEdge(21, 19, 10.1);
		gr.addEdge(21, 18, 12.6);
		gr.addEdge(21, 23, 19.6);

		gr.addEdge(19, 24, 16.3);
		gr.addEdge(19, 25, 18.2);
		gr.addEdge(23, 24, 14.1);
		gr.addEdge(23, 17, 15.42);
		gr.addEdge(29, 28, 5.1);
		gr.addEdge(17, 28, 6.8);
		gr.addEdge(17, 37, 13.3);
		gr.addEdge(17, 26, 19.32);
		gr.addEdge(28, 45, 15.18);
		gr.addEdge(32, 30, 11.2);

		gr.addEdge(32, 34, 4.2);
		gr.addEdge(30, 35, 7.4);
		gr.addEdge(35, 40, 10.4);
		gr.addEdge(40, 36, 15.2);
		gr.addEdge(33, 59, 15.4);
		gr.addEdge(59, 39, 3.8);
		gr.addEdge(39, 38, 3.2);
		gr.addEdge(39, 41, 6.4);
		gr.addEdge(41, 40, 2.1);
		gr.addEdge(41, 42, 2.1);

		gr.addEdge(42, 43, 2.0);
		gr.addEdge(60, 58, 10.2);
		gr.addEdge(60, 57, 2.0);
		gr.addEdge(57, 58, 8.4);
		gr.addEdge(58, 55, 13.9);
		gr.addEdge(58, 57, 14.5);
		gr.addEdge(56, 54, 12.3);
		gr.addEdge(24, 25, 2.6);
		gr.addEdge(55, 43, 15.2);
		gr.addEdge(54, 53, 12.9);

		gr.addEdge(53, 46, 11.4);
		gr.addEdge(46, 52, 18.3);
		gr.addEdge(52, 47, 16.6);
		gr.addEdge(52, 48, 13.2);
		gr.addEdge(52, 50, 14.8);
		gr.addEdge(44, 36, 13.9);
		gr.addEdge(36, 45, 14.8);
		gr.addEdge(37, 27, 5.2);
		gr.addEdge(37, 47, 13.4);
		gr.addEdge(27, 26, 17.3);

		gr.addEdge(27, 47, 14.9);
		gr.addEdge(44, 47, 14.8);
		gr.addEdge(47, 48, 6.3);
		gr.addEdge(48, 49, 4.2);
		gr.addEdge(49, 26, 4.9);
		gr.addEdge(26, 51, 18.8);
		gr.addEdge(26, 25, 16.6);
		gr.addEdge(50, 51, 6.1);
		
		//this is generator for old uniform distribution
		//RandomObjectGenerator.generateRandomObjectsOnMap6(gr, 100, 200);
	//	RandomObjectGenerator.printStatistics();
		
		//this is the generator for non-uniform distribution
		RandomObjectGenerator.generateRandomObjectsOnEdgesWithCentroid(gr, 30, 40, true);
		RandomObjectGenerator.printStatistics();		
		gr.printObjectsOnEdges();
	}
	

}
