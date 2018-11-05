package Framework;

//import java.awt.RenderingHints.Key;
import java.util.*;

public class Graph {
	//private int modificationCount; // AZIZ: I didn't get why you need this variable
	private int m_numEdges;
	private int m_numOfNodes;

	private final Map<Integer, Map<Integer, Double>> m_adjancencyMap = new HashMap();
	private final Map<Map<Integer, Integer>, Map<Integer, Double>> m_edgePOIMap = new HashMap();

	public Map<Integer, Map<Integer, Double>> getAdjancencyMap() {
		return m_adjancencyMap;
	}
	public Map<Map<Integer, Integer>, Map<Integer, Double>> getEdgePOIMap() {
		return m_edgePOIMap;
	}

	private static ArrayList<Node> m_nodes = new ArrayList<Node>();	
	private static ArrayList<Poi> m_pointOfInterests = new ArrayList<Poi>();

	public void loadDataset(String nodeDatasetFile, String edgeDatasetFile, String poiDatasetFile) {

		// use UtilsManagement to get Node, Edge, POI data
		// then use Graph's methods to store in memory (m_adjancencyMap, m_edgePOIMap)
		ArrayList<Edge> edges = new ArrayList<Edge>();
		// read edge dataset and add edges to Graph
		// read node dataset and add edges to Graph
		// read poi dataset and add to Graph

		UtilsManagment utilm = new UtilsManagment();
		m_nodes = utilm.readVertexFiles(nodeDatasetFile);
		edges=utilm.readEdgeFile(edgeDatasetFile);
		m_pointOfInterests=utilm.readPOIFile2(poiDatasetFile);
		
		for(int i=0;i<edges.size();i++) {
			addEdge(edges.get(i).getStartNodeId(), edges.get(i).getEndNodeId(), edges.get(i).getLength());
		}
			
		
//		for(int k=0;k<pointOfInterests.size();k++) {
//			addPOI(pointOfInterests.get(k).getM_intPOIID(), pointOfInterests.get(k).get, endNode, distFromStartNode)
//		}
		

	}
	public void loadDataset(String edgeDatasetFile) {

		// use UtilsManagement to get Node, Edge, POI data
		// then use Graph's methods to store in memory (m_adjancencyMap, m_edgePOIMap)
		ArrayList<Edge> edges = new ArrayList<Edge>();
		// read edge dataset and add edges to Graph
		// read node dataset and add edges to Graph
		// read poi dataset and add to Graph

		UtilsManagment utilm = new UtilsManagment();
		//vertices = utilm.readVertexFiles(nodeDatasetFile);
		edges=utilm.readEdgeFile(edgeDatasetFile);
		//pointOfInterests=utilm.readPOIFile2(poiDatasetFile);
		
		for(int i=0;i<edges.size();i++) {
			addEdge(edges.get(i).getStartNodeId(), edges.get(i).getEndNodeId(), edges.get(i).getLength());
		}
		
		

	}
	public int getNumberOfEdges() {
		return m_numEdges;
	}

	public int getNumberOfNodes() {
		return m_numOfNodes;
	}

	
	public boolean addNode(int int_nodeID) {
		if (m_adjancencyMap.containsKey(int_nodeID)) {
			return false;
		}
		m_adjancencyMap.put(int_nodeID, new LinkedHashMap<>());
		//modificationCount++;
		m_numOfNodes++;
		return true;
	}

	public boolean addEdge(int int_startNode, int int_endNode, double doub_distance) {
		if (int_startNode == int_endNode) {
			return false;
		}
		addNode(int_startNode);
		addNode(int_endNode);

		if (!m_adjancencyMap.get(int_startNode).containsKey(int_endNode)) {
			m_adjancencyMap.get(int_startNode).put(int_endNode, doub_distance);
			m_adjancencyMap.get(int_endNode).put(int_startNode, doub_distance);
			//modificationCount++;
			m_numEdges++;

		} else {
			double prev_doubDistance = m_adjancencyMap.get(int_startNode).get(int_endNode);
			m_adjancencyMap.get(int_startNode).put(int_endNode, doub_distance);
			m_adjancencyMap.get(int_endNode).put(int_startNode, doub_distance);

			if (prev_doubDistance != doub_distance) {
				//modificationCount++;
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
	public boolean addPOI(int idPOI, int startNode, int endNode, double distFromStartNode) {

		if (!hasEdge(startNode, endNode)) {
			return false;
		}

		Map<Integer, Integer> edge = new HashMap<>();
		edge.put(startNode, endNode);

		if (m_edgePOIMap.containsKey(edge)) {
			m_edgePOIMap.get(edge).put(idPOI, distFromStartNode);
		} else {
			Map<Integer, Double> pois = new HashMap<>();
			pois.put(idPOI, distFromStartNode);

			m_edgePOIMap.put(edge, pois);
		}

		return true;
	}
	
	public boolean addPOI(int idPOI, int startNode, int endNode, double distFromStartNode, int categoryId) {

		if (!hasEdge(startNode, endNode)) {
			return false;
		}

		Map<Integer, Integer> edge = new HashMap<>();
		edge.put(startNode, endNode);

		if (m_edgePOIMap.containsKey(edge)) {
			m_edgePOIMap.get(edge).put(idPOI, distFromStartNode);
		} else {
			Map<Integer, Double> pois = new HashMap<>();
			pois.put(idPOI, distFromStartNode);

			m_edgePOIMap.put(edge, pois);
		}

		return true;
	}
	
	public boolean addPOI(Poi newPoi, Edge edge) {
		
		addPOI(newPoi.getPoiId(), edge.getStartNodeId(), edge.getEndNodeId(), edge.getLength());
		
		return true;		
		
	}

	public void printPOIs() {
		System.out.println("Point of Interest List: ");

		for (Map<Integer, Integer> key : m_edgePOIMap.keySet()) {

			System.out.println("Edge " + key + ":\t" + m_edgePOIMap.get(key));
		}
	}

	public ArrayList<Integer> getPOIs(int startNode, int endNode) {
		if (!hasEdge(startNode, endNode)) {
			return null;
		}
		Map<Integer, Integer> edge = new HashMap<>();
		edge.put(startNode, endNode);
		ArrayList<Integer> int_POI = new ArrayList<Integer>();
		for (Integer key : m_edgePOIMap.get(edge).keySet()) {
			int_POI.add(key);
		}
		return int_POI;
	}

	public Map<Integer, Double> getPOIsWithDistance(int startNode, int endNode) {
		if (!hasEdge(startNode, endNode)) {
			return null;
		}

		Map<Integer, Integer> edges = new HashMap<>();
		edges.put(startNode, endNode);

		return m_edgePOIMap.get(edges);
	}
	
	public void getPoiEdge(int poiId) {
		
		
		
		//return startNode, endNode (edge)
	}
	public void getPoiDistanceFromSource(int poiId) {
		
		
		
		//return distance from start node
	}

	
}
