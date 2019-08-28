package framework;

//import java.awt.RenderingHints.Key;
import java.util.*;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

public class Graph2 {
	private int m_numEdges;
	private int m_numOfNodes;
	private int intEdgeId = 0;

	// Map <startNodeId, map <endNodeId, edgeLength> >
	private final Map<Integer, Map<Integer, Double>> m_adjancencyMap = new HashMap();

	// list of Nodes with full encapsulated properties:
	// (nodeId, longitude, latitude)
	private ArrayList<Node> m_nodesWithInfo = new ArrayList<Node>();
	private ArrayList<Edge> m_edgeWithInfo = new ArrayList<Edge>();

	// Integer= ObjectId RandomObject=RandomObject
	private Map<Map<Integer, Integer>, Map<Integer, Double>> m_edgeObject = new HashMap<>();
	// private Map<Integer, RandomObject> m_edgeObject=new HashMap<>();

	private Map<Integer, Poi> m_edgeObject1 = new HashMap<Integer, Poi>();

	// Using Multivalued Map that shares common collection
	// Importing from the collections is required before initializing
	// Can work for duplicate keys for distinct values
	private MultiValuedMap<Integer, Poi> m_edgeObject2 = new HashSetValuedHashMap<Integer, Poi>();

	// We will be using this to create a object as it stores.
	// The Edge Id, List of POIs
	private Map<Integer, List<Poi>> m_edgeObject3 = new HashMap<Integer, List<Poi>>();

	private int m_totalNumberOfObjects = 0;
	private int m_totalNumberOfTrueObjects = 0;
	private int m_totalNumberOfFalseObjects = 0;

	public boolean addObject(int objectId, int startNode, int endNode, double distanceFromStartNode) {
		if (!hasEdge(startNode, endNode)) {
			return false;
		}
		Map<Integer, Integer> edge = new HashMap<>();
		edge.put(startNode, endNode);

		if (m_edgeObject.containsKey(edge)) {
			m_edgeObject.get(edge).put(objectId, distanceFromStartNode);
		} else {
			Map<Integer, Double> randObjects = new HashMap<>();
			randObjects.put(objectId, distanceFromStartNode);
			m_edgeObject.put(edge, randObjects);
		}
		return true;
	}

	// For Object3
	public boolean addObjectOnEdge3(int edgeId, Poi newPoi) {

		boolean isTrue = false;
		if (m_edgeWithInfo.isEmpty()) {
			System.out.println("m_edgeWithInfo is empty" + ", for poi: " + newPoi);
			return false;
		}
		for (Edge e : m_edgeWithInfo) {

			if (e.getEdgeId() == edgeId) {
				isTrue = true;
			}

		}
		if (!isTrue) {
			System.out.println("There is no edge: " + edgeId + ", for poi: " + newPoi);
			return false;
		}
		// System.out.println("edgeId: " + edgeId);

		if ((m_edgeObject3 != null) && m_edgeObject3.get(edgeId) != null) {
			for (Poi poi : m_edgeObject3.get(edgeId)) {
				if (poi.getPoiId() == newPoi.getPoiId()
						|| poi.getDistanceFromStartNode() == newPoi.getDistanceFromStartNode()) {
					System.out.println("Either poiId (" + newPoi.getPoiId() + ") or dist from SN ("
							+ newPoi.getDistanceFromStartNode() + ") is existed on edge: " + edgeId);
					return false;
				}
			}
		}

		if (!m_edgeObject3.containsKey(edgeId)) {
			List<Poi> newListOfPoi = new ArrayList<Poi>();
			newListOfPoi.add(newPoi);
			m_edgeObject3.put(edgeId, newListOfPoi);
			m_totalNumberOfObjects++;
			if (newPoi.getType()) {
				m_totalNumberOfTrueObjects++;
			} else {
				m_totalNumberOfFalseObjects++;	
			}

		} else {
			m_edgeObject3.get(edgeId).add(newPoi);
			m_totalNumberOfObjects++;
			if (newPoi.getType()) {
				m_totalNumberOfTrueObjects++;
			} else {
				m_totalNumberOfFalseObjects++;	
			}
		}
		return true;
	}

