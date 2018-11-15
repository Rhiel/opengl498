package com.jagex;
/* BZIPContext - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import com.rs2.client.scene.Scene;

import me.waliedyassen.graphics.rasterizer.Rasterizer;

public class BZIPContext
{
	public int anInt1329;
	public int[][] anIntArrayArray1330 = new int[6][258];
	public int anInt1332 = 0;
	public int anInt1333;
	public int anInt1334;
	public int[][] anIntArrayArray1335 = new int[6][258];
	public int[] anIntArray1336;
	public int anInt1337;
	public int anInt1338;
	static short[] aShortArray1339;
	static RSString aClass16_1340;
	public byte[] aByteArray1341 = new byte[256];
	public int anInt1342;
	static RSString aClass16_1343;
	public int[][] anIntArrayArray1344 = new int[6][258];
	public byte[][] aByteArrayArray1345;
	public static RSString aClass16_1346 = RSString.createString("Your profile will be transferred in:");
	public int anInt1347;
	public byte aByte1348;
	public byte[] aByteArray1349;
	public int anInt1350;
	public int anInt1351;
	public int anInt1352;
	static RSString aClass16_1353;
	public int anInt1354;
	public byte[] aByteArray1356;
	public int[] anIntArray1357 = new int[16];
	public int[] anIntArray1359;
	static RSString aClass16_1360;
	public boolean[] aBooleanArray1362;
	public boolean[] aBooleanArray1363;
	public static RSString aClass16_1364;
	public int anInt1365;
	public int[] anIntArray1366;
	public byte[] aByteArray1367;
	public int anInt1368;
	public byte[] aByteArray1369;
	public byte[] aByteArray1370;
	public int anInt1371;
	
	static final int method1313(int i, int i_0_) {
		return 127 & i_0_ >> 11;
	}
	
	static final void method1314(int i, int i_2_, int i_3_, int i_4_, int i_5_, int i_6_, int i_7_, int i_8_) {
		if (i_4_ < 128 || i_8_ < 128 || i_4_ > 13056 || i_8_ > 13056) {
			StaticMethods.anInt2989 = -1;
			Class97.anInt1645 = -1;
		} else {
			int i_9_ = -i_2_ + Scene.get_average_height(ObjType.localHeight, i_4_, i_8_);
			i_4_ -= Camera.xCameraPos;
			i_8_ -= Camera.yCameraPos;
			i_9_ -= Camera.zCameraPos;
			int i_10_ = Rasterizer.SINE[Camera.yCameraCurve];
			int i_11_ = Rasterizer.COSINE[Camera.xCameraCurve];
			int i_12_ = Rasterizer.SINE[Camera.xCameraCurve];
			int i_13_ = i_11_ * i_4_ + i_8_ * i_12_ >> 16;
			int i_14_ = Rasterizer.COSINE[Camera.yCameraCurve];
			i_8_ = i_8_ * i_11_ + -(i_4_ * i_12_) >> 16;
			i_4_ = i_13_;
			i_13_ = -(i_8_ * i_10_) + i_14_ * i_9_ >> 16;
			i_8_ = i_14_ * i_8_ + i_9_ * i_10_ >> 16;
			i_9_ = i_13_;
			if (i_6_ != -16734) {
				method1315(true);
			}
			if (i_8_ < 50) {
				StaticMethods.anInt2989 = -1;
				Class97.anInt1645 = -1;
			} else {
				StaticMethods.anInt2989 = i_7_ - -((i_4_ << 9) / i_8_);
				Class97.anInt1645 = i - -((i_9_ << 9) / i_8_);
			}
		}
	}
	
	public static void method1315(boolean bool) {
		aClass16_1340 = null;
		aClass16_1364 = null;
		if (bool != false) {
			aClass16_1360 = null;
		}
		aClass16_1360 = null;
		aClass16_1353 = null;
		aShortArray1339 = null;
		aClass16_1346 = null;
		aClass16_1343 = null;
	}

	BZIPContext() {
		anInt1337 = 0;
		aBooleanArray1362 = new boolean[16];
		aByteArrayArray1345 = new byte[6][258];
		aBooleanArray1363 = new boolean[256];
		aByteArray1367 = new byte[18002];
		anIntArray1336 = new int[6];
		aByteArray1369 = new byte[4096];
		anIntArray1366 = new int[256];
		anIntArray1359 = new int[257];
		aByteArray1370 = new byte[18002];
	}
	
	static {
		aClass16_1340 = aClass16_1346;
		aClass16_1343 = RSString.createString("Bitte laden Sie die Seite neu)3");
		aClass16_1360 = RSString.createString("(U2");
		aClass16_1364 = RSString.createString("Connecting to server)3)3)3");
		aClass16_1353 = aClass16_1364;
	}
}
