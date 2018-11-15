package com.jagex;

import com.jagex.graphics.runetek4.media.Model;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.model.OpenGLModel;

public class NPCType extends Queuable {

	public static final SeqType[] aClass142Array1168 = new SeqType[14];
	public static final AnimFrameset[] aClass3_Sub28_Sub5Array4031 = new AnimFrameset[14];
	public static final AnimFrameset[] aClass3_Sub28_Sub5Array3041 = new AnimFrameset[14];
	public static final int[] anIntArray574 = new int[14];
	public static final int[] anIntArray2574 = new int[14];
	public static final int[] anIntArray664 = new int[14];
	public static final int[] anIntArray912 = new int[14];

	public static MemoryCache npcMap = new MemoryCache(64);
	public static Js5 npcContainer;
	public int[] dialogue_models;
	public int configId;
	public static RSString aClass16_4140 = RSString.createString("Use");
	public int scale_2d;
	public int walkAnimationId;
	public int turnCounterCW;
	public int standAnimation = -1;
	public short aShort4145;
	public int[] model_ids;
	public int turn180Animation;
	public static IoSession aClass34_4150;
	public int degreesToTurn;
	public int[] morphisms;
	public boolean drawMinimapDot;
	public int shadowModifier;
	public int scale_3d;
	public int configFileId;
	public int npcid;
	public boolean visible;
	public short[] retex_src;
	public int turnAnimation;
	public static RSString aClass16_4163;
	public int headIcon;
	public short[] recol_dst;
	public int combatLevel;
	public short[] retex_dst;
	public boolean clickable;
	public int turnCW;
	public int size;
	public short[] recol_src;
	public boolean aBoolean4178;
	public boolean spotshadowed = true;
	public int lightModifier;
	public int alsoTurn;
	public RSString[] actions;
	public short aShort4183;
	public byte[] recol_palette_indices;
	public RSString name;
	public byte aByte1275 = -16;
	public byte aByte1287 = -96;
	public short aShort1286;
	public short aShort1256;
	public int[][] offsets;

	static final NPCType getNPCDefinition(int npcId) {
		NPCType def = (NPCType) npcMap.get(npcId);
		if (def != null) {
			return def;
		}
		byte[] bs = npcContainer.get_file(getArchiveId(npcId), getFileId(npcId));
		def = new NPCType();
		def.npcid = npcId;
		if (bs != null) {
			def.parse(new Packet(bs));
		}
		if (def.name.equals(RSString.create("Grand Exchange clerk"))) {
			def.standAnimation = 808;
		}
		npcMap.put(npcId, def);
		return def;
	}

	static final int getFileId(int npcId) {
		return npcId & 0xff;
	}

	static final int getArchiveId(int i) {
		return i >>> 8;
	}

	final NPCType getChildNPC() {
		int value = -1;
		if (configFileId != -1) {
			value = Varbit.getConfigFileValue(configFileId);
		} else if ((configId ^ 0xffffffff) != 0) {
			value = GameClient.configs[configId];
		}
		if (value < 0 || morphisms.length + -1 <= value || morphisms[value] == -1) {
			int i_1_ = morphisms[-1 + morphisms.length];
			if (i_1_ == -1) {
				return null;
			}
			return getNPCDefinition(i_1_);
		}
		return getNPCDefinition(morphisms[value]);
	}

	static final int method814(int i, boolean bool) {
		if (GameClient.getLanguage() == 1) {
			return 7;
		}
		int i_2_ = i;
		while_131_: do {
			while_130_: do {
				while_129_: do {
					while_128_: do {
						do {
							if (i_2_ != 77) {
								if (i_2_ != 38) {
									if (i_2_ != 16) {
										if (i_2_ != 161) {
											if (i_2_ != 191) {
												if (i_2_ != 69) {
													break while_131_;
												}
											} else {
												break while_129_;
											}
											break while_130_;
										}
									} else {
										break;
									}
									break while_128_;
								}
							} else {
								return 1;
							}
							return 2;
						} while (false);
						return 3;
					} while (false);
					return 4;
				} while (false);
				return 5;
			} while (false);
			return 6;
		} while (false);
		if (bool != true) {
			return -114;
		}
		return 0;
	}

