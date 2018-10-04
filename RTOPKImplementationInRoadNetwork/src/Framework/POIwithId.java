package Framework;

public class POIwithId {
	private double m_doubLongitude;
	private double m_doubLatitude;
	private String m_strCategoryId;
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

	public String getM_strCategoryId() {
		return m_strCategoryId;
	}

	public void setM_strCategoryId(String strCategoryId) {
		this.m_strCategoryId = strCategoryId;
	}

}
