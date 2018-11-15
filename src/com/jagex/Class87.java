package com.jagex;
/* Class87 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public abstract class Class87
{
	public int anInt1481;
	static RSString aClass16_1482;
	public static RSString aClass16_1483 = RSString.createString("Loaded sprites");
	static short aShort1484;
	public int anInt1487;
	static long aLong1489;
	static RSString aClass16_1493 = RSString.createString("Konfig geladen)3");
	public int anInt1494;

	public static final int method1403(int i, int i_0_, byte b, int i_1_) {
		int i_2_ = i_1_ / i_0_;
		int i_3_ = i / i_0_;
		int i_4_ = -1 + i_0_ & i;
		int i_5_ = i_0_ + -1 & i_1_;
		int i_6_ = MonochromeImageCacheSlot.method870(4, i_2_, i_3_);
		int i_7_ = MonochromeImageCacheSlot.method870(4, i_2_, 1 + i_3_);
		int i_8_ = MonochromeImageCacheSlot.method870(4, 1 + i_2_, i_3_);
		int i_9_ = MonochromeImageCacheSlot.method870(4, 1 + i_2_, 1 + i_3_);
		int i_10_ = WallDecoration.method918(i_4_, i_7_, i_6_, 0, i_0_);
		int i_11_ = WallDecoration.method918(i_4_, i_9_, i_8_, 0, i_0_);
		return WallDecoration.method918(i_5_, i_11_, i_10_, 0, i_0_);
	}
	
	public static void method1404(int i) {
		aClass16_1493 = null;
		if (i != -13896) {
			method1404(74);
		}
		PlayerIdentityKit.identityKitMap = null;
		aClass16_1483 = null;
		aClass16_1482 = null;
		MusicPlayer.musicContainer = null;
	}

	public abstract void method1406(int i, int i_13_, int i_14_);
	
	public static final Class87_Sub3 method1407(Packet class23_sub5, int i) {
		if (i != 0) {
			return null;
		}
		return new Class87_Sub3(class23_sub5.g2s(), class23_sub5.g2s(), class23_sub5.g2s(), class23_sub5.g2s(), class23_sub5.g3(), class23_sub5.g1());
	}

	public abstract void method1408(int i, int i_15_, int i_16_);

	public abstract void method1409(int i, int i_17_, int i_18_);

	public Class87(int i, int i_19_, int i_20_) {
		anInt1487 = i_19_;
		anInt1481 = i;
		anInt1494 = i_20_;
	}
	
	static {
		aLong1489 = 0L;
		aClass16_1482 = aClass16_1483;
		aShort1484 = (short) 256;
		PlayerIdentityKit.identityKitMap = new MemoryCache(64);
	}
}
