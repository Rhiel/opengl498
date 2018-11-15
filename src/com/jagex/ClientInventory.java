package com.jagex;
/* ClientInventory - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class ClientInventory extends Linkable
{
    static RSString aClass16_2363 = RSString.createString("RuneScape wurde aktualisiert(Q");
	public int[] slot_obj_count = new int[1];
	static RSString aClass16_2365;
    static RSString aClass16_2367 = RSString.createString("Bitte versuchen Sie)1");
    public static RSString aClass16_2370;
	static RSString aClass16_2371 = RSString.createString("<col=ff7000>");
	public static int anInt2372;
	public int[] slot_obj_id = { -1 };
	
	public static void method873(boolean bool) {
		aClass16_2370 = null;
		aClass16_2367 = null;
		if (bool == false) {
			aClass16_2371 = null;
			aClass16_2363 = null;
			CacheConstants.varbitCacheIdx = null;
			aClass16_2365 = null;
		}
	}
	
	static {
		aClass16_2370 = RSString.createString("Members only world");
		anInt2372 = 0;
		aClass16_2365 = aClass16_2370;
	}
}
