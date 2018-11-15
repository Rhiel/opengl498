package com.rs2.client.scene.environment;

import java.util.Random;

import com.jagex.core.tools.MathTools;
import com.rs2.client.scene.light.Class271;
import com.rs2.client.scene.light.Class273;

public class FlickeringTextureGenerator {
	public static float aFloat7861 = 4096.0F;
	public static int anInt7862 = 4096;
	public static int anInt7863 = 8192;
	public static int anInt7864 = 4095;
	public static int anInt7865 = 2048;
	public static final int anInt2352 = 4096;
	public static int[] anIntArray2362 = new int[anInt2352];
	public static int anInt7866 = 12;
	public static int anInt9841 = 4;
	public byte[] aByteArray9842 = new byte[512];
	public static int anInt9843 = 4;
	public static int anInt9844 = 0;
	public short[] aShortArray9845;
	public int anInt9846;
	public short[] aShortArray9847;
	public static int anInt9848 = 1638;
	public int anInt9849 = 0;
	public int anInt9850 = 1673664476;
	public int anInt9851 = -197967564;
	public int anInt9852 = 2124597948;
	public boolean aBoolean9853;
	public static int anInt5948;
	public static int[] anIntArray5949;
	public static int[] anIntArray5954;
	public static int anInt5950;
	public static int[] anIntArray3451;
	public static boolean aBoolean9854 = true;
	public static int anInt9855 = 8;

	public void method3849(int i, int[] is) {
		int i_1_ = FlickeringTextureGenerator.anIntArray5954[i] * anInt9851 * 853207173;
		if (1 == anInt9852 * 138667087) {
			int i_2_ = aShortArray9845[0];
			int i_3_ = aShortArray9847[0] << anInt7866;
			int i_4_ = i_3_ * 853207173 * anInt9851 >> anInt7866;
			int i_5_ = 1549519815 * anInt9850 * i_3_ >> anInt7866;
			int i_6_ = i_3_ * i_1_ >> anInt7866;
			int i_7_ = i_6_ >> anInt7866;
			int i_8_ = i_7_ + 1;
			if (i_8_ >= i_4_) {
				i_8_ = 0;
			}
			i_6_ &= 0xfff;
			int i_9_ = anIntArray2362[i_6_];
			int i_10_ = aByteArray9842[i_7_ & 0xff] & 0xff;
			int i_11_ = aByteArray9842[i_8_ & 0xff] & 0xff;
			if (aBoolean9853) {
				for (int i_12_ = 0; i_12_ < -1507352525 * FlickeringTextureGenerator.anInt5948; i_12_++) {
					int i_13_ = anInt9850 * 1549519815 * FlickeringTextureGenerator.anIntArray5949[i_12_];
					int i_14_ = method3852(i_3_ * i_13_ >> anInt7866, i_6_, i_10_, i_11_, i_9_, i_5_);
					i_14_ = i_2_ * i_14_ >> anInt7866;
					is[i_12_] = anInt7865 + (i_14_ >> 1);
				}
			} else {
				for (int i_15_ = 0; i_15_ < -1507352525 * FlickeringTextureGenerator.anInt5948; i_15_++) {
					int i_16_ = anInt9850 * 1549519815 * FlickeringTextureGenerator.anIntArray5949[i_15_];
					int i_17_ = method3852(i_3_ * i_16_ >> anInt7866, i_6_, i_10_, i_11_, i_9_, i_5_);
					is[i_15_] = i_2_ * i_17_ >> anInt7866;
				}
			}
		} else {
			int i_18_ = aShortArray9845[0];
			if (i_18_ > 8 || i_18_ < -8) {
				int i_19_ = aShortArray9847[0] << anInt7866;
				int i_20_ = anInt9851 * 853207173 * i_19_ >> anInt7866;
				int i_21_ = anInt9850 * 1549519815 * i_19_ >> anInt7866;
				int i_22_ = i_1_ * i_19_ >> anInt7866;
				int i_23_ = i_22_ >> anInt7866;
				int i_24_ = 1 + i_23_;
				if (i_24_ >= i_20_) {
					i_24_ = 0;
				}
				i_22_ &= 0xfff;
				int i_25_ = anIntArray2362[i_22_];
				int i_26_ = aByteArray9842[i_23_ & 0xff] & 0xff;
				int i_27_ = aByteArray9842[i_24_ & 0xff] & 0xff;
				for (int i_28_ = 0; i_28_ < -1507352525 * FlickeringTextureGenerator.anInt5948; i_28_++) {
					int i_29_ = anInt9850 * 1549519815 * FlickeringTextureGenerator.anIntArray5949[i_28_];
					int i_30_ = method3852(i_29_ * i_19_ >> anInt7866, i_22_, i_26_, i_27_, i_25_, i_21_);
					is[i_28_] = i_30_ * i_18_ >> anInt7866;
				}
			}
			for (int i_31_ = 1; i_31_ < 138667087 * anInt9852; i_31_++) {
				i_18_ = aShortArray9845[i_31_];
				if (i_18_ > 8 || i_18_ < -8) {
					int i_32_ = aShortArray9847[i_31_] << anInt7866;
					int i_33_ = 853207173 * anInt9851 * i_32_ >> anInt7866;
					int i_34_ = 1549519815 * anInt9850 * i_32_ >> anInt7866;
					int i_35_ = i_32_ * i_1_ >> anInt7866;
					int i_36_ = i_35_ >> anInt7866;
					int i_37_ = i_36_ + 1;
					if (i_37_ >= i_33_) {
						i_37_ = 0;
					}
					i_35_ &= 0xfff;
					int i_38_ = anIntArray2362[i_35_];
					int i_39_ = aByteArray9842[i_36_ & 0xff] & 0xff;
					int i_40_ = aByteArray9842[i_37_ & 0xff] & 0xff;
					if (aBoolean9853 && i_31_ == anInt9852 * 138667087 - 1) {
						for (int i_41_ = 0; i_41_ < -1507352525 * FlickeringTextureGenerator.anInt5948; i_41_++) {
							int i_42_ = FlickeringTextureGenerator.anIntArray5949[i_41_] * 1549519815 * anInt9850;
							int i_43_ = method3852(i_42_ * i_32_ >> anInt7866, i_35_, i_39_, i_40_, i_38_, i_34_);
							i_43_ = is[i_41_] + (i_43_ * i_18_ >> anInt7866);
							is[i_41_] = anInt7865 + (i_43_ >> 1);
						}
					} else {
						for (int i_44_ = 0; i_44_ < FlickeringTextureGenerator.anInt5948 * -1507352525; i_44_++) {
							int i_45_ = FlickeringTextureGenerator.anIntArray5949[i_44_] * anInt9850 * 1549519815;
							int i_46_ = method3852(i_45_ * i_32_ >> anInt7866, i_35_, i_39_, i_40_, i_38_, i_34_);
							is[i_44_] += i_46_ * i_18_ >> anInt7866;
						}
					}
				}
			}
		}
	}

