package com.jagex;
/* Class23_Sub7 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class23_Sub7 extends Linkable
{
	public int anInt2194;
	public int anInt2197;
	public Class23_Sub10_Sub1 aClass23_Sub10_Sub1_2198;
	public int anInt2199;
	static RSString aClass16_2200;
	static Class23_Sub10_Sub4 aClass23_Sub10_Sub4_2201;
	static RSString adminIcon = RSString.createString("<img=1>");
	public int anInt2204;
	public int anInt2205;
	public int anInt2207;
	public int anInt2208;
	public Class47 aClass47_2209;
	public int anInt2210;
	public SomeSoundClass2 aSomeSoundClass2_2211;
	public int anInt2212;
	public int anInt2214;
	public int anInt2215;
	public int anInt2217;
	public int anInt2218;
	public int anInt2220;
	public int anInt2222;
	public int anInt2223;
	public SoundEffects aSoundEffects_2224;
	public int anInt2225;
	static RSString aClass16_2226;
	
	public static void method494(int i) {
		aClass16_2226 = null;
		GameClient.random_file = null;
		if (i != -1) {
			GameClient.outBuffer = null;
		}
		aClass16_2200 = null;
		adminIcon = null;
		aClass23_Sub10_Sub4_2201 = null;
		GameClient.outBuffer = null;
	}
	
	final void method496(byte b) {
		aClass23_Sub10_Sub1_2198 = null;
		aSoundEffects_2224 = null;
		aSomeSoundClass2_2211 = null;
		if (b != -53) {
			anInt2212 = -13;
		}
		aClass47_2209 = null;
	}
	
	Class23_Sub7() {
		/* empty */
	}
	
	static {
		aClass16_2200 = RSString.createString("<col=ffff00>");
		GameClient.cross_type = 0;
		aClass16_2226 = RSString.createString("<)4col>");
		GameClient.outBuffer = new ISAACPacket(5000);
	}
}
