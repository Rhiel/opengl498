package com.jagex;

import com.jagex.graphics.runetek4.media.Model;
import com.jagex.launcher.GamePlayConfiguration;
import com.rs2.client.scene.Scene;

import me.waliedyassen.graphics.rasterizer.Rasterizer;
import me.waliedyassen.materials.MaterialRaw;

public class SoftwareModel extends Model {

	public static final float PI = 3.1415927F;
	public static final float PI2 = PI * 2;

	public static int origin_y;
	public static int origin_z;
	public static int[][] anIntArrayArray3938;
	public static int[] anIntArray3944;
	public static int[] anIntArray3936;
	public static int[] depthListIndices;
	public static boolean[] hasRestrictedEdges;
	public static int[] projected_vertices_z;
	public static float vertexPerspectiveZB[] = new float[8000];
	public static int[] anIntArray3922;
	public static int origin_x;
	public static int[] projected_vertices_y;
	public static boolean[] out_of_screen;
	public static int[] camera_vertex_x;
	public static int[] camera_vertex_y;
	public static int[][] faceLists;
	public static int[] anIntArray1679;
	public static int[] projected_vertices_x;
	public static int[] camera_vertex_z;
	public static int[] anIntArray1678;
	public static int[] anIntArray3941;
	public static int[] anIntArray4362;
	public static byte[] aByteArray4363;
	public static byte[] aByteArray3933;
	public static SoftwareModel modelInstance = new SoftwareModel();
	public static byte[] aByteArray4336 = new byte[1];
	public static SoftwareModel model;
	private static boolean within_origin = false;

	public int num_vertices;
	public int num_faces;
	public int num_textures;
	public byte priority;
	public int[] vertices_x;
	public int[] vertices_y;
	public int[] vertices_z;
	public int[] faces_a;
	public int[] faces_b;
	public int[] faces_c;
	public byte[] faces_priority;
	public byte[] faces_mapping;
	public short[] faces_colour;
	public int[] faces_colour_a;
	public int[] faces_colour_b;
	public int[] faces_colour_c;
	public byte[] faces_alpha;
	public short[] faces_material;
	public int[][] faces_by_label;
	public int[][] vertices_by_label;
	public boolean texture_alpha_blended;
	public boolean texture_animated;
	public short min_x;
	public short max_x;
	public short min_y;
	public short max_y;
	public short min_z;
	public short max_z;
	public short size_2d;
	public short size_3d;
	public boolean bounds_updated;
	// public float[][] umap;
	// public float[][] vmap;
	private int[] textures_mapping_p;
	private int[] textures_mapping_m;
	private int[] textures_mapping_n;
	private boolean animation_precision;
	public short[] vertices_submeshes;
	public short[] faces_submeshes;

	public SoftwareModel() {
		num_textures = 0;
		num_faces = 0;
	}

