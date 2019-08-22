package framework;

public class RandomObject {
	private int m_intObjectId; //The id of the object
	private double m_doubleDistanceFromStartNode; //Distance from the start node.
	private boolean m_boolType; // 0 (False) - data object, 1 (True) - feature object,
	
	public double getDistanceFromStartNode() {
		return m_doubleDistanceFromStartNode;
	}

	public void setDistanceFromStartNode(double doubDistanceFromStartNode) {
		this.m_doubleDistanceFromStartNode = doubDistanceFromStartNode;
	}
	
	public int getObjectId() {
		return m_intObjectId;
	}
	
	public void setObjectId(int objectId) {
		this.m_intObjectId=objectId;
	}
	
	public boolean getType() {
		return m_boolType;
	}

	public void setType(boolean boolType) {
		this.m_boolType = boolType;
	}
	
	@Override
	public String toString() {
		return "Object [ObjectID=" + m_intObjectId + " ObjectType= " + m_boolType + "]";
	}
	
	/*private int m_intPoiId;
	private double m_doubLongitude;
	private double m_doubLatitude;
	private int m_intPoiCategoryId;
	private double m_doubDistanceFromStartNode;
	private double m_doubRating; // [0, 10]

	public double getRating() {
		return m_doubRating;
	}

	public void setRating(double doubRating) {
		if ((doubRating < 0) || (doubRating > 10)) {
			System.err.println("Assigned rating should be between 0 and 10");
		} else {
			this.m_doubRating = doubRating;
		}
	}*/

	

/*	public double getDistanceFromStartNode() {
		return m_doubDistanceFromStartNode;
	}

	public void setDistanceFromStartNode(double doubDistanceFromStartNode) {
		this.m_doubDistanceFromStartNode = doubDistanceFromStartNode;
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
	


	@Override
	public String toString() {
		return "POI [PoiId=" + m_intPoiId + ", Latitude= " + m_doubLatitude + ", Longitude= " + m_doubLongitude
				+ ", CategoryId= " + m_intPoiCategoryId + ", Type= " + m_boolType + ", Rating=" + m_doubRating+"]";
	}
	*/

}
