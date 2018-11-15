package com.jagex;
/* Class23_Sub8 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class ObjectNode extends Linkable
{
	public int anInt2227;
	public Class23_Sub10_Sub1 aClass23_Sub10_Sub1_2228;
	public int anInt2229;
	static ISAACPacket aClass23_Sub5_Sub1_2230 = new ISAACPacket(5000);
	public Class23_Sub10_Sub1 aClass23_Sub10_Sub1_2232;
	public int anInt2233;
	public LocType objectDef;
    public int anInt2236;
	static RSString aClass16_2238;
	public int[] anIntArray2239;
	public int anInt2240;
	public int anInt2241;
	public int anInt2242;
	public static RSString aClass16_2243 = RSString.createString("You can(Wt add yourself to your own friend list)3");
	public static int[] anIntArray2245 = { 0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535, 131071, 262143, 524287, 1048575, 2097151, 4194303, 8388607, 16777215, 33554431, 67108863, 134217727, 268435455, 536870911, 1073741823, 2147483647, -1 };
	public int anInt2246;
	public static int anInt2251;
	public int anInt2253;
	public int anInt2254;

	public static final void method336() {
		for (ObjectNode class23_sub8 = (ObjectNode) Js5.aClass89_1767.get_first(); class23_sub8 != null; class23_sub8 = (ObjectNode) Js5.aClass89_1767.get_next()) {
			if (class23_sub8.aClass23_Sub10_Sub1_2228 != null) {
				Class23_Sub7.aClass23_Sub10_Sub4_2201.method595(class23_sub8.aClass23_Sub10_Sub1_2228);
				class23_sub8.aClass23_Sub10_Sub1_2228 = null;
			}
			if (class23_sub8.aClass23_Sub10_Sub1_2232 != null) {
				Class23_Sub7.aClass23_Sub10_Sub4_2201.method595(class23_sub8.aClass23_Sub10_Sub1_2232);
				class23_sub8.aClass23_Sub10_Sub1_2232 = null;
			}
		}
		Js5.aClass89_1767.clear();
	}

	final void method497(byte b) {
		if (b >= 12) {
			int i = anInt2227;
			LocType def = objectDef.morph();
			if (def != null) {
				anInt2241 = def.anInt3782;
				anInt2227 = def.anInt2996;
				anInt2233 = def.anInt2981 * 128;
				anIntArray2239 = def.anIntArray3801;
				anInt2236 = def.anInt3755;
			} else {
				anIntArray2239 = null;
				anInt2227 = -1;
				anInt2233 = 0;
				anInt2236 = 0;
				anInt2241 = 0;
			}
			if ((anInt2227 ^ 0xffffffff) != (i ^ 0xffffffff) && aClass23_Sub10_Sub1_2228 != null) {
				Class23_Sub7.aClass23_Sub10_Sub4_2201.method595(aClass23_Sub10_Sub1_2228);
				aClass23_Sub10_Sub1_2228 = null;
			}
		}
	}
	
	public static void method499(byte b) {
		MapLoader.square_loc_data = null;
		aClass23_Sub5_Sub1_2230 = null;
		aClass16_2238 = null;
		aClass16_2243 = null;
		anIntArray2245 = null;
		if (b != -3) {
			method499((byte) -101);
		}
	}
	
	ObjectNode() {
		/* empty */
	}
	
	static {
		aClass16_2238 = aClass16_2243;
		anInt2251 = 0;
		GameClient.setGameType(0);
	}
}