	public SoftwareModel(Mesh mesh, int ambient, int contrast, int sun_positionx, int sun_positiony, int sun_positionz) {
		num_vertices = 0;
		num_textures = 0;
		num_faces = 0;
		mesh.calculate_normals();
		mesh.calculate_bonetables();
		num_vertices = mesh.num_vertices;
		vertices_x = mesh.vertices_x;
		vertices_y = mesh.vertices_y;
		vertices_z = mesh.vertices_z;
		num_faces = mesh.num_faces;
		faces_a = mesh.faces_a;
		faces_b = mesh.faces_b;
		faces_c = mesh.faces_c;
		faces_colour = mesh.faces_colour;
		faces_priority = mesh.faces_layer;
		faces_alpha = mesh.faces_alpha;
		priority = mesh.priority;
		vertices_by_label = mesh.vertices_by_label;
		faces_by_label = mesh.faces_by_label;
		faces_submeshes = mesh.faces_submeshes;
		vertices_submeshes = mesh.vertices_submeshes;
		int var7 = (int) Math.sqrt(sun_positionx * sun_positionx + sun_positiony * sun_positiony + sun_positionz * sun_positionz);
		int var8 = contrast * var7 >> 8;
		// // creat the faces list.
		// int[] faces_list = new int[num_faces];
		// for (int face = 0; face < num_faces; face++) {
		// faces_list[face] = face;
		// }
		// // TODO: maybe priority
		// umap = new float[num_faces][];
		// vmap = new float[num_faces][];
		// TextureTransformationMatrix texture_transformation_matrix = create_texture_matrix(mesh, faces_list, num_faces);
		// boolean has_materials = false;
		// for (int face_index = 0; face_index < num_faces; face_index++) {
		// int face = face_index;
		// int material_index;
		// if (mesh.faces_texture == null) {
		// material_index = -1;
		// } else {
		// material_index = mesh.faces_texture[face];
		// }
		// int material_id = mesh.faces_material == null ? -1 : mesh.faces_material[face];
		// if (material_id != -1) {
		// has_materials = true;
		// float[] u = umap[face] = new float[3];
		// float[] v = vmap[face] = new float[3];
		// if (material_index == -1) {
		// u[0] = 0.0F;
		// v[0] = 1.0F;
		// u[1] = 1.0F;
		// v[1] = 1.0F;
		// u[2] = 0.0F;
		// v[2] = 0.0F;
		// } else {
		// float[] uv = new float[2];
		// material_index &= 0xff;
		// byte i_150_ = mesh.textures_mapping[material_index];
		// if (i_150_ == 0) {
		// int i_151_ = faces_a[face];
		// int i_152_ = faces_b[face];
		// int i_153_ = faces_c[face];
		// short i_154_ = mesh.textures_mapping_p[material_index];
		// short i_155_ = mesh.textures_mapping_m[material_index];
		// short i_156_ = mesh.textures_mapping_n[material_index];
		// float f = vertices_x[i_154_];
		// float f_157_ = vertices_y[i_154_];
		// float f_158_ = vertices_z[i_154_];
		// float f_159_ = vertices_x[i_155_] - f;
		// float f_160_ = vertices_y[i_155_] - f_157_;
		// float f_161_ = vertices_z[i_155_] - f_158_;
		// float f_162_ = vertices_x[i_156_] - f;
		// float f_163_ = vertices_y[i_156_] - f_157_;
		// float f_164_ = vertices_z[i_156_] - f_158_;
		// float f_165_ = vertices_x[i_151_] - f;
		// float f_166_ = vertices_y[i_151_] - f_157_;
		// float f_167_ = vertices_z[i_151_] - f_158_;
		// float f_168_ = vertices_x[i_152_] - f;
		// float f_169_ = vertices_y[i_152_] - f_157_;
		// float f_170_ = vertices_z[i_152_] - f_158_;
		// float f_171_ = vertices_x[i_153_] - f;
		// float f_172_ = vertices_y[i_153_] - f_157_;
		// float f_173_ = vertices_z[i_153_] - f_158_;
		// float f_174_ = f_160_ * f_164_ - f_161_ * f_163_;
		// float f_175_ = f_161_ * f_162_ - f_159_ * f_164_;
		// float f_176_ = f_159_ * f_163_ - f_160_ * f_162_;
		// float f_177_ = f_163_ * f_176_ - f_164_ * f_175_;
		// float f_178_ = f_164_ * f_174_ - f_162_ * f_176_;
		// float f_179_ = f_162_ * f_175_ - f_163_ * f_174_;
		// float f_180_ = 1.0F / (f_177_ * f_159_ + f_178_ * f_160_ + f_179_ * f_161_);
		// u[0] = (f_177_ * f_165_ + f_178_ * f_166_ + f_179_ * f_167_) * f_180_;
		// u[1] = (f_177_ * f_168_ + f_178_ * f_169_ + f_179_ * f_170_) * f_180_;
		// u[2] = (f_177_ * f_171_ + f_178_ * f_172_ + f_179_ * f_173_) * f_180_;
		// f_177_ = f_160_ * f_176_ - f_161_ * f_175_;
		// f_178_ = f_161_ * f_174_ - f_159_ * f_176_;
		// f_179_ = f_159_ * f_175_ - f_160_ * f_174_;
		// f_180_ = 1.0F / (f_177_ * f_162_ + f_178_ * f_163_ + f_179_ * f_164_);
		// v[0] = (f_177_ * f_165_ + f_178_ * f_166_ + f_179_ * f_167_) * f_180_;
		// v[1] = (f_177_ * f_168_ + f_178_ * f_169_ + f_179_ * f_170_) * f_180_;
		// v[2] = (f_177_ * f_171_ + f_178_ * f_172_ + f_179_ * f_173_) * f_180_;
		// } else {
		// int a = faces_a[face];
		// int b = faces_b[face];
		// int c = faces_c[face];
		// int sx = texture_transformation_matrix.faceCenterX[material_index];
		// int sy = texture_transformation_matrix.faceCenterY[material_index];
		// int sz = texture_transformation_matrix.faceCenterZ[material_index];
		// float[] sc = texture_transformation_matrix.textureMatrix[material_index];
		// byte i_188_ = mesh.textures_direction[material_index];
		// float speed = mesh.textures_speed[material_index] / 256.0F;
		// if (i_150_ == 1) {
		// float f_189_ = mesh.textures_scale_z[material_index] / 1024.0F;
		// cylindricalUV(vertices_x[a], vertices_y[a], vertices_z[a], sx, sy, sz, sc, f_189_, i_188_, speed, uv);
		// u[0] = uv[0];
		// v[0] = uv[1];
		// cylindricalUV(vertices_x[b], vertices_y[b], vertices_z[b], sx, sy, sz, sc, f_189_, i_188_, speed, uv);
		// u[1] = uv[0];
		// v[1] = uv[1];
		// cylindricalUV(vertices_x[c], vertices_y[c], vertices_z[c], sx, sy, sz, sc, f_189_, i_188_, speed, uv);
		// u[2] = uv[0];
		// v[2] = uv[1];
		// float f_190_ = f_189_ / 2.0F;
		// if ((i_188_ & 0x1) == 0) {
		// if (u[1] - u[0] > f_190_) {
		// u[1] -= f_189_;
		// } else if (u[0] - u[1] > f_190_) {
		// u[1] += f_189_;
		// }
		// if (u[2] - u[0] > f_190_) {
		// u[2] -= f_189_;
		// } else if (u[0] - u[2] > f_190_) {
		// u[2] += f_189_;
		// }
		// } else {
		// if (v[1] - v[0] > f_190_) {
		// v[1] -= f_189_;
		// } else if (v[0] - v[1] > f_190_) {
		// v[1] += f_189_;
		// }
		// if (v[2] - v[0] > f_190_) {
		// v[2] -= f_189_;
		// } else if (v[0] - v[2] > f_190_) {
		// v[2] += f_189_;
		// }
		// }
		// } else if (i_150_ == 2) {
		// float f_191_ = mesh.textures_vtrans[material_index] / 256.0F;
		// float f_192_ = mesh.textures_utrans[material_index] / 256.0F;
		// int i_193_ = vertices_x[b] - vertices_x[a];
		// int i_194_ = vertices_y[b] - vertices_y[a];
		// int i_195_ = vertices_z[b] - vertices_z[a];
		// int i_196_ = vertices_x[c] - vertices_x[a];
		// int i_197_ = vertices_y[c] - vertices_y[a];
		// int i_198_ = vertices_z[c] - vertices_z[a];
		// int i_199_ = i_194_ * i_198_ - i_197_ * i_195_;
		// int i_200_ = i_195_ * i_196_ - i_198_ * i_193_;
		// int i_201_ = i_193_ * i_197_ - i_196_ * i_194_;
		// float f_202_ = 64.0F / mesh.textures_scale_x[material_index];
		// float f_203_ = 64.0F / mesh.textures_scale_y[material_index];
		// float f_204_ = 64.0F / mesh.textures_scale_z[material_index];
		// float f_205_ = (i_199_ * sc[0] + i_200_ * sc[1] + i_201_ * sc[2]) / f_202_;
		// float f_206_ = (i_199_ * sc[3] + i_200_ * sc[4] + i_201_ * sc[5]) / f_203_;
		// float f_207_ = (i_199_ * sc[6] + i_200_ * sc[7] + i_201_ * sc[8]) / f_204_;
		// int i_208_ = getCubeFace(f_205_, f_206_, f_207_);
		// rectangularUV(vertices_x[a], vertices_y[a], vertices_z[a], sx, sy, sz, i_208_, sc, i_188_, speed, f_191_, f_192_,
		// uv);
		// u[0] = uv[0];
		// v[0] = uv[1];
		// rectangularUV(vertices_x[b], vertices_y[b], vertices_z[b], sx, sy, sz, i_208_, sc, i_188_, speed, f_191_, f_192_,
		// uv);
		// u[1] = uv[0];
		// v[1] = uv[1];
		// rectangularUV(vertices_x[c], vertices_y[c], vertices_z[c], sx, sy, sz, i_208_, sc, i_188_, speed, f_191_, f_192_,
		// uv);
		// u[2] = uv[0];
		// v[2] = uv[1];
		// } else if (i_150_ == 3) {
		// sphericalUV(vertices_x[a], vertices_y[a], vertices_z[a], sx, sy, sz, sc, i_188_, speed, uv);
		// u[0] = uv[0];
		// v[0] = uv[1];
		// sphericalUV(vertices_x[b], vertices_y[b], vertices_z[b], sx, sy, sz, sc, i_188_, speed, uv);
		// u[1] = uv[0];
		// v[1] = uv[1];
		// sphericalUV(vertices_x[c], vertices_y[c], vertices_z[c], sx, sy, sz, sc, i_188_, speed, uv);
		// u[2] = uv[0];
		// v[2] = uv[1];
		// if ((i_188_ & 0x1) == 0) {
		// if (u[1] - u[0] > 0.5F) {
		// u[1]--;
		// } else if (u[0] - u[1] > 0.5F) {
		// u[1]++;
		// }
		// if (u[2] - u[0] > 0.5F) {
		// u[2]--;
		// } else if (u[0] - u[2] > 0.5F) {
		// u[2]++;
		// }
		// } else {
		// if (v[1] - v[0] > 0.5F) {
		// v[1]--;
		// } else if (v[0] - v[1] > 0.5F) {
		// v[1]++;
		// }
		// if (v[2] - v[0] > 0.5F) {
		// v[2]--;
		// } else if (v[0] - v[2] > 0.5F) {
		// v[2]++;
		// }
		// }
		// }
		// }
		// }
		// }
		// }
		// if (!has_materials) {
		// vmap = null;
		// umap = null;
		// }
		if (mesh.faces_material != null) {
			faces_material = new short[num_faces];
			boolean has_textures = false;
			for (int face = 0; face < num_faces; face++) {
				short material_id = mesh.faces_material[face];
				if (material_id != -1) {
					MaterialRaw material = GraphicTools.get_materials().get_material(material_id);
					if (GraphicSettings.hasDetails() || !material.details_only) {
						faces_material[face] = material_id;
						has_textures = true;
						if (material.speed_u != 0 || material.speed_v != 0) {
							texture_animated = true;
						}
					} else {
						faces_material[face] = (short) -1;
					}
				} else {
					faces_material[face] = (short) -1;
				}
			}
			if (!has_textures) {
				faces_material = null;
			}
		} else {
			faces_material = null;
		}
		if (mesh.num_textures > 0 && mesh.faces_texture != null) {
			int[] var16 = new int[mesh.num_textures];

			int var17;
			for (var17 = 0; var17 < num_faces; ++var17) {
				if (mesh.faces_texture[var17] != -1) {
					++var16[mesh.faces_texture[var17] & 255];
				}
			}

			num_textures = 0;

			for (var17 = 0; var17 < mesh.num_textures; ++var17) {
				if (var16[var17] > 0 && mesh.textures_mapping[var17] == 0) {
					++num_textures;
				}
			}

			textures_mapping_p = new int[num_textures];
			textures_mapping_m = new int[num_textures];
			textures_mapping_n = new int[num_textures];
			var17 = 0;

			int var11;
			for (var11 = 0; var11 < mesh.num_textures; ++var11) {
				if (var16[var11] > 0 && mesh.textures_mapping[var11] == 0) {
					textures_mapping_p[var17] = mesh.textures_mapping_p[var11] & '\uffff';
					textures_mapping_m[var17] = mesh.textures_mapping_m[var11] & '\uffff';
					textures_mapping_n[var17] = mesh.textures_mapping_n[var11] & '\uffff';
					var16[var11] = var17++;
				} else {
					var16[var11] = -1;
				}
			}

			faces_mapping = new byte[num_faces];

			for (var11 = 0; var11 < num_faces; ++var11) {
				if (mesh.faces_texture[var11] != -1) {
					faces_mapping[var11] = (byte) var16[mesh.faces_texture[var11] & 255];
					if (faces_mapping[var11] == -1 && faces_material != null) {
						faces_material[var11] = -1;
					}
				} else {
					faces_mapping[var11] = -1;
				}
			}
		}
		faces_colour_a = new int[num_faces];
		faces_colour_b = new int[num_faces];
		faces_colour_c = new int[num_faces];
		for (int var9 = 0; var9 < num_faces; ++var9) {
			byte var18;
			if (mesh.faces_type == null) {
				var18 = 0;
			} else {
				var18 = mesh.faces_type[var9];
			}

			byte var19;
			if (mesh.faces_alpha == null) {
				var19 = 0;
			} else {
				var19 = mesh.faces_alpha[var9];
			}

			short var12;
			if (faces_material == null) {
				var12 = -1;
			} else {
				var12 = faces_material[var9];
			}

			if (var19 == -2) {
				var18 = 3;
			}

			if (var19 == -1) {
				var18 = 2;
			}

			VertexNormal var13;
			int var14;
			FaceNormal var20;
			if (var12 == -1) {
				if (var18 == 0) {
					int var15 = mesh.faces_colour[var9] & '\uffff';
					if (mesh.vertices_normals_2 != null && mesh.vertices_normals_2[faces_a[var9]] != null) {
						var13 = mesh.vertices_normals_2[faces_a[var9]];
					} else {
						var13 = mesh.vertices_normals_1[faces_a[var9]];
					}

					var14 = ambient + (sun_positionx * var13.x + sun_positiony * var13.y + sun_positionz * var13.z) / (var8 * var13.magnitude) << 17;
					faces_colour_a[var9] = var14 | MapLoader.repackHSL(var15, var14 >> 17);
					if (mesh.vertices_normals_2 != null && mesh.vertices_normals_2[faces_b[var9]] != null) {
						var13 = mesh.vertices_normals_2[faces_b[var9]];
					} else {
						var13 = mesh.vertices_normals_1[faces_b[var9]];
					}

					var14 = ambient + (sun_positionx * var13.x + sun_positiony * var13.y + sun_positionz * var13.z) / (var8 * var13.magnitude) << 17;
					faces_colour_b[var9] = var14 | MapLoader.repackHSL(var15, var14 >> 17);
					if (mesh.vertices_normals_2 != null && mesh.vertices_normals_2[faces_c[var9]] != null) {
						var13 = mesh.vertices_normals_2[faces_c[var9]];
					} else {
						var13 = mesh.vertices_normals_1[faces_c[var9]];
					}

					var14 = ambient + (sun_positionx * var13.x + sun_positiony * var13.y + sun_positionz * var13.z) / (var8 * var13.magnitude) << 17;
					faces_colour_c[var9] = var14 | MapLoader.repackHSL(var15, var14 >> 17);
				} else if (var18 == 1) {
					var20 = mesh.faces_normal[var9];
					var14 = ambient + (sun_positionx * var20.x + sun_positiony * var20.y + sun_positionz * var20.z) / (var8 + var8 / 2) << 17;
					faces_colour_a[var9] = var14 | MapLoader.repackHSL(mesh.faces_colour[var9] & '\uffff', var14 >> 17);
					faces_colour_c[var9] = -1;
				} else if (var18 == 3) {
					faces_colour_a[var9] = 128;
					faces_colour_c[var9] = -1;
				} else {
					faces_colour_c[var9] = -2;
				}
			} else if (var18 == 0) {
				if (mesh.vertices_normals_2 != null && mesh.vertices_normals_2[faces_a[var9]] != null) {
					var13 = mesh.vertices_normals_2[faces_a[var9]];
				} else {
					var13 = mesh.vertices_normals_1[faces_a[var9]];
				}

				var14 = ambient + (sun_positionx * var13.x + sun_positiony * var13.y + sun_positionz * var13.z) / (var8 * var13.magnitude);
				faces_colour_a[var9] = method1937(var14);
				if (mesh.vertices_normals_2 != null && mesh.vertices_normals_2[faces_b[var9]] != null) {
					var13 = mesh.vertices_normals_2[faces_b[var9]];
				} else {
					var13 = mesh.vertices_normals_1[faces_b[var9]];
				}

				var14 = ambient + (sun_positionx * var13.x + sun_positiony * var13.y + sun_positionz * var13.z) / (var8 * var13.magnitude);
				faces_colour_b[var9] = method1937(var14);
				if (mesh.vertices_normals_2 != null && mesh.vertices_normals_2[faces_c[var9]] != null) {
					var13 = mesh.vertices_normals_2[faces_c[var9]];
				} else {
					var13 = mesh.vertices_normals_1[faces_c[var9]];
				}

				var14 = ambient + (sun_positionx * var13.x + sun_positiony * var13.y + sun_positionz * var13.z) / (var8 * var13.magnitude);
				faces_colour_c[var9] = method1937(var14);
			} else if (var18 == 1) {
				var20 = mesh.faces_normal[var9];
				var14 = ambient + (sun_positionx * var20.x + sun_positiony * var20.y + sun_positionz * var20.z) / (var8 + var8 / 2);
				faces_colour_a[var9] = method1937(var14);
				faces_colour_c[var9] = -1;
			} else {
				faces_colour_c[var9] = -2;
			}
		}
		// for (int face = 0; face < num_faces; face++) {
		// byte type;
		// if (mesh.faces_type == null) {
		// type = (byte) 0;
		// } else {
		// type = mesh.faces_type[face];
		// }
		// byte alpha;
		// if (mesh.faces_alpha == null) {
		// alpha = (byte) 0;
		// } else {
		// alpha = mesh.faces_alpha[face];
		// }
		// short material;
		// if (faces_material == null) {
		// material = (short) -1;
		// } else {
		// material = faces_material[face];
		// }
		// if (alpha == -2) {
		// type = (byte) 3;
		// }
		// if (alpha == -1) {
		// type = (byte) 2;
		// }
		// /* normalise the position */
		// int globalsun_x = 0;
		// int globalsun_y = 0;
		// globalsun_x = (int) (globalsun_x * 65535.0F);
		// globalsun_y = (int) (globalsun_y * 65535.0F);
		// /* normalise the rotation */
		// float size = (float) Math.sqrt(globalsun_anglex * globalsun_anglex + globalsun_angley * globalsun_angley +
		// globalsun_anglez * globalsun_anglez);
		// globalsun_anglex = (int) (globalsun_anglex * 65535.0F / size);
		// globalsun_angley = (int) (globalsun_angley * 65535.0F / size);
		// globalsun_anglez = (int) (globalsun_anglez * 65535.0F / size);
		// int global_ambient = (int) (1.0F * 65535.0F);
		// global_ambient >>= 8;
		// if (material == -1) {
		// if (type == 0) {
		// int i_763_ = faces_colour[face] & 0xffff;
		// int i_764_ = (i_763_ & 0x7f) * ambient >> 7;
		// short i_765_ = ColourUtil.hsl_to_hsv(i_763_ & ~0x7f | i_764_);
		// VertexNormal vertex_normal;
		// if (mesh.vertices_normals_2 != null && mesh.vertices_normals_2[faces_a[face]] != null) {
		// vertex_normal = mesh.vertices_normals_2[faces_a[face]];
		// } else {
		// vertex_normal = mesh.vertices_normals_1[faces_a[face]];
		// }
		// int i_766_ = (globalsun_anglex * vertex_normal.x + globalsun_angley * vertex_normal.y + globalsun_anglez *
		// vertex_normal.z) / vertex_normal.magnitude >> 16;
		// int i_767_ = i_766_ > 256 ? globalsun_x : globalsun_y;
		// int i_768_ = (global_ambient >> 1) + (i_767_ * i_766_ >> 17);
		// faces_colour_a[face] = i_768_ << 17 | method1026(i_765_, i_768_);
		// if (mesh.vertices_normals_2 != null && mesh.vertices_normals_2[faces_b[face]] != null) {
		// vertex_normal = mesh.vertices_normals_2[faces_b[face]];
		// } else {
		// vertex_normal = mesh.vertices_normals_1[faces_b[face]];
		// }
		// i_766_ = (globalsun_anglex * vertex_normal.x + globalsun_angley * vertex_normal.y + globalsun_anglez *
		// vertex_normal.z) / vertex_normal.magnitude >> 16;
		// i_767_ = i_766_ > 256 ? globalsun_x : globalsun_y;
		// i_768_ = (global_ambient >> 1) + (i_767_ * i_766_ >> 17);
		// faces_colour_b[face] = i_768_ << 17 | method1026(i_765_, i_768_);
		// if (mesh.vertices_normals_2 != null && mesh.vertices_normals_2[faces_c[face]] != null) {
		// vertex_normal = mesh.vertices_normals_2[faces_c[face]];
		// } else {
		// vertex_normal = mesh.vertices_normals_1[faces_c[face]];
		// }
		// i_766_ = (globalsun_anglex * vertex_normal.x + globalsun_angley * vertex_normal.y + globalsun_anglez *
		// vertex_normal.z) / vertex_normal.magnitude >> 16;
		// i_767_ = i_766_ > 256 ? globalsun_x : globalsun_y;
		// i_768_ = (global_ambient >> 1) + (i_767_ * i_766_ >> 17);
		// faces_colour_c[face] = i_768_ << 17 | method1026(i_765_, i_768_);
		// } else if (type == 1) {
		// int i_769_ = faces_colour[face] & 0xffff;
		// int i_770_ = (i_769_ & 0x7f) * ambient >> 7;
		// short i_771_ = ColourUtil.hsl_to_hsv(i_769_ & ~0x7f | i_770_);
		// FaceNormal face_normal = mesh.faces_normal[face];
		// int i_772_ = globalsun_anglex * face_normal.x + globalsun_angley * face_normal.y + globalsun_anglez * face_normal.z
		// >> 16;
		// int i_773_ = i_772_ > 256 ? globalsun_x : globalsun_y;
		// int i_774_ = (global_ambient >> 1) + (i_773_ * i_772_ >> 17);
		// faces_colour_a[face] = i_774_ << 17 | method1026(i_771_, i_774_);
		// faces_colour_c[face] = -1;
		// } else if (type == 3) {
		// faces_colour_a[face] = 128;
		// faces_colour_c[face] = -1;
		// } else {
		// faces_colour_c[face] = -2;
		// }
		// } else {
		// int i_775_ = faces_colour[face] & 0xffff;
		// if (type == 0) {
		// VertexNormal vertexNormal;
		// if (mesh.vertices_normals_2 != null && mesh.vertices_normals_2[faces_a[face]] != null) {
		// vertexNormal = mesh.vertices_normals_2[faces_a[face]];
		// } else {
		// vertexNormal = mesh.vertices_normals_1[faces_a[face]];
		// }
		// int i_776_ = (globalsun_anglex * vertexNormal.x + globalsun_angley * vertexNormal.y + globalsun_anglez *
		// vertexNormal.z) / vertexNormal.magnitude >> 16;
		// int i_777_ = i_776_ > 256 ? globalsun_x : globalsun_y;
		// int i_778_ = method1027((global_ambient >> 2) + (i_777_ * i_776_ >> 18));
		// faces_colour_a[face] = i_778_ << 24 | method4552(i_775_, material, i_778_);
		// if (mesh.vertices_normals_2 != null && mesh.vertices_normals_2[faces_b[face]] != null) {
		// vertexNormal = mesh.vertices_normals_2[faces_b[face]];
		// } else {
		// vertexNormal = mesh.vertices_normals_1[faces_b[face]];
		// }
		// i_776_ = (globalsun_anglex * vertexNormal.x + globalsun_angley * vertexNormal.y + globalsun_anglez * vertexNormal.z)
		// / vertexNormal.magnitude >> 16;
		// i_777_ = i_776_ > 256 ? globalsun_x : globalsun_y;
		// i_778_ = method1027((global_ambient >> 2) + (i_777_ * i_776_ >> 18));
		// faces_colour_b[face] = i_778_ << 24 | method4552(i_775_, material, i_778_);
		// if (mesh.vertices_normals_2 != null && mesh.vertices_normals_2[faces_c[face]] != null) {
		// vertexNormal = mesh.vertices_normals_2[faces_c[face]];
		// } else {
		// vertexNormal = mesh.vertices_normals_1[faces_c[face]];
		// }
		// i_776_ = (globalsun_anglex * vertexNormal.x + globalsun_angley * vertexNormal.y + globalsun_anglez * vertexNormal.z)
		// / vertexNormal.magnitude >> 16;
		// i_777_ = i_776_ > 256 ? globalsun_x : globalsun_y;
		// i_778_ = method1027((global_ambient >> 2) + (i_777_ * i_776_ >> 18));
		// faces_colour_c[face] = i_778_ << 24 | method4552(i_775_, material, i_778_);
		// } else if (type == 1) {
		// FaceNormal face_normal = mesh.faces_normal[face];
		// int i_779_ = globalsun_anglex * face_normal.x + globalsun_angley * face_normal.y + globalsun_anglez * face_normal.z
		// >> 16;
		// int i_780_ = i_779_ > 256 ? globalsun_x : globalsun_y;
		// int i_781_ = method1027((global_ambient >> 2) + (i_780_ * i_779_ >> 18));
		// faces_colour_a[face] = i_781_ << 24 | method4552(i_775_, material, i_781_);
		// faces_colour_c[face] = -1;
		// } else {
		// faces_colour_c[face] = -2;
		// }
		// }
		// }
	}

