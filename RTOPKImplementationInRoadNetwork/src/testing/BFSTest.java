package testing;

import algorithm.BFS;
import algorithm.GraphTraversal;
import framework.Graph2;

public class BFSTest {

	public static void main(String[] args) {
		
		Graph2 gr = new Graph2();
		BFS bfs = new BFS();

		gr.addEdge(1, 2, 18.3698);
		gr.addEdge(1, 3, 20.5499);
		gr.addEdge(2, 3, 19.0658);
		gr.addEdge(3, 4, 15.887);
		gr.addEdge(4, 10, 15.887);
		gr.addEdge(3, 5, 17.01466);
		gr.addEdge(5, 6, 23.963);
		gr.addEdge(4, 6, 22.17489);
		gr.addEdge(6, 7, 21.12365);
		gr.addEdge(7, 8, 24.4421);
		
		gr.printGraph();
		
		bfs.traverse(gr, 6);
		

	}

}
