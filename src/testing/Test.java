package testing;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.annotation.Generated;

import com.opencsv.CSVWriter;

import framework.Graph;
import framework.UtilsManagment;

public class Test {

	public static void main(String[] args) {
		Graph gr=new Graph();
		
		String nodeDatasetFile = "Datasets/SANF-Node_NId-NLong-NLat.csv";
		String edgeDatasetFile = "Datasets/SANF-Edge_Eid-ESrc-EDest-EDist.csv";

		UtilsManagment.readNodeFile(gr, nodeDatasetFile);
		UtilsManagment.readEdgeFile(gr, edgeDatasetFile);
		
//		double intendednumber = 518332.133324001;
//		ArrayList<Integer> parameters = new ArrayList<Integer>();
//		parameters.add(27000);
//		parameters.add(37000);
//		parameters.add(57000);
//		parameters.add(77000);
//		parameters.add(107000);
//		parameters.add(30000);
//		parameters.add(40000);
//		parameters.add(60000);
//		parameters.add(80000);
//		parameters.add(110000);
		gr.getTotalLengthOfAllEdges();
		System.out.println("Ttoal Graph length: "+gr.getTotalLengthOfAllEdges());

//		for (int i = 0; i < parameters.size(); i++) {
//			double minDistBetweenObjects = Math.round((intendednumber / parameters.get(i)) * 100000.0) / 100000.0;
//			System.out
//					.println("min distance between object for: " + parameters.get(i) + " is " + minDistBetweenObjects);
//
//		}

	}

	private static void writeToFile(int number) {
		String roadObjsOnEdgeCSVFile = "GeneratedFiles/custom" + ".txt";

		try {

			FileWriter outputFile = new FileWriter(roadObjsOnEdgeCSVFile);

			for (int i = 0; i < number; i++) {
				String a = String.valueOf(i);
				outputFile.write(a);

				outputFile.write(System.lineSeparator()); // new line
			}

			outputFile.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private static void generateRandomTrueFalseObj() {
		Random rand = new Random();

		ArrayList<Boolean> values = new ArrayList<Boolean>();

		for (int i = 0; i < 5; i++) {
			values.add(true);
		}
		for (int i = 0; i < 10; i++) {
			values.add(false);
		}

		System.out.println("Size of values: " + values.size());
		System.out.println(values);
		System.out.println(" ");
		System.out.println("after shuffling");
		Collections.shuffle(values);
		System.out.println(values);
		System.out.println("-------------------------------------------");
		ArrayList<Boolean> newValues = new ArrayList<Boolean>();
		for (int i = 0; i < values.size(); i++) {
			int randomIndex = rand.nextInt(values.size());
			newValues.add(values.get(randomIndex));

		}
		System.out.println("Size of values: " + newValues.size());
		System.out.println(newValues);
		System.out.println("after shuffling");
		Collections.shuffle(newValues);
		System.out.println(newValues);

	}
}