	public void printObjectOnEdge3() {

		System.out.println("Object Information: ");
		int generatedPoiCounter = 0;

		for (Integer key : m_edgeObject3.keySet()) {
			System.out.println("Edge " + key + ";\t" + "Length: " + getEdgeDistance(key) + " ("
					+ m_edgeObject3.get(key).size() + ") " + m_edgeObject3.get(key));
			generatedPoiCounter += m_edgeObject3.get(key).size();
		}
		System.out.println("Total lenght of all edges: " + getTotalLengthOfAllEdges() + " Total number of POIs: "
				+ generatedPoiCounter);
	}

	public int getTotalNumberOfObjects() {
		return m_totalNumberOfObjects;
	}

	public int getTotalNumberOfTrueObjects() {

		return m_totalNumberOfTrueObjects;
	}

	public int getTotalNumberOfFalseObjects() {

		return m_totalNumberOfFalseObjects;
	}

	public ArrayList<Node> getNodesWithInfo() {
		return m_nodesWithInfo;
	}

	public ArrayList<Edge> getEdgesWithInfo() {
		return m_edgeWithInfo;
	}

	public void setNodesWithInfo(ArrayList<Node> nodes) {
		this.m_nodesWithInfo = nodes;
	}

	public void setEdgeWithInfo(ArrayList<Edge> edgeWithInfo) {
		this.m_edgeWithInfo = edgeWithInfo;
	}

	public Map<Integer, Map<Integer, Double>> getAdjancencyMap() {
		return m_adjancencyMap;
	}

	public int getNumberOfEdges() {
		return m_numEdges;
	}

	public int getNumberOfNodes() {
		return m_numOfNodes;
	}

	private boolean addNode(int int_nodeID) {
		if (m_adjancencyMap.containsKey(int_nodeID)) {
			return false;
		}
		// Node n = new Node();
		// n.setNodeId(int_nodeID);
		// // Long an Lat
		// m_nodesWithInfo.add(n);
		m_adjancencyMap.put(int_nodeID, new LinkedHashMap<>());
		m_numOfNodes++;
		return true;
	}

	public boolean addEdge(int int_startNode, int int_endNode, double doub_distance) {
		if (int_startNode == int_endNode) {
			return false;
		}
		addNode(int_startNode);
		addNode(int_endNode);

		Edge newEdge = new Edge();
		intEdgeId++;
		newEdge.setEdgeId(intEdgeId);
		newEdge.setStartNodeId(int_startNode);
		newEdge.setEndNodeId(int_endNode);
		newEdge.setLength(doub_distance);
		m_edgeWithInfo.add(newEdge);
		if (!m_adjancencyMap.get(int_startNode).containsKey(int_endNode)) {
			m_adjancencyMap.get(int_startNode).put(int_endNode, doub_distance);
			m_adjancencyMap.get(int_endNode).put(int_startNode, doub_distance);
			m_numEdges++;

		} else {
			double prev_doubDistance = m_adjancencyMap.get(int_startNode).get(int_endNode);
			m_adjancencyMap.get(int_startNode).put(int_endNode, doub_distance);
			m_adjancencyMap.get(int_endNode).put(int_startNode, doub_distance);

			if (prev_doubDistance != doub_distance) {

				return true;
			}
		}
		return false;
	}

	public boolean addEdge(int edgeId, int int_startNode, int int_endNode, double doub_distance) {
		if (int_startNode == int_endNode) {
			return false;
		}
		addNode(int_startNode);
		addNode(int_endNode);

		Edge newEdge = new Edge();
		newEdge.setEdgeId(edgeId);
		newEdge.setStartNodeId(int_startNode);
		newEdge.setEndNodeId(int_endNode);
		newEdge.setLength(doub_distance);
		m_edgeWithInfo.add(newEdge);
		if (!m_adjancencyMap.get(int_startNode).containsKey(int_endNode)) {
			m_adjancencyMap.get(int_startNode).put(int_endNode, doub_distance);
			m_adjancencyMap.get(int_endNode).put(int_startNode, doub_distance);
			m_numEdges++;
		} else {
			double prev_doubDistance = m_adjancencyMap.get(int_startNode).get(int_endNode);
			m_adjancencyMap.get(int_startNode).put(int_endNode, doub_distance);
			m_adjancencyMap.get(int_endNode).put(int_startNode, doub_distance);

			if (prev_doubDistance != doub_distance) {

				return true;
			}
		}
		return false;
	}