	public SoftwareModel(SoftwareModel[] submeshes, int num_submeshes) {
		num_faces = 0;
		boolean has_faces_priority = false;
		boolean has_faces_alpha = false;
		boolean has_faces_texture = false;
		boolean has_faces_material = false;
		num_vertices = 0;
		num_faces = 0;
		priority = (byte) -1;
		for (int i_357_ = 0; i_357_ < num_submeshes; i_357_++) {
			SoftwareModel model = submeshes[i_357_];
			if (model != null) {
				num_vertices += model.num_vertices;
				num_faces += model.num_faces;
				if (model.faces_priority != null) {
					has_faces_priority = true;
				} else {
					if (priority == -1) {
						priority = model.priority;
					}
					if (priority != model.priority) {
						has_faces_priority = true;
					}
				}
				has_faces_alpha = has_faces_alpha | model.faces_alpha != null;
				has_faces_texture = has_faces_texture | model.faces_material != null;
				has_faces_material = has_faces_material | model.faces_mapping != null;
			}
		}
		vertices_x = new int[num_vertices];
		vertices_y = new int[num_vertices];
		vertices_z = new int[num_vertices];
		faces_a = new int[num_faces];
		faces_b = new int[num_faces];
		faces_c = new int[num_faces];
		faces_colour = new short[num_faces];
		faces_colour_a = new int[num_faces];
		faces_colour_b = new int[num_faces];
		faces_colour_c = new int[num_faces];
		if (has_faces_priority) {
			faces_priority = new byte[num_faces];
		}
		if (has_faces_alpha) {
			faces_alpha = new byte[num_faces];
		}
		if (has_faces_texture) {
			faces_material = new short[num_faces];
		}
		if (has_faces_material) {
			faces_mapping = new byte[num_faces];
		}
		if (num_textures > 0) {
			textures_mapping_p = new int[num_textures];
			textures_mapping_m = new int[num_textures];
			textures_mapping_n = new int[num_textures];
		}
		// umap = new float[num_faces][];
		// vmap = new float[num_faces][];
		num_vertices = 0;
		num_faces = 0;
		for (int submesh_index = 0; submesh_index < num_submeshes; submesh_index++) {
			SoftwareModel submesh = submeshes[submesh_index];
			if (submesh != null) {
				for (int i_361_ = 0; i_361_ < submesh.num_faces; i_361_++) {
					faces_a[num_faces] = submesh.faces_a[i_361_] + num_vertices;
					faces_b[num_faces] = submesh.faces_b[i_361_] + num_vertices;
					faces_c[num_faces] = submesh.faces_c[i_361_] + num_vertices;
					faces_colour[num_faces] = submesh.faces_colour[i_361_];
					faces_colour_a[num_faces] = submesh.faces_colour_a[i_361_];
					faces_colour_b[num_faces] = submesh.faces_colour_b[i_361_];
					faces_colour_c[num_faces] = submesh.faces_colour_c[i_361_];
					// umap[num_faces] = submesh.umap[i_361_];
					// umap[num_faces] = submesh.vmap[i_361_];
					if (has_faces_priority) {
						if (submesh.faces_priority != null) {
							faces_priority[num_faces] = submesh.faces_priority[i_361_];
						} else {
							faces_priority[num_faces] = submesh.priority;
						}
					}
					if (has_faces_alpha && submesh.faces_alpha != null) {
						faces_alpha[num_faces] = submesh.faces_alpha[i_361_];
					}
					if (has_faces_texture) {
						if (submesh.faces_material != null) {
							faces_material[num_faces] = submesh.faces_material[i_361_];
						} else {
							faces_material[num_faces] = (short) -1;
						}
					}
					if (has_faces_material) {
						if (submesh.faces_mapping != null && submesh.faces_mapping[i_361_] != -1) {
							faces_mapping[num_faces] = (byte) (submesh.faces_mapping[i_361_] + num_textures);
						} else {
							faces_mapping[num_faces] = (byte) -1;
						}
					}
					num_faces++;
				}
				for (int var11 = 0; var11 < submesh.num_textures; ++var11) {
					textures_mapping_p[num_textures] = submesh.textures_mapping_p[var11] + num_vertices;
					textures_mapping_m[num_textures] = submesh.textures_mapping_m[var11] + num_vertices;
					textures_mapping_n[num_textures] = submesh.textures_mapping_n[var11] + num_vertices;
					++num_textures;
				}
				for (int i_363_ = 0; i_363_ < submesh.num_vertices; i_363_++) {
					vertices_x[num_vertices] = submesh.vertices_x[i_363_];
					vertices_y[num_vertices] = submesh.vertices_y[i_363_];
					vertices_z[num_vertices] = submesh.vertices_z[i_363_];
					num_vertices++;
				}
			}
		}
	}

	private final void method1936() {
		for (int var4 = 0; var4 < num_faces; ++var4) {
			short var3 = faces_material != null ? faces_material[var4] : -1;
			if (var3 == -1) {
				int var1 = faces_colour[var4] & '\uffff';
				int var2;
				if (faces_colour_c[var4] == -1) {
					var2 = faces_colour_a[var4] & -131072;
					faces_colour_a[var4] = var2 | MapLoader.repackHSL(var1, var2 >> 17);
				} else if (faces_colour_c[var4] != -2) {
					var2 = faces_colour_a[var4] & -131072;
					faces_colour_a[var4] = var2 | MapLoader.repackHSL(var1, var2 >> 17);
					var2 = faces_colour_b[var4] & -131072;
					faces_colour_b[var4] = var2 | MapLoader.repackHSL(var1, var2 >> 17);
					var2 = faces_colour_c[var4] & -131072;
					faces_colour_c[var4] = var2 | MapLoader.repackHSL(var1, var2 >> 17);
				}
			}
		}

	}

	@Override
	public int get_size2d() {
		if (!bounds_updated) {
			calculate_bounds();
		}
		return size_2d;
	}

	public int method4552(int i, short i_844_, int i_845_) {
		int i_846_ = ColourUtil.hslToRgbTable[method1026(i, i_845_)];
		MaterialRaw jagexTexture = GraphicTools.get_materials().get_material(i_844_ & 0xffff);
		int i_847_ = jagexTexture.intensity & 0xff;
		if (i_847_ != 0) {
			int i_848_ = 131586 * i_845_;
			if (i_847_ == 256) {
				i_846_ = i_848_;
			} else {
				int i_850_ = 256 - i_847_;
				i_846_ = ((i_848_ & 0xff00ff) * i_847_ + (i_846_ & 0xff00ff) * i_850_ & ~0xff00ff) + ((i_848_ & 0xff00) * i_847_ + (i_846_ & 0xff00) * i_850_ & 0xff0000) >> 8;
			}
		}
		int i_851_ = jagexTexture.brightness & 0xff;
		if (i_851_ != 0) {
			i_851_ += 256;
			int i_852_ = ((i_846_ & 0xff0000) >> 16) * i_851_;
			if (i_852_ > 65535) {
				i_852_ = 65535;
			}
			int i_853_ = ((i_846_ & 0xff00) >> 8) * i_851_;
			if (i_853_ > 65535) {
				i_853_ = 65535;
			}
			int i_854_ = (i_846_ & 0xff) * i_851_;
			if (i_854_ > 65535) {
				i_854_ = 65535;
			}
			i_846_ = (i_852_ << 8 & 0xff0000) + (i_853_ & 0xff00) + (i_854_ >> 8);
		}
		return i_846_;
	}

	public Model copy(boolean bool, SoftwareModel class38_sub1_sub1_0_, byte[] bs) {
		class38_sub1_sub1_0_.num_vertices = num_vertices;
		class38_sub1_sub1_0_.num_faces = num_faces;
		class38_sub1_sub1_0_.num_textures = num_textures;
		if (class38_sub1_sub1_0_.vertices_x == null || class38_sub1_sub1_0_.vertices_x.length < num_vertices) {
			class38_sub1_sub1_0_.vertices_x = new int[num_vertices + 100];
			class38_sub1_sub1_0_.vertices_y = new int[num_vertices + 100];
			class38_sub1_sub1_0_.vertices_z = new int[num_vertices + 100];
		}
		for (int i = 0; i < num_vertices; i++) {
			class38_sub1_sub1_0_.vertices_x[i] = vertices_x[i];
			class38_sub1_sub1_0_.vertices_y[i] = vertices_y[i];
			class38_sub1_sub1_0_.vertices_z[i] = vertices_z[i];
		}
		if (bool) {
			class38_sub1_sub1_0_.faces_alpha = faces_alpha;
		} else {
			class38_sub1_sub1_0_.faces_alpha = bs;
			if (faces_alpha == null) {
				for (int i = 0; i < num_faces; i++) {
					class38_sub1_sub1_0_.faces_alpha[i] = (byte) 0;
				}
			} else {
				for (int i = 0; i < num_faces; i++) {
					class38_sub1_sub1_0_.faces_alpha[i] = faces_alpha[i];
				}
			}
		}
		class38_sub1_sub1_0_.faces_a = faces_a;
		class38_sub1_sub1_0_.faces_b = faces_b;
		class38_sub1_sub1_0_.faces_c = faces_c;
		// class38_sub1_sub1_0_.umap = umap;
		// class38_sub1_sub1_0_.vmap = vmap;
		class38_sub1_sub1_0_.faces_colour_a = faces_colour_a;
		class38_sub1_sub1_0_.faces_colour_b = faces_colour_b;
		class38_sub1_sub1_0_.faces_colour_c = faces_colour_c;
		class38_sub1_sub1_0_.faces_priority = faces_priority;
		class38_sub1_sub1_0_.faces_mapping = faces_mapping;
		class38_sub1_sub1_0_.faces_material = faces_material;
		class38_sub1_sub1_0_.textures_mapping_p = textures_mapping_p;
		class38_sub1_sub1_0_.textures_mapping_m = textures_mapping_m;
		class38_sub1_sub1_0_.textures_mapping_n = textures_mapping_n;
		class38_sub1_sub1_0_.priority = priority;
		class38_sub1_sub1_0_.vertices_by_label = vertices_by_label;
		class38_sub1_sub1_0_.faces_by_label = faces_by_label;
		class38_sub1_sub1_0_.renders_in_one_tile = renders_in_one_tile;
		class38_sub1_sub1_0_.faces_submeshes = faces_submeshes;
		class38_sub1_sub1_0_.vertices_submeshes = vertices_submeshes;
		class38_sub1_sub1_0_.bounds_updated = false;
		return class38_sub1_sub1_0_;
	}

	public void animate_transform(int transType, int[] skinList, int xOffset, int yOffset, int zOffset) {
		int skinlistCount = skinList.length;
		if (transType == 0) {
			int count = 0;
			origin_x = 0;
			origin_y = 0;
			origin_z = 0;
			for (int i_6_ = 0; i_6_ < skinlistCount; i_6_++) {
				int i_7_ = skinList[i_6_];
				if (i_7_ < vertices_by_label.length) {
					int[] is_8_ = vertices_by_label[i_7_];
					for (int i_10_ : is_8_) {
						origin_x += vertices_x[i_10_];
						origin_y += vertices_y[i_10_];
						origin_z += vertices_z[i_10_];
						count++;
					}
				}
			}
			if (count > 0) {
				origin_x = origin_x / count + xOffset;
				origin_y = origin_y / count + yOffset;
				origin_z = origin_z / count + zOffset;
				return;
			} else {
				origin_x = xOffset;
				origin_y = yOffset;
				origin_z = zOffset;
				return;
			}
		} else if (transType == 1) {
			for (int k1 = 0; k1 < skinlistCount; k1++) {
				int l2 = skinList[k1];
				if (l2 < vertices_by_label.length) {
					int ai1[] = vertices_by_label[l2];
					for (int element : ai1) {
						int j5 = element;
						vertices_x[j5] += xOffset >> 2;
						vertices_y[j5] += yOffset >> 2;
						vertices_z[j5] += zOffset >> 2;
					}
				}
			}
			return;
		} else if (transType == 2) {
			for (int l1 = 0; l1 < skinlistCount; l1++) {
				int i3 = skinList[l1];
				if (i3 < vertices_by_label.length) {
					int[] ai2 = vertices_by_label[i3];
					for (int element2 : ai2) {
						int k5 = element2;
						vertices_x[k5] -= origin_x;
						vertices_y[k5] -= origin_y;
						vertices_z[k5] -= origin_z;
						if (zOffset != 0) {
							int j7 = MathUtils.SINE[zOffset];
							int i8 = MathUtils.COSINE[zOffset];
							int l8 = vertices_y[k5] * j7 + vertices_x[k5] * i8 + 16383 >> 14;
							vertices_y[k5] = vertices_y[k5] * i8 - vertices_x[k5] * j7 + 16383 >> 14;
							vertices_x[k5] = l8;
						}
						if (xOffset != 0) {
							int i_24_ = MathUtils.SINE[xOffset];
							int i_25_ = MathUtils.COSINE[xOffset];
							int i_26_ = vertices_y[k5] * i_25_ - vertices_z[k5] * i_24_ + 16383 >> 14;
							vertices_z[k5] = vertices_y[k5] * i_24_ + vertices_z[k5] * i_25_ + 16383 >> 14;
							vertices_y[k5] = i_26_;
						}
						if (yOffset != 0) {
							int i_27_ = MathUtils.SINE[yOffset];
							int i_28_ = MathUtils.COSINE[yOffset];
							int i_29_ = vertices_z[k5] * i_27_ + vertices_x[k5] * i_28_ + 16383 >> 14;
							vertices_z[k5] = vertices_z[k5] * i_28_ - vertices_x[k5] * i_27_ + 16383 >> 14;
							vertices_x[k5] = i_29_;
						}
						vertices_x[k5] += origin_x;
						vertices_y[k5] += origin_y;
						vertices_z[k5] += origin_z;
					}
				}
			}
		} else if (transType == 3) {
			for (int i_30_ = 0; i_30_ < skinlistCount; i_30_++) {
				int i_31_ = skinList[i_30_];
				if (i_31_ < vertices_by_label.length) {
					int[] is_32_ = vertices_by_label[i_31_];
					for (int element : is_32_) {
						int i_34_ = element;
						vertices_x[i_34_] -= origin_x;
						vertices_y[i_34_] -= origin_y;
						vertices_z[i_34_] -= origin_z;
						vertices_x[i_34_] = vertices_x[i_34_] * xOffset >> 7;
						vertices_y[i_34_] = vertices_y[i_34_] * yOffset >> 7;
						vertices_z[i_34_] = vertices_z[i_34_] * zOffset >> 7;// thi is right in 667 decoder for this
						vertices_x[i_34_] += origin_x;
						vertices_y[i_34_] += origin_y;
						vertices_z[i_34_] += origin_z;
					}
				}
			}
		} else if (transType == 5 && faces_by_label != null) {
			for (int i_35_ = 0; i_35_ < skinlistCount; i_35_++) {
				int i_36_ = skinList[i_35_];
				if (i_36_ < faces_by_label.length) {
					int[] is_37_ = faces_by_label[i_36_];
					for (int element : is_37_) {
						int i_39_ = element;
						int i_40_ = (faces_alpha[i_39_] & 0xff) + xOffset * 8;
						if (i_40_ < 0) {
							i_40_ = 0;
						} else if (i_40_ > 255) {
							i_40_ = 255;
						}
						faces_alpha[i_39_] = (byte) i_40_;
					}
				}
			}
		} else if (transType == 7) {
			/* */
		} else if (transType == 8) {
			/* requires upgraded engine */
		} else if (transType == 10) {
			/* requires upgraded engine */
		} else if (transType == 9) {
			/* requires upgraded engine */
		}
	}

	@Override
	public void rotate90_without_normals() {
		for (int i = 0; i < num_vertices; i++) {
			int i_50_ = vertices_x[i];
			vertices_x[i] = vertices_z[i];
			vertices_z[i] = -i_50_;
		}
		bounds_updated = false;
	}

	@Override
	public int get_maxz() {
		if (!bounds_updated) {
			calculate_bounds();
		}
		return max_z;
	}

	@Override
	public Model copy2(boolean reference_alphas, boolean bool_51_, boolean reference_normals) {
		if (!reference_alphas && aByteArray4363.length < num_faces) {
			aByteArray4363 = new byte[num_faces + 100];
		}
		return copy(reference_alphas, model, aByteArray4363);
	}

	@Override
	public int get_minz() {
		if (!bounds_updated) {
			calculate_bounds();
		}
		return min_z;
	}

	public boolean method1023(int i, int i_52_, int i_53_, int i_54_, int i_55_, int i_56_, int i_57_, int i_58_) {
		if (i_52_ < i_53_ && i_52_ < i_54_ && i_52_ < i_55_) {
			return false;
		}
		if (i_52_ > i_53_ && i_52_ > i_54_ && i_52_ > i_55_) {
			return false;
		}
		if (i < i_56_ && i < i_57_ && i < i_58_) {
			return false;
		}
		if (i > i_56_ && i > i_57_ && i > i_58_) {
			return false;
		}
		return true;
	}

	@Override
	public int get_miny() {
		if (!bounds_updated) {
			calculate_bounds();
		}
		return min_y;
	}

