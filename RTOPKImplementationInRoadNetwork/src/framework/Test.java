package framework;

import java.util.ArrayList;
import java.util.List;

import algorithm.RandomObjectGenerator;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		 * Poi poi1 = new Poi(); Poi poi2 = new Poi(); Poi poi3 = new Poi(); Poi poi4 =
		 * new Poi();
		 * 
		 * poi1.setPoiId(1); poi1.setDistanceFromStartNode(2); poi1.setType(true);
		 * 
		 * 
		 * poi2.setPoiId(1); poi2.setDistanceFromStartNode(2); poi2.setType(true);
		 * 
		 * 
		 * poi3.setPoiId(2); poi3.setDistanceFromStartNode(2); poi3.setType(true);
		 * 
		 * poi4.setPoiId(3); poi4.setDistanceFromStartNode(4); poi4.setType(true);
		 * 
		 * Graph gr = new Graph();
		 * 
		 * gr.printPoisOnEdge2(); //gr.printPoisInfo(); gr.addPoi(poi1, 1);
		 * //gr.printPoisInfo(); gr.printPoisOnEdge2(); gr.addPoi(poi2, 1);
		 * //gr.printPoisInfo(); gr.printPoisOnEdge2(); gr.addPoi(poi3, 1);
		 * //gr.printPoisInfo(); gr.printPoisOnEdge2(); gr.addPoi(poi4, 1);
		 * //gr.printPoisInfo(); gr.printPoisOnEdge2();
		 */

		Poi poi1 = new Poi();
		Poi poi2 = new Poi();
		Poi poi3 = new Poi();
		Poi poi4 = new Poi();
		Poi poi5 = new Poi();

		Graph2 gr = new Graph2();

//		poi1.setPoiId(100);
//		poi1.setDistanceFromStartNode(2.0);
//		poi1.setType(false);
//
//		poi2.setPoiId(101);
//		poi2.setDistanceFromStartNode(1.2);
//		poi2.setType(true);
//
//		poi3.setPoiId(102);
//		poi3.setDistanceFromStartNode(1.8);
//		poi3.setType(false);
//
//		poi4.setPoiId(103);
//		poi4.setDistanceFromStartNode(1);
//		poi4.setType(true);
//
//		poi5.setPoiId(100);
//		poi5.setDistanceFromStartNode(1.3);
//		poi5.setType(true);
//
//
//		gr.addObjectOnEdge(111, poi1);
//		gr.addObjectOnEdge(111, poi5);
//		// gr.printObjectOnEdge();
//
//		gr.addObjectOnEdge(112, poi2);
//		// gr.printObjectOnEdge();
//
//		gr.addObjectOnEdge(113, poi3);
//		gr.addObjectOnEdge(113, poi4);
//		gr.printObjectOnEdge();
//		
//		System.out.println("-------This is another Testing------");
//		
//		gr.addObjectOnEdge3(111, poi1);
//		gr.addObjectOnEdge3(111, poi5);
//		// gr.printObjectOnEdge();
//
//		gr.addObjectOnEdge3(112, poi2);
//		// gr.printObjectOnEdge();
//
//		gr.addObjectOnEdge3(113, poi3);
//		gr.addObjectOnEdge3(113, poi4);
//		gr.printObjectOnEdge3();
//		
//		
//		List<Poi> pois = new ArrayList<Poi>();
//		pois.add(poi1);
//		pois.add(poi2);
//		pois.add(poi3);
//		pois.add(poi4);
//		pois.add(poi5);
		
		RandomObjectGenerator ranG =new RandomObjectGenerator();
		
		gr.addEdge(1, 2, 80);
		gr.addEdge(1, 3, 25);
		gr.addEdge(2, 3, 20);
		gr.addEdge(3, 4, 50);
		gr.addEdge(3, 5, 40);
		gr.addEdge(4, 6, 13);
		gr.addEdge(6, 7, 60);
		
		
		
		gr.printGraph();
		gr.printObjectOnEdge3();
		gr.addObjectOnEdge3(1, poi1);
		
		
		ranG.generateObjectOnEdge(gr);
		System.out.println("---This is after generator------");
		gr.printGraph();
		gr.printObjectOnEdge3();
		
		}

}
