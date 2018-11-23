package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;

import framework.Graph;
import framework.Path;

public class KnnAlgorithm implements Algorithm {

	private Graph graph;

	public KnnAlgorithm(Graph graph) {
		this.graph = graph;

	}

	// Method that will return the nearest neighbor node along with their distance
	public Map<Integer, Double> getKNNNodesWithDistance(int queryPointNode, int numberofK) {

		Map<Integer, Double> resultSet = new HashMap<>();
		resultSet = getNeighborNodesWithDistancesAscOrder(queryPointNode);

		int numberofNeighbor = resultSet.keySet().size();

//		ArrayList<Integer> keyInt = new ArrayList<Integer>(resultSet.keySet());
//		ArrayList<Double> valDoub = new ArrayList<Double>(resultSet.values());

		ArrayList<Integer> keyInt = new ArrayList<Integer>();
		ArrayList<Double> valDoub = new ArrayList<Double>();
		
		CollectionUtils.addAll(keyInt, resultSet.keySet());
		CollectionUtils.addAll(valDoub, resultSet.values());

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

		Map<Integer, Double> resultSet = new HashMap<>();
		resultSet = getNeighborNodesWithDistancesAscOrder(queryPointNode);

		int numberofNeighbor = resultSet.keySet().size();

		ArrayList<Integer> keyInt = new ArrayList<Integer>(resultSet.keySet());
		// ArrayList<Double> valDoub = new ArrayList<Double>(resultSet.values());

		if (numberofK > numberofNeighbor) {
			System.out.println("The requested number of K is more than the neighbors preseted");

		} else {
			for (int i = numberofK; i < numberofNeighbor; i++) {
				keyInt.remove(numberofK);
				// valDoub.remove(numberofK);
			}

			System.out.println(numberofK + " nearest neighbor nodes of " + queryPointNode + ": " + keyInt);

		}

		return keyInt;
	}

