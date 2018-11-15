package com.jagex;
/* Class71_Sub3 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import com.rs2.client.scene.Scene;

public class Class71_Sub3 extends Class71
{
	public static int soundStoreCount = 0;
	public static int anInt2742 = 0;
	public static Js5 aClass105_2745;

	static final void method1287(int i) {
		Scene.visible_level_start = i;
		for (int i_0_ = 0; i_0_ < Scene.width; i_0_++) {
			for (int i_1_ = 0; i_1_ < Scene.height; i_1_++) {
				if (com.rs2.client.scene.Scene.current_grounds[i][i_0_][i_1_] == null) {
					com.rs2.client.scene.Scene.current_grounds[i][i_0_][i_1_] = new Ground(i, i_0_, i_1_);
				}
			}
		}
	}
	
	static final int method1288(int i, int i_2_, int i_3_) {
		if (i > -121) {
			return 20;
		}
		int i_4_ = i_3_ + i_2_ * 57;
		i_4_ ^= i_4_ << 13;
		int i_5_ = 1376312589 + (15731 * (i_4_ * i_4_) + 789221) * i_4_ & 0x7fffffff;
		return i_5_ >> 19 & 0xff;
	}
	
	public static void method1289(int i) {
		if (i == -789436973) {
			aClass105_2745 = null;
			GameShell.setCanvas(null);
		}
	}
	
	static {
	}
}
