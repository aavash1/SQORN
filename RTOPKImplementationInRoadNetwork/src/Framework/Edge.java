package Framework;

public class Edge {
	private String m_strEdgeId;
	private String m_strSourceId;
	private String m_strDestinationId;
	private double m_doubDistance;

	public String getEdgeId() {
		return m_strEdgeId;
	}

	public void setEdgeId(String strEdgeId) {
		if (strEdgeId != null) {
			this.m_strEdgeId = strEdgeId;
		}
		else {
			System.err.println("Error!! Cannot be null");
		}
	}

	public String getM_strSourceId() {
		return m_strSourceId;
	}

	public void setM_strSourceId(String strSourceId) {
		this.m_strSourceId = strSourceId;
	}

	public String getM_strDestinationId() {
		return m_strDestinationId;
	}

	public void setM_strDestinationId(String strDestinationId) {
		this.m_strDestinationId = strDestinationId;
	}

	public double getM_doubDistance() {
		return m_doubDistance;
	}

	public void setM_doubDistance(double doubDistance) {
		this.m_doubDistance = doubDistance;
	}

	@Override
	public String toString() {
		return "Edge [m_strEdgeId=" + m_strEdgeId + ", m_strSourceId=" + m_strSourceId + ", m_strDestinationId="
				+ m_strDestinationId + ", m_doubDistance=" + m_doubDistance + "]";
	}
	
	

}
