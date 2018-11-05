package Framework;

public class Poi {
	private int m_intPoiId;
	private double m_doubLongitude;
	private double m_doubLatitude;	
	private int m_intPoiCategoryId;
	private double m_doubDistanceFromStartNode;
	
	private boolean m_boolType; // 0 (False) - data object, 1 (True) - feature object,
	private double m_doubRating; // [0, 10]

	public double getDistanceFromStartNode() {
		return m_doubDistanceFromStartNode;
	}

	public void setDistanceFromStartNode(double m_doubDistanceFromStartNode) {
		this.m_doubDistanceFromStartNode = m_doubDistanceFromStartNode;
	}

	public double getLongitude() {
		return m_doubLongitude;
	}

	public void setLongitude(double doubLongitude) {
		this.m_doubLongitude = doubLongitude;
	}

	public double getLatitude() {
		return m_doubLatitude;
	}

	public void setLatitude(double doubLatitude) {
		this.m_doubLatitude = doubLatitude;
	}

	public int getPoiCategoryId() {
		return m_intPoiCategoryId;
	}

	public void setPoiCategoryId(int intPOICategoryId) {
		this.m_intPoiCategoryId = intPOICategoryId;
	}

	public int getPoiId() {
		return m_intPoiId;
	}

	public void setPoiId(int intPOIID) {
		this.m_intPoiId = intPOIID;
	}

}
