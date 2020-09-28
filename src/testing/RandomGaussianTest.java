package testing;

import java.util.Random;

public final class RandomGaussianTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RandomGaussianTest gaussian = new RandomGaussianTest();

		double MEAN = 10.0f;
		double VARAIANCE = 1.0f;
		for (int idx = 1; idx <= 15; idx++) {
			log("Generated : "+gaussian.getGaussianInt(MEAN, VARAIANCE));

		}

	}

	private Random fRandom = new Random();

	private double getGaussian(double aMean, double aVariance) {
		return aMean + fRandom.nextGaussian() * aVariance;
	}
	
	private int getGaussianInt(double aMean, double aVariance) {
		int x= (int)(aMean + fRandom.nextGaussian() * aVariance);
		return x;
	}

	private static void log(Object aMsg) {
		System.out.println(String.valueOf(aMsg));
	}

}
