package com.jagex;
/* ISAACCipher - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class ISAACCipher
{
	static RSString aClass16_1223;
	public int[] keyset;
	public int anInt1226;
	public int anInt1232;
	public int[] cryptSet;
	static RSString aClass16_1235 = RSString.createString("Fallen lassen");
	public int keyArrayIndex;
	public int anInt1241;
	
	public static void method1260(int i) {
		aClass16_1223 = null;
		aClass16_1235 = null;
		if (i != -31834) {
			method1260(-72);
		}
	}
	
	final int getNextValue(int i) {
		if (keyArrayIndex-- == 0) {
			isaac(false);
			keyArrayIndex = 255;
		}
		if (i != 256) {
			init((byte) 70);
		}
		return 0;//results[anInt1236];
	}
	
	public final void isaac(boolean bool) {
		anInt1226 += ++anInt1241;
		if (bool == false) {
			for (int i = 0; i < 256; i++) {
				int i_0_ = cryptSet[i];
				if ((i & 0x2 ^ 0xffffffff) != -1) {
					if ((0x1 & i ^ 0xffffffff) == -1) {
						anInt1232 ^= anInt1232 << 2;
					} else {
						anInt1232 ^= anInt1232 >>> 16;
					}
				} else if ((0x1 & i) != 0) {
					anInt1232 ^= anInt1232 >>> 6;
				} else {
					anInt1232 ^= anInt1232 << 13;
				}
				anInt1232 += cryptSet[i + 128 & 0xff];
				int i_1_;
				cryptSet[i] = i_1_ = cryptSet[MathUtils.bitAnd(1020, i_0_) >> 2] - -anInt1232 + anInt1226;
				keyset[i] = anInt1226 = cryptSet[MathUtils.bitAnd(1020, i_1_ >> 8) >> 2] - -i_0_;
			}
		}
	}
	
	public final void init(byte b) {
		if (b == -3) {
			int i_2_;
			int i_3_;
			int i_4_;
			int i_5_;
			int i_6_;
			int i_7_;
			int i_8_; 
			int i = i_2_ = i_3_ = i_4_ = i_5_ = i_6_ = i_7_ = i_8_ = -1640531527;
			for (int i_9_ = 0; i_9_ < 4; i_9_++) {
				i ^= i_2_ << 11;
				i_4_ += i;
				i_2_ += i_3_;
				i_2_ ^= i_3_ >>> 2;
				i_5_ += i_2_;
				i_3_ += i_4_;
				i_3_ ^= i_4_ << 8;
				i_4_ += i_5_;
				i_4_ ^= i_5_ >>> 16;
				i_7_ += i_4_;
				i_6_ += i_3_;
				i_5_ += i_6_;
				i_5_ ^= i_6_ << 10;
				i_6_ += i_7_;
				i_6_ ^= i_7_ >>> 4;
				i += i_6_;
				i_8_ += i_5_;
				i_7_ += i_8_;
				i_7_ ^= i_8_ << 8;
				i_8_ += i;
				i_2_ += i_7_;
				i_8_ ^= i >>> 9;
				i += i_2_;
				i_3_ += i_8_;
			}
			for (int i_10_ = 0; i_10_ < 256; i_10_ += 8) {
				i_5_ += keyset[4 + i_10_];
				i_7_ += keyset[i_10_ - -6];
				i += keyset[i_10_];
				i_2_ += keyset[1 + i_10_];
				i_3_ += keyset[2 + i_10_];
				i_4_ += keyset[i_10_ + 3];
				i ^= i_2_ << 11;
				i_6_ += keyset[5 + i_10_];
				i_8_ += keyset[i_10_ + 7];
				i_2_ += i_3_;
				i_4_ += i;
				i_2_ ^= i_3_ >>> 2;
				i_3_ += i_4_;
				i_3_ ^= i_4_ << 8;
				i_6_ += i_3_;
				i_5_ += i_2_;
				i_4_ += i_5_;
				i_4_ ^= i_5_ >>> 16;
				i_7_ += i_4_;
				i_5_ += i_6_;
				i_5_ ^= i_6_ << 10;
				i_6_ += i_7_;
				i_8_ += i_5_;
				i_6_ ^= i_7_ >>> 4;
				i_7_ += i_8_;
				i_7_ ^= i_8_ << 8;
				i_2_ += i_7_;
				i += i_6_;
				i_8_ += i;
				i_8_ ^= i >>> 9;
				i_3_ += i_8_;
				i += i_2_;
				cryptSet[i_10_] = i;
				cryptSet[1 + i_10_] = i_2_;
				cryptSet[2 + i_10_] = i_3_;
				cryptSet[i_10_ - -3] = i_4_;
				cryptSet[4 + i_10_] = i_5_;
				cryptSet[i_10_ + 5] = i_6_;
				cryptSet[6 + i_10_] = i_7_;
				cryptSet[7 + i_10_] = i_8_;
			}
			for (int i_11_ = 0; i_11_ < 256; i_11_ += 8) {
				i_5_ += cryptSet[4 + i_11_];
				i_4_ += cryptSet[3 + i_11_];
				i_8_ += cryptSet[i_11_ - -7];
				i_7_ += cryptSet[6 + i_11_];
				i_3_ += cryptSet[2 + i_11_];
				i_2_ += cryptSet[i_11_ + 1];
				i += cryptSet[i_11_];
				i ^= i_2_ << 11;
				i_2_ += i_3_;
				i_6_ += cryptSet[5 + i_11_];
				i_2_ ^= i_3_ >>> 2;
				i_5_ += i_2_;
				i_4_ += i;
				i_3_ += i_4_;
				i_3_ ^= i_4_ << 8;
				i_4_ += i_5_;
				i_4_ ^= i_5_ >>> 16;
				i_7_ += i_4_;
				i_6_ += i_3_;
				i_5_ += i_6_;
				i_5_ ^= i_6_ << 10;
				i_6_ += i_7_;
				i_8_ += i_5_;
				i_6_ ^= i_7_ >>> 4;
				i_7_ += i_8_;
				i_7_ ^= i_8_ << 8;
				i += i_6_;
				i_2_ += i_7_;
				i_8_ += i;
				i_8_ ^= i >>> 9;
				i_3_ += i_8_;
				i += i_2_;
				cryptSet[i_11_] = i;
				cryptSet[1 + i_11_] = i_2_;
				cryptSet[2 + i_11_] = i_3_;
				cryptSet[3 + i_11_] = i_4_;
				cryptSet[i_11_ + 4] = i_5_;
				cryptSet[i_11_ - -5] = i_6_;
				cryptSet[i_11_ - -6] = i_7_;
				cryptSet[7 + i_11_] = i_8_;
			}
			isaac(false);
			keyArrayIndex = 256;
		}
	}
	
	static final void method1264(int i, int i_12_, int i_13_, int i_14_, byte b) {
		if ((i_13_ ^ 0xffffffff) >= (i_12_ ^ 0xffffffff)) {
			for (int i_15_ = i_13_; (i_15_ ^ 0xffffffff) > (i_12_ ^ 0xffffffff); i_15_++) {
				Class4.anIntArrayArray98[i_15_][i_14_] = i;
			}
		} else {
			for (int i_16_ = i_12_; (i_13_ ^ 0xffffffff) < (i_16_ ^ 0xffffffff); i_16_++) {
				Class4.anIntArrayArray98[i_16_][i_14_] = i;
			}
		}
	}
	
	@SuppressWarnings("unused")
	public ISAACCipher() {
		/* empty */
	}
	
	ISAACCipher(int[] seed) {
		keyset = new int[256];
		cryptSet = new int[256];
		for (int i = 0; i < seed.length; i++) {
			keyset[i] = seed[i];
		}
		init((byte) -3);
	}
	
	static {
		aClass16_1223 = RSString.createString("Keine Antwort vom Anmelde)2Server)3");
	}
}
