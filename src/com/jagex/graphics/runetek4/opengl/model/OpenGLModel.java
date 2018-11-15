package com.jagex.graphics.runetek4.opengl.model;

import static jaggl.GLConstants.GL_FLOAT;
import static jaggl.GLConstants.GL_POINT_SIZE_MAX;
import static jaggl.GLConstants.GL_UNSIGNED_BYTE;
import static jaggl.GLConstants.GL_UNSIGNED_SHORT;
import static jaggl.OpenGL.glGetFloatv;
import static jaggl.OpenGL.glPopMatrix;
import static jaggl.OpenGL.glPushMatrix;
import static jaggl.OpenGL.glRotatef;
import static jaggl.OpenGL.glTranslatef;

import com.jagex.*;
import com.jagex.game.runetek4.clientoptions.ClientOptions;
import com.jagex.graphics.runetek4.media.Model;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.GLState;
import com.jagex.graphics.runetek4.opengl.buffer.OpenGLVertexAttribute;
import com.jagex.graphics.runetek4.opengl.buffer.VertexBuffer;
import com.jagex.graphics.runetek4.opengl.environment.OpenGLEnvironment;
import com.jagex.graphics.runetek4.software.sprite.SoftwarePaletteSprite;
import com.rs2.client.scene.Scene;

import jaggl.OpenGL;
import me.waliedyassen.graphics.rasterizer.AlphaMode;
import me.waliedyassen.graphics.rasterizer.Rasterizer;
import me.waliedyassen.materials.MaterialRaw;

/**
 * @author Walied K. Yassen
 */
public final class OpenGLModel extends Model {

	public static Packet buffer = new Packet(10000);
	public static OpenGLModel copy_one_model = new OpenGLModel();
	public static OpenGLModel copy_one_target = new OpenGLModel();
	public static OpenGLModel copy_two_model = new OpenGLModel();
	public static OpenGLModel copy_two_target = new OpenGLModel();
	public static OpenGLModel copy_three_model = new OpenGLModel();
	public static OpenGLModel copy_three_target = new OpenGLModel();
	public static int[] shadow_vertices_x = new int[1];
	public static int[] shadow_vertices_y = new int[1];
	public static float mapped_cylinderical_u;
	public static float mapped_cylinderical_v;
	public static float rectangular_u;
	public static float rectangular_v;
	public static float spherical_u;
	public static float spherical_v;
	public static long[] faces_uid_cache;
	public static int origin_x;
	public static int origin_y;
	public static int origin_z;
	public static boolean origin_attached;

	public int num_rendering_vertices;
	public int num_rendering_faces;
	public int num_vertices;
	public int[] vertices_y;
	public int[] vertices_x;
	public int[] vertices_z;
	public float[] vertices_u;
	public float[] vertices_v;
	public short[] normals_x;
	public short[] normals_y;
	public short[] normals_z;
	public short[] normals_m;
	public short[] faces_a;
	public short[] faces_b;
	public short[] faces_c;
	public short[] faces_colour;
	public short[] faces_submeshes;
	public short[] faces_materials;
	public byte[] faces_alpha;
	public byte[] faces_label;
	public short ambient;
	public short contrast;
	public short[] vertices_submeshes;
	public short[] vertices_renderlist;
	public int[][] faces_by_label;
	public int[][] vertices_by_label;
	public int[] shared_vertices;
	public int[] rendering_lists;
	public int[] vertices_label;
	public byte vertices_buffer_state;
	public byte functionmask;
	public BoundingBox bounding_box;
	public VertexNormalList saved_normals;
	public VertexBuffer vertices_buffer;
	public VertexBuffer temporary_buffer;
	public OpenGLModelIndexBuffer elements_buffer;
	public OpenGLVertexAttribute positions_attribute;
	public OpenGLVertexAttribute colours_attribute;
	public OpenGLVertexAttribute normals_attribute;
	public OpenGLVertexAttribute texcoords_attribute;
	private boolean positions_referenced;
	public boolean aBoolean3809 = false;

	private OpenGLModel() {
		positions_attribute = new OpenGLVertexAttribute(null, GL_FLOAT, 3, 0);
		texcoords_attribute = new OpenGLVertexAttribute(null, GL_FLOAT, 2, 0);
		normals_attribute = new OpenGLVertexAttribute(null, GL_FLOAT, 3, 0);
		colours_attribute = new OpenGLVertexAttribute(null, GL_UNSIGNED_BYTE, 4, 0);
		elements_buffer = new OpenGLModelIndexBuffer();
	}

	public OpenGLModel(Mesh mesh, int var2, int var3, boolean use_layers) {
		int[] rendering_faces = new int[mesh.num_faces];
		shared_vertices = new int[mesh.num_vertices + 1];
		for (int face = 0; face < mesh.num_faces; ++face) {
			if (mesh.faces_type == null || mesh.faces_type[face] != 2) {
				if (mesh.faces_material != null && mesh.faces_material[face] != -1) {
					MaterialRaw material = GraphicTools.materials.get_material(mesh.faces_material[face] & 0xffff);
					if (material.terrains_only) {
						continue;
					}
				}
				rendering_faces[num_rendering_faces++] = face;
				shared_vertices[mesh.faces_a[face]]++;
				shared_vertices[mesh.faces_b[face]]++;
				shared_vertices[mesh.faces_c[face]]++;
			}
		}
		long[] render_order = new long[num_rendering_faces];
		for (int face_index = 0; face_index < num_rendering_faces; ++face_index) {
			int var8 = rendering_faces[face_index];
			int layer = 0;
			short materialid = -1;
			int effect_id = 0;
			int effect_param1 = 0;
			if (mesh.faces_material != null) {
				materialid = mesh.faces_material[var8];
				if (materialid != -1) {
					MaterialRaw material = GraphicTools.get_materials().get_material(materialid & 0xffff);
					effect_id = material.effect_type;
					effect_param1 = material.effect_param1;
				}
			}
			boolean consider_layers = mesh.faces_alpha != null && mesh.faces_alpha[var8] != 0 || materialid != -1 && GraphicTools.get_materials().get_material(mesh.faces_material[var8] & 0xffff).alpha_mode != AlphaMode.ALPHA_OPAQUE;
			if ((use_layers || consider_layers) && mesh.faces_layer != null) {
				layer += mesh.faces_layer[var8] << 17;
			}
			if (consider_layers) {
				layer += 65536;
			}
			layer += (effect_id & 255) << 8;
			layer += effect_param1 & 255;
			int lsp = face_index & 0xffff;
			render_order[face_index] = ((long) layer << 32) + lsp;
		}
		ArrayUtils.quicksort(rendering_faces, render_order);
		num_vertices = mesh.num_vertices;
		vertices_x = mesh.vertices_x;
		vertices_y = mesh.vertices_y;
		vertices_z = mesh.vertices_z;
		vertices_label = mesh.vertices_label;
		vertices_submeshes = mesh.vertices_submeshes;
		int var7 = num_rendering_faces * 3;
		normals_x = new short[var7];
		normals_y = new short[var7];
		normals_z = new short[var7];
		normals_m = new short[var7];
		vertices_u = new float[var7];
		vertices_v = new float[var7];
		faces_colour = new short[num_rendering_faces];
		faces_alpha = new byte[num_rendering_faces];
		faces_a = new short[num_rendering_faces];
		faces_b = new short[num_rendering_faces];
		faces_c = new short[num_rendering_faces];
		faces_materials = new short[num_rendering_faces];
		if (mesh.faces_label != null) {
			faces_label = new byte[num_rendering_faces];
		}
		if (mesh.faces_submeshes != null) {
			faces_submeshes = new short[num_rendering_faces];
		}
		bounding_box = new BoundingBox();
		positions_attribute = new OpenGLVertexAttribute(null, GL_FLOAT, 3, 0);
		colours_attribute = new OpenGLVertexAttribute(null, GL_UNSIGNED_BYTE, 4, 0);
		if (ClientOptions.clientoption_highdetails_lighting) {
			normals_attribute = new OpenGLVertexAttribute(null, GL_FLOAT, 3, 0);
		}
		texcoords_attribute = new OpenGLVertexAttribute(null, GL_FLOAT, 2, 0);
		elements_buffer = new OpenGLModelIndexBuffer();
		ambient = (short) var2;
		contrast = (short) var3;
		vertices_renderlist = new short[var7];
		faces_uid_cache = new long[var7];
		int var8 = 0;
		for (int var9 = 0; var9 < mesh.num_vertices; ++var9) {
			int var62 = shared_vertices[var9];
			shared_vertices[var9] = var8;
			var8 += var62;
		}
		shared_vertices[mesh.num_vertices] = var8;
		int num_textures = mesh.num_textures;
		int[] center_x = new int[num_textures];
		int[] center_y = new int[num_textures];
		int[] center_z = new int[num_textures];
		float[][] texture_matrices = new float[num_textures][];
		if (mesh.faces_texture != null) {
			int[] minxs = new int[num_textures];
			int[] maxxs = new int[num_textures];
			int[] minys = new int[num_textures];
			int[] maxys = new int[num_textures];
			int[] minzs = new int[num_textures];
			int[] maxzs = new int[num_textures];
			for (int texture_index = 0; texture_index < num_textures; ++texture_index) {
				minxs[texture_index] = +Integer.MAX_VALUE;
				maxxs[texture_index] = -Integer.MAX_VALUE;
				minys[texture_index] = +Integer.MAX_VALUE;
				maxys[texture_index] = -Integer.MAX_VALUE;
				minzs[texture_index] = +Integer.MAX_VALUE;
				maxzs[texture_index] = -Integer.MAX_VALUE;
			}
			for (int face_index = 0; face_index < num_rendering_faces; ++face_index) {
				int rendering_index = rendering_faces[face_index];
				if (mesh.faces_texture[rendering_index] != -1) {
					int mapping = mesh.faces_texture[rendering_index] & 255;
					for (int axis = 0; axis < 3; ++axis) {
						int face;
						if (axis == 0) {
							face = mesh.faces_a[rendering_index];
						} else if (axis == 1) {
							face = mesh.faces_b[rendering_index];
						} else {
							face = mesh.faces_c[rendering_index];
						}
						int vertex_x = mesh.vertices_x[face];
						int veretx_y = mesh.vertices_y[face];
						int vertex_z = mesh.vertices_z[face];
						if (vertex_x < minxs[mapping]) {
							minxs[mapping] = vertex_x;
						}
						if (vertex_x > maxxs[mapping]) {
							maxxs[mapping] = vertex_x;
						}
						if (veretx_y < minys[mapping]) {
							minys[mapping] = veretx_y;
						}
						if (veretx_y > maxys[mapping]) {
							maxys[mapping] = veretx_y;
						}
						if (vertex_z < minzs[mapping]) {
							minzs[mapping] = vertex_z;
						}
						if (vertex_z > maxzs[mapping]) {
							maxzs[mapping] = vertex_z;
						}
					}
				}
			}
			for (int texture_index = 0; texture_index < num_textures; ++texture_index) {
				byte mapping_type = mesh.textures_mapping[texture_index];
				if (mapping_type > 0) {
					center_x[texture_index] = (minxs[texture_index] + maxxs[texture_index]) / 2;
					center_y[texture_index] = (minys[texture_index] + maxys[texture_index]) / 2;
					center_z[texture_index] = (minzs[texture_index] + maxzs[texture_index]) / 2;
					float scale_x;
					float scale_y;
					float scale_z;
					if (mapping_type == 1) {
						short var80 = mesh.textures_scale_x[texture_index];
						if (var80 == 0) {
							scale_x = 1.0F;
							scale_z = 1.0F;
						} else if (var80 > 0) {
							scale_x = 1.0F;
							scale_z = var80 / 1024.0F;
						} else {
							scale_z = 1.0F;
							scale_x = -var80 / 1024.0F;
						}
						scale_y = 64.0F / (mesh.textures_scale_y[texture_index] & 0xffff);
					} else if (mapping_type == 2) {
						scale_x = 64.0F / (mesh.textures_scale_x[texture_index] & 0xffff);
						scale_y = 64.0F / (mesh.textures_scale_y[texture_index] & 0xffff);
						scale_z = 64.0F / (mesh.textures_scale_z[texture_index] & 0xffff);
					} else {
						scale_x = mesh.textures_scale_x[texture_index] / 1024.0F;
						scale_y = mesh.textures_scale_y[texture_index] / 1024.0F;
						scale_z = mesh.textures_scale_z[texture_index] / 1024.0F;
					}
					texture_matrices[texture_index] = calculate_texture_matrix(mesh.textures_mapping_p[texture_index], mesh.textures_mapping_m[texture_index], mesh.textures_mapping_n[texture_index], mesh.textures_rotation[texture_index] & 255, scale_x, scale_y, scale_z);
				}
			}
		}
		for (int face_index = 0; face_index < num_rendering_faces; ++face_index) {
			int face = rendering_faces[face_index];
			int colour = mesh.faces_colour[face] & 0xffff;
			short material;
			if (mesh.faces_material == null) {
				material = -1;
			} else {
				material = mesh.faces_material[face];
			}
			int texture;
			if (mesh.faces_texture == null) {
				texture = -1;
			} else {
				texture = mesh.faces_texture[face];
			}

			int alpha;
			if (mesh.faces_alpha == null) {
				alpha = 0;
			} else {
				alpha = mesh.faces_alpha[face] & 255;
			}
			float u1 = 0.0F;
			float v1 = 0.0F;
			float u2 = 0.0F;
			float v2 = 0.0F;
			float u3 = 0.0F;
			float v3 = 0.0F;
			byte uid_offset_b = 0;
			byte uid_offset_c = 0;
			int var27 = 0;
			if (material != -1) {
				if (texture == -1) {
					u1 = 0.0F;
					v1 = 1.0F;
					u2 = 1.0F;
					v2 = 1.0F;
					uid_offset_b = 1;
					u3 = 0.0F;
					v3 = 0.0F;
					uid_offset_c = 2;
				} else {
					texture &= 255;
					byte mapping_Type = mesh.textures_mapping[texture];
					if (mapping_Type == 0) {
						int a = mesh.faces_a[face];
						int b = mesh.faces_b[face];
						int c = mesh.faces_c[face];
						short p = mesh.textures_mapping_p[texture];
						short m = mesh.textures_mapping_m[texture];
						short n = mesh.textures_mapping_n[texture];
						float origin_x = mesh.vertices_x[p];
						float origin_y = mesh.vertices_y[p];
						float origin_z = mesh.vertices_z[p];
						float mx_distance = mesh.vertices_x[m] - origin_x;
						float my_distance = mesh.vertices_y[m] - origin_y;
						float mz_distance = mesh.vertices_z[m] - origin_z;
						float nx_distance = mesh.vertices_x[n] - origin_x;
						float ny_distance = mesh.vertices_y[n] - origin_y;
						float nz_distance = mesh.vertices_z[n] - origin_z;
						float ax_distance = mesh.vertices_x[a] - origin_x;
						float ay_distance = mesh.vertices_y[a] - origin_y;
						float az_distance = mesh.vertices_z[a] - origin_z;
						float bx_distance = mesh.vertices_x[b] - origin_x;
						float by_distance = mesh.vertices_y[b] - origin_y;
						float bz_distance = mesh.vertices_z[b] - origin_z;
						float cx_distance = mesh.vertices_x[c] - origin_x;
						float cy_distance = mesh.vertices_y[c] - origin_y;
						float cz_distance = mesh.vertices_z[c] - origin_z;
						float var53 = my_distance * nz_distance - mz_distance * ny_distance;
						float var54 = mz_distance * nx_distance - mx_distance * nz_distance;
						float var55 = mx_distance * ny_distance - my_distance * nx_distance;
						float var56 = ny_distance * var55 - nz_distance * var54;
						float var57 = nz_distance * var53 - nx_distance * var55;
						float var58 = nx_distance * var54 - ny_distance * var53;
						float var59 = 1.0F / (var56 * mx_distance + var57 * my_distance + var58 * mz_distance);
						u1 = (var56 * ax_distance + var57 * ay_distance + var58 * az_distance) * var59;
						u2 = (var56 * bx_distance + var57 * by_distance + var58 * bz_distance) * var59;
						u3 = (var56 * cx_distance + var57 * cy_distance + var58 * cz_distance) * var59;
						var56 = my_distance * var55 - mz_distance * var54;
						var57 = mz_distance * var53 - mx_distance * var55;
						var58 = mx_distance * var54 - my_distance * var53;
						var59 = 1.0F / (var56 * nx_distance + var57 * ny_distance + var58 * nz_distance);
						v1 = (var56 * ax_distance + var57 * ay_distance + var58 * az_distance) * var59;
						v2 = (var56 * bx_distance + var57 * by_distance + var58 * bz_distance) * var59;
						v3 = (var56 * cx_distance + var57 * cy_distance + var58 * cz_distance) * var59;
					} else {
						int var29 = mesh.faces_a[face];
						int var30 = mesh.faces_b[face];
						int var31 = mesh.faces_c[face];
						int var88 = center_x[texture];
						int var87 = center_y[texture];
						int var94 = center_z[texture];
						float[] texture_matrix = texture_matrices[texture];
						byte direction = mesh.textures_direction[texture];
						float speed = mesh.textures_speed[texture] / 256.0F;
						if (mapping_Type == 1) {
							float var38 = (mesh.textures_scale_z[texture] & 0xffff) / 1024.0F;
							map_cylinderical(mesh.vertices_x[var29], mesh.vertices_y[var29], mesh.vertices_z[var29], var88, var87, var94, texture_matrix, var38, direction, speed);
							u1 = mapped_cylinderical_u;
							v1 = mapped_cylinderical_v;
							map_cylinderical(mesh.vertices_x[var30], mesh.vertices_y[var30], mesh.vertices_z[var30], var88, var87, var94, texture_matrix, var38, direction, speed);
							u2 = mapped_cylinderical_u;
							v2 = mapped_cylinderical_v;
							map_cylinderical(mesh.vertices_x[var31], mesh.vertices_y[var31], mesh.vertices_z[var31], var88, var87, var94, texture_matrix, var38, direction, speed);
							u3 = mapped_cylinderical_u;
							v3 = mapped_cylinderical_v;
							float var39 = var38 / 2.0F;
							if ((direction & 1) == 0) {
								if (u2 - u1 > var39) {
									u2 -= var38;
									uid_offset_b = 1;
								} else if (u1 - u2 > var39) {
									u2 += var38;
									uid_offset_b = 2;
								}

								if (u3 - u1 > var39) {
									u3 -= var38;
									uid_offset_c = 1;
								} else if (u1 - u3 > var39) {
									u3 += var38;
									uid_offset_c = 2;
								}
							} else {
								if (v2 - v1 > var39) {
									v2 -= var38;
									uid_offset_b = 1;
								} else if (v1 - v2 > var39) {
									v2 += var38;
									uid_offset_b = 2;
								}

								if (v3 - v1 > var39) {
									v3 -= var38;
									uid_offset_c = 1;
								} else if (v1 - v3 > var39) {
									v3 += var38;
									uid_offset_c = 2;
								}
							}
						} else if (mapping_Type == 2) {
							float var38 = mesh.textures_utrans[texture] / 256.0F;
							float var39 = mesh.textures_vtrans[texture] / 256.0F;
							int var97 = mesh.vertices_x[var30] - mesh.vertices_x[var29];
							int var96 = mesh.vertices_y[var30] - mesh.vertices_y[var29];
							int var101 = mesh.vertices_z[var30] - mesh.vertices_z[var29];
							int var102 = mesh.vertices_x[var31] - mesh.vertices_x[var29];
							int var103 = mesh.vertices_y[var31] - mesh.vertices_y[var29];
							int var98 = mesh.vertices_z[var31] - mesh.vertices_z[var29];
							int var99 = var96 * var98 - var103 * var101;
							int var100 = var101 * var102 - var98 * var97;
							int var104 = var97 * var103 - var102 * var96;
							float var49 = 64.0F / (mesh.textures_scale_x[texture] & 0xffff);
							float var50 = 64.0F / (mesh.textures_scale_y[texture] & 0xffff);
							float var51 = 64.0F / (mesh.textures_scale_z[texture] & 0xffff);
							float var52 = (var99 * texture_matrix[0] + var100 * texture_matrix[1] + var104 * texture_matrix[2]) / var49;
							float var53 = (var99 * texture_matrix[3] + var100 * texture_matrix[4] + var104 * texture_matrix[5]) / var50;
							float var54 = (var99 * texture_matrix[6] + var100 * texture_matrix[7] + var104 * texture_matrix[8]) / var51;
							var27 = get_cube_face(var52, var53, var54);
							map_rectangular(mesh.vertices_x[var29], mesh.vertices_y[var29], mesh.vertices_z[var29], var88, var87, var94, var27, texture_matrix, direction, speed, var38, var39);
							u1 = rectangular_u;
							v1 = rectangular_v;
							map_rectangular(mesh.vertices_x[var30], mesh.vertices_y[var30], mesh.vertices_z[var30], var88, var87, var94, var27, texture_matrix, direction, speed, var38, var39);
							u2 = rectangular_u;
							v2 = rectangular_v;
							map_rectangular(mesh.vertices_x[var31], mesh.vertices_y[var31], mesh.vertices_z[var31], var88, var87, var94, var27, texture_matrix, direction, speed, var38, var39);
							u3 = rectangular_u;
							v3 = rectangular_v;
						} else if (mapping_Type == 3) {
							map_spherical(mesh.vertices_x[var29], mesh.vertices_y[var29], mesh.vertices_z[var29], var88, var87, var94, texture_matrix, direction, speed);
							u1 = spherical_u;
							v1 = spherical_v;
							map_spherical(mesh.vertices_x[var30], mesh.vertices_y[var30], mesh.vertices_z[var30], var88, var87, var94, texture_matrix, direction, speed);
							u2 = spherical_u;
							v2 = spherical_v;
							map_spherical(mesh.vertices_x[var31], mesh.vertices_y[var31], mesh.vertices_z[var31], var88, var87, var94, texture_matrix, direction, speed);
							u3 = spherical_u;
							v3 = spherical_v;
							if ((direction & 1) == 0) {
								if (u2 - u1 > 0.5F) {
									--u2;
									uid_offset_b = 1;
								} else if (u1 - u2 > 0.5F) {
									++u2;
									uid_offset_b = 2;
								}

								if (u3 - u1 > 0.5F) {
									--u3;
									uid_offset_c = 1;
								} else if (u1 - u3 > 0.5F) {
									++u3;
									uid_offset_c = 2;
								}
							} else {
								if (v2 - v1 > 0.5F) {
									--v2;
									uid_offset_b = 1;
								} else if (v1 - v2 > 0.5F) {
									++v2;
									uid_offset_b = 2;
								}

								if (v3 - v1 > 0.5F) {
									--v3;
									uid_offset_c = 1;
								} else if (v1 - v3 > 0.5F) {
									++v3;
									uid_offset_c = 2;
								}
							}
						}
					}
				}
			}

			mesh.calculate_normals();
			int face_type;
			if (mesh.faces_type == null) {
				face_type = 0;
			} else {
				face_type = mesh.faces_type[face];
			}
			if (face_type == 0) {
				long uid = (texture << 2) + ((long) (var27 << 24) + (colour << 8) + alpha << 32);
				int index_a = mesh.faces_a[face];
				VertexNormal normal_a = mesh.vertices_normals_1[index_a];
				faces_a[face_index] = add_vertex(mesh, index_a, uid, normal_a.x, normal_a.y, normal_a.z, normal_a.magnitude, u1, v1);
				int index_b = mesh.faces_b[face];
				VertexNormal normal_b = mesh.vertices_normals_1[index_b];
				faces_b[face_index] = add_vertex(mesh, index_b, uid + uid_offset_b, normal_b.x, normal_b.y, normal_b.z, normal_b.magnitude, u2, v2);
				int index_c = mesh.faces_c[face];
				VertexNormal normal_c = mesh.vertices_normals_1[index_c];
				faces_c[face_index] = add_vertex(mesh, index_c, uid + uid_offset_c, normal_c.x, normal_c.y, normal_c.z, normal_c.magnitude, u3, v3);
			} else if (face_type == 1) {
				FaceNormal normal = mesh.faces_normal[face];
				long var84 = (texture << 2) + (normal.x > 0 ? 1024 : 2048) + (normal.y + 256 << 12) + (normal.z + 256 << 22) + ((long) (var27 << 24) + (colour << 8) + alpha << 32);
				faces_a[face_index] = add_vertex(mesh, mesh.faces_a[face], var84, normal.x, normal.y, normal.z, 0, u1, v1);
				faces_b[face_index] = add_vertex(mesh, mesh.faces_b[face], var84 + uid_offset_b, normal.x, normal.y, normal.z, 0, u2, v2);
				faces_c[face_index] = add_vertex(mesh, mesh.faces_c[face], var84 + uid_offset_c, normal.x, normal.y, normal.z, 0, u3, v3);
			}

			if (mesh.faces_material != null) {
				faces_materials[face_index] = mesh.faces_material[face];
			} else {
				faces_materials[face_index] = -1;
			}
			if (faces_label != null) {
				faces_label[face_index] = (byte) mesh.faces_label[face];
			}
			faces_colour[face_index] = mesh.faces_colour[face];
			if (mesh.faces_alpha != null) {
				faces_alpha[face_index] = mesh.faces_alpha[face];
			}
			if (mesh.faces_submeshes != null) {
				faces_submeshes[face_index] = mesh.faces_submeshes[face];
			}
		}
		int num_lists = 0;
		short last_materialid = -10000;
		for (int var67 = 0; var67 < num_rendering_faces; ++var67) {
			short materialid = faces_materials[var67];
			if (materialid != last_materialid) {
				last_materialid = materialid;
				num_lists++;
			}
		}

		rendering_lists = new int[num_lists + 1];
		num_lists = 0;
		last_materialid = -10000;
		for (int var67 = 0; var67 < num_rendering_faces; ++var67) {
			short var71 = faces_materials[var67];
			if (var71 != last_materialid) {
				rendering_lists[num_lists++] = var67;
				last_materialid = var71;
			}
		}

		rendering_lists[num_lists] = num_rendering_faces;
		faces_uid_cache = null;
		normals_x = copy(normals_x, num_rendering_vertices);
		normals_y = copy(normals_y, num_rendering_vertices);
		normals_z = copy(normals_z, num_rendering_vertices);
		normals_m = copy(normals_m, num_rendering_vertices);
		vertices_u = copy(vertices_u, num_rendering_vertices);
		vertices_v = copy(vertices_v, num_rendering_vertices);
	}

