package algorithm;

import java.util.ArrayList;
import java.util.Random;

import framework.Edge;
import framework.Graph2;
import framework.Poi;

public class RandomObjectGenerator {

	private int m_totalNumberofEdge;
	private int m_totalNumerofNode;
	private int m_totalNumberofObject, m_totalNumberofDataObject, m_intTotalNumberofQueryObject,
			m_maxNumberofObjectPerEdge;
	private double m_minDistanceBetweenNodeandObject, m_minDistanceBetweenObject;

	public void generateObjectOnEdge(Graph2 graph2) {

		boolean uniformDataObjects = false;
		boolean uniformQueryObjects = false;
		// Object Type: false = data object ; true = query object;
		/////////////////////////////////////

		boolean uniformObjects = false;

		int edgeId;
		int poiId;
		double distanceFromStartNode;
		boolean objectType;
		double edgeLength;
		int totalNumberOfEdges = graph2.getNumberOfEdges();
		double minDistanceBetPois = 1;
		int totalNumberOfPois = (int) (graph2.getTotalLengthOfAllEdges() / minDistanceBetPois - 1);
		ArrayList<Poi> poiList = new ArrayList<Poi>();
		Poi listPoi[] = new Poi[totalNumberOfPois];
		ArrayList<Integer> usedPoiId = new ArrayList<Integer>();

		Random rand = new Random();
		for (int i = 0; i < listPoi.length; i++) {

			edgeId = rand.nextInt(totalNumberOfEdges);
			
			edgeLength = graph2.getEdgeDistance(edgeId);
			listPoi[i] = new Poi();
			listPoi[i].setPoiId(i * 10);
			// Prevent 0 distance
			distanceFromStartNode = Math.round(rand.nextDouble() * edgeLength* 100000.0) / 100000.0;

			listPoi[i].setDistanceFromStartNode(distanceFromStartNode);
			listPoi[i].setType(rand.nextBoolean());

			graph2.addObjectOnEdge3(edgeId, listPoi[i]);
		}

	}

}
