package com.jagex;
/* Class95 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */


public class Class95
{
	public static int anInt1612;
	public static int anInt1617 = 0;
	static RSString aClass16_1620;
	static RSString aClass16_1621;
	
	static final void method1467(int i, int i_0_, int i_1_, int i_2_, int i_3_, int i_4_, int i_5_, int i_6_) {
		int i_7_ = 0;
		int i_8_ = i_0_;
		int i_9_ = -i_6_ + i_2_;
		int i_10_ = -i_6_ + i_0_;
		int i_11_ = i_2_ * i_2_;
		int i_12_ = 0;
		int i_13_ = i_9_ * i_9_;
		int i_14_ = i_0_ * i_0_;
		int i_15_ = i_10_ * i_10_;
		int i_16_ = i_14_ << 1;
		int i_17_ = i_11_ << 1;
		int i_18_ = i_15_ << 1;
		int i_19_ = i_0_ << 1;
		int i_20_ = i_13_ << 1;
		int i_21_ = i_14_ - (-1 + i_19_) * i_17_;
		int i_22_ = i_10_ << 1;
		int i_23_ = i_11_ * (-i_19_ + 1) + i_16_;
		int i_24_ = i_18_ + i_13_ * (1 + -i_22_);
		int i_25_ = i_15_ + -((-1 + i_22_) * i_20_);
		int i_26_ = i_11_ << 2;
		int i_27_ = i_14_ << 2;
		int i_28_ = i_13_ << 2;
		int i_29_ = i_16_ * 3;
		int i_30_ = i_15_ << 2;
		int i_31_ = (i_19_ - 3) * i_17_;
		int i_32_ = i_18_ * 3;
		int i_33_ = i_27_;
		int i_34_ = i_20_ * (i_22_ - 3);
		int i_35_ = i_26_ * (-1 + i_0_);
		int i_36_ = (i_10_ + -1) * i_28_;
		int i_37_ = i_30_;
		if (i >= Class88.anInt1503 && (StaticMethods.anInt3435 ^ 0xffffffff) <= (i ^ 0xffffffff)) {
			int[] is = Class4.anIntArrayArray98[i];
			int i_38_ = StaticMethods.method405(94, VarpDefinition.anInt3728, -i_2_ + i_5_, Class35.anInt554);
			int i_39_ = StaticMethods.method405(i_4_ + 13856, VarpDefinition.anInt3728, i_2_ + i_5_, Class35.anInt554);
			int i_40_ = StaticMethods.method405(69, VarpDefinition.anInt3728, -i_9_ + i_5_, Class35.anInt554);
			int i_41_ = StaticMethods.method405(117, VarpDefinition.anInt3728, i_5_ + i_9_, Class35.anInt554);
			VarpDefinition.method632(i_40_, (byte) -30, i_3_, is, i_38_);
			VarpDefinition.method632(i_41_, (byte) -30, i_1_, is, i_40_);
			VarpDefinition.method632(i_39_, (byte) -30, i_3_, is, i_41_);
		}
		while ((i_8_ ^ 0xffffffff) < -1) {
			if ((i_23_ ^ 0xffffffff) > -1) {
				while (i_23_ < 0) {
					i_23_ += i_29_;
					i_21_ += i_33_;
					i_33_ += i_27_;
					i_7_++;
					i_29_ += i_27_;
				}
			}
			if ((i_21_ ^ 0xffffffff) > -1) {
				i_21_ += i_33_;
				i_33_ += i_27_;
				i_23_ += i_29_;
				i_7_++;
				i_29_ += i_27_;
			}
			i_21_ += -i_31_;
			i_23_ += -i_35_;
			boolean bool = (i_10_ ^ 0xffffffff) <= (i_8_ ^ 0xffffffff);
			i_8_--;
			i_35_ -= i_26_;
			int i_42_ = i - i_8_;
			i_31_ -= i_26_;
			if (bool) {
				if ((i_24_ ^ 0xffffffff) > -1) {
					while (i_24_ < 0) {
						i_12_++;
						i_24_ += i_32_;
						i_25_ += i_37_;
						i_32_ += i_30_;
						i_37_ += i_30_;
					}
				}
				if ((i_25_ ^ 0xffffffff) > -1) {
					i_12_++;
					i_25_ += i_37_;
					i_37_ += i_30_;
					i_24_ += i_32_;
					i_32_ += i_30_;
				}
				i_24_ += -i_36_;
				i_25_ += -i_34_;
				i_36_ -= i_28_;
				i_34_ -= i_28_;
			}
			int i_43_ = i_8_ + i;
			if ((Class88.anInt1503 ^ 0xffffffff) >= (i_43_ ^ 0xffffffff) && StaticMethods.anInt3435 >= i_42_) {
				int i_44_ = StaticMethods.method405(i_4_ ^ ~0x35d1, VarpDefinition.anInt3728, i_7_ + i_5_, Class35.anInt554);
				int i_45_ = StaticMethods.method405(i_4_ + 13856, VarpDefinition.anInt3728, -i_7_ + i_5_, Class35.anInt554);
				if (!bool) {
					if ((Class88.anInt1503 ^ 0xffffffff) >= (i_42_ ^ 0xffffffff)) {
						VarpDefinition.method632(i_44_, (byte) -30, i_3_, Class4.anIntArrayArray98[i_42_], i_45_);
					}
					if ((StaticMethods.anInt3435 ^ 0xffffffff) <= (i_43_ ^ 0xffffffff)) {
						VarpDefinition.method632(i_44_, (byte) -30, i_3_, Class4.anIntArrayArray98[i_43_], i_45_);
					}
				} else {
					int i_46_ = StaticMethods.method405(i_4_ + 13921, VarpDefinition.anInt3728, i_12_ + i_5_, Class35.anInt554);
					int i_47_ = StaticMethods.method405(75, VarpDefinition.anInt3728, -i_12_ + i_5_, Class35.anInt554);
					if ((Class88.anInt1503 ^ 0xffffffff) >= (i_42_ ^ 0xffffffff)) {
						int[] is = Class4.anIntArrayArray98[i_42_];
						VarpDefinition.method632(i_47_, (byte) -30, i_3_, is, i_45_);
						VarpDefinition.method632(i_46_, (byte) -30, i_1_, is, i_47_);
						VarpDefinition.method632(i_44_, (byte) -30, i_3_, is, i_46_);
					}
					if (i_43_ <= StaticMethods.anInt3435) {
						int[] is = Class4.anIntArrayArray98[i_43_];
						VarpDefinition.method632(i_47_, (byte) -30, i_3_, is, i_45_);
						VarpDefinition.method632(i_46_, (byte) -30, i_1_, is, i_47_);
						VarpDefinition.method632(i_44_, (byte) -30, i_3_, is, i_46_);
					}
				}
			}
		}
	}
	
	static final void sendGameMessage(int i, int i_48_, RSString string, RSString username) {
		StaticMethods2.sendPublicChat(i, i_48_, string, null, username, (byte) -100);
	}
	
	public static void method1469(int i) {
		aClass16_1621 = null;
		ObjType.itemModelContainer = null;
		if (i == 586448449) {
			aClass16_1620 = null;
			SeqTypeList.seqsJs5 = null;
		}
	}
	
	static final int method1470(byte b, int i) {
		return (0xe8405 & i) >> 17;
	}
	
	static {
		aClass16_1620 = RSString.createString("Bitte wenden Sie sich an den Kundendienst)3");
		anInt1612 = 0;
		aClass16_1621 = RSString.createString("<col=ffffff>*V");
	}
}
