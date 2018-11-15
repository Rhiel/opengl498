package com.jagex;

public class Class23_Sub13_Sub12 extends Queuable {
	public int[] anIntArray3966;
	static RSString aClass16_3968 = RSString.createString("Der Anmelde)2Server ist offline)3");
	public RSString[] aClass16Array3969;
	static short aShort3971 = 205;
	public int[][] anIntArrayArray3974;
	static long aLong3975 = 0L;
	public int[] anIntArray3981;

	final int method745(int i, int i_1_, int i_2_) {
		if (i_1_ > -119) {
			return 34;
		}
		if (anIntArray3981 == null || i < 0 || i > anIntArray3981.length) {
			return -1;
		}
		if (anIntArrayArray3974[i] == null || i_2_ < 0 || anIntArrayArray3974[i].length < i_2_) {
			return -1;
		}
		return anIntArrayArray3974[i][i_2_];
	}

	public final void method747(byte b, Packet class23_sub5, int i) {
		if (b >= -115) {
			method747((byte) 49, null, -8);
		}
		if (i != 1) {
			if (i != 2) {
				if (i == 3) {
					int i_4_ = class23_sub5.g1();
					anIntArrayArray3974 = new int[i_4_][];
					anIntArray3981 = new int[i_4_];
					for (int i_5_ = 0; i_5_ < i_4_; i_5_++) {
						int i_6_ = class23_sub5.g2();
						anIntArray3981[i_5_] = i_6_;
						anIntArrayArray3974[i_5_] = new int[Varbit.anIntArray4001[i_6_]];
						for (int i_7_ = 0; (Varbit.anIntArray4001[i_6_] ^ 0xffffffff) < (i_7_ ^ 0xffffffff); i_7_++) {
							anIntArrayArray3974[i_5_][i_7_] = class23_sub5.g2();
						}
					}
				}
			} else {
				int i_8_ = class23_sub5.g1();
				anIntArray3966 = new int[i_8_];
				for (int i_9_ = 0; i_8_ > i_9_; i_9_++) {
					anIntArray3966[i_9_] = class23_sub5.g2();
				}
			}
		} else {
			aClass16Array3969 = class23_sub5.gstr().method168(60);
		}
	}

	final RSString method749(byte b) {
		RSString class16 = RSString.create(80, 0);
		class16.append(aClass16Array3969[0]);
		for (int i_24_ = 1; (aClass16Array3969.length ^ 0xffffffff) < (i_24_ ^ 0xffffffff); i_24_++) {
			class16.append(ObjType.aClass16_3958);
			class16.append(aClass16Array3969[i_24_]);
		}
		return class16.method178();
	}

	final void method750(Packet class23_sub5, int i, int[] is) {
		if (anIntArray3981 != null) {
			if (i != 4056) {
				method752(null, -88);
			}
			for (int i_25_ = 0; i_25_ < anIntArray3981.length && (i_25_ ^ 0xffffffff) > (is.length ^ 0xffffffff); i_25_++) {
				int i_26_ = GameShell.anIntArray42[method751(0, i_25_)];
				if ((i_26_ ^ 0xffffffff) < -1) {
					class23_sub5.method448(44, i_26_, is[i_25_]);
				}
			}
		}
	}

	final int method751(int i, int i_27_) {
		if (anIntArray3981 == null || i_27_ < 0 || (anIntArray3981.length ^ 0xffffffff) > (i_27_ ^ 0xffffffff)) {
			return -1;
		}
		if (i != 0) {
			return -23;
		}
		return anIntArray3981[i_27_];
	}

	final RSString method752(Packet buffer, int i) {
		RSString class16 = RSString.create(80, i);
		if (anIntArray3981 != null) {
			for (int index = 0; (anIntArray3981.length ^ 0xffffffff) < (index ^ 0xffffffff); index++) {
				class16.append(aClass16Array3969[index]);
				class16.append(Class71.method1265(-6, anIntArray3981[index], buffer.method449(Class44.anIntArray672[anIntArray3981[index]], 0), anIntArrayArray3974[index]));
			}
		}
		class16.append(aClass16Array3969[-1 + aClass16Array3969.length]);
		return class16.method178();
	}

	final int method753(int i) {
		if (i != 0) {
			aShort3971 = (short) 13;
		}
		if (anIntArray3981 == null) {
			return 0;
		}
		return anIntArray3981.length;
	}

	public static void method754(int i) {
		aClass16_3968 = null;
		if (i != -4) {
			aClass16_3968 = null;
		}
	}

	final void method755(Packet class23_sub5, int i) {
		if (i != -28386) {
			aClass16Array3969 = null;
		}
		for (;;) {
			int i_29_ = class23_sub5.g1();
			if ((i_29_ ^ 0xffffffff) == -1) {
				break;
			}
			method747((byte) -125, class23_sub5, i_29_);
		}
	}
}
