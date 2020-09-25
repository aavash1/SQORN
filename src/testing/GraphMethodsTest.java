package testing;

import framework.Graph;
import framework.RoadObject;
import road_network.ManualRN4;

public class GraphMethodsTest {

	public static void main(String[] args) {
		
		
		Graph gr = ManualRN4.getGraph();

		gr.printObjectsOnEdges();

		System.out.println();
		
		System.out.println("Distance related methods");
		System.out.println(gr.getDistanceFromNodeToGivenObjOnSameEdge(10, 31));
		System.out.println(gr.getDistanceFromNodeToGivenObjOnSameEdge(3, 10, 31));
		System.out.println(gr.getDistanceFromNodeToGivenObjOnSameEdge(6, 31));
		System.out.println(gr.getDistanceFromNodeToGivenObjOnSameEdge(3, 6, 31));
		
		System.out.println("");
//		System.out.println(gr.getDistanceToNearestObjectFromGivenNodeOnEdge(28, 15));		
//		System.out.println(gr.getDistanceToNearestObjectFromGivenNodeOnEdge(28, 25));
//		System.out.println(gr.getDistanceToNearestTrueObjectFromGivenNodeOnEdge(28, 15));
//		System.out.println(gr.getDistanceToNearestTrueObjectFromGivenNodeOnEdge(28, 25));
//		System.out.println(gr.getDistanceToNearestFalseObjectFromGivenNodeOnEdge(28, 15));
//		System.out.println(gr.getDistanceToNearestFalseObjectFromGivenNodeOnEdge(28, 25));
//		
								

	}

}
