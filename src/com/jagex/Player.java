package com.jagex;

import com.jagex.core.collections.memory.AdvancedMemoryCache;
import com.jagex.game.runetek4.clientoptions.ClientOptions;
import com.jagex.graphics.runetek4.media.Model;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.GLState;
import com.jagex.graphics.runetek4.opengl.model.OpenGLModel;
import com.rs2.client.scene.Scene;

import me.waliedyassen.graphics.rasterizer.Rasterizer;

public class Player extends Entity {

	public static boolean aBoolean3416 = false;
	public int anInt4379;
	public int anInt4380;
	public int combatLevel;
	public boolean aBoolean4384 = false;
	public short aShort4385 = 0;
	public int skillTotalLevel;
	public int anInt4387;
	public RSString username;
	public RSString title;
	public int anInt4390;
	public int anInt4391;
	public short aShort4392 = 0;
	public PlayerAppearance appearance;
	public int headIcon;
	public int anInt4395;
	public int anInt4396;
	public int currentTeamId;
	public int skullIcon;
	public Object aClass38_Sub1_4402;
	public int anInt4403;
	static RSString aClass16_4404 = RSString.createString("Diese Welt ist voll)3");
	static RSString aClass16_4406;
	static int anInt4410 = 0;
	public int anInt4411;
	int summoning;
	public RSString titleUsername;
	public static AdvancedMemoryCache modelsCache = new AdvancedMemoryCache(4);
	static int[] anIntArray4412;

	@Override
	public void update_shadows(int var1, int var2, int var3, int var4, int var5) {
	}

	public static final void setAnimation(int i, int delay, int animationId, Player player) {
		if (player.current_performing_seqid != animationId || animationId == -1) {
			if (animationId == -1 || player.current_performing_seqid == -1 || SeqTypeList.list(animationId).priority >= SeqTypeList.list(player.current_performing_seqid).priority) {
				player.current_performing_frameid = 0;
				player.anInt2640 = player.anInt2660;
				player.current_performing_tick = 0;
				player.next_performing_frameid = 1;
				player.anInt2828 = delay;
				player.current_performing_seqid = animationId;
				player.seq_replays_done = 0;
				if (player.current_performing_seqid != -1) {
					Class21.playAnimationSound(player, player.bound_extents_z, player.bound_extents_x, SeqTypeList.list(player.current_performing_seqid), player.current_performing_frameid);
				}
			}
		} else {
			SeqType class23_sub13_sub22 = SeqTypeList.list(animationId);
			int i_6_ = class23_sub13_sub22.loop_type;
			if (i_6_ == 1) {
				player.next_performing_frameid = 1;
				player.current_performing_frameid = 0;
				player.seq_replays_done = 0;
				player.anInt2828 = delay;
				player.current_performing_tick = 0;
				Class21.playAnimationSound(player, player.bound_extents_z, player.bound_extents_x, class23_sub13_sub22, player.current_performing_frameid);
			}
			if (i_6_ == 2) {
				player.seq_replays_done = 0;
			}
		}
	}

	@Override
	public boolean is_ready() {
		if (appearance == null) {
			return false;
		}
		return true;
	}

	static final AbstractMouseWheel method1097(int i) {
		try {
			return new MouseWheelHandler();
		} catch (Throwable throwable) {
			return null;
		}
	}