	final Model method1476(WornObjAnim[] worn_anims, SeqType standing_seq, SeqType performing_seq, int current_standing_frameid, int current_performing_frameid, int nex_standing_frameid, int next_performing_frameid, int current_standing_tick, int current_performing_tick) {
		if (morphisms != null) {
			NPCType def = getChildNPC();
			if (def == null) {
				return null;
			}
			return def.method1476(worn_anims, standing_seq, performing_seq, current_standing_frameid, current_performing_frameid, nex_standing_frameid, next_performing_frameid, current_standing_tick, current_performing_tick);
		}
		Model var11 = (Model) ISAACPacket.models_cache.get(npcid);
		if (var11 == null) {
			boolean bool = false;
			for (int modelId : model_ids) {
				if (!StaticMethods.modelCacheIndex.is_file_cached(modelId, 0)) {
					bool = true;
				}
			}
			if (bool) {
				return null;
			}
			Mesh[] meshes = new Mesh[model_ids.length];
			for (int i_8_ = 0; (model_ids.length ^ 0xffffffff) < (i_8_ ^ 0xffffffff); i_8_++) {
				meshes[i_8_] = Mesh.fromJs5(StaticMethods.modelCacheIndex, model_ids[i_8_], 0);
				if (offsets != null && offsets[i_8_] != null && offsets[i_8_] != null) {
					meshes[i_8_].translate(offsets[i_8_][0], offsets[i_8_][1], offsets[i_8_][2]);
				}
			}
			Mesh mesh;
			if (meshes.length == 1) {
				mesh = meshes[0];
			} else {
				mesh = new Mesh(meshes, meshes.length);
			}
			if (recol_src != null) {
				for (int i_9_ = 0; (recol_src.length ^ 0xffffffff) < (i_9_ ^ 0xffffffff); i_9_++) {
					if (recol_palette_indices == null || i_9_ >= recol_palette_indices.length) {
						mesh.recolor(recol_src[i_9_], recol_dst[i_9_]);
					} else {
						mesh.recolor(recol_src[i_9_], SpotType.npcColors[0xff & recol_palette_indices[i_9_]]);
					}
				}
			}
			if (retex_src != null) {
				for (int i_10_ = 0; (retex_src.length ^ 0xffffffff) < (i_10_ ^ 0xffffffff); i_10_++) {
					mesh.retexture(retex_src[i_10_], retex_dst[i_10_]);
				}
			}
			var11 = mesh.method2008(lightModifier + 64, shadowModifier + 850, -30, -50, -30);
			if (GLManager.opengl_mode) {
				((OpenGLModel) var11).method1920(false, false, false, true, false, false, true);
			}
			ISAACPacket.models_cache.put(npcid, var11);
		}

		boolean var12 = false;
		boolean var37 = false;
		boolean var35 = false;
		boolean var36 = false;
		int var16 = null != worn_anims ? worn_anims.length : 0;
		for (int var17 = 0; ~var16 < ~var17; ++var17) {
			if (worn_anims[var17] != null) {
				SeqType var39 = SeqTypeList.list(worn_anims[var17].seqid);
				if (null != var39.frames_data) {
					NPCType.aClass142Array1168[var17] = var39;
					int var20 = worn_anims[var17].next_frameid;
					var12 = true;
					int var19 = worn_anims[var17].frameid;
					int var21 = var39.frames_data[var19];
					NPCType.aClass3_Sub28_Sub5Array3041[var17] = SeqTypeList.load_frameset(var21 >>> 16);
					var21 &= '\uffff';
					NPCType.anIntArray912[var17] = var21;
					if (NPCType.aClass3_Sub28_Sub5Array3041[var17] != null) {
						var35 |= NPCType.aClass3_Sub28_Sub5Array3041[var17].modifies_color(var21);
						var37 |= NPCType.aClass3_Sub28_Sub5Array3041[var17].modifies_alpha(var21);
						var36 |= var39.lights;
					}

					if ((var39.tween || SeqTypeList.force_tweening) && 0 != ~var20 && ~var39.frames_data.length < ~var20) {
						NPCType.anIntArray664[var17] = var39.frames_durations[var19];
						NPCType.anIntArray2574[var17] = worn_anims[var17].timer;
						int var22 = var39.frames_data[var20];
						NPCType.aClass3_Sub28_Sub5Array4031[var17] = SeqTypeList.load_frameset(var22 >>> 16);
						var22 &= '\uffff';
						NPCType.anIntArray574[var17] = var22;
						if (null != NPCType.aClass3_Sub28_Sub5Array4031[var17]) {
							var35 |= NPCType.aClass3_Sub28_Sub5Array4031[var17].modifies_color(var22);
							var37 |= NPCType.aClass3_Sub28_Sub5Array4031[var17].modifies_alpha(var22);
						}
					} else {
						NPCType.anIntArray664[var17] = 0;
						NPCType.anIntArray2574[var17] = 0;
						NPCType.aClass3_Sub28_Sub5Array4031[var17] = null;
						NPCType.anIntArray574[var17] = -1;
					}
				}
			}
		}
		if (!var12 && null == performing_seq && standing_seq == null) {
			Model var41 = var11.copy1(true, true, true);
			if (scale_2d != 128 || -129 != ~scale_3d) {
				var41.scale(scale_2d, scale_3d, scale_2d);
			}

			return var41;
		} else {
			int var18 = -1;
			int var17 = -1;
			int var19 = 0;
			AnimFrameset var40 = null;
			AnimFrameset var43 = null;
			if (performing_seq != null) {
				var17 = performing_seq.frames_data[current_performing_frameid];
				int var22 = var17 >>> 16;
				var17 &= '\uffff';
				var40 = SeqTypeList.load_frameset(var22);
				if (null != var40) {
					var35 |= var40.modifies_color(var17);
					var37 |= var40.modifies_alpha(var17);
					var36 |= performing_seq.lights;
				}

				if ((performing_seq.tween || SeqTypeList.force_tweening) && 0 != ~next_performing_frameid && ~performing_seq.frames_data.length < ~next_performing_frameid) {
					var19 = performing_seq.frames_durations[current_performing_frameid];
					var18 = performing_seq.frames_data[next_performing_frameid];
					int var42 = var18 >>> 16;
					var18 &= '\uffff';
					if (var22 != var42) {
						var43 = SeqTypeList.load_frameset(var18 >>> 16);
					} else {
						var43 = var40;
					}

					if (var43 != null) {
						var35 |= var43.modifies_color(var18);
						var37 |= var43.modifies_alpha(var18);
					}
				}
			}

			int var22 = -1;
			int var42 = -1;
			int var24 = 0;
			AnimFrameset var44 = null;
			AnimFrameset var46 = null;
			if (standing_seq != null) {
				var22 = standing_seq.frames_data[current_standing_frameid];
				int var27 = var22 >>> 16;
				var22 &= '\uffff';
				var44 = SeqTypeList.load_frameset(var27);
				if (var44 != null) {
					var35 |= var44.modifies_color(var22);
					var37 |= var44.modifies_alpha(var22);
					var36 |= standing_seq.lights;
				}

				if ((standing_seq.tween || SeqTypeList.force_tweening) && 0 != ~nex_standing_frameid && nex_standing_frameid < standing_seq.frames_data.length) {
					var24 = standing_seq.frames_durations[current_standing_frameid];
					var42 = standing_seq.frames_data[nex_standing_frameid];
					int var28 = var42 >>> 16;
					var42 &= '\uffff';
					if (~var28 == ~var27) {
						var46 = var44;
					} else {
						var46 = SeqTypeList.load_frameset(var42 >>> 16);
					}

					if (null != var46) {
						var35 |= var46.modifies_color(var42);
						var37 |= var46.modifies_alpha(var42);
					}
				}
			}

			Model var45 = var11.copy1(!var37, !var35, !var36);
			int var29 = 1;
			for (int var28 = 0; var28 < var16; ++var28) {
				if (NPCType.aClass3_Sub28_Sub5Array3041[var28] != null) {
					var45.method1887(NPCType.aClass3_Sub28_Sub5Array3041[var28], NPCType.anIntArray912[var28], NPCType.aClass3_Sub28_Sub5Array4031[var28], NPCType.anIntArray574[var28], -1 + NPCType.anIntArray2574[var28], NPCType.anIntArray664[var28], var29, NPCType.aClass142Array1168[var28].lights, null/* anIntArrayArray1258[var28] */);
				}
				var29 <<= 1;
			}
			if (var40 != null && var44 != null) {
				var45.method1892(var40, var17, var43, var18, current_performing_tick + -1, var19, var44, var22, var46, var42, current_standing_tick + -1, var24, performing_seq.interleave_order, performing_seq.lights | standing_seq.lights);
			} else if (var40 == null) {
				if (null != var44) {
					var45.animate_model_frame(var44, var22, var46, var42, -1 + current_standing_tick, var24, standing_seq.lights);
				}
			} else {
				var45.animate_model_frame(var40, var17, var43, var18, current_performing_tick + -1, var19, performing_seq.lights);
			}
			for (int var28 = 0; ~var28 > ~var16; ++var28) {
				NPCType.aClass3_Sub28_Sub5Array3041[var28] = null;
				NPCType.aClass3_Sub28_Sub5Array4031[var28] = null;
				NPCType.aClass142Array1168[var28] = null;
			}
			if (~scale_2d != -129 || -129 != ~scale_3d) {
				var45.scale(scale_2d, scale_3d, scale_2d);
			}

			return var45;
		}
	}

