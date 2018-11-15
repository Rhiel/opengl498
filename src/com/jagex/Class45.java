package com.jagex;

public class Class45 {
	static RSString aClass16_682 = RSString.createString("W-=hlen Sie eine Welt");
	public int anInt686;
	public static RSString aClass16_687;
	public static RSString aClass16_688 = RSString.createString("Loaded interfaces");
	static RSString aClass16_693;
	public int[][] anIntArrayArray694;
	public static int friends_count;
	static RSString aClass16_698;
	static RSString aClass16_699;
	public int anInt701;

	final int method1130(int i, boolean bool) {
		if (bool != true) {
			anInt701 = 111;
		}
		if (anIntArrayArray694 != null) {
			i = (int) ((long) i * (long) anInt686 / anInt701);
		}
		return i;
	}

	public static void method1131(boolean bool) {
		aClass16_698 = null;
		aClass16_682 = null;
		aClass16_688 = null;
		aClass16_687 = null;
		aClass16_699 = null;
		aClass16_693 = null;
	}

	static final void addFriend(long name, int i) {
		if ((name ^ 0xffffffffffffffffL) != -1L) {
			if (friends_count >= 100 && Class30.anInt478 != 1 || friends_count >= 200) {
				Class95.sendGameMessage(0, -1, Class97.aClass16_1640, StaticMethods.empty_string);
			} else {
				RSString nameString = WallObject.getStringFromLong(-1, name).method154();
				for (int i_0_ = 0; (friends_count ^ 0xffffffff) < (i_0_ ^ 0xffffffff); i_0_++) {
					if (NameHashTable.friends_uid[i_0_] == name) {
						Class95.sendGameMessage(0, i + -32768, RSString.joinRsStrings(new RSString[] { nameString, Class107.aClass16_1835 }), StaticMethods.empty_string);
						return;
					}
				}
				if (i != 32767) {
					method1137(-117, false);
				}
				for (int i_1_ = 0; (i_1_ ^ 0xffffffff) > (PlayerRelations.ignoreListSize ^ 0xffffffff); i_1_++) {
					if (name == PlayerRelations.ignoreList[i_1_]) {
						Class95.sendGameMessage(0, i ^ ~0x7fff, RSString.joinRsStrings(new RSString[] { HashTable.aClass16_1254, nameString, Huffman.aClass16_1830 }), StaticMethods.empty_string);
						return;
					}
				}
				if (nameString.equals(GameClient.currentPlayer.username)) {
					Class95.sendGameMessage(0, i ^ ~0x7fff, ObjectNode.aClass16_2238, StaticMethods.empty_string);
				} else {
					StaticMethods.friends_name[friends_count] = nameString;
					NameHashTable.friends_uid[friends_count] = name;
					Class23_Sub10_Sub3.friends_worldid[friends_count] = 0;
					Class87_Sub3.friends_worldname[friends_count] = StaticMethods.empty_string;
					Mouse.friends_rank[friends_count] = 0;
					StaticMethods2.aBooleanArray1741[friends_count] = false;
					friends_count++;
					Class75.anInt1372 = SomeSoundClass.anInt3589;
					GameClient.outBuffer.putOpcode(92);
					GameClient.outBuffer.putLong(name, (byte) -120);
				}
			}
		}
	}

	static final void renderGlobalPlayers(int i) {
		if (i == 0) {
			while (StaticMethods2.packet.bitdiff(1693770787, StaticMethods.currentLength) >= 11) {
				int index = StaticMethods2.packet.getBits(11);
				if (index == 2047) {
					break;
				}
				boolean bool = false;
				if (GameClient.localPlayers[index] == null) {
					GameClient.localPlayers[index] = new Player();
					bool = true;
					if (NPC.aClass23_Sub5Array4377[index] != null) {
						GameClient.localPlayers[index].parseAppearance(12, NPC.aClass23_Sub5Array4377[index]);
					}
				}
				GameClient.localPlayerPointers[StaticMethods.anInt3067++] = index;
				Player class38_sub7_sub2 = GameClient.localPlayers[index];
				class38_sub7_sub2.lastUpdate = GameClient.timer;
				int update = StaticMethods2.packet.getBits(1);
				if (update == 1) { // Update masks should be parsed.
					TimeTools.maskUpdates[SoundEffects.updateMaskIndex++] = index;
				}
				int offsetY = StaticMethods2.packet.getBits(5);
				int direction = StaticMethods.anIntArray3097[StaticMethods2.packet.getBits(3)];
				if (bool) {
					class38_sub7_sub2.faceDirection = class38_sub7_sub2.anInt2680 = direction;
				}
				int teleporting = StaticMethods2.packet.getBits(1);
				int offsetX = StaticMethods2.packet.getBits(5);
				if (offsetY > 15) {
					offsetY -= 32;
				}
				if (offsetX > 15) {
					offsetX -= 32;
				}
				// System.out.println("")
				// System.out.println("Adding player " + index + " [update=" + (update == 1) + ", offx=" + offsetX + ", offy=" + offsetY
				// + ", dir=" + direction + ", cached=" + (cachedAppearance == 1) + "].");
				class38_sub7_sub2.updatePosition(offsetX + GameClient.currentPlayer.waypointsX[0], (byte) -116, teleporting == 1, GameClient.currentPlayer.waypointsY[0] + offsetY);
			}
			StaticMethods2.packet.end_bitwise_access(-110);
		}
	}

