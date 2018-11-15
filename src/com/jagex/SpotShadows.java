package com.jagex;

import com.jagex.core.collections.memory.AdvancedMemoryCache;
import com.jagex.graphics.runetek4.media.Model;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.model.OpenGLModel;
import com.rs2.client.scene.Scene;

import me.waliedyassen.graphics.rasterizer.Rasterizer;

/**
 * @author Walied K. Yassen
 */
public class SpotShadows {

	static AdvancedMemoryCache spotshadows_cache = new AdvancedMemoryCache(32);

	static final Model get_spotshadow_model(int var0, boolean var1, SeqType var2, int var3, int var4, int var5, int var6, int var7, Model var8, int var9, int var10, int var11, int var12, byte var13) {
		long var14 = ((long) var4 << 48) + var7 + (var0 << 16) - -(var12 << 24) + ((long) var6 << 32);
		Model var16 = (Model) spotshadows_cache.get(var14);
		int var21;
		int var23;
		int var25;
		int var24;
		int var28;
		if (var16 == null) {
			byte var17;
			if (1 == var7) {
				var17 = 9;
			} else if (var7 == 2) {
				var17 = 12;
			} else if (-4 != ~var7) {
				if (4 == var7) {
					var17 = 18;
				} else {
					var17 = 21;
				}
			} else {
				var17 = 15;
			}

			int[] var19 = new int[] { 64, 96, 128 };
			byte var18 = 3;
			Mesh var20 = new Mesh(1 + var18 * var17, -var17 + var17 * var18 * 2, 0);
			var21 = var20.add_vertex(0, 0, 0);
			int[][] var22 = new int[var18][var17];

			for (var23 = 0; ~var23 > ~var18; ++var23) {
				var24 = var19[var23];
				var25 = var19[var23];

				for (int var26 = 0; var26 < var17; ++var26) {
					int var27 = (var26 << 11) / var17;
					int var29 = var5 - -(Rasterizer.COSINE[var27] * var25) >> 16;
					var28 = var3 + Rasterizer.SINE[var27] * var24 >> 16;
					var22[var23][var26] = var20.add_vertex(var28, 0, var29);
				}
			}

			for (var23 = 0; var23 < var18; ++var23) {
				var24 = (256 * var23 - -128) / var18;
				var25 = 256 + -var24;
				byte var38 = (byte) (var12 * var24 + var0 * var25 >> 8);
				short var39 = (short) (((var6 & 127) * var25 + (127 & var4) * var24 & 32512) + (var25 * (var6 & 896) + var24 * (var4 & 896) & 229376) + (var24 * (var4 & '\ufc00') + ('\ufc00' & var6) * var25 & 16515072) >> 8);

				for (var28 = 0; var28 < var17; ++var28) {
					if (var23 == 0) {
						var20.add_face(var21, var22[0][(1 + var28) % var17], var22[0][var28], (byte) 1, var39, var38);
					} else {
						var20.add_face(var22[var23 - 1][var28], var22[var23 + -1][(var28 + 1) % var17], var22[var23][(var28 - -1) % var17], (byte) 1, var39, var38);
						var20.add_face(var22[-1 + var23][var28], var22[var23][(1 + var28) % var17], var22[var23][var28], (byte) 1, var39, var38);
					}
				}
			}

			var16 = var20.method2008(64, 768, -50, -10, -50);
			spotshadows_cache.put(var14, var16);
		}

		int var32 = var7 * 64 + -1;
		if (var13 != -49) {
			return null;
		} else {
			int var33 = -var32;
			int var31 = -var32;
			int var34 = var32;
			int var35 = var8.get_minx();
			AnimFrameset var40 = null;
			var23 = var8.get_maxx();
			var24 = var8.get_minz();
			var25 = var8.get_maxz();
			if (var2 != null) {
				var10 = var2.frames_data[var10];
				var40 = SeqTypeList.load_frameset(var10 >> 16);
				var10 &= '\uffff';
			}

			var21 = var32;
			if (var1) {
				if (1664 < var9 || 384 > var9) {
					var31 -= 128;
				}

				if (var9 > 1152 && var9 < 1920) {
					var34 = var32 + 128;
				}

				if (640 < var9 && ~var9 > -1409) {
					var21 = var32 + 128;
				}

				if (-129 > ~var9 && ~var9 > -897) {
					var33 -= 128;
				}
			}

			if (var21 < var25) {
				var25 = var21;
			}

			if (var33 > var35) {
				var35 = var33;
			}

			if (var24 < var31) {
				var24 = var31;
			}

			if (var34 < var23) {
				var23 = var34;
			}

			if (null == var40) {
				var16 = var16.copy2(true, true, true);
				var16.scale((var23 + -var35) / 2, 128, (var25 - var24) / 2);
				var16.translate((var35 + var23) / 2, 0, (var24 - -var25) / 2);
			} else {
				var16 = var16.copy2(!var40.modifies_alpha(var10), !var40.modifies_color(var10), true);
				var16.scale((var23 + -var35) / 2, 128, (var25 + -var24) / 2);
				var16.translate((var35 + var23) / 2, 0, (var24 + var25) / 2);
				var16.animate_shadow_frame(var40, var10);
			}

			if (var9 != 0) {
				var16.yaxis_rotate_without_normals(var9);
			}

			if (!GLManager.opengl_mode) {
				SoftwareModel var37 = (SoftwareModel) var16;
				if (~Scene.get_average_height(ObjType.localHeight, var3 - -var35, var5 - -var24) != ~var11 || var11 != Scene.get_average_height(ObjType.localHeight, var3 + var23, var5 - -var25)) {
					for (var28 = 0; ~var37.num_vertices < ~var28; ++var28) {
						var37.vertices_y[var28] += -var11 + Scene.get_average_height(ObjType.localHeight, var3 + var37.vertices_x[var28], var5 + var37.vertices_z[var28]);
					}

					var37.bounds_updated = false;
				}
			} else {
				OpenGLModel var36 = (OpenGLModel) var16;
				if (~Scene.get_average_height(ObjType.localHeight, var3 + var35, var24 + var5) != ~var11 || ~Scene.get_average_height(ObjType.localHeight, var23 + var3, var5 - -var25) != ~var11) {
					for (var28 = 0; var28 < var36.num_vertices; ++var28) {
						var36.vertices_y[var28] += -var11 + Scene.get_average_height(ObjType.localHeight, var36.vertices_x[var28] - -var3, var5 + var36.vertices_z[var28]);
					}

					var36.bounding_box.is_clean = false;
					var36.positions_attribute.is_clean = false;
				}
			}

			return var16;
		}
	}

}
