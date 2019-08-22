package framework;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Poi poi1 = new Poi();
		Poi poi2 = new Poi();
		Poi poi3 = new Poi();
		Poi poi4 = new Poi();
		
		poi1.setPoiId(1);
		poi1.setDistanceFromStartNode(2);
		poi1.setType(true);
		
		
		poi2.setPoiId(1);
		poi2.setDistanceFromStartNode(2);
		poi2.setType(true);
		
		
		poi3.setPoiId(2);
		poi3.setDistanceFromStartNode(2);
		poi3.setType(true);
		
		poi4.setPoiId(3);
		poi4.setDistanceFromStartNode(4);
		poi4.setType(true);
		
		Graph gr = new Graph();
		
		gr.printPoisOnEdge2();
		//gr.printPoisInfo();
		gr.addPoi(poi1, 1);
		//gr.printPoisInfo();
		gr.printPoisOnEdge2();
		gr.addPoi(poi2, 1);
		//gr.printPoisInfo();
		gr.printPoisOnEdge2();
		gr.addPoi(poi3, 1);
		//gr.printPoisInfo();
		gr.printPoisOnEdge2();
		gr.addPoi(poi4, 1);
		//gr.printPoisInfo();
		gr.printPoisOnEdge2();
	}

}
