package com.jagex.core.tools;

import java.util.Random;

import com.jagex.Class25;

/**
 * @author Walied K. Yassen
 */
public final class MathTools {

	public static int get_greater_pow2(int number) {
		number--;
		number = number | number >>> 1;
		number |= number >>> 2;
		number |= number >>> 4;
		number |= number >>> 8;
		number |= number >>> 16;
		return number + 1;
	}

	public static boolean is_power_of_two(int number) {
		return (-number & number) == number;
	}

	public MathTools() {

	}

	public static final int getRandom(Random random, int i_1_) {
		if ((i_1_ ^ 0xffffffff) >= -1) {
			throw new IllegalArgumentException();
		}
		if (Class25.method921(i_1_, (byte) 120)) {
			return (int) ((0xffffffffL & random.nextInt()) * i_1_ >> 32);
		}
		int i_2_ = -2147483648 + -(int) (4294967296L % i_1_);
		int i_3_;
		do {
			i_3_ = random.nextInt();
		} while (i_3_ >= i_2_);
		return MathTools.getRandom(1, i_3_, i_1_);
	}

	public static int getRandom(int i, int i_15_, int i_16_) {
		int i_17_ = i_15_ >> 31 & i_16_ - i;
		return (i_15_ + (i_15_ >>> 31)) % i_16_ - -i_17_;
	}

}
