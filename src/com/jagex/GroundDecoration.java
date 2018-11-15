package com.jagex;
/* GroundDecoration - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import com.jagex.graphics.runetek4.media.PaletteSprite;
import com.rs2.client.scene.Scene;

public class GroundDecoration {
	public static Huffman aHuffman_1206;
	public long uid;
	public int position_y;
	public int anInt1210;
	public static RSString aClass16_1217 = RSString.createString("document)3cookie=(R");
	public SceneNode node;
	public static PaletteSprite logoImage;
	public int position_x;
	public boolean aBoolean329 = false;

	public static void method1256() {
		aHuffman_1206 = null;
		aClass16_1217 = null;
		logoImage = null;
	}

	static final void method1257(boolean bool) {
		int i = (GameClient.currentPlayer.bound_extents_x >> 7) + MapLoader.region_aboslute_z;
		int i_0_ = (GameClient.currentPlayer.bound_extents_z >> 7) - -MapLoader.region_aboslute_x;
		StaticMethods.anInt3519 = 0;
		if (i >= 3053 && i <= 3156 && i_0_ >= 3056 && i_0_ <= 3136) {
			StaticMethods.anInt3519 = 1;
		}
		if (i >= 3072 && i <= 3118 && i_0_ >= 9492 && i_0_ <= 9535) {
			StaticMethods.anInt3519 = 1;
		}
		if (StaticMethods.anInt3519 == 1 && i >= 3139 && i <= 3199 && i_0_ >= 3008 && i_0_ <= 3062) {
			StaticMethods.anInt3519 = 0;
		}
		if (bool != true) {
			GameClient.last_ramchk = -59L;
		}
	}

	static final long getGroundDecorationUid(int i, int i_8_, int i_9_) {
		Ground class23_sub1 = Scene.current_grounds[i][i_8_][i_9_];
		if (class23_sub1 == null || class23_sub1.decoration == null) {
			return 0L;
		}
		return class23_sub1.decoration.uid;
	}

	static {
		GameClient.last_ramchk = 0L;
		GameClient.interface_top_id = -1;
	}

	static final GroundDecoration getGroundDecoration(int z, int x, int y) {
		Ground class23_sub1 = Scene.current_grounds[z][x][y];
		if (class23_sub1 == null || class23_sub1.decoration == null) {
			return null;
		}
		return class23_sub1.decoration;
	}

	static final void addGroundDecoration(int i, int i_2_, int i_3_, int i_4_, SceneNode abstractModel, long l, boolean dummy) {
		if (abstractModel != null) {
			GroundDecoration groundDecoration = new GroundDecoration();
			groundDecoration.node = abstractModel;
			groundDecoration.position_x = i_2_ * 128 + 64;
			groundDecoration.position_y = i_3_ * 128 + 64;
			groundDecoration.anInt1210 = i_4_;
			groundDecoration.uid = l;
			groundDecoration.aBoolean329 = dummy;
			if (Scene.current_grounds[i][i_2_][i_3_] == null) {
				Scene.current_grounds[i][i_2_][i_3_] = new Ground(i, i_2_, i_3_);
			}
			Scene.current_grounds[i][i_2_][i_3_].decoration = groundDecoration;
		}
	}
}
