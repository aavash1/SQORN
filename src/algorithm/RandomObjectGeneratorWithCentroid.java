package algorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.decimal4j.util.DoubleRounder;

import com.google.common.annotations.VisibleForTesting;

//import com.google.common.collect.MinMaxPriorityQueue;

import framework.Edge;
import framework.Graph;
import framework.RoadObject;
import framework.UtilsManagment;
import algorithm.GraphTraversal;

public class RandomObjectGeneratorWithCentroid {

	// not complete yet
	// Object Type: false = data object ; true = query object;
	private static boolean uniformObjects = false;
	private static boolean uniformDataObjects = false;
	private static boolean uniformQueryObjects = false;
	private static int maxNumberOfObjsPerEdge;
	private static double minDistBetNodeAndObject;

	// For Statistics
	private static int m_totalNumberOfObjects;
	private static int m_totalNumberOfTrueObjects;
	private static int m_totalNumberOfFalseObjects;
	private static int m_totalNumberOfEdges;
	private static int m_totalNumberOfEdgesContainingObjects;
	private static double m_totalLengthOfEdges;
	private static double m_minEdgeLength;
	private static double m_maxEdgeLength;
	private static double m_minDistBetweenObjs;
	private static double m_minDistBetweenObjsPrecision = 1000000.0;
	private static double m_distFromStartNodePrecision = 1000000.0;
	
