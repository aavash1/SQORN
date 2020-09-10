package testing;

import algorithm.VivetAlgorithm;
import framework.Graph;
import framework.RoadObject;
import framework.UtilsManagment;

public class VivetAlgorithmManualDatasetTest {

	public static void main(String[] args) {
		
		String generatedDatasetForDijkstra1 = "Datasets/TextFiles/DatasetForDijkstra1.txt";
		Graph roadNetwork = UtilsManagment.readEdgeFileReturnGraph(generatedDatasetForDijkstra1);
//		roadNetwork.printGraph();
//		System.out.println();
//		roadNetwork.printEdgesInfo();
//		System.out.println();
//		roadNetwork.printNodesInfo();
//		System.out.println();
				
		RoadObject rObj11= new RoadObject();
		rObj11.setObjId(11);
		rObj11.setDistanceFromStartNode(100.0);
		rObj11.setType(false);
		roadNetwork.addObjectOnEdge(1, rObj11);
		
		RoadObject rObj31= new RoadObject();
		rObj31.setObjId(31);
		rObj31.setDistanceFromStartNode(50.0);
		rObj31.setType(true);
		roadNetwork.addObjectOnEdge(3, rObj31);
		
		RoadObject rObj61= new RoadObject();
		rObj61.setObjId(61);
		rObj61.setDistanceFromStartNode(200.0);
		rObj61.setType(false);
		roadNetwork.addObjectOnEdge(6, rObj61);
		
		RoadObject rObj71= new RoadObject();
		rObj71.setObjId(71);
		rObj71.setDistanceFromStartNode(60.0);
		rObj71.setType(true);
		roadNetwork.addObjectOnEdge(7, rObj71);
		
		RoadObject rObj81= new RoadObject();
		rObj81.setObjId(81);
		rObj81.setDistanceFromStartNode(110.0);
		rObj81.setType(false);
		roadNetwork.addObjectOnEdge(8, rObj81);
		
		RoadObject rObj91= new RoadObject();
		rObj91.setObjId(91);
		rObj91.setDistanceFromStartNode(70.0);
		rObj91.setType(true);
		roadNetwork.addObjectOnEdge(9, rObj91);
		
		RoadObject rObj92= new RoadObject();
		rObj92.setObjId(92);
		rObj92.setDistanceFromStartNode(300.0);
		rObj92.setType(false);
		roadNetwork.addObjectOnEdge(9, rObj92);
		
		RoadObject rObj101= new RoadObject();
		rObj101.setObjId(101);
		rObj101.setDistanceFromStartNode(15.0);
		rObj101.setType(true);
		roadNetwork.addObjectOnEdge(10, rObj101);
		
		//roadNetwork.printObjectsOnEdges();
		
		VivetAlgorithm vivetAlg = new VivetAlgorithm();
		long startTimeVivet = System.nanoTime();
		vivetAlg.compute(roadNetwork);
		long vivetAlgTime = System.nanoTime() - startTimeVivet;
		double vivetAlgTimeD = (double) vivetAlgTime / 1000000000.0;
		System.out.println("Time to compute Vivet ANN: " + vivetAlgTimeD);
		
		
	}

}