	public Map<Integer, Double> getKNNNodesWithDistance2(int queryPointNode, int numberofK) {

		Map<Integer, Double> finalReturnMap = new LinkedHashMap<Integer, Double>();

		List<Path> arrListOfPaths = new ArrayList<Path>();

		Map<Integer, Double> tempNeighborNodeMap = new LinkedHashMap<Integer, Double>();

		tempNeighborNodeMap = getNeighborNodesWithDistancesAscOrder(queryPointNode);

		int numberofNeighbor = tempNeighborNodeMap.keySet().size();

		ArrayList<Integer> keyInt = new ArrayList<Integer>(tempNeighborNodeMap.keySet());
		ArrayList<Double> valDoub = new ArrayList<Double>(tempNeighborNodeMap.values());
		HashMap<Integer, Double> pruned = new HashMap<Integer, Double>();

		Path path = new Path();
		path.addNode(queryPointNode);
		arrListOfPaths.add(path);

		if (numberofK > numberofNeighbor) {
			System.out.println("The requested number of K is more than number of direct neighbors");
			finalReturnMap = tempNeighborNodeMap;
			int intFoundNearestNeighbors = numberofNeighbor;

			ArrayList<Integer> arrListOfNewNodes = new ArrayList<>();
			ArrayList<Map<Integer, Double>> arrListOfNewNodesWithDist = new ArrayList<Map<Integer, Double>>();

			// Map<Integer, Double> tempResultMap = new HashMap<>();
			// tempResultMap = tempNeighborNodeMap;
			while (intFoundNearestNeighbors < numberofK) {
				// tempNeighborNodeMap = getNeighborNodesWithDistancesAscOrder(queryPointNode);

				Set<Integer> keySet = new HashSet<Integer>();
				keySet = tempNeighborNodeMap.keySet();
				ArrayList<Integer> arrListTempNodes = new ArrayList<Integer>();
				for (Integer key : keySet) {
					arrListTempNodes = getNeighborNodesAscOrder(key);

					for (int i = 0; i < arrListTempNodes.size(); i++) {
						if (arrListTempNodes.contains(arrListTempNodes.get(i))) {
							System.out.println(arrListTempNodes.get(i) + " in key set");
							continue;
						}
						System.out.println(arrListTempNodes.get(i));
					}
					//
					System.out.println("not head key: " + key);

				}
				// for (Integer i = 0; i < tempResultMap.size(); i++) {
				//
				// //finalReturnMap
				// }

				for (Map.Entry<Integer, Double> entry : tempNeighborNodeMap.entrySet()) {
					// System.out.println("Key = " + entry.getKey() + ", Value = " +
					// entry.getValue());
					finalReturnMap.put(entry.getKey(), entry.getValue());
				}
				for (Map.Entry<Integer, Double> entry : finalReturnMap.entrySet()) {
					System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
				}
				// System.out.println(intFoundNearestNeighbors);
				intFoundNearestNeighbors++;

			}

			// /// test start
			// ArrayList<Integer> testResultList = new ArrayList<Integer>();
			// testResultList = getNeighborNodes(3);
			// System.out.println(testResultList);
			//
			// testResultList = getNeighborNodesAscOrder(3);
			// System.out.println(testResultList);
			//
			// Map<Integer, Double> testResultSet = new HashMap<>();
			// testResultSet = getNeighborNodesWithDistancesAscOrder(3);
			// System.out.println(testResultSet);
			// /// test end

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

	public Map<Integer, Double> getKNNNodesWithDistance3(int sourceNode, int numberOfK) {

		Map<Integer, Double> mapDistinctNodesShortestDistToSrc = new LinkedHashMap<Integer, Double>();
		Map<Integer, Double> mapTempDistinctNodesShortestDistToSrc = new LinkedHashMap<Integer, Double>();

		Map<Integer, Double> mapFinalReturnNodes = new LinkedHashMap<Integer, Double>();
		Map<Integer, Double> mapTempNodes = new LinkedHashMap<Integer, Double>();
		Map<Integer, Double> mapTempChosenNodes = new LinkedHashMap<Integer, Double>();

		int tempNumOfNeighbors = 0;
		int numOfTotalFoundNodes = 0, numOfNewNodes, numOfTakeNodes;
		int headNode = sourceNode;
		int indexOfNewNode = 0;
		ArrayList<Integer> listUsedNewNodeIndex = new ArrayList<>();
		
		
		ArrayList<Integer> newNodes = new ArrayList<Integer>();
		
		

		ArrayList<Integer> keyInt = new ArrayList<Integer>();
		ArrayList<Double> valDoub = new ArrayList<Double>();
		//HashMap<Integer, Double> prunedMap = new LinkedHashMap<Integer, Double>();
		
		while (numOfTotalFoundNodes < numberOfK) {

			mapTempNodes = getNeighborNodesWithDistancesAscOrder(headNode);
			tempNumOfNeighbors = mapTempNodes.size();
	
			CollectionUtils.addAll(keyInt, mapTempNodes.keySet());
			CollectionUtils.addAll(valDoub, mapTempNodes.values());
			
			
			
			
			if (numberOfK > tempNumOfNeighbors) {

				for (Map.Entry<Integer, Double> entry : mapTempNodes.entrySet()) {
					// System.out.println("temp neighbor Key = " + entry.getKey() + ", temp neighbor
					// Value = " + entry.getValue());
					
					if (!mapFinalReturnNodes.containsKey(entry.getKey())) { 
						mapFinalReturnNodes.put(entry.getKey(), entry.getValue());
						numOfTotalFoundNodes ++; 
						newNodes.add(entry.getKey());
						
					}					
					//indexOfNewNode = newNodes.get(0);
					if (!mapTempDistinctNodesShortestDistToSrc.containsKey(entry.getKey())) {
						mapTempDistinctNodesShortestDistToSrc.put(entry.getKey(), entry.getValue());
					}
					
				}			
				
				

				mapTempChosenNodes = mapTempNodes;

			} else {

				
				for (int i = numberOfK; i < tempNumOfNeighbors; i++) {
					keyInt.remove(numberOfK);
					valDoub.remove(numberOfK);
				}

				for (int i = 0; i < numberOfK; i++) {
					mapFinalReturnNodes.put(keyInt.get(i), valDoub.get(i));
				}				
				numOfTotalFoundNodes++;
				
				
				
			}
			numOfTotalFoundNodes++;
			headNode = 4;//indexOfNewNode; // assign dynamically
			
		}

//		Map<Integer, Double> finalReturnMap = new LinkedHashMap<Integer, Double>();
//
//		List<Path> arrListOfPaths = new ArrayList<Path>();
//
//		Map<Integer, Double> tempNeighborNodeMap = new LinkedHashMap<Integer, Double>();
//
//		tempNeighborNodeMap = getNeighborNodesWithDistancesAscOrder(queryPointNode);
//
//		int numberofNeighbor = tempNeighborNodeMap.keySet().size();
//		int intFoundNearestNeighbors = numberofNeighbor;
//
//		ArrayList<Integer> keyInt = new ArrayList<Integer>(tempNeighborNodeMap.keySet());
//		ArrayList<Double> valDoub = new ArrayList<Double>(tempNeighborNodeMap.values());
//		HashMap<Integer, Double> pruned = new HashMap<Integer, Double>();
//
//		Path path = new Path();
//		path.addNode(queryPointNode);
//		arrListOfPaths.add(path);
//
//		// System.out.println("The requested number of K is more than number of direct
//		// neighbors");
//		finalReturnMap = tempNeighborNodeMap;
//
//		ArrayList<Integer> arrListOfNewNodes = new ArrayList<>();
//		ArrayList<Map<Integer, Double>> arrListOfNewNodesWithDist = new ArrayList<Map<Integer, Double>>();
//
//		// Map<Integer, Double> tempResultMap = new HashMap<>();
//		// tempResultMap = tempNeighborNodeMap;
//		while (intFoundNearestNeighbors < numberofK) {
//			// tempNeighborNodeMap = getNeighborNodesWithDistancesAscOrder(queryPointNode);
//
//			Set<Integer> keySet = new HashSet<Integer>();
//			keySet = tempNeighborNodeMap.keySet();
//			ArrayList<Integer> arrListTempNodes = new ArrayList<Integer>();
//
//			for (Integer key : keySet) {
//				arrListTempNodes = getNeighborNodesAscOrder(key);
//
//				for (int i = 0; i < arrListTempNodes.size(); i++) {
//					if (arrListTempNodes.contains(arrListTempNodes.get(i))) {
//						System.out.println(arrListTempNodes.get(i) + " in key set");
//						continue;
//					}
//					System.out.println(arrListTempNodes.get(i));
//				}
//				//
//				System.out.println("not head key: " + key);
//
//			}
//
//			for (Map.Entry<Integer, Double> entry : tempNeighborNodeMap.entrySet()) {
//				System.out.println(
//						"temp neighbor Key = " + entry.getKey() + ", temp neighbor Value = " + entry.getValue());
//				finalReturnMap.put(entry.getKey(), entry.getValue());
//			}
//
//			// System.out.println(intFoundNearestNeighbors);
//			intFoundNearestNeighbors++;
//
//		}

		// /// test start
		// ArrayList<Integer> testResultList = new ArrayList<Integer>();
		// testResultList = getNeighborNodes(3);
		// System.out.println(testResultList);
		//
		// testResultList = getNeighborNodesAscOrder(3);
		// System.out.println(testResultList);
		//
		// Map<Integer, Double> testResultSet = new HashMap<>();
		// testResultSet = getNeighborNodesWithDistancesAscOrder(3);
		// System.out.println(testResultSet);
		// /// test end

//		for (Map.Entry<Integer, Double> entry : finalReturnMap.entrySet()) {
//			System.out.println("final return Key = " + entry.getKey() + ", final return Value = " + entry.getValue());
//		}
//
//		for (int i = numberofK; i < numberofNeighbor; i++) {
//			keyInt.remove(numberofK);
//			valDoub.remove(numberofK);
//		}
//
//		for (int i = 0; i < numberofK; i++) {
//			pruned.put(keyInt.get(i), valDoub.get(i));
//		}
		System.out.println(numberOfK + " nearest neighbor nodes of " + sourceNode + ": " + mapFinalReturnNodes);

		return mapFinalReturnNodes;

	}

	////////////////// ---Private methods----//////////////////
	private Map<Integer, Double> getNeighborNodesWithDistances(int sourceNode) {
		Map<Integer, Double> resultSet = new HashMap<Integer, Double>();
		resultSet = graph.getAdjancencyMap().get(sourceNode);
		return resultSet;
	}

	private ArrayList<Integer> getNeighborNodes(int sourceNode) {
		Set<Integer> resultSet = getNeighborNodesWithDistances(sourceNode).keySet();
		ArrayList<Integer> resultList = new ArrayList<Integer>(resultSet);
		return resultList;
	}

	private Map<Integer, Double> getNeighborNodesWithDistancesAscOrder(int sourceNode) {
		Map<Integer, Double> resultSet = new LinkedHashMap<Integer, Double>();
		resultSet = getNeighborNodesWithDistances(sourceNode);

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

		return temp;
	}

	private ArrayList<Integer> getNeighborNodesAscOrder(int sourceNode) {

		Map<Integer, Double> resultSet = new LinkedHashMap<Integer, Double>();
		resultSet = getNeighborNodesWithDistances(sourceNode);

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
		ArrayList<Integer> arrListNeighborNodes = new ArrayList<Integer>(temp.keySet());

		return arrListNeighborNodes;
	}

	private boolean isNodeInList(ArrayList<Integer> list, int node) {

		return list.contains(node);
	}

	private boolean isNodeInSet(Set<Integer> set, int node) {

		return set.contains(node);
	}

	private boolean isNodeListInList(ArrayList<Integer> list, ArrayList<Integer> list2) {

		return list.containsAll(list2);
	}

	private boolean isNodeSetInSet(Set<Integer> set, Set<Integer> set2) {

		return set.containsAll(set2);
	}

}