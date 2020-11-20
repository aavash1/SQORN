package visualization;

import java.util.ArrayList;
import java.util.Random;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import framework.Graph;
import framework.UtilsManagment;
import road_network.ManualRN5;

public class TestGraphDraw {

	public static void main(String[] args) {

		double scaleFactor = 6;
		Random rand = new Random();
		ArrayList<Vector2D> xyCoordinates = UtilsManagment.getLocationCoordinate(1, 20);
		Graph gr = ManualRN5.getGraph();

		for (int i = 0; i < gr.getEdgesWithInfo().size(); i++) {
			double longit1 = xyCoordinates.get(rand.nextInt(xyCoordinates.size())).getX();
			double latid1 = xyCoordinates.get(rand.nextInt(xyCoordinates.size())).getY();
			double longit2 = xyCoordinates.get(rand.nextInt(xyCoordinates.size())).getX();
			double latid2 = xyCoordinates.get(rand.nextInt(xyCoordinates.size())).getY();
			int st = gr.getEdgesWithInfo().get(i).getStartNodeId();
			int dst = gr.getEdgesWithInfo().get(i).getEndNodeId();
			gr.getNode(st).setLongitude(longit1*scaleFactor);
			gr.getNode(st).setLatitude(latid1*scaleFactor);
			gr.getNode(dst).setLongitude(longit2*scaleFactor);
			gr.getNode(dst).setLatitude(latid2*scaleFactor);

		}
		// Graph roadNetwork1 = new Graph();
//		for (int i = 0; i < 20; i++) {
//			int st = rand.nextInt();
//			int dst = rand.nextInt();
//
//			double dist = rand.nextDouble();
//			roadNetwork1.addEdge(st, dst, dist);
//			double longit1 = xyCoordinates.get(rand.nextInt(xyCoordinates.size())).getX();
//			double latid1 = xyCoordinates.get(rand.nextInt(xyCoordinates.size())).getY();
//			double longit2 = xyCoordinates.get(rand.nextInt(xyCoordinates.size())).getX();
//			double latid2 = xyCoordinates.get(rand.nextInt(xyCoordinates.size())).getY();
//
//			roadNetwork1.getNode(st).setLongitude(longit1 * scaleFactor);
//			roadNetwork1.getNode(st).setLatitude(latid1 * scaleFactor);
//			roadNetwork1.getNode(dst).setLongitude(longit2 * scaleFactor);
//			roadNetwork1.getNode(dst).setLatitude(latid2 * scaleFactor);
//
//		}

		// roadNetwork1.addEdge(0, 1, 2.24);
		// roadNetwork1.addEdge(0, 4, 5.0);
		// roadNetwork1.addEdge(1, 2, 3.0);
		// roadNetwork1.addEdge(1, 3, 5.0);
		// roadNetwork1.addEdge(1, 4, 5.83);
		// roadNetwork1.addEdge(2, 3, 3.16);
		// roadNetwork1.addEdge(3, 4, 2.24);
		// roadNetwork1.printGraph();
		// System.out.println();
		//
		// roadNetwork1.getNode(0).setLatitude(4.0 * scaleFactor);
		// roadNetwork1.getNode(0).setLongitude(5.0 * scaleFactor);
		// roadNetwork1.getNode(1).setLatitude(6.0 * scaleFactor);
		// roadNetwork1.getNode(1).setLongitude(6.0 * scaleFactor);
		// ;
		// roadNetwork1.getNode(2).setLatitude(6.0 * scaleFactor);
		// roadNetwork1.getNode(2).setLongitude(9.0 * scaleFactor);
		// ;
		// roadNetwork1.getNode(3).setLatitude(3.0 * scaleFactor);
		// roadNetwork1.getNode(3).setLongitude(10.0 * scaleFactor);
		// ;
		// roadNetwork1.getNode(4).setLatitude(1.0 * scaleFactor);
		// roadNetwork1.getNode(4).setLongitude(9.0 * scaleFactor);
		// ;
		// roadNetwork1.printNodesInfo();
		// roadNetwork1.addPoi(31, 0, 1, 1.3);
		// roadNetwork1.addPoi(32, 0, 1, 1.9);
		// roadNetwork1.addPoi(33, 0, 4, 2.2);

		GraphDraw graphDraw = new GraphDraw("Test Road Network");
		graphDraw.setGraph(gr);

		graphDraw.setSize(1000, 1000);
		graphDraw.setVisible(true);

	}
}
