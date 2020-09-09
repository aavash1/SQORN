package algorithm;

import java.util.ArrayList;
import java.util.Map;

import framework.Edge;
import framework.Graph;
import framework.Node;
import framework.RoadObject;

public class VivetAlgorithm {

	// Map<Node, Map<ObjectId, Distance> >
	private Map<Node, Map<Integer, Double>> nodeObjDistMap;

	public void precompute(Graph graph) {

		Graph virtualGraph = createVirtualGraph(graph);

		
		
		
	}

	public void lookup() {

	}

	public void compute(Graph graph) {

		precompute(graph);

		lookup();
	}

	private Graph createVirtualGraph(Graph inputGraph) {

		Graph virtualGraph = new Graph(inputGraph.getDatasetName() + " virtual");

		// step 1: create a virtual node

		Node virtualNode = new Node();
		int virtualNodeId;// = virtualGraph.getNodesWithInfo().get(virtualGraph.getNodesWithInfo().size() - 1).getNodeId() + 1;
		//virtualNode.setNodeId(virtualNodeId);

		// step _: convert every data object into node (make sure they are connected to
		// related nodes)
		// keep the table (data/query object - new node)
		
		
		// step 2: connect virtual node to every data object
//		for (Integer edgeId : virtualGraph.getObjectsOnEdges().keySet()) {
//			ArrayList<Integer> dataObjectsOnEdge = virtualGraph.getFalseObjectsIdOnGivenEdge(edgeId);
//			
//			for (Integer dataObjectId : dataObjectsOnEdge) {
//				virtualNodeId++;
//				virtualGraph.addEdge(virtualNodeId, virtualNodeId, 0.0);				
//			}
//		}
		
		System.out.println("Adjacency list: ");

		for (Integer key : inputGraph.getAdjancencyMap().keySet()) {

			System.err.println("head " + key + ":\t" + inputGraph.getAdjancencyMap().get(key));
			
			
		}
		

		System.out.println("Objects on Edges:  ");
		for (Integer key : inputGraph.getObjectsOnEdges().keySet()) {
			System.err.println("Edge " + key + " " + inputGraph.getObjectsOnEdges().get(key));
			
			
			
		}
		
		System.out.println("Edge's Information: ");

		for (Edge e : inputGraph.getEdgesWithInfo()) {
			System.err.println(e.toString());
			
			// check if there any object on edge
			ArrayList<RoadObject> allObjectsOnEdgeSortedByDistAsc = inputGraph.getAllObjectsOnEdgeSortedByDistAsc(e.getEdgeId());
			if (allObjectsOnEdgeSortedByDistAsc.size() == 0) { 
				
			}
			else { 
				for (int i = 0; i < allObjectsOnEdgeSortedByDistAsc.size(); i++) { 
					
					
					
				}
				
			}
			
		}
		
		
		return virtualGraph;

	}
	
	private ArrayList<Edge> createVirtualEdges(Graph graph, int edgeId) {
		
		ArrayList<Edge> virtualEdges = new ArrayList<Edge>();
		
		
		
		return virtualEdges; 		
	}

}
