package com.jagex;
/* FaceNormal - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class FaceNormal
{
	public int y;
	public int z;
	public int x;
	public static int[] anIntArray628 = { 8, 11, 4, 6, 9, 7, 10 };
	static RSString aClass16_629 = RSString.createString("null");
	static final void method1105(byte b, int i, int i_0_, int i_1_, int i_2_) {
		int i_3_ = i_0_;
		if (b > -109) {
			method1108(4);
		}
		int i_4_ = -i_0_;
		int i_5_ = -1;
		int i_6_ = 0;
		VarpDefinition.method632(i + i_0_, (byte) -30, i_1_, Class4.anIntArrayArray98[i_2_], i - i_0_);
		while (i_6_ < i_3_) {
			i_5_ += 2;
			i_6_++;
			i_4_ += i_5_;
			if ((i_4_ ^ 0xffffffff) <= -1) {
				i_3_--;
				int[] is = Class4.anIntArrayArray98[i_2_ - -i_3_];
				i_4_ -= i_3_ << 1;
				int i_7_ = i_6_ + i;
				int[] is_8_ = Class4.anIntArrayArray98[i_2_ - i_3_];
				int i_9_ = i + -i_6_;
				VarpDefinition.method632(i_7_, (byte) -30, i_1_, is, i_9_);
				VarpDefinition.method632(i_7_, (byte) -30, i_1_, is_8_, i_9_);
			}
			int i_10_ = i - -i_3_;
			int i_11_ = i - i_3_;
			int[] is = Class4.anIntArrayArray98[-i_6_ + i_2_];
			int[] is_12_ = Class4.anIntArrayArray98[i_6_ + i_2_];
			VarpDefinition.method632(i_10_, (byte) -30, i_1_, is_12_, i_11_);
			VarpDefinition.method632(i_10_, (byte) -30, i_1_, is, i_11_);
		}
	}
	
	static final int method1106(int i, int i_13_, int i_14_) {
		int i_15_ = 0;
		while (i > 0) {
			i--;
			i_15_ = i_13_ & 0x1 | i_15_ << 1;
			i_13_ >>>= 1;
		}
		if (i_14_ != 32768) {
			return 99;
		}
		return i_15_;
	}
	
	public static final int method1107(int i) {
		try {
			int i_16_ = 3;
			if (Camera.yCameraCurve < 310) {
				int i_17_ = Camera.xCameraPos >> 7;
				int i_18_ = Camera.yCameraPos >> 7;
				int height = ObjType.localHeight;
				byte[][][] data = com.jagex.MapLoader.settings;
				if (height >= data.length || i_17_ < 0 || i_18_ < 0 || i_17_ >= data[height].length || i_18_ >= data[height][i_17_].length) {
					//System.err.println("Invalid offset: " + ItemDefinition.localHeight + ", " + i_17_ + ", " + i_18_);
					return ObjType.localHeight;
				}
				if ((com.jagex.MapLoader.settings[ObjType.localHeight][i_17_][i_18_] & 0x4 ^ 0xffffffff) != -1) {
					i_16_ = ObjType.localHeight;
				}
				int i_19_ = GameClient.currentPlayer.bound_extents_x >> 7;
				int i_20_ = GameClient.currentPlayer.bound_extents_z >> 7;
				int i_21_;
				if ((i_19_ ^ 0xffffffff) >= (i_17_ ^ 0xffffffff)) {
					i_21_ = -i_19_ + i_17_;
				} else {
					i_21_ = -i_17_ + i_19_;
				}
				int i_22_;
				if ((i_20_ ^ 0xffffffff) >= (i_18_ ^ 0xffffffff)) {
					i_22_ = -i_20_ + i_18_;
				} else {
					i_22_ = i_20_ + -i_18_;
				}
				if (i_22_ < i_21_) {
					int i_23_ = i_22_ * 65536 / i_21_;
					int i_24_ = 32768;
					while ((i_17_ ^ 0xffffffff) != (i_19_ ^ 0xffffffff)) {
						i_24_ += i_23_;
						if ((i_19_ ^ 0xffffffff) >= (i_17_ ^ 0xffffffff)) {
							if (i_17_ > i_19_) {
								i_17_--;
							}
						} else {
							i_17_++;
						}
						if ((com.jagex.MapLoader.settings[ObjType.localHeight][i_17_][i_18_] & 0x4 ^ 0xffffffff) != -1) {
							i_16_ = ObjType.localHeight;
						}
						if ((i_24_ ^ 0xffffffff) <= -65537) {
							i_24_ -= 65536;
							if ((i_20_ ^ 0xffffffff) >= (i_18_ ^ 0xffffffff)) {
								if ((i_20_ ^ 0xffffffff) > (i_18_ ^ 0xffffffff)) {
									i_18_--;
								}
							} else {
								i_18_++;
							}
							if ((0x4 & com.jagex.MapLoader.settings[ObjType.localHeight][i_17_][i_18_]) != 0) {
								i_16_ = ObjType.localHeight;
							}
						}
					}
				} else {
					int i_25_ = 65536 * i_21_ / i_22_;
					int i_26_ = 32768;
					while (i_20_ != i_18_) {
						i_26_ += i_25_;
						if (i_18_ >= i_20_) {
							if ((i_18_ ^ 0xffffffff) < (i_20_ ^ 0xffffffff)) {
								i_18_--;
							}
						} else {
							i_18_++;
						}
						if ((0x4 & com.jagex.MapLoader.settings[ObjType.localHeight]
								[i_17_]
										[i_18_]) != 0) {
							i_16_ = ObjType.localHeight;
						}
						if (i_26_ >= 65536) {
							i_26_ -= 65536;
							if ((i_19_ ^ 0xffffffff) >= (i_17_ ^ 0xffffffff)) {
								if (i_19_ < i_17_) {
									i_17_--;
								}
							} else {
								i_17_++;
							}
							if ((0x4 & com.jagex.MapLoader.settings[ObjType.localHeight][i_17_][i_18_] ^ 0xffffffff) != -1) {
								i_16_ = ObjType.localHeight;
							}
						}
					}
				}
			}
			if ((0x4 & com.jagex.MapLoader.settings[ObjType.localHeight][GameClient.currentPlayer.bound_extents_x >> 7][GameClient.currentPlayer.bound_extents_z >> 7] ^ 0xffffffff) != -1) {
				i_16_ = ObjType.localHeight;
			}
			if (i != -175122297) {
				VarpDefinition.varpMap = null;
			}
			return i_16_;
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return ObjType.localHeight;
	}
	
	public static void method1108(int i) {
		AnimFrameset.basesJs5 = null;
		MapRegion.MAP_L = null;
		anIntArray628 = null;
		aClass16_629 = null;
		VarpDefinition.varpMap = null;
	}
}