	@Override
	public void draw(int rot_x, int rot_y, int rot_z, int rot_xw, int trans_x, int trans_y, int trans_z) {
		try {
			if (!bounds_updated) {
				calculate_bounds();
			}
			Rasterizer rasterizer = GraphicTools.get_rasterizer();
			int vp_center_x = rasterizer.center_x;
			int vp_center_y = rasterizer.center_y;
			int sin_x = Rasterizer.SINE[rot_x];
			int cos_x = Rasterizer.COSINE[rot_x];
			int sin_y = Rasterizer.SINE[rot_y];
			int cos_y = Rasterizer.COSINE[rot_y];
			int sin_z = Rasterizer.SINE[rot_z];
			int cos_z = Rasterizer.COSINE[rot_z];
			int sin_x_world = Rasterizer.SINE[rot_xw];
			int cos_x_world = Rasterizer.COSINE[rot_xw];
			int j4 = trans_y * sin_x_world + trans_z * cos_x_world >> 16;
			for (int k4 = 0; k4 < num_vertices; k4++) {
				int l4 = vertices_x[k4];// >> (downsize ? 2 : 0);
				int i5 = vertices_y[k4];// >> (downsize ? 2 : 0);
				int z = vertices_z[k4];// >> (downsize ? 2 : 0);
				if (rot_z != 0) {
					int i_80_ = i5 * sin_z + l4 * cos_z >> 16;
					i5 = i5 * cos_z - l4 * sin_z >> 16;
					l4 = i_80_;
				}
				if (rot_x != 0) {
					int i_81_ = i5 * cos_x - z * sin_x >> 16;
					z = i5 * sin_x + z * cos_x >> 16;
					i5 = i_81_;
				}
				if (rot_y != 0) {
					int i_82_ = z * sin_y + l4 * cos_y >> 16;
					z = z * cos_y - l4 * sin_y >> 16;
					l4 = i_82_;
				}
				l4 += trans_x;
				i5 += trans_y;
				z += trans_z;
				int j6 = i5 * cos_x_world - z * sin_x_world >> 16;
				z = i5 * sin_x_world + z * cos_x_world >> 16;
				i5 = j6;
				projected_vertices_z[k4] = z - j4;
				if (GamePlayConfiguration.DEPTH_BUFFER) {
					vertexPerspectiveZB[k4] = z;
				}
				projected_vertices_x[k4] = vp_center_x + (l4 << 9) / z;
				projected_vertices_y[k4] = vp_center_y + (i5 << 9) / z;
				if (num_textures > 0) {
					camera_vertex_x[k4] = l4;
					camera_vertex_y[k4] = i5;
					camera_vertex_z[k4] = z;
				}
			}
			translateToScreen(rasterizer, false, false, 0L, size_3d, size_3d << 1);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	Model method1024(Model class38_sub1) {
		return new SoftwareModel(new SoftwareModel[] { this, (SoftwareModel) class38_sub1 }, 2);
	}

	@Override
	public int get_maxx() {
		if (!bounds_updated) {
			calculate_bounds();
		}
		return max_x;
	}

	void method1025(int i, int i_84_, int i_85_, int i_86_, int i_87_, int i_88_, int i_89_, int i_90_) {
		try {
			if (!bounds_updated) {
				calculate_bounds();
			}
			Rasterizer rasterizer = GraphicTools.get_rasterizer();
			int i_91_ = rasterizer.center_x;
			int i_92_ = rasterizer.center_y;
			int i_93_ = Rasterizer.SINE[i];
			int i_94_ = Rasterizer.COSINE[i];
			int i_95_ = Rasterizer.SINE[i_84_];
			int i_96_ = Rasterizer.COSINE[i_84_];
			int i_97_ = Rasterizer.SINE[i_85_];
			int i_98_ = Rasterizer.COSINE[i_85_];
			int i_99_ = Rasterizer.SINE[i_86_];
			int i_100_ = Rasterizer.COSINE[i_86_];
			int i_101_ = i_88_ * i_99_ + i_89_ * i_100_ >> 16;
			for (int i_102_ = 0; i_102_ < num_vertices; i_102_++) {
				int i_103_ = vertices_x[i_102_];
				int i_104_ = vertices_y[i_102_];
				int z = vertices_z[i_102_];
				if (i_85_ != 0) {
					int i_106_ = i_104_ * i_97_ + i_103_ * i_98_ >> 16;
					i_104_ = i_104_ * i_98_ - i_103_ * i_97_ >> 16;
					i_103_ = i_106_;
				}
				if (i != 0) {
					int i_107_ = i_104_ * i_94_ - z * i_93_ >> 16;
					z = i_104_ * i_93_ + z * i_94_ >> 16;
					i_104_ = i_107_;
				}
				if (i_84_ != 0) {
					int i_108_ = z * i_95_ + i_103_ * i_96_ >> 16;
					z = z * i_96_ - i_103_ * i_95_ >> 16;
					i_103_ = i_108_;
				}
				i_103_ += i_87_;
				i_104_ += i_88_;
				z += i_89_;
				int i_109_ = i_104_ * i_100_ - z * i_99_ >> 16;
				z = i_104_ * i_99_ + z * i_100_ >> 16;
				i_104_ = i_109_;
				projected_vertices_z[i_102_] = z - i_101_;
				if (GamePlayConfiguration.DEPTH_BUFFER) {
					vertexPerspectiveZB[i_102_] = z;
				}
				projected_vertices_x[i_102_] = i_91_ + (i_103_ << 9) / i_90_;
				projected_vertices_y[i_102_] = i_92_ + (i_104_ << 9) / i_90_;
				if (num_textures > 0) {
					camera_vertex_x[i_102_] = i_103_;
					camera_vertex_y[i_102_] = i_104_;
					camera_vertex_z[i_102_] = z;
				}
			}
			translateToScreen(rasterizer, false, false, 0L, size_3d, size_3d << 1);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int get_minx() {
		if (!bounds_updated) {
			calculate_bounds();
		}
		return min_x;
	}

	public static int method1026(int i, int i_110_) {
		i_110_ = i_110_ * (i & 0x7f) >> 7;
		if (i_110_ < 2) {
			i_110_ = 2;
		} else if (i_110_ > 126) {
			i_110_ = 126;
		}
		return (i & 0xff80) + i_110_;
	}

	public static int method1027(int i) {
		if (i < 2) {
			i = 2;
		} else if (i > 126) {
			i = 126;
		}
		return i;
	}

	public static void method1028() {
		modelInstance = null;
		aByteArray4336 = null;
		model = null;
		aByteArray4363 = null;
		hasRestrictedEdges = null;
		out_of_screen = null;
		projected_vertices_x = null;
		projected_vertices_y = null;
		projected_vertices_z = null;
		camera_vertex_x = null;
		camera_vertex_y = null;
		camera_vertex_z = null;
		depthListIndices = null;
		faceLists = null;
		anIntArray4362 = null;
		anIntArrayArray3938 = null;
		anIntArray3922 = null;
		anIntArray3941 = null;
		anIntArray3944 = null;
		anIntArray1678 = null;
		anIntArray1679 = null;
		anIntArray3936 = null;
	}

	// @Override
	// public void draw(int ycurve, int xangle, int yangle, int zangle, int x, int y, int z, long var8) {
	// try {
	// if (!bounds_updated) {
	// calculate_bounds();
	// }
	// Rasterizer rasterizer = GraphicTools.get_rasterizer();
	// int var10 = rasterizer.center_x;
	// int var11 = rasterizer.center_y;
	// int var12 = Rasterizer.SINE[ycurve];
	// int var13 = Rasterizer.COSINE[ycurve];
	// int var14 = Rasterizer.SINE[xangle];
	// int var15 = Rasterizer.COSINE[xangle];
	// int var16 = Rasterizer.SINE[yangle];
	// int var17 = Rasterizer.COSINE[yangle];
	// int var18 = Rasterizer.SINE[zangle];
	// int var19 = Rasterizer.COSINE[zangle];
	// int var20 = y * var18 + z * var19 >> 16;
	//
	// for (int var21 = 0; var21 < num_vertices; ++var21) {
	// int var22 = vertices_x[var21];
	// int var23 = vertices_y[var21];
	// int var24 = vertices_z[var21];
	// int var25;
	// if (yangle != 0) {
	// var25 = var23 * var16 + var22 * var17 >> 16;
	// var23 = var23 * var17 - var22 * var16 >> 16;
	// var22 = var25;
	// }
	//
	// if (ycurve != 0) {
	// var25 = var23 * var13 - var24 * var12 >> 16;
	// var24 = var23 * var12 + var24 * var13 >> 16;
	// var23 = var25;
	// }
	//
	// if (xangle != 0) {
	// var25 = var24 * var14 + var22 * var15 >> 16;
	// var24 = var24 * var15 - var22 * var14 >> 16;
	// var22 = var25;
	// }
	//
	// var22 += x;
	// var23 += y;
	// var24 += z;
	// var25 = var23 * var19 - var24 * var18 >> 16;
	// var24 = var23 * var18 + var24 * var19 >> 16;
	// projected_vertices_z[var21] = var24 - var20;
	// projected_vertices_x[var21] = var10 + (var22 << 9) / var24;
	// projected_vertices_y[var21] = var11 + (var25 << 9) / var24;
	// if (num_textures > 0) {
	// camera_vertex_x[var21] = var22;
	// camera_vertex_y[var21] = var25;
	// camera_vertex_z[var21] = var24;
	// }
	// }
	//
	// translateToScreen(rasterizer, false, var8 >= 0L, var8, size_3d, size_3d << 1);
	// } catch (RuntimeException e) {
	// // NOOP
	// }
	//
	// }

	@Override
	public void draw2(int angle, int yCameraSine, int yCameraCosine, int xCurveSine, int xCurveCosine, int x, int y, int z, long uid, int bufferOffset) {
		if (!bounds_updated) {
			calculate_bounds();
		}
		Rasterizer rasterizer = GraphicTools.get_rasterizer();
		int j2 = z * xCurveCosine - x * xCurveSine >> 16;
		int k2 = y * yCameraSine + j2 * yCameraCosine >> 16;
		int i3 = k2 + (size_2d * yCameraCosine + max_y * yCameraSine >> 16);
		if (i3 > 50) {
			int k5 = k2 + (-size_2d * yCameraCosine + min_y * yCameraSine >> 16);
			if (k5 < 3500) {
				int j3 = z * xCurveSine + x * xCurveCosine >> 16;
				int i_123_ = j3 + size_2d << 9;
				if (i_123_ / i3 > Viewport.screen_left) {
					int i_124_ = j3 - size_2d << 9;
					if (i_124_ / i3 < Viewport.screen_right) {
						int i4 = y * yCameraCosine - j2 * yCameraSine >> 16;
						int i_126_ = i4 + (size_2d * yCameraSine + max_y * yCameraCosine >> 16) << 9;
						if (i_126_ / i3 > Viewport.screen_top) {
							int i_127_ = i4 + (-size_2d * yCameraSine + min_y * yCameraCosine >> 16) << 9;
							if (i_127_ / i3 < Viewport.screen_bottom) {
								boolean bool = false;
								boolean bool_128_ = k5 <= 50;
								boolean flag = bool_128_ || num_textures > 0;
								int l5 = rasterizer.center_x;
								int j6 = rasterizer.center_y;
								int l6 = 0;
								int i7 = 0;
								if (angle != 0) {
									l6 = Rasterizer.SINE[angle];
									i7 = Rasterizer.COSINE[angle];
								}
								boolean flag1 = false;
								if (uid > 0L && Scene.object_selected && k5 > 0) {
									int i_135_;
									int i_136_;
									if (j3 > 0) {
										i_135_ = i_124_ / i3;
										i_136_ = i_123_ / k5;
									} else {
										i_135_ = i_124_ / k5;
										i_136_ = i_123_ / i3;
									}
									int i_137_;
									int i_138_;
									if (i4 > 0) {
										i_137_ = i_127_ / i3;
										i_138_ = i_126_ / k5;
									} else {
										i_137_ = i_127_ / k5;
										i_138_ = i_126_ / i3;
									}
									if (Viewport.anInt3878 >= i_135_ && Viewport.anInt3878 <= i_136_ && Viewport.anInt1973 >= i_137_ && Viewport.anInt1973 <= i_138_) {
										i_135_ = 999999;
										i_136_ = -999999;
										i_137_ = 999999;
										i_138_ = -999999;
										int[] is = new int[] { min_x, max_x, min_x, max_x, min_x, max_x, min_x, max_x };
										int[] is_139_ = new int[] { min_z, min_z, max_z, max_z, min_z, min_z, max_z, max_z };
										int[] is_140_ = new int[] { min_y, min_y, min_y, min_y, max_y, max_y, max_y, max_y };
										for (int i_141_ = 0; i_141_ < 8; i_141_++) {
											int i_142_ = is[i_141_];
											int i_143_ = is_140_[i_141_];
											int i_144_ = is_139_[i_141_];
											if (angle != 0) {
												int i_145_ = i_144_ * l6 + i_142_ * i7 >> 16;
												i_144_ = i_144_ * i7 - i_142_ * l6 >> 16;
												i_142_ = i_145_;
											}
											i_142_ += x;
											i_143_ += y;
											i_144_ += z;
											int i_146_ = i_144_ * xCurveSine + i_142_ * xCurveCosine >> 16;
											i_144_ = i_144_ * xCurveCosine - i_142_ * xCurveSine >> 16;
											i_142_ = i_146_;
											i_146_ = i_143_ * yCameraCosine - i_144_ * yCameraSine >> 16;
											i_144_ = i_143_ * yCameraSine + i_144_ * yCameraCosine >> 16;
											i_143_ = i_146_;
											if (i_144_ > 0) {
												int i_147_ = (i_142_ << 9) / i_144_;
												int i_148_ = (i_143_ << 9) / i_144_;
												if (i_147_ < i_135_) {
													i_135_ = i_147_;
												}
												if (i_147_ > i_136_) {
													i_136_ = i_147_;
												}
												if (i_148_ < i_137_) {
													i_137_ = i_148_;
												}
												if (i_148_ > i_138_) {
													i_138_ = i_148_;
												}
											}
										}
										if (Viewport.anInt3878 >= i_135_ && Viewport.anInt3878 <= i_136_ && Viewport.anInt1973 >= i_137_ && Viewport.anInt1973 <= i_138_) {
											if (renders_in_one_tile) {
												Scene.rendered_models_uid[Scene.num_rendered++] = uid;
											} else {
												flag1 = true;
											}
										}
									}
								}
								for (int j7 = 0; j7 < num_vertices; j7++) {
									int k7 = vertices_x[j7];
									int l7 = vertices_y[j7];
									int i8 = vertices_z[j7];
									if (angle != 0) {
										int j8 = i8 * l6 + k7 * i7 >> 16;
										i8 = i8 * i7 - k7 * l6 >> 16;
										k7 = j8;
									}
									k7 += x;
									l7 += y;
									i8 += z;
									int k8 = i8 * xCurveSine + k7 * xCurveCosine >> 16;
									i8 = i8 * xCurveCosine - k7 * xCurveSine >> 16;
									k7 = k8;
									k8 = l7 * yCameraCosine - i8 * yCameraSine >> 16;
									i8 = l7 * yCameraSine + i8 * yCameraCosine >> 16;
									l7 = k8;
									projected_vertices_z[j7] = i8 - k2;
									if (i8 >= 50) {
										projected_vertices_x[j7] = l5 + (k7 << 9) / i8;
										projected_vertices_y[j7] = j6 + (l7 << 9) / i8;
										if (GamePlayConfiguration.DEPTH_BUFFER) {
											vertexPerspectiveZB[j7] = i8;
										}
									} else {
										projected_vertices_x[j7] = -5000;
										bool = true;
									}
									if (flag) {
										camera_vertex_x[j7] = k7;
										camera_vertex_y[j7] = l7;
										camera_vertex_z[j7] = i8;
									}
								}
								try {
									translateToScreen(rasterizer, bool, flag1, uid, k2 - k5, i3 - k5 + 2);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void zaxis_rotate_without_normals(int angle) {
		int i_155_ = Rasterizer.SINE[angle];
		int i_156_ = Rasterizer.COSINE[angle];
		for (int i_157_ = 0; i_157_ < num_vertices; i_157_++) {
			int i_158_ = vertices_y[i_157_] * i_155_ + vertices_x[i_157_] * i_156_ >> 16;
			vertices_y[i_157_] = vertices_y[i_157_] * i_156_ - vertices_x[i_157_] * i_155_ >> 16;
			vertices_x[i_157_] = i_158_;
		}
		bounds_updated = false;
	}

	public void calculate_bounds() {
		int i = 32767;
		int i_159_ = 32767;
		int i_160_ = 32767;
		int i_161_ = -32768;
		int i_162_ = -32768;
		int i_163_ = -32768;
		int i_164_ = 0;
		int i_165_ = 0;
		for (int i_166_ = 0; i_166_ < num_vertices; i_166_++) {
			int i_167_ = vertices_x[i_166_];
			int i_168_ = vertices_y[i_166_];
			int i_169_ = vertices_z[i_166_];
			if (i_167_ < i) {
				i = i_167_;
			}
			if (i_167_ > i_161_) {
				i_161_ = i_167_;
			}
			if (i_168_ < i_159_) {
				i_159_ = i_168_;
			}
			if (i_168_ > i_162_) {
				i_162_ = i_168_;
			}
			if (i_169_ < i_160_) {
				i_160_ = i_169_;
			}
			if (i_169_ > i_163_) {
				i_163_ = i_169_;
			}
			int i_170_ = i_167_ * i_167_ + i_169_ * i_169_;
			if (i_170_ > i_164_) {
				i_164_ = i_170_;
			}
			i_170_ = i_167_ * i_167_ + i_169_ * i_169_ + i_168_ * i_168_;
			if (i_170_ > i_165_) {
				i_165_ = i_170_;
			}
		}
		min_x = (short) i;
		max_x = (short) i_161_;
		min_y = (short) i_159_;
		max_y = (short) i_162_;
		min_z = (short) i_160_;
		max_z = (short) i_163_;
		size_2d = (short) (int) (Math.sqrt(i_164_) + 0.99);
		size_3d = (short) (int) (Math.sqrt(i_165_) + 0.99);
		bounds_updated = true;
	}

	public void reduce(Rasterizer rasterizer, int face_index) {
		int j = rasterizer.center_x;
		int k = rasterizer.center_y;
		int l = 0;
		int a = faces_a[face_index];
		int b = faces_b[face_index];
		int c = faces_c[face_index];
		int l1 = camera_vertex_z[a];
		int i2 = camera_vertex_z[b];
		int j2 = camera_vertex_z[c];
		if (faces_alpha == null) {
			rasterizer.blending_alpha = 0;
		} else {
			rasterizer.blending_alpha = faces_alpha[face_index] & 0xff;
		}
		if (l1 >= 50) {
			anIntArray1678[l] = projected_vertices_x[a];
			anIntArray1679[l] = projected_vertices_y[a];
			anIntArray3936[l++] = faces_colour_a[face_index];
		} else {
			int k2 = camera_vertex_x[a];
			int k3 = camera_vertex_y[a];
			int k4 = faces_colour_a[face_index];
			if (j2 >= 50) {
				int i_183_ = (50 - l1) * Rasterizer.LIGHT_DECAY[j2 - l1];
				anIntArray1678[l] = j + (k2 + ((camera_vertex_x[c] - k2) * i_183_ >> 16) << 9) / 50;
				anIntArray1679[l] = k + (k3 + ((camera_vertex_y[c] - k3) * i_183_ >> 16) << 9) / 50;
				anIntArray3936[l++] = k4 + ((faces_colour_c[face_index] - k4) * i_183_ >> 16);
			}
			if (i2 >= 50) {
				int i_184_ = (50 - l1) * Rasterizer.LIGHT_DECAY[i2 - l1];
				anIntArray1678[l] = j + (k2 + ((camera_vertex_x[b] - k2) * i_184_ >> 16) << 9) / 50;
				anIntArray1679[l] = k + (k3 + ((camera_vertex_y[b] - k3) * i_184_ >> 16) << 9) / 50;
				anIntArray3936[l++] = k4 + ((faces_colour_b[face_index] - k4) * i_184_ >> 16);
			}
		}
		if (i2 >= 50) {
			anIntArray1678[l] = projected_vertices_x[b];
			anIntArray1679[l] = projected_vertices_y[b];
			anIntArray3936[l++] = faces_colour_b[face_index];
		} else {
			int l3 = camera_vertex_x[b];
			int l2 = camera_vertex_y[b];
			int l4 = faces_colour_b[face_index];
			if (l1 >= 50) {
				int i_188_ = (50 - i2) * Rasterizer.LIGHT_DECAY[l1 - i2];
				anIntArray1678[l] = j + (l3 + ((camera_vertex_x[a] - l3) * i_188_ >> 16) << 9) / 50;
				anIntArray1679[l] = k + (l2 + ((camera_vertex_y[a] - l2) * i_188_ >> 16) << 9) / 50;
				anIntArray3936[l++] = l4 + ((faces_colour_a[face_index] - l4) * i_188_ >> 16);
			}
			if (j2 >= 50) {
				int i_189_ = (50 - i2) * Rasterizer.LIGHT_DECAY[j2 - i2];
				anIntArray1678[l] = j + (l3 + ((camera_vertex_x[c] - l3) * i_189_ >> 16) << 9) / 50;
				anIntArray1679[l] = k + (l2 + ((camera_vertex_y[c] - l2) * i_189_ >> 16) << 9) / 50;
				anIntArray3936[l++] = l4 + ((faces_colour_c[face_index] - l4) * i_189_ >> 16);
			}
		}
		if (j2 >= 50) {
			anIntArray1678[l] = projected_vertices_x[c];
			anIntArray1679[l] = projected_vertices_y[c];
			anIntArray3936[l++] = faces_colour_c[face_index];
		} else {
			int i3 = camera_vertex_x[c];
			int i4 = camera_vertex_y[c];
			int i5 = faces_colour_c[face_index];
			if (i2 >= 50) {
				int i_193_ = (50 - j2) * Rasterizer.LIGHT_DECAY[i2 - j2];
				anIntArray1678[l] = j + (i3 + ((camera_vertex_x[b] - i3) * i_193_ >> 16) << 9) / 50;
				anIntArray1679[l] = k + (i4 + ((camera_vertex_y[b] - i4) * i_193_ >> 16) << 9) / 50;
				anIntArray3936[l++] = i5 + ((faces_colour_b[face_index] - i5) * i_193_ >> 16);
			}
			if (l1 >= 50) {
				int i_194_ = (50 - j2) * Rasterizer.LIGHT_DECAY[l1 - j2];
				anIntArray1678[l] = j + (i3 + ((camera_vertex_x[a] - i3) * i_194_ >> 16) << 9) / 50;
				anIntArray1679[l] = k + (i4 + ((camera_vertex_y[a] - i4) * i_194_ >> 16) << 9) / 50;
				anIntArray3936[l++] = i5 + ((faces_colour_a[face_index] - i5) * i_194_ >> 16);
			}
		}
		int xa = anIntArray1678[0];
		int xb = anIntArray1678[1];
		int xc = anIntArray1678[2];
		int ya = anIntArray1679[0];
		int yb = anIntArray1679[1];
		int yc = anIntArray1679[2];
		rasterizer.clip_edges = false;
		if (l == 3) {
			if (xa < 0 || xb < 0 || xc < 0 || xa > rasterizer.clip_width || xb > rasterizer.clip_width || xc > rasterizer.clip_width) {
				rasterizer.clip_edges = true;
			}
			if (faces_material == null || faces_material[face_index] == -1) {
				if (faces_colour_c[face_index] == -1) {
					rasterizer.method1144(ya, yb, yc, xa, xb, xc, ColourUtil.hslToRgbTable[faces_colour_a[face_index] & '\uffff']);
				} else {
					rasterizer.method1154(ya, yb, yc, xa, xb, xc, anIntArray3936[0], anIntArray3936[1], anIntArray3936[2]);
				}
			} else {
				int p;
				int m;
				int n;
				if (faces_mapping != null && faces_mapping[face_index] != -1) {
					int i_204_ = faces_mapping[face_index] & 0xff;
					p = textures_mapping_p[i_204_];
					m = textures_mapping_m[i_204_];
					n = textures_mapping_n[i_204_];
				} else {
					p = a;
					m = b;
					n = c;
				}
				if (faces_colour_c[face_index] == -1) {
					rasterizer.method1138(ya, yb, yc, xa, xb, xc, faces_colour_a[face_index], faces_colour_a[face_index], faces_colour_a[face_index], camera_vertex_x[p], camera_vertex_x[m], camera_vertex_x[n], camera_vertex_y[p], camera_vertex_y[m], camera_vertex_y[n], camera_vertex_z[p], camera_vertex_z[m], camera_vertex_z[n], faces_material[face_index]);
				} else {
					rasterizer.method1138(ya, yb, yc, xa, xb, xc, anIntArray3936[0], anIntArray3936[1], anIntArray3936[2], camera_vertex_x[p], camera_vertex_x[m], camera_vertex_x[n], camera_vertex_y[p], camera_vertex_y[m], camera_vertex_y[n], camera_vertex_z[p], camera_vertex_z[m], camera_vertex_z[n], faces_material[face_index]);
				}
			}
		}
		if (l == 4) {
			if (xa < 0 || xb < 0 || xc < 0 || xa > rasterizer.clip_width || xb > rasterizer.clip_width || xc > rasterizer.clip_width || anIntArray1678[3] < 0 || anIntArray1678[3] > rasterizer.clip_width) {
				rasterizer.clip_edges = true;
			}
			if (faces_material == null || faces_material[face_index] == -1) {
				if (faces_colour_c[face_index] == -1) {
					int var17 = ColourUtil.hslToRgbTable[faces_colour_a[face_index] & '\uffff'];
					rasterizer.method1144(ya, yb, yc, xa, xb, xc, var17);
					rasterizer.method1144(ya, yc, anIntArray1679[3], xa, xc, anIntArray1678[3], var17);
				} else {
					rasterizer.method1154(ya, yb, yc, xa, xb, xc, anIntArray3936[0], anIntArray3936[1], anIntArray3936[2]);
					rasterizer.method1154(ya, yc, anIntArray1679[3], xa, xc, anIntArray1678[3], anIntArray3936[0], anIntArray3936[2], anIntArray3936[3]);
				}
			} else {
				int p;
				int m;
				int n;
				if (faces_mapping != null && faces_mapping[face_index] != -1) {
					int i_209_ = faces_mapping[face_index] & 0xff;
					p = textures_mapping_p[i_209_];
					m = textures_mapping_m[i_209_];
					n = textures_mapping_n[i_209_];
				} else {
					p = a;
					m = b;
					n = c;
				}
				short s = faces_material[face_index];
				short textureid = faces_material[face_index];
				if (faces_colour_c[face_index] == -1) {
					rasterizer.method1138(ya, yb, yc, xa, xb, xc, faces_colour_a[face_index], faces_colour_a[face_index], faces_colour_a[face_index], camera_vertex_x[p], camera_vertex_x[m], camera_vertex_x[n], camera_vertex_y[p], camera_vertex_y[m], camera_vertex_y[n], camera_vertex_z[p], camera_vertex_z[m], camera_vertex_z[n], textureid);
					rasterizer.method1138(ya, yc, anIntArray1679[3], xa, xc, anIntArray1678[3], faces_colour_a[face_index], faces_colour_a[face_index], faces_colour_a[face_index], camera_vertex_x[p], camera_vertex_x[m], camera_vertex_x[n], camera_vertex_y[p], camera_vertex_y[m], camera_vertex_y[n], camera_vertex_z[p], camera_vertex_z[m], camera_vertex_z[n], textureid);
				} else {
					rasterizer.method1138(ya, yb, yc, xa, xb, xc, anIntArray3936[0], anIntArray3936[1], anIntArray3936[2], camera_vertex_x[p], camera_vertex_x[m], camera_vertex_x[n], camera_vertex_y[p], camera_vertex_y[m], camera_vertex_y[n], camera_vertex_z[p], camera_vertex_z[m], camera_vertex_z[n], textureid);
					rasterizer.method1138(ya, yc, anIntArray1679[3], xa, xc, anIntArray1678[3], anIntArray3936[0], anIntArray3936[2], anIntArray3936[3], camera_vertex_x[p], camera_vertex_x[m], camera_vertex_x[n], camera_vertex_y[p], camera_vertex_y[m], camera_vertex_y[n], camera_vertex_z[p], camera_vertex_z[m], camera_vertex_z[n], textureid);
				}
			}
		}
	}

	@Override
	public void xaxis_rotate_without_normals(int i) {
		int i_210_ = Rasterizer.SINE[i];
		int i_211_ = Rasterizer.COSINE[i];
		for (int i_212_ = 0; i_212_ < num_vertices; i_212_++) {
			int i_213_ = vertices_y[i_212_] * i_211_ - vertices_z[i_212_] * i_210_ >> 16;
			vertices_z[i_212_] = vertices_y[i_212_] * i_210_ + vertices_z[i_212_] * i_211_ >> 16;
			vertices_y[i_212_] = i_213_;
		}
		bounds_updated = false;
	}

	@Override
	public Model copy1(boolean reference_alphas, boolean reference_colors, boolean reference_normals) {
		if (!reference_alphas && aByteArray4336.length < num_faces) {
			aByteArray4336 = new byte[num_faces + 100];
		}
		return copy(reference_alphas, modelInstance, aByteArray4336);
	}

	@Override
	public void rotate270_without_normals() {
		for (int i = 0; i < num_vertices; i++) {
			int i_215_ = vertices_z[i];
			vertices_z[i] = vertices_x[i];
			vertices_x[i] = -i_215_;
		}
		bounds_updated = false;
	}

	@Override
	public void scale(int scaleX, int scaleY, int scaleZ) {
		for (int i_218_ = 0; i_218_ < num_vertices; i_218_++) {
			vertices_x[i_218_] = vertices_x[i_218_] * scaleX / 128;
			vertices_y[i_218_] = vertices_y[i_218_] * scaleY / 128;
			vertices_z[i_218_] = vertices_z[i_218_] * scaleZ / 128;
		}
		bounds_updated = false;
	}

	@Override
	public void yaxis_rotate_without_normals(int angle) {
		int i_219_ = Rasterizer.SINE[angle];
		int i_220_ = Rasterizer.COSINE[angle];
		for (int i_221_ = 0; i_221_ < num_vertices; i_221_++) {
			int i_222_ = vertices_z[i_221_] * i_219_ + vertices_x[i_221_] * i_220_ >> 16;
			vertices_z[i_221_] = vertices_z[i_221_] * i_220_ - vertices_x[i_221_] * i_219_ >> 16;
			vertices_x[i_221_] = i_222_;
		}
		bounds_updated = false;
	}

	@Override
	public void translate(int offset_x, int i_223_, int i_224_) {
		for (int i_225_ = 0; i_225_ < num_vertices; i_225_++) {
			vertices_x[i_225_] += offset_x;
			vertices_y[i_225_] += i_223_;
			vertices_z[i_225_] += i_224_;
		}
		bounds_updated = false;
	}

	public void render_face(Rasterizer rasterizer, int face_index) {
		if (out_of_screen[face_index]) {
			reduce(rasterizer, face_index);
		} else {
			int index_a = faces_a[face_index];
			int index_b = faces_b[face_index];
			int index_c = faces_c[face_index];
			rasterizer.clip_edges = hasRestrictedEdges[face_index];
			if (faces_alpha == null) {
				rasterizer.blending_alpha = 0;
			} else {
				rasterizer.blending_alpha = faces_alpha[face_index] & 0xff;
			}
			if (faces_material == null || faces_material[face_index] == -1) {
				if (faces_colour_c[face_index] == -1) {
					rasterizer.method1144(projected_vertices_y[index_a], projected_vertices_y[index_b], projected_vertices_y[index_c], projected_vertices_x[index_a], projected_vertices_x[index_b], projected_vertices_x[index_c], ColourUtil.hslToRgbTable[faces_colour_a[face_index] & '\uffff']);
				} else {
					rasterizer.method1154(projected_vertices_y[index_a], projected_vertices_y[index_b], projected_vertices_y[index_c], projected_vertices_x[index_a], projected_vertices_x[index_b], projected_vertices_x[index_c], faces_colour_a[face_index] & '\uffff', faces_colour_b[face_index] & '\uffff', faces_colour_c[face_index] & '\uffff');
				}
			} else {
				int j;
				int k;
				int l;
				if (faces_mapping != null && faces_mapping[face_index] != -1) {
					int i_232_ = faces_mapping[face_index] & 0xff;
					j = textures_mapping_p[i_232_];
					k = textures_mapping_m[i_232_];
					l = textures_mapping_n[i_232_];
				} else {
					j = index_a;
					k = index_b;
					l = index_c;
				}
				if (faces_colour_c[face_index] == -1) {
					rasterizer.method1138(projected_vertices_y[index_a], projected_vertices_y[index_b], projected_vertices_y[index_c], projected_vertices_x[index_a], projected_vertices_x[index_b], projected_vertices_x[index_c], faces_colour_a[face_index], faces_colour_a[face_index], faces_colour_a[face_index], camera_vertex_x[j], camera_vertex_x[k], camera_vertex_x[l], camera_vertex_y[j], camera_vertex_y[k], camera_vertex_y[l], camera_vertex_z[j], camera_vertex_z[k], camera_vertex_z[l], faces_material[face_index]);
				} else {
					rasterizer.method1138(projected_vertices_y[index_a], projected_vertices_y[index_b], projected_vertices_y[index_c], projected_vertices_x[index_a], projected_vertices_x[index_b], projected_vertices_x[index_c], faces_colour_a[face_index], faces_colour_b[face_index], faces_colour_c[face_index], camera_vertex_x[j], camera_vertex_x[k], camera_vertex_x[l], camera_vertex_y[j], camera_vertex_y[k], camera_vertex_y[l], camera_vertex_z[j], camera_vertex_z[k], camera_vertex_z[l], faces_material[face_index]);
				}
			}
		}
	}

	public boolean hasAlpha(int triangle) {
		if (faces_alpha == null) {
			return false;
		}
		if (faces_alpha[triangle] == 0) {
			return false;
		}
		return true;
	}

	@Override
	public void rotate180_without_normals() {
		for (int i = 0; i < num_vertices; i++) {
			vertices_x[i] = -vertices_x[i];
			vertices_z[i] = -vertices_z[i];
		}
		bounds_updated = false;
	}

	SoftwareModel method1941(int type, int value, int[][] is, int[][] is_253_, int x, int y, int z, boolean bool) {
		if (!bounds_updated) {
			calculate_bounds();
		}
		int i_257_ = x + min_x;
		int i_258_ = x + max_x;
		int i_259_ = z + min_z;
		int i_260_ = z + max_z;
		if ((type == 1 || type == 2 || type == 3 || type == 5) && (i_257_ < 0 || i_258_ + 128 >> 7 >= is.length || i_259_ < 0 || i_260_ + 128 >> 7 >= is[0].length)) {
			return this;
		}
		if (type == 4 || type == 5) {
			if (is_253_ == null) {
				return this;
			}
			if (i_257_ < 0 || i_258_ + 128 >> 7 >= is_253_.length || i_259_ < 0 || i_260_ + 128 >> 7 >= is_253_[0].length) {
				return this;
			}
		} else {
			i_257_ >>= 7;
			i_258_ = i_258_ + 127 >> 7;
			i_259_ >>= 7;
			i_260_ = i_260_ + 127 >> 7;
			if (is[i_257_][i_259_] == y && is[i_258_][i_259_] == y && is[i_257_][i_260_] == y && is[i_258_][i_260_] == y) {
				return this;
			}
		}
		SoftwareModel class38_sub1_sub1_261_;
		if (bool) {
			class38_sub1_sub1_261_ = new SoftwareModel();
			class38_sub1_sub1_261_.num_vertices = num_vertices;
			class38_sub1_sub1_261_.num_faces = num_faces;
			class38_sub1_sub1_261_.num_textures = num_textures;
			class38_sub1_sub1_261_.faces_a = faces_a;
			class38_sub1_sub1_261_.faces_b = faces_b;
			class38_sub1_sub1_261_.faces_c = faces_c;
			class38_sub1_sub1_261_.faces_colour_a = faces_colour_a;
			class38_sub1_sub1_261_.faces_colour_b = faces_colour_b;
			class38_sub1_sub1_261_.faces_colour_c = faces_colour_c;
			class38_sub1_sub1_261_.faces_priority = faces_priority;
			class38_sub1_sub1_261_.faces_alpha = faces_alpha;
			class38_sub1_sub1_261_.faces_mapping = faces_mapping;
			class38_sub1_sub1_261_.faces_material = faces_material;
			class38_sub1_sub1_261_.priority = priority;
			// class38_sub1_sub1_261_.umap = umap;
			// class38_sub1_sub1_261_.vmap = vmap;
			class38_sub1_sub1_261_.textures_mapping_p = textures_mapping_p;
			class38_sub1_sub1_261_.textures_mapping_m = textures_mapping_m;
			class38_sub1_sub1_261_.textures_mapping_n = textures_mapping_n;
			class38_sub1_sub1_261_.vertices_by_label = vertices_by_label;
			class38_sub1_sub1_261_.faces_by_label = faces_by_label;
			class38_sub1_sub1_261_.vertices_submeshes = vertices_submeshes;
			class38_sub1_sub1_261_.faces_submeshes = faces_submeshes;
			class38_sub1_sub1_261_.renders_in_one_tile = renders_in_one_tile;
			if (type == 3) {
				class38_sub1_sub1_261_.vertices_x = CollisionMap.method1291(0, vertices_x);
				class38_sub1_sub1_261_.vertices_y = CollisionMap.method1291(0, vertices_y);
				class38_sub1_sub1_261_.vertices_z = CollisionMap.method1291(0, vertices_z);
			} else {
				class38_sub1_sub1_261_.vertices_x = vertices_x;
				class38_sub1_sub1_261_.vertices_y = new int[class38_sub1_sub1_261_.num_vertices];
				class38_sub1_sub1_261_.vertices_z = vertices_z;
			}
		} else {
			class38_sub1_sub1_261_ = this;
		}
		if (type == 1) {
			for (int i_262_ = 0; i_262_ < class38_sub1_sub1_261_.num_vertices; i_262_++) {
				int i_263_ = vertices_x[i_262_] + x;
				int i_264_ = vertices_z[i_262_] + z;
				int i_265_ = i_263_ & 0x7f;
				int i_266_ = i_264_ & 0x7f;
				int i_267_ = i_263_ >> 7;
				int i_268_ = i_264_ >> 7;
				int i_269_ = is[i_267_][i_268_] * (128 - i_265_) + is[i_267_ + 1][i_268_] * i_265_ >> 7;
				int i_270_ = is[i_267_][i_268_ + 1] * (128 - i_265_) + is[i_267_ + 1][i_268_ + 1] * i_265_ >> 7;
				int i_271_ = i_269_ * (128 - i_266_) + i_270_ * i_266_ >> 7;
				class38_sub1_sub1_261_.vertices_y[i_262_] = vertices_y[i_262_] + i_271_ - y;
			}
		} else if (type == 2) {
			for (int i_272_ = 0; i_272_ < class38_sub1_sub1_261_.num_vertices; i_272_++) {
				int i_273_ = (vertices_y[i_272_] << 16) / min_y;
				if (i_273_ < value) {
					int i_274_ = vertices_x[i_272_] + x;
					int i_275_ = vertices_z[i_272_] + z;
					int i_276_ = i_274_ & 0x7f;
					int i_277_ = i_275_ & 0x7f;
					int i_278_ = i_274_ >> 7;
					int i_279_ = i_275_ >> 7;
					int i_280_ = is[i_278_][i_279_] * (128 - i_276_) + is[i_278_ + 1][i_279_] * i_276_ >> 7;
					int i_281_ = is[i_278_][i_279_ + 1] * (128 - i_276_) + is[i_278_ + 1][i_279_ + 1] * i_276_ >> 7;
					int i_282_ = i_280_ * (128 - i_277_) + i_281_ * i_277_ >> 7;
					class38_sub1_sub1_261_.vertices_y[i_272_] = vertices_y[i_272_] + (i_282_ - y) * (value - i_273_) / value;
				} else {
					class38_sub1_sub1_261_.vertices_y[i_272_] = vertices_y[i_272_];
				}
			}
		} else if (type == 3) {
			int width = (value & 0xff) * 4;
			int length = (value >> 8 & 0xff) * 4;
			int xan = (value >> 16 & 0xff) << 6;
			int i_1043_ = (value >> 24 & 0xff) << 6;
			if (!(x - (width >> 1) < 0 || x + (width >> 1) + TileConstants.SIZE_1BY1 >= 104 << TileConstants.SIZE_BITS)) {
				class38_sub1_sub1_261_.hillchange(is, x, y, z, width, length, xan, i_1043_);
			}
		} else if (type == 4) {
			int i_285_ = max_y - min_y;
			for (int i_286_ = 0; i_286_ < num_vertices; i_286_++) {
				int i_287_ = vertices_x[i_286_] + x;
				int i_288_ = vertices_z[i_286_] + z;
				int i_289_ = i_287_ & 0x7f;
				int i_290_ = i_288_ & 0x7f;
				int i_291_ = i_287_ >> 7;
				int i_292_ = i_288_ >> 7;
				int i_293_ = is_253_[i_291_][i_292_] * (128 - i_289_) + is_253_[i_291_ + 1][i_292_] * i_289_ >> 7;
				int i_294_ = is_253_[i_291_][i_292_ + 1] * (128 - i_289_) + is_253_[i_291_ + 1][i_292_ + 1] * i_289_ >> 7;
				int i_295_ = i_293_ * (128 - i_290_) + i_294_ * i_290_ >> 7;
				class38_sub1_sub1_261_.vertices_y[i_286_] = vertices_y[i_286_] + i_295_ - y + i_285_;
			}
		} else if (type == 5) {
			int i_296_ = max_y - min_y;
			for (int i_297_ = 0; i_297_ < num_vertices; i_297_++) {
				int i_298_ = vertices_x[i_297_] + x;
				int i_299_ = vertices_z[i_297_] + z;
				int i_300_ = i_298_ & 0x7f;
				int i_301_ = i_299_ & 0x7f;
				int i_302_ = i_298_ >> 7;
				int i_303_ = i_299_ >> 7;
				int i_304_ = is[i_302_][i_303_] * (128 - i_300_) + is[i_302_ + 1][i_303_] * i_300_ >> 7;
				int i_305_ = is[i_302_][i_303_ + 1] * (128 - i_300_) + is[i_302_ + 1][i_303_ + 1] * i_300_ >> 7;
				int i_306_ = i_304_ * (128 - i_301_) + i_305_ * i_301_ >> 7;
				i_304_ = is_253_[i_302_][i_303_] * (128 - i_300_) + is_253_[i_302_ + 1][i_303_] * i_300_ >> 7;
				i_305_ = is_253_[i_302_][i_303_ + 1] * (128 - i_300_) + is_253_[i_302_ + 1][i_303_ + 1] * i_300_ >> 7;
				int i_307_ = i_304_ * (128 - i_301_) + i_305_ * i_301_ >> 7;
				int i_308_ = i_306_ - i_307_;
				class38_sub1_sub1_261_.vertices_y[i_297_] = ((vertices_y[i_297_] << 8) / i_296_ * i_308_ >> 8) - (y - i_306_);
			}
		}
		class38_sub1_sub1_261_.bounds_updated = false;
		return class38_sub1_sub1_261_;
	}

	public void translateToScreen(Rasterizer rasterizer, boolean flag, boolean flag1, long uid, int i, int i_310_) {
		if (i_310_ < 1600) {
			rasterizer.smooth_edges = false;// TODO: this should be enabled for self player
			rasterizer.blending = false;
			for (int j = 0; j < i_310_; j++) {
				depthListIndices[j] = 0;
			}
			for (int var10 = 0; var10 < num_faces; var10++) {
				if (faces_colour_c[var10] != -2) {
					int l = faces_a[var10];
					int k1 = faces_b[var10];
					int j2 = faces_c[var10];
					int i3 = projected_vertices_x[l];
					int l3 = projected_vertices_x[k1];
					int k4 = projected_vertices_x[j2];
					if (flag && (i3 == -5000 || l3 == -5000 || k4 == -5000)) {
						int i_319_ = camera_vertex_x[l];
						int i_320_ = camera_vertex_x[k1];
						int i_321_ = camera_vertex_x[j2];
						int i_322_ = camera_vertex_y[l];
						int i_323_ = camera_vertex_y[k1];
						int i_324_ = camera_vertex_y[j2];
						int i_325_ = camera_vertex_z[l];
						int i_326_ = camera_vertex_z[k1];
						int i_327_ = camera_vertex_z[j2];
						i_319_ -= i_320_;
						i_321_ -= i_320_;
						i_322_ -= i_323_;
						i_324_ -= i_323_;
						i_325_ -= i_326_;
						i_327_ -= i_326_;
						int i_328_ = i_322_ * i_327_ - i_325_ * i_324_;
						int i_329_ = i_325_ * i_321_ - i_319_ * i_327_;
						int i_330_ = i_319_ * i_324_ - i_322_ * i_321_;
						if (i_320_ * i_328_ + i_323_ * i_329_ + i_326_ * i_330_ > 0) {
							out_of_screen[var10] = true;
							int j5 = (projected_vertices_z[l] + projected_vertices_z[k1] + projected_vertices_z[j2]) / 3 + i;
							faceLists[j5][depthListIndices[j5]++] = var10;
						}
					} else {
						if (flag1 && method1023(Viewport.anInt3878 + rasterizer.center_x, Viewport.anInt1973 + rasterizer.center_y, projected_vertices_y[l], projected_vertices_y[k1], projected_vertices_y[j2], i3, l3, k4)) {
							Scene.rendered_models_uid[Scene.num_rendered++] = uid;
							flag1 = false;
						}
						if ((i3 - l3) * (projected_vertices_y[j2] - projected_vertices_y[k1]) - (projected_vertices_y[l] - projected_vertices_y[k1]) * (k4 - l3) > 0) {
							out_of_screen[var10] = false;
							if (i3 < 0 || l3 < 0 || k4 < 0 || i3 > rasterizer.clip_width || l3 > rasterizer.clip_width || k4 > rasterizer.clip_width) {
								hasRestrictedEdges[var10] = true;
							} else {
								hasRestrictedEdges[var10] = false;
							}
							int var29 = (projected_vertices_z[l] + projected_vertices_z[k1] + projected_vertices_z[j2]) / 3 + i;
							faceLists[var29][depthListIndices[var29]++] = var10;
						}
					}
				}
			}
			if (faces_priority == null) {
				for (int i_333_ = i_310_ - 1; i_333_ >= 0; i_333_--) {
					int i_334_ = depthListIndices[i_333_];
					if (i_334_ > 0) {
						int[] is = faceLists[i_333_];
						for (int i_335_ = 0; i_335_ < i_334_; i_335_++) {
							render_face(rasterizer, is[i_335_]);
						}
					}
				}
			} else {
				for (int i_336_ = 0; i_336_ < 12; i_336_++) {
					anIntArray4362[i_336_] = 0;
					anIntArray3944[i_336_] = 0;
				}
				for (int i_337_ = i_310_ - 1; i_337_ >= 0; i_337_--) {
					int i_338_ = depthListIndices[i_337_];
					if (i_338_ > 0) {
						int[] is = faceLists[i_337_];
						for (int i_339_ = 0; i_339_ < i_338_; i_339_++) {
							int i_340_ = is[i_339_];
							byte b = faces_priority[i_340_];
							int i_341_ = anIntArray4362[b]++;
							anIntArrayArray3938[b][i_341_] = i_340_;
							if (b < 10) {
								anIntArray3944[b] += i_337_;
							} else if (b == 10) {
								anIntArray3922[i_341_] = i_337_;
							} else {
								anIntArray3941[i_341_] = i_337_;
							}
						}
					}
				}
				int i_342_ = 0;
				if (anIntArray4362[1] > 0 || anIntArray4362[2] > 0) {
					i_342_ = (anIntArray3944[1] + anIntArray3944[2]) / (anIntArray4362[1] + anIntArray4362[2]);
				}
				int i_343_ = 0;
				if (anIntArray4362[3] > 0 || anIntArray4362[4] > 0) {
					i_343_ = (anIntArray3944[3] + anIntArray3944[4]) / (anIntArray4362[3] + anIntArray4362[4]);
				}
				int i_344_ = 0;
				if (anIntArray4362[6] > 0 || anIntArray4362[8] > 0) {
					i_344_ = (anIntArray3944[6] + anIntArray3944[8]) / (anIntArray4362[6] + anIntArray4362[8]);
				}
				int i_345_ = 0;
				int i_346_ = anIntArray4362[10];
				int[] is = anIntArrayArray3938[10];
				int[] is_347_ = anIntArray3922;
				if (i_345_ == i_346_) {
					i_345_ = 0;
					i_346_ = anIntArray4362[11];
					is = anIntArrayArray3938[11];
					is_347_ = anIntArray3941;
				}
				int i_348_;
				if (i_345_ < i_346_) {
					i_348_ = is_347_[i_345_];
				} else {
					i_348_ = -1000;
				}
				for (int i_349_ = 0; i_349_ < 10; i_349_++) {
					while (i_349_ == 0) {
						if (i_348_ <= i_342_) {
							break;
						}
						render_face(rasterizer, is[i_345_++]);
						if (i_345_ == i_346_ && is != anIntArrayArray3938[11]) {
							i_345_ = 0;
							i_346_ = anIntArray4362[11];
							is = anIntArrayArray3938[11];
							is_347_ = anIntArray3941;
						}
						if (i_345_ < i_346_) {
							i_348_ = is_347_[i_345_];
						} else {
							i_348_ = -1000;
						}
					}
					while (i_349_ == 3) {
						if (i_348_ <= i_343_) {
							break;
						}
						render_face(rasterizer, is[i_345_++]);
						if (i_345_ == i_346_ && is != anIntArrayArray3938[11]) {
							i_345_ = 0;
							i_346_ = anIntArray4362[11];
							is = anIntArrayArray3938[11];
							is_347_ = anIntArray3941;
						}
						if (i_345_ < i_346_) {
							i_348_ = is_347_[i_345_];
						} else {
							i_348_ = -1000;
						}
					}
					while (i_349_ == 5 && i_348_ > i_344_) {
						render_face(rasterizer, is[i_345_++]);
						if (i_345_ == i_346_ && is != anIntArrayArray3938[11]) {
							i_345_ = 0;
							i_346_ = anIntArray4362[11];
							is = anIntArrayArray3938[11];
							is_347_ = anIntArray3941;
						}
						if (i_345_ < i_346_) {
							i_348_ = is_347_[i_345_];
						} else {
							i_348_ = -1000;
						}
					}
					int i_350_ = anIntArray4362[i_349_];
					int[] is_351_ = anIntArrayArray3938[i_349_];
					for (int i_352_ = 0; i_352_ < i_350_; i_352_++) {
						render_face(rasterizer, is_351_[i_352_]);
					}
				}
				while (i_348_ != -1000) {
					render_face(rasterizer, is[i_345_++]);
					if (i_345_ == i_346_ && is != anIntArrayArray3938[11]) {
						i_345_ = 0;
						is = anIntArrayArray3938[11];
						i_346_ = anIntArray4362[11];
						is_347_ = anIntArray3941;
					}
					if (i_345_ < i_346_) {
						i_348_ = is_347_[i_345_];
					} else {
						i_348_ = -1000;
					}
				}
			}
		}
	}

	static {
		model = new SoftwareModel();
		anIntArray3944 = new int[12];
		depthListIndices = new int[8000];
		faceLists = new int[8000][512];
		projected_vertices_z = new int[8000];
		out_of_screen = new boolean[10000];
		camera_vertex_z = new int[8000];
		projected_vertices_x = new int[8000];
		anIntArray1679 = new int[10];
		anIntArray3936 = new int[10];
		camera_vertex_x = new int[8000];
		projected_vertices_y = new int[8000];
		anIntArray3922 = new int[8000];
		anIntArray1678 = new int[10];
		camera_vertex_y = new int[8000];
		anIntArray3941 = new int[8000];
		hasRestrictedEdges = new boolean[10000];
		anIntArrayArray3938 = new int[12][10000];
		anIntArray4362 = new int[12];
		aByteArray4363 = new byte[1];
		aByteArray3933 = new byte[1];
	}

	@Override
	public boolean animate_start() {
		if (vertices_by_label == null) {
			return false;
		} else {
			origin_x = 0;
			origin_y = 0;
			origin_z = 0;
			return true;
		}
	}

	@Override
	public void animate_end() {
		if (animation_precision) {
			method1936();
			animation_precision = false;
		}
		bounds_updated = false;
	}

	@Override
	public void animate_transform(int var1, int[] var2, int var3, int var4, int var5, boolean var6) {
		int var7 = var2.length;
		int var8;
		int var9;
		int var12;
		int var13;
		if (var1 == 0) {
			var8 = 0;
			origin_x = 0;
			origin_y = 0;
			origin_z = 0;

			for (var9 = 0; var9 < var7; ++var9) {
				int var17 = var2[var9];
				if (var17 < vertices_by_label.length) {
					int[] var18 = vertices_by_label[var17];

					for (var12 = 0; var12 < var18.length; ++var12) {
						var13 = var18[var12];
						origin_x += vertices_x[var13];
						origin_y += vertices_y[var13];
						origin_z += vertices_z[var13];
						++var8;
					}
				}
			}

			if (var8 > 0) {
				origin_x = origin_x / var8 + var3;
				origin_y = origin_y / var8 + var4;
				origin_z = origin_z / var8 + var5;
			} else {
				origin_x = var3;
				origin_y = var4;
				origin_z = var5;
			}

		} else {
			int[] var10;
			int var11;
			if (var1 == 1) {
				for (var8 = 0; var8 < var7; ++var8) {
					var9 = var2[var8];
					if (var9 < vertices_by_label.length) {
						var10 = vertices_by_label[var9];

						for (var11 = 0; var11 < var10.length; ++var11) {
							var12 = var10[var11];
							vertices_x[var12] += var3;
							vertices_y[var12] += var4;
							vertices_z[var12] += var5;
						}
					}
				}

			} else {
				int var14;
				int var15;
				if (var1 == 2) {
					for (var8 = 0; var8 < var7; ++var8) {
						var9 = var2[var8];
						if (var9 < vertices_by_label.length) {
							var10 = vertices_by_label[var9];

							for (var11 = 0; var11 < var10.length; ++var11) {
								var12 = var10[var11];
								vertices_x[var12] -= origin_x;
								vertices_y[var12] -= origin_y;
								vertices_z[var12] -= origin_z;
								if (var5 != 0) {
									var13 = Rasterizer.SINE[var5];
									var14 = Rasterizer.COSINE[var5];
									var15 = vertices_y[var12] * var13 + vertices_x[var12] * var14 + 32767 >> 16;
									vertices_y[var12] = vertices_y[var12] * var14 - vertices_x[var12] * var13 + 32767 >> 16;
									vertices_x[var12] = var15;
								}

								if (var3 != 0) {
									var13 = Rasterizer.SINE[var3];
									var14 = Rasterizer.COSINE[var3];
									var15 = vertices_y[var12] * var14 - vertices_z[var12] * var13 + 32767 >> 16;
									vertices_z[var12] = vertices_y[var12] * var13 + vertices_z[var12] * var14 + 32767 >> 16;
									vertices_y[var12] = var15;
								}

								if (var4 != 0) {
									var13 = Rasterizer.SINE[var4];
									var14 = Rasterizer.COSINE[var4];
									var15 = vertices_z[var12] * var13 + vertices_x[var12] * var14 + 32767 >> 16;
									vertices_z[var12] = vertices_z[var12] * var14 - vertices_x[var12] * var13 + 32767 >> 16;
									vertices_x[var12] = var15;
								}

								vertices_x[var12] += origin_x;
								vertices_y[var12] += origin_y;
								vertices_z[var12] += origin_z;
							}
						}
					}

				} else if (var1 == 3) {
					for (var8 = 0; var8 < var7; ++var8) {
						var9 = var2[var8];
						if (var9 < vertices_by_label.length) {
							var10 = vertices_by_label[var9];

							for (var11 = 0; var11 < var10.length; ++var11) {
								var12 = var10[var11];
								vertices_x[var12] -= origin_x;
								vertices_y[var12] -= origin_y;
								vertices_z[var12] -= origin_z;
								vertices_x[var12] = vertices_x[var12] * var3 / 128;
								vertices_y[var12] = vertices_y[var12] * var4 / 128;
								vertices_z[var12] = vertices_z[var12] * var5 / 128;
								vertices_x[var12] += origin_x;
								vertices_y[var12] += origin_y;
								vertices_z[var12] += origin_z;
							}
						}
					}

				} else if (var1 == 5) {
					if (faces_by_label != null && faces_alpha != null) {
						for (var8 = 0; var8 < var7; ++var8) {
							var9 = var2[var8];
							if (var9 < faces_by_label.length) {
								var10 = faces_by_label[var9];

								for (var11 = 0; var11 < var10.length; ++var11) {
									var12 = var10[var11];
									var13 = (faces_alpha[var12] & 255) + var3 * 8;
									if (var13 < 0) {
										var13 = 0;
									} else if (var13 > 255) {
										var13 = 255;
									}

									faces_alpha[var12] = (byte) var13;
								}
							}
						}
					}

				} else if (var1 == 7) {
					if (faces_by_label != null) {
						for (var8 = 0; var8 < var7; ++var8) {
							var9 = var2[var8];
							if (var9 < faces_by_label.length) {
								var10 = faces_by_label[var9];

								for (var11 = 0; var11 < var10.length; ++var11) {
									var12 = var10[var11];
									var13 = faces_colour[var12] & '\uffff';
									var14 = var13 >> 10 & 63;
									var15 = var13 >> 7 & 7;
									int var16 = var13 & 127;
									var14 = var14 + var3 & 63;
									var15 += var4;
									if (var15 < 0) {
										var15 = 0;
									} else if (var15 > 7) {
										var15 = 7;
									}

									var16 += var5;
									if (var16 < 0) {
										var16 = 0;
									} else if (var16 > 127) {
										var16 = 127;
									}

									faces_colour[var12] = (short) (var14 << 10 | var15 << 7 | var16);
								}

								animation_precision = true;
							}
						}
					}

				}
			}
		}
	}

	@Override
	public void animate_partialtransform(int var1, int[] var2, int var3, int var4, int var5, boolean var6, int var7, int[] var8) {
		int var9 = var2.length;
		int var10;
		int var11;
		int var14;
		int var15;
		int var47;
		if (var1 == 0) {
			var10 = 0;
			origin_x = 0;
			origin_y = 0;
			origin_z = 0;

			for (var11 = 0; var11 < var9; ++var11) {
				var47 = var2[var11];
				if (var47 < vertices_by_label.length) {
					int[] var48 = vertices_by_label[var47];

					for (var14 = 0; var14 < var48.length; ++var14) {
						var15 = var48[var14];
						if (vertices_submeshes == null || (var7 & vertices_submeshes[var15]) != 0) {
							origin_x += vertices_x[var15];
							origin_y += vertices_y[var15];
							origin_z += vertices_z[var15];
							++var10;
						}
					}
				}
			}

			if (var10 > 0) {
				origin_x = origin_x / var10 + var3;
				origin_y = origin_y / var10 + var4;
				origin_z = origin_z / var10 + var5;
				within_origin = true;
			} else {
				origin_x = var3;
				origin_y = var4;
				origin_z = var5;
			}

		} else {
			int[] var12;
			int var13;
			if (var1 == 1) {
				if (var8 != null) {
					var10 = var8[0] * var3 + var8[1] * var4 + var8[2] * var5 + 16384 >> 15;
					var11 = var8[3] * var3 + var8[4] * var4 + var8[5] * var5 + 16384 >> 15;
					var47 = var8[6] * var3 + var8[7] * var4 + var8[8] * var5 + 16384 >> 15;
					var3 = var10;
					var4 = var11;
					var5 = var47;
				}

				for (var10 = 0; var10 < var9; ++var10) {
					var11 = var2[var10];
					if (var11 < vertices_by_label.length) {
						var12 = vertices_by_label[var11];

						for (var13 = 0; var13 < var12.length; ++var13) {
							var14 = var12[var13];
							if (vertices_submeshes == null || (var7 & vertices_submeshes[var14]) != 0) {
								vertices_x[var14] += var3;
								vertices_y[var14] += var4;
								vertices_z[var14] += var5;
							}
						}
					}
				}

			} else {
				int var17;
				int var16;
				int var19;
				int var18;
				int var21;
				int var20;
				int var23;
				int var22;
				int var24;
				int var27;
				int var26;
				int var28;
				int var30;
				int var34;
				int[] var35;
				int var32;
				int var33;
				int var38;
				int var39;
				int var36;
				int var37;
				int var40;
				if (var1 == 2) {
					if (var8 != null) {
						var10 = var8[9];
						var11 = var8[10];
						var47 = var8[11];
						var13 = var8[12];
						var14 = var8[13];
						var15 = var8[14];
						if (within_origin) {
							var16 = var8[0] * origin_x + var8[3] * origin_y + var8[6] * origin_z + 16384 >> 15;
							var17 = var8[1] * origin_x + var8[4] * origin_y + var8[7] * origin_z + 16384 >> 15;
							var18 = var8[2] * origin_x + var8[5] * origin_y + var8[8] * origin_z + 16384 >> 15;
							var16 += var13;
							var17 += var14;
							var18 += var15;
							origin_x = var16;
							origin_y = var17;
							origin_z = var18;
							within_origin = false;
						}

						int[] var49 = new int[9];
						var17 = Rasterizer.COSINE[var3] >> 1;
						var18 = Rasterizer.SINE[var3] >> 1;
						var19 = Rasterizer.COSINE[var4] >> 1;
						var20 = Rasterizer.SINE[var4] >> 1;
						var21 = Rasterizer.COSINE[var5] >> 1;
						var22 = Rasterizer.SINE[var5] >> 1;
						var23 = var18 * var21 + 16384 >> 15;
						var24 = var18 * var22 + 16384 >> 15;
						var49[0] = var19 * var21 + var20 * var24 + 16384 >> 15;
						var49[1] = -var19 * var22 + var20 * var23 + 16384 >> 15;
						var49[2] = var20 * var17 + 16384 >> 15;
						var49[3] = var17 * var22 + 16384 >> 15;
						var49[4] = var17 * var21 + 16384 >> 15;
						var49[5] = -var18;
						var49[6] = -var20 * var21 + var19 * var24 + 16384 >> 15;
						var49[7] = var20 * var22 + var19 * var23 + 16384 >> 15;
						var49[8] = var19 * var17 + 16384 >> 15;
						int var50 = var49[0] * -origin_x + var49[1] * -origin_y + var49[2] * -origin_z + 16384 >> 15;
						var26 = var49[3] * -origin_x + var49[4] * -origin_y + var49[5] * -origin_z + 16384 >> 15;
						var27 = var49[6] * -origin_x + var49[7] * -origin_y + var49[8] * -origin_z + 16384 >> 15;
						var28 = var50 + origin_x;
						int var51 = var26 + origin_y;
						var30 = var27 + origin_z;
						int[] var52 = new int[9];

						for (var32 = 0; var32 < 3; ++var32) {
							for (var33 = 0; var33 < 3; ++var33) {
								var34 = 0;

								for (int var53 = 0; var53 < 3; ++var53) {
									var34 += var49[var32 * 3 + var53] * var8[var33 * 3 + var53];
								}

								var52[var32 * 3 + var33] = var34 + 16384 >> 15;
							}
						}

						var32 = var49[0] * var13 + var49[1] * var14 + var49[2] * var15 + 16384 >> 15;
						var33 = var49[3] * var13 + var49[4] * var14 + var49[5] * var15 + 16384 >> 15;
						var34 = var49[6] * var13 + var49[7] * var14 + var49[8] * var15 + 16384 >> 15;
						var32 += var28;
						var33 += var51;
						var34 += var30;
						var35 = new int[9];

						for (var36 = 0; var36 < 3; ++var36) {
							for (var37 = 0; var37 < 3; ++var37) {
								var38 = 0;

								for (var39 = 0; var39 < 3; ++var39) {
									var38 += var8[var36 * 3 + var39] * var52[var37 + var39 * 3];
								}

								var35[var36 * 3 + var37] = var38 + 16384 >> 15;
							}
						}

						var36 = var8[0] * var32 + var8[1] * var33 + var8[2] * var34 + 16384 >> 15;
						var37 = var8[3] * var32 + var8[4] * var33 + var8[5] * var34 + 16384 >> 15;
						var38 = var8[6] * var32 + var8[7] * var33 + var8[8] * var34 + 16384 >> 15;
						var36 += var10;
						var37 += var11;
						var38 += var47;

						for (var39 = 0; var39 < var9; ++var39) {
							var40 = var2[var39];
							if (var40 < vertices_by_label.length) {
								int[] var41 = vertices_by_label[var40];

								for (int element : var41) {
									int var43 = element;
									if (vertices_submeshes == null || (var7 & vertices_submeshes[var43]) != 0) {
										int var44 = var35[0] * vertices_x[var43] + var35[1] * vertices_y[var43] + var35[2] * vertices_z[var43] + 16384 >> 15;
										int var45 = var35[3] * vertices_x[var43] + var35[4] * vertices_y[var43] + var35[5] * vertices_z[var43] + 16384 >> 15;
										int var46 = var35[6] * vertices_x[var43] + var35[7] * vertices_y[var43] + var35[8] * vertices_z[var43] + 16384 >> 15;
										var44 += var36;
										var45 += var37;
										var46 += var38;
										vertices_x[var43] = var44;
										vertices_y[var43] = var45;
										vertices_z[var43] = var46;
									}
								}
							}
						}
					} else {
						for (var10 = 0; var10 < var9; ++var10) {
							var11 = var2[var10];
							if (var11 < vertices_by_label.length) {
								var12 = vertices_by_label[var11];

								for (var13 = 0; var13 < var12.length; ++var13) {
									var14 = var12[var13];
									if (vertices_submeshes == null || (var7 & vertices_submeshes[var14]) != 0) {
										vertices_x[var14] -= origin_x;
										vertices_y[var14] -= origin_y;
										vertices_z[var14] -= origin_z;
										if (var5 != 0) {
											var15 = Rasterizer.SINE[var5];
											var16 = Rasterizer.COSINE[var5];
											var17 = vertices_y[var14] * var15 + vertices_x[var14] * var16 + 32767 >> 16;
											vertices_y[var14] = vertices_y[var14] * var16 - vertices_x[var14] * var15 + 32767 >> 16;
											vertices_x[var14] = var17;
										}

										if (var3 != 0) {
											var15 = Rasterizer.SINE[var3];
											var16 = Rasterizer.COSINE[var3];
											var17 = vertices_y[var14] * var16 - vertices_z[var14] * var15 + 32767 >> 16;
											vertices_z[var14] = vertices_y[var14] * var15 + vertices_z[var14] * var16 + 32767 >> 16;
											vertices_y[var14] = var17;
										}

										if (var4 != 0) {
											var15 = Rasterizer.SINE[var4];
											var16 = Rasterizer.COSINE[var4];
											var17 = vertices_z[var14] * var15 + vertices_x[var14] * var16 + 32767 >> 16;
											vertices_z[var14] = vertices_z[var14] * var16 - vertices_x[var14] * var15 + 32767 >> 16;
											vertices_x[var14] = var17;
										}

										vertices_x[var14] += origin_x;
										vertices_y[var14] += origin_y;
										vertices_z[var14] += origin_z;
									}
								}
							}
						}
					}

				} else if (var1 == 3) {
					if (var8 != null) {
						var10 = var8[9];
						var11 = var8[10];
						var47 = var8[11];
						var13 = var8[12];
						var14 = var8[13];
						var15 = var8[14];
						if (within_origin) {
							var16 = var8[0] * origin_x + var8[3] * origin_y + var8[6] * origin_z + 16384 >> 15;
							var17 = var8[1] * origin_x + var8[4] * origin_y + var8[7] * origin_z + 16384 >> 15;
							var18 = var8[2] * origin_x + var8[5] * origin_y + var8[8] * origin_z + 16384 >> 15;
							var16 += var13;
							var17 += var14;
							var18 += var15;
							origin_x = var16;
							origin_y = var17;
							origin_z = var18;
							within_origin = false;
						}

						var16 = var3 << 15 >> 7;
						var17 = var4 << 15 >> 7;
						var18 = var5 << 15 >> 7;
						var19 = var16 * -origin_x + 16384 >> 15;
						var20 = var17 * -origin_y + 16384 >> 15;
						var21 = var18 * -origin_z + 16384 >> 15;
						var22 = var19 + origin_x;
						var23 = var20 + origin_y;
						var24 = var21 + origin_z;
						int[] var25 = new int[] { var16 * var8[0] + 16384 >> 15, var16 * var8[3] + 16384 >> 15, var16 * var8[6] + 16384 >> 15, var17 * var8[1] + 16384 >> 15, var17 * var8[4] + 16384 >> 15, var17 * var8[7] + 16384 >> 15, var18 * var8[2] + 16384 >> 15, var18 * var8[5] + 16384 >> 15, var18 * var8[8] + 16384 >> 15 };
						var26 = var16 * var13 + 16384 >> 15;
						var27 = var17 * var14 + 16384 >> 15;
						var28 = var18 * var15 + 16384 >> 15;
						var26 += var22;
						var27 += var23;
						var28 += var24;
						int[] var29 = new int[9];

						int var31;
						for (var30 = 0; var30 < 3; ++var30) {
							for (var31 = 0; var31 < 3; ++var31) {
								var32 = 0;

								for (var33 = 0; var33 < 3; ++var33) {
									var32 += var8[var30 * 3 + var33] * var25[var31 + var33 * 3];
								}

								var29[var30 * 3 + var31] = var32 + 16384 >> 15;
							}
						}

						var30 = var8[0] * var26 + var8[1] * var27 + var8[2] * var28 + 16384 >> 15;
						var31 = var8[3] * var26 + var8[4] * var27 + var8[5] * var28 + 16384 >> 15;
						var32 = var8[6] * var26 + var8[7] * var27 + var8[8] * var28 + 16384 >> 15;
						var30 += var10;
						var31 += var11;
						var32 += var47;

						for (var33 = 0; var33 < var9; ++var33) {
							var34 = var2[var33];
							if (var34 < vertices_by_label.length) {
								var35 = vertices_by_label[var34];

								for (var36 = 0; var36 < var35.length; ++var36) {
									var37 = var35[var36];
									if (vertices_submeshes == null || (var7 & vertices_submeshes[var37]) != 0) {
										var38 = var29[0] * vertices_x[var37] + var29[1] * vertices_y[var37] + var29[2] * vertices_z[var37] + 16384 >> 15;
										var39 = var29[3] * vertices_x[var37] + var29[4] * vertices_y[var37] + var29[5] * vertices_z[var37] + 16384 >> 15;
										var40 = var29[6] * vertices_x[var37] + var29[7] * vertices_y[var37] + var29[8] * vertices_z[var37] + 16384 >> 15;
										var38 += var30;
										var39 += var31;
										var40 += var32;
										vertices_x[var37] = var38;
										vertices_y[var37] = var39;
										vertices_z[var37] = var40;
									}
								}
							}
						}
					} else {
						for (var10 = 0; var10 < var9; ++var10) {
							var11 = var2[var10];
							if (var11 < vertices_by_label.length) {
								var12 = vertices_by_label[var11];

								for (var13 = 0; var13 < var12.length; ++var13) {
									var14 = var12[var13];
									if (vertices_submeshes == null || (var7 & vertices_submeshes[var14]) != 0) {
										vertices_x[var14] -= origin_x;
										vertices_y[var14] -= origin_y;
										vertices_z[var14] -= origin_z;
										vertices_x[var14] = vertices_x[var14] * var3 / 128;
										vertices_y[var14] = vertices_y[var14] * var4 / 128;
										vertices_z[var14] = vertices_z[var14] * var5 / 128;
										vertices_x[var14] += origin_x;
										vertices_y[var14] += origin_y;
										vertices_z[var14] += origin_z;
									}
								}
							}
						}
					}

				} else if (var1 == 5) {
					if (faces_by_label != null && faces_alpha != null) {
						for (var10 = 0; var10 < var9; ++var10) {
							var11 = var2[var10];
							if (var11 < faces_by_label.length) {
								var12 = faces_by_label[var11];

								for (var13 = 0; var13 < var12.length; ++var13) {
									var14 = var12[var13];
									if (faces_submeshes == null || (var7 & faces_submeshes[var14]) != 0) {
										var15 = (faces_alpha[var14] & 255) + var3 * 8;
										if (var15 < 0) {
											var15 = 0;
										} else if (var15 > 255) {
											var15 = 255;
										}

										faces_alpha[var14] = (byte) var15;
									}
								}
							}
						}
					}

				} else if (var1 == 7) {
					if (faces_by_label != null) {
						for (var10 = 0; var10 < var9; ++var10) {
							var11 = var2[var10];
							if (var11 < faces_by_label.length) {
								var12 = faces_by_label[var11];

								for (var13 = 0; var13 < var12.length; ++var13) {
									var14 = var12[var13];
									if (faces_submeshes == null || (var7 & faces_submeshes[var14]) != 0) {
										var15 = faces_colour[var14] & '\uffff';
										var16 = var15 >> 10 & 63;
										var17 = var15 >> 7 & 7;
										var18 = var15 & 127;
										var16 = var16 + var3 & 63;
										var17 += var4;
										if (var17 < 0) {
											var17 = 0;
										} else if (var17 > 7) {
											var17 = 7;
										}

										var18 += var5;
										if (var18 < 0) {
											var18 = 0;
										} else if (var18 > 127) {
											var18 = 127;
										}

										faces_colour[var14] = (short) (var16 << 10 | var17 << 7 | var18);
									}
								}

								animation_precision = true;
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void animate_shadowtransform(int var1, int var2, int var3, int var4) {
		int var5;
		int var6;
		if (var1 == 0) {
			var5 = 0;
			origin_x = 0;
			origin_y = 0;
			origin_z = 0;

			for (var6 = 0; var6 < num_vertices; ++var6) {
				origin_x += vertices_x[var6];
				origin_y += vertices_y[var6];
				origin_z += vertices_z[var6];
				++var5;
			}

			if (var5 > 0) {
				origin_x = origin_x / var5 + var2;
				origin_y = origin_y / var5 + var3;
				origin_z = origin_z / var5 + var4;
			} else {
				origin_x = var2;
				origin_y = var3;
				origin_z = var4;
			}

		} else if (var1 == 1) {
			for (var5 = 0; var5 < num_vertices; ++var5) {
				vertices_x[var5] += var2;
				vertices_y[var5] += var3;
				vertices_z[var5] += var4;
			}

		} else {
			int var7;
			int var8;
			if (var1 == 2) {
				for (var5 = 0; var5 < num_vertices; ++var5) {
					vertices_x[var5] -= origin_x;
					vertices_y[var5] -= origin_y;
					vertices_z[var5] -= origin_z;
					if (var4 != 0) {
						var6 = Rasterizer.SINE[var4];
						var7 = Rasterizer.COSINE[var4];
						var8 = vertices_y[var5] * var6 + vertices_x[var5] * var7 + 32767 >> 16;
						vertices_y[var5] = vertices_y[var5] * var7 - vertices_x[var5] * var6 + 32767 >> 16;
						vertices_x[var5] = var8;
					}

					if (var2 != 0) {
						var6 = Rasterizer.SINE[var2];
						var7 = Rasterizer.COSINE[var2];
						var8 = vertices_y[var5] * var7 - vertices_z[var5] * var6 + 32767 >> 16;
						vertices_z[var5] = vertices_y[var5] * var6 + vertices_z[var5] * var7 + 32767 >> 16;
						vertices_y[var5] = var8;
					}

					if (var3 != 0) {
						var6 = Rasterizer.SINE[var3];
						var7 = Rasterizer.COSINE[var3];
						var8 = vertices_z[var5] * var6 + vertices_x[var5] * var7 + 32767 >> 16;
						vertices_z[var5] = vertices_z[var5] * var7 - vertices_x[var5] * var6 + 32767 >> 16;
						vertices_x[var5] = var8;
					}

					vertices_x[var5] += origin_x;
					vertices_y[var5] += origin_y;
					vertices_z[var5] += origin_z;
				}

			} else if (var1 != 3) {
				if (var1 == 5) {
					for (var5 = 0; var5 < num_faces; ++var5) {
						var6 = (faces_alpha[var5] & 255) + var2 * 8;
						if (var6 < 0) {
							var6 = 0;
						} else if (var6 > 255) {
							var6 = 255;
						}

						faces_alpha[var5] = (byte) var6;
					}

				} else if (var1 == 7) {
					for (var5 = 0; var5 < num_faces; ++var5) {
						var6 = faces_colour[var5] & '\uffff';
						var7 = var6 >> 10 & 63;
						var8 = var6 >> 7 & 7;
						int var9 = var6 & 127;
						var7 = var7 + var2 & 63;
						var8 += var3;
						if (var8 < 0) {
							var8 = 0;
						} else if (var8 > 7) {
							var8 = 7;
						}

						var9 += var4;
						if (var9 < 0) {
							var9 = 0;
						} else if (var9 > 127) {
							var9 = 127;
						}

						faces_colour[var5] = (short) (var7 << 10 | var8 << 7 | var9);
					}
					animation_precision = true;
				}
			} else {
				for (var5 = 0; var5 < num_vertices; ++var5) {
					vertices_x[var5] -= origin_x;
					vertices_y[var5] -= origin_y;
					vertices_z[var5] -= origin_z;
					vertices_x[var5] = vertices_x[var5] * var2 / 128;
					vertices_y[var5] = vertices_y[var5] * var3 / 128;
					vertices_z[var5] = vertices_z[var5] * var4 / 128;
					vertices_x[var5] += origin_x;
					vertices_y[var5] += origin_y;
					vertices_z[var5] += origin_z;
				}

			}
		}
	}

	@Override
	public Model copy3(boolean reference_alphas, boolean reference_colors, boolean reference_normals) {
		if (!reference_alphas && aByteArray3933.length < num_faces) {
			aByteArray3933 = new byte[num_faces + 100];
		}
		return copy(reference_alphas, model, aByteArray3933);
	}

	private static final int method1937(int var0) {
		if (var0 < 2) {
			var0 = 2;
		} else if (var0 > 126) {
			var0 = 126;
		}

		return var0;
	}
}
