package com.jagex;
/* Class87_Sub3 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class87_Sub3 extends Class87
{
	public final int anInt2807;
	public final int anInt2811;
	public final int anInt2815;
	static RSString[] friends_worldname = new RSString[200];
	public final int anInt2817;
	public static int anInt2820;
	static RSString aClass16_2821;
	public static int firstSlot;
	static RSString aClass16_2823;
	public static RSString aClass16_2824;
	static RSString aClass16_2825;
	static RSString aClass16_2826;

	@Override
	public final void method1409(int i, int i_3_, int i_4_) {
		int i_5_ = anInt2811 * i >> 12;
		int i_6_ = i_4_ * anInt2815 >> 12;
		int i_7_ = anInt2807 * i >> 12;
		int i_8_ = anInt2817 * i_4_ >> 12;
		Class55.method1180(i_8_, i_5_, i_6_, i_7_, anInt1487, (byte) 122);
	}
	
	static final void method1417(byte b) {
		try {
			if (GroundObjEntity.anInt708 == 1) {
				int i = ModelList.aSomeSoundClass_1437.method556(0);
				if (i > 0 && ModelList.aSomeSoundClass_1437.method570(-92)) {
					i -= LocResult.anInt3720;
					if (i < 0) {
						i = 0;
					}
					ModelList.aSomeSoundClass_1437.method579(2, i);
					return;
				}
				ModelList.aSomeSoundClass_1437.method551((byte) 24);
				ModelList.aSomeSoundClass_1437.method583(-86);
				StaticMethods.aInstrumentDefinition_2911 = null;
				if (MusicPlayer.musicContainer != null) {
					GroundObjEntity.anInt708 = 2;
				} else {
					GroundObjEntity.anInt708 = 0;
				}
				StaticMethods.aClass23_Sub18_2952 = null;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			ModelList.aSomeSoundClass_1437.method551((byte) 24);
			StaticMethods.aClass23_Sub18_2952 = null;
			StaticMethods.aInstrumentDefinition_2911 = null;
			GroundObjEntity.anInt708 = 0;
			MusicPlayer.musicContainer = null;
		}
	}

	static final void method1419(RSInterface class64, boolean bool, int i, int i_10_, int i_11_, int i_12_, int i_13_, int i_14_) {
		if (!StaticMethods.aBoolean1867) {
			SongUpdater.anInt177 = 0;
		} else {
			SongUpdater.anInt177 = 32;
		}
		StaticMethods.aBoolean1867 = bool;
		if (SongUpdater.anInt175 != 0) {
			if (i > i_11_ || i_11_ >= 16 + i || i_13_ < i_12_ || (i_13_ ^ 0xffffffff) <= (i_12_ - -16 ^ 0xffffffff)) {
				if ((i ^ 0xffffffff) >= (i_11_ ^ 0xffffffff) && (i_11_ ^ 0xffffffff) > (16 + i ^ 0xffffffff) && (-16 + i_10_ + i_12_ ^ 0xffffffff) >= (i_13_ ^ 0xffffffff) && i_10_ + i_12_ > i_13_) {
					class64.scroll_y += 4;
					RSInterfaceList.setDirty(class64);
				} else if (i + -SongUpdater.anInt177 <= i_11_ && (i_11_ ^ 0xffffffff) > (i + 16 - -SongUpdater.anInt177 ^ 0xffffffff) && (16 + i_12_ ^ 0xffffffff) >= (i_13_ ^ 0xffffffff) && (-16 + i_12_ - -i_10_ ^ 0xffffffff) < (i_13_ ^ 0xffffffff)) {
					int i_15_ = (-32 + i_10_) * i_10_ / i_14_;
					if (i_15_ < 8) {
						i_15_ = 8;
					}
					int i_16_ = -(i_15_ / 2) + -16 + (i_13_ - i_12_);
					int i_17_ = -32 + i_10_ - i_15_;
					class64.scroll_y = i_16_ * (i_14_ + -i_10_) / i_17_;
					RSInterfaceList.setDirty(class64);
					StaticMethods.aBoolean1867 = true;
				}
			} else {
				class64.scroll_y -= 4;
				RSInterfaceList.setDirty(class64);
			}
		}
		if (Class48.anInt749 != 0) {
			int i_18_ = class64.layout_width;
			if (i_11_ >= i + -i_18_ && (i_13_ ^ 0xffffffff) <= (i_12_ ^ 0xffffffff) && (i - -16 ^ 0xffffffff) < (i_11_ ^ 0xffffffff) && (i_12_ + i_10_ ^ 0xffffffff) <= (i_13_ ^ 0xffffffff)) {
				class64.scroll_y += 45 * Class48.anInt749;
				RSInterfaceList.setDirty(class64);
			}
		}
	}
	
	public static void method1420(int i) {
		friends_worldname = null;
		aClass16_2823 = null;
		if (i <= -92) {
			aClass16_2824 = null;
			aClass16_2825 = null;
			aClass16_2821 = null;
			aClass16_2826 = null;
		}
	}
	
	Class87_Sub3(int i, int i_23_, int i_24_, int i_25_, int i_26_, int i_27_) {
		super(-1, i_26_, i_27_);
		anInt2817 = i_25_;
		anInt2807 = i;
		anInt2811 = i_24_;
		anInt2815 = i_23_;
	}

	@Override
	public final void method1406(int i, int i_28_, int i_29_) {
		if (i != 1) {
			method1420(115);
		}
	}

	@Override
	public void method1408(int i, int i_15_, int i_16_) {

	}

	static {
		anInt2820 = 0;
		aClass16_2821 = RSString.createString(" x ");
		firstSlot = 0;
		aClass16_2824 = RSString.createString("Zaros Information");
		aClass16_2823 = aClass16_2824;
		aClass16_2826 = RSString.createString("Ung-Ultiges Anmelde)2Paket)3");
		aClass16_2825 = RSString.createString("; version=1; path=)4; domain=");
	}
}