	public boolean hasEdge(int int_startNode, int int_endNode) {
		if (!m_adjancencyMap.containsKey(int_startNode)) {
			return false;
		}
		return m_adjancencyMap.get(int_startNode).containsKey(int_endNode);

	}

	public double getEdgeDistance(int int_startNode, int int_endNode) {
		if (!hasEdge(int_startNode, int_endNode)) {
			return Double.NaN;
			// NaN: A constant holding a Not-a-Number (NaN) value of type double. It is
			// equivalent to the value returned by Double.longBitsToDouble
		}
		return m_adjancencyMap.get(int_startNode).get(int_endNode);
	}

	public double getEdgeDistance(int edgeId) {
		for (Edge edge : m_edgeWithInfo) {
			if (edgeId == edge.getEdgeId()) {
				return edge.getLength();
			}
		}
		return 0.0;
	}

	public double getTotalLengthOfAllEdges() {
		double total = 0.0;
		for (Edge edge : m_edgeWithInfo) {
			total += edge.getLength();
		}

		return total;
	}

	// print the adjacency list (representation of graph)
	public void printGraph() {

		System.out.println("Adjacency list: ");

		for (Integer key : m_adjancencyMap.keySet()) {

			System.out.println("head " + key + ":\t" + m_adjancencyMap.get(key));
		}
	}

	public boolean removeEdge(int startNode, int endNode) {
		if ((!m_adjancencyMap.containsKey(startNode)) || (!m_adjancencyMap.containsKey(endNode))) {
			return false;
		}
		m_adjancencyMap.get(startNode).remove(endNode);
		m_adjancencyMap.get(endNode).remove(startNode);
		m_numEdges--;
		return true;
	}

	public ArrayList<Integer> getEdges(int int_nodeID) {
		if (!m_adjancencyMap.containsKey(int_nodeID)) {
			return null;
		}
		ArrayList<Integer> int_linkedNodes = new ArrayList<Integer>(m_adjancencyMap.get(int_nodeID).keySet());

		return int_linkedNodes;

	}

	public Map<Integer, Double> getEdgesWithDistances(int node) {

		return m_adjancencyMap.get(node);
	}

	public void printNodesInfo() {
		System.out.println("Nodes' Information: ");

		for (Node n : m_nodesWithInfo) {

			System.out.println(
					"Node Id: " + n.getNodeId() + " Latitude: " + n.getLatitude() + " Longitude : " + n.getLongitude());
			;
		}
	}

	public void printEdgesInfo() {
		System.out.println("Edge's Information: ");

		for (Edge e : m_edgeWithInfo) {
			System.out.println(e.toString());
		}
	}

	public Node getNode(int nodeId) {
		for (int i = 0; i < m_nodesWithInfo.size(); i++) {
			if (m_nodesWithInfo.get(i).getNodeId() == nodeId) {
				return m_nodesWithInfo.get(i);
			}
		}
		return null;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////// Archive//////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void printObjectOnEdge() {

		System.out.println("Object Information: ");

		for (Integer key : m_edgeObject2.keySet()) {
			System.out.println("Edge " + key + ";\t" + m_edgeObject2.get(key));
		}
	}

	public boolean addObjectOnEdge(int edgeId, Poi randObj) {

		if (m_edgeObject2.containsKey(randObj)) {
			return false;
		}

		for (Integer key : m_edgeObject2.keySet()) {
			// System.out.println("Edge " + key + ";\t" + m_edgeObject2.get(key));
			for (Poi poiValue : m_edgeObject2.values()) {
				if (poiValue.getPoiId() == randObj.getPoiId()
						|| poiValue.getDistanceFromStartNode() == randObj.getDistanceFromStartNode()) {
					return false;
				}
			}
		}
		m_edgeObject2.put(edgeId, randObj);
		return true;
	}

	public boolean addObject(int edgeId, Poi randObj) {
		if (m_edgeObject1.containsKey(randObj)) {
			return false;
		}
		m_edgeObject1.put(edgeId, randObj);
		// assertThat((Collection<Integer>)
		// m_edgeObject.get(edgeId)).containsExactly(randObj);
		// assertThat(m_ed)
		return true;

	}
}
