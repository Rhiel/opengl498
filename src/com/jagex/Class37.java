package com.jagex;
/* Class37 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class37
{
	public int anInt574 = Class23_Sub3.method250(16);
	public int anInt575;
	public int[] anIntArray576;
	public int anInt577 = Class23_Sub3.method250(24);
	public int anInt578;
	public int anInt579;
	public int anInt580;
	
	Class37() {
		anInt575 = Class23_Sub3.method250(24);
		anInt580 = Class23_Sub3.method250(24) + 1;
		anInt578 = Class23_Sub3.method250(6) + 1;
		anInt579 = Class23_Sub3.method250(8);
		int[] is = new int[anInt578];
		for (int i = 0; i < anInt578; i++) {
			int i_0_ = 0;
			int i_1_ = Class23_Sub3.method250(3);
			boolean bool = Class23_Sub3.method252() != 0;
			if (bool) {
				i_0_ = Class23_Sub3.method250(5);
			}
			is[i] = i_0_ << 3 | i_1_;
		}
		anIntArray576 = new int[anInt578 * 8];
		for (int i = 0; i < anInt578 * 8; i++)
			anIntArray576[i] = (is[i >> 3] & 1 << (i & 0x7)) != 0 ? Class23_Sub3.method250(8) : -1;
	}
	
	final void method991(float[] fs, int i, boolean bool) {
		for (int i_2_ = 0; i_2_ < i; i_2_++)
			fs[i_2_] = 0.0F;
		if (!bool) {
			int i_3_ = Class23_Sub3.aClass77Array2085[anInt579].anInt1404;
			int i_4_ = anInt575 - anInt577;
			int i_5_ = i_4_ / anInt580;
			int[] is = new int[i_5_];
			for (int i_6_ = 0; i_6_ < 8; i_6_++) {
				int i_7_ = 0;
				while (i_7_ < i_5_) {
					if (i_6_ == 0) {
						int i_8_ = Class23_Sub3.aClass77Array2085[anInt579].method1338();
						for (int i_9_ = i_3_ - 1; i_9_ >= 0; i_9_--) {
							if (i_7_ + i_9_ < i_5_) {
								is[i_7_ + i_9_] = i_8_ % anInt578;
							}
							i_8_ /= anInt578;
						}
					}
					for (int i_10_ = 0; i_10_ < i_3_; i_10_++) {
						int i_11_ = is[i_7_];
						int i_12_ = anIntArray576[i_11_ * 8 + i_6_];
						if (i_12_ >= 0) {
							int i_13_ = anInt577 + i_7_ * anInt580;
							Class77 class77 = Class23_Sub3.aClass77Array2085[i_12_];
							if (anInt574 == 0) {
								int i_14_ = anInt580 / class77.anInt1404;
								for (int i_15_ = 0; i_15_ < i_14_; i_15_++) {
									float[] fs_16_ = class77.method1340();
									for (int i_17_ = 0; i_17_ < class77.anInt1404; i_17_++)
										fs[i_13_ + i_15_ + i_17_ * i_14_] += fs_16_[i_17_];
								}
							} else {
								int i_18_ = 0;
								while (i_18_ < anInt580) {
									float[] fs_19_ = class77.method1340();
									for (int i_20_ = 0; i_20_ < class77.anInt1404; i_20_++) {
										fs[i_13_ + i_18_] += fs_19_[i_20_];
										i_18_++;
									}
								}
							}
						}
						if (++i_7_ >= i_5_) {
							break;
						}
					}
				}
			}
		}
	}
}