	public void generate_labels_lookup_table() {
		int[] var1;
		int var2;
		int var3;
		int var4;
		if (vertices_label != null) {
			var1 = new int[256];
			var2 = 0;
			for (var3 = 0; var3 < num_vertices; ++var3) {
				var4 = vertices_label[var3] & 255;
				++var1[var4];
				if (var4 > var2) {
					var2 = var4;
				}
			}
			vertices_by_label = new int[var2 + 1][];
			for (var3 = 0; var3 <= var2; ++var3) {
				vertices_by_label[var3] = new int[var1[var3]];
				var1[var3] = 0;
			}
			for (var3 = 0; var3 < num_vertices; vertices_by_label[var4][var1[var4]++] = var3++) {
				var4 = vertices_label[var3] & 255;
			}
			vertices_label = null;
		}
		if (faces_label != null) {
			var1 = new int[256];
			var2 = 0;

			for (var3 = 0; var3 < num_rendering_faces; ++var3) {
				var4 = faces_label[var3] & 255;
				++var1[var4];
				if (var4 > var2) {
					var2 = var4;
				}
			}

			faces_by_label = new int[var2 + 1][];

			for (var3 = 0; var3 <= var2; ++var3) {
				faces_by_label[var3] = new int[var1[var3]];
				var1[var3] = 0;
			}

			for (var3 = 0; var3 < num_rendering_faces; faces_by_label[var4][var1[var4]++] = var3++) {
				var4 = faces_label[var3] & 255;
			}

			faces_label = null;
		}

	}

	public short add_vertex(Mesh mesh, int source_index, long uid, int normal_x, int normal_y, int normal_z, int normal_m, float u, float v) {
		int var11 = shared_vertices[source_index];
		int var12 = shared_vertices[source_index + 1];
		int vertex_index = 0;
		for (int var14 = var11; var14 < var12; ++var14) {
			short var15 = vertices_renderlist[var14];
			if (var15 == 0) {
				vertex_index = var14;
				break;
			}
			if (faces_uid_cache[var14] == uid) {
				return (short) (var15 - 1);
			}
		}
		faces_uid_cache[vertex_index] = uid;
		vertices_renderlist[vertex_index] = (short) (num_rendering_vertices + 1);
		normals_x[num_rendering_vertices] = (short) normal_x;
		normals_y[num_rendering_vertices] = (short) normal_y;
		normals_z[num_rendering_vertices] = (short) normal_z;
		normals_m[num_rendering_vertices] = (short) normal_m;
		vertices_u[num_rendering_vertices] = u;
		vertices_v[num_rendering_vertices] = v;
		return (short) num_rendering_vertices++;
	}

	public void method1919(int var1, int value, OpenGLModel var3, int[][] var4, int[][] var5, int var6, int var7, int var8) {
		if (!var3.bounding_box.is_clean) {
			var3.update_boundingbox();
		}
		int var9 = var6 + var3.bounding_box.minx;
		int var10 = var6 + var3.bounding_box.maxx;
		int var11 = var8 + var3.bounding_box.minz;
		int var12 = var8 + var3.bounding_box.maxz;
		if (var1 != 1 && var1 != 2 && var1 != 3 && var1 != 5 || var9 >= 0 && var10 + 128 >> 7 < var4.length && var11 >= 0 && var12 + 128 >> 7 < var4[0].length) {
			if (var1 != 4 && var1 != 5) {
				var9 >>= 7;
				var10 = var10 + 127 >> 7;
				var11 >>= 7;
				var12 = var12 + 127 >> 7;
				if (var4[var9][var11] == var7 && var4[var10][var11] == var7 && var4[var9][var12] == var7 && var4[var10][var12] == var7) {
					return;
				}
			} else {
				if (var5 == null) {
					return;
				}

				if (var9 < 0 || var10 + 128 >> 7 >= var5.length || var11 < 0 || var12 + 128 >> 7 >= var5[0].length) {
					return;
				}
			}
			if (var1 == 1) {
				for (int var13 = 0; var13 < num_vertices; ++var13) {
					int var14 = vertices_x[var13] + var6;
					int var15 = vertices_z[var13] + var8;
					int var16 = var14 & 127;
					int var17 = var15 & 127;
					int var18 = var14 >> 7;
					int var19 = var15 >> 7;
					int var20 = var4[var18][var19] * (128 - var16) + var4[var18 + 1][var19] * var16 >> 7;
					int var21 = var4[var18][var19 + 1] * (128 - var16) + var4[var18 + 1][var19 + 1] * var16 >> 7;
					int var22 = var20 * (128 - var17) + var21 * var17 >> 7;
					vertices_y[var13] = vertices_y[var13] + var22 - var7;
				}
			} else {
				if (var1 == 2) {
					short var26 = var3.bounding_box.miny;
					for (int var14 = 0; var14 < num_vertices; ++var14) {
						int var15 = (vertices_y[var14] << 16) / var26;
						if (var15 < value) {
							int var16 = vertices_x[var14] + var6;
							int var17 = vertices_z[var14] + var8;
							int var18 = var16 & 127;
							int var19 = var17 & 127;
							int var20 = var16 >> 7;
							int var21 = var17 >> 7;
							int var22 = var4[var20][var21] * (128 - var18) + var4[var20 + 1][var21] * var18 >> 7;
							int var23 = var4[var20][var21 + 1] * (128 - var18) + var4[var20 + 1][var21 + 1] * var18 >> 7;
							int var24 = var22 * (128 - var19) + var23 * var19 >> 7;
							vertices_y[var14] += (var24 - var7) * (value - var15) / value;
						}
					}
				} else if (var1 == 3) {
					int width = (value & 0xff) * 4;
					int length = (value >> 8 & 0xff) * 4;
					int xan = (value >> 16 & 0xff) << 6;
					int zan = (value >> 24 & 0xff) << 6;
					hillchange(var4, var6, var7, var8, width, length, xan, zan);
				} else if (var1 == 4) {
					int var13 = var3.bounding_box.maxy - var3.bounding_box.miny;

					for (int var14 = 0; var14 < num_vertices; ++var14) {
						int var15 = vertices_x[var14] + var6;
						int var16 = vertices_z[var14] + var8;
						int var17 = var15 & 127;
						int var18 = var16 & 127;
						int var19 = var15 >> 7;
						int var20 = var16 >> 7;
						int var21 = var5[var19][var20] * (128 - var17) + var5[var19 + 1][var20] * var17 >> 7;
						int var22 = var5[var19][var20 + 1] * (128 - var17) + var5[var19 + 1][var20 + 1] * var17 >> 7;
						int var23 = var21 * (128 - var18) + var22 * var18 >> 7;
						vertices_y[var14] = vertices_y[var14] + var23 - var7 + var13;
					}
				} else if (var1 == 5) {
					int var13 = var3.bounding_box.maxy - var3.bounding_box.miny;
					for (int var14 = 0; var14 < num_vertices; ++var14) {
						int var15 = vertices_x[var14] + var6;
						int var16 = vertices_z[var14] + var8;
						int var17 = var15 & 127;
						int var18 = var16 & 127;
						int var19 = var15 >> 7;
						int var20 = var16 >> 7;
						int var21 = var4[var19][var20] * (128 - var17) + var4[var19 + 1][var20] * var17 >> 7;
						int var22 = var4[var19][var20 + 1] * (128 - var17) + var4[var19 + 1][var20 + 1] * var17 >> 7;
						int var23 = var21 * (128 - var18) + var22 * var18 >> 7;
						var21 = var5[var19][var20] * (128 - var17) + var5[var19 + 1][var20] * var17 >> 7;
						var22 = var5[var19][var20 + 1] * (128 - var17) + var5[var19 + 1][var20 + 1] * var17 >> 7;
						int var24 = var21 * (128 - var18) + var22 * var18 >> 7;
						int var25 = var23 - var24;
						vertices_y[var14] = ((vertices_y[var14] << 8) / var13 * var25 >> 8) - (var7 - var23);
					}
				}
			}
			positions_attribute.is_clean = false;
			bounding_box.is_clean = false;
		}
	}

