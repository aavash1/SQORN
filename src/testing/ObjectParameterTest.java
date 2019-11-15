package testing;

import framework.UtilsManagment;

public class ObjectParameterTest {

	public static void main(String[] args) {
		String edgeDatasetFileCal = "Datasets/CAL-Edge_Eid-ESrc-EDest-EDist.csv";
		
		int objParamForCal30000 = UtilsManagment.getObjectParameter(edgeDatasetFileCal, 30000);
		System.out.println("objParamForCal30000: " + objParamForCal30000);
		int objParamForCal40000 = UtilsManagment.getObjectParameter(edgeDatasetFileCal, 40000);
		System.out.println("objParamForCal40000: " + objParamForCal40000);
		int objParamForCal60000 = UtilsManagment.getObjectParameter(edgeDatasetFileCal, 60000);
		System.out.println("objParamForCal60000: " + objParamForCal60000);
		int objParamForCal80000 = UtilsManagment.getObjectParameter(edgeDatasetFileCal, 80000);
		System.out.println("objParamForCal80000: " + objParamForCal80000);
		int objParamForCal1100000 = UtilsManagment.getObjectParameter(edgeDatasetFileCal, 1100000);
		System.out.println("objParamForCal1100000: " + objParamForCal1100000);
		
		
		String edgeDatasetFileSanf = "Datasets/SANF-Edge_Eid-ESrc-EDest-EDist.csv";
		
		int objParamForSanf30000 = UtilsManagment.getObjectParameter(edgeDatasetFileSanf, 30000);
		System.out.println("objParamForSanf30000: " + objParamForSanf30000);
		int objParamForSanf40000 = UtilsManagment.getObjectParameter(edgeDatasetFileSanf, 40000);
		System.out.println("objParamForSanf40000: " + objParamForSanf40000);
		int objParamForSanf60000 = UtilsManagment.getObjectParameter(edgeDatasetFileSanf, 60000);
		System.out.println("objParamForSanf60000: " + objParamForSanf60000);
		int objParamForSanf80000 = UtilsManagment.getObjectParameter(edgeDatasetFileSanf, 80000);
		System.out.println("objParamForSanf80000: " + objParamForSanf80000);
		int objParamForSanf1100000 = UtilsManagment.getObjectParameter(edgeDatasetFileSanf, 1100000);
		System.out.println("objParamForSanf1100000: " + objParamForSanf1100000);
		
	}

}
