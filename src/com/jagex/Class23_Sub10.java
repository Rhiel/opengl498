package com.jagex;
/* Class23_Sub10 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

abstract class Class23_Sub10 extends Linkable
{
	public int anInt2275;
	volatile boolean aBoolean2276 = true;
	public Class23_Sub10 aClass23_Sub10_2277;
	public AbstractSound aClass23_Sub6_2278;
	
	abstract int method501();
	
	abstract Class23_Sub10 method502();
	
	abstract Class23_Sub10 method503();
	
	final void method504(int[] is, int i, int i_0_) {
		if (aBoolean2276) {
			generate_samples(is, i, i_0_);
		} else {
			method507(i_0_);
		}
	}
	
	int method505() {
		return 255;
	}
	
	abstract void generate_samples(int[] is, int i, int i_1_);
	
	abstract void method507(int i);
	
	public Class23_Sub10() {
		/* empty */
	}
}
