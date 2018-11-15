
package com.jagex.graphics.runetek4.opengl.terrain;

import static jaggl.GLConstants.GL_FLOAT;
import static jaggl.GLConstants.GL_TEXTURE0;
import static jaggl.GLConstants.GL_TRIANGLES;
import static jaggl.GLConstants.GL_UNSIGNED_BYTE;
import static jaggl.OpenGL.glClientActiveTexture;
import static jaggl.OpenGL.glTexCoordPointer;

import com.jagex.Ground;
import com.jagex.HashTable;
import com.jagex.IntegerNode;
import com.jagex.Linkable;
import com.jagex.MapLoader;
import com.jagex.Packet;
import com.jagex.core.tools.MathTools;
import com.jagex.game.runetek4.clientoptions.ClientOptions;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.GLState;
import com.jagex.graphics.runetek4.opengl.buffer.OpenGLIndexBuffer;
import com.jagex.graphics.runetek4.opengl.buffer.OpenGLVertexAttribute;
import com.jagex.graphics.runetek4.opengl.buffer.VertexBuffer;
import com.jagex.graphics.runetek4.opengl.effects.WaterDepthEffect;

public class OpenGLTile extends Linkable {

	private static Packet abovewater_stream;
	private static Packet underwater_stream;
	private static Packet bake_stream;
	public int total_vertices;
	public int num_vertices;
	public int total_faces;
	public int textureid;
	public int underwater_colour;
	public boolean has_underwater;
	private float[] verices_waterdepth;
	private boolean has_waterdepth;
	private int[] vertices_colour;
	private int[] faces_gridy;
	private int[] faces_gridx;
	private int[] vertices_z;
	private HashTable vertices_cache;
	private float[] normals_z;
	private int num_underwater_faces;
	private int[][] faces_indices;
	private int[] vertices_y;
	private int num_abovewater_faces;
	private int[][] faces_underwater_indices;
	private float[] normals_y;
	private int num_faces;
	private int[] faces_gridlevel;
	private float[] normals_x;
	private boolean[] faces_underwater;
	private int[] vertices_x;
	private float texturescale;
	private VertexBuffer vertices_buffer;
	private OpenGLVertexAttribute positions_buffer;
	private OpenGLVertexAttribute normals_buffer;
	private OpenGLVertexAttribute colors_buffer;
	private OpenGLVertexAttribute texcoords_buffer;
	private OpenGLIndexBuffer indices_buffer;

	public OpenGLTile(int textureid, float texturescale, boolean has_underwater, boolean has_waterdepth, int underwater_colour) {
		this.textureid = textureid;
		this.texturescale = texturescale;
		this.has_underwater = has_underwater;
		this.has_waterdepth = has_waterdepth;
		this.underwater_colour = underwater_colour;
	}

	public void initialise_tables() {
		vertices_x = new int[total_vertices];
		vertices_y = new int[total_vertices];
		vertices_z = new int[total_vertices];
		if (has_waterdepth) {
			verices_waterdepth = new float[total_vertices];
		}
		vertices_colour = new int[total_vertices];
		normals_x = new float[total_vertices];
		normals_y = new float[total_vertices];
		normals_z = new float[total_vertices];
		faces_gridx = new int[total_faces];
		faces_gridy = new int[total_faces];
		faces_gridlevel = new int[total_faces];
		faces_indices = new int[total_faces][];
		vertices_cache = new HashTable(MathTools.get_greater_pow2(total_vertices));
		if (has_underwater) {
			faces_underwater_indices = new int[total_faces][];
			faces_underwater = new boolean[total_faces];
		}
	}

