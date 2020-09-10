package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import framework.Edge;
import framework.Graph;
import framework.Node;
import framework.RoadObject;

public class VivetAlgorithm {

	// Map<Node, Map<ObjectId, Distance> >   - not used right now
	private Map<Node, Map<Integer, Double>> nodeObjDistMap = new HashMap<Node, Map<Integer, Double>>();
	
	// Map <NodeId, NodeId> OR  Map <PotentialQueryObj, PotentialDataObj> 
	private Map<Integer, Integer> precomputationTable = new HashMap<Integer, Integer>();
	
	// m_nearestNeighborSets: Map<QueryObjectId, DataObjectId>.
	private Map<Integer, Integer> m_nearestNeighborSets = new HashMap<Integer, Integer>();
	
	// Map <NewNodeId, QueryObjId>
	private Map<Integer, Integer> queryObjNodeIdMap = new HashMap<Integer, Integer>();
	
	// Map <NewNodeId, DataObjId>
	private Map<Integer, Integer> dataObjNodeIdMap = new HashMap<Integer, Integer>();

	private DijkstraAlgorithmUndirected dijkstraAlgorithm;
	
	private int virtualNodeId;
	
	public void precompute(Graph graph) {

		Graph virtualGraph = createVirtualGraph(graph);

		//printNewGraphInfo(virtualGraph);
		
		dijkstraAlgorithm = new DijkstraAlgorithmUndirected(virtualGraph);
		
		dijkstraAlgorithm.execute(virtualGraph.getNode(virtualNodeId));				
		
		//ArrayList<LinkedList<Node>> listofPath = new ArrayList<LinkedList<Node>>();
		LinkedList<Node> listofNodes = new LinkedList<Node>();
		
		ArrayList<Integer> nodeList = new ArrayList<Integer>();
		for (int i = 0; i < virtualGraph.getNodesWithInfo().size(); i++) {
			if (virtualGraph.getNodesWithInfo().get(i).getNodeId() != virtualNodeId) {
				nodeList.add(virtualGraph.getNodesWithInfo().get(i).getNodeId());
			}
		}
//		System.out.println("Node IDs without Virtual Node: " + nodeList);
//		System.out.println("Total number of those Nodes: " + nodeList.size());	
//		System.out.println("Virtual Node ID: " + virtualNodeId);
//		System.err.println("");
		
		
		for (int j = 0; j < nodeList.size(); j++) {
			int destinationId = nodeList.get(j);
			// System.out.println("dest id when j at "+j+" is : "+destinationId);

			listofNodes = dijkstraAlgorithm.getPath(virtualGraph.getNode(destinationId));
			//listofPath.add(listofNodes);
			if (listofNodes != null) {
				if (listofNodes.size() > 2) {
					// precomputationTable <PotentialQueryObj, PotentialDataObj>
					populatePrecimputationTable(listofNodes.get(listofNodes.size()-1).getNodeId(), listofNodes.get(1).getNodeId());		
				}					
			}			
		}
		
//		System.out.println("List of shortest paths to every node from virtual node: ");
//		for (int i = 0; i < listofPath.size(); i++) {
//
//			if (listofPath.get(i) != null) {
//				for (Node vertex : listofPath.get(i)) {
//					System.out.print(vertex.getNodeId()+ " ");
//					
//				}
//			}
//			System.out.println();
//		}
		
		
	}

	

	public void compute(Graph graph) {
		long startTimePrecompute = System.nanoTime();
		precompute(graph);
		long precomputeTime = System.nanoTime() - startTimePrecompute;
		double precomputeTimeD = (double) precomputeTime / 1000000000.0;
		System.out.println("Time of precomputation for Vivet:  " + precomputeTimeD);
		//printPrecomputationTable();
		System.err.println("Precomputation is done! Size of table is " + precomputationTable.size());
		lookup();
		
		//printNearestNeighborSets();
	}

