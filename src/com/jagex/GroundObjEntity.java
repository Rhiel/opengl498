package com.jagex;
/* GroundItemTile - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import com.rs2.client.scene.Scene;

public class GroundObjEntity {
	public SceneNode second_node;
	public int anInt703;
	public SceneNode first_node;
	public int position_x;
	public int position_y;
	public static RSString worldOffString;
	public static int anInt708 = 0;
	public int anInt709;
	public long aLong711;
	public static Class42 aClass42_712;
	public static RSString aClass16_714 = RSString.createString("::");
	public SceneNode third_node;
	public static RSString worldOff = RSString.createString("OFF");

	static final void addGroundItem(int i, int i_0_, int i_1_, int i_2_, SceneNode abstractModel, long l, SceneNode abstractModel_3_, SceneNode abstractModel_4_) {
		GroundObjEntity groundItemTile = new GroundObjEntity();
		groundItemTile.first_node = abstractModel;
		groundItemTile.position_x = i_0_ * 128 + 64;
		groundItemTile.position_y = i_1_ * 128 + 64;
		groundItemTile.anInt703 = i_2_;
		groundItemTile.aLong711 = l;
		groundItemTile.second_node = abstractModel_3_;
		groundItemTile.third_node = abstractModel_4_;
		int i_5_ = 0;
		Ground class23_sub1 = Scene.current_grounds[i][i_0_][i_1_];
		if (class23_sub1 != null) {
			for (int i_6_ = 0; i_6_ < class23_sub1.num_interactives; i_6_++) {
				InteractiveEntity interactiveObject = class23_sub1.interactives[i_6_];
				if ((interactiveObject.uid & 0x400000L) == 4194304L) {
					int i_7_ = interactiveObject.node.get_miny();
					if (i_7_ != -32768 && i_7_ < i_5_) {
						i_5_ = i_7_;
					}
				}
			}
		}
		groundItemTile.anInt709 = -i_5_;
		if (Scene.current_grounds[i][i_0_][i_1_] == null) {
			Scene.current_grounds[i][i_0_][i_1_] = new Ground(i, i_0_, i_1_);
		}
		Scene.current_grounds[i][i_0_][i_1_].obj_entity = groundItemTile;
	}

	static final void method1138(int i, int i_0_, byte b, int i_1_, int i_2_, int i_3_) {
		VarpDefinition.method632(i_2_ + i_3_, (byte) -30, i, Class4.anIntArrayArray98[i_0_], i_3_ - i_2_);
		int i_4_ = i_1_;
		int i_5_ = i_2_ * i_2_;
		int i_6_ = 0;
		int i_7_ = i_1_ * i_1_;
		int i_8_ = i_7_ << 1;
		int i_9_ = i_1_ << 1;
		int i_10_ = i_5_ << 1;
		int i_11_ = i_7_ << 2;
		int i_12_ = i_7_ + -(i_10_ * (-1 + i_9_));
		int i_13_ = i_5_ * (1 - i_9_) - -i_8_;
		int i_14_ = ((i_4_ << 1) + -3) * i_10_;
		int i_15_ = i_5_ << 2;
		int i_16_ = (3 + (i_6_ << 1)) * i_8_;
		int i_17_ = i_15_ * (i_4_ - 1);
		int i_18_ = i_11_ * (i_6_ + 1);
		while (i_4_ > 0) {
			i_4_--;
			if ((i_13_ ^ 0xffffffff) > -1) {
				while (i_13_ < 0) {
					i_13_ += i_16_;
					i_12_ += i_18_;
					i_18_ += i_11_;
					i_16_ += i_11_;
					i_6_++;
				}
			}
			int i_19_ = -i_4_ + i_0_;
			int i_20_ = i_4_ + i_0_;
			if ((i_12_ ^ 0xffffffff) > -1) {
				i_12_ += i_18_;
				i_18_ += i_11_;
				i_13_ += i_16_;
				i_6_++;
				i_16_ += i_11_;
			}
			i_13_ += -i_17_;
			i_17_ -= i_15_;
			i_12_ += -i_14_;
			int i_21_ = i_3_ - i_6_;
			i_14_ -= i_15_;
			int i_22_ = i_3_ - -i_6_;
			VarpDefinition.method632(i_22_, (byte) -30, i, Class4.anIntArrayArray98[i_19_], i_21_);
			VarpDefinition.method632(i_22_, (byte) -30, i, Class4.anIntArrayArray98[i_20_], i_21_);
		}
	}

	public static void method1140(byte b) {
		worldOff = null;
		aClass16_714 = null;
		if (b != 39) {
			method1141((byte) 23, null);
		}
		worldOffString = null;
		CS2Runtime.str_local_vars = null;
		aClass42_712 = null;
	}

	static final void method1141(byte b, RSInterface class64) {
		int i = class64.content_type;
		if (i == 324) {
			if (Class4.anInt88 == -1) {
				Class4.anInt88 = class64.graphicid;
				Class71_Sub1_Sub1.anInt4457 = class64.enabled_graphic;
			}
			if (StaticMethods2.aPlayerAppearance_1440.female) {
				class64.graphicid = Class4.anInt88;
			} else {
				class64.graphicid = Class71_Sub1_Sub1.anInt4457;
			}
		} else {
			if (b < 14) {
				method1138(105, 85, (byte) -109, 124, 48, 3);
			}
			if (i == 325) {
				if ((Class4.anInt88 ^ 0xffffffff) == 0) {
					Class4.anInt88 = class64.graphicid;
					Class71_Sub1_Sub1.anInt4457 = class64.enabled_graphic;
				}
				if (!StaticMethods2.aPlayerAppearance_1440.female) {
					class64.graphicid = Class4.anInt88;
				} else {
					class64.graphicid = Class71_Sub1_Sub1.anInt4457;
				}
			} else if (i == 327) {
				class64.media_xangle = 150;
				class64.media_yangle = (int) (256.0 * Math.sin(GameClient.timer / 40.0)) & 0x7ff;
				class64.media_id = -1;
				class64.media_type = 5;
			} else if (i == 328) {
				if (GameClient.currentPlayer.username == null) {
					class64.media_id = 0;
				} else {
					class64.media_xangle = 150;
					class64.media_yangle = 0x7ff & (int) (Math.sin(GameClient.timer / 40.0) * 256.0);
					class64.media_type = 5;
					class64.media_id = ((int) GameClient.currentPlayer.username.toUsernameLong() << 11) - -2047;
					class64.media_animid = GameClient.currentPlayer.current_standing_animation;
					class64.media_current_frameid = GameClient.currentPlayer.current_standing_frameid;
					class64.media_next_frameid = GameClient.currentPlayer.next_standing_frameid;
				}
			}
		}
	}

	GroundObjEntity() {
		/* empty */
	}

	static {
		worldOffString = worldOff;
	}
}