	public final void parseAppearance(int equipmentSize, Packet buffer) {
		buffer.index = 0;
		int settings = buffer.g1();
		int gender = settings & 0x1;
		// System.out.println(gender);
		if ((settings & 0x2) == 2) { // Changes the angle of the player
			aShort4392 = (short) (buffer.g1() << 2);
			aShort4385 = (short) (buffer.g1() << 2);
		} else {
			aShort4392 = (short) 0;
			aShort4385 = (short) 0;
		}
		boolean showSkillLevel = (0x4 & settings) != 0;
		size = 1 + (settings >> 3);
		skullIcon = buffer.g1s();
		headIcon = buffer.g1s();
		int[] looks = new int[equipmentSize];
		int npcId = -1;
		currentTeamId = 0;
		for (int j = 0; j < 12; j++) {
			int value = buffer.g1();
			if (value == 0) {
				looks[j] = 0;
			} else {
				int i_5_ = buffer.g1();
				int look = i_5_ + (value << 8);
				if (j == 0 && look == 65535) {
					npcId = buffer.g2();
					break;
				}
				if (look >= 32768) {// items
					look -= 32768;
					looks[j] = MathUtils.doBitwiseOr(look, 1073741824);
					int i_7_ = ObjTypeList.list(look).team;
					if ((i_7_ ^ 0xffffffff) != -1) {
						currentTeamId = i_7_;
					}
				} else {// identity
					looks[j] = MathUtils.doBitwiseOr(-2147483648, look + -256);
				}
			}
		}
		int[] colours = new int[5];
		for (int i_9_ = 0; i_9_ < 5; i_9_++) {
			int colour = buffer.g1();
			if ((colour ^ 0xffffffff) > -1 || (Class44.aShortArrayArray679[i_9_].length ^ 0xffffffff) >= (colour ^ 0xffffffff)) {
				colour = 0;
			}
			colours[i_9_] = colour;
		}
		standAnimation = buffer.g2();
		if (standAnimation == 65535) {
			standAnimation = -1;
		}
		turnAnimation = buffer.g2();
		if (turnAnimation == 65535) {
			turnAnimation = -1;
		}
		alsoTurn = turnAnimation;
		walkAnimation = buffer.g2();
		if ((walkAnimation ^ 0xffffffff) == -65536) {
			walkAnimation = -1;
		}
		turn180 = buffer.g2();
		if (turn180 == 65535) {
			turn180 = -1;
		}
		turn90cw = buffer.g2();
		if (turn90cw == 65535) {
			turn90cw = -1;
		}
		turn90ccw = buffer.g2();
		if (turn90ccw == 65535) {
			turn90ccw = -1;
		}
		runAnimation = buffer.g2();
		if (runAnimation == 65535) {
			runAnimation = -1;
		}
		username = WallObject.getStringFromLong(equipmentSize + -13, buffer.getLong()).method154();
		boolean title = buffer.g1() == 1;
		if (title) {
			this.title = WallObject.getStringFromLong(equipmentSize + -13, buffer.getLong()).method154();
			// RSString titleColor = WallObject.getStringFromLong(equipmentSize + -13, buffer.getLong(-98)).method154((byte) 59);
			// titleColor = RSString.create("<col=" + titleColor.toString() + ">");
			// this.title = TextureOperation32.joinRsStrings(new RSString[] {titleColor, this.title}, -74);
			this.title = RSString.create("<img=" + this.title.toString() + ">");
			titleUsername = RSString.create(this.title.toString() + username.toString());
		} else {
			this.title = null;
			titleUsername = null;
		}
		combatLevel = buffer.g1();
		summoning = buffer.g1();
		if (showSkillLevel) {
			skillTotalLevel = buffer.g2();
		} else {
			skillTotalLevel = 0;
		}
		if (appearance == null) {
			appearance = new PlayerAppearance();
		}
		appearance.setAppearanceData(gender == 1, colours, npcId, looks);
	}

