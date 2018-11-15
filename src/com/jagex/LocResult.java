package com.jagex;
/* ObjectInstance - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import com.jagex.graphics.runetek4.software.sprite.SoftwarePaletteSprite;

public class LocResult extends Queuable {
	public static int[] anIntArray3719 = new int[128];
	public static int anInt3720;
	public static int anInt3722 = 0;
	public SceneNode node;
	public SoftwarePaletteSprite shadow;
	public static int anInt3724;

	static final void method625(int i, int i_0_) {
		if (i == -1066663896) {
			synchronized (InputManager.mouse) {
				InteractiveEntity.idleMouseTicks = i_0_;
			}
		}
	}

	public static void method626(int i) {
		anIntArray3719 = null;
	}
}
