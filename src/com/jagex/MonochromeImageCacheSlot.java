package com.jagex;
/* MonochromeImageCacheSlot - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class MonochromeImageCacheSlot extends Linkable
{
	public static RSString aClass16_2342 = RSString.createString("Starting 3d library");
	public static RSString nativeLibraryLoading = RSString.createString("Fetching native libraries");
	public int slot_id;
	static RSString aClass16_2345 = aClass16_2342;
	static RSString aClass16_2347 = RSString.createString("Die Adresse dieses Computers wurde gesperrt)1");
	static RSString aClass16_2348 = RSString.createString("Bitte versuchen Sie es erneut)3");
	static RSString aClass16_2349 = RSString.createString("weiss:");
	public int image_id;
	
	static final void method867(Js5 class105, int i) {
		Class25.enums_js5 = class105;
		if (i != 0) {
			method867(null, -37);
		}
	}
	
	public static void method868(int i) {
		aClass16_2347 = null;
		aClass16_2349 = null;
		aClass16_2345 = null;
		aClass16_2348 = null;
		aClass16_2342 = null;
	}
	
	static final void method869(int i) {
		if (i != -2415) {
			method867(null, -17);
		}
		GameClient.outBuffer.putOpcode(244);
		GameClient.outBuffer.putLong(0L, (byte) -114);
	}
	
	MonochromeImageCacheSlot(int i, int i_1_) {
		image_id = i;
		slot_id = i_1_;
	}
	
	static final int method870(int i, int i_2_, int i_3_) {
		if (i != 4) {
			method869(105);
		}
		int i_4_ = Class71_Sub3.method1288(-128, i_2_ + -1, i_3_ + -1) - -Class71_Sub3.method1288(-128, -1 + i_2_, 1 + i_3_) - -Class71_Sub3.method1288(-123, 1 + i_2_, i_3_ + -1) + Class71_Sub3.method1288(-124, 1 + i_2_, i_3_ - -1);
		int i_5_ = Class71_Sub3.method1288(-123, i_2_, -1 + i_3_) + Class71_Sub3.method1288(-128, i_2_, i_3_ + 1) + Class71_Sub3.method1288(-122, -1 + i_2_, i_3_) + Class71_Sub3.method1288(-125, i_2_ - -1, i_3_);
		int i_6_ = Class71_Sub3.method1288(-126, i_2_, i_3_);
		return i_6_ / 4 + i_5_ / 8 + i_4_ / 16;
	}
}