	public void method3850() {
		aByteArray9842 = method6176(-345374061 * anInt9849);
		method3851();
		for (int i_47_ = 138667087 * anInt9852 - 1; i_47_ >= 1; i_47_--) {
			short i_48_ = aShortArray9845[i_47_];
			if (i_48_ > 8 || i_48_ < -8) {
				break;
			}
			anInt9852 -= 1604891311;
		}
	}

	public FlickeringTextureGenerator() {
		super();
		anInt9846 = -554748930;
		aBoolean9853 = true;
	}

	void method3851() {
		if (-944615731 * anInt9846 > 0) {
			aShortArray9845 = new short[138667087 * anInt9852];
			aShortArray9847 = new short[138667087 * anInt9852];
			for (int i_49_ = 0; i_49_ < 138667087 * anInt9852; i_49_++) {
				aShortArray9845[i_49_] = (short) (int) (Math.pow(-944615731 * anInt9846 / aFloat7861, i_49_) * aFloat7861);
				aShortArray9847[i_49_] = (short) (int) Math.pow(2.0, i_49_);
			}
		} else if (aShortArray9845 != null && aShortArray9845.length == anInt9852 * 138667087) {
			aShortArray9847 = new short[anInt9852 * 138667087];
			for (int i_50_ = 0; i_50_ < anInt9852 * 138667087; i_50_++) {
				aShortArray9847[i_50_] = (short) (int) Math.pow(2.0, i_50_);
			}
		}
	}

	int method3852(int i, int i_51_, int i_52_, int i_53_, int i_54_, int i_55_) {
		int i_57_ = i >> anInt7866;
		int i_58_ = 1 + i_57_;
		if (i_58_ >= i_55_) {
			i_58_ = 0;
		}
		i &= 0xfff;
		i_57_ &= 0xff;
		i_58_ &= 0xff;
		int i_59_ = i - anInt7862;
		int i_60_ = i_51_ - anInt7862;
		int i_61_ = anIntArray2362[i];
		int i_62_ = aByteArray9842[i_57_ + i_52_] & 0x3;
		int i_63_;
		if (i_62_ <= 1) {
			i_63_ = i_62_ == 0 ? i_51_ + i : i_51_ - i;
		} else {
			i_63_ = 2 == i_62_ ? i - i_51_ : -i - i_51_;
		}
		i_62_ = aByteArray9842[i_58_ + i_52_] & 0x3;
		int i_64_;
		if (i_62_ <= 1) {
			i_64_ = 0 == i_62_ ? i_51_ + i_59_ : i_51_ - i_59_;
		} else {
			i_64_ = 2 == i_62_ ? i_59_ - i_51_ : -i_59_ - i_51_;
		}
		int i_65_ = (i_61_ * (i_64_ - i_63_) >> anInt7866) + i_63_;
		i_62_ = aByteArray9842[i_53_ + i_57_] & 0x3;
		if (i_62_ <= 1) {
			i_63_ = i_62_ == 0 ? i + i_60_ : i_60_ - i;
		} else {
			i_63_ = 2 == i_62_ ? i - i_60_ : -i - i_60_;
		}
		i_62_ = aByteArray9842[i_53_ + i_58_] & 0x3;
		if (i_62_ <= 1) {
			i_64_ = 0 == i_62_ ? i_60_ + i_59_ : i_60_ - i_59_;
		} else {
			i_64_ = i_62_ == 2 ? i_59_ - i_60_ : -i_59_ - i_60_;
		}
		int i_66_ = ((i_64_ - i_63_) * i_61_ >> anInt7866) + i_63_;
		return i_65_ + ((i_66_ - i_65_) * i_54_ >> anInt7866);
	}