	//this method will first generate 10 objects on different random edges
	//And selects all the edges where 10 object exists and traverses through the whole other perimeter
	//And it generates remaining number of true objects for the whole other centroids
	//finally we will generate remaining total other number of False Objects
	//NOTE: This method will first generate only True objects and create TRUE object centroids.
	public static void generateRandomObjectsOnMapWithCentroidForTrueObjects(Graph graph, int totalNumberOfTrueObjects,
			int totalNumberOfFalseObjects) {
		graph.removeObjectsOnEdges();
		int objCounter = 1;
		int totalNumberOfEdges = graph.getNumberOfEdges();
		int totalNumOfObjects = totalNumberOfTrueObjects + totalNumberOfFalseObjects;

		Map<Integer, ArrayList<Double>> acceptedDistancesOnEdge = new HashMap<Integer, ArrayList<Double>>();
//
//		LinkedList<Boolean> boolValues = new LinkedList<Boolean>();
//		for (int i = 0; i < totalNumberOfFalseObjects; i++) {
//			boolValues.add(false);
//		}
//		Collections.shuffle(boolValues);
		
		boolean testVar;
		int randomEdgeId;
		while(objCounter<=totalNumOfObjects) {
			if(graph.getObjectsWithInfo().isEmpty()) {
				for(int i=0;i<10;i++) {
					//randomEdgeId = (int) getThreadRandomNumberInBetween(0, totalNumberOfEdges-1);
					randomEdgeId = (int) getThreadRandomNumberInBetween(1, totalNumberOfEdges-1);
					RoadObject object = new RoadObject();
					object.setObjId(objCounter);
					object.setType(true);

					if (!acceptedDistancesOnEdge.containsKey(randomEdgeId)) {
						ArrayList<Double> acceptedDistances = new ArrayList<Double>();
						acceptedDistancesOnEdge.put(randomEdgeId, acceptedDistances);
					}
					double edgeLength = graph.getEdgeDistance(randomEdgeId);
					double distFromStartNode = getRandDoubleInBetween(0, edgeLength);
					if (!acceptedDistancesOnEdge.get(randomEdgeId).contains(distFromStartNode)) {
						object.setDistanceFromStartNode(distFromStartNode);
					} else {
						continue;

					}
					//object.setType(true);
//					if (!boolValues.isEmpty()) {
//						//testVar = boolValues.poll();
//						
//					}
					if (graph.addObjectOnEdge(randomEdgeId, object)) {
						objCounter++;
						acceptedDistancesOnEdge.get(randomEdgeId).add(distFromStartNode);
						//System.out.println(objCounter + " Object Added" + ", distFromSN: " + distFromStartNode + " on edge: "
						//		+ randomEdgeId + " of length: " + edgeLength);
					}
					
				}
			
				
			}
			else {
				//Select all edges that has 10 true objects and make it as centroid
				//Generate ((n-10)/10) object for each centroid
				for(Integer edgeWithObjects:graph.getObjectsOnEdges().keySet()) {
					int centroidObjectCounter=0;
					int remainingObjectsTobeGenerated=(totalNumberOfTrueObjects-10)/10;
					ArrayList<Integer> edgesSelected=getEdgesWithinPerimeter(graph, 48.3, edgeWithObjects);
					while(centroidObjectCounter<=remainingObjectsTobeGenerated) {
						randomEdgeId = edgesSelected.get(ThreadLocalRandom.current().nextInt(edgesSelected.size()));
						RoadObject object = new RoadObject();
						object.setObjId(objCounter);
						object.setType(true);
						
						if (!acceptedDistancesOnEdge.containsKey(randomEdgeId)) {
							ArrayList<Double> acceptedDistances = new ArrayList<Double>();
							acceptedDistancesOnEdge.put(randomEdgeId, acceptedDistances);
						}
						double edgeLength = graph.getEdgeDistance(randomEdgeId);
						double distFromStartNode = getRandDoubleInBetween(0, edgeLength);
						if (!acceptedDistancesOnEdge.get(randomEdgeId).contains(distFromStartNode)) {
							object.setDistanceFromStartNode(distFromStartNode);
						} else {
							continue;

						}

						//object.setType(true);
//						if (!boolValues.isEmpty()) {
//							object.setType(true);
//						}
						if (graph.addObjectOnEdge(randomEdgeId, object)) {
							objCounter++;
							centroidObjectCounter++;
							acceptedDistancesOnEdge.get(randomEdgeId).add(distFromStartNode);
						}
					}
					
					
					
				}//ends the for loop here
				//Generate Rest of other objects "FALSE" objects
				//randomEdgeId = (int) getThreadRandomNumberInBetween(0, totalNumberOfEdges-1);
				randomEdgeId = (int) getThreadRandomNumberInBetween(1, totalNumberOfEdges-1);
				RoadObject object = new RoadObject();
				object.setObjId(objCounter);
				object.setType(false);
				//just moved it here
				
				if (!acceptedDistancesOnEdge.containsKey(randomEdgeId)) {
					ArrayList<Double> acceptedDistances = new ArrayList<Double>();
					acceptedDistancesOnEdge.put(randomEdgeId, acceptedDistances);
				}
				double edgeLength = graph.getEdgeDistance(randomEdgeId);
				double distFromStartNode = getRandDoubleInBetween(0, edgeLength);
				if (!acceptedDistancesOnEdge.get(randomEdgeId).contains(distFromStartNode)) {
					object.setDistanceFromStartNode(distFromStartNode);
				} else {
					continue;

				}

				//object.setType(false);
//				if (!boolValues.isEmpty()) {
//					testVar = boolValues.poll();
//					object.setType(testVar);
//				}
				if (graph.addObjectOnEdge(randomEdgeId, object)) {
					objCounter++;
					acceptedDistancesOnEdge.get(randomEdgeId).add(distFromStartNode);
				}
				
			}
			
			
		}
		
		m_totalNumberOfObjects = graph.getTotalNumberOfObjects();
		m_totalNumberOfTrueObjects = graph.getTotalNumberOfTrueObjects();
		m_totalNumberOfFalseObjects = graph.getTotalNumberOfFalseObjects();
		m_totalNumberOfEdges = totalNumberOfEdges;
		m_totalNumberOfEdgesContainingObjects = graph.getObjectsOnEdges().size();

		// System.out.println("size of acceptedDistancesOnEdge: " +
		// acceptedDistancesOnEdge.size());
		//return fileName;
		System.out.println("Finished Generating Road Objects");
	}
	