	public void bake() {
		final int vertex_size = has_waterdepth ? 40 : 36;
		if (bake_stream == null || bake_stream.byteBuffer.length < vertex_size * num_vertices) {
			bake_stream = new Packet(vertex_size * num_vertices);
		} else {
			bake_stream.index = 0;
		}
		for (int var2 = 0; var2 < num_vertices; ++var2) {
			if (GLManager.big_endianess) {
				bake_stream.pf(vertices_x[var2]);
				bake_stream.pf(vertices_y[var2]);
				bake_stream.pf(vertices_z[var2]);
				bake_stream.p4(vertices_colour[var2]);
				bake_stream.pf(normals_x[var2]);
				bake_stream.pf(normals_y[var2]);
				bake_stream.pf(normals_z[var2]);
				bake_stream.pf(vertices_x[var2] / texturescale);
				bake_stream.pf(vertices_z[var2] / texturescale);
				if (has_waterdepth) {
					bake_stream.pf(verices_waterdepth[var2]);
				}
			} else {
				bake_stream.ipf(vertices_x[var2]);
				bake_stream.ipf(vertices_y[var2]);
				bake_stream.ipf(vertices_z[var2]);
				bake_stream.p4(vertices_colour[var2]);
				bake_stream.ipf(normals_x[var2]);
				bake_stream.ipf(normals_y[var2]);
				bake_stream.ipf(normals_z[var2]);
				bake_stream.ipf(vertices_x[var2] / texturescale);
				bake_stream.ipf(vertices_z[var2] / texturescale);
				if (has_waterdepth) {
					bake_stream.ipf(verices_waterdepth[var2]);
				}
			}
		}
		if (vertices_buffer == null) {
			vertices_buffer = GLManager.create_vertex_buffer(vertex_size, bake_stream.byteBuffer, bake_stream.index, false);
			positions_buffer = new OpenGLVertexAttribute(vertices_buffer, GL_FLOAT, 3, 0);
			colors_buffer = new OpenGLVertexAttribute(vertices_buffer, GL_UNSIGNED_BYTE, 4, 12);
			normals_buffer = new OpenGLVertexAttribute(vertices_buffer, GL_FLOAT, 3, 16);
			texcoords_buffer = new OpenGLVertexAttribute(vertices_buffer, GL_FLOAT, 2, 28);
		} else {
			vertices_buffer.upload(vertex_size, bake_stream.byteBuffer, bake_stream.index);
		}
		indices_buffer = new OpenGLIndexBuffer(null, 1);
		vertices_x = null;
		vertices_y = null;
		vertices_z = null;
		vertices_colour = null;
		normals_x = null;
		normals_y = null;
		normals_z = null;
		vertices_cache = null;
		verices_waterdepth = null;
	}

	public void render(Ground[][][] grounds, float var2, boolean var3) {
		if (abovewater_stream != null && abovewater_stream.byteBuffer.length >= num_abovewater_faces * 4) {
			abovewater_stream.index = 0;
		} else {
			abovewater_stream = new Packet(num_abovewater_faces * 4);
		}
		if (underwater_stream != null && underwater_stream.byteBuffer.length >= num_underwater_faces * 4) {
			underwater_stream.index = 0;
		} else {
			underwater_stream = new Packet(num_underwater_faces * 4);
		}
		if (GLManager.big_endianess) {
			for (int var4 = 0; var4 < num_faces; ++var4) {
				Ground var5 = grounds[faces_gridlevel[var4]][faces_gridx[var4]][faces_gridy[var4]];
				if (var5 != null && var5.aBoolean2021) {
					int[] changed_indices = faces_indices[var4];
					Packet stream;
					if (has_underwater) {
						int[] indices = faces_underwater_indices[var4];
						if (indices != null) {
							for (int index : indices) {
								underwater_stream.p4(index);
							}
						}
						stream = faces_underwater[var4] ? underwater_stream : abovewater_stream;
					} else {
						stream = abovewater_stream;
					}
					for (int var12 = 1; var12 < changed_indices.length - 1; ++var12) {
						stream.p4(changed_indices[0]);
						stream.p4(changed_indices[var12]);
						stream.p4(changed_indices[var12 + 1]);
					}
				}
			}
		} else {
			for (int var4 = 0; var4 < num_faces; ++var4) {
				Ground var5 = grounds[faces_gridlevel[var4]][faces_gridx[var4]][faces_gridy[var4]];
				Packet stream;
				if (var5 != null && var5.aBoolean2021) {
					int[] changed_indices = faces_indices[var4];
					if (has_underwater) {
						int[] var8 = faces_underwater_indices[var4];
						if (var8 != null) {
							for (int element : var8) {
								underwater_stream.ip4(element);
							}
						}
						stream = faces_underwater[var4] ? underwater_stream : abovewater_stream;
					} else {
						stream = abovewater_stream;
					}
					for (int var12 = 1; var12 < changed_indices.length - 1; ++var12) {
						stream.ip4(changed_indices[0]);
						stream.ip4(changed_indices[var12]);
						stream.ip4(changed_indices[var12 + 1]);
					}
				}
			}
		}
		if (abovewater_stream.index != 0 || underwater_stream.index != 0) {
			if (textureid != -1 && !var3) {
				GLState.load_texture(textureid);
			} else {
				GLState.load_texture(-1);
			}
			int var11 = has_waterdepth ? 40 : 36;
			GLManager.load_vertices_array(positions_buffer, ClientOptions.clientoption_highdetails_lighting ? normals_buffer : null, colors_buffer, texcoords_buffer);
			if (has_waterdepth) {
				glClientActiveTexture(WaterDepthEffect.get_texture_id());
				glTexCoordPointer(1, 5126, var11, vertices_buffer.getAddress() + 36L);
				glClientActiveTexture(GL_TEXTURE0);
			}
			if (abovewater_stream != null && abovewater_stream.index != 0) {
				indices_buffer.upload(abovewater_stream.byteBuffer, abovewater_stream.index);
				GLState.update_depth_range(var2);
				GLManager.draw_index_buffer_int(indices_buffer, GL_TRIANGLES, 0, abovewater_stream.index / 4);
			}
			if (underwater_stream != null & underwater_stream.index != 0) {
				GLState.update_depth_range(var2 - 100.0F);
				GLState.disable_depthmask();
				indices_buffer.upload(underwater_stream.byteBuffer, underwater_stream.index);
				GLManager.draw_index_buffer_int(indices_buffer, GL_TRIANGLES, 0, underwater_stream.index / 4);
				GLState.enable_depthmask();
			}
		}
	}

