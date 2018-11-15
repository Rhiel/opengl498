package com.jagex;
/* PacketParser - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Queuable extends Linkable {
	
	static RSString aClass16_2306;
	public static RSString aClass16_2308;
	public static int anInt2309 = 0;
	public Queuable queue_next;
	static Js5 aClass105_2312;
	static RSString aClass16_2313 = RSString.createString("Lade Schrifts-=tze )2 ");
	public Queuable queue_prev;
	public static int systemUpdateTime;
	static boolean[] aBooleanArray2317;
	public static int anInt2320;

	public long queue_id;

	public static void method600(int i) {
		aClass105_2312 = null;
		aClass16_2306 = null;
		if (i != -1399) {
			systemUpdateTime = -29;
		}
		aClass16_2313 = null;
		aBooleanArray2317 = null;
		aClass16_2308 = null;
	}

	public final void unlink_queue() {
		if (queue_prev != null) {
			queue_prev.queue_next = queue_next;
			queue_next.queue_prev = queue_prev;
			queue_next = null;
			queue_prev = null;
		}
	}

	static final void method602(int i) {
		SpotType.gfxMap.clear();
		UpdateServerNode.aModelList_2325.clearModelCache((byte) 124);
	}


	static {
		aClass16_2308 = RSString.createString("start your journey off by clicking");
		systemUpdateTime = 0;
		aClass16_2306 = aClass16_2308;
		aBooleanArray2317 = new boolean[8];
	}
}