	final int method1134(int i, int i_8_) {
		if (anIntArrayArray694 != null) {
			i = 6 - -(int) ((long) i * (long) anInt686 / anInt701);
		}
		if (i_8_ != 23596) {
			return -69;
		}
		return i;
	}

	final byte[] method1135(int i, byte[] bs) {
		if (anIntArrayArray694 != null) {
			int i_9_ = 0;
			int i_10_ = (int) ((long) anInt686 * (long) bs.length / anInt701) + 14;
			int i_11_ = 0;
			int[] is = new int[i_10_];
			for (int i_12_ = 0; (i_12_ ^ 0xffffffff) > (bs.length ^ 0xffffffff); i_12_++) {
				int i_13_ = bs[i_12_];
				int[] is_14_ = anIntArrayArray694[i_9_];
				for (int i_15_ = 0; i_15_ < 14; i_15_++) {
					is[i_15_ + i_11_] += i_13_ * is_14_[i_15_];
				}
				i_9_ += anInt686;
				int i_16_ = i_9_ / anInt701;
				i_11_ += i_16_;
				i_9_ -= anInt701 * i_16_;
			}
			bs = new byte[i_10_];
			for (int i_17_ = 0; i_17_ < i_10_; i_17_++) {
				int i_18_ = 32768 + is[i_17_] >> 16;
				if (i_18_ < -128) {
					bs[i_17_] = (byte) -128;
				} else if (i_18_ > 127) {
					bs[i_17_] = (byte) 127;
				} else {
					bs[i_17_] = (byte) i_18_;
				}
			}
		}
		return bs;
	}

	static final boolean method1137(int optionMask, boolean bool) {
		if ((optionMask & 0x18c68b) >> 20 == 0) {
			return false;
		}
		return true;
	}

	Class45(int i, int i_19_) {
		if ((i ^ 0xffffffff) != (i_19_ ^ 0xffffffff)) {
			int i_20_ = StaticMethods2.method852(i, 81, i_19_);
			i_19_ /= i_20_;
			i /= i_20_;
			anInt701 = i;
			anInt686 = i_19_;
			anIntArrayArray694 = new int[i][14];
			for (int i_21_ = 0; (i_21_ ^ 0xffffffff) > (i ^ 0xffffffff); i_21_++) {
				int[] is = anIntArrayArray694[i_21_];
				double d = 6.0 + (double) i_21_ / (double) i;
				int i_22_ = (int) Math.floor(-7.0 + d + 1.0);
				if ((i_22_ ^ 0xffffffff) > -1) {
					i_22_ = 0;
				}
				int i_23_ = (int) Math.ceil(7.0 + d);
				if (i_23_ > 14) {
					i_23_ = 14;
				}
				double d_24_ = (double) i_19_ / (double) i;
				for (/**/; i_23_ > i_22_; i_22_++) {
					double d_25_ = (-d + i_22_) * 3.141592653589793;
					double d_26_ = d_24_;
					if (d_25_ < -1.0E-4 || d_25_ > 1.0E-4) {
						d_26_ *= Math.sin(d_25_) / d_25_;
					}
					d_26_ *= 0.46 * Math.cos((i_22_ - d) * 0.2243994752564138) + 0.54;
					is[i_22_] = (int) Math.floor(65536.0 * d_26_ + 0.5);
				}
			}
		}
	}

	static {
		aClass16_687 = RSString.createString("You can(Wt add yourself to your own ignore list)3");
		aClass16_693 = aClass16_687;
		friends_count = 0;
		aClass16_698 = RSString.createString("und Ihr Passwort ein)3");
		aClass16_699 = aClass16_688;
	}
}
