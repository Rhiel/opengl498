package com.jagex;

/**
 * Created by Chris on 3/31/2017.
 */
public class MathUtils {

	public static final int[] SINE = new int[16384];
	public static final int[] COSINE = new int[16384];

	static {
		double d = 3.834951969714103E-4;
		for (int i = 0; i < 16384; i++) {
			SINE[i] = (int) (16384.0 * Math.sin(i * d));
			COSINE[i] = (int) (16384.0 * Math.cos(i * d));
		}
	}

	public static int bitAnd(int value, int secondValue) {
		return value & secondValue;
	}

	public static int doBitwiseOr(int i, int i_1_) {
		return i | i_1_;
	}

	public static int power(int base, int exponent) {
		return base ^ exponent;
	}
}
