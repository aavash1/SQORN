package Framework;

public class Main {
	public static void main(String[] args) {
		UtilsManagment um = new UtilsManagment();

		um.readVertexFiles("CALNodeLongLat.csv");
		System.out.println("Vertex File Imported Successfully!");

		um.readEdgeFile("CALNoSouDesDis.csv");
		System.out.println("Edge File Imported Successfully!");

//		um.readPOIFile("CategoryLongLat.csv");
//		System.out.println("POI File Imported Successfully!");

		um.readPOIFile2("CALPOILonLatCatID.csv");
		System.out.println("POI file with Id Imported Successfully");
		
		um.populatePOIMap("CALDataset(CategoryCategoryId).csv");
		um.populatePOIMap("CAL-CategoryData.csv");
		um.displayPOIHmap();

	}

}
