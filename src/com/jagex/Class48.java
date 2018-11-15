package com.jagex;

public class Class48
{
	public static int anInt744 = 0;
	static RSString aClass16_745;
	static RSString aClass16_746;
	static RSString b12_full = RSString.createString("b12_full");
	public static RSString aClass16_748;
	public static int anInt749;
	public static int anInt751;
	static short aShort752 = 256;
	
	public static final void process_audio() {
		if (Class97.aStereo_1646 != null) {
			Class97.aStereo_1646.method85((byte) -39);
		}
		if (SeekableFile.aStereo_471 != null) {
			SeekableFile.aStereo_471.method85((byte) -39);
		}
	}
	
	public static void method1151(byte b) {
		if (b != 74) {
			anInt749 = 38;
		}
		aClass16_745 = null;
		b12_full = null;
		aClass16_748 = null;
		aClass16_746 = null;
	}
	
	static {
		anInt751 = -1;
		aClass16_748 = RSString.createString("shake:");
		anInt749 = 0;
		aClass16_745 = aClass16_748;
		aClass16_746 = aClass16_748;
	}
}