	final void parse(Packet buffer) {
		for (;;) {
			int opcode = buffer.g1();
			if (opcode == 0) {
				break;
			}
			parseOpcode(opcode, buffer);
		}
	}


	public final void parseOpcode(int opcode, Packet buffer) {
		if (opcode == 1) {
			int i_20_ = buffer.g1();
			model_ids = new int[i_20_];
			for (int i_21_ = 0; i_21_ < i_20_; i_21_++) {
				model_ids[i_21_] = buffer.g4();
			}
		} else if (opcode == 2) {
			name = buffer.gstr();
		} else if (opcode == 121) {
			offsets = new int[model_ids.length][];
			int var4 = buffer.g1();
			for (int var5 = 0; var5 < var4; ++var5) {
				int var6 = buffer.g1();
				int[] var7 = offsets[var6] = new int[3];
				var7[0] = buffer.g1s();
				var7[1] = buffer.g1s();
				var7[2] = buffer.g1s();
			}
		} else if (opcode != 12) {
			if (opcode != 13) {
				if (opcode != 14) {
					if (opcode == 15) {
						turnAnimation = buffer.g2();
					} else if (opcode != 16) {
						if (opcode != 17) {
							if (opcode >= 30 && opcode < 35) {
								actions[-30 + opcode] = buffer.gstr();
								if (actions[opcode + -30].equalsIgnoreCase(Mouse.hiddenOptionString)) {
									actions[-30 + opcode] = null;
								}
							} else if (opcode == 40) {
								int length = buffer.g1();
								recol_src = new short[length];
								recol_dst = new short[length];
								for (int i_23_ = 0; i_23_ < length; i_23_++) {
									recol_src[i_23_] = (short) buffer.g2();
									recol_dst[i_23_] = (short) buffer.g2();
								}
							} else if (opcode != 41) {
								if (opcode != 42) {
									if (opcode == 60) {
										int i_24_ = buffer.g1();
										dialogue_models = new int[i_24_];
										for (int i_25_ = 0; i_25_ < i_24_; i_25_++) {
											dialogue_models[i_25_] = buffer.g4();
										}
									} else if (opcode == 93) {
										drawMinimapDot = false;
									} else if (opcode != 95) {
										if (opcode != 97) {
											if (opcode == 98) {
												scale_3d = buffer.g2();
											} else if (opcode != 99) {
												if (opcode != 100) {
													if (opcode == 101) {
														shadowModifier = buffer.g1s() * 5;
													} else if (opcode != 102) {
														if (opcode != 103) {
															if (opcode == 106 || opcode == 118) {
																configFileId = buffer.g2();
																if (configFileId == 65535) {
																	configFileId = -1;
																}
																configId = buffer.g2();
																if (configId == 65535) {
																	configId = -1;
																}
																int i_27_ = buffer.g1();
																morphisms = new int[i_27_];
																for (int i_28_ = 0; i_27_ > i_28_; i_28_++) {
																	morphisms[i_28_] = buffer.g4();
																}
															} else if (opcode != 107) {
																if (opcode != 109) {
																	if (opcode != 111) {
																		if (opcode == 113) {
																			aShort1286 = (short) buffer.g2();
																			aShort1256 = (short) buffer.g2();
																		} else if (opcode != 114) {
																			if (opcode == 115) {
																				aShort4183 = (short) (4 * buffer.g1());
																				aShort4145 = (short) (buffer.g1() * 4);
																			}
																		} else {
																			aByte1287 = buffer.g1s();
																			aByte1275 = buffer.g1s();
																		}
																	} else {
																		spotshadowed = false;
																	}
																} else {
																	aBoolean4178 = false;
																}
															} else {
																clickable = false;
															}
														} else {
															degreesToTurn = buffer.g2();
														}
													} else {
														headIcon = buffer.g2();
													}
												} else {
													lightModifier = buffer.g1s();
												}
											} else {
												visible = true;
											}
										} else {
											scale_2d = buffer.g2();
										}
									} else {
										combatLevel = buffer.g2();
									}
								} else {
									int i_29_ = buffer.g1();
									recol_palette_indices = new byte[i_29_];
									for (int i_30_ = 0; i_30_ < i_29_; i_30_++) {
										recol_palette_indices[i_30_] = buffer.g1s();
									}
									// System.out.println("Smd " + new String(aByteArray4184));
								}
							} else {
								int i_31_ = buffer.g1();
								retex_dst = new short[i_31_];
								retex_src = new short[i_31_];
								for (int i_32_ = 0; i_32_ < i_31_; i_32_++) {
									retex_src[i_32_] = (short) buffer.g2();
									retex_dst[i_32_] = (short) buffer.g2();
								}
							}
						} else {
							walkAnimationId = buffer.g2();
							turn180Animation = buffer.g2();
							turnCW = buffer.g2();
							turnCounterCW = buffer.g2();
						}
					} else {
						alsoTurn = buffer.g2();
					}
				} else {
					walkAnimationId = buffer.g2();
				}
			} else {
				standAnimation = buffer.g2();
			}
		} else {
			size = buffer.g1();
		}
	}

