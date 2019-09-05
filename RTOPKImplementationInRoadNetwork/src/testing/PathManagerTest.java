package testing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.collections4.MultiSet;

import framework.PathManager;

public class PathManagerTest {

	public static void main(String[] args) {

		PathManager annPath = new PathManager();
		
		// get java.util.Map representation of the multi-valued map
		// Map<Double, Collection<ArrayList<Integer>>> mapPaths = m_paths.asMap();

		ArrayList<Integer> nodes = new ArrayList<Integer>();
		nodes.add(2);
		nodes.add(4);
		annPath.getAnnPathsObj().put(3.5, nodes);
		System.out.println(annPath.getAnnPathsObj());

		ArrayList<Integer> nodes2 = new ArrayList<Integer>();
		nodes2.add(1);
		nodes2.add(3);
		annPath.getAnnPathsObj().put(2.4, nodes2);
		System.out.println(annPath.getAnnPathsObj());

		ArrayList<Integer> nodes3 = new ArrayList<Integer>();
		nodes3.add(5);
		nodes3.add(6);
		annPath.getAnnPathsObj().put(3.0, nodes3);
		System.out.println(annPath.getAnnPathsObj());

		ArrayList<Integer> nodes4 = new ArrayList<Integer>();
		nodes4.add(7);
		nodes4.add(8);
		annPath.getAnnPathsObj().put(3.6, nodes4);
		System.out.println(annPath.getAnnPathsObj());

		ArrayList<Integer> nodes5 = new ArrayList<Integer>();
		nodes5.add(0);
		nodes5.add(9);
		annPath.getAnnPathsObj().put(3.1, nodes5);
		System.out.println(annPath.getAnnPathsObj());

		// after adding duplicated key (3.1)
		ArrayList<Integer> nodes6 = new ArrayList<Integer>();
		nodes6.add(0);
		nodes6.add(12);
		annPath.getAnnPathsObj().put(3.1, nodes6);
		System.out.println(annPath.getAnnPathsObj());
		ArrayList<Integer> nodes62 = new ArrayList<Integer>();
		nodes62.add(1);
		nodes62.add(7);
		nodes62.add(5);
		annPath.getAnnPathsObj().put(3.1, nodes62);
		System.out.println(annPath.getAnnPathsObj());

		System.out.println("----------adding node list with same distance key (3.0)-----------");
		ArrayList<Integer> nodes7 = new ArrayList<Integer>();
		nodes7.add(13);
		nodes7.add(14);
		// mapPaths.get(3.0).add(nodes7);
		annPath.getAnnPathsObj().get(3.0).add(nodes7);
		System.out.println(annPath.getAnnPathsObj());
		System.out.println("values of 3.1:");
		System.out.println(annPath.getAnnPathsObj().get(3.1));
		System.out.println("1st node list of values of 3.1:");
		// ArrayList<Integer> nodeList = new ArrayList<Integer>();
		Collection<ArrayList<Integer>> nodeListCollection = new HashSet<ArrayList<Integer>>();
		nodeListCollection = annPath.getAnnPathsObj().get(3.1);
		// nodeList = nodeListCollection.
		Iterator<ArrayList<Integer>> nodeLists = nodeListCollection.iterator();
		while (nodeLists.hasNext()) {
			ArrayList<Integer> nodeList = nodeLists.next();
			System.out.println(nodeList);
			// break; //break after first item
		}
		// System.out.println(nodeListCollection);
		System.out.println("Another iteration (3.1):");
		nodeListCollection.forEach(nodeList -> {
			System.out.println(nodeList);
		});
		System.out.println("Another iteration with break (3.1):");
		for (ArrayList<Integer> nodeList : nodeListCollection) {
			System.out.println(nodeList);
			break;
		}
		System.out.println("List of legths (KeySet):");
		Set<Double> keySet = annPath.getAnnPathsObj().keySet();
		System.out.println(keySet);
		System.out.println("List of legths (Keys):");
		MultiSet<Double> keys = annPath.getAnnPathsObj().keys();
		System.out.println(keys);
		// keySet.forEach(key -> System.out.println(key));

		System.out.println("Sorted node lists:");
		for (Double key : new TreeSet<Double>(annPath.getAnnPathsObj().keySet())) {
			System.out.println(annPath.getAnnPathsObj().get(key));
		}
		System.out.println("Sorted lenghts:");
		TreeSet<Double> minDistances = new TreeSet<Double>(annPath.getAnnPathsObj().keySet());
		System.out.println(minDistances);
		System.out.println("min lenght:");
		System.out.println(minDistances.first());
		Collection<ArrayList<Integer>> nodeListCollection2 = annPath.getAnnPathsObj().get(minDistances.first());
		System.out.println(nodeListCollection2);
		System.out.println("max lenght:");
		System.out.println(minDistances.last());
		System.out.println("---------------------------");
		System.out.println(annPath.getAnnPathsObj());
		System.out.println("Updating key (3.5 -> 3.8):");
		Collection<ArrayList<Integer>> nodeListCollection3 = annPath.getAnnPathsObj().remove(3.5);	
		
		Iterator<ArrayList<Integer>> nodeLists2 = nodeListCollection3.iterator();
		while (nodeLists2.hasNext()) {
			ArrayList<Integer> nodeList2 = nodeLists2.next();
			nodeList2.add(12);
			//System.out.println(nodeList2);
			annPath.getAnnPathsObj().put(3.8, nodeList2);
			//break; //break after first item
		}				
		
		System.out.println(annPath.getAnnPathsObj());
		
		// Key update tests
		System.out.println("Updating key using method (3.8 -> 3.9):");
		annPath.updatePath(3.8, 3.9, 12, 15);
		System.out.println(annPath.getAnnPathsObj());
		System.out.println("Updating key using method (3.0 -> 3.3):");
		annPath.updatePath(3.0, 3.3, 6, 16);
		System.out.println(annPath.getAnnPathsObj());
		System.out.println("Non-existing path lenght update test (3.4):");
		annPath.updatePath(3.4, 3.5, 16, 18);
		System.out.println(annPath.getAnnPathsObj());
		//
		
		// Iteration through whole Paths
		System.out.println("Iteration through whole Paths:");
		for (Double key : annPath.getAnnPathsObj().keySet()) {
			Collection<ArrayList<Integer>> nodeListCollection4 = annPath.getAnnPathsObj().get(key);
			System.out.print("Lenght: " + key + " -> ");
			nodeListCollection4.forEach(list -> System.out.print(list + "; "));
			System.out.println();			
		}
		
		annPath.printPathsSortedByLenght();
		
		System.out.println("Return Lenght of Path with given last Node Id (5):");
		System.out.println(annPath.getPathLenght(5));
		
	}

}
