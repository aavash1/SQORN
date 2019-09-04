package testing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.collections4.MultiSet;

import framework.AnnPath;

public class AnnpathTest {

	public static void main(String[] args) {

		AnnPath annPath = new AnnPath();
		
		// get java.util.Map representation of the multi-valued map
		// Map<Double, Collection<ArrayList<Integer>>> mapPaths = m_paths.asMap();

		ArrayList<Integer> nodes = new ArrayList<Integer>();
		nodes.add(2);
		nodes.add(4);
		annPath.getAnnPath().put(3.5, nodes);
		System.out.println(annPath.getAnnPath());

		ArrayList<Integer> nodes2 = new ArrayList<Integer>();
		nodes2.add(1);
		nodes2.add(3);
		annPath.getAnnPath().put(2.4, nodes2);
		System.out.println(annPath.getAnnPath());

		ArrayList<Integer> nodes3 = new ArrayList<Integer>();
		nodes3.add(5);
		nodes3.add(6);
		annPath.getAnnPath().put(3.0, nodes3);
		System.out.println(annPath.getAnnPath());

		ArrayList<Integer> nodes4 = new ArrayList<Integer>();
		nodes4.add(7);
		nodes4.add(8);
		annPath.getAnnPath().put(3.6, nodes4);
		System.out.println(annPath.getAnnPath());

		ArrayList<Integer> nodes5 = new ArrayList<Integer>();
		nodes5.add(0);
		nodes5.add(9);
		annPath.getAnnPath().put(3.1, nodes5);
		System.out.println(annPath.getAnnPath());

		// after adding duplicated key (3.1)
		ArrayList<Integer> nodes6 = new ArrayList<Integer>();
		nodes6.add(0);
		nodes6.add(12);
		annPath.getAnnPath().put(3.1, nodes6);
		System.out.println(annPath.getAnnPath());
		ArrayList<Integer> nodes61 = new ArrayList<Integer>();
		nodes61.add(1);
		nodes61.add(5);
		annPath.getAnnPath().put(3.1, nodes61);
		System.out.println(annPath.getAnnPath());
		ArrayList<Integer> nodes62 = new ArrayList<Integer>();
		nodes62.add(1);
		nodes62.add(7);
		annPath.getAnnPath().put(3.1, nodes62);
		System.out.println(annPath.getAnnPath());

		System.out.println("----------adding node list with same distance key-----------");
		ArrayList<Integer> nodes7 = new ArrayList<Integer>();
		nodes7.add(13);
		nodes7.add(14);
		// mapPaths.get(3.0).add(nodes7);
		annPath.getAnnPath().get(3.0).add(nodes7);
		System.out.println(annPath.getAnnPath());
		System.out.println("values of 3.1:");
		System.out.println(annPath.getAnnPath().get(3.1));
		System.out.println("1st node list of values of 3.1:");
		// ArrayList<Integer> nodeList = new ArrayList<Integer>();
		Collection<ArrayList<Integer>> nodeListCollection = new HashSet<ArrayList<Integer>>();
		nodeListCollection = annPath.getAnnPath().get(3.1);
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

		Set<Double> keySet = annPath.getAnnPath().keySet();
		System.out.println(keySet);
		MultiSet<Double> keys = annPath.getAnnPath().keys();
		System.out.println(keys);
		// keySet.forEach(key -> System.out.println(key));

		System.out.println("Sorted node lists:");
		for (Double key : new TreeSet<Double>(annPath.getAnnPath().keySet())) {
			System.out.println(annPath.getAnnPath().get(key));
		}
		System.out.println("Sorted lenghts:");
		TreeSet<Double> minDistances = new TreeSet<Double>(annPath.getAnnPath().keySet());
		System.out.println(minDistances);
		System.out.println("min lenght:");
		System.out.println(minDistances.first());
		System.out.println("max lenght:");
		System.out.println(minDistances.last());

	}

}
