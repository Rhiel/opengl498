package com.jagex;
/* Class44 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import com.rs2.client.scene.Scene;

public class Class44
{
	public static int[] anIntArray672;
	static RSString aClass16_673;
	static Js5 soundsContainer;
	static short[][] aShortArrayArray679;
	
	public static void method1126(int i) {
		aShortArrayArray679 = null;
		soundsContainer = null;
		aClass16_673 = null;
		anIntArray672 = null;
		if (i != 21150) {
			method1128(126);
		}
	}
	
	static final WallDecoration method1127(int i, int i_0_, int i_1_) {
		Ground ground = Scene.current_grounds[i][i_0_][i_1_];
		if (ground != null) {
			WallDecoration decoration = ground.wall_decoration;
			ground.wall_decoration = null;
			return decoration;
		}
		return null;
	}
	
	static final void method1128(int i) {
		if ((StaticMethods.anInt3400 ^ 0xffffffff) < -1) {
			StaticMethods.method326(true);
		} else {
			GameClient.updateClientState(40);
			NPCType.aClass34_4150 = ColourImageCacheSlot.session;
			ColourImageCacheSlot.session = null;
		}
	}
	
	static final void method1129(int i, int i_3_, byte b) {
		ObjType.localHeight %= 4;
		NodeDeque class89 = StaticMethods2.groundItems[ObjType.localHeight][i][i_3_];
		if (class89 == null) {
			SeekableFile.method949(ObjType.localHeight, i, i_3_);
		} else {
			int i_4_ = -99999999;
			GroundItemNode class23_sub13_sub1 = (GroundItemNode) class89.get_first();
			GroundItemNode class23_sub13_sub1_5_ = null;
			for (/**/; class23_sub13_sub1 != null; class23_sub13_sub1 = (GroundItemNode) class89.get_next()) {
				ObjType class23_sub13_sub11 = ObjTypeList.list(class23_sub13_sub1.groundItem.itemId);
				int i_6_ = class23_sub13_sub11.value;
				if (class23_sub13_sub11.stackable == 1) {
					i_6_ *= class23_sub13_sub1.groundItem.amount + 1;
				}
				if ((i_6_ ^ 0xffffffff) < (i_4_ ^ 0xffffffff)) {
					class23_sub13_sub1_5_ = class23_sub13_sub1;
					i_4_ = i_6_;
				}
			}
			if (class23_sub13_sub1_5_ == null) {
				SeekableFile.method949(ObjType.localHeight, i, i_3_);
			} else {
				if (b <= 112) {
					anIntArray672 = null;
				}
				class89.add_first(class23_sub13_sub1_5_, -1);
				class23_sub13_sub1 = (GroundItemNode) class89.get_first();
				GroundItem class38_sub2 = null;
				GroundItem class38_sub2_7_ = null;
				for (/**/; class23_sub13_sub1 != null; class23_sub13_sub1 = (GroundItemNode) class89.get_next()) {
					GroundItem class38_sub2_8_ = class23_sub13_sub1.groundItem;
					if ((class38_sub2_8_.itemId ^ 0xffffffff) != (class23_sub13_sub1_5_.groundItem.itemId ^ 0xffffffff)) {
						if (class38_sub2 == null) {
							class38_sub2 = class38_sub2_8_;
						}
						if ((class38_sub2.itemId ^ 0xffffffff) != (class38_sub2_8_.itemId ^ 0xffffffff) && class38_sub2_7_ == null) {
							class38_sub2_7_ = class38_sub2_8_;
						}
					}
				}
				long l = 1610612736 + (i_3_ << 7) + i;
				GroundObjEntity.addGroundItem(ObjType.localHeight, i, i_3_, Scene.get_average_height(ObjType.localHeight, 64 + i * 128, 64 + 128 * i_3_), class23_sub13_sub1_5_.groundItem, l, class38_sub2, class38_sub2_7_);
			}
		}
	}
	
	static {
		aClass16_673 = RSString.createString("Hidden)2");
		anIntArray672 = new int[] { 2, 2, 4, 2, 1, 8, 4, 1, 4, 4, 2 };
	}
}
