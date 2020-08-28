package testing;

import algorithm.ClusteringNodes;
import algorithm.ClusteringRoadObjects;
import algorithm.ANNNaive;
import framework.Graph;
import framework.RoadObject;

public class ClusteringRoadObjectsNewManualTest {

	public static void main(String[] args) {

		Graph gr = new Graph();

		gr.addEdge(0, 1, 5.4);
		gr.addEdge(1, 2, 6.2);
		gr.addEdge(1, 4, 10.0);
		gr.addEdge(1, 3, 4.1);
		gr.addEdge(3, 6, 11.20);
		gr.addEdge(3, 7, 11.21);
		gr.addEdge(3, 5, 8.2);
		gr.addEdge(4, 5, 7.6);
		gr.addEdge(2, 59, 32.6);
		gr.addEdge(4, 33, 15.7);
		gr.addEdge(5, 31, 10.01);

		gr.addEdge(5, 9, 8.1);
		gr.addEdge(9, 7, 3.4);
		gr.addEdge(6, 8, 14.1);
		gr.addEdge(8, 12, 18.3);
		gr.addEdge(10, 11, 16.7);
		gr.addEdge(11, 12, 6.1);
		gr.addEdge(10, 14, 10.5);
		gr.addEdge(14, 31, 14.2);
		gr.addEdge(14, 15, 6.0);
		gr.addEdge(13, 22, 17.2);

		gr.addEdge(22, 20, 17.4);
		gr.addEdge(20, 21, 9.3);
		gr.addEdge(21, 16, 16.7);
		gr.addEdge(16, 13, 10.6);
		gr.addEdge(16, 23, 16.4);
		gr.addEdge(15, 29, 5.8);
		gr.addEdge(14, 35, 16.2);
		gr.addEdge(21, 19, 10.1);
		gr.addEdge(21, 18, 12.6);
		gr.addEdge(21, 23, 19.6);

		gr.addEdge(19, 24, 16.3);
		gr.addEdge(19, 25, 18.2);
		gr.addEdge(23, 24, 14.1);
		gr.addEdge(23, 17, 15.42);
		gr.addEdge(29, 28, 5.1);
		gr.addEdge(17, 28, 6.8);
		gr.addEdge(17, 37, 13.3);
		gr.addEdge(17, 26, 19.32);
		gr.addEdge(28, 45, 15.18);
		gr.addEdge(32, 30, 11.2);

		gr.addEdge(32, 34, 4.2);
		gr.addEdge(30, 35, 7.4);
		gr.addEdge(35, 40, 10.4);
		gr.addEdge(40, 36, 15.2);
		gr.addEdge(33, 59, 15.4);
		gr.addEdge(59, 39, 3.8);
		gr.addEdge(39, 38, 3.2);
		gr.addEdge(39, 41, 6.4);
		gr.addEdge(41, 40, 2.1);
		gr.addEdge(41, 42, 2.1);

		gr.addEdge(42, 43, 2.0);
		gr.addEdge(60, 58, 10.2);
		gr.addEdge(60, 57, 2.0);
		gr.addEdge(57, 58, 8.4);
		gr.addEdge(58, 55, 13.9);
		gr.addEdge(58, 54, 14.5);
		gr.addEdge(56, 54, 12.3);
		gr.addEdge(24, 25, 2.6);
		gr.addEdge(55, 43, 15.2);
		gr.addEdge(54, 53, 12.9);

		gr.addEdge(53, 46, 11.4);
		gr.addEdge(46, 52, 18.3);
		gr.addEdge(52, 47, 16.6);
		gr.addEdge(52, 48, 13.2);
		gr.addEdge(52, 50, 14.8);
		gr.addEdge(44, 36, 13.9);
		gr.addEdge(36, 45, 14.8);
		gr.addEdge(37, 27, 5.2);
		gr.addEdge(37, 47, 13.4);
		gr.addEdge(27, 26, 17.3);

		gr.addEdge(27, 47, 14.9);
		gr.addEdge(44, 47, 14.8);
		gr.addEdge(47, 48, 6.3);
		gr.addEdge(48, 49, 4.2);
		gr.addEdge(49, 26, 4.9);
		gr.addEdge(26, 51, 18.8);
		gr.addEdge(26, 25, 16.6);
		gr.addEdge(50, 51, 6.1);
		
		// true=query; false=data
		// Create road objects and add on graph
		//adding false objects
		RoadObject rObj1 = new RoadObject();
		rObj1.setObjId(31);
		rObj1.setDistanceFromStartNode(8);
		rObj1.setType(false);
		gr.addObjectOnEdge(3, rObj1);
		
		RoadObject rObj2 = new RoadObject();
		rObj2.setObjId(41);
		rObj2.setDistanceFromStartNode(3.1);
		rObj2.setType(false);
		gr.addObjectOnEdge(4, rObj2);
		
		RoadObject rObj3 = new RoadObject();
		rObj3.setObjId(51);
		rObj3.setDistanceFromStartNode(9.2);
		rObj3.setType(false);
		gr.addObjectOnEdge(5, rObj3);
		
		RoadObject rObj4= new RoadObject();
		rObj4.setObjId(71);
		rObj4.setDistanceFromStartNode(6.1);
		rObj4.setType(false);
		gr.addObjectOnEdge(7, rObj4);
		
		RoadObject rObj5= new RoadObject();
		rObj5.setObjId(81);
		rObj5.setDistanceFromStartNode(4.4);
		rObj5.setType(false);
		gr.addObjectOnEdge(8, rObj5);
		
		RoadObject rObj6= new RoadObject();
		rObj6.setObjId(101);
		rObj6.setDistanceFromStartNode(4.3);
		rObj6.setType(false);
		gr.addObjectOnEdge(10, rObj6);
		
		RoadObject rObj7= new RoadObject();
		rObj7.setObjId(102);
		rObj7.setDistanceFromStartNode(4.4);
		rObj7.setType(false);
		gr.addObjectOnEdge(10, rObj7);
		
		RoadObject rObj8= new RoadObject();
		rObj8.setObjId(461);
		rObj8.setDistanceFromStartNode(14.4);
		rObj8.setType(false);
		gr.addObjectOnEdge(46, rObj8);

		RoadObject rObj9= new RoadObject();
		rObj9.setObjId(571);
		rObj9.setDistanceFromStartNode(11.3);
		rObj9.setType(false);
		gr.addObjectOnEdge(57, rObj9);
		
		RoadObject rObj10= new RoadObject();
		rObj10.setObjId(611);
		rObj10.setDistanceFromStartNode(2);
		rObj10.setType(false);
		gr.addObjectOnEdge(61, rObj10);
	
		RoadObject rObj11= new RoadObject();
		rObj11.setObjId(171);
		rObj11.setDistanceFromStartNode(1);
		rObj11.setType(false);
		gr.addObjectOnEdge(17, rObj11);
		
		RoadObject rObj12= new RoadObject();
		rObj12.setObjId(181);
		rObj12.setDistanceFromStartNode(2.3);
		rObj12.setType(false);
		gr.addObjectOnEdge(18, rObj12);
		
		RoadObject rObj13= new RoadObject();
		rObj13.setObjId(182);
		rObj13.setDistanceFromStartNode(7.9);
		rObj13.setType(false);
		gr.addObjectOnEdge(18, rObj13);
		
		RoadObject rObj14= new RoadObject();
		rObj14.setObjId(271);
		rObj14.setDistanceFromStartNode(2.2);
		rObj14.setType(false);
		gr.addObjectOnEdge(27,rObj14);
		
		RoadObject rObj15= new RoadObject();
		rObj15.setObjId(371);
		rObj15.setDistanceFromStartNode(2.7);
		rObj15.setType(false);
		gr.addObjectOnEdge(37, rObj15);
		
		RoadObject rObj16= new RoadObject();
		rObj16.setObjId(441);
		rObj16.setDistanceFromStartNode(4.4);
		rObj16.setType(false);
		gr.addObjectOnEdge(44, rObj16);
		
		RoadObject rObj17= new RoadObject();
		rObj17.setObjId(451);
		rObj17.setDistanceFromStartNode(3.2);
		rObj17.setType(false);
		gr.addObjectOnEdge(45, rObj17);
		
		RoadObject rObj18= new RoadObject();
		rObj18.setObjId(452);
		rObj18.setDistanceFromStartNode(12.8);
		rObj18.setType(false);
		gr.addObjectOnEdge(45, rObj18);
		
		RoadObject rObj19= new RoadObject();
		rObj19.setObjId(631);
		rObj19.setDistanceFromStartNode(3.4);
		rObj19.setType(false);
		gr.addObjectOnEdge(63, rObj19);
		
		RoadObject rObj20= new RoadObject();
		rObj20.setObjId(632);
		rObj20.setDistanceFromStartNode(10.1);
		rObj20.setType(false);
		gr.addObjectOnEdge(63, rObj20);
		
		RoadObject rObj21= new RoadObject();
		rObj21.setObjId(251);
		rObj21.setDistanceFromStartNode(3.8);
		rObj21.setType(false);
		gr.addObjectOnEdge(25, rObj21);
		
		RoadObject rObj22= new RoadObject();
		rObj22.setObjId(261);
		rObj22.setDistanceFromStartNode(4.4);
		rObj22.setType(false);
		gr.addObjectOnEdge(26, rObj22);
		
		RoadObject rObj23= new RoadObject();
		rObj23.setObjId(351);
		rObj23.setDistanceFromStartNode(5.6);
		rObj23.setType(false);
		gr.addObjectOnEdge(35, rObj23);
		
		RoadObject rObj24= new RoadObject();
		rObj24.setObjId(711);
		rObj24.setDistanceFromStartNode(11.4);
		rObj24.setType(false);
		gr.addObjectOnEdge(71, rObj24);
		
		RoadObject rObj25= new RoadObject();
		rObj25.setObjId(641);
		rObj25.setDistanceFromStartNode(5.9);
		rObj25.setType(false);
		gr.addObjectOnEdge(64, rObj25);
		
		RoadObject rObj26= new RoadObject();
		rObj26.setObjId(311);
		rObj26.setDistanceFromStartNode(4.6);
		rObj26.setType(false);
		gr.addObjectOnEdge(31, rObj26);
		
		RoadObject rObj27= new RoadObject();
		rObj27.setObjId(661);
		rObj27.setDistanceFromStartNode(6.7);
		rObj27.setType(false);
		gr.addObjectOnEdge(66, rObj27);
		
		RoadObject rObj28= new RoadObject();
		rObj28.setObjId(231);
		rObj28.setDistanceFromStartNode(4.1);
		rObj28.setType(false);
		gr.addObjectOnEdge(23, rObj28);
		
		RoadObject rObj29= new RoadObject();
		rObj29.setObjId(291);
		rObj29.setDistanceFromStartNode(3.8);
		rObj29.setType(false);
		gr.addObjectOnEdge(29, rObj29);
		
		RoadObject rObj30= new RoadObject();
		rObj30.setObjId(781);
		rObj30.setDistanceFromStartNode(3.1);
		rObj30.setType(false);
		gr.addObjectOnEdge(78, rObj30);
		
		
		//adding true objects
		RoadObject rObj31= new RoadObject();
		rObj31.setObjId(11);
		rObj31.setDistanceFromStartNode(3.1);
		rObj31.setType(true);
		gr.addObjectOnEdge(1, rObj31);
		
		RoadObject rObj32= new RoadObject();
		rObj32.setObjId(61);
		rObj32.setDistanceFromStartNode(2.8);
		rObj32.setType(true);
		gr.addObjectOnEdge(6, rObj32);
		
		RoadObject rObj33= new RoadObject();
		rObj33.setObjId(62);
		rObj33.setDistanceFromStartNode(7.4);
		rObj33.setType(true);
		gr.addObjectOnEdge(6, rObj33);
		
		RoadObject rObj34= new RoadObject();
		rObj34.setObjId(91);
		rObj34.setDistanceFromStartNode(6.8);
		rObj34.setType(true);
		gr.addObjectOnEdge(9, rObj34);
		
		RoadObject rObj35= new RoadObject();
		rObj35.setObjId(92);
		rObj35.setDistanceFromStartNode(16.1);
		rObj35.setType(true);
		gr.addObjectOnEdge(9, rObj35);
		
		RoadObject rObj36= new RoadObject();
		rObj36.setObjId(93);
		rObj36.setDistanceFromStartNode(19.4);
		rObj36.setType(true);
		gr.addObjectOnEdge(9, rObj36);
		
		RoadObject rObj37= new RoadObject();
		rObj37.setObjId(94);
		rObj37.setDistanceFromStartNode(22.5);
		rObj37.setType(true);
		gr.addObjectOnEdge(9, rObj37);
		
		RoadObject rObj38= new RoadObject();
		rObj38.setObjId(95);
		rObj38.setDistanceFromStartNode(29.7);
		rObj38.setType(true);
		gr.addObjectOnEdge(9, rObj38);
		
		RoadObject rObj39= new RoadObject();
		rObj39.setObjId(121);
		rObj39.setDistanceFromStartNode(2.3);
		rObj39.setType(true);
		gr.addObjectOnEdge(12, rObj39);
		
		RoadObject rObj40= new RoadObject();
		rObj40.setObjId(122);
		rObj40.setDistanceFromStartNode(5.9);
		rObj40.setType(true);
		gr.addObjectOnEdge(12, rObj40);
		
		RoadObject rObj41= new RoadObject();
		rObj41.setObjId(111);
		rObj41.setDistanceFromStartNode(4.9);
		rObj41.setType(true);
		gr.addObjectOnEdge(11, rObj41);
		
		RoadObject rObj42= new RoadObject();
		rObj42.setObjId(421);
		rObj42.setDistanceFromStartNode(2);
		rObj42.setType(true);
		gr.addObjectOnEdge(42, rObj42);
		
		RoadObject rObj43= new RoadObject();
		rObj43.setObjId(612);
		rObj43.setDistanceFromStartNode(8.4);
		rObj43.setType(true);
		gr.addObjectOnEdge(61, rObj43);
		
		RoadObject rObj44= new RoadObject();
		rObj44.setObjId(141);
		rObj44.setDistanceFromStartNode(10);
		rObj44.setType(true);
		gr.addObjectOnEdge(14, rObj44);
		
		RoadObject rObj45= new RoadObject();
		rObj45.setObjId(151);
		rObj45.setDistanceFromStartNode(12.3);
		rObj45.setType(true);
		gr.addObjectOnEdge(15, rObj45);
		
		///
		RoadObject rObj46= new RoadObject();
		rObj46.setObjId(161);
		rObj46.setDistanceFromStartNode(5.3);
		rObj46.setType(true);
		gr.addObjectOnEdge(16, rObj46);
		
		RoadObject rObj47= new RoadObject();
		rObj47.setObjId(162);
		rObj47.setDistanceFromStartNode(11.1);
		rObj47.setType(true);
		gr.addObjectOnEdge(16, rObj47);
		
		RoadObject rObj48= new RoadObject();
		rObj48.setObjId(163);
		rObj48.setDistanceFromStartNode(15.7);
		rObj48.setType(true);
		gr.addObjectOnEdge(16, rObj48);
		
		///
		RoadObject rObj49= new RoadObject();
		rObj49.setObjId(191);
		rObj49.setDistanceFromStartNode(3.5);
		rObj49.setType(true);
		gr.addObjectOnEdge(19, rObj49);
		
		RoadObject rObj50= new RoadObject();
		rObj50.setObjId(192);
		rObj50.setDistanceFromStartNode(6.8);
		rObj50.setType(true);
		gr.addObjectOnEdge(19, rObj50);
		
		RoadObject rObj51= new RoadObject();
		rObj51.setObjId(193);
		rObj51.setDistanceFromStartNode(12.6);
		rObj51.setType(true);
		gr.addObjectOnEdge(19, rObj51);
		//
		
		RoadObject rObj52= new RoadObject();
		rObj52.setObjId(281);
		rObj52.setDistanceFromStartNode(5.3);
		rObj52.setType(true);
		gr.addObjectOnEdge(28, rObj52);
		
		RoadObject rObj53= new RoadObject();
		rObj53.setObjId(282);
		rObj53.setDistanceFromStartNode(12.5);
		rObj53.setType(true);
		gr.addObjectOnEdge(28, rObj53);
		
		RoadObject rObj54= new RoadObject();
		rObj54.setObjId(411);
		rObj54.setDistanceFromStartNode(9.1);
		rObj54.setType(true);
		gr.addObjectOnEdge(41, rObj54);
		
		RoadObject rObj55= new RoadObject();
		rObj55.setObjId(431);
		rObj55.setDistanceFromStartNode(3.4);
		rObj55.setType(true);
		gr.addObjectOnEdge(43, rObj55);
		
		RoadObject rObj56= new RoadObject();
		rObj56.setObjId(621);
		rObj56.setDistanceFromStartNode(4.2);
		rObj56.setType(true);
		gr.addObjectOnEdge(62, rObj56);
		
		RoadObject rObj57= new RoadObject();
		rObj57.setObjId(622);
		rObj57.setDistanceFromStartNode(8.5);
		rObj57.setType(true);
		gr.addObjectOnEdge(62, rObj57);
		
		RoadObject rObj58= new RoadObject();
		rObj58.setObjId(211);
		rObj58.setDistanceFromStartNode(13.2);
		rObj58.setType(true);
		gr.addObjectOnEdge(21, rObj58);
		
		RoadObject rObj59= new RoadObject();
		rObj59.setObjId(212);
		rObj59.setDistanceFromStartNode(15.4);
		rObj59.setType(true);
		gr.addObjectOnEdge(21, rObj59);
		
		RoadObject rObj60= new RoadObject();
		rObj60.setObjId(252);
		rObj60.setDistanceFromStartNode(7.8);
		rObj60.setType(true);
		gr.addObjectOnEdge(25, rObj60);
		
		RoadObject rObj61= new RoadObject();
		rObj61.setObjId(241);
		rObj61.setDistanceFromStartNode(3.6);
		rObj61.setType(true);
		gr.addObjectOnEdge(24, rObj61);
		
		RoadObject rObj62= new RoadObject();
		rObj62.setObjId(262);
		rObj62.setDistanceFromStartNode(11.3);
		rObj62.setType(true);
		gr.addObjectOnEdge(26, rObj62);
		
		RoadObject rObj63= new RoadObject();
		rObj63.setObjId(633);
		rObj63.setDistanceFromStartNode(12.2);
		rObj63.setType(true);
		gr.addObjectOnEdge(63, rObj63);
		
		RoadObject rObj64= new RoadObject();
		rObj64.setObjId(634);
		rObj64.setDistanceFromStartNode(16);
		rObj64.setType(true);
		gr.addObjectOnEdge(63, rObj64);
		
		RoadObject rObj65= new RoadObject();
		rObj65.setObjId(221);
		rObj65.setDistanceFromStartNode(13.6);
		rObj65.setType(true);
		gr.addObjectOnEdge(22, rObj65);
		
		RoadObject rObj66= new RoadObject();
		rObj66.setObjId(301);
		rObj66.setDistanceFromStartNode(3.8);
		rObj66.setType(true);
		gr.addObjectOnEdge(30, rObj66);
		
		RoadObject rObj67= new RoadObject();
		rObj67.setObjId(302);
		rObj67.setDistanceFromStartNode(6.1);
		rObj67.setType(true);
		gr.addObjectOnEdge(30, rObj67);
		
		RoadObject rObj68= new RoadObject();
		rObj68.setObjId(303);
		rObj68.setDistanceFromStartNode(10.4);
		rObj68.setType(true);
		gr.addObjectOnEdge(30, rObj68);
		
		RoadObject rObj69= new RoadObject();
		rObj69.setObjId(312);
		rObj69.setDistanceFromStartNode(13.2);
		rObj69.setType(true);
		gr.addObjectOnEdge(31, rObj69);
		
		RoadObject rObj70= new RoadObject();
		rObj70.setObjId(341);
		rObj70.setDistanceFromStartNode(4);
		rObj70.setType(true);
		gr.addObjectOnEdge(34, rObj70);
		
		RoadObject rObj71= new RoadObject();
		rObj71.setObjId(782);
		rObj71.setDistanceFromStartNode(12.1);
		rObj71.setType(true);
		gr.addObjectOnEdge(78, rObj71);
		
		RoadObject rObj72= new RoadObject();
		rObj72.setObjId(771);
		rObj72.setDistanceFromStartNode(6);
		rObj72.setType(true);
		gr.addObjectOnEdge(77, rObj72);
		
		RoadObject rObj73= new RoadObject();
		rObj73.setObjId(772);
		rObj73.setDistanceFromStartNode(12.6);
		rObj73.setType(true);
		gr.addObjectOnEdge(77, rObj73);
		
		RoadObject rObj74= new RoadObject();
		rObj74.setObjId(662);
		rObj74.setDistanceFromStartNode(11.9);
		rObj74.setType(true);
		gr.addObjectOnEdge(66, rObj74);
			
		RoadObject rObj75= new RoadObject();
		rObj75.setObjId(662);
		rObj75.setDistanceFromStartNode(10.2);
		rObj75.setType(true);
		gr.addObjectOnEdge(34, rObj75);

//		ANNNaive nAnn = new ANNNaive();
//		nAnn.compute(gr);
//		nAnn.printNearestNeighborSets();

		ClusteringNodes clusteringNodes = new ClusteringNodes();

		ClusteringRoadObjects clusteringObjects = new ClusteringRoadObjects();
		clusteringObjects.clusterWithIndex2(gr, clusteringNodes.cluster(gr), true);
		// clusteringObjects.clusterWithIndex(gr, clusteringNodes.cluster(gr), false);
		clusteringNodes.printNodeClusters();
		clusteringObjects.printRoadObjectClusters();

	}

}
