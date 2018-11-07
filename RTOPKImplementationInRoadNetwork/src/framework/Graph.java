package framework;

//import java.awt.RenderingHints.Key;
import java.util.*;

public class Graph {
	private int m_numEdges;
	private int m_numOfNodes;

	private int intEdgeId = 0;
	// Map <startNodeId, map <endNodeId, edgeLength> >
	private final Map<Integer, Map<Integer, Double>> m_adjancencyMap = new HashMap();

	// Map < map <startNodeId,EndNodeId>, map <poiId, distanceFromSource> >
	private final Map<Map<Integer, Integer>, Map<Integer, Double>> m_edgePoiMap = new HashMap();

	// list of Nodes with full encapsulated properties:
	// (nodeId, longitude, latitude)
	private ArrayList<Node> m_nodesWithInfo = new ArrayList<Node>();

	private ArrayList<Edge> m_edgeWithInfo = new ArrayList<Edge>();

	// list of POIs with full encapsulated properties:
	// (poiId, longitude, latitude, categoryId, distanceFromStartNode, type, rating)
	private ArrayList<Poi> m_poisWithInfo = new ArrayList<Poi>();

	public ArrayList<Node> getNodesWithInfo() {
		return m_nodesWithInfo;
	}

	public ArrayList<Edge> getEdgesWithInfo() {
		return m_edgeWithInfo;
	}

	public void setNodesWithInfo(ArrayList<Node> m_nodes) {
		this.m_nodesWithInfo = m_nodes;
	}

	public ArrayList<Poi> getPoisWithInfo() {
		return m_poisWithInfo;
	}

	public void setPoisWithInfo(ArrayList<Poi> m_pois) {
		this.m_poisWithInfo = m_pois;
	}

	public Map<Integer, Map<Integer, Double>> getAdjancencyMap() {
		return m_adjancencyMap;
	}

	public Map<Map<Integer, Integer>, Map<Integer, Double>> getEdgePoiMap() {
		return m_edgePoiMap;
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
		Node n = new Node();
		n.setNodeId(int_nodeID);
		// Long an Lat
		m_nodesWithInfo.add(n);
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

	// Implementation for POI mapping in respective edges.
	public boolean addPoi(int poiId, int startNode, int endNode, double distFromStartNode) {

		if (!hasEdge(startNode, endNode)) {
			return false;
		}

		Map<Integer, Integer> edge = new HashMap<>();
		edge.put(startNode, endNode);

		if (m_edgePoiMap.containsKey(edge)) {
			m_edgePoiMap.get(edge).put(poiId, distFromStartNode);
		} else {
			Map<Integer, Double> pois = new HashMap<>();
			pois.put(poiId, distFromStartNode);

			m_edgePoiMap.put(edge, pois);
		}

		return true;
	}

	public boolean addPoi(int poiId, int startNode, int endNode, double distFromStartNode, int categoryId) {

		if (!hasEdge(startNode, endNode)) {
			return false;
		}

		Map<Integer, Integer> edge = new HashMap<>();
		edge.put(startNode, endNode);

		if (m_edgePoiMap.containsKey(edge)) {
			m_edgePoiMap.get(edge).put(poiId, distFromStartNode);
		} else {
			Map<Integer, Double> pois = new HashMap<>();
			pois.put(poiId, distFromStartNode);

			m_edgePoiMap.put(edge, pois);
		}

		return true;
	}

	public boolean addPoi(Poi newPoi, int startNodeId, int endNodeId, double distanceFromStartNode) {

		addPoi(newPoi.getPoiId(), startNodeId, endNodeId, distanceFromStartNode);

		return true;

	}

	public void printPoisOnEdge() {
		System.out.println("Point of Interest List: ");

		for (Map<Integer, Integer> key : m_edgePoiMap.keySet()) {

			System.out.println("Edge " + key + ":\t" + m_edgePoiMap.get(key));
		}
	}

	public void printNodesInfo() {
		System.out.println("Nodes' Information: ");

		for (Node n : m_nodesWithInfo) {

			System.out.println(
					"Node Id: " + n.getNodeId() + " Latitude: " + n.getLatitude() + " Longitude : " + n.getLongitude());
			;
		}
	}

	public void printPoisInfo() {
		System.out.println("POIs' Information: ");

		for (Poi p : m_poisWithInfo) {

			// System.out.println(
			// "POI Id: " + p.getPoiId() + " Longitude: " + p.getLongitude() + " Latitude: "
			// + p.getLatitude());
			// ;
			System.out.println(p.toString());
		}
	}

	public void printEdgesInfo() {
		System.out.println("Edge's Information: ");

		for (Edge e : m_edgeWithInfo) {
			System.out.println(e.toString());
		}
	}

	public ArrayList<Integer> getPois(int startNode, int endNode) {
		if (!hasEdge(startNode, endNode)) {
			return null;
		}
		Map<Integer, Integer> edge = new HashMap<>();
		edge.put(startNode, endNode);
		ArrayList<Integer> int_POI = new ArrayList<Integer>();
		for (Integer key : m_edgePoiMap.get(edge).keySet()) {
			int_POI.add(key);
		}
		return int_POI;
	}

	public Map<Integer, Double> getPoisWithDistance(int startNode, int endNode) {
		if (!hasEdge(startNode, endNode)) {
			return null;
		}

		Map<Integer, Integer> edges = new HashMap<>();
		edges.put(startNode, endNode);

		return m_edgePoiMap.get(edges);
	}

	// return startNode and endNode of Edge on which given Poi is located
	public Map<Integer, Integer> getPoiEdge(int poiId) {
		if (!m_poisWithInfo.contains(poiId)) {
			System.err.println("POI ID Unavailable");
			return null;
		}
		// required elaboration
		// find return value from here: m_edgePoiMap

		return null;
	}

	// return distance from start node to the given Poi
	public Double getPoiDistanceFromSource(int poiId) {
		if (!m_poisWithInfo.contains(poiId)) {
			System.err.println("POI ID Unavailable");
			return null;
		}
		return m_poisWithInfo.get(poiId).getDistanceFromStartNode();

	}

	public Node getNode(int nodeId) {
		for (int i = 0; i < m_nodesWithInfo.size(); i++) {
			if (m_nodesWithInfo.get(i).getNodeId() == nodeId) {
				return m_nodesWithInfo.get(i);
			}
		}
		return null;
	}

	// visual display of graph
	public void display() {

	}

}
