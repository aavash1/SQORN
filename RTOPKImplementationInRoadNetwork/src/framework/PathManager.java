package framework;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

public class PathManager {
	// m_paths: Map<Length, List<NodeId>>; Length - length of current path
	private MultiValuedMap<Double, ArrayList<Integer>> m_paths = new HashSetValuedHashMap<Double, ArrayList<Integer>>();

	// get java.util.Map representation of the multi-valued map
	// Map<Double, Collection<ArrayList<Integer>>> mapPaths = m_paths.asMap();

	public MultiValuedMap<Double, ArrayList<Integer>> getAnnPathsObj() {
		return m_paths;
	}

	public void addPath(double pathLength, ArrayList<Integer> nodePath) {
		m_paths.put(pathLength, nodePath);
	}

	public void updatePath(double oldPathLength, double newPathLength, int lastNodeInPath, int newNode) {
		Collection<ArrayList<Integer>> nodeListCollection = m_paths.remove(oldPathLength);
		Iterator<ArrayList<Integer>> nodeLists = nodeListCollection.iterator();
		Collection<ArrayList<Integer>> remainingNodeListCollection = new HashSet<ArrayList<Integer>>();
		int listSize;
		if (nodeListCollection.size() == 0) {
			System.out.println("There is no such path (length = " + oldPathLength + ")");
		}

		while (nodeLists.hasNext()) {
			ArrayList<Integer> nodeList = nodeLists.next();
			listSize = nodeList.size();
			if (nodeList.get(listSize - 1) == lastNodeInPath) {
				nodeList.add(newNode);
				m_paths.put(newPathLength, nodeList);
			} else {
				remainingNodeListCollection.add(nodeList);
			}
			m_paths.putAll(oldPathLength, remainingNodeListCollection);
		}

	}

	public double getPathLength(int lastNodeId) {
		// it is assumed that there is only one path with given last node id
		for (Double key : new TreeSet<Double>(m_paths.keySet())) {
			Collection<ArrayList<Integer>> nodeListCollection = m_paths.get(key);
			Iterator<ArrayList<Integer>> nodeLists = nodeListCollection.iterator();
			while (nodeLists.hasNext()) {
				ArrayList<Integer> nodeList = nodeLists.next();
				int listSize = nodeList.size();
				if (nodeList.get(listSize - 1) == lastNodeId) {
					return key;
				}
			}
		}
		return -1;
	}

	public void printPathsSortedByLength() {
		System.out.println("Iteration through whole Paths (sorted by length):");
		for (Double key : new TreeSet<Double>(m_paths.keySet())) {
			Collection<ArrayList<Integer>> nodeListCollection = m_paths.get(key);
			System.out.print("Length: " + key + " -> ");
			nodeListCollection.forEach(nodeList -> System.out.print(nodeList + "; "));
			System.out.println();
		}
	}

}
