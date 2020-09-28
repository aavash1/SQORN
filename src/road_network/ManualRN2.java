package road_network;

import framework.Graph;
import framework.RoadObject;

public class ManualRN2 {

	// Manual graph originally was designed to test ... 
	
	private static Graph gr = new Graph();
	public static Graph getGraph () { 
		initialize();
		return gr;
	}
	
	private static void initialize() { 
		gr.addEdge(1, 2, 7);
		gr.addEdge(2, 3, 7);
		gr.addEdge(3, 4, 5);
		gr.addEdge(3, 5, 4);
		gr.addEdge(3, 6, 6);
		gr.addEdge(4, 7, 7);
		gr.addEdge(4, 15, 5);
		gr.addEdge(15, 16, 4);
		gr.addEdge(5, 8, 6);
		gr.addEdge(5, 13, 5);
		gr.addEdge(13, 14, 4);
		gr.addEdge(6, 7, 6);
		gr.addEdge(7, 9, 5);
		gr.addEdge(6, 8, 6);
		gr.addEdge(6, 9, 7);
		gr.addEdge(8, 10, 5);
		gr.addEdge(10, 11, 4);
		gr.addEdge(11, 12, 5);

		RoadObject rObj1 = new RoadObject();
		rObj1.setObjId(11);
		rObj1.setDistanceFromStartNode(2);
		rObj1.setType(false);
		gr.addObjectOnEdge(1, rObj1);

		RoadObject rObj2 = new RoadObject();
		rObj2.setObjId(12);
		rObj2.setDistanceFromStartNode(5);
		rObj2.setType(true);
		gr.addObjectOnEdge(1, rObj2);

		RoadObject rObj3 = new RoadObject();
		rObj3.setObjId(13);
		rObj3.setDistanceFromStartNode(6);
		rObj3.setType(true);
		gr.addObjectOnEdge(1, rObj3);
		//
		RoadObject rObj4 = new RoadObject();
		rObj4.setObjId(21);
		rObj4.setDistanceFromStartNode(2);
		rObj4.setType(true);
		gr.addObjectOnEdge(2, rObj4);

		RoadObject rObj5 = new RoadObject();
		rObj5.setObjId(22);
		rObj5.setDistanceFromStartNode(5);
		rObj5.setType(false);
		gr.addObjectOnEdge(2, rObj5);

		RoadObject rObj6 = new RoadObject();
		rObj6.setObjId(31);
		rObj6.setDistanceFromStartNode(4);
		rObj6.setType(true);
		gr.addObjectOnEdge(3, rObj6);

		RoadObject rObj7 = new RoadObject();
		rObj7.setObjId(41);
		rObj7.setDistanceFromStartNode(1);
		rObj7.setType(false);
		gr.addObjectOnEdge(4, rObj7);

		RoadObject rObj8 = new RoadObject();
		rObj8.setObjId(42);
		rObj8.setDistanceFromStartNode(3);
		rObj8.setType(false);
		gr.addObjectOnEdge(4, rObj8);

		RoadObject rObj9 = new RoadObject();
		rObj9.setObjId(51);
		rObj9.setDistanceFromStartNode(1);
		rObj9.setType(true);
		gr.addObjectOnEdge(5, rObj9);

		RoadObject rObj10 = new RoadObject();
		rObj10.setObjId(52);
		rObj10.setDistanceFromStartNode(4);
		rObj10.setType(true);
		gr.addObjectOnEdge(5, rObj10);

		RoadObject rObj11 = new RoadObject();
		rObj11.setObjId(61);
		rObj11.setDistanceFromStartNode(4);
		rObj11.setType(false);
		gr.addObjectOnEdge(6, rObj11);

		RoadObject rObj12 = new RoadObject();
		rObj12.setObjId(71);
		rObj12.setDistanceFromStartNode(1);
		rObj12.setType(true);
		gr.addObjectOnEdge(7, rObj12);

		RoadObject rObj13 = new RoadObject();
		rObj13.setObjId(72);
		rObj13.setDistanceFromStartNode(2);
		rObj13.setType(true);
		gr.addObjectOnEdge(7, rObj13);

		RoadObject rObj14 = new RoadObject();
		rObj14.setObjId(73);
		rObj14.setDistanceFromStartNode(4);
		rObj14.setType(false);
		gr.addObjectOnEdge(7, rObj14);

		RoadObject rObj15 = new RoadObject();
		rObj15.setObjId(81);
		rObj15.setDistanceFromStartNode(2);
		rObj15.setType(true);
		gr.addObjectOnEdge(8, rObj15);

		RoadObject rObj16 = new RoadObject();
		rObj16.setObjId(91);
		rObj16.setDistanceFromStartNode(3);
		rObj16.setType(true);
		gr.addObjectOnEdge(9, rObj16);

		RoadObject rObj17 = new RoadObject();
		rObj17.setObjId(101);
		rObj17.setDistanceFromStartNode(1);
		rObj17.setType(true);
		gr.addObjectOnEdge(10, rObj17);

		RoadObject rObj18 = new RoadObject();
		rObj18.setObjId(102);
		rObj18.setDistanceFromStartNode(4);
		rObj18.setType(true);
		gr.addObjectOnEdge(10, rObj18);

		RoadObject rObj19 = new RoadObject();
		rObj19.setObjId(111);
		rObj19.setDistanceFromStartNode(1);
		rObj19.setType(true);
		gr.addObjectOnEdge(11, rObj19);

		RoadObject rObj20 = new RoadObject();
		rObj20.setObjId(112);
		rObj20.setDistanceFromStartNode(3);
		rObj20.setType(true);
		gr.addObjectOnEdge(11, rObj20);

		RoadObject rObj21 = new RoadObject();
		rObj21.setObjId(121);
		rObj21.setDistanceFromStartNode(3);
		rObj21.setType(false);
		gr.addObjectOnEdge(12, rObj21);

		RoadObject rObj22 = new RoadObject();
		rObj22.setObjId(131);
		rObj22.setDistanceFromStartNode(1);
		rObj22.setType(false);
		gr.addObjectOnEdge(13, rObj22);

		RoadObject rObj23 = new RoadObject();
		rObj23.setObjId(132);
		rObj23.setDistanceFromStartNode(3);
		rObj23.setType(false);
		gr.addObjectOnEdge(13, rObj23);

		RoadObject rObj24 = new RoadObject();
		rObj24.setObjId(141);
		rObj24.setDistanceFromStartNode(1);
		rObj24.setType(false);
		gr.addObjectOnEdge(14, rObj24);

		RoadObject rObj25 = new RoadObject();
		rObj25.setObjId(151);
		rObj25.setDistanceFromStartNode(1);
		rObj25.setType(true);
		gr.addObjectOnEdge(15, rObj25);

		RoadObject rObj26 = new RoadObject();
		rObj26.setObjId(152);
		rObj26.setDistanceFromStartNode(3);
		rObj26.setType(false);
		gr.addObjectOnEdge(15, rObj26);

		RoadObject rObj27 = new RoadObject();
		rObj27.setObjId(153);
		rObj27.setDistanceFromStartNode(5);
		rObj27.setType(true);
		gr.addObjectOnEdge(15, rObj27);

		RoadObject rObj28 = new RoadObject();
		rObj28.setObjId(154);
		rObj28.setDistanceFromStartNode(6);
		rObj28.setType(true);
		gr.addObjectOnEdge(15, rObj28);

		RoadObject rObj29 = new RoadObject();
		rObj29.setObjId(161);
		rObj29.setDistanceFromStartNode(2);
		rObj29.setType(false);
		gr.addObjectOnEdge(16, rObj29);

		RoadObject rObj30 = new RoadObject();
		rObj30.setObjId(162);
		rObj30.setDistanceFromStartNode(4);
		rObj30.setType(true);
		gr.addObjectOnEdge(16, rObj30);

		RoadObject rObj31 = new RoadObject();
		rObj31.setObjId(171);
		rObj31.setDistanceFromStartNode(1);
		rObj31.setType(true);
		gr.addObjectOnEdge(17, rObj31);

		RoadObject rObj32 = new RoadObject();
		rObj32.setObjId(172);
		rObj32.setDistanceFromStartNode(3);
		rObj32.setType(true);
		gr.addObjectOnEdge(17, rObj32);

		//gr.printObjectsOnEdges();
	}
	
}
