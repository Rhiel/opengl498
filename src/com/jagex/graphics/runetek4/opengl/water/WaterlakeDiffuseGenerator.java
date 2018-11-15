package com.jagex.graphics.runetek4.opengl.water;

public class WaterlakeDiffuseGenerator extends WaterlakeBillowGenerator {

	public byte[] bytes;

	public WaterlakeDiffuseGenerator() {
		super(8, 5, 8, 8, 2, 0.1F, 0.55F, 3.0F);
	}

	public byte[] method2250(int var1, int var2, int var3) {
		bytes = new byte[var1 * var2 * var3 * 2];
		method2230(-98, var1, var3, var2);
		return bytes;
	}

	@Override
	public void method2244(int var1, byte var2) {
		int off = var1 * 2;
		int value = var2 & 255;
		bytes[off++] = (byte) (3 * value >> 5);
		bytes[off] = (byte) (value >> 2);
	}
}
