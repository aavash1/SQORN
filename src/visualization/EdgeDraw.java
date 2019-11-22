package visualization;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JFrame;

import framework.RoadObject;

public class EdgeDraw extends JFrame {

	private final static Color COLOR_EDGE_LINE = Color.black;
	private final static Color COLOR_OBJECT_CIRCLE = Color.blue;
	private final static Color COLOR_OBJECT_DIST = Color.red;

	private int m_objCircleWidth;
	private int m_objCircleHeight;

	private int m_distFromTop = 100;
	private int m_distFromLeft = 0;
	private int m_scaleFactor = 10;

	private double m_edgeLength;
	private ArrayList<Double> m_objsDistFromStartNode;
	private ArrayList<RoadObject> m_objs;

	public EdgeDraw(String name) { // Construct with label
		this.setTitle(name);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m_objCircleWidth = 5;
		m_objCircleHeight = 5;
	}

	public void drawEdgeLine(double edgeLength, ArrayList<Double> objsDistFromStartNode) {
		m_edgeLength = edgeLength;
		m_objsDistFromStartNode = objsDistFromStartNode;
	}

	public void drawEdgeLineFullInfo(double edgeLength, ArrayList<RoadObject> objs) {
		m_edgeLength = edgeLength;
		m_objs = objs;
	}

	public void paint(Graphics g) { // draw the nodes and edges
		FontMetrics f = g.getFontMetrics();

		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(COLOR_EDGE_LINE);
		double x1, y1, x2, y2;
		x1 = m_distFromLeft;
		y1 = m_distFromTop;

		x2 = m_edgeLength * m_scaleFactor;
		y2 = m_distFromTop;

		Shape edgeLine = new Line2D.Double(x1, y1, x2, y2);
		g2.draw(edgeLine);
		// g.drawLine(x1, y1, x2, y2);

		String str = "5";
		// int objCircleHeight = Math.max(m_objCircleHeight, f.getHeight());
		// int objCircleWidth = Math.max(m_objCircleWidth, f.stringWidth(str) +
		// m_objCircleWidth / 2);
		int objCircleHeight = m_objCircleHeight;
		int objCircleWidth = m_objCircleWidth;

		for (Double objDist : m_objsDistFromStartNode) {

			g2.setColor(COLOR_OBJECT_CIRCLE);
			// X of upper-left corner, Y of upper-left corner, width, height
			double dVal = objDist * m_scaleFactor - m_objCircleWidth / 2;
			int intVal = (int) dVal;
			Double distVal = objDist;
			distVal = Math.round(distVal * 100.0) / 100.0;

			Ellipse2D.Double obj = new Ellipse2D.Double(objDist * m_scaleFactor - m_objCircleWidth / 2,
					m_distFromTop - m_objCircleHeight / 2, m_objCircleWidth, m_objCircleHeight);
			g2.draw(obj);
			g2.setColor(COLOR_OBJECT_DIST);
			g2.drawString(Double.toString(distVal), intVal + 5, m_distFromTop - 10);
		}
		// begining of edge
		g2.fillOval((int) (m_scaleFactor - objCircleWidth / 2), (int) (m_distFromTop - objCircleHeight / 2),
				objCircleWidth, objCircleHeight);
		// end of edge
		g2.fillOval((int) (m_edgeLength * m_scaleFactor - objCircleWidth / 2),
				(int) (m_distFromTop - objCircleHeight / 2), objCircleWidth, objCircleHeight);

	}

}
