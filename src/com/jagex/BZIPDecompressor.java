package com.jagex;
/* BZIPDecompressor - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class BZIPDecompressor
{
	public static BZIPContext aClass74_1480 = new BZIPContext();
	
	public static final void method1390(BZIPContext class74) {
		byte b = class74.aByte1348;
		int i = class74.anInt1347;
		int i_0_ = class74.anInt1342;
		int i_1_ = class74.anInt1333;
		int[] is = Player.anIntArray4412;
		int i_2_ = class74.anInt1371;
		byte[] bs = class74.aByteArray1349;
		int i_3_ = class74.anInt1332;
		int i_4_ = class74.anInt1351;
		int i_5_ = i_4_;
		int i_6_ = class74.anInt1334 + 1;
	while_118_:
		for (;;) {
			if (i > 0) {
				for (;;) {
					if (i_4_ == 0) {
						break while_118_;
					}
					if (i == 1) {
						break;
					}
					bs[i_3_] = b;
					i--;
					i_3_++;
					i_4_--;
				}
				if (i_4_ == 0) {
					i = 1;
					break;
				}
				bs[i_3_] = b;
				i_3_++;
				i_4_--;
			}
			boolean bool = true;
			while (bool) {
				bool = false;
				if (i_0_ == i_6_) {
					i = 0;
					break while_118_;
				}
				b = (byte) i_1_;
				i_2_ = is[i_2_];
				int i_7_ = (byte) (i_2_ & 0xff);
				i_2_ >>= 8;
				i_0_++;
				if (i_7_ != i_1_) {
					i_1_ = i_7_;
					if (i_4_ == 0) {
						i = 1;
						break while_118_;
					}
					bs[i_3_] = b;
					i_3_++;
					i_4_--;
					bool = true;
				} else if (i_0_ == i_6_) {
					if (i_4_ == 0) {
						i = 1;
						break while_118_;
					}
					bs[i_3_] = b;
					i_3_++;
					i_4_--;
					bool = true;
				}
			}
			i = 2;
			i_2_ = is[i_2_];
			int i_8_ = (byte) (i_2_ & 0xff);
			i_2_ >>= 8;
			if (++i_0_ != i_6_) {
				if (i_8_ != i_1_) {
					i_1_ = i_8_;
				} else {
					i = 3;
					i_2_ = is[i_2_];
					i_8_ = (byte) (i_2_ & 0xff);
					i_2_ >>= 8;
					if (++i_0_ != i_6_) {
						if (i_8_ != i_1_) {
							i_1_ = i_8_;
						} else {
							i_2_ = is[i_2_];
							i_8_ = (byte) (i_2_ & 0xff);
							i_2_ >>= 8;
							i_0_++;
							i = (i_8_ & 0xff) + 4;
							i_2_ = is[i_2_];
							i_1_ = (byte) (i_2_ & 0xff);
							i_2_ >>= 8;
							i_0_++;
						}
					}
				}
			}
		}
		class74.anInt1352 += i_5_ - i_4_;
		class74.aByte1348 = b;
		class74.anInt1347 = i;
		class74.anInt1342 = i_0_;
		class74.anInt1333 = i_1_;
		Player.anIntArray4412 = is;
		class74.anInt1371 = i_2_;
		class74.aByteArray1349 = bs;
		class74.anInt1332 = i_3_;
		class74.anInt1351 = i_4_;
	}
	
	public static final byte method1391(BZIPContext class74) {
		return (byte) method1394(1, class74);
	}
	
	public static final byte method1392(BZIPContext class74) {
		return (byte) method1394(8, class74);
	}
	
	public static final void method1393(BZIPContext class74) {
		class74.anInt1368 = 0;
		for (int i = 0; i < 256; i++) {
			if (class74.aBooleanArray1363[i]) {
				class74.aByteArray1341[class74.anInt1368] = (byte) i;
				class74.anInt1368++;
			}
		}
	}
	
	public static final int method1394(int i, BZIPContext class74) {
		int i_10_;
		for (;;) {
			if (class74.anInt1354 >= i) {
				int i_11_ = class74.anInt1329 >> class74.anInt1354 - i & (1 << i) - 1;
				class74.anInt1354 -= i;
				i_10_ = i_11_;
				break;
			}
			class74.anInt1329 = class74.anInt1329 << 8 | class74.aByteArray1356[class74.anInt1337] & 0xff;
			class74.anInt1354 += 8;
			class74.anInt1337++;
			class74.anInt1350++;
		}
		return i_10_;
	}
	
	public static final int decompress(byte[] bs, int i, byte[] bs_12_, int i_13_, int i_14_) {
		synchronized (aClass74_1480) {
			aClass74_1480.aByteArray1356 = bs_12_;
			aClass74_1480.anInt1337 = i_14_;
			aClass74_1480.aByteArray1349 = bs;
			aClass74_1480.anInt1332 = 0;
			aClass74_1480.anInt1351 = i;
			aClass74_1480.anInt1354 = 0;
			aClass74_1480.anInt1329 = 0;
			aClass74_1480.anInt1350 = 0;
			aClass74_1480.anInt1352 = 0;
			method1397(aClass74_1480);
			i -= aClass74_1480.anInt1351;
			aClass74_1480.aByteArray1356 = null;
			aClass74_1480.aByteArray1349 = null;
			return i;
		}
	}
	
	public static void method1396() {
		aClass74_1480 = null;
	}
	
	@SuppressWarnings("unused")
	public static final void method1397(BZIPContext class74) {
		boolean bool = false;
		boolean bool_15_ = false;
		boolean bool_16_ = false;
		boolean bool_17_ = false;
		boolean bool_18_ = false;
		boolean bool_19_ = false;
		boolean bool_20_ = false;
		boolean bool_21_ = false;
		boolean bool_22_ = false;
		boolean bool_23_ = false;
		boolean bool_24_ = false;
		boolean bool_25_ = false;
		boolean bool_26_ = false;
		boolean bool_27_ = false;
		boolean bool_28_ = false;
		boolean bool_29_ = false;
		boolean bool_30_ = false;
		boolean bool_31_ = false;
		int i = 0;
		int[] is = null;
		int[] is_32_ = null;
		int[] is_33_ = null;
		class74.anInt1365 = 1;
		if (Player.anIntArray4412 == null) {
			Player.anIntArray4412 = new int[class74.anInt1365 * 100000];
		}
		boolean bool_34_ = true;
		while (bool_34_) {
			byte b = method1392(class74);
			if (b == 23) {
				break;
			}
			b = method1392(class74);
			b = method1392(class74);
			b = method1392(class74);
			b = method1392(class74);
			b = method1392(class74);
			b = method1392(class74);
			b = method1392(class74);
			b = method1392(class74);
			b = method1392(class74);
			b = method1391(class74);
			class74.anInt1338 = 0;
			int i_35_ = method1392(class74);
			class74.anInt1338 = class74.anInt1338 << 8 | i_35_ & 0xff;
			i_35_ = method1392(class74);
			class74.anInt1338 = class74.anInt1338 << 8 | i_35_ & 0xff;
			i_35_ = method1392(class74);
			class74.anInt1338 = class74.anInt1338 << 8 | i_35_ & 0xff;
			for (int i_36_ = 0; i_36_ < 16; i_36_++) {
				b = method1391(class74);
				if (b == 1) {
					class74.aBooleanArray1362[i_36_] = true;
				} else {
					class74.aBooleanArray1362[i_36_] = false;
				}
			}
			for (int i_37_ = 0; i_37_ < 256; i_37_++)
				class74.aBooleanArray1363[i_37_] = false;
			for (int i_38_ = 0; i_38_ < 16; i_38_++) {
				if (class74.aBooleanArray1362[i_38_]) {
					for (int i_39_ = 0; i_39_ < 16; i_39_++) {
						b = method1391(class74);
						if (b == 1) {
							class74.aBooleanArray1363[i_38_ * 16 + i_39_] = true;
						}
					}
				}
			}
			method1393(class74);
			int i_40_ = class74.anInt1368 + 2;
			int i_41_ = method1394(3, class74);
			int i_42_ = method1394(15, class74);
			for (int i_43_ = 0; i_43_ < i_42_; i_43_++) {
				int i_44_ = 0;
				for (;;) {
					b = method1391(class74);
					if (b == 0) {
						break;
					}
					i_44_++;
				}
				class74.aByteArray1370[i_43_] = (byte) i_44_;
			}
			byte[] bs = new byte[6];
			for (byte b_45_ = 0; b_45_ < i_41_; b_45_++)
				bs[b_45_] = b_45_;
			for (int i_46_ = 0; i_46_ < i_42_; i_46_++) {
				byte b_47_ = class74.aByteArray1370[i_46_];
				byte b_48_ = bs[b_47_];
				for (/**/; b_47_ > 0; b_47_--)
					bs[b_47_] = bs[b_47_ - 1];
				bs[0] = b_48_;
				class74.aByteArray1367[i_46_] = b_48_;
			}
			for (int i_49_ = 0; i_49_ < i_41_; i_49_++) {
				int i_50_ = method1394(5, class74);
				for (int i_51_ = 0; i_51_ < i_40_; i_51_++) {
					for (;;) {
						b = method1391(class74);
						if (b == 0) {
							break;
						}
						b = method1391(class74);
						if (b == 0) {
							i_50_++;
						} else {
							i_50_--;
						}
					}
					class74.aByteArrayArray1345[i_49_][i_51_] = (byte) i_50_;
				}
			}
			for (int i_52_ = 0; i_52_ < i_41_; i_52_++) {
				int i_53_ = 32;
				byte b_54_ = 0;
				for (int i_55_ = 0; i_55_ < i_40_; i_55_++) {
					if (class74.aByteArrayArray1345[i_52_][i_55_] > b_54_) {
						b_54_ = class74.aByteArrayArray1345[i_52_][i_55_];
					}
					if (class74.aByteArrayArray1345[i_52_][i_55_] < i_53_) {
						i_53_ = class74.aByteArrayArray1345[i_52_][i_55_];
					}
				}
				method1398(class74.anIntArrayArray1335[i_52_], class74.anIntArrayArray1344[i_52_], class74.anIntArrayArray1330[i_52_], class74.aByteArrayArray1345[i_52_], i_53_, b_54_, i_40_);
				class74.anIntArray1336[i_52_] = i_53_;
			}
			int i_56_ = class74.anInt1368 + 1;
			int i_57_ = -1;
			int i_58_ = 0;
			for (int i_59_ = 0; i_59_ <= 255; i_59_++)
				class74.anIntArray1366[i_59_] = 0;
			int i_60_ = 4095;
			for (int i_61_ = 15; i_61_ >= 0; i_61_--) {
				for (int i_62_ = 15; i_62_ >= 0; i_62_--) {
					class74.aByteArray1369[i_60_] = (byte) (i_61_ * 16 + i_62_);
					i_60_--;
				}
				class74.anIntArray1357[i_61_] = i_60_ + 1;
			}
			int i_63_ = 0;
			if (i_58_ == 0) {
				i_57_++;
				i_58_ = 50;
				byte b_64_ = class74.aByteArray1367[i_57_];
				i = class74.anIntArray1336[b_64_];
				is = class74.anIntArrayArray1335[b_64_];
				is_33_ = class74.anIntArrayArray1330[b_64_];
				is_32_ = class74.anIntArrayArray1344[b_64_];
			}
			i_58_--;
			int i_65_ = i;
			int i_66_;
			int i_67_;
			for (i_67_ = method1394(i_65_, class74); i_67_ > is[i_65_]; i_67_ = i_67_ << 1 | i_66_) {
				i_65_++;
				i_66_ = method1391(class74);
			}
			int i_68_ = is_33_[i_67_ - is_32_[i_65_]];
			while (i_68_ != i_56_) {
				if (i_68_ == 0 || i_68_ == 1) {
					int i_69_ = -1;
					int i_70_ = 1;
					do {
						if (i_68_ == 0) {
							i_69_ += i_70_;
						} else if (i_68_ == 1) {
							i_69_ += 2 * i_70_;
						}
						i_70_ *= 2;
						if (i_58_ == 0) {
							i_57_++;
							i_58_ = 50;
							byte b_71_ = class74.aByteArray1367[i_57_];
							i = class74.anIntArray1336[b_71_];
							is = class74.anIntArrayArray1335[b_71_];
							is_33_ = class74.anIntArrayArray1330[b_71_];
							is_32_ = class74.anIntArrayArray1344[b_71_];
						}
						i_58_--;
						i_65_ = i;
						for (i_67_ = method1394(i_65_, class74); i_67_ > is[i_65_]; i_67_ = i_67_ << 1 | i_66_) {
							i_65_++;
							i_66_ = method1391(class74);
						}
						i_68_ = is_33_[i_67_ - is_32_[i_65_]];
					} while (i_68_ == 0 || i_68_ == 1);
					i_69_++;
					i_35_ = class74.aByteArray1341[class74.aByteArray1369[class74.anIntArray1357[0]] & 0xff];
					class74.anIntArray1366[i_35_ & 0xff] += i_69_;
					for (/**/; i_69_ > 0; i_69_--) {
						Player.anIntArray4412[i_63_] = i_35_ & 0xff;
						i_63_++;
					}
				} else {
					int i_72_ = i_68_ - 1;
					if (i_72_ < 16) {
						int i_73_ = class74.anIntArray1357[0];
						b = class74.aByteArray1369[i_73_ + i_72_];
						for (/**/; i_72_ > 3; i_72_ -= 4) {
							int i_74_ = i_73_ + i_72_;
							class74.aByteArray1369[i_74_] = class74.aByteArray1369[i_74_ - 1];
							class74.aByteArray1369[i_74_ - 1] = class74.aByteArray1369[i_74_ - 2];
							class74.aByteArray1369[i_74_ - 2] = class74.aByteArray1369[i_74_ - 3];
							class74.aByteArray1369[i_74_ - 3] = class74.aByteArray1369[i_74_ - 4];
						}
						for (/**/; i_72_ > 0; i_72_--)
							class74.aByteArray1369[i_73_ + i_72_] = class74.aByteArray1369[i_73_ + i_72_ - 1];
						class74.aByteArray1369[i_73_] = b;
					} else {
						int i_75_ = i_72_ / 16;
						int i_76_ = i_72_ % 16;
						int i_77_ = class74.anIntArray1357[i_75_] + i_76_;
						b = class74.aByteArray1369[i_77_];
						for (/**/; i_77_ > class74.anIntArray1357[i_75_]; i_77_--)
							class74.aByteArray1369[i_77_] = class74.aByteArray1369[i_77_ - 1];
						class74.anIntArray1357[i_75_]++;
						for (/**/; i_75_ > 0; i_75_--) {
							class74.anIntArray1357[i_75_]--;
							class74.aByteArray1369[class74.anIntArray1357[i_75_]] = class74.aByteArray1369[class74.anIntArray1357[i_75_ - 1] + 16 - 1];
						}
						class74.anIntArray1357[0]--;
						class74.aByteArray1369[class74.anIntArray1357[0]] = b;
						if (class74.anIntArray1357[0] == 0) {
							int i_78_ = 4095;
							for (int i_79_ = 15; i_79_ >= 0; i_79_--) {
								for (int i_80_ = 15; i_80_ >= 0; i_80_--) {
									class74.aByteArray1369[i_78_] = class74.aByteArray1369[class74.anIntArray1357[i_79_] + i_80_];
									i_78_--;
								}
								class74.anIntArray1357[i_79_] = i_78_ + 1;
							}
						}
					}
					class74.anIntArray1366[class74.aByteArray1341[b & 0xff] & 0xff]++;
					Player.anIntArray4412[i_63_] = class74.aByteArray1341[b & 0xff] & 0xff;
					i_63_++;
					if (i_58_ == 0) {
						i_57_++;
						i_58_ = 50;
						byte b_81_ = class74.aByteArray1367[i_57_];
						i = class74.anIntArray1336[b_81_];
						is = class74.anIntArrayArray1335[b_81_];
						is_33_ = class74.anIntArrayArray1330[b_81_];
						is_32_ = class74.anIntArrayArray1344[b_81_];
					}
					i_58_--;
					i_65_ = i;
					for (i_67_ = method1394(i_65_, class74); i_67_ > is[i_65_]; i_67_ = i_67_ << 1 | i_66_) {
						i_65_++;
						i_66_ = method1391(class74);
					}
					i_68_ = is_33_[i_67_ - is_32_[i_65_]];
				}
			}
			class74.anInt1347 = 0;
			class74.aByte1348 = (byte) 0;
			class74.anIntArray1359[0] = 0;
			for (int i_82_ = 1; i_82_ <= 256; i_82_++)
				class74.anIntArray1359[i_82_] = class74.anIntArray1366[i_82_ - 1];
			for (int i_83_ = 1; i_83_ <= 256; i_83_++)
				class74.anIntArray1359[i_83_] += class74.anIntArray1359[i_83_ - 1];
			for (int i_84_ = 0; i_84_ < i_63_; i_84_++) {
				i_35_ = (byte) (Player.anIntArray4412[i_84_] & 0xff);
				Player.anIntArray4412[class74.anIntArray1359[i_35_ & 0xff]] |= i_84_ << 8;
				class74.anIntArray1359[i_35_ & 0xff]++;
			}
			class74.anInt1371 = Player.anIntArray4412[class74.anInt1338] >> 8;
			class74.anInt1342 = 0;
			class74.anInt1371 = Player.anIntArray4412[class74.anInt1371];
			class74.anInt1333 = (byte) (class74.anInt1371 & 0xff);
			class74.anInt1371 >>= 8;
			class74.anInt1342++;
			class74.anInt1334 = i_63_;
			method1390(class74);
			if (class74.anInt1342 == class74.anInt1334 + 1 && class74.anInt1347 == 0) {
				bool_34_ = true;
			} else {
				bool_34_ = false;
			}
		}
	}
	
	public static final void method1398(int[] is, int[] is_85_, int[] is_86_, byte[] bs, int i, int i_87_, int i_88_) {
		int i_89_ = 0;
		for (int i_90_ = i; i_90_ <= i_87_; i_90_++) {
			for (int i_91_ = 0; i_91_ < i_88_; i_91_++) {
				if (bs[i_91_] == i_90_) {
					is_86_[i_89_] = i_91_;
					i_89_++;
				}
			}
		}
		for (int i_92_ = 0; i_92_ < 23; i_92_++)
			is_85_[i_92_] = 0;
		for (int i_93_ = 0; i_93_ < i_88_; i_93_++)
			is_85_[bs[i_93_] + 1]++;
		for (int i_94_ = 1; i_94_ < 23; i_94_++)
			is_85_[i_94_] += is_85_[i_94_ - 1];
		for (int i_95_ = 0; i_95_ < 23; i_95_++)
			is[i_95_] = 0;
		int i_96_ = 0;
		for (int i_97_ = i; i_97_ <= i_87_; i_97_++) {
			i_96_ += is_85_[i_97_ + 1] - is_85_[i_97_];
			is[i_97_] = i_96_ - 1;
			i_96_ <<= 1;
		}
		for (int i_98_ = i + 1; i_98_ <= i_87_; i_98_++)
			is_85_[i_98_] = (is[i_98_ - 1] + 1 << 1) - is_85_[i_98_];
	}
}