	@Override
	public void draw2(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, long var9, int var11) {
		if (appearance != null) {
			SeqType performing_seq = (current_performing_seqid ^ 0xffffffff) != 0 && anInt2828 == 0 ? SeqTypeList.list(current_performing_seqid) : null;
			SeqType standing_seq = current_standing_animation == -1 || aBoolean4384 || (standAnimation ^ 0xffffffff) == (current_standing_animation ^ 0xffffffff) && performing_seq != null ? null : SeqTypeList.list(current_standing_animation);
			Model var15 = appearance.getAnimatedModel(worn_objs_animations, standing_seq, performing_seq, current_standing_frameid, current_performing_frameid, current_standing_tick, current_performing_tick, next_standing_frameid, next_performing_frameid, true);
			int var16 = PlayerAppearance.get_cache_size();
			if (GLManager.opengl_mode && GameShell.max_memory < 96 && ~var16 < -51) {
				Player.method90(1);
			}
			if (var15 != null) {
				anInt2820 = var15.get_miny();
				if (ClientOptions.clientoption_spotshadows && (-1 == appearance.transformNPCId || NPCType.getNPCDefinition(appearance.transformNPCId).spotshadowed)) {
					Model var23 = SpotShadows.get_spotshadow_model(160, aBoolean2810, standing_seq == null ? performing_seq : standing_seq, bound_extents_x, 0, bound_extents_z, 0, 1, var15, var1, null != standing_seq ? current_standing_frameid : current_performing_frameid, render_y, 240, (byte) -49);
					if (GLManager.opengl_mode) {
						float var18 = GLState.get_depth_range_offset();
						float var19 = GLState.get_depth_range_scale();
						GLState.disable_depthmask();
						GLState.update_depth_range(var18, -150.0F + var19);
						var23.draw2(0, var2, var3, var4, var5, var6, var7, var8, -1L, var11);
						GLState.enable_depthmask();
						GLState.update_depth_range(var18, var19);
					} else {
						var23.draw2(0, var2, var3, var4, var5, var6, var7, var8, -1L, var11);
					}
				}
				if (GameClient.currentPlayer == this) {
					for (int i_19_ = ReflectionRequest.currentHintIcons.length - 1; i_19_ >= 0; i_19_--) {
						HintIcon hintIcon = ReflectionRequest.currentHintIcons[i_19_];
						if (hintIcon != null && hintIcon.modelId != -1) {
							if (hintIcon.targetType == 1 && hintIcon.entityIndex >= 0 && (GameClient.activeNPCs.length ^ 0xffffffff) < (hintIcon.entityIndex ^ 0xffffffff)) {
								NPC class38_sub7_sub1 = GameClient.activeNPCs[hintIcon.entityIndex];
								if (class38_sub7_sub1 != null) {
									int i_20_ = -(GameClient.currentPlayer.bound_extents_x / 32) + class38_sub7_sub1.bound_extents_x / 32;
									int i_21_ = -(GameClient.currentPlayer.bound_extents_z / 32) + class38_sub7_sub1.bound_extents_z / 32;
									method1101(var6, i_20_, var4, var7, hintIcon.modelId, var15, var2, var1, i_21_, var5, var3, var8, var11);
								}
							}
							if (hintIcon.targetType == 2) {
								int i_22_ = (hintIcon.targetX + -MapLoader.region_aboslute_z) * 4 - (-2 + GameClient.currentPlayer.bound_extents_x / 32);
								int i_23_ = -(GameClient.currentPlayer.bound_extents_z / 32) + (hintIcon.targetY - MapLoader.region_aboslute_x) * 4 - -2;
								method1101(var6, i_22_, var4, var7, hintIcon.modelId, var15, var2, var1, i_23_, var5, var3, var8, var11);
							}
							if (hintIcon.targetType == 10 && hintIcon.entityIndex >= 0 && hintIcon.entityIndex < GameClient.localPlayers.length) {
								Player class38_sub7_sub2_24_ = GameClient.localPlayers[hintIcon.entityIndex];
								if (class38_sub7_sub2_24_ != null) {
									int i_25_ = class38_sub7_sub2_24_.bound_extents_z / 32 - GameClient.currentPlayer.bound_extents_z / 32;
									int i_26_ = -(GameClient.currentPlayer.bound_extents_x / 32) + class38_sub7_sub2_24_.bound_extents_x / 32;
									method1101(var6, i_26_, var4, var7, hintIcon.modelId, var15, var2, var1, i_25_, var5, var3, var8, var11);
								}
							}
						}
					}
				}
				int i_27_ = 0;
				int i_28_ = 0;
				int i_29_ = 0;
				if (aShort4392 != 0 && aShort4385 != 0) {
					int i_30_ = Rasterizer.SINE[var1];
					int i_31_ = Rasterizer.COSINE[var1];
					int i_32_ = aShort4385;
					int i_33_ = -i_32_ / 2;
					int i_34_ = aShort4392;
					int i_35_ = -i_34_ / 2;
					int i_36_ = -i_32_ / 2;
					int i_37_ = i_33_ * i_31_ + -(i_35_ * i_30_) >> 16;
					int i_38_ = i_33_ * i_30_ + i_31_ * i_35_ >> 16;
					int i_39_ = i_34_ / 2;
					int i_40_ = i_39_ * i_31_ + i_36_ * i_30_ >> 16;
					int i_41_ = -i_34_ / 2;
					int i_42_ = Scene.get_average_height(ObjType.localHeight, i_38_ + bound_extents_x, bound_extents_z + i_37_);
					int i_43_ = -(i_30_ * i_39_) + i_31_ * i_36_ >> 16;
					int i_44_ = Scene.get_average_height(ObjType.localHeight, bound_extents_x - -i_40_, i_43_ + bound_extents_z);
					int i_45_ = i_32_ / 2;
					int i_46_ = i_45_ * i_30_ + i_31_ * i_41_ >> 16;
					int i_47_ = -(i_30_ * i_41_) + i_31_ * i_45_ >> 16;
					int i_48_ = Scene.get_average_height(ObjType.localHeight, bound_extents_x + i_46_, bound_extents_z + i_47_);
					int i_49_ = i_32_ / 2;
					int i_50_ = i_34_ / 2;
					int i_51_ = i_31_ * i_50_ + i_30_ * i_49_ >> 16;
					int i_52_ = -(i_30_ * i_50_) + i_49_ * i_31_ >> 16;
					int i_53_ = (i_42_ ^ 0xffffffff) <= (i_44_ ^ 0xffffffff) ? i_44_ : i_42_;
					int i_54_ = Scene.get_average_height(ObjType.localHeight, bound_extents_x - -i_51_, bound_extents_z + i_52_);
					i_29_ = i_54_ + i_42_;
					int i_55_ = (i_44_ ^ 0xffffffff) <= (i_54_ ^ 0xffffffff) ? i_54_ : i_44_;
					int i_56_ = i_48_ <= i_42_ ? i_48_ : i_42_;
					if (i_29_ > i_44_ - -i_48_) {
						i_29_ = i_48_ + i_44_;
					}
					int i_57_ = i_54_ <= i_48_ ? i_54_ : i_48_;
					i_27_ = (int) (Math.atan2(-i_57_ + i_53_, i_32_) * 325.95) & 0x7ff;
					if ((i_27_ ^ 0xffffffff) != -1) {
						var15.xaxis_rotate_without_normals(i_27_);
					}
					i_28_ = (int) (325.95 * Math.atan2(-i_55_ + i_56_, i_34_)) & 0x7ff;
					if ((i_28_ ^ 0xffffffff) != -1) {
						var15.zaxis_rotate_without_normals(i_28_);
					}
					i_29_ = (i_29_ >> 1) + -render_y;
					if ((i_29_ ^ 0xffffffff) != -1) {
						var15.translate(0, i_29_, 0);
					}
				}
				Model class38_sub1_58_ = null;
				if (!aBoolean4384 && current_spotanimid != -1 && current_spotanim_frameid != -1) {
					SpotType class23_sub13_sub17 = SpotType.list(current_spotanimid);
					class38_sub1_58_ = class23_sub13_sub17.get_model(current_spotanim_frameid, next_spotanim_frameid, current_spotanim_tick);
					if (class38_sub1_58_ != null) {
						class38_sub1_58_.translate(0, -anInt2647, 0);
						if (class23_sub13_sub17.aBoolean4054) {
							if (i_27_ != 0) {
								class38_sub1_58_.xaxis_rotate_without_normals(i_27_);
							}
							if (i_28_ != 0) {
								class38_sub1_58_.zaxis_rotate_without_normals(i_28_);
							}
							if (i_29_ != 0) {
								class38_sub1_58_.translate(0, i_29_, 0);
							}
						}
					}
				}
				Model var25 = null;
				if (!aBoolean4384 && aClass38_Sub1_4402 != null) {
					if (GameClient.timer >= anInt4411) {
						aClass38_Sub1_4402 = null;
					}
					if ((GameClient.timer ^ 0xffffffff) <= (anInt4380 ^ 0xffffffff) && (anInt4411 ^ 0xffffffff) < (GameClient.timer ^ 0xffffffff)) {
						if (!(aClass38_Sub1_4402 instanceof GameObject)) {
							var25 = (Model) aClass38_Sub1_4402;
						} else {
							var25 = (Model) ((GameObject) aClass38_Sub1_4402).get_rendering_node();
						}
						var25.translate(-bound_extents_x + anInt4395, anInt4403 + -render_y, anInt4379 - bound_extents_z);
						if (faceDirection != 512) {
							if (faceDirection != 1024) {
								if (faceDirection == 1536) {
									var25.rotate90_without_normals();
								}
							} else {
								var25.rotate180_without_normals();
							}
						} else {
							var25.rotate270_without_normals();
						}
					}
				}
				if (GLManager.opengl_mode) {
					var15.renders_in_one_tile = true;
					var15.draw2(var1, var2, var3, var4, var5, var6, var7, var8, var9, var11);
					if (class38_sub1_58_ != null) {
						class38_sub1_58_.renders_in_one_tile = true;
						class38_sub1_58_.draw2(var1, var2, var3, var4, var5, var6, var7, var8, var9, var11);
					}
				} else {
					if (class38_sub1_58_ != null) {
						var15 = ((SoftwareModel) var15).method1024(class38_sub1_58_);
					}
					if (var25 != null) {
						var15 = ((SoftwareModel) var15).method1024(var25);
					}
					var15.renders_in_one_tile = true;
					var15.draw2(var1, var2, var3, var4, var5, var6, var7, var8, var9, var11);
				}
				if (var25 != null) {
					if (faceDirection == 512) {
						var25.rotate90_without_normals();
					} else if (faceDirection != 1024) {
						if (faceDirection == 1536) {
							var25.rotate270_without_normals();
						}
					} else {
						var25.rotate180_without_normals();
					}
					var25.translate(-anInt4395 + bound_extents_x, -anInt4403 + render_y, -anInt4379 + bound_extents_z);
				}
			}
		}
	}

