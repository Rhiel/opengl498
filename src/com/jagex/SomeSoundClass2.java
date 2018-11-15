package com.jagex;
/* SomeSoundClass2 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class SomeSoundClass2 extends AbstractSound
{
	public byte[] aByteArray3544;
	public boolean aBoolean3545;
	public int anInt3546;
	public int anInt3547;
	public int anInt3548;
	
	final SomeSoundClass2 method493(Class45 class45) {
		aByteArray3544 = class45.method1135(-1694746864, aByteArray3544);
		anInt3547 = class45.method1130(anInt3547, true);
		if (anInt3548 == anInt3546) {
			anInt3548 = anInt3546 = class45.method1134(anInt3548, 23596);
		} else {
			anInt3548 = class45.method1134(anInt3548, 23596);
			anInt3546 = class45.method1134(anInt3546, 23596);
			if (anInt3548 == anInt3546) {
				anInt3548--;
			}
		}
		return this;
	}
	
	SomeSoundClass2(int i, byte[] bs, int i_0_, int i_1_) {
		anInt3547 = i;
		aByteArray3544 = bs;
		anInt3548 = i_0_;
		anInt3546 = i_1_;
	}
	
	SomeSoundClass2(int i, byte[] bs, int i_2_, int i_3_, boolean bool) {
		anInt3547 = i;
		aByteArray3544 = bs;
		anInt3548 = i_2_;
		anInt3546 = i_3_;
		aBoolean3545 = bool;
	}
}
