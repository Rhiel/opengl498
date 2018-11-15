package com.jagex;

/* GameTimer - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class GameTimer extends AbstractTimer
{
	public static int anInt1988 = 0;
	public int anInt1991;
	public long aLong1994;
	public int anInt1995;
	public int anInt1997;
	public int anInt1998;
	static NodeDeque aClass89_1999;
	public long[] aLongArray2000 = new long[10];
	public static int[] keyCodesPressed = new int[128];
	public static int anInt2002;
	public static boolean aBoolean2003;
	
	public static void method191(byte b) {
		aClass89_1999 = null;
		if (b > -10) {
			method191((byte) -27);
		}
		keyCodesPressed = null;
	}
	
	static final int method192(int i, int i_0_, RSInterface inter) {
		if (inter.childBuffers == null || i_0_ >= inter.childBuffers.length) {
			return -2;
		}
		try {
			if (i != -26) {
				aClass89_1999 = null;
			}
			int value = 0;
			int[] buffer = inter.childBuffers[i_0_];
			int operationType = 0;
			int index = 0;
			for (;;) {
				int opcode = buffer[index++];
				int i_5_ = 0;
				int i_6_ = 0;
				if (opcode == 0) {
					return value;
				}
				if (opcode == 1) {
					i_5_ = CacheFileWorker.skillLevels[buffer[index++]];
				}
				if (opcode == 2) {
					i_5_ = ReflectionRequest.anIntArray2482[buffer[index++]];
				}
				if (opcode == 3) {
					i_5_ = PlayerAppearance.skillExperience[buffer[index++]];
				}
				if (opcode == 4) {
					int i_7_ = buffer[index++] << 16;
					i_7_ += buffer[index++];
					RSInterface child = RSInterface.getInterface(i_7_);
					int childId = buffer[index++];
					if (childId != -1 && (!ObjTypeList.list(childId).membersItem || GameClient.isMembers())) {
						for (int i_10_ = 0; (i_10_ ^ 0xffffffff) > (child.obj_ids.length ^ 0xffffffff); i_10_++) {
							if (1 + childId == child.obj_ids[i_10_]) {
								i_5_ += child.obj_counts[i_10_];
							}
						}
					}
				}
				if (opcode == 15) {
					i_6_ = 1;
				}
				if (opcode == 5) {
					int configId = buffer[index++];
					i_5_ = GameClient.configs[configId];
				}
				if (opcode == 16) {
					i_6_ = 2;
				}
				if (opcode == 6) {
					i_5_ = Class36.anIntArray567[ReflectionRequest.anIntArray2482[buffer[index++]] + -1];
				}
				if (opcode == 17) {
					i_6_ = 3;
				}
				if (opcode == 7) {
					int configId = buffer[index++];
					i_5_ = 100 * GameClient.configs[configId] / 46875;
				}
				if (opcode == 8) {
					i_5_ = GameClient.currentPlayer.combatLevel;
				}
				if (opcode == 9) {
					for (int i_11_ = 0; i_11_ < 26; i_11_++) {
						if (WallDecoration.aBooleanArray376[i_11_]) {
							i_5_ += ReflectionRequest.anIntArray2482[i_11_];
						}
					}
				}
				if (opcode == 10) {
					int i_12_ = buffer[index++] << 16;
					i_12_ += buffer[index++];
					RSInterface class64_13_ = RSInterface.getInterface(i_12_);
					int i_14_ = buffer[index++];
					if ((i_14_ ^ 0xffffffff) != 0 && (!ObjTypeList.list( i_14_).membersItem || GameClient.isMembers())) {
						for (int obj_id : class64_13_.obj_ids) {
							if ((obj_id ^ 0xffffffff) == (1 + i_14_ ^ 0xffffffff)) {
								i_5_ = 999999999;
								break;
							}
						}
					}
				}
				if (opcode == 11) {
					i_5_ = SpotType.runEnergy;
				}
				if (opcode == 12) {
					i_5_ = Class36.anInt569;
				}
				if (opcode == 13) {
					int configId = buffer[index++];
					int i_16_ = GameClient.configs[configId];
					int i_17_ = buffer[index++];
					i_5_ = (1 << i_17_ & i_16_) == 0 ? 0 : 1;
				}
				if (opcode == 14) {
					int configFileId = buffer[index++];
					i_5_ = Varbit.getConfigFileValue(configFileId);
				}
				if (opcode == 18) {
					i_5_ = MapLoader.region_aboslute_z + (GameClient.currentPlayer.bound_extents_x >> 7);
				}
				if (opcode == 19) {
					i_5_ = MapLoader.region_aboslute_x + (GameClient.currentPlayer.bound_extents_z >> 7);
				}
				if (opcode == 20) {
					i_5_ = buffer[index++];
				}
				if (i_6_ != 0) {
					operationType = i_6_;
				} else {
					if (operationType == 0) {
						value += i_5_;
					}
					if (operationType == 1) {
						value -= i_5_;
					}
					if (operationType == 2 && i_5_ != 0) {
						value /= i_5_;
					}
					if (operationType == 3) {
						value *= i_5_;
					}
					operationType = 0;
				}
			}
		} catch (Exception exception) {
			return -1;
		}
	}
	
	@Override
	final int wait_for_next_frame(int i, byte b, int i_19_) {
		int i_20_ = anInt1998;
		anInt1998 = 300;
		int i_21_ = anInt1991;
		anInt1991 = 1;
		aLong1994 = TimeTools.getMillis();
		if (aLongArray2000[anInt1995] != 0L) {
			if ((aLongArray2000[anInt1995] ^ 0xffffffffffffffffL) > (aLong1994 ^ 0xffffffffffffffffL)) {
				anInt1998 = (int) (i * 2560 / (-aLongArray2000[anInt1995] + aLong1994));
			}
		} else {
			anInt1998 = i_20_;
			anInt1991 = i_21_;
		}
		if (anInt1998 < 25) {
			anInt1998 = 25;
		}
		if (anInt1998 > 256) {
			anInt1998 = 256;
			anInt1991 = (int) (-((-aLongArray2000[anInt1995] + aLong1994) / 10L) + i);
		}
		if ((anInt1991 ^ 0xffffffff) < (i ^ 0xffffffff)) {
			anInt1991 = i;
		}
		aLongArray2000[anInt1995] = aLong1994;
		anInt1995 = (anInt1995 - -1) % 10;
		if (anInt1991 > 1) {
			for (int i_23_ = 0; i_23_ < 10; i_23_++) {
				if ((aLongArray2000[i_23_] ^ 0xffffffffffffffffL) != -1L) {
					aLongArray2000[i_23_] = anInt1991 + aLongArray2000[i_23_];
				}
			}
		}
		if (i_19_ > anInt1991) {
			anInt1991 = i_19_;
		}
		TimeTools.sleep(anInt1991);
		int i_24_ = 0;
		for (/**/; anInt1997 < 256; anInt1997 += anInt1998) {
			i_24_++;
		}
		anInt1997 &= 0xff;
		return i_24_;
	}
	
	GameTimer() {
		anInt1998 = 256;
		anInt1997 = 0;
		anInt1991 = 1;
		aLong1994 = TimeTools.getMillis();
		for (int i = 0; i < 10; i++) {
			aLongArray2000[i] = aLong1994;
		}
	}
	
	@Override
	final void method188(int i) {
		for (int i_26_ = 0; i_26_ < 10; i_26_++) {
			aLongArray2000[i_26_] = 0L;
		}
	}
	
	static {
		aClass89_1999 = new NodeDeque();
		anInt2002 = 0;
		aBoolean2003 = false;
	}
}
