package com.jagex;
/* Class68 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import com.jagex.graphics.runetek4.media.PaletteSprite;

public class NameHashTable
{
	static ClanChatMember[] currentClanChatUsers;
	static long[] friends_uid;
	static short[][] aShortArrayArray1189;
	static RSString aClass16_1191 = RSString.createString("Ihr Spielkonto wird bereits benutzt)3");
	public static int result_buffer_ptr;
	static RSString aClass16_1198 = RSString.createString("rect_debug=");
	public static int anInt1199;
	public static int anInt1202;
	public int[] identTable;
	
	public final int get_index(int nameHash) {
		int i_2_ = -1 + (identTable.length >> 1);
		int i_3_ = i_2_ & nameHash;
		for (;;) {
			int i_4_ = identTable[i_3_ + i_3_ + 1];
			if ((i_4_ ^ 0xffffffff) == 0) {
				return -1;
			}
			if (nameHash == identTable[i_3_ + i_3_]) {
				return i_4_;
			}
			i_3_ = 1 + i_3_ & i_2_;
		}
	}
	
	static final void method1250(int i, int i_7_, int[] is, int i_8_, int i_9_, int i_10_, int i_11_, int i_12_, byte[] bs, int i_13_) {
		int i_14_ = -(i_13_ >> 2);
		i_13_ = -(0x3 & i_13_);
		int i_15_ = -i_11_;
		if (i >= -80) {
			PaletteSprite.load_all(null, -100, 118);
		}
		for (/**/; i_15_ < 0; i_15_++) {
			for (int i_16_ = i_14_; (i_16_ ^ 0xffffffff) > -1; i_16_++) {
				if (bs[i_8_++] == 0) {
					i_9_++;
				} else {
					is[i_9_++] = i_12_;
				}
				if (bs[i_8_++] == 0) {
					i_9_++;
				} else {
					is[i_9_++] = i_12_;
				}
				if (bs[i_8_++] != 0) {
					is[i_9_++] = i_12_;
				} else {
					i_9_++;
				}
				if ((bs[i_8_++] ^ 0xffffffff) == -1) {
					i_9_++;
				} else {
					is[i_9_++] = i_12_;
				}
			}
			for (int i_17_ = i_13_; i_17_ < 0; i_17_++) {
				if (bs[i_8_++] != 0) {
					is[i_9_++] = i_12_;
				} else {
					i_9_++;
				}
			}
			i_9_ += i_10_;
			i_8_ += i_7_;
		}
	}


	public static void method1255(int i) {
		aClass16_1198 = null;
		GameClient.js5_connect_request = null;
		aShortArrayArray1189 = null;
		aClass16_1191 = null;
		currentClanChatUsers = null;
		friends_uid = null;
	}
	
	public NameHashTable(int[] is) {
		int i;
		for (i = 1; i <= is.length + (is.length >> 1); i <<= 1) {
			/* empty */
		}
		identTable = new int[i - -i];
		for (int i_28_ = 0; i_28_ < i + i; i_28_++) {
			identTable[i_28_] = -1;
		}
		for (int i_29_ = 0; (is.length ^ 0xffffffff) < (i_29_ ^ 0xffffffff); i_29_++) {
			int i_30_;
			for (i_30_ = i + -1 & is[i_29_]; identTable[1 + i_30_ + i_30_] != -1; i_30_ = i - 1 & i_30_ - -1) {
				/* empty */
			}
			identTable[i_30_ + i_30_] = is[i_29_];
			identTable[i_30_ - -i_30_ - -1] = i_29_;
		}
	}
	
	static {
		anInt1202 = 0;
		friends_uid = new long[200];
	}
}
