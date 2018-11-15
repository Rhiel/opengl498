package com.jagex;
/* Class55 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class55 {
	static RSString aClass16_864;
	public static int[] anIntArray865;
	public static RSString aClass16_866 = RSString.createString(" from your friend list first)3");
	public static RSString aClass16_868;
	static RSString slash;
	static RSString aClass16_869;
	public static int anInt875;
	public static int[] anIntArray876;
	public static int[] anIntArray877;
	public static int[] anIntArray878;
	public static int[] anIntArray880;
	static RSString aClass16_881;
	public static int[] anIntArray882;
	public static int[] anIntArray883;
	static RSString[] aClass16Array884;
	public static int[] anIntArray885;

	public static void method1179(int i) {
		aClass16Array884 = null;
		aClass16_881 = null;
		aClass16_868 = null;
		anIntArray877 = null;
		anIntArray865 = null;
		anIntArray885 = null;
		aClass16_869 = null;
		anIntArray883 = null;
		anIntArray880 = null;
		anIntArray882 = null;
		aClass16_866 = null;
		anIntArray876 = null;
		anIntArray878 = null;
		aClass16_864 = null;
	}

	static final void method1180(int i, int i_0_, int i_1_, int i_2_, int i_3_, byte b) {
		int i_4_ = i_0_ - i_2_;
		int i_5_ = i + -i_1_;
		if (i_4_ != 0) {
			if ((i_5_ ^ 0xffffffff) == -1) {
				StaticMethods.method393(1, i_2_, i_1_, i_0_, i_3_);
				return;
			}
		} else {
			if (i_5_ != 0) {
				StaticMethods2.method683(i_1_, i_3_, i, -1, i_2_);
			}
			return;
		}
		int i_6_ = (i_5_ << 12) / i_4_;
		int i_7_ = -(i_6_ * i_2_ >> 12) + i_1_;
		if (b >= 115) {
			int i_8_;
			int i_9_;
			if ((i_0_ ^ 0xffffffff) > (VarpDefinition.anInt3728 ^ 0xffffffff)) {
				i_8_ = VarpDefinition.anInt3728;
				i_9_ = (VarpDefinition.anInt3728 * i_6_ >> 12) + i_7_;
			} else if ((i_0_ ^ 0xffffffff) < (Class35.anInt554 ^ 0xffffffff)) {
				i_8_ = Class35.anInt554;
				i_9_ = (i_6_ * Class35.anInt554 >> 12) + i_7_;
			} else {
				i_8_ = i_0_;
				i_9_ = i;
			}
			int i_10_;
			int i_11_;
			if ((VarpDefinition.anInt3728 ^ 0xffffffff) >= (i_2_ ^ 0xffffffff)) {
				if (Class35.anInt554 < i_2_) {
					i_10_ = Class35.anInt554;
					i_11_ = i_7_ - -(i_6_ * Class35.anInt554 >> 12);
				} else {
					i_10_ = i_2_;
					i_11_ = i_1_;
				}
			} else {
				i_10_ = VarpDefinition.anInt3728;
				i_11_ = i_7_ - -(i_6_ * VarpDefinition.anInt3728 >> 12);
			}
			if ((Class88.anInt1503 ^ 0xffffffff) >= (i_11_ ^ 0xffffffff)) {
				if ((i_11_ ^ 0xffffffff) < (StaticMethods.anInt3435 ^ 0xffffffff)) {
					i_10_ = (StaticMethods.anInt3435 - i_7_ << 12) / i_6_;
					i_11_ = StaticMethods.anInt3435;
				}
			} else {
				i_11_ = Class88.anInt1503;
				i_10_ = (-i_7_ + Class88.anInt1503 << 12) / i_6_;
			}
			if (Class88.anInt1503 <= i_9_) {
				if (StaticMethods.anInt3435 < i_9_) {
					i_8_ = (-i_7_ + StaticMethods.anInt3435 << 12) / i_6_;
					i_9_ = StaticMethods.anInt3435;
				}
			} else {
				i_8_ = (-i_7_ + Class88.anInt1503 << 12) / i_6_;
				i_9_ = Class88.anInt1503;
			}
			UpdateServerNode.method863(i_10_, i_9_, i_3_, i_11_, (byte) -88, i_8_);
		}
	}

	public static final int method1181(byte b, int i, int i_12_, int i_13_) {
		i_13_ &= 0x3;
		if ((i_13_ ^ 0xffffffff) == -1) {
			return i;
		}
		@SuppressWarnings("unused")
		int i_14_ = 33 % ((37 - b) / 55);
		if (i_13_ == 1) {
			return i_12_;
		}
		if (i_13_ == 2) {
			return -i + 7;
		}
		return 7 - i_12_;
	}


	static {
		anIntArray865 = new int[100];
		aClass16_868 = RSString.createString("k");
		slash = RSString.createString("/");
		aClass16_869 = RSString.createString("Verbindung mit Update)2Server)3)3)3");
		aClass16_864 = aClass16_866;
		anInt875 = 50;
		anIntArray876 = new int[anInt875];
		anIntArray877 = new int[anInt875];
		anIntArray882 = new int[anInt875];

		aClass16_881 = RSString.createString("Fps:");
		anIntArray885 = new int[anInt875];
		anIntArray878 = new int[anInt875];
		anIntArray880 = new int[anInt875];
		anIntArray883 = new int[anInt875];
		aClass16Array884 = new RSString[anInt875];
	}
}
