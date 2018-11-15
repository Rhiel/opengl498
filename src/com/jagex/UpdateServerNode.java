package com.jagex;
/* UpdateServerNode - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import com.jagex.game.runetek4.clientoptions.ClientOptions;

public class UpdateServerNode extends Linkable {

	static ModelList aModelList_2325;
	static RSString aClass16_2326;
	public static RSString aClass16_2329 = RSString.createString("Loading)3)3)3");
	static long[] aLongArray2334;
	static RSString[] chatUsers;
	static RSString aClass16_2339;

	static final void method861(int i, int i_0_, int i_1_) {
		int[] is = new int[4];
		is[0] = i_1_;
		int i_2_ = 1;
		if (i_0_ >= -13) {
			chatUsers = null;
		}
		int[] is_3_ = new int[4];
		is_3_[0] = i;
		for (int i_4_ = 0; i_4_ < 4; i_4_++) {
			if ((i_1_ ^ 0xffffffff) != (Class88.anIntArray1507[i_4_] ^ 0xffffffff)) {
				is[i_2_] = Class88.anIntArray1507[i_4_];
				is_3_[i_2_] = StaticMethods2.anIntArray2401[i_4_];
				i_2_++;
			}
		}
		Class88.anIntArray1507 = is;
		StaticMethods2.anIntArray2401 = is_3_;
		SpotEntity.method1080(0, 0, LobbyWorld.worldLists, LobbyWorld.worldLists.length - 1);
	}

	public static void method862(byte b) {
		aClass16_2329 = null;
		aModelList_2325 = null;
		aClass16_2326 = null;
		aClass16_2339 = null;
		CS2Runtime.int_array_size = null;
		aLongArray2334 = null;
		if (b == -93) {
			chatUsers = null;
		}
	}

	static final void method863(int i, int i_5_, int i_6_, int i_7_, byte b, int i_8_) {
		if (b != -88) {
			aClass16_2329 = null;
		}
		int i_9_ = -i + i_8_;
		int i_10_ = i_5_ - i_7_;
		if (i_9_ == 0) {
			if (i_10_ != 0) {
				ISAACCipher.method1264(i_6_, i_5_, i_7_, i, (byte) -119);
			}
		} else if (i_10_ == 0) {
			Class56.method1187(false, i_6_, i_8_, i, i_7_);
		} else {
			if ((i_9_ ^ 0xffffffff) > -1) {
				i_9_ = -i_9_;
			}
			if ((i_10_ ^ 0xffffffff) > -1) {
				i_10_ = -i_10_;
			}
			boolean bool = (i_9_ ^ 0xffffffff) > (i_10_ ^ 0xffffffff);
			if (bool) {
				int i_11_ = i;
				int i_12_ = i_8_;
				i_8_ = i_5_;
				i_5_ = i_12_;
				i = i_7_;
				i_7_ = i_11_;
			}
			if (i_8_ < i) {
				int i_13_ = i;
				i = i_8_;
				int i_14_ = i_7_;
				i_8_ = i_13_;
				i_7_ = i_5_;
				i_5_ = i_14_;
			}
			int i_15_ = i_7_;
			int i_16_ = i_8_ - i;
			int i_17_ = -i_7_ + i_5_;
			if ((i_17_ ^ 0xffffffff) > -1) {
				i_17_ = -i_17_;
			}
			int i_18_ = -(i_16_ >> 1);
			int i_19_ = i_7_ < i_5_ ? 1 : -1;
			if (bool) {
				for (int i_20_ = i; (i_8_ ^ 0xffffffff) <= (i_20_ ^ 0xffffffff); i_20_++) {
					Class4.anIntArrayArray98[i_20_][i_15_] = i_6_;
					i_18_ += i_17_;
					if (i_18_ > 0) {
						i_15_ += i_19_;
						i_18_ -= i_16_;
					}
				}
			} else {
				for (int i_21_ = i; i_8_ >= i_21_; i_21_++) {
					i_18_ += i_17_;
					Class4.anIntArrayArray98[i_15_][i_21_] = i_6_;
					if (i_18_ > 0) {
						i_15_ += i_19_;
						i_18_ -= i_16_;
					}
				}
			}
		}
	}

	static final void method864(int i, int i_22_, int i_23_, int i_24_, int i_25_, int i_26_, int i_27_) {
		int i_29_ = StaticMethods.method405(57, Class88.anInt1503, i_24_, StaticMethods.anInt3435);
		int i_30_ = StaticMethods.method405(65, Class88.anInt1503, i_26_, StaticMethods.anInt3435);
		int i_31_ = StaticMethods.method405(60, VarpDefinition.anInt3728, i, Class35.anInt554);
		int i_32_ = StaticMethods.method405(111, VarpDefinition.anInt3728, i_27_, Class35.anInt554);
		int i_33_ = StaticMethods.method405(78, Class88.anInt1503, i_24_ - -i_25_, StaticMethods.anInt3435);
		int i_34_ = StaticMethods.method405(117, Class88.anInt1503, -i_25_ + i_26_, StaticMethods.anInt3435);
		for (int i_35_ = i_29_; (i_35_ ^ 0xffffffff) > (i_33_ ^ 0xffffffff); i_35_++) {
			VarpDefinition.method632(i_32_, (byte) -30, i_22_, Class4.anIntArrayArray98[i_35_], i_31_);
		}
		for (int i_36_ = i_30_; i_34_ < i_36_; i_36_--) {
			VarpDefinition.method632(i_32_, (byte) -30, i_22_, Class4.anIntArrayArray98[i_36_], i_31_);
		}
		int i_37_ = StaticMethods.method405(89, VarpDefinition.anInt3728, i_25_ + i, Class35.anInt554);
		int i_38_ = StaticMethods.method405(67, VarpDefinition.anInt3728, i_27_ + -i_25_, Class35.anInt554);
		for (int i_39_ = i_33_; (i_34_ ^ 0xffffffff) <= (i_39_ ^ 0xffffffff); i_39_++) {
			int[] is = Class4.anIntArrayArray98[i_39_];
			VarpDefinition.method632(i_37_, (byte) -30, i_22_, is, i_31_);
			VarpDefinition.method632(i_32_, (byte) -30, i_22_, is, i_38_);
		}
	}

	static {
		aClass16_2326 = aClass16_2329;
		aModelList_2325 = new ModelList(30);
		ClientOptions.clientoption_brightness = 3;
		aLongArray2334 = new long[32];
		chatUsers = new RSString[100];
		aClass16_2339 = RSString.createString("Stufe)2");
	}
}
