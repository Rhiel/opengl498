package com.jagex;
/* Class87_Sub2 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class87_Sub2 extends Class87
{
	public final int anInt2792;
	public final int anInt2793;
	public static int anInt2794;
	public final int anInt2796;
	public final int anInt2800;
	static RSString aClass16_2805 = RSString.createString("<col=00ffff>");
	
	public static void method1412(byte b) {
		ObjType.itemModelCache = null;
		aClass16_2805 = null;
		PlayerIdentityKit.identityKitContainer = null;
	}
	
	
	@Override
	public final void method1408(int i, int i_0_, int i_1_) {
		if (i_1_ >= -101) {
			method1409(-80, 75, -40);
		}
		int i_2_ = anInt2800 * i >> 12;
		int i_3_ = i * anInt2793 >> 12;
		int i_4_ = anInt2792 * i_0_ >> 12;
		int i_5_ = anInt2796 * i_0_ >> 12;
		StaticMethods.method294(anInt1494, i_3_, anInt1481, i_2_, anInt1487, 2048, i_4_, i_5_);
	}

	@Override
	public final void method1409(int i, int i_6_, int i_7_) {
		int i_8_ = anInt2793 * i >> 12;
		int i_9_ = anInt2796 * i_7_ >> 12;
		int i_10_ = anInt2792 * i_7_ >> 12;
		int i_11_ = anInt2800 * i >> 12;
		if (i_6_ >= 31) {
			Class107.method1581(anInt1487, i_11_, (byte) -40, i_8_, i_9_, i_10_, anInt1494);
		}
	}
	
	Class87_Sub2(int i, int i_12_, int i_13_, int i_14_, int i_15_, int i_16_, int i_17_) {
		super(i_15_, i_16_, i_17_);
		anInt2800 = i_13_;
		anInt2792 = i_12_;
		anInt2793 = i;
		anInt2796 = i_14_;
	}

	@Override
	public final void method1406(int i, int i_18_, int i_19_) {
		int i_20_ = i_19_ * anInt2793 >> 12;
		int i_21_ = anInt2800 * i_19_ >> 12;
		int i_22_ = anInt2792 * i_18_ >> 12;
		int i_23_ = anInt2796 * i_18_ >> 12;
		if (i != 1) {
			aClass16_2805 = null;
		}
		StaticMethods2.method1358(i_22_, i_23_, i_20_, i_21_, anInt1481);
	}
}
