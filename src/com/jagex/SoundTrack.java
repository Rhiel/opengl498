package com.jagex;
/* SoundTrack - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.util.Random;

public class SoundTrack
{
	public Class41 aClass41_1574;
	public Class41 aClass41_1575;
	public int[] anIntArray1576 = new int[5];
	public Class41 aClass41_1577;
	public static int[] anIntArray1578;
	public Class41 aClass41_1579;
	public Class41 aClass41_1580;
	public SoundEngine aSoundEngine_1581;
	public int[] anIntArray1582;
	public int anInt1583;
	public Class41 aClass41_1584;
	public static int[] anIntArray1585 = new int[32768];
	public Class41 aClass41_1586;
	public int[] anIntArray1587 = new int[5];
	public int anInt1588;
	public Class41 aClass41_1589;
	public int anInt1590;
	public int anInt1591;
	public Class41 aClass41_1592;
	public static int[] anIntArray1593;
	public static int[] anIntArray1594;
	public static int[] anIntArray1595;
	public static int[] anIntArray1596;
	public static int[] anIntArray1597;
	public static int[] anIntArray1598;
	
	final void decode(Packet class23_sub5) {
		aClass41_1589 = new Class41();
		aClass41_1589.method1111(class23_sub5);
		aClass41_1580 = new Class41();
		aClass41_1580.method1111(class23_sub5);
		int i = class23_sub5.g1();
		if (i != 0) {
			class23_sub5.index--;
			aClass41_1586 = new Class41();
			aClass41_1586.method1111(class23_sub5);
			aClass41_1577 = new Class41();
			aClass41_1577.method1111(class23_sub5);
		}
		i = class23_sub5.g1();
		if (i != 0) {
			class23_sub5.index--;
			aClass41_1592 = new Class41();
			aClass41_1592.method1111(class23_sub5);
			aClass41_1575 = new Class41();
			aClass41_1575.method1111(class23_sub5);
		}
		i = class23_sub5.g1();
		if (i != 0) {
			class23_sub5.index--;
			aClass41_1574 = new Class41();
			aClass41_1574.method1111(class23_sub5);
			aClass41_1579 = new Class41();
			aClass41_1579.method1111(class23_sub5);
		}
		for (int i_0_ = 0; i_0_ < 10; i_0_++) {
			int i_1_ = class23_sub5.getSmart0();
			if (i_1_ == 0) {
				break;
			}
			anIntArray1576[i_0_] = i_1_;
			anIntArray1582[i_0_] = class23_sub5.gSmart1or2s();
			anIntArray1587[i_0_] = class23_sub5.getSmart0();
		}
		anInt1588 = class23_sub5.getSmart0();
		anInt1590 = class23_sub5.getSmart0();
		anInt1591 = class23_sub5.g2();
		anInt1583 = class23_sub5.g2();
		aSoundEngine_1581 = new SoundEngine();
		aClass41_1584 = new Class41();
		aSoundEngine_1581.method117(class23_sub5, aClass41_1584);
	}
	
	public final int method1462(int i, int i_2_, int i_3_) {
		if (i_3_ == 1) {
			if ((i & 0x7fff) < 16384) {
				return i_2_;
			}
			return -i_2_;
		}
		if (i_3_ == 2) {
			return anIntArray1593[i & 0x7fff] * i_2_ >> 14;
		}
		if (i_3_ == 3) {
			return ((i & 0x7fff) * i_2_ >> 14) - i_2_;
		}
		if (i_3_ == 4) {
			return anIntArray1585[i / 2607 & 0x7fff] * i_2_;
		}
		return 0;
	}
	
	final int[] method1463(int i, int i_4_) {
		ArrayUtils.clear_array(anIntArray1578, 0, i);
		if (i_4_ < 10) {
			return anIntArray1578;
		}
		double d = i / (i_4_ + 0.0);
		aClass41_1589.method1110();
		aClass41_1580.method1110();
		int i_5_ = 0;
		int i_6_ = 0;
		int i_7_ = 0;
		if (aClass41_1586 != null) {
			aClass41_1586.method1110();
			aClass41_1577.method1110();
			i_5_ = (int) ((aClass41_1586.anInt633 - aClass41_1586.anInt636) * 32.768 / d);
			i_6_ = (int) (aClass41_1586.anInt636 * 32.768 / d);
		}
		int i_8_ = 0;
		int i_9_ = 0;
		int i_10_ = 0;
		if (aClass41_1592 != null) {
			aClass41_1592.method1110();
			aClass41_1575.method1110();
			i_8_ = (int) ((aClass41_1592.anInt633 - aClass41_1592.anInt636) * 32.768 / d);
			i_9_ = (int) (aClass41_1592.anInt636 * 32.768 / d);
		}
		for (int i_11_ = 0; i_11_ < 5; i_11_++) {
			if (anIntArray1576[i_11_] != 0) {
				anIntArray1595[i_11_] = 0;
				anIntArray1594[i_11_] = (int) (anIntArray1587[i_11_] * d);
				anIntArray1597[i_11_] = (anIntArray1576[i_11_] << 14) / 100;
				anIntArray1598[i_11_] = (int) ((aClass41_1589.anInt633 - aClass41_1589.anInt636) * 32.768 * Math.pow(1.0057929410678534, anIntArray1582[i_11_]) / d);
				anIntArray1596[i_11_] = (int) (aClass41_1589.anInt636 * 32.768 / d);
			}
		}
		for (int i_12_ = 0; i_12_ < i; i_12_++) {
			int i_13_ = aClass41_1589.method1112(i);
			int i_14_ = aClass41_1580.method1112(i);
			if (aClass41_1586 != null) {
				int i_15_ = aClass41_1586.method1112(i);
				int i_16_ = aClass41_1577.method1112(i);
				i_13_ += method1462(i_7_, i_16_, aClass41_1586.anInt638) >> 1;
				i_7_ += (i_15_ * i_5_ >> 16) + i_6_;
			}
			if (aClass41_1592 != null) {
				int i_17_ = aClass41_1592.method1112(i);
				int i_18_ = aClass41_1575.method1112(i);
				i_14_ = i_14_ * ((method1462(i_10_, i_18_, aClass41_1592.anInt638) >> 1) + 32768) >> 15;
				i_10_ += (i_17_ * i_8_ >> 16) + i_9_;
			}
			for (int i_19_ = 0; i_19_ < 5; i_19_++) {
				if (anIntArray1576[i_19_] != 0) {
					int i_20_ = i_12_ + anIntArray1594[i_19_];
					if (i_20_ < i) {
						anIntArray1578[i_20_] += method1462(anIntArray1595[i_19_], i_14_ * anIntArray1597[i_19_] >> 15, aClass41_1589.anInt638);
						anIntArray1595[i_19_] += (i_13_ * anIntArray1598[i_19_] >> 16) + anIntArray1596[i_19_];
					}
				}
			}
		}
		if (aClass41_1574 != null) {
			aClass41_1574.method1110();
			aClass41_1579.method1110();
			int i_21_ = 0;
			@SuppressWarnings("unused")
			boolean bool = false;
			boolean bool_22_ = true;
			for (int i_23_ = 0; i_23_ < i; i_23_++) {
				int i_24_ = aClass41_1574.method1112(i);
				int i_25_ = aClass41_1579.method1112(i);
				int i_26_;
				if (bool_22_) {
					i_26_ = aClass41_1574.anInt636 + ((aClass41_1574.anInt633 - aClass41_1574.anInt636) * i_24_ >> 8);
				} else {
					i_26_ = aClass41_1574.anInt636 + ((aClass41_1574.anInt633 - aClass41_1574.anInt636) * i_25_ >> 8);
				}
				i_21_ += 256;
				if (i_21_ >= i_26_) {
					i_21_ = 0;
					bool_22_ = !bool_22_;
				}
				if (bool_22_) {
					anIntArray1578[i_23_] = 0;
				}
			}
		}
		if (anInt1588 > 0 && anInt1590 > 0) {
			int i_27_ = (int) (anInt1588 * d);
			for (int i_28_ = i_27_; i_28_ < i; i_28_++) {
				anIntArray1578[i_28_] += anIntArray1578[i_28_ - i_27_] * anInt1590 / 100;
			}
		}
		if (aSoundEngine_1581.anIntArray236[0] > 0 || aSoundEngine_1581.anIntArray236[1] > 0) {
			aClass41_1584.method1110();
			int i_29_ = aClass41_1584.method1112(i + 1);
			int i_30_ = aSoundEngine_1581.method114(0, i_29_ / 65536.0F);
			int i_31_ = aSoundEngine_1581.method114(1, i_29_ / 65536.0F);
			if (i >= i_30_ + i_31_) {
				int i_32_ = 0;
				int i_33_ = i_31_;
				if (i_33_ > i - i_30_) {
					i_33_ = i - i_30_;
				}
				for (/**/; i_32_ < i_33_; i_32_++) {
					int i_34_ = (int) ((long) anIntArray1578[i_32_ + i_30_] * (long) SoundEngine.anInt237 >> 16);
					for (int i_35_ = 0; i_35_ < i_30_; i_35_++) {
						i_34_ += (int) ((long) anIntArray1578[i_32_ + i_30_ - 1 - i_35_] * (long) SoundEngine.anIntArrayArray233[0][i_35_] >> 16);
					}
					for (int i_36_ = 0; i_36_ < i_32_; i_36_++) {
						i_34_ -= (int) ((long) anIntArray1578[i_32_ - 1 - i_36_] * (long) SoundEngine.anIntArrayArray233[1][i_36_] >> 16);
					}
					anIntArray1578[i_32_] = i_34_;
					i_29_ = aClass41_1584.method1112(i + 1);
				}
				i_33_ = 128;
				for (;;) {
					if (i_33_ > i - i_30_) {
						i_33_ = i - i_30_;
					}
					for (/**/; i_32_ < i_33_; i_32_++) {
						int i_37_ = (int) ((long) anIntArray1578[i_32_ + i_30_] * (long) SoundEngine.anInt237 >> 16);
						for (int i_38_ = 0; i_38_ < i_30_; i_38_++) {
							i_37_ += (int) ((long) anIntArray1578[i_32_ + i_30_ - 1 - i_38_] * (long) SoundEngine.anIntArrayArray233[0][i_38_] >> 16);
						}
						for (int i_39_ = 0; i_39_ < i_31_; i_39_++) {
							i_37_ -= (int) ((long) anIntArray1578[i_32_ - 1 - i_39_] * (long) SoundEngine.anIntArrayArray233[1][i_39_] >> 16);
						}
						anIntArray1578[i_32_] = i_37_;
						i_29_ = aClass41_1584.method1112(i + 1);
					}
					if (i_32_ >= i - i_30_) {
						break;
					}
					i_30_ = aSoundEngine_1581.method114(0, i_29_ / 65536.0F);
					i_31_ = aSoundEngine_1581.method114(1, i_29_ / 65536.0F);
					i_33_ += 128;
				}
				for (/**/; i_32_ < i; i_32_++) {
					int i_40_ = 0;
					for (int i_41_ = i_32_ + i_30_ - i; i_41_ < i_30_; i_41_++) {
						i_40_ += (int) ((long) anIntArray1578[i_32_ + i_30_ - 1 - i_41_] * (long) SoundEngine.anIntArrayArray233[0][i_41_] >> 16);
					}
					for (int i_42_ = 0; i_42_ < i_31_; i_42_++) {
						i_40_ -= (int) ((long) anIntArray1578[i_32_ - 1 - i_42_] * (long) SoundEngine.anIntArrayArray233[1][i_42_] >> 16);
					}
					anIntArray1578[i_32_] = i_40_;
					i_29_ = aClass41_1584.method1112(i + 1);
				}
			}
		}
		for (int i_43_ = 0; i_43_ < i; i_43_++) {
			if (anIntArray1578[i_43_] < -32768) {
				anIntArray1578[i_43_] = -32768;
			}
			if (anIntArray1578[i_43_] > 32767) {
				anIntArray1578[i_43_] = 32767;
			}
		}
		return anIntArray1578;
	}
	
	public static void method1464() {
		anIntArray1578 = null;
		anIntArray1585 = null;
		anIntArray1593 = null;
		anIntArray1595 = null;
		anIntArray1594 = null;
		anIntArray1597 = null;
		anIntArray1598 = null;
		anIntArray1596 = null;
	}
	
	public SoundTrack() {
		anInt1583 = 0;
		anIntArray1582 = new int[5];
		anInt1590 = 100;
		anInt1588 = 0;
		anInt1591 = 500;
	}
	
	static {
		Random random = new Random(0L);
		for (int i = 0; i < 32768; i++) {
			anIntArray1585[i] = (random.nextInt() & 0x2) - 1;
		}
		anIntArray1593 = new int[32768];
		for (int i = 0; i < 32768; i++) {
			anIntArray1593[i] = (int) (Math.sin(i / 5215.1903) * 16384.0);
		}
		anIntArray1578 = new int[220500];
		anIntArray1594 = new int[5];
		anIntArray1596 = new int[5];
		anIntArray1595 = new int[5];
		anIntArray1597 = new int[5];
		anIntArray1598 = new int[5];
	}
}
