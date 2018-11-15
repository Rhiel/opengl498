package com.jagex;

import com.jagex.core.collections.memory.AdvancedMemoryCache;
import com.jagex.core.tools.MathTools;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.model.OpenGLModel;
import com.jagex.graphics.runetek4.software.sprite.SoftwarePaletteSprite;

public class LocType extends Queuable {

	public static Mesh[] modelBuffer1 = new Mesh[4];
	public static Mesh[] modelBuffer2 = new Mesh[4];

	public int resize_z;
	public boolean aBoolean1502;
	public short[] retex_src;
	public int anInt2996;
	public int mapScene;
	public int[] morphisms;
	public int morph_varbit = -1;
	public int offset_z;
	public int map_sprite_id;
	public int size2d;
	public int size3d;
	public int anInt3755;
	public int offset_x;
	public boolean isSolidObject;
	public boolean membersOnly;
	public short[] recolor_dst;
	public int resize_x;
	public boolean projectile_blocked;
	public byte[] recolor_palette_indices;
	public byte terrain_adjust_type;
	public int[] shapes;
	public boolean aBoolean1492;
	public boolean cullable;
	public short[] recolor_src;
	public boolean soft_shadows;
	public int seqid;
	public int contrast;
	public int offsetMultiplier;
	public int anInt3782;
	public int anInt2975;
	public static int anInt3784 = 0;
	public boolean aBoolean736;
	public int morph_var;
	public int resize_y;
	public int anInt2981;
	public int terrain_adjust_value;
	public int walkingFlag;
	public int id;
	public int solid;
	public int offset_y;
	public boolean mirrored;
	public RSString name;
	public int[][] models;
	public RSString[] actions;
	public int[] anIntArray3801;
	public short[] retex_dst;
	public int ambient;
	public boolean non_flat_shading;
	public int clipping_type;
	public int[] quests;
	public byte tint_hue;
	public byte tint_saturation;
	public byte tint_lightness;
	public byte tint_opacity;
	public int shadow_offset_x;// dummy
	public int shadow_offset_y;// dummy
	public int shadow_offset_z;// dummy
	public int map_categoryid;
	public int map_icon_id;
	public int map_icon_rotation;
	public boolean map_icon_fliped;
	public boolean map_icon_rotates;
	public boolean aBoolean1530;
	public HashTable parameters;
	public boolean opcode98;
	public boolean shadowed;
	public boolean aBoolean1507;

	public LocType() {
		shadowed = true;
		anInt2996 = -1;
		offset_x = 0;
		offset_z = 0;
		map_sprite_id = -1;
		aBoolean1492 = true;
		projectile_blocked = true;
		mapScene = -1;
		seqid = -1;
		resize_z = 128;
		aBoolean736 = false;
		soft_shadows = true;
		size3d = 1;
		size2d = 1;
		contrast = 0;
		resize_y = 128;
		terrain_adjust_value = (short) -1;
		isSolidObject = false;
		actions = new RSString[5];
		cullable = false;
		offsetMultiplier = 16;
		mirrored = false;
		morph_var = -1;
		solid = -1;
		offset_y = 0;
		membersOnly = false;
		anInt3755 = 0;
		name = StaticMethods2.aClass16_351;
		anInt2975 = -1;
		resize_x = 128;
		anInt2981 = 0;
		ambient = 0;
		aBoolean1502 = false;
		anInt3782 = 0;
		clipping_type = 2;
		terrain_adjust_type = (byte) 0;
		non_flat_shading = false;
		walkingFlag = 0;
		map_icon_id = -1;
		map_icon_rotation = 0;
		map_icon_fliped = false;
		map_icon_rotates = false;
		map_categoryid = -1;
	}

	public static void setObjectCache(Js5 objectWorker, Js5 modelWorker, boolean isMembers) {
		LocTypeList.objectContainer = objectWorker;
		LocTypeList.models_js5 = modelWorker;
		LocTypeList.isMember = isMembers;
		LocTypeList.cache = new AdvancedMemoryCache(30);
	}

