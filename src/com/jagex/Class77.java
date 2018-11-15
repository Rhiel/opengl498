package com.jagex;
/* Class77 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class77
{
	public int anInt1401;
	public int[] anIntArray1402;
	public float[][] aFloatArrayArray1403;
	public int anInt1404;
	public int[] anIntArray1405;
	public int[] anIntArray1406;
	
	final int method1338() {
		int i;
		for (i = 0; anIntArray1406[i] >= 0; i = Class23_Sub3.method252() != 0 ? anIntArray1406[i] : i + 1) {
			/* empty */
		}
		return anIntArray1406[i] ^ 0xffffffff;
	}
	
	public static final int method1339(int i, int i_0_) {
		int i_1_;
		for (i_1_ = (int) Math.pow(i, 1.0 / i_0_) + 1; StaticMethods.method306(115, i_0_, i_1_) > i; i_1_--) {
			/* empty */
		}
		return i_1_;
	}
	
	final float[] method1340() {
		return aFloatArrayArray1403[method1338()];
	}
	
	public final void method1341() {
		int[] is = new int[anInt1401];
		int[] is_2_ = new int[33];
		for (int i = 0; i < anInt1401; i++) {
			int i_3_ = anIntArray1402[i];
			if (i_3_ != 0) {
				int i_4_ = 1 << 32 - i_3_;
				int i_5_ = is_2_[i_3_];
				is[i] = i_5_;
				int i_6_;
				if ((i_5_ & i_4_) != 0) {
					i_6_ = is_2_[i_3_ - 1];
				} else {
					i_6_ = i_5_ | i_4_;
					for (int i_7_ = i_3_ - 1; i_7_ >= 1; i_7_--) {
						int i_8_ = is_2_[i_7_];
						if (i_8_ != i_5_) {
							break;
						}
						int i_9_ = 1 << 32 - i_7_;
						if ((i_8_ & i_9_) != 0) {
							is_2_[i_7_] = is_2_[i_7_ - 1];
							break;
						}
						is_2_[i_7_] = i_8_ | i_9_;
					}
				}
				is_2_[i_3_] = i_6_;
				for (int i_10_ = i_3_ + 1; i_10_ <= 32; i_10_++) {
					int i_11_ = is_2_[i_10_];
					if (i_11_ == i_5_) {
						is_2_[i_10_] = i_6_;
					}
				}
			}
		}
		anIntArray1406 = new int[8];
		int i = 0;
		for (int i_12_ = 0; i_12_ < anInt1401; i_12_++) {
			int i_13_ = anIntArray1402[i_12_];
			if (i_13_ != 0) {
				int i_14_ = is[i_12_];
				int i_15_ = 0;
				for (int i_16_ = 0; i_16_ < i_13_; i_16_++) {
					int i_17_ = -2147483648 >>> i_16_;
					if ((i_14_ & i_17_) != 0) {
						if (anIntArray1406[i_15_] == 0) {
							anIntArray1406[i_15_] = i;
						}
						i_15_ = anIntArray1406[i_15_];
					} else {
						i_15_++;
					}
					if (i_15_ >= anIntArray1406.length) {
						int[] is_18_ = new int[anIntArray1406.length * 2];
						for (int i_19_ = 0; i_19_ < anIntArray1406.length; i_19_++)
							is_18_[i_19_] = anIntArray1406[i_19_];
						anIntArray1406 = is_18_;
					}
					i_17_ >>>= 1;
				}
				anIntArray1406[i_15_] = i_12_ ^ 0xffffffff;
				if (i_15_ >= i) {
					i = i_15_ + 1;
				}
			}
		}
	}
	
	Class77() {
		Class23_Sub3.method250(24);
		anInt1404 = Class23_Sub3.method250(16);
		anInt1401 = Class23_Sub3.method250(24);
		anIntArray1402 = new int[anInt1401];
		boolean bool = Class23_Sub3.method252() != 0;
		if (bool) {
			int i = 0;
			int i_20_ = Class23_Sub3.method250(5) + 1;
			while (i < anInt1401) {
				int i_21_ = Class23_Sub3.method250(Class100.method1504(8, anInt1401 - i));
				for (int i_22_ = 0; i_22_ < i_21_; i_22_++)
					anIntArray1402[i++] = i_20_;
				i_20_++;
			}
		} else {
			boolean bool_23_ = Class23_Sub3.method252() != 0;
			for (int i = 0; i < anInt1401; i++) {
				if (bool_23_ && Class23_Sub3.method252() == 0) {
					anIntArray1402[i] = 0;
				} else {
					anIntArray1402[i] = Class23_Sub3.method250(5) + 1;
				}
			}
		}
		method1341();
		int i = Class23_Sub3.method250(4);
		if (i > 0) {
			float f = Class23_Sub3.method249(Class23_Sub3.method250(32));
			float f_24_ = Class23_Sub3.method249(Class23_Sub3.method250(32));
			int i_25_ = Class23_Sub3.method250(4) + 1;
			boolean bool_26_ = Class23_Sub3.method252() != 0;
			int i_27_;
			if (i == 1) {
				i_27_ = method1339(anInt1401, anInt1404);
			} else {
				i_27_ = anInt1401 * anInt1404;
			}
			anIntArray1405 = new int[i_27_];
			for (int i_28_ = 0; i_28_ < i_27_; i_28_++)
				anIntArray1405[i_28_] = Class23_Sub3.method250(i_25_);
			aFloatArrayArray1403 = new float[anInt1401][anInt1404];
			if (i == 1) {
				for (int i_29_ = 0; i_29_ < anInt1401; i_29_++) {
					float f_30_ = 0.0F;
					int i_31_ = 1;
					for (int i_32_ = 0; i_32_ < anInt1404; i_32_++) {
						int i_33_ = i_29_ / i_31_ % i_27_;
						float f_34_ = anIntArray1405[i_33_] * f_24_ + f + f_30_;
						aFloatArrayArray1403[i_29_][i_32_] = f_34_;
						if (bool_26_) {
							f_30_ = f_34_;
						}
						i_31_ *= i_27_;
					}
				}
			} else {
				for (int i_35_ = 0; i_35_ < anInt1401; i_35_++) {
					float f_36_ = 0.0F;
					int i_37_ = i_35_ * anInt1404;
					for (int i_38_ = 0; i_38_ < anInt1404; i_38_++) {
						float f_39_ = anIntArray1405[i_37_] * f_24_ + f + f_36_;
						aFloatArrayArray1403[i_35_][i_38_] = f_39_;
						if (bool_26_) {
							f_36_ = f_39_;
						}
						i_37_++;
					}
				}
			}
		}
	}
}
