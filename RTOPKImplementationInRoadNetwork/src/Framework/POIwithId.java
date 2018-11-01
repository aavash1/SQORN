package Framework;

public class POIwithId {
	private double m_doubLongitude;
	private double m_doubLatitude;
	private int m_intPOIId;
	private int m_intPOICategoryId;
	private int m_doubDistanceFromStartNode;
	private boolean m_boolType; //0 (False) - data object, 1 (True) - feature object, 
	private double m_doubRating; //[0, 10]
	
	public double getM_doubLongitude() {
		return m_doubLongitude;
	}

	public void setM_doubLongitude(double doubLongitude) {
		this.m_doubLongitude = doubLongitude;
	}

	public double getM_doubLatitude() {
		return m_doubLatitude;
	}

	public void setM_doubLatitude(double doubLatitude) {
		this.m_doubLatitude = doubLatitude;
	}

	public int getM_intPOICategoryId() {
		return m_intPOICategoryId;
	}

	public void setM_intPOICategoryId(int intPOICategoryId) {
		this.m_intPOICategoryId = intPOICategoryId;
	}
	public int getM_intPOIID() {
		return m_intPOIId;
	}

	public void setM_intPOIID(int intPOIID) {
		this.m_intPOIId = intPOIID;
	}

	public int getM_doubDistanceFromStartNode() {
		return m_doubDistanceFromStartNode;
	}

	public void setM_doubDistanceFromStartNode(int m_doubDistanceFromStartNode) {
		this.m_doubDistanceFromStartNode = m_doubDistanceFromStartNode;
	}

}
