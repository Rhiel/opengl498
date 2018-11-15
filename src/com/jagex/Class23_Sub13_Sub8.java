package com.jagex;
/* Class23_Sub13_Sub8 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.util.Random;

abstract class Class23_Sub13_Sub8 extends Queuable
{
	public static int anInt3807;
	public static RSString aClass16_3808;
	public int anInt3809;
	public static RSString aClass16_3810;
	public int anInt3811 = 0;
	public static RSString softHyphenTag;
	public byte[] aByteArray3813;
	public static RSString multiplierTag = RSString.createString("times");
	public static int anInt3815;
	public static RSString aClass16_3816;
	public static int anInt3817;
	public static RSString registrationTag;
	public int anInt3819;
	public static RSString aClass16_3820;
	public static RSString greaterThanTag;
	public Class43[] aClass43Array3822;
	public static RSString[] aClass16Array3823;
	public static RSString imageTag;
	public int[] anIntArray3825;
	public static RSString aClass16_3826;
	public static int anInt3827;
	public static int anInt3828;
	public static int anInt3829;
	public static RSString aClass16_3830;
	public int[] anIntArray3831;
	public static RSString aClass16_3832;
	public int[] anIntArray3833;
	public static int anInt3834;
	public static int anInt3835;
	public int[] anIntArray3836;
	public static RSString aClass16_3837;
	public static RSString euroTag;
	public static int anInt3839;
	public static RSString copyRightTag;
	public static RSString aClass16_3841;
	public static RSString lesserThanTag;
	public static RSString aClass16_3843;
	public static RSString aClass16_3844;
	public static RSString aClass16_3845;
	public int[] anIntArray3846;
	public int[] anIntArray3847;
	public static RSString nonBreakingSpaceTag;
	public static RSString aClass16_3849;
	public static RSString aClass16_3850;
	public static int anInt3851;
	
	final int method648(RSString class16, int i, int i_0_, int i_1_, int i_2_, Random random, int i_3_) {
		if (class16 == null) {
			return 0;
		}
		random.setSeed(i_3_);
		method658(i_1_, i_2_, 192 + (random.nextInt() & 0x1f));
		int[] is = new int[class16.length];
		int i_4_ = 0;
		for (int i_5_ = 0; i_5_ < class16.length; i_5_++) {
			is[i_5_] = i_4_;
			if ((random.nextInt() & 0x3) == 0) {
				i_4_++;
			}
		}
		method651(class16, i, i_0_, is, null);
		return i_4_;
	}
	
	final int method649(RSString class16) {
		if (class16 == null) {
			return 0;
		}
		int i = -1;
		int i_6_ = -1;
		int i_7_ = 0;
		for (int i_8_ = 0; i_8_ < class16.length; i_8_++) {
			int i_9_ = class16.bytes[i_8_] & 0xff;
			if (i_9_ == 60) {
				i = i_8_;
			} else {
				if (i_9_ == 62 && i != -1) {
					RSString class16_10_ = class16.substring(i_8_, i + 1);
					i = -1;
					if (class16_10_.equals(lesserThanTag)) {
						i_9_ = 60;
					} else if (class16_10_.equals(greaterThanTag)) {
						i_9_ = 62;
					} else if (class16_10_.equals(nonBreakingSpaceTag)) {
						i_9_ = 160;
					} else if (class16_10_.equals(softHyphenTag)) {
						i_9_ = 173;
					} else if (class16_10_.equals(multiplierTag)) {
						i_9_ = 215;
					} else if (class16_10_.equals(euroTag)) {
						i_9_ = 128;
					} else if (class16_10_.equals(copyRightTag)) {
						i_9_ = 169;
					} else if (class16_10_.equals(registrationTag)) {
						i_9_ = 174;
					} else {
						if (class16_10_.startsWith(imageTag)) {
							try {
								int i_11_ = class16_10_.substring(4).toInteger();
								i_7_ += aClass43Array3822[i_11_].anInt668;
								i_6_ = -1;
							} catch (Exception exception) {
								/* empty */
							}
						}
						continue;
					}
				}
				if (i == -1) {
					i_7_ += anIntArray3831[i_9_];
					if (aByteArray3813 != null && i_6_ != -1) {
						i_7_ += aByteArray3813[(i_6_ << 8) + i_9_];
					}
					i_6_ = i_9_;
				}
			}
		}
		return i_7_;
	}
	
	final void method650(RSString class16, int i, int i_12_, int i_13_, int i_14_) {
		if (class16 != null) {
			method672(i_13_, i_14_);
			method664(class16, i, i_12_);
		}
	}
	
	public final void method651(RSString class16, int i, int i_15_, int[] is, int[] is_16_) {
		i_15_ -= anInt3811;
		int i_17_ = -1;
		int i_18_ = -1;
		int i_19_ = 0;
		for (int i_20_ = 0; i_20_ < class16.length; i_20_++) {
			int i_21_ = class16.bytes[i_20_] & 0xff;
			if (i_21_ == 60) {
				i_17_ = i_20_;
			} else {
				if (i_21_ == 62 && i_17_ != -1) {
					RSString class16_22_ = class16.substring(i_20_, i_17_ + 1);
					i_17_ = -1;
					if (class16_22_.equals(lesserThanTag)) {
						i_21_ = 60;
					} else if (class16_22_.equals(greaterThanTag)) {
						i_21_ = 62;
					} else if (class16_22_.equals(nonBreakingSpaceTag)) {
						i_21_ = 160;
					} else if (class16_22_.equals(softHyphenTag)) {
						i_21_ = 173;
					} else if (class16_22_.equals(multiplierTag)) {
						i_21_ = 215;
					} else if (class16_22_.equals(euroTag)) {
						i_21_ = 128;
					} else if (class16_22_.equals(copyRightTag)) {
						i_21_ = 169;
					} else if (class16_22_.equals(registrationTag)) {
						i_21_ = 174;
					} else {
						if (class16_22_.startsWith(imageTag)) {
							try {
								int i_23_;
								if (is != null) {
									i_23_ = is[i_19_];
								} else {
									i_23_ = 0;
								}
								int i_24_;
								if (is_16_ != null) {
									i_24_ = is_16_[i_19_];
								} else {
									i_24_ = 0;
								}
								i_19_++;
								int i_25_ = class16_22_.substring(4).toInteger();
								Class43 class43 = aClass43Array3822[i_25_];
								int i_26_ = anIntArray3833 != null ? anIntArray3833[i_25_] : class43.anInt667;
								if (anInt3807 == 256) {
									class43.method1119(i + i_23_, i_15_ + anInt3811 - i_26_ + i_24_);
								} else {
									class43.method1117(i + i_23_, i_15_ + anInt3811 - i_26_ + i_24_, anInt3807);
								}
								i += class43.anInt668;
								i_18_ = -1;
							} catch (Exception exception) {
								/* empty */
							}
						} else {
							method675(class16_22_);
						}
						continue;
					}
				}
				if (i_17_ == -1) {
					if (aByteArray3813 != null && i_18_ != -1) {
						i += aByteArray3813[(i_18_ << 8) + i_21_];
					}
					int i_27_ = anIntArray3836[i_21_];
					int i_28_ = anIntArray3825[i_21_];
					int i_29_;
					if (is != null) {
						i_29_ = is[i_19_];
					} else {
						i_29_ = 0;
					}
					int i_30_;
					if (is_16_ != null) {
						i_30_ = is_16_[i_19_];
					} else {
						i_30_ = 0;
					}
					i_19_++;
					if (i_21_ != 32) {
						if (anInt3807 == 256) {
							if (anInt3829 != -1) {
								method662(i_21_, i + anIntArray3847[i_21_] + 1 + i_29_, i_15_ + anIntArray3846[i_21_] + 1 + i_30_, i_27_, i_28_, anInt3829);
							}
							method663(i_21_, i + anIntArray3847[i_21_] + i_29_, i_15_ + anIntArray3846[i_21_] + i_30_, i_27_, i_28_, anInt3834);
						} else {
							if (anInt3829 != -1) {
								method673(i_21_, i + anIntArray3847[i_21_] + 1 + i_29_, i_15_ + anIntArray3846[i_21_] + 1 + i_30_, i_27_, i_28_, anInt3829, anInt3807);
							}
							method660(i_21_, i + anIntArray3847[i_21_] + i_29_, i_15_ + anIntArray3846[i_21_] + i_30_, i_27_, i_28_, anInt3834, anInt3807);
						}
					} else if (anInt3839 > 0) {
						anInt3817 += anInt3839;
						i += anInt3817 >> 8;
						anInt3817 &= 0xff;
					}
					int i_31_ = anIntArray3831[i_21_];
					if (anInt3815 != -1) {
						Rasterizer2D.draw_horizontal_line(i, i_15_ + (int) (anInt3811 * 0.7), i_31_, anInt3815);
					}
					if (anInt3827 != -1) {
						Rasterizer2D.draw_horizontal_line(i, i_15_ + anInt3811, i_31_, anInt3827);
					}
					i += i_31_;
					i_18_ = i_21_;
				}
			}
		}
	}
	
	public static void method652() {
		lesserThanTag = null;
		greaterThanTag = null;
		nonBreakingSpaceTag = null;
		softHyphenTag = null;
		multiplierTag = null;
		euroTag = null;
		copyRightTag = null;
		registrationTag = null;
		imageTag = null;
		aClass16_3843 = null;
		aClass16_3820 = null;
		aClass16_3837 = null;
		aClass16_3844 = null;
		aClass16_3830 = null;
		aClass16_3841 = null;
		aClass16_3850 = null;
		aClass16_3832 = null;
		aClass16_3810 = null;
		aClass16_3849 = null;
		aClass16_3845 = null;
		aClass16_3816 = null;
		aClass16_3808 = null;
		aClass16_3826 = null;
		aClass16Array3823 = null;
	}
	
	public static final int method653(byte[][] bs, byte[][] bs_32_, int[] is, int[] is_33_, int[] is_34_, int i, int i_35_) {
		int i_36_ = is[i];
		int i_37_ = i_36_ + is_34_[i];
		int i_38_ = is[i_35_];
		int i_39_ = i_38_ + is_34_[i_35_];
		int i_40_ = i_36_;
		if (i_38_ > i_36_) {
			i_40_ = i_38_;
		}
		int i_41_ = i_37_;
		if (i_39_ < i_37_) {
			i_41_ = i_39_;
		}
		int i_42_ = is_33_[i];
		if (is_33_[i_35_] < i_42_) {
			i_42_ = is_33_[i_35_];
		}
		byte[] bs_43_ = bs_32_[i];
		byte[] bs_44_ = bs[i_35_];
		int i_45_ = i_40_ - i_36_;
		int i_46_ = i_40_ - i_38_;
		for (int i_47_ = i_40_; i_47_ < i_41_; i_47_++) {
			int i_48_ = bs_43_[i_45_++] + bs_44_[i_46_++];
			if (i_48_ < i_42_) {
				i_42_ = i_48_;
			}
		}
		return -i_42_;
	}
	
	public final void method654(byte[] bs) {
		anIntArray3831 = new int[256];
		if (bs.length == 257) {
			for (int i = 0; i < anIntArray3831.length; i++)
				anIntArray3831[i] = bs[i] & 0xff;
			anInt3811 = bs[256] & 0xff;
		} else {
			int i = 0;
			for (int i_49_ = 0; i_49_ < 256; i_49_++)
				anIntArray3831[i_49_] = bs[i++] & 0xff;
			int[] is = new int[256];
			int[] is_50_ = new int[256];
			for (int i_51_ = 0; i_51_ < 256; i_51_++)
				is[i_51_] = bs[i++] & 0xff;
			for (int i_52_ = 0; i_52_ < 256; i_52_++)
				is_50_[i_52_] = bs[i++] & 0xff;
			byte[][] bs_53_ = new byte[256][];
			for (int i_54_ = 0; i_54_ < 256; i_54_++) {
				bs_53_[i_54_] = new byte[is[i_54_]];
				byte b = 0;
				for (int i_55_ = 0; i_55_ < bs_53_[i_54_].length; i_55_++) {
					b += bs[i++];
					bs_53_[i_54_][i_55_] = b;
				}
			}
			byte[][] bs_56_ = new byte[256][];
			for (int i_57_ = 0; i_57_ < 256; i_57_++) {
				bs_56_[i_57_] = new byte[is[i_57_]];
				byte b = 0;
				for (int i_58_ = 0; i_58_ < bs_56_[i_57_].length; i_58_++) {
					b += bs[i++];
					bs_56_[i_57_][i_58_] = b;
				}
			}
			aByteArray3813 = new byte[65536];
			for (int i_59_ = 0; i_59_ < 256; i_59_++) {
				if (i_59_ != 32 && i_59_ != 160) {
					for (int i_60_ = 0; i_60_ < 256; i_60_++) {
						if (i_60_ != 32 && i_60_ != 160) {
							aByteArray3813[(i_59_ << 8) + i_60_] = (byte) method653(bs_53_, bs_56_, is_50_, anIntArray3831, is, i_59_, i_60_);
						}
					}
				}
			}
			anInt3811 = is_50_[32] + is[32];
		}
	}
	
	final void method655(RSString class16, int i, int i_61_, int i_62_, int i_63_, int i_64_) {
		if (class16 != null) {
			method672(i_62_, i_63_);
			int[] is = new int[class16.length];
			int[] is_65_ = new int[class16.length];
			for (int i_66_ = 0; i_66_ < class16.length; i_66_++) {
				is[i_66_] = (int) (Math.sin(i_66_ / 5.0 + i_64_ / 5.0) * 5.0);
				is_65_[i_66_] = (int) (Math.sin(i_66_ / 3.0 + i_64_ / 5.0) * 5.0);
			}
			method651(class16, i - method649(class16) / 2, i_61_, is, is_65_);
		}
	}
	
	public final void method656(RSString class16, int i) {
		int i_67_ = 0;
		boolean bool = false;
		for (int i_68_ = 0; i_68_ < class16.length(); i_68_++) {
			int i_69_ = class16.charAt(i_68_);
			if (i_69_ == 60) {
				bool = true;
			} else if (i_69_ == 62) {
				bool = false;
			} else if (!bool && i_69_ == 32) {
				i_67_++;
			}
		}
		if (i_67_ > 0) {
			anInt3839 = (i - method649(class16) << 8) / i_67_;
		}
	}
	
	final void method657(RSString class16, int i, int i_70_, int i_71_, int i_72_, int i_73_) {
		if (class16 != null) {
			method672(i_71_, i_72_);
			int[] is = new int[class16.length];
			for (int i_74_ = 0; i_74_ < class16.length; i_74_++)
				is[i_74_] = (int) (Math.sin(i_74_ / 2.0 + i_73_ / 5.0) * 5.0);
			method651(class16, i - method649(class16) / 2, i_70_, null, is);
		}
	}
	
	public final void method658(int i, int i_75_, int i_76_) {
		anInt3815 = -1;
		anInt3827 = -1;
		anInt3829 = anInt3828 = i_75_;
		anInt3834 = anInt3835 = i;
		anInt3807 = anInt3851 = i_76_;
		anInt3839 = 0;
		anInt3817 = 0;
	}
	
	public final int method659(int i) {
		return anIntArray3831[i & 0xff];
	}
	
	abstract void method660(int i, int i_77_, int i_78_, int i_79_, int i_80_, int i_81_, int i_82_);
	
	final void method661(Class43[] class43s, int[] is) {
		if (is != null && is.length != class43s.length) {
			throw new IllegalArgumentException();
		}
		aClass43Array3822 = class43s;
		anIntArray3833 = is;
	}
	
	abstract void method662(int i, int i_83_, int i_84_, int i_85_, int i_86_, int i_87_);
	
	abstract void method663(int i, int i_88_, int i_89_, int i_90_, int i_91_, int i_92_);
	
	public final void method664(RSString class16, int i, int i_93_) {
		i_93_ -= anInt3811;
		int i_94_ = -1;
		int i_95_ = -1;
		for (int i_96_ = 0; i_96_ < class16.length; i_96_++) {
			int i_97_ = class16.bytes[i_96_] & 0xff;
			if (i_97_ == 60) {
				i_94_ = i_96_;
			} else {
				if (i_97_ == 62 && i_94_ != -1) {
					RSString class16_98_ = class16.substring(i_96_, i_94_ + 1);
					i_94_ = -1;
					if (class16_98_.equals(lesserThanTag)) {
						i_97_ = 60;
					} else if (class16_98_.equals(greaterThanTag)) {
						i_97_ = 62;
					} else if (class16_98_.equals(nonBreakingSpaceTag)) {
						i_97_ = 160;
					} else if (class16_98_.equals(softHyphenTag)) {
						i_97_ = 173;
					} else if (class16_98_.equals(multiplierTag)) {
						i_97_ = 215;
					} else if (class16_98_.equals(euroTag)) {
						i_97_ = 128;
					} else if (class16_98_.equals(copyRightTag)) {
						i_97_ = 169;
					} else if (class16_98_.equals(registrationTag)) {
						i_97_ = 174;
					} else {
						if (class16_98_.startsWith(imageTag)) {
							try {
								int i_99_ = class16_98_.substring(4).toInteger();
								Class43 class43 = aClass43Array3822[i_99_];
								int i_100_ = anIntArray3833 != null ? anIntArray3833[i_99_] : class43.anInt667;
								if (anInt3807 == 256) {
									class43.method1119(i, i_93_ + anInt3811 - i_100_);
								} else {
									class43.method1117(i, i_93_ + anInt3811 - i_100_, anInt3807);
								}
								i += class43.anInt668;
								i_95_ = -1;
							} catch (Exception exception) {
								/* empty */
							}
						} else {
							method675(class16_98_);
						}
						continue;
					}
				}
				if (i_94_ == -1) {
					if (aByteArray3813 != null && i_95_ != -1) {
						i += aByteArray3813[(i_95_ << 8) + i_97_];
					}
					int i_101_ = anIntArray3836[i_97_];
					int i_102_ = anIntArray3825[i_97_];
					if (i_97_ != 32) {
						if (anInt3807 == 256) {
							if (anInt3829 != -1) {
								method662(i_97_, i + anIntArray3847[i_97_] + 1, i_93_ + anIntArray3846[i_97_] + 1, i_101_, i_102_, anInt3829);
							}
							method663(i_97_, i + anIntArray3847[i_97_], i_93_ + anIntArray3846[i_97_], i_101_, i_102_, anInt3834);
						} else {
							if (anInt3829 != -1) {
								method673(i_97_, i + anIntArray3847[i_97_] + 1, i_93_ + anIntArray3846[i_97_] + 1, i_101_, i_102_, anInt3829, anInt3807);
							}
							method660(i_97_, i + anIntArray3847[i_97_], i_93_ + anIntArray3846[i_97_], i_101_, i_102_, anInt3834, anInt3807);
						}
					} else if (anInt3839 > 0) {
						anInt3817 += anInt3839;
						i += anInt3817 >> 8;
						anInt3817 &= 0xff;
					}
					int i_103_ = anIntArray3831[i_97_];
					if (anInt3815 != -1) {
						Rasterizer2D.draw_horizontal_line(i, i_93_ + (int) (anInt3811 * 0.7), i_103_, anInt3815);
					}
					if (anInt3827 != -1) {
						Rasterizer2D.draw_horizontal_line(i, i_93_ + anInt3811 + 1, i_103_, anInt3827);
					}
					i += i_103_;
					i_95_ = i_97_;
				}
			}
		}
	}
	
	final int method665(RSString class16, int i, int i_104_, int i_105_, int i_106_, int i_107_, int i_108_, int i_109_, int i_110_, int i_111_) {
		return method676(class16, i, i_104_, i_105_, i_106_, i_107_, i_108_, 256, i_109_, i_110_, i_111_);
	}
	
	final void method666(RSString class16, int i, int i_112_, int i_113_, int i_114_) {
		if (class16 != null) {
			method672(i_113_, i_114_);
			method664(class16, i - method649(class16) / 2, i_112_);
		}
	}
	
	final int method667(RSString class16, int i) {
		int i_115_ = method671(class16, new int[] { i }, aClass16Array3823);
		int i_116_ = 0;
		for (int i_117_ = 0; i_117_ < i_115_; i_117_++) {
			int i_118_ = method649(aClass16Array3823[i_117_]);
			if (i_118_ > i_116_) {
				i_116_ = i_118_;
			}
		}
		return i_116_;
	}
	
	final void method668(RSString class16, int i, int i_119_, int i_120_, int i_121_) {
		if (class16 != null) {
			method672(i_120_, i_121_);
			method664(class16, i - method649(class16), i_119_);
		}
	}
	
	final int method669(RSString class16, int i) {
		return method671(class16, new int[] { i }, aClass16Array3823);
	}
	
	final void method670(RSString class16, int i, int i_122_, int i_123_, int i_124_, int i_125_, int i_126_) {
		if (class16 != null) {
			method672(i_123_, i_124_);
			double d = 7.0 - i_126_ / 8.0;
			if (d < 0.0) {
				d = 0.0;
			}
			int[] is = new int[class16.length];
			for (int i_127_ = 0; i_127_ < class16.length; i_127_++)
				is[i_127_] = (int) (Math.sin(i_127_ / 1.5 + i_125_) * d);
			method651(class16, i - method649(class16) / 2, i_122_, null, is);
		}
	}
	
	public final int method671(RSString class16, int[] is, RSString[] class16s) {
		if (class16 == null) {
			return 0;
		}
		int i = 0;
		int i_128_ = 0;
		RSString class16_129_ = RSString.create(100, 0);
		int i_130_ = -1;
		int i_131_ = 0;
		int i_132_ = 0;
		int i_133_ = -1;
		int i_134_ = -1;
		int i_135_ = 0;
		int i_136_ = class16.length();
		for (int i_137_ = 0; i_137_ < i_136_; i_137_++) {
			int i_138_ = class16.charAt(i_137_);
			if (i_138_ == 60) {
				i_133_ = i_137_;
			} else {
				if (i_138_ == 62 && i_133_ != -1) {
					RSString class16_139_ = class16.substring(i_137_, i_133_ + 1);
					i_133_ = -1;
					class16_129_.append(60);
					class16_129_.append(class16_139_);
					class16_129_.append(62);
					if (class16_139_.equals(aClass16_3843)) {
						class16s[i_135_++] = class16_129_.substring(class16_129_.length(), i_128_);
						i_128_ = class16_129_.length();
						i = 0;
						i_130_ = -1;
						i_134_ = -1;
					} else if (class16_139_.equals(lesserThanTag)) {
						i += method659(60);
						if (aByteArray3813 != null && i_134_ != -1) {
							i += aByteArray3813[(i_134_ << 8) + 60];
						}
						i_134_ = 60;
					} else if (class16_139_.equals(greaterThanTag)) {
						i += method659(62);
						if (aByteArray3813 != null && i_134_ != -1) {
							i += aByteArray3813[(i_134_ << 8) + 62];
						}
						i_134_ = 62;
					} else if (class16_139_.equals(nonBreakingSpaceTag)) {
						i += method659(160);
						if (aByteArray3813 != null && i_134_ != -1) {
							i += aByteArray3813[(i_134_ << 8) + 160];
						}
						i_134_ = 160;
					} else if (class16_139_.equals(softHyphenTag)) {
						i += method659(173);
						if (aByteArray3813 != null && i_134_ != -1) {
							i += aByteArray3813[(i_134_ << 8) + 173];
						}
						i_134_ = 173;
					} else if (class16_139_.equals(multiplierTag)) {
						i += method659(215);
						if (aByteArray3813 != null && i_134_ != -1) {
							i += aByteArray3813[(i_134_ << 8) + 215];
						}
						i_134_ = 215;
					} else if (class16_139_.equals(euroTag)) {
						i += method659(128);
						if (aByteArray3813 != null && i_134_ != -1) {
							i += aByteArray3813[(i_134_ << 8) + 128];
						}
						i_134_ = 128;
					} else if (class16_139_.equals(copyRightTag)) {
						i += method659(169);
						if (aByteArray3813 != null && i_134_ != -1) {
							i += aByteArray3813[(i_134_ << 8) + 169];
						}
						i_134_ = 169;
					} else if (class16_139_.equals(registrationTag)) {
						i += method659(174);
						if (aByteArray3813 != null && i_134_ != -1) {
							i += aByteArray3813[(i_134_ << 8) + 174];
						}
						i_134_ = 174;
					} else if (class16_139_.startsWith(imageTag)) {
						try {
							int i_140_ = class16_139_.substring(4).toInteger();
							i += aClass43Array3822[i_140_].anInt668;
							i_134_ = -1;
						} catch (Exception exception) {
							/* empty */
						}
					}
					i_138_ = -1;
				}
				if (i_133_ == -1) {
					if (i_138_ != -1) {
						class16_129_.append(i_138_);
						i += method659(i_138_);
						if (aByteArray3813 != null && i_134_ != -1) {
							i += aByteArray3813[(i_134_ << 8) + i_138_];
						}
						i_134_ = i_138_;
					}
					if (i_138_ == 32) {
						i_130_ = class16_129_.length();
						i_131_ = i;
						i_132_ = 1;
					}
					if (is != null && i > is[i_135_ < is.length ? i_135_ : is.length - 1] && i_130_ >= 0) {
						class16s[i_135_++] = class16_129_.substring(i_130_ - i_132_, i_128_);
						i_128_ = i_130_;
						i_130_ = -1;
						i -= i_131_;
						i_134_ = -1;
					}
					if (i_138_ == 45) {
						i_130_ = class16_129_.length();
						i_131_ = i;
						i_132_ = 0;
					}
				}
			}
		}
		if (class16_129_.length() > i_128_) {
			class16s[i_135_++] = class16_129_.substring(class16_129_.length(), i_128_);
		}
		return i_135_;
	}
	
	public final void method672(int i, int i_141_) {
		anInt3815 = -1;
		anInt3827 = -1;
		anInt3829 = anInt3828 = i_141_;
		anInt3834 = anInt3835 = i;
		anInt3807 = anInt3851 = 256;
		anInt3839 = 0;
		anInt3817 = 0;
	}
	
	abstract void method673(int i, int i_142_, int i_143_, int i_144_, int i_145_, int i_146_, int i_147_);
	
	static final RSString method674(RSString class16) {
		int i = class16.length();
		int i_148_ = 0;
		for (int i_149_ = 0; i_149_ < i; i_149_++) {
			byte b = class16.bytes[i_149_];
			if (b == 60 || b == 62) {
				i_148_ += 3;
			}
		}
		RSString class16_150_ = new RSString();
		class16_150_.length = i + i_148_;
		class16_150_.bytes = new byte[class16_150_.length];
		int i_151_ = 0;
		for (int i_152_ = 0; i_152_ < i; i_152_++) {
			byte b = class16.bytes[i_152_];
			if (b == 60) {
				class16_150_.bytes[i_151_++] = (byte) 60;
				class16_150_.bytes[i_151_++] = (byte) 108;
				class16_150_.bytes[i_151_++] = (byte) 116;
				class16_150_.bytes[i_151_++] = (byte) 62;
			} else if (b == 62) {
				class16_150_.bytes[i_151_++] = (byte) 60;
				class16_150_.bytes[i_151_++] = (byte) 103;
				class16_150_.bytes[i_151_++] = (byte) 116;
				class16_150_.bytes[i_151_++] = (byte) 62;
			} else {
				class16_150_.bytes[i_151_++] = b;
			}
		}
		return class16_150_;
	}
	
	public final void method675(RSString class16) {
		do {
			try {
				if (class16.startsWith(aClass16_3820)) {
					anInt3834 = class16.substring(4).parseInt(16);
				} else if (class16.equals(aClass16_3837)) {
					anInt3834 = anInt3835;
				} else if (class16.startsWith(aClass16_3844)) {
					anInt3807 = class16.substring(6).toInteger();
				} else if (class16.equals(aClass16_3830)) {
					anInt3807 = anInt3851;
				} else if (class16.startsWith(aClass16_3816)) {
					anInt3815 = class16.substring(4).parseInt(16);
				} else if (class16.equals(aClass16_3808)) {
					anInt3815 = 8388608;
				} else if (class16.equals(aClass16_3826)) {
					anInt3815 = -1;
				} else if (class16.startsWith(aClass16_3841)) {
					anInt3827 = class16.substring(2).parseInt(16);
				} else if (class16.equals(aClass16_3850)) {
					anInt3827 = 0;
				} else if (class16.equals(aClass16_3832)) {
					anInt3827 = -1;
				} else if (class16.startsWith(aClass16_3810)) {
					anInt3829 = class16.substring(5).parseInt(16);
				} else if (class16.equals(aClass16_3849)) {
					anInt3829 = 0;
				} else if (class16.equals(aClass16_3845)) {
					anInt3829 = anInt3828;
				} else {
					if (!class16.equals(aClass16_3843)) {
						break;
					}
					method658(anInt3835, anInt3828, anInt3851);
				}
			} catch (Exception exception) {
				break;
			}
			break;
		} while (false);
	}
	
	public final int method676(RSString class16, int i, int i_153_, int i_154_, int i_155_, int i_156_, int i_157_, int i_158_, int i_159_, int i_160_, int i_161_) {
		if (class16 == null) {
			return 0;
		}
		method658(i_156_, i_157_, i_158_);
		if (i_161_ == 0) {
			i_161_ = anInt3811;
		}
		int[] is = { i_154_ };
		if (i_155_ < anInt3819 + anInt3809 + i_161_ && i_155_ < i_161_ + i_161_) {
			is = null;
		}
		int i_162_ = method671(class16, is, aClass16Array3823);
		if (i_160_ == 3 && i_162_ == 1) {
			i_160_ = 1;
		}
		int i_163_;
		if (i_160_ == 0) {
			i_163_ = i_153_ + anInt3819;
		} else if (i_160_ == 1) {
			i_163_ = i_153_ + anInt3819 + (i_155_ - anInt3819 - anInt3809 - (i_162_ - 1) * i_161_) / 2;
		} else if (i_160_ == 2) {
			i_163_ = i_153_ + i_155_ - anInt3809 - (i_162_ - 1) * i_161_;
		} else {
			int i_164_ = (i_155_ - anInt3819 - anInt3809 - (i_162_ - 1) * i_161_) / (i_162_ + 1);
			if (i_164_ < 0) {
				i_164_ = 0;
			}
			i_163_ = i_153_ + anInt3819 + i_164_;
			i_161_ += i_164_;
		}
		for (int i_165_ = 0; i_165_ < i_162_; i_165_++) {
			if (i_159_ == 0) {
				method664(aClass16Array3823[i_165_], i, i_163_);
			} else if (i_159_ == 1) {
				method664(aClass16Array3823[i_165_], i + (i_154_ - method649(aClass16Array3823[i_165_])) / 2, i_163_);
			} else if (i_159_ == 2) {
				method664(aClass16Array3823[i_165_], i + i_154_ - method649(aClass16Array3823[i_165_]), i_163_);
			} else if (i_165_ == i_162_ - 1) {
				method664(aClass16Array3823[i_165_], i, i_163_);
			} else {
				method656(aClass16Array3823[i_165_], i_154_);
				method664(aClass16Array3823[i_165_], i, i_163_);
				anInt3839 = 0;
			}
			i_163_ += i_161_;
		}
		return i_162_;
	}
	
	Class23_Sub13_Sub8(byte[] bs, int[] is, int[] is_166_, int[] is_167_, int[] is_168_) {
		anIntArray3847 = is;
		anIntArray3846 = is_166_;
		anIntArray3836 = is_167_;
		anIntArray3825 = is_168_;
		method654(bs);
		int i = 2147483647;
		int i_169_ = -2147483648;
		for (int i_170_ = 0; i_170_ < 256; i_170_++) {
			if (anIntArray3846[i_170_] < i && anIntArray3825[i_170_] != 0) {
				i = anIntArray3846[i_170_];
			}
			if (anIntArray3846[i_170_] + anIntArray3825[i_170_] > i_169_) {
				i_169_ = anIntArray3846[i_170_] + anIntArray3825[i_170_];
			}
		}
		anInt3819 = anInt3811 - i;
		anInt3809 = i_169_ - anInt3811;
	}
	
	Class23_Sub13_Sub8(byte[] bs) {
		method654(bs);
	}
	
	static {
		aClass16_3808 = RSString.createString("str");
		softHyphenTag = RSString.createString("shy");
		aClass16_3810 = RSString.createString("shad=");
		aClass16_3820 = RSString.createString("col=");
		imageTag = RSString.createString("img=");
		registrationTag = RSString.createString("reg");
		anInt3815 = -1;
		aClass16Array3823 = new RSString[100];
		anInt3827 = -1;
		aClass16_3837 = RSString.createString(")4col");
		greaterThanTag = RSString.createString("gt");
		anInt3829 = -1;
		anInt3835 = 0;
		anInt3828 = -1;
		lesserThanTag = RSString.createString("lt");
		aClass16_3832 = RSString.createString(")4u");
		anInt3807 = 256;
		copyRightTag = RSString.createString("copy");
		aClass16_3816 = RSString.createString("str=");
		anInt3839 = 0;
		anInt3834 = 0;
		aClass16_3843 = RSString.createString("br");
		anInt3817 = 0;
		aClass16_3830 = RSString.createString(")4trans");
		aClass16_3845 = RSString.createString(")4shad");
		aClass16_3841 = RSString.createString("u=");
		aClass16_3844 = RSString.createString("trans=");
		euroTag = RSString.createString("euro");
		aClass16_3826 = RSString.createString(")4str");
		nonBreakingSpaceTag = RSString.createString("nbsp");
		aClass16_3849 = RSString.createString("shad");
		aClass16_3850 = RSString.createString("u");
		anInt3851 = 256;
	}
}
