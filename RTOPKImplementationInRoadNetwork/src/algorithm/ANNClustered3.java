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

	public void compute(Graph gr, boolean queryObjectType) {
		System.out.println();
		System.out.println("Clustered ANN is running ... ");

		m_graph = gr;

		ClusteringNodes clusteringNodes = new ClusteringNodes();

		ClusteringRoadObjects clusteringObjects = new ClusteringRoadObjects();
		m_objectIdClusters = clusteringObjects.cluster(gr, clusteringNodes.cluster(gr), queryObjectType);
		clusteringNodes.printNodeClusters();
		clusteringObjects.printRoadObjectClusters();

		NearestNeighbor nn = new NearestNeighbor();

		int beginingExitQueryObj, endExitQueryObj;

		if (queryObjectType) {
			// Query object = True Object; Data Object = False Object
			System.out.println("Query object = True Object (" + m_graph.getTotalNumberOfTrueObjects()
					+ "); Data Object = False Object (" + m_graph.getTotalNumberOfFalseObjects() + ")");

			// Iterate through boundary objects of object clusters
			for (Integer index : m_objectIdClusters.keySet()) {
				// System.out.println("Object Cluster # " + index + ": " +
				// m_objectIdClusters.get(index));

				if (m_objectIdClusters.get(index).size() == 1) {
					int queryObj = m_objectIdClusters.get(index).getFirst();
					int nearestFalseObjId = nn.getNearestFalseObjectIdToGivenObjOnMap(m_graph, queryObj);
					m_nearestNeighborSets.put(queryObj, nearestFalseObjId);

				} else if (m_objectIdClusters.get(index).size() == 2) {
					beginingExitQueryObj = m_objectIdClusters.get(index).getFirst();
					endExitQueryObj = m_objectIdClusters.get(index).getLast();
					int nearestFalseObjForBeginingExit = nn.getNearestFalseObjectIdToGivenObjOnMap(m_graph,
							beginingExitQueryObj);
					int nearestFalseObjForEndExit = nn.getNearestFalseObjectIdToGivenObjOnMap(m_graph, endExitQueryObj);
					m_nearestNeighborSets.put(beginingExitQueryObj, nearestFalseObjForBeginingExit);
					m_nearestNeighborSets.put(endExitQueryObj, nearestFalseObjForEndExit);

				} else {
					beginingExitQueryObj = m_objectIdClusters.get(index).getFirst();
					int indexOfBeginningObject = m_objectIdClusters.get(index).indexOf(beginingExitQueryObj);

					endExitQueryObj = m_objectIdClusters.get(index).getLast();
					int indexOfEndObject = m_objectIdClusters.get(index).indexOf(endExitQueryObj);
					System.out.println(
							"The First Index is: " + indexOfBeginningObject + ", & Last Index is: " + indexOfEndObject);

					int nearestFalseObjForBeginingExit = nn.getNearestFalseObjectIdToGivenObjOnMap(m_graph,
							beginingExitQueryObj);
					int nearestFalseObjForEndExit = nn.getNearestFalseObjectIdToGivenObjOnMap(m_graph, endExitQueryObj);

					// In Loop Put All Pairs of Query & Data Objects into Sets (Based on distance
					// between query objects)
					// m_nearestNeighborSets.put(trueObj.getObjectId(), nearestFalseObjId);
					m_nearestNeighborSets.put(beginingExitQueryObj, nearestFalseObjForBeginingExit);
					// put other query objects that are close to beginingExitQueryObj
					// m_nearestNeighborSets.put(__, nearestFalseObjForBeginingExit);

					m_nearestNeighborSets.put(endExitQueryObj, nearestFalseObjForEndExit);
					// put other query objects that are close to endExitQueryObj
					// m_nearestNeighborSets.put(__, nearestFalseObjForEndExit);
					for (int i = m_objectIdClusters.get(index).indexOf(beginingExitQueryObj) + 1; i < m_objectIdClusters
							.get(index).size() - 1; i++) {
						System.out.println("The nearest True object of " + m_objectIdClusters.get(index).get(i) + " is "
								+ nn.getNearestTrueObjectToGivenObjOnMap(m_graph, m_objectIdClusters.get(index).get(i))
										.getObjectId());

					}
				}
			}

		} else {
			// Query object = False Object; Data Object = True Object
			System.out.println("Query object = False Object (" + m_graph.getTotalNumberOfFalseObjects()
					+ "); Data Object = True Object (" + m_graph.getTotalNumberOfTrueObjects() + ")");

			// Iterate through boundary objects of object clusters
			for (Integer index : m_objectIdClusters.keySet()) {
				// System.out.println("Object Cluster # " + index + ": " +
				// m_objectIdClusters.get(index));
				if (m_objectIdClusters.get(index).size() > 1) {
					beginingExitQueryObj = m_objectIdClusters.get(index).getFirst();
					endExitQueryObj = m_objectIdClusters.get(index).getLast();
					int nearestFalseObjForBeginingExit = nn.getNearestTrueObjectIdToGivenObjOnMap(m_graph,
							beginingExitQueryObj);
					int nearestFalseObjForEndExit = nn.getNearestTrueObjectIdToGivenObjOnMap(m_graph, endExitQueryObj);

					/// In Loop Put All Pairs of Query & Data Objects into Sets (Based on distance
					// between query objects)
					// m_nearestNeighborSets.put(trueObj.getObjectId(), nearestFalseObjId);
					m_nearestNeighborSets.put(beginingExitQueryObj, nearestFalseObjForBeginingExit);
					// put other query objects that are close to beginingExitQueryObj
					// m_nearestNeighborSets.put(__, nearestFalseObjForBeginingExit);

					m_nearestNeighborSets.put(endExitQueryObj, nearestFalseObjForEndExit);
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
