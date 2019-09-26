package algorithm;

import java.io.FileWriter;
import java.util.Random;

import framework.Graph;

public class UserPreferenceGenerator {
	private int numOfUsers;
	private int numOfAttributes; // We here only pass 2 as the attributes will be only Distance and Rating
	private char separator = ',';

	public void generateRandomUserPreference(int userCount, int attribCount) {
		this.numOfUsers = userCount;
		this.numOfAttributes = attribCount;
		try {
			FileWriter fw = new FileWriter("userpreference.txt");
			Double[][] columns = new Double[numOfUsers][numOfAttributes];

			double randomValue1;
			double randomvalue2;

			for (int i = 0; i < columns.length; i++) {
				for (int j = 0; j < columns[i].length; j += 2) {

					Random rand = new Random();
					int some = rand.nextInt(101);
					randomValue1 = some / 100.0;
					randomvalue2 = 1.0 - randomValue1;
					double roundOff = Math.round(randomvalue2 * 100.0) / 100.0;
					columns[i][j] = randomValue1;
					columns[i][1] = roundOff;
					fw.write(String.valueOf(columns[i][j]));
					fw.write(separator);
					fw.write(String.valueOf(columns[i][j + 1]));

				}
				fw.write("\n");

			}
			fw.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		System.out.println("Success..");
	}
	
	
	
	
	
	
	
	
	
	

}
