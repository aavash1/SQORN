package testing;

import org.decimal4j.util.DoubleRounder;

import framework.UtilsManagment;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.commons.math3.util.Precision;

public class randomNumberTest {
	public static void main(String[] args) {
		double minimumDist = 0.004049292657077970, calculatedMinDist;

		System.out.println(Math.round(minimumDist*10000.0)/10000.0);
		
//		double totalLength = 351.127114;
//		int param = 85865;
//
//		calculatedMinDist = totalLength / param;
//
//		System.out.println("min dist: " + minimumDist + " calculatedMinDist: " + calculatedMinDist);
//
//		// Precision.round(calculatedMinDist, 5);
//
//		// BigDecimal bd1 = new BigDecimal(Double.toString(minimumDist));
//		BigDecimal bd = new BigDecimal(Double.toString(calculatedMinDist));
//		bd = bd.setScale(17, RoundingMode.HALF_DOWN);
//		double newD = bd.doubleValue();
//		// double newD1 = bd1.doubleValue();
//		System.out.println("min dist: " + minimumDist + " calculatedMinDist: " + bd);
//
//		if (newD == minimumDist) {
//			System.out.println("Correct");
//		} else {
//			System.out.println("Differemce: " + (newD - minimumDist));
//		}
//		double totalLengthofDataset = 1958446.328;
//		int totalNumOdDObjs = 60000;
//
//		double newValue = Math.round((totalLengthofDataset / 387729) * 1000000000000000000.0) / 1000000000000000000.0;
//		System.out.println("newValue: " + newValue);
//
//		double minValueminimal = Double.MIN_VALUE;
//		System.out.println(minValueminimal);
//
//		double maxValueMax = Double.MAX_VALUE;
//		System.out.println(maxValueMax);
//		
		
		

	}

}