	public SoftwarePaletteSprite create_shadow_sprite(SoftwarePaletteSprite var1) {
		if (num_rendering_vertices == 0) {
			return null;
		} else {
			if (!bounding_box.is_clean) {
				update_boundingbox();
			}
			int var2;
			int var3;
			if (OpenGLEnvironment.shadows_scale_x > 0) {
				var2 = bounding_box.minx - (bounding_box.maxy * OpenGLEnvironment.shadows_scale_x >> 8) >> 3;
				var3 = bounding_box.maxx - (bounding_box.miny * OpenGLEnvironment.shadows_scale_x >> 8) >> 3;
			} else {
				var2 = bounding_box.minx - (bounding_box.miny * OpenGLEnvironment.shadows_scale_x >> 8) >> 3;
				var3 = bounding_box.maxx - (bounding_box.maxy * OpenGLEnvironment.shadows_scale_x >> 8) >> 3;
			}

			int var4;
			int var5;
			if (OpenGLEnvironment.shadows_scale_z > 0) {
				var4 = bounding_box.minz - (bounding_box.maxy * OpenGLEnvironment.shadows_scale_z >> 8) >> 3;
				var5 = bounding_box.maxz - (bounding_box.miny * OpenGLEnvironment.shadows_scale_z >> 8) >> 3;
			} else {
				var4 = bounding_box.minz - (bounding_box.miny * OpenGLEnvironment.shadows_scale_z >> 8) >> 3;
				var5 = bounding_box.maxz - (bounding_box.maxy * OpenGLEnvironment.shadows_scale_z >> 8) >> 3;
			}
			int var6 = var3 - var2 + 1;
			int var7 = var5 - var4 + 1;
			SoftwarePaletteSprite shadowmap;
			if (var1 != null && var1.pixels.length >= var6 * var7) {
				shadowmap = var1;
				var1.trim_width = var1.width = var6;
				var1.trim_height = var1.height = var7;
				var1.clear();
			} else {
				shadowmap = new SoftwarePaletteSprite(var6, var7, 0);
			}

			shadowmap.offset_x = var2;
			shadowmap.offset_y = var4;
			if (shadow_vertices_x.length < num_rendering_vertices) {
				shadow_vertices_x = new int[num_rendering_vertices];
				shadow_vertices_y = new int[num_rendering_vertices];
			}
			int var9 = 0;

			int var13;
			int var14;
			int var15;
			while (var9 < num_vertices) {
				int var10 = (vertices_x[var9] - (vertices_y[var9] * OpenGLEnvironment.shadows_scale_x >> 8) >> 3) - var2;
				int var11 = (vertices_z[var9] - (vertices_y[var9] * OpenGLEnvironment.shadows_scale_z >> 8) >> 3) - var4;
				int var12 = shared_vertices[var9];
				var13 = shared_vertices[var9 + 1];
				var14 = var12;

				while (true) {
					if (var14 < var13) {
						var15 = vertices_renderlist[var14] - 1;
						if (var15 != -1) {
							shadow_vertices_x[var15] = var10;
							shadow_vertices_y[var15] = var11;
							++var14;
							continue;
						}
					}

					++var9;
					break;
				}
			}

			for (var9 = 0; var9 < num_rendering_faces; ++var9) {
				if (faces_alpha[var9] <= 128) {
					short var20 = faces_a[var9];
					short var19 = faces_b[var9];
					short var21 = faces_c[var9];
					var13 = shadow_vertices_x[var20];
					var14 = shadow_vertices_x[var19];
					var15 = shadow_vertices_x[var21];
					int var16 = shadow_vertices_y[var20];
					int var17 = shadow_vertices_y[var19];
					int var18 = shadow_vertices_y[var21];
					if ((var13 - var14) * (var17 - var18) - (var17 - var16) * (var15 - var14) > 0) {
						OpenGLModel.rasterize_shadowmap(shadowmap.pixels, var16, var17, var18, var13, var14, var15, var6);
					}
				}
			}
			return shadowmap;
		}
	}

	@Override
	public void draw(int rotationX, int rotationY, int rotationZ, int rotationX1, int translateX, int translateY, int translateZ) {
		if (num_rendering_vertices != 0) {
			glPushMatrix();
			if (rotationX1 != 0) {
				glRotatef(rotationX1 * 0.17578125F, 1.0F, 0.0F, 0.0F);
			}
			glTranslatef(translateX, translateY, translateZ);
			if (rotationY != 0) {
				glRotatef(rotationY * 0.17578125F, 0.0F, 1.0F, 0.0F);
			}

			if (rotationX != 0) {
				glRotatef(rotationX * 0.17578125F, 1.0F, 0.0F, 0.0F);
			}

			if (rotationZ != 0) {
				glRotatef(-rotationZ * 0.17578125F, 0.0F, 0.0F, 1.0F);
			}
			this.draw();
			glPopMatrix();
		}
	}

	@Override
	public void draw2(int rotationY, int var2, int var3, int var4, int var5, int translateX, int translateY, int translateZ, long key, int var11) {
		if (num_rendering_vertices != 0) {
			if (!bounding_box.is_clean) {
				update_boundingbox();
			}
			short var13 = bounding_box.size2d;
			short var14 = bounding_box.miny;
			short var15 = bounding_box.maxy;
			int var16 = translateZ * var5 - translateX * var4 >> 16;
			int var17 = translateY * var2 + var16 * var3 >> 16;
			int depth = var17 + (var13 * var3 + var15 * var2 >> 16);
			if (depth > 50) {
				int var19 = var17 + (-var13 * var3 + var14 * var2 >> 16);
				if (var19 < 3584) {
					int var20 = translateZ * var4 + translateX * var5 >> 16;
					int var21 = var20 + var13 << 9;
					if (var21 / depth > Viewport.screen_left) {
						int var22 = var20 - var13 << 9;
						if (var22 / depth < Viewport.screen_right) {
							int var23 = translateY * var3 - var16 * var2 >> 16;
							int var24 = var23 + (var13 * var2 + var15 * var3 >> 16) << 9;
							if (var24 / depth > Viewport.screen_top) {
								int var25 = var23 + (-var13 * var2 + var14 * var3 >> 16) << 9;
								if (var25 / depth < Viewport.screen_bottom) {
									int var26 = 0;
									int var27 = 0;
									if (rotationY != 0) {
										var26 = Rasterizer.SINE[rotationY];
										var27 = Rasterizer.COSINE[rotationY];
									}

									if (key > 0L && Scene.object_selected && var19 > 0) {
										int var28;
										int var30;
										if (var20 > 0) {
											var28 = var22 / depth;
											var30 = var21 / var19;
										} else {
											var28 = var22 / var19;
											var30 = var21 / depth;
										}

										int var29;
										int var31;
										if (var23 > 0) {
											var29 = var25 / depth;
											var31 = var24 / var19;
										} else {
											var29 = var25 / var19;
											var31 = var24 / depth;
										}

										if (Viewport.anInt3878 >= var28 && Viewport.anInt3878 <= var30 && Viewport.anInt1973 >= var29 && Viewport.anInt1973 <= var31) {
											var28 = 999999;
											var30 = -999999;
											var29 = 999999;
											var31 = -999999;
											short var32 = bounding_box.minx;
											short var33 = bounding_box.maxx;
											short var34 = bounding_box.minz;
											short var35 = bounding_box.maxz;
											int[] var36 = new int[] { var32, var33, var32, var33, var32, var33, var32, var33 };
											int[] var37 = new int[] { var34, var34, var35, var35, var34, var34, var35, var35 };
											int[] var38 = new int[] { var14, var14, var14, var14, var15, var15, var15, var15 };

											int var39;
											int tz;
											int var43;
											int var40;
											int var41;
											int screenX;
											int screenY;
											for (var39 = 0; var39 < 8; ++var39) {
												var40 = var36[var39];
												var41 = var38[var39];
												tz = var37[var39];
												if (rotationY != 0) {
													var43 = tz * var26 + var40 * var27 >> 16;
													tz = tz * var27 - var40 * var26 >> 16;
													var40 = var43;
												}

												var40 += translateX;
												var41 += translateY;
												tz += translateZ;
												var43 = tz * var4 + var40 * var5 >> 16;
												tz = tz * var5 - var40 * var4 >> 16;
												var40 = var43;
												var43 = var41 * var3 - tz * var2 >> 16;
												tz = var41 * var2 + tz * var3 >> 16;
												if (tz > 0) {
													screenX = (var40 << 9) / tz;
													screenY = (var43 << 9) / tz;
													if (screenX < var28) {
														var28 = screenX;
													}

													if (screenX > var30) {
														var30 = screenX;
													}

													if (screenY < var29) {
														var29 = screenY;
													}

													if (screenY > var31) {
														var31 = screenY;
													}
												}
											}

											if (Viewport.anInt3878 >= var28 && Viewport.anInt3878 <= var30 && Viewport.anInt1973 >= var29 && Viewport.anInt1973 <= var31) {
												if (renders_in_one_tile) {
													Scene.rendered_models_uid[Scene.num_rendered++] = key;
												} else {
													if (shadow_vertices_x.length < num_rendering_vertices) {
														shadow_vertices_x = new int[num_rendering_vertices];
														shadow_vertices_y = new int[num_rendering_vertices];
													}

													var39 = 0;

													label118: while (true) {
														if (var39 >= num_vertices) {
															var39 = 0;

															while (true) {
																if (var39 >= num_rendering_faces) {
																	break label118;
																}

																short var53 = faces_a[var39];
																short var52 = faces_b[var39];
																short var51 = faces_c[var39];
																if (is_within_bounds(Viewport.anInt3878, Viewport.anInt1973, shadow_vertices_y[var53], shadow_vertices_y[var52], shadow_vertices_y[var51], shadow_vertices_x[var53], shadow_vertices_x[var52], shadow_vertices_x[var51])) {
																	Scene.rendered_models_uid[Scene.num_rendered++] = key;
																	break label118;
																}

																++var39;
															}
														}

														var40 = vertices_x[var39];
														var41 = vertices_y[var39];
														tz = vertices_z[var39];
														if (rotationY != 0) {
															var43 = tz * var26 + var40 * var27 >> 16;
															tz = tz * var27 - var40 * var26 >> 16;
															var40 = var43;
														}

														var40 += translateX;
														var41 += translateY;
														tz += translateZ;
														var43 = tz * var4 + var40 * var5 >> 16;
														tz = tz * var5 - var40 * var4 >> 16;
														var40 = var43;
														var43 = var41 * var3 - tz * var2 >> 16;
														tz = var41 * var2 + tz * var3 >> 16;
														if (tz < 50) {
															break;
														}

														screenX = (var40 << 9) / tz;
														screenY = (var43 << 9) / tz;
														int var46 = shared_vertices[var39];
														int var47 = shared_vertices[var39 + 1];

														for (int var48 = var46; var48 < var47; ++var48) {
															int var49 = vertices_renderlist[var48] - 1;
															if (var49 == -1) {
																break;
															}

															shadow_vertices_x[var49] = screenX;
															shadow_vertices_y[var49] = screenY;
														}

														++var39;
													}
												}
											}
										}
									}

									glPushMatrix();
									glTranslatef(translateX, translateY, translateZ);
									glRotatef(rotationY * 0.17578125F, 0.0F, 1.0F, 0.0F);
									draw();
									glRotatef(-rotationY * 0.17578125F, 0.0F, 1.0F, 0.0F);
									glTranslatef(-translateX, -translateY, -translateZ);
									glPopMatrix();
								}
							}
						}
					}
				}
			}
		}
	}

	public void draw() {
		if (num_rendering_faces != 0) {
			if (vertices_buffer_state != 0) {
				create_vertices_buffer(true, !positions_attribute.is_clean && (vertices_buffer_state & 1) != 0, !colours_attribute.is_clean && (vertices_buffer_state & 2) != 0, normals_attribute != null && !normals_attribute.is_clean && (vertices_buffer_state & 4) != 0, false);
			}
			create_vertices_buffer(false, !positions_attribute.is_clean, !colours_attribute.is_clean, normals_attribute != null && !normals_attribute.is_clean, !texcoords_attribute.is_clean);
			if (!elements_buffer.is_clean) {
				create_elements_buffer();
			}
			if (functionmask != 0) {
				if ((functionmask & 1) != 0) {
					vertices_x = null;
					vertices_y = null;
					vertices_z = null;
					vertices_renderlist = null;
					shared_vertices = null;
				}
				if ((functionmask & 2) != 0) {
					faces_colour = null;
					faces_alpha = null;
				}
				if ((functionmask & 4) != 0) {
					normals_x = null;
					normals_y = null;
					normals_z = null;
					normals_m = null;
				}
				if ((functionmask & 8) != 0) {
					vertices_u = null;
					vertices_v = null;
				}
				if ((functionmask & 16) != 0) {
					faces_a = null;
					faces_b = null;
					faces_c = null;
				}
				functionmask = 0;
			}
			if (elements_buffer != null) {
				GLState.set_lights_enabled(normals_attribute.buffer != null);
				GLManager.load_vertices_array(positions_attribute, normals_attribute, colours_attribute, texcoords_attribute);
				int var3 = rendering_lists.length - 1;
				for (int var4 = 0; var4 < var3; ++var4) {
					int drawListStart = rendering_lists[var4];
					int drawListEnd = rendering_lists[var4 + 1];
					short var7 = faces_materials[drawListStart];
					if (var7 == -1) {
						GLState.load_texture(-1);
					} else {
						GLState.load_texture(var7 & 0xffff);
					}
					GLManager.draw_index_buffer(elements_buffer.buffer, OpenGL.GL_TRIANGLES, drawListStart * 3, (drawListEnd - drawListStart) * 3);

				}
			}
		}
	}

	private static boolean is_within_bounds(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
		return var2 < var3 && var2 < var4 && var2 < var5 ? false : var2 > var3 && var2 > var4 && var2 > var5 ? false : var1 < var6 && var1 < var7 && var1 < var8 ? false : var1 <= var6 || var1 <= var7 || var1 <= var8;
	}

