package com.jagex;
/* Class23_Sub9 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class CS2Event extends Linkable
{
	public RSInterface aClass64_2255;
	static RSString aClass16_2256 = RSString.createString("blinken3:");
	public static int anInt2257;
	public int mouseY;
	static RSString aClass16_2259 = RSString.createString("lila:");
	public RSString opbase;
	public int keyCode;
	public boolean hasMouseListener;
	public RSInterface component;
	public int mouseX;
	public static int[] anIntArray2265;
	public int keyChar;
	static RSString aClass16_2267 = RSString.createString("T");
	public static int anInt2268;
	public int anInt2270;
	public Object[] scriptArguments;
	
	public static void method500(byte b) {
		aClass16_2256 = null;
		aClass16_2259 = null;
		LoginHandler.loginRequest = null;
		if (b != -14) {
			method500((byte) -93);
		}
		aClass16_2267 = null;
		anIntArray2265 = null;
	}
	
	static {
		anIntArray2265 = new int[128];
	}
}
