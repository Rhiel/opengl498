package com.jagex;
/* Class91 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import com.jagex.graphics.runetek4.software.sprite.SoftwarePaletteSprite;
import com.rs2.client.scene.Scene;

public class Class91
{
	static RSString aClass16_1552;
	public static int anInt1556;
	static RSString aClass16_1558;
	
	static final synchronized byte[] method1449(byte b, int i) {
		if (b >= -114) {
			return null;
		}
		if (i == 100 && StaticMethods.anInt3453 > 0) {
			byte[] bs = ComponentCanvas.aByteArrayArray54[--StaticMethods.anInt3453];
			ComponentCanvas.aByteArrayArray54[StaticMethods.anInt3453] = null;
			return bs;
		}
		if (i == 5000 && (Class95.anInt1617 ^ 0xffffffff) < -1) {
			byte[] bs = Mouse.aByteArrayArray231[--Class95.anInt1617];
			Mouse.aByteArrayArray231[Class95.anInt1617] = null;
			return bs;
		}
		if (i == 30000 && IoSession.anInt531 > 0) {
			byte[] bs = StaticMethods.aByteArrayArray3163[--IoSession.anInt531];
			StaticMethods.aByteArrayArray3163[IoSession.anInt531] = null;
			return bs;
		}
		return new byte[i];
	}

	static final void parseCS2Script(CS2Event script, byte b) {
		CS2Runtime.parseCS2Script(10000000, script, true);
		if (b > -87) {
			method1449((byte) -31, -6);
		}
	}
	
	static final boolean method1453(int i, int i_1_, int i_2_, int i_3_, int i_4_, int i_5_, SceneNode abstractModel, int i_6_, long l) {
		if (abstractModel == null) {
			return true;
		}
		int i_7_ = i_1_ * 128 + 64 * i_4_;
		int i_8_ = i_2_ * 128 + 64 * i_5_;
		return Scene.addInteractiveObject(i, i_1_, i_2_, i_4_, i_5_, i_7_, i_8_, i_3_, abstractModel, i_6_, false, l);
	}
	
	static final SoftwarePaletteSprite method1454(Js5 class105, int i, int i_9_, byte b) {
		if (!SpriteLoader.cache_sprites(class105, i_9_, i)) {
			return null;
		}
		return CS2CallFrame.method1158((byte) -106);
	}
	
	static final void method1455(byte b, int i, int i_10_, int i_11_, int i_12_, int i_13_) {
		if (b == -51) {
			if (i_11_ <= StaticMethods.anInt3435 && i_10_ >= Class88.anInt1503) {
				boolean bool;
				if ((i_13_ ^ 0xffffffff) > (VarpDefinition.anInt3728 ^ 0xffffffff)) {
					i_13_ = VarpDefinition.anInt3728;
					bool = false;
				} else if ((Class35.anInt554 ^ 0xffffffff) <= (i_13_ ^ 0xffffffff)) {
					bool = true;
				} else {
					i_13_ = Class35.anInt554;
					bool = false;
				}
				boolean bool_14_;
				if ((i_12_ ^ 0xffffffff) > (VarpDefinition.anInt3728 ^ 0xffffffff)) {
					bool_14_ = false;
					i_12_ = VarpDefinition.anInt3728;
				} else if (Class35.anInt554 < i_12_) {
					bool_14_ = false;
					i_12_ = Class35.anInt554;
				} else {
					bool_14_ = true;
				}
				if ((i_11_ ^ 0xffffffff) > (Class88.anInt1503 ^ 0xffffffff)) {
					i_11_ = Class88.anInt1503;
				} else {
					VarpDefinition.method632(i_13_, (byte) -30, i, Class4.anIntArrayArray98[i_11_++], i_12_);
				}
				if (i_10_ > StaticMethods.anInt3435) {
					i_10_ = StaticMethods.anInt3435;
				} else {
					VarpDefinition.method632(i_13_, (byte) -30, i, Class4.anIntArrayArray98[i_10_--], i_12_);
				}
				if (bool_14_ && bool) {
					for (int i_15_ = i_11_; i_10_ >= i_15_; i_15_++) {
						int[] is = Class4.anIntArrayArray98[i_15_];
						is[i_12_] = is[i_13_] = i;
					}
				} else if (!bool_14_) {
					if (bool) {
						for (int i_16_ = i_11_; (i_10_ ^ 0xffffffff) <= (i_16_ ^ 0xffffffff); i_16_++)
							Class4.anIntArrayArray98[i_16_][i_13_] = i;
					}
				} else {
					for (int i_17_ = i_11_; (i_17_ ^ 0xffffffff) >= (i_10_ ^ 0xffffffff); i_17_++)
						Class4.anIntArrayArray98[i_17_][i_12_] = i;
				}
			}
		}
	}
	
	public static void method1456(int i) {
		aClass16_1558 = null;
		aClass16_1552 = null;
		if (i != -30192) {
			parseCS2Script(null, (byte) 12);
		}
	}
	
	static {
		aClass16_1552 = RSString.createString("<col=ff3000>");
		anInt1556 = 20;
		aClass16_1558 = RSString.createString("event_opbase");
	}
}
