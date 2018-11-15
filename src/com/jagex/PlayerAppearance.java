package com.jagex;

import com.jagex.core.collections.memory.AdvancedMemoryCache;
import com.jagex.graphics.runetek4.media.Model;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.model.OpenGLModel;

public class PlayerAppearance {

	public static final int[] slots_next_frame_time = new int[14];
	public static final SeqType[] slots_seqtype = new SeqType[14];
	public static final AnimFrameset[] slots_next_framesset = new AnimFrameset[14];
	public static final AnimFrameset[] slots_current_animframeset = new AnimFrameset[14];
	public static final int[] anIntArray1679 = new int[14];
	public static final int[] slots_current_frame = new int[14];
	public static final int[] anIntArray3139 = new int[14];
	static long[] calculatedHashes;
	public long aLong855;
	static RSInterface aClass64_790;
	public long appearanceHash;
	public boolean female;
	public static int[] skillExperience;
	public int[] looks;
	static RSString aClass16_804;
	public int[] colors;
	public static RSString aClass16_807;
	public int transformNPCId;
	static AdvancedMemoryCache cache = new AdvancedMemoryCache(260);
	static RSString aClass94_853 = RSString.createString("::tele ");
	final Model method1160(SeqType seq, int current_frameid, int next_frameid, int tick) {
		if (transformNPCId != -1) {
			return NPCType.getNPCDefinition(transformNPCId).get_dialogue_model(seq, current_frameid, next_frameid, tick);
		}
		Model class38_sub1 = (Model) StaticMethods.aModelList_3469.get(appearanceHash);
		if (class38_sub1 == null) {
			boolean bool = false;
			for (int i_1_ = 0; i_1_ < 12; i_1_++) {
				int i_2_ = looks[i_1_];
				if ((i_2_ & 0x40000000 ^ 0xffffffff) != -1) {
					if (!ObjTypeList.list(i_2_ & 0x3fffffff).method739(female)) {
						bool = true;
					}
				} else if ((i_2_ & ~0x7fffffff ^ 0xffffffff) != -1 && !PlayerIdentityKit.getIdentityKit(0x3fffffff & i_2_).method801()) {
					bool = true;
				}
			}
			if (bool) {
				return null;
			}
			Mesh[] models = new Mesh[12];
			int i_3_ = 0;
			for (int i_4_ = 0; i_4_ < 12; i_4_++) {
				int i_5_ = looks[i_4_];
				if ((0x40000000 & i_5_ ^ 0xffffffff) != -1) {
					Mesh model = ObjTypeList.list(0x3fffffff & i_5_).getDialogueModels(-92, female);
					if (model != null) {
						models[i_3_++] = model;
					}
				} else if ((i_5_ & ~0x7fffffff) != 0) {
					Mesh model = PlayerIdentityKit.getIdentityKit(i_5_ & 0x3fffffff).fetchHeadModels();
					if (model != null) {
						models[i_3_++] = model;
					}
				}
			}
			Mesh model = new Mesh(models, i_3_);
			for (int i_6_ = 0; i_6_ < 5; i_6_++) {
				if ((Class44.aShortArrayArray679[i_6_].length ^ 0xffffffff) < (colors[i_6_] ^ 0xffffffff)) {
					model.recolor(StaticMethods.aShortArray3417[i_6_], Class44.aShortArrayArray679[i_6_][colors[i_6_]]);
				}
				if (NameHashTable.aShortArrayArray1189[i_6_].length > colors[i_6_]) {
					model.recolor(Class42.aShortArray655[i_6_], NameHashTable.aShortArrayArray1189[i_6_][colors[i_6_]]);
				}
			}
			class38_sub1 = model.method2008(64, 768, -50, -10, -50);
			StaticMethods.aModelList_3469.put(appearanceHash, class38_sub1);
		}
		if (seq != null) {
			class38_sub1 = seq.get_animated_dialoghead(class38_sub1, current_frameid, next_frameid, tick);
		}
		return class38_sub1;
	}

