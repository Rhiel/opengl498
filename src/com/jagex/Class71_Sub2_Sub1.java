package com.jagex;
/* Class71_Sub2_Sub1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class71_Sub2_Sub1 extends Class71_Sub2
{
	static RSString aClass16_4469 = RSString.createString("Sie haben gerade eine andere Welt verlassen)3");
	static NodeDeque aClass89_4470 = new NodeDeque();
	public static int[] anIntArray4473;
	public static int anInt4475;
	
	static final void method1281(byte b, int i, int i_0_, int i_1_, int i_2_, int i_3_, int i_4_, int[] is, int i_5_, int i_6_, byte[] bs) {
		i_3_ = ((0xff00ff & i_3_) * i & ~0xff00ff) + (i * (i_3_ & 0xff00) & 0xff0000) >> 8;
		i = -i + 256;
		int i_7_ = -i_5_;
		for (/**/; i_7_ < 0; i_7_++) {
			for (int i_8_ = -i_2_; i_8_ < 0; i_8_++) {
				if (bs[i_4_++] == 0) {
					i_1_++;
				} else {
					int i_9_ = is[i_1_];
					is[i_1_++] = (MathUtils.bitAnd(16711680, i * MathUtils.bitAnd(i_9_, 65280)) + MathUtils.bitAnd(i * MathUtils.bitAnd(16711935, i_9_), -16711936) >> 8) - -i_3_;
				}
			}
			i_4_ += i_6_;
			i_1_ += i_0_;
		}
	}
	
	static final void method1282(int offset, RSInterface[] class64s, int i_10_) {
		for (int i_11_ = offset; (class64s.length ^ 0xffffffff) < (i_11_ ^ 0xffffffff); i_11_++) {
			RSInterface class64 = class64s[i_11_];
			if (class64 != null) {
				if ((class64.type ^ 0xffffffff) == -1) {
					if (class64.dynamic_components != null) {
						method1282(0, class64.dynamic_components, i_10_);
					}
					InterfaceNode class23_sub25 = (InterfaceNode) Class36.anOa565.get(class64.uid);
					if (class23_sub25 != null) {
						StaticMethods2.method757(class23_sub25.interfaceId, i_10_, false);
					}
				}
				if (i_10_ == 0 && class64.attachment_update_handler != null) {
					CS2Event class23_sub9 = new CS2Event();
					class23_sub9.component = class64;
					class23_sub9.scriptArguments = class64.attachment_update_handler;
					Class91.parseCS2Script(class23_sub9, (byte) -95);
				}
				if (i_10_ == 1 && class64.anObjectArray1014 != null) {
					if (class64.componentIndex >= 0) {
						RSInterface class64_12_ = RSInterface.getInterface(class64.uid);
						if (class64_12_ == null || class64_12_.dynamic_components == null || (class64.componentIndex ^ 0xffffffff) <= (class64_12_.dynamic_components.length ^ 0xffffffff) || class64_12_.dynamic_components[class64.componentIndex] != class64) {
							continue;
						}
					}
					CS2Event class23_sub9 = new CS2Event();
					class23_sub9.scriptArguments = class64.anObjectArray1014;
					class23_sub9.component = class64;
					Class91.parseCS2Script(class23_sub9, (byte) -91);
				}
			}
		}
	}
	
	static final void method1283(RSInterface[] interfaces, int i, int i_13_) {
		if (i_13_ == -22561) {
			for (RSInterface interface1 : interfaces) {
				RSInterface var4 = interface1;
				if (var4 != null && (i ^ 0xffffffff) == (var4.parentId ^ 0xffffffff) && (!var4.newer_interface || !HintIcon.isHidden(true, var4))) {
					if (var4.type == 0) {
						if (!var4.newer_interface && HintIcon.isHidden(true, var4) && Class42.aClass64_663 != var4) {
							continue;
						}
						method1283(interfaces, var4.uid, -22561);
						if (var4.dynamic_components != null) {
							method1283(var4.dynamic_components, var4.uid, i_13_);
						}
						InterfaceNode class23_sub25 = (InterfaceNode) Class36.anOa565.get(var4.uid);
						if (class23_sub25 != null) {
							Class87_Sub4.method1427(class23_sub25.interfaceId, false);
						}
					}
					if (var4.type == 6) {
						if (var4.media_animid != -1 || var4.anInt198 != -1) {
							boolean bool = RSInterface.isComponentEnabled(true, var4);
							int i_15_;
							if (!bool) {
								i_15_ = var4.media_animid;
							} else {
								i_15_ = var4.anInt198;
							}
							if (i_15_ != -1) {
								SeqType var7 = SeqTypeList.list(i_15_);
								if (var7 != null) {
									var4.media_tween_tick += InterfaceNode.anInt2459;
									while (var7.frames_durations[var4.media_current_frameid] < var4.media_tween_tick) {
										var4.media_tween_tick -= var7.frames_durations[var4.media_current_frameid];
										var4.media_current_frameid++;
										if (var4.media_current_frameid >= var7.frames_data.length) {
											var4.media_current_frameid -= var7.replay_interval;
											if ((var4.media_current_frameid ^ 0xffffffff) > -1 || var7.frames_data.length <= var4.media_current_frameid) {
												var4.media_current_frameid = 0;
											}
										}
										var4.media_next_frameid = var4.media_current_frameid + 1;
										if (var7.frames_data.length <= var4.media_next_frameid) {
											var4.media_next_frameid -= var7.replay_interval;
											if (~var4.media_next_frameid > -1 || var7.frames_data.length <= var4.media_next_frameid) {
												var4.media_next_frameid = -1;
											}
										}
										RSInterfaceList.setDirty(var4);
									}
								}
							}
						}
						if (var4.anInt1090 != 0 && !var4.newer_interface) {
							int i_16_ = var4.anInt1090 >> 16;
							i_16_ *= InterfaceNode.anInt2459;
							var4.media_xangle = i_16_ + var4.media_xangle & 0x7ff;
							int i_17_ = var4.anInt1090 << 16 >> 16;
							i_17_ *= InterfaceNode.anInt2459;
							var4.media_yangle = i_17_ + var4.media_yangle & 0x7ff;
							RSInterfaceList.setDirty(var4);
						}
					}
				}
			}
		}
	}
	
	static final void method1284(int i, int i_18_) {
		Class91.anInt1556 = i / i_18_;
	}
	
	public static void method1286(byte b) {
		anIntArray4473 = null;
		aClass16_4469 = null;
		if (b != 62) {
			method1282(-52, null, -118);
		}
		aClass89_4470 = null;
	}
}
