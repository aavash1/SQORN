package algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import framework.Graph;
import framework.RoadObject;

public class ANNClustered3 {

	Graph m_graph;

	// m_nearestNeighborSets: Map<QueryObjectId, DataObjectId>.
	private Map<Integer, Integer> m_nearestNeighborSets = new HashMap<Integer, Integer>();
	private Map<Integer, LinkedList<Integer>> m_objectIdClusters;
	private Map<Integer, LinkedList<Integer>> m_nodeIdClusters;

	public void compute(Graph gr, boolean queryObjectType) {
		System.out.println();
		System.out.println("Clustered ANN is running ... ");

		m_graph = gr;

		ClusteringNodes clusteringNodes = new ClusteringNodes();

		ClusteringRoadObjects clusteringObjects = new ClusteringRoadObjects();
		m_nodeIdClusters = clusteringNodes.cluster(gr);
		m_objectIdClusters = clusteringObjects.cluster2(gr, clusteringNodes.cluster(gr), queryObjectType);

		// m_nodeIdClusters = clusteringObjects.cluster(gr, clusteringNodes.cluster(gr),
		// queryObjectType);
		clusteringNodes.printNodeClusters();
		clusteringObjects.printRoadObjectClusters();

		NearestNeighbor nn = new NearestNeighbor();

		int boundaryStartQueryObj, boundaryEndQueryObj;

		if (queryObjectType) {
			// Query object = True Object; Data Object = False Object
			System.out.println("Query object = True Object (" + m_graph.getTotalNumberOfTrueObjects()
					+ "); Data Object = False Object (" + m_graph.getTotalNumberOfFalseObjects() + ")");

			// Iterate through boundary objects of object clusters
			for (Integer index : m_objectIdClusters.keySet()) {
				// System.out.println("Object Cluster # " + index + ": " +
				// m_objectIdClusters.get(index));

				if (!m_objectIdClusters.get(index).isEmpty()) {
					if (m_objectIdClusters.get(index).size() == 1) {
						int queryObj = m_objectIdClusters.get(index).getFirst();
						int nearestFalseObjId = nn.getNearestFalseObjectIdToGivenObjOnMap(m_graph, queryObj);

						m_nearestNeighborSets.put(queryObj, nearestFalseObjId);

					} else if (m_objectIdClusters.get(index).size() == 2) {
						boundaryStartQueryObj = m_objectIdClusters.get(index).getFirst();
						boundaryEndQueryObj = m_objectIdClusters.get(index).getLast();

						Map<RoadObject, Double> nearestFalseObjectForBoundaryBeginObject = nn
								.getNearestFalseObjectToGivenObjOnMap(gr, boundaryStartQueryObj);
						int nearestFalseObjForBeginingExit = 1089;
						// Dummy input for commit
						int nearestFalseObjForEndExit = 10;
						// The inserted data is dumy.
						m_nearestNeighborSets.put(boundaryStartQueryObj, nearestFalseObjForBeginingExit);
						m_nearestNeighborSets.put(boundaryEndQueryObj, nearestFalseObjForEndExit);

					} else {
						boundaryStartQueryObj = m_objectIdClusters.get(index).getFirst();
						int indexOfBeginningObject = m_objectIdClusters.get(index).indexOf(boundaryStartQueryObj);

						boundaryEndQueryObj = m_objectIdClusters.get(index).getLast();
						int indexOfEndObject = m_objectIdClusters.get(index).indexOf(boundaryEndQueryObj);
						System.out.println("The First Index is: " + indexOfBeginningObject + ", & Last Index is: "
								+ indexOfEndObject);

						int nearestFalseObjForBeginingExit = nn.getNearestFalseObjectIdToGivenObjOnMap(m_graph,
								boundaryStartQueryObj);
						int nearestFalseObjForEndExit = nn.getNearestFalseObjectIdToGivenObjOnMap(m_graph,
								boundaryEndQueryObj);

						// In Loop Put All Pairs of Query & Data Objects into Sets (Based on distance
						// between query objects)
						// m_nearestNeighborSets.put(trueObj.getObjectId(), nearestFalseObjId);
						m_nearestNeighborSets.put(boundaryStartQueryObj, nearestFalseObjForBeginingExit);
						// put other query objects that are close to beginingExitQueryObj
						// m_nearestNeighborSets.put(__, nearestFalseObjForBeginingExit);

						m_nearestNeighborSets.put(boundaryEndQueryObj, nearestFalseObjForEndExit);
						// put other query objects that are close to endExitQueryObj
						// m_nearestNeighborSets.put(__, nearestFalseObjForEndExit);
						for (int i = m_objectIdClusters.get(index).indexOf(boundaryStartQueryObj)
								+ 1; i < m_objectIdClusters.get(index).size() - 1; i++) {
							int currentTrueObject = m_objectIdClusters.get(index).get(i);
							LinkedList<Integer> currentObjCluster = new LinkedList<Integer>();
							currentObjCluster.addAll(m_objectIdClusters.get(index));
							LinkedList<Integer> currentNodeCluster = new LinkedList<Integer>();
							currentNodeCluster.addAll(m_nodeIdClusters.get(index));

							System.out.println("Current True Object is: " + currentTrueObject);
							System.out
									.println(
											"The nearest True object of " + m_objectIdClusters.get(index).get(i)
													+ " is " + nn
															.getNearestTrueObjectToGivenObjOnMap(m_graph,
																	m_objectIdClusters.get(index).get(i))
															.getObjectId());
							double distToBoundaryStartObj = m_graph.getDistanceBetweenBoundaryObjAndCurrentObj(
									currentNodeCluster, currentObjCluster, boundaryStartQueryObj, currentTrueObject);
							double distToBoundaryEndObj = m_graph.getDistanceBetweenBoundaryObjAndCurrentObj(
									currentNodeCluster, currentObjCluster, boundaryEndQueryObj, currentTrueObject);

							double distanceFromCurrentObjectToBeginNearestFalseObject = +distToBoundaryStartObj;
							double distanceFromCurrentObjectToEndNearestFalseObject = m_graph
									.getDistanceBetweenTwoObjectsOnEdge(boundaryEndQueryObj, nearestFalseObjForEndExit)
									+ distToBoundaryEndObj;
							if (distanceFromCurrentObjectToBeginNearestFalseObject > distanceFromCurrentObjectToEndNearestFalseObject) {
								m_nearestNeighborSets.put(currentTrueObject, nearestFalseObjForEndExit);
							} else {
								m_nearestNeighborSets.put(currentTrueObject, nearestFalseObjForBeginingExit);
							}
							// m_nearestNeighborSets.put(boundaryEndQueryObj, nearestFalseObjForEndExit);

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
				// System.out.println("Object Cluster # " + index + ": " +
				// m_objectIdClusters.get(index));
				if (m_objectIdClusters.get(index).size() > 1) {
					boundaryStartQueryObj = m_objectIdClusters.get(index).getFirst();
					boundaryEndQueryObj = m_objectIdClusters.get(index).getLast();
					int nearestFalseObjForBeginingExit = nn.getNearestTrueObjectIdToGivenObjOnMap(m_graph,
							boundaryStartQueryObj);
					int nearestFalseObjForEndExit = nn.getNearestTrueObjectIdToGivenObjOnMap(m_graph,
							boundaryEndQueryObj);

					/// In Loop Put All Pairs of Query & Data Objects into Sets (Based on distance
					// between query objects)
					// m_nearestNeighborSets.put(trueObj.getObjectId(), nearestFalseObjId);
					m_nearestNeighborSets.put(boundaryStartQueryObj, nearestFalseObjForBeginingExit);
					// put other query objects that are close to beginingExitQueryObj
					// m_nearestNeighborSets.put(__, nearestFalseObjForBeginingExit);

					m_nearestNeighborSets.put(boundaryEndQueryObj, nearestFalseObjForEndExit);
					// put other query objects that are close to endExitQueryObj
					// m_nearestNeighborSets.put(__, nearestFalseObjForEndExit);
				} else {
					int queryObj = m_objectIdClusters.get(index).getFirst();
					int nearestFalseObjId = nn.getNearestTrueObjectIdToGivenObjOnMap(m_graph, queryObj);
					m_nearestNeighborSets.put(queryObj, nearestFalseObjId);
				}
			}
		}
	}

	public void printNearestSets() {
		for (Map.Entry<Integer, Integer> entry : m_nearestNeighborSets.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}

}
