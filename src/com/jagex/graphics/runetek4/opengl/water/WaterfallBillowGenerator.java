package com.jagex.graphics.runetek4.opengl.water;
public class WaterfallBillowGenerator extends WaterGenerator {

	public int anInt3010;
	public int[] anIntArray3014;
	public byte[] aByteArray3015;
	public int anInt3016;

	public WaterfallBillowGenerator(int var1, int var2, int var3, int var4, int var5, float var6) {
		super(var1, var2, var3, var4, var5);
		anIntArray3014 = new int[anInt2062];
		for (int var7 = 0; ~anInt2062 < ~var7; ++var7) {
			anIntArray3014[var7] = (short) (int) (Math.pow(var6, var7) * 4096.0D);
		}
	}

	public void method2242(int var1, byte var2) {
		aByteArray3015[anInt3016++] = (byte) (127 + ((var2 & 0xff) >> 1));
	}

	@Override
	public void method2231(byte var1) {
		anInt3010 = Math.abs(anInt3010);
		if (anInt3010 >= 4096) {
			anInt3010 = 4095;
		}
		method2242(anInt3016++, (byte) (anInt3010 >> 4));
		anInt3010 = 0;
	}

	@Override
	public void method2233(int var1) {
		anInt3016 = 0;
		anInt3010 = 0;
	}

	@Override
	public void method2237(int var1, int var2, int var3) {
		anInt3010 += var1 * anIntArray3014[var2] >> 12;
	}

}
