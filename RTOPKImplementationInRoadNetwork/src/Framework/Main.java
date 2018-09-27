package Framework;

public class Main {
	public static void main(String[]args) {
		UtilsManagment um=new UtilsManagment();
		
		um.readVertexFiles("CALNodeLongLat.csv");
		System.out.println("Files Imported Successfully!");
		
		um.readEdgeFile("CALNoSouDesDis.csv");
		System.out.println("Files Imported Successfully!");
		
		um.readPOIFile("CategoryLongLat.csv");
		System.out.println("Files Imported Successfully!");

				
	}

}
