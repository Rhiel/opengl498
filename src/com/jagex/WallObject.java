package com.jagex;

import com.rs2.client.scene.Scene;

/* WallObject - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class WallObject {
	public int position_x;
	public static int musicId;
	public int anInt1055;
	public static Class45 aClass45_1462;
	public SceneNode first_node;
	public int position_y;
	public SceneNode second_node;
	public int anInt1469;
	public int anInt1059;
	public long uid = 0L;

	public static final RSString getStringFromLong(int dummy, long l) {
		if ((l ^ 0xffffffffffffffffL) >= -1L || (l ^ 0xffffffffffffffffL) <= -6582952005840035282L) {
			return null;
		}
		if ((l % 37L ^ 0xffffffffffffffffL) == -1L) {
			return null;
		}
		int i_0_ = 0;
		for (long l_1_ = l; (l_1_ ^ 0xffffffffffffffffL) != -1L; l_1_ /= 37L) {
			i_0_++;
		}
		byte[] bs = new byte[i_0_];
		while ((l ^ 0xffffffffffffffffL) != -1L) {
			long l_2_ = l;
			l /= 37L;
			bs[--i_0_] = SpotEntity.aByteArray2618[(int) (-(l * 37L) + l_2_)];
		}
		RSString class16 = new RSString();
		class16.bytes = bs;
		if (dummy != -1) {
			musicId = -124;
		}
		class16.length = bs.length;
		return class16;
	}

	static final WallObject getWallObject(int i, int i_3_, int i_4_) {
		Ground tile = Scene.current_grounds[i][i_3_][i_4_];
		if (tile == null) {
			return null;
		}
		return tile.wall_object;
	}

	static final long getWallObjectUid(int z, int x, int y) {
		Ground tile = Scene.current_grounds[z][x][y];
		if (tile == null || tile.wall_object == null) {
			return 0L;
		}
		return tile.wall_object.uid;
	}

	static final void addWallObject(int i, int i_0_, int i_1_, int i_2_, SceneNode abstractModel, SceneNode abstractModel_3_, int i_4_, int i_5_, long l) {
		if (abstractModel != null || abstractModel_3_ != null) {
			WallObject wallObject = new WallObject();
			wallObject.uid = l;
			wallObject.position_x = i_0_ * 128 + 64;
			wallObject.position_y = i_1_ * 128 + 64;
			wallObject.anInt1469 = i_2_;
			wallObject.first_node = abstractModel;
			wallObject.second_node = abstractModel_3_;
			wallObject.anInt1055 = i_4_;
			wallObject.anInt1059 = i_5_;
			for (int i_6_ = i; i_6_ >= 0; i_6_--) {
				if (Scene.current_grounds[i_6_][i_0_][i_1_] == null) {
					Scene.current_grounds[i_6_][i_0_][i_1_] = new Ground(i_6_, i_0_, i_1_);
				}
			}
			Scene.current_grounds[i][i_0_][i_1_].wall_object = wallObject;
		}
	}

	public static void method1378() {
		aClass45_1462 = null;
	}

	static {
		musicId = -1;
	}
}
