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

public class AnnPath {
	// m_paths: Map<Length, List<NodeId>>; Length - length of current path
	MultiValuedMap<Double, ArrayList<Integer>> m_paths = new HashSetValuedHashMap<Double, ArrayList<Integer>>();

	// get java.util.Map representation of the multi-valued map
	// Map<Double, Collection<ArrayList<Integer>>> mapPaths = m_paths.asMap();

	public MultiValuedMap<Double, ArrayList<Integer>> getAnnPath () { 
		return  m_paths;
	}

}
