package testing;

import java.io.FileWriter;
import java.io.IOException;

public class HelloWorldTesting {

	public static void main(String[] args) {
		String evaluationResultTxtFile = "ResultFiles/test.txt";
		try {

			FileWriter outputFile = new FileWriter(evaluationResultTxtFile);

			outputFile.write(String.format("The total number of Nodes in Data set TOOOWO"));

			outputFile.write(System.lineSeparator()); // new line
			outputFile.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}
