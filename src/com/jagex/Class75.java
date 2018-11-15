package com.jagex;
/* Class75 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class75
{
	public static int anInt1372;
	static boolean qa_op_test;
	static RSString aClass16_1374 = RSString.createString("cookieprefix");
	public static int[] anIntArray1375;
	public static int anInt1376 = 0;
	public static int anInt1382;
	static byte[] aClass8343;
	
	public static void method1316(int i) {
		InvType.invTypeContainer = null;
		if (i == 0) {
			anIntArray1375 = null;
			aClass16_1374 = null;
		}
	}
	
	static {
		anInt1372 = 0;
		qa_op_test = false;
		GameClient.setMembers(true);// fuck with this and yur sum orb is gone..
		anIntArray1375 = new int[] { 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3 };
		anInt1382 = 1;
		aClass8343 = new byte[] {105, 111, 114, 101, 103, 32, 45, 108, 32, 124, 32, 97, 119, 107, 32, 39, 47, 73, 79, 80, 108, 97, 116, 102, 111, 114, 109, 83, 101, 114, 105, 97, 108, 78, 117, 109, 98, 101, 114, 47, 32, 123, 32, 112, 114, 105, 110, 116, 32, 36, 52, 59, 125, 39};
	}
}
