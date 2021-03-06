package visualization;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Point2D;

import javax.swing.JFrame;

import framework.Graph;
import framework.Graph;
import framework.Node;
import framework.UtilsManagment;

public class GraphDraw extends JFrame {

	private final static Color COLOR_EDGE_LINE = Color.black;
	private final static Color COLOR_NODE_CIRCLE = Color.blue;
	private final static Color COLOR_NODE_ID = Color.red;

	private int nodeCircleWidth;
	private int nodeCircleHeight;

	Graph graph;

	//if Node/Edge/Poi added to existing and visualized graph, then need to repaint using local method: this.repaint();
	public GraphDraw() { // Constructor
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		nodeCircleWidth = 5;
		nodeCircleHeight = 5;
	}

	public GraphDraw(String name) { // Construct with label
		this.setTitle(name);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		nodeCircleWidth = 1;
		nodeCircleHeight = 1;
	}

	public void setGraph(Graph g) {
		this.graph = g;
	}		

	public void paint(Graphics g) { // draw the nodes and edges
		FontMetrics f = g.getFontMetrics();
		int nodeHeight = Math.max(nodeCircleHeight, f.getHeight());

		g.setColor(COLOR_EDGE_LINE);
		int x1, y1, x2, y2;
		for (Integer headNodeId : graph.getAdjancencyMap().keySet()) {
			for (Integer endNodeId : graph.getAdjNodeIds(headNodeId)) {
				x1 = UtilsManagment.convertDoubleToInteger(graph.getNode((int) headNodeId).getLatitude());
				y1 = UtilsManagment.convertDoubleToInteger(graph.getNode((int) headNodeId).getLongitude());
				x2 = UtilsManagment.convertDoubleToInteger(graph.getNode((int) endNodeId).getLatitude());
				y2 = UtilsManagment.convertDoubleToInteger(graph.getNode((int) endNodeId).getLongitude());
				g.drawLine(x1, y1, x2, y2);
			}
		}

		for (Node n : graph.getNodesWithInfo()) {
			String str = "5";
			int nodeWidth = Math.max(nodeCircleWidth, f.stringWidth(str) + nodeCircleWidth / 2);
			g.setColor(COLOR_NODE_CIRCLE);
			g.fillOval((int)(n.getLatitude() - nodeWidth / 2), (int)(n.getLongitude() - nodeHeight / 2), nodeWidth, nodeHeight);
			g.setColor(COLOR_NODE_ID);
			g.drawOval((int)(n.getLatitude() - nodeWidth / 2), (int)(n.getLongitude() - nodeHeight / 2), nodeWidth, nodeHeight);
			int x,y;
			x = UtilsManagment.convertDoubleToInteger(n.getLatitude() - f.stringWidth(Integer.toString(n.getNodeId())) / 2);
			y = UtilsManagment.convertDoubleToInteger(n.getLongitude() + f.getHeight() / 2);
			g.drawString(Integer.toString(n.getNodeId()), x, y);
		}
	}
	
	
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

}
