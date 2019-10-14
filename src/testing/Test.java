package testing;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;

public class Test {

	public static void main(String[] args) {

		writeToFile(179179);

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
}