	public void create_vertices_buffer(boolean check_states, boolean positions_updated, boolean colors_updated, boolean normals_updated, boolean texcoords_updated) {
		byte vertex_size = 0;
		byte positions_offset = 0;
		byte colors_offset = 0;
		byte normals_offset = 0;
		byte texcoords_offset = 0;
		if (positions_updated) {
			positions_offset = vertex_size;
			vertex_size += 12;
		}
		if (colors_updated) {
			colors_offset = vertex_size;
			vertex_size += 4;
		}
		if (normals_updated) {
			normals_offset = vertex_size;
			vertex_size += 12;
		}
		if (texcoords_updated) {
			texcoords_offset = vertex_size;
			vertex_size += 8;
		}
		if (vertex_size != 0) {
			if (buffer.byteBuffer.length >= num_rendering_vertices * vertex_size) {
				buffer.index = 0;
			} else {
				buffer = new Packet((num_rendering_vertices + 100) * vertex_size);
			}
			if (positions_updated) {
				if (GLManager.big_endianess) {
					for (int var7 = 0; var7 < num_vertices; ++var7) {
						int var11 = shared_vertices[var7];
						int var12 = shared_vertices[var7 + 1];
						for (int var13 = var11; var13 < var12; ++var13) {
							int start = vertices_renderlist[var13] - 1;
							if (start == -1) {
								break;
							}
							buffer.index = start * vertex_size;
							buffer.pf(vertices_x[var7]);
							buffer.pf(vertices_y[var7]);
							buffer.pf(vertices_z[var7]);
						}
					}
				} else {
					for (int var7 = 0; var7 < num_vertices; ++var7) {
						int var11 = shared_vertices[var7];
						int var12 = shared_vertices[var7 + 1];
						for (int var13 = var11; var13 < var12; ++var13) {
							int start = vertices_renderlist[var13] - 1;
							if (start == -1) {
								break;
							}
							buffer.index = start * vertex_size;
							buffer.ipf(vertices_x[var7]);
							buffer.ipf(vertices_y[var7]);
							buffer.ipf(vertices_z[var7]);
						}
					}
				}
			}

			if (colors_updated) {
				if (!ClientOptions.clientoption_highdetails_lighting) {
					int var7 = (int) OpenGLEnvironment.sun_position_buffer[0];
					int var8 = (int) OpenGLEnvironment.sun_position_buffer[1];
					int var9 = (int) OpenGLEnvironment.sun_position_buffer[2];
					int var10 = (int) Math.sqrt(var7 * var7 + var8 * var8 + var9 * var9);
					int var11 = (int) (ambient * 1.3F);
					int var12 = contrast * var10 >> 8;
					for (int var13 = 0; var13 < num_rendering_faces; ++var13) {
						short var33 = faces_a[var13];
						short var15 = normals_m[var33];
						int var16;
						if (var15 < 0) {
							var16 = -1 - var15;
						} else {
							if (var15 != 0) {
								var16 = var11 + (var7 * normals_x[var33] + var8 * normals_y[var33] + var9 * normals_z[var33]) / (var12 * var15);
							} else {
								var16 = var11 + (var7 * normals_x[var33] + var8 * normals_y[var33] + var9 * normals_z[var33]) / (var12 + var12 / 2);
							}

							if (var16 < 0) {
								var16 = 0;
							} else if (var16 > 16384) {
								var16 = 16384;
							}

							normals_m[var33] = (short) (-1 - var16);
						}
						short var17 = faces_b[var13];
						short var18 = normals_m[var17];
						int var19;
						if (var18 < 0) {
							var19 = -1 - var18;
						} else {
							if (var18 != 0) {
								var19 = var11 + (var7 * normals_x[var17] + var8 * normals_y[var17] + var9 * normals_z[var17]) / (var12 * var18);
							} else {
								var19 = var11 + (var7 * normals_x[var17] + var8 * normals_y[var17] + var9 * normals_z[var17]) / (var12 + var12 / 2);
							}

							if (var19 < 0) {
								var19 = 0;
							} else if (var19 > 16384) {
								var19 = 16384;
							}

							normals_m[var17] = (short) (-1 - var19);
						}

						short var20 = faces_c[var13];
						short var21 = normals_m[var20];
						int var22;
						if (var21 < 0) {
							var22 = -1 - var21;
						} else {
							if (var21 != 0) {
								var22 = var11 + (var7 * normals_x[var20] + var8 * normals_y[var20] + var9 * normals_z[var20]) / (var12 * var21);
							} else {
								var22 = var11 + (var7 * normals_x[var20] + var8 * normals_y[var20] + var9 * normals_z[var20]) / (var12 + var12 / 2);
							}

							if (var22 < 0) {
								var22 = 0;
							} else if (var22 > 16384) {
								var22 = 16384;
							}

							normals_m[var20] = (short) (-1 - var22);
						}

						int var23 = get_face_colour(faces_colour[var13], faces_materials[var13], var16, faces_alpha[var13]);
						int var24 = get_face_colour(faces_colour[var13], faces_materials[var13], var19, faces_alpha[var13]);
						int var25 = get_face_colour(faces_colour[var13], faces_materials[var13], var22, faces_alpha[var13]);
						buffer.index = colors_offset + var33 * vertex_size;
						buffer.p4(var23);
						buffer.index = colors_offset + var17 * vertex_size;
						buffer.p4(var24);
						buffer.index = colors_offset + var20 * vertex_size;
						buffer.p4(var25);
					}

					normals_x = null;
					normals_y = null;
					normals_z = null;
				} else {
					for (int var7 = 0; var7 < num_rendering_faces; ++var7) {
						int var8 = get_face_colour(faces_colour[var7], faces_materials[var7], ambient, faces_alpha[var7]);
						buffer.index = colors_offset + faces_a[var7] * vertex_size;
						buffer.p4(var8);
						buffer.index = colors_offset + faces_b[var7] * vertex_size;
						buffer.p4(var8);
						buffer.index = colors_offset + faces_c[var7] * vertex_size;
						buffer.p4(var8);
					}
				}
			}

			if (normals_updated) {
				float var27 = 3.0F / contrast;
				float var26 = 3.0F / (contrast + contrast / 2);
				buffer.index = normals_offset;
				short var30;
				float var32;
				if (GLManager.big_endianess) {
					for (int var9 = 0; var9 < num_rendering_vertices; ++var9) {
						var30 = normals_m[var9];
						if (var30 == 0) {
							buffer.pf(normals_x[var9] * var26);
							buffer.pf(normals_y[var9] * var26);
							buffer.pf(normals_z[var9] * var26);
						} else {
							var32 = var27 / var30;
							buffer.pf(normals_x[var9] * var32);
							buffer.pf(normals_y[var9] * var32);
							buffer.pf(normals_z[var9] * var32);
						}

						buffer.index += vertex_size - 12;
					}
				} else {
					for (int var9 = 0; var9 < num_rendering_vertices; ++var9) {
						var30 = normals_m[var9];
						if (var30 == 0) {
							buffer.ipf(normals_x[var9] * var26);
							buffer.ipf(normals_y[var9] * var26);
							buffer.ipf(normals_z[var9] * var26);
						} else {
							var32 = var27 / var30;
							buffer.ipf(normals_x[var9] * var32);
							buffer.ipf(normals_y[var9] * var32);
							buffer.ipf(normals_z[var9] * var32);
						}

						buffer.index += vertex_size - 12;
					}
				}
			}

			if (texcoords_updated) {
				buffer.index = texcoords_offset;
				if (GLManager.big_endianess) {
					for (int var7 = 0; var7 < num_rendering_vertices; ++var7) {
						buffer.pf(vertices_u[var7]);
						buffer.pf(vertices_v[var7]);
						buffer.index += vertex_size - 8;
					}
				} else {
					for (int var7 = 0; var7 < num_rendering_vertices; ++var7) {
						buffer.ipf(vertices_u[var7]);
						buffer.ipf(vertices_v[var7]);
						buffer.index += vertex_size - 8;
					}
				}
			}
			buffer.index = vertex_size * num_rendering_vertices;
			VertexBuffer created_buffer;
			boolean debug = false;
			if (check_states) {
				if (vertices_buffer == null) {
					vertices_buffer = GLManager.create_vertex_buffer(vertex_size, buffer.byteBuffer, buffer.index, true);
				} else {
					vertices_buffer.upload(vertex_size, buffer.byteBuffer, buffer.index);
				}
				created_buffer = vertices_buffer;
				vertices_buffer_state = (byte) 0;
			} else {
				created_buffer = GLManager.create_vertex_buffer(vertex_size, buffer.byteBuffer, buffer.index, false);
				debug = true;
			}
			VertexBuffer b0 = null, b1 = null, b2 = null, b3 = null;
			if (positions_updated) {
				b0 = positions_attribute.buffer;
				positions_attribute.buffer = created_buffer;
				positions_attribute.offset = positions_offset;
				positions_attribute.is_clean = true;
			}
			if (texcoords_updated) {
				b1 = texcoords_attribute.buffer;
				texcoords_attribute.buffer = created_buffer;
				texcoords_attribute.offset = texcoords_offset;
				texcoords_attribute.is_clean = true;
			}
			if (colors_updated) {
				b2 = colours_attribute.buffer;
				colours_attribute.buffer = created_buffer;
				colours_attribute.offset = colors_offset;
				colours_attribute.is_clean = true;
			}
			if (normals_updated) {
				b3 = normals_attribute.buffer;
				normals_attribute.buffer = created_buffer;
				normals_attribute.offset = normals_offset;
				normals_attribute.is_clean = true;
			}
			if (!is_vertexbuffer_used(b0)) {
				b0.destroy();
			}
			if (!is_vertexbuffer_used(b1)) {
				b1.destroy();
			}
			if (!is_vertexbuffer_used(b2)) {
				b2.destroy();
			}
			if (!is_vertexbuffer_used(b3)) {
				b3.destroy();
			}
		}
	}

	private boolean is_vertexbuffer_used(VertexBuffer buffer) {
		return buffer == null || buffer == vertices_buffer || positions_attribute != null && positions_attribute.buffer == buffer || colours_attribute != null && colours_attribute.buffer == buffer || normals_attribute != null && normals_attribute.buffer == buffer || texcoords_attribute != null && texcoords_attribute.buffer == buffer;
	}

	public void method1920(boolean reset_positions, boolean reset_colours, boolean reset_normals, boolean reset_texcoords, boolean reset_faces, boolean rest_labels, boolean reset_buffers) {
		if (vertices_buffer_state != 0) {
			throw new IllegalArgumentException();
		} else if (num_rendering_vertices != 0) {
			if (reset_buffers) {
				boolean var8 = !colours_attribute.is_clean && (reset_colours || reset_normals && !ClientOptions.clientoption_highdetails_lighting);
				create_vertices_buffer(false, !positions_attribute.is_clean && reset_positions, var8, normals_attribute != null && !normals_attribute.is_clean && reset_normals, !texcoords_attribute.is_clean && reset_texcoords);
				if (!elements_buffer.is_clean && reset_faces && reset_colours) {
					create_elements_buffer();
				}
			}
			if (reset_positions) {
				if (positions_attribute.is_clean) {
					if (!bounding_box.is_clean) {
						update_boundingbox();
					}
					vertices_x = null;
					vertices_y = null;
					vertices_z = null;
					vertices_renderlist = null;
					shared_vertices = null;
				} else {
					functionmask = (byte) (functionmask | 1);
				}
			}
			if (reset_colours) {
				if (colours_attribute.is_clean) {
					faces_colour = null;
					faces_alpha = null;
				} else {
					functionmask = (byte) (functionmask | 2);
				}
			}

			if (reset_normals && ClientOptions.clientoption_highdetails_lighting) {
				if (normals_attribute.is_clean) {
					normals_x = null;
					normals_y = null;
					normals_z = null;
					normals_m = null;
				} else {
					functionmask = (byte) (functionmask | 4);
				}
			}

			if (reset_texcoords) {
				if (texcoords_attribute.is_clean) {
					vertices_u = null;
					vertices_v = null;
				} else {
					functionmask = (byte) (functionmask | 8);
				}
			}

			if (reset_faces && reset_colours) {
				if (elements_buffer.is_clean && colours_attribute.is_clean) {
					faces_a = null;
					faces_b = null;
					faces_c = null;
				} else {
					functionmask = (byte) (functionmask | 16);
				}
			}

			if (rest_labels) {
				vertices_label = null;
				faces_label = null;
				vertices_by_label = null;
				faces_by_label = null;
			}

		}
	}

	public static int get_face_colour(int var0, short var1, int var2, byte alpha) {
		int rgb = ColourUtil.hslToRgbTable[MapLoader.repackHSL(var0, var2)];
		if (var1 != -1) {
			MaterialRaw material = GraphicTools.get_materials().get_material(var1 & 0xffff);
			int var5 = material.intensity & 0xff;
			if (var5 != 0) {
				int var6;
				if (var2 < 0) {
					var6 = 0;
				} else if (var2 > 127) {
					var6 = 16777215;
				} else {
					var6 = 131586 * var2;
				}
				if (var5 == 256) {
					rgb = var6;
				} else {
					int var8 = 256 - var5;
					rgb = ((var6 & 16711935) * var5 + (rgb & 16711935) * var8 & -16711936) + ((var6 & 0xff00) * var5 + (rgb & 0xff00) * var8 & 16711680) >> 8;
				}
			}
			int var6 = material.brightness & 0xff;
			if (var6 != 0) {
				var6 += 256;
				int var7 = ((rgb & 16711680) >> 16) * var6;
				if (var7 > 0xffff) {
					var7 = 0xffff;
				}
				int var8 = ((rgb & 0xff00) >> 8) * var6;
				if (var8 > 0xffff) {
					var8 = 0xffff;
				}
				int var9 = (rgb & 255) * var6;
				if (var9 > 0xffff) {
					var9 = 0xffff;
				}
				rgb = (var7 << 8 & 16711680) + (var8 & 0xff00) + (var9 >> 8);
			}
		}
		return rgb << 8 | 255 - (alpha & 255);
	}

	public void create_elements_buffer() {
		if (buffer.byteBuffer.length >= num_rendering_vertices * 12) {
			buffer.index = 0;
		} else {
			buffer = new Packet((num_rendering_vertices + 100) * 12);
		}
		if (GLManager.big_endianess) {
			for (int face = 0; face < num_rendering_faces; ++face) {
				buffer.putShort(faces_a[face]);
				buffer.putShort(faces_b[face]);
				buffer.putShort(faces_c[face]);
			}
		} else {
			for (int face = 0; face < num_rendering_faces; ++face) {
				buffer.putLEShort(faces_a[face]);
				buffer.putLEShort(faces_b[face]);
				buffer.putLEShort(faces_c[face]);
			}
		}
		if (elements_buffer.buffer == null) {
			elements_buffer.buffer = GLManager.create_index_buffer(GL_UNSIGNED_SHORT, buffer.byteBuffer, buffer.index, false);
		} else {
			elements_buffer.buffer.upload(GL_UNSIGNED_SHORT, buffer.byteBuffer, buffer.index);
		}
		elements_buffer.is_clean = true;
	}

