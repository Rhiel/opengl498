package com.jagex.graphics.runetek4.opengl.water;

import java.util.Random;

import com.jagex.core.tools.MathTools;

public abstract class WaterGenerator {

	public static final int[] anIntArray52 = new int[4096];

	public int anInt2062 = 4;
	public short[] aShortArray2047 = new short[512];
	public short[] aShortArray2049;
	public int anInt2054 = 0;
	public int anInt2056 = 4;
	public int anInt2057 = 4;
	public int anInt2060 = 4;

	public WaterGenerator(int var1, int var2, int var3, int var4, int var5) {
		anInt2056 = var4;
		anInt2062 = var2;
		anInt2054 = var1;
		anInt2060 = var5;
		anInt2057 = var3;
		method2232((byte) -60);
		method2236(-190126388);
	}

	static {
		for (int var0 = 0; var0 < 4096; ++var0) {
			anIntArray52[var0] = method2246((byte) 83, var0);
		}
	}

	public abstract void method2237(int var1, int var2, int var3);

	public abstract void method2233(int var1);

	public abstract void method2231(byte var1);

	public void method2230(int var1, int var2, int var3, int var4) {
		int[] var39 = new int[var2];
		int[] var40 = new int[var4];
		int[] var41 = new int[var3];
		for (int var42 = 0; ~var2 < ~var42; ++var42) {
			var39[var42] = (var42 << 12) / var2;
		}
		for (int var42 = 0; var42 < var4; ++var42) {
			var40[var42] = (var42 << 12) / var4;
		}
		for (int var42 = 0; var42 < var3; ++var42) {
			var41[var42] = (var42 << 12) / var3;
		}
		method2233(-949697716);
		for (int var37 = 0; var37 < var3; ++var37) {
			for (int var36 = 0; ~var4 < ~var36; ++var36) {
				for (int var35 = 0; var35 < var2; ++var35) {
					for (int var38 = 0; ~anInt2062 < ~var38; ++var38) {
						int var42 = aShortArray2049[var38] << 12;
						int var8 = anInt2057 * var42 >> 12;
						int var7 = var42 * var41[var37] >> 12;
						int var9 = var42 * anInt2056 >> 12;
						var7 *= anInt2060;
						int var10 = var42 * anInt2060 >> 12;
						int var5 = var39[var35] * var42 >> 12;
						var5 *= anInt2057;
						int var11 = var5 >> 12;
						var5 &= 4095;
						int var15 = var7 >> 12;
						int var20 = var5 + -4096;
						int var12 = var11 + 1;
						int var16 = var15 - -1;
						int var6 = var40[var36] * var42 >> 12;
						int var17 = anIntArray52[var5];
						var6 *= anInt2056;
						var15 &= 255;
						var11 &= 255;
						if (var10 <= var16) {
							var16 = 0;
						} else {
							var16 &= 255;
						}

						int var13 = var6 >> 12;
						short var23 = aShortArray2047[var15];
						short var24 = aShortArray2047[var16];
						int var14 = var13 + 1;
						if (var9 > var14) {
							var14 &= 255;
						} else {
							var14 = 0;
						}

						var6 &= 4095;
						short var28 = aShortArray2047[var14 - -var24];
						var7 &= 4095;
						var13 &= 255;
						int var19 = anIntArray52[var7];
						short var27 = aShortArray2047[var13 + var24];
						short var25 = aShortArray2047[var23 + var13];
						if (var8 <= var12) {
							var12 = 0;
						} else {
							var12 &= 255;
						}

						int var21 = -4096 + var6;
						int var18 = anIntArray52[var6];
						int var22 = var7 - 4096;
						short var26 = aShortArray2047[var23 + var14];
						int var29 = WaterGenerator.method1788(var5, var7, var6, aShortArray2047[var25 + var11], true);
						int var30 = WaterGenerator.method1788(var20, var7, var6, aShortArray2047[var12 - -var25], true);
						int var31 = var29 - -(var17 * (var30 - var29) >> 12);
						var29 = WaterGenerator.method1788(var5, var7, var21, aShortArray2047[var26 + var11], true);
						var30 = WaterGenerator.method1788(var20, var7, var21, aShortArray2047[var12 + var26], true);
						int var32 = var29 + (var17 * (-var29 + var30) >> 12);
						int var33 = ((-var31 + var32) * var18 >> 12) + var31;
						var29 = WaterGenerator.method1788(var5, var22, var6, aShortArray2047[var11 + var27], true);
						var30 = WaterGenerator.method1788(var20, var22, var6, aShortArray2047[var12 + var27], true);
						var31 = (var17 * (var30 + -var29) >> 12) + var29;
						var29 = WaterGenerator.method1788(var5, var22, var21, aShortArray2047[var11 - -var28], true);
						var30 = WaterGenerator.method1788(var20, var22, var21, aShortArray2047[var12 + var28], true);
						var32 = var29 + ((-var29 + var30) * var17 >> 12);
						int var34 = var31 - -((var32 + -var31) * var18 >> 12);
						method2237(((-var33 + var34) * var19 >> 12) + var33, var38, -20975);
					}
					method2231((byte) -92);
				}
			}
		}
	}

	public void method2232(byte var1) {
		aShortArray2049 = new short[anInt2062];
		int var2 = 0;
		if (var1 != -60) {
			method2234(-92, 105);
		}

		while (~anInt2062 < ~var2) {
			aShortArray2049[var2] = (short) (int) Math.pow(2.0D, var2);
			++var2;
		}
	}

	public void method2236(int var1) {
		Random var2 = new Random(anInt2054);
		for (int var3 = 0; var3 < 255; ++var3) {
			aShortArray2047[var3] = (short) var3;
		}
		for (int var3 = 0; var3 < 255; ++var3) {
			int var4 = -var3 + 255;
			int var5 = method1603((byte) -120, var4, var2);
			short var6 = aShortArray2047[var5];
			aShortArray2047[var5] = aShortArray2047[var4];
			aShortArray2047[var4] = aShortArray2047[256 + var4] = var6;
		}

	}

	public static int method1788(int var0, int var1, int var2, int var3, boolean var4) {
		if (!var4) {
			return 127;
		} else {
			int var5 = 15 & var3;
			int var7 = -5 >= ~var5 ? ~var5 != -13 && -15 != ~var5 ? var1 : var0 : var2;
			int var6 = ~var5 > -9 ? var0 : var2;
			return (-1 != ~(var5 & 1) ? -var6 : var6) - -(~(2 & var5) != -1 ? -var7 : var7);
		}
	}

	public static int method2246(byte var0, int var1) {
		int var2 = var1 * (var1 * var1 >> 12) >> 12;
		int var3 = 6 * var1 - '\uf000';
		int var4 = (var1 * var3 >> 12) + '\ua000';
		return var2 * var4 >> 12;
	}

	public static int method1603(byte var0, int var1, Random var2) {
		if (~var1 >= -1) {
			throw new IllegalArgumentException();
		} else if (MathTools.is_power_of_two(var1)) {
			return (int) ((var2.nextInt() & 4294967295L) * var1 >> 32);
		} else {
			int var3 = -((int) (4294967296L % var1)) + Integer.MIN_VALUE;
			int var4;
			do {
				var4 = var2.nextInt();
			} while (var3 <= var4);
			return method201(var4, var1, -58);
		}
	}

	public static int method201(int var0, int var1, int var2) {
		int var3 = var1 + -1 & var0 >> 31;
		return var3 + (var0 + (var0 >>> 31)) % var1;
	}

	public static int method2234(int var0, int var1) {
		return 1023 & var0;
	}

}
