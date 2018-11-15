package com.jagex;
/* Class107 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import com.jagex.graphics.runetek4.software.sprite.SoftwarePaletteSprite;

public class Class107
{
	public static RSString aClass16_1832 = RSString.createString("wave2:");
	static RSString aClass16_1834 = aClass16_1832;
	static RSString aClass16_1835;
	public static RSString aClass16_1837;
	static RSString aClass16_1839 = aClass16_1832;
	static boolean aBoolean1841;
	
	static final boolean method1580(int i, int i_0_) {
		if (i_0_ != -26304) {
			method1583((byte) 30);
		}
		if ((0x1 & i >> 30) == 0) {
			return false;
		}
		return true;
	}
	
	static final void method1581(int i, int i_1_, byte b, int i_2_, int i_3_, int i_4_, int i_5_) {
		if (VarpDefinition.anInt3728 <= i_2_ && (i_1_ ^ 0xffffffff) >= (Class35.anInt554 ^ 0xffffffff) && (i_4_ ^ 0xffffffff) <= (Class88.anInt1503 ^ 0xffffffff) && StaticMethods.anInt3435 >= i_3_) {
			if (i_5_ == 1) {
				AbstractMouseWheel.method1237(i_2_, i_4_, i_3_, i_1_, i, 8622);
			} else {
				CS2CallFrame.method1156(i_3_, i_5_, i_1_, i_2_, 28, i, i_4_);
			}
		} else if (i_5_ == 1) {
			Class91.method1455((byte) -51, i, i_3_, i_4_, i_2_, i_1_);
		} else {
			UpdateServerNode.method864(i_2_, i, 117, i_4_, i_5_, i_3_, i_1_);
		}
		if (b != -40) {
			aClass16_1839 = null;
		}
	}
	
	static final SoftwarePaletteSprite[] method1582(String class16, Js5 class105, byte b, String class16_6_) {
		int i = class105.get_groupid(class16);
		int i_7_ = 0;
		return Class71_Sub1_Sub1.method1273(i, class105, true, i_7_);
	}
	
	public static void method1583(byte b) {
		aClass16_1834 = null;
		aClass16_1835 = null;
		aClass16_1837 = null;
		aClass16_1832 = null;
		if (b != -126) {
			method1580(-113, 87);
		}
		aClass16_1839 = null;
	}
	
	static {
		aClass16_1837 = RSString.createString(" is already on your friend list)3");
		aBoolean1841 = true;
		aClass16_1835 = aClass16_1837;
	}
}
