package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import framework.Graph;

public class kNNAlgorithm implements Algorithm {

	private Graph graph;

	public kNNAlgorithm(Graph graph) {
		this.graph = graph;

	}

	// Method that will return the nearest neighbor node along with their distance
	public Map<Integer, Double> getKNNNodesWithDistance(int queryPointNode, int numberofK) {

		int numberofNeighbor = graph.getAdjancencyMap().get(queryPointNode).keySet().size();
		Map<Integer, Double> resultSet = new HashMap<>();
		resultSet = graph.getAdjancencyMap().get(queryPointNode);

		// Create a list from elements of HashMap
		LinkedList<Map.Entry<Integer, Double>> resultSetlist = new LinkedList<Map.Entry<Integer, Double>>(
				resultSet.entrySet());

		// Sort the list
		Collections.sort(resultSetlist, new Comparator<Map.Entry<Integer, Double>>() {
			public int compare(Map.Entry<Integer, Double> firstValue, Map.Entry<Integer, Double> secondValue) {
				return (firstValue.getValue()).compareTo(secondValue.getValue());
			}
		});
		// put data from sorted list to HashMap
		HashMap<Integer, Double> temp = new LinkedHashMap<Integer, Double>();
		for (Map.Entry<Integer, Double> aa : resultSetlist) {
			temp.put(aa.getKey(), aa.getValue());
		}

		ArrayList<Integer> keyInt = new ArrayList<Integer>(temp.keySet());
		ArrayList<Double> valDoub = new ArrayList<Double>(temp.values());
		HashMap<Integer, Double> pruned = new LinkedHashMap<Integer, Double>();

		if (numberofK > numberofNeighbor) {
			System.out.println("The requested number of K is more than the neighbors preseted");

		} else {
			for (int i = numberofK; i < numberofNeighbor; i++) {
				keyInt.remove(numberofK);
				valDoub.remove(numberofK);
			}

			for (int i = 0; i < numberofK; i++) {
				pruned.put(keyInt.get(i), valDoub.get(i));
			}
			System.out.println(numberofK + " nearest neighbor nodes of " + queryPointNode + ": " + pruned);

		}
		return pruned;

	}

	// Method that will return only the Nearest neighbor nodes.
	public ArrayList<Integer> getKNNNodes(int queryPointNode, int numberofK) {
		int numberofNeighbor = graph.getAdjancencyMap().get(queryPointNode).keySet().size();
		Map<Integer, Double> resultSet = new HashMap<>();
		resultSet = graph.getAdjancencyMap().get(queryPointNode);

		// Create a list from elements of HashMap
		LinkedList<Map.Entry<Integer, Double>> resultSetlist = new LinkedList<Map.Entry<Integer, Double>>(
				resultSet.entrySet());

		// Sort the list
		Collections.sort(resultSetlist, new Comparator<Map.Entry<Integer, Double>>() {
			public int compare(Map.Entry<Integer, Double> firstValue, Map.Entry<Integer, Double> secondValue) {
				return (firstValue.getValue()).compareTo(secondValue.getValue());
			}
		});
		// put data from sorted list to HashMap
		HashMap<Integer, Double> temp = new LinkedHashMap<Integer, Double>();
		for (Map.Entry<Integer, Double> aa : resultSetlist) {
			temp.put(aa.getKey(), aa.getValue());
		}

		ArrayList<Integer> keyInt = new ArrayList<Integer>(temp.keySet());
		//ArrayList<Double> valDoub = new ArrayList<Double>(temp.values());

		if (numberofK > numberofNeighbor) {
			System.out.println("The requested number of K is more than the neighbors preseted");

		} else {
			for (int i = numberofK; i < numberofNeighbor; i++) {
				keyInt.remove(numberofK);
				//valDoub.remove(numberofK);
			}

			System.out.println(numberofK + " nearest neighbor nodes of " + queryPointNode + ": " + keyInt);

		}

		return keyInt;
	}

}