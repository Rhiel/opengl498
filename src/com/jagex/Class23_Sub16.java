package com.jagex;
/* Class23_Sub16 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class23_Sub16 extends Linkable
{
	public static RSString aClass16_2351 = RSString.createString(": ");
	static RSString aClass16_2353;
	static RSString aClass16_2354;
	static RSString aClass16_2355 = RSString.createString("Spieler)3 Bitte w-=hlen Sie eine andere Welt)3");
	static RSString aClass16_2356;
	static RSString[] aClass16Array2357 = new RSString[100];
	public byte[] aByteArray2359;
	public static RSString aClass16_2360;
	static RSString aClass16_2361;
	
	public static void method871(int i) {
		aClass16_2356 = null;
		aClass16_2354 = null;
		LoginHandler.world_switch_button = null;
		aClass16_2361 = null;
		aClass16_2351 = null;
		aClass16_2360 = null;
		aClass16Array2357 = null;
		aClass16_2353 = null;
		aClass16_2355 = null;
		if (i != 100) {
			aClass16Array2357 = null;
		}
	}
	
	Class23_Sub16(byte[] bs) {
		aByteArray2359 = bs;
	}
	
	static {
		aClass16_2356 = RSString.createString("blaugr-Un:");
		aClass16_2353 = RSString.createString("Schrifts-=tze geladen)3");
		aClass16_2360 = RSString.createString("You can visit the forums for more information");
		aClass16_2354 = aClass16_2360;
		aClass16_2361 = RSString.createString("Keine Antwort vom Server)3");
	}
}
