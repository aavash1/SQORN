package testing;

import framework.Graph2;
import framework.RoadObject;

public class NearestNeighborTestOnManualObjectsManualDataset {

	public static void main(String[] args) {
		Graph2 gr = new Graph2();

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
		System.out.println();
		
		gr.printEdgesInfo();
		System.out.println();
		
		gr.addObjectOnEdge(3, rObj1);
		gr.addObjectOnEdge(5, rObj2);
		gr.addObjectOnEdge(1, rObj3);
		gr.addObjectOnEdge(7, rObj4);
		gr.addObjectOnEdge(8, rObj5);
		
		//extra objects
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
		

		gr.printObjectsOnEdges();
		System.out.println();
		
		System.out.println("Nearest False Object to Obj id 31:");
//		int nearestFalseObj = gr.getNearestFalseObjectIdToGivenObjOnMap(31);
//		System.out.println(nearestFalseObj);
		int nearestObj = gr.getNearestObjectIdToGivenObjOnMap(31);
		System.out.println(nearestObj);

		

	}

}
