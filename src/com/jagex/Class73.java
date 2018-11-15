package com.jagex;
/* Class73 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class73 {
	public static NodeDeque aClass89_1316;
	public static RSString aClass16_1320;
	public static int anInt1321;
	public static RSString aClass16_1322;
	public static RSString aClass16_1323;
	public static int anInt1325;
	public static RSString aClass16_1326;
	public static RSString aClass16_1327;

	public static void method1310(int i) {
		aClass16_1322 = null;
		GameClient.index_datafs = null;
		aClass16_1323 = null;
		aClass16_1326 = null;
		aClass16_1327 = null;
		aClass89_1316 = null;
		if (i != 8) {
			aClass16_1320 = null;
		}
		aClass16_1320 = null;
	}

	public static final Class87_Sub2 method1312(Packet class23_sub5, int i) {
		if (i != 23090) {
			return null;
		}
		return new Class87_Sub2(class23_sub5.g2s(), class23_sub5.g2s(), class23_sub5.g2s(), class23_sub5.g2s(), class23_sub5.g3(), class23_sub5.g3(), class23_sub5.g1());
	}

	static {
		aClass89_1316 = new NodeDeque();
		aClass16_1320 = RSString.createString("Created gameworld");
		aClass16_1322 = RSString.createString("null");
		anInt1325 = 1;
		aClass16_1323 = RSString.createString("sich mit einer anderen Welt zu verbinden)3");
		aClass16_1327 = aClass16_1320;
		aClass16_1326 = RSString.createString("60 Sekunden noch einmal)3)3)3");
	}
}