	public final void method1101(int var5, int var4, int var11, int var15, int var13, Model var3, int var12, int i_67_, int var2, int var10, int var14, int var9, int var6) {
		int var16 = var4 * var4 - -(var2 * var2);
		if (-17 >= ~var16 && -360001 <= ~var16) {
			int var17 = (int) (325.949D * Math.atan2(var4, var2)) & 0x7ff;
			Model var18 = Player.method1763(var3, var17, bound_extents_z, var13, bound_extents_x, render_y);
			if (var18 != null) {
				if (GLManager.opengl_mode) {
					float var19 = GLState.get_depth_range_offset();
					float var20 = GLState.get_depth_range_scale();
					GLState.disable_depthmask();
					GLState.update_depth_range(var19, var20 - 150.0F);
					var18.draw2(0, var12, var14, var11, var10, var5, var15, var9, -1L, var6);
					GLState.enable_depthmask();
					GLState.update_depth_range(var19, var20);
				} else {
					var18.draw2(0, var12, var14, var11, var10, var5, var15, var9, -1L, var6);
				}
			}
		}
	}

	public static void method90(int var0) {
		if (GLManager.opengl_mode) {
			if (!Player.aBoolean3416) {
				Ground[][][] var1 = Scene.current_grounds;
				for (Ground[][] element : var1) {
					Ground[][] var3 = element;
					for (int var4 = 0; ~var3.length < ~var4; ++var4) {
						for (int var5 = 0; var3[var4].length > var5; ++var5) {
							Ground var6 = var3[var4][var5];
							if (var6 != null) {
								OpenGLModel var7;
								if (var6.decoration != null && var6.decoration.node instanceof OpenGLModel) {
									var7 = (OpenGLModel) var6.decoration.node;
									if (~(var6.decoration.uid & Long.MIN_VALUE) == -1L) {
										var7.method1920(false, true, true, true, false, true, true);
									} else {
										var7.method1920(true, true, true, true, true, true, true);
									}
								}
								if (null != var6.wall_decoration) {
									if (var6.wall_decoration.first_node instanceof OpenGLModel) {
										var7 = (OpenGLModel) var6.wall_decoration.first_node;
										if (0L == (var6.wall_decoration.uid & Long.MIN_VALUE)) {
											var7.method1920(false, true, true, true, false, true, true);
										} else {
											var7.method1920(true, true, true, true, true, true, true);
										}
									}

									if (var6.wall_decoration.second_node instanceof OpenGLModel) {
										var7 = (OpenGLModel) var6.wall_decoration.second_node;
										if (-1L != ~(Long.MIN_VALUE & var6.wall_decoration.uid)) {
											var7.method1920(true, true, true, true, true, true, true);
										} else {
											var7.method1920(false, true, true, true, false, true, true);
										}
									}
								}
								if (var6.wall_object != null) {
									if (var6.wall_object.first_node instanceof OpenGLModel) {
										var7 = (OpenGLModel) var6.wall_object.first_node;
										if (-1L != ~(var6.wall_object.uid & Long.MIN_VALUE)) {
											var7.method1920(true, true, true, true, true, true, true);
										} else {
											var7.method1920(false, true, true, true, false, true, true);
										}
									}
									if (var6.wall_object.second_node instanceof OpenGLModel) {
										var7 = (OpenGLModel) var6.wall_object.second_node;
										if ((Long.MIN_VALUE & var6.wall_object.uid) != 0L) {
											var7.method1920(true, true, true, true, true, true, true);
										} else {
											var7.method1920(false, true, true, true, false, true, true);
										}
									}
								}

								for (int var10 = 0; ~var10 > ~var6.num_interactives; ++var10) {
									if (var6.interactives[var10].node instanceof OpenGLModel) {
										OpenGLModel var8 = (OpenGLModel) var6.interactives[var10].node;
										if (-1L == ~(Long.MIN_VALUE & var6.interactives[var10].uid)) {
											var8.method1920(false, true, true, true, false, true, true);
										} else {
											var8.method1920(true, true, true, true, true, true, true);
										}
									}
								}
							}
						}
					}
				}

				Player.aBoolean3416 = true;
			}
		}
	}

