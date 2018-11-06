package framework;

public class Node {
	private int m_intNodeId;
	private Double m_doubLongitude;
	private Double m_doubLatitude;

	public int getNodeId() {
		return m_intNodeId;
	}

	public void setNodeId(int strNodeId) {
		this.m_intNodeId = strNodeId;
	}

	public Double getLongitude() {
		return m_doubLongitude;
	}

	public void setLongitude(Double doubLongitude) {
		this.m_doubLongitude = doubLongitude;
	}

	public Double getLatitude() {
		return m_doubLatitude;
	}

	public void setLatitude(Double doubLatitude) {
		this.m_doubLatitude = doubLatitude;
	}

	@Override
	public String toString() {
		return "Node [NodeId=" + m_intNodeId + ", Longitude=" + m_doubLongitude + ", Latitude="
				+ m_doubLatitude + "]";
	}
	
}
