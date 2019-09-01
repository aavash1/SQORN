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
	private ArrayList<Edge> m_edgesWithInfo = new ArrayList<Edge>();

	// We will be using this to create a object as it stores.
	// The Edge Id, List of Objects
	private Map<Integer, ArrayList<RoadObject>> m_objectsOnEdges = new HashMap<Integer, ArrayList<RoadObject>>();

	private int m_totalNumberOfObjects = 0;
	private int m_totalNumberOfTrueObjects = 0;
	private int m_totalNumberOfFalseObjects = 0;

	////////////////////////////////////// [Currently not
	////////////////////////////////////// used////////////////////////////////////////////////
	private Map<Map<Integer, Integer>, Map<Integer, Double>> m_objectsOnEdges1 = new HashMap<>();
	// Multivalued Map can work for duplicate keys
	private MultiValuedMap<Integer, RoadObject> m_objectsOnEdges2 = new HashSetValuedHashMap<Integer, RoadObject>();
	private Map<Integer, RoadObject> m_objectsOnEdges3 = new HashMap<Integer, RoadObject>();
	////////////////////////////////////// Currently not
	////////////////////////////////////// used]////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////// [Node related
	////////////////////////////////////////////////////////////////////////////////////////////////// methods///////////////////////////////////////

	private boolean addNode(int int_nodeID) {
		if (m_adjancencyMap.containsKey(int_nodeID)) {
			return false;
		}
		Node n = new Node();
		n.setNodeId(int_nodeID);
		// // Long an Lat
		m_nodesWithInfo.add(n);
		m_adjancencyMap.put(int_nodeID, new LinkedHashMap<>());
		m_numOfNodes++;
		return true;
	}

	public boolean setNodeLatAndLong(int nodeId, double lat, double longt) {

		Boolean isNodeExist = false;

		if (m_nodesWithInfo.isEmpty())
			return false;

		for (Node n : m_nodesWithInfo) {
			if (n.getNodeId() == nodeId) {
				n.setLatitude(lat);
				n.setLongitude(longt);
				isNodeExist = true;
			}
		}
		if (!isNodeExist)
			return false;
		return true;
	}

	public ArrayList<Node> getNodesWithInfo() {
		return m_nodesWithInfo;
	}

	public void setNodesWithInfo(ArrayList<Node> nodes) {
		this.m_nodesWithInfo = nodes;
	}

	public int getNumberOfNodes() {
		if (m_numOfNodes == 0)
			return m_nodesWithInfo.size();

		return m_numOfNodes;
	}

	public void printNodesInfo() {
		System.out.println("Nodes' Information: ");

		for (Node n : m_nodesWithInfo) {
			System.out.println(
					"Node Id: " + n.getNodeId() + " Latitude: " + n.getLatitude() + " Longitude : " + n.getLongitude());
			;
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

	public boolean isTerminalNode(int edgeID) {

		if (getEdges(edgeID).size() == 1) {
			return true;
		}
		return false;
	}

	public boolean isIntermediateNode(int edgeID) {
		if (getEdges(edgeID).size() == 2) {
			return true;
		}
		return false;
	}

	public boolean isIntersectionNode(int edgeID) {
		if (getEdges(edgeID).size() >= 3) {
			return true;
		}
		return false;
	}

	public int getIndexOfNodeByNodeId(int nodeId) {

		for (Node n : m_nodesWithInfo) {
			if (n.getNodeId() == nodeId) {
				return m_nodesWithInfo.indexOf(n);
			}
		}
		return -1;
	}

	////////////////////////////////////// Node related
	////////////////////////////////////// methods]///////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////// [Edge related
	////////////////////////////////////////////////////////////////////////////////////////////////// methods///////////////////////////////////////

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
		m_edgesWithInfo.add(newEdge);
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
		m_edgesWithInfo.add(newEdge);
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

	public ArrayList<Edge> getEdgesWithInfo() {
		return m_edgesWithInfo;
	}

	public void setEdgeWithInfo(ArrayList<Edge> edgeWithInfo) {
		this.m_edgesWithInfo = edgeWithInfo;
	}

	public int getNumberOfEdges() {
		return m_numEdges;
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
		for (Edge edge : m_edgesWithInfo) {
			if (edgeId == edge.getEdgeId()) {
				return edge.getLength();
			}
		}
		return 0.0;
	}

	public double getTotalLengthOfAllEdges() {
		double total = 0.0;
		for (Edge edge : m_edgesWithInfo) {
			total += edge.getLength();
		}
		return total;
	}

	public void printEdgesInfo() {
		System.out.println("Edge's Information: ");

		for (Edge e : m_edgesWithInfo) {
			System.out.println(e.toString());
		}
	}
	/////////////////////////////////// Edge related
	/////////////////////////////////// methods]//////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////// [Graph related
	////////////////////////////////////////////////////////////////////////////////////////////////// methods/////////////////////////////////////////

	public Map<Integer, Map<Integer, Double>> getAdjancencyMap() {
		return m_adjancencyMap;
	}

	// print the adjacency list (representation of graph)
	public void printGraph() {

		System.out.println("Adjacency list: ");

		for (Integer key : m_adjancencyMap.keySet()) {

			System.out.println("head " + key + ":\t" + m_adjancencyMap.get(key));
		}
	}

	/////////////////////////////////// Graph related
	/////////////////////////////////// methods]/////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////// [Road Object related
	////////////////////////////////////////////////////////////////////////////////////////////////// methods///////////////////////////////////

	// Working with m_objectsOnEdges: Map<Integer, List<RoadObject>>
	public boolean addObjectOnEdge(int edgeId, RoadObject newObj) {

		boolean isEdgeExists = false;
		if (m_edgesWithInfo.isEmpty()) {
			System.out.println("m_edgeWithInfo is empty" + ", for obj: " + newObj);
			return false;
		}
		for (Edge e : m_edgesWithInfo) {

			if (e.getEdgeId() == edgeId) {
				isEdgeExists = true;
			}
		}
		if (!isEdgeExists) {
			System.out.println("There is no edge: " + edgeId + "(for obj: " + newObj + " )");
			return false;
		}
		// System.out.println("edgeId: " + edgeId);

		if ((m_objectsOnEdges != null) && m_objectsOnEdges.get(edgeId) != null) {
			for (RoadObject poi : m_objectsOnEdges.get(edgeId)) {
				if (poi.getObjectId() == newObj.getObjectId()
						|| poi.getDistanceFromStartNode() == newObj.getDistanceFromStartNode()) {
					System.out.println("Either objId (" + newObj.getObjectId() + ") or dist from SN ("
							+ newObj.getDistanceFromStartNode() + ") is existed on edge: " + edgeId);
					return false;
				}
			}
		}

		if (!m_objectsOnEdges.containsKey(edgeId)) {
			ArrayList<RoadObject> newListOfObjs = new ArrayList<RoadObject>();
			newListOfObjs.add(newObj);
			m_objectsOnEdges.put(edgeId, newListOfObjs);
			m_totalNumberOfObjects++;
			if (newObj.getType()) {
				m_totalNumberOfTrueObjects++;
			} else {
				m_totalNumberOfFalseObjects++;
			}

		} else {
			m_objectsOnEdges.get(edgeId).add(newObj);
			m_totalNumberOfObjects++;
			if (newObj.getType()) {
				m_totalNumberOfTrueObjects++;
			} else {
				m_totalNumberOfFalseObjects++;
			}
		}
		return true;
	}

	// Working with m_objectsOnEdges: Map<Integer, List<RoadObject>>
	public void printObjectsOnEdges() {

		System.out.println("Object Information: ");
		int generatedObjCounter = 0;

		for (Integer key : m_objectsOnEdges.keySet()) {
			System.out.println("Edge " + key + ";\t" + "Length: " + getEdgeDistance(key) + " ("
					+ m_objectsOnEdges.get(key).size() + ") " + m_objectsOnEdges.get(key));
			generatedObjCounter += m_objectsOnEdges.get(key).size();
		}
		System.out.println("Total lenght of all edges: " + getTotalLengthOfAllEdges() + " Total number of Objs: "
				+ generatedObjCounter);
	}

	public Map<Integer, ArrayList<RoadObject>> getObjectsOnEdges() {
		return m_objectsOnEdges;
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

	// All Objects
	public ArrayList<RoadObject> getAllObjectsOnGivenEdge(int edgeId) {
		return m_objectsOnEdges.get(edgeId);
	}

	public ArrayList<Integer> getAllObjectsIdOnGivenEdge(int edgeId) {
		ArrayList<Integer> listOfObjIds = new ArrayList<Integer>();

		for (RoadObject obj : m_objectsOnEdges.get(edgeId)) {
			listOfObjIds.add(obj.getObjectId());
		}
		return listOfObjIds;
	}

	// All True Objects
	public ArrayList<RoadObject> getAllTrueObjectsOnGivenEdge(int edgeId) {
		ArrayList<RoadObject> listOfTrueObjs = new ArrayList<RoadObject>();
		for (RoadObject obj : m_objectsOnEdges.get(edgeId)) {
			if (obj.getType() == true) {
				listOfTrueObjs.add(obj);
			}
		}
		return listOfTrueObjs;
	}

	public ArrayList<Integer> getAllTrueObjectsIdOnGivenEdge(int edgeId) {
		ArrayList<Integer> listOfTrueObjIds = new ArrayList<Integer>();

		for (RoadObject obj : m_objectsOnEdges.get(edgeId)) {
			if (obj.getType() == true) {
				listOfTrueObjIds.add(obj.getObjectId());
			}
		}
		return listOfTrueObjIds;
	}

	// All False Objects
	public ArrayList<RoadObject> getAllFalseObjectsOnGivenEdge(int edgeId) {
		ArrayList<RoadObject> listOfFalseObjs = new ArrayList<RoadObject>();
		for (RoadObject obj : m_objectsOnEdges.get(edgeId)) {
			if (obj.getType() == false) {
				listOfFalseObjs.add(obj);
			}
		}
		return listOfFalseObjs;
	}

	public ArrayList<Integer> getAllFalseObjectsIdOnGivenEdge(int edgeId) {
		ArrayList<Integer> listOfFalseObjs = new ArrayList<Integer>();

		for (RoadObject obj : m_objectsOnEdges.get(edgeId)) {
			if (obj.getType() == false) {
				listOfFalseObjs.add(obj.getObjectId());
			}
		}
		return listOfFalseObjs;
	}

	// Distance related methods
	public RoadObject getNearestObjectOnEdge(int edgeId, RoadObject sourceObj) {
		RoadObject obj = new RoadObject();

		return obj;

	}

	// Distance related methods
	public int getNearestObjectIdOnEdge(int edgeId, int sourceObjId) {
		// RoadObject obj = new RoadObject();
		int nearestObjId = -1;

		return nearestObjId;

	}

	public RoadObject getNearestTrueObjectOnEdge(int edgeId, RoadObject sourceObj) {
		RoadObject obj = new RoadObject();

		return obj;

	}

	// Distance related methods
	public int getNearestTrueObjectIdOnEdge(int edgeId, int sourceObjId) {
		// RoadObject obj = new RoadObject();
		int nearestObjId = -1;

		return nearestObjId;

	}

	public RoadObject getNearestFalseObjectOnEdge(int edgeId, RoadObject sourceObj) {
		RoadObject obj = new RoadObject();

		return obj;

	}

	// Distance related methods
	public int getNearestFalseObjectIdOnEdge(int edgeId, int sourceObjId) {
		// RoadObject obj = new RoadObject();
		int nearestObjId = -1;

		return nearestObjId;

	}

	////////////////////////////////////// Road Object related
	////////////////////////////////////// methods]////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////// Currently not used
	////////////////////////////////////////////////////////////////////////////////////////////////// methods//////////////////////////////////

	// Working with m_objectsOnEdges1: Map<Map<Integer, Integer>, Map<Integer,
	// Double>>
	public boolean addObject1(int objectId, int startNode, int endNode, double distanceFromStartNode) {
		if (!hasEdge(startNode, endNode)) {
			return false;
		}
		Map<Integer, Integer> edge = new HashMap<>();
		edge.put(startNode, endNode);

		if (m_objectsOnEdges1.containsKey(edge)) {
			m_objectsOnEdges1.get(edge).put(objectId, distanceFromStartNode);
		} else {
			Map<Integer, Double> randObjects = new HashMap<>();
			randObjects.put(objectId, distanceFromStartNode);
			m_objectsOnEdges1.put(edge, randObjects);
		}
		return true;
	}

	////////////////////////////////////// Currently not
	////////////////////////////////////// used//////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////// Archive///////////////////////////////////////////////////
	public void printObjectsOnEdges2() {

		System.out.println("Object Information: ");

		for (Integer key : m_objectsOnEdges2.keySet()) {
			System.out.println("Edge " + key + ";\t" + m_objectsOnEdges2.get(key));
		}
	}

	public boolean addObjectOnEdge2(int edgeId, RoadObject newObj) {

		if (m_objectsOnEdges2.containsKey(newObj)) {
			return false;
		}

		for (Integer key : m_objectsOnEdges2.keySet()) {
			// System.out.println("Edge " + key + ";\t" + m_edgeObject2.get(key));
			for (RoadObject poiValue : m_objectsOnEdges2.values()) {
				if (poiValue.getObjectId() == newObj.getObjectId()
						|| poiValue.getDistanceFromStartNode() == newObj.getDistanceFromStartNode()) {
					return false;
				}
			}
		}
		m_objectsOnEdges2.put(edgeId, newObj);
		return true;
	}

	public boolean addObject3(int edgeId, RoadObject newObj) {
		if (m_objectsOnEdges3.containsKey(newObj)) {
			return false;
		}
		m_objectsOnEdges3.put(edgeId, newObj);
		// assertThat((Collection<Integer>)
		// m_edgeObject.get(edgeId)).containsExactly(newObj);
		// assertThat(m_ed)
		return true;

	}

}
