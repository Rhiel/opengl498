package com.jagex;
/* Class49 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import com.jagex.graphics.runetek4.software.sprite.SoftwareSprite;

public class Class49
{
	static RSString aClass16_753 = RSString.createString("(U5");
	static SoftwareSprite aClass23_Sub13_Sub10_Sub1_754;
    static int anInt759;
	static RSString aClass16_760 = RSString.createString("Texturen geladen)3");
	public static int secondSlot = 0;
	static RSString aClass16_762;
	static RSString aClass16_763;
	static RSString aClass16_764 = null;
	static RSString aClass16_765 = RSString.createString("Verbindung konnte nicht hergestellt werden)3");
	
	static final boolean method1152(int i, int i_0_) {
		if (i != -1) {
			method1152(22, 69);
		}
		if ((0x1 & i_0_ >> 31 ^ 0xffffffff) == -1) {
			return false;
		}
		return true;
	}

	public static void method1154(int i) {
		aClass23_Sub13_Sub10_Sub1_754 = null;
		aClass16_753 = null;
		aClass16_760 = null;
		aClass16_765 = null;
		aClass16_764 = null;
		aClass16_763 = null;
		aClass16_762 = null;
		if (i >= -66) {
			aClass16_764 = null;
		}
	}
	
	static {
		aClass16_763 = RSString.createString("Nehmen");
		aClass16_762 = RSString.createString("Lade)3)3)3");
	}
}
