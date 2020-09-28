package road_network;

import framework.Graph;
import framework.RoadObject;

public class ManualRN3 {

	// Manual graph originally was designed to test ... 
	
	private static Graph gr = new Graph();
	public static Graph getGraph () { 
		initialize();
		return gr;
	}
	
	private static void initialize() { 
		gr.addEdge(1, 2, 4);
		gr.addEdge(2, 3, 6);
		gr.addEdge(3, 4, 5);
		gr.addEdge(4, 5, 4);
		gr.addEdge(5, 6, 5);
		gr.addEdge(4, 7, 5);
		gr.addEdge(7, 8, 5);
		
		RoadObject rObj13 = new RoadObject();
		rObj13.setObjId(11);
		rObj13.setDistanceFromStartNode(2);
		rObj13.setType(false);
		gr.addObjectOnEdge(1, rObj13);

		RoadObject rObj1 = new RoadObject();
		rObj1.setObjId(21);
		rObj1.setDistanceFromStartNode(2);
		rObj1.setType(true);
		gr.addObjectOnEdge(2, rObj1);
		
		RoadObject rObj2 = new RoadObject();
		rObj2.setObjId(22);
		rObj2.setDistanceFromStartNode(3);
		rObj2.setType(true);
		gr.addObjectOnEdge(2, rObj2);
		
		RoadObject rObj3 = new RoadObject();
		rObj3.setObjId(31);
		rObj3.setDistanceFromStartNode(1);
		rObj3.setType(true);
		gr.addObjectOnEdge(3, rObj3);
		
		RoadObject rObj4 = new RoadObject();
		rObj4.setObjId(32);
		rObj4.setDistanceFromStartNode(3);
		rObj4.setType(false);
		gr.addObjectOnEdge(3, rObj4);
		
		RoadObject rObj5 = new RoadObject();
		rObj5.setObjId(33);
		rObj5.setDistanceFromStartNode(4);
		rObj5.setType(false);
		gr.addObjectOnEdge(3, rObj5);
		
		RoadObject rObj6 = new RoadObject();
		rObj6.setObjId(41);
		rObj6.setDistanceFromStartNode(1);
		rObj6.setType(false);
		gr.addObjectOnEdge(4, rObj6);
		
		RoadObject rObj7 = new RoadObject();
		rObj7.setObjId(42);
		rObj7.setDistanceFromStartNode(2);
		rObj7.setType(true);
		gr.addObjectOnEdge(4, rObj7);
		
		RoadObject rObj8 = new RoadObject();
		rObj8.setObjId(43);
		rObj8.setDistanceFromStartNode(3);
		rObj8.setType(true);
		gr.addObjectOnEdge(4, rObj8);
		
		RoadObject rObj9 = new RoadObject();
		rObj9.setObjId(51);
		rObj9.setDistanceFromStartNode(1);
		rObj9.setType(true);
		gr.addObjectOnEdge(5, rObj9);
		
		RoadObject rObj10 = new RoadObject();
		rObj10.setObjId(52);
		rObj10.setDistanceFromStartNode(3);
		rObj10.setType(false);
		gr.addObjectOnEdge(5, rObj10);
		
		RoadObject rObj11 = new RoadObject();
		rObj11.setObjId(61);
		rObj11.setDistanceFromStartNode(2);
		rObj11.setType(true);
		gr.addObjectOnEdge(6,rObj11);
		
		RoadObject rObj12 = new RoadObject();
		rObj12.setObjId(62);
		rObj12.setDistanceFromStartNode(3);
		rObj12.setType(true);
		gr.addObjectOnEdge(6, rObj12);



		//gr.printObjectsOnEdges();
	}
	
}
