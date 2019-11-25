package framework;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.MultiValuedMap;

import com.opencsv.CSVWriter;

import algorithm.ANNClustered;
import algorithm.ClusteringNodes;

public class UtilsManagment {
	final static String csvSplitBy = ",";
	final int byteOrderMark = 65279;

	// private int poiID;
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

	public boolean populateObjMap(String csvFilename) {
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

	public void displayObjHmap() {
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
	public static ArrayList<Node> readNodeFile(String csvFilename) {
		String line = "";
		ArrayList<Node> listOfNodes = new ArrayList<Node>();
		boolean removedBOM = false;
		try (BufferedReader br = new BufferedReader(new FileReader(csvFilename))) {
			while ((line = br.readLine()) != null) {
				String[] record = line.split(csvSplitBy);

				if (record.length == 3) {
					if (!removedBOM && record[0] != "0") {

						record[0] = String.valueOf(0);
						removedBOM = true;

					}
					Node v = new Node();
					v.setNodeId(Integer.parseInt(record[0]));
					v.setLongitude(Double.parseDouble(record[1]));
					v.setLatitude(Double.parseDouble(record[2]));
					listOfNodes.add(v);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return listOfNodes;

	}

	// Method to read the vertex files from the datasets
	public static boolean readNodeFile(Graph graph, String csvFilename) {
		String line = "";
		ArrayList<Node> listOfNodes = new ArrayList<Node>();
		boolean removedBOM = false;
		try (BufferedReader br = new BufferedReader(new FileReader(csvFilename))) {
			while ((line = br.readLine()) != null) {
				String[] record = line.split(csvSplitBy);

				if (record.length == 3) {
					if (!removedBOM && record[0] != "0") {

						record[0] = String.valueOf(0);
						removedBOM = true;

					}
					Node v = new Node();
					v.setNodeId(Integer.parseInt(record[0]));
					v.setLongitude(Double.parseDouble(record[1]));
					v.setLatitude(Double.parseDouble(record[2]));
					listOfNodes.add(v);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		graph.setNodesWithInfo(listOfNodes);
		return true;

	}

	// Method to read the Edge files from the datasets
	public static ArrayList<Edge> readEdgeFile(String csvFilename) {
		String line = "";
		ArrayList<Edge> listEd = new ArrayList<Edge>();
		boolean removedBOM = false;
		try (BufferedReader br = new BufferedReader(new FileReader(csvFilename))) {
			while ((line = br.readLine()) != null) {
				String[] record = line.split(csvSplitBy);

				if (record.length == 4) {
					if (!removedBOM && record[0] != "0") {

						record[0] = String.valueOf(0);
						removedBOM = true;

					}
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

	public static ArrayList<Double> readEdgeFileReturnListOfEdgeLength(String csvFilename) {
		String line = "";
		ArrayList<Double> listEdgeLength = new ArrayList<Double>();
		boolean removedBOM = false;
		try (BufferedReader br = new BufferedReader(new FileReader(csvFilename))) {
			while ((line = br.readLine()) != null) {
				String[] record = line.split(csvSplitBy);

				if (record.length == 4) {
					if (!removedBOM && record[0] != "0") {

						record[0] = String.valueOf(0);
						removedBOM = true;

					}
					// Edge ed = new Edge();
					// ed.setEdgeId(Integer.parseInt(record[0]));
					// ed.setStartNodeId(Integer.parseInt(record[1]));

					// ed.setEndNodeId(Integer.parseInt(record[2]));
					// ed.setLength(Double.parseDouble(record[3]));

					listEdgeLength.add(Double.parseDouble(record[3]));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return listEdgeLength;

	}

	public static Graph readEdgeFileReturnGraph(String csvFilename) {
		Graph graph = new Graph();

		String line = "";
		boolean removedBOM = false;
		try (BufferedReader br = new BufferedReader(new FileReader(csvFilename))) {
			while ((line = br.readLine()) != null) {
				String[] record = line.split(csvSplitBy);
				if (record.length == 4) {

					if (!removedBOM && record[0] != "0") {

						record[0] = String.valueOf(0);
						removedBOM = true;

					}
					graph.addEdge(Integer.parseInt(record[0]), Integer.parseInt(record[1]), Integer.parseInt(record[2]),
							Double.parseDouble(record[3]));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return graph;

	}

	public static boolean readEdgeFile(Graph graph, String csvFilename) {
		String line = "";
		ArrayList<Edge> listEd = new ArrayList<Edge>();
		boolean removedBOM = false;
		try (BufferedReader br = new BufferedReader(new FileReader(csvFilename))) {
			while ((line = br.readLine()) != null) {
				String[] record = line.split(csvSplitBy);
				if (record.length == 4) {
					if (!removedBOM && record[0] != "0") {

						record[0] = String.valueOf(0);
						removedBOM = true;

					}
					Edge ed = new Edge();
					ed.setEdgeId(Integer.parseInt(record[0]));
					ed.setStartNodeId(Integer.parseInt(record[1]));
					ed.setEndNodeId(Integer.parseInt(record[2]));
					ed.setLength(Double.parseDouble(record[3]));

					listEd.add(ed);
					graph.addEdge(Integer.parseInt(record[0]), Integer.parseInt(record[1]), Integer.parseInt(record[2]),
							Double.parseDouble(record[3]));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		graph.setEdgeWithInfo(listEd);
		return true;

	}

	public static Graph readMergedObjectFile(String fileName) {
		Graph graph = new Graph();
		String line = "";
		int startNode = 0, endNode = 0;
		// int objId = 0; // currently not used
		double edge_length;
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			while ((line = br.readLine()) != null) {
				String[] record = line.split(" ");
				if (record.length == 4) {
					if (!isInteger(record[3])) {
						// System.out.println("Line has 4 numbers and it ends with double");
						// objId++;
						graph.addObjectOnMap(Integer.parseInt(record[0]), startNode, endNode,
								Double.parseDouble(record[1]));
						// objId++;
						graph.addObjectOnMap(Integer.parseInt(record[2]), startNode, endNode,
								Double.parseDouble(record[3]));

					} else {
						// System.out.println("Line has 4 numbers and it ends with integer");
						startNode = Integer.parseInt(record[0]);
						endNode = Integer.parseInt(record[1]);
						edge_length = Double.parseDouble(record[2]);
						graph.addEdge(startNode, endNode, edge_length);

					}
				} else {
					// System.out.println("line has 2 or more than 4 numbers");
					for (int i = 0; i < record.length - 1; i += 2) {
						// objId++;
						graph.addObjectOnMap(Integer.parseInt(record[i]), startNode, endNode,
								Double.parseDouble(record[i + 1]));
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return graph;
	}

	// method to create the RoadObjectFile from the previously Generated Objects
	public static void writeRoadObjsOnEdgeFile(Map<Integer, ArrayList<RoadObject>> roadObjectsOnEdge,
			String datasetName, String roadObjsOnEdgeCSVFile) {

		try {
			FileWriter outputFile = new FileWriter(roadObjsOnEdgeCSVFile);
			for (Integer edgeId : roadObjectsOnEdge.keySet()) {
				for (int i = 0; i < roadObjectsOnEdge.get(edgeId).size(); i++) {
					outputFile.write(Integer.toString(edgeId) + ","
							+ Integer.toString(roadObjectsOnEdge.get(edgeId).get(i).getObjectId()) + ","
							+ String.valueOf((roadObjectsOnEdge.get(edgeId).get(i).getType())) + ","
							+ Double.toString(roadObjectsOnEdge.get(edgeId).get(i).getDistanceFromStartNode()));
					outputFile.write(System.lineSeparator());
				}

			}

			//System.out.println("File: " + roadObjsOnEdgeCSVFile + " is written Successfully");
			outputFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeANNQueriesResult(ANNClustered annClustered, String datasetName) {

		String annQueriesResultCSVFile = "QueryResults/annQueriesResult_" + datasetName + "_" + getNormalDateTime()
				+ ".csv";
		try {
			FileWriter outputFile = new FileWriter(annQueriesResultCSVFile);
			// Using CSV Functions to write the fine with comma separated Values.
			CSVWriter writer = new CSVWriter(outputFile, ',', CSVWriter.NO_QUOTE_CHARACTER,
					CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

			// Edge ID, Query Obj ID, Edge ID, Data Obj ID -for next version
			// Query Obj ID, DataObj ID - current version
			for (Integer qObj : annClustered.getNearestNeighborSets().keySet()) {
				writer.writeNext(
						new String[] { qObj.toString(), annClustered.getNearestNeighborSets().get(qObj).toString() });
			}
			System.out.println("File: " + annQueriesResultCSVFile + " is written Successfully");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeNaiveAndClusteredANNTestResult(Graph graph, int totalNumOfNodeClusters,
			int totalNumOfObjectClusters, double timeElapsedToComputeANNNAive,
			double timeElapsedToComputeANNCLustered) {

		String evaluationResultTxtFile = "ResultFiles/NaiveAndClustedANNResult-" + graph.getDatasetName() + "_"
				+ getNormalDateTime() + ".txt";
		try {

			FileWriter outputFile = new FileWriter(evaluationResultTxtFile);

			outputFile
					.write(String.format("The total number of Nodes in Data set: %s", graph.getNodesWithInfo().size()));
			outputFile.write(System.lineSeparator()); // new line
			outputFile
					.write(String.format("The total number of Edges in Data set: %s", graph.getEdgesWithInfo().size()));
			outputFile.write(System.lineSeparator()); // new line
			outputFile.write(String.format("Number of Edges containing objects: %s", graph.getObjectsOnEdges().size()));
			outputFile.write(System.lineSeparator()); // new line
			outputFile.write(String.format("Total number of Objects: %s", graph.getTotalNumberOfObjects()));
			outputFile.write(System.lineSeparator()); // new line
			outputFile.write(String.format("Total number of TRUE Objects: %s", graph.getTotalNumberOfTrueObjects()));
			outputFile.write(System.lineSeparator()); // new line
			outputFile.write(String.format("Total number of FALSE Objects: %s", graph.getTotalNumberOfFalseObjects()));
			// outputFile.write(String.format("Percentage of True objects: %3f ",
			// (double) (graph.getTotalNumberOfTrueObjects() /
			// graph.getTotalNumberOfObjects())));
			outputFile.write(System.lineSeparator()); // new line
			outputFile.write(String.format("Total number of Node clusters: %s", totalNumOfNodeClusters));
			outputFile.write(System.lineSeparator()); // new line
			outputFile.write(String.format("Total number of Object clusters: %s", totalNumOfObjectClusters));
			outputFile.write(System.lineSeparator()); // new line
			outputFile.write(
					String.format("Time elapsed to compute Naive ANN:  %.4f secs", timeElapsedToComputeANNNAive));
			outputFile.write(System.lineSeparator()); // new line
			outputFile.write(String.format("Time elapsed to compute Clustered ANN: %.4f secs",
					timeElapsedToComputeANNCLustered));
			outputFile.write(System.lineSeparator()); // new line
			outputFile.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public static void writeFinalEvaluationResult(Graph graph, String fileName, double timeElapsedToComputeANNNAive,
			double timeElapsedToComputeANNCLustered) {

		try {
			FileWriter outputFile = new FileWriter(fileName, true);

			// DATASET-NAME | QUERY-OBJ-NUM | DATA-OBJ-NUM | NAIVE-ANN-TIME |
			// CLUSTERED-ANN-TIME
			outputFile.write(String.format(graph.getDatasetName() + csvSplitBy + graph.getTotalNumberOfTrueObjects()
					+ csvSplitBy + graph.getTotalNumberOfFalseObjects() + csvSplitBy + timeElapsedToComputeANNNAive
					+ csvSplitBy + timeElapsedToComputeANNCLustered));
			outputFile.write(System.lineSeparator()); // new line
			outputFile.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public static void writeObjStats(Graph graph) {

		String evaluationResultTxtFile = "Statistics/objsOnEdgeInformation-" + graph.getDatasetName() + " "
				+ getNormalDateTime() + ".txt";
		try {

			FileWriter outputFile = new FileWriter(evaluationResultTxtFile);

			outputFile
					.write(String.format("The total number of Nodes in Data set: %s", graph.getNodesWithInfo().size()));
			outputFile.write(System.lineSeparator()); // new line
			outputFile
					.write(String.format("The total number of Edges in Data set: %s", graph.getEdgesWithInfo().size()));
			outputFile.write(System.lineSeparator()); // new line
			outputFile.write(String.format("Number of Edges containing objects: %s", graph.getObjectsOnEdges().size()));
			outputFile.write(System.lineSeparator()); // new line
			outputFile.write(String.format("Total number of Objects: %s", graph.getTotalNumberOfObjects()));
			outputFile.write(System.lineSeparator()); // new line
			outputFile.write(String.format("Total number of TRUE Objects: %s", graph.getTotalNumberOfTrueObjects()));
			outputFile.write(System.lineSeparator()); // new line
			outputFile.write(String.format("Total number of FALSE Objects: %s", graph.getTotalNumberOfFalseObjects()));
			outputFile.write(System.lineSeparator()); // new line
			if (graph.getTotalNumberOfObjects() != 0) {
				outputFile.write(String.format("Percentage of True objects: %3f ",
						(double) (graph.getTotalNumberOfTrueObjects() / graph.getTotalNumberOfObjects())));
			}
			outputFile.write(System.lineSeparator()); // new line
			outputFile.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public static void writeDatasetStatistics(Graph graph) {

		String datasetStatisticsTxtFile = "Statistics/datasetStatistics-" + graph.getDatasetName() + " "
				+ getNormalDateTime() + ".txt";
		ClusteringNodes clusteringNodes = new ClusteringNodes();
		clusteringNodes.cluster(graph);
		// double minEdgeLength = Double.MAX_VALUE;
		// int maxNoOfObjs = 0;
		// for (Edge edge : graph.getEdgesWithInfo()) {
		// if (edge.getLength() < minEdgeLength) {
		// minEdgeLength = edge.getLength();
		// }
		// }
		//
		// double minDistBetweenObjects = Math.round((minEdgeLength / 3) * 100000.0) /
		// 100000.0;
		// for (Edge edge : graph.getEdgesWithInfo()) {
		// maxNoOfObjs += (int) (edge.getLength() / minDistBetweenObjects - 1);
		// }

		try {

			FileWriter outputFile = new FileWriter(datasetStatisticsTxtFile);

			outputFile
					.write(String.format("The total number of Nodes in Data set: %s", graph.getNodesWithInfo().size()));
			outputFile.write(System.lineSeparator()); // new line
			outputFile
					.write(String.format("The total number of Edges in Data set: %s", graph.getEdgesWithInfo().size()));
			outputFile.write(System.lineSeparator()); // new line
			// outputFile.write(String.format("Number of Edges containing objects: %s",
			// graph.getObjectsOnEdges().size()));
			// outputFile.write(System.lineSeparator()); // new line
			// outputFile.write(String.format("Max number of Objects on Graph: %s",
			// maxNoOfObjs));
			// outputFile.write(System.lineSeparator()); // new line
			outputFile.write(
					String.format("Total number of Node Clusters: %s", clusteringNodes.getTotalNumberOfNodeClusters()));
			outputFile.write(System.lineSeparator()); // new line

			outputFile.write(System.lineSeparator()); // new line
			outputFile.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	// Method to read the POI with category Id files from the data-set
	public static ArrayList<RoadObject> readRoadObjFile(String csvFilename) {
		String line = "";
		int poiID = 0;
		ArrayList<RoadObject> listObjs = new ArrayList<RoadObject>();

		try (BufferedReader br = new BufferedReader(new FileReader(csvFilename))) {
			while ((line = br.readLine()) != null) {
				String[] record = line.split(csvSplitBy);
				if (record.length == 3) {
					RoadObject obj = new RoadObject();
					obj.setLongitude(Double.parseDouble(record[0]));
					obj.setLatitude(Double.parseDouble(record[1]));
					obj.setObjCategoryId(Integer.parseInt(record[2]));
					poiID++;
					obj.setObjId(poiID);
					//
					listObjs.add(obj);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return listObjs;

	}

	public static Map<Integer, ArrayList<RoadObject>> readRoadObjectFile(String csvFilename) {

		Map<Integer, ArrayList<RoadObject>> m_objectsOnEdge = new HashMap<Integer, ArrayList<RoadObject>>();

		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(csvFilename))) {
			while ((line = br.readLine()) != null) {
				String[] record = line.split(csvSplitBy);
				if (record.length == 4) {

					RoadObject rObject = new RoadObject();
					rObject.setObjId(Integer.parseInt(record[1]));
					rObject.setType(Boolean.parseBoolean(record[2]));
					rObject.setDistanceFromStartNode(Double.parseDouble(record[3]));

					if (!m_objectsOnEdge.containsKey(Integer.parseInt(record[0]))) {
						ArrayList<RoadObject> roadObjects = new ArrayList<RoadObject>();
						m_objectsOnEdge.put(Integer.parseInt(record[0]), roadObjects);
					}
					if (!m_objectsOnEdge.get(Integer.parseInt(record[0])).contains(rObject)) {
						m_objectsOnEdge.get(Integer.parseInt(record[0])).add(rObject);

					}

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return m_objectsOnEdge;

	}

	public static void writeObjectParameterInfo() {

		// IF you need to have written those parameters in file

	}

	public static int getObjectParameter(String datasetName, int totaNumberOfObjects) {

		int objParam = 300000, maxNumOfObjsPerEdge;
		int tempTotaNumberOfObjects = 0;

		double totalLengthOfAllEdges = 0;
		double minDistBetweenObjs = 0;

		ArrayList<Double> listOfEdgeLength = readEdgeFileReturnListOfEdgeLength(datasetName);
		// Collections.sort(listOfEdgeLength);
		// Collections.reverse(listOfEdgeLength);

		for (int i = 0; i < listOfEdgeLength.size(); i++) {
			totalLengthOfAllEdges += listOfEdgeLength.get(i);
		}
		System.out.println("totalLengthOfAllEdges: " + totalLengthOfAllEdges);
		while (tempTotaNumberOfObjects < totaNumberOfObjects) {
			if (tempTotaNumberOfObjects == totaNumberOfObjects)
				break;
			objParam++;
			minDistBetweenObjs = Math.round(totalLengthOfAllEdges / objParam * 100000000.0) / 100000000.0;
			tempTotaNumberOfObjects = 0;
			for (int i = 0; i < listOfEdgeLength.size(); i++) {
				maxNumOfObjsPerEdge = getMaxNumOfObjsPerEdge(listOfEdgeLength.get(i), minDistBetweenObjs);
				tempTotaNumberOfObjects += maxNumOfObjsPerEdge;
				// System.out.println("edgeLength: " + listOfEdgeLength.get(i) + ",
				// maxNumOfObjsPerEdge: " + maxNumOfObjsPerEdge);
				// System.out.println("objParam: " + objParam + ", tempTotaNumberOfObjects: " +
				// tempTotaNumberOfObjects + ", totaNumberOfObjects: " + totaNumberOfObjects);
			}
			// System.out.println("objParam: " + objParam + ", tempTotaNumberOfObjects: " +
			// tempTotaNumberOfObjects
			// + ", totaNumberOfObjects: " + totaNumberOfObjects);

		}
		return objParam;
	}

	private static int getMaxNumOfObjsPerEdge(double edgeLength, double minDistBetweenObjs) {
		double m1;
		if ((edgeLength / minDistBetweenObjs - 6) < 0) {
			m1 = 0.0;
		} else {
			m1 = Math.round((edgeLength / minDistBetweenObjs - 6) * 100000000.0) / 100000000.0;
		}

		double m2;
		if (m1 > minDistBetweenObjs) {
			m2 = m1;
		} else {
			m2 = 0;
		}

		if (m2 > 0) {
			if (m2 > 1) {
				return (int) m2;
			} else {
				return 1;
			}
		} else {
			return 0;
		}
	}

	// load information of nodes from csv file and add these nodes to list of nodes
	// in a give graph
	public void loadNodesInfo(Graph graph, String csvFile) {
		graph.setNodesWithInfo(readNodeFile(csvFile));
	}

	// load information of pois from csv file and add these pois to list of pois in
	// a give graph
	public void loadPoiInfo(Graph graph, String csvFile) {
		graph.setObjectsWithInfo(readRoadObjFile(csvFile));
		// graph.getPois().get(0).setPoiCategoryId(intPOICategoryId);
	}

	public static int convertDoubleToInteger(double dValue) {
		return (int) Math.round(dValue);

	}

	public static String getNormalDateTime() {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

		return format.format(new Date());

	}

	public static Double getMinimumKey(MultiValuedMap<Double, Integer> multivaluemap) {
		ArrayList<Double> keys = new ArrayList<Double>(multivaluemap.keySet());

		Collections.sort(keys);

		return keys.get(0);
	}

	public static Double getSecondKey(MultiValuedMap<Double, Integer> multivaluemap) {
		ArrayList<Double> keys = new ArrayList<Double>(multivaluemap.keySet());

		Collections.sort(keys);
		if (keys.size() > 1) {
			return keys.get(1);
		} else
			return keys.get(0);

	}

	public static <K, V> K getMapKey(MultiValuedMap<K, V> map, V value) {
		if (map != null) {
			for (Map.Entry<K, V> entry : map.entries()) {
				if (entry.getValue().equals(value)) {
					return entry.getKey();
				}
			}
		}
		return null;
	}

	public static <K, V> K getMapKeyValue(MultiValuedMap<K, V> map) {
		if (map != null) {
			for (Map.Entry<K, V> entry : map.entries()) {
				return entry.getKey();

			}
		}
		return null;
	}

	public static int getFirstElementFromCollection(Collection<Integer> colls) {

		return colls.iterator().next();

	}

	// Private methods
	private static boolean isInteger(String str) {

		try {
			int a = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
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
}
