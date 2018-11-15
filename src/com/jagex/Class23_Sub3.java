package com.jagex;
/* Class23_Sub3 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class23_Sub3 extends Linkable
{
	public static int anInt2069;
	public float[] aFloatArray2070;
	public static Class37[] aClass37Array2071;
	public int anInt2072;
	public byte[][] aByteArrayArray2073;
	public static float[] aFloatArray2074;
	public static Class22[] aClass22Array2075;
	public static float[] aFloatArray2076;
	public static int[] anIntArray2077;
	public static int[] anIntArray2078;
	public static float[] aFloatArray2079;
	public boolean aBoolean2080;
	public static int[] anIntArray2081;
	public static int anInt2082;
	public static int anInt2083;
	public static float[] aFloatArray2084;
	static Class77[] aClass77Array2085;
	public int anInt2086;
	public int anInt2087;
	public static boolean aBoolean2088 = false;
	public static float[] aFloatArray2089;
	public static byte[] aByteArray2090;
	public static float[] aFloatArray2091;
	public static int anInt2092;
	public static float[] aFloatArray2093;
	public static boolean[] aBooleanArray2094;
	public int anInt2095;
	public static Class101[] aClass101Array2096;
	public int anInt2097;
	public int anInt2098;
	public boolean aBoolean2099;
	public int anInt2100;
	public int anInt2101;
	public byte[] aByteArray2102;

	public static final Class23_Sub3 a(Js5 var_js5, int i) {
		if (!method242(var_js5)) {
			var_js5.is_file_cached(i);
			return null;
		}
		byte[] is = var_js5.get_file(i);
		if (is == null)
			return null;
		return new Class23_Sub3(is);
	}
	
	public static final boolean method242(Js5 class105) {
		if (!aBoolean2088) {
			byte[] bs = class105.get_file(0, 0);
			if (bs == null) {
				return false;
			}
			method248(bs);
			aBoolean2088 = true;
		}
		return true;
	}
	
	public static final void method243(byte[] bs, int i) {
		aByteArray2090 = bs;
		anInt2092 = i;
		anInt2082 = 0;
	}
	
	public static void method244() {
		aByteArray2090 = null;
		aClass77Array2085 = null;
		aClass101Array2096 = null;
		aClass37Array2071 = null;
		aClass22Array2075 = null;
		aBooleanArray2094 = null;
		anIntArray2077 = null;
		aFloatArray2084 = null;
		aFloatArray2079 = null;
		aFloatArray2091 = null;
		aFloatArray2074 = null;
		aFloatArray2093 = null;
		aFloatArray2089 = null;
		aFloatArray2076 = null;
		anIntArray2081 = null;
		anIntArray2078 = null;
	}
	
	public final float[] method245(int i) {
		method243(aByteArrayArray2073[i], 0);
		method252();
		int i_0_ = method250(Class100.method1504(8, anIntArray2077.length - 1));
		boolean bool = aBooleanArray2094[i_0_];
		int i_1_ = bool ? anInt2069 : anInt2083;
		boolean bool_2_ = false;
		boolean bool_3_ = false;
		if (bool) {
			bool_2_ = method252() != 0;
			bool_3_ = method252() != 0;
		}
		int i_4_ = i_1_ >> 1;
		int i_5_;
		int i_6_;
		int i_7_;
		if (bool && !bool_2_) {
			i_5_ = (i_1_ >> 2) - (anInt2083 >> 2);
			i_6_ = (i_1_ >> 2) + (anInt2083 >> 2);
			i_7_ = anInt2083 >> 1;
		} else {
			i_5_ = 0;
			i_6_ = i_4_;
			i_7_ = i_1_ >> 1;
		}
		int i_8_;
		int i_9_;
		int i_10_;
		if (bool && !bool_3_) {
			i_8_ = i_1_ - (i_1_ >> 2) - (anInt2083 >> 2);
			i_9_ = i_1_ - (i_1_ >> 2) + (anInt2083 >> 2);
			i_10_ = anInt2083 >> 1;
		} else {
			i_8_ = i_4_;
			i_9_ = i_1_;
			i_10_ = i_1_ >> 1;
		}
		Class22 class22 = aClass22Array2075[anIntArray2077[i_0_]];
		int i_11_ = class22.anInt346;
		int i_12_ = class22.anIntArray344[i_11_];
		boolean bool_13_ = !aClass101Array2096[i_12_].method1506();
		boolean bool_14_ = bool_13_;
		for (int i_15_ = 0; i_15_ < class22.anInt345; i_15_++) {
			Class37 class37 = aClass37Array2071[class22.anIntArray343[i_15_]];
			float[] fs = aFloatArray2084;
			class37.method991(fs, i_1_ >> 1, bool_14_);
		}
		if (!bool_13_) {
			int i_16_ = class22.anInt346;
			int i_17_ = class22.anIntArray344[i_16_];
			aClass101Array2096[i_17_].method1510(aFloatArray2084, i_1_ >> 1);
		}
		if (bool_13_) {
			for (int i_18_ = i_1_ >> 1; i_18_ < i_1_; i_18_++)
				aFloatArray2084[i_18_] = 0.0F;
		} else {
			int i_19_ = i_1_ >> 1;
			int i_20_ = i_1_ >> 2;
			int i_21_ = i_1_ >> 3;
			float[] fs = aFloatArray2084;
			for (int i_22_ = 0; i_22_ < i_19_; i_22_++)
				fs[i_22_] *= 0.5F;
			for (int i_23_ = i_19_; i_23_ < i_1_; i_23_++)
				fs[i_23_] = -fs[i_1_ - i_23_ - 1];
			float[] fs_24_ = bool ? aFloatArray2093 : aFloatArray2079;
			float[] fs_25_ = bool ? aFloatArray2089 : aFloatArray2091;
			float[] fs_26_ = bool ? aFloatArray2076 : aFloatArray2074;
			int[] is = bool ? anIntArray2078 : anIntArray2081;
			for (int i_27_ = 0; i_27_ < i_20_; i_27_++) {
				float f = fs[4 * i_27_] - fs[i_1_ - 4 * i_27_ - 1];
				float f_28_ = fs[4 * i_27_ + 2] - fs[i_1_ - 4 * i_27_ - 3];
				float f_29_ = fs_24_[2 * i_27_];
				float f_30_ = fs_24_[2 * i_27_ + 1];
				fs[i_1_ - 4 * i_27_ - 1] = f * f_29_ - f_28_ * f_30_;
				fs[i_1_ - 4 * i_27_ - 3] = f * f_30_ + f_28_ * f_29_;
			}
			for (int i_31_ = 0; i_31_ < i_21_; i_31_++) {
				float f = fs[i_19_ + 3 + 4 * i_31_];
				float f_32_ = fs[i_19_ + 1 + 4 * i_31_];
				float f_33_ = fs[4 * i_31_ + 3];
				float f_34_ = fs[4 * i_31_ + 1];
				fs[i_19_ + 3 + 4 * i_31_] = f + f_33_;
				fs[i_19_ + 1 + 4 * i_31_] = f_32_ + f_34_;
				float f_35_ = fs_24_[i_19_ - 4 - 4 * i_31_];
				float f_36_ = fs_24_[i_19_ - 3 - 4 * i_31_];
				fs[4 * i_31_ + 3] = (f - f_33_) * f_35_ - (f_32_ - f_34_) * f_36_;
				fs[4 * i_31_ + 1] = (f_32_ - f_34_) * f_35_ + (f - f_33_) * f_36_;
			}
			int i_37_ = Class100.method1504(8, i_1_ - 1);
			for (int i_38_ = 0; i_38_ < i_37_ - 3; i_38_++) {
				int i_39_ = i_1_ >> i_38_ + 2;
				int i_40_ = 8 << i_38_;
				for (int i_41_ = 0; i_41_ < 2 << i_38_; i_41_++) {
					int i_42_ = i_1_ - i_39_ * 2 * i_41_;
					int i_43_ = i_1_ - i_39_ * (2 * i_41_ + 1);
					for (int i_44_ = 0; i_44_ < i_1_ >> i_38_ + 4; i_44_++) {
						int i_45_ = 4 * i_44_;
						float f = fs[i_42_ - 1 - i_45_];
						float f_46_ = fs[i_42_ - 3 - i_45_];
						float f_47_ = fs[i_43_ - 1 - i_45_];
						float f_48_ = fs[i_43_ - 3 - i_45_];
						fs[i_42_ - 1 - i_45_] = f + f_47_;
						fs[i_42_ - 3 - i_45_] = f_46_ + f_48_;
						float f_49_ = fs_24_[i_44_ * i_40_];
						float f_50_ = fs_24_[i_44_ * i_40_ + 1];
						fs[i_43_ - 1 - i_45_] = (f - f_47_) * f_49_ - (f_46_ - f_48_) * f_50_;
						fs[i_43_ - 3 - i_45_] = (f_46_ - f_48_) * f_49_ + (f - f_47_) * f_50_;
					}
				}
			}
			for (int i_51_ = 1; i_51_ < i_21_ - 1; i_51_++) {
				int i_52_ = is[i_51_];
				if (i_51_ < i_52_) {
					int i_53_ = 8 * i_51_;
					int i_54_ = 8 * i_52_;
					float f = fs[i_53_ + 1];
					fs[i_53_ + 1] = fs[i_54_ + 1];
					fs[i_54_ + 1] = f;
					f = fs[i_53_ + 3];
					fs[i_53_ + 3] = fs[i_54_ + 3];
					fs[i_54_ + 3] = f;
					f = fs[i_53_ + 5];
					fs[i_53_ + 5] = fs[i_54_ + 5];
					fs[i_54_ + 5] = f;
					f = fs[i_53_ + 7];
					fs[i_53_ + 7] = fs[i_54_ + 7];
					fs[i_54_ + 7] = f;
				}
			}
			for (int i_55_ = 0; i_55_ < i_19_; i_55_++)
				fs[i_55_] = fs[2 * i_55_ + 1];
			for (int i_56_ = 0; i_56_ < i_21_; i_56_++) {
				fs[i_1_ - 1 - 2 * i_56_] = fs[4 * i_56_];
				fs[i_1_ - 2 - 2 * i_56_] = fs[4 * i_56_ + 1];
				fs[i_1_ - i_20_ - 1 - 2 * i_56_] = fs[4 * i_56_ + 2];
				fs[i_1_ - i_20_ - 2 - 2 * i_56_] = fs[4 * i_56_ + 3];
			}
			for (int i_57_ = 0; i_57_ < i_21_; i_57_++) {
				float f = fs_26_[2 * i_57_];
				float f_58_ = fs_26_[2 * i_57_ + 1];
				float f_59_ = fs[i_19_ + 2 * i_57_];
				float f_60_ = fs[i_19_ + 2 * i_57_ + 1];
				float f_61_ = fs[i_1_ - 2 - 2 * i_57_];
				float f_62_ = fs[i_1_ - 1 - 2 * i_57_];
				float f_63_ = f_58_ * (f_59_ - f_61_) + f * (f_60_ + f_62_);
				fs[i_19_ + 2 * i_57_] = (f_59_ + f_61_ + f_63_) * 0.5F;
				fs[i_1_ - 2 - 2 * i_57_] = (f_59_ + f_61_ - f_63_) * 0.5F;
				f_63_ = f_58_ * (f_60_ + f_62_) - f * (f_59_ - f_61_);
				fs[i_19_ + 2 * i_57_ + 1] = (f_60_ - f_62_ + f_63_) * 0.5F;
				fs[i_1_ - 1 - 2 * i_57_] = (-f_60_ + f_62_ + f_63_) * 0.5F;
			}
			for (int i_64_ = 0; i_64_ < i_20_; i_64_++) {
				fs[i_64_] = fs[2 * i_64_ + i_19_] * fs_25_[2 * i_64_] + fs[2 * i_64_ + 1 + i_19_] * fs_25_[2 * i_64_ + 1];
				fs[i_19_ - 1 - i_64_] = fs[2 * i_64_ + i_19_] * fs_25_[2 * i_64_ + 1] - fs[2 * i_64_ + 1 + i_19_] * fs_25_[2 * i_64_];
			}
			for (int i_65_ = 0; i_65_ < i_20_; i_65_++)
				fs[i_1_ - i_20_ + i_65_] = -fs[i_65_];
			for (int i_66_ = 0; i_66_ < i_20_; i_66_++)
				fs[i_66_] = fs[i_20_ + i_66_];
			for (int i_67_ = 0; i_67_ < i_20_; i_67_++)
				fs[i_20_ + i_67_] = -fs[i_20_ - i_67_ - 1];
			for (int i_68_ = 0; i_68_ < i_20_; i_68_++)
				fs[i_19_ + i_68_] = fs[i_1_ - i_68_ - 1];
			for (int i_69_ = i_5_; i_69_ < i_6_; i_69_++) {
				float f = (float) Math.sin((i_69_ - i_5_ + 0.5) / i_7_ * 0.5 * 3.141592653589793);
				aFloatArray2084[i_69_] *= (float) Math.sin(1.5707963267948966 * f * f);
			}
			for (int i_70_ = i_8_; i_70_ < i_9_; i_70_++) {
				float f = (float) Math.sin((i_70_ - i_8_ + 0.5) / i_10_ * 0.5 * 3.141592653589793 + 1.5707963267948966);
				aFloatArray2084[i_70_] *= (float) Math.sin(1.5707963267948966 * f * f);
			}
		}
		float[] fs = null;
		if (anInt2095 > 0) {
			int i_71_ = anInt2095 + i_1_ >> 2;
			fs = new float[i_71_];
			if (!aBoolean2080) {
				for (int i_72_ = 0; i_72_ < anInt2097; i_72_++) {
					int i_73_ = (anInt2095 >> 1) + i_72_;
					fs[i_72_] += aFloatArray2070[i_73_];
				}
			}
			if (!bool_13_) {
				for (int i_74_ = i_5_; i_74_ < i_1_ >> 1; i_74_++) {
					int i_75_ = fs.length - (i_1_ >> 1) + i_74_;
					fs[i_75_] += aFloatArray2084[i_74_];
				}
			}
		}
		float[] fs_76_ = aFloatArray2070;
		aFloatArray2070 = aFloatArray2084;
		aFloatArray2084 = fs_76_;
		anInt2095 = i_1_;
		anInt2097 = i_9_ - (i_1_ >> 1);
		aBoolean2080 = bool_13_;
		return fs;
	}
	
	static final Class23_Sub3 method246(Js5 class105, int archiveId, int fileId) {
		if (!method242(class105)) {
			class105.is_file_cached(archiveId, fileId);
			return null;
		}
		byte[] bs = class105.get_file(archiveId, fileId);
		if (bs == null) {
			return null;
		}
		return new Class23_Sub3(bs);
	}
	
	final SomeSoundClass2 method247(int[] is) {
		if (is != null && is[0] <= 0) {
			return null;
		}
		if (aByteArray2102 == null) {
			anInt2095 = 0;
			aFloatArray2070 = new float[anInt2069];
			aByteArray2102 = new byte[anInt2086];
			anInt2100 = 0;
			anInt2101 = 0;
		}
		for (/**/; anInt2101 < aByteArrayArray2073.length; anInt2101++) {
			if (is != null && is[0] <= 0) {
				return null;
			}
			float[] fs = method245(anInt2101);
			if (fs != null) {
				int i = anInt2100;
				int i_78_ = fs.length;
				if (i_78_ > anInt2086 - i) {
					i_78_ = anInt2086 - i;
				}
				for (int i_79_ = 0; i_79_ < i_78_; i_79_++) {
					int i_80_ = (int) (128.0F + fs[i_79_] * 128.0F);
					if ((i_80_ & ~0xff) != 0) {
						i_80_ = (i_80_ ^ 0xffffffff) >> 31;
					}
					aByteArray2102[i++] = (byte) (i_80_ - 128);
				}
				if (is != null) {
					is[0] -= i - anInt2100;
				}
				anInt2100 = i;
			}
		}
		aFloatArray2070 = null;
		byte[] bs = aByteArray2102;
		aByteArray2102 = null;
		return new SomeSoundClass2(anInt2072, bs, anInt2098, anInt2087, aBoolean2099);
	}
	
	public static final void method248(byte[] bs) {
		method243(bs, 0);
		anInt2083 = 1 << method250(4);
		anInt2069 = 1 << method250(4);
		aFloatArray2084 = new float[anInt2069];
		for (int i = 0; i < 2; i++) {
			int i_81_ = i != 0 ? anInt2069 : anInt2083;
			int i_82_ = i_81_ >> 1;
			int i_83_ = i_81_ >> 2;
			int i_84_ = i_81_ >> 3;
			float[] fs = new float[i_82_];
			for (int i_85_ = 0; i_85_ < i_83_; i_85_++) {
				fs[2 * i_85_] = (float) Math.cos(4 * i_85_ * 3.141592653589793 / i_81_);
				fs[2 * i_85_ + 1] = -(float) Math.sin(4 * i_85_ * 3.141592653589793 / i_81_);
			}
			float[] fs_86_ = new float[i_82_];
			for (int i_87_ = 0; i_87_ < i_83_; i_87_++) {
				fs_86_[2 * i_87_] = (float) Math.cos((2 * i_87_ + 1) * 3.141592653589793 / (2 * i_81_));
				fs_86_[2 * i_87_ + 1] = (float) Math.sin((2 * i_87_ + 1) * 3.141592653589793 / (2 * i_81_));
			}
			float[] fs_88_ = new float[i_83_];
			for (int i_89_ = 0; i_89_ < i_84_; i_89_++) {
				fs_88_[2 * i_89_] = (float) Math.cos((4 * i_89_ + 2) * 3.141592653589793 / i_81_);
				fs_88_[2 * i_89_ + 1] = -(float) Math.sin((4 * i_89_ + 2) * 3.141592653589793 / i_81_);
			}
			int[] is = new int[i_84_];
			int i_90_ = Class100.method1504(8, i_84_ - 1);
			for (int i_91_ = 0; i_91_ < i_84_; i_91_++)
				is[i_91_] = FaceNormal.method1106(i_90_, i_91_, 32768);
			if (i != 0) {
				aFloatArray2093 = fs;
				aFloatArray2089 = fs_86_;
				aFloatArray2076 = fs_88_;
				anIntArray2078 = is;
			} else {
				aFloatArray2079 = fs;
				aFloatArray2091 = fs_86_;
				aFloatArray2074 = fs_88_;
				anIntArray2081 = is;
			}
		}
		int i = method250(8) + 1;
		aClass77Array2085 = new Class77[i];
		for (int i_92_ = 0; i_92_ < i; i_92_++)
			aClass77Array2085[i_92_] = new Class77();
		int i_93_ = method250(6) + 1;
		for (int i_94_ = 0; i_94_ < i_93_; i_94_++)
			method250(16);
		int i_95_ = method250(6) + 1;
		aClass101Array2096 = new Class101[i_95_];
		for (int i_96_ = 0; i_96_ < i_95_; i_96_++)
			aClass101Array2096[i_96_] = new Class101();
		int i_97_ = method250(6) + 1;
		aClass37Array2071 = new Class37[i_97_];
		for (int i_98_ = 0; i_98_ < i_97_; i_98_++)
			aClass37Array2071[i_98_] = new Class37();
		int i_99_ = method250(6) + 1;
		aClass22Array2075 = new Class22[i_99_];
		for (int i_100_ = 0; i_100_ < i_99_; i_100_++)
			aClass22Array2075[i_100_] = new Class22();
		int i_101_ = method250(6) + 1;
		aBooleanArray2094 = new boolean[i_101_];
		anIntArray2077 = new int[i_101_];
		for (int i_102_ = 0; i_102_ < i_101_; i_102_++) {
			aBooleanArray2094[i_102_] = method252() != 0;
			method250(16);
			method250(16);
			anIntArray2077[i_102_] = method250(8);
		}
	}
	
	static final float method249(int i) {
		int i_103_ = i & 0x1fffff;
		int i_104_ = i & ~0x7fffffff;
		int i_105_ = (i & 0x7fe00000) >> 21;
		if (i_104_ != 0) {
			i_103_ = -i_103_;
		}
		return (float) (i_103_ * Math.pow(2.0, i_105_ - 788));
	}
	
	static final int method250(int i) {
		int i_106_ = 0;
		int i_107_ = 0;
		int i_108_;
		for (/**/; i >= 8 - anInt2082; i -= i_108_) {
			i_108_ = 8 - anInt2082;
			int i_109_ = (1 << i_108_) - 1;
			i_106_ += (aByteArray2090[anInt2092] >> anInt2082 & i_109_) << i_107_;
			anInt2082 = 0;
			anInt2092++;
			i_107_ += i_108_;
		}
		if (i > 0) {
			i_108_ = (1 << i) - 1;
			i_106_ += (aByteArray2090[anInt2092] >> anInt2082 & i_108_) << i_107_;
			anInt2082 += i;
		}
		return i_106_;
	}
	
	public final void method251(byte[] bs) {
		Packet class23_sub5 = new Packet(bs);
		anInt2072 = class23_sub5.g4();
		anInt2086 = class23_sub5.g4();
		anInt2098 = class23_sub5.g4();
		anInt2087 = class23_sub5.g4();
		if (anInt2087 < 0) {
			anInt2087 = anInt2087 ^ 0xffffffff;
			aBoolean2099 = true;
		}
		int i = class23_sub5.g4();
		aByteArrayArray2073 = new byte[i][];
		for (int i_110_ = 0; i_110_ < i; i_110_++) {
			int i_111_ = 0;
			int i_112_;
			do {
				i_112_ = class23_sub5.g1();
				i_111_ += i_112_;
			} while (i_112_ >= 255);
			byte[] bs_113_ = new byte[i_111_];
			class23_sub5.get(bs_113_, 0, i_111_);
			aByteArrayArray2073[i_110_] = bs_113_;
		}
	}
	
	static final int method252() {
		int i = aByteArray2090[anInt2092] >> anInt2082 & 0x1;
		anInt2082++;
		anInt2092 += anInt2082 >> 3;
		anInt2082 &= 0x7;
		return i;
	}
	
	public Class23_Sub3(byte[] bs) {
		method251(bs);
	}
}