	@Override
	public void sharelight(SceneNode with_node, int var2, int var3, int var4, boolean var5) {
		OpenGLModel with_model = (OpenGLModel) with_node;
		if (num_rendering_faces != 0 && with_model.num_rendering_faces != 0) {
			int var7 = with_model.num_vertices;
			int[] var8 = with_model.vertices_x;
			int[] var9 = with_model.vertices_y;
			int[] var10 = with_model.vertices_z;
			short[] var11 = with_model.normals_x;
			short[] var12 = with_model.normals_y;
			short[] var13 = with_model.normals_z;
			short[] var14 = with_model.normals_m;
			short[] var15;
			short[] var17;
			short[] var16;
			short[] var18;
			if (saved_normals != null) {
				var15 = saved_normals.aShortArray417;
				var16 = saved_normals.aShortArray419;
				var17 = saved_normals.aShortArray418;
				var18 = saved_normals.aShortArray416;
			} else {
				var15 = null;
				var16 = null;
				var17 = null;
				var18 = null;
			}

			short[] var19;
			short[] var21;
			short[] var20;
			short[] var22;
			if (with_model.saved_normals != null) {
				var19 = with_model.saved_normals.aShortArray417;
				var20 = with_model.saved_normals.aShortArray419;
				var21 = with_model.saved_normals.aShortArray418;
				var22 = with_model.saved_normals.aShortArray416;
			} else {
				var19 = null;
				var20 = null;
				var21 = null;
				var22 = null;
			}

			int[] var23 = with_model.shared_vertices;
			short[] var24 = with_model.vertices_renderlist;
			if (!with_model.bounding_box.is_clean) {
				with_model.update_boundingbox();
			}

			short var25 = with_model.bounding_box.miny;
			short var26 = with_model.bounding_box.maxy;
			short var27 = with_model.bounding_box.minx;
			short var28 = with_model.bounding_box.maxx;
			short var29 = with_model.bounding_box.minz;
			short var30 = with_model.bounding_box.maxz;

			for (int var31 = 0; var31 < num_vertices; ++var31) {
				int var32 = vertices_y[var31] - var3;
				if (var32 >= var25 && var32 <= var26) {
					int var33 = vertices_x[var31] - var2;
					if (var33 >= var27 && var33 <= var28) {
						int var34 = vertices_z[var31] - var4;
						if (var34 >= var29 && var34 <= var30) {
							int var35 = -1;
							int var36 = shared_vertices[var31];
							int var37 = shared_vertices[var31 + 1];

							int var38;
							for (var38 = var36; var38 < var37; ++var38) {
								var35 = vertices_renderlist[var38] - 1;
								if (var35 == -1 || normals_m[var35] != 0) {
									break;
								}
							}

							if (var35 != -1) {
								for (var38 = 0; var38 < var7; ++var38) {
									if (var33 == var8[var38] && var34 == var10[var38] && var32 == var9[var38]) {
										int var39 = -1;
										var36 = var23[var38];
										var37 = var23[var38 + 1];

										for (int var40 = var36; var40 < var37; ++var40) {
											var39 = var24[var40] - 1;
											if (var39 == -1 || var14[var39] != 0) {
												break;
											}
										}

										if (var39 != -1) {
											if (var15 == null) {
												saved_normals = new VertexNormalList();
												var15 = saved_normals.aShortArray417 = ArrayUtils.copy(normals_x);
												var16 = saved_normals.aShortArray419 = ArrayUtils.copy(normals_y);
												var17 = saved_normals.aShortArray418 = ArrayUtils.copy(normals_z);
												var18 = saved_normals.aShortArray416 = ArrayUtils.copy(normals_m);
											}

											if (var19 == null) {
												VertexNormalList var47 = with_model.saved_normals = new VertexNormalList();
												var19 = var47.aShortArray417 = ArrayUtils.copy(var11);
												var20 = var47.aShortArray419 = ArrayUtils.copy(var12);
												var21 = var47.aShortArray418 = ArrayUtils.copy(var13);
												var22 = var47.aShortArray416 = ArrayUtils.copy(var14);
											}

											short var46 = normals_x[var35];
											short var41 = normals_y[var35];
											short var42 = normals_z[var35];
											short var43 = normals_m[var35];
											var36 = var23[var38];
											var37 = var23[var38 + 1];

											int var44;
											int var45;
											for (var44 = var36; var44 < var37; ++var44) {
												var45 = var24[var44] - 1;
												if (var45 == -1) {
													break;
												}

												if (var22[var45] != 0) {
													var19[var45] += var46;
													var20[var45] += var41;
													var21[var45] += var42;
													var22[var45] += var43;
												}
											}

											var46 = var11[var39];
											var41 = var12[var39];
											var42 = var13[var39];
											var43 = var14[var39];
											var36 = shared_vertices[var31];
											var37 = shared_vertices[var31 + 1];

											for (var44 = var36; var44 < var37; ++var44) {
												var45 = vertices_renderlist[var44] - 1;
												if (var45 == -1) {
													break;
												}

												if (var18[var45] != 0) {
													var15[var45] += var46;
													var16[var45] += var41;
													var17[var45] += var42;
													var18[var45] += var43;
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}

		}
	}

	@Override
	public void xaxis_rotate_without_normals(int angle) {
		int sin = Rasterizer.SINE[angle];
		int cos = Rasterizer.COSINE[angle];
		for (int vertex = 0; vertex < num_vertices; ++vertex) {
			int temp = vertices_y[vertex] * cos - vertices_z[vertex] * sin >> 16;
			vertices_z[vertex] = vertices_y[vertex] * sin + vertices_z[vertex] * cos >> 16;
			vertices_y[vertex] = temp;
		}
		bounding_box.is_clean = false;
		positions_attribute.is_clean = false;
	}

	public void yaxis_rotate(int var1) {
		if (normals_x == null) {
			yaxis_rotate_without_normals(var1);
		} else {
			int var2 = Rasterizer.SINE[var1];
			int var3 = Rasterizer.COSINE[var1];
			for (int var4 = 0; var4 < num_vertices; ++var4) {
				int var5 = vertices_z[var4] * var2 + vertices_x[var4] * var3 >> 16;
				vertices_z[var4] = vertices_z[var4] * var3 - vertices_x[var4] * var2 >> 16;
				vertices_x[var4] = var5;
			}

			for (int var4 = 0; var4 < num_rendering_vertices; ++var4) {
				int var5 = normals_z[var4] * var2 + normals_x[var4] * var3 >> 16;
				normals_z[var4] = (short) (normals_z[var4] * var3 - normals_x[var4] * var2 >> 16);
				normals_x[var4] = (short) var5;
			}
			bounding_box.is_clean = false;
			positions_attribute.is_clean = false;
			if (normals_attribute != null) {
				normals_attribute.is_clean = false;
			}

		}
	}

	@Override
	public void yaxis_rotate_without_normals(int angle) {
		int sin = Rasterizer.SINE[angle];
		int cos = Rasterizer.COSINE[angle];
		for (int vertex = 0; vertex < num_vertices; ++vertex) {
			int temp = vertices_z[vertex] * sin + vertices_x[vertex] * cos >> 16;
			vertices_z[vertex] = vertices_z[vertex] * cos - vertices_x[vertex] * sin >> 16;
			vertices_x[vertex] = temp;
		}
		bounding_box.is_clean = false;
		positions_attribute.is_clean = false;
	}

	@Override
	public void zaxis_rotate_without_normals(int angle) {
		int sin = Rasterizer.SINE[angle];
		int cos = Rasterizer.COSINE[angle];
		for (int vertex = 0; vertex < num_vertices; ++vertex) {
			int temp = vertices_y[vertex] * sin + vertices_x[vertex] * cos >> 16;
			vertices_y[vertex] = vertices_y[vertex] * cos - vertices_x[vertex] * sin >> 16;
			vertices_x[vertex] = temp;
		}
		bounding_box.is_clean = false;
		positions_attribute.is_clean = false;
	}

	@Override
	public void translate(int offset_x, int offset_y, int offset_z) {
		for (int vertex = 0; vertex < num_vertices; ++vertex) {
			vertices_x[vertex] += offset_x;
			vertices_y[vertex] += offset_y;
			vertices_z[vertex] += offset_z;
		}
		bounding_box.is_clean = false;
		positions_attribute.is_clean = false;
	}

	@Override
	public void scale(int scale_x, int scale_y, int scale_z) {
		for (int vertex = 0; vertex < num_vertices; ++vertex) {
			vertices_x[vertex] = vertices_x[vertex] * scale_x >> 7;
			vertices_y[vertex] = vertices_y[vertex] * scale_y >> 7;
			vertices_z[vertex] = vertices_z[vertex] * scale_z >> 7;
		}
		bounding_box.is_clean = false;
		positions_attribute.is_clean = false;
	}

	@Override
	public int get_minx() {
		if (!bounding_box.is_clean) {
			update_boundingbox();
		}
		return bounding_box.minx;
	}

	@Override
	public int get_maxx() {
		if (!bounding_box.is_clean) {
			update_boundingbox();
		}
		return bounding_box.maxx;
	}

	@Override
	public int get_miny() {
		if (!bounding_box.is_clean) {
			update_boundingbox();
		}

		return bounding_box.miny;
	}

	@Override
	public int get_minz() {
		if (!bounding_box.is_clean) {
			update_boundingbox();
		}

		return bounding_box.minz;
	}

	@Override
	public int get_maxz() {
		if (!bounding_box.is_clean) {
			update_boundingbox();
		}

		return bounding_box.maxz;
	}

	@Override
	public int get_size2d() {
		if (!bounding_box.is_clean) {
			update_boundingbox();
		}
		return bounding_box.size2d;
	}

	public void update_boundingbox() {
		int min_x = Short.MAX_VALUE;
		int min_y = Short.MAX_VALUE;
		int min_z = Short.MAX_VALUE;
		int max_x = Short.MIN_VALUE;
		int max_y = Short.MIN_VALUE;
		int max_z = Short.MIN_VALUE;
		int min_size2d = 0;
		int min_size3d = 0;
		for (int var9 = 0; var9 < num_vertices; ++var9) {
			int vertex_x = vertices_x[var9];
			int vertex_y = vertices_y[var9];
			int vertex_z = vertices_z[var9];
			if (vertex_x < min_x) {
				min_x = vertex_x;
			}
			if (vertex_x > max_x) {
				max_x = vertex_x;
			}
			if (vertex_y < min_y) {
				min_y = vertex_y;
			}
			if (vertex_y > max_y) {
				max_y = vertex_y;
			}
			if (vertex_z < min_z) {
				min_z = vertex_z;
			}
			if (vertex_z > max_z) {
				max_z = vertex_z;
			}
			int size2d = vertex_x * vertex_x + vertex_z * vertex_z;
			if (size2d > min_size2d) {
				min_size2d = size2d;
			}
			int size_3d = vertex_x * vertex_x + vertex_z * vertex_z + vertex_y * vertex_y;
			if (size_3d > min_size3d) {
				min_size3d = size_3d;
			}
		}
		bounding_box.minx = (short) min_x;
		bounding_box.maxx = (short) max_x;
		bounding_box.miny = (short) min_y;
		bounding_box.maxy = (short) max_y;
		bounding_box.minz = (short) min_z;
		bounding_box.maxz = (short) max_z;
		bounding_box.size2d = (short) (int) (Math.sqrt(min_size2d) + 0.99D);
		Math.sqrt(min_size3d);
		bounding_box.is_clean = true;
	}

	public void mirror_z() {
		int var1;
		for (var1 = 0; var1 < num_vertices; ++var1) {
			vertices_z[var1] = -vertices_z[var1];
		}
		if (normals_z != null) {
			for (var1 = 0; var1 < num_rendering_vertices; ++var1) {
				normals_z[var1] = (short) -normals_z[var1];
			}
		}
		for (var1 = 0; var1 < num_rendering_faces; ++var1) {
			short var2 = faces_a[var1];
			faces_a[var1] = faces_c[var1];
			faces_c[var1] = var2;
		}
		bounding_box.is_clean = false;
		positions_attribute.is_clean = false;
		if (normals_attribute != null) {
			normals_attribute.is_clean = false;
		}
		elements_buffer.is_clean = false;
	}

	public void rotate180() {
		if (normals_x == null) {
			rotate180_without_normals();
		} else {
			int var1;
			for (var1 = 0; var1 < num_vertices; ++var1) {
				vertices_x[var1] = -vertices_x[var1];
				vertices_z[var1] = -vertices_z[var1];
			}

			for (var1 = 0; var1 < num_rendering_vertices; ++var1) {
				normals_x[var1] = (short) -normals_x[var1];
				normals_z[var1] = (short) -normals_z[var1];
			}

			bounding_box.is_clean = false;
			positions_attribute.is_clean = false;
			if (normals_attribute != null) {
				normals_attribute.is_clean = false;
			}

		}
	}

	@Override
	public void rotate180_without_normals() {
		for (int vertex = 0; vertex < num_vertices; ++vertex) {
			vertices_x[vertex] = -vertices_x[vertex];
			vertices_z[vertex] = -vertices_z[vertex];
		}
		bounding_box.is_clean = false;
		positions_attribute.is_clean = false;
	}

	public void rotate270() {
		if (normals_x == null) {
			rotate270_without_normals();
		} else {
			int var1;
			for (var1 = 0; var1 < num_vertices; ++var1) {
				int var2 = vertices_z[var1];
				vertices_z[var1] = vertices_x[var1];
				vertices_x[var1] = -var2;
			}

			for (var1 = 0; var1 < num_rendering_vertices; ++var1) {
				short var3 = normals_z[var1];
				normals_z[var1] = normals_x[var1];
				normals_x[var1] = (short) -var3;
			}

			bounding_box.is_clean = false;
			positions_attribute.is_clean = false;
			if (normals_attribute != null) {
				normals_attribute.is_clean = false;
			}

		}
	}

	@Override
	public void rotate270_without_normals() {
		for (int vertex = 0; vertex < num_vertices; ++vertex) {
			int temp = vertices_z[vertex];
			vertices_z[vertex] = vertices_x[vertex];
			vertices_x[vertex] = -temp;
		}
		bounding_box.is_clean = false;
		positions_attribute.is_clean = false;
	}

	public void rotate90() {
		if (normals_x == null) {
			rotate90_without_normals();
		} else {
			int var1;
			for (var1 = 0; var1 < num_vertices; ++var1) {
				int var2 = vertices_x[var1];
				vertices_x[var1] = vertices_z[var1];
				vertices_z[var1] = -var2;
			}

			for (var1 = 0; var1 < num_rendering_vertices; ++var1) {
				short var3 = normals_x[var1];
				normals_x[var1] = normals_z[var1];
				normals_z[var1] = (short) -var3;
			}

			bounding_box.is_clean = false;
			positions_attribute.is_clean = false;
			if (normals_attribute != null) {
				normals_attribute.is_clean = false;
			}

		}
	}

	@Override
	public void rotate90_without_normals() {
		for (int vertex = 0; vertex < num_vertices; ++vertex) {
			int temp = vertices_x[vertex];
			vertices_x[vertex] = vertices_z[vertex];
			vertices_z[vertex] = -temp;
		}
		bounding_box.is_clean = false;
		positions_attribute.is_clean = false;
	}

	@Override
	public Model copy1(boolean reference_alphas, boolean reference_colors, boolean reference_normals) {
		return copy(reference_alphas, reference_colors, reference_normals, copy_one_target, copy_one_model);
	}

	@Override
	public Model copy2(boolean reference_alphas, boolean reference_colors, boolean reference_normals) {
		return copy(reference_alphas, reference_colors, reference_normals, copy_two_target, copy_two_model);
	}

	public Model copy(boolean reference_alphas, boolean reference_colors, boolean reference_normals, OpenGLModel target_model, OpenGLModel empty_model) {
		target_model.num_vertices = num_vertices;
		target_model.num_rendering_vertices = num_rendering_vertices;
		target_model.num_rendering_faces = num_rendering_faces;
		target_model.ambient = ambient;
		target_model.contrast = contrast;
		target_model.vertices_buffer_state = (byte) (1 | (reference_alphas && reference_colors ? 0 : 2) | (reference_normals ? 0 : 4));
		if (target_model.vertices_x == null || target_model.vertices_x.length < num_vertices) {
			target_model.vertices_x = new int[num_vertices + 100];
			target_model.vertices_y = new int[num_vertices + 100];
			target_model.vertices_z = new int[num_vertices + 100];
		}
		for (int var6 = 0; var6 < num_vertices; ++var6) {
			target_model.vertices_x[var6] = vertices_x[var6];
			target_model.vertices_y[var6] = vertices_y[var6];
			target_model.vertices_z[var6] = vertices_z[var6];
		}

		if (target_model.positions_attribute == null) {
			target_model.positions_attribute = new OpenGLVertexAttribute(null, GL_FLOAT, 3, 0);
		}

		target_model.positions_attribute.is_clean = false;
		if (target_model.bounding_box == null) {
			target_model.bounding_box = new BoundingBox();
		}

		target_model.bounding_box.is_clean = false;
		if (reference_alphas) {
			target_model.faces_alpha = faces_alpha;
		} else {
			if (empty_model.faces_alpha == null || empty_model.faces_alpha.length < num_rendering_faces) {
				empty_model.faces_alpha = new byte[num_rendering_faces + 100];
			}
			target_model.faces_alpha = empty_model.faces_alpha;
			for (int var6 = 0; var6 < num_rendering_faces; ++var6) {
				target_model.faces_alpha[var6] = faces_alpha[var6];
			}
		}
		if (reference_colors) {
			target_model.faces_colour = faces_colour;
		} else {
			if (empty_model.faces_colour == null || empty_model.faces_colour.length < num_rendering_faces) {
				empty_model.faces_colour = new short[num_rendering_faces + 100];
			}
			target_model.faces_colour = empty_model.faces_colour;
			for (int var6 = 0; var6 < num_rendering_faces; ++var6) {
				target_model.faces_colour[var6] = faces_colour[var6];
			}
		}
		if (reference_alphas && reference_colors) {
			target_model.colours_attribute = colours_attribute;
		} else {
			if (empty_model.colours_attribute == null) {
				empty_model.colours_attribute = new OpenGLVertexAttribute(null, GL_UNSIGNED_BYTE, 4, 0);
			}
			target_model.colours_attribute = empty_model.colours_attribute;
			target_model.colours_attribute.is_clean = false;
		}
		if (!reference_normals && normals_x != null) {
			if (empty_model.normals_x == null || empty_model.normals_x.length < num_rendering_vertices) {
				empty_model.normals_x = new short[num_rendering_vertices + 100];
				empty_model.normals_y = new short[num_rendering_vertices + 100];
				empty_model.normals_z = new short[num_rendering_vertices + 100];
				empty_model.normals_m = new short[num_rendering_vertices + 100];
			}
			target_model.normals_x = empty_model.normals_x;
			target_model.normals_y = empty_model.normals_y;
			target_model.normals_z = empty_model.normals_z;
			target_model.normals_m = empty_model.normals_m;
			for (int var6 = 0; var6 < num_rendering_vertices; ++var6) {
				target_model.normals_x[var6] = normals_x[var6];
				target_model.normals_y[var6] = normals_y[var6];
				target_model.normals_z[var6] = normals_z[var6];
				target_model.normals_m[var6] = normals_m[var6];
			}
			if (ClientOptions.clientoption_highdetails_lighting) {
				if (empty_model.normals_attribute == null) {
					empty_model.normals_attribute = new OpenGLVertexAttribute(null, GL_FLOAT, 3, 0);
				}
				target_model.normals_attribute = empty_model.normals_attribute;
				target_model.normals_attribute.is_clean = false;
			} else {
				target_model.normals_attribute = null;
			}
		} else {
			target_model.normals_x = normals_x;
			target_model.normals_y = normals_y;
			target_model.normals_z = normals_z;
			target_model.normals_m = normals_m;
			target_model.normals_attribute = normals_attribute;
		}
		target_model.vertices_u = vertices_u;
		target_model.vertices_v = vertices_v;
		target_model.vertices_label = vertices_label;
		target_model.vertices_by_label = vertices_by_label;
		target_model.faces_a = faces_a;
		target_model.faces_b = faces_b;
		target_model.faces_c = faces_c;
		target_model.faces_materials = faces_materials;
		target_model.faces_label = faces_label;
		target_model.faces_by_label = faces_by_label;
		target_model.texcoords_attribute = texcoords_attribute;
		target_model.elements_buffer = elements_buffer;
		target_model.rendering_lists = rendering_lists;
		target_model.vertices_renderlist = vertices_renderlist;
		target_model.shared_vertices = shared_vertices;
		target_model.renders_in_one_tile = renders_in_one_tile;
		target_model.vertices_submeshes = vertices_submeshes;
		target_model.faces_submeshes = faces_submeshes;
		return target_model;
	}

	public OpenGLModel duplicate(boolean copy_positions, boolean copy_heights, boolean var3, boolean var4, boolean var5, boolean var6, boolean var7, boolean var8, boolean var9, boolean var10, boolean directMaterials) {
		OpenGLModel var12 = new OpenGLModel();
		var12.num_vertices = num_vertices;
		var12.num_rendering_vertices = num_rendering_vertices;
		var12.num_rendering_faces = num_rendering_faces;
		if (copy_positions) {
			var12.vertices_x = vertices_x;
			var12.vertices_z = vertices_z;
		} else {
			var12.vertices_x = ArrayUtils.copy(vertices_x);
			var12.vertices_z = ArrayUtils.copy(vertices_z);
		}

		if (copy_heights) {
			var12.vertices_y = vertices_y;
		} else {
			var12.vertices_y = ArrayUtils.copy(vertices_y);
		}

		if (copy_positions && copy_heights) {
			var12.positions_attribute = positions_attribute;
			var12.bounding_box = bounding_box;
			var12.positions_referenced = true;
		} else {
			var12.positions_attribute = new OpenGLVertexAttribute(null, GL_FLOAT, 3, 0);
			var12.bounding_box = new BoundingBox();
		}

		if (var3) {
			var12.faces_colour = faces_colour;
		} else {
			var12.faces_colour = ArrayUtils.copy(faces_colour);
		}

		if (var4) {
			var12.faces_alpha = faces_alpha;
		} else {
			var12.faces_alpha = ArrayUtils.copy(faces_alpha);
		}

		if (var3 && var4 && var5 && (var8 && var6 || ClientOptions.clientoption_highdetails_lighting)) {
			var12.colours_attribute = colours_attribute;
		} else {
			var12.colours_attribute = new OpenGLVertexAttribute(null, GL_UNSIGNED_BYTE, 4, 0);
		}

		if (var6) {
			var12.normals_x = normals_x;
			var12.normals_y = normals_y;
			var12.normals_z = normals_z;
			var12.normals_m = normals_m;
		} else {
			var12.normals_x = ArrayUtils.copy(normals_x);
			var12.normals_y = ArrayUtils.copy(normals_y);
			var12.normals_z = ArrayUtils.copy(normals_z);
			var12.normals_m = ArrayUtils.copy(normals_m);
		}
		if (ClientOptions.clientoption_highdetails_lighting) {
			if (var6 && var7 && var8) {
				var12.normals_attribute = normals_attribute;
			} else {
				var12.normals_attribute = new OpenGLVertexAttribute(null, GL_FLOAT, 3, 0);
			}
		} else {
			var12.normals_attribute = null;
		}
		if (var9) {
			var12.vertices_u = vertices_u;
			var12.vertices_v = vertices_v;
			var12.texcoords_attribute = texcoords_attribute;
		} else {
			var12.vertices_u = ArrayUtils.copy(vertices_u);
			var12.vertices_v = ArrayUtils.copy(vertices_v);
			var12.texcoords_attribute = new OpenGLVertexAttribute(null, GL_FLOAT, 2, 0);
		}
		if (var10) {
			var12.faces_a = faces_a;
			var12.faces_b = faces_b;
			var12.faces_c = faces_c;
			var12.elements_buffer = elements_buffer;
		} else {
			var12.faces_a = ArrayUtils.copy(faces_a);
			var12.faces_b = ArrayUtils.copy(faces_b);
			var12.faces_c = ArrayUtils.copy(faces_c);
			var12.elements_buffer = new OpenGLModelIndexBuffer();
		}

		if (directMaterials) {
			var12.faces_materials = faces_materials;
		} else {
			var12.faces_materials = ArrayUtils.copy(faces_materials);
		}

		var12.vertices_label = vertices_label;
		var12.vertices_by_label = vertices_by_label;
		var12.faces_label = faces_label;
		var12.faces_by_label = faces_by_label;
		var12.rendering_lists = rendering_lists;
		var12.vertices_renderlist = vertices_renderlist;
		var12.shared_vertices = shared_vertices;
		var12.ambient = ambient;
		var12.contrast = contrast;
		var12.vertices_submeshes = vertices_submeshes;
		var12.faces_submeshes = faces_submeshes;
		return var12;
	}

	public void recolor(short src_colour, short dst_colour) {
		for (int var3 = 0; var3 < num_rendering_faces; ++var3) {
			if (faces_colour[var3] == src_colour) {
				faces_colour[var3] = dst_colour;
			}
		}
		colours_attribute.is_clean = false;
	}

	public void retexture(short src_materialid, short dst_materialid) {
		for (int var3 = 0; var3 < num_rendering_faces; ++var3) {
			if (faces_materials[var3] == src_materialid) {
				faces_materials[var3] = dst_materialid;
			}
		}
		int var3 = 0;
		int var4 = 0;
		if (src_materialid != -1) {
			MaterialRaw src_material = GraphicTools.get_materials().get_material(src_materialid & 0xffff);

			var3 = src_material.intensity & 0xff;
			var4 = src_material.brightness & 0xff;
		}
		int var5 = 0;
		int var6 = 0;
		if (dst_materialid != -1) {
			MaterialRaw dst_material = GraphicTools.get_materials().get_material(src_materialid & 0xffff);
			var5 = dst_material.intensity & 0xff;
			var6 = dst_material.brightness & 0xff;
		}
		if (var3 != var5 || var4 != var6) {
			colours_attribute.is_clean = false;
		}
	}

	public int get_ambient() {
		return ambient;
	}

	public void set_ambient(int var1) {
		ambient = (short) var1;
		colours_attribute.is_clean = false;
	}

	public int get_contrast() {
		return contrast;
	}

	public void set_contrast(int var1) {
		contrast = (short) var1;
		if (normals_attribute != null) {
			normals_attribute.is_clean = false;
		}
	}

	public static float[] calculate_texture_matrix(int p, int m, int n, int rotation, float scale_x, float scale_y, float scale_z) {
		float[] var7 = new float[9];
		float[] matrix = new float[9];
		float cos = (float) Math.cos(rotation * 0.024543693F);
		float sin = (float) Math.sin(rotation * 0.024543693F);
		float var11 = 1.0F - cos;
		var7[0] = cos;
		var7[1] = 0.0F;
		var7[2] = sin;
		var7[3] = 0.0F;
		var7[4] = 1.0F;
		var7[5] = 0.0F;
		var7[6] = -sin;
		var7[7] = 0.0F;
		var7[8] = cos;
		float[] var12 = new float[9];
		float var13 = 1.0F;
		float var14 = 0.0F;
		cos = m / 32767.0F;
		sin = -((float) Math.sqrt(1.0F - cos * cos));
		var11 = 1.0F - cos;
		float pn_size = (float) Math.sqrt(p * p + n * n);
		if (pn_size == 0.0F && cos == 0.0F) {
			matrix = var7;
		} else {
			if (pn_size != 0.0F) {
				var13 = -n / pn_size;
				var14 = p / pn_size;
			}
			var12[0] = cos + var13 * var13 * var11;
			var12[1] = var14 * sin;
			var12[2] = var14 * var13 * var11;
			var12[3] = -var14 * sin;
			var12[4] = cos;
			var12[5] = var13 * sin;
			var12[6] = var13 * var14 * var11;
			var12[7] = -var13 * sin;
			var12[8] = cos + var14 * var14 * var11;
			matrix[0] = var7[0] * var12[0] + var7[1] * var12[3] + var7[2] * var12[6];
			matrix[1] = var7[0] * var12[1] + var7[1] * var12[4] + var7[2] * var12[7];
			matrix[2] = var7[0] * var12[2] + var7[1] * var12[5] + var7[2] * var12[8];
			matrix[3] = var7[3] * var12[0] + var7[4] * var12[3] + var7[5] * var12[6];
			matrix[4] = var7[3] * var12[1] + var7[4] * var12[4] + var7[5] * var12[7];
			matrix[5] = var7[3] * var12[2] + var7[4] * var12[5] + var7[5] * var12[8];
			matrix[6] = var7[6] * var12[0] + var7[7] * var12[3] + var7[8] * var12[6];
			matrix[7] = var7[6] * var12[1] + var7[7] * var12[4] + var7[8] * var12[7];
			matrix[8] = var7[6] * var12[2] + var7[7] * var12[5] + var7[8] * var12[8];
		}
		matrix[0] *= scale_x;
		matrix[1] *= scale_x;
		matrix[2] *= scale_x;
		matrix[3] *= scale_y;
		matrix[4] *= scale_y;
		matrix[5] *= scale_y;
		matrix[6] *= scale_z;
		matrix[7] *= scale_z;
		matrix[8] *= scale_z;
		return matrix;
	}

	public static void map_rectangular(int var0, int var1, int var2, int var3, int var4, int var5, int var6, float[] var7, int var8, float var9, float var10, float var11) {
		var0 -= var3;
		var1 -= var4;
		var2 -= var5;
		float var12 = var0 * var7[0] + var1 * var7[1] + var2 * var7[2];
		float var13 = var0 * var7[3] + var1 * var7[4] + var2 * var7[5];
		float var14 = var0 * var7[6] + var1 * var7[7] + var2 * var7[8];
		float var15;
		float var16;
		if (var6 == 0) {
			var15 = var12 + var9 + 0.5F;
			var16 = -var14 + var11 + 0.5F;
		} else if (var6 == 1) {
			var15 = var12 + var9 + 0.5F;
			var16 = var14 + var11 + 0.5F;
		} else if (var6 == 2) {
			var15 = -var12 + var9 + 0.5F;
			var16 = -var13 + var10 + 0.5F;
		} else if (var6 == 3) {
			var15 = var12 + var9 + 0.5F;
			var16 = -var13 + var10 + 0.5F;
		} else if (var6 == 4) {
			var15 = var14 + var11 + 0.5F;
			var16 = -var13 + var10 + 0.5F;
		} else {
			var15 = -var14 + var11 + 0.5F;
			var16 = -var13 + var10 + 0.5F;
		}

		float var17;
		if (var8 == 1) {
			var17 = var15;
			var15 = -var16;
			var16 = var17;
		} else if (var8 == 2) {
			var15 = -var15;
			var16 = -var16;
		} else if (var8 == 3) {
			var17 = var15;
			var15 = var16;
			var16 = -var17;
		}
		rectangular_u = var15;
		rectangular_v = var16;
	}

	public static void map_cylinderical(int var0, int var1, int var2, int var3, int var4, int var5, float[] var6, float var7, int var8, float var9) {
		var0 -= var3;
		var1 -= var4;
		var2 -= var5;
		float var10 = var0 * var6[0] + var1 * var6[1] + var2 * var6[2];
		float var11 = var0 * var6[3] + var1 * var6[4] + var2 * var6[5];
		float var12 = var0 * var6[6] + var1 * var6[7] + var2 * var6[8];
		float var13 = (float) Math.atan2(var10, var12) / 6.2831855F + 0.5F;
		if (var7 != 1.0F) {
			var13 *= var7;
		}
		float var14 = var11 + 0.5F + var9;
		float var15;
		if (var8 == 1) {
			var15 = var13;
			var13 = -var14;
			var14 = var15;
		} else if (var8 == 2) {
			var13 = -var13;
			var14 = -var14;
		} else if (var8 == 3) {
			var15 = var13;
			var13 = var14;
			var14 = -var15;
		}
		mapped_cylinderical_u = var13;
		mapped_cylinderical_v = var14;
	}

	public static void map_spherical(int var0, int var1, int var2, int var3, int var4, int var5, float[] var6, int var7, float var8) {
		var0 -= var3;
		var1 -= var4;
		var2 -= var5;
		float var9 = var0 * var6[0] + var1 * var6[1] + var2 * var6[2];
		float var10 = var0 * var6[3] + var1 * var6[4] + var2 * var6[5];
		float var11 = var0 * var6[6] + var1 * var6[7] + var2 * var6[8];
		float var12 = (float) Math.sqrt(var9 * var9 + var10 * var10 + var11 * var11);
		float var13 = (float) Math.atan2(var9, var11) / 6.2831855F + 0.5F;
		float var14 = (float) Math.asin(var10 / var12) / 3.1415927F + 0.5F + var8;
		float var15;
		if (var7 == 1) {
			var15 = var13;
			var13 = -var14;
			var14 = var15;
		} else if (var7 == 2) {
			var13 = -var13;
			var14 = -var14;
		} else if (var7 == 3) {
			var15 = var13;
			var13 = var14;
			var14 = -var15;
		}
		spherical_u = var13;
		spherical_v = var14;
	}

	public static int get_cube_face(float x, float y, float z) {
		float f_17_ = x < 0.0F ? -x : x;
		float f_18_ = y < 0.0F ? -y : y;
		float f_19_ = z < 0.0F ? -z : z;
		if (f_18_ > f_17_ && f_18_ > f_19_) {
			if (y > 0.0F) {
				return 0;
			}
			return 1;
		}
		if (f_19_ > f_17_ && f_19_ > f_18_) {
			if (z > 0.0F) {
				return 2;
			}
			return 3;
		}
		if (x > 0.0F) {
			return 4;
		}
		return 5;
	}

	public static void rasterize_shadowmap(byte[] var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
		int var8 = 0;
		if (var2 != var1) {
			var8 = (var5 - var4 << 16) / (var2 - var1);
		}
		int var9 = 0;
		if (var3 != var2) {
			var9 = (var6 - var5 << 16) / (var3 - var2);
		}
		int var10 = 0;
		if (var3 != var1) {
			var10 = (var4 - var6 << 16) / (var1 - var3);
		}
		if (var1 <= var2 && var1 <= var3) {
			if (var2 < var3) {
				var6 = var4 <<= 16;
				if (var1 < 0) {
					var6 -= var10 * var1;
					var4 -= var8 * var1;
					var1 = 0;
				}

				var5 <<= 16;
				if (var2 < 0) {
					var5 -= var9 * var2;
					var2 = 0;
				}

				if ((var1 == var2 || var10 >= var8) && (var1 != var2 || var10 <= var9)) {
					var3 -= var2;
					var2 -= var1;
					var1 *= var7;

					while (true) {
						--var2;
						if (var2 < 0) {
							while (true) {
								--var3;
								if (var3 < 0) {
									return;
								}

								rasterizer_shadow_scanline(var0, var1, 0, var5 >> 16, var6 >> 16);
								var6 += var10;
								var5 += var9;
								var1 += var7;
							}
						}

						rasterizer_shadow_scanline(var0, var1, 0, var4 >> 16, var6 >> 16);
						var6 += var10;
						var4 += var8;
						var1 += var7;
					}
				} else {
					var3 -= var2;
					var2 -= var1;
					var1 *= var7;

					while (true) {
						--var2;
						if (var2 < 0) {
							while (true) {
								--var3;
								if (var3 < 0) {
									return;
								}

								rasterizer_shadow_scanline(var0, var1, 0, var6 >> 16, var5 >> 16);
								var6 += var10;
								var5 += var9;
								var1 += var7;
							}
						}

						rasterizer_shadow_scanline(var0, var1, 0, var6 >> 16, var4 >> 16);
						var6 += var10;
						var4 += var8;
						var1 += var7;
					}
				}
			} else {
				var5 = var4 <<= 16;
				if (var1 < 0) {
					var5 -= var10 * var1;
					var4 -= var8 * var1;
					var1 = 0;
				}

				var6 <<= 16;
				if (var3 < 0) {
					var6 -= var9 * var3;
					var3 = 0;
				}

				if ((var1 == var3 || var10 >= var8) && (var1 != var3 || var9 <= var8)) {
					var2 -= var3;
					var3 -= var1;
					var1 *= var7;

					while (true) {
						--var3;
						if (var3 < 0) {
							while (true) {
								--var2;
								if (var2 < 0) {
									return;
								}

								rasterizer_shadow_scanline(var0, var1, 0, var4 >> 16, var6 >> 16);
								var6 += var9;
								var4 += var8;
								var1 += var7;
							}
						}

						rasterizer_shadow_scanline(var0, var1, 0, var4 >> 16, var5 >> 16);
						var5 += var10;
						var4 += var8;
						var1 += var7;
					}
				} else {
					var2 -= var3;
					var3 -= var1;
					var1 *= var7;

					while (true) {
						--var3;
						if (var3 < 0) {
							while (true) {
								--var2;
								if (var2 < 0) {
									return;
								}

								rasterizer_shadow_scanline(var0, var1, 0, var6 >> 16, var4 >> 16);
								var6 += var9;
								var4 += var8;
								var1 += var7;
							}
						}

						rasterizer_shadow_scanline(var0, var1, 0, var5 >> 16, var4 >> 16);
						var5 += var10;
						var4 += var8;
						var1 += var7;
					}
				}
			}
		} else if (var2 <= var3) {
			if (var3 < var1) {
				var4 = var5 <<= 16;
				if (var2 < 0) {
					var4 -= var8 * var2;
					var5 -= var9 * var2;
					var2 = 0;
				}

				var6 <<= 16;
				if (var3 < 0) {
					var6 -= var10 * var3;
					var3 = 0;
				}

				if ((var2 == var3 || var8 >= var9) && (var2 != var3 || var8 <= var10)) {
					var1 -= var3;
					var3 -= var2;
					var2 *= var7;

					while (true) {
						--var3;
						if (var3 < 0) {
							while (true) {
								--var1;
								if (var1 < 0) {
									return;
								}

								rasterizer_shadow_scanline(var0, var2, 0, var6 >> 16, var4 >> 16);
								var4 += var8;
								var6 += var10;
								var2 += var7;
							}
						}

						rasterizer_shadow_scanline(var0, var2, 0, var5 >> 16, var4 >> 16);
						var4 += var8;
						var5 += var9;
						var2 += var7;
					}
				} else {
					var1 -= var3;
					var3 -= var2;
					var2 *= var7;

					while (true) {
						--var3;
						if (var3 < 0) {
							while (true) {
								--var1;
								if (var1 < 0) {
									return;
								}

								rasterizer_shadow_scanline(var0, var2, 0, var4 >> 16, var6 >> 16);
								var4 += var8;
								var6 += var10;
								var2 += var7;
							}
						}

						rasterizer_shadow_scanline(var0, var2, 0, var4 >> 16, var5 >> 16);
						var4 += var8;
						var5 += var9;
						var2 += var7;
					}
				}
			} else {
				var6 = var5 <<= 16;
				if (var2 < 0) {
					var6 -= var8 * var2;
					var5 -= var9 * var2;
					var2 = 0;
				}

				var4 <<= 16;
				if (var1 < 0) {
					var4 -= var10 * var1;
					var1 = 0;
				}

				if (var8 < var9) {
					var3 -= var1;
					var1 -= var2;
					var2 *= var7;

					while (true) {
						--var1;
						if (var1 < 0) {
							while (true) {
								--var3;
								if (var3 < 0) {
									return;
								}

								rasterizer_shadow_scanline(var0, var2, 0, var4 >> 16, var5 >> 16);
								var4 += var10;
								var5 += var9;
								var2 += var7;
							}
						}

						rasterizer_shadow_scanline(var0, var2, 0, var6 >> 16, var5 >> 16);
						var6 += var8;
						var5 += var9;
						var2 += var7;
					}
				} else {
					var3 -= var1;
					var1 -= var2;
					var2 *= var7;

					while (true) {
						--var1;
						if (var1 < 0) {
							while (true) {
								--var3;
								if (var3 < 0) {
									return;
								}

								rasterizer_shadow_scanline(var0, var2, 0, var5 >> 16, var4 >> 16);
								var4 += var10;
								var5 += var9;
								var2 += var7;
							}
						}

						rasterizer_shadow_scanline(var0, var2, 0, var5 >> 16, var6 >> 16);
						var6 += var8;
						var5 += var9;
						var2 += var7;
					}
				}
			}
		} else if (var1 < var2) {
			var5 = var6 <<= 16;
			if (var3 < 0) {
				var5 -= var9 * var3;
				var6 -= var10 * var3;
				var3 = 0;
			}

			var4 <<= 16;
			if (var1 < 0) {
				var4 -= var8 * var1;
				var1 = 0;
			}

			if (var9 < var10) {
				var2 -= var1;
				var1 -= var3;
				var3 *= var7;

				while (true) {
					--var1;
					if (var1 < 0) {
						while (true) {
							--var2;
							if (var2 < 0) {
								return;
							}

							rasterizer_shadow_scanline(var0, var3, 0, var5 >> 16, var4 >> 16);
							var5 += var9;
							var4 += var8;
							var3 += var7;
						}
					}

					rasterizer_shadow_scanline(var0, var3, 0, var5 >> 16, var6 >> 16);
					var5 += var9;
					var6 += var10;
					var3 += var7;
				}
			} else {
				var2 -= var1;
				var1 -= var3;
				var3 *= var7;

				while (true) {
					--var1;
					if (var1 < 0) {
						while (true) {
							--var2;
							if (var2 < 0) {
								return;
							}

							rasterizer_shadow_scanline(var0, var3, 0, var4 >> 16, var5 >> 16);
							var5 += var9;
							var4 += var8;
							var3 += var7;
						}
					}

					rasterizer_shadow_scanline(var0, var3, 0, var6 >> 16, var5 >> 16);
					var5 += var9;
					var6 += var10;
					var3 += var7;
				}
			}
		} else {
			var4 = var6 <<= 16;
			if (var3 < 0) {
				var4 -= var9 * var3;
				var6 -= var10 * var3;
				var3 = 0;
			}

			var5 <<= 16;
			if (var2 < 0) {
				var5 -= var8 * var2;
				var2 = 0;
			}

			if (var9 < var10) {
				var1 -= var2;
				var2 -= var3;
				var3 *= var7;

				while (true) {
					--var2;
					if (var2 < 0) {
						while (true) {
							--var1;
							if (var1 < 0) {
								return;
							}

							rasterizer_shadow_scanline(var0, var3, 0, var5 >> 16, var6 >> 16);
							var5 += var8;
							var6 += var10;
							var3 += var7;
						}
					}

					rasterizer_shadow_scanline(var0, var3, 0, var4 >> 16, var6 >> 16);
					var4 += var9;
					var6 += var10;
					var3 += var7;
				}
			} else {
				var1 -= var2;
				var2 -= var3;
				var3 *= var7;

				while (true) {
					--var2;
					if (var2 < 0) {
						while (true) {
							--var1;
							if (var1 < 0) {
								return;
							}

							rasterizer_shadow_scanline(var0, var3, 0, var6 >> 16, var5 >> 16);
							var5 += var8;
							var6 += var10;
							var3 += var7;
						}
					}

					rasterizer_shadow_scanline(var0, var3, 0, var6 >> 16, var4 >> 16);
					var4 += var9;
					var6 += var10;
					var3 += var7;
				}
			}
		}
	}

	static final void rasterizer_shadow_scanline(byte[] var0, int var1, int var2, int var3, int var4) {
		if (var3 < var4) {
			var1 += var3;
			var2 = var4 - var3 >> 2;
			while (true) {
				--var2;
				if (var2 < 0) {
					var2 = var4 - var3 & 3;

					while (true) {
						--var2;
						if (var2 < 0) {
							return;
						}

						var0[var1++] = 1;
					}
				}
				var0[var1++] = 1;
				var0[var1++] = 1;
				var0[var1++] = 1;
				var0[var1++] = 1;
			}
		}
	}

	public static short[] copy(short[] var0, int var1) {
		short[] var2 = new short[var1];
		ArrayUtils.copy(var0, 0, var2, 0, var1);
		return var2;
	}

	public static float[] copy(float[] var0, int var1) {
		float[] var2 = new float[var1];
		ArrayUtils.copy(var0, 0, var2, 0, var1);
		return var2;
	}

	@Override
	public boolean animate_start() {
		if (vertices_by_label == null) {
			return false;
		} else {
			for (int vertex = 0; vertex < num_vertices; ++vertex) {
				vertices_x[vertex] <<= 4;
				vertices_y[vertex] <<= 4;
				vertices_z[vertex] <<= 4;
			}
			origin_x = 0;
			origin_y = 0;
			origin_z = 0;
			return true;
		}
	}

	@Override
	public void animate_end() {
		for (int vertex = 0; vertex < num_vertices; ++vertex) {
			vertices_x[vertex] = vertices_x[vertex] + 7 >> 4;
			vertices_y[vertex] = vertices_y[vertex] + 7 >> 4;
			vertices_z[vertex] = vertices_z[vertex] + 7 >> 4;
		}
		bounding_box.is_clean = false;
		positions_attribute.is_clean = false;
	}

	@Override
	public void animate_transform(int var1, int[] var2, int var3, int var4, int var5, boolean var6) {
		int var7 = var2.length;
		int var13;
		if (var1 == 0) {
			var3 <<= 4;
			var4 <<= 4;
			var5 <<= 4;
			int var8 = 0;
			origin_x = 0;
			origin_y = 0;
			origin_z = 0;
			for (int var9 = 0; var9 < var7; ++var9) {
				int var20 = var2[var9];
				if (var20 < vertices_by_label.length) {
					int[] var21 = vertices_by_label[var20];
					for (int element : var21) {
						var13 = element;
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
			if (var1 == 1) {
				var3 <<= 4;
				var4 <<= 4;
				var5 <<= 4;
				for (int var8 = 0; var8 < var7; ++var8) {
					int var9 = var2[var8];
					if (var9 < vertices_by_label.length) {
						var10 = vertices_by_label[var9];
						for (int element : var10) {
							int var12 = element;
							vertices_x[var12] += var3;
							vertices_y[var12] += var4;
							vertices_z[var12] += var5;
						}
					}
				}
			} else {
				int var14;
				int var15;
				int var16;
				if (var1 == 2) {
					for (int var8 = 0; var8 < var7; ++var8) {
						int var9 = var2[var8];
						if (var9 < vertices_by_label.length) {
							var10 = vertices_by_label[var9];
							for (int element : var10) {
								int var12 = element;
								vertices_x[var12] -= origin_x;
								vertices_y[var12] -= origin_y;
								vertices_z[var12] -= origin_z;
								if (var5 != 0) {
									var13 = Rasterizer.SINE_SMOOTH[var5];
									var14 = Rasterizer.COSINE_SMOOTH[var5];
									var15 = vertices_y[var12] * var13 + vertices_x[var12] * var14 + 16383 >> 14;
									vertices_y[var12] = vertices_y[var12] * var14 - vertices_x[var12] * var13 + 16383 >> 14;
									vertices_x[var12] = var15;
								}

								if (var3 != 0) {
									var13 = Rasterizer.SINE_SMOOTH[var3];
									var14 = Rasterizer.COSINE_SMOOTH[var3];
									var15 = vertices_y[var12] * var14 - vertices_z[var12] * var13 + 16383 >> 14;
									vertices_z[var12] = vertices_y[var12] * var13 + vertices_z[var12] * var14 + 16383 >> 14;
									vertices_y[var12] = var15;
								}

								if (var4 != 0) {
									var13 = Rasterizer.SINE_SMOOTH[var4];
									var14 = Rasterizer.COSINE_SMOOTH[var4];
									var15 = vertices_z[var12] * var13 + vertices_x[var12] * var14 + 16383 >> 14;
									vertices_z[var12] = vertices_z[var12] * var14 - vertices_x[var12] * var13 + 16383 >> 14;
									vertices_x[var12] = var15;
								}

								vertices_x[var12] += origin_x;
								vertices_y[var12] += origin_y;
								vertices_z[var12] += origin_z;
							}
						}
					}

					if (var6 && normals_x != null) {
						for (int var8 = 0; var8 < var7; ++var8) {
							int var9 = var2[var8];
							if (var9 < vertices_by_label.length) {
								var10 = vertices_by_label[var9];
								for (int var12 : var10) {
									var13 = shared_vertices[var12];
									var14 = shared_vertices[var12 + 1];
									for (var15 = var13; var15 < var14; ++var15) {
										var16 = vertices_renderlist[var15] - 1;
										if (var16 == -1) {
											break;
										}
										if (var5 != 0) {
											int var17 = Rasterizer.SINE_SMOOTH[var5];
											int var18 = Rasterizer.COSINE_SMOOTH[var5];
											int var19 = normals_y[var16] * var17 + normals_x[var16] * var18 + 16383 >> 14;
											normals_y[var16] = (short) (normals_y[var16] * var18 - normals_x[var16] * var17 + 16383 >> 14);
											normals_x[var16] = (short) var19;
										}
										if (var3 != 0) {
											int var17 = Rasterizer.SINE_SMOOTH[var3];
											int var18 = Rasterizer.COSINE_SMOOTH[var3];
											int var19 = normals_y[var16] * var18 - normals_z[var16] * var17 + 16383 >> 14;
											normals_z[var16] = (short) (normals_y[var16] * var17 + normals_z[var16] * var18 + 16383 >> 14);
											normals_y[var16] = (short) var19;
										}
										if (var4 != 0) {
											int var17 = Rasterizer.SINE_SMOOTH[var4];
											int var18 = Rasterizer.COSINE_SMOOTH[var4];
											int var19 = normals_z[var16] * var17 + normals_x[var16] * var18 + 16383 >> 14;
											normals_z[var16] = (short) (normals_z[var16] * var18 - normals_x[var16] * var17 + 16383 >> 14);
											normals_x[var16] = (short) var19;
										}
									}
								}
							}
						}
						if (normals_attribute != null) {
							normals_attribute.is_clean = false;
						}
					}
				} else if (var1 == 3) {
					for (int var8 = 0; var8 < var7; ++var8) {
						int var9 = var2[var8];
						if (var9 < vertices_by_label.length) {
							var10 = vertices_by_label[var9];
							for (int element : var10) {
								int var12 = element;
								vertices_x[var12] -= origin_x;
								vertices_y[var12] -= origin_y;
								vertices_z[var12] -= origin_z;
								vertices_x[var12] = vertices_x[var12] * var3 >> 7;
								vertices_y[var12] = vertices_y[var12] * var4 >> 7;
								vertices_z[var12] = vertices_z[var12] * var5 >> 7;
								vertices_x[var12] += origin_x;
								vertices_y[var12] += origin_y;
								vertices_z[var12] += origin_z;
							}
						}
					}

				} else if (var1 == 5) {
					if (faces_by_label != null && faces_alpha != null) {
						for (int var8 = 0; var8 < var7; ++var8) {
							int var9 = var2[var8];
							if (var9 < faces_by_label.length) {
								var10 = faces_by_label[var9];
								for (int element : var10) {
									int var12 = element;
									var13 = (faces_alpha[var12] & 255) + var3 * 8;
									if (var13 < 0) {
										var13 = 0;
									} else if (var13 > 255) {
										var13 = 255;
									}
									faces_alpha[var12] = (byte) var13;
								}
								if (var10.length > 0) {
									colours_attribute.is_clean = false;
								}
							}
						}
					}

				} else if (var1 == 7) {
					if (faces_by_label != null) {
						for (int var8 = 0; var8 < var7; ++var8) {
							int var9 = var2[var8];
							if (var9 < faces_by_label.length) {
								var10 = faces_by_label[var9];
								for (int element : var10) {
									int var12 = element;
									var13 = faces_colour[var12] & 0xffff;
									var14 = var13 >> 10 & 63;
									var15 = var13 >> 7 & 7;
									var16 = var13 & 127;
									var14 = var14 + var3 & 63;
									var15 += var4 / 4;
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
								if (var10.length > 0) {
									colours_attribute.is_clean = false;
								}
							}
						}
					}

				}
			}
		}
	}

	@Override
	public void animate_partialtransform(int opcode, int[] var2, int var3, int var4, int var5, boolean var6, int var7, int[] var8) {
		int var9 = var2.length;
		if (opcode == 0) {
			var3 <<= 4;
			var4 <<= 4;
			var5 <<= 4;
			int var10 = 0;
			origin_x = 0;
			origin_y = 0;
			origin_z = 0;
			for (int var11 = 0; var11 < var9; ++var11) {
				int var47 = var2[var11];
				if (var47 < vertices_by_label.length) {
					int[] var48 = vertices_by_label[var47];
					for (int var15 : var48) {
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
				origin_attached = true;
			} else {
				origin_x = var3;
				origin_y = var4;
				origin_z = var5;
			}
		} else {
			if (opcode == 1) {
				if (var8 != null) {
					int var10 = var8[0] * var3 + var8[1] * var4 + var8[2] * var5 + 8192 >> 14;
					int var11 = var8[3] * var3 + var8[4] * var4 + var8[5] * var5 + 8192 >> 14;
					int var47 = var8[6] * var3 + var8[7] * var4 + var8[8] * var5 + 8192 >> 14;
					var3 = var10;
					var4 = var11;
					var5 = var47;
				}

				var3 <<= 4;
				var4 <<= 4;
				var5 <<= 4;
				for (int var10 = 0; var10 < var9; ++var10) {
					int var11 = var2[var10];
					if (var11 < vertices_by_label.length) {
						int[] var12 = vertices_by_label[var11];
						for (int element : var12) {
							int var14 = element;
							if (vertices_submeshes == null || (var7 & vertices_submeshes[var14]) != 0) {
								vertices_x[var14] += var3;
								vertices_y[var14] += var4;
								vertices_z[var14] += var5;
							}
						}
					}
				}

			} else {
				if (opcode == 2) {
					if (var8 != null) {
						int var10 = var8[9] << 4;
						int var11 = var8[10] << 4;
						int var47 = var8[11] << 4;
						int var13 = var8[12] << 4;
						int var14 = var8[13] << 4;
						int var15 = var8[14] << 4;
						if (origin_attached) {
							int var16 = var8[0] * origin_x + var8[3] * origin_y + var8[6] * origin_z + 8192 >> 14;
							int var17 = var8[1] * origin_x + var8[4] * origin_y + var8[7] * origin_z + 8192 >> 14;
							int var18 = var8[2] * origin_x + var8[5] * origin_y + var8[8] * origin_z + 8192 >> 14;
							var16 += var13;
							var17 += var14;
							var18 += var15;
							origin_x = var16;
							origin_y = var17;
							origin_z = var18;
							origin_attached = false;
						}

						int[] var49 = new int[9];
						int var17 = Rasterizer.COSINE_SMOOTH[var3];
						int var18 = Rasterizer.SINE_SMOOTH[var3];
						int var19 = Rasterizer.COSINE_SMOOTH[var4];
						int var20 = Rasterizer.SINE_SMOOTH[var4];
						int var21 = Rasterizer.COSINE_SMOOTH[var5];
						int var22 = Rasterizer.SINE_SMOOTH[var5];
						int var23 = var18 * var21 + 8192 >> 14;
						int var24 = var18 * var22 + 8192 >> 14;
						var49[0] = var19 * var21 + var20 * var24 + 8192 >> 14;
						var49[1] = -var19 * var22 + var20 * var23 + 8192 >> 14;
						var49[2] = var20 * var17 + 8192 >> 14;
						var49[3] = var17 * var22 + 8192 >> 14;
						var49[4] = var17 * var21 + 8192 >> 14;
						var49[5] = -var18;
						var49[6] = -var20 * var21 + var19 * var24 + 8192 >> 14;
						var49[7] = var20 * var22 + var19 * var23 + 8192 >> 14;
						var49[8] = var19 * var17 + 8192 >> 14;
						int var50 = var49[0] * -origin_x + var49[1] * -origin_y + var49[2] * -origin_z + 8192 >> 14;
						int var26 = var49[3] * -origin_x + var49[4] * -origin_y + var49[5] * -origin_z + 8192 >> 14;
						int var27 = var49[6] * -origin_x + var49[7] * -origin_y + var49[8] * -origin_z + 8192 >> 14;
						int var28 = var50 + origin_x;
						int var51 = var26 + origin_y;
						int var30 = var27 + origin_z;
						int[] var52 = new int[9];
						for (int var32 = 0; var32 < 3; ++var32) {
							for (int var33 = 0; var33 < 3; ++var33) {
								int var34 = 0;
								for (int var53 = 0; var53 < 3; ++var53) {
									var34 += var49[var32 * 3 + var53] * var8[var33 * 3 + var53];
								}
								var52[var32 * 3 + var33] = var34 + 8192 >> 14;
							}
						}
						int var32 = var49[0] * var13 + var49[1] * var14 + var49[2] * var15 + 8192 >> 14;
						int var33 = var49[3] * var13 + var49[4] * var14 + var49[5] * var15 + 8192 >> 14;
						int var34 = var49[6] * var13 + var49[7] * var14 + var49[8] * var15 + 8192 >> 14;
						var32 += var28;
						var33 += var51;
						var34 += var30;
						int[] var35 = new int[9];
						for (int var36 = 0; var36 < 3; ++var36) {
							for (int var37 = 0; var37 < 3; ++var37) {
								int var38 = 0;
								for (int var39 = 0; var39 < 3; ++var39) {
									var38 += var8[var36 * 3 + var39] * var52[var37 + var39 * 3];
								}
								var35[var36 * 3 + var37] = var38 + 8192 >> 15;
							}
						}

						int var36 = var8[0] * var32 + var8[1] * var33 + var8[2] * var34 + 8192 >> 14;
						int var37 = var8[3] * var32 + var8[4] * var33 + var8[5] * var34 + 8192 >> 14;
						int var38 = var8[6] * var32 + var8[7] * var33 + var8[8] * var34 + 8192 >> 14;
						var36 += var10;
						var37 += var11;
						var38 += var47;
						for (int var39 = 0; var39 < var9; ++var39) {
							int var40 = var2[var39];
							if (var40 < vertices_by_label.length) {
								int[] var41 = vertices_by_label[var40];
								for (int element : var41) {
									int var43 = element;
									if (vertices_submeshes == null || (var7 & vertices_submeshes[var43]) != 0) {
										int var44 = var35[0] * vertices_x[var43] + var35[1] * vertices_y[var43] + var35[2] * vertices_z[var43] + 8192 >> 14;
										int var45 = var35[3] * vertices_x[var43] + var35[4] * vertices_y[var43] + var35[5] * vertices_z[var43] + 8192 >> 14;
										int var46 = var35[6] * vertices_x[var43] + var35[7] * vertices_y[var43] + var35[8] * vertices_z[var43] + 8192 >> 14;
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
						for (int var10 = 0; var10 < var9; ++var10) {
							int var11 = var2[var10];
							if (var11 < vertices_by_label.length) {
								int[] var12 = vertices_by_label[var11];
								for (int element : var12) {
									int var14 = element;
									if (vertices_submeshes == null || (var7 & vertices_submeshes[var14]) != 0) {
										vertices_x[var14] -= origin_x;
										vertices_y[var14] -= origin_y;
										vertices_z[var14] -= origin_z;
										if (var5 != 0) {
											int var15 = Rasterizer.SINE_SMOOTH[var5];
											int var16 = Rasterizer.COSINE_SMOOTH[var5];
											int var17 = vertices_y[var14] * var15 + vertices_x[var14] * var16 + 16383 >> 14;
											vertices_y[var14] = vertices_y[var14] * var16 - vertices_x[var14] * var15 + 16383 >> 14;
											vertices_x[var14] = var17;
										}

										if (var3 != 0) {
											int var15 = Rasterizer.SINE_SMOOTH[var3];
											int var16 = Rasterizer.COSINE_SMOOTH[var3];
											int var17 = vertices_y[var14] * var16 - vertices_z[var14] * var15 + 16383 >> 14;
											vertices_z[var14] = vertices_y[var14] * var15 + vertices_z[var14] * var16 + 16383 >> 14;
											vertices_y[var14] = var17;
										}

										if (var4 != 0) {
											int var15 = Rasterizer.SINE_SMOOTH[var4];
											int var16 = Rasterizer.COSINE_SMOOTH[var4];
											int var17 = vertices_z[var14] * var15 + vertices_x[var14] * var16 + 16383 >> 14;
											vertices_z[var14] = vertices_z[var14] * var16 - vertices_x[var14] * var15 + 16383 >> 14;
											vertices_x[var14] = var17;
										}

										vertices_x[var14] += origin_x;
										vertices_y[var14] += origin_y;
										vertices_z[var14] += origin_z;
									}
								}
							}
						}

						if (var6 && normals_x != null) {
							for (int var10 = 0; var10 < var9; ++var10) {
								int var11 = var2[var10];
								if (var11 < vertices_by_label.length) {
									int[] var12 = vertices_by_label[var11];
									for (int var14 : var12) {
										if (vertices_submeshes == null || (var7 & vertices_submeshes[var14]) != 0) {
											int var15 = shared_vertices[var14];
											int var16 = shared_vertices[var14 + 1];
											for (int var17 = var15; var17 < var16; ++var17) {
												int var18 = vertices_renderlist[var17] - 1;
												if (var18 == -1) {
													break;
												}
												if (var5 != 0) {
													int var19 = Rasterizer.SINE_SMOOTH[var5];
													int var20 = Rasterizer.COSINE_SMOOTH[var5];
													int var21 = normals_y[var18] * var19 + normals_x[var18] * var20 + 16383 >> 14;
													normals_y[var18] = (short) (normals_y[var18] * var20 - normals_x[var18] * var19 + 16383 >> 14);
													normals_x[var18] = (short) var21;
												}

												if (var3 != 0) {
													int var19 = Rasterizer.SINE_SMOOTH[var3];
													int var20 = Rasterizer.COSINE_SMOOTH[var3];
													int var21 = normals_y[var18] * var20 - normals_z[var18] * var19 + 16383 >> 14;
													normals_z[var18] = (short) (normals_y[var18] * var19 + normals_z[var18] * var20 + 16383 >> 14);
													normals_y[var18] = (short) var21;
												}

												if (var4 != 0) {
													int var19 = Rasterizer.SINE_SMOOTH[var4];
													int var20 = Rasterizer.COSINE_SMOOTH[var4];
													int var21 = normals_z[var18] * var19 + normals_x[var18] * var20 + 16383 >> 14;
													normals_z[var18] = (short) (normals_z[var18] * var20 - normals_x[var18] * var19 + 16383 >> 14);
													normals_x[var18] = (short) var21;
												}
											}
										}
									}
								}
							}

							if (normals_attribute != null) {
								normals_attribute.is_clean = false;
							}
						}
					}

				} else if (opcode == 3) {
					if (var8 != null) {
						int var10 = var8[9] << 4;
						int var11 = var8[10] << 4;
						int var47 = var8[11] << 4;
						int var13 = var8[12] << 4;
						int var14 = var8[13] << 4;
						int var15 = var8[14] << 4;
						if (origin_attached) {
							int var16 = var8[0] * origin_x + var8[3] * origin_y + var8[6] * origin_z + 8192 >> 14;
							int var17 = var8[1] * origin_x + var8[4] * origin_y + var8[7] * origin_z + 8192 >> 14;
							int var18 = var8[2] * origin_x + var8[5] * origin_y + var8[8] * origin_z + 8192 >> 14;
							var16 += var13;
							var17 += var14;
							var18 += var15;
							origin_x = var16;
							origin_y = var17;
							origin_z = var18;
							origin_attached = false;
						}

						int var16 = var3 << 15 >> 7;
						int var17 = var4 << 15 >> 7;
						int var18 = var5 << 15 >> 7;
						int var19 = var16 * -origin_x + 8192 >> 14;
						int var20 = var17 * -origin_y + 8192 >> 14;
						int var21 = var18 * -origin_z + 8192 >> 14;
						int var22 = var19 + origin_x;
						int var23 = var20 + origin_y;
						int var24 = var21 + origin_z;
						int[] var25 = new int[] { var16 * var8[0] + 8192 >> 14, var16 * var8[3] + 8192 >> 14, var16 * var8[6] + 8192 >> 14, var17 * var8[1] + 8192 >> 14, var17 * var8[4] + 8192 >> 14, var17 * var8[7] + 8192 >> 14, var18 * var8[2] + 8192 >> 14, var18 * var8[5] + 8192 >> 14, var18 * var8[8] + 8192 >> 14 };
						int var26 = var16 * var13 + 8192 >> 14;
						int var27 = var17 * var14 + 8192 >> 14;
						int var28 = var18 * var15 + 8192 >> 14;
						var26 += var22;
						var27 += var23;
						var28 += var24;
						int[] var29 = new int[9];
						for (int var30 = 0; var30 < 3; ++var30) {
							for (int var31 = 0; var31 < 3; ++var31) {
								int var32 = 0;
								for (int var33 = 0; var33 < 3; ++var33) {
									var32 += var8[var30 * 3 + var33] * var25[var31 + var33 * 3];
								}
								var29[var30 * 3 + var31] = var32 + 8192 >> 14;
							}
						}

						int var30 = var8[0] * var26 + var8[1] * var27 + var8[2] * var28 + 8192 >> 14;
						int var31 = var8[3] * var26 + var8[4] * var27 + var8[5] * var28 + 8192 >> 14;
						int var32 = var8[6] * var26 + var8[7] * var27 + var8[8] * var28 + 8192 >> 14;
						var30 += var10;
						var31 += var11;
						var32 += var47;
						for (int var33 = 0; var33 < var9; ++var33) {
							int var34 = var2[var33];
							if (var34 < vertices_by_label.length) {
								int[] var35 = vertices_by_label[var34];
								for (int element : var35) {
									int var37 = element;
									if (vertices_submeshes == null || (var7 & vertices_submeshes[var37]) != 0) {
										int var38 = var29[0] * vertices_x[var37] + var29[1] * vertices_y[var37] + var29[2] * vertices_z[var37] + 8192 >> 14;
										int var39 = var29[3] * vertices_x[var37] + var29[4] * vertices_y[var37] + var29[5] * vertices_z[var37] + 8192 >> 14;
										int var40 = var29[6] * vertices_x[var37] + var29[7] * vertices_y[var37] + var29[8] * vertices_z[var37] + 8192 >> 14;
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
						for (int var10 = 0; var10 < var9; ++var10) {
							int var11 = var2[var10];
							if (var11 < vertices_by_label.length) {
								int[] var12 = vertices_by_label[var11];
								for (int element : var12) {
									int var14 = element;
									if (vertices_submeshes == null || (var7 & vertices_submeshes[var14]) != 0) {
										vertices_x[var14] -= origin_x;
										vertices_y[var14] -= origin_y;
										vertices_z[var14] -= origin_z;
										vertices_x[var14] = vertices_x[var14] * var3 >> 7;
										vertices_y[var14] = vertices_y[var14] * var4 >> 7;
										vertices_z[var14] = vertices_z[var14] * var5 >> 7;
										vertices_x[var14] += origin_x;
										vertices_y[var14] += origin_y;
										vertices_z[var14] += origin_z;
									}
								}
							}
						}
					}

				} else if (opcode == 5) {
					if (faces_by_label != null && faces_alpha != null) {
						for (int var10 = 0; var10 < var9; ++var10) {
							int var11 = var2[var10];
							if (var11 < faces_by_label.length) {
								int[] var12 = faces_by_label[var11];
								for (int element : var12) {
									int var14 = element;
									if (faces_submeshes == null || (var7 & faces_submeshes[var14]) != 0) {
										int var15 = (faces_alpha[var14] & 255) + var3 * 8;
										if (var15 < 0) {
											var15 = 0;
										} else if (var15 > 255) {
											var15 = 255;
										}
										faces_alpha[var14] = (byte) var15;
									}
								}
								if (var12.length > 0) {
									colours_attribute.is_clean = false;
								}
							}
						}
					}
				} else if (opcode == 7) {
					if (faces_by_label != null) {
						for (int var10 = 0; var10 < var9; ++var10) {
							int var11 = var2[var10];
							if (var11 < faces_by_label.length) {
								int[] var12 = faces_by_label[var11];
								for (int element : var12) {
									int var14 = element;
									if (faces_submeshes == null || (var7 & faces_submeshes[var14]) != 0) {
										int var15 = faces_colour[var14] & 0xffff;
										int var16 = var15 >> 10 & 63;
										int var17 = var15 >> 7 & 7;
										int var18 = var15 & 127;
										var16 = var16 + var3 & 63;
										var17 += var4 / 4;
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
								if (var12.length > 0) {
									colours_attribute.is_clean = false;
								}
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void animate_shadowtransform(int var1, int var2, int var3, int var4) {
		if (var1 == 0) {
			int var5 = 0;
			origin_x = 0;
			origin_y = 0;
			origin_z = 0;
			for (int var6 = 0; var6 < num_vertices; ++var6) {
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
			for (int var5 = 0; var5 < num_vertices; ++var5) {
				vertices_x[var5] += var2;
				vertices_y[var5] += var3;
				vertices_z[var5] += var4;
			}
		} else {
			if (var1 == 2) {
				for (int var5 = 0; var5 < num_vertices; ++var5) {
					vertices_x[var5] -= origin_x;
					vertices_y[var5] -= origin_y;
					vertices_z[var5] -= origin_z;
					if (var4 != 0) {
						int var6 = Rasterizer.SINE_SMOOTH[var4];
						int var7 = Rasterizer.COSINE_SMOOTH[var4];
						int var8 = vertices_y[var5] * var6 + vertices_x[var5] * var7 + 16383 >> 14;
						vertices_y[var5] = vertices_y[var5] * var7 - vertices_x[var5] * var6 + 16383 >> 14;
						vertices_x[var5] = var8;
					}

					if (var2 != 0) {
						int var6 = Rasterizer.SINE_SMOOTH[var2];
						int var7 = Rasterizer.COSINE_SMOOTH[var2];
						int var8 = vertices_y[var5] * var7 - vertices_z[var5] * var6 + 16383 >> 14;
						vertices_z[var5] = vertices_y[var5] * var6 + vertices_z[var5] * var7 + 16383 >> 14;
						vertices_y[var5] = var8;
					}

					if (var3 != 0) {
						int var6 = Rasterizer.SINE_SMOOTH[var3];
						int var7 = Rasterizer.COSINE_SMOOTH[var3];
						int var8 = vertices_z[var5] * var6 + vertices_x[var5] * var7 + 16383 >> 14;
						vertices_z[var5] = vertices_z[var5] * var7 - vertices_x[var5] * var6 + 16383 >> 14;
						vertices_x[var5] = var8;
					}
					vertices_x[var5] += origin_x;
					vertices_y[var5] += origin_y;
					vertices_z[var5] += origin_z;
				}

			} else if (var1 != 3) {
				if (var1 == 5) {
					for (int var5 = 0; var5 < num_rendering_faces; ++var5) {
						int var6 = (faces_alpha[var5] & 255) + var2 * 8;
						if (var6 < 0) {
							var6 = 0;
						} else if (var6 > 255) {
							var6 = 255;
						}
						faces_alpha[var5] = (byte) var6;
					}
					colours_attribute.is_clean = false;
				} else if (var1 == 7) {
					for (int var5 = 0; var5 < num_rendering_faces; ++var5) {
						int var6 = faces_colour[var5] & 0xffff;
						int var7 = var6 >> 10 & 63;
						int var8 = var6 >> 7 & 7;
						int var9 = var6 & 127;
						var7 = var7 + var2 & 63;
						var8 += var3 / 4;
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
					colours_attribute.is_clean = false;
				}
			} else {
				for (int var5 = 0; var5 < num_vertices; ++var5) {
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
		return copy(reference_alphas, reference_colors, reference_normals, copy_three_target, copy_three_model);
	}

	/*
	 * (non-Javadoc)
	 * @see com.jagex.SceneNode#clean_memory()
	 */
	@Override
	public void clean_memory() {
		// if (!positions_referenced) {
		// positions_attribute.buffer.destroy();
		// positions_attribute.buffer = null;
		// }
	}

	public static void method1929() {
		copy_one_model = new OpenGLModel();
		copy_one_target = new OpenGLModel();
		copy_two_model = new OpenGLModel();
		copy_two_target = new OpenGLModel();
		copy_three_model = new OpenGLModel();
		copy_three_target = new OpenGLModel();
	}

	public static final void method1755() {
		if (GLManager.gl.hasExtension("GL_ARB_point_parameters")) {
			float[] var1 = new float[] { 1.0F, 0.0F, 5.0E-4F };
			// glPointParameterfvARB(GL_POINT_DISTANCE_ATTENUATION, var1, 0);
			float[] var2 = new float[1];
			glGetFloatv(GL_POINT_SIZE_MAX, var2, 0);
			float var3 = var2[0];
			if (var3 > 1024.0F) {
				var3 = 1024.0F;
			}
			// glPointParameterfARB(GL_POINT_SIZE_MIN, 1.0F);
			// glPointParameterfARB(GL_POINT_SIZE_MAX, var3);
		}
	}


}