	public static void method1161(int i) {
		aClass16_807 = null;
		aClass64_790 = null;
		if (i > -48) {
			LobbyWorld.worldSize = -24;
		}
		aClass16_804 = null;
		skillExperience = null;
	}

	public final void updateAppearanceHash() {
		long l = appearanceHash;
		long[] ls = calculatedHashes;
		appearanceHash = -1L;
		for (int i = 0; i < 12; i++) {
			appearanceHash = ls[(int) (0xffL & (looks[i] >> 24 ^ appearanceHash))] ^ appearanceHash >>> 8;
			appearanceHash = ls[(int) (0xffL & (appearanceHash ^ looks[i] >> 16))] ^ appearanceHash >>> 8;
			appearanceHash = appearanceHash >>> 8 ^ ls[(int) (0xffL & (appearanceHash ^ looks[i] >> 8))];
			appearanceHash = ls[(int) ((appearanceHash ^ looks[i]) & 0xffL)] ^ appearanceHash >>> 8;
		}
		for (int i = 0; i < 5; i++) {
			appearanceHash = ls[(int) (0xffL & (appearanceHash ^ colors[i]))] ^ appearanceHash >>> 8;
		}
		appearanceHash = ls[(int) (0xffL & (appearanceHash ^ (!female ? 0 : 1)))] ^ appearanceHash >>> 8;
		if (l != 0L && l != appearanceHash) {
			PlayerAppearance.cache.remove(l);
		}
	}

	static final void method1163(byte b) {
		if (b >= -87) {
			method1163((byte) -43);
		}
		for (int i = -1; (StaticMethods.anInt3067 ^ 0xffffffff) < (i ^ 0xffffffff); i++) {
			int i_7_;
			if ((i ^ 0xffffffff) == 0) {
				i_7_ = 2047;
			} else {
				i_7_ = GameClient.localPlayerPointers[i];
			}
			Player class38_sub7_sub2 = GameClient.localPlayers[i_7_];
			if (class38_sub7_sub2 != null) {
				Queue.method928((byte) 111, class38_sub7_sub2, class38_sub7_sub2.size);
			}
		}
	}

	final void setAppearanceData(boolean female, int[] colors, int npcId, int[] look) {
		if (look == null) {
			look = new int[12];
			for (int slot = 0; slot < 7; slot++) {
				for (int clothId = 0; PlayerIdentityKit.identityKitAmount > clothId; clothId++) {
					PlayerIdentityKit def = PlayerIdentityKit.getIdentityKit(clothId);
					if (def != null && !def.customisation && def.anInt4102 == (female ? 7 : 0) + slot) {
						look[FaceNormal.anIntArray628[slot]] = MathUtils.doBitwiseOr(clothId, -2147483648);
						break;
					}
				}
			}
		}
		looks = look;
		this.colors = colors;
		this.female = female;
		transformNPCId = npcId;
		updateAppearanceHash();
	}

	final void updateLooks(int i, int i_12_, int i_13_) {
		int i_14_ = FaceNormal.anIntArray628[i_13_];
		if (i_12_ != (looks[i_14_] ^ 0xffffffff) && PlayerIdentityKit.getIdentityKit(i) != null) {
			looks[i_14_] = MathUtils.doBitwiseOr(i, -2147483648);
			updateAppearanceHash();
		}
	}

