package com.jagex;
/* Class36 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class36 {
	public static NodeDeque aClass89_562 = new NodeDeque();
	public static HashTable anOa565 = new HashTable(8);
	public static HashTable mapList = new HashTable(8);
	public static RSString aClass16_566;
	public static int[] anIntArray567;
	public static RSString aClass16_568 = RSString.createString(" has logged out)3");
	public static int anInt569;
	public static RSString aClass16_570;
	public static boolean[] needs_clipping;
	public static HashTable anOa572;
	public static RSString aClass16_573;

	static final boolean method987(int i, byte b) {
		if (b < 121) {
			return true;
		}
		if ((i >> 21 & 0x1) == 0) {
			return false;
		}
		return true;
	}

	public static void method988(byte b) {
		if (b == 87) {
			aClass89_562 = null;
			aClass16_573 = null;
			aClass16_568 = null;
			aClass16_570 = null;
			anOa572 = null;
			anIntArray567 = null;
			aClass16_566 = null;
			anOa565 = null;
			needs_clipping = null;
		}
	}

	static final void method990(byte b, int useless, int musicId) {
		if (Class21.anInt342 != 0 && musicId != -1) {
			MusicPlayer.updateCurrentMusic(musicId, CacheConstants.extraMusicCacheIdx, 1, 0, Class21.anInt342, false);
			Js5.aBoolean1806 = true;
		}
		if (b <= 84) {
			method988((byte) -105);
		}
	}

	static {
		aClass16_566 = aClass16_568;
		anIntArray567 = new int[120];
		int i = 0;
		for (int i_10_ = 0; i_10_ < 120; i_10_++) {
			int i_11_ = 1 + i_10_;
			int i_12_ = (int) (Math.pow(2.0, i_11_ / 7.0) * 300.0 + i_11_);
			i += i_12_;
			anIntArray567[i_10_] = i / 4;
		}
		anInt569 = 0;
		needs_clipping = new boolean[100];
		aClass16_573 = RSString.createString(" seconds)3");
		aClass16_570 = aClass16_573;
	}
}
