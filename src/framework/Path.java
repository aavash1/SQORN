package framework;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Path {

	private List<Integer> nodes = new LinkedList<Integer> ();
	private Stack<Integer> stack = new Stack<Integer>(); //remove later
	
	public Path () { 
			
	}
	
	public Path (List<Integer> listOfNodes) { 
		this.nodes = listOfNodes;		
	}
	
	public boolean addNode (int nodeId) {
		
		nodes.add(nodeId);		
		return true; 		
	}
	
	public List<Integer> getNodes () { 
		return this.nodes;
	}
	
	public void setNewPath(List<Integer> path) { 
		this.nodes = path;
	}
	
	public void reset() { 
		nodes.clear();
	}
	
}
