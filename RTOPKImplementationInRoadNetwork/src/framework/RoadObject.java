package framework;

import java.util.Comparator;

public class RoadObject {
	private int m_intObjId;
	private double m_doubLongitude;
	private double m_doubLatitude;
	private int m_intObjCategoryId;
	private double m_doubDistanceFromStartNode;

	private boolean m_boolType; // 0 (False) - data object, 1 (True) - feature object,
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
	}

	public boolean getType() {
		return m_boolType;
	}

	public void setType(boolean boolType) {
		this.m_boolType = boolType;
	}

	public double getDistanceFromStartNode() {
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

	public int getObjCategoryId() {
		return m_intObjCategoryId;
	}

	public void setObjCategoryId(int intPOICategoryId) {
		this.m_intObjCategoryId = intPOICategoryId;
	}

	public int getObjectId() {
		return m_intObjId;
	}

	public void setObjId(int intPOIID) {
		this.m_intObjId = intPOIID;
	}

	// @Override
	// public String toString() {
	// return "Road Object [ObjId=" + m_intObjId + ", Latitude= " + m_doubLatitude +
	// ", Longitude= " + m_doubLongitude
	// + ", CategoryId= " + m_intObjCategoryId + ", Type= " + m_boolType + ",
	// Distance From SN= " + m_doubDistanceFromStartNode +"]";
	// }

	@Override
	public String toString() {
		return "Road Object [ObjId=" + m_intObjId + ", Type= " + m_boolType + ", Dist From SN= "
				+ m_doubDistanceFromStartNode + "]";
	}

	public static Comparator<RoadObject> DistanceComparator = new Comparator<RoadObject>() {

		public int compare(RoadObject obj1, RoadObject obj2) {
			double distanceDiff = obj1.getDistanceFromStartNode() - obj2.getDistanceFromStartNode();
			return (int) Math.round(distanceDiff);
		}
	};

	public static Comparator<RoadObject> ObjIdComparator = new Comparator<RoadObject>() {

		public int compare(RoadObject obj1, RoadObject obj2) {
			return obj1.getObjectId() - obj2.getObjectId();
		}
	};

	public static Comparator<RoadObject> RatingComparator = new Comparator<RoadObject>() {

		public int compare(RoadObject obj1, RoadObject obj2) {
			double ratingDiff = obj1.getRating() - obj2.getRating();
			return (int) Math.round(ratingDiff);
		}
	};

}
