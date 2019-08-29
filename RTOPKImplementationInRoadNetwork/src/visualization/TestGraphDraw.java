package visualization;

import framework.Graph;
import framework.Graph2;

public class TestGraphDraw {
	
	public static void main(String[] args) {
		
		 Graph2 roadNetwork1 = new Graph2();
		 roadNetwork1.addEdge(0, 1, 2.24);
		 roadNetwork1.addEdge(0, 4, 5.0);
		 roadNetwork1.addEdge(1, 2, 3.0);
		 roadNetwork1.addEdge(1, 3, 5.0);
		 roadNetwork1.addEdge(1, 4, 5.83);
		 roadNetwork1.addEdge(2, 3, 3.16);
		 roadNetwork1.addEdge(3, 4, 2.24);
		 roadNetwork1.printGraph();
		 System.out.println();
		 
		 roadNetwork1.getNode(0).setLatitude(4.0*20);
		 roadNetwork1.getNode(0).setLongitude(5.0*20);
		 roadNetwork1.getNode(1).setLatitude(6.0*20);
		 roadNetwork1.getNode(1).setLongitude(6.0*20);;
		 roadNetwork1.getNode(2).setLatitude(6.0*20);
		 roadNetwork1.getNode(2).setLongitude(9.0*20);;
		 roadNetwork1.getNode(3).setLatitude(3.0*20);
		 roadNetwork1.getNode(3).setLongitude(10.0*20);;
		 roadNetwork1.getNode(4).setLatitude(1.0*20);
		 roadNetwork1.getNode(4).setLongitude(9.0*20);;
		 roadNetwork1.printNodesInfo();
//		 roadNetwork1.addPoi(31, 0, 1, 1.3);
//		 roadNetwork1.addPoi(32, 0, 1, 1.9);
//		 roadNetwork1.addPoi(33, 0, 4, 2.2);
		
		 GraphDraw graphDraw= new GraphDraw("Test Window");
		 graphDraw.setGraph(roadNetwork1);		 
		 
		 graphDraw.setSize(400, 300);
		 graphDraw.setVisible(true);
		 
	}
}
