package com.jagex;
/* Class23_Sub10_Sub4 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class23_Sub10_Sub4 extends Class23_Sub10
{
	public NodeDeque aClass89_3661 = new NodeDeque();
	public NodeDeque aClass89_3662 = new NodeDeque();
	public int anInt3663 = -1;
	public int anInt3664 = 0;
	
	public final void method589(int[] is, int i, int i_0_) {
		for (Class23_Sub10 class23_sub10 = (Class23_Sub10) aClass89_3661.get_first(); class23_sub10 != null; class23_sub10 = (Class23_Sub10) aClass89_3661.get_next())
			class23_sub10.method504(is, i, i_0_);
	}
	
	final synchronized void method590(Class23_Sub10 class23_sub10) {
		aClass89_3661.add_first(class23_sub10, -1);
	}
	
	public final void method591(Class23_Sub22 class23_sub22) {
		class23_sub22.unlink();
		class23_sub22.method893();
		Linkable parser = aClass89_3662.head.next;
		if (parser == aClass89_3662.head) {
			anInt3663 = -1;
		} else {
			anInt3663 = ((Class23_Sub22) parser).anInt2416;
		}
	}
	
	@Override
	final int method501() {
		return 0;
	}
	
	@Override
	final synchronized void generate_samples(int[] is, int i, int i_1_) {
		do {
			if (anInt3663 < 0) {
				method589(is, i, i_1_);
				break;
			}
			if (anInt3664 + i_1_ < anInt3663) {
				anInt3664 += i_1_;
				method589(is, i, i_1_);
				break;
			}
			int i_2_ = anInt3663 - anInt3664;
			method589(is, i, i_2_);
			i += i_2_;
			i_1_ -= i_2_;
			anInt3664 += i_2_;
			method592();
			Class23_Sub22 class23_sub22 = (Class23_Sub22) aClass89_3662.get_first();
			synchronized (class23_sub22) {
				int i_3_ = class23_sub22.method894(this);
				if (i_3_ < 0) {
					class23_sub22.anInt2416 = 0;
					method591(class23_sub22);
				} else {
					class23_sub22.anInt2416 = i_3_;
					method594(class23_sub22.next, class23_sub22);
				}
			}
		} while (i_1_ != 0);
	}
	
	@Override
	final Class23_Sub10 method503() {
		return (Class23_Sub10) aClass89_3661.get_first();
	}
	
	public final void method592() {
		if (anInt3664 > 0) {
			for (Class23_Sub22 class23_sub22 = (Class23_Sub22) aClass89_3662.get_first(); class23_sub22 != null; class23_sub22 = (Class23_Sub22) aClass89_3662.get_next())
				class23_sub22.anInt2416 -= anInt3664;
			anInt3663 -= anInt3664;
			anInt3664 = 0;
		}
	}
	
	public final void method593(int i) {
		for (Class23_Sub10 class23_sub10 = (Class23_Sub10) aClass89_3661.get_first(); class23_sub10 != null; class23_sub10 = (Class23_Sub10) aClass89_3661.get_next())
			class23_sub10.method507(i);
	}
	
	@Override
	final Class23_Sub10 method502() {
		return (Class23_Sub10) aClass89_3661.get_next();
	}
	
	@Override
	final synchronized void method507(int i) {
		do {
			if (anInt3663 < 0) {
				method593(i);
				break;
			}
			if (anInt3664 + i < anInt3663) {
				anInt3664 += i;
				method593(i);
				break;
			}
			int i_4_ = anInt3663 - anInt3664;
			method593(i_4_);
			i -= i_4_;
			anInt3664 += i_4_;
			method592();
			Class23_Sub22 class23_sub22 = (Class23_Sub22) aClass89_3662.get_first();
			synchronized (class23_sub22) {
				int i_5_ = class23_sub22.method894(this);
				if (i_5_ < 0) {
					class23_sub22.anInt2416 = 0;
					method591(class23_sub22);
				} else {
					class23_sub22.anInt2416 = i_5_;
					method594(class23_sub22.next, class23_sub22);
				}
			}
		} while (i != 0);
	}
	
	public final void method594(Linkable parser, Class23_Sub22 class23_sub22) {
		for (/**/; parser != aClass89_3662.head && ((Class23_Sub22) parser).anInt2416 <= class23_sub22.anInt2416; parser = parser.next) {
			/* empty */
		}
		aClass89_3662.method1441(parser, 0, class23_sub22);
		anInt3663 = ((Class23_Sub22) aClass89_3662.head.next).anInt2416;
	}
	
	final synchronized void method595(Class23_Sub10 class23_sub10) {
		class23_sub10.unlink();
	}
}