	public int add_vertex(int position_x, int position_y, int position_z, float normal_x, float normal_y, float normal_z, int colour, float waterdepth) {
		long uid = 0L;
		if ((position_x & 127) == 0 || (position_z & 127) == 0) {
			uid = position_x + ((long) position_z << 16) + ((long) colour << 32);
			IntegerNode var11 = (IntegerNode) vertices_cache.get(uid);
			if (var11 != null) {
				if (position_y < vertices_y[var11.value]) {
					vertices_y[var11.value] = position_y;
				}

				return var11.value;
			}
		}
		vertices_x[num_vertices] = position_x;
		vertices_y[num_vertices] = position_y;
		vertices_z[num_vertices] = position_z;
		if (has_waterdepth) {
			verices_waterdepth[num_vertices] = waterdepth;
		}
		normals_x[num_vertices] = normal_x;
		normals_y[num_vertices] = normal_y;
		normals_z[num_vertices] = normal_z;
		vertices_colour[num_vertices] = colour;
		if (uid != 0L) {
			vertices_cache.put(uid, new IntegerNode(num_vertices));
		}
		return num_vertices++;
	}

	public int add_face(int grid_level, int grid_x, int grid_y, int[] indices, int[] underwater_indices, boolean underwater) {
		if (has_underwater) {
			faces_underwater_indices[num_faces] = underwater_indices;
			faces_underwater[num_faces] = underwater;
			if (underwater_indices != null) {
				num_underwater_faces += underwater_indices.length;
			}
			if (underwater) {
				num_underwater_faces += 3 * (indices.length - 2);
			} else {
				num_abovewater_faces += 3 * (indices.length - 2);
			}
		} else {
			num_abovewater_faces += 3 * (indices.length - 2);
		}

		faces_gridlevel[num_faces] = grid_level;
		faces_gridx[num_faces] = grid_x;
		faces_gridy[num_faces] = grid_y;
		faces_indices[num_faces] = indices;
		return num_faces++;
	}

	public static void add_vertices(OpenGLTile tile, int x, int y, byte rotation, int[] positions, float[][] normals_x, float[][] normals_y, float[][] normals_z, int north_colour, int south_colour, int west_colour, int east_colour, boolean solid_north, boolean solid_south, boolean solid_west, boolean solid_east, int vertex_size, boolean[] overworld_heights, int[][] underworld_heights, int var6, int var13, int[][] var17) {
		int north_rgba = (north_colour << 8) + (solid_north ? 255 : 0);
		int south_rgba = (south_colour << 8) + (solid_south ? 255 : 0);
		int west_rgba = (west_colour << 8) + (solid_west ? 255 : 0);
		int east_rgba = (east_colour << 8) + (solid_east ? 255 : 0);
		int[] vertices = new int[positions.length / vertex_size];
		for (int index = 0; index < vertices.length; index++) {
			int vertex_x = positions[index + index];
			int vertex_y = positions[index + index + 1];
			int[][] heights = underworld_heights != null && overworld_heights != null && overworld_heights[index] ? underworld_heights : var17;
			vertices[index] = add_vertex(tile, x, y, rotation, vertex_x, vertex_y, normals_x, normals_y, normals_z, heights, underworld_heights, north_rgba, south_rgba, west_rgba, east_rgba, var13, false);
		}
		tile.add_face(var6, x, y, vertices, null, false);
	}

