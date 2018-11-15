package com.jagex;
/* Class22 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class22
{
	public int[] anIntArray343;
	public int[] anIntArray344;
	public int anInt345;
	public int anInt346;
	
	Class22() {
		Class23_Sub3.method250(16);
		anInt345 = Class23_Sub3.method252() != 0 ? Class23_Sub3.method250(4) + 1 : 1;
		if (Class23_Sub3.method252() != 0) {
			Class23_Sub3.method250(8);
		}
		Class23_Sub3.method250(2);
		if (anInt345 > 1) {
			anInt346 = Class23_Sub3.method250(4);
		}
		anIntArray344 = new int[anInt345];
		anIntArray343 = new int[anInt345];
		for (int i = 0; i < anInt345; i++) {
			Class23_Sub3.method250(8);
			anIntArray344[i] = Class23_Sub3.method250(8);
			anIntArray343[i] = Class23_Sub3.method250(8);
		}
	}
}
