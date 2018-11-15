package com.jagex;

public class CacheFileWorker {
	public static int anInt2854;
	static RSString aClass16_2856 = RSString.createString(")1j");
	static RSString aClass16_2862;
	static RSString aClass16_2867;
	static RSString aClass16_2869;
	public static int[] skillLevels;
	public static RSString aClass16_2873 = RSString.createString("Started 3d library");
	static RSString aClass16_2877;

	public static void method1567(boolean bool) {
		aClass16_2877 = null;
		aClass16_2869 = null;
		aClass16_2862 = null;
		if (bool != false) {
			aClass16_2867 = null;
		}
		skillLevels = null;
		aClass16_2867 = null;
		aClass16_2873 = null;
		aClass16_2856 = null;
	}

	static final boolean method1572(int i) {
		synchronized (InputManager.keyboard) {
			if (ColourImageCache.anInt1724 == StaticMethods.anInt3011) {
				return false;
			}
			Class88.keyPressedID = StaticMethods.anIntArray596[ColourImageCache.anInt1724];
			if (i != 25014) {
				return false;
			}
			Class53.anInt833 = LocResult.anIntArray3719[ColourImageCache.anInt1724];
			ColourImageCache.anInt1724 = 0x7f & 1 + ColourImageCache.anInt1724;
			return true;
		}
	}

	static {
		skillLevels = new int[26];
		anInt2854 = 0;
		aClass16_2869 = RSString.createString("null");
		aClass16_2867 = RSString.createString("(U");
		aClass16_2877 = null;
		CacheFileWorker.aClass16_2877 = StaticMethods.empty_string;
		aClass16_2862 = aClass16_2873;
	}
}
