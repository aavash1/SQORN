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
	final int byteOrderMark = 65279;

	private int poiID;
	private HashMap<Integer, String> m_hmapCategoriesName = new HashMap<Integer, String>(); // key is category Id and
																							// value is category name
	private HashMap<Integer, String> m_hmapCategoriesType = new HashMap<Integer, String>(); // key is category Id and
																							// value is category type

	public String returnCategoryName(int category_Id) {

		if ((category_Id < 0) || (!m_hmapCategoriesName.containsKey(category_Id))) {
			System.err.println("Cannot Pass null value");
			return null;

		}

		else
			return m_hmapCategoriesName.get(category_Id);

	}

	public int returnCategoryId(String categoryName) {
		int key = -1;
		String values = "null";

		if ((categoryName != null) && (m_hmapCategoriesName.containsValue(categoryName))) {
			for (Map.Entry entry : m_hmapCategoriesName.entrySet()) {
				if (values.equals(entry.getValue())) {
					key = (int) entry.getKey();
					break;
				}
			}

		}
		return key;
	}

	public boolean returnCategoryType(int category_typeid) {
		if (m_hmapCategoriesType.get(category_typeid).equals("1")) {
			return true;

		} else
			return false;

	}

	public boolean populatePOIMap(String csvFilename) {
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(csvFilename))) {
			while ((line = br.readLine()) != null) {
				String[] record = line.split(csvSplitBy);
				if (record.length == 2) {
					Integer newInt;
					if ((int) record[0].charAt(0) == byteOrderMark) {
						newInt = new Integer(Integer.parseInt(record[0].substring(1)));
					} else {
						newInt = new Integer(Integer.parseInt(record[0]));
					}
					m_hmapCategoriesName.put(newInt, record[1]);
					m_hmapCategoriesType.put(newInt, record[2]);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;

	}

	public void displayPOIHmap() {
		// Get a set of the entries
		Set set = m_hmapCategoriesName.entrySet();
		Set set1 = m_hmapCategoriesType.entrySet();

		// Get an iterator
		Iterator i = set.iterator();
		while (i.hasNext()) {
			Map.Entry me = (Map.Entry) i.next();
			System.out.print(me.getKey() + ": ");
			System.out.println(me.getValue());

		}
		System.out.println();

		Iterator i1 = set1.iterator();
		while (i1.hasNext()) {
			Map.Entry me1 = (Map.Entry) i1.next();
			System.out.print(me1.getKey() + ": ");
			System.out.println(me1.getValue());

		}
		System.out.println();
	}

	// Method to read the vertex files from the datasets
	public ArrayList<Node> readVertexFiles(String csvFilename) {
		String line = "";
		ArrayList<Node> listVer = new ArrayList<Node>();

		try (BufferedReader br = new BufferedReader(new FileReader(csvFilename))) {
			while ((line = br.readLine()) != null) {
				String[] record = line.split(csvSplitBy);
				if (record.length == 3) {
					Node v = new Node();
					v.setNodeId(Integer.parseInt(record[0]));
					v.setLongitude(Double.parseDouble(record[1]));
					v.setLatitude(Double.parseDouble(record[2]));
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
					ed.setEdgeId(Integer.parseInt(record[0]));
					ed.setStartNodeId(Integer.parseInt(record[1]));

					ed.setEndNodeId(Integer.parseInt(record[2]));
					ed.setLength(Double.parseDouble(record[3]));

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
	public ArrayList<Poi> readPOIFile2(String csvFilename) {
		String line = "";
		ArrayList<Poi> listPOI2 = new ArrayList<Poi>();

		try (BufferedReader br = new BufferedReader(new FileReader(csvFilename))) {
			while ((line = br.readLine()) != null) {
				String[] record = line.split(csvSplitBy);
				if (record.length == 3) {
					Poi POI2 = new Poi();
					POI2.setLongitude(Double.parseDouble(record[0]));
					POI2.setLatitude(Double.parseDouble(record[1]));
					POI2.setPoiCategoryId(Integer.parseInt(record[2]));
					poiID++;
					POI2.setPoiId(poiID);
					//
					listPOI2.add(POI2);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return listPOI2;

	}

	// Method to Read Merged Point of Interest with Original.

	// For time being it is "void", but later need to determine the return type
	public Graph readMergedPOI(String csvFilename) {
		Graph graph = new Graph();
		Poi poi = new Poi();
		String line = "";

		try (BufferedReader br = new BufferedReader(new FileReader(csvFilename))) {
			while ((line = br.readLine()) != null) {
				String[] record = line.split(",");
				if (record.length == 4) {
					if (record[4].contains(".")) {
						System.out.println("Cannot be a double value");

					} else {
						int num_of_POI = Integer.parseInt(record[4]);
						graph.addEdge(Integer.parseInt(record[0]), Integer.parseInt(record[1]),
								Double.parseDouble(record[3]));
						if (num_of_POI == 0) {
							br.readLine();

						} else {
							br.readLine();
							for (int i = 0; i < (num_of_POI * 2); i++) {
								poi.setPoiCategoryId(Integer.parseInt(record[i]));
								poi.setDistanceFromStartNode(Double.parseDouble(record[i + 1]));
							}
						}
					}
//This code is not working I cannot debug it well. Please check the logic and give me feedbacks! I will do it later with another machine.
				} else {
					System.out.println("line has 2 or more than 4 numbers");
					// add POI to graph

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return graph;

	}

}