	//NOTE: This method will first generate only False objects and create FALSE object centroids.
	public static void generateRandomObjectsOnMapWithCentroidForFalseObjects(Graph graph, int totalNumberOfTrueObjects,
			int totalNumberOfFalseObjects) {
		graph.removeObjectsOnEdges();
		int objCounter = 1;
		int totalNumberOfEdges = graph.getNumberOfEdges();
		int totalNumOfObjects = totalNumberOfTrueObjects + totalNumberOfFalseObjects;

		Map<Integer, ArrayList<Double>> acceptedDistancesOnEdge = new HashMap<Integer, ArrayList<Double>>();

		LinkedList<Boolean> boolValues = new LinkedList<Boolean>();
		for (int i = 0; i < totalNumberOfFalseObjects; i++) {
			boolValues.add(true);
		}
		Collections.shuffle(boolValues);
		
		boolean testVar;
		int randomEdgeId;
		while(objCounter<=totalNumOfObjects) {
			if(graph.getObjectsWithInfo().isEmpty()) {
				for(int i=0;i<10;i++) {
					randomEdgeId = (int) getThreadRandomNumberInBetween(0, totalNumberOfEdges-1);
					RoadObject object = new RoadObject();
					object.setObjId(objCounter);

					if (!acceptedDistancesOnEdge.containsKey(randomEdgeId)) {
						ArrayList<Double> acceptedDistances = new ArrayList<Double>();
						acceptedDistancesOnEdge.put(randomEdgeId, acceptedDistances);
					}
					double edgeLength = graph.getEdgeDistance(randomEdgeId);
					double distFromStartNode = getRandDoubleInBetween(0, edgeLength);
					if (!acceptedDistancesOnEdge.get(randomEdgeId).contains(distFromStartNode)) {
						object.setDistanceFromStartNode(distFromStartNode);
					} else {
						continue;

					}

					if (!boolValues.isEmpty()) {
						//testVar = boolValues.poll();
						object.setType(false);
					}
					if (graph.addObjectOnEdge(randomEdgeId, object)) {
						objCounter++;
						acceptedDistancesOnEdge.get(randomEdgeId).add(distFromStartNode);
						//System.out.println(objCounter + " Object Added" + ", distFromSN: " + distFromStartNode + " on edge: "
						//		+ randomEdgeId + " of length: " + edgeLength);
					}
					
				}
			
				
			}
			else {
				//Select all edges that has 10 true objects and make it as centroid
				//Generate ((n-10)/10) object for each centroid
				for(Integer edgeWithObjects:graph.getObjectsOnEdges().keySet()) {
					int centroidObjectCounter=0;
					int remainingObjectsTobeGenerated=(totalNumberOfTrueObjects-10)/10;
					ArrayList<Integer> edgesSelected=getEdgesWithinPerimeter(graph, 48.3, edgeWithObjects);
					while(centroidObjectCounter<=remainingObjectsTobeGenerated) {
						randomEdgeId = edgesSelected.get(ThreadLocalRandom.current().nextInt(edgesSelected.size()));
						RoadObject object = new RoadObject();
						object.setObjId(objCounter);
						
						if (!acceptedDistancesOnEdge.containsKey(randomEdgeId)) {
							ArrayList<Double> acceptedDistances = new ArrayList<Double>();
							acceptedDistancesOnEdge.put(randomEdgeId, acceptedDistances);
						}
						double edgeLength = graph.getEdgeDistance(randomEdgeId);
						double distFromStartNode = getRandDoubleInBetween(0, edgeLength);
						if (!acceptedDistancesOnEdge.get(randomEdgeId).contains(distFromStartNode)) {
							object.setDistanceFromStartNode(distFromStartNode);
						} else {
							continue;

						}

						if (!boolValues.isEmpty()) {
							object.setType(false);
						}
						if (graph.addObjectOnEdge(randomEdgeId, object)) {
							objCounter++;
							centroidObjectCounter++;
							acceptedDistancesOnEdge.get(randomEdgeId).add(distFromStartNode);
						}
					}
					
					
					
				}//ends the for loop here
				//Generate Rest of other objects "FALSE" objects
				randomEdgeId = (int) getThreadRandomNumberInBetween(0, totalNumberOfEdges-1);
				RoadObject object = new RoadObject();
				object.setObjId(objCounter);
				
				if (!acceptedDistancesOnEdge.containsKey(randomEdgeId)) {
					ArrayList<Double> acceptedDistances = new ArrayList<Double>();
					acceptedDistancesOnEdge.put(randomEdgeId, acceptedDistances);
				}
				double edgeLength = graph.getEdgeDistance(randomEdgeId);
				double distFromStartNode = getRandDoubleInBetween(0, edgeLength);
				if (!acceptedDistancesOnEdge.get(randomEdgeId).contains(distFromStartNode)) {
					object.setDistanceFromStartNode(distFromStartNode);
				} else {
					continue;

				}

				if (!boolValues.isEmpty()) {
					testVar = boolValues.poll();
					object.setType(testVar);
				}
				if (graph.addObjectOnEdge(randomEdgeId, object)) {
					objCounter++;
					acceptedDistancesOnEdge.get(randomEdgeId).add(distFromStartNode);
				}
				
			}
			
			
		}
		
		m_totalNumberOfObjects = graph.getTotalNumberOfObjects();
		m_totalNumberOfTrueObjects = graph.getTotalNumberOfTrueObjects();
		m_totalNumberOfFalseObjects = graph.getTotalNumberOfFalseObjects();
		m_totalNumberOfEdges = totalNumberOfEdges;
		m_totalNumberOfEdgesContainingObjects = graph.getObjectsOnEdges().size();

		// System.out.println("size of acceptedDistancesOnEdge: " +
		// acceptedDistancesOnEdge.size());
		//return fileName;
		System.out.println("Finished Generating Road Objects");
	}
	

