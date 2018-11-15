package com.jagex;
/* SoundEngine - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class SoundEngine
{
	public int[][][] anIntArrayArrayArray232;
	public static int[][] anIntArrayArray233 = new int[2][8];
	public static float aFloat234;
	public int[][][] anIntArrayArrayArray235 = new int[2][2][4];
	public int[] anIntArray236;
	public static int anInt237;
	public int[] anIntArray238;
	public static float[][] aFloatArrayArray239 = new float[2][8];
	
	public static void method112() {
		aFloatArrayArray239 = null;
		anIntArrayArray233 = null;
	}
	
	public final float method113(int i, int i_0_, float f) {
		float f_1_ = anIntArrayArrayArray235[i][0][i_0_] + f * (anIntArrayArrayArray235[i][1][i_0_] - anIntArrayArrayArray235[i][0][i_0_]);
		f_1_ *= 1.2207031E-4F;
		return method115(f_1_);
	}
	
	final int method114(int i, float f) {
		if (i == 0) {
			float f_2_ = anIntArray238[0] + (anIntArray238[1] - anIntArray238[0]) * f;
			f_2_ *= 0.0030517578F;
			aFloat234 = (float) Math.pow(0.1, f_2_ / 20.0F);
			anInt237 = (int) (aFloat234 * 65536.0F);
		}
		if (anIntArray236[i] == 0) {
			return 0;
		}
		float f_3_ = method116(i, 0, f);
		aFloatArrayArray239[i][0] = -2.0F * f_3_ * (float) Math.cos(method113(i, 0, f));
		aFloatArrayArray239[i][1] = f_3_ * f_3_;
		for (int i_4_ = 1; i_4_ < anIntArray236[i]; i_4_++) {
			f_3_ = method116(i, i_4_, f);
			float f_5_ = -2.0F * f_3_ * (float) Math.cos(method113(i, i_4_, f));
			float f_6_ = f_3_ * f_3_;
			aFloatArrayArray239[i][i_4_ * 2 + 1] = aFloatArrayArray239[i][i_4_ * 2 - 1] * f_6_;
			aFloatArrayArray239[i][i_4_ * 2] = aFloatArrayArray239[i][i_4_ * 2 - 1] * f_5_ + aFloatArrayArray239[i][i_4_ * 2 - 2] * f_6_;
			for (int i_7_ = i_4_ * 2 - 1; i_7_ >= 2; i_7_--)
				aFloatArrayArray239[i][i_7_] += aFloatArrayArray239[i][i_7_ - 1] * f_5_ + aFloatArrayArray239[i][i_7_ - 2] * f_6_;
			aFloatArrayArray239[i][1] += aFloatArrayArray239[i][0] * f_5_ + f_6_;
			aFloatArrayArray239[i][0] += f_5_;
		}
		if (i == 0) {
			for (int i_8_ = 0; i_8_ < anIntArray236[0] * 2; i_8_++)
				aFloatArrayArray239[0][i_8_] *= aFloat234;
		}
		for (int i_9_ = 0; i_9_ < anIntArray236[i] * 2; i_9_++)
			anIntArrayArray233[i][i_9_] = (int) (aFloatArrayArray239[i][i_9_] * 65536.0F);
		return anIntArray236[i] * 2;
	}
	
	public static final float method115(float f) {
		float f_10_ = 32.703197F * (float) Math.pow(2.0, f);
		return f_10_ * 3.1415927F / 11025.0F;
	}
	
	public SoundEngine() {
		anIntArrayArrayArray232 = new int[2][2][4];
		anIntArray236 = new int[2];
		anIntArray238 = new int[2];
	}
	
	public final float method116(int i, int i_11_, float f) {
		float f_12_ = anIntArrayArrayArray232[i][0][i_11_] + f * (anIntArrayArrayArray232[i][1][i_11_] - anIntArrayArrayArray232[i][0][i_11_]);
		f_12_ *= 0.0015258789F;
		return 1.0F - (float) Math.pow(10.0, -f_12_ / 20.0F);
	}
	
	final void method117(Packet class23_sub5, Class41 class41) {
		int i = class23_sub5.g1();
		anIntArray236[0] = i >> 4;
		anIntArray236[1] = i & 0xf;
		if (i != 0) {
			anIntArray238[0] = class23_sub5.g2();
			anIntArray238[1] = class23_sub5.g2();
			int i_13_ = class23_sub5.g1();
			for (int i_14_ = 0; i_14_ < 2; i_14_++) {
				for (int i_15_ = 0; i_15_ < anIntArray236[i_14_]; i_15_++) {
					anIntArrayArrayArray235[i_14_][0][i_15_] = class23_sub5.g2();
					anIntArrayArrayArray232[i_14_][0][i_15_] = class23_sub5.g2();
				}
			}
			for (int i_16_ = 0; i_16_ < 2; i_16_++) {
				for (int i_17_ = 0; i_17_ < anIntArray236[i_16_]; i_17_++) {
					if ((i_13_ & 1 << i_16_ * 4 << i_17_) != 0) {
						anIntArrayArrayArray235[i_16_][1][i_17_] = class23_sub5.g2();
						anIntArrayArrayArray232[i_16_][1][i_17_] = class23_sub5.g2();
					} else {
						anIntArrayArrayArray235[i_16_][1][i_17_] = anIntArrayArrayArray235[i_16_][0][i_17_];
						anIntArrayArrayArray232[i_16_][1][i_17_] = anIntArrayArrayArray232[i_16_][0][i_17_];
					}
				}
			}
			if (i_13_ != 0 || anIntArray238[1] != anIntArray238[0]) {
				class41.method1109(class23_sub5);
			}
		} else {
			anIntArray238[0] = anIntArray238[1] = 0;
		}
	}
}
