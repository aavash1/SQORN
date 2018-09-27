package Framework;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class UtilsManagment {
	final String csvSplitBy = ",";

	// Method to read the vertex files from the datasets
	public ArrayList<Vertex> readVertexFiles(String csvFilename) {
		String line = "";
		ArrayList<Vertex> listVer = new ArrayList<Vertex>();

		try (BufferedReader br = new BufferedReader(new FileReader(csvFilename))) {
			while ((line = br.readLine()) != null) {
				String[] record = line.split(csvSplitBy);
				Vertex v = new Vertex();
				v.setM_strNodeId(record[0]);
				v.setM_doubLongitude(Double.parseDouble(record[1]));
				v.setM_doubLatitude(Double.parseDouble(record[2]));
				listVer.add(v);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return listVer;

	}

	// Method to read the Node files from the datasets
	public ArrayList<Edge> readEdgeFile(String csvFilename) {
		String line = "";
		ArrayList<Edge> listEd = new ArrayList<Edge>();

		try (BufferedReader br = new BufferedReader(new FileReader(csvFilename))) {
			while ((line = br.readLine()) != null) {
				String[] record = line.split(csvSplitBy);
				Edge ed = new Edge();
				ed.setM_strNodeId(record[0]);
				ed.setM_strSourceId(record[1]);
				ed.setM_strDestinationId(record[2]);
				ed.setM_doubDistance(Double.parseDouble(record[3]));
				listEd.add(ed);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return listEd;

	}

	// Method to read the POI files from the datasets
	public ArrayList<PointOfInterest> readPOIFile(String csvFilename) {
		String line = "";
		ArrayList<PointOfInterest> listPOI = new ArrayList<PointOfInterest>();

		try (BufferedReader br = new BufferedReader(new FileReader(csvFilename))) {
			while ((line = br.readLine()) != null) {
				String[] record = line.split(csvSplitBy);
				PointOfInterest poi = new PointOfInterest();
				
//				if(record.length <1) {
//					System.out.println("something"+record[0]);
//				}
				poi.setM_strCategory(record[0]);
				double Plongitude = Double.parseDouble(record[1]);
				double Platitude = Double.parseDouble(record[2]);

				poi.setM_doubLongitude(Plongitude);
				poi.setM_doubleLatitide(Platitude);
				listPOI.add(poi);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return listPOI;

	}

}