	final Model getAnimatedModel(WornObjAnim[] worn_anims, SeqType standing_seq, SeqType performing_seq, int current_standing_frameid, int current_performing_frameid, int current_standing_tick, int current_performing_tick, int next_standing_frameid, int next_performing_frameid, boolean var9) {
		if ((transformNPCId ^ 0xffffffff) != 0) {
			return NPCType.getNPCDefinition(transformNPCId).method1476(worn_anims, standing_seq, performing_seq, current_standing_frameid, current_performing_frameid, next_standing_frameid, next_performing_frameid, current_standing_tick, current_performing_tick);
		}
		long var13 = appearanceHash;
		int[] var15 = looks;
		if (performing_seq != null && (performing_seq.main_hand_obj >= 0 || performing_seq.off_hand_obj >= 0)) {
			var15 = new int[12];
			for (int i_17_ = 0; i_17_ < 12; i_17_++) {
				var15[i_17_] = looks[i_17_];
			}
			if ((performing_seq.main_hand_obj ^ 0xffffffff) <= -1) {
				if ((performing_seq.main_hand_obj ^ 0xffffffff) != -65536) {
					var15[5] = MathUtils.doBitwiseOr(1073741824, performing_seq.main_hand_obj);
					var13 ^= (long) var15[5] << 32;
				} else {
					var13 ^= ~0xffffffffL;
					var15[5] = 0;
				}
			}
			if (performing_seq.off_hand_obj >= 0) {
				if ((performing_seq.off_hand_obj ^ 0xffffffff) != -65536) {
					var15[3] = MathUtils.doBitwiseOr(1073741824, performing_seq.off_hand_obj);
					var13 ^= var15[3];
				} else {
					var15[3] = 0;
					var13 ^= 0xffffffffL;
				}
			}
		}
		Model var37 = (Model) PlayerAppearance.cache.get(var13);
		if (var37 == null) {
			boolean bool = false;
			for (int i_18_ = 0; i_18_ < 12; i_18_++) {
				int i_19_ = var15[i_18_];
				if ((0x40000000 & i_19_) == 0) {
					if ((~0x7fffffff & i_19_) != 0 && !PlayerIdentityKit.getIdentityKit(i_19_ & 0x3fffffff).isIdentityKitCached()) {
						bool = true;
					}
				} else if (!ObjTypeList.list(0x3fffffff & i_19_).areEquipModelsCached(female)) {
					bool = true;
				}
			}
			if (bool) {
				if (aLong855 != -1L) {
					var37 = (Model) PlayerAppearance.cache.get(aLong855);
				}
				if (var37 == null) {
					return null;
				}
			}
			if (var37 == null) {
				Mesh[] models = new Mesh[12];
				int i_20_ = 0;
				for (int i_21_ = 0; i_21_ < 12; i_21_++) {
					int i_22_ = var15[i_21_];
					if ((0x40000000 & i_22_) == 0) {
						if ((~0x7fffffff & i_22_) != 0) {
							Mesh class38_sub4 = PlayerIdentityKit.getIdentityKit(0x3fffffff & i_22_).fetchBodyModels();
							if (class38_sub4 != null) {
								models[i_20_++] = class38_sub4;
							}
						}
					} else {
						Mesh class38_sub4 = ObjTypeList.list(i_22_ & 0x3fffffff).getWornModel(female);
						if (class38_sub4 != null) {
							models[i_20_++] = class38_sub4;
						}
					}
				}

				Mesh var43 = new Mesh(models, i_20_);
				for (int i_31_ = 0; i_31_ < 5; i_31_++) {
					int color = colors[i_31_];
					if (color < Class44.aShortArrayArray679[i_31_].length && color > 0) {
						var43.recolor(StaticMethods.aShortArray3417[i_31_], Class44.aShortArrayArray679[i_31_][color]);
					}
					if (NameHashTable.aShortArrayArray1189[i_31_].length > color && color > 0) {
						var43.recolor(Class42.aShortArray655[i_31_], NameHashTable.aShortArrayArray1189[i_31_][color]);
					}
				}
				var37 = var43.method2008(64, 850, -30, -50, -30);
				if (GLManager.opengl_mode) {
					((OpenGLModel) var37).method1920(false, false, true, true, false, false, true);
				}
				PlayerAppearance.cache.put(var13, var37);
				aLong855 = var13;
			}
		}
		boolean var17 = false;
		boolean var38 = false;
		boolean var42 = false;
		boolean var44 = false;
		int var45 = worn_anims != null ? worn_anims.length : 0;
		for (int var22 = 0; ~var45 < ~var22; ++var22) {
			if (worn_anims[var22] != null) {
				SeqType var41 = SeqTypeList.list(worn_anims[var22].seqid);
				if (var41.frames_data != null) {
					var17 = true;
					PlayerAppearance.slots_seqtype[var22] = var41;
					int var24 = worn_anims[var22].frameid;
					int var25 = worn_anims[var22].next_frameid;
					int var26 = var41.frames_data[var24];
					PlayerAppearance.slots_current_animframeset[var22] = SeqTypeList.load_frameset(var26 >>> 16);
					var26 &= 0xffff;
					PlayerAppearance.slots_current_frame[var22] = var26;
					if (PlayerAppearance.slots_current_animframeset[var22] != null) {
						var42 |= PlayerAppearance.slots_current_animframeset[var22].modifies_color(var26);
						var38 |= PlayerAppearance.slots_current_animframeset[var22].modifies_alpha(var26);
						var44 |= var41.lights;
					}

					if ((var41.tween || SeqTypeList.force_tweening) && 0 != ~var25 && var25 < var41.frames_data.length) {
						PlayerAppearance.slots_next_frame_time[var22] = var41.frames_durations[var24];
						PlayerAppearance.anIntArray3139[var22] = worn_anims[var22].timer;
						int var47 = var41.frames_data[var25];
						PlayerAppearance.slots_next_framesset[var22] = SeqTypeList.load_frameset(var47 >>> 16);
						var47 &= 0xffff;
						PlayerAppearance.anIntArray1679[var22] = var47;
						if (null != PlayerAppearance.slots_next_framesset[var22]) {
							var42 |= PlayerAppearance.slots_next_framesset[var22].modifies_color(var47);
							var38 |= PlayerAppearance.slots_next_framesset[var22].modifies_alpha(var47);
						}
					} else {
						PlayerAppearance.slots_next_frame_time[var22] = 0;
						PlayerAppearance.anIntArray3139[var22] = 0;
						PlayerAppearance.slots_next_framesset[var22] = null;
						PlayerAppearance.anIntArray1679[var22] = -1;
					}
				}
			}
		}

		if (!var17 && null == performing_seq && null == standing_seq) {
			return var37;
		} else {
			int var22 = -1;
			int var23 = -1;
			int var24 = 0;
			AnimFrameset var48 = null;
			AnimFrameset var46 = null;
			if (null != performing_seq) {
				var22 = performing_seq.frames_data[current_performing_frameid];
				int var47 = var22 >>> 16;
				var46 = SeqTypeList.load_frameset(var47);
				var22 &= 0xffff;
				if (var46 != null) {
					var42 |= var46.modifies_color(var22);
					var38 |= var46.modifies_alpha(var22);
					var44 |= performing_seq.lights;
				}

				if ((performing_seq.tween || SeqTypeList.force_tweening) && next_performing_frameid != -1 && performing_seq.frames_data.length > next_performing_frameid) {
					var23 = performing_seq.frames_data[next_performing_frameid];
					int var28 = var23 >>> 16;
					var23 &= 0xffff;
					var24 = performing_seq.frames_durations[current_performing_frameid];
					if (~var47 != ~var28) {
						var48 = SeqTypeList.load_frameset(var23 >>> 16);
					} else {
						var48 = var46;
					}

					if (null != var48) {
						var42 |= var48.modifies_color(var23);
						var38 |= var48.modifies_alpha(var23);
					}
				}
			}

			int var47 = -1;
			int var28 = -1;
			AnimFrameset var49 = null;
			AnimFrameset var50 = null;
			int var29 = 0;
			if (standing_seq != null) {
				var47 = standing_seq.frames_data[current_standing_frameid];
				int var32 = var47 >>> 16;
				var47 &= 0xffff;
				var49 = SeqTypeList.load_frameset(var32);
				if (null != var49) {
					var42 |= var49.modifies_color(var47);
					var38 |= var49.modifies_alpha(var47);
					var44 |= standing_seq.lights;
				}

				if ((standing_seq.tween || SeqTypeList.force_tweening) && 0 != ~next_standing_frameid && standing_seq.frames_data.length > next_standing_frameid) {
					var29 = standing_seq.frames_durations[current_standing_frameid];
					var28 = standing_seq.frames_data[next_standing_frameid];
					int var33 = var28 >>> 16;
					var28 &= 0xffff;
					if (~var32 != ~var33) {
						var50 = SeqTypeList.load_frameset(var28 >>> 16);
					} else {
						var50 = var49;
					}

					if (null != var50) {
						var42 |= var50.modifies_color(var28);
						var38 |= var50.modifies_alpha(var28);
					}
				}
			}

			Model model = var37.copy1(!var38, !var42, !var44);
			int var33 = 0;
			for (int var34 = 1; var33 < var45; var34 <<= 1) {
				if (PlayerAppearance.slots_current_animframeset[var33] != null) {
					model.method1887(PlayerAppearance.slots_current_animframeset[var33], PlayerAppearance.slots_current_frame[var33], PlayerAppearance.slots_next_framesset[var33], PlayerAppearance.anIntArray1679[var33], PlayerAppearance.anIntArray3139[var33] + -1, PlayerAppearance.slots_next_frame_time[var33], var34, PlayerAppearance.slots_seqtype[var33].lights, null/* anIntArrayArray863[var33] */);
				}
				++var33;
			}

			if (null != var46 && null != var49) {
				model.method1892(var46, var22, var48, var23, current_performing_tick - 1, var24, var49, var47, var50, var28, current_standing_tick + -1, var29, performing_seq.interleave_order, performing_seq.lights | standing_seq.lights);
			} else if (var46 == null) {
				if (null != var49) {
					model.animate_model_frame(var49, var47, var50, var28, current_standing_tick - 1, var29, standing_seq.lights);
				}
			} else {
				model.animate_model_frame(var46, var22, var48, var23, current_performing_tick + -1, var24, performing_seq.lights);
			}

			for (var33 = 0; var33 < var45; ++var33) {
				PlayerAppearance.slots_current_animframeset[var33] = null;
				PlayerAppearance.slots_next_framesset[var33] = null;
				PlayerAppearance.slots_seqtype[var33] = null;
			}

			return model;
		}
	}