	public Map<Integer, Integer> lookup() {		
		
		for (Integer key : precomputationTable.keySet()) { 
			//System.out.println("NodeID (potential Query Obj): " + key + " NodeID (potential Data Obj): " + precomputationTable.get(key));
			int queryObjId, dataObjId;
			if (queryObjNodeIdMap.containsKey(key)) {
				queryObjId = queryObjNodeIdMap.get(key);
				dataObjId = dataObjNodeIdMap.get(precomputationTable.get(key));
				m_nearestNeighborSets.put(queryObjId, dataObjId);
			}
		}		
		return m_nearestNeighborSets;
	}
	
	private Graph createVirtualGraph(Graph inputGraph) {

		Graph virtualGraph = new Graph(inputGraph.getDatasetName() + " virtual");			
				
		
		int newEdgeId = inputGraph.getNumberOfEdges(); // we assume that in Edge dataset EdgeID starts from 0
		int newNodeId = inputGraph.getNumberOfNodes(); // we assume that in Node dataset NodeID starts from 0
		virtualNodeId = newNodeId;
		int prevNodeId = newNodeId + 1;
		//System.out.println("Edge's Information: ");
		for (Edge e : inputGraph.getEdgesWithInfo()) {
			//System.err.println(e.toString());
			
			// check if there any object on edge
			ArrayList<RoadObject> allObjectsOnEdgeSortedByDistAsc = inputGraph.getAllObjectsOnEdgeSortedByDistAsc(e.getEdgeId());
			if (allObjectsOnEdgeSortedByDistAsc.size() == 0) { 
				
				//addEdge() will update m_edgesWithInfo & m_adjancencyMap
				// keeping same EdgeId
				virtualGraph.addEdge(e.getEdgeId(), e.getStartNodeId(), e.getEndNodeId(), e.getLength());
				
			}
			else { 
				for (int i = 0; i < allObjectsOnEdgeSortedByDistAsc.size(); i++) { 					
					
					if (i == 0 && i == allObjectsOnEdgeSortedByDistAsc.size() -1 ) { 
						// 1st & last object from start node
						virtualGraph.addEdge(newEdgeId, e.getStartNodeId(), prevNodeId, allObjectsOnEdgeSortedByDistAsc.get(i).getDistanceFromStartNode());
						if (allObjectsOnEdgeSortedByDistAsc.get(i).getType()) { 
							// tracking QueryObjId with its new Node ID
							queryObjNodeIdMap.put(prevNodeId, allObjectsOnEdgeSortedByDistAsc.get(i).getObjectId());
						}
						else {   
							// connecting virtual node with data object
							newEdgeId++;
							virtualGraph.addEdge(newEdgeId, virtualNodeId, prevNodeId, 0.0);
							dataObjNodeIdMap.put(prevNodeId, allObjectsOnEdgeSortedByDistAsc.get(i).getObjectId());
						}
						newEdgeId++;
						virtualGraph.addEdge(newEdgeId, prevNodeId, e.getEndNodeId(), e.getLength() - allObjectsOnEdgeSortedByDistAsc.get(i).getDistanceFromStartNode());
						
					}
					
					else if (i == 0 ) { 
						// 1st object from start node
						virtualGraph.addEdge(newEdgeId, e.getStartNodeId(), prevNodeId, allObjectsOnEdgeSortedByDistAsc.get(i).getDistanceFromStartNode());
						if (allObjectsOnEdgeSortedByDistAsc.get(i).getType()) { 
							queryObjNodeIdMap.put(prevNodeId, allObjectsOnEdgeSortedByDistAsc.get(i).getObjectId());
						}
						else { 
							// connecting virtual node with data object
							newEdgeId++;
							virtualGraph.addEdge(newEdgeId, virtualNodeId, prevNodeId, 0.0);
							dataObjNodeIdMap.put(prevNodeId, allObjectsOnEdgeSortedByDistAsc.get(i).getObjectId());
						}
					}
					else if (i == allObjectsOnEdgeSortedByDistAsc.size() -1) { 						
						// last object from start node
						double newDistance = allObjectsOnEdgeSortedByDistAsc.get(i).getDistanceFromStartNode() - allObjectsOnEdgeSortedByDistAsc.get(i-1).getDistanceFromStartNode();
						virtualGraph.addEdge(newEdgeId, prevNodeId, (prevNodeId +1), newDistance);
						if (allObjectsOnEdgeSortedByDistAsc.get(i).getType()) { 
							queryObjNodeIdMap.put(prevNodeId+1, allObjectsOnEdgeSortedByDistAsc.get(i).getObjectId());
						}
						else { 
							// connecting virtual node with data object
							newEdgeId++;
							virtualGraph.addEdge(newEdgeId, virtualNodeId, (prevNodeId+1), 0.0);
							dataObjNodeIdMap.put(prevNodeId+1, allObjectsOnEdgeSortedByDistAsc.get(i).getObjectId());
						}
						newEdgeId++;
						prevNodeId++;
						virtualGraph.addEdge(newEdgeId, prevNodeId, e.getEndNodeId(), e.getLength() - allObjectsOnEdgeSortedByDistAsc.get(i).getDistanceFromStartNode());
												
					}
					else {
						// object in the middle
						double newDistance = allObjectsOnEdgeSortedByDistAsc.get(i).getDistanceFromStartNode() - allObjectsOnEdgeSortedByDistAsc.get(i-1).getDistanceFromStartNode();
						virtualGraph.addEdge(newEdgeId, prevNodeId, (prevNodeId +1), newDistance);
						if (allObjectsOnEdgeSortedByDistAsc.get(i).getType()) { 
							queryObjNodeIdMap.put(prevNodeId+1, allObjectsOnEdgeSortedByDistAsc.get(i).getObjectId());
						}
						else { 
							// connecting virtual node with data object
							newEdgeId++;
							virtualGraph.addEdge(newEdgeId, virtualNodeId, (prevNodeId+1), 0.0);
							dataObjNodeIdMap.put(prevNodeId+1, allObjectsOnEdgeSortedByDistAsc.get(i).getObjectId());
						}
						prevNodeId++;
					}
					newEdgeId++;
				}				
				prevNodeId++;
			}			
		}	
		
//		System.out.println("New Node ID & Query Obj: " + queryObjNodeIdMap);
//		System.out.println("New Node ID & Data Obj: " + dataObjNodeIdMap);
		
		return virtualGraph;

	}	
	
