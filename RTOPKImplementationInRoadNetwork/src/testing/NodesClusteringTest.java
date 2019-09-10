package testing;

import algorithm.BFS;
import algorithm.ClusteringNodes;
import algorithm.NaiveANN;
import framework.Graph;
import framework.RoadObject;

public class NodesClusteringTest {

	public static void main(String[] args) {
		Graph gr = new Graph();

		gr.addEdge(1, 2, 40.6);
		gr.addEdge(1, 6, 40);
		gr.addEdge(6, 10, 43.33);
		gr.addEdge(1, 7, 65.23);
		gr.addEdge(3, 7, 65.22);
		gr.addEdge(3, 4, 33.20);
		gr.addEdge(4, 12, 62.55);
		gr.addEdge(4, 8, 47.47);
		gr.addEdge(5, 8, 49.08);
		gr.addEdge(8, 14, 19.54);
		gr.addEdge(14, 15, 64.03);
		gr.addEdge(13, 15, 55.22);
		gr.addEdge(11, 13, 48.00);
		gr.addEdge(10, 11, 33.2);
		gr.addEdge(9, 10, 33);
		gr.addEdge(9, 18, 53.3);
		gr.addEdge(11, 17, 25.21);
		gr.addEdge(18, 20, 43.33);
		gr.addEdge(17, 20, 60.12);
		gr.addEdge(17, 21, 40.22);
		gr.addEdge(16, 17, 33.11);
		gr.addEdge(13, 16, 31.13);
		gr.addEdge(16, 23, 37.62);
		gr.addEdge(22, 23, 26.00);
		gr.addEdge(22, 24, 43.11);
		gr.addEdge(20, 24, 22.43);
		gr.addEdge(20, 19, 30);

		gr.printEdgesInfo();

		RoadObject rObj1 = new RoadObject();
		rObj1.setObjId(11);
		rObj1.setDistanceFromStartNode(30.44);
		rObj1.setType(false);

		RoadObject qObj1 = new RoadObject();
		qObj1.setObjId(12);
		qObj1.setDistanceFromStartNode(9.81);
		qObj1.setType(true);

		gr.addObjectOnEdge(1, rObj1);
		gr.addObjectOnEdge(1, qObj1);

		RoadObject rObj3 = new RoadObject();
		RoadObject rObj31 = new RoadObject();

		rObj3.setObjId(31);
		rObj3.setDistanceFromStartNode(12.03);
		rObj3.setType(false);

		rObj31.setObjId(32);
		rObj31.setDistanceFromStartNode(22.16);
		rObj31.setType(false);

		gr.addObjectOnEdge(3, rObj3);
		gr.addObjectOnEdge(3, rObj31);

		RoadObject rObj4 = new RoadObject();
		rObj4.setObjId(41);
		rObj4.setDistanceFromStartNode(10.11);
		rObj4.setType(false);

		gr.addObjectOnEdge(4, rObj4);

		RoadObject rObj5 = new RoadObject();
		rObj5.setObjId(51);
		rObj5.setDistanceFromStartNode(20.10);
		rObj5.setType(true);

		gr.addObjectOnEdge(5, rObj5);

		RoadObject rObj7 = new RoadObject();
		rObj7.setObjId(71);
		rObj7.setDistanceFromStartNode(19.64);
		rObj7.setType(false);

		RoadObject rObj71 = new RoadObject();
		rObj71.setObjId(72);
		rObj71.setDistanceFromStartNode(58.06);
		rObj71.setType(false);

		RoadObject qObj7 = new RoadObject();
		qObj7.setObjId(73);
		qObj7.setDistanceFromStartNode(39.75);
		qObj7.setType(true);

		gr.addObjectOnEdge(7, rObj7);
		gr.addObjectOnEdge(7, rObj71);
		gr.addObjectOnEdge(7, qObj7);

		RoadObject rObj8 = new RoadObject();
		RoadObject rObj81 = new RoadObject();

		rObj8.setObjId(81);
		rObj8.setDistanceFromStartNode(10.54);
		rObj8.setType(false);

		rObj81.setObjId(82);
		rObj81.setDistanceFromStartNode(23.14);
		rObj81.setType(false);

		gr.addObjectOnEdge(8, rObj8);
		gr.addObjectOnEdge(8, rObj81);

		RoadObject qObj10 = new RoadObject();
		qObj10.setObjId(100);
		qObj10.setDistanceFromStartNode(8.22);
		qObj10.setType(true);

		gr.addObjectOnEdge(10, qObj10);

		RoadObject qObj11 = new RoadObject();
		qObj11.setObjId(110);
		qObj11.setDistanceFromStartNode(12.26);
		qObj11.setType(true);

		RoadObject qObj111 = new RoadObject();
		qObj111.setObjId(111);
		qObj111.setDistanceFromStartNode(32.13);
		qObj111.setType(true);

		RoadObject qObj112 = new RoadObject();
		qObj112.setObjId(112);
		qObj112.setDistanceFromStartNode(43.67);
		qObj112.setType(true);

		gr.addObjectOnEdge(11, qObj11);
		gr.addObjectOnEdge(11, qObj111);
		gr.addObjectOnEdge(11, qObj112);

		RoadObject rObj121 = new RoadObject();
		rObj121.setObjId(121);
		rObj121.setDistanceFromStartNode(28.1);
		rObj121.setType(false);

		RoadObject rObj122 = new RoadObject();
		rObj122.setObjId(122);
		rObj122.setDistanceFromStartNode(47.2);
		rObj122.setType(false);

		gr.addObjectOnEdge(12, rObj121);
		gr.addObjectOnEdge(12, rObj122);

		RoadObject rObj161 = new RoadObject();
		rObj161.setObjId(161);
		rObj161.setDistanceFromStartNode(10.02);
		rObj161.setType(false);

		RoadObject rObj162 = new RoadObject();
		rObj162.setObjId(162);
		rObj162.setDistanceFromStartNode(23.21);
		rObj162.setType(false);

		RoadObject rObj163 = new RoadObject();
		rObj163.setObjId(163);
		rObj163.setDistanceFromStartNode(38.22);
		rObj163.setType(false);

		gr.addObjectOnEdge(16, rObj161);
		gr.addObjectOnEdge(16, rObj162);
		gr.addObjectOnEdge(16, rObj163);

		RoadObject qObj171 = new RoadObject();
		qObj171.setObjId(171);
		qObj171.setDistanceFromStartNode(7.11);
		qObj171.setType(true);

		RoadObject qObj172 = new RoadObject();
		qObj172.setObjId(172);
		qObj172.setDistanceFromStartNode(17.2);
		qObj172.setType(true);

		gr.addObjectOnEdge(17, qObj171);
		gr.addObjectOnEdge(17, qObj172);

		RoadObject qObj181 = new RoadObject();
		qObj181.setObjId(181);
		qObj181.setDistanceFromStartNode(7.11);
		qObj181.setType(true);

		RoadObject qObj182 = new RoadObject();
		qObj182.setObjId(182);
		qObj182.setDistanceFromStartNode(16.31);
		qObj182.setType(true);

		RoadObject qObj183 = new RoadObject();
		qObj183.setObjId(183);
		qObj183.setDistanceFromStartNode(25.21);
		qObj183.setType(true);

		RoadObject qObj184 = new RoadObject();
		qObj184.setObjId(184);
		qObj184.setDistanceFromStartNode(33.41);
		qObj184.setType(true);

		gr.addObjectOnEdge(18, qObj181);
		gr.addObjectOnEdge(18, qObj182);
		gr.addObjectOnEdge(18, qObj183);
		gr.addObjectOnEdge(18, qObj184);

		RoadObject rObj191 = new RoadObject();
		rObj191.setObjId(191);
		rObj191.setDistanceFromStartNode(15.11);
		rObj191.setType(false);

		RoadObject rObj192 = new RoadObject();
		rObj192.setObjId(192);
		rObj192.setDistanceFromStartNode(47.11);
		rObj192.setType(false);

		gr.addObjectOnEdge(19, rObj191);
		gr.addObjectOnEdge(19, rObj192);

		RoadObject rObj200 = new RoadObject();
		rObj200.setObjId(201);
		rObj200.setDistanceFromStartNode(27.69);
		rObj200.setType(false);

		gr.addObjectOnEdge(20, rObj200);

		RoadObject rObj221 = new RoadObject();
		rObj221.setObjId(221);
		rObj221.setDistanceFromStartNode(8.1);
		rObj221.setType(false);

		RoadObject rObj222 = new RoadObject();
		rObj222.setObjId(222);
		rObj222.setDistanceFromStartNode(24.25);
		rObj222.setType(false);

		gr.addObjectOnEdge(22, rObj221);
		gr.addObjectOnEdge(22, rObj222);

		RoadObject rObj241 = new RoadObject();
		rObj241.setObjId(241);
		rObj241.setDistanceFromStartNode(5.11);
		rObj241.setType(false);

		RoadObject rObj242 = new RoadObject();
		rObj242.setObjId(242);
		rObj242.setDistanceFromStartNode(17.25);
		rObj242.setType(false);

		gr.addObjectOnEdge(24, rObj241);
		gr.addObjectOnEdge(24, rObj242);

		RoadObject rObj250 = new RoadObject();
		rObj250.setObjId(251);
		rObj250.setDistanceFromStartNode(10.25);
		rObj250.setType(false);

		gr.addObjectOnEdge(25, rObj250);

		RoadObject qObj260 = new RoadObject();
		qObj260.setObjId(261);
		qObj260.setDistanceFromStartNode(13.25);
		qObj260.setType(false);

		gr.addObjectOnEdge(26, qObj260);

		gr.printObjectsOnEdges();

		System.out.println();
		NaiveANN nAnn = new NaiveANN();
		nAnn.computeANN(gr);
		nAnn.printNearestNeighborSets();
	}

}
