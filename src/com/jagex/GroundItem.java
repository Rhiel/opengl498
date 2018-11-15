package com.jagex;

import com.jagex.graphics.runetek4.media.Model;

public class GroundItem extends SceneNode {
	static boolean loginscreen_loaded;
	public int anInt2494 = -32768;
	public int itemId;
	static short[][] aShortArrayArray2497 = { { 6554, 115, 10304, 28, 5702, 7756, 5681, 4510, -31835, 22437, 2859, -11339, 16, 5157, 10446, 3658, -27314, -21965, 472, 580, 784, 21966, 28950, -15697, -14002 }, { 9104, 10275, 7595, 3610, 7975, 8526, 918, -26734, 24466, 10145, -6882, 5027, 1457, 16565, -30545, 25486, 24, 5392, 10429, 3673, -27335, -21957, 192, 687, 412, 21821, 28835, -15460, -14019 }, new short[0], new short[0], new short[0] };
	public static int anInt2498;
	public int amount;
	public static int anInt2503 = 0;

	@Override
	public final int get_miny() {
		return anInt2494;
	}

	public static final void method1034(int offset, RSString[] names, int length, short[] ses) {
		if ((length ^ 0xffffffff) < (offset ^ 0xffffffff)) {
			int i_1_ = (length + offset) / 2;
			int i_2_ = offset;
			RSString name = names[i_1_];
			names[i_1_] = names[length];
			names[length] = name;
			short s = ses[i_1_];
			ses[i_1_] = ses[length];
			ses[length] = s;
			for (int i = offset; i < length; i++) {
				if (name == null || names[i] != null && (names[i].method151(name) ^ 0xffffffff) > (i & 0x1 ^ 0xffffffff)) {
					RSString class16_4_ = names[i];
					names[i] = names[i_2_];
					names[i_2_] = class16_4_;
					short s_5_ = ses[i];
					ses[i] = ses[i_2_];
					ses[i_2_++] = s_5_;
				}
			}
			names[length] = names[i_2_];
			names[i_2_] = name;
			ses[length] = ses[i_2_];
			ses[i_2_] = s;
			method1034(offset, names, -1 + i_2_, ses);
			method1034(1 + i_2_, names, length, ses);
		}
	}

	@Override
	public void update_shadows(int var1, int var2, int var3, int var4, int var5) {
	}

	@Override
	public final void draw2(int i, int i_6_, int i_7_, int i_8_, int i_9_, int i_10_, int i_11_, int i_12_, long l, int bufferOffset) {
		Model class38_sub1 = ObjTypeList.list(itemId).get_dialog_model(null, 0, -1, 0, amount);
		if (class38_sub1 != null) {
			class38_sub1.draw2(i, i_6_, i_7_, i_8_, i_9_, i_10_, i_11_, i_12_, l, bufferOffset);
			anInt2494 = class38_sub1.get_miny();
		}
	}

	public static void method1035(int i) {
		ContextMenu.menuActionCmd2 = null;
		aShortArrayArray2497 = null;
	}


	GroundItem() {
		/* empty */
	}
}
