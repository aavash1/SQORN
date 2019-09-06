package framework;

public class Edge {
	private int m_intEdgeId;
	private int m_intStartNodeId;
	private int m_intEndNodeId;
	private double m_doubLength;

	public int getEdgeId() {
		return m_intEdgeId;
	}

	public void setEdgeId(int intEdgeId) {
		if (intEdgeId < 0) {
			System.err.println("Error!! Cannot be null");
		} else {
			this.m_intEdgeId = intEdgeId;
		}
	}

	public int getStartNodeId() {
		return m_intStartNodeId;
	}

	public void setStartNodeId(int intSourceId) {
		this.m_intStartNodeId = intSourceId;
	}

	public int getEndNodeId() {
		return m_intEndNodeId;
	}

	public void setEndNodeId(int m_intDestinationId) {
		this.m_intEndNodeId = m_intDestinationId;
	}

	public double getLength() {
		return m_doubLength;
	}

	public void setLength(double doubDistance) {
		this.m_doubLength = doubDistance;
	}

	@Override
	public String toString() {
		return "Edge [EdgeId=" + m_intEdgeId + ", Start Node=" + m_intStartNodeId + ", End Node="
				+ m_intEndNodeId + ", Length=" + m_doubLength + "]";
	}

}