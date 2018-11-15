package com.jagex.graphics.runetek4.opengl.water;

public class WaterlakeBillowGenerator extends WaterGenerator {

	public int anInt3018;
	public int anInt3021;
	public int anInt3022;
	public int anInt3023;
	public int anInt3024;
	public byte[] aByteArray3025;
	public int anInt3026;
	public int anInt3028;
	public int anInt3029;

	public WaterlakeBillowGenerator(int var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8) {
		super(var1, var2, var3, var4, var5);
		anInt3022 = (int) (4096.0F * var8);
		anInt3026 = (int) (var7 * 4096.0F);
		anInt3018 = anInt3021 = (int) (Math.pow(0.5D, -var6) * 4096.0D);
	}

	public void method2244(int var1, byte var2) {
		aByteArray3025[var1] = var2;
	}

	@Override
	public void method2231(byte var1) {
		anInt3018 = anInt3021;
		anInt3029 >>= 4;
		if (0 > anInt3029) {
			anInt3029 = 0;
		} else if (255 < anInt3029) {
			anInt3029 = 255;
		}
		method2244(anInt3028++, (byte) anInt3029);
		anInt3029 = 0;
	}

	@Override
	public void method2233(int var1) {
		anInt3028 = 0;
		anInt3029 = 0;
	}

	@Override
	public void method2237(int var1, int var2, int var3) {
		if (0 != var2) {
			anInt3023 = anInt3022 * anInt3024 >> 12;
			if (-1 >= ~anInt3023) {
				if (~anInt3023 < -4097) {
					anInt3023 = 4096;
				}
			} else {
				anInt3023 = 0;
			}

			anInt3024 = -(-1 >= ~var1 ? var1 : -var1) + anInt3026;
			anInt3024 = anInt3024 * anInt3024 >> 12;
			anInt3024 = anInt3024 * anInt3023 >> 12;
			anInt3029 += anInt3018 * anInt3024 >> 12;
			anInt3018 = anInt3021 * anInt3018 >> 12;
		} else {
			anInt3023 = 4096;
			anInt3024 = -(-1 >= ~var1 ? var1 : -var1) + anInt3026;
			anInt3024 = anInt3024 * anInt3024 >> 12;
			anInt3029 = anInt3024;
		}
	}
}
