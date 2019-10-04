package testing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

public class WhileTestClas {
	public static void main(String[] args) {

		MultiValuedMap<Double, Integer> tm = new HashSetValuedHashMap<Double, Integer>();

		tm.put(1.032, 1);
		tm.put(1.301, 2);
		tm.put(2.21, 3);
		tm.put(0.91, 6);
		tm.put(5.28, 5);
		tm.put(6.12, 4);
		tm.put(4.01, 7);
		tm.put(3.82, 9);
		tm.put(7.25, 8);
		tm.put(7.13, 10);
		tm.put(7.01, 11);
		tm.put(4.01, 7);
		tm.put(10.12, 13);
		tm.put(4.01, 12);
		tm.put(0.32, 91);

		System.out.println(tm);

		TreeMap<Double, Integer> trm = new TreeMap<Double, Integer>();

		MultiValuedMap<Double, Integer> tm1 = new ArrayListValuedHashMap<Double, Integer>();

		tm1.put(1.032, 1);
		tm1.put(1.301, 2);
		tm1.put(2.21, 3);
		tm1.put(0.91, 6);
		tm1.put(5.28, 5);
		tm1.put(6.12, 4);
		tm1.put(4.01, 7);
		tm1.put(3.82, 9);
		tm1.put(7.25, 8);
		tm1.put(7.13, 10);
		tm1.put(7.01, 11);
		tm1.put(4.01, 7);
		tm1.put(10.12, 13);
		tm1.put(4.01, 12);

		System.out.println(tm1);

		System.out.println(sortMultiValuedMap(tm));
		System.out.println(getMintKey(tm));
	}

	private static MultiValuedMap<Double, Integer> sortMultiValuedMap(MultiValuedMap<Double, Integer> mvm) {

		MultiValuedMap<Double, Integer> mvmSorted = new HashSetValuedHashMap<Double, Integer>();
		// mvmSorted.putAll(mvm);

		ArrayList<Double> keys = new ArrayList<Double>();
		keys.addAll(mvm.keySet());
		Collections.sort(keys);
		System.out.println(mvm.size());
		ArrayList<Integer> values = new ArrayList<Integer>();
		for (int i = 0; i < mvm.size() - 1; i++) {
			// values.add(mvm.get(keys.get(i)));
			Collection<Integer> value = mvm.get(keys.get(i));
			values.addAll(value);
		}

		for (int j = 0; j < mvm.size() - 1; j++) {

			mvmSorted.putAll(keys.get(j), mvm.get(keys.get(j)));
		}

		System.out.println(keys);
		// SortedMap<Integer> newMap=new TreeMap<Integer>();

		return mvmSorted;

	}

	private static Double getMintKey(MultiValuedMap<Double, Integer> mvm) {

		ArrayList<Double> keys = new ArrayList<Double>(mvm.keySet());
		
		Collections.sort(keys);

		return keys.get(0);

	}

}
