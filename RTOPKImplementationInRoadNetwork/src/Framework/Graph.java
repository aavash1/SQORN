package Framework;

import java.util.*;

public class Graph {
	private int modificationCount;
	private int m_numEdges;
	private final Map<Integer, Map<Integer, Double>> m_GraphMap = new LinkedHashMap();

	public int getNumberOfEdges() {
		return m_numEdges;
	}

	public boolean addNode(int int_nodeID) {
		if (m_GraphMap.containsKey(int_nodeID)) {
			return false;
		}
		m_GraphMap.put(int_nodeID, new LinkedHashMap<>());
		modificationCount++;
		return true;
	}

	public boolean addEdge(int int_startNode, int int_endNode, double doub_distance) {
		if (int_startNode == int_endNode) {
			return false;
		}
		addNode(int_startNode);
		addNode(int_endNode);

		if (!m_GraphMap.get(int_startNode).containsKey(int_endNode)) {
			m_GraphMap.get(int_startNode).put(int_endNode, doub_distance);
			m_GraphMap.get(int_endNode).put(int_startNode, doub_distance);
			modificationCount++;
			m_numEdges++;

		} else {
			double prev_doubDistance = m_GraphMap.get(int_startNode).get(int_endNode);
			m_GraphMap.get(int_startNode).put(int_endNode, doub_distance);
			m_GraphMap.get(int_endNode).put(int_startNode, doub_distance);

			if (prev_doubDistance != doub_distance) {
				modificationCount++;
				return true;
			}
		}
		return false;
	}

	public boolean hasEdge(int int_startNode, int int_endNode) {
		if (!m_GraphMap.containsKey(int_startNode)) {
			return false;
		}
		return m_GraphMap.get(int_startNode).containsKey(int_endNode);

	}

	public double getEdgeDistance(int int_startNode, int int_endNode) {
		if (!hasEdge(int_startNode, int_endNode)) {
			return Double.NaN;
			// NaN: A constant holding a Not-a-Number (NaN) value of type double. It is
			// equivalent to the value returned by Double.longBitsToDouble
		}
		return m_GraphMap.get(int_startNode).get(int_endNode);
	}

}
