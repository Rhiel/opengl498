package com.jagex;
/* Class42 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class42
{
	public int chatFileID;
	public Class23_Sub13_Sub12 aClass23_Sub13_Sub12_646;
	public int[] anIntArray648;
	static RSString aClass16_651 = RSString.createString("<col=80ff00>");
	static RSString aClass16_652;
	static RSString aClass16_653;
	public static RSString aClass16_654 = RSString.createString("slide:");
	static short[] aShortArray655;
	public static RSString aClass16_656 = RSString.createString("K");
	static RSString aClass16_657;
	static RSString aClass16_659;
	public static int anInt660;
	public static MemoryCache aJList_661;
    static RSInterface aClass64_663;
	
	public static void method1113() {
		aClass16_653 = null;
		aClass16_654 = null;
		aClass64_663 = null;
		aClass16_657 = null;
		aClass16_659 = null;
		aJList_661 = null;
		aShortArray655 = null;
		aClass16_651 = null;
		aClass16_656 = null;
		aClass16_652 = null;
	}
	
	static final boolean method1116(RSString class16) {
		if (class16 == null) {
			return false;
		}
		for (int i = 0; (i ^ 0xffffffff) > (Class45.friends_count ^ 0xffffffff); i++) {
			if (class16.equalsIgnoreCase(StaticMethods.friends_name[i])) {
				return true;
			}
		}
		if (class16.equalsIgnoreCase(GameClient.currentPlayer.username)) {
			return true;
		}
		return false;
	}
	
	static {
		aClass16_653 = aClass16_654;
		aClass16_659 = aClass16_654;
		aClass16_652 = aClass16_656;
		aClass16_657 = aClass16_656;
		aJList_661 = new MemoryCache(16);
	}
}
