package com.jagex;
/* Class4 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class4 {
	static RSString aClass16_87;
	public static int anInt88;
	static RSString aClass16_90;
	static RSString aClass16_91;
	static RSString aClass16_92;
	public static RSString aClass16_93;
	public static int anInt96 = 2;
	public static int[][] anIntArrayArray98;
	static RSString aClass16_99;
	static boolean fetching_sprites;
	public static RSString aClass16_101;

	static final void method58(int i, int i_0_) {
		if (i_0_ >= -106) {
			aClass16_87 = null;
		}
		if (StaticMethods.anIntArray3183 == null || StaticMethods.anIntArray3183.length < i) {
			StaticMethods.anIntArray3183 = new int[i];
		}
	}

	public static void method59(int i) {
		anIntArrayArray98 = null;
		aClass16_93 = null;
		aClass16_91 = null;
		aClass16_87 = null;
		aClass16_101 = null;
		MapLoader.collision_maps = null;
		aClass16_92 = null;
		aClass16_90 = null;
		aClass16_99 = null;
	}

	static final void method61(int i) {
		for (int i_2_ = -1; (StaticMethods.anInt3067 ^ 0xffffffff) < (i_2_ ^ 0xffffffff); i_2_++) {
			int i_3_;
			if ((i_2_ ^ 0xffffffff) == 0) {
				i_3_ = 2047;
			} else {
				i_3_ = GameClient.localPlayerPointers[i_2_];
			}
			Player class38_sub7_sub2 = GameClient.localPlayers[i_3_];
			if (class38_sub7_sub2 != null && (class38_sub7_sub2.anInt2639 ^ 0xffffffff) < -1) {
				class38_sub7_sub2.anInt2639--;
				if (class38_sub7_sub2.anInt2639 == 0) {
					class38_sub7_sub2.aClass16_2670 = null;
				}
			}
		}
		for (int i_4_ = i; (i_4_ ^ 0xffffffff) > (EntityUpdating.localNPCCount ^ 0xffffffff); i_4_++) {
			int i_5_ = EntityUpdating.localNPCIndexes[i_4_];
			NPC class38_sub7_sub1 = GameClient.activeNPCs[i_5_];
			if (class38_sub7_sub1 != null && (class38_sub7_sub1.anInt2639 ^ 0xffffffff) < -1) {
				class38_sub7_sub1.anInt2639--;
				if ((class38_sub7_sub1.anInt2639 ^ 0xffffffff) == -1) {
					class38_sub7_sub1.aClass16_2670 = null;
				}
			}
		}
	}

	static {
		anInt88 = -1;
		aClass16_87 = RSString.createString("und loggen sich dann erneut ein)3");
		aClass16_93 = RSString.createString("Please wait)3)3)3");
		aClass16_91 = RSString.createString("Abbrechen");
		aClass16_92 = aClass16_93;
		fetching_sprites = false;
		aClass16_101 = RSString.createString("green:");
		aClass16_90 = aClass16_101;
		aClass16_99 = aClass16_101;
	}
}
