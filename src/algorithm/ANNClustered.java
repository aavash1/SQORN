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
				if (m_objectIdClusters.get(index).size() > 1) {
					beginingExitQueryObj = m_objectIdClusters.get(index).getFirst();
					endExitQueryObj = m_objectIdClusters.get(index).getLast();
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

				} else {
					int queryObj = m_objectIdClusters.get(index).getFirst();
					int nearestFalseObjId = nn.getNearestFalseObjectIdToGivenObjOnMap(m_graph, queryObj);
					m_nearestNeighborSets.put(queryObj, nearestFalseObjId);
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

}
