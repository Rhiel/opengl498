package com.jagex;
/* CS2CallFrame - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import com.jagex.graphics.runetek4.software.sprite.SoftwarePaletteSprite;

public class CS2CallFrame {
	public static RSString aClass16_771;
	public int ip = -1;
	public static int[] anIntArray774;
	public static int anInt776 = 0;
	public static RSString aClass16_777;
	public CS2ScriptDefinition cs2Script;
	public static int[] anIntArray780;
	public RSString[] str_local_vars;
	public static RSString aClass16_784;
	public int[] int_local_vars;
	public long[] long_local_vars;

	public static void method1155(int i) {
		anIntArray780 = null;
		aClass16_777 = null;
		aClass16_784 = null;
		anIntArray774 = null;
		aClass16_771 = null;
	}

	static final void method1156(int i, int i_0_, int i_1_, int i_2_, int i_3_, int i_4_, int i_5_) {
		int i_6_ = i_0_ + i_5_;
		int i_7_ = -i_0_ + i;
		int i_8_ = i_2_ - -i_0_;
		int i_9_ = i_1_ - i_0_;
		for (int i_10_ = i_5_; (i_10_ ^ 0xffffffff) > (i_6_ ^ 0xffffffff); i_10_++) {
			VarpDefinition.method632(i_1_, (byte) -30, i_4_, Class4.anIntArrayArray98[i_10_], i_2_);
		}
		int i_11_ = i;
		if (i_3_ >= 24) {
			for (/**/; (i_7_ ^ 0xffffffff) > (i_11_ ^ 0xffffffff); i_11_--) {
				VarpDefinition.method632(i_1_, (byte) -30, i_4_, Class4.anIntArrayArray98[i_11_], i_2_);
			}
			for (int i_12_ = i_6_; (i_7_ ^ 0xffffffff) <= (i_12_ ^ 0xffffffff); i_12_++) {
				int[] is = Class4.anIntArrayArray98[i_12_];
				VarpDefinition.method632(i_8_, (byte) -30, i_4_, is, i_2_);
				VarpDefinition.method632(i_1_, (byte) -30, i_4_, is, i_9_);
			}
		}
	}

	static final SoftwarePaletteSprite method1158(byte b) {
		@SuppressWarnings("unused")
		int i = -22 / ((b - -27) / 57);
		SoftwarePaletteSprite indexedSprite = new SoftwarePaletteSprite(SpriteLoader.trimmed_width, SpriteLoader.trimmed_height, SpriteLoader.sprites_offsetx[0], SpriteLoader.sprites_offsety[0], SpriteLoader.sprites_width[0], SpriteLoader.sprites_height[0], SpriteLoader.sprites_pixels[0], SpriteLoader.palette);
		SpriteLoader.reset();
		return indexedSprite;
	}

	static {
		anIntArray774 = new int[] { 0, 4, 4, 8, 0, 0, 8, 0, 0 };
		anIntArray780 = new int[32];
		aClass16_777 = RSString.createString("Loading textures )2 ");
		aClass16_771 = aClass16_777;
		aClass16_784 = RSString.createString("Starte 3D)2Softwarebibliothek)3");
	}
}