	public static int[] method5620(int i, int i_1_, int i_2_, int i_3_, int i_4_, float f, boolean bool) {
		int[] is = new int[i];
		FlickeringTextureGenerator class330_sub49_sub1 = new FlickeringTextureGenerator();
		class330_sub49_sub1.anInt9849 = 242183067 * i_1_;
		class330_sub49_sub1.aBoolean9853 = bool;
		class330_sub49_sub1.anInt9850 = i_2_ * -1729067529;
		class330_sub49_sub1.anInt9851 = i_3_ * 2097991757;
		class330_sub49_sub1.anInt9852 = i_4_ * 1604891311;
		class330_sub49_sub1.anInt9846 = 2005552645 * (int) (f * 4096.0F);
		class330_sub49_sub1.method3850();
		method6178(i, 1);
		class330_sub49_sub1.method3849(0, is);
		return is;

	}

	public static void method6178(int i, int i_9_) {
		if (i != -1507352525 * anInt5948) {
			anIntArray5949 = new int[i];
			for (int i_11_ = 0; i_11_ < i; i_11_++) {
				anIntArray5949[i_11_] = (i_11_ << 12) / i;
			}
			anInt5948 = i * -1881384197;
		}
		if (i_9_ != -871786603 * anInt5950) {
			if (anInt5948 * -1507352525 != i_9_) {
				anIntArray5954 = new int[i_9_];
				for (int i_12_ = 0; i_12_ < i_9_; i_12_++) {
					anIntArray5954[i_12_] = (i_12_ << 12) / i_9_;
				}
			} else {
				anIntArray5954 = anIntArray5949;
			}
			anInt5950 = -1659044931 * i_9_;
		}
	}

	static {
		for (int i = 0; i < anInt2352; i++) {
			anIntArray2362[i] = method2278(i);
		}
	}

	public static final int method2278(int i) {
		int i_11_ = i * (i * i >> 12) >> 12;
		int i_12_ = i * 6 - 61440;
		int i_13_ = 40960 + (i_12_ * i >> 12);
		return i_13_ * i_11_ >> 12;
	}

	public static byte[] method6176(int i) {
		byte[] is = (byte[]) aClass273_5953.method2680(Integer.valueOf(i));
		if (is == null) {
			is = new byte[512];
			Random random = new Random(i);
			for (int i_1_ = 0; i_1_ < 255; i_1_++) {
				is[i_1_] = (byte) i_1_;
			}
			for (int i_2_ = 0; i_2_ < 255; i_2_++) {
				int i_3_ = 255 - i_2_;
				int i_4_ = MathTools.getRandom(random, i_3_);
				byte i_5_ = is[i_4_];
				is[i_4_] = is[i_3_];
				is[i_3_] = is[511 - i_2_] = i_5_;
			}
			aClass273_5953.method2676(Integer.valueOf(i), is);
		}
		return is;
	}

	public static int[][] method3378(int i, int i_1_, int i_3_, int i_4_, int i_5_, float f, boolean bool) {
		int[][] is = new int[i_1_][i];
		FlickeringTextureGenerator class330_sub49_sub1 = new FlickeringTextureGenerator();
		class330_sub49_sub1.aBoolean9853 = bool;
		class330_sub49_sub1.anInt9850 = -1729067529 * i_3_;
		class330_sub49_sub1.anInt9851 = 2097991757 * i_4_;
		class330_sub49_sub1.anInt9852 = i_5_ * 1604891311;
		class330_sub49_sub1.anInt9846 = 2005552645 * (int) (f * 4096.0F);
		class330_sub49_sub1.method3850();
		method6178(i, i_1_);
		for (int i_7_ = 0; i_7_ < i_1_; i_7_++) {
			class330_sub49_sub1.method3849(i_7_, is[i_7_]);
		}
		return is;
	}

	public static Class273 aClass273_5953 = new Class273(16, Class271.aClass271_2755);
}
