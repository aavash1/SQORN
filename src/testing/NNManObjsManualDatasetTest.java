package testing;

import algorithm.NearestNeighbor;
import framework.Graph;
import framework.RoadObject;

public class NNManObjsManualDatasetTest {

	public static void main(String[] args) {
		Graph gr = new Graph();

		gr.addEdge(1, 2, 18.3698);
		gr.addEdge(1, 3, 20.5499);
		// gr.addEdge(2, 3, 19.0658);
		gr.addEdge(3, 4, 15.887);
		gr.addEdge(3, 5, 17.01466);
		gr.addEdge(5, 6, 23.963);
		gr.addEdge(4, 6, 22.17489);
		gr.addEdge(6, 7, 21.12365);
		gr.addEdge(7, 8, 24.4421);
		gr.addEdge(4, 10, 16.432);

		// objects for Scenario #1
		RoadObject rObj1 = new RoadObject();
		RoadObject rObj2 = new RoadObject();
		RoadObject rObj3 = new RoadObject();
		RoadObject rObj4 = new RoadObject();
		RoadObject rObj5 = new RoadObject();

		rObj1.setObjId(31);
		rObj1.setDistanceFromStartNode(5.0);
		rObj1.setType(true);

		rObj2.setObjId(51);
		rObj2.setDistanceFromStartNode(3.0);
		rObj2.setType(false);

		rObj3.setObjId(11);
		rObj3.setDistanceFromStartNode(8.0);
		rObj3.setType(false);

		rObj4.setObjId(71);
		rObj4.setDistanceFromStartNode(1.0);
		rObj4.setType(false);

		rObj5.setObjId(81);
		rObj5.setDistanceFromStartNode(20.0);
		rObj5.setType(false);

		gr.addObjectOnEdge(3, rObj1);
		gr.addObjectOnEdge(5, rObj2);
		gr.addObjectOnEdge(1, rObj3);
		gr.addObjectOnEdge(7, rObj4);
		gr.addObjectOnEdge(8, rObj5);

		// extra objects for Scenario #2
		RoadObject rObj6 = new RoadObject();
		RoadObject rObj7 = new RoadObject();
		RoadObject rObj8 = new RoadObject();
		RoadObject rObj9 = new RoadObject();

		rObj6.setObjId(21);
		rObj6.setDistanceFromStartNode(1.0);
		rObj6.setType(false);
		rObj7.setObjId(41);
		rObj7.setDistanceFromStartNode(10.0);
		rObj7.setType(false);
		rObj8.setObjId(61);
		rObj8.setDistanceFromStartNode(6.0);
		rObj8.setType(false);
		rObj9.setObjId(91);
		rObj9.setDistanceFromStartNode(8.0);
		rObj9.setType(false);

		gr.addObjectOnEdge(2, rObj6);
		gr.addObjectOnEdge(4, rObj7);
		gr.addObjectOnEdge(6, rObj8);
		gr.addObjectOnEdge(9, rObj9);

		// extra objects for Scenario #3
		gr.addEdge(4, 9, 7.0);
		gr.addEdge(9, 11, 15.0);

		RoadObject rObj10 = new RoadObject();
		rObj10.setObjId(111);
		rObj10.setDistanceFromStartNode(1.0);
		rObj10.setType(false);
		// gr.addObjectOnEdge(11, rObj10);

		gr.printGraph();
		System.out.println();

		gr.printEdgesInfo();
		System.out.println();

		gr.printObjectsOnEdges();
		System.out.println();

		System.out.println("Nearest Object to Obj id 31:");
//		int nearestFalseObj = gr.getNearestFalseObjectIdToGivenObjOnMap(31);
//		System.out.println(nearestFalseObj);

		NearestNeighbor nn = new NearestNeighbor();
		RoadObject nearestObj = nn.getNearestObjectToGivenObjOnMap(gr, 31);
		System.out.println(nearestObj);

	}

}