	public static void method1099(int i) {
		aClass16_4404 = null;
		aClass16_4406 = null;
		if (i != -1803925744) {
			aClass16_4406 = null;
		}
		anIntArray4412 = null;
		CacheConstants.graphicCacheIdx = null;
	}

	@Override
	public final int get_miny() {
		return anInt2820;
	}

	static final Model method1763(Model var5, int angle, int var2, int uid, int var4, int var6) {
		long _uid = uid;
		Model var9 = (Model) modelsCache.get(_uid);
		if (var9 == null) {
			Mesh var10 = Mesh.fromJs5(CacheConstants.modelCacheIdx, uid, 0);
			if (var10 == null) {
				return null;
			}

			var9 = var10.method2008(64, 768, -50, -10, -50);
			modelsCache.put(_uid, var9);
		}

		int var17 = var5.get_minx();
		int var11 = var5.get_maxx();
		int var12 = var5.get_minz();
		int var13 = var5.get_maxz();
		var9 = var9.copy2(true, true, true);
		if (angle != 0) {
			var9.yaxis_rotate_without_normals(angle);
		}
		int var15;
		if (GLManager.opengl_mode) {
			OpenGLModel var14 = (OpenGLModel) var9;
			if (var6 != Scene.get_average_height(ObjType.localHeight, var4 + var17, var2 + var12) || var6 != Scene.get_average_height(ObjType.localHeight, var4 - -var11, var13 + var2)) {
				for (var15 = 0; ~var15 > ~var14.num_vertices; ++var15) {
					var14.vertices_y[var15] += Scene.get_average_height(ObjType.localHeight, var14.vertices_x[var15] + var4, var14.vertices_z[var15] + var2) - var6;
				}
				var14.positions_attribute.is_clean = false;
				var14.bounding_box.is_clean = false;
			}
		} else {
			SoftwareModel var18 = (SoftwareModel) var9;
			if (var6 != Scene.get_average_height(ObjType.localHeight, var17 + var4, var12 + var2) || var6 != Scene.get_average_height(ObjType.localHeight, var4 - -var11, var13 + var2)) {
				for (var15 = 0; var18.num_vertices > var15; ++var15) {
					var18.vertices_y[var15] += Scene.get_average_height(ObjType.localHeight, var4 + var18.vertices_x[var15], var18.vertices_z[var15] + var2) - var6;
				}

				var18.bounds_updated = false;
			}
		}

		return var9;
	}

