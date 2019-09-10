package testing;

import algorithm.NaiveANN;
import framework.Graph;
import framework.RoadObject;

public class NaiveANNManualDatasetTest {
	public static void main(String[] args) {
		Graph gr = new Graph();

		gr.addEdge(1, 2, 14.69);
		gr.addEdge(1, 3, 20.5499);
		// gr.addEdge(2, 3, 19.0658);
		gr.addEdge(3, 4, 14.887);
		gr.addEdge(3, 5, 11.01466);
		gr.addEdge(5, 6, 16.963);
		gr.addEdge(4, 6, 13.17489);
		gr.addEdge(6, 7, 11.12365);
		gr.addEdge(7, 8, 9.4421);
		gr.addEdge(4, 10, 16.432);
		gr.addEdge(10, 11, 9.87);
		gr.addEdge(5, 12, 7.89);
		gr.addEdge(12, 13, 8.48);
		gr.addEdge(6, 13, 15.87);
		gr.addEdge(7, 14, 5.65);
		gr.addEdge(14, 15, 10.15);

		gr.printEdgesInfo();

		RoadObject rObj11 = new RoadObject();
		RoadObject rObj12 = new RoadObject();
		RoadObject rObj13 = new RoadObject();
		RoadObject rObj31 = new RoadObject();
		RoadObject rObj41 = new RoadObject();
		RoadObject rObj51 = new RoadObject();
		RoadObject rObj52 = new RoadObject();
		RoadObject rObj111 = new RoadObject();
		RoadObject rObj131 = new RoadObject();
		RoadObject rObj132 = new RoadObject();
		RoadObject rObj151 = new RoadObject();
		RoadObject rObj142 = new RoadObject();
		RoadObject rObj83 = new RoadObject();
		RoadObject rObj64 = new RoadObject();

		rObj11.setObjId(11);
		rObj11.setDistanceFromStartNode(3.15);
		rObj11.setType(false);

		rObj12.setObjId(12);
		rObj12.setDistanceFromStartNode(8.46);
		rObj142.setType(false);

		rObj13.setObjId(13);
		rObj13.setDistanceFromStartNode(12.68);
		rObj13.setType(false);

		gr.addObjectOnEdge(1, rObj11);
		gr.addObjectOnEdge(1, rObj12);
		gr.addObjectOnEdge(1, rObj13);

		rObj31.setObjId(31);
		rObj31.setDistanceFromStartNode(9.18);
		rObj31.setType(false);
		gr.addObjectOnEdge(3, rObj31);

		rObj41.setObjId(41);
		rObj41.setDistanceFromStartNode(8.45);
		rObj41.setType(false);
		gr.addObjectOnEdge(4, rObj41);

		rObj51.setObjId(51);
		rObj51.setDistanceFromStartNode(8.12);
		rObj51.setType(false);

		rObj52.setObjId(52);
		rObj52.setDistanceFromStartNode(10.44);
		rObj52.setType(false);

		gr.addObjectOnEdge(5, rObj51);
		gr.addObjectOnEdge(5, rObj52);

		rObj111.setObjId(111);
		rObj111.setDistanceFromStartNode(4.32);
		rObj111.setType(false);
		gr.addObjectOnEdge(11, rObj111);

		rObj131.setObjId(131);
		rObj131.setDistanceFromStartNode(3.48);
		rObj131.setType(false);

		rObj132.setObjId(132);
		rObj132.setDistanceFromStartNode(11.36);
		rObj132.setType(false);

		gr.addObjectOnEdge(13, rObj131);
		gr.addObjectOnEdge(13, rObj132);

		rObj151.setObjId(151);
		rObj151.setDistanceFromStartNode(5.24);
		rObj151.setType(false);
		gr.addObjectOnEdge(15, rObj151);

		rObj142.setObjId(142);
		rObj142.setDistanceFromStartNode(2.15);
		rObj142.setType(false);
		gr.addObjectOnEdge(14, rObj142);

		rObj83.setObjId(83);
		rObj83.setDistanceFromStartNode(8.22);
		rObj83.setType(false);
		gr.addObjectOnEdge(8, rObj83);

		rObj64.setObjId(64);
		rObj64.setDistanceFromStartNode(3.28);
		rObj64.setType(false);
		gr.addObjectOnEdge(6, rObj64);

		RoadObject qObject33 = new RoadObject();
		RoadObject qObject101 = new RoadObject();
		RoadObject qObject71 = new RoadObject();
		RoadObject qObject121 = new RoadObject();

		qObject33.setObjId(33);
		qObject33.setDistanceFromStartNode(3.25);
		qObject33.setType(true);
		gr.addObjectOnEdge(3, qObject33);

		qObject101.setObjId(101);
		qObject101.setDistanceFromStartNode(7.25);
		qObject101.setType(true);
		gr.addObjectOnEdge(10, qObject101);

		qObject71.setObjId(71);
		qObject71.setDistanceFromStartNode(5.42);
		qObject71.setType(true);
		gr.addObjectOnEdge(7, qObject71);

		qObject121.setObjId(121);
		qObject121.setDistanceFromStartNode(4.26);
		qObject121.setType(true);
		gr.addObjectOnEdge(12, qObject121);

		gr.printObjectsOnEdges();
		NaiveANN nann2 = new NaiveANN();

		nann2.computeANN(gr);
		nann2.printNearestNeighborSets();

	}

}
