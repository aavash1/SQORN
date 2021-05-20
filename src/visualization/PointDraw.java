package visualization;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JFrame;

//import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import framework.Graph;
import framework.Graph;
import framework.Node;
import framework.UtilsManagment;

public class PointDraw extends JFrame {

	// private final static Color COLOR_EDGE_LINE = Color.black;
	private final static Color COLOR_NODE_CIRCLE = Color.black;
	private final static Color COLOR_NODE_ID = Color.white;

	private int nodeCircleWidth;
	private int nodeCircleHeight;

	private static String str = "2";

	// Graph graph;

	//ArrayList<Vector2D> points;

	// if Node/Edge/Poi added to existing and visualized graph, then need to repaint
	// using local method: this.repaint();
	public PointDraw() { // Constructor
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		nodeCircleWidth = 1;
		nodeCircleHeight = 1;
	}

	public PointDraw(String name) { // Construct with label
		this.setTitle(name);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		nodeCircleWidth = 10;
		nodeCircleHeight = 10;
	}

	// public void setGraph(Graph g) {
	// this.graph = g;
	// }

//	public void setPoints(ArrayList<Vector2D> points) {
//		this.points = points;
//	}

//	public void paint(Graphics g) { // draw the nodes and edges
//		FontMetrics f = g.getFontMetrics();
//		// double scaleFactor=1.8245;
////		int nodeWidth = Math.max(nodeCircleWidth, f.stringWidth(str) + nodeCircleWidth / 2);
////		int nodeHeight = Math.max(nodeCircleHeight, f.getHeight());
////		g.setColor(COLOR_NODE_CIRCLE);
////		g.fillOval((int) (250 - nodeWidth / 2), (int) (250 - nodeHeight / 2), nodeWidth,
////				nodeHeight);
////		g.setColor(COLOR_NODE_ID);
//
//		for (Vector2D point : points) {
//			int nodeHeight = Math.max(nodeCircleHeight, f.getHeight());
//			int nodeWidth = Math.max(nodeCircleWidth, f.stringWidth(str) + nodeCircleWidth / 2);
//			g.setColor(COLOR_NODE_CIRCLE);
//			//g.fillOval((int) (point.getX() - nodeWidth / 2), (int) (point.getY() - nodeHeight / 2), nodeWidth,
//				//	nodeHeight);
//
//			
//			g.drawOval((int) (point.getX() - nodeWidth / 30), (int) (point.getY() - nodeHeight / 30), nodeWidth,
//					nodeHeight);
//
//			int x, y;
//			x = UtilsManagment.convertDoubleToInteger(point.getX() - f.stringWidth("."));
//			y = UtilsManagment.convertDoubleToInteger(point.getY() + f.getHeight());
//			//g.drawString(".", x, y);
//		}
//
//	}

	// These 5 methods might be possible in future
	private double calculateDistance(Node n1, Node n2) {
		return Point2D.distance(n1.getLatitude(), n1.getLongitude(), n2.getLatitude(), n2.getLongitude());
	}

	private boolean isTriangle(int a, int b, int c) {

		if ((a + b < c) || (a + c < b) || (b + c < a))
			return false;

		return true;
	}

	private boolean isTriangle(double a, double b, double c) {

		if ((a + b < c) || (a + c < b) || (b + c < a))
			return false;

		return true;
	}

	private boolean isTriangle(Node n1, Node n2, Node n3) {

		if ((calculateDistance(n1, n2) + calculateDistance(n1, n3) < calculateDistance(n2, n3))
				|| (calculateDistance(n1, n3) + calculateDistance(n2, n3) < calculateDistance(n1, n2))
				|| (calculateDistance(n1, n2) + calculateDistance(n2, n3) < calculateDistance(n1, n3)))
			return false;

		return true;
	}

	private Node findPossibleThirdNodeCoordinates(Node n1, Node n2) {

		Node n3 = null;
		for (int i = 0; i < Math.max(nodeCircleWidth, nodeCircleHeight); i++) {
			for (int j = 0; j < Math.max(nodeCircleWidth, nodeCircleHeight); j++) {
				n3.setLatitude((double) i);
				n3.setLongitude((double) j);
				if (isTriangle(n1, n2, n3)) {
					return n3;
				}
			}
		}
		return n3;
	}

	private Node findPossibleFourthNodeCoordinates(Node n1, Node n2) {

		Node n3 = null;
		for (int i = 0; i < Math.max(nodeCircleWidth, nodeCircleHeight); i++) {
			for (int j = 0; j < Math.max(nodeCircleWidth, nodeCircleHeight); j++) {
				n3.setLatitude((double) i);
				n3.setLongitude((double) j);
				if (isTriangle(n1, n2, n3)) {
					return n3;
				}
			}
		}
		return n3;
	}

}
