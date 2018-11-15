package com.jagex;
/* Class98 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class98 implements Runnable
{
	static RSString aClass16_1649 = RSString.createString("welle:");
	public static int anInt1650;
	static RSString aClass16_1652;
	public Object anObject1653;
	static MemoryCache aJList_1654;
	public static RSString[] aClass16Array1655 = new RSString[500];
	public boolean aBoolean1656 = true;
	public int[] anIntArray1658;
	public int anInt1659;
	public int[] anIntArray1661;
	
	static final int method1492(byte b, int i) {
		i = (~0x2aaaaaaa & i >>> 1) + (0x55555555 & i);
		i = (i >>> 2 & 0x33333333) + (i & 0x33333333);
		i = 0xf0f0f0f & (i >>> 4) + i;
		i += i >>> 8;
		i += i >>> 16;
		return 0xff & i;
	}
	
	@Override
	public final void run() {
		while (aBoolean1656) {
			synchronized (anObject1653) {
				if (anInt1659 < 500) {
					anIntArray1658[anInt1659] = Mouse.mouseX;
					anIntArray1661[anInt1659] = Mouse.mouseY;
					anInt1659++;
				}
			}
			TimeTools.sleep(50L);
		}
	}
	
	public static void method1493(int i) {
		aJList_1654 = null;
		if (i <= -85) {
			aClass16Array1655 = null;
			aClass16_1649 = null;
			aClass16_1652 = null;
		}
	}
	
	public Class98() {
		anObject1653 = new Object();
		anInt1659 = 0;
		anIntArray1658 = new int[500];
		anIntArray1661 = new int[500];
	}
	
	static {
		aClass16_1652 = RSString.createString("");
		aJList_1654 = new MemoryCache(20);
	}
}