	public void decode(Packet buffer) {
		try {
			for (;;) {
				int opcode = buffer.g1();
				if (opcode == 0) {
					break;
				}
				decode(buffer, opcode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void decode(Packet buffer, int opcode) {
		if (opcode == 1 || opcode == 5) {
			if (opcode == 5 && LocTypeList.isMember) {
				skip_models(buffer);
			}
			int num_shapes = buffer.g1();
			models = new int[num_shapes][];
			shapes = new int[num_shapes];
			for (int shape_index = 0; shape_index < num_shapes; shape_index++) {
				shapes[shape_index] = buffer.g1s();
				int num_models = buffer.g1();
				models[shape_index] = new int[num_models];
				for (int model_index = 0; model_index < num_models; model_index++) {
					models[shape_index][model_index] = buffer.g2();
				}
			}
			if (opcode == 5 && !LocTypeList.isMember) {
				skip_models(buffer);
			}
		} else if (opcode == 2) {
			name = buffer.gstr();
		} else if (opcode == 14) {
			size2d = buffer.g1();
		} else if (opcode == 15) {
			size3d = buffer.g1();
		} else if (opcode == 17) {
			projectile_blocked = false;
			clipping_type = 0;
		} else if (opcode == 18) {
			projectile_blocked = false;
		} else if (opcode == 19) {
			solid = buffer.g1();
		} else if (opcode == 21) {
			terrain_adjust_type = (byte) 1;
		} else if (opcode == 22) {
			non_flat_shading = true;
		} else if (opcode == 23) {
			cullable = false;
		} else if (opcode == 24) {
			seqid = buffer.g2();
			if (seqid == 65535) {
				seqid = -1;
			}
		} else if (opcode == 27) {
			clipping_type = 1;
		} else if (opcode == 28) {
			offsetMultiplier = buffer.g1();
		} else if (opcode == 29) {
			ambient = buffer.g1s();
		} else if (opcode == 39) {
			contrast = 5 * buffer.g1s();
		} else if (opcode >= 30 && opcode < 35) {
			actions[-30 + opcode] = buffer.gstr();
			if (actions[opcode + -30].equalsIgnoreCase(Mouse.hiddenOptionString)) {
				actions[opcode + -30] = null;
			}
		} else if (opcode == 40) {
			int i_2_ = buffer.g1();
			recolor_src = new short[i_2_];
			recolor_dst = new short[i_2_];
			for (int i_3_ = 0; i_3_ < i_2_; i_3_++) {
				recolor_src[i_3_] = (short) buffer.g2();
				recolor_dst[i_3_] = (short) buffer.g2();
			}
		} else if (opcode == 41) {
			int i_4_ = buffer.g1();
			retex_src = new short[i_4_];
			retex_dst = new short[i_4_];
			for (int i_5_ = 0; i_5_ < i_4_; i_5_++) {
				retex_src[i_5_] = (short) buffer.g2();
				retex_dst[i_5_] = (short) buffer.g2();
			}
		} else if (opcode == 42) {
			int i_6_ = buffer.g1();
			recolor_palette_indices = new byte[i_6_];
			for (int i_7_ = 0; i_7_ < i_6_; i_7_++) {
				recolor_palette_indices[i_7_] = buffer.g1s();
			}
		} else if (opcode == 60) {
			map_sprite_id = buffer.g2();
		} else if (opcode == 62) {
			mirrored = true;
		} else if (opcode == 64) {
			soft_shadows = false;
		} else if (opcode == 65) {
			resize_x = buffer.g2();
		} else if (opcode == 66) {
			resize_y = buffer.g2();
		} else if (opcode == 67) {
			resize_z = buffer.g2();
		} else if (opcode == 68) {
			buffer.g2();
		} else if (opcode == 69) {
			walkingFlag = buffer.g1();
		} else if (opcode == 70) {
			offset_x = buffer.g2s();// << 2;
		} else if (opcode == 71) { // << 2;
			offset_y = buffer.g2s();// << 2;
		} else if (opcode == 72) {
			offset_z = buffer.g2s();
		} else if (opcode == 73) {
			aBoolean736 = true;
		} else if (opcode == 74) {
			isSolidObject = true;
		} else if (opcode == 75) {
			anInt2975 = buffer.g1();
		} else if (opcode == 77 || opcode == 92) {
			morph_varbit = buffer.g2();
			if (morph_varbit == 65535) {
				morph_varbit = -1;
			}
			morph_var = buffer.g2();
			if (morph_var == 65535) {
				morph_var = -1;
			}
			int default_morphism = -1;
			if (opcode == 92) {
				default_morphism = buffer.g2();
				if (default_morphism == 65535) {
					default_morphism = -1;
				}
			}
			int num_morphs = buffer.g1();
			morphisms = new int[num_morphs + 2];
			for (int morph_index = 0; morph_index <= num_morphs; morph_index++) {
				morphisms[morph_index] = buffer.g2();
				if (morphisms[morph_index] == 65535) {
					morphisms[morph_index] = -1;
				}
			}
			morphisms[num_morphs + 1] = default_morphism;
		} else if (opcode == 78) {
			anInt2996 = buffer.g2();
			anInt2981 = buffer.g1();
		} else if (opcode == 79) {
			anInt3755 = buffer.g2();
			anInt3782 = buffer.g2();
			anInt2981 = buffer.g1();
			int i_8_ = buffer.g1();
			anIntArray3801 = new int[i_8_];
			for (int i_9_ = 0; i_9_ < i_8_; i_9_++) {
				anIntArray3801[i_9_] = buffer.g2();
			}
		} else if (opcode == 81) {
			terrain_adjust_type = (byte) 2;
			terrain_adjust_value = (short) (buffer.g1() * 256);
		} else if (opcode == 82) {
			// NOOP: 639
		} else if (opcode == 88) {
			shadowed = false;
		} else if (opcode == 89) {
			aBoolean1492 = false;
		} else if (opcode == 90) {
			aBoolean1502 = true;
		} else if (opcode == 91) {
			membersOnly = true;
		} else if (opcode == 93) {
			terrain_adjust_type = (byte) 3;
			terrain_adjust_value = buffer.g2();
		} else if (opcode == 94) {
			terrain_adjust_type = (byte) 4;
		} else if (opcode == 95) {
			terrain_adjust_type = (byte) 5;
			terrain_adjust_value = buffer.g2s();
		} else if (opcode == 96) {
			aBoolean1507 = true;
		} else if (opcode == 97) {
			map_icon_rotates = true;
		} else if (opcode == 98) {
			opcode98 = true;
		} else if (opcode == 99) {
			// NOOP: 639
			buffer.g1();
			buffer.g2();
		} else if (opcode == 100) {
			// NOOP: 639
			buffer.g1();
			buffer.g2();
		} else if (opcode == 101) {
			map_icon_rotation = buffer.g1();
		} else if (opcode == 102) {
			map_icon_id = buffer.g2();
		} else if (opcode == 103) {
			// NOOP: 639
		} else if (opcode == 104) {
			// NOOP: 639
			buffer.g1();
		} else if (opcode == 105) {
			map_icon_fliped = true;
		} else if (opcode == 106) {
			// NOOP: 639
			int i_39_ = buffer.g1();
			for (int i_40_ = 0; i_40_ < i_39_; i_40_++) {
				buffer.g2();
				buffer.g1();
			}
		} else if (opcode == 107) {
			map_categoryid = buffer.g2();
		} else if (opcode >= 150 && opcode < 155) {
			actions[opcode - 150] = buffer.gstr();
			if (actions[opcode - 150].equalsIgnoreCase(Mouse.hiddenOptionString)) {
				actions[opcode - 150] = null;
			}
		} else if (opcode == 160) {
			// TODO: implement icons for this opcode
			int num_quests = buffer.g1();
			quests = new int[num_quests];
			for (int quest_index = 0; num_quests > quest_index; quest_index++) {
				quests[quest_index] = buffer.g2();
			}
		} else if (opcode == 162) {
			terrain_adjust_type = (byte) 3;
			terrain_adjust_value = buffer.g4();
		} else if (opcode == 163) {
			tint_hue = buffer.g1s();
			tint_saturation = buffer.g1s();
			tint_lightness = buffer.g1s();
			tint_opacity = buffer.g1s();
		} else if (opcode == 164) {
			shadow_offset_x = buffer.g2s();
		} else if (opcode == 165) {
			shadow_offset_y = buffer.g2s();
		} else if (opcode == 166) {
			shadow_offset_z = buffer.g2s();
		} else if (opcode == 167) {
			// NOOP: 639
			buffer.g2();// placement offset
		} else if (opcode == 168) {
			// NOOP: 639
		} else if (opcode == 169) {
			// NOOP: 639
		} else if (opcode == 170) {
			// NOOP: 639
			buffer.getSmart0();
		} else if (opcode == 171) {
			// NOOP: 639
			buffer.getSmart0();
		} else if (opcode == 173) {
			// NOOP: 639
			buffer.g2();
			buffer.g2();
		} else if (opcode == 174) {
			// NOOP: 639
			buffer.g2();
		} else if (opcode == 178) {
			// NOOP: 639
			buffer.g1();
		} else if (opcode == 249) {
			// NOOP: 639
			int num_params = buffer.g1();
			if (parameters == null) {
				parameters = new HashTable(MathTools.get_greater_pow2(num_params));
			}
			for (int param_index = 0; param_index < num_params; param_index++) {
				boolean string_param = buffer.g1() == 1;
				int key = buffer.g3();
				if (!string_param) {
					parameters.put(key, new IntegerNode(buffer.g4()));
				} else {
					parameters.put(key, new StringNode(buffer.gstr()));
				}
			}
		}
	}

	public void skip_models(Packet buffer) {
		int num_shapes = buffer.g1();
		for (int shape_index = 0; shape_index < num_shapes; shape_index++) {
			buffer.index++;
			int num_models = buffer.g1();
			buffer.index += num_models * 2;
		}
	}

	public LocResult get_pair(int[][] is_22_, int rotation, int i_24_, int type, int i_26_, int[][] is, boolean bool_21_, int i_23_, SoftwarePaletteSprite shadow_sprite, boolean shadowed) {
		long var12;
		// if (type >= 4 && type <= 8) {
		// type = 4;
		// }
		if (shapes == null) {
			var12 = (id << 10) + rotation;
		} else {
			var12 = (id << 10) + (type << 3) + rotation;
		}
		if (!GLManager.opengl_mode) {
			boolean bool_27_;
			if (!bool_21_ || !non_flat_shading) {
				bool_27_ = false;
			} else {
				bool_27_ = true;
				var12 |= ~0x7fffffffffffffffL;
			}
			Object model = LocTypeList.cache.get(var12);
			if (model == null) {
				Mesh class38_sub4 = create_mesh(rotation, type);
				if (class38_sub4 == null) {
					LocTypeList.result.node = null;
					return LocTypeList.result;
				}
				class38_sub4.method1063();
				if (!bool_27_) {
					model = new SoftwareModel(class38_sub4, 64 + ambient, 5 * contrast + 768, -50, -10, -50);
				} else {
					class38_sub4.contrast = (short) (5 * contrast + 768);
					model = class38_sub4;
					class38_sub4.ambient = (short) (ambient + 64);
					class38_sub4.calculate_normals();
				}
				LocTypeList.cache.put(var12, model);
			}
			if (bool_27_) {
				model = ((Mesh) model).copy();
			}
			if (terrain_adjust_type != 0) {
				if (!(model instanceof SoftwareModel)) {
					if (model instanceof Mesh) {
						model = ((Mesh) model).method1999(terrain_adjust_type, terrain_adjust_value, is_22_, is, i_24_, i_26_, i_23_, true, false);
					}
				} else {
					model = ((SoftwareModel) model).method1941(terrain_adjust_type, terrain_adjust_value, is_22_, is, i_24_, i_26_, i_23_, true);
				}
			}
			LocTypeList.result.node = (SceneNode) model;
			return LocTypeList.result;
		} else {
			LocResult var16 = (LocResult) LocTypeList.cache.get(var12);
			OpenGLModel var14;
			SoftwarePaletteSprite var15;
			if (null == var16) {
				var14 = create_opengl_model(rotation, false, true, type);
				if (null == var14) {
					LocTypeList.result.node = null;
					LocTypeList.result.shadow = null;
					return LocTypeList.result;
				}

				if (~type == -11 && rotation > 3) {
					var14.yaxis_rotate_without_normals(256);
				}

				if (shadowed) {
					var15 = var14.create_shadow_sprite(shadow_sprite);
				} else {
					var15 = null;
				}

				var16 = new LocResult();
				var16.node = var14;
				var16.shadow = var15;
				LocTypeList.cache.put(var12, var16);
			} else {
				var14 = (OpenGLModel) var16.node;
				var15 = var16.shadow;
			}

			boolean var17 = non_flat_shading & bool_21_;
			OpenGLModel model = var14.duplicate(terrain_adjust_type != 3, terrain_adjust_type == 0, true, true, true, true, !var17, true, true, true, true);
			if (~terrain_adjust_type != -1) {
				model.method1919(terrain_adjust_type, terrain_adjust_value, var14, is_22_, is, i_24_, i_26_, i_23_);
			}
			model.method1920(~solid == -1 && !opcode98, true, true, true, -1 == ~solid, true, false);
			LocTypeList.result.node = model;
			model.aBoolean3809 = var17;
			LocTypeList.result.shadow = var15;
			return LocTypeList.result;
		}
	}

	LocResult get_animated_pair(SeqType var5, int[][] var7, int var1, int var2, int rotation, int[][] var11, int var4, int type, int current_frameid, int next_frameid, int num, int var10, SoftwarePaletteSprite shadow_sprie, boolean shadowed) {
		long var15;
		if (GLManager.opengl_mode) {
			if (shapes != null) {
				var15 = (type << 3) + (id << 10) - -rotation;
			} else {
				var15 = rotation + (id << 10);
			}
			OpenGLModel model = (OpenGLModel) LocTypeList.somecache.get(var15);
			if (model == null) {
				model = create_opengl_model(rotation, true, true, type);
				if (null == model) {
					return null;
				}
				model.generate_labels_lookup_table();
				model.method1920(false, false, false, true, false, false, true);
				LocTypeList.somecache.put(var15, model);
			}
			boolean var19 = false;
			OpenGLModel var22 = model;
			if (null != var5) {
				var22 = (OpenGLModel) var5.get_animated_loc(model, rotation, current_frameid, next_frameid, num);
				var19 = true;
			}
			if (~type == -11 && 3 < rotation) {
				if (!var19) {
					var22 = (OpenGLModel) var22.copy3(true, true, true);
					var19 = true;
				}

				var22.yaxis_rotate_without_normals(256);
			}
			if (shadowed) {
				LocTypeList.result.shadow = var22.create_shadow_sprite(shadow_sprie);
			} else {
				LocTypeList.result.shadow = null;
			}
			if (terrain_adjust_type != 0) {
				if (!var19) {
					var19 = true;
					var22 = (OpenGLModel) var22.copy3(true, true, true);
				}
				var22.method1919(terrain_adjust_type, terrain_adjust_value, model, var7, var11, var2, var4, var1);
			}
			LocTypeList.result.node = var22;
			return LocTypeList.result;
		} else {
			if (shapes == null) {
				var15 = (id << 10) + rotation;
			} else {
				var15 = rotation + (id << 10) + (type << 3);
			}

			SoftwareModel model = (SoftwareModel) LocTypeList.somecache.get(var15);
			if (model == null) {
				Mesh mesh = create_mesh(rotation, type);
				if (mesh == null) {
					return null;
				}
				model = new SoftwareModel(mesh, 64 + ambient, contrast * 5 + 768, -50, -10, -50);
				LocTypeList.somecache.put(var15, model);
			}

			boolean var21 = false;
			if (var5 != null) {
				var21 = true;
				model = (SoftwareModel) var5.get_animated_model(model, current_frameid, next_frameid, rotation, num);
			}

			if (-11 == ~type && rotation > 3) {
				if (!var21) {
					var21 = true;
					model = (SoftwareModel) model.copy3(true, true, true);
				}

				model.yaxis_rotate_without_normals(256);
			}

			if (terrain_adjust_type != 0) {
				if (!var21) {
					model = (SoftwareModel) model.copy3(true, true, true);
					var21 = true;
				}
				model = model.method1941(terrain_adjust_type, terrain_adjust_value, var7, var11, var2, var4, var1, false);
			}

			LocTypeList.result.node = model;
			return LocTypeList.result;
		}
	}

	public Mesh create_mesh(int rotation, int type) {
		boolean needs_mirroring = mirrored;
		Mesh created_mesh = null;
		if (type == 2 && rotation > 3) {
			needs_mirroring = !needs_mirroring;
		}
		if (shapes == null) {
			// if (type != 10) {
			// return null;
			// }
			if (models == null) {
				return null;
			}
			int num_types = models.length;
			for (int type_index = 0; type_index < num_types; type_index++) {
				int num_models = models[type_index].length;
				for (int model_index = 0; model_index < num_models; model_index++) {
					int model_id = models[type_index][model_index];
					if (needs_mirroring) {
						model_id += 0x1000000;
					}
					created_mesh = Mesh.fromJs5(LocTypeList.models_js5, model_id & 0xffffff, 0);
					if (created_mesh == null) {
						return null;
					}
					if (needs_mirroring) {
						created_mesh.mirror_z();
					}
					if (num_models > 1) {
						modelBuffer2[model_index] = created_mesh;
					}
				}
				if (num_models > 1) {
					created_mesh = new Mesh(modelBuffer2, num_models);
				}

				if (num_types > 1) {
					modelBuffer1[type_index] = created_mesh;
				}
			}
			if (num_types > 1) {
				created_mesh = new Mesh(modelBuffer1, num_types);
			}
		} else {
			int selected_index = -1;
			for (int index = 0; index < shapes.length; index++) {
				if (shapes[index] == type) {
					selected_index = index;
					break;
				}
			}
			if (selected_index == -1) {
				return null;
			}
			int num_models = models[selected_index].length;
			for (int x = 0; x != num_models; x++) {
				int model_id = models[selected_index][x];
				if (needs_mirroring) {
					model_id |= 0x1000000;
				}
				created_mesh = Mesh.fromJs5(LocTypeList.models_js5, model_id & 0xffffff, 0);
				if (created_mesh == null) {
					return null;
				}
				if (needs_mirroring) {
					created_mesh.mirror_z();
				}
				if (num_models > 1) {
					modelBuffer1[x] = created_mesh;
				}
			}
			if (num_models > 1) {
				created_mesh = new Mesh(modelBuffer1, num_models);
			}
		}
		boolean needs_resize;
		if (resize_x == 128 && resize_y == 128 && resize_z == 128) {
			needs_resize = false;
		} else {
			needs_resize = true;
		}
		boolean needs_translate;
		if (offset_x == 0 && offset_y == 0 && offset_z == 0) {
			needs_translate = false;
		} else {
			needs_translate = true;
		}
		Mesh mesh = new Mesh(created_mesh, rotation == 0 && !needs_resize && !needs_translate, recolor_src == null, retex_src == null, true);
		if (type == 4 && rotation > 3) {
			mesh.yaxis_rotate(256);
			mesh.translate(45, 0, -45);
		}
		rotation &= 0x3;
		if (rotation == 1) {
			mesh.rotation90();
		} else if (rotation == 2) {
			mesh.rotate180();
		} else if (rotation == 3) {
			mesh.rotate270();
		}
		if (recolor_src != null) {
			for (int recol_index = 0; recol_index < recolor_src.length; recol_index++) {
				if (recolor_palette_indices != null && recol_index < recolor_palette_indices.length) {
					mesh.recolor(recolor_src[recol_index], GameClient.client_palette[recolor_palette_indices[recol_index] & 0xff]);
				} else {
					mesh.recolor(recolor_src[recol_index], recolor_dst[recol_index]);
				}
			}
		}
		if (retex_src != null) {
			for (int retex_index = 0; retex_index < retex_src.length; retex_index++) {
				mesh.retexture(retex_src[retex_index], retex_dst[retex_index]);
			}
		}
		if (tint_opacity != 0) {
			mesh.tint(tint_hue, tint_saturation, tint_lightness, tint_opacity);
		}
		if (needs_resize) {
			mesh.resize(resize_x, resize_y, resize_z);
		}
		if (needs_translate) {
			mesh.translate(offset_x, offset_y, offset_z);
		}
		return mesh;
	}

	private OpenGLModel create_opengl_model(int var1, boolean needs_mirroring, boolean var3, int type) {
		int var6 = ambient + 64;
		int var7 = 5 * contrast + 768;
		int uid = id << 10 | (type >= 4 && type <= 8 ? 4 : type) << 3 | var1;
		OpenGLModel created_model = (OpenGLModel) LocTypeList.opengl_cache.get(uid);
		if (created_model == null) {
			Mesh created_mesh = null;
			if (shapes != null) {
				int shape_index = -1;
				for (int index = 0; index < shapes.length; ++index) {
					if (shapes[index] == type) {
						shape_index = index;
						break;
					}
				}
				if (shape_index == -1) {
					return null;
				}
				int num_models = models[shape_index].length;
				for (int model_index = 0; model_index < num_models; model_index++) {
					int model_id = models[shape_index][model_index];
					if (needs_mirroring) {
						model_id |= 0x1000000;
					}
					created_mesh = Mesh.fromJs5(LocTypeList.models_js5, model_id & 0xffffff, 0);
					if (created_mesh == null) {
						return null;
					}
					if (num_models > 1) {
						modelBuffer1[model_index] = created_mesh;
					}
				}
				if (num_models > 1) {
					created_mesh = new Mesh(modelBuffer1, num_models);
				}
			} else {
				// if (type != 10) {
				// return null;
				// }
				if (models == null) {
					return null;
				}
				int num_types = models.length;
				if (num_types == 0) {
					return null;
				}
				for (int type_index = 0; type_index < num_types; type_index++) {
					int num_models = models[type_index].length;
					for (int model_index = 0; model_index < num_models; model_index++) {
						int model_id = models[type_index][model_index];
						if (needs_mirroring) {
							model_id += 0x1000000;
						}
						created_mesh = Mesh.fromJs5(LocTypeList.models_js5, model_id & 0xffffff, 0);
						if (created_mesh == null) {
							return null;
						}
						if (num_models > 1) {
							modelBuffer2[model_index] = created_mesh;
						}
					}
					if (num_models > 1) {
						created_mesh = new Mesh(modelBuffer2, num_models);
					}
					if (num_types > 1) {
						modelBuffer1[type_index] = created_mesh;
					}
				}
				if (num_types > 1) {
					created_mesh = new Mesh(modelBuffer1, num_types);
				}
			}
			created_model = new OpenGLModel(created_mesh, var6, var7, needs_mirroring);
			LocTypeList.opengl_cache.put(uid, created_model);
		}
		boolean var14 = mirrored;
		if (~type == -3 && var1 > 3) {
			var14 = !var14;
		}

		boolean var15 = 128 == resize_y && -1 == ~offset_y;
		boolean var18 = -1 == ~var1 && 128 == resize_x && ~resize_z == -129 && ~offset_x == -1 && offset_z == 0 && !var14;
		OpenGLModel var19 = created_model.duplicate(var18, var15, recolor_src == null, true, ~created_model.get_ambient() == ~var6, -1 == ~var1 && !var14, var3, ~var7 == ~created_model.get_contrast(), true, !var14, retex_src == null);
		if (var14) {
			var19.mirror_z();
		}
		if (type == 4 && 3 < var1) {
			var19.yaxis_rotate(256);
			var19.translate(45, 0, -45);
		}
		var1 &= 3;
		if (1 == var1) {
			var19.rotate90();
		} else if (~var1 != -3) {
			if (~var1 == -4) {
				var19.rotate270();
			}
		} else {
			var19.rotate180();
		}
		if (null != recolor_src) {
			for (int var12 = 0; ~recolor_src.length < ~var12; ++var12) {
				var19.recolor(recolor_src[var12], recolor_dst[var12]);
			}
		}
		if (null != retex_src) {
			for (int var12 = 0; ~retex_src.length < ~var12; ++var12) {
				var19.retexture(retex_src[var12], retex_dst[var12]);
			}
		}
		if (resize_x != 128 || -129 != ~resize_y || ~resize_z != -129) {
			var19.scale(resize_x, resize_y, resize_z);
		}
		if (-1 != ~offset_x || offset_y != 0 || 0 != offset_z) {
			var19.translate(offset_x, offset_y, offset_z);
		}
		if (var6 != var19.get_ambient()) {
			var19.set_ambient(var6);
		}
		if (var19.get_contrast() != var7) {
			var19.set_contrast(var7);
		}
		return var19;
	}

	public static void method637() {
		StaticMethods2.aClass16_3762 = null;
		StaticMethods2.aClass16_3805 = null;
		StaticMethods2.aClass16_3739 = null;
	}

	boolean areModelsCached(int i) {
		if (models == null) {
			return true;
		}
		boolean cached = true;
		int amount = 0;
		for (int[] models : models) {
			int childCount = models.length;
			for (int inner = 0; inner != childCount; ++inner) {
				cached &= LocTypeList.models_js5.is_file_cached(models[inner], 0);
				if (!cached) {
					amount++;
				}
			}
		}
		if (amount < 2) {
			return true;
		}
		return cached;
	}

	void setObjectClipping() {
		if (solid == -1) {
			solid = 0;
			if (models != null && (shapes == null || shapes[0] == 10)) {
				solid = 1;
			}
			for (int i_44_ = 0; i_44_ < 5; i_44_++) {
				if (actions[i_44_] != null) {
					solid = 1;
					break;
				}
			}
		}
		if (anInt2975 == -1) {
			anInt2975 = clipping_type == 0 ? 0 : 1;
		}
	}

	boolean isVisible() {
		if (morphisms == null) {
			if (anInt2996 == -1 && anIntArray3801 == null) {
				return false;
			}
			return true;
		}
		for (int childrenId : morphisms) {
			if (childrenId != -1) {
				LocType def = LocTypeList.list(childrenId);
				if (def.anInt2996 != -1 || def.anIntArray3801 != null) {
					return true;
				}
			}
		}
		return false;
	}

	public LocType morph() {
		int configValue = -1;
		if (morph_varbit != -1) {
			configValue = Varbit.getConfigFileValue(morph_varbit);
		} else if (morph_var != -1) {
			configValue = GameClient.configs[morph_var];
		}
		if (configValue < 0 || morphisms.length + -1 <= configValue || morphisms[configValue] == -1) {
			int objectId = morphisms[-1 + morphisms.length];
			if (objectId != -1) {
				return LocTypeList.list(objectId);
			}
			return null;
		}
		return LocTypeList.list(morphisms[configValue]);
	}

	boolean areModelTypesCached(int type) {
		boolean flag = true;
		if (shapes != null) {
			for (int i_57_ = 0; i_57_ < shapes.length; i_57_++) {
				if (shapes[i_57_] == type) {
					int childCount = models[i_57_].length;
					for (int i1 = 0; i1 != childCount; ++i1) {
						flag &= LocTypeList.models_js5.is_file_cached(models[i_57_][i1], 0);
					}
					break;
				}
			}
			return flag;
		}
		if (models == null) {
			return true;
		}
		// if (type != 10) {
		// return true;
		// }
		boolean bool = true;
		for (int[] object_model_id : models) {
			int childCount = object_model_id.length;
			for (int i1 = 0; i1 != childCount; ++i1) {
				bool &= LocTypeList.models_js5.is_file_cached(object_model_id[i1], 0);
			}
		}
		return bool;
	}
}
