package com.jagex;

import com.rs2.client.scene.Scene;

import me.waliedyassen.graphics.rasterizer.Rasterizer;

public class WallDecoration {
	public int position_y;
	public long uid = 0L;
	public static int anInt365 = 0;
	public int position_x;
	public SceneNode first_node;
	public SceneNode second_node;
	public int anInt370;
	public int anInt371;
	public static int[] anIntArray372 = new int[100];
	public int anInt374;
	public int anInt375;
	public static boolean[] aBooleanArray376 = { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, true };
	public int anInt378;

	public static void method916(int i) {
		anIntArray372 = null;
		aBooleanArray376 = null;
		if (i != 0) {
			GameClient.atInventoryIndex = -89;
		}
	}

	static final int method918(int i, int i_6_, int i_7_, int i_8_, int i_9_) {
		int i_10_ = -Rasterizer.COSINE[i * 1024 / i_9_] + 65536 >> 1;
		if (i_8_ != 0) {
			return 40;
		}
		return (i_6_ * i_10_ >> 16) + (i_7_ * (65536 - i_10_) >> 16);
	}

	static final WallDecoration getWallDecoration(int i, int i_21_, int i_22_) {
		Ground tile = Scene.current_grounds[i][i_21_][i_22_];
		if (tile == null) {
			return null;
		}
		return tile.wall_decoration;
	}

	static final long getWallDecorationUid(int i, int i_0_, int i_1_) {
		Ground class23_sub1 = Scene.current_grounds[i][i_0_][i_1_];
		if (class23_sub1 == null || class23_sub1.wall_decoration == null) {
			return 0L;
		}
		return class23_sub1.wall_decoration.uid;
	}

	static final void addWallDecoration(int i, int i_2_, int i_3_, int i_4_, SceneNode abstractModel, SceneNode abstractModel_5_, int i_6_, int i_7_, int i_8_, int i_9_, long l) {
		if (abstractModel != null) {
			WallDecoration class24 = new WallDecoration();
			class24.uid = l;
			class24.position_x = i_2_ * 128 + 64;
			class24.position_y = i_3_ * 128 + 64;
			class24.anInt370 = i_4_;
			class24.first_node = abstractModel;
			class24.second_node = abstractModel_5_;
			class24.anInt378 = i_6_;
			class24.anInt374 = i_7_;
			class24.anInt375 = i_8_;
			class24.anInt371 = i_9_;
			for (int i_10_ = i; i_10_ >= 0; i_10_--) {
				if (Scene.current_grounds[i_10_][i_2_][i_3_] == null) {
					Scene.current_grounds[i_10_][i_2_][i_3_] = new Ground(i_10_, i_2_, i_3_);
				}
			}
			Scene.current_grounds[i][i_2_][i_3_].wall_decoration = class24;
		}
	}

}
