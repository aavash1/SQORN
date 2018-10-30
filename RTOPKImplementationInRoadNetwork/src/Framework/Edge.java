package Framework;

public class Edge {
	private int m_intEdgeId;
	private int m_intSourceId;
	private int m_intDestinationId;
	private double m_doubDistance;

	public int getEdgeId() {
		return m_intEdgeId;
	}

	public void setEdgeId(int intEdgeId) {
		if (intEdgeId < 0) {
			this.m_intEdgeId = intEdgeId;
		} else {
			System.err.println("Error!! Cannot be null");
		}
	}

	public int getM_intSourceId() {
		return m_intSourceId;
	}

	public void setM_intSourceId(int intSourceId) {
		this.m_intSourceId = intSourceId;
	}

	public int getM_intDestinationId() {
		return m_intDestinationId;
	}

	public void setM_intDestinationId(int m_intDestinationId) {
		this.m_intDestinationId = m_intDestinationId;
	}

	public double getM_doubDistance() {
		return m_doubDistance;
	}

	public void setM_doubDistance(double doubDistance) {
		this.m_doubDistance = doubDistance;
	}

	@Override
	public String toString() {
		return "Edge [m_intEdgeId=" + m_intEdgeId + ", m_intSourceId=" + m_intSourceId + ", m_intDestinationId="
				+ m_intDestinationId + ", m_doubDistance=" + m_doubDistance + "]";
	}

}
