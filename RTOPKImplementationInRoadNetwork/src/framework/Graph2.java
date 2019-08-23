package framework;

//import java.awt.RenderingHints.Key;
import java.util.*;

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

	private Map<Integer, RandomObject> m_edgeObject1 = new HashMap<>();

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

	public boolean addObject( int edgeId, RandomObject randObj) {
		if (m_edgeObject.containsKey(randObj)) {
			return false;
		}
		m_edgeObject1.put(edgeId, randObj);
		return false;

	}

	public void printObjectonEdge() {

		System.out.print("Object Information: ");

		for (Integer key : m_edgeObject1.keySet()) {
			System.out.println("Edge" + key + ";\t" + m_edgeObject.get(key));

		}

	}

	public ArrayList<Node> getNodesWithInfo() {
		return m_nodesWithInfo;
	}

	public ArrayList<Edge> getEdgesWithInfo() {
		return m_edgeWithInfo;
	}

	public void setNodesWithInfo(ArrayList<Node> m_nodes) {
		this.m_nodesWithInfo = m_nodes;
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
//		Node n = new Node();
//		n.setNodeId(int_nodeID);
//		// Long an Lat
//		m_nodesWithInfo.add(n);
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

}
