package framework;

//import java.awt.RenderingHints.Key;
import java.util.*;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

public class Graph {
	private int m_numEdges;
	private int m_numOfNodes;
	private int intEdgeId = 0;

	// m_adjancencyMap: Map<startNodeId, Map <endNodeId, edgeLength> >
	private final Map<Integer, Map<Integer, Double>> m_adjancencyMap = new HashMap<Integer, Map<Integer, Double>>();

	// list of Nodes with full encapsulated properties:
	// (nodeId, longitude, latitude)
	private ArrayList<Node> m_nodesWithInfo = new ArrayList<Node>();
	private ArrayList<Edge> m_edgesWithInfo = new ArrayList<Edge>();

	// We will be using this to create a object as it stores.
	// m_objectsOnEdges: Map<Edge Id, ArrayList<RoadObjects>>
	private Map<Integer, ArrayList<RoadObject>> m_objectsOnEdges = new HashMap<Integer, ArrayList<RoadObject>>();

	private int m_totalNumberOfObjects = 0;
	private int m_totalNumberOfTrueObjects = 0;
	private int m_totalNumberOfFalseObjects = 0;

	private int m_objToEdgeId = 10; // this number helps to identify edge on which obj is located, sync it with same
									// variable in RandomObjectGenerator class

	////////////////////////////////////// [Currently not
	////////////////////////////////////// used////////////////////////////////////////
	private Map<Map<Integer, Integer>, Map<Integer, Double>> m_objectsOnEdges1 = new HashMap<>();
	// Multivalued Map can work for duplicate keys
	private MultiValuedMap<Integer, RoadObject> m_objectsOnEdges2 = new HashSetValuedHashMap<Integer, RoadObject>();
	private Map<Integer, RoadObject> m_objectsOnEdges3 = new HashMap<Integer, RoadObject>();
	////////////////////////////////////// Currently not
	////////////////////////////////////// used]////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////// [Node related
	////////////////////////////////////////////////////////////////////////////////////////////////// methods//////////////////////////////////////

	private boolean addNode(int nodeId) {
		if (m_adjancencyMap.containsKey(nodeId)) {
			return false;
		}
		Node n = new Node();
		n.setNodeId(nodeId);
		m_nodesWithInfo.add(n);
		m_adjancencyMap.put(nodeId, new LinkedHashMap<>());
		m_numOfNodes++;
		return true;
	}

	private boolean addNode(int nodeId, double lat, double longt) {
		if (m_adjancencyMap.containsKey(nodeId)) {
			return false;
		}
		Node n = new Node();
		n.setNodeId(nodeId);
		n.setLatitude(lat);
		n.setLongitude(longt);
		m_nodesWithInfo.add(n);
		m_adjancencyMap.put(nodeId, new LinkedHashMap<>());
		m_numOfNodes++;
		return true;
	}

