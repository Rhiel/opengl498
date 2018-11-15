package com.jagex.graphics.runetek4.opengl.water;

public class WaterfallDiffuseGenerator extends WaterfallBillowGenerator {

	public byte[] aByteArray4028;

	public WaterfallDiffuseGenerator() {
		super(12, 5, 16, 2, 2, 0.45F);
	}

	@Override
	public void method2242(int var1, byte var2) {
		int var3 = var1 * 2;
		var2 = (byte) (127 + ((var2 & 255) >> 1));
		aByteArray4028[var3++] = var2;
		aByteArray4028[var3] = var2;
	}

	public byte[] method2243(int var1, int var2, int var3) {
		aByteArray4028 = new byte[var1 * var2 * var3 * 2];
		method2230(-95, var1, var3, var2);
		return aByteArray4028;
	}
}
