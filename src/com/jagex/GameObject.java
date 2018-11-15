package com.jagex;
/* GameObject - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import com.jagex.game.runetek4.clientoptions.ClientOptions;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.software.sprite.SoftwarePaletteSprite;
import com.rs2.client.scene.Scene;
import com.rs2.client.scene.shadow.SceneShadowMapper;

public class GameObject extends SceneNode {
	public int level;
	public int x;
	public int rotation;
	public int locid;
	public int type;
	public int anInt2512;
	public int last_update_time;
	public int anInt2514 = -32768;
	public int y;
	public SeqType seqtype;
	public int anInt2746;
	public int anInt2733;
	public SoftwarePaletteSprite aClass109_Sub1_2738;
	public int anInt2725 = 0;
	public int anInt2720 = 0;
	public int anInt2748 = 0;
	public int anInt2750 = -1;
	public int anInt2752 = -1;
	public boolean has_morphism;
	public boolean aBoolean2728;

	public GameObject(int level, int x, int y, int locid, int type, int rotation, int seqid, boolean bool, SceneNode node) {
		this.level = level;
		this.x = x;
		this.y = y;
		this.locid = locid;
		this.type = type;
		this.rotation = rotation;
		LocType loctype;
		if (GLManager.opengl_mode && null != node) {
			if (node instanceof GameObject) {
				((GameObject) node).reset_shadows(-1);
			} else {
				loctype = LocTypeList.list(locid);
				if (loctype.morphisms != null) {
					loctype = loctype.morph();
				}
				if (null != loctype) {
					SceneShadowMapper.method840(loctype, 0, rotation, 0, type, x, y, level);
				}
			}
		}
		if (-1 != seqid) {
			seqtype = SeqTypeList.list(seqid);
			anInt2512 = 0;
			if (1 >= seqtype.frames_data.length) {
				anInt2733 = 0;
			} else {
				anInt2733 = 1;
			}

			anInt2746 = 1;
			last_update_time = -1 + GameClient.timer;
			if (-1 == ~seqtype.loop_type && null != node && node instanceof GameObject) {
				GameObject var12 = (GameObject) node;
				if (seqtype == var12.seqtype) {
					anInt2512 = var12.anInt2512;
					last_update_time = var12.last_update_time;
					anInt2746 = var12.anInt2746;
					anInt2733 = var12.anInt2733;
					return;
				}
			}

			if (bool && seqtype.replay_interval != -1) {
				anInt2512 = (int) (Math.random() * seqtype.frames_data.length);
				anInt2733 = anInt2512 - -1;
				if (~anInt2733 <= ~seqtype.frames_data.length) {
					anInt2733 -= seqtype.replay_interval;
					if (anInt2733 < 0 || ~anInt2733 <= ~seqtype.frames_data.length) {
						anInt2733 = -1;
					}
				}
				anInt2746 = 1 - -((int) (Math.random() * seqtype.frames_durations[anInt2512]));
				last_update_time = -anInt2746 + GameClient.timer;
			}
		}
		if (GLManager.opengl_mode && node != null) {
			process_node_cycle(true, -2);
		}
		if (node == null) {
			loctype = LocTypeList.list(locid);
			if (null != loctype.morphisms) {
				has_morphism = true;
			}
		}
	}

	@Override
	public void draw2(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, long var9, int var11) {
		SceneNode node = get_rendering_node();
		if (null != node) {
			node.draw2(var1, var2, var3, var4, var5, var6, var7, var8, var9, var11);
		}
	}

	@Override
	public void update_shadows(int var1, int var2, int var3, int var4, int var5) {
		if (GLManager.opengl_mode) {
			process_node_cycle(true, -2);
		} else {
			process_animation_cycle(var5, var4);
		}
	}

	public void process_animation_cycle(int var1, int var2) {
		if (seqtype != null) {
			int current_frame = GameClient.timer - last_update_time;
			if (-101 > ~current_frame && seqtype.replay_interval > 0) {
				int var5;
				for (var5 = seqtype.frames_data.length - seqtype.replay_interval; ~anInt2512 > ~var5 && ~seqtype.frames_durations[anInt2512] > ~current_frame; ++anInt2512) {
					current_frame -= seqtype.frames_durations[anInt2512];
				}

				if (~anInt2512 <= ~var5) {
					int var6 = 0;

					for (int var7 = var5; ~seqtype.frames_data.length < ~var7; ++var7) {
						var6 += seqtype.frames_durations[var7];
					}

					current_frame %= var6;
				}

				anInt2733 = 1 + anInt2512;
				if (anInt2733 >= seqtype.frames_data.length) {
					anInt2733 -= seqtype.replay_interval;
					if (~anInt2733 > -1 || seqtype.frames_data.length <= anInt2733) {
						anInt2733 = -1;
					}
				}
			}

			while (~current_frame < ~seqtype.frames_durations[anInt2512]) {
				// SocketStream.play_animation_sound(var1, seqtype, var2, false, anInt2512);
				current_frame -= seqtype.frames_durations[anInt2512];
				++anInt2512;
				if (~seqtype.frames_data.length >= ~anInt2512) {
					anInt2512 -= seqtype.replay_interval;
					if (0 > anInt2512 || ~seqtype.frames_data.length >= ~anInt2512) {
						seqtype = null;
						break;
					}
				}

				anInt2733 = anInt2512 - -1;
				if (~seqtype.frames_data.length >= ~anInt2733) {
					anInt2733 -= seqtype.replay_interval;
					if (~anInt2733 > -1 || ~anInt2733 <= ~seqtype.frames_data.length) {
						anInt2733 = -1;
					}
				}
			}

			anInt2746 = current_frame;
			last_update_time = -current_frame + GameClient.timer;
		}
	}

	public SceneNode get_rendering_node() {
		return process_node_cycle(false, 3 + -5);
	}

	public SceneNode process_node_cycle(boolean var1, int var2) {
		boolean var3 = Scene.surface_heightmap != Scene.current_heightmap;
		LocType var4 = LocTypeList.list(locid);
		int var5 = var4.seqid;
		if (null != var4.morphisms) {
			var4 = var4.morph();
		}

		if (null == var4) {
			if (GLManager.opengl_mode && !var3) {
				reset_shadows(-1);
			}
			return null;
		} else {
			int var6;
			if (has_morphism && (null == seqtype || null != seqtype && ~seqtype.id != ~var4.seqid)) {
				var6 = var4.seqid;
				if (0 == ~var4.seqid) {
					var6 = var5;
				}

				if (0 == ~var6) {
					seqtype = null;
				} else {
					seqtype = SeqTypeList.list(var6);
				}

				if (null != seqtype) {
					if (var4.aBoolean1492 && -1 != seqtype.replay_interval) {
						anInt2512 = (int) (Math.random() * seqtype.frames_data.length);
						last_update_time -= (int) (Math.random() * seqtype.frames_durations[anInt2512]);
					} else {
						anInt2512 = 0;
						last_update_time = GameClient.timer + -1;
					}
				}
			}

			var6 = rotation & 3;
			int var7;
			int var8;
			if (~var6 != var2 && -4 != ~var6) {
				var7 = var4.size2d;
				var8 = var4.size3d;
			} else {
				var8 = var4.size2d;
				var7 = var4.size3d;
			}

			int var10 = x - -(1 + var7 >> 1);
			int var9 = (var7 >> 1) + x;
			int var11 = (var8 >> 1) + y;
			int var12 = (var8 - -1 >> 1) + y;
			process_animation_cycle(128 * var11, var9 * 128);
			boolean var13 = !var3 && var4.shadowed && (var4.id != anInt2750 || (~anInt2512 != ~anInt2752 || seqtype != null && (seqtype.aBoolean1872 || SeqTypeList.force_tweening) && ~anInt2512 != ~anInt2733) && ~ClientOptions.clientoption_hardshadows <= -3);
			if (var1 && !var13) {
				return null;
			} else {
				int[][] var14 = Scene.current_heightmap[level];
				int var15 = var14[var10][var12] + var14[var9][var12] + var14[var9][var11] + var14[var10][var11] >> 2;
				int var16 = (var7 << 6) + (x << 7);
				int var17 = (var8 << 6) + (y << 7);
				int[][] var18 = null;
				if (!var3) {
					if (-4 < ~level) {
						var18 = Scene.current_heightmap[1 + level];
					}
				} else {
					var18 = Scene.surface_heightmap[0];
				}

				if (GLManager.opengl_mode && var13) {
					SceneShadowMapper.method2047(aClass109_Sub1_2738, anInt2725, anInt2720, anInt2748);
				}

				boolean var19 = null == aClass109_Sub1_2738;
				LocResult var20;
				if (seqtype != null) {
					var20 = var4.get_animated_pair(seqtype, var14, var17, var16, rotation, var18, var15, type, anInt2512, anInt2733, anInt2746, var2 ^ -8310, !var19 ? aClass109_Sub1_2738 : SceneShadowMapper.aClass109_Sub1_2631, var13);
				} else {
					var20 = var4.get_pair(var14, rotation, var16, type, var15, var18, false, var17, var19 ? SceneShadowMapper.aClass109_Sub1_2631 : aClass109_Sub1_2738, var13);
				}

				if (null == var20) {
					return null;
				} else {
					if (GLManager.opengl_mode && var13) {
						if (var19) {
							SceneShadowMapper.aClass109_Sub1_2631 = var20.shadow;
						}

						int var21 = 0;
						if (-1 != ~level) {
							int[][] var22 = Scene.current_heightmap[0];
							var21 = var15 - (var22[var10][var11] + var22[var9][var11] - (-var22[var9][var12] - var22[var10][var12]) >> 2);
						}

						SoftwarePaletteSprite var24 = var20.shadow;
						if (aBoolean2728 && SceneShadowMapper.method2049(var24, var16, var21, var17)) {
							aBoolean2728 = false;
						}

						if (!aBoolean2728) {
							SceneShadowMapper.method2051(var24, var16, var21, var17);
							aClass109_Sub1_2738 = var24;
							anInt2748 = var17;
							if (var19) {
								SceneShadowMapper.aClass109_Sub1_2631 = null;
							}

							anInt2720 = var21;
							anInt2725 = var16;
						}

						anInt2750 = var4.id;
						anInt2752 = anInt2512;
					}

					return var20.node;
				}
			}
		}
	}

	public void reset_shadows(int var1) {
		if (aClass109_Sub1_2738 != null) {
			SceneShadowMapper.method2047(aClass109_Sub1_2738, anInt2725, anInt2720, anInt2748);
		}
		anInt2750 = -1;
		anInt2752 = var1;
		aClass109_Sub1_2738 = null;
	}

	@Override
	public final int get_miny() {
		return anInt2514;
	}

}