	private static int getMaxNumOfObjsPerEdge(double edgeLength, double minDistBetweenObjs) {
		double m1 = 0.0;
		if ((edgeLength / minDistBetweenObjs - 6) < 0) {
			m1 = 0.0;
		} else {
			m1 = Math.round((edgeLength / minDistBetweenObjs - 6) * m_minDistBetweenObjsPrecision)
					/ m_minDistBetweenObjsPrecision;
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

	private static int getTotalObjParam(String datasetName, int totalObject) {
		String line = "";
		String txtFilename = "ConfigFiles/totalObjectParam.txt";
		datasetName = datasetName.trim().toUpperCase();

		try (BufferedReader br = new BufferedReader(new FileReader(txtFilename))) {
			while ((line = br.readLine()) != null) {
				String[] record = line.split(" ");

				if (record.length == 3) {
					if ((datasetName.equals(record[0])) && (Integer.parseInt(record[1]) == totalObject)) {
						return Integer.parseInt(record[2]);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return totalObject;
	}

	public static int getRandIntBetRange(double min, double max) {
		int x = (int) ((Math.random() * ((max - min) + 1)) + min);
		return x;
	}

	public static double getRandDoubleBetRange(double min, double max) {
		double x = ThreadLocalRandom.current().nextDouble(min, max);
		// DoubleRounder.round(x, 17);
		// DoubleRounder.round(x,m_distFromStartNodePrecision);
		x = Math.round(x * m_distFromStartNodePrecision) / m_distFromStartNodePrecision;
		return x;
	}
	
	public static long getThreadRandomNumberInBetween(long lower,long upper) {
		ThreadLocalRandom generator=ThreadLocalRandom.current();
		long randomValue=generator.nextLong(lower,upper);
		return randomValue;
	}

	public static double getRandDoubleInBetween(double min, double max) {
		double x = (Math.random() * ((max - min) + 1)) + min;
		x = Math.round(x * m_distFromStartNodePrecision) / m_distFromStartNodePrecision;
		return x;
	}
	
	public static double getRandDoubleBetRangeThreadLocal(double min, double max) {
		ThreadLocalRandom generator=ThreadLocalRandom.current();
		double randomValue=generator.nextDouble(min,max);
		return randomValue;
	}

	public static double getRandDoubleBetRange1(double min, double max) {
		double x;
		x = ThreadLocalRandom.current().nextDouble(min, max);
		BigDecimal bd = new BigDecimal(Double.toString(x));
		bd = bd.setScale(17, RoundingMode.HALF_DOWN);
		double finalValue = bd.doubleValue();
		return finalValue;
	}

	public static double getRandDoubleBetRange3(double min, double max) {
		// 7.2 unboundeddoublerandom with commons math
		double generateDouble = new RandomDataGenerator().getRandomGenerator().nextDouble() * ((max - min) + 1) + min;
		// double x = (Math.random() * ((max - min) + 1)) + min;
		generateDouble = Math.round(generateDouble * m_distFromStartNodePrecision) / m_distFromStartNodePrecision;
		return generateDouble;
	}

	public static double getRandDoubleBetRange4(double min, double max) {
		// 8.1 boundeddoublerandom with commons math
		double generateDouble = min + new Random().nextDouble() * (max - min);
		generateDouble = Math.round(generateDouble * m_distFromStartNodePrecision) / m_distFromStartNodePrecision;
		return generateDouble;
	}

	public static double getRandDoubleBetRange5(double min, double max) {
		// 8.2 boundeddoublerandom with commons math
		double generateDouble = new RandomDataGenerator().nextUniform(min, max);
		generateDouble = Math.round(generateDouble * m_distFromStartNodePrecision) / m_distFromStartNodePrecision;
		return generateDouble;
	}

	// This method will return the edgelist that are within a certain perimeter.
	public static ArrayList<Integer> getEdgesWithinPerimeter(Graph graph, double perimeter, int edgeId) {
		ArrayList<Integer> selectedEdges = new ArrayList<Integer>();
		LinkedList<HashMap<Integer, Double>> visitedEdges = new LinkedList<HashMap<Integer, Double>>();
		LinkedList<Integer> edgeQueue = new LinkedList<Integer>();

		edgeQueue.add(edgeId);
		double initialDistance = 0.0;

		while (!edgeQueue.isEmpty()) {
			int currentEdge = edgeQueue.poll();
			if (visitedEdges.isEmpty()) {
				HashMap<Integer, Double> hashedEdge = new HashMap<Integer, Double>();
				hashedEdge.put(currentEdge, initialDistance);
				visitedEdges.add(hashedEdge);
				if (selectedEdges.isEmpty()) {
					if (!selectedEdges.contains(currentEdge)) {
						selectedEdges.add(currentEdge);
					}
				}
				edgeQueue.add(currentEdge);
			} else {
				for (Integer edgeIndex : graph.getAdjacencyEdgeIds(currentEdge)) {
					if (checkIfEdgeExists(visitedEdges, edgeIndex) == true) {
						continue;
					} else {
						double edgeLength = graph.getEdgeDistance(edgeIndex);
						// System.out.println("Given Edge Len: " + edgeLength);

						for (int i = 0; i < visitedEdges.size(); i++) {
							if (visitedEdges.get(i).containsKey(currentEdge)) {
								double currentEdgeDistance = visitedEdges.get(i).get(currentEdge);
								// System.out.println("Current Edge Len: " + currentEdgeDistance);

								if (edgeLength + currentEdgeDistance <= perimeter) {
									HashMap<Integer, Double> hashedEdge = new HashMap<Integer, Double>();
									double lengthAfterAdding = currentEdgeDistance + edgeLength;
									// System.out.println("Edge Len after adding: " + lengthAfterAdding);

									hashedEdge.put(edgeIndex, lengthAfterAdding);
									visitedEdges.add(hashedEdge);
									if (!selectedEdges.contains(edgeIndex)) {
										selectedEdges.add(edgeIndex);
									}
									edgeQueue.add(edgeIndex);
								}

							}
						}

					}

				}
			}

		}

		return selectedEdges;
	}

	public static boolean checkIfEdgeExists(LinkedList<HashMap<Integer, Double>> visitedEdges, int edgeId) {

		for (int i = 0; i < visitedEdges.size(); i++) {
			if (visitedEdges.get(i).containsKey(edgeId)) {
				return true;
			}
		}
		return false;
	}

	public static void printGeneratorParameters() {
		System.out.println("--------------------------------------------------");
		System.out.println("Generator's Configurable Parameters: ");
		// System.out.println("minNumberOfObjsPerEdge: " + minNumberOfObjsPerEdge);
		System.out.println("minDistanceBetObjs: " + m_minDistBetweenObjs);
		// System.out.println("maxNumberOfObjsPerEdge: " + maxNumberOfObjsPerEdge );
		// System.out.println("uniformObjects: " + uniformObjects);
		// System.out.println("uniformDataObjects: " + uniformDataObjects);
		// System.out.println("uniformQueryObjects: " + uniformQueryObjects);
		System.out.println("--------------------------------------------------");
	}

	public static void printStatistics() {
		double trueObjectsPer, falseObjectsPer;
		trueObjectsPer = Math.round((double) m_totalNumberOfTrueObjects / m_totalNumberOfObjects * 100 * 100.0) / 100.0;
		falseObjectsPer = Math.round((double) m_totalNumberOfFalseObjects / m_totalNumberOfObjects * 100 * 100.0)
				/ 100.0;
		System.out.println("--------------------------------------------------");
		System.out.println("Total number of Objects: " + m_totalNumberOfObjects);
		System.out
				.println("Total number of True Objects: " + m_totalNumberOfTrueObjects + ", " + trueObjectsPer + " %");
		System.out.println(
				"Total number of False Objects: " + m_totalNumberOfFalseObjects + ", " + falseObjectsPer + " %");
		System.out.println("Total number of Edges: " + m_totalNumberOfEdges);
		System.out.println("Total number of Edges containing objects: " + m_totalNumberOfEdgesContainingObjects);
		System.out.println("Minimum edge length: " + m_minEdgeLength);
		System.out.println("Maximum edge length: " + m_maxEdgeLength);
		System.out.println("Total lenght of all edges: " + m_totalLengthOfEdges);
		System.out.println("Minimum dist between objects: " + m_minDistBetweenObjs);

		System.out.println("--------------------------------------------------");

	}

	private static void printCounterInfo(int expected, int actual, String fileName) {
		// String debugCounterInfo = "Statistics/DebugCounterInfo-" +
		// UtilsManagment.getNormalDateTime() + ".txt";

		try {
			FileWriter outputFile = new FileWriter(fileName, true);
			PrintWriter pw = new PrintWriter(outputFile, true);
			pw.append(actual + "\n");
			// pw.append("maxNumberOfObjects: "+ expected + " counterForObject: " + actual +
			// "\n");
			// pw.write(System.lineSeparator()); // new line
			pw.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	// public int getDistinctRandomEdgeId ()
	// {
	// while (!isAcceptableEdgeId) {
	// if (randomlyChosenEdgeIds.length == 0) {
	// edgeId = (rand.nextInt(totalNumberOfEdges - 1) + 1) + 1;
	// isAcceptableEdgeId = true;
	// } else {
	// edgeId = (rand.nextInt(totalNumberOfEdges - 1) + 1) + 1;
	// for (int a = 0; a < randomlyChosenEdgeIds.length; a++) {
	// if (randomlyChosenEdgeIds[a] == edgeId) {
	// randomlyChosenEdgeIds[a] = edgeId;
	// isAcceptableEdgeId = true;
	// }
	// }
	// }
	// }
	// }
	///////////////////////////////////////////////////// End of
	// Archive//////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}