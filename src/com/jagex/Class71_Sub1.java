package com.jagex;
/* Class71_Sub1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import com.jagex.graphics.runetek4.media.Sprite;

class Class71_Sub1 extends Class71
{
	public static int anInt2725;
    static RSString aClass16_2728;
	static RSString aClass16_2730 = RSString.createString("settings");
	static RSString aClass16_2732;
	
	static final Sprite getSprite(Js5 class105, String class16, int i, String class16_0_) {
		int i_2_ = class105.get_groupid(class16);
		int i_3_ = 0;
		return Sprite.load_software_alpha(class105, i_2_, i_3_);
	}
	
	
	public static void method1270(boolean bool) {
		aClass16_2730 = null;
		FileSystem.cacheFileBuffer = null;
		aClass16_2728 = null;
		StaticMedia.cross = null;
		if (bool != false) {
			getSprite(null, null, -66, null);
		}
		aClass16_2732 = null;
	}
	
	static {
		aClass16_2728 = RSString.createString(":clanreq:");
		aClass16_2732 = RSString.createString("Standort");
	}
}