	public static int add_vertex(OpenGLTile tile, int x, int y, int rotation, int vertex_x, int vertex_y, float[][] normals_x, float[][] normals_y, float[][] normals_z, int[][] heights, int[][] underworld_heights, int north_colour, int south_colour, int west_colour, int east_colour, float var1, boolean var11) {
		if (rotation == 1) {
			int temp = vertex_x;
			vertex_x = vertex_y;
			vertex_y = 128 - temp;
		} else if (rotation == 2) {
			vertex_x = 128 - vertex_x;
			vertex_y = 128 - vertex_y;
		} else if (rotation == 3) {
			int temp = vertex_x;
			vertex_x = 128 - vertex_y;
			vertex_y = temp;
		}
		float normal_x;
		float normal_y;
		float normal_z;
		int colour;
		if (vertex_x == 0 && vertex_y == 0) {
			normal_x = normals_x[x][y];
			normal_y = normals_y[x][y];
			normal_z = normals_z[x][y];
			colour = south_colour;
		} else if (vertex_x == 128 && vertex_y == 0) {
			colour = east_colour;
			normal_x = normals_x[x + 1][y];
			normal_y = normals_y[x + 1][y];
			normal_z = normals_z[x + 1][y];
		} else if (vertex_x == 128 && vertex_y == 128) {
			normal_x = normals_x[x + 1][y + 1];
			normal_y = normals_y[x + 1][y + 1];
			normal_z = normals_z[x + 1][y + 1];
			colour = west_colour;
		} else if (vertex_x == 0 && vertex_y == 128) {
			normal_x = normals_x[x][y + 1];
			normal_y = normals_y[x][y + 1];
			normal_z = normals_z[x][y + 1];
			colour = north_colour;
		} else {
			normal_x = normals_x[x][y];
			normal_y = normals_y[x][y];
			float x_factor = vertex_y / 128.0F;
			float y_factor = vertex_x / 128.0F;
			normal_x += (normals_x[x - -1][y] - normal_x) * y_factor;
			normal_y += (-normal_y + normals_y[x + 1][y]) * y_factor;
			float var24 = normals_x[x][y + 1];
			float var25 = normals_y[x][y + 1];
			var25 += (-var25 + normals_y[x + 1][y - -1]) * y_factor;
			normal_z = normals_z[x][y];
			normal_y += x_factor * (-normal_y + var25);
			float var26 = normals_z[x][y + 1];
			var24 += (-var24 + normals_x[x - -1][y + 1]) * y_factor;
			normal_x += x_factor * (-normal_x + var24);
			var26 += (-var26 + normals_z[x + 1][y - -1]) * y_factor;
			normal_z += (-normal_z + normals_z[x - -1][y]) * y_factor;
			normal_z += (-normal_z + var26) * x_factor;
			int south_east_colour = OpenGLTile.slerp(south_colour, east_colour, vertex_x);
			int north_west_colour = OpenGLTile.slerp(north_colour, west_colour, vertex_x);
			colour = OpenGLTile.slerp(south_east_colour, north_west_colour, vertex_y);
		}
		int position_z = vertex_y + (y << 7);
		int position_y = MapLoader.get_average_height(heights, x, y, vertex_x, vertex_y);
		int position_x = (x << 7) - -vertex_x;
		return tile.add_vertex(position_x, position_y, position_z, normal_x, normal_y, normal_z, !var11 ? colour : colour & -256, underworld_heights != null ? (position_y - MapLoader.get_average_height(underworld_heights, x, y, vertex_x, vertex_y)) / var1 : 0.0F);
	}

	public static int slerp(int first_colour, int second_colour, int factor) {
		if (first_colour == second_colour) {
			return first_colour;
		}
		int dst_alpha = 128 - factor;
		int var5 = factor * ((0xff00ff00 & second_colour) >>> 7) + dst_alpha * ((0xff00ff00 & first_colour) >>> 7) & 0xff00ff00;
		int var6 = dst_alpha * (0xff00ff & first_colour) + (second_colour & 0xff00ff) * factor & 0xff00ff00;
		return var5 + (var6 >> 7);
	}

	public static void reset_buffers() {
		// if (abovewater_stream != null) {
		// abovewater_stream.deallocate();
		// abovewater_stream = null;
		// }
		// if (underwater_stream != null) {
		// underwater_stream.deallocate();
		// abovewater_stream = null;
		// }
		abovewater_stream = null;
		underwater_stream = null;
		bake_stream = null;
	}

}
