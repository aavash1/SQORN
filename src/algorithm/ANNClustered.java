package algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import framework.Graph;
import framework.RoadObject;

public class ANNClustered {

	Graph m_graph;

	// m_nearestNeighborSets: Map<QueryObjectId, DataObjectId>.
	private Map<Integer, Integer> m_nearestNeighborSets = new HashMap<Integer, Integer>();
	private Map<Integer, LinkedList<Integer>> m_objectIdClusters;
	private Map<Integer, LinkedList<Integer>> m_nodeIdClusters;
	private int sizeOfNodeClusters, sizeOfObjectClusters;

	public void compute(Graph gr, boolean queryObjectType) {
		System.out.println();
		System.out.println("Clustered ANN is running ... ");

		m_graph = gr;

		ClusteringNodes clusteringNodes = new ClusteringNodes();

		ClusteringRoadObjects clusteringObjects = new ClusteringRoadObjects();
		m_nodeIdClusters = clusteringNodes.cluster(gr);
		m_objectIdClusters = clusteringObjects.clusterWithIndex(gr, m_nodeIdClusters, queryObjectType);
		sizeOfNodeClusters = m_nodeIdClusters.size();
		sizeOfObjectClusters = m_objectIdClusters.size();

		// clusteringNodes.printNodeClusters();
		// clusteringObjects.printRoadObjectClusters();

		NearestNeighbor nn = new NearestNeighbor();

		int boundaryStartQueryObj, boundaryEndQueryObj;
		int objectClusterCounter = 0;
		if (queryObjectType) {
			// Query object = True Object; Data Object = False Object
			System.out.println("Query object = True Object (" + m_graph.getTotalNumberOfTrueObjects()
					+ "); Data Object = False Object (" + m_graph.getTotalNumberOfFalseObjects() + ")");

			// Iterate through boundary objects of object clusters
			for (Integer index : m_objectIdClusters.keySet()) {
				objectClusterCounter++;
				System.out.println(objectClusterCounter + " out of " + m_objectIdClusters.size());

				if (!m_objectIdClusters.get(index).isEmpty()) {
					if (m_objectIdClusters.get(index).size() == 1) {
						int queryObj = m_objectIdClusters.get(index).getFirst();
						int nearestFalseObjId = nn.getNearestFalseObjectIdToGivenObjOnMap(m_graph, queryObj);

						m_nearestNeighborSets.put(queryObj, nearestFalseObjId);

					} else if (m_objectIdClusters.get(index).size() == 2) {
						boundaryStartQueryObj = m_objectIdClusters.get(index).getFirst();
						boundaryEndQueryObj = m_objectIdClusters.get(index).getLast();

						int nearestFalseObjForBeginingExit = nn.getNearestFalseObjectIdToGivenObjOnMap(m_graph,
								boundaryStartQueryObj);
						int nearestFalseObjForEndExit = nn.getNearestFalseObjectIdToGivenObjOnMap(m_graph,
								boundaryEndQueryObj);

						m_nearestNeighborSets.put(boundaryStartQueryObj, nearestFalseObjForBeginingExit);
						m_nearestNeighborSets.put(boundaryEndQueryObj, nearestFalseObjForEndExit);

					} else {
						boundaryStartQueryObj = m_objectIdClusters.get(index).getFirst();
						boundaryEndQueryObj = m_objectIdClusters.get(index).getLast();

						Map<RoadObject, Double> nearestFalseObjWithDistBoundaryStart = nn
								.getNearestFalseObjectToGivenObjOnMap(gr, boundaryStartQueryObj);
						Map<RoadObject, Double> nearestFalseObjWithDistBoundaryEnd = nn
								.getNearestFalseObjectToGivenObjOnMap(gr, boundaryEndQueryObj);

						RoadObject[] nearestFalseObjBoundaryStart = nearestFalseObjWithDistBoundaryStart.keySet()
								.toArray(new RoadObject[0]);
						RoadObject[] nearestFalseObjBoundaryEnd = nearestFalseObjWithDistBoundaryEnd.keySet()
								.toArray(new RoadObject[0]);

						int nearestFalseObjIdForBoundaryStart = nearestFalseObjBoundaryStart[0].getObjectId();
						int nearestFalseObjIdForBoundaryEnd = nearestFalseObjBoundaryEnd[0].getObjectId();

						Double[] nearestFalseObjDistBoundaryStart = nearestFalseObjWithDistBoundaryStart.values()
								.toArray(new Double[0]);
						Double[] nearestFalseObjDistBoundaryEnd = nearestFalseObjWithDistBoundaryEnd.values()
								.toArray(new Double[0]);

						m_nearestNeighborSets.put(boundaryStartQueryObj, nearestFalseObjIdForBoundaryStart);

						m_nearestNeighborSets.put(boundaryEndQueryObj, nearestFalseObjIdForBoundaryEnd);

						for (int i = m_objectIdClusters.get(index).indexOf(boundaryStartQueryObj)
								+ 1; i < m_objectIdClusters.get(index).size() - 1; i++) {
							int currentTrueObject = m_objectIdClusters.get(index).get(i);
							LinkedList<Integer> currentObjCluster = new LinkedList<Integer>();
							currentObjCluster.addAll(m_objectIdClusters.get(index));
							LinkedList<Integer> currentNodeCluster = new LinkedList<Integer>();
							currentNodeCluster.addAll(m_nodeIdClusters.get(index));

							double distToBoundaryStartObj = m_graph.getDistanceBetweenBoundaryObjAndCurrentObj(
									currentNodeCluster, currentObjCluster, boundaryStartQueryObj, currentTrueObject);
							double distToBoundaryEndObj = m_graph.getDistanceBetweenBoundaryObjAndCurrentObj(
									currentNodeCluster, currentObjCluster, boundaryEndQueryObj, currentTrueObject);

							double distanceFromCurrentObjectToBoundaryStartNearestFalseObject = nearestFalseObjDistBoundaryStart[0]
									+ distToBoundaryStartObj;
							double distanceFromCurrentObjectToBoundaryEndNearestFalseObject = nearestFalseObjDistBoundaryEnd[0]
									+ distToBoundaryEndObj;

							if (distanceFromCurrentObjectToBoundaryStartNearestFalseObject > distanceFromCurrentObjectToBoundaryEndNearestFalseObject) {
								m_nearestNeighborSets.put(currentTrueObject, nearestFalseObjIdForBoundaryEnd);
							} else {
								m_nearestNeighborSets.put(currentTrueObject, nearestFalseObjIdForBoundaryStart);
							}

						}
					}
				}
			}

		}

		else {
			// Query object = False Object; Data Object = True Object
			System.out.println("Query object = False Object (" + m_graph.getTotalNumberOfFalseObjects()
					+ "); Data Object = True Object (" + m_graph.getTotalNumberOfTrueObjects() + ")");

			// Iterate through boundary objects of object clusters
			for (Integer index : m_objectIdClusters.keySet()) {
				objectClusterCounter++;
				System.out.println(objectClusterCounter + " out of " + m_objectIdClusters.size());

				if (!m_objectIdClusters.get(index).isEmpty()) {
					if (m_objectIdClusters.get(index).size() == 1) {
						int queryObj = m_objectIdClusters.get(index).getFirst();
						int nearestTrueObjId = nn.getNearestTrueObjectIdToGivenObjOnMap(m_graph, queryObj);

						m_nearestNeighborSets.put(queryObj, nearestTrueObjId);

					} else if (m_objectIdClusters.get(index).size() == 2) {
						boundaryStartQueryObj = m_objectIdClusters.get(index).getFirst();
						boundaryEndQueryObj = m_objectIdClusters.get(index).getLast();

						int nearestTrueObjForBeginingExit = nn.getNearestTrueObjectIdToGivenObjOnMap(m_graph,
								boundaryStartQueryObj);
						int nearestTrueObjForEndExit = nn.getNearestTrueObjectIdToGivenObjOnMap(m_graph,
								boundaryEndQueryObj);

						m_nearestNeighborSets.put(boundaryStartQueryObj, nearestTrueObjForBeginingExit);
						m_nearestNeighborSets.put(boundaryEndQueryObj, nearestTrueObjForEndExit);

					} else {
						boundaryStartQueryObj = m_objectIdClusters.get(index).getFirst();
						boundaryEndQueryObj = m_objectIdClusters.get(index).getLast();

						Map<RoadObject, Double> nearestTrueObjWithDistBoundaryStart = nn
								.getNearestTrueObjectToGivenObjOnMap(gr, boundaryStartQueryObj);
						Map<RoadObject, Double> nearestTrueObjWithDistBoundaryEnd = nn
								.getNearestTrueObjectToGivenObjOnMap(gr, boundaryEndQueryObj);

						RoadObject[] nearestTrueObjBoundaryStart = nearestTrueObjWithDistBoundaryStart.keySet()
								.toArray(new RoadObject[0]);
						RoadObject[] nearestTrueObjBoundaryEnd = nearestTrueObjWithDistBoundaryEnd.keySet()
								.toArray(new RoadObject[0]);

						int nearestTrueObjIdForBoundaryStart = nearestTrueObjBoundaryStart[0].getObjectId();
						int nearestTrueObjIdForBoundaryEnd = nearestTrueObjBoundaryEnd[0].getObjectId();

						Double[] nearestTrueObjDistBoundaryStart = nearestTrueObjWithDistBoundaryStart.values()
								.toArray(new Double[0]);
						Double[] nearestTrueObjDistBoundaryEnd = nearestTrueObjWithDistBoundaryEnd.values()
								.toArray(new Double[0]);

						m_nearestNeighborSets.put(boundaryStartQueryObj, nearestTrueObjIdForBoundaryStart);

						m_nearestNeighborSets.put(boundaryEndQueryObj, nearestTrueObjIdForBoundaryEnd);

						for (int i = m_objectIdClusters.get(index).indexOf(boundaryStartQueryObj)
								+ 1; i < m_objectIdClusters.get(index).size() - 1; i++) {
							int currentTrueObject = m_objectIdClusters.get(index).get(i);
							LinkedList<Integer> currentObjCluster = new LinkedList<Integer>();
							currentObjCluster.addAll(m_objectIdClusters.get(index));
							LinkedList<Integer> currentNodeCluster = new LinkedList<Integer>();
							currentNodeCluster.addAll(m_nodeIdClusters.get(index));

							double distToBoundaryStartObj = m_graph.getDistanceBetweenBoundaryObjAndCurrentObj(
									currentNodeCluster, currentObjCluster, boundaryStartQueryObj, currentTrueObject);
							double distToBoundaryEndObj = m_graph.getDistanceBetweenBoundaryObjAndCurrentObj(
									currentNodeCluster, currentObjCluster, boundaryEndQueryObj, currentTrueObject);

							double distanceFromCurrentObjectToBoundaryStartNearestFalseObject = nearestTrueObjDistBoundaryStart[0]
									+ distToBoundaryStartObj;
							double distanceFromCurrentObjectToBoundaryEndNearestFalseObject = nearestTrueObjDistBoundaryEnd[0]
									+ distToBoundaryEndObj;

							if (distanceFromCurrentObjectToBoundaryStartNearestFalseObject > distanceFromCurrentObjectToBoundaryEndNearestFalseObject) {
								m_nearestNeighborSets.put(currentTrueObject, nearestTrueObjIdForBoundaryEnd);
							} else {
								m_nearestNeighborSets.put(currentTrueObject, nearestTrueObjIdForBoundaryStart);
							}

						}
					}
				}
			}
		}
	}

	public void printNearestSets() {
		for (Map.Entry<Integer, Integer> entry : m_nearestNeighborSets.entrySet()) {
			System.out.println("Query obj: " + entry.getKey() + " - Data obj: " + entry.getValue());
		}
	}

	public int getSizeOfNodeClusters() {
		return sizeOfNodeClusters;

	}

	public int getSizeOfObjectClusters() {
		return sizeOfObjectClusters;

	}

}
