package com.jagex;
/* Class38_Sub7_Sub1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import com.jagex.game.runetek4.clientoptions.ClientOptions;
import com.jagex.graphics.runetek4.media.Model;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.GLState;
import com.rs2.client.scene.Scene;

import me.waliedyassen.graphics.rasterizer.Rasterizer;

public class NPC extends Entity {
	public static int[][] anIntArrayArray4367;
	public static int anInt4368;
	public NPCType config;
	public static int anInt4374;
	public static int anInt4376;
	public static Packet[] aClass23_Sub5Array4377;

	@Override
	public void update_shadows(int var1, int var2, int var3, int var4, int var5) {

	}

	@Override
	public boolean is_ready() {
		if (config == null) {
			return false;
		}
		return true;
	}

	public static void method1091(int i) {
		MapRegion.UNDERSCORE = null;
		if (i != 2047) {
			method1093(-11);
		}
		anIntArrayArray4367 = null;
		aClass23_Sub5Array4377 = null;
	}

	static final boolean method1092(RSInterface class64, int i) {
		if (i == class64.content_type) {
			StaticMethods.anInt3400 = 250;
			return true;
		}
		return false;
	}

	@Override
	public final int get_miny() {
		return anInt2820;
	}

	static final void method1093(int i) {
		if (InputManager.mouse != null) {
			synchronized (InputManager.mouse) {
				InputManager.mouse = null;
			}
		}
		@SuppressWarnings("unused")
		int i_0_ = 73 / ((-54 - i) / 44);
	}

	static final Class23_Sub13_Sub12 getOtherQuickChat(int data, int i_1_) {
		Class23_Sub13_Sub12 class23_sub13_sub12 = (Class23_Sub13_Sub12) StaticMethods.aJList_2972.get(data);
		if (class23_sub13_sub12 != null) {
			return class23_sub13_sub12;
		}
		if (i_1_ != 1) {
			return null;
		}
		byte[] bs;
		if (data >= 32768) {
			bs = FileSystem.aClass105_265.get_file(1, 0x7fff & data);
		} else {
			bs = StaticMethods.aClass105_3119.get_file(1, data);
		}
		class23_sub13_sub12 = new Class23_Sub13_Sub12();
		if (bs != null) {
			class23_sub13_sub12.method755(new Packet(bs), -28386);
		}
		StaticMethods.aJList_2972.put(data, class23_sub13_sub12);
		return class23_sub13_sub12;
	}

	@Override
	public final void draw2(int angle, int var2, int var3, int var4, int var5, int var6, int var7, int var8, long var9, int var11) {
		if (config != null) {
			SeqType performing_anim = (current_performing_seqid ^ 0xffffffff) != 0 && (anInt2828 ^ 0xffffffff) == -1 ? SeqTypeList.list(current_performing_seqid) : null;
			SeqType standing_anim = current_standing_animation == -1 || (standAnimation ^ 0xffffffff) == (current_standing_animation ^ 0xffffffff) && performing_anim != null ? null : SeqTypeList.list(current_standing_animation);
			Model var15 = config.method1476(worn_objs_animations, standing_anim, performing_anim, current_standing_frameid, current_performing_frameid, next_standing_frameid, next_performing_frameid, current_standing_tick, current_performing_tick);
			if (var15 != null) {
				anInt2820 = var15.get_miny();
				if (ClientOptions.clientoption_spotshadows && config.spotshadowed) {
					Model shadow_model = SpotShadows.get_spotshadow_model(config.aByte1287, aBoolean2810, null == standing_anim ? performing_anim : standing_anim, bound_extents_x, config.aShort1256, bound_extents_z, config.aShort1286, config.size, var15, angle, null != standing_anim ? current_standing_frameid : current_performing_frameid, render_y, config.aByte1275, (byte) -49);
					if (GLManager.opengl_mode) {
						float var18 = GLState.get_depth_range_offset();
						float var19 = GLState.get_depth_range_scale();
						GLState.disable_depthmask();
						GLState.update_depth_range(var18, -150.0F + var19);
						shadow_model.draw2(0, var2, var3, var4, var5, var6, var7, var8, -1L, var11);
						GLState.enable_depthmask();
						GLState.update_depth_range(var18, var19);
					} else {
						shadow_model.draw2(0, var2, var3, var4, var5, var6, var7, var8, -1L, var11);
					}
				}

				int i_10_ = 0;
				int i_11_ = 0;
				int i_12_ = 0;
				if ((config.aShort4183 ^ 0xffffffff) != -1 && config.aShort4145 != 0) {
					int i_13_ = Rasterizer.SINE[angle];
					int i_14_ = Rasterizer.COSINE[angle];
					int i_15_ = config.aShort4183;
					int i_16_ = config.aShort4145;
					int i_17_ = -i_15_ / 2;
					int i_18_ = -i_16_ / 2;
					int i_19_ = -(i_13_ * i_17_) + i_18_ * i_14_ >> 16;
					int i_20_ = i_15_ / 2;
					int i_21_ = -i_16_ / 2;
					int i_22_ = i_14_ * i_20_ + i_13_ * i_21_ >> 16;
					int i_23_ = i_16_ / 2;
					int i_24_ = i_13_ * i_18_ - -(i_17_ * i_14_) >> 16;
					int i_25_ = i_21_ * i_14_ - i_20_ * i_13_ >> 16;
					int i_26_ = Scene.get_average_height(ObjType.localHeight, i_24_ + bound_extents_x, bound_extents_z - -i_19_);
					int i_27_ = Scene.get_average_height(ObjType.localHeight, bound_extents_x + i_22_, i_25_ + bound_extents_z);
					int i_28_ = -i_15_ / 2;
					int i_29_ = i_13_ * i_23_ + i_14_ * i_28_ >> 16;
					int i_30_ = i_14_ * i_23_ - i_13_ * i_28_ >> 16;
					int i_31_ = Scene.get_average_height(ObjType.localHeight, i_29_ + bound_extents_x, bound_extents_z + i_30_);
					int i_32_ = i_15_ / 2;
					int i_33_ = i_16_ / 2;
					int i_34_ = i_13_ * i_33_ + i_14_ * i_32_ >> 16;
					int i_35_ = i_33_ * i_14_ - i_13_ * i_32_ >> 16;
					int i_36_ = (i_26_ ^ 0xffffffff) <= (i_31_ ^ 0xffffffff) ? i_31_ : i_26_;
					int i_37_ = i_27_ > i_26_ ? i_26_ : i_27_;
					int i_38_ = Scene.get_average_height(ObjType.localHeight, i_34_ + bound_extents_x, i_35_ + bound_extents_z);
					int i_39_ = i_38_ <= i_27_ ? i_38_ : i_27_;
					int i_40_ = i_31_ < i_38_ ? i_31_ : i_38_;
					i_11_ = 0x7ff & (int) (325.95 * Math.atan2(-i_40_ + i_37_, i_16_));
					i_12_ = i_38_ + i_26_;
					if ((i_11_ ^ 0xffffffff) != -1) {
						var15.xaxis_rotate_without_normals(i_11_);
					}
					i_10_ = 0x7ff & (int) (325.95 * Math.atan2(-i_39_ + i_36_, i_15_));
					if (i_10_ != 0) {
						var15.zaxis_rotate_without_normals(i_10_);
					}
					if (i_27_ - -i_31_ < i_12_) {
						i_12_ = i_27_ + i_31_;
					}
					i_12_ = (i_12_ >> 1) + -render_y;
					if ((i_12_ ^ 0xffffffff) != -1) {
						var15.translate(0, i_12_, 0);
					}
				}
				Model var17 = null;
				if ((current_spotanimid ^ 0xffffffff) != 0 && (current_spotanim_frameid ^ 0xffffffff) != 0) {
					SpotType class23_sub13_sub17 = SpotType.list(current_spotanimid);
					var17 = class23_sub13_sub17.get_model(current_spotanim_frameid, next_spotanim_frameid, current_spotanim_tick);
					if (var17 != null) {
						var17.translate(0, -anInt2647, 0);
						if (class23_sub13_sub17.aBoolean4054) {
							if (i_11_ != 0) {
								var17.xaxis_rotate_without_normals(i_11_);
							}
							if ((i_10_ ^ 0xffffffff) != -1) {
								var17.zaxis_rotate_without_normals(i_10_);
							}
							if ((i_12_ ^ 0xffffffff) != -1) {
								var17.translate(0, i_12_, 0);
							}
						}
					}
				}
				if (!GLManager.opengl_mode) {
					if (null != var17) {
						var15 = ((SoftwareModel) var15).method1024(var17);
					}
					if (config.size == 1) {
						var15.renders_in_one_tile = true;
					}
					var15.draw2(angle, var2, var3, var4, var5, var6, var7, var8, var9, var11);
				} else {
					if (-2 == ~config.size) {
						var15.renders_in_one_tile = true;
					}
					var15.draw2(angle, var2, var3, var4, var5, var6, var7, var8, var9, var11);
					if (var17 != null) {
						if (-2 == ~config.size) {
							var17.renders_in_one_tile = true;
						}
						var17.draw2(angle, var2, var3, var4, var5, var6, var7, var8, var9, var11);
					}
				}
			}
		}
	}

	public static void add_worn_obj_anim(NPC npc, int[] enabled, int[] seqids, int[] durations) {
		int var5 = 0;
		while (var5 < seqids.length) {
			int var6 = seqids[var5];
			int var7 = enabled[var5];
			int var8 = durations[var5];

			for (int var9 = 0; -1 != ~var7 && ~npc.worn_objs_animations.length < ~var9; ++var9) {
				if ((1 & var7) != 0) {
					if (~var6 == 0) {
						npc.worn_objs_animations[var9] = null;
					} else {
						SeqType var10 = SeqTypeList.list(var6);
						WornObjAnim var12 = npc.worn_objs_animations[var9];
						int var11 = var10.loop_type;
						if (null != var12) {
							if (~var6 != ~var12.seqid) {
								if (~var10.priority <= ~SeqTypeList.list(var12.seqid).priority) {
									var12 = npc.worn_objs_animations[var9] = null;
								}
							} else if (~var11 == -1) {
								var12 = npc.worn_objs_animations[var9] = null;
							} else if (-2 == ~var11) {
								var12.frameid = 0;
								var12.replaysdone = 0;
								var12.next_frameid = 1;
								var12.timer = 0;
								var12.duration = var8;
								Class21.playAnimationSound(npc, npc.bound_extents_z, npc.bound_extents_x, var10, 0);
							} else if (~var11 == -3) {
								var12.replaysdone = 0;
							}
						}

						if (null == var12) {
							var12 = npc.worn_objs_animations[var9] = new WornObjAnim();
							var12.next_frameid = 1;
							var12.timer = 0;
							var12.duration = var8;
							var12.seqid = var6;
							var12.replaysdone = 0;
							var12.frameid = 0;
							Class21.playAnimationSound(npc, npc.bound_extents_z, npc.bound_extents_x, var10, 0);
						}
					}
				}

				var7 >>>= 1;
			}

			++var5;
		}
	}

	static {
		anIntArrayArray4367 = new int[104][104];
		anInt4368 = 0;
		anInt4374 = 0;
		anInt4376 = 1;
		aClass23_Sub5Array4377 = new Packet[2048];
	}
}
