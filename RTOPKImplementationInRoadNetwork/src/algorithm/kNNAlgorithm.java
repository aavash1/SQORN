package algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import framework.Graph;
import framework.Node;
import framework.Edge;

public class kNNAlgorithm implements Algorithm {
//FiguredOut how to sort that ArrayList but failed to make kNN. Will continue Tomorrow.
	private ArrayList<Integer> resultSet = new ArrayList<>();
	private final ArrayList<Node> nodes;
	private final ArrayList<Edge> edges;
	private Map<Node, Integer> distance;

	private Map<Integer, Double> sortingValues = new HashMap<Integer, Double>();

	public kNNAlgorithm(Graph graph) {
		this.nodes = new ArrayList<Node>(graph.getNodesWithInfo());
		this.edges = new ArrayList<Edge>(graph.getEdgesWithInfo());
	}

	public ArrayList<Integer> getKNN(Node queryPoint, int numberOfKnn) {
		Graph g = new Graph();

		return resultSet;

	}

	public Map<Integer, Double> sortNode(Graph g, int i) {
		ArrayList<Double> distance1=new ArrayList<>();
		
		double[] distance = new double[g.getAdjancencyMap().get(i).size()];
		
			for (Double value : g.getAdjancencyMap().get(i).values()) {
				
				distance1.add(value);
			}
			System.out.println("Before Sorting: "+distance1);
		//mergeSort(distance1);
			Collections.sort(distance1);
		//for(int j=0;j<distance1.size();j++) {
		System.out.println("after Sorting: "+distance1);
		//}
	//System.out.println();

		return sortingValues;
	}

//	public static Comparable[] mergeSort(Comparable[] list) {
//		if (list.length <= 1) {
//			return list;
//		}
//
//		Comparable[] first = new Comparable[list.length / 2];
//		Comparable[] second = new Comparable[list.length - first.length];
//
//		mergeSort(first);
//		mergeSort(second);
//
//		merge(first, second, list);
//		return list;
//	}
//
//	private static void merge(Comparable[] first, Comparable[] second, Comparable[] result) {
//
//		int iFirst = 0;
//
//		int iSecond = 0;
//		int iMerged = 0;
//
//		while (iFirst < first.length && iSecond < second.length) {
//			if (first[iFirst].compareTo(second[iSecond]) < 0) {
//				result[iMerged] = first[iFirst];
//				iFirst++;
//			} else {
//				result[iMerged] = second[iSecond];
//				iSecond++;
//			}
//			iMerged++;
//		}
//
//	}

}
