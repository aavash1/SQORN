package algorithm;

import java.util.Random;

import framework.Graph2;

public class RandomObjectGenerator {


	public double minDistanceBetweenObjects = 1.0;
	public int maxNumberOfObjectsPerEdge = 5; 
	public int totalNumberOfObjectsOnMap = 30000;
	
	
	public void generateRandomObject(Graph2 rn)
	{
		
		//get random edge
		//get random number of objects for this edge
			//get random object type
			//get random distance from SN
		
		//consider total number of objects on map
		//consider ratio of data objects and quesry objects
		Random r = new Random();
		int totalNumberOfEdges = rn.getEdgesWithInfo().size();
		int randomNumberOfEdges = r.nextInt(totalNumberOfEdges);
		
		for (int i = 0; i < randomNumberOfEdges; i++) {
			
			rn.getEdgesWithDistances(r.nextInt(totalNumberOfEdges));
			
			//rn.
		}
		
	}
	
	
	
}