	Player() {
		anInt4380 = 0;
		skullIcon = -1;
		currentTeamId = 0;
		skillTotalLevel = 0;
		headIcon = -1;
		combatLevel = 0;
		summoning = 0;
		anInt4411 = 0;
	}

	static {
		aClass16_4406 = RSString.createString("<)4col>");
		LoginHandler.activeLoginTextField = 0;
	}

	public static void add_worn_obj_anim(Player player, int[] seqids, int[] enabled, int[] durations) {
		int var5 = 0;
		while (seqids.length > var5) {
			int var6 = seqids[var5];
			int var7 = enabled[var5];
			int var8 = durations[var5];

			for (int var9 = 0; ~var7 != -1 && ~var9 > ~player.worn_objs_animations.length; var7 >>>= 1) {
				if (~(1 & var7) != -1) {
					if (~var6 == 0) {
						player.worn_objs_animations[var9] = null;
					} else {
						SeqType var10 = SeqTypeList.list(var6);
						int var11 = var10.loop_type;
						WornObjAnim var12 = player.worn_objs_animations[var9];
						if (var12 != null) {
							if (~var6 == ~var12.seqid) {
								if (var11 != 0) {
									if (1 == var11) {
										var12.replaysdone = 0;
										var12.next_frameid = 1;
										var12.frameid = 0;
										var12.duration = var8;
										var12.timer = 0;
										Class21.playAnimationSound(player, player.bound_extents_z, player.bound_extents_x, var10, 0);
									} else if (-3 == ~var11) {
										var12.replaysdone = 0;
									}
								} else {
									var12 = player.worn_objs_animations[var9] = null;
								}
							} else if (var10.priority >= SeqTypeList.list(var12.seqid).priority) {
								var12 = player.worn_objs_animations[var9] = null;
							}
						}

						if (null == var12) {
							var12 = player.worn_objs_animations[var9] = new WornObjAnim();
							var12.seqid = var6;
							var12.next_frameid = 1;
							var12.timer = 0;
							var12.duration = var8;
							var12.frameid = 0;
							var12.replaysdone = 0;
							Class21.playAnimationSound(player, player.bound_extents_z, player.bound_extents_x, var10, 0);
						}
					}
				}
				++var9;
			}
			++var5;
		}
	}
}
