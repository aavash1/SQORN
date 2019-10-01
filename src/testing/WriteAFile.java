package testing;

//import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WriteAFile {

	public static void main(String[] args) throws IOException {
		
		
		try {
			//To write in the same line.
//			String csvFile = "C:\\Users\\Aavash\\Desktop\\newCSVFile.csv";
//			CSVWriter writer = new CSVWriter(new FileWriter(csvFile));
//			String[] country = "westIndiex#India#Australia#NewZealand#Pakistan".split("#");
//			writer.writeNext(country);
//			System.out.println("CSV file written successfully line by line");
//			writer.close();
			
			//To write in multiple column.
			String csvFile = "C:\\Users\\Aavash\\Desktop\\newCSVFile.csv";
			CSVWriter writer = new CSVWriter(new FileWriter(csvFile));
			List<String[]> data = new ArrayList<String[]>();
			data.add(new String[] { "WestIndies", "Run 150" });
			data.add(new String[] { "India", "Run 152" });
			data.add(new String[] { "Srilanka", "Run 20" });
			data.add(new String[] { "NZ", "Run 115" });
			data.add(new String[] { "Pakistan", "Run 15" });
			data.add(new String[] { "WestIndies", "Run 15" });
			writer.writeAll(data);
			System.out.println("Successfuylly weritten");
			writer.close();
			
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}