	public boolean method820(int i) {
		if (i != 0) {
			return true;
		}
		if (morphisms == null) {
			return true;
		}
		int i_33_ = -1;
		if (configFileId == -1) {
			if (configId != -1) {
				i_33_ = GameClient.configs[configId];
			}
		} else {
			i_33_ = Varbit.getConfigFileValue(configFileId);
		}
		if (i_33_ < 0 || -1 + morphisms.length <= i_33_ || morphisms[i_33_] == -1) {
			int i_34_ = morphisms[morphisms.length - 1];
			if (i_34_ == -1) {
				return false;
			}
			return true;
		}
		return true;
	}

	public static void method822(int i) {
		aClass16_4163 = null;
		aClass16_4140 = null;
		aClass34_4150 = null;
	}

	final Model get_dialogue_model(SeqType seq, int current_frameid, int next_frameid, int tick) {
		if (morphisms != null) {
			NPCType def = getChildNPC();
			if (def == null) {
				return null;
			}
			return def.get_dialogue_model(seq, current_frameid, next_frameid, tick);
		}
		if (dialogue_models == null) {
			return null;
		}
		Model model = (Model) StaticMethods2.aModelList_4267.get(npcid);
		if (model == null) {
			boolean bool = false;
			for (int dialogueModel : dialogue_models) {
				if (!StaticMethods.modelCacheIndex.is_file_cached(dialogueModel, 0)) {
					bool = true;
				}
			}
			if (bool) {
				return null;
			}
			Mesh[] class38_sub4s = new Mesh[dialogue_models.length];
			for (int i_40_ = 0; i_40_ < dialogue_models.length; i_40_++) {
				class38_sub4s[i_40_] = Mesh.fromJs5(StaticMethods.modelCacheIndex, dialogue_models[i_40_], 0);
			}
			Mesh class38_sub4;
			if (class38_sub4s.length != 1) {
				class38_sub4 = new Mesh(class38_sub4s, class38_sub4s.length);
			} else {
				class38_sub4 = class38_sub4s[0];
			}
			if (recol_src != null) {
				for (int i_41_ = 0; recol_src.length > i_41_; i_41_++) {
					if (recol_palette_indices == null || recol_palette_indices.length <= i_41_) {
						class38_sub4.recolor(recol_src[i_41_], recol_dst[i_41_]);
					} else {
						class38_sub4.recolor(recol_src[i_41_], SpotType.npcColors[recol_palette_indices[i_41_] & 0xff]);
					}
				}
			}
			if (retex_src != null) {
				for (int i_42_ = 0; i_42_ < retex_src.length; i_42_++) {
					class38_sub4.retexture(retex_src[i_42_], retex_dst[i_42_]);
				}
			}
			model = class38_sub4.method2008(64, 768, -50, -10, -50);
			StaticMethods2.aModelList_4267.put(npcid, model);
		}
		if (seq != null) {
			model = seq.get_animated_dialoghead(model, current_frameid, next_frameid, tick);
		}
		return model;
	}

	public NPCType() {
		scale_2d = 128;
		configId = -1;
		turnCounterCW = -1;
		scale_3d = 128;
		turn180Animation = -1;
		configFileId = -1;
		turnAnimation = -1;
		clickable = true;
		combatLevel = -1;
		headIcon = -1;
		drawMinimapDot = true;
		visible = false;
		aShort4145 = (short) 0;
		size = 1;
		walkAnimationId = -1;
		shadowModifier = 0;
		turnCW = -1;
		aBoolean4178 = true;
		aShort4183 = (short) 0;
		actions = new RSString[5];
		lightModifier = 0;
		alsoTurn = -1;
		degreesToTurn = 32;
		name = TimeTools.aClass16_1601;
	}

	static {
		aClass16_4163 = aClass16_4140;
	}
}
