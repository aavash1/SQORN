package testing;

import framework.Graph;
import framework.RoadObject;

public class StaticObjectTest {
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
		gr.addEdge(4, 10, 15.887);

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

		gr.printGraph();
		gr.printEdgesInfo();

		gr.addObjectOnEdge(3, rObj1);
		gr.addObjectOnEdge(5, rObj2);
		gr.addObjectOnEdge(1, rObj3);
		gr.addObjectOnEdge(7, rObj4);
		gr.addObjectOnEdge(8, rObj5);

		gr.printObjectsOnEdges();
	}

}