	final int getMediaID() {
		if (transformNPCId == -1) {
			return looks[1] + (looks[11] << 5) + (colors[0] << 25) - (-(colors[4] << 20) + -(looks[0] << 15) - (looks[8] << 10));
		}
		return NPCType.getNPCDefinition(transformNPCId).npcid + 305419896;
	}

	final void updateAppearanceColor(int bodyPart, int color) {
		colors[bodyPart] = color;
		updateAppearanceHash();
	}

	final void updateGender(boolean female) {
		this.female = female;
		updateAppearanceHash();
	}

	static {
		LobbyWorld.worldSize = 0;
		skillExperience = new int[26];
		GameClient.cross_index = 0;
		aClass16_807 = RSString.createString("Loading title screen )2 ");
		aClass16_804 = aClass16_807;
		calculatedHashes = new long[256];
		for (int i = 0; i < 256; i++) {
			long l = i;
			for (int i_48_ = 0; i_48_ < 8; i_48_++) {
				if ((0x1L & l ^ 0xffffffffffffffffL) != -2L) {
					l >>>= 1;
				} else {
					l = l >>> 1 ^ ~0x3693a86a2878f0bdL;
				}
			}
			calculatedHashes[i] = l;
		}
	}

	public static int get_cache_size() {
		return cache.size();
	}

}
