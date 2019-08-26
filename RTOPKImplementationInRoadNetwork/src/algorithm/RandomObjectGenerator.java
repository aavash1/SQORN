package algorithm;

import java.util.ArrayList;
import java.util.Random;

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
		//Object Type: false = data object ; true = query object;
		/////////////////////////////////////
		boolean uniformObjects = false;
		
		int edge_id;
		int poi_id;
		double distance_from_start_Node;
		boolean objectType;

		int limit = 15;
		ArrayList<Poi> poiList = new ArrayList<Poi>();
		Poi listPoi[] = new Poi[limit];

		Random rand = new Random();
		for (int i = 0; i < listPoi.length; i++) {
			listPoi[i] = new Poi();
			listPoi[i].setPoiId(i * 10);
			listPoi[i].setDistanceFromStartNode(rand.nextDouble() * 10);
			listPoi[i].setType(rand.nextBoolean());

			graph2.addObjectOnEdge3(rand.nextInt(8), listPoi[i]);
		}

	}

}
