package com.jagex;
/* Class100 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class100 {
	static RSString aClass16_1682;
	static RSString aClass16_1684;
	public static int anInt1686 = 0;
	static RSString aClass16_1687;

	static final void method1503(int i, int i_7_, int i_8_, int i_9_, boolean bool, int i_10_) {
		if (bool != true) {
			aClass16_1682 = null;
		}
		int i_11_ = StaticMethods.method405(72, Class88.anInt1503, i, StaticMethods.anInt3435);
		int i_12_ = StaticMethods.method405(92, Class88.anInt1503, i_9_, StaticMethods.anInt3435);
		int i_13_ = StaticMethods.method405(69, VarpDefinition.anInt3728, i_7_, Class35.anInt554);
		int i_14_ = StaticMethods.method405(107, VarpDefinition.anInt3728, i_10_, Class35.anInt554);
		for (int i_15_ = i_11_; (i_15_ ^ 0xffffffff) >= (i_12_ ^ 0xffffffff); i_15_++) {
			VarpDefinition.method632(i_14_, (byte) -30, i_8_, Class4.anIntArrayArray98[i_15_], i_13_);
		}
	}

	static final int method1504(int i, int i_16_) {
		int i_17_ = 0;
		if ((i_16_ ^ 0xffffffff) > -1 || i_16_ >= 65536) {
			i_16_ >>>= 16;
			i_17_ += 16;
		}
		if (i_16_ >= 256) {
			i_16_ >>>= 8;
			i_17_ += 8;
		}
		if (i_16_ >= 16) {
			i_16_ >>>= 4;
			i_17_ += 4;
		}
		if (i_16_ >= 4) {
			i_16_ >>>= 2;
			i_17_ += 2;
		}
		if (i != 8) {
			return 5;
		}
		if (i_16_ >= 1) {
			i_17_++;
			i_16_ >>>= 1;
		}
		return i_16_ + i_17_;
	}

	public static void method1505(byte b) {
		aClass16_1682 = null;
		aClass16_1684 = null;
		aClass16_1687 = null;
		if (b >= -126) {
			aClass16_1684 = null;
		}
	}

	static {
		aClass16_1684 = RSString.createString("<col=ff0000>");
		aClass16_1682 = RSString.createString(" steht bereits auf Ihrer Ignorieren)2Liste(Q");
		aClass16_1687 = RSString.createString("Sie k-Onnen sich selbst nicht auf Ihre Freunde)2Liste setzen(Q");
	}
}
