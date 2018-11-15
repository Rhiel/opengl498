package com.jagex;
/* Class87_Sub1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.awt.Component;

import com.jagex.launcher.GameLaunch;

public class Class87_Sub1 extends Class87
{
	public final int anInt2771;
	public final int anInt2774;
	public final int anInt2777;
	static RSString challengeRequestMessage;
	static RSString aClass16_2783;
	public final int anInt2785;
	static RSString aClass16_2786;
	
	static final Stereo method1410(int i, int i_0_, byte b, SignLink signLink, Component component) {
		if ((Keyboard.sampleRate ^ 0xffffffff) == -1) {
			throw new IllegalStateException();
		}
		if ((i_0_ ^ 0xffffffff) > -1 || i_0_ >= 2) {
			throw new IllegalArgumentException();
		}
		if (i < 256) {
			i = 256;
		}
		try {
			Stereo stereo = (Stereo) Class.forName("rs.zaros.jagex.JavaAudioSink").newInstance();
			stereo.samples = new int[(!FileSystem.stereo ? 1 : 2) * 256];
			stereo.anInt158 = i;
			stereo.initialize(component);
			stereo.anInt150 = (i & ~0x3ff) + 1024;
			if (b <= 23) {
				return null;
			}
			if (stereo.anInt150 > 16384) {
				stereo.anInt150 = 16384;
			}
			stereo.open_line(stereo.anInt150);
			if ((RSInterface.anInt1030 ^ 0xffffffff) < -1 && Class28.aClass33_428 == null) {
				Class28.aClass33_428 = new Class33();
				Class28.aClass33_428.aSignLink_510 = signLink;
				signLink.newRunnable(RSInterface.anInt1030, Class28.aClass33_428, (byte) 72);
			}
			if (Class28.aClass33_428 != null) {
				if (Class28.aClass33_428.aStereoArray512[i_0_] != null) {
					throw new IllegalArgumentException();
				}
				Class28.aClass33_428.aStereoArray512[i_0_] = stereo;
			}
			return stereo;
		} catch (Throwable throwable) {
			try {
				StereoUtils stereoUtils = new StereoUtils(signLink, i_0_);
				stereoUtils.anInt158 = i;
				stereoUtils.samples = new int[256 * (FileSystem.stereo ? 2 : 1)];
				stereoUtils.initialize(component);
				stereoUtils.anInt150 = 16384;
				stereoUtils.open_line(stereoUtils.anInt150);
				if ((RSInterface.anInt1030 ^ 0xffffffff) < -1 && Class28.aClass33_428 == null) {
					Class28.aClass33_428 = new Class33();
					Class28.aClass33_428.aSignLink_510 = signLink;
					signLink.newRunnable(RSInterface.anInt1030, Class28.aClass33_428, (byte) 72);
				}
				if (Class28.aClass33_428 != null) {
					if (Class28.aClass33_428.aStereoArray512[i_0_] != null) {
						throw new IllegalArgumentException();
					}
					Class28.aClass33_428.aStereoArray512[i_0_] = stereoUtils;
				}
				return stereoUtils;
			} catch (Throwable throwable_1_) {
				return new Stereo();
			}
		}
	}
	
	Class87_Sub1(int i, int i_2_, int i_3_, int i_4_, int i_5_, int i_6_, int i_7_) {
		super(i_5_, i_6_, i_7_);
		anInt2777 = i;
		anInt2771 = i_4_;
		anInt2774 = i_3_;
		anInt2785 = i_2_;
	}

	@Override
	public final void method1409(int i, int i_8_, int i_9_) {
		if (i_8_ <= 31) {
			method1411((byte) 115);
		}
	}

	@Override
	public final void method1408(int i, int i_10_, int i_11_) {
		if (i_11_ >= -101) {
			method1410(-5, -52, (byte) -70, null, null);
		}
		int i_12_ = anInt2774 * i >> 12;
		int i_13_ = i * anInt2777 >> 12;
		int i_14_ = i_10_ * anInt2771 >> 12;
		int i_15_ = i_10_ * anInt2785 >> 12;
		Class28.method940(i_13_, i_14_, anInt1487, i_12_, (byte) 120, anInt1481, i_15_, anInt1494);
	}

	@Override
	public final void method1406(int i, int i_16_, int i_17_) {
		int i_18_ = i_17_ * anInt2777 >> 12;
		int i_19_ = anInt2785 * i_16_ >> 12;
		if (i != 1) {
			aClass16_2786 = null;
		}
		int i_20_ = anInt2774 * i_17_ >> 12;
		int i_21_ = anInt2771 * i_16_ >> 12;
		ColourImageCacheSlot.method901(i_18_, i_20_, i_21_, anInt1481, i_19_, -4838);
	}
	
	public static void method1411(byte b) {
		aClass16_2786 = null;
		aClass16_2783 = null;
		challengeRequestMessage = null;
		if (b <= 44) {
			challengeRequestMessage = null;
		}
	}
	
	static {
		aClass16_2783 = RSString.createString("gr-Un:");
		challengeRequestMessage = RSString.createString(":chalreq:");
		SceneController.picked_tile_x = -1;
		aClass16_2786 = RSString.createString("zur-Uck auf die "+ GameLaunch.getSetting().getName() +")2Webseite gehen");
	}
}
