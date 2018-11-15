package com.jagex;
/* Class41 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class41
{
	public int anInt633;
	public int anInt634 = 2;
	public int[] anIntArray635 = new int[2];
	public int anInt636;
	public int[] anIntArray637 = new int[2];
	public int anInt638;
	public int anInt639;
	public int anInt640;
	public int anInt641;
	public int anInt642;
	public int anInt643;
	
	final void method1109(Packet class23_sub5) {
		anInt634 = class23_sub5.g1();
		anIntArray635 = new int[anInt634];
		anIntArray637 = new int[anInt634];
		for (int i = 0; i < anInt634; i++) {
			anIntArray635[i] = class23_sub5.g2();
			anIntArray637[i] = class23_sub5.g2();
		}
	}
	
	final void method1110() {
		anInt639 = 0;
		anInt643 = 0;
		anInt642 = 0;
		anInt640 = 0;
		anInt641 = 0;
	}
	
	final void method1111(Packet class23_sub5) {
		anInt638 = class23_sub5.g1();
		anInt636 = class23_sub5.g4();
		anInt633 = class23_sub5.g4();
		method1109(class23_sub5);
	}
	
	final int method1112(int i) {
		if (anInt641 >= anInt639) {
			anInt640 = anIntArray637[anInt643++] << 15;
			if (anInt643 >= anInt634) {
				anInt643 = anInt634 - 1;
			}
			anInt639 = (int) (anIntArray635[anInt643] / 65536.0 * i);
			if (anInt639 > anInt641) {
				anInt642 = ((anIntArray637[anInt643] << 15) - anInt640) / (anInt639 - anInt641);
			}
		}
		anInt640 += anInt642;
		anInt641++;
		return anInt640 - anInt642 >> 15;
	}
	
	public Class41() {
		anIntArray635[0] = 0;
		anIntArray635[1] = 65535;
		anIntArray637[0] = 0;
		anIntArray637[1] = 65535;
	}
}
