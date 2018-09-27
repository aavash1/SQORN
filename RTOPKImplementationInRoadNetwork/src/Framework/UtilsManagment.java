package Framework;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class UtilsManagment {
	final String csvSplitBy = ",";
	final int  byteOrderMark = 65279;

	private HashMap<Integer, String> m_hmapCategories = new HashMap<Integer, String>();

	public boolean populatePOIMap(String csvFilename) {
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(csvFilename))) {
			while ((line = br.readLine()) != null) {
				String[] record = line.split(csvSplitBy);
				if (record.length == 2) {
					Integer newInt;
					if ((int)record[0].charAt(0) == byteOrderMark) {
						newInt = new Integer(Integer.parseInt(record[0].substring(1)));
					}
					else
					{
						newInt = new Integer(Integer.parseInt(record[0]));
					}
					m_hmapCategories.put(newInt, record[1]);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;

	}

	public void displayPOIHmap() {
		// Get a set of the entries
		Set set = m_hmapCategories.entrySet();

		// Get an iterator
		Iterator i = set.iterator();
		while (i.hasNext()) {
			Map.Entry me = (Map.Entry) i.next();
			System.out.print(me.getKey() + ": ");
			System.out.println(me.getValue());

		}
		System.out.println();
	}

	// Method to read the vertex files from the datasets
	public ArrayList<Vertex> readVertexFiles(String csvFilename) {
		String line = "";
		ArrayList<Vertex> listVer = new ArrayList<Vertex>();

		try (BufferedReader br = new BufferedReader(new FileReader(csvFilename))) {
			while ((line = br.readLine()) != null) {
				String[] record = line.split(csvSplitBy);
				if (record.length == 3) {
					Vertex v = new Vertex();
					v.setM_strNodeId(record[0]);
					v.setM_doubLongitude(Double.parseDouble(record[1]));
					v.setM_doubLatitude(Double.parseDouble(record[2]));
					listVer.add(v);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return listVer;

	}

	// Method to read the Node files from the datasets
	public ArrayList<Edge> readEdgeFile(String csvFilename) {
		String line = "";
		ArrayList<Edge> listEd = new ArrayList<Edge>();

		try (BufferedReader br = new BufferedReader(new FileReader(csvFilename))) {
			while ((line = br.readLine()) != null) {
				String[] record = line.split(csvSplitBy);
				if (record.length == 4) {
					Edge ed = new Edge();
					ed.setM_strNodeId(record[0]);
					ed.setM_strSourceId(record[1]);
					ed.setM_strDestinationId(record[2]);
					ed.setM_doubDistance(Double.parseDouble(record[3]));
					listEd.add(ed);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return listEd;

	}

	// Method to read the POI files from the datasets
	/*
	 * public ArrayList<PointOfInterest> readPOIFile(String csvFilename) { String
	 * line = ""; ArrayList<PointOfInterest> listPOI = new
	 * ArrayList<PointOfInterest>();
	 * 
	 * try (BufferedReader br = new BufferedReader(new FileReader(csvFilename))) {
	 * while ((line = br.readLine()) != null) { String[] record =
	 * line.split(csvSplitBy); if (record.length == 3) { PointOfInterest poi = new
	 * PointOfInterest(); poi.setM_strCategory(record[0]); double Plongitude =
	 * Double.parseDouble(record[1]); double Platitude =
	 * Double.parseDouble(record[2]);
	 * 
	 * poi.setM_doubLongitude(Plongitude); poi.setM_doubleLatitide(Platitude);
	 * listPOI.add(poi); }
	 * 
	 * } } catch (IOException e) { e.printStackTrace(); }
	 * 
	 * return listPOI;
	 * 
	 * }
	 */

	// Method to read the POI with category Id files from the data-set
	public ArrayList<POIwithId> readPOIFile2(String csvFilename) {
		String line = "";
		ArrayList<POIwithId> listPOI2 = new ArrayList<POIwithId>();

		try (BufferedReader br = new BufferedReader(new FileReader(csvFilename))) {
			while ((line = br.readLine()) != null) {
				String[] record = line.split(csvSplitBy);
				if (record.length == 3) {
					POIwithId POI2 = new POIwithId();
					POI2.setM_doubLatitude(Double.parseDouble(record[1]));
					POI2.setM_doubLongitude(Double.parseDouble(record[0]));
					POI2.setM_strCategoryId(record[2]);
					listPOI2.add(POI2);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return listPOI2;

	}

}