	public boolean setNodeLatAndLong(int nodeId, double lat, double longt) {
		boolean isNodeExist = false;
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

	public ArrayList<Integer> getAdjNodeIds(int int_nodeId) {
		if (!m_adjancencyMap.containsKey(int_nodeId)) {
			return null;
		}
		ArrayList<Integer> int_linkedNodes = new ArrayList<Integer>(m_adjancencyMap.get(int_nodeId).keySet());
		return int_linkedNodes;
	}

	public Map<Integer, Double> getAdjNodeIdsWithDistanceToEach(int node) {
		return m_adjancencyMap.get(node);
	}

	public boolean isTerminalNode(int nodeId) {

		if (getAdjNodeIds(nodeId).size() == 1) {
			return true;
		}
		return false;
	}

	public boolean isIntermediateNode(int nodeId) {
		if (getAdjNodeIds(nodeId).size() == 2) {
			return true;
		}
		return false;
	}

	public boolean isIntersectionNode(int nodeId) {
		if (getAdjNodeIds(nodeId).size() >= 3) {
			return true;
		}
		return false;
	}

	public boolean isStartNode(int nodeId, int edgeId) {
		int startNodeId = getStartNodeIdOfEdge(edgeId);
		if (startNodeId == nodeId) {
			return true;
		}
		return false;
	}

	public ArrayList<Integer> getStartAndEndNodes(int edgeId) {
		ArrayList<Integer> nodeList = new ArrayList<Integer>();
		int startNodeId = getStartNodeIdOfEdge(edgeId);
		int endNodeId = getEndNodeIdOfEdge(edgeId);
		nodeList.add(startNodeId);
		nodeList.add(endNodeId);
		return nodeList;
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

	public boolean addEdge(int startNode, int endNode, double distance) {
		if (startNode == endNode) {
			return false;
		}
		addNode(startNode);
		addNode(endNode);

		Edge newEdge = new Edge();
		intEdgeId++;
		newEdge.setEdgeId(intEdgeId);
		newEdge.setStartNodeId(startNode);
		newEdge.setEndNodeId(endNode);
		newEdge.setLength(distance);
		m_edgesWithInfo.add(newEdge);
		if (!m_adjancencyMap.get(startNode).containsKey(endNode)) {
			m_adjancencyMap.get(startNode).put(endNode, distance);
			m_adjancencyMap.get(endNode).put(startNode, distance);
			m_numEdges++;

		} else {
			double prev_doubDistance = m_adjancencyMap.get(startNode).get(endNode);
			m_adjancencyMap.get(startNode).put(endNode, distance);
			m_adjancencyMap.get(endNode).put(startNode, distance);

			if (prev_doubDistance != distance) {

				return true;
			}
		}
		return false;
	}

	public boolean addEdge(int edgeId, int startNode, int endNode, double distance) {
		if (startNode == endNode) {
			return false;
		}
		addNode(startNode);
		addNode(endNode);

		Edge newEdge = new Edge();
		newEdge.setEdgeId(edgeId);
		newEdge.setStartNodeId(startNode);
		newEdge.setEndNodeId(endNode);
		newEdge.setLength(distance);
		m_edgesWithInfo.add(newEdge);
		if (!m_adjancencyMap.get(startNode).containsKey(endNode)) {
			m_adjancencyMap.get(startNode).put(endNode, distance);
			m_adjancencyMap.get(endNode).put(startNode, distance);
			m_numEdges++;
		} else {
			double prev_doubDistance = m_adjancencyMap.get(startNode).get(endNode);
			m_adjancencyMap.get(startNode).put(endNode, distance);
			m_adjancencyMap.get(endNode).put(startNode, distance);

			if (prev_doubDistance != distance) {

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
			if (edge.getEdgeId() == edgeId) {
				return edge.getLength();
			}
		}
		return 0.0;
	}

	public int getStartNodeIdOfEdge(int edgeId) {
		for (Edge edge : m_edgesWithInfo) {
			if (edge.getEdgeId() == edgeId) {
				return edge.getStartNodeId();
			}
		}
		return -1;
	}

	public int getEndNodeIdOfEdge(int edgeId) {
		for (Edge edge : m_edgesWithInfo) {
			if (edge.getEdgeId() == edgeId) {
				return edge.getEndNodeId();
			}
		}
		return -1;
	}

	public int getEdgeIdStrictOrder(int startNodeId, int endNodeId) {
		if (!hasEdge(startNodeId, endNodeId)) {
			return -1;
		}
		for (Edge edge : m_edgesWithInfo) {
			if ((edge.getStartNodeId() == startNodeId) && (edge.getEndNodeId() == endNodeId)) {
				return edge.getEdgeId();
			}
		}
		return -1;
	}

	public int getEdgeId(int nodeId1, int nodeId2) {
		for (Edge edge : m_edgesWithInfo) {
			if ((edge.getStartNodeId() == nodeId1) && (edge.getEndNodeId() == nodeId2)) {
				return edge.getEdgeId();
			}
			if ((edge.getStartNodeId() == nodeId2) && (edge.getEndNodeId() == nodeId1)) {
				return edge.getEdgeId();
			}
		}
		return -1;
	}

	// VALIDATE THIS METHOD
	public ArrayList<Integer> getAdjacencyEdgeIds(int edgeId) {
		ArrayList<Integer> edgeIdList = new ArrayList<Integer>();
		int startNode = getStartNodeIdOfEdge(edgeId);
		int endNode = getEndNodeIdOfEdge(edgeId);

		ArrayList<Integer> adjNodesToStartNode = new ArrayList<Integer>();
		ArrayList<Integer> adjNodesToEndNode = new ArrayList<Integer>();

		adjNodesToStartNode = getAdjNodeIds(startNode);
		adjNodesToEndNode = getAdjNodeIds(endNode);

		for (Integer adjNode : adjNodesToStartNode) {
			int newAdjEdgeId1 = getEdgeId(adjNode, startNode);
			if (newAdjEdgeId1 > 0)
				edgeIdList.add(newAdjEdgeId1);
			int newAdjEdgeId2 = getEdgeId(startNode, adjNode);
			if (newAdjEdgeId2 > 0)
				edgeIdList.add(newAdjEdgeId2);

		}
		for (Integer adjNode : adjNodesToEndNode) {
			int newAdjEdgeId1 = getEdgeId(adjNode, endNode);
			if (newAdjEdgeId1 > 0)
				edgeIdList.add(newAdjEdgeId1);
			int newAdjEdgeId2 = getEdgeId(endNode, adjNode);
			if (newAdjEdgeId2 > 0)
				edgeIdList.add(newAdjEdgeId2);
		}
		// removing source edge itself (twice)
		edgeIdList.remove(edgeIdList.indexOf(edgeId));
		edgeIdList.remove(edgeIdList.lastIndexOf(edgeId));
		return edgeIdList;
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

	public RoadObject getRoadObject(int objId) {

		for (Integer edgeId : m_objectsOnEdges.keySet()) {
			for (RoadObject obj : m_objectsOnEdges.get(edgeId)) {
				if (obj.getObjectId() == objId) {
					return obj;
				}
			}
		}
		return null;
	}

	public RoadObject getRoadObjectOnEdge(int edgeId, int objId) {
		if (m_objectsOnEdges.get(edgeId) != null) {
			for (RoadObject obj : m_objectsOnEdges.get(edgeId)) {
				if (obj.getObjectId() == objId) {
					return obj;
				}
			}
		} else {
			System.out.println("Obj Id " + objId + " is not located on edge id " + edgeId);
		}
		return null;
	}

	public Map<Integer, ArrayList<RoadObject>> getObjectsOnEdges() {
		return m_objectsOnEdges;
	}

	public int getEdgeIdOfRoadObject(int objId) {
		// for (Integer edgeId : m_objectsOnEdges.keySet()) {
		// for (RoadObject obj : m_objectsOnEdges.get(edgeId)) {
		// if (obj.getObjectId() == objId) {
		// return edgeId;
		// }
		// }
		// }
		return objId / m_objToEdgeId;
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

	// NOT COMPLETE YET
	public ArrayList<Integer> getNeighborEdgeIdListWithObjs(int edgeId) {
		ArrayList<Integer> edgeIdList = new ArrayList<Integer>();

		ArrayList<Integer> adjEdgeIdList = getAdjNodeIds(edgeId);

		return edgeIdList;
	}

	// All Objects
	public int getNumberOfObjectsOnGivenEdge(int edgeId) {
		if (m_objectsOnEdges.get(edgeId) != null) {
			return m_objectsOnEdges.get(edgeId).size();
		} else {
			//System.out.println("There are no objects on the edge #" + edgeId);
			return 0;
		}
	}

	public ArrayList<RoadObject> getAllObjectsOnGivenEdge(int edgeId) {
		if (m_objectsOnEdges.get(edgeId) != null) {
			return m_objectsOnEdges.get(edgeId);
		} else {
			//System.out.println("There are no objects on the edge #" + edgeId);
			return new ArrayList<RoadObject>();
		}

	}

	public ArrayList<Integer> getAllObjectsIdOnGivenEdge(int edgeId) {

		return convertObjListToIdList(getAllObjectsOnGivenEdge(edgeId));

		// if (m_objectsOnEdges.get(edgeId) != null) {
		// ArrayList<Integer> listOfObjIds = new ArrayList<Integer>();
		// for (RoadObject obj : m_objectsOnEdges.get(edgeId)) {
		// listOfObjIds.add(obj.getObjectId());
		// }
		// return listOfObjIds;
		// } else {
		// System.out.println("There are no objects on the edge #" + edgeId);
		// return new ArrayList<Integer>();
		// }
	}

	// All True Objects
	public int getNumberOfTrueObjectsOnGivenEdge(int edgeId) {
		int numberOfTrueObjs = 0;
		if (m_objectsOnEdges.get(edgeId) != null) {
			for (RoadObject obj : m_objectsOnEdges.get(edgeId)) {
				if (obj.getType() == true) {
					numberOfTrueObjs++;
				}
			}
		} else {
			//System.out.println("There are no objects on the edge #" + edgeId);
		}
		return numberOfTrueObjs;
	}

	public ArrayList<RoadObject> getTrueObjectsOnGivenEdge(int edgeId) {
		if (m_objectsOnEdges.get(edgeId) != null) {
			ArrayList<RoadObject> listOfTrueObjs = new ArrayList<RoadObject>();
			for (RoadObject obj : m_objectsOnEdges.get(edgeId)) {
				if (obj.getType() == true) {
					listOfTrueObjs.add(obj);
				}
			}
			return listOfTrueObjs;
		} else {
			//System.out.println("There are no objects on the edge #" + edgeId);
			return new ArrayList<RoadObject>();
		}
	}

	public ArrayList<Integer> getTrueObjectsIdOnGivenEdge(int edgeId) {

		return convertObjListToIdList(getTrueObjectsOnGivenEdge(edgeId));
		// if (m_objectsOnEdges.get(edgeId) != null) {
		// ArrayList<Integer> listOfTrueObjIds = new ArrayList<Integer>();
		// for (RoadObject obj : m_objectsOnEdges.get(edgeId)) {
		// if (obj.getType() == true) {
		// listOfTrueObjIds.add(obj.getObjectId());
		// }
		// }
		// return listOfTrueObjIds;
		// } else {
		// System.out.println("There are no objects on the edge #" + edgeId);
		// return new ArrayList<Integer>();
		// }
	}

	// All False Objects
	public int getNumberOfFalseObjectsOnGivenEdge(int edgeId) {
		int numberOfFalseObjs = 0;
		if (m_objectsOnEdges.get(edgeId) != null) {
			for (RoadObject obj : m_objectsOnEdges.get(edgeId)) {
				if (obj.getType() == false) {
					numberOfFalseObjs++;
				}
			}
		} else {
			//System.out.println("There are no objects on the edge #" + edgeId);
		}
		return numberOfFalseObjs;
	}

	public ArrayList<RoadObject> getFalseObjectsOnGivenEdge(int edgeId) {
		if (m_objectsOnEdges.get(edgeId) != null) {
			ArrayList<RoadObject> listOfFalseObjs = new ArrayList<RoadObject>();
			for (RoadObject obj : m_objectsOnEdges.get(edgeId)) {
				if (obj.getType() == false) {
					listOfFalseObjs.add(obj);
				}
			}
			return listOfFalseObjs;
		} else {
			//System.out.println("There are no objects on the edge #" + edgeId);
			return new ArrayList<RoadObject>();
		}
	}

	public ArrayList<Integer> getFalseObjectsIdOnGivenEdge(int edgeId) {

		return convertObjListToIdList(getFalseObjectsOnGivenEdge(edgeId));
		// if (m_objectsOnEdges.get(edgeId) != null) {
		// ArrayList<Integer> listOfFalseObjs = new ArrayList<Integer>();
		// for (RoadObject obj : m_objectsOnEdges.get(edgeId)) {
		// if (obj.getType() == false) {
		// listOfFalseObjs.add(obj.getObjectId());
		// }
		// }
		// return listOfFalseObjs;
		// } else {
		// System.out.println("There are no objects on the edge #" + edgeId);
		// return new ArrayList<Integer>();
		// }
	}

	//////////////////////// Sorting methods
	public ArrayList<RoadObject> getAllObjectsOnEdgeSortedById(int edgeId) {
		ArrayList<RoadObject> objsList = new ArrayList<RoadObject>(getAllObjectsOnGivenEdge(edgeId));
		if (objsList != null) {
			Collections.sort(objsList, RoadObject.ObjIdComparator);
		}
		return objsList;
	}

	public ArrayList<RoadObject> getAllObjectsOnEdgeSortedByDist(int edgeId) {
		ArrayList<RoadObject> objsList = new ArrayList<RoadObject>(getAllObjectsOnGivenEdge(edgeId));
		if (objsList != null) {
			Collections.sort(objsList, RoadObject.DistanceComparator);
		}
		return objsList;
	}

	public ArrayList<RoadObject> getAllObjectsOnEdgeSortedByRating(int edgeId) {
		ArrayList<RoadObject> objsList = new ArrayList<RoadObject>(getAllObjectsOnGivenEdge(edgeId));
		if (objsList != null) {
			Collections.sort(objsList, RoadObject.RatingComparator);
		}
		return objsList;
	}

	public ArrayList<RoadObject> getTrueObjectsOnEdgeSortedById(int edgeId) {
		ArrayList<RoadObject> objsList = new ArrayList<RoadObject>(getTrueObjectsOnGivenEdge(edgeId));
		if (objsList != null) {
			Collections.sort(objsList, RoadObject.ObjIdComparator);
		}
		return objsList;
	}

	public ArrayList<RoadObject> getTrueObjectsOnEdgeSortedByDist(int edgeId) {
		ArrayList<RoadObject> objsList = new ArrayList<RoadObject>(getTrueObjectsOnGivenEdge(edgeId));
		if (objsList != null) {
			Collections.sort(objsList, RoadObject.DistanceComparator);
		}
		return objsList;
	}

	public ArrayList<RoadObject> getTrueObjectsOnEdgeSortedByRating(int edgeId) {
		ArrayList<RoadObject> objsList = new ArrayList<RoadObject>(getTrueObjectsOnGivenEdge(edgeId));
		if (objsList != null) {
			Collections.sort(objsList, RoadObject.RatingComparator);
		}
		return objsList;
	}

	public ArrayList<RoadObject> getFalseObjectsOnEdgeSortedById(int edgeId) {
		ArrayList<RoadObject> objsList = new ArrayList<RoadObject>(getFalseObjectsOnGivenEdge(edgeId));
		if (objsList != null) {
			Collections.sort(objsList, RoadObject.ObjIdComparator);
		}
		return objsList;
	}

	public ArrayList<RoadObject> getFalseObjectsOnEdgeSortedByDist(int edgeId) {
		ArrayList<RoadObject> objsList = new ArrayList<RoadObject>(getFalseObjectsOnGivenEdge(edgeId));
		if (objsList != null) {
			Collections.sort(objsList, RoadObject.DistanceComparator);
		}
		return objsList;
	}

	public ArrayList<RoadObject> getFalseObjectsOnEdgeSortedByRating(int edgeId) {
		ArrayList<RoadObject> objsList = new ArrayList<RoadObject>(getFalseObjectsOnGivenEdge(edgeId));
		if (objsList != null) {
			Collections.sort(objsList, RoadObject.RatingComparator);
		}
		return objsList;
	}

	/////////////// Distance related methods ////////////
	// Validate this method!
	// Distance between: Any given Node -> given Object
	public double getDistanceFromNodeToGivenObjOnSameEdge(int sourceNode, int objId) {
		int edgeId = getEdgeIdOfRoadObject(objId);
		RoadObject obj = getRoadObjectOnEdge(edgeId, objId);
		double distanceFromStartNode = obj.getDistanceFromStartNode();
		if (isStartNode(sourceNode, edgeId)) {
			return distanceFromStartNode;
		} else {
			return getEdgeDistance(getStartNodeIdOfEdge(edgeId), getEndNodeIdOfEdge(edgeId)) - distanceFromStartNode;
		}
	}
	////

	// Validate this method!
	// Get Any Nearest Object to given Node on same Edge
	public RoadObject getNearestObjectToGivenNodeOnEdge(int edgeId, int sourceNode) {
		RoadObject nearestObj = new RoadObject();
		if (isStartNode(sourceNode, edgeId)) {
			nearestObj = getNearestObjectToStartNodeOnEdge(edgeId);
		} else {
			nearestObj = getFarthestObjectFromStartNodeOnEdge(edgeId);
		}
		return nearestObj;
	}

	// Validate this method!
	// Distance between: Any given Node -> Nearest Object
	public double getDistanceToNearestObjectFromGivenNodeOnEdge(int edgeId, int sourceNode) {
		RoadObject nearestObj = getNearestObjectToGivenNodeOnEdge(edgeId, sourceNode);
		return getDistanceFromNodeToGivenObjOnSameEdge(sourceNode, nearestObj.getObjectId());
	}

	// Validate this method!
	// Distance between: given Object -> Any Nearest Object
	public double getDistanceToNearestObjectFromGivenObjOnEdge(int edgeId, int sourceObjId) {
		RoadObject nearestObj = getNearestObjectToGivenObjOnEdge(edgeId, sourceObjId);
		if (nearestObj == null)
			return -1;
		RoadObject sourceObject = getRoadObjectOnEdge(edgeId, sourceObjId);

		return Math.abs(sourceObject.getDistanceFromStartNode() - nearestObj.getDistanceFromStartNode());
	}

	// True Object
	// Get Nearest True Object to given Node on same Edge
	public RoadObject getNearestTrueObjectToGivenNodeOnEdge(int edgeId, int sourceNode) {
		RoadObject nearestObj = new RoadObject();
		if (isStartNode(sourceNode, edgeId)) {
			nearestObj = getNearestTrueObjectToStartNodeOnEdge(edgeId);
		} else {
			nearestObj = getFarthestTrueObjectFromStartNodeOnEdge(edgeId);
		}
		return nearestObj;
	}

	// Distance between: given Object -> Nearest True Object
	public double getDistanceToNearestTrueObjectOnEdge(int edgeId, int sourceObjId) {
		RoadObject nearestTrueObj = getNearestTrueObjectToGivenObjOnEdge(edgeId, sourceObjId);
		if (nearestTrueObj == null)
			return -1;
		RoadObject sourceObject = getRoadObjectOnEdge(edgeId, sourceObjId);
		return Math.abs(sourceObject.getDistanceFromStartNode() - nearestTrueObj.getDistanceFromStartNode());
	}

	// Validate this method!
	// Distance between: Any given Node -> Nearest True Object
	public double getDistanceToNearestTrueObjectFromGivenNodeOnEdge(int edgeId, int sourceNode) {
		RoadObject nearestObj = getNearestTrueObjectToGivenNodeOnEdge(edgeId, sourceNode);
		return getDistanceFromNodeToGivenObjOnSameEdge(sourceNode, nearestObj.getObjectId());
	}

	// False Object
	// Get Nearest False Object to given Node on same Edge
	public RoadObject getNearestFalseObjectToGivenNodeOnEdge(int edgeId, int sourceNode) {
		RoadObject nearestObj = new RoadObject();
		if (isStartNode(sourceNode, edgeId)) {
			nearestObj = getNearestFalseObjectToStartNodeOnEdge(edgeId);
		} else {
			nearestObj = getFarthestFalseObjectFromStartNodeOnEdge(edgeId);
		}
		return nearestObj;
	}

	// Distance between: given Object -> Nearest False Object
	public double getDistanceToNearestFalseObjectOnEdge(int edgeId, int sourceObjId) {
		RoadObject nearestFalseObj = getNearestTrueObjectToGivenObjOnEdge(edgeId, sourceObjId);
		if (nearestFalseObj == null)
			return -1;
		RoadObject sourceObject = getRoadObjectOnEdge(edgeId, sourceObjId);
		return Math.abs(sourceObject.getDistanceFromStartNode() - nearestFalseObj.getDistanceFromStartNode());
	}

	// Validate this method!
	// Distance between: Any given Node -> Nearest True Object
	public double getDistanceToNearestFalseObjectFromGivenNodeOnEdge(int edgeId, int sourceNode) {
		RoadObject nearestObj = getNearestFalseObjectToGivenNodeOnEdge(edgeId, sourceNode);
		return getDistanceFromNodeToGivenObjOnSameEdge(sourceNode, nearestObj.getObjectId());
	}

	// All Objects - Get Nearest Object To Start Node
	public RoadObject getNearestObjectToStartNodeOnEdge(int edgeId) {
		if (getNumberOfObjectsOnGivenEdge(edgeId) > 0) {
			return getAllObjectsOnEdgeSortedByDist(edgeId).get(0);
		}
		return null;
	}

	public int getNearestObjectIdToStartNodeOnEdge(int edgeId) {
		if (getNearestObjectToStartNodeOnEdge(edgeId) != null) {
			return getNearestObjectToStartNodeOnEdge(edgeId).getObjectId();
		}
		return -1;
	}

	public RoadObject getFarthestObjectFromStartNodeOnEdge(int edgeId) {
		int size = getNumberOfObjectsOnGivenEdge(edgeId);
		if (size > 0) {
			return getAllObjectsOnEdgeSortedByDist(edgeId).get(size - 1);
		}
		return null;
	}

	public int getFarthestObjectIdFromStartNodeOnEdge(int edgeId) {
		if (getFarthestObjectFromStartNodeOnEdge(edgeId) != null) {
			return getFarthestObjectFromStartNodeOnEdge(edgeId).getObjectId();
		}
		return -1;
	}

	// True Objects
	public RoadObject getNearestTrueObjectToStartNodeOnEdge(int edgeId) {
		if (getNumberOfTrueObjectsOnGivenEdge(edgeId) > 0) {
			return getTrueObjectsOnEdgeSortedByDist(edgeId).get(0);
		}
		return null;
	}

	public int getNearestTrueObjectIdToStartNodeOnEdge(int edgeId) {
		if (getNearestTrueObjectToStartNodeOnEdge(edgeId) != null) {
			return getNearestTrueObjectToStartNodeOnEdge(edgeId).getObjectId();
		}
		return -1;
	}

	public RoadObject getFarthestTrueObjectFromStartNodeOnEdge(int edgeId) {
		int size = getNumberOfTrueObjectsOnGivenEdge(edgeId);
		if (size > 0) {
			return getTrueObjectsOnEdgeSortedByDist(edgeId).get(size - 1);
		}
		return null;
	}

	public int getFarthestTrueObjectIdFromStartNodeOnEdge(int edgeId) {
		if (getFarthestTrueObjectFromStartNodeOnEdge(edgeId) != null) {
			return getFarthestTrueObjectFromStartNodeOnEdge(edgeId).getObjectId();
		}
		return -1;
	}

	// False Objects
	public RoadObject getNearestFalseObjectToStartNodeOnEdge(int edgeId) {
		if (getNumberOfFalseObjectsOnGivenEdge(edgeId) > 0) {
			return getFalseObjectsOnEdgeSortedByDist(edgeId).get(0);
		}
		return null;
	}

	public int getNearestFalseObjectIdToStartNodeOnEdge(int edgeId) {
		if (getNearestFalseObjectToStartNodeOnEdge(edgeId) != null) {
			return getNearestFalseObjectToStartNodeOnEdge(edgeId).getObjectId();
		}
		return -1;
	}

	public RoadObject getFarthestFalseObjectFromStartNodeOnEdge(int edgeId) {
		int size = getNumberOfFalseObjectsOnGivenEdge(edgeId);
		if (size > 0) {
			return getFalseObjectsOnEdgeSortedByDist(edgeId).get(size - 1);
		}
		return null;
	}

	public int getFarthestFalseObjectIdFromStartNodeOnEdge(int edgeId) {
		if (getFarthestFalseObjectFromStartNodeOnEdge(edgeId) != null) {
			return getFarthestFalseObjectFromStartNodeOnEdge(edgeId).getObjectId();
		}
		return -1;
	}

	////// Get Nearest Obj to a given Object on same Edge
	public RoadObject getNearestObjectToGivenObjOnEdge(int edgeId, int sourceObjId) {

		double minDistance = Double.MAX_VALUE;
		RoadObject sourceObj = getRoadObjectOnEdge(edgeId, sourceObjId);
		RoadObject nearestObj = null;// new RoadObject();

		if (sourceObj != null && m_objectsOnEdges.get(edgeId) != null) {
			for (RoadObject obj : m_objectsOnEdges.get(edgeId)) {
				if ((Math.abs(obj.getDistanceFromStartNode() - sourceObj.getDistanceFromStartNode()) < minDistance)
						&& (Math.abs(obj.getDistanceFromStartNode() - sourceObj.getDistanceFromStartNode()) != 0)) {
					minDistance = Math.abs(obj.getDistanceFromStartNode() - sourceObj.getDistanceFromStartNode());
					nearestObj = obj;
				}
			}
		}
		return nearestObj;
	}

	public int getNearestObjectIdToGivenObjOnEdge(int edgeId, int sourceObjId) {
		if (getNearestObjectToGivenObjOnEdge(edgeId, sourceObjId) != null) {
			return getNearestObjectToGivenObjOnEdge(edgeId, sourceObjId).getObjectId();
		}
		return -1;
	}

	// True Object
	public RoadObject getNearestTrueObjectToGivenObjOnEdge(int edgeId, int sourceObjId) {
		double minDistance = Double.MAX_VALUE;
		RoadObject sourceObj = getRoadObjectOnEdge(edgeId, sourceObjId);
		RoadObject nearestObj = null;// new RoadObject();
		if (getTrueObjectsOnGivenEdge(edgeId).size() > 0 ) {
			for (RoadObject obj : getTrueObjectsOnGivenEdge(edgeId)) {
				if ((Math.abs(obj.getDistanceFromStartNode() - sourceObj.getDistanceFromStartNode()) < minDistance)
						&& (Math.abs(obj.getDistanceFromStartNode() - sourceObj.getDistanceFromStartNode()) != 0)) {
					minDistance = Math.abs(obj.getDistanceFromStartNode() - sourceObj.getDistanceFromStartNode());
					nearestObj = obj;
				}
			}
		}
		return nearestObj;

	}

	public int getNearestTrueObjectIdToGivenObjOnEdge(int edgeId, int sourceObjId) {
		if (getNearestTrueObjectToGivenObjOnEdge(edgeId, sourceObjId) != null) {
			return getNearestTrueObjectToGivenObjOnEdge(edgeId, sourceObjId).getObjectId();
		}
		return -1;

	}

	// False Object
	public RoadObject getNearestFalseObjectToGivenObjOnEdge(int edgeId, int sourceObjId) {
		double minDistance = Double.MAX_VALUE;
		RoadObject sourceObj = getRoadObjectOnEdge(edgeId, sourceObjId);
		RoadObject nearestObj = null;// new RoadObject();
		if (getFalseObjectsOnGivenEdge(edgeId).size() > 0 ) {
			for (RoadObject obj : getFalseObjectsOnGivenEdge(edgeId)) {
				if ((Math.abs(obj.getDistanceFromStartNode() - sourceObj.getDistanceFromStartNode()) < minDistance)
						&& (Math.abs(obj.getDistanceFromStartNode() - sourceObj.getDistanceFromStartNode()) != 0)) {
					minDistance = Math.abs(obj.getDistanceFromStartNode() - sourceObj.getDistanceFromStartNode());
					nearestObj = obj;
				}
			}
		}
		return nearestObj;

	}

	public int getNearestFalseObjectIdToGivenObjOnEdge(int edgeId, int sourceObjId) {
		if (getNearestFalseObjectToGivenObjOnEdge(edgeId, sourceObjId) != null) {
			return getNearestFalseObjectToGivenObjOnEdge(edgeId, sourceObjId).getObjectId();
		}
		return -1;
	}

	//////////////// Utilities
	public ArrayList<Integer> convertObjListToIdList(ArrayList<RoadObject> roadObjList) {
		ArrayList<Integer> roadObjIdList = new ArrayList<Integer>();
		;
		if (roadObjList.size() > 0) {
			for (RoadObject obj : roadObjList) {
				roadObjIdList.add(obj.getObjectId());
			}
		}
		return roadObjIdList;
	}

	public ArrayList<RoadObject> convertIdListToObjList(ArrayList<Integer> roadObjIdList) {
		ArrayList<RoadObject> roadObjList = new ArrayList<RoadObject>();
		if (roadObjIdList.size() > 0) {
			for (Integer objId : roadObjIdList) {
				roadObjList.add(getRoadObject(objId));
			}
		}
		return roadObjList;
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