	private void  printNewGraphInfo(Graph graph) { 
		System.out.println("Virtual Graph:");
		graph.printGraph();
		System.out.println();
		graph.printEdgesInfo();
		System.out.println();
		graph.printNodesInfo();
		System.out.println();
		graph.printObjectsOnEdges();
	}
	
	private void populatePrecimputationTable(int potentialQueryObj, int potentialDataObj) { 
		
		precomputationTable.put(potentialQueryObj, potentialDataObj);
	}
	
	private void printPrecomputationTable() { 
		System.out.println("Precomputation Table:");
		for (Integer key : precomputationTable.keySet()) { 
			System.out.println("NodeID (potential Query Obj): " + key + " NodeID (potential Data Obj): " + precomputationTable.get(key));
		}
	}
	
	public void printNearestNeighborSets() {
		if (!m_nearestNeighborSets.isEmpty()) {
			System.out.println();
			System.out.println("Result of VIVET computation: ");
			for (Integer queryObj : m_nearestNeighborSets.keySet()) {
//				System.out.println("Query Object ID: " + queryObj + " [edge #" + m_graph.getEdgeIdOfRoadObject(queryObj)
//						+ "] - Data Object ID: " + m_nearestNeighborSets.get(queryObj) + " [edge #"
//						+ m_graph.getEdgeIdOfRoadObject(m_nearestNeighborSets.get(queryObj)) + "]");
				 System.out.println("Query Object ID: " + queryObj + " - Data Object ID: " +
						 m_nearestNeighborSets.get(queryObj));

			}
		}
	}
}
